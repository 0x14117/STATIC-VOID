"""
Topic 6 — Selection.

Mission content for this topic. See missions/__init__.py for how the
per-topic modules are stitched into the single MISSIONS mapping the
server and the checkers use, and missions/common.py for the shared
checker factories and boilerplate constants.

This is where branching arrives. Topics 1-5 deliberately avoided if
statements — a comparison there was a value to print, not a fork in the
road — so the first missions here are the player's first taste of code
that does different things depending on its data.
"""

from .common import (
    BASIC_BOILERPLATE,
    METHOD_BOILERPLATE,
    SCANNER_BOILERPLATE,
    make_checker,
)

MISSIONS = {

    # =====================================================================
    # TOPIC 6 — SELECTION
    # if, if/else, else-if chains, nested conditions, logical operators in
    # conditions, String comparison, switch, and the traps in each.
    # =====================================================================

    # ---------------------------------------------------------------------
    # Batch 1 — the if statement itself.
    # ---------------------------------------------------------------------

    "t6m1": {
        "title": "The First Decision",
        "topic": 6, "topic_name": "Selection",
        "concept": "the if statement",
        "teach": (
            "Until now every line of your programs ran, every time, in order. An if "
            "statement breaks that: the code inside it runs only when a condition is "
            "true.\n\n"
            "if (condition) {\n"
            "    // runs only if the condition is true\n"
            "}\n\n"
            "WORKED EXAMPLE:\n"
            "int fuel = 20;\n"
            "if (fuel < 50) {\n"
            "    System.out.println(\"LOW FUEL\");\n"
            "}\n"
            "System.out.println(\"CHECK COMPLETE\");\n"
            "-> prints:\n"
            "LOW FUEL\n"
            "CHECK COMPLETE\n\n"
            "If fuel had been 80, the first line would simply not appear — and CHECK "
            "COMPLETE would still print, because it's outside the braces.\n\n"
            "The condition goes in round brackets and must be a boolean — exactly the "
            "comparisons you built in Topic 3. The body goes in curly braces.\n\n"
            "COMMON MISTAKE: putting a semicolon after the condition.\n"
            "if (fuel < 50);  { ... }\n"
            "That compiles. The semicolon becomes the entire body of the if — an empty "
            "statement — and the braces afterwards then run unconditionally, every time. "
            "It is a genuinely nasty bug because it looks completely normal."
        ),
        "briefing": "Add a low-fuel warning. Declare int fuel as 20. If fuel is below 50, print LOW FUEL. After the if, always print CHECK COMPLETE.",
        "hints": [
            "if (fuel < 50) { — round brackets for the condition, curly braces for the body.",
            "The CHECK COMPLETE line goes AFTER the closing brace, so it always runs.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int fuel = 20;
        if (fuel < 50) {
            System.out.println("LOW FUEL");
        }
        System.out.println("CHECK COMPLETE");
    }
}''',
        "wrong": [
            # no if at all — prints both unconditionally
            '''public class Main {
    public static void main(String[] args) {
        int fuel = 20;
        System.out.println("LOW FUEL");
        System.out.println("CHECK COMPLETE");
    }
}''',
        ],
        "check": make_checker(
            expected_output="LOW FUEL\nCHECK COMPLETE",
            success_line="Your first branch. That warning appeared because the data said so — not because the line was there.",
            missing_hint="I need LOW FUEL then CHECK COMPLETE.",
            mismatch_hint="Check the condition is fuel < 50 and that CHECK COMPLETE sits outside the braces.",
            code_requires=["if (", "fuel < 50"],
            code_requires_hint="This needs a real if statement testing fuel < 50, not two plain println calls.",
        ),
    },

    "t6m2": {
        "title": "This Or That",
        "topic": 6, "topic_name": "Selection",
        "concept": "if / else",
        "teach": (
            "else gives you the other path: one of the two blocks always runs, never "
            "both, never neither.\n\n"
            "if (condition) {\n"
            "    // when true\n"
            "} else {\n"
            "    // when false\n"
            "}\n\n"
            "WORKED EXAMPLE:\n"
            "int power = 80;\n"
            "if (power > 50) {\n"
            "    System.out.println(\"SYSTEMS NOMINAL\");\n"
            "} else {\n"
            "    System.out.println(\"SYSTEMS FAILING\");\n"
            "}\n"
            "-> prints: SYSTEMS NOMINAL\n\n"
            "else has no condition of its own — it's simply 'everything the if didn't "
            "catch'. Writing else (power <= 50) is a compile error.\n\n"
            "This matters more than it looks: because else catches EVERYTHING else, you "
            "can't accidentally leave a gap where nothing happens. Two separate ifs with "
            "opposite conditions can drift apart when you edit one and forget the other; "
            "if/else can't.\n\n"
            "COMMON MISTAKE: putting code between the if's closing brace and the else. "
            "The } and the else must be adjacent — } else { — or it won't compile."
        ),
        "briefing": "Report a reactor's state both ways. Declare int power as 35. If power is above 50, print SYSTEMS NOMINAL; otherwise print SYSTEMS FAILING.",
        "hints": [
            "The structure is if (power > 50) { ... } else { ... }",
            "With power at 35 the condition is false, so the else branch is the one that runs.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int power = 35;
        if (power > 50) {
            System.out.println("SYSTEMS NOMINAL");
        } else {
            System.out.println("SYSTEMS FAILING");
        }
    }
}''',
        "wrong": [
            # printed the answer without branching
            '''public class Main {
    public static void main(String[] args) {
        int power = 35;
        System.out.println("SYSTEMS FAILING");
    }
}''',
        ],
        "check": make_checker(
            expected_output="SYSTEMS FAILING",
            success_line="One branch ran, the other didn't. Exactly one always will.",
            missing_hint="I need exactly one line of output, chosen by the condition.",
            mismatch_hint="With power at 35, power > 50 is false — so the else branch should run.",
            code_requires=["if (", "else"],
            code_requires_hint="This needs a real if/else, so the printed line is chosen by the condition.",
        ),
    },

    "t6m3": {
        "title": "Branch Both Ways",
        "topic": 6, "topic_name": "Selection",
        "concept": "the same condition, tested against different data",
        "teach": (
            "An if statement isn't interesting until the data changes. The same code can "
            "take different paths on different runs — that's the entire point.\n\n"
            "The cleanest way to see this is to put the decision in a METHOD and call it "
            "more than once, which also stops you copying the same if twice.\n\n"
            "WORKED EXAMPLE:\n"
            "public static void report(int depth) {\n"
            "    if (depth > 100) {\n"
            "        System.out.println(depth + \": DEEP\");\n"
            "    } else {\n"
            "        System.out.println(depth + \": SHALLOW\");\n"
            "    }\n"
            "}\n"
            "// called twice:\n"
            "report(150);\n"
            "report(40);\n"
            "-> prints:\n"
            "150: DEEP\n"
            "40: SHALLOW\n\n"
            "One if statement, two different outcomes, because it ran twice with "
            "different values. Each call gets its own stack frame and makes its own "
            "decision.\n\n"
            "COMMON MISTAKE: writing the same if twice with different hardcoded values "
            "instead of calling a method twice. If you ever need to change the rule, "
            "you now have two places to change and one of them will get forgotten."
        ),
        "briefing": "Write one decision and use it twice. Write a method check(int depth) that prints the depth, a colon and a space, then DEEP if depth is above 100 or SHALLOW otherwise. In main(), call check(150) then check(40).",
        "hints": [
            "The if/else goes INSIDE the method, so it runs afresh on every call.",
            "System.out.println(depth + \": DEEP\"); — the value and the label in one line.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void check(int depth) {
        if (depth > 100) {
            System.out.println(depth + ": DEEP");
        } else {
            System.out.println(depth + ": SHALLOW");
        }
    }

    public static void main(String[] args) {
        check(150);
        check(40);
    }
}''',
        "wrong": [
            # two hardcoded prints, no branch and no reuse
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("150: DEEP");
        System.out.println("40: SHALLOW");
    }
}''',
        ],
        "check": make_checker(
            expected_output="150: DEEP\n40: SHALLOW",
            success_line="One rule, two verdicts. Put the decision in a method and it can't drift out of step with itself.",
            missing_hint="I need both lines: 150: DEEP and 40: SHALLOW, from one method called twice.",
            mismatch_hint="Check the label and the colon-space, and that the threshold is 100.",
            code_requires=["static void check", "if (", "check(150)", "check(40)"],
            code_requires_hint="I need a check(int depth) method containing the if, called twice from main.",
        ),
    },

    "t6m4": {
        "title": "The Chain",
        "topic": 6, "topic_name": "Selection",
        "concept": "else if chains",
        "teach": (
            "For more than two outcomes, chain them with else if. Java tests each "
            "condition in order and stops at the FIRST one that's true.\n\n"
            "WORKED EXAMPLE:\n"
            "int score = 72;\n"
            "if (score >= 90) {\n"
            "    System.out.println(\"A\");\n"
            "} else if (score >= 70) {\n"
            "    System.out.println(\"B\");\n"
            "} else if (score >= 50) {\n"
            "    System.out.println(\"C\");\n"
            "} else {\n"
            "    System.out.println(\"FAIL\");\n"
            "}\n"
            "-> prints: B\n\n"
            "Notice the second condition is just score >= 70, NOT score >= 70 && score < "
            "90. It doesn't need the upper bound: if the score were 95, the chain would "
            "already have stopped at the first branch and never reached this one.\n\n"
            "ORDER IS EVERYTHING. Put >= 50 first and a score of 95 prints C, because 95 "
            "is indeed at least 50 and the chain stops there. For descending thresholds, "
            "go highest first.\n\n"
            "The final else is optional, but without one an unmatched value produces no "
            "output at all — usually a bug rather than a decision.\n\n"
            "COMMON MISTAKE: writing separate ifs instead of a chain. Four independent "
            "ifs test all four conditions and can print several lines; a chain prints "
            "exactly one."
        ),
        "briefing": "Grade a score with a chain. Write a method grade(int score) printing A for 90 or above, B for 70 or above, C for 50 or above, and FAIL otherwise. In main(), call it with 95, 72, 55 and 20 — one line each.",
        "hints": [
            "Highest threshold first, then work downwards — each else if only needs its lower bound.",
            "Four calls in main: grade(95); grade(72); grade(55); grade(20);",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void grade(int score) {
        if (score >= 90) {
            System.out.println("A");
        } else if (score >= 70) {
            System.out.println("B");
        } else if (score >= 50) {
            System.out.println("C");
        } else {
            System.out.println("FAIL");
        }
    }

    public static void main(String[] args) {
        grade(95);
        grade(72);
        grade(55);
        grade(20);
    }
}''',
        "wrong": [
            # chain in the wrong order — everything above 50 prints C
            '''public class Main {
    public static void grade(int score) {
        if (score >= 50) {
            System.out.println("C");
        } else if (score >= 70) {
            System.out.println("B");
        } else if (score >= 90) {
            System.out.println("A");
        } else {
            System.out.println("FAIL");
        }
    }

    public static void main(String[] args) {
        grade(95);
        grade(72);
        grade(55);
        grade(20);
    }
}''',
        ],
        "check": make_checker(
            expected_output="A\nB\nC\nFAIL",
            success_line="A, B, C, FAIL. The chain stopped at the first true condition every time — which is why the order had to be highest first.",
            missing_hint="I need four lines, one per call: A, B, C, FAIL.",
            mismatch_hint="If everything came out C, your thresholds are ascending — start from the highest and work down.",
            code_requires=["else if"],
            code_requires_hint="This one wants a single else-if chain, not separate if statements.",
        ),
    },

    "t6m5": {
        "title": "BOSS — Clearance Gate",
        "topic": 6, "topic_name": "Selection",
        "concept": "if, else and chains together — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "if (condition) { }                  runs only when true\n"
            "if (c) { } else { }                 exactly one of the two runs\n"
            "if (a) { } else if (b) { } else { } first true condition wins, then stops\n\n"
            "Conditions are the booleans from Topic 3. Chains test in order, so "
            "descending thresholds go highest first. And never put a semicolon after "
            "the condition."
        ),
        "briefing": (
            "Write the clearance gate from scratch.\n\n"
            "Write a method gate(int level) that prints:\n"
            "  level, then a colon and space, then\n"
            "  DIRECTOR   when level is 9 or above\n"
            "  OFFICER    when level is 5 or above\n"
            "  RECRUIT    when level is 1 or above\n"
            "  DENIED     otherwise\n\n"
            "In main(), call gate(9), gate(6), gate(2) and gate(0).\n\n"
            "Expected output:\n"
            "9: DIRECTOR\n"
            "6: OFFICER\n"
            "2: RECRUIT\n"
            "0: DENIED"
        ),
        "hints": [
            "One else-if chain inside the method, highest threshold first.",
            "Each branch prints level + \": \" + the label — so the number and verdict share a line.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void gate(int level) {
        if (level >= 9) {
            System.out.println(level + ": DIRECTOR");
        } else if (level >= 5) {
            System.out.println(level + ": OFFICER");
        } else if (level >= 1) {
            System.out.println(level + ": RECRUIT");
        } else {
            System.out.println(level + ": DENIED");
        }
    }

    public static void main(String[] args) {
        gate(9);
        gate(6);
        gate(2);
        gate(0);
    }
}''',
        "wrong": [
            # separate ifs rather than a chain — 9 matches three of them
            '''public class Main {
    public static void gate(int level) {
        if (level >= 9) {
            System.out.println(level + ": DIRECTOR");
        }
        if (level >= 5) {
            System.out.println(level + ": OFFICER");
        }
        if (level >= 1) {
            System.out.println(level + ": RECRUIT");
        }
    }

    public static void main(String[] args) {
        gate(9);
        gate(6);
        gate(2);
        gate(0);
    }
}''',
        ],
        "check": make_checker(
            expected_output="9: DIRECTOR\n6: OFFICER\n2: RECRUIT\n0: DENIED",
            success_line="Four levels, four verdicts, one chain. Block complete.",
            missing_hint="I need exactly four lines — one per call. Check the method is defined beside main().",
            mismatch_hint="If you got more than four lines, those are separate ifs rather than a chain — a chain stops at the first match.",
            code_requires=["public class Main", "static void gate", "else if"],
            code_requires_hint="I need a gate(int level) method using an else-if chain, called four times from main.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 2 — nesting, and the Topic 3 logical operators now doing the
    # job they were built for.
    # ---------------------------------------------------------------------

    "t6m6": {
        "title": "A Test Inside A Test",
        "topic": 6, "topic_name": "Selection",
        "concept": "nested if statements",
        "teach": (
            "An if can contain another if. The inner one is only reached when the outer "
            "condition was true.\n\n"
            "WORKED EXAMPLE:\n"
            "boolean hasKey = true;\n"
            "int level = 3;\n"
            "if (hasKey) {\n"
            "    if (level >= 5) {\n"
            "        System.out.println(\"VAULT OPEN\");\n"
            "    } else {\n"
            "        System.out.println(\"KEY OK, LEVEL TOO LOW\");\n"
            "    }\n"
            "} else {\n"
            "    System.out.println(\"NO KEY\");\n"
            "}\n"
            "-> prints: KEY OK, LEVEL TOO LOW\n\n"
            "The level check never happens at all without a key — which is exactly the "
            "point. Nesting lets the second question depend on the first having been "
            "answered.\n\n"
            "Nest only when you genuinely need that dependency. If both conditions must "
            "simply be true and there's nothing to say in between, one && is clearer than "
            "two levels of braces:\n"
            "  if (hasKey && level >= 5) { ... }\n"
            "The nested version earns its extra lines only because it can distinguish "
            "'no key' from 'key but wrong level'.\n\n"
            "COMMON MISTAKE: nesting four or five levels deep. Past two levels it becomes "
            "genuinely hard to see which else belongs to which if — that's the moment to "
            "pull the inner part out into a method."
        ),
        "briefing": "Guard a vault with two questions. Declare boolean hasKey as true and int level as 3. If there's no key, print NO KEY. If there is a key, check the level: 5 or above prints VAULT OPEN, otherwise KEY OK, LEVEL TOO LOW.",
        "hints": [
            "The outer if tests hasKey; the level check lives inside its braces.",
            "With hasKey true and level 3, you should reach the inner else.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        boolean hasKey = true;
        int level = 3;

        if (hasKey) {
            if (level >= 5) {
                System.out.println("VAULT OPEN");
            } else {
                System.out.println("KEY OK, LEVEL TOO LOW");
            }
        } else {
            System.out.println("NO KEY");
        }
    }
}''',
        "check": make_checker(
            expected_output="KEY OK, LEVEL TOO LOW",
            success_line="The inner question was only reachable because the outer one passed. That dependency is what nesting is for.",
            missing_hint="I need exactly one line of output, chosen by the two nested conditions.",
            mismatch_hint="With hasKey true and level 3, you should land in the inner else branch.",
            code_requires=["if (hasKey", "if (level"],
            code_requires_hint="I need the level test nested INSIDE the hasKey test, not beside it.",
        ),
    },

    "t6m7": {
        "title": "Both Conditions",
        "topic": 6, "topic_name": "Selection",
        "concept": "&& inside an if",
        "teach": (
            "You built && in Topic 3 and printed its result. Here's the job it was "
            "actually designed for: combining two requirements into one condition.\n\n"
            "WORKED EXAMPLE:\n"
            "int age = 25;\n"
            "boolean hasTicket = true;\n"
            "if (age >= 18 && hasTicket) {\n"
            "    System.out.println(\"ENTRY ALLOWED\");\n"
            "} else {\n"
            "    System.out.println(\"ENTRY REFUSED\");\n"
            "}\n"
            "-> prints: ENTRY ALLOWED\n\n"
            "Both sides must be true. This is the flat equivalent of nesting one if "
            "inside another — and when you have nothing to say about WHICH requirement "
            "failed, it's the better shape: one condition, one pair of braces.\n\n"
            "Short-circuiting matters here in a way it didn't when you were just printing "
            "results. If the left side is false, the right side is never evaluated — so "
            "you can safely write a check whose second half would fail if the first "
            "half weren't true.\n\n"
            "COMMON MISTAKE: age >= 18 && <= 65. Each side of && must be a complete "
            "condition — write age >= 18 && age <= 65."
        ),
        "briefing": "Both requirements or nothing. Write a method entry(int age, boolean hasTicket) printing ENTRY ALLOWED when age is at least 18 AND they have a ticket, and ENTRY REFUSED otherwise. In main(), call entry(25, true), entry(15, true) and entry(30, false).",
        "hints": [
            "One condition with && joining both requirements: if (age >= 18 && hasTicket)",
            "Only the first call should be allowed — the other two each fail on a different half.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void entry(int age, boolean hasTicket) {
        if (age >= 18 && hasTicket) {
            System.out.println("ENTRY ALLOWED");
        } else {
            System.out.println("ENTRY REFUSED");
        }
    }

    public static void main(String[] args) {
        entry(25, true);
        entry(15, true);
        entry(30, false);
    }
}''',
        "wrong": [
            # used || instead of && — lets both failures through
            '''public class Main {
    public static void entry(int age, boolean hasTicket) {
        if (age >= 18 || hasTicket) {
            System.out.println("ENTRY ALLOWED");
        } else {
            System.out.println("ENTRY REFUSED");
        }
    }

    public static void main(String[] args) {
        entry(25, true);
        entry(15, true);
        entry(30, false);
    }
}''',
        ],
        "check": make_checker(
            expected_output="ENTRY ALLOWED\nENTRY REFUSED\nENTRY REFUSED",
            success_line="Allowed once, refused twice — each refusal failing a different half. That's && doing its job.",
            missing_hint="I need three lines, one per call.",
            mismatch_hint="If all three were allowed, you're using || — && requires BOTH sides.",
            code_requires=["&&"],
            code_requires_hint="This one needs && joining the two requirements in a single condition.",
        ),
    },

    "t6m8": {
        "title": "Either Route",
        "topic": 6, "topic_name": "Selection",
        "concept": "|| inside an if",
        "teach": (
            "|| in a condition means any one route is enough.\n\n"
            "WORKED EXAMPLE:\n"
            "boolean isStaff = false;\n"
            "boolean isVip = true;\n"
            "if (isStaff || isVip) {\n"
            "    System.out.println(\"FAST TRACK\");\n"
            "} else {\n"
            "    System.out.println(\"MAIN QUEUE\");\n"
            "}\n"
            "-> prints: FAST TRACK\n\n"
            "Translating a rule into && or || is where most real bugs start, because "
            "English is ambiguous and code isn't. 'Staff and VIPs get fast track' means a "
            "person who is EITHER — that's ||. Nobody is simultaneously staff and a VIP, "
            "so && would let nobody through at all.\n\n"
            "The test that saves you: describe a SINGLE person who should pass, then "
            "check your condition against them.\n\n"
            "COMMON MISTAKE: chaining || where you meant a range. x == 1 || 2 || 3 is not "
            "valid Java — each side must be a whole condition: x == 1 || x == 2 || x == 3."
        ),
        "briefing": "Let either credential through. Write a method queue(boolean isStaff, boolean isVip) printing FAST TRACK when either is true, and MAIN QUEUE when neither is. In main(), call queue(false, true), queue(true, false) and queue(false, false).",
        "hints": [
            "if (isStaff || isVip) — either one is enough.",
            "Only the third call has nothing true, so only it should reach the main queue.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void queue(boolean isStaff, boolean isVip) {
        if (isStaff || isVip) {
            System.out.println("FAST TRACK");
        } else {
            System.out.println("MAIN QUEUE");
        }
    }

    public static void main(String[] args) {
        queue(false, true);
        queue(true, false);
        queue(false, false);
    }
}''',
        "wrong": [
            # && instead of || — nobody with a single credential gets through
            '''public class Main {
    public static void queue(boolean isStaff, boolean isVip) {
        if (isStaff && isVip) {
            System.out.println("FAST TRACK");
        } else {
            System.out.println("MAIN QUEUE");
        }
    }

    public static void main(String[] args) {
        queue(false, true);
        queue(true, false);
        queue(false, false);
    }
}''',
        ],
        "check": make_checker(
            expected_output="FAST TRACK\nFAST TRACK\nMAIN QUEUE",
            success_line="Either credential was enough, and nothing got through with neither. That's the rule translated correctly.",
            missing_hint="I need three lines, one per call.",
            mismatch_hint="If only the main queue printed, you used && — 'staff or VIP' needs ||.",
            code_requires=["||"],
            code_requires_hint="This one needs || — either credential should be enough on its own.",
        ),
    },

    "t6m9": {
        "title": "Unless",
        "topic": 6, "topic_name": "Selection",
        "concept": "! inside a condition, and the guard pattern",
        "teach": (
            "! in a condition reads as 'unless' or 'if not'. It's at its best on a "
            "well-named boolean.\n\n"
            "WORKED EXAMPLE:\n"
            "boolean isLocked = true;\n"
            "if (!isLocked) {\n"
            "    System.out.println(\"PROCEEDING\");\n"
            "} else {\n"
            "    System.out.println(\"BLOCKED\");\n"
            "}\n"
            "-> prints: BLOCKED\n\n"
            "The pattern this unlocks is the GUARD CLAUSE — check for the bad case first, "
            "deal with it, and let the rest of the method assume everything is fine:\n\n"
            "public static void launch(boolean isReady) {\n"
            "    if (!isReady) {\n"
            "        System.out.println(\"ABORT\");\n"
            "        return;                  // leave immediately\n"
            "    }\n"
            "    System.out.println(\"LAUNCHING\");\n"
            "}\n\n"
            "That early return from Topic 4 is doing the work. Everything after the guard "
            "runs only in the good case, without being wrapped in another level of "
            "braces — which is why experienced code tends to be flatter than beginners "
            "expect.\n\n"
            "COMMON MISTAKE: if (!isReady == false). Double negatives are legal and "
            "unreadable. If you're writing one, the condition wants inverting instead."
        ),
        "briefing": "Guard a launch. Write a method launch(boolean isReady) that, if NOT ready, prints ABORT and returns immediately; otherwise it prints LAUNCHING. In main(), call launch(false) then launch(true).",
        "hints": [
            "The guard is if (!isReady) { print ABORT; return; } with no else at all.",
            "The LAUNCHING line then sits after the guard's closing brace — reached only when ready.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void launch(boolean isReady) {
        if (!isReady) {
            System.out.println("ABORT");
            return;
        }
        System.out.println("LAUNCHING");
    }

    public static void main(String[] args) {
        launch(false);
        launch(true);
    }
}''',
        "wrong": [
            # no return — prints ABORT and then launches anyway
            '''public class Main {
    public static void launch(boolean isReady) {
        if (!isReady) {
            System.out.println("ABORT");
        }
        System.out.println("LAUNCHING");
    }

    public static void main(String[] args) {
        launch(false);
        launch(true);
    }
}''',
        ],
        "check": make_checker(
            expected_output="ABORT\nLAUNCHING",
            success_line="Guarded. The bad case left early, and the rest of the method never had to worry about it.",
            missing_hint="I need two lines: ABORT from the first call, LAUNCHING from the second.",
            mismatch_hint="If ABORT was followed by LAUNCHING on the same call, the guard is missing its return.",
            code_requires=["!isReady", "return"],
            code_requires_hint="The guard needs to test !isReady and then return immediately, so the rest of the method is skipped.",
        ),
    },

    "t6m10": {
        "title": "BOSS — Security Checkpoint",
        "topic": 6, "topic_name": "Selection",
        "concept": "nesting, &&, || and ! together — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "if (a) { if (b) { } }     nest when the second question depends on the first\n"
            "if (a && b)               flat, when you just need both\n"
            "if (a || b)               either is enough\n"
            "if (!a) { ...; return; }  a guard — handle the bad case and leave\n\n"
            "Translate the RULE, not the sentence. 'Staff and VIPs' almost always means "
            "either, which is ||."
        ),
        "briefing": (
            "Write the security checkpoint from scratch.\n\n"
            "Write a method screen(boolean hasBadge, boolean isEscorted, int threatLevel):\n"
            "- if neither badge nor escort, print NO CREDENTIALS and return immediately\n"
            "- otherwise, if the threat level is 7 or above, print HOLD FOR SEARCH\n"
            "- otherwise print CLEARED\n\n"
            "In main(), call screen(false, false, 1), screen(true, false, 9) and "
            "screen(false, true, 2).\n\n"
            "Expected output:\n"
            "NO CREDENTIALS\n"
            "HOLD FOR SEARCH\n"
            "CLEARED"
        ),
        "hints": [
            "The first check is a guard on 'neither' — if (!hasBadge && !isEscorted) { ...; return; }",
            "After the guard, a plain if/else on threatLevel >= 7 is all that's left.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void screen(boolean hasBadge, boolean isEscorted, int threatLevel) {
        if (!hasBadge && !isEscorted) {
            System.out.println("NO CREDENTIALS");
            return;
        }

        if (threatLevel >= 7) {
            System.out.println("HOLD FOR SEARCH");
        } else {
            System.out.println("CLEARED");
        }
    }

    public static void main(String[] args) {
        screen(false, false, 1);
        screen(true, false, 9);
        screen(false, true, 2);
    }
}''',
        "wrong": [
            # guard uses || so anyone missing either credential is turned away
            '''public class Main {
    public static void screen(boolean hasBadge, boolean isEscorted, int threatLevel) {
        if (!hasBadge || !isEscorted) {
            System.out.println("NO CREDENTIALS");
            return;
        }

        if (threatLevel >= 7) {
            System.out.println("HOLD FOR SEARCH");
        } else {
            System.out.println("CLEARED");
        }
    }

    public static void main(String[] args) {
        screen(false, false, 1);
        screen(true, false, 9);
        screen(false, true, 2);
    }
}''',
        ],
        "check": make_checker(
            expected_output="NO CREDENTIALS\nHOLD FOR SEARCH\nCLEARED",
            success_line="Guarded, then graded. Three different paths through one method. Block complete.",
            missing_hint="I need exactly three lines, one per call.",
            mismatch_hint="If everything came out NO CREDENTIALS, the guard is too strict — it should reject only when BOTH credentials are missing.",
            code_requires=["public class Main", "static void screen", "return"],
            code_requires_hint="I need a screen(...) method with an early-returning guard, called three times from main.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 3 — comparing Strings. == on text is the bug that costs
    # students the most marks in this topic, so it gets three missions and
    # a teach block that explains WHY rather than just forbidding it.
    # ---------------------------------------------------------------------

    "t6m11": {
        "title": "The Equals Trap",
        "topic": 6, "topic_name": "Selection",
        "concept": "== vs .equals() for Strings",
        "teach": (
            "This is the most important mission in Topic 6. Read it twice.\n\n"
            "== on Strings does NOT compare the text. It asks 'are these two the same "
            "object in memory?' — which is almost never the question you meant.\n\n"
            "BROKEN:\n"
            "String typed = new String(\"OPEN\");\n"
            "if (typed == \"OPEN\") {\n"
            "    System.out.println(\"MATCH\");\n"
            "} else {\n"
            "    System.out.println(\"NO MATCH\");\n"
            "}\n"
            "-> prints: NO MATCH\n"
            "The text is identical. The objects are different. == says no.\n\n"
            "THE FIX — .equals() compares the actual characters:\n"
            "if (typed.equals(\"OPEN\")) { ... }\n"
            "-> MATCH\n\n"
            "What makes this genuinely dangerous is that == sometimes appears to work. "
            "Java reuses identical string LITERALS from a shared pool, so\n"
            "  String a = \"OPEN\"; if (a == \"OPEN\")\n"
            "is true — and you conclude == is fine. Then the text arrives from a Scanner, "
            "a file, or the network instead of a literal, it's a fresh object, and the "
            "same code silently stops working.\n\n"
            "RULE: == for numbers, chars and booleans. .equals() for Strings and every "
            "other object. No exceptions.\n\n"
            "COMMON MISTAKE: assuming this affects only Strings. == on ANY object "
            "compares identity, not contents — you'll meet it again in Topic 9."
        ),
        "briefing": "Compare text properly. Declare String command holding a new String(\"OPEN\") — deliberately a separate object. Print the result of comparing it to \"OPEN\" with == , then print the result of comparing it with .equals(). Two lines: false, then true.",
        "hints": [
            "new String(\"OPEN\") makes a distinct object holding the same characters — that's what exposes the difference.",
            "System.out.println(command == \"OPEN\"); then System.out.println(command.equals(\"OPEN\"));",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        String command = new String("OPEN");
        System.out.println(command == "OPEN");
        System.out.println(command.equals("OPEN"));
    }
}''',
        "check": make_checker(
            expected_output="false\ntrue",
            success_line="false then true, on identical text. That is the single most expensive bug in first-year Java — and you've now seen it fail on purpose.",
            missing_hint="I need two lines: the == comparison, then the .equals() comparison.",
            mismatch_hint="If both printed true, the String isn't a separate object — use new String(\"OPEN\") to force one.",
            code_requires=['== "OPEN"', '.equals("OPEN")'],
            code_requires_hint="I need both comparisons written out — one with == and one with .equals().",
        ),
    },

    "t6m12": {
        "title": "Case Insensitive",
        "topic": 6, "topic_name": "Selection",
        "concept": "equalsIgnoreCase",
        "teach": (
            ".equals() is exact — every character, capitals included. \"Yes\" and \"yes\" "
            "are different strings.\n\n"
            "For user input that's usually the wrong strictness. equalsIgnoreCase "
            "compares the letters and ignores the capitalisation.\n\n"
            "WORKED EXAMPLE:\n"
            "String answer = \"YeS\";\n"
            "System.out.println(answer.equals(\"yes\"));             // false\n"
            "System.out.println(answer.equalsIgnoreCase(\"yes\"));   // true\n\n"
            "Any time a human types the text, reach for equalsIgnoreCase — nobody types "
            "consistent capitals, and rejecting someone's answer because they pressed "
            "shift is a bug, not a feature.\n\n"
            "Keep .equals() for values your program controls: codes, keys, identifiers "
            "from a file you wrote.\n\n"
            "COMMON MISTAKE: calling a String method on a variable that might be null. "
            "answer.equals(...) on a null answer throws NullPointerException. The trick "
            "is to put the literal first — \"yes\".equalsIgnoreCase(answer) — because a "
            "literal is never null. That's a genuinely useful habit."
        ),
        "briefing": "Accept an answer however it's typed. Write a method confirm(String answer) printing CONFIRMED when the answer matches yes ignoring capitals, and CANCELLED otherwise. In main(), call confirm(\"YES\"), confirm(\"yes\") and confirm(\"no\").",
        "hints": [
            "answer.equalsIgnoreCase(\"yes\") — one method call does the whole comparison.",
            "The first two calls should both be confirmed; only the third is cancelled.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void confirm(String answer) {
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("CONFIRMED");
        } else {
            System.out.println("CANCELLED");
        }
    }

    public static void main(String[] args) {
        confirm("YES");
        confirm("yes");
        confirm("no");
    }
}''',
        "wrong": [
            # exact equals — rejects the capitalised YES
            '''public class Main {
    public static void confirm(String answer) {
        if (answer.equals("yes")) {
            System.out.println("CONFIRMED");
        } else {
            System.out.println("CANCELLED");
        }
    }

    public static void main(String[] args) {
        confirm("YES");
        confirm("yes");
        confirm("no");
    }
}''',
        ],
        "check": make_checker(
            expected_output="CONFIRMED\nCONFIRMED\nCANCELLED",
            success_line="Both spellings accepted. Never make someone's caps lock decide whether their answer counts.",
            missing_hint="I need three lines, one per call.",
            mismatch_hint="If YES was cancelled, you used .equals() — capitals matter to it. equalsIgnoreCase doesn't care.",
            code_requires=["equalsIgnoreCase"],
            code_requires_hint="This one specifically wants equalsIgnoreCase.",
        ),
    },

    "t6m13": {
        "title": "Which Comes First",
        "topic": 6, "topic_name": "Selection",
        "concept": "compareTo for ordering text",
        "teach": (
            ".equals() answers 'are these the same?'. compareTo answers 'which comes "
            "first alphabetically?' — and it answers with a NUMBER.\n\n"
            "a.compareTo(b)  is  negative  when a comes before b\n"
            "                    zero      when they're equal\n"
            "                    positive  when a comes after b\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(\"apple\".compareTo(\"banana\") < 0);   // true\n"
            "System.out.println(\"banana\".compareTo(\"apple\") < 0);   // false\n"
            "System.out.println(\"apple\".compareTo(\"apple\") == 0);   // true\n\n"
            "Don't test the exact number — it's the character-code difference and varies. "
            "Compare it against zero.\n\n"
            "The trap: compareTo is case SENSITIVE, and in character codes every capital "
            "comes before every lowercase letter. So \"Zebra\".compareTo(\"apple\") is "
            "negative — Zebra sorts first, which is not what a human calls alphabetical. "
            "For human-facing ordering use compareToIgnoreCase.\n\n"
            "COMMON MISTAKE: using > and < directly on Strings. name1 < name2 doesn't "
            "compile — relational operators only work on numbers. compareTo is how Java "
            "orders text, and it's what sorting uses under the hood."
        ),
        "briefing": "Order two callsigns. Write a method first(String a, String b) that prints whichever comes first alphabetically, using compareTo. In main(), call first(\"ZULU\", \"ALPHA\") then first(\"BRAVO\", \"DELTA\"). Two lines: ALPHA, then BRAVO.",
        "hints": [
            "if (a.compareTo(b) < 0) means a comes first — print a, otherwise print b.",
            "Remember to compare the result against 0 rather than testing for a specific number.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void first(String a, String b) {
        if (a.compareTo(b) < 0) {
            System.out.println(a);
        } else {
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        first("ZULU", "ALPHA");
        first("BRAVO", "DELTA");
    }
}''',
        "wrong": [
            # comparison backwards — prints the later one
            '''public class Main {
    public static void first(String a, String b) {
        if (a.compareTo(b) > 0) {
            System.out.println(a);
        } else {
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        first("ZULU", "ALPHA");
        first("BRAVO", "DELTA");
    }
}''',
        ],
        "check": make_checker(
            expected_output="ALPHA\nBRAVO",
            success_line="Ordered correctly, both ways round. Negative means 'a comes first' — that's the whole convention.",
            missing_hint="I need two lines: ALPHA then BRAVO.",
            mismatch_hint="If you got ZULU and DELTA, the comparison is the wrong way round — negative means the FIRST argument comes first.",
            code_requires=["compareTo"],
            code_requires_hint="This one wants compareTo — relational operators like < don't work on Strings.",
        ),
    },

    "t6m14": {
        "title": "The Switch",
        "topic": 6, "topic_name": "Selection",
        "concept": "switch on an int",
        "teach": (
            "When you're testing ONE value against many fixed possibilities, a switch "
            "says it more clearly than a long else-if chain.\n\n"
            "WORKED EXAMPLE:\n"
            "int option = 2;\n"
            "switch (option) {\n"
            "    case 1:\n"
            "        System.out.println(\"SCAN\");\n"
            "        break;\n"
            "    case 2:\n"
            "        System.out.println(\"REPORT\");\n"
            "        break;\n"
            "    default:\n"
            "        System.out.println(\"UNKNOWN\");\n"
            "}\n"
            "-> prints: REPORT\n\n"
            "switch takes the value; each case is one possibility; break ends that case; "
            "default catches everything unmatched, like the final else in a chain.\n\n"
            "Use a switch when you're matching one variable against fixed VALUES. Use an "
            "else-if chain when the branches test ranges or different variables — a "
            "switch can't express score >= 90.\n\n"
            "COMMON MISTAKE: forgetting break. Execution then falls straight through into "
            "the next case and runs that too. It's the next mission, because it's such a "
            "reliable source of confusion."
        ),
        "briefing": "Route a menu choice. Write a method route(int option) using a switch: 1 prints SCAN, 2 prints REPORT, 3 prints RETREAT, anything else prints UNKNOWN. In main(), call route(1), route(3) and route(9).",
        "hints": [
            "switch (option) { case 1: ... break; case 2: ... break; ... default: ... }",
            "Every case needs its own break, or it will run the case below it as well.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void route(int option) {
        switch (option) {
            case 1:
                System.out.println("SCAN");
                break;
            case 2:
                System.out.println("REPORT");
                break;
            case 3:
                System.out.println("RETREAT");
                break;
            default:
                System.out.println("UNKNOWN");
        }
    }

    public static void main(String[] args) {
        route(1);
        route(3);
        route(9);
    }
}''',
        "wrong": [
            # if/else chain rather than a switch
            '''public class Main {
    public static void route(int option) {
        if (option == 1) {
            System.out.println("SCAN");
        } else if (option == 2) {
            System.out.println("REPORT");
        } else if (option == 3) {
            System.out.println("RETREAT");
        } else {
            System.out.println("UNKNOWN");
        }
    }

    public static void main(String[] args) {
        route(1);
        route(3);
        route(9);
    }
}''',
        ],
        "check": make_checker(
            expected_output="SCAN\nRETREAT\nUNKNOWN",
            success_line="Three routes, one switch, default catching the stragglers. Clearer than a chain when you're matching fixed values.",
            missing_hint="I need three lines, one per call.",
            mismatch_hint="If extra lines appeared, a case is missing its break and fell through into the next one.",
            code_requires=["switch", "case", "break", "default"],
            code_requires_hint="This one specifically wants a switch with cases, breaks and a default — not an if/else chain.",
        ),
    },

    "t6m15": {
        "title": "BOSS — Command Console",
        "topic": 6, "topic_name": "Selection",
        "concept": "String comparison and switch together — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "s.equals(t)              same characters — ALWAYS this, never == \n"
            "s.equalsIgnoreCase(t)    same letters, ignoring capitals\n"
            "s.compareTo(t) < 0       s comes first alphabetically\n"
            "switch (v) { case X: ... break; default: ... }\n\n"
            "== on a String asks whether two references point at the same object. It "
            "sometimes says true by accident, which is exactly what makes it dangerous."
        ),
        "briefing": (
            "Write the command console from scratch.\n\n"
            "Write a method execute(String command):\n"
            "- if the command matches STATUS ignoring capitals, print ALL SYSTEMS GO\n"
            "- otherwise if it matches ABORT ignoring capitals, print ABORTING\n"
            "- otherwise print UNKNOWN COMMAND\n\n"
            "In main(), call execute(\"status\"), execute(\"AbOrT\") and execute(\"dance\").\n\n"
            "Expected output:\n"
            "ALL SYSTEMS GO\n"
            "ABORTING\n"
            "UNKNOWN COMMAND\n\n"
            "Comparing with == will fail this mission, and Cipher will notice."
        ),
        "hints": [
            "An else-if chain of equalsIgnoreCase calls is the straightforward shape here.",
            "Putting the literal first — \"STATUS\".equalsIgnoreCase(command) — also protects you if the command is ever null.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void execute(String command) {
        if (command.equalsIgnoreCase("STATUS")) {
            System.out.println("ALL SYSTEMS GO");
        } else if (command.equalsIgnoreCase("ABORT")) {
            System.out.println("ABORTING");
        } else {
            System.out.println("UNKNOWN COMMAND");
        }
    }

    public static void main(String[] args) {
        execute("status");
        execute("AbOrT");
        execute("dance");
    }
}''',
        "wrong": [
            # == on Strings — nothing matches
            '''public class Main {
    public static void execute(String command) {
        if (command == "STATUS") {
            System.out.println("ALL SYSTEMS GO");
        } else if (command == "ABORT") {
            System.out.println("ABORTING");
        } else {
            System.out.println("UNKNOWN COMMAND");
        }
    }

    public static void main(String[] args) {
        execute("status");
        execute("AbOrT");
        execute("dance");
    }
}''',
        ],
        "check": make_checker(
            expected_output="ALL SYSTEMS GO\nABORTING\nUNKNOWN COMMAND",
            success_line="Both commands recognised whatever the capitals, and the nonsense rejected. Block complete.",
            missing_hint="I need exactly three lines, one per call.",
            mismatch_hint="If everything came out UNKNOWN COMMAND, you're comparing text with == — that asks a different question entirely.",
            code_requires=["public class Main", "equalsIgnoreCase"],
            code_requires_hint="Compare the commands with equalsIgnoreCase — == on Strings won't work here.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 4 — switch on text, deliberate fall-through, if vs ternary, and
    # the missing-braces bug.
    # ---------------------------------------------------------------------

    "t6m16": {
        "title": "Switching On Text",
        "topic": 6, "topic_name": "Selection",
        "concept": "switch on a String",
        "teach": (
            "switch works on Strings too, and it's the tidiest way to route a text "
            "command.\n\n"
            "WORKED EXAMPLE:\n"
            "String colour = \"RED\";\n"
            "switch (colour) {\n"
            "    case \"RED\":\n"
            "        System.out.println(\"STOP\");\n"
            "        break;\n"
            "    case \"GREEN\":\n"
            "        System.out.println(\"GO\");\n"
            "        break;\n"
            "    default:\n"
            "        System.out.println(\"UNKNOWN\");\n"
            "}\n"
            "-> prints: STOP\n\n"
            "Here's the part worth noticing: switch on a String compares with .equals() "
            "internally, NOT with ==. So the trap from the earlier mission doesn't apply "
            "here — a switch gets String comparison right for you automatically.\n"
            "It is, however, case SENSITIVE. \"red\" would not match case \"RED\", and "
            "there's no ignore-case version — so real code usually does\n"
            "  switch (colour.toUpperCase())\n"
            "to normalise the text first.\n\n"
            "switch also works on int, char and enum. It does NOT work on double or "
            "boolean.\n\n"
            "COMMON MISTAKE: a null String. switch (colour) throws "
            "NullPointerException if colour is null — not the default case, an actual "
            "crash."
        ),
        "briefing": "Route colour codes. Write a method signal(String colour) using a switch on the colour converted to upper case: RED prints STOP, GREEN prints GO, AMBER prints WAIT, anything else prints UNKNOWN. In main(), call signal(\"red\"), signal(\"GREEN\") and signal(\"purple\").",
        "hints": [
            "Normalise first: switch (colour.toUpperCase()) — then every case can be written in capitals.",
            "Three cases plus a default, each case ended with break.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void signal(String colour) {
        switch (colour.toUpperCase()) {
            case "RED":
                System.out.println("STOP");
                break;
            case "GREEN":
                System.out.println("GO");
                break;
            case "AMBER":
                System.out.println("WAIT");
                break;
            default:
                System.out.println("UNKNOWN");
        }
    }

    public static void main(String[] args) {
        signal("red");
        signal("GREEN");
        signal("purple");
    }
}''',
        "wrong": [
            # no toUpperCase — lowercase "red" falls through to default
            '''public class Main {
    public static void signal(String colour) {
        switch (colour) {
            case "RED":
                System.out.println("STOP");
                break;
            case "GREEN":
                System.out.println("GO");
                break;
            case "AMBER":
                System.out.println("WAIT");
                break;
            default:
                System.out.println("UNKNOWN");
        }
    }

    public static void main(String[] args) {
        signal("red");
        signal("GREEN");
        signal("purple");
    }
}''',
        ],
        "check": make_checker(
            expected_output="STOP\nGO\nUNKNOWN",
            success_line="Routed correctly, whatever the capitals. Normalising the text before the switch is the standard move.",
            missing_hint="I need three lines, one per call.",
            mismatch_hint="If 'red' came out UNKNOWN, the switch is case sensitive — normalise with toUpperCase() first.",
            code_requires=["switch", "toUpperCase"],
            code_requires_hint="I need a switch on the colour normalised with toUpperCase().",
        ),
    },

    "t6m17": {
        "title": "Falling Through",
        "topic": 6, "topic_name": "Selection",
        "concept": "stacked cases — fall-through used deliberately",
        "teach": (
            "A case without break falls through into the next one. Usually that's a bug. "
            "Occasionally it's exactly what you want.\n\n"
            "THE BUG:\n"
            "case 1:\n"
            "    System.out.println(\"ONE\");    // no break!\n"
            "case 2:\n"
            "    System.out.println(\"TWO\");\n"
            "    break;\n"
            "-> with option 1, prints BOTH lines. Execution enters at case 1 and keeps "
            "going until it hits a break.\n\n"
            "THE DELIBERATE USE — stacking labels so several values share one body:\n"
            "WORKED EXAMPLE:\n"
            "switch (day) {\n"
            "    case \"SAT\":\n"
            "    case \"SUN\":\n"
            "        System.out.println(\"WEEKEND\");\n"
            "        break;\n"
            "    default:\n"
            "        System.out.println(\"WEEKDAY\");\n"
            "}\n"
            "-> SAT and SUN both print WEEKEND\n\n"
            "case \"SAT\": has no body at all — it falls straight into case \"SUN\", "
            "which does. That's the idiom for 'any of these values'.\n\n"
            "COMMON MISTAKE: leaving out a break by accident and getting this behaviour "
            "when you didn't want it. If a case is meant to fall through, say so with a "
            "comment — otherwise the next reader will assume it's a bug and 'fix' it."
        ),
        "briefing": "Group several values onto one outcome. Write a method dayType(String day) using a switch where SAT and SUN both print WEEKEND, and anything else prints WEEKDAY — by stacking the two case labels, not by writing the body twice. In main(), call dayType(\"SAT\"), dayType(\"SUN\") and dayType(\"MON\").",
        "hints": [
            "Stack the labels with nothing between them: case \"SAT\": case \"SUN\": then one shared body.",
            "Only one break is needed, at the end of the shared body.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void dayType(String day) {
        switch (day) {
            case "SAT":
            case "SUN":
                System.out.println("WEEKEND");
                break;
            default:
                System.out.println("WEEKDAY");
        }
    }

    public static void main(String[] args) {
        dayType("SAT");
        dayType("SUN");
        dayType("MON");
    }
}''',
        "check": make_checker(
            expected_output="WEEKEND\nWEEKEND\nWEEKDAY",
            success_line="Two labels, one body. Fall-through is a bug right up until it's the idiom.",
            missing_hint="I need three lines: WEEKEND, WEEKEND, WEEKDAY.",
            mismatch_hint="Check both SAT and SUN reach the same body, and that MON falls to default.",
            code_requires=['case "SAT"', 'case "SUN"', "switch"],
            code_requires_hint="Stack the two case labels so they share one body — don't write the WEEKEND line twice.",
        ),
    },

    "t6m18": {
        "title": "Statement Or Expression",
        "topic": 6, "topic_name": "Selection",
        "concept": "when to use a ternary and when to use an if",
        "teach": (
            "You met the ternary in Topic 3. Now that you have if, here's how to choose.\n\n"
            "A TERNARY produces a VALUE. Use it when you're picking between two values:\n"
            "String state = power > 50 ? \"HIGH\" : \"LOW\";\n\n"
            "An IF performs an ACTION. Use it when the branches DO different things:\n"
            "if (power > 50) {\n"
            "    System.out.println(\"HIGH\");\n"
            "    logReading(power);\n"
            "} else {\n"
            "    shutDown();\n"
            "}\n\n"
            "WORKED EXAMPLE — the same decision, right tool:\n"
            "int lives = 1;\n"
            "String word = lives == 1 ? \"LIFE\" : \"LIVES\";\n"
            "System.out.println(lives + \" \" + word);\n"
            "-> prints: 1 LIFE\n\n"
            "Doing that with an if would take five lines and a variable declared empty "
            "first. Doing a two-statement branch with a ternary is impossible — a ternary "
            "can only produce a value, not run statements.\n\n"
            "The rule: choosing a VALUE, ternary. Choosing an ACTION, if.\n\n"
            "COMMON MISTAKE: System.out.println(x > 5 ? print(\"a\") : print(\"b\")); — "
            "trying to force actions into a ternary. It doesn't work and it reads badly."
        ),
        "briefing": "Pick the right tool for each job. Write a method describe(int lives) that uses a TERNARY to choose the word LIFE (when lives is 1) or LIVES (otherwise), then prints the count and the word. In main(), call describe(1) then describe(3). Two lines: 1 LIFE, then 3 LIVES.",
        "hints": [
            "String word = lives == 1 ? \"LIFE\" : \"LIVES\"; — one line, choosing a value.",
            "Then one println joining the number and the chosen word with a space.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void describe(int lives) {
        String word = lives == 1 ? "LIFE" : "LIVES";
        System.out.println(lives + " " + word);
    }

    public static void main(String[] args) {
        describe(1);
        describe(3);
    }
}''',
        "wrong": [
            # used an if where the mission asked for a ternary, and hardcoded the output
            '''public class Main {
    public static void describe(int lives) {
        System.out.println(lives + " LIVES");
    }

    public static void main(String[] args) {
        describe(1);
        describe(3);
    }
}''',
        ],
        "check": make_checker(
            expected_output="1 LIFE\n3 LIVES",
            success_line="One line instead of five, because you were choosing a value rather than an action.",
            missing_hint="I need two lines: 1 LIFE and 3 LIVES.",
            mismatch_hint="Check the singular is used only for 1 — the condition is lives == 1.",
            code_requires=["?", ":", "lives == 1"],
            code_requires_hint="This one specifically wants a ternary choosing between the two words.",
        ),
    },

    "t6m19": {
        "title": "Braces Matter",
        "topic": 6, "topic_name": "Selection",
        "concept": "an if without braces controls only ONE statement",
        "teach": (
            "Java lets you omit the braces when an if body is a single statement. This "
            "is legal:\n"
            "if (x > 10)\n"
            "    System.out.println(\"BIG\");\n\n"
            "And it is the source of a genuinely famous class of bug, because the if "
            "controls exactly ONE statement — the indentation is a lie.\n\n"
            "BROKEN:\n"
            "int x = 5;\n"
            "if (x > 10)\n"
            "    System.out.println(\"BIG\");\n"
            "    System.out.println(\"ALSO BIG?\");\n"
            "-> prints: ALSO BIG?\n\n"
            "The second line is indented as though it belongs to the if. It doesn't. It's "
            "an ordinary statement after the if and runs every single time. Java ignores "
            "your indentation completely — only braces group statements.\n\n"
            "THE FIX — always use braces:\n"
            "if (x > 10) {\n"
            "    System.out.println(\"BIG\");\n"
            "    System.out.println(\"ALSO BIG\");\n"
            "}\n"
            "-> prints nothing, correctly\n\n"
            "Real security holes have shipped because of exactly this. Use braces every "
            "time, even for one line — then adding a second line later can't silently "
            "change what your program does.\n\n"
            "COMMON MISTAKE: trusting the indentation when reading someone else's code. "
            "Look for the braces."
        ),
        "briefing": "Fix a brace bug. The editor holds a check that prints its second line unconditionally because the braces are missing. Add braces so BOTH lines are controlled by the condition. With level at 3 and a threshold of 10, the program should print only CHECK COMPLETE.",
        "hints": [
            "Wrap both println lines inside { } belonging to the if.",
            "With level 3, neither ALERT line should print at all — only the final CHECK COMPLETE.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    public static void main(String[] args) {\n"
            "        int level = 3;\n"
            "        // the second line looks like it belongs to the if. It doesn't.\n"
            "        if (level > 10)\n"
            "            System.out.println(\"ALERT: HIGH\");\n"
            "            System.out.println(\"ALERT: ESCALATING\");\n"
            "        System.out.println(\"CHECK COMPLETE\");\n"
            "    }\n"
            "}"
        ),
        "solution": '''public class Main {
    public static void main(String[] args) {
        int level = 3;
        if (level > 10) {
            System.out.println("ALERT: HIGH");
            System.out.println("ALERT: ESCALATING");
        }
        System.out.println("CHECK COMPLETE");
    }
}''',
        "check": make_checker(
            expected_output="CHECK COMPLETE",
            success_line="Only the line that should have printed did. Indentation is for humans; braces are for the compiler.",
            missing_hint="I need exactly one line of output: CHECK COMPLETE.",
            mismatch_hint="If an ALERT line still printed, it's outside the if's braces — both alert lines need to be inside them.",
            code_requires=["if (level > 10) {"],
            code_requires_hint="Add braces to the if so it controls both alert lines.",
        ),
    },

    "t6m20": {
        "title": "BOSS — Grade Classifier",
        "topic": 6, "topic_name": "Selection",
        "concept": "switch, ternary and braces together — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "switch (s.toUpperCase()) { case \"X\": ... break; default: ... }\n"
            "case A: case B: one shared body   — stacked labels for 'any of these'\n"
            "cond ? valueA : valueB            — choosing a VALUE\n"
            "if (cond) { ... }                 — choosing an ACTION; always brace it\n\n"
            "switch on a String compares with .equals() for you, but is case sensitive."
        ),
        "briefing": (
            "Write the classifier from scratch.\n\n"
            "Write a method classify(String grade) that prints the grade, a colon and a "
            "space, then:\n"
            "- PASS for A, B or C (using stacked case labels, not three copies)\n"
            "- FAIL for anything else\n"
            "Match the grade regardless of capitals.\n\n"
            "Then, on the same line, append a space and either DISTINCTION or STANDARD, "
            "chosen with a TERNARY: distinction only when the grade is A.\n\n"
            "In main(), call classify(\"A\"), classify(\"c\") and classify(\"F\").\n\n"
            "Expected output:\n"
            "A: PASS DISTINCTION\n"
            "c: PASS STANDARD\n"
            "F: FAIL STANDARD"
        ),
        "hints": [
            "Normalise once into a variable — String g = grade.toUpperCase(); — then switch on g and use g in the ternary too.",
            "Build the line as grade + \": \" + verdict + \" \" + tier, where verdict comes from the switch and tier from the ternary.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void classify(String grade) {
        String g = grade.toUpperCase();
        String verdict;

        switch (g) {
            case "A":
            case "B":
            case "C":
                verdict = "PASS";
                break;
            default:
                verdict = "FAIL";
        }

        String tier = g.equals("A") ? "DISTINCTION" : "STANDARD";
        System.out.println(grade + ": " + verdict + " " + tier);
    }

    public static void main(String[] args) {
        classify("A");
        classify("c");
        classify("F");
    }
}''',
        "wrong": [
            # no normalising — lowercase c fails
            '''public class Main {
    public static void classify(String grade) {
        String verdict;

        switch (grade) {
            case "A":
            case "B":
            case "C":
                verdict = "PASS";
                break;
            default:
                verdict = "FAIL";
        }

        String tier = grade.equals("A") ? "DISTINCTION" : "STANDARD";
        System.out.println(grade + ": " + verdict + " " + tier);
    }

    public static void main(String[] args) {
        classify("A");
        classify("c");
        classify("F");
    }
}''',
        ],
        "check": make_checker(
            expected_output="A: PASS DISTINCTION\nc: PASS STANDARD\nF: FAIL STANDARD",
            success_line="Stacked cases, a ternary for the tier, and the original capitalisation preserved in the output. Block complete.",
            missing_hint="I need three lines, one per call. Note the printed grade keeps its ORIGINAL capitals.",
            mismatch_hint="If the lowercase c failed, normalise with toUpperCase() before the switch — but print the original grade.",
            code_requires=["public class Main", "switch", "toUpperCase", "?"],
            code_requires_hint="I need a switch with stacked cases on the normalised grade, plus a ternary for the tier.",
        ),
    },
}
