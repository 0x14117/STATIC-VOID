"""
Standalone checks for java_sandbox.py — no test framework, just asserts.
Run: python3 tests/test_java_sandbox.py
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from java_sandbox import run_java

passed = 0
failed = 0


def check(name, condition, detail=""):
    global passed, failed
    if condition:
        passed += 1
        print("PASS: {}".format(name))
    else:
        failed += 1
        print("FAIL: {}  {}".format(name, detail))


HELLO = '''public class Main {
    public static void main(String[] args) {
        System.out.println("ACCESS GRANTED");
    }
}'''

# 1. Legitimate code compiles and runs
r = run_java(HELLO)
check("legitimate code compiles and runs", r["ok"] is True and "ACCESS GRANTED" in r["output"], r)

# 2. Variables, math, string concat work
VARS = '''public class Main {
    public static void main(String[] args) {
        int a = 12;
        int b = 7;
        String name = "Cipher";
        System.out.println(name + " clearance " + (a + b));
    }
}'''
r = run_java(VARS)
check("variables/math/concat work", r["ok"] is True and "Cipher clearance 19" in r["output"], r)

# 3. Scanner-based input works
SCANNER = '''import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("hello " + name);
    }
}'''
r = run_java(SCANNER, input_values=["Agent47"])
check("Scanner input works", r["ok"] is True and "hello Agent47" in r["output"], r)

# 4. Compile errors are reported cleanly, not a crash
BROKEN = '''public class Main {
    public static void main(String[] args) {
        System.out.println("missing semicolon")
    }
}'''
r = run_java(BROKEN)
check("compile errors reported cleanly", r["ok"] is False and r["error"]["type"] == "CompileError", r)
check("compile error message has no leaked server temp-dir path", "/tmp/" not in r["error"]["message"] and "nullsector_java_" not in r["error"]["message"], r)
check("compile error message references Main.java directly", r["error"]["message"].startswith("Main.java:"), r)

# 4b. Reassigning a final variable is a real compile error (this is what the
# Java version's Topic 2 teaching content claims — verify it's actually true)
REASSIGN_FINAL = '''public class Main {
    public static void main(String[] args) {
        final int X = 1;
        X = 2;
        System.out.println(X);
    }
}'''
r = run_java(REASSIGN_FINAL)
check("reassigning a final variable fails to compile", r["ok"] is False and r["error"]["type"] == "CompileError" and "final" in r["error"]["message"], r)

# 5. Wrong class name gets a friendly hint, not a raw javac dump
WRONG_CLASS = '''public class Foo {
    public static void main(String[] args) {
        System.out.println("hi");
    }
}'''
r = run_java(WRONG_CLASS)
check("wrong class name gets friendly hint", r["ok"] is False and "public class Main" in r["error"]["message"], r)

# 6. Blocked APIs are rejected before compiling
for pattern, snippet in [
    ("Runtime", 'Runtime.getRuntime().exec("whoami");'),
    ("ProcessBuilder", 'new ProcessBuilder("ls").start();'),
    ("java.io.File", 'java.io.File f = new java.io.File("/etc/passwd");'),
    ("java.net", 'java.net.Socket s = null;'),
]:
    code = '''public class Main {
    public static void main(String[] args) throws Exception {
        %s
        System.out.println("should not run");
    }
}''' % snippet
    r = run_java(code)
    check("blocks `{}`".format(pattern), r["blocked"] is True and "should not run" not in r["output"], r)

# 7. Infinite loop times out instead of hanging the server
INFINITE = '''public class Main {
    public static void main(String[] args) {
        while (true) {}
    }
}'''
r = run_java(INFINITE)
check("infinite loop times out", r["timeout"] is True, r)

# 8. Runtime exceptions surface as a normal failure, not a crash
DIVIDE_ZERO = '''public class Main {
    public static void main(String[] args) {
        System.out.println("before");
        int x = 1 / 0;
    }
}'''
r = run_java(DIVIDE_ZERO)
check("runtime exceptions surface normally", r["ok"] is False and r["blocked"] is False and r["timeout"] is False
      and "before" in r["output"] and r["error"]["type"] == "RuntimeError", r)

print("\n{} passed, {} failed".format(passed, failed))
sys.exit(1 if failed else 0)
