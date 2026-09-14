"""
Runs a reference solution for every Stage 4 mission through the real
sandbox + mission checker (feeding each mission's scripted input()
values, mirroring what server.py now does), and confirms each one is
judged PASS. Also checks deliberately wrong/incomplete submissions
are judged FAIL.

Run: python3 tests/test_stage4_missions.py
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from sandbox import run_sandboxed
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
    "s4e1": 'name = input()\nprint(f"WELCOME: {name}")',
    "s4e2": 'age = int(input())\nprint(age + 1)',
    "s4e3": 'height = float(input())\nprint(height)',
    "s4e4": 'code = input()\nprint(f"CODE ENTERED: {code}")',
    "s4e5": 'num = int(input())\nprint(num * 2)',

    "s4m1": 'a = int(input())\nb = int(input())\nprint(f"SUM: {a + b}")',
    "s4m2": 'name = input()\nage = int(input())\nprint(f"AGENT: {name} | AGE: {age}")',
    "s4m3": 'price = float(input())\nqty = int(input())\nprint(f"TOTAL: {price * qty}")',
    "s4m4": 'text = input()\nprint(f"LENGTH: {len(text)}")',
    "s4m5": 'num = int(input())\nprint(f"DOUBLE: {num * 2}")\nprint(f"SQUARE: {num ** 2}")',

    "s4h1": (
        'name = input()\nclearance = int(input())\ndivision = input()\n'
        'print(f"AGENT: {name}")\nprint(f"CLEARANCE: {clearance}")\nprint(f"DIVISION: {division}")\n'
        'print(f"CLEARANCE X2: {clearance * 2}")'
    ),
    "s4h2": (
        'width = float(input())\nheight = float(input())\narea = width * height\n'
        'print(f"WIDTH: {width}")\nprint(f"HEIGHT: {height}")\nprint(f"AREA: {round(area, 2)}")'
    ),
    "s4h3": (
        'score1 = int(input())\nscore2 = int(input())\nscore3 = int(input())\n'
        'total = score1 + score2 + score3\naverage = total / 3\n'
        'print(f"TOTAL: {total}")\nprint(f"AVERAGE: {round(average, 2)}")'
    ),
    "s4h4": (
        'celsius = float(input())\nfahrenheit = celsius * 9 / 5 + 32\n'
        'print(f"CELSIUS: {celsius}")\nprint(f"FAHRENHEIT: {round(fahrenheit, 1)}")'
    ),
    "s4h5": (
        'name = input()\nprincipal = float(input())\nrate = float(input())\nyears = int(input())\n'
        'final = principal * (1 + rate) ** years\n'
        'print(f"AGENT: {name}")\nprint(f"PRINCIPAL: {principal}")\n'
        'print(f"RATE: {rate}")\nprint(f"YEARS: {years}")\nprint(f"FINAL: {round(final, 2)}")\n'
        'print("REPORT COMPLETE.")'
    ),
}

STAGE4_IDS = [mid for mid in MISSIONS if mid.startswith("s4")]


def main():
    check(
        "reference solutions cover all Stage 4 missions",
        set(REFERENCE_SOLUTIONS.keys()) == set(STAGE4_IDS),
        "missing: {}".format(set(STAGE4_IDS) - set(REFERENCE_SOLUTIONS.keys())),
    )

    for mission_id, code in REFERENCE_SOLUTIONS.items():
        inputs = MISSIONS[mission_id].get("inputs")
        result = run_sandboxed(code, input_values=inputs)
        if not result["ok"]:
            check("{} reference solution runs cleanly".format(mission_id), False, result.get("error"))
            continue
        ok, reason = check_mission(mission_id, code, result["output"])
        check("{} reference solution passes ({})".format(mission_id, MISSIONS[mission_id]["title"]), ok, reason)

    # every mission that touches input() must actually have scripted inputs configured,
    # otherwise input() would silently return "" in the real server
    for mission_id in STAGE4_IDS:
        code = REFERENCE_SOLUTIONS[mission_id]
        if "input(" in code:
            check(
                "{} has non-empty scripted inputs".format(mission_id),
                bool(MISSIONS[mission_id].get("inputs")),
            )

    wrong_cases = [
        ("s4e1", 'name = input()\nprint("WELCOME: someone else")'),
        ("s4e2", 'age = int(input())\nprint(age)'),  # forgot +1
        ("s4m1", 'print("SUM: 0")'),  # ignores input entirely
        ("s4h4", 'celsius = float(input())\nprint(f"CELSIUS: {celsius}")\nprint("FAHRENHEIT: 0.0")'),
        ("s4h5", 'print("wrong")'),
    ]
    for mission_id, code in wrong_cases:
        inputs = MISSIONS[mission_id].get("inputs")
        result = run_sandboxed(code, input_values=inputs)
        output = result["output"] if result["ok"] else ""
        ok, reason = check_mission(mission_id, code, output)
        check("{} wrong submission correctly fails".format(mission_id), ok is False, reason)

    print("\n{} passed, {} failed".format(passed, failed))
    sys.exit(1 if failed else 0)


if __name__ == "__main__":
    main()
