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

    # ---------------------------------------------------------------------
    # Batch 2 — precedence, brackets, and the increment operators.
    # ---------------------------------------------------------------------

    "t3m6": {
        "title": "Order Of Operations",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "operator precedence",
        "teach": (
            "Java doesn't work strictly left to right. It follows PRECEDENCE — the same "
            "rules you learned in maths. Multiplication and division happen before "
            "addition and subtraction.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(2 + 3 * 4);\n"
            "-> prints: 14\n\n"
            "Not 20. Java does 3 * 4 first (12), then adds 2. If you read it left to "
            "right you'd get 5 * 4 = 20, and you'd be wrong.\n\n"
            "The order, highest first:\n"
            "1. ( )        brackets\n"
            "2. * / %      multiply, divide, remainder\n"
            "3. + -        add, subtract\n\n"
            "Operators on the same level go left to right: 20 - 5 - 3 is 12, not 18.\n\n"
            "COMMON MISTAKE: averaging. 10 + 20 + 30 / 3 gives 40, because only the 30 "
            "gets divided. The average you wanted needs brackets: (10 + 20 + 30) / 3."
        ),
        "briefing": "Two expressions, no brackets anywhere, printed on two lines. First print the result of 2 + 3 * 4. Then print the result of 20 - 5 - 3. Work out what Java will do before you run it.",
        "hints": [
            "Multiplication binds tighter than addition, so the second half of the first expression happens first.",
            "The second one is all the same precedence level, so it runs strictly left to right: (20 - 5) then - 3.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println(2 + 3 * 4);
        System.out.println(20 - 5 - 3);
    }
}''',
        "wrong": [
            # bracketed to force left-to-right — gives 20, the intuitive wrong answer
            '''public class Main {
    public static void main(String[] args) {
        System.out.println((2 + 3) * 4);
        System.out.println(20 - 5 - 3);
    }
}''',
        ],
        "check": make_checker(
            expected_output="14\n12",
            success_line="14 and 12. Precedence, then left to right for ties — no brackets needed to predict it.",
            missing_hint="I need two lines: the value of 2 + 3 * 4, then of 20 - 5 - 3.",
            mismatch_hint="If the first line says 20, something forced the addition to happen first — * binds tighter than +.",
            code_requires=["2 + 3 * 4", "20 - 5 - 3"],
            code_requires_hint="Write the expressions exactly as given — 2 + 3 * 4 and 20 - 5 - 3 — and let Java resolve them.",
        ),
    },

    "t3m7": {
        "title": "Forcing The Order",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "brackets override precedence",
        "teach": (
            "Brackets beat everything. Whatever's inside them is worked out first, so "
            "they're how you say 'no, I meant this order'.\n\n"
            "WORKED EXAMPLE — the classic average:\n"
            "System.out.println(10 + 20 + 30 / 3);\n"
            "-> prints: 40      (only the 30 was divided: 10 + 20 + 10)\n\n"
            "System.out.println((10 + 20 + 30) / 3);\n"
            "-> prints: 20      (the sum, then divided — the actual average)\n\n"
            "Same numbers, same operators, completely different answer.\n\n"
            "Brackets nest, innermost first: ((4 + 6) * 2) - 5 is 15.\n\n"
            "Use brackets even where precedence would have given you the right answer "
            "anyway. a + (b * c) costs nothing and means the next reader doesn't have to "
            "remember the rules.\n\n"
            "COMMON MISTAKE: unbalanced brackets. Every ( needs a ). Count them when you "
            "see 'illegal start of expression' — it's almost always this."
        ),
        "briefing": "Work out a genuine average. Using brackets, print the average of 12, 18 and 27 — that's the three numbers added together, then divided by 3. It must be one expression, not a pre-calculated answer.",
        "hints": [
            "The addition has to finish before the division starts, so it needs to be inside brackets.",
            "System.out.println((12 + 18 + 27) / 3);",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println((12 + 18 + 27) / 3);
    }
}''',
        "wrong": [
            # no brackets — only 27 gets divided
            '''public class Main {
    public static void main(String[] args) {
        System.out.println(12 + 18 + 27 / 3);
    }
}''',
        ],
        "check": make_checker(
            expected_output="19",
            success_line="19 — the real average. Brackets are how you say what you meant.",
            missing_hint="I need the average of 12, 18 and 27 printed as a single calculation.",
            mismatch_hint="If you got 39, only the 27 was divided — the addition needs brackets around it.",
            code_requires=["12", "18", "27", "/ 3", "("],
            code_requires_hint="Do it as one bracketed expression: (12 + 18 + 27) / 3",
        ),
    },

    "t3m8": {
        "title": "Step By One",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "++ and -- ",
        "teach": (
            "Adding one to a variable is so common Java has a dedicated operator for it.\n\n"
            "count++;      exactly the same as   count = count + 1;\n"
            "count--;      exactly the same as   count = count - 1;\n\n"
            "WORKED EXAMPLE:\n"
            "int lives = 3;\n"
            "lives--;\n"
            "System.out.println(lives);\n"
            "-> prints: 2\n\n"
            "These only move by one — there's no count+++ for two. For anything else "
            "you want the compound operators you've already met: count += 5;\n\n"
            "You'll see ++ constantly once you reach loops in Topic 7; it's how nearly "
            "every counter in every language advances.\n\n"
            "COMMON MISTAKE: writing count++ but forgetting it CHANGES count. It isn't a "
            "question about count, it's an instruction to modify it. If you only want to "
            "see 'one more than count' without changing anything, that's count + 1."
        ),
        "briefing": "Track a supply count as it moves. Declare int crates as 10, increase it by one with ++, then decrease it by one twice with --. Print the value after each change: 11, then 10, then 9.",
        "hints": [
            "crates++; on its own line, then System.out.println(crates); to see the result.",
            "Then crates--; print, then crates--; print again. Six statements in total.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int crates = 10;
        crates++;
        System.out.println(crates);
        crates--;
        System.out.println(crates);
        crates--;
        System.out.println(crates);
    }
}''',
        "wrong": [
            # printed the numbers without ever changing a variable
            '''public class Main {
    public static void main(String[] args) {
        System.out.println(11);
        System.out.println(10);
        System.out.println(9);
    }
}''',
        ],
        "check": make_checker(
            expected_output="11\n10\n9",
            success_line="Up one, down two. ++ and -- modify the variable — they're instructions, not questions.",
            missing_hint="I need three lines: 11, 10, 9 — each printed after a change to the same variable.",
            mismatch_hint="Check you print after EACH change, and that you start from 10.",
            code_requires=["crates++", "crates--"],
            code_requires_hint="Use the ++ and -- operators on a variable called crates rather than printing the numbers directly.",
        ),
    },

    "t3m9": {
        "title": "Before Or After",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "pre-increment vs post-increment",
        "teach": (
            "++ can go before or after the variable, and it matters — but only when you "
            "USE the value in the same statement.\n\n"
            "count++   POST: hand back the current value, THEN add one\n"
            "++count   PRE:  add one first, THEN hand back the new value\n\n"
            "WORKED EXAMPLE:\n"
            "int a = 5;\n"
            "System.out.println(a++);    // prints 5   <- the old value\n"
            "System.out.println(a);      // prints 6   <- but it did increment\n\n"
            "int b = 5;\n"
            "System.out.println(++b);    // prints 6   <- incremented first\n"
            "System.out.println(b);      // prints 6\n\n"
            "Both variables end at 6. The difference is only in what the PRINT saw.\n\n"
            "On a line of its own — count++; — there's no difference whatsoever, because "
            "nothing is using the returned value. That's the form you'll write 95% of "
            "the time.\n\n"
            "COMMON MISTAKE: writing clever code like arr[i++] = x + arr[++i]; and then "
            "being unable to debug it. Knowing the rule is exam material; relying on it "
            "for real work is how you write code nobody can read."
        ),
        "briefing": "Demonstrate the difference. Declare int a as 5 and print a++ (which shows the old value), then print a on the next line. Then declare int b as 5 and print ++b, then print b. Expected output: 5, 6, 6, 6.",
        "hints": [
            "First pair: System.out.println(a++); then System.out.println(a);",
            "Second pair uses the pre form — the ++ goes BEFORE the variable name: ++b",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int a = 5;
        System.out.println(a++);
        System.out.println(a);

        int b = 5;
        System.out.println(++b);
        System.out.println(b);
    }
}''',
        "wrong": [
            # used post-increment in both places — prints 5,6,5,6
            '''public class Main {
    public static void main(String[] args) {
        int a = 5;
        System.out.println(a++);
        System.out.println(a);

        int b = 5;
        System.out.println(b++);
        System.out.println(b);
    }
}''',
        ],
        "check": make_checker(
            expected_output="5\n6\n6\n6",
            success_line="5, 6, 6, 6. Both variables ended at 6 — the only difference was what the print saw.",
            missing_hint="I need four lines: 5, 6, 6, 6.",
            mismatch_hint="If line three says 5, you used b++ where ++b was wanted — the pre form increments before handing the value over.",
            code_requires=["a++", "++b"],
            code_requires_hint="I need both forms: a++ (post) for the first pair and ++b (pre) for the second.",
        ),
    },

    "t3m10": {
        "title": "BOSS — Counter Readout",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "precedence, brackets and increments together — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "( )  then  * / %  then  + -      ties resolve left to right\n"
            "brackets force any order you want, and cost nothing to add\n"
            "x++ / x--     change x by one\n"
            "x++ hands back the OLD value; ++x hands back the new one\n\n"
            "Alone on a line, x++ and ++x are identical. The difference only shows when "
            "something is using the value."
        ),
        "briefing": (
            "Write the counter readout from scratch.\n\n"
            "Declare int reading as 8. Print the result of the expression\n"
            "reading + 2 * 5 (no brackets — let precedence decide).\n"
            "Then print (reading + 2) * 5 (bracketed).\n"
            "Then increment reading with ++ and print it.\n\n"
            "Expected output:\n"
            "18\n"
            "50\n"
            "9"
        ),
        "hints": [
            "The first two lines use the SAME numbers — only the brackets differ, and they change the answer completely.",
            "The third line is reading++; on its own, then print reading.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void main(String[] args) {
        int reading = 8;
        System.out.println(reading + 2 * 5);
        System.out.println((reading + 2) * 5);
        reading++;
        System.out.println(reading);
    }
}''',
        "wrong": [
            # brackets on the wrong line — both come out the same
            '''public class Main {
    public static void main(String[] args) {
        int reading = 8;
        System.out.println((reading + 2) * 5);
        System.out.println((reading + 2) * 5);
        reading++;
        System.out.println(reading);
    }
}''',
        ],
        "check": make_checker(
            expected_output="18\n50\n9",
            success_line="Same numbers, three different answers, all predicted. Block complete.",
            missing_hint="I need three lines: 18, 50, 9.",
            mismatch_hint="Check line one has NO brackets (so * wins) and line two does (so + wins).",
            code_requires=["public class Main", "reading + 2 * 5", "(reading + 2) * 5", "reading++"],
            code_requires_hint="I need both forms of the expression — unbracketed then bracketed — plus a reading++ at the end.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 3 — comparison and logical operators. Every result here is a
    # boolean printed directly; branching on them arrives in Topic 6.
    # ---------------------------------------------------------------------

    "t3m11": {
        "title": "Greater Or Less",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "the relational operators < > <= >=",
        "teach": (
            "A relational operator compares two values and produces a boolean — true or "
            "false. There are four:\n\n"
            "a > b     greater than\n"
            "a < b     less than\n"
            "a >= b    greater than or equal to\n"
            "a <= b    less than or equal to\n\n"
            "WORKED EXAMPLE:\n"
            "int speed = 60;\n"
            "System.out.println(speed > 50);     // true\n"
            "System.out.println(speed >= 60);    // true  — the 'or equal' half counts\n"
            "System.out.println(speed < 60);     // false\n\n"
            "The result is a real value you can print or store, exactly like a number.\n\n"
            "Note >= is written in that order, with no space between the characters. "
            "=> is not a Java operator and won't compile.\n\n"
            "COMMON MISTAKE: assuming < and <= are interchangeable at the boundary. When "
            "speed is exactly 60, speed < 60 is false but speed <= 60 is true. Almost "
            "every off-by-one bug you will ever write lives in that one character."
        ),
        "briefing": "Test the speed limit three ways. Declare int speed as 60, then print the results of speed > 50, then speed >= 60, then speed < 60 — three lines of true/false.",
        "hints": [
            "You can print a comparison directly: System.out.println(speed > 50);",
            "Watch the third one — speed is exactly 60, so a strict < is false.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int speed = 60;
        System.out.println(speed > 50);
        System.out.println(speed >= 60);
        System.out.println(speed < 60);
    }
}''',
        "wrong": [
            # used > instead of >= on the boundary case
            '''public class Main {
    public static void main(String[] args) {
        int speed = 60;
        System.out.println(speed > 50);
        System.out.println(speed > 60);
        System.out.println(speed < 60);
    }
}''',
        ],
        "check": make_checker(
            expected_output="true\ntrue\nfalse",
            success_line="true, true, false. The boundary is where >= and > part company — remember that one.",
            missing_hint="I need three lines of true/false from the three comparisons.",
            mismatch_hint="Check the second one uses >= (true at exactly 60) and the third uses < (false at exactly 60).",
            code_requires=["speed > 50", "speed >= 60", "speed < 60"],
            code_requires_hint="I need all three comparisons written out: speed > 50, speed >= 60, speed < 60.",
        ),
    },

    "t3m12": {
        "title": "Equal And Not",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "== and != , and why it isn't =",
        "teach": (
            "To ask whether two values are equal you use TWO equals signs.\n\n"
            "a == b    are they equal?      (a question)\n"
            "a != b    are they different?  (a question)\n"
            "a = b     put b into a         (an instruction)\n\n"
            "WORKED EXAMPLE:\n"
            "int code = 7;\n"
            "System.out.println(code == 7);    // true\n"
            "System.out.println(code != 7);    // false\n\n"
            "!= reads as 'not equal to' — the ! means 'not' throughout Java.\n\n"
            "Mixing up = and == is the single most common beginner error in every "
            "C-family language. Java protects you better than most: if (x = 5) won't "
            "compile, because 5 isn't a boolean. But with boolean variables it compiles "
            "happily and does the wrong thing silently, which is much worse.\n\n"
            "COMMON MISTAKE: using == on Strings. For text it compiles, and it sometimes "
            "even appears to work, but it's asking a different question than you think. "
            "Topic 6 covers that properly — for now, == is for numbers and chars."
        ),
        "briefing": "Verify an access code. Declare int code as 7, then print whether it equals 7, and whether it is different from 9 — two lines, both true.",
        "hints": [
            "Two equals signs for the question: code == 7",
            "The second line asks about difference: code != 9",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int code = 7;
        System.out.println(code == 7);
        System.out.println(code != 9);
    }
}''',
        "check": make_checker(
            expected_output="true\ntrue",
            success_line="Both true. One equals assigns, two equals asks — that distinction never stops mattering.",
            missing_hint="I need two lines, both true: one == comparison and one != comparison.",
            mismatch_hint="Check you used == for the first and != for the second, with the values 7 and 9.",
            code_requires=["code == 7", "code != 9"],
            code_requires_hint="I need both comparisons written out: code == 7 and code != 9.",
        ),
    },

    "t3m13": {
        "title": "Both Must Hold",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "the && (AND) operator",
        "teach": (
            "&& combines two booleans and is true only when BOTH sides are true. It's "
            "written as two ampersands.\n\n"
            "true  && true    ->  true\n"
            "true  && false   ->  false\n"
            "false && true    ->  false\n"
            "false && false   ->  false\n\n"
            "WORKED EXAMPLE:\n"
            "int age = 25;\n"
            "boolean hasTicket = true;\n"
            "System.out.println(age >= 18 && hasTicket);\n"
            "-> prints: true       (25 is over 18, AND they have a ticket)\n\n"
            "Each side has to be a complete comparison. age >= 18 && <= 65 is not valid "
            "Java — write age >= 18 && age <= 65.\n\n"
            "&& is SHORT-CIRCUITING: if the left side is false, the right side is never "
            "evaluated at all, because the answer is already settled. That's not just an "
            "optimisation — it's how you safely write a check whose second half would "
            "crash if the first half weren't true.\n\n"
            "COMMON MISTAKE: writing a single & . It compiles and usually gives the same "
            "answer, but it evaluates both sides always, losing the protection above. "
            "Use && ."
        ),
        "briefing": "Check a two-part entry rule. Declare int age as 25 and boolean hasTicket as true, then print whether age is at least 18 AND hasTicket is true. Then print the result of age >= 30 && hasTicket. Two lines: true, then false.",
        "hints": [
            "System.out.println(age >= 18 && hasTicket); — hasTicket is already a boolean, so it needs no comparison of its own.",
            "The second line fails its left half (25 is not >= 30), so the whole thing is false.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int age = 25;
        boolean hasTicket = true;
        System.out.println(age >= 18 && hasTicket);
        System.out.println(age >= 30 && hasTicket);
    }
}''',
        "wrong": [
            # used || instead of && — second line wrongly comes out true
            '''public class Main {
    public static void main(String[] args) {
        int age = 25;
        boolean hasTicket = true;
        System.out.println(age >= 18 || hasTicket);
        System.out.println(age >= 30 || hasTicket);
    }
}''',
        ],
        "check": make_checker(
            expected_output="true\nfalse",
            success_line="true then false. && needs both halves — one failure sinks the whole expression.",
            missing_hint="I need two lines: true then false, from two && expressions.",
            mismatch_hint="If both say true, you're using || (OR) somewhere — && needs BOTH sides true.",
            code_requires=["&&"],
            code_requires_hint="This one needs the && operator joining two conditions.",
        ),
    },

    "t3m14": {
        "title": "Either Will Do",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "the || (OR) operator",
        "teach": (
            "|| is true when AT LEAST ONE side is true. It's written as two pipe "
            "characters (Shift + backslash on most keyboards).\n\n"
            "true  || true    ->  true\n"
            "true  || false   ->  true\n"
            "false || true    ->  true\n"
            "false || false   ->  false\n\n"
            "WORKED EXAMPLE:\n"
            "boolean isStaff = false;\n"
            "boolean isVip = true;\n"
            "System.out.println(isStaff || isVip);\n"
            "-> prints: true      (one of them is enough)\n\n"
            "Like &&, it short-circuits — but the other way round. As soon as one side "
            "is true, the rest is skipped, because nothing could change the answer.\n\n"
            "Read them as English and they're hard to get wrong: && is 'and', meaning "
            "everything must hold; || is 'or', meaning anything will do.\n\n"
            "COMMON MISTAKE: reaching for || when the rule really is AND. 'Members and "
            "staff get in' usually means a person who is EITHER — which is ||. Translate "
            "the rule, not the sentence."
        ),
        "briefing": "Check a two-route access rule. Declare boolean isStaff as false and boolean isVip as true. Print whether either is true, then print isStaff || false. Two lines: true, then false.",
        "hints": [
            "System.out.println(isStaff || isVip); — one true side is enough to make it true.",
            "The second line has nothing true on either side, so it comes out false.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        boolean isStaff = false;
        boolean isVip = true;
        System.out.println(isStaff || isVip);
        System.out.println(isStaff || false);
    }
}''',
        "check": make_checker(
            expected_output="true\nfalse",
            success_line="true then false. || only fails when every side fails.",
            missing_hint="I need two lines: true then false, from two || expressions.",
            mismatch_hint="Check the first line ORs the two variables together, and the second has no true side at all.",
            code_requires=["||"],
            code_requires_hint="This one needs the || operator — two pipe characters.",
        ),
    },

    "t3m15": {
        "title": "BOSS — Access Logic",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "relational and logical operators combined — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "<  >  <=  >=     compare, produce a boolean\n"
            "==  !=           equal / not equal (numbers and chars for now)\n"
            "&&               both sides must be true\n"
            "||               at least one side must be true\n\n"
            "Each side of && and || must be a complete condition of its own. A boolean "
            "variable is already complete — hasKey needs no == true after it."
        ),
        "briefing": (
            "Write the access logic from scratch.\n\n"
            "Declare int clearance as 5, boolean hasKeycard as true, and boolean "
            "isBlacklisted as false.\n\n"
            "Print, in order:\n"
            "- whether clearance is at least 3 AND they have a keycard\n"
            "- whether clearance is above 8 OR they have a keycard\n"
            "- whether clearance is exactly 5\n"
            "- whether isBlacklisted is different from true\n\n"
            "All four lines should print true."
        ),
        "hints": [
            "Line by line: clearance >= 3 && hasKeycard, then clearance > 8 || hasKeycard.",
            "The last two use == and != : clearance == 5, then isBlacklisted != true.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void main(String[] args) {
        int clearance = 5;
        boolean hasKeycard = true;
        boolean isBlacklisted = false;

        System.out.println(clearance >= 3 && hasKeycard);
        System.out.println(clearance > 8 || hasKeycard);
        System.out.println(clearance == 5);
        System.out.println(isBlacklisted != true);
    }
}''',
        "wrong": [
            # just printed four literal trues
            '''public class Main {
    public static void main(String[] args) {
        System.out.println(true);
        System.out.println(true);
        System.out.println(true);
        System.out.println(true);
    }
}''',
        ],
        "check": make_checker(
            expected_output="true\ntrue\ntrue\ntrue",
            success_line="Four conditions, four operators, all correct. Block complete.",
            missing_hint="I need four lines, all true, each from a real comparison.",
            mismatch_hint="One of the four is coming out false. Check each operator against what the briefing asked for.",
            code_requires=["public class Main", "&&", "||", "==", "!="],
            code_requires_hint="I need all four operators used: &&, ||, ==, and != — on the declared variables.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 4 — negation, combined logic, the concatenation trap, and the
    # two things % is actually for.
    # ---------------------------------------------------------------------

    "t3m16": {
        "title": "The Opposite",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "the ! (NOT) operator",
        "teach": (
            "! flips a boolean. True becomes false, false becomes true. It goes in FRONT "
            "of what it's negating.\n\n"
            "!true    ->  false\n"
            "!false   ->  true\n\n"
            "WORKED EXAMPLE:\n"
            "boolean isLocked = true;\n"
            "System.out.println(!isLocked);      // false\n"
            "System.out.println(!(3 > 5));       // true  — 3 > 5 is false, flipped\n\n"
            "Note the brackets in the second one. ! binds very tightly, so !3 > 5 would "
            "try to negate the number 3 and fail to compile. When negating a comparison, "
            "bracket it.\n\n"
            "The most readable use is on a well-named boolean: !isEmpty, !hasExpired — "
            "those read like English.\n\n"
            "COMMON MISTAKE: stacking negatives. !(!isReady) is legal and means isReady, "
            "but anyone reading it has to stop and think. If you find yourself writing "
            "!(a && b), consider whether the condition is better expressed the other way "
            "round — clarity beats cleverness every time."
        ),
        "briefing": "Invert two signals. Declare boolean isLocked as true, then print !isLocked, then print the negation of the comparison 3 > 5. Two lines: false, then true.",
        "hints": [
            "The first is simply System.out.println(!isLocked);",
            "The second needs brackets around the comparison: !(3 > 5)",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        boolean isLocked = true;
        System.out.println(!isLocked);
        System.out.println(!(3 > 5));
    }
}''',
        "check": make_checker(
            expected_output="false\ntrue",
            success_line="Flipped both. ! is 'not', and it binds tightly — bracket the comparison.",
            missing_hint="I need two lines: false then true.",
            mismatch_hint="Check the ! is in front of each expression, and that the comparison is bracketed.",
            code_requires=["!isLocked", "!(3 > 5)"],
            code_requires_hint="I need both negations: !isLocked and !(3 > 5).",
        ),
    },

    "t3m17": {
        "title": "Compound Conditions",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "combining && and || with brackets",
        "teach": (
            "&& and || can be combined — but && binds TIGHTER than ||, which means Java "
            "groups them in a way you might not intend.\n\n"
            "a || b && c    is read as    a || (b && c)\n\n"
            "WORKED EXAMPLE — the same three values, grouped two ways:\n"
            "boolean isVip = true, isBanned = true, hasTicket = false;\n"
            "\n"
            "System.out.println(isVip || isBanned && hasTicket);\n"
            "-> true    (it becomes isVip || (isBanned && hasTicket) = true || false)\n"
            "\n"
            "System.out.println((isVip || isBanned) && hasTicket);\n"
            "-> false   (true && false)\n\n"
            "Completely different answers from identical text. This is exactly why real "
            "codebases bracket compound conditions even when the precedence would have "
            "worked out — the brackets tell the next reader what you MEANT, and protect "
            "you from having quietly meant the other one.\n\n"
            "COMMON MISTAKE: trusting precedence in a condition with three or more "
            "parts. Bracket it. It is free and it removes the whole class of bug."
        ),
        "briefing": "Show that grouping changes the answer. Declare boolean isVip true, isBanned true, hasTicket false. Print isVip || isBanned && hasTicket, then print (isVip || isBanned) && hasTicket. Two lines: true, then false.",
        "hints": [
            "Line one has no brackets — && grabs its two neighbours first.",
            "Line two forces the || to happen first by bracketing it.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        boolean isVip = true;
        boolean isBanned = true;
        boolean hasTicket = false;

        System.out.println(isVip || isBanned && hasTicket);
        System.out.println((isVip || isBanned) && hasTicket);
    }
}''',
        "wrong": [
            # bracketed both lines the same way
            '''public class Main {
    public static void main(String[] args) {
        boolean isVip = true;
        boolean isBanned = true;
        boolean hasTicket = false;

        System.out.println((isVip || isBanned) && hasTicket);
        System.out.println((isVip || isBanned) && hasTicket);
    }
}''',
        ],
        "check": make_checker(
            expected_output="true\nfalse",
            success_line="Same words, opposite answers. That's the argument for bracketing every compound condition you write.",
            missing_hint="I need two lines: true then false.",
            mismatch_hint="Line one must have NO brackets, line two must bracket the || part. That difference is the whole mission.",
            code_requires=["isVip || isBanned && hasTicket", "(isVip || isBanned) && hasTicket"],
            code_requires_hint="I need both versions — the unbracketed one first, then the bracketed one.",
        ),
    },

    "t3m18": {
        "title": "The Concatenation Trap",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "+ means join as soon as a String is involved",
        "teach": (
            "This is the operator bug you are most likely to actually ship. + means add "
            "for numbers and join for strings — and Java decides which, one step at a "
            "time, left to right.\n\n"
            "BROKEN:\n"
            "System.out.println(\"TOTAL: \" + 2 + 3);\n"
            "-> prints: TOTAL: 23\n"
            "Step 1: \"TOTAL: \" + 2 — one side is a String, so JOIN -> \"TOTAL: 2\"\n"
            "Step 2: \"TOTAL: 2\" + 3 — still a String, so JOIN -> \"TOTAL: 23\"\n"
            "The addition never happened.\n\n"
            "THE FIX — brackets, so the maths finishes before the joining starts:\n"
            "System.out.println(\"TOTAL: \" + (2 + 3));\n"
            "-> prints: TOTAL: 5\n\n"
            "And the same trap the other way round:\n"
            "System.out.println(2 + 3 + \" UNITS\");\n"
            "-> prints: 5 UNITS\n"
            "Here the two numbers meet FIRST (both numbers, so add), and only then does "
            "the String appear. Left to right explains both cases.\n\n"
            "COMMON MISTAKE: 'fixing' it by casting or by String.valueOf. The fix is one "
            "pair of brackets around the arithmetic."
        ),
        "briefing": "Demonstrate the trap and the fix. Print \"TOTAL: \" + 2 + 3 exactly as written, then print the same thing with brackets around the addition. Then print 2 + 3 + \" UNITS\". Three lines: TOTAL: 23, TOTAL: 5, 5 UNITS.",
        "hints": [
            "Write the first line exactly as the briefing gives it — it's supposed to produce the wrong-looking answer.",
            "The second differs only by a pair of brackets: \"TOTAL: \" + (2 + 3)",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("TOTAL: " + 2 + 3);
        System.out.println("TOTAL: " + (2 + 3));
        System.out.println(2 + 3 + " UNITS");
    }
}''',
        "check": make_checker(
            expected_output="TOTAL: 23\nTOTAL: 5\n5 UNITS",
            success_line="23, then 5, then 5 UNITS. Left to right, and + changes meaning the moment a String turns up.",
            missing_hint="I need three lines: TOTAL: 23, TOTAL: 5, and 5 UNITS.",
            mismatch_hint="Line one should show the BROKEN result (23) — don't fix it. Only line two gets the brackets.",
            code_requires=['"TOTAL: " + 2 + 3', '"TOTAL: " + (2 + 3)'],
            code_requires_hint="I need the trap version and the bracketed version, in that order.",
        ),
    },

    "t3m19": {
        "title": "What Modulo Is For",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "% for even/odd and for extracting digits",
        "teach": (
            "You've met % as 'the remainder'. Here's what it's actually USED for, because "
            "'remainder' alone rarely sounds useful.\n\n"
            "1. IS IT EVEN? A number is even exactly when dividing by 2 leaves nothing.\n"
            "   n % 2 == 0     even\n"
            "   n % 2 == 1     odd\n\n"
            "2. THE LAST DIGIT. % 10 leaves whatever wouldn't fit into the tens.\n"
            "   1947 % 10  ->  7\n\n"
            "3. WRAPPING ROUND. Any value % 12 is always 0 to 11 — how clock arithmetic,\n"
            "   cycling through colours, or looping an index back to the start all work.\n\n"
            "WORKED EXAMPLE:\n"
            "int serial = 3084;\n"
            "System.out.println(serial % 2 == 0);   // true  — it's even\n"
            "System.out.println(serial % 10);       // 4     — the last digit\n\n"
            "COMMON MISTAKE: using % with negative numbers and expecting a positive "
            "answer. In Java -7 % 3 is -1, not 2 — the sign follows the left operand."
        ),
        "briefing": "Analyse a serial number. Declare int serial as 3084. Print whether it is even (using % and ==), then print its last digit (using %). Two lines: true, then 4.",
        "hints": [
            "Even means the remainder after dividing by 2 is zero: serial % 2 == 0",
            "The last digit is what's left over from a division by 10: serial % 10",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int serial = 3084;
        System.out.println(serial % 2 == 0);
        System.out.println(serial % 10);
    }
}''',
        "wrong": [
            # answered without using % at all
            '''public class Main {
    public static void main(String[] args) {
        System.out.println(true);
        System.out.println(4);
    }
}''',
        ],
        "check": make_checker(
            expected_output="true\n4",
            success_line="Even, and ending in 4 — both from one operator. That's what % is really for.",
            missing_hint="I need two lines: true (is it even) then 4 (the last digit).",
            mismatch_hint="Check you used % 2 == 0 for the first and % 10 for the second.",
            code_requires=["serial % 2 == 0", "serial % 10"],
            code_requires_hint="Both answers have to come from % — serial % 2 == 0 and serial % 10.",
        ),
    },

    "t3m20": {
        "title": "BOSS — Checksum Unit",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "logic, negation and modulo together — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "!x                  flips a boolean; bracket any comparison you negate\n"
            "&& binds tighter than ||  — so bracket compound conditions\n"
            "\"T: \" + 2 + 3       joins to T: 23; \"T: \" + (2 + 3) adds first\n"
            "n % 2 == 0          even\n"
            "n % 10              last digit\n\n"
            "Everything here produces a value you can print directly — no if statements "
            "needed, and none available until the next topic."
        ),
        "briefing": (
            "Write the checksum unit from scratch.\n\n"
            "Declare int packetId as 4471 and boolean isCorrupt as false.\n\n"
            "Print, in order:\n"
            "- the last digit of packetId\n"
            "- whether packetId is even\n"
            "- whether packetId is over 1000 AND not corrupt\n"
            "- the line CHECKSUM: followed by packetId % 7 added to 10, correctly\n"
            "  bracketed so the arithmetic really happens\n\n"
            "Expected output:\n"
            "1\n"
            "false\n"
            "true\n"
            "CHECKSUM: 15"
        ),
        "hints": [
            "The third line combines a comparison with a negated boolean: packetId > 1000 && !isCorrupt",
            "The last line needs brackets or it will join instead of add: \"CHECKSUM: \" + (packetId % 7 + 10)",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void main(String[] args) {
        int packetId = 4471;
        boolean isCorrupt = false;

        System.out.println(packetId % 10);
        System.out.println(packetId % 2 == 0);
        System.out.println(packetId > 1000 && !isCorrupt);
        System.out.println("CHECKSUM: " + (packetId % 7 + 10));
    }
}''',
        "wrong": [
            # last line missing its brackets — joins instead of adding
            '''public class Main {
    public static void main(String[] args) {
        int packetId = 4471;
        boolean isCorrupt = false;

        System.out.println(packetId % 10);
        System.out.println(packetId % 2 == 0);
        System.out.println(packetId > 1000 && !isCorrupt);
        System.out.println("CHECKSUM: " + packetId % 7 + 10);
    }
}''',
        ],
        "check": make_checker(
            expected_output="1\nfalse\ntrue\nCHECKSUM: 15",
            success_line="Every operator in this topic, used correctly, from an empty editor. Block complete.",
            missing_hint="Output doesn't match, or it didn't compile. Work down the four lines one at a time.",
            mismatch_hint="If the last line reads CHECKSUM: 510, the brackets are missing — the joining swallowed your addition.",
            code_requires=["public class Main", "packetId % 10", "packetId % 2 == 0",
                           "&&", "!isCorrupt"],
            code_requires_hint="I need the modulo checks, an && with a negated !isCorrupt, and a properly bracketed checksum.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 5 — the two ways arithmetic lies to you, the Math library, and
    # the ternary. Final boss closes the topic.
    # ---------------------------------------------------------------------

    "t3m21": {
        "title": "Silent Overflow",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "integer overflow wraps around without warning",
        "teach": (
            "int stops at 2,147,483,647. Add one more and it doesn't crash, doesn't warn, "
            "doesn't grow — it WRAPS to the most negative value there is.\n\n"
            "WORKED EXAMPLE:\n"
            "int max = 2147483647;\n"
            "System.out.println(max + 1);\n"
            "-> prints: -2147483648\n\n"
            "That's not a bug in Java; it's how fixed-width binary arithmetic works "
            "everywhere. The bits ran out and the sign bit flipped.\n\n"
            "Java gives you the limits as named constants so you never have to type "
            "those digits:\n"
            "Integer.MAX_VALUE     2147483647\n"
            "Integer.MIN_VALUE     -2147483648\n\n"
            "Where it actually bites: multiplying a few large ints (milliseconds, file "
            "sizes, ids) and storing the result in an int. The fix is to use long for "
            "the calculation, not to hope.\n\n"
            "COMMON MISTAKE: assuming the assignment is what overflows. long total = "
            "bigInt * bigInt; still overflows, because the multiplication happens as int "
            "arithmetic FIRST and only the broken result is widened to long."
        ),
        "briefing": "Watch an int run off the end. Print Integer.MAX_VALUE, then print Integer.MAX_VALUE + 1 to show the wrap. Two lines: 2147483647, then -2147483648.",
        "hints": [
            "Use the constant rather than typing the digits: System.out.println(Integer.MAX_VALUE);",
            "The second line is the same expression plus one: Integer.MAX_VALUE + 1",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);
    }
}''',
        "check": make_checker(
            expected_output="2147483647\n-2147483648",
            success_line="Straight past the ceiling and out the bottom. No warning, no crash — which is exactly what makes it dangerous.",
            missing_hint="I need two lines: the maximum int, then that value plus one.",
            mismatch_hint="Check the second line really is MAX_VALUE + 1 — it should come out negative.",
            code_requires=["Integer.MAX_VALUE"],
            code_requires_hint="Use the Integer.MAX_VALUE constant rather than typing the number out.",
        ),
    },

    "t3m22": {
        "title": "Almost Point Three",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "why 0.1 + 0.2 isn't 0.3",
        "teach": (
            "Run this and brace yourself:\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(0.1 + 0.2);\n"
            "-> prints: 0.30000000000000004\n\n"
            "Java is not broken. double stores numbers in binary, and 0.1 in binary is a "
            "recurring fraction — like 1/3 in decimal, it never finishes. The stored "
            "value is a very close approximation, and the tiny errors surface when you "
            "add them.\n\n"
            "The consequence that matters: NEVER compare doubles with ==.\n"
            "  0.1 + 0.2 == 0.3     is false\n"
            "Instead, check the difference is small enough:\n"
            "  Math.abs(x - y) < 0.0001\n\n"
            "And never use double for money. Use integer pence, or BigDecimal. Every "
            "developer learns this the expensive way at least once.\n\n"
            "COMMON MISTAKE: assuming more decimal places fixes it. float is worse, not "
            "better. The problem is binary representation itself, not precision."
        ),
        "briefing": "See the imprecision for yourself. Print the result of 0.1 + 0.2, then print whether 0.1 + 0.2 == 0.3. Two lines: the long ugly number, then false.",
        "hints": [
            "Print the sum directly: System.out.println(0.1 + 0.2);",
            "The second line asks the equality question — bracket the sum: (0.1 + 0.2) == 0.3",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);
        System.out.println((0.1 + 0.2) == 0.3);
    }
}''',
        "check": make_checker(
            expected_output="0.30000000000000004\nfalse",
            success_line="0.30000000000000004, and false. Now you know why comparing doubles with == is a bug waiting to happen.",
            missing_hint="I need the sum printed, then the == comparison against 0.3.",
            mismatch_hint="Check both lines — the sum prints in full, and the comparison comes out false.",
            code_requires=["0.1 + 0.2"],
            code_requires_hint="Use the actual expression 0.1 + 0.2 rather than typing the result.",
        ),
    },

    "t3m23": {
        "title": "The Math Toolkit",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "Math.abs, Math.pow, Math.sqrt, Math.max",
        "teach": (
            "Java's Math class holds the operations that don't have their own symbols. "
            "You call them directly on Math — no import, no object to create.\n\n"
            "Math.abs(-9)        9           distance from zero, always positive\n"
            "Math.pow(2, 10)     1024.0      2 to the power of 10 — returns a double\n"
            "Math.sqrt(81)       9.0         square root — returns a double\n"
            "Math.max(3, 11)     11          the larger of two\n"
            "Math.min(3, 11)     3           the smaller of two\n"
            "Math.round(2.6)     3           nearest whole number\n\n"
            "WORKED EXAMPLE:\n"
            "int drift = -14;\n"
            "System.out.println(\"DRIFT: \" + Math.abs(drift));\n"
            "-> prints: DRIFT: 14\n\n"
            "Watch the return types. pow and sqrt always give a double, so Math.pow(2, 3) "
            "prints 8.0, not 8. If you need an int, cast it: (int) Math.pow(2, 3).\n\n"
            "COMMON MISTAKE: writing Math.pow(2, 10) when you want 2 * 2 * 2... For "
            "squaring, x * x is clearer and faster than Math.pow(x, 2)."
        ),
        "briefing": "Run four readings through the toolkit. Print Math.abs(-14), then Math.pow(2, 5), then Math.sqrt(144), then Math.max(17, 9). Four lines: 14, 32.0, 12.0, 17.",
        "hints": [
            "Each is a single println with the Math call inside: System.out.println(Math.abs(-14));",
            "Two of them return doubles, so expect 32.0 and 12.0 rather than 32 and 12.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println(Math.abs(-14));
        System.out.println(Math.pow(2, 5));
        System.out.println(Math.sqrt(144));
        System.out.println(Math.max(17, 9));
    }
}''',
        "check": make_checker(
            expected_output="14\n32.0\n12.0\n17",
            success_line="Four tools, four answers — and two of them doubles, which is the detail that catches people.",
            missing_hint="I need four lines from the four Math calls.",
            mismatch_hint="If you got 32 instead of 32.0, something converted it — pow and sqrt return doubles.",
            code_requires=["Math.abs", "Math.pow", "Math.sqrt", "Math.max"],
            code_requires_hint="I need all four: Math.abs, Math.pow, Math.sqrt and Math.max.",
        ),
    },

    "t3m24": {
        "title": "The Question Mark Operator",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "the ternary operator ?:",
        "teach": (
            "The ternary is the one operator that CHOOSES. It takes a condition and two "
            "values, and hands back one of them.\n\n"
            "condition ? valueIfTrue : valueIfFalse\n\n"
            "WORKED EXAMPLE:\n"
            "int temperature = 4;\n"
            "String state = temperature > 0 ? \"LIQUID\" : \"FROZEN\";\n"
            "System.out.println(state);\n"
            "-> prints: LIQUID\n\n"
            "Read it as a question: 'is temperature above zero? then LIQUID, otherwise "
            "FROZEN'. The ? is the question mark, the : is 'otherwise'.\n\n"
            "Crucially it is an EXPRESSION — it produces a value, so it can sit on the "
            "right of an assignment or straight inside a println(). That's what makes it "
            "different from the if statement you'll meet in Topic 6, which is a statement "
            "and produces nothing.\n\n"
            "Use it for exactly this: picking between two values. Anything longer belongs "
            "in an if.\n\n"
            "COMMON MISTAKE: nesting them — a ? b : c ? d : e. Legal, unreadable. One "
            "level only."
        ),
        "briefing": "Grade a reading with a single expression. Declare int power as 35, then use a ternary to build a String called status that is HIGH when power is above 50 and LOW otherwise. Print POWER: 35 then STATUS: LOW.",
        "hints": [
            "String status = power > 50 ? \"HIGH\" : \"LOW\";  — condition, question mark, then the two options.",
            "35 is not above 50, so the second option wins.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int power = 35;
        String status = power > 50 ? "HIGH" : "LOW";
        System.out.println("POWER: " + power);
        System.out.println("STATUS: " + status);
    }
}''',
        "wrong": [
            # hardcoded the answer instead of choosing it
            '''public class Main {
    public static void main(String[] args) {
        int power = 35;
        String status = "LOW";
        System.out.println("POWER: " + power);
        System.out.println("STATUS: " + status);
    }
}''',
        ],
        "check": make_checker(
            expected_output="POWER: 35\nSTATUS: LOW",
            success_line="One line, one decision, one value. The ternary picks — it doesn't branch.",
            missing_hint="I need POWER: 35 and STATUS: LOW, with the status chosen by a ternary.",
            mismatch_hint="Check the condition is power > 50 and that HIGH comes before the colon, LOW after.",
            code_requires=["?", ":", "power > 50"],
            code_requires_hint="The status must come from a ternary: power > 50 ? \"HIGH\" : \"LOW\"",
        ),
    },

    "t3m25": {
        "title": "FINAL BOSS — Expression Engine",
        "topic": 3, "topic_name": "Operators, Expressions & Statements",
        "concept": "the entire topic, from an empty editor",
        "teach": (
            "Empty editor. Everything from Topic 3.\n\n"
            "FULL RECAP:\n"
            "+ - * /            arithmetic; int / int truncates\n"
            "%                  remainder — even/odd, last digit, wrapping\n"
            "( )                beat all precedence; * / % beat + -\n"
            "(double) x / y     cast ONE operand to get a real answer\n"
            "+= -= *= /=        compound assignment\n"
            "++ --              step by one; pre hands back the new value\n"
            "< > <= >= == !=    comparisons, producing booleans\n"
            "&& || !            logic; && binds tighter than ||\n"
            "cond ? a : b       choose between two values\n"
            "Math.abs/pow/sqrt/max\n"
            "int overflows silently; doubles are approximate — never compare with ==\n\n"
            "Note the cast trick in line four — it's from earlier in this topic and it's "
            "the one people forget under exam pressure."
        ),
        "briefing": (
            "Write the expression engine from scratch.\n\n"
            "Declare int signalA as 47 and int signalB as 8.\n\n"
            "Print, in order:\n"
            "- signalA divided by signalB as INTEGER division\n"
            "- the remainder of that same division\n"
            "- the same division as a real decimal, by casting one operand\n"
            "- whether signalA is odd AND signalB is even\n"
            "- the larger of the two, via Math\n"
            "- the word EVEN or ODD for signalA, chosen with a ternary\n\n"
            "Expected output:\n"
            "5\n"
            "7\n"
            "5.875\n"
            "true\n"
            "47\n"
            "ODD"
        ),
        "hints": [
            "Line three needs the cast on ONE operand: (double) signalA / signalB. Casting the whole expression truncates first and gives 5.0.",
            "Line four is two modulo checks joined: signalA % 2 == 1 && signalB % 2 == 0",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void main(String[] args) {
        int signalA = 47;
        int signalB = 8;

        System.out.println(signalA / signalB);
        System.out.println(signalA % signalB);
        System.out.println((double) signalA / signalB);
        System.out.println(signalA % 2 == 1 && signalB % 2 == 0);
        System.out.println(Math.max(signalA, signalB));
        System.out.println(signalA % 2 == 0 ? "EVEN" : "ODD");
    }
}''',
        "wrong": [
            # cast applied to the whole expression — truncates before converting
            '''public class Main {
    public static void main(String[] args) {
        int signalA = 47;
        int signalB = 8;

        System.out.println(signalA / signalB);
        System.out.println(signalA % signalB);
        System.out.println((double) (signalA / signalB));
        System.out.println(signalA % 2 == 1 && signalB % 2 == 0);
        System.out.println(Math.max(signalA, signalB));
        System.out.println(signalA % 2 == 0 ? "EVEN" : "ODD");
    }
}''',
        ],
        "check": make_checker(
            expected_output="5\n7\n5.875\ntrue\n47\nODD",
            success_line="Division, remainder, casting, logic, Math and a ternary — six lines, every operator in the topic. Topic Three complete.",
            missing_hint="Output doesn't match, or it didn't compile. Take the six lines one at a time.",
            mismatch_hint="If line three reads 5.0, the cast is on the whole expression — it must be on one operand: (double) signalA / signalB.",
            code_requires=["public class Main", "(double) signalA / signalB", "Math.max", "?", "%"],
            code_requires_hint="I need the single-operand cast, a Math.max call, a ternary, and the modulo checks.",
        ),
    },
}
