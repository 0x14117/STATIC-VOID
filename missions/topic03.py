"""
Topic 3 — Operators, Expressions & Statements.

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
        "solution": '''public class Main {
    public static void main(String[] args) {
        int a = 12;
        int b = 7;
        System.out.println(a + b);
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println(19);
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        int a = 17;
        int b = 5;
        System.out.println(a / b);
        System.out.println(a % b);
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        int a = 17;
        int b = 5;
        double result = (double) a / b;
        System.out.println(result);
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        int wholeNumber = 17;
        double result = (double) wholeNumber / 5;
        System.out.println(result);
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        int wholeNumber = 17;
        System.out.println(wholeNumber / 5);
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        int score = 10;
        score = score + 5;
        score += 3;
        System.out.println(score);
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        int score = 10;
        System.out.println(score);
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        int base = 3;
        int bonus = 4;
        int total = base * 2 + bonus * 3;
        System.out.println("BASE: " + base);
        System.out.println("BONUS: " + bonus);
        System.out.println("TOTAL: " + total);
        int wholeAvg = 17;
        double avg = (double) wholeAvg / 5;
        System.out.println("AVERAGE: " + avg);
        int counter = 1;
        counter += 9;
        System.out.println("COUNTER: " + counter);
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
