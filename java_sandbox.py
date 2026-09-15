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
MAX_OUTPUT_CHARS = 200_000  # generous for any real mission (a few hundred bytes); guards a print-flood loop

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


def _clean_stderr_lines(stderr):
    """Strip JVM/launcher diagnostic noise that isn't the actual error —
    e.g. "Picked up JAVA_TOOL_OPTIONS: ..." printed whenever that env var
    is set (common behind a proxy, e.g. a school lab or corporate laptop).
    Without this, that noise — which can include internal proxy config —
    would be mistaken for the real compiler/runtime error. Also strips the
    server's own temp-directory path off the front of javac's file
    references, leaving just "Main.java:4:" — the player never sees our
    filesystem layout, only their own file and line number."""
    lines = stderr.strip().splitlines()
    lines = [line for line in lines if not line.startswith("Picked up ")]
    return [re.sub(r"^/\S*/Main\.java:", "Main.java:", line) for line in lines]


def _cap_output(text):
    """Truncate captured stdout so a print-flood loop (e.g. `while(true)
    System.out.println("A");`) can't send megabytes back to the browser or
    balloon the mission-check string comparison. This runs AFTER
    subprocess.run() has already buffered the full output in this
    process's memory — it protects downstream consumers (HTTP payload,
    JSON, the browser), not peak memory during the run itself. That's an
    acceptable gap for this game's threat model (a single local player on
    their own machine, bounded by RUN_TIMEOUT_SECONDS either way); a truly
    adversarial/multi-tenant setting would need a streaming read with an
    early kill instead."""
    if len(text) <= MAX_OUTPUT_CHARS:
        return text, False
    return text[:MAX_OUTPUT_CHARS], True


def _friendly_compile_error(stderr):
    if "class Main is public, should be declared in a file named" in stderr or \
       re.search(r"class \w+ is public, should be declared", stderr):
        return "Your top-level class must be exactly 'public class Main' — Java requires the class name to match the file."
    lines = _clean_stderr_lines(stderr)
    return lines[0] if lines else "Compilation failed."


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

        stdout, truncated = _cap_output(run_result.stdout)

        if run_result.returncode != 0:
            clean_lines = _clean_stderr_lines(run_result.stderr)
            message = clean_lines[0] if clean_lines else "Runtime error."
            return {
                "ok": False, "blocked": False, "timeout": False, "output": stdout,
                "error": {"type": "RuntimeError", "message": message},
            }

        if truncated:
            return {
                "ok": False, "blocked": False, "timeout": False, "output": stdout,
                "error": {
                    "type": "OutputTooLarge",
                    "message": "Your program printed way more than any mission needs ({}+ characters). Check for a loop that never stops printing.".format(MAX_OUTPUT_CHARS),
                },
            }

        return {
            "ok": True, "blocked": False, "timeout": False,
            "output": stdout, "error": None,
        }
    finally:
        shutil.rmtree(workdir, ignore_errors=True)
