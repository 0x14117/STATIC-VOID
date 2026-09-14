"""
Standalone checks for sandbox.py — no test framework, just asserts.
Run: python3 tests/test_sandbox.py
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from sandbox import run_sandboxed

passed = 0
failed = 0


def check(name, condition):
    global passed, failed
    if condition:
        passed += 1
        print("PASS: {}".format(name))
    else:
        failed += 1
        print("FAIL: {}".format(name))


def main():
    # 1. Normal code runs and produces output
    r = run_sandboxed("print('ACCESS GRANTED')")
    check("legitimate code runs", r["ok"] is True and "ACCESS GRANTED" in r["output"])

    # 2. Basic language features used by real missions work under the whitelist
    r = run_sandboxed(
        "name = 'Cipher'\n"
        "level = 5\n"
        "print(f'{name} clearance {level}')\n"
        "nums = [1, 2, 3]\n"
        "print(sum(nums), sorted(nums, reverse=True))\n"
    )
    check("f-strings / lists / sorted work", r["ok"] is True and "Cipher clearance 5" in r["output"])

    # 3. input() is patched and deterministic
    r = run_sandboxed("name = input()\nprint('hello ' + name)", input_values=["Agent47"])
    check("patched input() works", r["ok"] is True and "hello Agent47" in r["output"])

    # 4. Blocked imports are rejected before execution
    for module in ["os", "subprocess", "socket", "sys", "shutil"]:
        r = run_sandboxed("import {}\nprint('should not run')".format(module))
        check("blocks `import {}`".format(module), r["blocked"] is True and "should not run" not in r["output"])

    r = run_sandboxed("from os import path")
    check("blocks `from os import path`", r["blocked"] is True)

    # 5. eval/exec/__import__/compile/open are rejected
    for expr in ["eval('1+1')", "exec('x=1')", "__import__('os')", "compile('1','<s>','eval')", "open('/etc/passwd')"]:
        r = run_sandboxed("{}\nprint('should not run')".format(expr))
        check("blocks `{}`".format(expr), r["blocked"] is True and "should not run" not in r["output"])

    # 6. Reaching for __builtins__ directly is rejected
    r = run_sandboxed("print(__builtins__)")
    check("blocks direct `__builtins__` access", r["blocked"] is True)

    # 7. Infinite loop times out instead of hanging the server
    r = run_sandboxed("while True:\n    pass")
    check("infinite loop times out", r["timeout"] is True)

    # 8. Runtime errors surface normally (not blocked, not timeout, just a normal failure)
    r = run_sandboxed("print(1 / 0)")
    check("runtime errors surface normally", r["ok"] is False and r["blocked"] is False and r["timeout"] is False
          and r["error"]["type"] == "ZeroDivisionError")

    # 9. Output from before a crash is still captured
    r = run_sandboxed("print('before')\nprint(undefined_variable)")
    check("partial output captured before a crash", "before" in r["output"] and r["ok"] is False)

    print("\n{} passed, {} failed".format(passed, failed))
    sys.exit(1 if failed else 0)


if __name__ == "__main__":
    main()
