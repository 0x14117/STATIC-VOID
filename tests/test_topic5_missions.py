"""
Runs a reference solution for every Topic 5 mission built so far
through the real Java sandbox + mission checker, confirms each passes,
confirms deliberately wrong submissions fail, confirms every mission
has teach content and exactly 2 hints, and verifies the teach blocks'
claims about Scanner behavior are actually true.

Topic 5 is being built in batches of 5. This file covers whatever
t5* missions currently exist.

Run: python3 tests/test_topic5_missions.py
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from java_sandbox import run_java
from missions import check_mission, MISSIONS

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


REFERENCE_SOLUTIONS = {
    "t5m1": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int clearance = sc.nextInt();
        System.out.println("CLEARANCE: " + clearance);
    }
}''',
    "t5m2": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double rating = sc.nextDouble();
        System.out.println("RATING: " + rating);
    }
}''',
    "t5m3": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        System.out.println("MESSAGE: " + message);
    }
}''',
    "t5m4": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.next();
        System.out.println("FIRST WORD: " + first);
    }
}''',
    "t5m5": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int clearance = sc.nextInt();
        sc.nextLine();
        String codename = sc.nextLine();
        System.out.println("CLEARANCE: " + clearance);
        System.out.println("CODENAME: " + codename);
    }
}''',
}

TOPIC5_IDS = [mid for mid in MISSIONS if mid.startswith("t5")]


def main():
    check(
        "reference solutions cover every Topic 5 mission that exists",
        set(REFERENCE_SOLUTIONS.keys()) == set(TOPIC5_IDS),
        "missing: {}".format(set(TOPIC5_IDS) - set(REFERENCE_SOLUTIONS.keys())),
    )

    for mission_id, code in REFERENCE_SOLUTIONS.items():
        result = run_java(
            code,
            input_values=MISSIONS[mission_id].get("inputs"),
            seed_files=MISSIONS[mission_id].get("seed_files"),
        )
        if not result["ok"]:
            check("{} reference solution compiles and runs cleanly".format(mission_id), False, result.get("error"))
            continue
        ok, reason = check_mission(mission_id, code, result["output"], result.get("files"))
        check("{} reference solution passes ({})".format(mission_id, MISSIONS[mission_id]["title"]), ok, reason)

    for mission_id in TOPIC5_IDS:
        m = MISSIONS[mission_id]
        check("{} has a non-empty 'teach' block".format(mission_id), bool(m.get("teach")) and len(m["teach"]) > 20)
        check("{} has exactly 2 hints".format(mission_id), len(m.get("hints", [])) == 2)

    # every Scanner mission must actually supply test inputs, or the
    # player's program would just hang/crash on an empty stream
    for mission_id in TOPIC5_IDS:
        code = REFERENCE_SOLUTIONS[mission_id]
        if "Scanner" in code:
            check(
                "{} supplies test inputs for its Scanner reads".format(mission_id),
                bool(MISSIONS[mission_id].get("inputs")),
            )

    # no mission in this topic may use if/else or loops — Selection is
    # Topic 6 and Iteration is Topic 7, neither taught yet
    for mission_id in TOPIC5_IDS:
        code = REFERENCE_SOLUTIONS[mission_id]
        check(
            "{} reference solution avoids if/for/while (not taught until Topics 6-7)".format(mission_id),
            "if (" not in code and "for (" not in code and "while (" not in code,
        )

    # VERIFY THE TEACH CLAIMS AGAINST REAL JAVA

    # 1. The buffer trap is real: nextInt() then nextLine() yields an empty string
    BUGGY_BUFFER = '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int clearance = sc.nextInt();
        String codename = sc.nextLine();
        System.out.println("CLEARANCE: " + clearance);
        System.out.println("CODENAME: " + codename);
    }
}'''
    r = run_java(BUGGY_BUFFER, input_values=["7", "Ghost Protocol"])
    check(
        "claim: nextInt() then nextLine() really does give an empty string (the trap)",
        r["ok"] and r["output"].rstrip("\n") == "CLEARANCE: 7\nCODENAME: ",
        r,
    )

    # ...and that the buggy version is correctly FAILED by the mission checker
    ok, reason = check_mission("t5m5", BUGGY_BUFFER, r["output"], r.get("files"))
    check("t5m5 correctly fails the buggy (trap-hitting) solution", ok is False, reason)

    # 2. next() really stops at the first space
    NEXT_STOPS = '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.next());
    }
}'''
    r = run_java(NEXT_STOPS, input_values=["alpha bravo charlie"])
    check("claim: next() stops at the first space", r["ok"] and r["output"].strip() == "alpha", r)

    # 3. nextLine() really takes the whole line including spaces
    LINE_TAKES_ALL = '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.nextLine());
    }
}'''
    r = run_java(LINE_TAKES_ALL, input_values=["alpha bravo charlie"])
    check("claim: nextLine() takes the whole line", r["ok"] and r["output"].strip() == "alpha bravo charlie", r)

    # 4. nextInt() on decimal input really throws, rather than rounding
    MISMATCH = '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(n);
    }
}'''
    r = run_java(MISMATCH, input_values=["36.6"])
    check(
        "claim: nextInt() on decimal input throws InputMismatchException (doesn't round)",
        r["ok"] is False and "InputMismatch" in r["error"]["message"],
        r,
    )

    # 5. Missing the Scanner import really is a compile error
    NO_IMPORT = '''public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.nextInt());
    }
}'''
    r = run_java(NO_IMPORT, input_values=["7"])
    check(
        "claim: omitting the java.util.Scanner import is a compile error",
        r["ok"] is False and r["error"]["type"] == "CompileError",
        r,
    )

    wrong_cases = [
        # hardcoded, never reads the input
        ("t5m1", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("CLEARANCE: 7");\n    }\n}'),
        # wrong read method for the mission
        ("t5m2", '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String rating = sc.nextLine();
        System.out.println("RATING: " + rating);
    }
}'''),
        # used next() where the whole line was needed
        ("t5m3", '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = sc.next();
        System.out.println("MESSAGE: " + message);
    }
}'''),
        # used nextLine() where only one word was wanted
        ("t5m4", '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.nextLine();
        System.out.println("FIRST WORD: " + first);
    }
}'''),
    ]
    for mission_id, code in wrong_cases:
        result = run_java(
            code,
            input_values=MISSIONS[mission_id].get("inputs"),
            seed_files=MISSIONS[mission_id].get("seed_files"),
        )
        output = result["output"] if result["ok"] else ""
        ok, reason = check_mission(mission_id, code, output, result.get("files"))
        check("{} wrong submission correctly fails".format(mission_id), ok is False, reason)

    print("\n{} passed, {} failed".format(passed, failed))
    sys.exit(1 if failed else 0)


if __name__ == "__main__":
    main()
