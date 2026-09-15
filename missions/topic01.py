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
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("ACCESS GRANTED");
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("access granted");
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.print("NULL");
        System.out.print("SECTOR");
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("NULL");
        System.out.println("SECTOR");
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        // this line is a comment
        System.out.println("SIGNAL LOCKED");
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("SIGNAL LOCKED");
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("STEP 1");
        System.out.println("STEP 2");
        System.out.println("STEP 3");
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("STEP 1");
        System.out.println("STEP 3");
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        // boot sequence
        System.out.println(">>> STATIC VOID BOOT SEQUENCE <<<");
        System.out.println("STATUS: ONLINE");
        System.out.println("AGENT: NOVA");
        System.out.println("BOOT COMPLETE.");
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("wrong");
    }
}''',
        ],
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

    # ---------------------------------------------------------------------
    # Batch 2 — escape sequences: the characters you can't just type.
    # ---------------------------------------------------------------------

    "t1m6": {
        "title": "Line Break Injection",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "the \\n escape sequence",
        "teach": (
            "You can't press Enter inside a string literal — Java will refuse to "
            "compile a string that runs onto the next line. So how do you print two "
            "lines from ONE println()?\n\n"
            "You use an escape sequence: \\n. That's a backslash followed by the letter "
            "n, and inside a string Java reads those two characters as a single "
            "invisible one — a line break.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(\"NORTH\\nSOUTH\");\n"
            "-> prints:\n"
            "NORTH\n"
            "SOUTH\n\n"
            "One statement, two lines of output. The \\n doesn't appear in the output "
            "itself — it becomes the break between the lines.\n\n"
            "COMMON MISTAKE: using a forward slash (/n). Only the backslash (\\) starts "
            "an escape sequence; \"/n\" just prints the literal characters / and n."
        ),
        "briefing": "The relay only accepts a single transmission, but control needs two lines. Using exactly ONE println(), print TOWER on the first line and GROUND on the second.",
        "hints": [
            "Put \\n between the two words, inside the same pair of quotes.",
            "System.out.println(\"TOWER\\nGROUND\"); — one statement, two lines out.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": r'''public class Main {
    public static void main(String[] args) {
        System.out.println("TOWER\nGROUND");
    }
}''',
        "wrong": [
            # forward slash instead of backslash — prints the characters literally
            r'''public class Main {
    public static void main(String[] args) {
        System.out.println("TOWER/nGROUND");
    }
}''',
        ],
        "check": make_checker(
            expected_output="TOWER\nGROUND",
            success_line="Two lines out of one statement. \\n is a character, not a keypress.",
            missing_hint="I need TOWER and GROUND on two separate lines. Use \\n between them.",
            mismatch_hint="Close — check you used a backslash (\\n), not a forward slash, and that both words are in one string.",
            code_requires=["\\n"],
            code_requires_hint="This one needs the \\n escape sequence inside your string.",
        ),
    },

    "t1m7": {
        "title": "Column Alignment",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "the \\t escape sequence",
        "teach": (
            "\\t is the tab escape — it jumps the cursor to the next tab stop, which is "
            "how you line text up into columns without counting spaces by hand.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(\"NAME\\tRANK\");\n"
            "System.out.println(\"Vega\\tPilot\");\n"
            "-> prints:\n"
            "NAME\tRANK\n"
            "Vega\tPilot\n\n"
            "The two columns line up even though NAME and Vega are different lengths — "
            "a tab stop is a fixed position, not a fixed number of spaces.\n\n"
            "COMMON MISTAKE: expecting \\t to always be the same width as some number "
            "of spaces. It isn't. It advances to the next stop, so how far it moves "
            "depends on where the cursor already was."
        ),
        "briefing": "The manifest display needs two columns. Print ID then a tab then STATUS on the first line, and 7741 then a tab then ACTIVE on the second.",
        "hints": [
            "\\t goes inside the string, exactly where you want the gap: \"ID\\tSTATUS\".",
            "Two println() calls, one per row — each with a \\t between the two column values.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": r'''public class Main {
    public static void main(String[] args) {
        System.out.println("ID\tSTATUS");
        System.out.println("7741\tACTIVE");
    }
}''',
        "wrong": [
            # spaces instead of a real tab character
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("ID    STATUS");
        System.out.println("7741    ACTIVE");
    }
}''',
        ],
        "check": make_checker(
            expected_output="ID\tSTATUS\n7741\tACTIVE",
            success_line="Columns locked. \\t does the alignment so you don't have to count spaces.",
            missing_hint="I need two rows: ID/STATUS then 7741/ACTIVE, separated by tabs.",
            mismatch_hint="Check you used \\t between the columns — spaces look similar on screen but aren't the same character.",
            code_requires=["\\t"],
            code_requires_hint="This one needs the \\t escape sequence, not spaces.",
        ),
    },

    "t1m8": {
        "title": "Quoting The Quote",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "escaped quotes (\\\") and backslashes (\\\\)",
        "teach": (
            "A double quote ends a string — so how do you print one? If you write\n"
            "System.out.println(\"She said \"stop\"\");\n"
            "Java sees the string end at the quote before 'stop' and then chokes on the "
            "rest. It won't compile.\n\n"
            "The fix is \\\" — a backslash before the quote tells Java 'this one is part "
            "of the text, not the end of it'.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(\"He typed \\\"exit\\\" and left.\");\n"
            "-> prints: He typed \"exit\" and left.\n\n"
            "Same idea for a backslash itself. One backslash starts an escape, so to "
            "print an actual backslash you write two: \\\\\n"
            "System.out.println(\"C\\\\temp\");   -> prints: C\\temp\n\n"
            "COMMON MISTAKE: writing \"C\\temp\" when you meant a literal backslash. "
            "That \\t is a TAB — you'd get 'C' then a tab then 'emp'."
        ),
        "briefing": "The intercept log has to quote the exact phrase. Print this line, quotation marks and all:\nCipher said \"trust nothing\"",
        "hints": [
            "Escape each quote you want to appear in the output with a backslash: \\\"",
            "System.out.println(\"Cipher said \\\"trust nothing\\\"\"); — note the final \" that actually ends the string.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": r'''public class Main {
    public static void main(String[] args) {
        System.out.println("Cipher said \"trust nothing\"");
    }
}''',
        "wrong": [
            # single quotes instead of escaped double quotes
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("Cipher said 'trust nothing'");
    }
}''',
        ],
        "check": make_checker(
            expected_output='Cipher said "trust nothing"',
            success_line="Real quotes in the output. \\\" is how you say 'this quote is text, not punctuation'.",
            missing_hint="I need the line to include actual double quote characters around trust nothing.",
            mismatch_hint="Close — those need to be double quotes (escaped with \\\"), not single quotes.",
            code_requires=['\\"'],
            code_requires_hint="This one needs escaped double quotes (\\\") inside your string.",
        ),
    },

    "t1m9": {
        "title": "Dead Air",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "println() with no arguments",
        "teach": (
            "Sometimes you want a blank line — spacing between sections of output. "
            "Calling println() with nothing inside the brackets does exactly that: it "
            "prints no text, then moves to the next line.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(\"TOP\");\n"
            "System.out.println();\n"
            "System.out.println(\"BOTTOM\");\n"
            "-> prints:\n"
            "TOP\n"
            "(blank line)\n"
            "BOTTOM\n\n"
            "Note the empty brackets — println() with nothing at all, not println(\"\") "
            "(which also works, but the empty version says what you mean).\n\n"
            "COMMON MISTAKE: using print() instead. print() with no arguments won't "
            "compile — there's no version of it that takes nothing, because printing "
            "nothing and not moving to a new line would do literally nothing."
        ),
        "briefing": "The report needs breathing room. Print HEADER, then a completely blank line, then FOOTER.",
        "hints": [
            "Three statements: println with text, println with empty brackets, println with text.",
            "System.out.println(); — nothing between the brackets — is your blank line.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("HEADER");
        System.out.println();
        System.out.println("FOOTER");
    }
}''',
        "wrong": [
            # no blank line between them
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("HEADER");
        System.out.println("FOOTER");
    }
}''',
        ],
        "check": make_checker(
            expected_output="HEADER\n\nFOOTER",
            success_line="Blank line delivered. Empty brackets, empty line.",
            missing_hint="I need HEADER, then an empty line, then FOOTER.",
            mismatch_hint="Both words are there, but I don't see the blank line between them — add System.out.println();",
        ),
    },

    "t1m10": {
        "title": "BOSS — Signal Banner",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "every escape sequence from this block, combined — no starter code",
        "teach": (
            "No starter code from here on in a BOSS mission — class, main, all of it, "
            "you write yourself. That's the point: the ceremony has to become automatic "
            "before the concepts can sit on top of it.\n\n"
            "RECAP of this block:\n"
            "\\n   line break inside a string\n"
            "\\t   tab, for columns\n"
            "\\\"   a double quote that's part of the text\n"
            "\\\\   a literal backslash\n"
            "System.out.println();   an empty line\n\n"
            "You can mix as many escapes into one string as you like."
        ),
        "briefing": (
            "Build the transmission banner. Write the whole program from scratch and "
            "produce exactly this output:\n\n"
            "=== STATIC VOID ===\n"
            "MODE\tSECURE\n"
            "\n"
            "Cipher says \"stay quiet\"\n\n"
            "That's: a header line, a tabbed row, a blank line, then the quoted line."
        ),
        "hints": [
            "Start from public class Main { public static void main(String[] args) { — then the printing.",
            "You can do it in as few as three statements: one with \\t or \\n, one empty println(), one with escaped quotes.",
        ],
        "boilerplate": "",
        "solution": r'''public class Main {
    public static void main(String[] args) {
        System.out.println("=== STATIC VOID ===");
        System.out.println("MODE\tSECURE");
        System.out.println();
        System.out.println("Cipher says \"stay quiet\"");
    }
}''',
        "wrong": [
            # missing the blank line and using the wrong quote characters
            r'''public class Main {
    public static void main(String[] args) {
        System.out.println("=== STATIC VOID ===");
        System.out.println("MODE\tSECURE");
        System.out.println("Cipher says 'stay quiet'");
    }
}''',
        ],
        "check": make_checker(
            expected_output='=== STATIC VOID ===\nMODE\tSECURE\n\nCipher says "stay quiet"',
            success_line="Banner up, every escape sequence in its right place. Block complete.",
            missing_hint="The banner doesn't match, or it didn't compile. Check the class and main structure first, then each line.",
            mismatch_hint="Part of it is right. Check the tab, the blank line, and the escaped quotes one at a time.",
            code_requires=["public class Main", "public static void main"],
            code_requires_hint="No starter code on a BOSS — you need to write the class and main() yourself.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 3 — literals, concatenation, and Java's strictness about names.
    # ---------------------------------------------------------------------

    "t1m11": {
        "title": "Numbers Without Quotes",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "number literals vs string literals",
        "teach": (
            "println() can print a number directly — no quotes needed. Quotes make "
            "something a STRING (text); without them, a number is a NUMBER.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(99);      -> prints: 99   (the number ninety-nine)\n"
            "System.out.println(\"99\");    -> prints: 99   (the text '9' then '9')\n\n"
            "Identical on screen. Completely different to the computer. The number can "
            "be used in arithmetic:\n"
            "System.out.println(20 + 5);     -> prints: 25   (Java adds them)\n"
            "System.out.println(\"20\" + \"5\"); -> prints: 205  (Java glues the text)\n\n"
            "That second one catches people constantly, and it's the whole reason this "
            "distinction matters — it decides whether + means 'add' or 'join'.\n\n"
            "COMMON MISTAKE: quoting a number you want to do maths with. \"20\" + \"5\" "
            "is 205, not 25, and Java won't warn you, because joining text is a "
            "perfectly legal thing to ask for."
        ),
        "briefing": "Two lines. First, print the number 40 plus the number 2 as real arithmetic. Second, print the text 40 joined to the text 2 — no arithmetic. So the output reads 42, then 402.",
        "hints": [
            "Line one: System.out.println(40 + 2); — no quotes, so Java adds.",
            "Line two: the same two values, each in double quotes, so Java joins them instead.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println(40 + 2);
        System.out.println("40" + "2");
    }
}''',
        "wrong": [
            # hardcoded both results instead of showing the difference
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("42");
        System.out.println("402");
    }
}''',
        ],
        "check": make_checker(
            expected_output="42\n402",
            success_line="42 then 402 — same symbols, different meaning. Quotes decide whether + adds or joins.",
            missing_hint="I need two lines: the arithmetic result, then the joined text.",
            mismatch_hint="One of the two is off. 40 + 2 without quotes is 42; \"40\" + \"2\" with quotes is 402.",
            code_requires=["40 + 2", '"40"'],
            code_requires_hint="I need to see the unquoted arithmetic (40 + 2) AND the quoted version (\"40\" + \"2\").",
        ),
    },

    "t1m12": {
        "title": "Joining Fragments",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "String concatenation with +",
        "teach": (
            "The + operator between two strings joins them end to end. This is called "
            "CONCATENATION, and it's how you build up a line of output from pieces.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(\"DEEP\" + \"SPACE\");\n"
            "-> prints: DEEPSPACE\n\n"
            "Notice there's no space between them. + joins EXACTLY what's in the quotes "
            "— it doesn't add anything of its own. If you want a gap, the gap has to be "
            "inside one of the strings:\n"
            "System.out.println(\"DEEP\" + \" \" + \"SPACE\");   -> prints: DEEP SPACE\n"
            "System.out.println(\"DEEP \" + \"SPACE\");        -> prints: DEEP SPACE\n\n"
            "Both work. The second is what most people write.\n\n"
            "COMMON MISTAKE: assuming + inserts a space because it looks like a gap in "
            "your source code. It doesn't. Whitespace around the + is just formatting; "
            "only what's inside the quotes reaches the output."
        ),
        "briefing": "Assemble the call sign from three separate fragments. Using + to join the strings \"NIGHT\", a space, and \"WATCH\" in one println(), print: NIGHT WATCH",
        "hints": [
            "You need the space to come from inside a string — \" \" on its own, or on the end of \"NIGHT \".",
            "System.out.println(\"NIGHT\" + \" \" + \"WATCH\");",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("NIGHT" + " " + "WATCH");
    }
}''',
        "wrong": [
            # relied on the spacing around + in the source, which never reaches the output
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("NIGHT" + "WATCH");
    }
}''',
        ],
        "check": make_checker(
            expected_output="NIGHT WATCH",
            success_line="Joined with a real space. + glues exactly what's in the quotes, nothing more.",
            missing_hint="I need NIGHT WATCH on one line, built with + from separate strings.",
            mismatch_hint="If you got NIGHTWATCH, the space isn't inside any of your quotes — the gaps around + don't count.",
            code_requires=["+"],
            code_requires_hint="Use + to join the fragments rather than typing the finished line as one string.",
        ),
    },

    "t1m13": {
        "title": "Labelled Readout",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "concatenating a number onto text",
        "teach": (
            "When + has a string on one side and a number on the other, Java converts "
            "the number to text and joins them. This is how nearly every real line of "
            "output gets built: a label, then a value.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(\"DEPTH: \" + 300);\n"
            "-> prints: DEPTH: 300\n\n"
            "The 300 has no quotes — it's a genuine number — but because it's being "
            "joined to a string, Java turns it into \"300\" for printing.\n\n"
            "Watch the space: \"DEPTH: \" ends with a space INSIDE the quotes. Without "
            "it you'd get DEPTH:300 jammed together.\n\n"
            "COMMON MISTAKE: \"TOTAL: \" + 2 + 3 prints TOTAL: 23, not TOTAL: 5. Java "
            "works left to right — once it has joined text to 2, everything after is "
            "joining too. Wrap the maths in brackets: \"TOTAL: \" + (2 + 3)."
        ),
        "briefing": "The reactor display needs a labelled value. Print the label CORE TEMP: followed by the result of the arithmetic 80 + 15 — as a real calculation, not a typed-in 95.",
        "hints": [
            "Put the arithmetic in brackets so it's calculated before it's joined: (80 + 15)",
            "System.out.println(\"CORE TEMP: \" + (80 + 15)); — note the space after the colon, inside the quotes.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("CORE TEMP: " + (80 + 15));
    }
}''',
        "wrong": [
            # no brackets — left-to-right joining gives CORE TEMP: 8015
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("CORE TEMP: " + 80 + 15);
    }
}''',
        ],
        "check": make_checker(
            expected_output="CORE TEMP: 95",
            success_line="Label and a calculated value. Brackets forced the maths to happen before the joining.",
            missing_hint="I need the line CORE TEMP: 95, with the 95 coming from 80 + 15.",
            mismatch_hint="If you got CORE TEMP: 8015, the addition got swallowed by the joining — put brackets around 80 + 15.",
            code_requires=["80", "15", "+"],
            code_requires_hint="Do the arithmetic in the code (80 + 15) rather than typing 95 yourself.",
        ),
    },

    "t1m14": {
        "title": "Exact Spelling",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "case sensitivity and exact identifiers",
        "teach": (
            "Java is CASE SENSITIVE. System and system are two different words to the "
            "compiler, and only one of them exists. These all fail:\n\n"
            "system.out.println(\"hi\");   // lowercase s — no such thing\n"
            "System.Out.println(\"hi\");   // capital O — no such thing\n"
            "System.out.printLn(\"hi\");   // capital L — no such thing\n"
            "System.out.println(\"hi\")    // no semicolon\n\n"
            "None of those are 'close enough'. The compiler does not guess.\n\n"
            "WORKED EXAMPLE — the only correct spelling:\n"
            "System.out.println(\"ONLINE\");\n"
            "capital S, lowercase out, lowercase p, capital L in the middle of println, "
            "brackets, semicolon.\n\n"
            "COMMON MISTAKE: reading 'cannot find symbol' as a mysterious error. It "
            "almost always means exactly one thing — something is spelled or capitalised "
            "differently from how it was defined. The compiler even points at the spot "
            "with a ^ marker; check that word character by character."
        ),
        "briefing": "Three deliberately broken lines are in your editor, each with a different capitalisation mistake. Fix all three so the program compiles and prints SYSTEMS NOMINAL on one line.",
        "hints": [
            "Go word by word: System (capital S), out (lowercase), println (lowercase p, capital L).",
            "The correct form is System.out.println(\"SYSTEMS NOMINAL\"); — everything else in the editor is a variation on a typo.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    public static void main(String[] args) {\n"
            "        // three broken lines — fix them into one correct one\n"
            "        // system.out.println(\"SYSTEMS NOMINAL\");\n"
            "        // System.Out.println(\"SYSTEMS NOMINAL\");\n"
            "        // System.out.printLn(\"SYSTEMS NOMINAL\");\n"
            "    }\n"
            "}"
        ),
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("SYSTEMS NOMINAL");
    }
}''',
        "check": make_checker(
            expected_output="SYSTEMS NOMINAL",
            success_line="Spelled exactly. 'cannot find symbol' nearly always means a capital letter in the wrong place.",
            missing_hint="Nothing printed. Check System (capital S), out, and println (capital L only) — and the semicolon.",
            mismatch_hint="Close — the text has to be exactly SYSTEMS NOMINAL.",
            code_requires=["System.out.println"],
            code_requires_hint="I need one correctly spelled System.out.println — capital S, lowercase out, capital L in println.",
        ),
    },

    "t1m15": {
        "title": "BOSS — Status Console",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "literals, concatenation and exact syntax, combined — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "\"text\"        a string literal\n"
            "42            a number literal — no quotes, can do arithmetic\n"
            "\"A\" + \"B\"     joins two strings (adds no space of its own)\n"
            "\"N: \" + 42    joins a number onto text\n"
            "\"N: \" + (2+3) brackets make the maths happen BEFORE the joining\n\n"
            "And every identifier is case sensitive, every statement ends in a "
            "semicolon."
        ),
        "briefing": (
            "Write the whole program yourself. Build a two-line status console where "
            "every value is CALCULATED, not typed:\n\n"
            "UNITS: 12\n"
            "TOTAL POWER: 240\n\n"
            "UNITS is 4 + 8. TOTAL POWER is 12 * 20. Both must appear as arithmetic in "
            "your code."
        ),
        "hints": [
            "Each line is a label string joined to a bracketed calculation: \"UNITS: \" + (4 + 8)",
            "Remember the space after each colon lives inside the quotes, and brackets keep the maths from being swallowed by the joining.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("UNITS: " + (4 + 8));
        System.out.println("TOTAL POWER: " + (12 * 20));
    }
}''',
        "wrong": [
            # values typed in rather than calculated
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("UNITS: 12");
        System.out.println("TOTAL POWER: 240");
    }
}''',
        ],
        "check": make_checker(
            expected_output="UNITS: 12\nTOTAL POWER: 240",
            success_line="Both values calculated, both lines labelled, structure written from scratch. Block complete.",
            missing_hint="The console doesn't match, or it didn't compile. Check the class/main structure, then each line.",
            mismatch_hint="Check the labels and spacing — and that the numbers come from (4 + 8) and (12 * 20).",
            code_requires=["public class Main", "public static void main", "4 + 8", "12 * 20"],
            code_requires_hint="Both numbers have to be worked out in code — I need to see 4 + 8 and 12 * 20.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 4 — statements, formatting, and the three kinds of comment.
    # ---------------------------------------------------------------------

    "t1m16": {
        "title": "Statement Boundaries",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "the semicolon ends a statement, not the line",
        "teach": (
            "A Java statement ends at the SEMICOLON, not at the end of the line. Java "
            "doesn't care about line breaks at all — the semicolon is the only thing "
            "that says 'this instruction is finished'.\n\n"
            "WORKED EXAMPLE — these two are identical to the compiler:\n"
            "System.out.println(\"A\"); System.out.println(\"B\");\n"
            "\n"
            "System.out.println(\"A\");\n"
            "System.out.println(\"B\");\n"
            "-> both print:\n"
            "A\n"
            "B\n\n"
            "Two statements either way, two lines of output either way. The line break "
            "in your source is for humans; the semicolons are for the compiler.\n\n"
            "So why put one statement per line? Because it's readable and because error "
            "messages point at a LINE — three statements crammed on line 4 means "
            "'error on line 4' tells you much less.\n\n"
            "COMMON MISTAKE: thinking the missing semicolon error is on the line the "
            "compiler names. It often points at the NEXT line, because that's where "
            "Java finally realised something was wrong."
        ),
        "briefing": "Prove you understand where a statement really ends: put both println() calls on ONE line of source, printing ALPHA then BRAVO on two lines of output.",
        "hints": [
            "Two complete statements, each with its own semicolon, sharing a single line of source.",
            "System.out.println(\"ALPHA\"); System.out.println(\"BRAVO\"); — all on one line.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("ALPHA"); System.out.println("BRAVO");
    }
}''',
        "check": make_checker(
            expected_output="ALPHA\nBRAVO",
            success_line="Two statements, one source line, two output lines. The semicolon is the boundary — not the newline.",
            missing_hint="I need ALPHA then BRAVO on separate output lines.",
            mismatch_hint="Check both words and their order — ALPHA first, then BRAVO.",
        ),
    },

    "t1m17": {
        "title": "Whitespace Is Yours",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "indentation and spacing are for humans, not the compiler",
        "teach": (
            "Java ignores how much whitespace you use. Indentation, blank lines, extra "
            "spaces around brackets — the compiler strips all of it. This compiles and "
            "runs perfectly:\n\n"
            "public class Main{public static void main(String[] args){\n"
            "System.out.println(\"OK\");}}\n\n"
            "It also makes you want to close the laptop. Formatting exists so a human "
            "can see the structure at a glance: everything inside a { } block gets "
            "indented one level deeper than the line that opened it.\n\n"
            "WORKED EXAMPLE — same program, formatted the way every Java codebase you "
            "will ever work in does it:\n"
            "public class Main {\n"
            "    public static void main(String[] args) {\n"
            "        System.out.println(\"OK\");\n"
            "    }\n"
            "}\n"
            "Class body indented 4 spaces, main's body indented 8. Your IDE will do this "
            "for you — in IntelliJ it's Ctrl+Alt+L, in Eclipse Ctrl+Shift+F.\n\n"
            "COMMON MISTAKE: assuming badly indented code is broken code. It isn't — "
            "which is exactly why a stray missing brace can be so hard to spot. Reformat "
            "first, then read."
        ),
        "briefing": "Your editor has a correct but unreadable program, all crushed onto one line. Reformat it properly — class body indented, main body indented deeper, closing braces lined up — without changing what it does. It should still print STRUCTURE MATTERS.",
        "hints": [
            "Put each { on the end of the line that opens it, and each } on its own line, lined up with the start of that line.",
            "Four spaces for the main() line, eight for the println, then close main, then close the class.",
        ],
        "boilerplate": (
            'public class Main{public static void main(String[] args){System.out.println("STRUCTURE MATTERS");}}'
        ),
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("STRUCTURE MATTERS");
    }
}''',
        "check": make_checker(
            expected_output="STRUCTURE MATTERS",
            success_line="Same behaviour, readable shape. Whitespace costs the compiler nothing and saves you hours.",
            missing_hint="I need it to still print STRUCTURE MATTERS — reformat it, don't rewrite what it does.",
            mismatch_hint="The text has to stay exactly STRUCTURE MATTERS.",
            # a real reformat means line breaks AND two levels of indentation;
            # just inserting a newline somewhere shouldn't count.
            code_requires=["\n    public static void main", "\n        System.out.println"],
            code_requires_hint="Not reformatted yet: main() should sit on its own line indented 4 spaces, and the println on its own line indented 8.",
        ),
    },

    "t1m18": {
        "title": "Blocked Out",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "block comments /* */ and commenting code out",
        "teach": (
            "You met // in an earlier mission — it comments out the rest of ONE line. "
            "A block comment runs from /* to */ and can span as many lines as you like.\n\n"
            "WORKED EXAMPLE:\n"
            "/* This whole thing is ignored,\n"
            "   including this second line. */\n"
            "System.out.println(\"RUNS\");\n"
            "-> prints: RUNS\n\n"
            "The most common real use isn't writing prose — it's temporarily disabling "
            "code you don't want to run yet, without deleting it:\n\n"
            "System.out.println(\"KEEP\");\n"
            "/* System.out.println(\"SILENCED\"); */\n"
            "-> prints only: KEEP\n\n"
            "COMMON MISTAKE: forgetting the */. Everything after /* is swallowed until "
            "Java finds a closing */ — miss it and the rest of your file vanishes, "
            "usually producing a baffling 'reached end of file while parsing'."
        ),
        "briefing": "Your editor has two println() calls. Use a block comment to silence the second one WITHOUT deleting it, so only CHANNEL ONE prints.",
        "hints": [
            "Wrap the line you want silenced in /* and */.",
            "/* System.out.println(\"CHANNEL TWO\"); */ — the code stays in the file but never runs.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    public static void main(String[] args) {\n"
            "        System.out.println(\"CHANNEL ONE\");\n"
            "        System.out.println(\"CHANNEL TWO\");\n"
            "    }\n"
            "}"
        ),
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("CHANNEL ONE");
        /* System.out.println("CHANNEL TWO"); */
    }
}''',
        "wrong": [
            # deleted the line instead of commenting it out
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("CHANNEL ONE");
    }
}''',
        ],
        "check": make_checker(
            expected_output="CHANNEL ONE",
            success_line="Silenced, not deleted. That's how you park code you might want back in a minute.",
            missing_hint="I need CHANNEL ONE to print. Check you didn't comment out the wrong line.",
            mismatch_hint="CHANNEL TWO is still printing — the block comment isn't wrapping it properly.",
            code_requires=["/*", "*/", "CHANNEL TWO"],
            code_requires_hint="Comment the second line OUT with /* */ — don't delete it. I need to still see CHANNEL TWO in your source.",
        ),
    },

    "t1m19": {
        "title": "Documented For The Record",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "Javadoc comments /** */",
        "teach": (
            "There's a third comment form: /** — with two stars — placed directly above "
            "a class or method. It's a JAVADOC comment, and it's how Java code documents "
            "itself.\n\n"
            "WORKED EXAMPLE:\n"
            "/**\n"
            " * Prints the current reactor state to the console.\n"
            " */\n"
            "public class Reactor {\n\n"
            "Why the separate form? Because tools read it. The `javadoc` tool turns "
            "these into browsable HTML documentation, and your IDE shows the text as a "
            "tooltip when you hover a method — which is exactly where the descriptions "
            "you see for built-in methods come from.\n\n"
            "The convention is a /** line, then lines starting with a single *, then a "
            "closing */. The leading stars are cosmetic; Java strips them.\n\n"
            "COMMON MISTAKE: using /** for a note in the middle of a method. Javadoc "
            "documents the thing immediately BELOW it — a class, a method, a field. For "
            "a passing note inside code, // is the right tool."
        ),
        "briefing": "Document your program properly: put a Javadoc comment directly above the class explaining what it does, then have it print DOCUMENTED.",
        "hints": [
            "The Javadoc block goes ABOVE the public class Main line, starting with /** and ending with */.",
            "/**\\n * Prints a confirmation that this program is documented.\\n */ then your class as normal.",
        ],
        "boilerplate": (
            "// replace this line with a proper /** ... */ Javadoc comment\n"
            "public class Main {\n"
            "    public static void main(String[] args) {\n"
            "        // YOUR CODE HERE\n"
            "    }\n"
            "}"
        ),
        "solution": '''/**
 * Confirms that this program carries proper documentation.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("DOCUMENTED");
    }
}''',
        "wrong": [
            # ordinary // comment instead of Javadoc
            '''// prints a confirmation
public class Main {
    public static void main(String[] args) {
        System.out.println("DOCUMENTED");
    }
}''',
        ],
        "check": make_checker(
            expected_output="DOCUMENTED",
            success_line="Javadoc in place. That's the comment form the tooling actually reads.",
            missing_hint="I need the program to print DOCUMENTED.",
            mismatch_hint="Check the text is exactly DOCUMENTED.",
            code_requires=["/**", "*/"],
            code_requires_hint="This one needs a Javadoc comment — /** with two stars to open, */ to close — above the class.",
        ),
    },

    "t1m20": {
        "title": "BOSS — Annotated Boot Record",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "all three comment forms plus clean structure — no starter code",
        "teach": (
            "No starter code. RECAP of every comment form in Java:\n\n"
            "//            to the end of this line\n"
            "/* ... */     a block, any number of lines, also used to disable code\n"
            "/** ... */    Javadoc — documents the class or method below it\n\n"
            "None of them produce output. All of them are for the next person who reads "
            "this file, which is usually you, three weeks later.\n\n"
            "Statements end at semicolons; indentation is for humans; identifiers are "
            "case sensitive."
        ),
        "briefing": (
            "Write a complete, properly formatted, fully annotated program from scratch. "
            "It must contain all three comment forms:\n"
            "- a /** Javadoc */ block above the class\n"
            "- a // line comment somewhere inside main()\n"
            "- a /* block comment */ that silences a println() WITHOUT deleting it\n\n"
            "The program prints exactly these two lines:\n"
            "BOOT SEQUENCE START\n"
            "BOOT SEQUENCE COMPLETE\n\n"
            "The commented-out println must be a third one that never runs — make it "
            "print DIAGNOSTICS."
        ),
        "hints": [
            "Structure: Javadoc, class, main, println START, a // note, the /* silenced DIAGNOSTICS line */, println COMPLETE.",
            "The silenced line still has to be in your source — Cipher checks that DIAGNOSTICS appears in the code but never in the output.",
        ],
        "boilerplate": "",
        "solution": '''/**
 * Boot record for the STATIC VOID terminal.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("BOOT SEQUENCE START");
        // diagnostics are disabled for now, but kept for later
        /* System.out.println("DIAGNOSTICS"); */
        System.out.println("BOOT SEQUENCE COMPLETE");
    }
}''',
        "wrong": [
            # diagnostics line left live — it runs and pollutes the output
            '''/**
 * Boot record.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("BOOT SEQUENCE START");
        // a note
        System.out.println("DIAGNOSTICS");
        System.out.println("BOOT SEQUENCE COMPLETE");
    }
}''',
        ],
        "check": make_checker(
            expected_output="BOOT SEQUENCE START\nBOOT SEQUENCE COMPLETE",
            success_line="Structure, output and all three comment forms. Topic One's syntax block is done.",
            missing_hint="Output doesn't match, or it didn't compile. Check the class/main structure and both printed lines.",
            mismatch_hint="If DIAGNOSTICS appeared, it isn't properly wrapped in /* */ — it must be in the code but never run.",
            code_requires=["public class Main", "/**", "//", "/*", "DIAGNOSTICS"],
            code_requires_hint="I need all three comment forms — /** */, //, and a /* */ that silences a DIAGNOSTICS println.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 5 — chars, the main() signature, composing a line, final boss.
    # ---------------------------------------------------------------------

    "t1m21": {
        "title": "Single Character",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "char literals use single quotes",
        "teach": (
            "Java has a separate idea for ONE character: a char, written with SINGLE "
            "quotes. Double quotes always mean a string, however short.\n\n"
            "'A'   a char — exactly one character\n"
            "\"A\"   a String — text that happens to be one character long\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println('X');\n"
            "-> prints: X\n\n"
            "Identical output to println(\"X\"), different type underneath. You'll care "
            "about the difference the moment you start storing values in variables.\n\n"
            "The single quotes hold exactly one character. 'AB' is a compile error — "
            "two characters won't fit in a char.\n\n"
            "COMMON MISTAKE: using single quotes for a word. 'STOP' looks reasonable and "
            "is illegal — that's four characters in a one-character box. Words need "
            "double quotes."
        ),
        "briefing": "The grid reference is a single character. Print the char Z using single quotes — not a string.",
        "hints": [
            "Single quotes, one character: 'Z'",
            "System.out.println('Z'); — note the single quotes, which make it a char rather than a String.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println('Z');
    }
}''',
        "wrong": [
            # double quotes — a String, not a char
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("Z");
    }
}''',
        ],
        "check": make_checker(
            expected_output="Z",
            success_line="One char, single quotes. Same output as a String, different type underneath.",
            missing_hint="I need the program to print Z.",
            mismatch_hint="Check it's exactly Z and nothing else.",
            code_requires=["'Z'"],
            code_requires_hint="This one specifically wants a char — 'Z' in single quotes, not \"Z\" in double quotes.",
        ),
    },

    "t1m22": {
        "title": "Composing A Line",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "building one output line from several print() calls",
        "teach": (
            "You've seen print() leaves the cursor where it is. Put several print() "
            "calls in a row and they build ONE line of output between them — then a "
            "final println() closes it.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.print(\"LAT:\");\n"
            "System.out.print(\" 51\");\n"
            "System.out.println(\" N\");\n"
            "-> prints one line: LAT: 51 N\n\n"
            "Three statements, one line. This matters once values come from different "
            "places — a loop, a calculation, a file — and you want them on the same row.\n\n"
            "COMMON MISTAKE: forgetting the final println(). If every call is print(), "
            "the line is never terminated, and the next thing your program prints — or "
            "the shell prompt itself — ends up stuck on the end of it."
        ),
        "briefing": "Assemble one line from three separate statements: print GRID, then print a dash surrounded by spaces, then finish the line with 7. The output is a single line reading GRID - 7.",
        "hints": [
            "Two print() calls then one println() — the last one ends the line.",
            "System.out.print(\"GRID\"); System.out.print(\" - \"); System.out.println(7);",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.print("GRID");
        System.out.print(" - ");
        System.out.println(7);
    }
}''',
        "wrong": [
            # all println — three lines instead of one
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("GRID");
        System.out.println(" - ");
        System.out.println(7);
    }
}''',
        ],
        "check": make_checker(
            expected_output="GRID - 7",
            success_line="Three statements, one line. print() builds it, println() closes it.",
            missing_hint="I need a single line reading GRID - 7, built from three separate statements.",
            mismatch_hint="If you got three lines, you used println() for all of them — only the last one should be println().",
            code_requires=["System.out.print(", "System.out.println("],
            code_requires_hint="I need at least one print() to build the line and a println() to finish it.",
        ),
    },

    "t1m23": {
        "title": "The Entry Point",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "why main()'s signature is exactly what it is",
        "teach": (
            "public static void main(String[] args) — you've typed it every mission. "
            "Here's what each word is doing, because a typo in any of them produces a "
            "program that compiles and then refuses to start.\n\n"
            "public      the JVM, which is outside your class, has to be able to see it\n"
            "static      it runs without anyone creating an object first\n"
            "void        it hands nothing back\n"
            "main        the exact name the JVM looks for. Not Main, not start\n"
            "String[] args   command-line arguments, as an array of text\n\n"
            "WORKED EXAMPLE of the failure mode:\n"
            "public static void main() { ... }\n"
            "That COMPILES — it's a perfectly legal method. But running it gives:\n"
            "  Main method not found in class Main\n"
            "because the JVM was looking for a main that takes String[] args, and this "
            "one doesn't.\n\n"
            "COMMON MISTAKE: assuming 'it compiled, so the signature is right'. The "
            "compiler only checks the method is legal Java. Whether it's the entry point "
            "is a question that only comes up at run time."
        ),
        "briefing": "The program in your editor compiles but won't run — the JVM can't find its entry point. Fix the main() signature so it starts, and have it print ENTRY POINT FOUND.",
        "hints": [
            "Compare the signature in the editor against the full one, word by word: public static void main(String[] args)",
            "The missing piece is the parameter — String[] args — without it the JVM doesn't recognise the method as the entry point.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    // this compiles, but the JVM won't accept it as the entry point\n"
            "    public static void main() {\n"
            "        // YOUR CODE HERE\n"
            "    }\n"
            "}"
        ),
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("ENTRY POINT FOUND");
    }
}''',
        "wrong": [
            # printing is right, signature still wrong — compiles, won't run
            '''public class Main {
    public static void main() {
        System.out.println("ENTRY POINT FOUND");
    }
}''',
        ],
        "check": make_checker(
            expected_output="ENTRY POINT FOUND",
            success_line="Entry point restored. Compiling and being runnable are two different questions.",
            missing_hint="Nothing ran. Check the signature is exactly public static void main(String[] args).",
            mismatch_hint="Check the text is exactly ENTRY POINT FOUND.",
            code_requires=["String[] args"],
            code_requires_hint="The JVM needs main to take String[] args — that's the part that's missing.",
        ),
    },

    "t1m24": {
        "title": "Reading The Error",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "diagnosing compile errors from the message",
        "teach": (
            "Compiler errors are not obstacles, they're instructions. Learning to read "
            "them is the single biggest speed-up available to you in this module. The "
            "three you'll meet constantly:\n\n"
            "';' expected\n"
            "    a statement has no semicolon. Java names the line it gave up on, which\n"
            "    is often the line AFTER the real mistake.\n\n"
            "cannot find symbol\n"
            "    a name doesn't exist — misspelled, wrong capitalisation, or a variable\n"
            "    used before it was declared. The ^ marker points at the exact word.\n\n"
            "reached end of file while parsing\n"
            "    a closing brace } is missing. Count your braces: every { needs one.\n\n"
            "WORKED EXAMPLE:\n"
            "Main.java:3: error: ';' expected\n"
            "        System.out.println(\"HI\")\n"
            "                               ^\n"
            "Line 3, right after the closing bracket. Add the semicolon there.\n\n"
            "COMMON MISTAKE: fixing errors from the bottom up. Always fix the FIRST "
            "error and recompile — one missing brace can invent a dozen fake errors "
            "underneath it."
        ),
        "briefing": "The editor holds a program with three separate syntax errors: a missing semicolon, a misspelled identifier, and a missing brace. Fix all three so it compiles and prints ERRORS CLEARED.",
        "hints": [
            "Run it and read the FIRST error only. Fix that, run again — the list will shrink faster than you expect.",
            "One line needs a ; at the end, one has println misspelled, and the class is missing its final }.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    public static void main(String[] args) {\n"
            "        System.out.printn(\"ERRORS CLEARED\")\n"
            "    }"
        ),
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("ERRORS CLEARED");
    }
}''',
        "check": make_checker(
            expected_output="ERRORS CLEARED",
            success_line="All three cleared. Fix the first error, recompile, repeat — the rest often vanish on their own.",
            missing_hint="Still not compiling. Work through the errors from the top: first the spelling, then the semicolon, then the brace.",
            mismatch_hint="Check the text is exactly ERRORS CLEARED.",
            code_requires=["System.out.println"],
            code_requires_hint="println is currently misspelled — check it character by character.",
        ),
    },

    "t1m25": {
        "title": "FINAL BOSS — Cold Start",
        "topic": 1, "topic_name": "Language & IDE Basic Elements",
        "concept": "the entire topic, from an empty editor",
        "teach": (
            "Empty editor. Everything from Topic 1, at once.\n\n"
            "FULL RECAP:\n"
            "public class Main { public static void main(String[] args) { } }\n"
            "System.out.println(x)   prints and ends the line\n"
            "System.out.print(x)     prints, stays on the line\n"
            "System.out.println()    a blank line\n"
            "\\n \\t \\\" \\\\            line break, tab, quote, backslash\n"
            "\"A\" + \"B\"              joins text; \"N: \" + (2+3) needs the brackets\n"
            "'A'                     a single char\n"
            "// /* */ /** */         line, block and Javadoc comments\n"
            "Every statement ends in a semicolon; every name is case sensitive.\n\n"
            "This is the whole foundation the other nine topics are built on. If you can "
            "produce this from nothing, the ceremony is no longer in your way."
        ),
        "briefing": (
            "Cold start. No starter code, no hand-holding. Produce this exact output:\n\n"
            "=== COLD START ===\n"
            "STAGE\tSTATUS\n"
            "1\tOK\n"
            "2\tOK\n"
            "\n"
            "UNITS ONLINE: 9\n"
            "Cipher: \"we're live\"\n\n"
            "Rules: the 9 must be calculated as 3 * 3 in your code, the blank line is a "
            "real empty println(), the columns are real tabs, and the program must carry "
            "a /** Javadoc */ comment above the class."
        ),
        "hints": [
            "Work top to bottom, one println() per output line — get it printing anything first, then fix the details.",
            "The tricky three: \\t between the columns, System.out.println(); for the blank line, and \\\" around we're live.",
        ],
        "boilerplate": "",
        "solution": r'''/**
 * Cold start report for the STATIC VOID terminal.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== COLD START ===");
        System.out.println("STAGE\tSTATUS");
        System.out.println("1\tOK");
        System.out.println("2\tOK");
        System.out.println();
        System.out.println("UNITS ONLINE: " + (3 * 3));
        System.out.println("Cipher: \"we're live\"");
    }
}''',
        "wrong": [
            # spaces for tabs, hardcoded 9, no javadoc
            r'''public class Main {
    public static void main(String[] args) {
        System.out.println("=== COLD START ===");
        System.out.println("STAGE   STATUS");
        System.out.println("1   OK");
        System.out.println("2   OK");
        System.out.println();
        System.out.println("UNITS ONLINE: 9");
        System.out.println("Cipher: \"we're live\"");
    }
}''',
        ],
        "check": make_checker(
            expected_output=(
                "=== COLD START ===\n"
                "STAGE\tSTATUS\n"
                "1\tOK\n"
                "2\tOK\n"
                "\n"
                "UNITS ONLINE: 9\n"
                "Cipher: \"we're live\""
            ),
            success_line="Cold start from an empty editor — structure, escapes, arithmetic, comments, all of it. Topic One complete. Twenty-five missions down.",
            missing_hint="Output doesn't match, or it didn't compile. Build it one line at a time rather than all at once.",
            mismatch_hint="Some lines are right. Check the tabs (\\t, not spaces), the blank line, and the escaped quotes.",
            code_requires=["public class Main", "public static void main", "/**", "3 * 3", "\\t"],
            code_requires_hint="I need the full structure, a /** Javadoc */ above the class, real tabs (\\t), and the 9 calculated as 3 * 3.",
        ),
    },
}
