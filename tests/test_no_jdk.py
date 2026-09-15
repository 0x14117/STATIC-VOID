"""
A machine with no JDK must be told so, clearly.

Python is easy to install and Java is a separate step, so "I installed Python
and it still does not work" is the single most likely first-run experience.
Before this was handled, the first submission died inside subprocess with
FileNotFoundError: [WinError 2] — a raw traceback in the server console and
nothing at all in the browser.

Run: python3 tests/test_no_jdk.py
"""
import importlib
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

passed = 0
failures = []


def check(name, condition, detail=""):
    global passed
    if condition:
        passed += 1
    else:
        failures.append((name, detail))


SAMPLE = "public class Main { public static void main(String[] args) {} }"

real_path = os.environ.get("PATH", "")
try:
    # A machine with no Java at all.
    os.environ["PATH"] = os.pathsep.join(["/nonexistent-for-tests"])
    import java_sandbox
    importlib.reload(java_sandbox)

    error = java_sandbox.missing_toolchain()
    check("missing toolchain is detected", error is not None)
    check("it is reported as NoJavaToolchain",
          error and error["type"] == "NoJavaToolchain", error)
    check("the message names javac", error and "javac" in error["message"])
    check("the message says where to get a JDK",
          error and "adoptium.net" in error["message"])
    check("the message explains a JRE is not enough",
          error and "JRE" in error["message"])
    check("the message says to open a new terminal",
          error and "new one" in error["message"])

    # The important one: a submission must NOT raise.
    raised = None
    try:
        result = java_sandbox.run_java(SAMPLE)
    except Exception as exc:                      # noqa: BLE001 - that is the point
        raised = exc
        result = None
    check("run_java does not raise without a JDK", raised is None, repr(raised))
    check("run_java reports failure cleanly",
          result is not None and result["ok"] is False
          and result["error"]["type"] == "NoJavaToolchain",
          result)
finally:
    os.environ["PATH"] = real_path
    importlib.reload(java_sandbox)

# With the real PATH back, the toolchain should be found again, otherwise
# every other test in this suite is meaningless.
check("the real toolchain is found again", java_sandbox.missing_toolchain() is None)

print("{} checks passed, {} failed".format(passed, len(failures)))
for name, detail in failures:
    print("  FAIL: {}  {}".format(name, detail))
sys.exit(1 if failures else 0)
