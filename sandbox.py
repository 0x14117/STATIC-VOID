"""
Sandboxed execution for player-submitted Python code.

Threat model: a single local player runs their own code against their own
machine (per NULL_SECTOR_Master_Document.pdf, section 09). This is NOT a
multi-tenant sandbox and must never be exposed to untrusted network input.
Defense is layered:

  1. AST inspection (inspect_code) rejects dangerous imports/calls before
     anything runs, with a Cipher-style message instead of a raw traceback.
  2. A whitelist __builtins__ (build_safe_builtins) removes file/eval/exec
     access even if the AST check misses something.
  3. Execution happens in a separate process (run_sandboxed) with a wall
     clock timeout and an address-space limit, so a slip past 1 and 2 still
     can't hang or crash the server.

Layers 1-2 do not claim to make arbitrary Python code fully escape-proof
(pure-Python sandboxes are famously breakable via object introspection);
layer 3's process isolation + timeout is the real backstop.
"""

import ast
import contextlib
import io
import multiprocessing

TIMEOUT_SECONDS = 5
MAX_MEMORY_BYTES = 256 * 1024 * 1024  # 256MB

BLOCKED_MODULES = {
    "os", "sys", "subprocess", "socket", "shutil", "ctypes",
    "importlib", "multiprocessing", "threading", "pickle",
    "marshal", "shelve", "sqlite3", "http", "urllib", "ftplib",
    "telnetlib", "smtplib", "asyncio", "signal", "resource",
    "tempfile", "glob", "inspect", "gc", "pathlib", "platform",
}

BLOCKED_CALLS = {"eval", "exec", "__import__", "compile", "open"}

SAFE_BUILTIN_NAMES = [
    "print", "int", "float", "str", "bool",
    "len", "range", "round", "abs", "max", "min", "sum",
    "list", "dict", "tuple", "set", "frozenset",
    "sorted", "reversed", "enumerate", "zip", "map", "filter",
    "isinstance", "type", "all", "any", "divmod", "pow", "chr", "ord",
    "True", "False", "None",
    "Exception", "ValueError", "TypeError", "KeyError",
    "IndexError", "ZeroDivisionError", "AttributeError",
    "StopIteration", "NameError", "RuntimeError",
]


def inspect_code(code):
    """Return a violation dict {'line', 'module'} if code is unsafe, else None.

    Syntax errors are NOT reported here — they're left for exec() to raise
    naturally so the normal error-explanation path handles them.
    """
    try:
        tree = ast.parse(code)
    except SyntaxError:
        return None

    for node in ast.walk(tree):
        if isinstance(node, ast.Import):
            for alias in node.names:
                root = alias.name.split(".")[0]
                if root in BLOCKED_MODULES:
                    return {"line": node.lineno, "module": root}
        elif isinstance(node, ast.ImportFrom):
            root = (node.module or "").split(".")[0]
            if root in BLOCKED_MODULES:
                return {"line": node.lineno, "module": root}
        elif isinstance(node, ast.Call):
            fn = node.func
            if isinstance(fn, ast.Name) and fn.id in BLOCKED_CALLS:
                return {"line": node.lineno, "module": fn.id}
        elif isinstance(node, ast.Name) and node.id == "__builtins__":
            return {"line": node.lineno, "module": "__builtins__"}

    return None


def build_safe_builtins():
    import builtins as _b
    return {name: getattr(_b, name) for name in SAFE_BUILTIN_NAMES if hasattr(_b, name)}


def _apply_resource_limits():
    try:
        import resource
        resource.setrlimit(resource.RLIMIT_AS, (MAX_MEMORY_BYTES, MAX_MEMORY_BYTES))
        resource.setrlimit(resource.RLIMIT_CPU, (TIMEOUT_SECONDS + 1, TIMEOUT_SECONDS + 1))
    except (ImportError, ValueError, OSError):
        pass  # not available on this platform (e.g. Windows); timeout still protects us


def _worker(code, input_values, result_queue):
    _apply_resource_limits()

    output = io.StringIO()
    input_iter = iter(input_values or [])

    def patched_input(prompt=""):
        try:
            return next(input_iter)
        except StopIteration:
            return ""

    safe_builtins = build_safe_builtins()
    safe_builtins["input"] = patched_input
    exec_globals = {"__builtins__": safe_builtins}

    try:
        with contextlib.redirect_stdout(output):
            exec(code, exec_globals)
        result_queue.put({"ok": True, "output": output.getvalue(), "error": None})
    except Exception as e:
        result_queue.put({
            "ok": False,
            "output": output.getvalue(),
            "error": {"type": type(e).__name__, "message": str(e)},
        })


def run_sandboxed(code, input_values=None):
    """Run player code safely. Returns a dict:
      {ok, blocked, timeout, output, error}
    error is None on success, else {'type', 'message', 'line'?}.
    """
    violation = inspect_code(code)
    if violation:
        return {
            "ok": False,
            "blocked": True,
            "timeout": False,
            "output": "",
            "error": {
                "type": "SecurityViolation",
                "message": "'{}' is blocked. Null Sector code runs sandboxed — no system access.".format(violation["module"]),
                "line": violation["line"],
            },
        }

    ctx = multiprocessing.get_context("spawn")
    result_queue = ctx.Queue()
    proc = ctx.Process(target=_worker, args=(code, input_values, result_queue))
    proc.start()
    proc.join(TIMEOUT_SECONDS)

    if proc.is_alive():
        proc.terminate()
        proc.join()
        return {
            "ok": False,
            "blocked": False,
            "timeout": True,
            "output": "",
            "error": {
                "type": "TimeoutError",
                "message": "Execution exceeded {} seconds. That loop has no exit.".format(TIMEOUT_SECONDS),
            },
        }

    try:
        result = result_queue.get_nowait()
    except Exception:
        result = {
            "ok": False,
            "output": "",
            "error": {"type": "RuntimeError", "message": "Process terminated unexpectedly."},
        }

    result.setdefault("blocked", False)
    result.setdefault("timeout", False)
    return result
