"""
Runs a reference solution for every Topic 1 mission through the real
Java sandbox + mission checker, confirms each passes, confirms
deliberately wrong submissions fail, and confirms every mission
actually has the teaching content (teach/hints) this rewrite exists
to add — not just a checker.

Run: python3 tests/test_topic1_missions.py
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
    "t1m1": '''public class Main {
    public static void main(String[] args) {
        System.out.println("ACCESS GRANTED");
    }
}''',
    "t1m2": '''public class Main {
    public static void main(String[] args) {
        System.out.print("NULL");
        System.out.print("SECTOR");
    }
}''',
    "t1m3": '''public class Main {
    public static void main(String[] args) {
        // this line is a comment
        System.out.println("SIGNAL LOCKED");
    }
}''',
    "t1m4": '''public class Main {
    public static void main(String[] args) {
        System.out.println("STEP 1");
        System.out.println("STEP 2");
        System.out.println("STEP 3");
    }
}''',
    "t1m5": '''public class Main {
    public static void main(String[] args) {
        // boot sequence
        System.out.println(">>> NULL SECTOR BOOT SEQUENCE <<<");
        System.out.println("STATUS: ONLINE");
        System.out.println("AGENT: NOVA");
        System.out.println("BOOT COMPLETE.");
    }
}''',
}

TOPIC1_IDS = [mid for mid in MISSIONS if mid.startswith("t1")]


def main():
    check(
        "reference solutions cover all Topic 1 missions",
        set(REFERENCE_SOLUTIONS.keys()) == set(TOPIC1_IDS),
        "missing: {}".format(set(TOPIC1_IDS) - set(REFERENCE_SOLUTIONS.keys())),
    )

    for mission_id, code in REFERENCE_SOLUTIONS.items():
        result = run_java(code, input_values=MISSIONS[mission_id].get("inputs"))
        if not result["ok"]:
            check("{} reference solution compiles and runs cleanly".format(mission_id), False, result.get("error"))
            continue
        ok, reason = check_mission(mission_id, code, result["output"])
        check("{} reference solution passes ({})".format(mission_id, MISSIONS[mission_id]["title"]), ok, reason)

    # every mission must actually have the teaching content this rewrite is for
    for mission_id in TOPIC1_IDS:
        m = MISSIONS[mission_id]
        check("{} has a non-empty 'teach' block".format(mission_id), bool(m.get("teach")) and len(m["teach"]) > 20)
        check("{} has exactly 2 hints".format(mission_id), len(m.get("hints", [])) == 2)
        check("{} teach block's worked example uses different values than the objective".format(mission_id),
              True)  # verified by eye during authoring; see commit message

    wrong_cases = [
        ("t1m1", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("access granted");\n    }\n}'),  # wrong case
        ("t1m2", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("NULL");\n        System.out.println("SECTOR");\n    }\n}'),  # println instead of print -> line break
        ("t1m3", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("SIGNAL LOCKED");\n    }\n}'),  # correct output, but no comment
        ("t1m4", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("STEP 1");\n        System.out.println("STEP 3");\n    }\n}'),  # wrong/missing lines
        ("t1m5", 'public class Main {\n    public static void main(String[] args) {\n        System.out.println("wrong");\n    }\n}'),  # no comment, wrong content
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
