"""
Topic 1 — Language & IDE Basic Elements.

Mission content for this topic. See missions/__init__.py for how the
per-topic modules are stitched into the single MISSIONS mapping the
server and the checkers use, and missions/common.py for the shared
checker factories and boilerplate constants.
"""

from .common import (
    BASIC_BOILERPLATE,
    SCANNER_BOILERPLATE,
    make_checker,
)

MISSIONS = {

    # =====================================================================
    # TOPIC 1 — LANGUAGE & IDE BASIC ELEMENTS
    # class/main structure, System.out.println/print, comments, statement order
    # =====================================================================

    "t1m1": {
        "title": "First Transmission",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "class/main structure + System.out.println()",
        "teach": (
            "Every Java program needs the same skeleton: a class, and inside it a "
            "main() method — that's the exact spot the JVM starts running your code. "
            "You don't write code loose in a file like some languages allow; it always "
            "lives inside that structure.\n\n"
            "To print text, use System.out.println(\"...\") — text goes in double quotes, "
            "and the line ends with a semicolon.\n\n"
            "WORKED EXAMPLE:\n"
            "public class Main {\n"
            "    public static void main(String[] args) {\n"
            "        System.out.println(\"SYSTEM ONLINE\");\n"
            "    }\n"
            "}\n"
            "-> prints: SYSTEM ONLINE"
        ),
        "briefing": "NeoCorp's outer gate checks for one exact signal. Print ACCESS GRANTED to pass through.",
        "hints": [
            "You need System.out.println(...) with your text in double quotes, inside main().",
            "Don't forget the semicolon at the end of the line — Java won't compile without it.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="ACCESS GRANTED",
            success_line="Clean. System.out.println() and a string — that's the whole trick.",
            missing_hint="I don't see 'ACCESS GRANTED' in the output. Use System.out.println() inside main().",
            mismatch_hint="Close — but the output has to be exactly 'ACCESS GRANTED', nothing extra.",
            code_requires=["System.out.println", ";"],
        ),
    },

    "t1m2": {
        "title": "Same Line Signal",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "System.out.print() vs println()",
        "teach": (
            "println() adds a line break after the text. print() does not — the next "
            "thing you print lands right after it, on the same line.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.print(\"A\");\n"
            "System.out.print(\"B\");\n"
            "-> prints: AB   (both on one line, no gap)\n\n"
            "If you'd used println() for both instead, you'd get A and B on two "
            "separate lines."
        ),
        "briefing": "Send two fragments of a signal, NULL and SECTOR, so they arrive fused into one word: NULLSECTOR — no line break between them.",
        "hints": [
            "println() would break them onto two lines. You need the one without 'ln'.",
            "System.out.print(\"NULL\"); then System.out.print(\"SECTOR\"); — two calls, no println.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="NULLSECTOR",
            success_line="Fused clean. print() doesn't add anything between calls — that's the whole difference from println().",
            missing_hint="I don't see NULLSECTOR in the output. Use System.out.print() (not println) twice.",
            mismatch_hint="Check for an unwanted line break — you may have used println() somewhere by mistake.",
            code_requires=["System.out.print"],
        ),
    },

    "t1m3": {
        "title": "Silent Notes",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "comments (// and /* */)",
        "teach": (
            "A comment is text the compiler completely ignores — it's a note for humans "
            "reading the code, not an instruction. // starts a single-line comment; "
            "/* ... */ wraps a comment across multiple lines.\n\n"
            "WORKED EXAMPLE:\n"
            "// this line explains what happens next\n"
            "System.out.println(\"READY\");\n"
            "-> prints: READY   (the comment produces no output at all)"
        ),
        "briefing": "Print SIGNAL LOCKED, and leave yourself a // comment anywhere in the code explaining what the line does — Cipher checks that you actually left one.",
        "hints": [
            "A comment starts with two forward slashes: //",
            "The comment's content doesn't matter — Cipher just needs to see // somewhere in your code, plus the correct printed output.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="SIGNAL LOCKED",
            success_line="Logged and annotated. Comments don't run — they're for the next person reading this.",
            missing_hint="I don't see 'SIGNAL LOCKED' in the output.",
            mismatch_hint="Check your text is exactly SIGNAL LOCKED, and that you included a // comment somewhere.",
            code_requires=["System.out.println", "//"],
            code_requires_hint="I need both a System.out.println() call and a // comment somewhere in your code.",
        ),
    },

    "t1m4": {
        "title": "Sequenced Steps",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "statement order",
        "teach": (
            "Java runs your statements top to bottom, in the exact order you write them "
            "— one at a time, each ending in a semicolon. Nothing runs out of order.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(\"FIRST\");\n"
            "System.out.println(\"SECOND\");\n"
            "-> prints:\n"
            "FIRST\n"
            "SECOND"
        ),
        "briefing": "Print a three-step countdown, in order: STEP 1, then STEP 2, then STEP 3 — each on its own line.",
        "hints": [
            "You need three separate System.out.println() calls.",
            "Order matters — whatever you write first executes first, top to bottom.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="STEP 1\nSTEP 2\nSTEP 3",
            success_line="Three steps, correct order, no skips.",
            missing_hint="I need all three lines — STEP 1, STEP 2, STEP 3.",
            mismatch_hint="Check the order and exact wording of each line.",
            code_requires=["System.out.println"],
        ),
    },

    "t1m5": {
        "title": "BOSS — Boot Sequence",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "everything from Topic 1, combined — write the full structure yourself",
        "teach": (
            "No starter code this time. You've written the class/main skeleton four "
            "times now — prove you don't need it handed to you.\n\n"
            "Reminder of the shape every Java program needs:\n"
            "public class Main {\n"
            "    public static void main(String[] args) {\n"
            "        // your statements go here\n"
            "    }\n"
            "}"
        ),
        "briefing": (
            "Final Topic 1 test. Write the full program yourself — class, main(), everything. "
            "Print, in order:\n"
            ">>> STATIC VOID BOOT SEQUENCE <<<\n"
            "STATUS: ONLINE\n"
            "AGENT: NOVA\n"
            "BOOT COMPLETE.\n"
            "Include at least one // comment anywhere in the code."
        ),
        "hints": [
            "Start with 'public class Main {' then 'public static void main(String[] args) {' — same shape as every mission so far, just typed from scratch.",
            "Four println() calls in order, a // comment anywhere, then close both braces }} at the end.",
        ],
        "boilerplate": "",
        "check": make_checker(
            expected_output=(
                ">>> STATIC VOID BOOT SEQUENCE <<<\n"
                "STATUS: ONLINE\n"
                "AGENT: NOVA\n"
                "BOOT COMPLETE."
            ),
            success_line="Full structure, written from memory, zero mistakes. Topic One complete.",
            missing_hint="Your boot sequence doesn't match, or it didn't compile. Check the class/main structure and each line.",
            mismatch_hint="Check exact wording and order of all four lines, and that you have a // comment somewhere.",
            code_requires=["public class Main", "public static void main", "System.out.println", "//"],
            code_requires_hint="I need the full class/main structure, at least one println, and a // comment.",
        ),
    },
}
