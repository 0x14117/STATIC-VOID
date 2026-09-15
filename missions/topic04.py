"""
Topic 4 — Methods, Parameters and the Stack.

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
}
