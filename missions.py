"""
Server-side mission content and validation, for the Java rewrite of
STATIC VOID, mapped topic-for-topic onto the player's actual LJMU
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

    def check(code, output, files=None):
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
        # "close" means at least one whole expected LINE shows up among the
        # actual output lines — not raw substring containment, which would
        # call a totally wrong "136" a near-miss on an expected "36" just
        # because "36" happens to appear inside it.
        expected_lines = set(expected_output.split("\n"))
        got_lines = set(got.split("\n"))
        if expected_lines & got_lines:
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

# Scanner missions need the import; the player still writes the Scanner
# line itself (repetition is the point — it's muscle memory they'll need
# in the exam), so only the import and skeleton are pre-filled.
SCANNER_BOILERPLATE = (
    "import java.util.Scanner;\n\n"
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

    # =====================================================================
    # TOPIC 4 — METHODS, PARAMETERS AND THE STACK
    # defining/calling methods, parameters, return values, the call stack
    # =====================================================================

    "t4m1": {
        "title": "First Method",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "defining and calling a void method",
        "teach": (
            "Every mission so far has put all its code inside main(). A method lets you "
            "name a block of code and reuse it — write it once, call it as many times as "
            "you like.\n\n"
            "A method is defined OUTSIDE main() but still inside the class. void means "
            "'this method doesn't send anything back' — it just does something (like "
            "printing) and finishes. You call it by writing its name followed by "
            "parentheses: methodName();\n\n"
            "WORKED EXAMPLE:\n"
            "public class Main {\n"
            "    public static void warning() {\n"
            "        System.out.println(\"INTRUSION DETECTED\");\n"
            "    }\n\n"
            "    public static void main(String[] args) {\n"
            "        warning();\n"
            "    }\n"
            "}\n"
            "-> prints: INTRUSION DETECTED\n\n"
            "COMMON MISTAKE: defining a method INSIDE main() by accident. Methods are "
            "siblings of main() — both live directly inside the class, at the same "
            "level, never nested inside each other."
        ),
        "briefing": "Define a void method called greet() that prints SIGNAL RECEIVED. Call it from main().",
        "hints": [
            "public static void greet() { ... } — defined above or below main(), but not inside it.",
            "Inside main(), just write greet(); to call it.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    // define your method here\n\n"
            "    public static void main(String[] args) {\n"
            "        // call your method here\n"
            "    }\n"
            "}"
        ),
        "check": make_checker(
            expected_output="SIGNAL RECEIVED",
            success_line="Method defined, method called. You just wrote reusable code.",
            missing_hint="I don't see 'SIGNAL RECEIVED' in the output. Define greet() and call it from main().",
            mismatch_hint="Check your method's print statement matches exactly.",
            code_requires=["static void", "greet()"],
        ),
    },

    "t4m2": {
        "title": "Passing Data In",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "parameters",
        "teach": (
            "A method that always prints the exact same thing isn't very useful. "
            "Parameters let you pass data INTO a method when you call it — the method "
            "declares what it expects in its parentheses (a type and a name), and you "
            "supply the actual value at the call site.\n\n"
            "WORKED EXAMPLE:\n"
            "public static void announce(String message) {\n"
            "    System.out.println(\"ALERT: \" + message);\n"
            "}\n"
            "// called with:\n"
            "announce(\"BREACH\");\n"
            "-> prints: ALERT: BREACH\n\n"
            "COMMON MISTAKE: mismatching the parameter type and what you pass in — "
            "announce(\"BREACH\") works because \"BREACH\" is a String, matching the "
            "declared String message parameter. announce(42) would fail to compile."
        ),
        "briefing": "Define a method announce(String name) that prints AGENT: followed by the name. Call it with Viper.",
        "hints": [
            "public static void announce(String name) { ... } — the parameter goes in the parentheses.",
            "Inside: System.out.println(\"AGENT: \" + name); then call announce(\"Viper\"); from main().",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    // define your method here\n\n"
            "    public static void main(String[] args) {\n"
            "        // call your method here\n"
            "    }\n"
            "}"
        ),
        "check": make_checker(
            expected_output="AGENT: Viper",
            success_line="AGENT: Viper. Same method, any name you pass it — that's the point of a parameter.",
            missing_hint="I don't see 'AGENT: Viper' in the output.",
            mismatch_hint="Check your method uses the parameter correctly and you called it with Viper.",
            code_requires=["static void announce", "(String"],
        ),
    },

    "t4m3": {
        "title": "Getting Data Back",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "return values",
        "teach": (
            "void methods do something but hand nothing back. A method with a real "
            "return type (int, String, double, boolean...) can compute a value and send "
            "it back to whoever called it, using the return keyword. The type in the "
            "method's signature (before its name) has to match what you actually return.\n\n"
            "WORKED EXAMPLE:\n"
            "public static int doubleIt(int n) {\n"
            "    return n * 2;\n"
            "}\n"
            "// called with:\n"
            "int result = doubleIt(5);\n"
            "System.out.println(result);\n"
            "-> prints: 10\n\n"
            "COMMON MISTAKE: forgetting the return statement entirely. A method declared "
            "to return int MUST return an int on every possible path through it, or it "
            "won't compile — Java checks this at compile time, not runtime."
        ),
        "briefing": "Define a method square(int n) that returns n * n. Call it with 6, store the result in a variable, and print it.",
        "hints": [
            "public static int square(int n) { return n * n; } — the return type comes before the method name.",
            "int result = square(6); then System.out.println(result); — should print 36.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    // define your method here\n\n"
            "    public static void main(String[] args) {\n"
            "        // call your method here\n"
            "    }\n"
            "}"
        ),
        "check": make_checker(
            expected_output="36",
            success_line="36. The method computed it and handed it straight back.",
            missing_hint="I don't see 36 in the output.",
            mismatch_hint="Check your square() method actually returns n * n, and that you called it with 6.",
            code_requires=["static int square", "return"],
        ),
    },

    "t4m4": {
        "title": "The Call Stack",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "the call stack — why each call is independent",
        "teach": (
            "This is the 'Stack' part of this topic's name, and it's worth understanding "
            "properly, not just memorising.\n\n"
            "When main() calls add(4, 9), Java doesn't just jump into add() and lose "
            "track of main() — it PUSHES a new frame onto the call stack. That frame "
            "holds add()'s own private copies of a and b, completely separate from "
            "anything in main(). When add() finishes and hits return, its frame is "
            "POPPED off the stack, the result value travels back to wherever it was "
            "called from, and main() resumes executing exactly where it left off.\n\n"
            "This is why calling add() twice with different numbers never causes any "
            "confusion — each call gets its own fresh frame, its own private copies of "
            "a and b. The two calls never see each other's data, even though it's the "
            "'same' method both times.\n\n"
            "WORKED EXAMPLE:\n"
            "public static int add(int a, int b) {\n"
            "    return a + b;\n"
            "}\n"
            "// called twice:\n"
            "System.out.println(add(2, 3));\n"
            "System.out.println(add(10, 20));\n"
            "-> prints:\n"
            "5\n"
            "30\n"
            "Two completely independent stack frames — the second call's a and b have "
            "no idea the first call ever happened.\n\n"
            "COMMON MISTAKE: assuming a method 'remembers' values from a previous call. "
            "It doesn't — every call starts with a brand new frame. (This is also "
            "exactly why deep recursion can crash with a StackOverflowError: too many "
            "frames pushed without any being popped yet.)"
        ),
        "briefing": "Define add(int a, int b) returning a + b. Call add(4, 9), store and print the result. Then call add() again passing that result and 100, store and print that result too.",
        "hints": [
            "public static int add(int a, int b) { return a + b; }",
            "int sum = add(4, 9); System.out.println(sum); int sum2 = add(sum, 100); System.out.println(sum2); — two independent calls.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    // define your method here\n\n"
            "    public static void main(String[] args) {\n"
            "        // call your method here\n"
            "    }\n"
            "}"
        ),
        "check": make_checker(
            expected_output="13\n113",
            success_line="13, then 113. Two calls, two independent stack frames, correct both times.",
            missing_hint="I need both lines — the first sum, then the second.",
            mismatch_hint="Check both calls to add() and that the second call uses the first result plus 100.",
            code_requires=["static int add", "return"],
        ),
    },

    "t4m5": {
        "title": "BOSS — Mission Report Generator",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "everything from Topic 4, combined — write the full structure yourself",
        "teach": (
            "No starter code. Recap of every method shape from this topic:\n\n"
            "void, no params      public static void header() { ... }\n"
            "params, returns      public static int combine(int a, int b) { return ...; }\n"
            "params, returns text public static String label(String name, int value) { return ...; }\n\n"
            "Every call still gets its own private stack frame, no matter which shape "
            "the method is."
        ),
        "briefing": (
            "Final Topic 4 test. Write the full program yourself, with three methods:\n"
            "- header(): void, no parameters, prints >>> MISSION REPORT <<<\n"
            "- combine(int a, int b): returns a + b\n"
            "- label(String name, int value): returns name + \": \" + value\n\n"
            "In main(): call header(). Call combine(15, 27), store as total, print "
            "label(\"TOTAL\", total). Call combine(total, total), store as doubled, print "
            "label(\"DOUBLED\", doubled).\n\n"
            "Expected output:\n"
            ">>> MISSION REPORT <<<\nTOTAL: 42\nDOUBLED: 84"
        ),
        "hints": [
            "Three separate methods, all defined at the class level (siblings of main(), not nested in it) — one void, two with return types.",
            "combine(15, 27) is 42. label(\"TOTAL\", 42) builds the string for you — you just print what it returns.",
        ],
        "boilerplate": "",
        "check": make_checker(
            expected_output=(
                ">>> MISSION REPORT <<<\nTOTAL: 42\nDOUBLED: 84"
            ),
            success_line="Three methods, working together, correct stack behavior throughout. Topic Four complete.",
            missing_hint="Your report doesn't match, or it didn't compile. Check all three method definitions and the calls in main().",
            mismatch_hint="Check exact wording, and that combine() and label() are called with the right values in order.",
            code_requires=["public class Main", "public static void main", "static void header", "static int combine", "static String label"],
            code_requires_hint="I need all three methods defined — header(), combine(), and label() — plus main() calling them.",
        ),
    },

    # =====================================================================
    # TOPIC 5 — BASIC I/O & FILE I/O
    # Batch 1 of 5: console input with Scanner.
    # Later batches in this topic: file writing, file reading, and
    # combined programs.
    # =====================================================================

    "t5m1": {
        "title": "Listening Post",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "Scanner + nextInt()",
        "teach": (
            "Until now your programs have been sealed — every value hardcoded. Scanner "
            "opens a channel so the program can read what someone types in.\n\n"
            "Two steps, every time:\n"
            "1. import java.util.Scanner;  at the very top of the file (above the class)\n"
            "2. Scanner sc = new Scanner(System.in);  inside your method\n\n"
            "System.in is the keyboard/console input stream. Once you have a Scanner, "
            "sc.nextInt() reads the next whole number typed in.\n\n"
            "WORKED EXAMPLE:\n"
            "Scanner sc = new Scanner(System.in);\n"
            "int signalStrength = sc.nextInt();\n"
            "System.out.println(\"SIGNAL: \" + signalStrength);\n"
            "// if 42 is typed in -> prints: SIGNAL: 42\n\n"
            "COMMON MISTAKE: forgetting the import line. Scanner isn't built into the "
            "language the way int and String are — it lives in java.util, and without "
            "the import Java will tell you it can't find the symbol 'Scanner'.\n\n"
            "NOTE ON TESTING: there's no live keyboard here, so each mission feeds your "
            "program fixed test values. They're listed under the objective so you know "
            "exactly what your Scanner will receive."
        ),
        "briefing": "Open a channel and read the incoming clearance level as an int. Print CLEARANCE: followed by the value.",
        "inputs": ["7"],
        "hints": [
            "Scanner sc = new Scanner(System.in); then int clearance = sc.nextInt();",
            "Print with concatenation: System.out.println(\"CLEARANCE: \" + clearance);",
        ],
        "boilerplate": SCANNER_BOILERPLATE,
        "check": make_checker(
            expected_output="CLEARANCE: 7",
            success_line="Channel open, value received. Your program can hear the outside world now.",
            missing_hint="I don't see 'CLEARANCE: 7' in the output. Read the value with sc.nextInt().",
            mismatch_hint="Check your label and spacing — 'CLEARANCE: ' then the number.",
            code_requires=["new Scanner", "nextInt()"],
            code_requires_hint="I need a Scanner created from System.in and a call to nextInt().",
        ),
    },

    "t5m2": {
        "title": "Precision Reading",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "Scanner + nextDouble()",
        "teach": (
            "Scanner has a matching read method for each type. nextInt() reads whole "
            "numbers; nextDouble() reads decimals. The method you call has to match the "
            "type of the variable you're storing it in, and the kind of data actually "
            "coming in.\n\n"
            "WORKED EXAMPLE:\n"
            "Scanner sc = new Scanner(System.in);\n"
            "double temperature = sc.nextDouble();\n"
            "System.out.println(\"TEMP: \" + temperature);\n"
            "// if 36.6 is typed in -> prints: TEMP: 36.6\n\n"
            "COMMON MISTAKE: calling nextInt() when the incoming value has a decimal "
            "point. It won't quietly round — it throws InputMismatchException and your "
            "program crashes. Match the method to the data."
        ),
        "briefing": "Read an incoming performance rating as a double. Print RATING: followed by the value.",
        "inputs": ["8.5"],
        "hints": [
            "double rating = sc.nextDouble(); — nextDouble, not nextInt.",
            "System.out.println(\"RATING: \" + rating);",
        ],
        "boilerplate": SCANNER_BOILERPLATE,
        "check": make_checker(
            expected_output="RATING: 8.5",
            success_line="8.5, read precisely. Right method for the right type.",
            missing_hint="I don't see 'RATING: 8.5' in the output. Use sc.nextDouble().",
            mismatch_hint="Check your label and that you read it as a double, not an int.",
            code_requires=["new Scanner", "nextDouble()"],
            code_requires_hint="I need a Scanner and a call to nextDouble().",
        ),
    },

    "t5m3": {
        "title": "Full Transmission",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "Scanner + nextLine()",
        "teach": (
            "nextLine() reads an entire line of text — everything up to the next line "
            "break, spaces included. That's what you want for names, messages, addresses: "
            "anything that isn't a single word.\n\n"
            "WORKED EXAMPLE:\n"
            "Scanner sc = new Scanner(System.in);\n"
            "String report = sc.nextLine();\n"
            "System.out.println(\"REPORT: \" + report);\n"
            "// if 'all quiet on the east wing' is typed in\n"
            "// -> prints: REPORT: all quiet on the east wing\n\n"
            "COMMON MISTAKE: assuming nextLine() gives you one word. It gives you the "
            "whole line — that's the entire point of it, and the next mission shows you "
            "the method that does read just one word."
        ),
        "briefing": "An intercepted message is coming through as a full line of text. Read it with nextLine() and print MESSAGE: followed by it.",
        "inputs": ["breach detected at tower"],
        "hints": [
            "String message = sc.nextLine(); — reads the whole line, spaces and all.",
            "System.out.println(\"MESSAGE: \" + message);",
        ],
        "boilerplate": SCANNER_BOILERPLATE,
        "check": make_checker(
            expected_output="MESSAGE: breach detected at tower",
            success_line="Whole line captured, spaces intact. That's what nextLine() is for.",
            missing_hint="I don't see the full message in the output. Use sc.nextLine() to get the whole line.",
            mismatch_hint="Check you captured the whole line — if you only got one word, you used the wrong method.",
            code_requires=["new Scanner", "nextLine()"],
            code_requires_hint="I need a Scanner and a call to nextLine().",
        ),
    },

    "t5m4": {
        "title": "One Word Only",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "next() vs nextLine()",
        "teach": (
            "next() reads a single TOKEN — one word, stopping at the first space. "
            "nextLine() reads the whole line. Same Scanner, very different results, and "
            "mixing them up is a classic source of confusion.\n\n"
            "WORKED EXAMPLE (input: 'alpha bravo charlie'):\n"
            "String one = sc.next();\n"
            "// one is \"alpha\"  — stops at the first space\n"
            "\n"
            "versus:\n"
            "String all = sc.nextLine();\n"
            "// all is \"alpha bravo charlie\"  — the entire line\n\n"
            "COMMON MISTAKE: using next() for someone's full name and wondering where "
            "the surname went. next() stopped at the space and left the rest sitting in "
            "the buffer, waiting for the next read."
        ),
        "briefing": "Three codewords arrive on one line, but you only need the first. Use next() to grab just the first word and print FIRST WORD: followed by it.",
        "inputs": ["Ghost Protocol Alpha"],
        "hints": [
            "String first = sc.next(); — next(), not nextLine().",
            "System.out.println(\"FIRST WORD: \" + first); — should print only Ghost.",
        ],
        "boilerplate": SCANNER_BOILERPLATE,
        "check": make_checker(
            expected_output="FIRST WORD: Ghost",
            success_line="Just the first token. next() stopped dead at the space, exactly as designed.",
            missing_hint="I don't see 'FIRST WORD: Ghost' in the output. Use sc.next() for a single word.",
            mismatch_hint="If you got all three words, you used nextLine() — next() reads only up to the first space.",
            code_requires=["new Scanner", "next()"],
            code_requires_hint="I need a Scanner and a call to next().",
        ),
    },

    "t5m5": {
        "title": "The Buffer Trap",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "the nextInt()-then-nextLine() bug (and the fix)",
        "teach": (
            "This is the most notorious Scanner bug in Java, and it will bite you in "
            "coursework if you don't know it. Read this one carefully.\n\n"
            "When you call nextInt(), Scanner reads the digits and STOPS — it leaves the "
            "invisible newline character (from pressing Enter) sitting in the buffer. So "
            "when you call nextLine() next, it reads everything up to that leftover "
            "newline... which is nothing. You get an empty string, instantly, without "
            "the user ever getting a chance to type.\n\n"
            "BROKEN (input: 7 then 'Ghost Protocol'):\n"
            "int clearance = sc.nextInt();\n"
            "String codename = sc.nextLine();   // <- gets \"\" , not \"Ghost Protocol\"\n"
            "-> prints: CODENAME:            (empty!)\n\n"
            "THE FIX — one throwaway nextLine() to consume the leftover newline:\n"
            "int clearance = sc.nextInt();\n"
            "sc.nextLine();                     // <- eats the leftover newline\n"
            "String codename = sc.nextLine();   // <- now gets \"Ghost Protocol\"\n\n"
            "COMMON MISTAKE: assuming your code is fine because it compiles. This bug "
            "never causes a compile error or a crash — it just silently hands you an "
            "empty string, which is exactly why it's so hard to spot."
        ),
        "briefing": (
            "Read an int clearance, then a full codename line — and avoid the buffer "
            "trap. Print:\nCLEARANCE: 7\nCODENAME: Ghost Protocol"
        ),
        "inputs": ["7", "Ghost Protocol"],
        "hints": [
            "Read the int first, then add a throwaway sc.nextLine(); before reading the real line.",
            "int clearance = sc.nextInt(); then sc.nextLine(); then String codename = sc.nextLine();",
        ],
        "boilerplate": SCANNER_BOILERPLATE,
        "check": make_checker(
            expected_output="CLEARANCE: 7\nCODENAME: Ghost Protocol",
            success_line="Trap avoided. You'll hit this bug again in real coursework — now you'll recognise it in seconds.",
            missing_hint="I need both lines — CLEARANCE then CODENAME.",
            mismatch_hint="If CODENAME came out empty, you hit the buffer trap — add a throwaway sc.nextLine() after nextInt().",
            code_requires=["nextInt()", "nextLine()"],
            code_requires_hint="This one needs both nextInt() and nextLine().",
        ),
    },
}


def check_mission(mission_id, code, output, files=None):
    """files is the {filename: contents} dict of whatever the player's
    code wrote to the sandboxed run directory (see java_sandbox), so
    File I/O missions can verify real written content instead of
    trusting a printed success message. Missions that don't involve
    files simply ignore it."""
    mission = MISSIONS.get(mission_id)
    if mission is None:
        return False, "That mission doesn't exist."
    return mission["check"](code, output, files or {})
