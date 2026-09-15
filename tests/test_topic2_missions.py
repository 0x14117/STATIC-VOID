"""
Runs a reference solution for every Topic 2 mission through the real
Java sandbox + mission checker, confirms each passes, confirms
deliberately wrong submissions fail, and confirms every mission has
teach content and exactly 2 hints.

Run: python3 tests/test_topic2_missions.py
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
    "t2m1": '''public class Main {
    public static void main(String[] args) {
        int total = 47;
        System.out.println(total);
    }
}''',
    "t2m2": '''public class Main {
    public static void main(String[] args) {
        String codename = "Viper";
        System.out.println(codename);
    }
}''',
    "t2m3": '''public class Main {
    public static void main(String[] args) {
        double price = 19.99;
        boolean inStock = false;
        System.out.println(price);
        System.out.println(inStock);
    }
}''',
    "t2m4": '''public class Main {
    public static void main(String[] args) {
        final int CLEARANCE_LEVEL = 9;
        System.out.println("CLEARANCE: " + CLEARANCE_LEVEL);
    }
}''',
    "t2m5": '''public class Main {
    public static void main(String[] args) {
        String agent = "Viper";
        int clearance = 7;
        double rating = 8.5;
        boolean active = true;
        final int MAX_CLEARANCE = 10;
        System.out.println("AGENT: " + agent);
        System.out.println("CLEARANCE: " + clearance);
        System.out.println("RATING: " + rating);
        System.out.println("ACTIVE: " + active);
        System.out.println("MAX CLEARANCE: " + MAX_CLEARANCE);
    }
}''',
}

TOPIC2_IDS = [mid for mid in MISSIONS if mid.startswith("t2")]


def main():
    check(
        "reference solutions cover all Topic 2 missions",
        set(REFERENCE_SOLUTIONS.keys()) == set(TOPIC2_IDS),
        "missing: {}".format(set(TOPIC2_IDS) - set(REFERENCE_SOLUTIONS.keys())),
    )

    for mission_id, code in REFERENCE_SOLUTIONS.items():
        result = run_java(code, input_values=MISSIONS[mission_id].get("inputs"))
        if not result["ok"]:
            check("{} reference solution compiles and runs cleanly".format(mission_id), False, result.get("error"))
            continue
        ok, reason = check_mission(mission_id, code, result["output"])
        check("{} reference solution passes ({})".format(mission_id, MISSIONS[mission_id]["title"]), ok, reason)

    for mission_id in TOPIC2_IDS:
        m = MISSIONS[mission_id]
        check("{} has a non-empty 'teach' block".format(mission_id), bool(m.get("teach")) and len(m["teach"]) > 20)
        check("{} has exactly 2 hints".format(mission_id), len(m.get("hints", [])) == 2)

    # confirm final actually gets enforced: reassigning a final variable must NOT compile
    REASSIGN_FINAL = '''public class Main {
    public static void main(String[] args) {
        final int CLEARANCE_LEVEL = 9;
        CLEARANCE_LEVEL = 10;
        System.out.println("CLEARANCE: " + CLEARANCE_LEVEL);
    }
}'''
    r = run_java(REASSIGN_FINAL)
    check("reassigning a final variable fails to compile (as taught)", r["ok"] is False and r["error"]["type"] == "CompileError", r)

    wrong_cases = [
        ("t2m1", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println(47);\n    }\n}'),  # no variable
        ("t2m2", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("Ghost");\n    }\n}'),  # wrong value
        ("t2m3", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("19.99");\n        System.out.println("false");\n    }\n}'),  # strings not real types
        ("t2m4", 'public class Main {\n    public static void main(String[] args) {\n        int clearanceLevel = 9;\n        System.out.println("CLEARANCE: " + clearanceLevel);\n    }\n}'),  # missing final
        ("t2m5", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("wrong");\n    }\n}'),
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
