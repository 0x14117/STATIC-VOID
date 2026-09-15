"""
Shared building blocks for labs.

Every checker factory returns a function with the same shape —
check(code, output, files) -> (passed, message) — so the server never has to
know which kind of task it is grading.

Grading philosophy (docs/02-LAB-SPEC.md): grade what the program DID, not what
its source looks like. Source requirements are a narrow exception for things
output cannot prove — "did they really use a Scanner rather than hardcode the
answer", "did they really use .equals() where == happens to agree".
"""


def _source_failure(code, requires, hint):
    """Each entry is a string that must appear in the submission, or a
    tuple/list of alternatives of which at least one must appear."""
    if not requires:
        return None
    for token in requires:
        if isinstance(token, (list, tuple)):
            if not any(alt in code for alt in token):
                return hint or "Your code needs one of {} here.".format(token)
        elif token not in code:
            return hint or "Your code needs to use '{}' here.".format(token)
    return None


def _shares_a_line(expected, got):
    """"Close" means a whole expected LINE appears in the actual output — not
    substring containment, which would call a wrong "136" a near miss on an
    expected "36" just because "36" sits inside it."""
    return bool(set(expected.split("\n")) & set(got.split("\n")))


def make_checker(expected_output, wrong_hint, close_hint=None,
                 requires=None, requires_hint=None):
    """Compare captured stdout against an exact string, ignoring only the
    trailing newline.

    wrong_hint  shown when the output is nowhere near right
    close_hint  shown when at least one line matched — defaults to wrong_hint
    """

    def check(code, output, files=None):
        failure = _source_failure(code, requires, requires_hint)
        if failure:
            return False, failure

        got = output.rstrip("\n")
        if got == expected_output:
            return True, "Correct."
        if _shares_a_line(expected_output, got):
            return False, close_hint or wrong_hint
        return False, wrong_hint

    return check


def make_file_checker(filename, expected_contents, wrong_hint,
                      missing_hint=None, expected_output=None,
                      requires=None, requires_hint=None):
    """Grade the file the submission actually left in the sandbox, so a task
    that says "write this to a file" cannot be passed by printing a success
    message. Trailing newlines are ignored on both sides — whether the last
    line ends in a newline is a PrintWriter-vs-FileWriter detail, not the idea
    being taught."""

    def check(code, output, files=None):
        failure = _source_failure(code, requires, requires_hint)
        if failure:
            return False, failure

        files = files or {}
        if filename not in files:
            return False, missing_hint or (
                "I cannot find a file called {} after your program ran.".format(filename)
            )
        if files[filename].rstrip("\n") != expected_contents:
            return False, wrong_hint
        if expected_output is not None and output.rstrip("\n") != expected_output:
            return False, "The file is right, but the console output is not. Expected:\n{}".format(
                expected_output
            )
        return True, "Correct."

    return check


# Starter code constants. A starter must never already pass — the tests
# enforce that — so these are skeletons, not head starts.

MAIN_ONLY = (
    "public class Main {\n"
    "    public static void main(String[] args) {\n"
    "        // your code here\n"
    "    }\n"
    "}"
)

WITH_CLASS = (
    "class Account {\n"
    "    // fields go here\n"
    "}\n\n"
    "public class Main {\n"
    "    public static void main(String[] args) {\n"
    "        // your code here\n"
    "    }\n"
    "}"
)

WITH_SCANNER = (
    "import java.util.Scanner;\n\n"
    "public class Main {\n"
    "    public static void main(String[] args) {\n"
    "        // your code here\n"
    "    }\n"
    "}"
)

WITH_FILE_IO = (
    "import java.io.*;\n\n"
    "public class Main {\n"
    "    public static void main(String[] args) throws IOException {\n"
    "        // your code here\n"
    "    }\n"
    "}"
)
