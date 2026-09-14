"""
Runs a reference solution for every Stage 2 mission through the real
sandbox + mission checker, and confirms each one is judged PASS. Also
checks deliberately wrong/incomplete submissions are judged FAIL.

Run: python3 tests/test_stage2_missions.py
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
    "s2e1": 'text = "access denied"\nprint(text.upper())',
    "s2e2": 'text = "TOP SECRET"\nprint(text.lower())',
    "s2e3": 'text = "   ping   "\nprint(text.strip())',
    "s2e4": 'text = "NULL SECTOR"\nprint(len(text))',
    "s2e5": 'text = "NEOCORP"\nprint(text[0:3])',
    "s2m1": 'text = "Access Granted"\nprint(f"UPPER: {text.upper()}")\nprint(f"LOWER: {text.lower()}")',
    "s2m2": 'raw = "  null sector  "\nclean = raw.strip()\nprint(clean.title())',
    "s2m3": 'text = "GHOST-PROTOCOL"\nprint(f"CODE: {text[0:5]}")',
    "s2m4": "text = \"1010101\"\nprint(f\"ONES: {text.count('1')}\")",
    "s2m5": 'text = "AGENT NOVA IS COMPROMISED"\nredacted = text.replace("NOVA", "[REDACTED]")\nprint(f"MESSAGE: {redacted}")',
    "s2h1": 'raw = "   breach detected   "\nclean = raw.strip()\nprint(f"RAW LENGTH: {len(raw)}")\nprint(f"CLEAN: {clean.upper()}")\nprint(f"CLEAN LENGTH: {len(clean)}")',
    "s2h2": 'first = "GHOST"\nsecond = "PROTOCOL"\ncode = first[0:2] + second[0:3]\nprint(f"CODENAME: {code.upper()}")',
    "s2h3": "text = \"AGENT NOVA MET AGENT VIPER AT NEOCORP TOWER\"\nredacted = text.replace(\"NOVA\", \"[REDACTED]\").replace(\"VIPER\", \"[REDACTED]\")\nprint(redacted)\nprint(f\"REDACTIONS: {redacted.count('[REDACTED]')}\")",
    "s2h4": 'name = "  agent nova  "\ndivision = "  null sector  "\nname = name.strip().title()\ndivision = division.strip().title()\nprint(f"NAME: {name}")\nprint(f"DIVISION: {division}")',
    "s2h5": (
        'raw = "   breach at neocorp tower   "\nclean = raw.strip()\n'
        'print(f"RAW LENGTH: {len(raw)}")\nprint(f"CLEAN: {clean.upper()}")\n'
        'print(f"TITLE: {clean.title()}")\nfirst_word = clean[0:6]\n'
        'print(f"FIRST WORD: {first_word.upper()}")\n'
        'alert = clean.replace("breach", "[CONTAINED]")\nprint(f"ALERT: {alert}")\n'
        'e_count = clean.count("e")\nprint(f"E COUNT: {e_count}")\nprint("ANALYSIS COMPLETE.")'
    ),
}

STAGE2_IDS = [mid for mid in MISSIONS if mid.startswith("s2")]


def main():
    check(
        "reference solutions cover all Stage 2 missions",
        set(REFERENCE_SOLUTIONS.keys()) == set(STAGE2_IDS),
        "missing: {}".format(set(STAGE2_IDS) - set(REFERENCE_SOLUTIONS.keys())),
    )

    for mission_id, code in REFERENCE_SOLUTIONS.items():
        result = run_sandboxed(code)
        if not result["ok"]:
            check("{} reference solution runs cleanly".format(mission_id), False, result.get("error"))
            continue
        ok, reason = check_mission(mission_id, code, result["output"])
        check("{} reference solution passes ({})".format(mission_id, MISSIONS[mission_id]["title"]), ok, reason)

    wrong_cases = [
        ("s2e1", 'text = "access denied"\nprint(text)'),                      # no .upper()
        ("s2e4", 'text = "NULL SECTOR"\nprint(10)'),                          # wrong number, no len()
        ("s2m1", 'print("UPPER: ACCESS GRANTED")\nprint("LOWER: access granted")'),  # no f-string/variable
        ("s2h3", 'text = "AGENT NOVA MET AGENT VIPER AT NEOCORP TOWER"\nprint(text)'),  # no replace/count
        ("s2h5", 'print("wrong")'),
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
