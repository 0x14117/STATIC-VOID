"""
Sandboxed compile-and-run for player-submitted Java code.

Same threat model as the old Python sandbox.py (single local player,
not multi-tenant, never expose this to untrusted network input) but a
different mechanism, because Java has no exec()/ast equivalent:

  1. A source-text scan (inspect_code) rejects dangerous APIs before
     anything compiles — Runtime, ProcessBuilder, network access,
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

File I/O (java.io.File, FileReader/Writer, BufferedReader/Writer) is
ALLOWED, not blocked — the LJMU syllabus this game follows has a whole
topic on it, so blocking it outright would leave a real course gap.
The safety story instead: every run happens inside a fresh, unique
temp directory that's deleted the moment the run finishes (see
run_java's finally block), so relative filenames like "data.txt" can
only ever touch that ephemeral sandbox. What's blocked is any string
literal that looks like it's trying to leave that directory — an
absolute path (starts with / or a drive letter) or a ".." traversal
segment — via _find_dangerous_path_literal below. This is a text scan
on string literals, same caveat as the BLOCKED_PATTERNS scan: it's
defense in depth, not a guarantee, and process isolation (a fresh
directory that's wiped after every run either way) is still the real
backstop even if a literal-obfuscation trick slipped past it.
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
    "java.nio", "java.net", "Socket", "URLConnection", "URLClassLoader",
    "reflect", "Class.forName", "ClassLoader", "sun.misc.Unsafe", "Unsafe",
    "ScriptEngine", "ProcessHandle",
]

# Matches Java string literals (handles \" escapes inside them).
_STRING_LITERAL_RE = re.compile(r'"((?:[^"\\]|\\.)*)"')


def _find_dangerous_path_literal(source):
    """Return the first string literal that looks like it's trying to
    leave the sandboxed working directory (absolute path or ".."
    traversal), or None. File I/O is allowed, but only within the
    per-run temp directory — no legitimate mission needs a literal
    like "/etc/passwd" or "../../secrets.txt"."""
    for match in _STRING_LITERAL_RE.finditer(source):
        literal = match.group(1)
        if literal.startswith("/") or literal.startswith("~") or ".." in literal or re.match(r"^[A-Za-z]:[\\/]", literal):
            return literal
    return None


class JavaSandboxError(Exception):
    pass


def inspect_code(source):
    """Return a violation dict {'pattern'} if code is unsafe, else None."""
    for pattern in BLOCKED_PATTERNS:
        if pattern in source:
            return {"pattern": pattern}
    dangerous_path = _find_dangerous_path_literal(source)
    if dangerous_path is not None:
        return {"pattern": dangerous_path, "kind": "path"}
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


def _capture_workdir_files(workdir):
    """Read back the files sitting in the run's temp directory after
    execution, so a File I/O mission's checker can verify what the
    player's code actually WROTE — not just what it claimed on stdout.
    Without this, a "write ACCESS GRANTED to a file" mission could be
    passed by printing a success message and never touching FileWriter.
    Main.java and the compiled .class files are excluded (they're ours,
    not the player's output)."""
    files = {}
    try:
        names = os.listdir(workdir)
    except OSError:
        return files
    for name in names:
        if name == "Main.java" or name.endswith(".class"):
            continue
        path = os.path.join(workdir, name)
        if not os.path.isfile(path):
            continue
        try:
            with open(path, "r", errors="replace") as f:
                files[name] = f.read()
        except OSError:
            pass
    return files


def _friendly_compile_error(stderr):
    if "class Main is public, should be declared in a file named" in stderr or \
       re.search(r"class \w+ is public, should be declared", stderr):
        return "Your top-level class must be exactly 'public class Main' — Java requires the class name to match the file."
    lines = _clean_stderr_lines(stderr)
    return lines[0] if lines else "Compilation failed."


def run_java(source, input_values=None, run_args=None, seed_files=None):
    """Compile and run player Java source. Returns a dict:
      {ok, blocked, timeout, output, files, error}
    error is None on success, else {'type', 'message'}. files is a
    {filename: contents} dict of whatever the player's code left in the
    run directory, so File I/O missions can verify what was actually
    written rather than trusting a printed success message.

    source is written verbatim to Main.java, so it must define
    `public class Main` with a `public static void main(String[] args)`.

    seed_files, if given, is a {filename: contents} dict written into the
    same per-run temp directory before compiling — used by File I/O
    missions that ask the player to read a file that needs to already
    exist. Filenames must be simple (no path separators), since they land
    directly in the sandboxed workdir.
    """
    violation = inspect_code(source)
    if violation:
        if violation.get("kind") == "path":
            message = "File paths must be simple filenames like \"data.txt\" — no absolute paths or \"..\" allowed. Found: \"{}\"".format(violation["pattern"])
        else:
            message = "'{}' is blocked. Null Sector code runs sandboxed — no system access.".format(violation["pattern"])
        return {
            "ok": False,
            "blocked": True,
            "timeout": False,
            "output": "",
            "files": {},
            "error": {"type": "SecurityViolation", "message": message},
        }

    workdir = tempfile.mkdtemp(prefix="nullsector_java_")
    try:
        for filename, contents in (seed_files or {}).items():
            with open(os.path.join(workdir, filename), "w") as f:
                f.write(contents)

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
                "ok": False, "blocked": False, "timeout": True, "output": "", "files": {},
                "error": {"type": "TimeoutError", "message": "Compilation took too long."},
            }

        if compile_result.returncode != 0:
            return {
                "ok": False, "blocked": False, "timeout": False, "output": "", "files": {},
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
                "ok": False, "blocked": False, "timeout": True, "output": "", "files": {},
                "error": {"type": "TimeoutError", "message": "Execution exceeded {} seconds. That loop has no exit.".format(RUN_TIMEOUT_SECONDS)},
            }

        stdout, truncated = _cap_output(run_result.stdout)
        workdir_files = _capture_workdir_files(workdir)

        if run_result.returncode != 0:
            clean_lines = _clean_stderr_lines(run_result.stderr)
            message = clean_lines[0] if clean_lines else "Runtime error."
            return {
                "ok": False, "blocked": False, "timeout": False, "output": stdout,
                "files": workdir_files,
                "error": {"type": "RuntimeError", "message": message},
            }

        if truncated:
            return {
                "ok": False, "blocked": False, "timeout": False, "output": stdout,
                "files": workdir_files,
                "error": {
                    "type": "OutputTooLarge",
                    "message": "Your program printed way more than any mission needs ({}+ characters). Check for a loop that never stops printing.".format(MAX_OUTPUT_CHARS),
                },
            }

        return {
            "ok": True, "blocked": False, "timeout": False,
            "output": stdout, "files": workdir_files, "error": None,
        }
    finally:
        shutil.rmtree(workdir, ignore_errors=True)
