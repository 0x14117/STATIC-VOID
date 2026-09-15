"""
Runs a reference solution for every Topic 3 mission through the real
Java sandbox + mission checker, confirms each passes, confirms
deliberately wrong submissions fail, confirms every mission has teach
content and exactly 2 hints, and verifies the teach block's specific
claims about Java behavior are actually true (not just asserted).

Run: python3 tests/test_topic3_missions.py
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
    "t3m1": '''public class Main {
    public static void main(String[] args) {
        int a = 12;
        int b = 7;
        System.out.println(a + b);
    }
}''',
    "t3m2": '''public class Main {
    public static void main(String[] args) {
        int a = 17;
        int b = 5;
        System.out.println(a / b);
        System.out.println(a % b);
    }
}''',
    "t3m3": '''public class Main {
    public static void main(String[] args) {
        int wholeNumber = 17;
        double result = (double) wholeNumber / 5;
        System.out.println(result);
    }
}''',
    "t3m4": '''public class Main {
    public static void main(String[] args) {
        int score = 10;
        score = score + 5;
        score += 3;
        System.out.println(score);
    }
}''',
    "t3m5": '''public class Main {
    public static void main(String[] args) {
        int base = 3;
        int bonus = 4;
        int total = base * 2 + bonus * 3;
        System.out.println("BASE: " + base);
        System.out.println("BONUS: " + bonus);
        System.out.println("TOTAL: " + total);
        int wholeAvg = 17;
        double avg = (double) wholeAvg / 5;
        System.out.println("AVERAGE: " + avg);
        int counter = 1;
        counter += 9;
        System.out.println("COUNTER: " + counter);
    }
}''',
}

TOPIC3_IDS = [mid for mid in MISSIONS if mid.startswith("t3")]


def main():
    check(
        "reference solutions cover all Topic 3 missions",
        set(REFERENCE_SOLUTIONS.keys()) == set(TOPIC3_IDS),
        "missing: {}".format(set(TOPIC3_IDS) - set(REFERENCE_SOLUTIONS.keys())),
    )

    for mission_id, code in REFERENCE_SOLUTIONS.items():
        result = run_java(code, input_values=MISSIONS[mission_id].get("inputs"))
        if not result["ok"]:
            check("{} reference solution compiles and runs cleanly".format(mission_id), False, result.get("error"))
            continue
        ok, reason = check_mission(mission_id, code, result["output"])
        check("{} reference solution passes ({})".format(mission_id, MISSIONS[mission_id]["title"]), ok, reason)

    for mission_id in TOPIC3_IDS:
        m = MISSIONS[mission_id]
        check("{} has a non-empty 'teach' block".format(mission_id), bool(m.get("teach")) and len(m["teach"]) > 20)
        check("{} has exactly 2 hints".format(mission_id), len(m.get("hints", [])) == 2)

    # Verify the teach block's specific claims are actually true in real Java,
    # not just text we wrote and hoped was right.
    INT_DIVISION = '''public class Main {
    public static void main(String[] args) {
        System.out.println(10 / 3);
    }
}'''
    r = run_java(INT_DIVISION)
    check("claim: int/int truncates (10/3 == 3, not 3.33)", r["ok"] and r["output"].strip() == "3", r)

    CAST_WHOLE_EXPRESSION = '''public class Main {
    public static void main(String[] args) {
        int x = 10;
        int y = 3;
        double result = (double) (x / y);
        System.out.println(result);
    }
}'''
    r = run_java(CAST_WHOLE_EXPRESSION)
    check(
        "claim: casting the whole expression (double)(x/y) still truncates first -> 3.0",
        r["ok"] and r["output"].strip() == "3.0",
        r,
    )

    CAST_OPERAND = '''public class Main {
    public static void main(String[] args) {
        int x = 10;
        int y = 3;
        double result = (double) x / y;
        System.out.println(result);
    }
}'''
    r = run_java(CAST_OPERAND)
    check(
        "claim: casting one operand (double) x / y gives the real decimal answer",
        r["ok"] and r["output"].strip() == "3.3333333333333335",
        r,
    )

    TYPO_PLUS_EQUALS = '''public class Main {
    public static void main(String[] args) {
        int score = 10;
        score =+ 5;
        System.out.println(score);
    }
}'''
    r = run_java(TYPO_PLUS_EQUALS)
    check(
        "claim: 'score =+ 5' compiles fine but means score = +5 (the typo trap)",
        r["ok"] and r["output"].strip() == "5",
        r,
    )

    wrong_cases = [
        ("t3m1", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println(19);\n    }\n}'),  # hardcoded, no +
        ("t3m2", 'public class Main {\n    public static void main(String[] args) {\n        int a = 17;\n        int b = 5;\n        double result = (double) a / b;\n        System.out.println(result);\n    }\n}'),  # wrong mission entirely — decimal instead of truncated
        ("t3m3", 'public class Main {\n    public static void main(String[] args) {\n        int wholeNumber = 17;\n        System.out.println(wholeNumber / 5);\n    }\n}'),  # no cast -> truncates to 3, not 3.4
        ("t3m4", 'public class Main {\n    public static void main(String[] args) {\n        int score = 10;\n        System.out.println(score);\n    }\n}'),  # never updates score
        ("t3m5", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("wrong");\n    }\n}'),
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
