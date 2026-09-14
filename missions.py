"""
Server-side mission validation.

Each mission is checked against the program's captured stdout (never by
inspecting/eval-ing the player's source beyond what sandbox.py already
does for security) so the check reflects what the code actually does.
This file currently covers Stage 1 Mission 1 only, as a working vertical
slice through the whole pipeline (sandbox -> validation -> Cipher
reaction). Stages 1-10 / 150 missions from the Master Document are not
yet ported here.
"""


def _check_s1e1(code, output):
    if output.strip() == "ACCESS GRANTED":
        return True, "Clean. That's all it takes — print() and a string."
    if "ACCESS GRANTED" not in output:
        return False, "I don't see 'ACCESS GRANTED' anywhere in your output. Use print()."
    return False, "Close — but the output has to be exactly 'ACCESS GRANTED', nothing extra around it."


MISSIONS = {
    "s1e1": {
        "title": "Open The Gate",
        "stage": 1,
        "tier": "easy",
        "concept": "print()",
        "briefing": "NeoCorp's outer gate checks for one exact signal. Print ACCESS GRANTED to pass through.",
        "check": _check_s1e1,
    },
}


def check_mission(mission_id, code, output):
    mission = MISSIONS.get(mission_id)
    if mission is None:
        return False, "That mission doesn't exist."
    return mission["check"](code, output)
