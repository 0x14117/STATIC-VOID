"""
Runs a reference solution for every Stage 1 mission through the real
sandbox + mission checker, and confirms each one is judged PASS. Also
checks a handful of deliberately wrong submissions are judged FAIL,
so the checkers aren't accidentally too lenient.

Run: python3 tests/test_stage1_missions.py
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
    "s1e1": 'print("ACCESS GRANTED")',

    "s1e2": 'codename = "GHOST"\nprint(codename)',

    "s1e3": 'a = "NULL"\nb = "SECTOR"\nprint(a + b)',

    "s1e4": 'a = "NULL"\nb = "SECTOR"\nprint(a + " " + b)',

    "s1e5": 'status = "OFFLINE"\nprint(status)\nstatus = "ONLINE"\nprint(status)',

    "s1m1": 'name = "NOVA"\nclearance = "5"\nprint("AGENT: " + name + " | CLEARANCE: " + clearance)',

    "s1m2": (
        'codename = "VIPER"\nmission = "BREACH"\nstatus = "ACTIVE"\n'
        'print("CODENAME: " + codename)\nprint("MISSION: " + mission)\nprint("STATUS: " + status)'
    ),

    "s1m3": 'alias = "UNKNOWN"\nprint("ALIAS: " + alias)\nalias = "PHANTOM"\nprint("ALIAS: " + alias)',

    "s1m4": (
        'first = "NULL"\nsecond = "SECTOR"\n'
        'combined = first + " " + second\nprint("UNIT: " + combined)'
    ),

    "s1m5": (
        'agent = "NOVA"\nlevel = "5"\nmission = "BREACH"\nstatus = "ACTIVE"\n'
        'print(agent + " | " + level + " | " + mission + " | " + status)'
    ),

    "s1h1": (
        'agent = "NOVA"\nclearance = "5"\ndivision = "NULL SECTOR"\nstatus = "PENDING"\n'
        'print(">>> REGISTRATION TERMINAL <<<")\n'
        'print("AGENT: " + agent)\n'
        'print("CLEARANCE: " + clearance)\n'
        'print("DIVISION: " + division)\n'
        'status = "ACTIVE"\n'
        'print("STATUS: " + status)'
    ),

    "s1h2": (
        'codename = "VIPER"\nmission = "BREACH"\nlocation = "NEOCORP TOWER"\n'
        'clearance = "7"\nstatus = "ACTIVE"\n'
        'print("CODENAME: " + codename)\n'
        'print("MISSION: " + mission)\n'
        'print("LOCATION: " + location)\n'
        'print("CLEARANCE: " + clearance)\n'
        'print("STATUS: " + status)'
    ),

    "s1h3": (
        'stage = "SYN"\nprint("STEP 1: " + stage)\n'
        'stage = "SYN-ACK"\nprint("STEP 2: " + stage)\n'
        'stage = "ACK"\nprint("STEP 3: " + stage)\n'
        'print("LINK ESTABLISHED")'
    ),

    "s1h4": (
        'name = "NOVA"\ncodename = "GHOST"\nclearance = "5"\n'
        'print("==============")\n'
        'print("NAME: " + name)\n'
        'print("CODENAME: " + codename)\n'
        'print("CLEARANCE: " + clearance)\n'
        'print("==============")'
    ),

    "s1h5": (
        'print(">>> NULL SECTOR BOOT SEQUENCE <<<")\n'
        'status = "INITIALIZING"\nprint("STATUS: " + status)\n'
        'status = "LOADING MODULES"\nprint("STATUS: " + status)\n'
        'status = "ONLINE"\nprint("STATUS: " + status)\n'
        'agent = "NOVA"\nclearance = "5"\n'
        'print("AGENT: " + agent + " | CLEARANCE: " + clearance)\n'
        'print("BOOT COMPLETE.")'
    ),
}


STAGE1_IDS = [mid for mid in MISSIONS if mid.startswith("s1")]


def main():
    # every Stage 1 mission has a reference solution
    check(
        "reference solutions cover all Stage 1 missions",
        set(REFERENCE_SOLUTIONS.keys()) == set(STAGE1_IDS),
        "missing: {}".format(set(STAGE1_IDS) - set(REFERENCE_SOLUTIONS.keys())),
    )

    # every reference solution passes its own mission, through the real sandbox
    for mission_id, code in REFERENCE_SOLUTIONS.items():
        result = run_sandboxed(code)
        if not result["ok"]:
            check("{} reference solution runs cleanly".format(mission_id), False, result.get("error"))
            continue
        ok, reason = check_mission(mission_id, code, result["output"])
        check("{} reference solution passes ({})".format(mission_id, MISSIONS[mission_id]["title"]), ok, reason)

    # a handful of deliberately wrong submissions must be judged FAIL
    wrong_cases = [
        ("s1e1", 'print("access granted")'),          # wrong case
        ("s1e2", 'print("GHOST")'),                    # no variable used
        ("s1e4", 'a = "NULL"\nb = "SECTOR"\nprint(a + b)'),  # missing space
        ("s1m2", 'print("CODENAME: VIPER")'),           # no variable/concatenation
        ("s1h5", 'print("wrong")'),                     # nowhere close
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
