"""
Compile and runtime errors must name the PLAYER's file, never the server's.

javac reports errors prefixed with the full path of the file it compiled,
which in this game is a temp directory belonging to the server. The player
should see "Main.java:4: error: ..." and nothing else — both because the
server's filesystem layout is none of their business, and because a wall of
temp path makes the line number harder to find.

The Windows cases here are the ones that actually broke: the original
pattern only matched Unix paths, so a Windows player saw the full
C:\\Users\\...\\AppData\\Local\\Temp\\... prefix on every compile error.

Run: python3 tests/test_error_paths.py
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from java_sandbox import _clean_stderr_lines, run_java

passed = 0
failures = []


def check(name, condition, detail=""):
    global passed
    if condition:
        passed += 1
    else:
        failures.append((name, detail))


CASES = [
    (
        "unix temp path is stripped",
        "/tmp/nullsector_java_ab12/Main.java:4: error: ';' expected",
        "Main.java:4: error: ';' expected",
    ),
    (
        "windows temp path is stripped",
        "C:\\Users\\afaq2\\AppData\\Local\\Temp\\nullsector_java_ab12\\Main.java:4: error: ';' expected",
        "Main.java:4: error: ';' expected",
    ),
    (
        "windows path containing a space is stripped",
        "C:\\Users\\Ada Lovelace\\AppData\\Local\\Temp\\nullsector_java_x\\Main.java:7: error: cannot find symbol",
        "Main.java:7: error: cannot find symbol",
    ),
    (
        "macos private temp path is stripped",
        "/var/folders/qr/T/nullsector_java_zz/Main.java:2: error: class, interface, enum, or record expected",
        "Main.java:2: error: class, interface, enum, or record expected",
    ),
    (
        "a line that is already clean is left alone",
        "Main.java:4: error: ';' expected",
        "Main.java:4: error: ';' expected",
    ),
]

for name, raw, expected in CASES:
    got = _clean_stderr_lines(raw)
    check(name, got == [expected], "got {!r}, wanted {!r}".format(got, [expected]))

# javac points at the offending token with an indented copy of the source
# line and a caret under it. That indentation is what makes the caret line
# up, so the cleaning must not disturb anything but the leading file path.
WINDOWS_BLOCK = (
    "C:\\Users\\afaq2\\AppData\\Local\\Temp\\nullsector_java_ab12\\Main.java:3: error: ';' expected\n"
    "        System.out.println(\"HI\")\n"
    "                               ^\n"
    "1 error"
)
check(
    "a full Windows javac block keeps its source line, caret and alignment",
    _clean_stderr_lines(WINDOWS_BLOCK) == [
        "Main.java:3: error: ';' expected",
        "        System.out.println(\"HI\")",
        "                               ^",
        "1 error",
    ],
    _clean_stderr_lines(WINDOWS_BLOCK),
)

check(
    "JAVA_TOOL_OPTIONS noise is dropped",
    _clean_stderr_lines("Picked up JAVA_TOOL_OPTIONS: -Dhttps.proxyHost=10.0.0.1\nMain.java:1: error: oops")
    == ["Main.java:1: error: oops"],
)

# End to end: a real compile failure through the real toolchain must not
# mention the temp directory the server compiled in.
BROKEN = '''public class Main {
    public static void main(String[] args) {
        System.out.println("MISSING SEMICOLON")
    }
}'''
result = run_java(BROKEN)
message = (result["error"] or {}).get("message", "")
check("real compile error names Main.java", "Main.java" in message, message)
check("real compile error leaks no temp path",
      "nullsector_java_" not in message and "/tmp" not in message.lower(), message)

print("{} checks passed, {} failed".format(passed, len(failures)))
for name, detail in failures:
    print("  FAIL: {}  {}".format(name, detail))
sys.exit(1 if failures else 0)
