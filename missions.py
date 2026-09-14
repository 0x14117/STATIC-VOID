"""
Server-side mission validation.

Each mission is checked against the program's captured stdout (never by
inspecting/eval-ing the player's source beyond what sandbox.py already
does for security), plus a light substring check on the source for
missions where the concept itself (e.g. "use a variable") can't be
inferred from output alone. This keeps checks honest to what the code
does without re-implementing a second sandbox here.

Stage 1 (The Basics: print, variables, concatenation) is fully ported
from the Master Document's mission plan — 15 missions, 5 easy / 5
medium / 5 hard. Stages 2-10 are not yet ported.
"""


def make_checker(expected_output, success_line, missing_hint, mismatch_hint,
                  code_requires=None, code_requires_hint=None):
    """Build a checker that compares captured stdout against an exact
    expected multi-line string (no trailing newline), with an optional
    lightweight source check for concepts output alone can't verify."""

    def check(code, output):
        if code_requires:
            for token in code_requires:
                if token not in code:
                    return False, code_requires_hint or "Your code needs to use '{}' for this one.".format(token)

        got = output.rstrip("\n")
        if got == expected_output:
            return True, success_line
        if expected_output in output:
            return False, mismatch_hint
        return False, missing_hint

    return check


MISSIONS = {

    # ---------------------------------------------------------------- EASY

    "s1e1": {
        "title": "Open The Gate",
        "stage": 1, "tier": "easy", "concept": "print()",
        "briefing": "NeoCorp's outer gate checks for one exact signal. Print ACCESS GRANTED to pass through.",
        "check": make_checker(
            expected_output="ACCESS GRANTED",
            success_line="Clean. That's all it takes — print() and a string.",
            missing_hint="I don't see 'ACCESS GRANTED' anywhere in your output. Use print().",
            mismatch_hint="Close — but the output has to be exactly 'ACCESS GRANTED', nothing extra around it.",
        ),
    },

    "s1e2": {
        "title": "Agent Handle",
        "stage": 1, "tier": "easy", "concept": "variables",
        "briefing": "Store the value 'GHOST' in a variable, then print that variable.",
        "check": make_checker(
            expected_output="GHOST",
            success_line="Good. A variable is just a labeled box. You just used one.",
            missing_hint="I don't see GHOST in the output. Assign it to a variable, then print the variable.",
            mismatch_hint="Close, but the output must be exactly GHOST — nothing else on that line.",
            code_requires=["="],
            code_requires_hint="Store the value in a variable first — I need to see an assignment (=).",
        ),
    },

    "s1e3": {
        "title": "Two Signals",
        "stage": 1, "tier": "easy", "concept": "concatenation",
        "briefing": "Store 'NULL' in one variable and 'SECTOR' in another. Concatenate them with + (no space) and print the result.",
        "check": make_checker(
            expected_output="NULLSECTOR",
            success_line="That's concatenation — two strings joined with +.",
            missing_hint="I don't see NULLSECTOR in the output. Join the two variables with +.",
            mismatch_hint="Almost — check for extra spaces or characters between NULL and SECTOR.",
            code_requires=["+"],
            code_requires_hint="Join the two variables with + — I don't see a + in your code.",
        ),
    },

    "s1e4": {
        "title": "Codename Format",
        "stage": 1, "tier": "easy", "concept": "concatenation",
        "briefing": "Store 'NULL' and 'SECTOR' in two variables. Print them joined with a single space between them.",
        "check": make_checker(
            expected_output="NULL SECTOR",
            success_line="Exactly one space, exactly where NeoCorp expects it.",
            missing_hint="I don't see NULL SECTOR in the output.",
            mismatch_hint="Check your spacing — I need exactly one space between NULL and SECTOR.",
            code_requires=["+"],
            code_requires_hint="Join the variables with + and a \" \" in between.",
        ),
    },

    "s1e5": {
        "title": "Status Flip",
        "stage": 1, "tier": "easy", "concept": "reassignment",
        "briefing": "Create a variable status set to 'OFFLINE' and print it. Then change status to 'ONLINE' and print it again.",
        "check": make_checker(
            expected_output="OFFLINE\nONLINE",
            success_line="That's reassignment. Same variable, new value, no confusion.",
            missing_hint="I need two lines of output — OFFLINE first, then ONLINE.",
            mismatch_hint="Check the order and spelling — OFFLINE then ONLINE, each on its own line.",
            code_requires=["="],
            code_requires_hint="Assign status twice — I don't see a reassignment (=) in your code.",
        ),
    },

    # -------------------------------------------------------------- MEDIUM

    "s1m1": {
        "title": "Agent Dossier",
        "stage": 1, "tier": "medium", "concept": "variables + concatenation",
        "briefing": "Store 'NOVA' in one variable and '5' in another. Print: AGENT: NOVA | CLEARANCE: 5 — built by concatenating your variables with the labels.",
        "check": make_checker(
            expected_output="AGENT: NOVA | CLEARANCE: 5",
            success_line="Clean dossier. NeoCorp files it without a second look.",
            missing_hint="I don't see the exact dossier line in your output.",
            mismatch_hint="Check your spacing and the ' | ' between the two fields.",
            code_requires=["+"],
        ),
    },

    "s1m2": {
        "title": "Three-Line Report",
        "stage": 1, "tier": "medium", "concept": "multiple variables",
        "briefing": "Create codename='VIPER', mission='BREACH', status='ACTIVE'. Print three lines: 'CODENAME: VIPER', 'MISSION: BREACH', 'STATUS: ACTIVE' — each built with concatenation.",
        "check": make_checker(
            expected_output="CODENAME: VIPER\nMISSION: BREACH\nSTATUS: ACTIVE",
            success_line="Three fields, three lines, zero typos. That's a clean report.",
            missing_hint="I need all three lines — CODENAME, MISSION, and STATUS.",
            mismatch_hint="Check the order and exact wording of each line.",
            code_requires=["+"],
        ),
    },

    "s1m3": {
        "title": "Rename Protocol",
        "stage": 1, "tier": "medium", "concept": "reassignment + concatenation",
        "briefing": "Create alias='UNKNOWN' and print 'ALIAS: UNKNOWN'. Then reassign alias to 'PHANTOM' and print 'ALIAS: PHANTOM'. Build both lines with concatenation.",
        "check": make_checker(
            expected_output="ALIAS: UNKNOWN\nALIAS: PHANTOM",
            success_line="Same variable, two identities. That's how cover names work.",
            missing_hint="I need both alias lines, UNKNOWN first, then PHANTOM.",
            mismatch_hint="Check spelling and order — UNKNOWN then PHANTOM.",
            code_requires=["+", "="],
        ),
    },

    "s1m4": {
        "title": "Combine And Relabel",
        "stage": 1, "tier": "medium", "concept": "building a variable from variables",
        "briefing": "Create first='NULL' and second='SECTOR'. Combine them with a space into a third variable. Print 'UNIT: ' followed by that combined variable.",
        "check": make_checker(
            expected_output="UNIT: NULL SECTOR",
            success_line="You built a new variable out of two old ones. That's the whole game.",
            missing_hint="I don't see UNIT: NULL SECTOR in your output.",
            mismatch_hint="Check your spacing between NULL and SECTOR, and after the colon.",
            code_requires=["+"],
        ),
    },

    "s1m5": {
        "title": "Multi-Field Banner",
        "stage": 1, "tier": "medium", "concept": "several variables, one line",
        "briefing": "Create agent='NOVA', level='5', mission='BREACH', status='ACTIVE'. Print one line: NOVA | 5 | BREACH | ACTIVE — fields separated by ' | '.",
        "check": make_checker(
            expected_output="NOVA | 5 | BREACH | ACTIVE",
            success_line="One line, four fields, clean separators. That's a banner NeoCorp would actually print.",
            missing_hint="I don't see the full banner line in your output.",
            mismatch_hint="Check your separators — exactly ' | ' between each field.",
            code_requires=["+"],
        ),
    },

    # ---------------------------------------------------------------- HARD

    "s1h1": {
        "title": "Full Registration Terminal",
        "stage": 1, "tier": "hard", "concept": "variables + concatenation + reassignment",
        "briefing": (
            "Build a registration terminal printout. Create agent='NOVA', clearance='5', "
            "division='NULL SECTOR', status='PENDING'. Print exactly these four lines "
            "(the header is literal, the rest built with concatenation):\n"
            ">>> REGISTRATION TERMINAL <<<\n"
            "AGENT: NOVA\n"
            "CLEARANCE: 5\n"
            "DIVISION: NULL SECTOR\n"
            "Then reassign status to 'ACTIVE' and print a final line: STATUS: ACTIVE"
        ),
        "check": make_checker(
            expected_output=(
                ">>> REGISTRATION TERMINAL <<<\n"
                "AGENT: NOVA\n"
                "CLEARANCE: 5\n"
                "DIVISION: NULL SECTOR\n"
                "STATUS: ACTIVE"
            ),
            success_line="Full dossier, clean terminal output. Agent NOVA is on record.",
            missing_hint="Your output doesn't match the registration terminal format. Check line by line.",
            mismatch_hint="Close — check exact wording, order, and spacing of each line.",
            code_requires=["+", "="],
        ),
    },

    "s1h2": {
        "title": "Mission Briefing Sheet",
        "stage": 1, "tier": "hard", "concept": "several variables, formatted output",
        "briefing": (
            "Create codename='VIPER', mission='BREACH', location='NEOCORP TOWER', clearance='7', "
            "status='ACTIVE'. Print a 5-line briefing, one field per line, each formatted as "
            "'LABEL: value' built with concatenation:\n"
            "CODENAME: VIPER\nMISSION: BREACH\nLOCATION: NEOCORP TOWER\nCLEARANCE: 7\nSTATUS: ACTIVE"
        ),
        "check": make_checker(
            expected_output="CODENAME: VIPER\nMISSION: BREACH\nLOCATION: NEOCORP TOWER\nCLEARANCE: 7\nSTATUS: ACTIVE",
            success_line="That's a full briefing sheet. Every field accounted for.",
            missing_hint="I need all five briefing lines, each on its own line.",
            mismatch_hint="Check labels, order, and spacing against the briefing format.",
            code_requires=["+"],
        ),
    },

    "s1h3": {
        "title": "Network Handshake Protocol",
        "stage": 1, "tier": "hard", "concept": "reassignment across several steps",
        "briefing": (
            "Simulate a handshake log. Create stage='SYN' and print 'STEP 1: ' + stage. "
            "Reassign stage='SYN-ACK' and print 'STEP 2: ' + stage. Reassign stage='ACK' and "
            "print 'STEP 3: ' + stage. Finally print 'LINK ESTABLISHED'."
        ),
        "check": make_checker(
            expected_output="STEP 1: SYN\nSTEP 2: SYN-ACK\nSTEP 3: ACK\nLINK ESTABLISHED",
            success_line="Three-way handshake, logged and confirmed. Link is live.",
            missing_hint="I need all three handshake steps plus the final LINK ESTABLISHED line.",
            mismatch_hint="Check each step's label and value, in order.",
            code_requires=["+", "="],
        ),
    },

    "s1h4": {
        "title": "Identity Card Generator",
        "stage": 1, "tier": "hard", "concept": "concatenation + literal formatting",
        "briefing": (
            "Build an identity card. Create name='NOVA', codename='GHOST', clearance='5'. "
            "Print exactly:\n"
            "==============\nNAME: NOVA\nCODENAME: GHOST\nCLEARANCE: 5\n=============="
        ),
        "check": make_checker(
            expected_output="==============\nNAME: NOVA\nCODENAME: GHOST\nCLEARANCE: 5\n==============",
            success_line="Card printed, borders straight, NeoCorp approved.",
            missing_hint="I don't see the full identity card in your output.",
            mismatch_hint="Check the border characters and field lines match exactly.",
            code_requires=["+"],
        ),
    },

    "s1h5": {
        "title": "BOSS — Terminal Boot Sequence",
        "stage": 1, "tier": "hard", "concept": "everything from Stage 1, combined",
        "briefing": (
            "Final Stage 1 test. Build a full boot sequence log:\n"
            "Print '>>> NULL SECTOR BOOT SEQUENCE <<<'\n"
            "Create status='INITIALIZING', print 'STATUS: ' + status\n"
            "Reassign status='LOADING MODULES', print 'STATUS: ' + status\n"
            "Reassign status='ONLINE', print 'STATUS: ' + status\n"
            "Create agent='NOVA' and clearance='5', print 'AGENT: ' + agent + ' | CLEARANCE: ' + clearance\n"
            "Print 'BOOT COMPLETE.'"
        ),
        "check": make_checker(
            expected_output=(
                ">>> NULL SECTOR BOOT SEQUENCE <<<\n"
                "STATUS: INITIALIZING\n"
                "STATUS: LOADING MODULES\n"
                "STATUS: ONLINE\n"
                "AGENT: NOVA | CLEARANCE: 5\n"
                "BOOT COMPLETE."
            ),
            success_line="Full boot sequence, clean. You're wired in. Stage One complete.",
            missing_hint="Your boot sequence doesn't match. Check each STATUS line and the final AGENT line.",
            mismatch_hint="Check exact wording and order — boot header, three STATUS lines, AGENT line, then BOOT COMPLETE.",
            code_requires=["+", "="],
        ),
    },
}


def check_mission(mission_id, code, output):
    mission = MISSIONS.get(mission_id)
    if mission is None:
        return False, "That mission doesn't exist."
    return mission["check"](code, output)
