"""
Shared building blocks for every topic's missions.

The checker factories here all return a function with the same shape —
check(code, output, files) -> (passed, cipher_line) — so the server never
has to care which kind of mission it is grading.

Grading philosophy, unchanged from the single-file version: missions are
judged on what the player's program actually DID (what it printed, what it
wrote to disk), not on what its source code looks like. code_requires is a
deliberate, narrow exception — some concepts genuinely can't be verified
from output alone ("did they actually write a comment", "did they use a
Scanner rather than hardcoding the answer"), and for those a light
substring check on the source is the honest option.
"""


def _code_requirements_failure(code, code_requires, code_requires_hint):
    """Shared by every checker below. Each entry is either a string that
    must appear in the submitted source, or a tuple/list of alternatives of
    which at least one must appear. Returns the hint to show, or None if
    the code satisfies every requirement."""
    if not code_requires:
        return None
    for token in code_requires:
        if isinstance(token, (list, tuple)):
            if not any(alt in code for alt in token):
                return code_requires_hint or "Your code needs one of {} for this one.".format(token)
        elif token not in code:
            return code_requires_hint or "Your code needs to use '{}' for this one.".format(token)
    return None


def _is_close(expected, got):
    """"Close" means at least one whole expected LINE shows up among the
    actual output lines — not raw substring containment, which would call a
    totally wrong "136" a near-miss on an expected "36" just because "36"
    happens to appear inside it."""
    return bool(set(expected.split("\n")) & set(got.split("\n")))


def make_checker(expected_output, success_line, missing_hint, mismatch_hint,
                 code_requires=None, code_requires_hint=None):
    """Compares captured stdout against an exact expected string (no
    trailing newline)."""

    def check(code, output, files=None):
        failure = _code_requirements_failure(code, code_requires, code_requires_hint)
        if failure:
            return False, failure

        got = output.rstrip("\n")
        if got == expected_output:
            return True, success_line
        if _is_close(expected_output, got):
            return False, mismatch_hint
        return False, missing_hint

    return check


def make_file_checker(filename, expected_contents, success_line, missing_hint,
                      mismatch_hint, expected_output=None, code_requires=None,
                      code_requires_hint=None):
    """For File I/O missions: grades the file the player's code actually
    left behind in the sandboxed run directory, not a printed claim that it
    wrote one. expected_output, if given, is additionally checked against
    stdout — for missions that both write a file and report on it.

    Trailing newlines are ignored on both sides: whether a student's last
    line ends with a newline is a PrintWriter-vs-FileWriter detail, not the
    concept being taught."""

    def check(code, output, files=None):
        failure = _code_requirements_failure(code, code_requires, code_requires_hint)
        if failure:
            return False, failure

        files = files or {}
        if filename not in files:
            return False, missing_hint

        written = files[filename].rstrip("\n")
        if written != expected_contents:
            return False, mismatch_hint

        if expected_output is not None:
            got = output.rstrip("\n")
            if got != expected_output:
                return False, (
                    "The file is correct, but the console output isn't. "
                    "Expected:\n{}".format(expected_output)
                )

        return True, success_line

    return check


def make_multiline_checker(expected_lines, success_line, missing_hint, mismatch_hint,
                           code_requires=None, code_requires_hint=None):
    """Convenience wrapper around make_checker for missions whose expected
    output is naturally written as a list of lines."""
    return make_checker(
        expected_output="\n".join(expected_lines),
        success_line=success_line,
        missing_hint=missing_hint,
        mismatch_hint=mismatch_hint,
        code_requires=code_requires,
        code_requires_hint=code_requires_hint,
    )


BASIC_BOILERPLATE = (
    "public class Main {\n"
    "    public static void main(String[] args) {\n"
    "        // YOUR CODE HERE\n"
    "    }\n"
    "}"
)

# Scanner missions need the import; the player still writes the Scanner
# line itself (repetition is the point — it's muscle memory they'll need
# in the exam), so only the import and skeleton are pre-filled.
SCANNER_BOILERPLATE = (
    "import java.util.Scanner;\n\n"
    "public class Main {\n"
    "    public static void main(String[] args) {\n"
    "        // YOUR CODE HERE\n"
    "    }\n"
    "}"
)

# File I/O missions need java.io; same reasoning as SCANNER_BOILERPLATE —
# the import is ceremony, the FileWriter/PrintWriter line is the lesson.
FILE_IO_BOILERPLATE = (
    "import java.io.*;\n\n"
    "public class Main {\n"
    "    public static void main(String[] args) throws IOException {\n"
    "        // YOUR CODE HERE\n"
    "    }\n"
    "}"
)

# Missions that define a class alongside Main (Topic 9) start from an empty
# editor by default, but a few need the two-class shape spelled out.
METHOD_BOILERPLATE = (
    "public class Main {\n"
    "    // define your method here\n\n"
    "    public static void main(String[] args) {\n"
    "        // call your method here\n"
    "    }\n"
    "}"
)
