"""
Checks that what the teaching text CLAIMS about Java is actually true.

A mission's teach block makes specific factual promises — int division
truncates, next() stops at a space, reassigning a final won't compile,
nextInt() followed by nextLine() silently hands you an empty string. Those
claims were written by hand, and a wrong one teaches a student something
false that they'll carry into an exam. So each is a tiny program run
through the real JDK here, rather than text we wrote and hoped was right.

Add a claim to this table whenever a teach block asserts how Java behaves.

Run: python3 tests/test_teaching_claims.py
"""
import os
import sys
from concurrent.futures import ThreadPoolExecutor

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from java_sandbox import run_java
from missions import check_mission

WORKERS = int(os.environ.get("STATIC_VOID_TEST_WORKERS", "8"))


def program(body, imports="", header="public class Main {\n    public static void main(String[] args) {\n"):
    return imports + header + body + "\n    }\n}"


CLAIMS = [
    # ---- Topic 1 / Chapter 1: structure, printing, if, while -----------
    {
        "topic": 1,
        "name": "a semicolon after if(test) detaches the block, which then always runs",
        "code": program('        int failures = 1;\n'
                        '        if (failures > 5);\n'
                        '        {\n'
                        '            System.out.println("ALERT");\n'
                        '        }\n'
                        '        System.out.println("DONE");'),
        "expect_output": "ALERT\nDONE",
    },
    {
        "topic": 1,
        "name": "removing that semicolon makes the block obey the test",
        "code": program('        int failures = 1;\n'
                        '        if (failures > 5) {\n'
                        '            System.out.println("ALERT");\n'
                        '        }\n'
                        '        System.out.println("DONE");'),
        "expect_output": "DONE",
    },
    {
        "topic": 1,
        "name": "a while loop whose counter never changes does not end",
        "code": program('        int scan = 1;\n'
                        '        while (scan <= 3) {\n'
                        '            System.out.println("SCANNING " + scan);\n'
                        '        }'),
        "expect_timeout": True,
    },
    {
        "topic": 1,
        "name": "count++ does the same thing as count = count + 1",
        "code": program('        int a = 5;\n'
                        '        int b = 5;\n'
                        '        a++;\n'
                        '        b = b + 1;\n'
                        '        System.out.println(a + " " + b);'),
        "expect_output": "6 6",
    },
    {
        "topic": 1,
        "name": "+ joins text and adds no space of its own",
        "code": program('        System.out.println("USER:" + "jsmith");'),
        "expect_output": "USER:jsmith",
    },
    {
        "topic": 1,
        "name": "print does not move to the next line, println does",
        "code": program('        System.out.print("HOST: ");\n'
                        '        System.out.println("gateway-02");'),
        "expect_output": "HOST: gateway-02",
    },
    {
        "topic": 1,
        "name": "lowercase system does not compile",
        "code": program('        system.out.println("hello");'),
        "expect_compile_error": True,
    },
    {
        "topic": 1,
        "name": "printLine is not a method and does not compile",
        "code": program('        System.out.printLine("hello");'),
        "expect_compile_error": True,
    },
    {
        "topic": 1,
        "name": "a loop tested with < stops one short of a loop tested with <=",
        "code": program('        int i = 1;\n'
                        '        int countLess = 0;\n'
                        '        while (i < 5) { countLess++; i++; }\n'
                        '        int j = 1;\n'
                        '        int countUpTo = 0;\n'
                        '        while (j <= 5) { countUpTo++; j++; }\n'
                        '        System.out.println(countLess + " " + countUpTo);'),
        "expect_output": "4 5",
    },
    # ---- Topic 2: constants -------------------------------------------
    {
        "topic": 2,
        "name": "reassigning a final variable fails to compile",
        "code": program('        final int CLEARANCE_LEVEL = 9;\n        CLEARANCE_LEVEL = 10;\n        System.out.println(CLEARANCE_LEVEL);'),
        "expect_compile_error": True,
    },
    # ---- Topic 3: arithmetic ------------------------------------------
    {
        "topic": 3,
        "name": "int/int truncates: 10/3 is 3, not 3.33",
        "code": program('        System.out.println(10 / 3);'),
        "expect_output": "3",
    },
    {
        "topic": 3,
        "name": "casting the whole expression, (double)(x/y), still truncates first",
        "code": program('        int x = 10;\n        int y = 3;\n        System.out.println((double) (x / y));'),
        "expect_output": "3.0",
    },
    {
        "topic": 3,
        "name": "casting one operand, (double) x / y, gives the real answer",
        "code": program('        int x = 10;\n        int y = 3;\n        System.out.println((double) x / y);'),
        "expect_output": "3.3333333333333335",
    },
    {
        "topic": 3,
        "name": "the '=+' typo compiles but means assignment, not increment",
        "code": program('        int score = 10;\n        score =+ 5;\n        System.out.println(score);'),
        "expect_output": "5",
    },
    # ---- Topic 5: Scanner ---------------------------------------------
    {
        "topic": 5,
        "name": "next() stops at the first space",
        "code": program('        Scanner sc = new Scanner(System.in);\n        System.out.println(sc.next());',
                        imports="import java.util.Scanner;\n\n"),
        "inputs": ["alpha bravo charlie"],
        "expect_output": "alpha",
    },
    {
        "topic": 5,
        "name": "nextLine() takes the whole line, spaces included",
        "code": program('        Scanner sc = new Scanner(System.in);\n        System.out.println(sc.nextLine());',
                        imports="import java.util.Scanner;\n\n"),
        "inputs": ["alpha bravo charlie"],
        "expect_output": "alpha bravo charlie",
    },
    {
        "topic": 5,
        "name": "nextInt() on decimal input throws rather than rounding",
        "code": program('        Scanner sc = new Scanner(System.in);\n        System.out.println(sc.nextInt());',
                        imports="import java.util.Scanner;\n\n"),
        "inputs": ["36.6"],
        "expect_runtime_error": "InputMismatch",
    },
    {
        "topic": 5,
        "name": "omitting the java.util.Scanner import is a compile error",
        "code": program('        Scanner sc = new Scanner(System.in);\n        System.out.println(sc.nextInt());'),
        "inputs": ["7"],
        "expect_compile_error": True,
    },
    {
        "topic": 5,
        "name": "nextInt() then nextLine() really does hand back an empty string",
        "code": program('        Scanner sc = new Scanner(System.in);\n'
                        '        int clearance = sc.nextInt();\n'
                        '        String codename = sc.nextLine();\n'
                        '        System.out.println("CLEARANCE: " + clearance);\n'
                        '        System.out.println("CODENAME: " + codename);',
                        imports="import java.util.Scanner;\n\n"),
        "inputs": ["7", "Ghost Protocol"],
        "expect_output": "CLEARANCE: 7\nCODENAME: ",
        # the mission that teaches the trap must also REJECT the trapped version
        "must_fail_mission": "t5m5",
    },
]

passed = 0
failures = []


def verify(claim):
    result = run_java(claim["code"], input_values=claim.get("inputs"))
    name = "T{} {}".format(claim["topic"], claim["name"])

    if claim.get("expect_compile_error"):
        ok = result["error"] is not None and result["error"]["type"] == "CompileError"
        return [(name, ok, result.get("error"))]

    if claim.get("expect_runtime_error"):
        ok = (result["error"] is not None
              and claim["expect_runtime_error"] in result["error"]["message"])
        return [(name, ok, result.get("error"))]

    if claim.get("expect_timeout"):
        # "The loop never ends" can only be proved by letting one not end. The
        # sandbox's wall-clock timeout is what stops it, which is also what the
        # lab promises the learner will happen.
        return [(name, result["timeout"] is True, result.get("error"))]

    if not result["ok"]:
        return [(name, False, result.get("error"))]

    results = [(name, result["output"].rstrip("\n") == claim["expect_output"],
                "got {!r}".format(result["output"]))]

    mission_id = claim.get("must_fail_mission")
    if mission_id:
        accepted, reason = check_mission(mission_id, claim["code"], result["output"], result.get("files"))
        results.append(("{} rejects the behaviour it warns about".format(mission_id),
                        not accepted, reason))
    return results


def main():
    global passed
    with ThreadPoolExecutor(max_workers=WORKERS) as pool:
        for batch in pool.map(verify, CLAIMS):
            for name, ok, detail in batch:
                if ok:
                    passed += 1
                else:
                    failures.append((name, detail))

    print("{} claims verified against real Java, {} failed".format(passed, len(failures)))
    for name, detail in failures:
        print("  FAIL: {}  {}".format(name, detail))
    sys.exit(1 if failures else 0)


if __name__ == "__main__":
    main()
