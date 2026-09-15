"""
Server-side mission content and validation, for the Java rewrite of
Null Sector, mapped topic-for-topic onto the player's actual LJMU
module syllabus (Language & IDE Basics -> Variables & Constants ->
Operators/Expressions/Statements -> Methods/Parameters/Stack ->
Basic I/O & File I/O -> Selection -> Iteration -> Collections ->
Classes -> Exceptions & Event Handling).

Every mission has four parts, not just a checker — this is the fix
for the earlier Python version, which only ever told the player what
to type ("store X in a variable, print it") without ever teaching the
concept first:

  teach     - Cipher explains the concept, with a WORKED EXAMPLE using
              different values than the objective, so the player has
              to transfer the idea rather than copy-paste it.
  briefing  - the objective, framed as a small scenario/problem.
  hints     - 2 progressive hints, revealed one at a time on request,
              not dumped on the player up front.
  boilerplate - starter code pre-filled in the editor. Java requires a
              class + main() every time, and making a beginner retype
              that ceremony on every single mission would bury the
              concept being taught under boilerplate friction.

Missions are checked against captured stdout (never by inspecting the
source beyond what java_sandbox.py's security scan already does),
plus a light substring check on the source for concepts output alone
can't verify (e.g. "did they actually write a comment").
"""


def make_checker(expected_output, success_line, missing_hint, mismatch_hint,
                  code_requires=None, code_requires_hint=None):
    """Compares captured stdout against an exact expected string (no
    trailing newline). code_requires entries are either a string (must
    appear in the submitted code) or a tuple/list of alternatives (at
    least one must appear)."""

    def check(code, output):
        if code_requires:
            for token in code_requires:
                if isinstance(token, (list, tuple)):
                    if not any(alt in code for alt in token):
                        return False, code_requires_hint or "Your code needs one of {} for this one.".format(token)
                elif token not in code:
                    return False, code_requires_hint or "Your code needs to use '{}' for this one.".format(token)

        got = output.rstrip("\n")
        if got == expected_output:
            return True, success_line
        if expected_output in output:
            return False, mismatch_hint
        return False, missing_hint

    return check


BASIC_BOILERPLATE = (
    "public class Main {\n"
    "    public static void main(String[] args) {\n"
    "        // YOUR CODE HERE\n"
    "    }\n"
    "}"
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
            ">>> NULL SECTOR BOOT SEQUENCE <<<\n"
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
                ">>> NULL SECTOR BOOT SEQUENCE <<<\n"
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

    # =====================================================================
    # TOPIC 2 — VARIABLES & CONSTANTS
    # int, String, double, boolean, final
    # =====================================================================

    "t2m1": {
        "title": "First Variable",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "int variables",
        "teach": (
            "A variable in Java has three parts: a TYPE, a NAME, and a VALUE. Once you "
            "declare the type, that variable can only ever hold that kind of value — this "
            "is called static typing, and it's one of the biggest differences from "
            "languages where a variable can hold anything.\n\n"
            "int is Java's type for whole numbers (no decimal point). The syntax is:\n"
            "type name = value;\n\n"
            "WORKED EXAMPLE:\n"
            "int score = 100;\n"
            "System.out.println(score);\n"
            "-> prints: 100\n\n"
            "COMMON MISTAKE: once a variable exists, you never write its type again to "
            "reassign it — score = 150; is fine, but int score = 150; on a later line is "
            "actually a compile error (you'd be declaring a duplicate variable)."
        ),
        "briefing": "Declare an int variable holding the value 47, and print it.",
        "hints": [
            "The pattern is: int name = value; — pick any name you like.",
            "int total = 47; then System.out.println(total); — don't forget the semicolons.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="47",
            success_line="47, declared and printed. Type, name, value — that's the whole pattern.",
            missing_hint="I don't see 47 in the output. Declare an int and print it.",
            mismatch_hint="Check your value — it should print exactly 47.",
            code_requires=["int "],
        ),
    },

    "t2m2": {
        "title": "Text Signal",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "String variables",
        "teach": (
            "Text in Java is stored in a String — note the capital S. Unlike int, double, "
            "and boolean (which are 'primitive' types built directly into the language), "
            "String is technically a class — you're creating an object. You don't need to "
            "worry about that distinction yet, just remember: String starts with a capital "
            "letter, the primitive types don't.\n\n"
            "Strings are always wrapped in double quotes, never single quotes — single "
            "quotes are for individual characters, a different type called char.\n\n"
            "WORKED EXAMPLE:\n"
            "String codename = \"Ghost\";\n"
            "System.out.println(codename);\n"
            "-> prints: Ghost\n\n"
            "COMMON MISTAKE: writing 'string' with a lowercase s. Java is case-sensitive — "
            "lowercase string isn't a real type and won't compile."
        ),
        "briefing": "Declare a String variable holding the value Viper, and print it.",
        "hints": [
            "The pattern is: String name = \"value\"; — capital S, double quotes around the text.",
            "String codename = \"Viper\"; then System.out.println(codename);",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="Viper",
            success_line="Viper, logged. Capital S, double quotes — that's a String.",
            missing_hint="I don't see Viper in the output. Declare a String and print it.",
            mismatch_hint="Check your text is exactly Viper.",
            code_requires=["String "],
        ),
    },

    "t2m3": {
        "title": "Decimals And Flags",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "double and boolean",
        "teach": (
            "Two more essential types. double holds decimal numbers (Java also has "
            "'float', but double is the standard everyday choice — more precision, used "
            "almost everywhere). boolean holds exactly one of two values: true or false — "
            "no quotes, they're keywords, not text.\n\n"
            "WORKED EXAMPLE:\n"
            "double weight = 72.5;\n"
            "boolean active = true;\n"
            "System.out.println(weight);\n"
            "System.out.println(active);\n"
            "-> prints:\n"
            "72.5\n"
            "true\n\n"
            "COMMON MISTAKE: writing a boolean value in quotes, like \"true\" — that makes "
            "it a String, not a boolean, and it will behave completely differently once "
            "you start using it in if-statements."
        ),
        "briefing": "Declare a double holding 19.99 and a boolean holding false. Print the double first, then the boolean, each on its own line.",
        "hints": [
            "double price = 19.99; and boolean inStock = false; — no quotes around true/false.",
            "Print them in order: System.out.println(price); then System.out.println(inStock);",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="19.99\nfalse",
            success_line="19.99, then false. Both types printed exactly as they should.",
            missing_hint="I need both lines — the double first, then the boolean.",
            mismatch_hint="Check both values and the order — double first, boolean second.",
            code_requires=["double ", "boolean "],
        ),
    },

    "t2m4": {
        "title": "Locked Value",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "final (constants)",
        "teach": (
            "Sometimes a value should never change once set — a maximum attempt limit, a "
            "fixed clearance level. Java's final keyword locks a variable: once assigned, "
            "trying to reassign it is a COMPILE ERROR, not something that silently fails "
            "later.\n\n"
            "Convention (expected in real code, and in your course): constants are named "
            "in ALL_CAPS with underscores, so they're instantly recognisable as 'this never "
            "changes.'\n\n"
            "WORKED EXAMPLE:\n"
            "final int MAX_ATTEMPTS = 3;\n"
            "System.out.println(MAX_ATTEMPTS);\n"
            "-> prints: 3\n\n"
            "COMMON MISTAKE: declaring something final and then trying to change it later "
            "(MAX_ATTEMPTS = 5; on a later line) — that won't compile. If a value needs to "
            "change, it isn't a constant."
        ),
        "briefing": "Declare a final int constant named CLEARANCE_LEVEL holding 9. Print \"CLEARANCE: \" followed by the value, joined with +.",
        "hints": [
            "final int CLEARANCE_LEVEL = 9; — final goes before the type.",
            "System.out.println(\"CLEARANCE: \" + CLEARANCE_LEVEL); — the + joins the text and the number together.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="CLEARANCE: 9",
            success_line="Locked at 9. That's a real constant — try to reassign it and the compiler stops you.",
            missing_hint="I don't see 'CLEARANCE: 9' in the output.",
            mismatch_hint="Check your label and that CLEARANCE_LEVEL is exactly 9.",
            code_requires=["final ", "+"],
        ),
    },

    "t2m5": {
        "title": "BOSS — Agent Profile",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "every type from Topic 2, combined — write the full structure yourself",
        "teach": (
            "No starter code this time. Recap of every type from this topic:\n\n"
            "int      whole numbers        e.g. int age = 25;\n"
            "String   text                 e.g. String name = \"Nova\";\n"
            "double   decimal numbers      e.g. double height = 5.9;\n"
            "boolean  true/false           e.g. boolean active = true;\n"
            "final    locks any of the above so it can never be reassigned\n\n"
            "Combine all five into one real program."
        ),
        "briefing": (
            "Final Topic 2 test. Write the full program yourself. Declare: a String agent "
            "= \"Viper\", an int clearance = 7, a double rating = 8.5, a boolean active = "
            "true, and a final int MAX_CLEARANCE = 10. Print, in order (each line built "
            "with + concatenation):\n"
            "AGENT: Viper\nCLEARANCE: 7\nRATING: 8.5\nACTIVE: true\nMAX CLEARANCE: 10"
        ),
        "hints": [
            "Same 5 declarations as the rest of this topic — String, int, double, boolean, and one final int. Declare all five first, then print all five.",
            "Every line follows the same shape: System.out.println(\"LABEL: \" + variable);",
        ],
        "boilerplate": "",
        "check": make_checker(
            expected_output=(
                "AGENT: Viper\nCLEARANCE: 7\nRATING: 8.5\nACTIVE: true\nMAX CLEARANCE: 10"
            ),
            success_line="Full profile, five types, zero mistakes. Topic Two complete.",
            missing_hint="Your profile doesn't match, or it didn't compile. Check the class/main structure and each field.",
            mismatch_hint="Check exact wording and order of all five lines, and that MAX_CLEARANCE is declared final.",
            code_requires=["public class Main", "public static void main", "String ", "int ", "double ", "boolean ", "final "],
            code_requires_hint="I need the full structure plus all five types: String, int, double, boolean, and final.",
        ),
    },

    # =====================================================================
    # TOPIC 3 — OPERATORS, EXPRESSIONS & STATEMENTS
    # + - * /, the integer-division trap, casting, compound assignment
    # =====================================================================

    "t3m1": {
        "title": "Signal Math",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "arithmetic operators (+ - *)",
        "teach": (
            "Java's basic arithmetic operators work exactly like you'd expect from maths "
            "class: + adds, - subtracts, * multiplies. An expression like a + b evaluates "
            "to a value, which you can print directly or store in another variable.\n\n"
            "WORKED EXAMPLE:\n"
            "int x = 6;\n"
            "int y = 3;\n"
            "System.out.println(x + y);\n"
            "System.out.println(x - y);\n"
            "System.out.println(x * y);\n"
            "-> prints:\n"
            "9\n"
            "3\n"
            "18\n\n"
            "COMMON MISTAKE: forgetting that System.out.println(x + y) prints the SUM, "
            "not \"x + y\" as text. If you actually wanted the text, you'd need quotes: "
            "System.out.println(\"x + y\")."
        ),
        "briefing": "Declare a=12 and b=7. Print a + b.",
        "hints": [
            "int a = 12; int b = 7; then System.out.println(a + b);",
            "No quotes around a + b — you want the calculated result, not literal text.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="19",
            success_line="19. Straightforward arithmetic — no surprises here.",
            missing_hint="I don't see 19 in the output.",
            mismatch_hint="Check your math — 12 + 7 should be 19.",
            code_requires=["+"],
        ),
    },

    "t3m2": {
        "title": "The Division Trap",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "integer division and % (the single most important gotcha in this topic)",
        "teach": (
            "This is the one that catches almost everyone. When you divide two int "
            "values with /, Java does INTEGER division — it throws away everything after "
            "the decimal point. No rounding, just truncation. 10 / 3 is not 3.33, it's 3. "
            "Full stop.\n\n"
            "The % operator (modulo) gives you the remainder left over from that "
            "division — genuinely useful for things like 'is this number even' (n % 2 == "
            "0) or splitting seconds into minutes and seconds.\n\n"
            "WORKED EXAMPLE:\n"
            "int a = 10;\n"
            "int b = 3;\n"
            "System.out.println(a / b);\n"
            "System.out.println(a % b);\n"
            "-> prints:\n"
            "3\n"
            "1\n\n"
            "COMMON MISTAKE: expecting int / int to give you a decimal answer because "
            "that's what a calculator would do. It never will — Java decides the result "
            "type from the operand types, and int / int is always int. The next mission "
            "shows you how to actually get the decimal."
        ),
        "briefing": "Declare a=17 and b=5. Print a / b, then print a % b, each on its own line.",
        "hints": [
            "int a = 17; int b = 5; — both int, so a / b truncates.",
            "System.out.println(a / b); then System.out.println(a % b); — division result, then remainder.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="3\n2",
            success_line="3, then 2. That's exactly what int division gives you — truncated, not rounded.",
            missing_hint="I need both lines — the division result, then the remainder.",
            mismatch_hint="Check both values — 17 / 5 truncates to 3, and 17 % 5 is 2.",
            code_requires=["/", "%"],
        ),
    },

    "t3m3": {
        "title": "Getting The Real Answer",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "casting (double)",
        "teach": (
            "So how do you actually get a decimal answer out of two int values? You cast "
            "one of them to double BEFORE the division happens, by putting the type in "
            "parentheses in front of it: (double) x. That forces Java to treat the whole "
            "expression as decimal math instead of integer math.\n\n"
            "Order matters: (double) x / y casts x first, then divides — you get real "
            "division. But x / (double) y also works fine, casting either side is enough. "
            "What does NOT work is (double) (x / y) — that computes the truncated integer "
            "result first, THEN converts it to a double, so you'd just get 3.0 instead of "
            "the real answer.\n\n"
            "WORKED EXAMPLE:\n"
            "int x = 10;\n"
            "int y = 3;\n"
            "double real = (double) x / y;\n"
            "System.out.println(real);\n"
            "-> prints: 3.3333333333333335\n\n"
            "COMMON MISTAKE: casting the whole expression instead of one operand — "
            "(double) (x / y) still truncates first, because the division inside the "
            "parentheses happens before the cast ever gets applied."
        ),
        "briefing": "Declare wholeNumber=17. Divide it by 5 with a (double) cast so you get the real decimal answer, and print the result.",
        "hints": [
            "double result = (double) wholeNumber / 5; — cast wholeNumber, not the whole expression.",
            "System.out.println(result); should print 3.4, not 3.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="3.4",
            success_line="3.4 — the real answer, not the truncated one. That's what casting buys you.",
            missing_hint="I don't see 3.4 in the output. Cast wholeNumber to double before dividing.",
            mismatch_hint="Check your cast placement — (double) should be on wholeNumber, before the division.",
            code_requires=["(double)"],
        ),
    },

    "t3m4": {
        "title": "Compound Assignment",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "+= -= *= /=",
        "teach": (
            "score = score + 5; is so common that Java gives you a shorthand: "
            "score += 5; does exactly the same thing — take the current value, add 5, "
            "store it back. Same idea for -=, *=, and /=.\n\n"
            "WORKED EXAMPLE:\n"
            "int hp = 20;\n"
            "hp -= 5;\n"
            "hp *= 2;\n"
            "System.out.println(hp);\n"
            "-> prints: 30   (20 - 5 = 15, then 15 * 2 = 30)\n\n"
            "COMMON MISTAKE: writing score =+ 5; instead of score += 5; — that's a typo "
            "that actually compiles (it means score = (+5), which just sets score to "
            "positive 5), so it fails silently instead of giving you a clear error. Watch "
            "the operator order."
        ),
        "briefing": "Declare score=10. Set score = score + 5 the long way, then use += to add 3 more. Print the final value.",
        "hints": [
            "int score = 10; score = score + 5; — the long form first.",
            "score += 3; then System.out.println(score); — should end at 18.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "check": make_checker(
            expected_output="18",
            success_line="18. Long form and shorthand, same result — += is just less typing.",
            missing_hint="I don't see 18 in the output.",
            mismatch_hint="Check your math — 10 + 5 = 15, then += 3 should give 18.",
            code_requires=["+="],
        ),
    },

    "t3m5": {
        "title": "BOSS — Full Calculation Report",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "everything from Topic 3, combined — write the full structure yourself",
        "teach": (
            "No starter code. Recap of every operator concept from this topic:\n\n"
            "+ - *      basic arithmetic, work exactly as expected\n"
            "/          int / int TRUNCATES — no rounding, just cuts off the decimal\n"
            "%          modulo — the remainder left over from division\n"
            "(double)   cast one operand before dividing, to get a real decimal answer\n"
            "+= -= *=   compound assignment — shorthand for 'update this variable'\n\n"
            "Combine all of them into one report."
        ),
        "briefing": (
            "Final Topic 3 test. Write the full program yourself. Declare base=3, "
            "bonus=4. Compute total = base * 2 + bonus * 3. Print, in order:\n"
            "BASE: 3\nBONUS: 4\nTOTAL: 18\n"
            "Then declare wholeAvg=17, compute avg as wholeAvg divided by 5 using a "
            "(double) cast, and print:\nAVERAGE: 3.4\n"
            "Finally declare counter=1, add 9 to it using +=, and print:\nCOUNTER: 10"
        ),
        "hints": [
            "Same three ideas as the rest of this topic: plain arithmetic (base*2 + bonus*3), a (double) cast for the average, and += for the counter.",
            "Five println lines total, each built with + concatenation for the labels — same pattern as every mission so far.",
        ],
        "boilerplate": "",
        "check": make_checker(
            expected_output=(
                "BASE: 3\nBONUS: 4\nTOTAL: 18\nAVERAGE: 3.4\nCOUNTER: 10"
            ),
            success_line="Full report, every operator used correctly — including the division trap avoided. Topic Three complete.",
            missing_hint="Your report doesn't match, or it didn't compile. Check each line and your math.",
            mismatch_hint="Check exact wording and order of all five lines, and that AVERAGE used a (double) cast.",
            code_requires=["public class Main", "public static void main", "(double)", "+="],
            code_requires_hint="I need the full structure, a (double) cast for the average, and += for the counter.",
        ),
    },
}


def check_mission(mission_id, code, output):
    mission = MISSIONS.get(mission_id)
    if mission is None:
        return False, "That mission doesn't exist."
    return mission["check"](code, output)
