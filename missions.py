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
    lightweight source check for concepts output alone can't verify.

    Each entry in code_requires is either a string (must appear in the
    submitted code) or a tuple/list of alternatives (at least one of
    them must appear) — e.g. ('f"', "f'") for either f-string quote
    style."""

    def check(code, output):
        if code_requires:
            for token in code_requires:
                if isinstance(token, (list, tuple)):
                    if not any(alt in code for alt in token):
                        return False, code_requires_hint or "Your code needs to use one of {} for this one.".format(token)
                elif token not in code:
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

    # =====================================================================
    # STAGE 2 — STRINGS & MANIPULATION
    # .upper() .lower() .strip() .title() .count() .replace() len() slicing f-strings
    # =====================================================================

    # ---------------------------------------------------------------- EASY

    "s2e1": {
        "title": "Shout Protocol",
        "stage": 2, "tier": "easy", "concept": ".upper()",
        "briefing": "Store 'access denied' in a variable called text. Print text.upper().",
        "check": make_checker(
            expected_output="ACCESS DENIED",
            success_line="Loud and clear. .upper() doesn't ask permission.",
            missing_hint="I don't see ACCESS DENIED in the output. Use .upper() on your variable.",
            mismatch_hint="Check the exact text and spacing — should be ACCESS DENIED, all caps.",
            code_requires=[".upper()"],
            code_requires_hint="Use the .upper() method on your string variable.",
        ),
    },

    "s2e2": {
        "title": "Whisper Protocol",
        "stage": 2, "tier": "easy", "concept": ".lower()",
        "briefing": "Store 'TOP SECRET' in a variable called text. Print text.lower().",
        "check": make_checker(
            expected_output="top secret",
            success_line="Quiet. .lower() takes the shouting out of it.",
            missing_hint="I don't see 'top secret' in the output. Use .lower() on your variable.",
            mismatch_hint="Check the exact text — should be top secret, all lowercase.",
            code_requires=[".lower()"],
            code_requires_hint="Use the .lower() method on your string variable.",
        ),
    },

    "s2e3": {
        "title": "Clean Signal",
        "stage": 2, "tier": "easy", "concept": ".strip()",
        "briefing": "Store '   ping   ' in a variable called text (with the spaces). Print text.strip().",
        "check": make_checker(
            expected_output="ping",
            success_line="Noise gone. .strip() only touches the edges.",
            missing_hint="I don't see 'ping' in the output. Use .strip() to remove the surrounding spaces.",
            mismatch_hint="Check for leftover spaces — .strip() should remove them all.",
            code_requires=[".strip()"],
            code_requires_hint="Use the .strip() method on your string variable.",
        ),
    },

    "s2e4": {
        "title": "Signal Length",
        "stage": 2, "tier": "easy", "concept": "len()",
        "briefing": "Store 'NULL SECTOR' in a variable called text. Print len(text).",
        "check": make_checker(
            expected_output="11",
            success_line="11 characters, space included. len() counts everything.",
            missing_hint="I don't see 11 in the output. Use len() on your variable.",
            mismatch_hint="Double check — len('NULL SECTOR') should be 11.",
            code_requires=["len("],
            code_requires_hint="Use len() on your string variable.",
        ),
    },

    "s2e5": {
        "title": "First Contact",
        "stage": 2, "tier": "easy", "concept": "slicing",
        "briefing": "Store 'NEOCORP' in a variable called text. Print the first 3 characters using slicing: text[0:3].",
        "check": make_checker(
            expected_output="NEO",
            success_line="First three characters, sliced clean.",
            missing_hint="I don't see NEO in the output. Slice the first 3 characters with text[0:3].",
            mismatch_hint="Check your slice indices — [0:3] should give exactly NEO.",
            code_requires=["["],
            code_requires_hint="Use slicing (square brackets) on your string variable.",
        ),
    },

    # -------------------------------------------------------------- MEDIUM

    "s2m1": {
        "title": "Case Flip Report",
        "stage": 2, "tier": "medium", "concept": ".upper() + .lower() + f-strings",
        "briefing": "Store 'Access Granted' in a variable called text. Using f-strings, print 'UPPER: ' + text.upper() on one line and 'LOWER: ' + text.lower() on the next.",
        "check": make_checker(
            expected_output="UPPER: ACCESS GRANTED\nLOWER: access granted",
            success_line="Both cases, one variable. f-strings make it painless.",
            missing_hint="I need both the UPPER and LOWER lines, each built from the same variable.",
            mismatch_hint="Check the labels and exact casing on each line.",
            code_requires=[("f\"", "f'")],
            code_requires_hint="Use an f-string to build each line.",
        ),
    },

    "s2m2": {
        "title": "Trim And Title",
        "stage": 2, "tier": "medium", "concept": ".strip() + .title()",
        "briefing": "Store '  null sector  ' in a variable called raw. Strip it, then print the result in title case using .title().",
        "check": make_checker(
            expected_output="Null Sector",
            success_line="Trimmed and titled. That's a clean codename.",
            missing_hint="I don't see 'Null Sector' in the output. Strip first, then .title().",
            mismatch_hint="Check spacing and capitalization — should be exactly 'Null Sector'.",
            code_requires=[".strip()", ".title()"],
            code_requires_hint="You need both .strip() and .title() in your code.",
        ),
    },

    "s2m3": {
        "title": "Codename Slice",
        "stage": 2, "tier": "medium", "concept": "slicing + f-strings",
        "briefing": "Store 'GHOST-PROTOCOL' in a variable called text. Print f'CODE: {the first 5 characters}' using slicing inside an f-string.",
        "check": make_checker(
            expected_output="CODE: GHOST",
            success_line="Sliced and labeled. That's your operating codename.",
            missing_hint="I don't see 'CODE: GHOST' in the output.",
            mismatch_hint="Check your slice — the first 5 characters of GHOST-PROTOCOL should be GHOST.",
            code_requires=["["],
            code_requires_hint="Use slicing on your string variable, inside the f-string.",
        ),
    },

    "s2m4": {
        "title": "Count The Signal",
        "stage": 2, "tier": "medium", "concept": ".count() + f-strings",
        "briefing": "Store '1010101' in a variable called text. Print f'ONES: {count of the character 1}' using .count() inside an f-string.",
        "check": make_checker(
            expected_output="ONES: 4",
            success_line="Four ones, counted exactly. .count() doesn't miss.",
            missing_hint="I don't see 'ONES: 4' in the output.",
            mismatch_hint="Check your count — '1010101' has exactly four '1' characters.",
            code_requires=[".count("],
            code_requires_hint="Use .count() on your string variable.",
        ),
    },

    "s2m5": {
        "title": "Redact The Message",
        "stage": 2, "tier": "medium", "concept": ".replace() + f-strings",
        "briefing": "Store 'AGENT NOVA IS COMPROMISED' in a variable called text. Replace 'NOVA' with '[REDACTED]' and print f'MESSAGE: {redacted text}'.",
        "check": make_checker(
            expected_output="MESSAGE: AGENT [REDACTED] IS COMPROMISED",
            success_line="Identity protected. That's how a real redaction looks.",
            missing_hint="I don't see the redacted message in the output.",
            mismatch_hint="Check that NOVA was replaced with exactly [REDACTED], nothing else changed.",
            code_requires=[".replace("],
            code_requires_hint="Use .replace() to swap NOVA for [REDACTED].",
        ),
    },

    # ---------------------------------------------------------------- HARD

    "s2h1": {
        "title": "Full Signal Cleaner",
        "stage": 2, "tier": "hard", "concept": "strip + upper + len + f-strings",
        "briefing": (
            "Store raw = '   breach detected   '. Strip it into a variable called clean. "
            "Print these 3 lines with f-strings:\n"
            "RAW LENGTH: {len(raw)}\nCLEAN: {clean.upper()}\nCLEAN LENGTH: {len(clean)}"
        ),
        "check": make_checker(
            expected_output="RAW LENGTH: 21\nCLEAN: BREACH DETECTED\nCLEAN LENGTH: 15",
            success_line="Full cleanup, measured before and after. That's a real signal report.",
            missing_hint="I need all three lines — RAW LENGTH, CLEAN, and CLEAN LENGTH.",
            mismatch_hint="Check your lengths and casing — raw is 21 characters, clean is 15.",
            code_requires=[".strip()", ".upper()", "len("],
        ),
    },

    "s2h2": {
        "title": "Codename Generator",
        "stage": 2, "tier": "hard", "concept": "slicing + concatenation + upper",
        "briefing": (
            "Store first='GHOST', second='PROTOCOL'. Take the first 2 letters of first and the "
            "first 3 letters of second, concatenate them, and print the uppercase result as "
            "f'CODENAME: {result}'."
        ),
        "check": make_checker(
            expected_output="CODENAME: GHPRO",
            success_line="GHPRO. NeoCorp's database has never seen that name.",
            missing_hint="I don't see 'CODENAME: GHPRO' in the output.",
            mismatch_hint="Check your slice indices — 2 letters from first, 3 from second.",
            code_requires=["[", "+"],
        ),
    },

    "s2h3": {
        "title": "Redaction Protocol",
        "stage": 2, "tier": "hard", "concept": "multiple .replace() + .count()",
        "briefing": (
            "Store text = 'AGENT NOVA MET AGENT VIPER AT NEOCORP TOWER'. Replace both 'NOVA' "
            "and 'VIPER' with '[REDACTED]'. Print the redacted text, then print "
            "f'REDACTIONS: {count of [REDACTED] in the text}'."
        ),
        "check": make_checker(
            expected_output="AGENT [REDACTED] MET AGENT [REDACTED] AT NEOCORP TOWER\nREDACTIONS: 2",
            success_line="Two names scrubbed, count confirmed. Clean redaction.",
            missing_hint="I need both the redacted line and the REDACTIONS count line.",
            mismatch_hint="Check that both NOVA and VIPER were replaced, and the count is right.",
            code_requires=[".replace(", ".count("],
        ),
    },

    "s2h4": {
        "title": "Terminal Formatter",
        "stage": 2, "tier": "hard", "concept": ".title() + .strip() + f-strings",
        "briefing": (
            "Store name = '  agent nova  ' and division = '  null sector  '. Strip and "
            "title-case both. Print two lines with f-strings: 'NAME: {name}' and "
            "'DIVISION: {division}'."
        ),
        "check": make_checker(
            expected_output="NAME: Agent Nova\nDIVISION: Null Sector",
            success_line="Formatted clean. That's how NeoCorp expects a file to look.",
            missing_hint="I need both the NAME and DIVISION lines, cleaned and title-cased.",
            mismatch_hint="Check spacing and capitalization on both lines.",
            code_requires=[".strip()", ".title()"],
        ),
    },

    "s2h5": {
        "title": "BOSS — Full String Processor",
        "stage": 2, "tier": "hard", "concept": "everything from Stage 2, combined",
        "briefing": (
            "Final Stage 2 test. Store raw = '   breach at neocorp tower   '. Strip it into clean. "
            "Print, in order:\n"
            "f'RAW LENGTH: {len(raw)}'\n"
            "f'CLEAN: {clean.upper()}'\n"
            "f'TITLE: {clean.title()}'\n"
            "the first 6 characters of clean, uppercased, as f'FIRST WORD: {...}'\n"
            "clean with 'breach' replaced by '[CONTAINED]', as f'ALERT: {...}'\n"
            "f'E COUNT: {count of \"e\" in clean}'\n"
            "'ANALYSIS COMPLETE.'"
        ),
        "check": make_checker(
            expected_output=(
                "RAW LENGTH: 29\n"
                "CLEAN: BREACH AT NEOCORP TOWER\n"
                "TITLE: Breach At Neocorp Tower\n"
                "FIRST WORD: BREACH\n"
                "ALERT: [CONTAINED] at neocorp tower\n"
                "E COUNT: 3\n"
                "ANALYSIS COMPLETE."
            ),
            success_line="Full string processor, seven lines, zero mistakes. Stage Two complete.",
            missing_hint="Your report doesn't match. Check each of the seven lines in order.",
            mismatch_hint="Check exact wording, casing, and order line by line against the spec.",
            code_requires=[".strip()", ".upper()", ".title()", ".replace(", ".count(", "len("],
        ),
    },
}


def check_mission(mission_id, code, output):
    mission = MISSIONS.get(mission_id)
    if mission is None:
        return False, "That mission doesn't exist."
    return mission["check"](code, output)
