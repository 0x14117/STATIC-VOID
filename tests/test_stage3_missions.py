"""
Runs a reference solution for every Stage 3 mission through the real
sandbox + mission checker, and confirms each one is judged PASS. Also
checks deliberately wrong/incomplete submissions are judged FAIL.

Run: python3 tests/test_stage3_missions.py
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
    "s3e1": 'a = 12\nb = 7\nprint(a + b)',
    "s3e2": 'a = 12\nb = 7\nprint(a - b)',
    "s3e3": 'a = 6\nb = 7\nprint(a * b)',
    "s3e4": 'a = 20\nb = 4\nprint(a / b)',
    "s3e5": 'a = 2\nb = 5\nprint(a ** b)',

    "s3m1": 'total = 47\ncount = 5\nprint(f"AVERAGE: {total / count}")',
    "s3m2": 'seconds = 145\nprint(f"MINUTES: {seconds // 60}")\nprint(f"REMAINDER: {seconds % 60}")',
    "s3m3": 'price = 19.999\nprint(f"PRICE: {round(price, 2)}")',
    "s3m4": 'a = 9\nb = 2\nprint(f"QUOTIENT: {a // b}")\nprint(f"REMAINDER: {a % b}")',
    "s3m5": 'base = 3\nexp = 4\nresult = base ** exp\nprint(f"RESULT: {result}")',

    "s3h1": (
        'width = 12.5\nheight = 4.0\narea = width * height\n'
        'print(f"WIDTH: {width}")\nprint(f"HEIGHT: {height}")\nprint(f"AREA: {round(area, 2)}")'
    ),
    "s3h2": (
        'total_seconds = 3725\nhours = total_seconds // 3600\n'
        'remainder = total_seconds % 3600\nminutes = remainder // 60\nseconds = remainder % 60\n'
        'print(f"HOURS: {hours}")\nprint(f"MINUTES: {minutes}")\nprint(f"SECONDS: {seconds}")'
    ),
    "s3h3": (
        'principal = 1000\nrate = 0.05\nyears = 3\n'
        'final = principal * (1 + rate) ** years\n'
        'print(f"PRINCIPAL: {principal}")\nprint(f"FINAL: {round(final, 2)}")'
    ),
    "s3h4": (
        'items = 7\nprice_each = 2.5\nsubtotal = items * price_each\ntax = subtotal * 0.1\ntotal = subtotal + tax\n'
        'print(f"SUBTOTAL: {round(subtotal, 2)}")\nprint(f"TAX: {round(tax, 2)}")\nprint(f"TOTAL: {round(total, 2)}")'
    ),
    "s3h5": (
        'a = 17\nb = 5\nsum_ab = a + b\ndiff_ab = a - b\nprod_ab = a * b\nquot_ab = a / b\n'
        'floor_ab = a // b\nmod_ab = a % b\npow_ab = a ** 2\n'
        'print(f"SUM: {sum_ab}")\nprint(f"DIFF: {diff_ab}")\nprint(f"PRODUCT: {prod_ab}")\n'
        'print(f"DIVISION: {round(quot_ab, 2)}")\nprint(f"FLOOR DIV: {floor_ab}")\n'
        'print(f"MOD: {mod_ab}")\nprint(f"POWER: {pow_ab}")\nprint("CALCULATION COMPLETE.")'
    ),
}

STAGE3_IDS = [mid for mid in MISSIONS if mid.startswith("s3")]


def main():
    check(
        "reference solutions cover all Stage 3 missions",
        set(REFERENCE_SOLUTIONS.keys()) == set(STAGE3_IDS),
        "missing: {}".format(set(STAGE3_IDS) - set(REFERENCE_SOLUTIONS.keys())),
    )

    for mission_id, code in REFERENCE_SOLUTIONS.items():
        result = run_sandboxed(code)
        if not result["ok"]:
            check("{} reference solution runs cleanly".format(mission_id), False, result.get("error"))
            continue
        ok, reason = check_mission(mission_id, code, result["output"])
        check("{} reference solution passes ({})".format(mission_id, MISSIONS[mission_id]["title"]), ok, reason)

    wrong_cases = [
        ("s3e1", 'a = 12\nb = 7\nprint(a - b)'),           # wrong operator
        ("s3e4", 'a = 20\nb = 4\nprint(5)'),                # hardcoded, no /
        ("s3m2", 'print("MINUTES: 2")\nprint("REMAINDER: 25")'),  # no // or %
        ("s3h3", 'principal = 1000\nprint(f"PRINCIPAL: {principal}")\nprint("FINAL: 1157.63")'),  # no **
        ("s3h5", 'print("wrong")'),
    ]
    for mission_id, code in wrong_cases:
        result = run_sandboxed(code)
        output = result["output"] if result["ok"] else ""
        ok, reason = check_mission(mission_id, code, output)
        check("{} wrong submission correctly fails".format(mission_id), ok is False, reason)

    print("\n{} passed, {} failed".format(passed, failed))
    sys.exit(1 if failed else 0)


if __name__ == "__main__":
    main()
