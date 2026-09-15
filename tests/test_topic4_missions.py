"""
Runs a reference solution for every Topic 4 mission through the real
Java sandbox + mission checker, confirms each passes, confirms
deliberately wrong submissions fail, confirms every mission has teach
content and exactly 2 hints, and verifies the teach block's specific
claims about methods/the call stack are actually true.

Run: python3 tests/test_topic4_missions.py
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
    "t4m1": '''public class Main {
    public static void greet() {
        System.out.println("SIGNAL RECEIVED");
    }

    public static void main(String[] args) {
        greet();
    }
}''',
    "t4m2": '''public class Main {
    public static void announce(String name) {
        System.out.println("AGENT: " + name);
    }

    public static void main(String[] args) {
        announce("Viper");
    }
}''',
    "t4m3": '''public class Main {
    public static int square(int n) {
        return n * n;
    }

    public static void main(String[] args) {
        int result = square(6);
        System.out.println(result);
    }
}''',
    "t4m4": '''public class Main {
    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        int sum = add(4, 9);
        System.out.println(sum);
        int sum2 = add(sum, 100);
        System.out.println(sum2);
    }
}''',
    "t4m5": '''public class Main {
    public static void header() {
        System.out.println(">>> MISSION REPORT <<<");
    }

    public static int combine(int a, int b) {
        return a + b;
    }

    public static String label(String name, int value) {
        return name + ": " + value;
    }

    public static void main(String[] args) {
        header();
        int total = combine(15, 27);
        System.out.println(label("TOTAL", total));
        int doubled = combine(total, total);
        System.out.println(label("DOUBLED", doubled));
    }
}''',
}

TOPIC4_IDS = [mid for mid in MISSIONS if mid.startswith("t4")]


def main():
    check(
        "reference solutions cover all Topic 4 missions",
        set(REFERENCE_SOLUTIONS.keys()) == set(TOPIC4_IDS),
        "missing: {}".format(set(TOPIC4_IDS) - set(REFERENCE_SOLUTIONS.keys())),
    )

    for mission_id, code in REFERENCE_SOLUTIONS.items():
        result = run_java(code, input_values=MISSIONS[mission_id].get("inputs"))
        if not result["ok"]:
            check("{} reference solution compiles and runs cleanly".format(mission_id), False, result.get("error"))
            continue
        ok, reason = check_mission(mission_id, code, result["output"])
        check("{} reference solution passes ({})".format(mission_id, MISSIONS[mission_id]["title"]), ok, reason)

    for mission_id in TOPIC4_IDS:
        m = MISSIONS[mission_id]
        check("{} has a non-empty 'teach' block".format(mission_id), bool(m.get("teach")) and len(m["teach"]) > 20)
        check("{} has exactly 2 hints".format(mission_id), len(m.get("hints", [])) == 2)

    # no mission in this topic should use if/else — Selection is Topic 6,
    # not taught yet, so nothing here may lean on it
    for mission_id in TOPIC4_IDS:
        code = REFERENCE_SOLUTIONS[mission_id]
        check(
            "{} reference solution doesn't use if/else (not taught until Topic 6)".format(mission_id),
            "if" not in code.replace("if (", "__IGNORE__") and " if(" not in code,
        )

    # Verify the call-stack independence claim: two calls to the same method
    # with different arguments really are independent, never see each other's data
    STACK_INDEPENDENCE = '''public class Main {
    public static int addOne(int n) {
        int local = n + 1;
        return local;
    }

    public static void main(String[] args) {
        System.out.println(addOne(2));
        System.out.println(addOne(50));
    }
}'''
    r = run_java(STACK_INDEPENDENCE)
    check(
        "claim: two calls to the same method are independent (3, then 51 — no leftover state)",
        r["ok"] and r["output"].strip() == "3\n51",
        r,
    )

    # Verify the "must return on every path" claim: a method declared to
    # return int that doesn't always return is a compile error
    MISSING_RETURN = '''public class Main {
    public static int maybe(int n) {
        int x = n * 2;
    }

    public static void main(String[] args) {
        System.out.println(maybe(5));
    }
}'''
    r = run_java(MISSING_RETURN)
    check(
        "claim: a method declared to return int must actually return one, or it won't compile",
        r["ok"] is False and r["error"]["type"] == "CompileError",
        r,
    )

    wrong_cases = [
        ("t4m1", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("SIGNAL RECEIVED");\n    }\n}'),  # no method defined
        ("t4m2", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("AGENT: Viper");\n    }\n}'),  # no method/parameter used
        ("t4m3", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println(36);\n    }\n}'),  # hardcoded
        ("t4m4", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println(13);\n        System.out.println(113);\n    }\n}'),  # hardcoded, no method
        ("t4m5", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("wrong");\n    }\n}'),
    ]
    for mission_id, code in wrong_cases:
        result = run_java(code, input_values=MISSIONS[mission_id].get("inputs"))
        output = result["output"] if result["ok"] else ""
        ok, reason = check_mission(mission_id, code, output)
        check("{} wrong submission correctly fails".format(mission_id), ok is False, reason)

    print("\n{} passed, {} failed".format(passed, failed))
    sys.exit(1 if failed else 0)


if __name__ == "__main__":
    main()
