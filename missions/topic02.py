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
}
