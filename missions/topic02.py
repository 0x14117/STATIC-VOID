"""
Topic 2 — Variables & Constants.

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
        "solution": '''public class Main {
    public static void main(String[] args) {
        int total = 47;
        System.out.println(total);
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println(47);
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        String codename = "Viper";
        System.out.println(codename);
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("Ghost");
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        double price = 19.99;
        boolean inStock = false;
        System.out.println(price);
        System.out.println(inStock);
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("19.99");
        System.out.println("false");
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        final int CLEARANCE_LEVEL = 9;
        System.out.println("CLEARANCE: " + CLEARANCE_LEVEL);
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        int clearanceLevel = 9;
        System.out.println("CLEARANCE: " + clearanceLevel);
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void main(String[] args) {
        String agent = "Viper";
        int clearance = 7;
        double rating = 8.5;
        boolean active = true;
        final int MAX_CLEARANCE = 10;
        System.out.println("AGENT: " + agent);
        System.out.println("CLEARANCE: " + clearance);
        System.out.println("RATING: " + rating);
        System.out.println("ACTIVE: " + active);
        System.out.println("MAX CLEARANCE: " + MAX_CLEARANCE);
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
                "AGENT: Viper\nCLEARANCE: 7\nRATING: 8.5\nACTIVE: true\nMAX CLEARANCE: 10"
            ),
            success_line="Full profile, five types, zero mistakes. Topic Two complete.",
            missing_hint="Your profile doesn't match, or it didn't compile. Check the class/main structure and each field.",
            mismatch_hint="Check exact wording and order of all five lines, and that MAX_CLEARANCE is declared final.",
            code_requires=["public class Main", "public static void main", "String ", "int ", "double ", "boolean ", "final "],
            code_requires_hint="I need the full structure plus all five types: String, int, double, boolean, and final.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 2 — the life cycle of a variable, and the rest of the types.
    # ---------------------------------------------------------------------

    "t2m6": {
        "title": "Declare Then Fill",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "declaration and initialisation are two separate steps",
        "teach": (
            "int score = 100; is actually two things happening at once:\n\n"
            "DECLARATION      int score;        reserve a box, label it, fix its type\n"
            "INITIALISATION   score = 100;      put the first value in it\n\n"
            "You can split them. This is useful when the value isn't known yet at the "
            "point you want the variable to exist.\n\n"
            "WORKED EXAMPLE:\n"
            "int depth;              // the box exists, but is empty\n"
            "depth = 250;            // now it holds 250\n"
            "System.out.println(depth);\n"
            "-> prints: 250\n\n"
            "Note the second line has NO type on it. The type is set once, at "
            "declaration; repeating it would be declaring a second variable of the same "
            "name, which is an error.\n\n"
            "COMMON MISTAKE: trying to read a local variable before assigning anything "
            "to it. Java refuses to compile that — 'variable depth might not have been "
            "initialized'. An empty box isn't zero; it's nothing."
        ),
        "briefing": "Reserve the slot first, then fill it. Declare an int called payload on one line WITHOUT a value, assign it 88 on a second line, then print it.",
        "hints": [
            "Line one is just the type and the name: int payload;",
            "Line two assigns with no type in front of it: payload = 88;",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int payload;
        payload = 88;
        System.out.println(payload);
    }
}''',
        "wrong": [
            # printed a literal, never used a variable at all
            '''public class Main {
    public static void main(String[] args) {
        System.out.println(88);
    }
}''',
        ],
        "check": make_checker(
            expected_output="88",
            success_line="Declared empty, filled later. Two steps, one variable.",
            missing_hint="I need 88 printed from a variable called payload.",
            mismatch_hint="Check the value assigned is 88.",
            code_requires=["int payload;", "payload = 88"],
            code_requires_hint="Split it properly: 'int payload;' on its own line, then 'payload = 88;' on the next.",
        ),
    },

    "t2m7": {
        "title": "Overwriting History",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "reassignment — a variable holds one value at a time",
        "teach": (
            "A variable holds exactly one value at a time. Assign a new one and the old "
            "one is gone — not stored, not recoverable, just overwritten.\n\n"
            "WORKED EXAMPLE:\n"
            "int fuel = 50;\n"
            "System.out.println(fuel);    // 50\n"
            "fuel = 20;\n"
            "System.out.println(fuel);    // 20\n"
            "-> prints:\n"
            "50\n"
            "20\n\n"
            "The order matters enormously. Each println() shows whatever was in the box "
            "at the moment that line ran — printing is a snapshot, not a live link. "
            "Moving both println() calls to the end would print 20 twice.\n\n"
            "COMMON MISTAKE: expecting a printed value to update later. Once "
            "System.out.println(fuel) has run, that output is fixed text — changing fuel "
            "afterwards does nothing to what's already on screen."
        ),
        "briefing": "Track a falling fuel reading. Declare an int fuel set to 50 and print it, then reassign it to 20 and print it again. Two lines out: 50 then 20.",
        "hints": [
            "The second assignment has no type in front: fuel = 20;",
            "Print BETWEEN the two assignments — that's the only way to see the old value at all.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int fuel = 50;
        System.out.println(fuel);
        fuel = 20;
        System.out.println(fuel);
    }
}''',
        "wrong": [
            # both prints after both assignments — 20 twice
            '''public class Main {
    public static void main(String[] args) {
        int fuel = 50;
        fuel = 20;
        System.out.println(fuel);
        System.out.println(fuel);
    }
}''',
        ],
        "check": make_checker(
            expected_output="50\n20",
            success_line="50 then 20. Printing is a snapshot of that exact moment, not a live feed.",
            missing_hint="I need two lines: 50, then 20.",
            mismatch_hint="If you got 20 twice, both prints happened after the reassignment — the first one has to come before it.",
            code_requires=["fuel"],
            code_requires_hint="Use a single variable called fuel and reassign it, rather than printing two literals.",
        ),
    },

    "t2m8": {
        "title": "Character Slot",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "char variables",
        "teach": (
            "char is the type for a single character, and its values are written in "
            "SINGLE quotes.\n\n"
            "WORKED EXAMPLE:\n"
            "char grade = 'B';\n"
            "System.out.println(\"GRADE: \" + grade);\n"
            "-> prints: GRADE: B\n\n"
            "char holds exactly one character — 'B', '7', '?', a space ' '. Note that "
            "'7' as a char is the SYMBOL seven, not the number seven; you can't do "
            "arithmetic with it the way you'd expect.\n\n"
            "Why have char at all when String exists? Because a lot of real work is "
            "per-character: reading a grade, a menu key, a single move in a game. "
            "A String is a sequence of chars, and you'll pull individual chars out of "
            "Strings constantly once you reach Topic 8.\n\n"
            "COMMON MISTAKE: char section = \"A\"; — double quotes make a String, and "
            "Java will reject the assignment outright: incompatible types."
        ),
        "briefing": "Log the sector designation as a single character. Declare a char called sector holding D, and print SECTOR: followed by it.",
        "hints": [
            "char sector = 'D';  — single quotes, one character.",
            "System.out.println(\"SECTOR: \" + sector);",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        char sector = 'D';
        System.out.println("SECTOR: " + sector);
    }
}''',
        "wrong": [
            # String, not char
            '''public class Main {
    public static void main(String[] args) {
        String sector = "D";
        System.out.println("SECTOR: " + sector);
    }
}''',
        ],
        "check": make_checker(
            expected_output="SECTOR: D",
            success_line="One character, one char. Single quotes are what make it a char rather than a String.",
            missing_hint="I need the line SECTOR: D, with D coming from a char variable.",
            mismatch_hint="Check the label and spacing — 'SECTOR: ' then the character.",
            code_requires=["char sector", "'D'"],
            code_requires_hint="This one specifically wants a char: char sector = 'D'; with single quotes.",
        ),
    },

    "t2m9": {
        "title": "Beyond Two Billion",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "long for values int can't hold",
        "teach": (
            "int has a ceiling: 2,147,483,647. Go past it and the value doesn't just get "
            "big — it wraps around to a negative number, silently.\n\n"
            "long is the bigger whole-number type, good to about 9.2 quintillion. Its "
            "literals need an L on the end:\n\n"
            "WORKED EXAMPLE:\n"
            "long distance = 5000000000L;\n"
            "System.out.println(distance);\n"
            "-> prints: 5000000000\n\n"
            "Without that L, Java reads 5000000000 as an int literal first, decides it "
            "doesn't fit, and refuses to compile — 'integer number too large' — before "
            "it ever gets as far as noticing the variable is a long. The L is you "
            "telling the compiler which kind of number you're writing.\n\n"
            "COMMON MISTAKE: assuming the L is optional for small values. long x = 5; "
            "is fine (5 fits in an int and widens automatically), which makes it easy to "
            "forget the rule until the day the number is genuinely big."
        ),
        "briefing": "The distance readout is far past what an int can hold. Declare a long called distance holding 5000000000 and print DISTANCE: followed by it.",
        "hints": [
            "The type is long, and the literal needs an L on the end: 5000000000L",
            "long distance = 5000000000L; then System.out.println(\"DISTANCE: \" + distance);",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        long distance = 5000000000L;
        System.out.println("DISTANCE: " + distance);
    }
}''',
        "check": make_checker(
            expected_output="DISTANCE: 5000000000",
            success_line="Past the int ceiling without wrapping. That L is what tells the compiler which kind of literal you meant.",
            missing_hint="I need DISTANCE: 5000000000, held in a long.",
            mismatch_hint="Check the label and the value — and that the literal ends in L.",
            code_requires=["long distance", "5000000000L"],
            code_requires_hint="Declare it as a long, and write the literal with the L suffix: 5000000000L",
        ),
    },

    "t2m10": {
        "title": "BOSS — Vessel Registry",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "every type so far in one record — no starter code",
        "teach": (
            "No starter code. RECAP of the types you now have:\n\n"
            "int     whole numbers          int crew = 8;\n"
            "long    very large whole nums  long range = 5000000000L;\n"
            "double  decimals               double mass = 7.5;\n"
            "boolean true or false          boolean armed = true;\n"
            "char    one character          char classLetter = 'A';\n"
            "String  text                   String name = \"VOID RUNNER\";\n"
            "final   locks a value          final int MAX = 10;\n\n"
            "Declaration and initialisation can be one step or two, and any non-final "
            "variable can be reassigned afterwards."
        ),
        "briefing": (
            "Build the vessel registry from scratch. Declare one variable of each type — "
            "String name VOID RUNNER, char classLetter V, int crew 8, long range "
            "5000000000, double mass 7.5, boolean armed true — then print exactly:\n\n"
            "NAME: VOID RUNNER\n"
            "CLASS: V\n"
            "CREW: 8\n"
            "RANGE: 5000000000\n"
            "MASS: 7.5\n"
            "ARMED: true\n\n"
            "Every value must come from a variable, not be typed into the string."
        ),
        "hints": [
            "Declare all six variables first, then do all six println() calls — it reads better and it's easier to debug.",
            "Watch the details: 'V' in single quotes for the char, the L suffix on the long, and true with no quotes for the boolean.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void main(String[] args) {
        String name = "VOID RUNNER";
        char classLetter = 'V';
        int crew = 8;
        long range = 5000000000L;
        double mass = 7.5;
        boolean armed = true;

        System.out.println("NAME: " + name);
        System.out.println("CLASS: " + classLetter);
        System.out.println("CREW: " + crew);
        System.out.println("RANGE: " + range);
        System.out.println("MASS: " + mass);
        System.out.println("ARMED: " + armed);
    }
}''',
        "wrong": [
            # everything hardcoded into the strings, no variables at all
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("NAME: VOID RUNNER");
        System.out.println("CLASS: V");
        System.out.println("CREW: 8");
        System.out.println("RANGE: 5000000000");
        System.out.println("MASS: 7.5");
        System.out.println("ARMED: true");
    }
}''',
        ],
        "check": make_checker(
            expected_output=(
                "NAME: VOID RUNNER\nCLASS: V\nCREW: 8\n"
                "RANGE: 5000000000\nMASS: 7.5\nARMED: true"
            ),
            success_line="Six types, six variables, one clean registry. Block complete.",
            missing_hint="The registry doesn't match, or it didn't compile. Check each declaration's type and each label.",
            mismatch_hint="Some lines are right. Check the char's single quotes, the long's L, and that MASS prints as 7.5.",
            code_requires=["String name", "char classLetter", "int crew", "long range",
                           "double mass", "boolean armed"],
            code_requires_hint="I need all six variables declared with those exact types and names — the values must come from variables, not be typed into the strings.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 3 — naming, deriving values, and what copying actually copies.
    # ---------------------------------------------------------------------

    "t2m11": {
        "title": "Naming Conventions",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "camelCase and names that describe the value",
        "teach": (
            "Java has conventions for names. They aren't enforced by the compiler — "
            "int X_1 = 5; compiles fine — but every Java codebase, marking scheme and "
            "colleague expects them.\n\n"
            "variables and methods   camelCase, starting lowercase   totalScore\n"
            "classes                 UpperCamelCase                  MissionReport\n"
            "constants (final)       UPPER_SNAKE_CASE                MAX_CLEARANCE\n\n"
            "The bigger rule: the name says WHAT THE VALUE IS. Compare\n"
            "  int x = 172;          // 172 what?\n"
            "  int heightInCm = 172; // oh.\n\n"
            "WORKED EXAMPLE:\n"
            "int daysRemaining = 14;\n"
            "System.out.println(\"DAYS: \" + daysRemaining);\n"
            "-> prints: DAYS: 14\n\n"
            "Rules the compiler DOES enforce: no spaces, can't start with a digit, "
            "can't be a Java keyword (int, class, new).\n\n"
            "COMMON MISTAKE: single-letter names everywhere. They're fine for a loop "
            "counter and terrible for anything else — in three weeks, 'n' means nothing "
            "to you either."
        ),
        "briefing": "Two badly named variables are in your editor. Rename them to proper camelCase names that describe what they hold — the crew count and the fuel percentage — without changing the values or the output. It must still print CREW: 6 then FUEL: 82.",
        "hints": [
            "camelCase means no underscore and no capital first letter: crewCount, fuelPercent.",
            "Rename the variable everywhere it appears — the declaration AND the println that uses it.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    public static void main(String[] args) {\n"
            "        int x = 6;\n"
            "        int Y_2 = 82;\n"
            "        System.out.println(\"CREW: \" + x);\n"
            "        System.out.println(\"FUEL: \" + Y_2);\n"
            "    }\n"
            "}"
        ),
        "solution": '''public class Main {
    public static void main(String[] args) {
        int crewCount = 6;
        int fuelPercent = 82;
        System.out.println("CREW: " + crewCount);
        System.out.println("FUEL: " + fuelPercent);
    }
}''',
        "check": make_checker(
            expected_output="CREW: 6\nFUEL: 82",
            success_line="Named for what they hold. The compiler didn't care — the next person to read this will.",
            missing_hint="I need CREW: 6 and FUEL: 82 still printing after the rename.",
            mismatch_hint="Check both lines still print the same values you started with.",
            code_requires=["crewCount", "fuelPercent"],
            code_requires_hint="Rename them to crewCount and fuelPercent — proper camelCase, describing what they hold.",
        ),
    },

    "t2m12": {
        "title": "Derived Values",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "initialising one variable from others",
        "teach": (
            "A variable's starting value doesn't have to be a literal. It can be an "
            "expression — including one built from variables you've already declared.\n\n"
            "WORKED EXAMPLE:\n"
            "int lengthM = 12;\n"
            "int widthM = 5;\n"
            "int areaM2 = lengthM * widthM;\n"
            "System.out.println(\"AREA: \" + areaM2);\n"
            "-> prints: AREA: 60\n\n"
            "Java evaluates the right-hand side FIRST, then stores the result. So areaM2 "
            "holds 60 — a plain number. It is not a live formula: change lengthM "
            "afterwards and areaM2 stays 60, because the multiplication already happened.\n\n"
            "This is the single most important thing to understand about assignment, and "
            "it trips up anyone who's used a spreadsheet, where a cell formula DOES "
            "update when its inputs change. Java assignment is a one-time snapshot.\n\n"
            "COMMON MISTAKE: using a variable above the line that declares it. Java "
            "reads top to bottom — it must already exist."
        ),
        "briefing": "Work out the squad's total supplies. Declare int squadSize as 7 and int rationsEach as 4, then declare a third variable totalRations initialised from those two multiplied together. Print TOTAL RATIONS: followed by it.",
        "hints": [
            "The third declaration uses the first two by name: int totalRations = squadSize * rationsEach;",
            "Declare it AFTER both of the variables it depends on — Java reads top to bottom.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int squadSize = 7;
        int rationsEach = 4;
        int totalRations = squadSize * rationsEach;
        System.out.println("TOTAL RATIONS: " + totalRations);
    }
}''',
        "wrong": [
            # typed the answer in rather than deriving it
            '''public class Main {
    public static void main(String[] args) {
        int totalRations = 28;
        System.out.println("TOTAL RATIONS: " + totalRations);
    }
}''',
        ],
        "check": make_checker(
            expected_output="TOTAL RATIONS: 28",
            success_line="Derived, not typed. The multiplication happened once, at the moment of assignment.",
            missing_hint="I need TOTAL RATIONS: 28, calculated from the two variables.",
            mismatch_hint="Check the arithmetic — 7 times 4 — and the label's spelling and spacing.",
            code_requires=["squadSize", "rationsEach", "squadSize * rationsEach"],
            code_requires_hint="The total has to be calculated from the other two: int totalRations = squadSize * rationsEach;",
        ),
    },

    "t2m13": {
        "title": "Building The Sentence",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "concatenating variables into a String variable",
        "teach": (
            "You've joined variables into text inside println(). You can do exactly the "
            "same thing to build a String VARIABLE, then print that.\n\n"
            "WORKED EXAMPLE:\n"
            "String city = \"Prague\";\n"
            "int year = 1968;\n"
            "String caption = city + \", \" + year;\n"
            "System.out.println(caption);\n"
            "-> prints: Prague, 1968\n\n"
            "caption is now an ordinary String holding \"Prague, 1968\" — the joining "
            "already happened. Splitting the building from the printing like this is "
            "worth the extra line whenever the text is long, or gets used twice.\n\n"
            "Notice the number became text automatically when joined to a String. That's "
            "the same rule as before: + with a String on one side means join, not add.\n\n"
            "COMMON MISTAKE: expecting caption to change if city changes afterwards. It "
            "won't — the value was built once and stored, same as any other assignment."
        ),
        "briefing": "Build the callsign line as a String variable before printing it. From String unit set to RAVEN and int number set to 3, build a String called callsign holding RAVEN-3 (with a hyphen between them), then print it.",
        "hints": [
            "String callsign = unit + \"-\" + number; — the hyphen is a string in the middle.",
            "Then just System.out.println(callsign); on its own line.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        String unit = "RAVEN";
        int number = 3;
        String callsign = unit + "-" + number;
        System.out.println(callsign);
    }
}''',
        "wrong": [
            # built nothing, just printed the finished text
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("RAVEN-3");
    }
}''',
        ],
        "check": make_checker(
            expected_output="RAVEN-3",
            success_line="Built once into a variable, then printed. Useful the moment that text gets used twice.",
            missing_hint="I need RAVEN-3 printed from a String variable called callsign.",
            mismatch_hint="Check the hyphen has no spaces around it in the output — RAVEN-3, not RAVEN - 3.",
            code_requires=["String callsign", "unit", "number"],
            code_requires_hint="Build a String variable called callsign from unit and number, then print that variable.",
        ),
    },

    "t2m14": {
        "title": "Copy, Not Link",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "assigning one variable to another copies the value",
        "teach": (
            "int b = a; copies the VALUE out of a and into b. It does not connect them. "
            "They are two separate boxes that happen to contain the same number.\n\n"
            "WORKED EXAMPLE:\n"
            "int original = 10;\n"
            "int backup = original;     // backup now holds 10\n"
            "original = 99;             // only original changes\n"
            "System.out.println(original);   // 99\n"
            "System.out.println(backup);     // 10\n"
            "-> prints:\n"
            "99\n"
            "10\n\n"
            "This is why backup still says 10: the copy was taken at that moment, and "
            "nothing links the two afterwards.\n\n"
            "COMMON MISTAKE: expecting backup to follow original. Nothing in Java's "
            "primitive types works that way. (Objects behave differently — two names CAN "
            "refer to one shared object — which is exactly why this distinction is worth "
            "being solid on before Topic 9.)"
        ),
        "briefing": "Take a snapshot before overwriting. Declare int reading as 40, copy it into a second int called snapshot, then change reading to 5. Print READING: 5 then SNAPSHOT: 40 to prove the copy survived.",
        "hints": [
            "int snapshot = reading; takes the copy — do it BEFORE you change reading.",
            "Then reading = 5; affects only reading. Print both at the end.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int reading = 40;
        int snapshot = reading;
        reading = 5;
        System.out.println("READING: " + reading);
        System.out.println("SNAPSHOT: " + snapshot);
    }
}''',
        "wrong": [
            # copied after the change, so the snapshot captures the wrong value
            '''public class Main {
    public static void main(String[] args) {
        int reading = 40;
        reading = 5;
        int snapshot = reading;
        System.out.println("READING: " + reading);
        System.out.println("SNAPSHOT: " + snapshot);
    }
}''',
        ],
        "check": make_checker(
            expected_output="READING: 5\nSNAPSHOT: 40",
            success_line="The copy held. Two separate boxes — changing one never touches the other.",
            missing_hint="I need both lines: READING: 5 and SNAPSHOT: 40.",
            mismatch_hint="If SNAPSHOT says 5, the copy was taken after the change — move it above the reading = 5 line.",
            code_requires=["snapshot = reading"],
            code_requires_hint="Copy one variable into the other — int snapshot = reading; — rather than assigning 40 again by hand.",
        ),
    },

    "t2m15": {
        "title": "BOSS — Transfer Log",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "reassignment, copying and derived values together — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "int b = a;          copies a's value — no link afterwards\n"
            "b = 99;             overwrites; the old value is gone\n"
            "int c = a * b;      evaluated once, right now, and stored\n"
            "camelCase names that say what the value is\n\n"
            "The thread running through all of it: assignment happens at a MOMENT. "
            "Order of statements is the whole story."
        ),
        "briefing": (
            "Write the transfer log from scratch. A vault starts with 500 credits and "
            "a transfer of 120 is made out of it.\n\n"
            "Declare int vaultBalance as 500 and int transferAmount as 120. Copy the "
            "starting balance into int openingBalance. Then set vaultBalance to what's "
            "left after the transfer, calculated in code. Print:\n\n"
            "OPENING: 500\n"
            "TRANSFER: 120\n"
            "CLOSING: 380\n\n"
            "The 380 must be calculated, and OPENING must still show 500 afterwards."
        ),
        "hints": [
            "Take the openingBalance copy BEFORE you change vaultBalance — afterwards is too late.",
            "vaultBalance = vaultBalance - transferAmount; reads the old value, subtracts, and stores the result back.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void main(String[] args) {
        int vaultBalance = 500;
        int transferAmount = 120;
        int openingBalance = vaultBalance;

        vaultBalance = vaultBalance - transferAmount;

        System.out.println("OPENING: " + openingBalance);
        System.out.println("TRANSFER: " + transferAmount);
        System.out.println("CLOSING: " + vaultBalance);
    }
}''',
        "wrong": [
            # closing value typed in rather than calculated
            '''public class Main {
    public static void main(String[] args) {
        int openingBalance = 500;
        int transferAmount = 120;
        System.out.println("OPENING: " + openingBalance);
        System.out.println("TRANSFER: " + transferAmount);
        System.out.println("CLOSING: 380");
    }
}''',
        ],
        "check": make_checker(
            expected_output="OPENING: 500\nTRANSFER: 120\nCLOSING: 380",
            success_line="Snapshot taken, balance updated, everything derived. Block complete.",
            missing_hint="The log doesn't match, or it didn't compile. Check the three labels and the order of your statements.",
            mismatch_hint="If OPENING shows 380, the copy was taken after the subtraction rather than before it.",
            code_requires=["public class Main", "vaultBalance", "transferAmount", "openingBalance",
                           "vaultBalance - transferAmount"],
            code_requires_hint="I need all three variables, and the closing balance worked out as vaultBalance - transferAmount.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 4 — the rest of the numeric types, booleans from comparisons,
    # and where a variable stops existing.
    # ---------------------------------------------------------------------

    "t2m16": {
        "title": "Float Or Double",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "float vs double, and the f suffix",
        "teach": (
            "Java has two decimal types. double is the default and the one you should "
            "reach for; float is half the size and half the precision.\n\n"
            "double mass = 7.5;      // fine — decimal literals ARE doubles\n"
            "float mass = 7.5;       // compile error!\n"
            "float mass = 7.5f;      // fine\n\n"
            "Why the error? Because 7.5 written plainly is a double literal, and a "
            "double doesn't fit in a float — Java refuses to narrow it silently and "
            "lose precision. The f suffix says 'I meant a float literal'.\n\n"
            "WORKED EXAMPLE:\n"
            "float temperature = 21.5f;\n"
            "double pressure = 101.3;\n"
            "System.out.println(temperature + \" / \" + pressure);\n"
            "-> prints: 21.5 / 101.3\n\n"
            "COMMON MISTAKE: using float to 'save memory'. On any modern machine it "
            "saves nothing worth having and costs you precision. Use double unless "
            "something external — a library, a file format, a lecturer — demands float. "
            "The reason to know the f rule is that you'll meet it in other people's code."
        ),
        "briefing": "Log both sensor readings with the right suffixes. Declare a float called temperature holding 21.5 and a double called pressure holding 101.3, then print TEMP: 21.5 and PRESSURE: 101.3 on two lines.",
        "hints": [
            "The float literal needs an f on the end — 21.5f — or it won't compile.",
            "The double needs no suffix at all: double pressure = 101.3;",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        float temperature = 21.5f;
        double pressure = 101.3;
        System.out.println("TEMP: " + temperature);
        System.out.println("PRESSURE: " + pressure);
    }
}''',
        "check": make_checker(
            expected_output="TEMP: 21.5\nPRESSURE: 101.3",
            success_line="Both types, both suffixes right. Reach for double by default — now you know why float exists.",
            missing_hint="I need both lines: TEMP: 21.5 and PRESSURE: 101.3.",
            mismatch_hint="Check the labels, and that the float literal ends in f.",
            code_requires=["float temperature", "21.5f", "double pressure"],
            code_requires_hint="temperature must be a float with the f suffix (21.5f), pressure a plain double.",
        ),
    },

    "t2m17": {
        "title": "Smaller Boxes",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "byte and short, and why the ranges matter",
        "teach": (
            "Java has four whole-number types, and they differ only in how much they "
            "can hold:\n\n"
            "byte    -128 to 127\n"
            "short   -32,768 to 32,767\n"
            "int     about -2.1 billion to 2.1 billion   <- the default\n"
            "long    about -9.2 quintillion to 9.2 quintillion\n\n"
            "WORKED EXAMPLE:\n"
            "byte errorCode = 12;\n"
            "short elevation = 8848;\n"
            "System.out.println(errorCode + \" / \" + elevation);\n"
            "-> prints: 12 / 8848\n\n"
            "Put a value outside the range and it's a compile error, not a warning:\n"
            "byte tooBig = 200;   // error: possible lossy conversion from int to byte\n\n"
            "In practice you'll use int for nearly everything. byte and short matter "
            "when you're reading binary file formats or network data, where the size of "
            "each field is fixed by something outside your program — and they're on the "
            "syllabus because knowing a type has LIMITS is the point, not memorising the "
            "numbers.\n\n"
            "COMMON MISTAKE: thinking the ranges are arbitrary. A byte is 8 bits: 2^8 = "
            "256 possible values, split either side of zero. Every range on that list is "
            "just a power of two."
        ),
        "briefing": "Two readings, each in the smallest type that fits. Declare a byte called errorCode holding 12 and a short called elevation holding 8848. Print ERROR: 12 then ELEVATION: 8848.",
        "hints": [
            "byte errorCode = 12; — 12 is comfortably inside the -128 to 127 range.",
            "short elevation = 8848; — inside the short range, but far too big for a byte.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        byte errorCode = 12;
        short elevation = 8848;
        System.out.println("ERROR: " + errorCode);
        System.out.println("ELEVATION: " + elevation);
    }
}''',
        "wrong": [
            # used int for both — works, but isn't what was asked
            '''public class Main {
    public static void main(String[] args) {
        int errorCode = 12;
        int elevation = 8848;
        System.out.println("ERROR: " + errorCode);
        System.out.println("ELEVATION: " + elevation);
    }
}''',
        ],
        "check": make_checker(
            expected_output="ERROR: 12\nELEVATION: 8848",
            success_line="Right value, right sized box. Every type has a ceiling — that's the lesson, not the exact numbers.",
            missing_hint="I need both lines: ERROR: 12 and ELEVATION: 8848.",
            mismatch_hint="Check the two labels and their values.",
            code_requires=["byte errorCode", "short elevation"],
            code_requires_hint="This one wants the specific types: byte for errorCode, short for elevation.",
        ),
    },

    "t2m18": {
        "title": "Storing A Question",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "a boolean variable holding the result of a comparison",
        "teach": (
            "A comparison like 10 > 3 doesn't just belong inside an if statement. It IS "
            "a value — true or false — and you can store it in a boolean variable like "
            "any other value.\n\n"
            "WORKED EXAMPLE:\n"
            "int altitude = 900;\n"
            "boolean isHigh = altitude > 500;\n"
            "System.out.println(\"HIGH: \" + isHigh);\n"
            "-> prints: HIGH: true\n\n"
            "Java works out altitude > 500, gets true, and stores that. isHigh is now an "
            "ordinary boolean — and, as ever, it's a snapshot: change altitude afterwards "
            "and isHigh keeps whatever it was given.\n\n"
            "The comparison operators: > < >= <= == (equal to) != (not equal to).\n\n"
            "Naming convention: booleans usually read like a yes/no question — isHigh, "
            "hasClearance, isEmpty. It makes the code that uses them read like English.\n\n"
            "COMMON MISTAKE: writing boolean isHigh = \"true\"; — quotes make that the "
            "TEXT 'true', which is a String, and Java rejects it. true and false are "
            "keywords, never quoted."
        ),
        "briefing": "Precompute the altitude check. Declare int altitude as 900, then a boolean called isHigh holding the result of comparing altitude to 500 with >. Print ALTITUDE: 900 then HIGH: true.",
        "hints": [
            "boolean isHigh = altitude > 500; — the comparison itself is the value, no quotes anywhere.",
            "Then two println() calls, one for each labelled line.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int altitude = 900;
        boolean isHigh = altitude > 500;
        System.out.println("ALTITUDE: " + altitude);
        System.out.println("HIGH: " + isHigh);
    }
}''',
        "wrong": [
            # hardcoded true instead of computing the comparison
            '''public class Main {
    public static void main(String[] args) {
        int altitude = 900;
        boolean isHigh = true;
        System.out.println("ALTITUDE: " + altitude);
        System.out.println("HIGH: " + isHigh);
    }
}''',
        ],
        "check": make_checker(
            expected_output="ALTITUDE: 900\nHIGH: true",
            success_line="A comparison is a value. That's the idea the whole of Topic 6 is built on.",
            missing_hint="I need both lines: ALTITUDE: 900 and HIGH: true.",
            mismatch_hint="Check both labels, and that HIGH prints true (no quotes around it anywhere).",
            code_requires=["altitude > 500"],
            code_requires_hint="The boolean has to come from the comparison itself — boolean isHigh = altitude > 500; — not from typing true.",
        ),
    },

    "t2m19": {
        "title": "Out Of Scope",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "scope — where a variable exists and where it doesn't",
        "teach": (
            "A variable exists only inside the { } block it was declared in. Leave that "
            "block and it's gone — not empty, GONE. That region is its SCOPE.\n\n"
            "WORKED EXAMPLE:\n"
            "public static void main(String[] args) {\n"
            "    int outer = 1;\n"
            "    {\n"
            "        int inner = 2;\n"
            "        System.out.println(outer);   // fine — outer is still in scope\n"
            "        System.out.println(inner);   // fine\n"
            "    }\n"
            "    System.out.println(inner);       // ERROR: cannot find symbol\n"
            "}\n\n"
            "The inner block can see outwards; the outer block cannot see inwards. Once "
            "the } closes, inner's name is no longer a thing that exists.\n\n"
            "This is why 'cannot find symbol' sometimes points at a variable you can "
            "plainly see three lines above — it's there, but it's in a block you've "
            "already left. It matters much more once you hit if statements and loops in "
            "Topics 6 and 7, which are made of blocks.\n\n"
            "COMMON MISTAKE: declaring a variable inside a block and expecting to use "
            "the result afterwards. Declare it OUTSIDE the block, and only assign to it "
            "inside — then it survives."
        ),
        "briefing": "The editor has a program that won't compile: it declares a variable inside a block, then tries to print it afterwards. Fix it by declaring result BEFORE the block and only assigning to it inside, so it prints RESULT: 42.",
        "hints": [
            "Move just the declaration — int result; — above the opening brace of the block.",
            "Inside the block, drop the type: result = 42; assigns to the variable that already exists outside.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    public static void main(String[] args) {\n"
            "        {\n"
            "            int result = 42;\n"
            "        }\n"
            "        // this line can't see result — fix the scope above\n"
            "        System.out.println(\"RESULT: \" + result);\n"
            "    }\n"
            "}"
        ),
        "solution": '''public class Main {
    public static void main(String[] args) {
        int result;
        {
            result = 42;
        }
        System.out.println("RESULT: " + result);
    }
}''',
        "check": make_checker(
            expected_output="RESULT: 42",
            success_line="Declared outside, assigned inside. That's the pattern for getting a value out of a block.",
            missing_hint="Still not compiling — the variable needs to be DECLARED outside the block for the println to see it.",
            mismatch_hint="Check the label: RESULT: then the value.",
            code_requires=["int result"],
            code_requires_hint="Declare int result outside the block, then assign to it inside with no type in front.",
        ),
    },

    "t2m20": {
        "title": "BOSS — Sensor Array",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "every numeric type, a derived boolean, and scope — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "float x = 1.5f;              the f suffix is mandatory\n"
            "byte / short / int / long    same idea, four ceilings\n"
            "boolean ok = value > 10;     a comparison IS a value\n"
            "{ }                          a block; variables inside it die at the }\n\n"
            "Declare outside, assign inside, when you need a value to outlive a block."
        ),
        "briefing": (
            "Build the sensor array readout from scratch.\n\n"
            "Declare: byte arrayId holding 4, short samplesTaken holding 12000, float "
            "signalStrength holding 76.5, and long totalBytes holding 3000000000.\n\n"
            "Then declare a boolean called isStrong holding the result of comparing "
            "signalStrength to 50 with >.\n\n"
            "Print exactly:\n"
            "ARRAY: 4\n"
            "SAMPLES: 12000\n"
            "SIGNAL: 76.5\n"
            "BYTES: 3000000000\n"
            "STRONG: true"
        ),
        "hints": [
            "Two suffixes to remember: f on the float literal, L on the long literal. Miss either and it won't compile.",
            "isStrong comes from the comparison signalStrength > 50 — don't type true yourself.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void main(String[] args) {
        byte arrayId = 4;
        short samplesTaken = 12000;
        float signalStrength = 76.5f;
        long totalBytes = 3000000000L;
        boolean isStrong = signalStrength > 50;

        System.out.println("ARRAY: " + arrayId);
        System.out.println("SAMPLES: " + samplesTaken);
        System.out.println("SIGNAL: " + signalStrength);
        System.out.println("BYTES: " + totalBytes);
        System.out.println("STRONG: " + isStrong);
    }
}''',
        "wrong": [
            # all ints and a hardcoded boolean
            '''public class Main {
    public static void main(String[] args) {
        int arrayId = 4;
        int samplesTaken = 12000;
        double signalStrength = 76.5;
        long totalBytes = 3000000000L;
        boolean isStrong = true;

        System.out.println("ARRAY: " + arrayId);
        System.out.println("SAMPLES: " + samplesTaken);
        System.out.println("SIGNAL: " + signalStrength);
        System.out.println("BYTES: " + totalBytes);
        System.out.println("STRONG: " + isStrong);
    }
}''',
        ],
        "check": make_checker(
            expected_output=(
                "ARRAY: 4\nSAMPLES: 12000\nSIGNAL: 76.5\n"
                "BYTES: 3000000000\nSTRONG: true"
            ),
            success_line="Five types, correct suffixes, a derived boolean. Block complete.",
            missing_hint="The readout doesn't match, or it didn't compile. Check the f and L suffixes first — they're the usual culprits.",
            mismatch_hint="Some lines are right. Check each label and that SIGNAL prints as 76.5.",
            code_requires=["byte arrayId", "short samplesTaken", "float signalStrength",
                           "76.5f", "long totalBytes", "3000000000L", "signalStrength > 50"],
            code_requires_hint="I need those exact types with their suffixes, and isStrong derived from signalStrength > 50.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 5 — constants done properly, swapping, var, and the
    # uninitialised-local error. Final boss closes the topic.
    # ---------------------------------------------------------------------

    "t2m21": {
        "title": "Constants By Convention",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "final + UPPER_SNAKE_CASE, and why magic numbers are a problem",
        "teach": (
            "You've met final — it locks a value so reassignment won't compile. The "
            "other half of the practice is the NAME: constants are written in "
            "UPPER_SNAKE_CASE, so that anyone reading the code can see at a glance that "
            "this value never moves.\n\n"
            "WORKED EXAMPLE:\n"
            "final double VAT_RATE = 0.2;\n"
            "final int MAX_PASSENGERS = 4;\n"
            "double price = 50;\n"
            "System.out.println(\"TAX: \" + (price * VAT_RATE));\n"
            "-> prints: TAX: 10.0\n\n"
            "The real argument for constants isn't safety, it's this: a bare 0.2 sitting "
            "in the middle of a calculation is a MAGIC NUMBER. Six months later nobody "
            "knows if it's VAT, a discount, or a typo — and when the rate changes you "
            "have to find every copy of it. Name it once at the top, use the name "
            "everywhere, change it in one place.\n\n"
            "COMMON MISTAKE: making something final that genuinely needs to change, then "
            "fighting the compiler. final is for values fixed for the whole run — a "
            "rate, a limit, a conversion factor."
        ),
        "briefing": "Replace the magic numbers. Declare a final double named FUEL_RATE holding 2.5 and a final int named TANK_COUNT holding 4, then print CAPACITY: followed by the two multiplied together.",
        "hints": [
            "final double FUEL_RATE = 2.5; — the word final first, then the type, then the SHOUTY name.",
            "System.out.println(\"CAPACITY: \" + (FUEL_RATE * TANK_COUNT)); — brackets round the maths.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        final double FUEL_RATE = 2.5;
        final int TANK_COUNT = 4;
        System.out.println("CAPACITY: " + (FUEL_RATE * TANK_COUNT));
    }
}''',
        "wrong": [
            # magic numbers, no constants
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("CAPACITY: " + (2.5 * 4));
    }
}''',
        ],
        "check": make_checker(
            expected_output="CAPACITY: 10.0",
            success_line="Named, locked, and used. One place to change it when the rate moves.",
            missing_hint="I need CAPACITY: 10.0, calculated from the two constants.",
            mismatch_hint="Check the maths — a double times an int gives 10.0, not 10.",
            code_requires=["final double FUEL_RATE", "final int TANK_COUNT"],
            code_requires_hint="Both values need to be final constants with UPPER_SNAKE_CASE names: FUEL_RATE and TANK_COUNT.",
        ),
    },

    "t2m22": {
        "title": "The Swap",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "swapping two variables with a temporary",
        "teach": (
            "Swapping two variables is the classic exercise that proves you understand "
            "assignment. The obvious attempt destroys data:\n\n"
            "BROKEN:\n"
            "primary = backup;    // primary's old value is now GONE\n"
            "backup = primary;    // so this just copies backup onto itself\n"
            "Both end up holding what backup held. You overwrote the thing you still "
            "needed before you'd finished with it.\n\n"
            "THE FIX — park one value somewhere first:\n"
            "int temp = primary;   // save primary\n"
            "primary = backup;     // now safe to overwrite\n"
            "backup = temp;        // and restore the saved one\n\n"
            "WORKED EXAMPLE (primary 10, backup 20):\n"
            "after the three lines above, primary is 20 and backup is 10.\n\n"
            "COMMON MISTAKE: getting the three lines out of order. The save must come "
            "first, and the restore last. Trace it on paper with real numbers — that "
            "habit is worth more than memorising the pattern."
        ),
        "briefing": "Two channels are crossed and need swapping. Declare int primary as 10 and int backup as 20, swap their values using a temporary variable, then print PRIMARY: 20 then BACKUP: 10.",
        "hints": [
            "Three assignments, and you need a third variable to hold one value while you move the other.",
            "int temp = primary; then primary = backup; then backup = temp;",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        int primary = 10;
        int backup = 20;

        int temp = primary;
        primary = backup;
        backup = temp;

        System.out.println("PRIMARY: " + primary);
        System.out.println("BACKUP: " + backup);
    }
}''',
        "wrong": [
            # the broken two-line swap — both end up 20
            '''public class Main {
    public static void main(String[] args) {
        int primary = 10;
        int backup = 20;

        primary = backup;
        backup = primary;

        System.out.println("PRIMARY: " + primary);
        System.out.println("BACKUP: " + backup);
    }
}''',
            # declared them the other way round instead of actually swapping
            '''public class Main {
    public static void main(String[] args) {
        int primary = 20;
        int backup = 10;
        System.out.println("PRIMARY: " + primary);
        System.out.println("BACKUP: " + backup);
    }
}''',
        ],
        "check": make_checker(
            expected_output="PRIMARY: 20\nBACKUP: 10",
            success_line="Swapped without losing either value. The temp variable is the whole trick.",
            missing_hint="I need PRIMARY: 20 and BACKUP: 10 after the swap.",
            mismatch_hint="If both show 20, you overwrote primary before saving it — you need a temp variable.",
            code_requires=["int primary = 10", "int backup = 20", "temp"],
            code_requires_hint="Start from primary = 10 and backup = 20 and genuinely swap them using a temp variable.",
        ),
    },

    "t2m23": {
        "title": "Inferred Type",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "var — the compiler works the type out, it doesn't remove it",
        "teach": (
            "Since Java 10 you can write var for a local variable and let the compiler "
            "work out the type from the value you assign.\n\n"
            "WORKED EXAMPLE:\n"
            "var count = 5;               // compiler decides: int\n"
            "var label = \"SECTOR\";        // compiler decides: String\n"
            "System.out.println(label + \" \" + count);\n"
            "-> prints: SECTOR 5\n\n"
            "This is NOT a dynamic type. count is an int, permanently and immediately — "
            "count = \"hello\"; is still a compile error. The type is fixed the moment "
            "it's inferred; you just didn't type it out.\n\n"
            "Because the type comes from the value, var needs one immediately: var x; "
            "won't compile, and neither will var x = null;. It's also local-variable "
            "only — never for fields or parameters.\n\n"
            "Use it where the type is already obvious from the right-hand side. Prefer "
            "the explicit type where it isn't — and write the explicit type in "
            "coursework unless you've been told otherwise, because it's what's being "
            "assessed.\n\n"
            "COMMON MISTAKE: reading var as 'this variable can hold anything'. It means "
            "'work out the one type it will hold, and hold me to it'."
        ),
        "briefing": "Let the compiler do the typing. Using var for both, declare count holding 5 and label holding SECTOR, then print SECTOR 5 on one line.",
        "hints": [
            "var count = 5; — no type written, but it IS an int from that moment on.",
            "System.out.println(label + \" \" + count); — the space is a string in the middle.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        var count = 5;
        var label = "SECTOR";
        System.out.println(label + " " + count);
    }
}''',
        "check": make_checker(
            expected_output="SECTOR 5",
            success_line="Inferred, not dynamic. The compiler wrote the type for you and will still hold you to it.",
            missing_hint="I need the single line SECTOR 5.",
            mismatch_hint="Check there's exactly one space between SECTOR and 5.",
            code_requires=["var count", "var label"],
            code_requires_hint="This one wants var for both declarations, so the compiler infers the types.",
        ),
    },

    "t2m24": {
        "title": "Nothing Is Not Zero",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "the 'might not have been initialized' error",
        "teach": (
            "A local variable that has been declared but never assigned holds NOTHING. "
            "Not zero, not empty — nothing. Java refuses to let you read it:\n\n"
            "BROKEN:\n"
            "int total;\n"
            "System.out.println(total);\n"
            "-> error: variable total might not have been initialized\n\n"
            "This is a feature. In some languages that prints garbage from whatever was "
            "in memory; Java stops you at compile time instead. The compiler is quite "
            "strict about it — it won't even allow a case where a value is assigned on "
            "some paths but not others.\n\n"
            "THE FIX — give it a starting value:\n"
            "int total = 0;\n"
            "System.out.println(total);   // prints 0\n\n"
            "Note that 0 is a real choice you're making, not a default that was already "
            "there.\n\n"
            "COMMON MISTAKE: assuming fields work the same way. Instance fields (Topic 9) "
            "DO get default values — 0, false, null — automatically. Local variables, "
            "inside a method, never do. It's a genuine inconsistency in the language, and "
            "knowing which is which saves confusion later."
        ),
        "briefing": "The editor has a program that won't compile because it reads a variable that was never given a value. Fix it so it prints TOTAL: 0 — by initialising the variable properly, not by deleting it.",
        "hints": [
            "The declaration needs a starting value on the same line: int total = 0;",
            "Zero is a decision here, not a default Java was going to supply for you.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    public static void main(String[] args) {\n"
            "        int total;\n"
            "        System.out.println(\"TOTAL: \" + total);\n"
            "    }\n"
            "}"
        ),
        "solution": '''public class Main {
    public static void main(String[] args) {
        int total = 0;
        System.out.println("TOTAL: " + total);
    }
}''',
        "check": make_checker(
            expected_output="TOTAL: 0",
            success_line="Initialised on purpose. Local variables get no free default — that's Java protecting you from garbage.",
            missing_hint="Still not compiling. The variable needs a value before it can be read.",
            mismatch_hint="Check the label: TOTAL: then the value.",
            code_requires=["int total"],
            code_requires_hint="Keep the variable — just give it a starting value: int total = 0;",
        ),
    },

    "t2m25": {
        "title": "FINAL BOSS — Agent Dossier",
        "topic": 2, "topic_name": "Variables & Constants",
        "concept": "the entire topic, from an empty editor",
        "teach": (
            "Empty editor. Everything from Topic 2.\n\n"
            "FULL RECAP:\n"
            "int long short byte      whole numbers, four ceilings\n"
            "double float             decimals (float literals need f)\n"
            "char 'x'   String \"xx\"   one character vs text\n"
            "boolean                  true / false, often from a comparison\n"
            "final NAME_LIKE_THIS     a locked constant\n"
            "var                      let the compiler infer a local's type\n"
            "int b = a;               copies the value, creates no link\n"
            "{ }                      a scope; declare outside to survive it\n"
            "Local variables have no default value — initialise them.\n\n"
            "Every value below must live in a variable of the right type. No magic "
            "numbers in the output strings."
        ),
        "briefing": (
            "Assemble the full agent dossier from scratch.\n\n"
            "Declare: String codename VESPER, char clearanceGrade A, int missionsRun 23, "
            "double successRate 91.5, boolean isActive true, and a constant final int "
            "MAX_MISSIONS holding 30.\n\n"
            "Then derive int missionsRemaining from MAX_MISSIONS minus missionsRun, and "
            "boolean isVeteran from missionsRun > 20.\n\n"
            "Print exactly:\n"
            "CODENAME: VESPER\n"
            "GRADE: A\n"
            "RUN: 23\n"
            "REMAINING: 7\n"
            "RATE: 91.5\n"
            "ACTIVE: true\n"
            "VETERAN: true"
        ),
        "hints": [
            "Declare all six variables first, then the two derived ones, then print. Order matters — a derived variable must come after what it's derived from.",
            "REMAINING must be MAX_MISSIONS - missionsRun in code, and VETERAN must come from missionsRun > 20. Neither may be typed in.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void main(String[] args) {
        String codename = "VESPER";
        char clearanceGrade = 'A';
        int missionsRun = 23;
        double successRate = 91.5;
        boolean isActive = true;
        final int MAX_MISSIONS = 30;

        int missionsRemaining = MAX_MISSIONS - missionsRun;
        boolean isVeteran = missionsRun > 20;

        System.out.println("CODENAME: " + codename);
        System.out.println("GRADE: " + clearanceGrade);
        System.out.println("RUN: " + missionsRun);
        System.out.println("REMAINING: " + missionsRemaining);
        System.out.println("RATE: " + successRate);
        System.out.println("ACTIVE: " + isActive);
        System.out.println("VETERAN: " + isVeteran);
    }
}''',
        "wrong": [
            # derived values typed in by hand
            '''public class Main {
    public static void main(String[] args) {
        String codename = "VESPER";
        char clearanceGrade = 'A';
        int missionsRun = 23;
        double successRate = 91.5;
        boolean isActive = true;
        int missionsRemaining = 7;
        boolean isVeteran = true;

        System.out.println("CODENAME: " + codename);
        System.out.println("GRADE: " + clearanceGrade);
        System.out.println("RUN: " + missionsRun);
        System.out.println("REMAINING: " + missionsRemaining);
        System.out.println("RATE: " + successRate);
        System.out.println("ACTIVE: " + isActive);
        System.out.println("VETERAN: " + isVeteran);
    }
}''',
        ],
        "check": make_checker(
            expected_output=(
                "CODENAME: VESPER\nGRADE: A\nRUN: 23\nREMAINING: 7\n"
                "RATE: 91.5\nACTIVE: true\nVETERAN: true"
            ),
            success_line="Every type, a real constant, two derived values, all from an empty editor. Topic Two complete.",
            missing_hint="The dossier doesn't match, or it didn't compile. Build it a few lines at a time.",
            mismatch_hint="Some lines are right. Check the char's single quotes, and that REMAINING and VETERAN are calculated rather than typed.",
            code_requires=["public class Main", "final int MAX_MISSIONS",
                           "MAX_MISSIONS - missionsRun", "missionsRun > 20", "'A'"],
            code_requires_hint="I need the MAX_MISSIONS constant, REMAINING derived as MAX_MISSIONS - missionsRun, VETERAN derived as missionsRun > 20, and the grade as a char in single quotes.",
        ),
    },
}
