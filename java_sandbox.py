"""
Sandboxed compile-and-run for player-submitted Java code.

Same threat model as the old Python sandbox.py (single local player,
not multi-tenant, never expose this to untrusted network input) but a
different mechanism, because Java has no exec()/ast equivalent:

  1. A source-text scan (inspect_code) rejects dangerous APIs before
     anything compiles — Runtime, ProcessBuilder, file/network access,
     reflection — with a Cipher-style message. There is no Java parser
     in the Python stdlib, so this is a substring/regex scan rather
     than a real AST walk (same spirit as the Python version's AST
     check, weaker guarantee — see layer 3 below for the real backstop).
  2. The player's class is always compiled as Main.java in a fresh
     temp directory (javac), so nothing touches the project tree.
  3. Execution happens in a separate `java` process with a wall-clock
     timeout and a JVM heap cap (-Xmx), so a slip past 1 can't hang or
     memory-bomb the server. This process isolation + timeout is the
     real backstop, same as the Python sandbox.
"""

import re
import shutil
import subprocess
import tempfile
import os

COMPILE_TIMEOUT_SECONDS = 15
RUN_TIMEOUT_SECONDS = 8
MAX_HEAP = "128m"

# Substring scan, not a real parser — case-sensitive on purpose (these
# are all real API names, lowercase versions won't false-positive on
# a student's variable named e.g. "runtime").
BLOCKED_PATTERNS = [
    "Runtime", "ProcessBuilder", "System.exit", "System.load", "System.getenv",
    "java.io.File", "FileReader", "FileWriter", "FileInputStream", "FileOutputStream",
    "java.nio", "java.net", "Socket", "URLConnection", "URLClassLoader",
    "reflect", "Class.forName", "ClassLoader", "sun.misc.Unsafe", "Unsafe",
    "ScriptEngine", "ProcessHandle",
]


class JavaSandboxError(Exception):
    pass


def inspect_code(source):
    """Return a violation dict {'pattern'} if code is unsafe, else None."""
    for pattern in BLOCKED_PATTERNS:
        if pattern in source:
            return {"pattern": pattern}
    return None


def _friendly_compile_error(stderr):
    if "class Main is public, should be declared in a file named" in stderr or \
       re.search(r"class \w+ is public, should be declared", stderr):
        return "Your top-level class must be exactly 'public class Main' — Java requires the class name to match the file."
    first_line = stderr.strip().splitlines()[0] if stderr.strip() else "Compilation failed."
    return first_line


def run_java(source, input_values=None, run_args=None):
    """Compile and run player Java source. Returns a dict:
      {ok, blocked, timeout, output, error}
    error is None on success, else {'type', 'message'}.

    source is written verbatim to Main.java, so it must define
    `public class Main` with a `public static void main(String[] args)`.
    """
    violation = inspect_code(source)
    if violation:
        return {
            "ok": False,
            "blocked": True,
            "timeout": False,
            "output": "",
            "error": {
                "type": "SecurityViolation",
                "message": "'{}' is blocked. Null Sector code runs sandboxed — no system access.".format(violation["pattern"]),
            },
        }

    workdir = tempfile.mkdtemp(prefix="nullsector_java_")
    try:
        source_path = os.path.join(workdir, "Main.java")
        with open(source_path, "w") as f:
            f.write(source)

        try:
            compile_result = subprocess.run(
                ["javac", "-d", workdir, source_path],
                cwd=workdir,
                capture_output=True,
                text=True,
                timeout=COMPILE_TIMEOUT_SECONDS,
            )
        except subprocess.TimeoutExpired:
            return {
                "ok": False, "blocked": False, "timeout": True, "output": "",
                "error": {"type": "TimeoutError", "message": "Compilation took too long."},
            }

        if compile_result.returncode != 0:
            return {
                "ok": False, "blocked": False, "timeout": False, "output": "",
                "error": {"type": "CompileError", "message": _friendly_compile_error(compile_result.stderr)},
            }

        stdin_text = "\n".join(input_values) + "\n" if input_values else ""
        args = run_args or []

        try:
            run_result = subprocess.run(
                ["java", "-Xmx" + MAX_HEAP, "-cp", workdir, "Main"] + args,
                cwd=workdir,
                input=stdin_text,
                capture_output=True,
                text=True,
                timeout=RUN_TIMEOUT_SECONDS,
            )
        except subprocess.TimeoutExpired:
            return {
                "ok": False, "blocked": False, "timeout": True, "output": "",
                "error": {"type": "TimeoutError", "message": "Execution exceeded {} seconds. That loop has no exit.".format(RUN_TIMEOUT_SECONDS)},
            }

        if run_result.returncode != 0:
            stderr_first_line = run_result.stderr.strip().splitlines()[0] if run_result.stderr.strip() else "Runtime error."
            return {
                "ok": False, "blocked": False, "timeout": False, "output": run_result.stdout,
                "error": {"type": "RuntimeError", "message": stderr_first_line},
            }

        return {
            "ok": True, "blocked": False, "timeout": False,
            "output": run_result.stdout, "error": None,
        }
    finally:
        shutil.rmtree(workdir, ignore_errors=True)
