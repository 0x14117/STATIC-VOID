"""
Topic 4 — Methods, Parameters and the Stack.

Mission content for this topic. See missions/__init__.py for how the
per-topic modules are stitched into the single MISSIONS mapping the
server and the checkers use, and missions/common.py for the shared
checker factories and boilerplate constants.
"""

from .common import (
    BASIC_BOILERPLATE,
    METHOD_BOILERPLATE,
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
        "solution": '''public class Main {
    public static void greet() {
        System.out.println("SIGNAL RECEIVED");
    }

    public static void main(String[] args) {
        greet();
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("SIGNAL RECEIVED");
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void announce(String name) {
        System.out.println("AGENT: " + name);
    }

    public static void main(String[] args) {
        announce("Viper");
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("AGENT: Viper");
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static int square(int n) {
        return n * n;
    }

    public static void main(String[] args) {
        int result = square(6);
        System.out.println(result);
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println(36);
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        int sum = add(4, 9);
        System.out.println(sum);
        int sum2 = add(sum, 100);
        System.out.println(sum2);
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println(13);
        System.out.println(113);
    }
}''',
        ],
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
        "solution": '''public class Main {
    public static void header() {
        System.out.println(">>> MISSION REPORT <<<");
    }

    public static int combine(int a, int b) {
        return a + b;
    }

    public static String label(String name, int value) {
        return name + ": " + value;
    }

    public static void main(String[] args) {
        header();
        int total = combine(15, 27);
        System.out.println(label("TOTAL", total));
        int doubled = combine(total, total);
        System.out.println(label("DOUBLED", doubled));
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
                ">>> MISSION REPORT <<<\nTOTAL: 42\nDOUBLED: 84"
            ),
            success_line="Three methods, working together, correct stack behavior throughout. Topic Four complete.",
            missing_hint="Your report doesn't match, or it didn't compile. Check all three method definitions and the calls in main().",
            mismatch_hint="Check exact wording, and that combine() and label() are called with the right values in order.",
            code_requires=["public class Main", "public static void main", "static void header", "static int combine", "static String label"],
            code_requires_hint="I need all three methods defined — header(), combine(), and label() — plus main() calling them.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 2 — the return type is a promise: String, double, boolean, and
    # methods that take several parameters at once.
    # ---------------------------------------------------------------------

    "t4m6": {
        "title": "Returning Text",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "a method with a String return type",
        "teach": (
            "A method's return type can be any type — not just int. Whatever you write "
            "in front of the method name is a PROMISE that the method will hand back a "
            "value of exactly that type.\n\n"
            "WORKED EXAMPLE:\n"
            "public static String shout(String word) {\n"
            "    return word + \"!\";\n"
            "}\n"
            "// called with:\n"
            "System.out.println(shout(\"go\"));\n"
            "-> prints: go!\n\n"
            "The method builds a String and returns it. The caller can print it, store "
            "it, or pass it straight into another method.\n\n"
            "The compiler holds you to the promise both ways: a String method must "
            "return a String on every possible path, and returning an int from it won't "
            "compile.\n\n"
            "COMMON MISTAKE: printing inside the method when you meant to return. "
            "System.out.println(word + \"!\") inside shout() puts text on screen and "
            "hands back nothing — so the caller has nothing to work with. Returning is "
            "almost always more useful: the caller can always print it, but printing "
            "can't be un-done."
        ),
        "briefing": "Build a tagging utility. Write a method tag(String name) that RETURNS the name wrapped in square brackets — so \"CIPHER\" becomes [CIPHER]. In main(), print the result of calling tag(\"CIPHER\").",
        "hints": [
            "public static String tag(String name) { return \"[\" + name + \"]\"; }",
            "In main: System.out.println(tag(\"CIPHER\")); — print what it returns, don't print inside the method.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static String tag(String name) {
        return "[" + name + "]";
    }

    public static void main(String[] args) {
        System.out.println(tag("CIPHER"));
    }
}''',
        "wrong": [
            # printed inside the method, returned nothing
            '''public class Main {
    public static void tag(String name) {
        System.out.println("[" + name + "]");
    }

    public static void main(String[] args) {
        tag("CIPHER");
    }
}''',
        ],
        "check": make_checker(
            expected_output="[CIPHER]",
            success_line="Built and handed back. The caller decides what to do with it — that's why returning beats printing.",
            missing_hint="I need [CIPHER] printed, from a method that RETURNS the bracketed text.",
            mismatch_hint="Check the brackets are square and there are no extra spaces inside them.",
            code_requires=["static String tag", "return"],
            code_requires_hint="I need a method declared as 'public static String tag(String name)' that uses return.",
        ),
    },

    "t4m7": {
        "title": "Returning A Decimal",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "a method with a double return type",
        "teach": (
            "Same idea, decimal flavour — and it combines with the int-division trap "
            "from Topic 3 in a way that catches people out.\n\n"
            "BROKEN:\n"
            "public static double half(int n) {\n"
            "    return n / 2;         // int / int — truncates BEFORE returning\n"
            "}\n"
            "half(7) gives 3.0, not 3.5. The division happened as int arithmetic, and "
            "widening the broken 3 into a double afterwards can't recover the .5.\n\n"
            "THE FIX — make one operand a double so the division itself is decimal:\n"
            "public static double half(int n) {\n"
            "    return n / 2.0;\n"
            "}\n"
            "half(7) now gives 3.5.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.println(half(9));\n"
            "-> prints: 4.5\n\n"
            "COMMON MISTAKE: assuming the double return type changes how the sum is "
            "worked out. It doesn't. The return type only says what the result gets "
            "converted to on the way out — by then the damage is done."
        ),
        "briefing": "Write a method half(int n) that returns a double: exactly half of n, keeping the decimal. In main(), print half(7). It must print 3.5, not 3.0.",
        "hints": [
            "Divide by 2.0, not 2 — that one character makes it decimal arithmetic.",
            "public static double half(int n) { return n / 2.0; }",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static double half(int n) {
        return n / 2.0;
    }

    public static void main(String[] args) {
        System.out.println(half(7));
    }
}''',
        "wrong": [
            # int division inside a double method — 3.0
            '''public class Main {
    public static double half(int n) {
        return n / 2;
    }

    public static void main(String[] args) {
        System.out.println(half(7));
    }
}''',
        ],
        "check": make_checker(
            expected_output="3.5",
            success_line="3.5. The return type didn't save the division — dividing by 2.0 did.",
            missing_hint="I need 3.5 printed from a method called half(7).",
            mismatch_hint="If you got 3.0, the division ran as int arithmetic — divide by 2.0 rather than 2.",
            code_requires=["static double half", "return"],
            code_requires_hint="I need a method 'public static double half(int n)' that returns the halved value.",
        ),
    },

    "t4m8": {
        "title": "Returning An Answer",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "a method with a boolean return type",
        "teach": (
            "A method can return true or false — and this is one of the most useful "
            "shapes in all of programming, because it turns a fiddly condition into a "
            "name you can read.\n\n"
            "WORKED EXAMPLE:\n"
            "public static boolean isPositive(int n) {\n"
            "    return n > 0;\n"
            "}\n"
            "System.out.println(isPositive(12));    // true\n"
            "System.out.println(isPositive(-3));    // false\n\n"
            "Note what the body ISN'T: there's no if. n > 0 already IS a boolean, so you "
            "just return it. Writing 'if (n > 0) return true; else return false;' does "
            "the same thing in four times the space, and every reviewer will tell you to "
            "simplify it.\n\n"
            "Convention: name these like questions — isEmpty, hasClearance, isValid. Then "
            "the call site reads as English.\n\n"
            "COMMON MISTAKE: returning the String \"true\" instead of the boolean true. "
            "Quotes make it text, and the compiler will reject it."
        ),
        "briefing": "Write a method isEven(int n) that returns true when n is even and false when it isn't — using % and ==, with no if statement. In main(), print isEven(10) then isEven(7). Two lines: true, then false.",
        "hints": [
            "The comparison is already a boolean, so return it directly: return n % 2 == 0;",
            "Call it twice in main, once with 10 and once with 7.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static void main(String[] args) {
        System.out.println(isEven(10));
        System.out.println(isEven(7));
    }
}''',
        "wrong": [
            # ignored the parameter and hardcoded the answers
            '''public class Main {
    public static boolean isEven(int n) {
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isEven(10));
        System.out.println(isEven(7));
    }
}''',
        ],
        "check": make_checker(
            expected_output="true\nfalse",
            success_line="A condition with a name. That's what boolean methods are really for.",
            missing_hint="I need two lines: true then false, from two calls to isEven.",
            mismatch_hint="If both lines match, the method is ignoring its parameter — the answer must come from n.",
            code_requires=["static boolean isEven", "n %"],
            code_requires_hint="I need 'public static boolean isEven(int n)' whose answer is computed from n using %.",
        ),
    },

    "t4m9": {
        "title": "Several Ingredients",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "multiple parameters of different types",
        "teach": (
            "A method can take as many parameters as it needs, of any mix of types. Each "
            "one gets its own type and name, separated by commas.\n\n"
            "WORKED EXAMPLE:\n"
            "public static String receipt(String item, int quantity, double price) {\n"
            "    return item + \" x\" + quantity + \" = \" + (quantity * price);\n"
            "}\n"
            "System.out.println(receipt(\"BOLT\", 3, 1.5));\n"
            "-> prints: BOLT x3 = 4.5\n\n"
            "Two rules the compiler enforces absolutely:\n"
            "- ORDER. receipt(3, \"BOLT\", 1.5) won't compile — the types don't line up.\n"
            "- COUNT. Too few or too many arguments is an error, never a default.\n\n"
            "The parameter names are private to the method. The caller's variables can "
            "be called anything; only position matters.\n\n"
            "COMMON MISTAKE: a long list of same-typed parameters — method(int, int, int, "
            "int). Nothing stops you passing them in the wrong order, and the compiler "
            "can't help. Once you reach Topic 9, an object is usually the better answer."
        ),
        "briefing": "Write a method logEntry(String label, int code, boolean urgent) that returns the three joined as label, then a space, then the code, then a space, then the urgent flag — so logEntry(\"BREACH\", 12, true) gives BREACH 12 true. Print that call's result in main().",
        "hints": [
            "Three parameters, comma separated, each with its own type: (String label, int code, boolean urgent)",
            "return label + \" \" + code + \" \" + urgent; — the numbers and booleans convert to text automatically.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static String logEntry(String label, int code, boolean urgent) {
        return label + " " + code + " " + urgent;
    }

    public static void main(String[] args) {
        System.out.println(logEntry("BREACH", 12, true));
    }
}''',
        "check": make_checker(
            expected_output="BREACH 12 true",
            success_line="Three types, one method, one line out. Order and count are both non-negotiable.",
            missing_hint="I need BREACH 12 true, built by a method taking three parameters.",
            mismatch_hint="Check the spacing — single spaces between the three parts.",
            code_requires=["static String logEntry", "String label", "int code", "boolean urgent"],
            code_requires_hint="I need the method signature exactly: logEntry(String label, int code, boolean urgent).",
        ),
    },

    "t4m10": {
        "title": "BOSS — Utility Toolkit",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "four return types in one program — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "public static String  name(params) { return aString;  }\n"
            "public static double  name(params) { return aDouble;  }\n"
            "public static boolean name(params) { return aBoolean; }\n"
            "public static int     name(params) { return anInt;    }\n\n"
            "The return type is a promise the compiler enforces on every path. "
            "Parameters are matched by position and type, never by name. A method that "
            "returns is more useful than one that prints, because the caller can always "
            "print the result — but can't un-print it."
        ),
        "briefing": (
            "Build the toolkit from scratch, with three methods plus main():\n\n"
            "- tag(String s): returns s wrapped in square brackets\n"
            "- percent(int part, int whole): returns a double — part divided by whole, "
            "times 100, keeping the decimals\n"
            "- isCritical(double value): returns true when value is above 90\n\n"
            "In main(), print tag(\"CORE\"), then percent(37, 40), then "
            "isCritical(percent(37, 40)).\n\n"
            "Expected output:\n"
            "[CORE]\n"
            "92.5\n"
            "true"
        ),
        "hints": [
            "For percent, make the division decimal: (double) part / whole * 100",
            "The third line passes one method's RESULT straight into another method — isCritical(percent(37, 40)).",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static String tag(String s) {
        return "[" + s + "]";
    }

    public static double percent(int part, int whole) {
        return (double) part / whole * 100;
    }

    public static boolean isCritical(double value) {
        return value > 90;
    }

    public static void main(String[] args) {
        System.out.println(tag("CORE"));
        System.out.println(percent(37, 40));
        System.out.println(isCritical(percent(37, 40)));
    }
}''',
        "wrong": [
            # int division inside percent — 90.0, and the critical check then fails
            '''public class Main {
    public static String tag(String s) {
        return "[" + s + "]";
    }

    public static double percent(int part, int whole) {
        return part / whole * 100;
    }

    public static boolean isCritical(double value) {
        return value > 90;
    }

    public static void main(String[] args) {
        System.out.println(tag("CORE"));
        System.out.println(percent(37, 40));
        System.out.println(isCritical(percent(37, 40)));
    }
}''',
        ],
        "check": make_checker(
            expected_output="[CORE]\n92.5\ntrue",
            success_line="Three return types, and one method's result feeding another. Block complete.",
            missing_hint="Output doesn't match, or it didn't compile. Check all three methods are defined at class level, beside main().",
            mismatch_hint="If percent gave 0.0 or 90.0, the division ran as ints — cast one operand before dividing.",
            code_requires=["static String tag", "static double percent", "static boolean isCritical"],
            code_requires_hint="I need all three methods with those exact names and return types, plus main() calling them.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 3 — composition, and the two things the stack guarantees:
    # locals are private, and primitives are passed by value.
    # ---------------------------------------------------------------------

    "t4m11": {
        "title": "Methods Calling Methods",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "one method calling another",
        "teach": (
            "Methods aren't only called from main(). Any method can call any other, and "
            "that's how real programs are built — small pieces, stacked.\n\n"
            "WORKED EXAMPLE:\n"
            "public static int doubled(int n) {\n"
            "    return n * 2;\n"
            "}\n"
            "\n"
            "public static int quadrupled(int n) {\n"
            "    return doubled(doubled(n));    // calls the other one, twice\n"
            "}\n"
            "System.out.println(quadrupled(5));\n"
            "-> prints: 20\n\n"
            "Follow the stack: main calls quadrupled, which calls doubled (returns 10), "
            "which is passed into doubled again (returns 20), which quadrupled returns to "
            "main. Four frames pushed and popped, in order.\n\n"
            "The order of the methods in the file doesn't matter at all — quadrupled can "
            "call doubled even if doubled is written below it. Java reads the whole class "
            "before running anything.\n\n"
            "COMMON MISTAKE: trying to define one method INSIDE another. Java has no "
            "nested methods; every method is a sibling, directly inside the class."
        ),
        "briefing": "Build one method on top of another. Write doubled(int n) returning n * 2, and quadrupled(int n) that returns the result of calling doubled twice — it must not multiply by 4 itself. In main(), print quadrupled(5).",
        "hints": [
            "quadrupled's body is a single return that calls the other method twice: return doubled(doubled(n));",
            "Both methods sit at class level, beside main() — never inside it.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static int doubled(int n) {
        return n * 2;
    }

    public static int quadrupled(int n) {
        return doubled(doubled(n));
    }

    public static void main(String[] args) {
        System.out.println(quadrupled(5));
    }
}''',
        "wrong": [
            # quadrupled does its own maths and never calls doubled
            '''public class Main {
    public static int doubled(int n) {
        return n * 2;
    }

    public static int quadrupled(int n) {
        return n * 4;
    }

    public static void main(String[] args) {
        System.out.println(quadrupled(5));
    }
}''',
        ],
        "check": make_checker(
            expected_output="20",
            success_line="20, through four stack frames. Small methods composing into bigger ones is the whole game.",
            missing_hint="I need 20 printed from a call to quadrupled(5).",
            mismatch_hint="Check doubled returns n * 2 and that quadrupled calls it rather than doing its own arithmetic.",
            code_requires=["static int doubled", "static int quadrupled", "doubled(doubled("],
            code_requires_hint="quadrupled must build on doubled — return doubled(doubled(n)); — not multiply by 4 directly.",
        ),
    },

    "t4m12": {
        "title": "Calls Inside Expressions",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "a method call is a value, so it can go anywhere a value can",
        "teach": (
            "A call to a method that returns something IS a value. It can go anywhere a "
            "value can go: in arithmetic, inside a string, as another method's argument, "
            "inside a condition.\n\n"
            "WORKED EXAMPLE:\n"
            "public static int area(int w, int h) {\n"
            "    return w * h;\n"
            "}\n"
            "\n"
            "System.out.println(\"TOTAL: \" + (area(2, 3) + area(4, 5)));\n"
            "-> prints: TOTAL: 26\n\n"
            "Java runs area(2, 3), gets 6, runs area(4, 5), gets 20, adds them, then "
            "joins the result to the label. Note the brackets round the addition — the "
            "concatenation trap from Topic 3 applies to method results exactly as it does "
            "to plain numbers.\n\n"
            "You don't need a variable for every intermediate step. Use one when it makes "
            "the code clearer, not because Java requires it.\n\n"
            "COMMON MISTAKE: calling a method and ignoring what it returns. area(2, 3); "
            "on a line of its own computes 6 and throws it away — legal, silent, "
            "pointless."
        ),
        "briefing": "Use method calls directly inside a bigger expression. Write area(int w, int h) returning w * h. In main(), print the line TOTAL: followed by area(2, 3) added to area(4, 5) — with no intermediate variables. It should read TOTAL: 26.",
        "hints": [
            "Both calls go straight inside the println, joined by +.",
            "Bracket the addition or the joining will swallow it: \"TOTAL: \" + (area(2, 3) + area(4, 5))",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static int area(int w, int h) {
        return w * h;
    }

    public static void main(String[] args) {
        System.out.println("TOTAL: " + (area(2, 3) + area(4, 5)));
    }
}''',
        "wrong": [
            # no brackets — the results get joined as text instead of added
            '''public class Main {
    public static int area(int w, int h) {
        return w * h;
    }

    public static void main(String[] args) {
        System.out.println("TOTAL: " + area(2, 3) + area(4, 5));
    }
}''',
        ],
        "check": make_checker(
            expected_output="TOTAL: 26",
            success_line="26. A call that returns a value is a value — it goes wherever a number could.",
            missing_hint="I need TOTAL: 26, from two area() calls added together.",
            mismatch_hint="If you got TOTAL: 620, the brackets are missing — the joining happened before the addition.",
            code_requires=["static int area", "area(2, 3)", "area(4, 5)"],
            code_requires_hint="Both calls must appear inside the println expression itself.",
        ),
    },

    "t4m13": {
        "title": "Private Workspace",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "local variables belong to one frame only",
        "teach": (
            "A variable declared inside a method exists only in THAT method's stack "
            "frame. Two methods can both have a variable called total and they are "
            "completely unrelated — different boxes, different frames, no connection.\n\n"
            "WORKED EXAMPLE:\n"
            "public static int compute() {\n"
            "    int total = 99;        // compute's own total\n"
            "    return total;\n"
            "}\n"
            "\n"
            "public static void main(String[] args) {\n"
            "    int total = 5;         // main's total — a different variable entirely\n"
            "    System.out.println(compute());   // 99\n"
            "    System.out.println(total);       // 5  — untouched\n"
            "}\n"
            "-> prints:\n"
            "99\n"
            "5\n\n"
            "This is the property that makes methods safe to use. You can call any method "
            "without wondering whether it will quietly wreck your variables, because it "
            "cannot reach them.\n\n"
            "COMMON MISTAKE: expecting a method to see main's variables. It can't. The "
            "ONLY things a method knows are its parameters and what it declares itself — "
            "which is why parameters exist at all."
        ),
        "briefing": "Prove two methods can share a variable name without sharing anything else. Write compute() returning an int, which declares its own int called total set to 99 and returns it. In main(), declare int total as 5, print compute(), then print main's total. Two lines: 99, then 5.",
        "hints": [
            "Both methods declare their own 'int total' — that's the point, not a mistake.",
            "Print compute() first, then print total — main's variable is untouched by the call.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static int compute() {
        int total = 99;
        return total;
    }

    public static void main(String[] args) {
        int total = 5;
        System.out.println(compute());
        System.out.println(total);
    }
}''',
        "check": make_checker(
            expected_output="99\n5",
            success_line="Same name, separate frames, no interference. That isolation is what makes methods trustworthy.",
            missing_hint="I need two lines: 99 from compute(), then 5 from main's own total.",
            mismatch_hint="If the second line says 99, the two variables aren't separate — each method should declare its own.",
            code_requires=["int total = 99", "int total = 5"],
            code_requires_hint="Both methods need their own local called total — 99 inside compute(), 5 inside main().",
        ),
    },

    "t4m14": {
        "title": "Pass By Value",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "changing a parameter never changes the caller's variable",
        "teach": (
            "When you pass an int to a method, Java copies the VALUE into the parameter. "
            "The method gets its own copy. Changing it does nothing to the original.\n\n"
            "WORKED EXAMPLE:\n"
            "public static void reset(int number) {\n"
            "    number = 0;              // only this frame's copy changes\n"
            "    System.out.println(number);   // 0\n"
            "}\n"
            "\n"
            "public static void main(String[] args) {\n"
            "    int score = 50;\n"
            "    reset(score);\n"
            "    System.out.println(score);    // 50 — completely unaffected\n"
            "}\n"
            "-> prints:\n"
            "0\n"
            "50\n\n"
            "This is called PASS BY VALUE, and Java does it for every primitive, always. "
            "There is no way to make a method change an int variable belonging to its "
            "caller. If you want the caller to get a new value, RETURN it.\n\n"
            "COMMON MISTAKE: writing a method that 'updates' a number by assigning to its "
            "parameter, then wondering why nothing happened. The fix is score = reset(score) "
            "— return the new value and let the caller store it.\n\n"
            "(Objects are subtler — the reference is copied, so the method can change the "
            "object's contents even though it can't repoint the caller's variable. That "
            "distinction lands properly in Topic 9.)"
        ),
        "briefing": "Show that a method can't reach its caller's variable. Write reset(int number) — returning nothing — that sets its parameter to 0 and prints it. In main(), declare int score as 50, call reset(score), then print score. Two lines: 0, then 50.",
        "hints": [
            "Inside reset: number = 0; then print number. That prints 0.",
            "Back in main, print score — it's still 50, because reset only ever touched its own copy.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void reset(int number) {
        number = 0;
        System.out.println(number);
    }

    public static void main(String[] args) {
        int score = 50;
        reset(score);
        System.out.println(score);
    }
}''',
        "wrong": [
            # reassigned score in main too, hiding the actual lesson
            '''public class Main {
    public static void reset(int number) {
        number = 0;
        System.out.println(number);
    }

    public static void main(String[] args) {
        int score = 50;
        reset(score);
        score = 0;
        System.out.println(score);
    }
}''',
        ],
        "check": make_checker(
            expected_output="0\n50",
            success_line="0 then 50. A method cannot reach out and change your variable — if you want a new value, return one.",
            missing_hint="I need two lines: 0 printed inside the method, then 50 printed in main.",
            mismatch_hint="If the second line is 0, something changed score in main — the call alone must leave it at 50.",
            code_requires=["static void reset", "number = 0"],
            code_requires_hint="reset must assign 0 to its own parameter, and main must print score afterwards without changing it.",
        ),
    },

    "t4m15": {
        "title": "BOSS — Stack Trace",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "composition, locals and pass-by-value together — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "any method can call any other; file order is irrelevant\n"
            "a call that returns a value IS a value — usable inside expressions\n"
            "locals live in one frame; two methods may share a name and nothing else\n"
            "primitives are passed BY VALUE — a copy, never a link\n\n"
            "Every call pushes a frame; every return pops one. Trace the pushes and pops "
            "and nothing in this topic is mysterious."
        ),
        "briefing": (
            "Write the whole program from scratch, with three methods plus main():\n\n"
            "- step(int n): returns n + 1\n"
            "- twice(int n): returns step() applied to the result of step(n)\n"
            "- spoil(int n): sets its parameter to -999 and returns nothing\n\n"
            "In main(): declare int value as 10. Print twice(value). Call spoil(value). "
            "Print value again to prove it survived.\n\n"
            "Expected output:\n"
            "12\n"
            "10"
        ),
        "hints": [
            "twice must be built from step, not do its own arithmetic: return step(step(n));",
            "spoil is void and its assignment affects nothing outside its own frame — that's the point of printing value afterwards.",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static int step(int n) {
        return n + 1;
    }

    public static int twice(int n) {
        return step(step(n));
    }

    public static void spoil(int n) {
        n = -999;
    }

    public static void main(String[] args) {
        int value = 10;
        System.out.println(twice(value));
        spoil(value);
        System.out.println(value);
    }
}''',
        "wrong": [
            # twice does its own maths rather than composing step
            '''public class Main {
    public static int step(int n) {
        return n + 1;
    }

    public static int twice(int n) {
        return n + 2;
    }

    public static void spoil(int n) {
        n = -999;
    }

    public static void main(String[] args) {
        int value = 10;
        System.out.println(twice(value));
        spoil(value);
        System.out.println(value);
    }
}''',
        ],
        "check": make_checker(
            expected_output="12\n10",
            success_line="12 then 10 — composed correctly, and untouched by a method that tried to change it. Block complete.",
            missing_hint="I need two lines: 12 then 10. Check all three methods sit beside main() at class level.",
            mismatch_hint="If the second line is -999, something assigned to value in main — spoil can't do that from inside its own frame.",
            code_requires=["public class Main", "static int step", "step(step(", "static void spoil"],
            code_requires_hint="I need step, twice built by calling step twice, and a void spoil — plus main calling all three.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 4 — overloading, then recursion. The recursive missions use the
    # ternary for their base case rather than an if, because if statements
    # are Topic 6 and the stack behaviour is what's being taught here.
    # ---------------------------------------------------------------------

    "t4m16": {
        "title": "Same Name, Different Shape",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "method overloading by parameter count",
        "teach": (
            "Two methods CAN share a name, as long as their parameter lists differ. This "
            "is OVERLOADING, and Java picks the right one by looking at how you called "
            "it.\n\n"
            "WORKED EXAMPLE:\n"
            "public static int total(int a, int b) {\n"
            "    return a + b;\n"
            "}\n"
            "\n"
            "public static int total(int a, int b, int c) {\n"
            "    return a + b + c;\n"
            "}\n"
            "System.out.println(total(2, 3));       // 5   — picks the two-parameter one\n"
            "System.out.println(total(2, 3, 4));    // 9   — picks the three-parameter one\n\n"
            "You've been using overloading since mission one without noticing: "
            "System.out.println() has versions for int, String, double, char and more. "
            "That's why it prints anything you give it.\n\n"
            "The decision happens at COMPILE time, purely from the argument types and "
            "count.\n\n"
            "COMMON MISTAKE: thinking the return type distinguishes them. It doesn't. Two "
            "methods differing ONLY by return type is a compile error — Java would have "
            "no way to tell which you meant."
        ),
        "briefing": "Write two methods both called total: one taking two ints, one taking three, each returning their sum. In main(), print total(2, 3) then total(2, 3, 4). Two lines: 5, then 9.",
        "hints": [
            "Both methods have the same name and return type — only the parameter LIST differs.",
            "Java chooses between them by counting the arguments at the call site.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static int total(int a, int b) {
        return a + b;
    }

    public static int total(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        System.out.println(total(2, 3));
        System.out.println(total(2, 3, 4));
    }
}''',
        "wrong": [
            # dodged overloading by using two different names
            '''public class Main {
    public static int totalTwo(int a, int b) {
        return a + b;
    }

    public static int totalThree(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        System.out.println(totalTwo(2, 3));
        System.out.println(totalThree(2, 3, 4));
    }
}''',
        ],
        "check": make_checker(
            expected_output="5\n9",
            success_line="One name, two shapes, chosen at compile time. That's how println() handles everything you throw at it.",
            missing_hint="I need two lines: 5 then 9, from two methods that share the name total.",
            mismatch_hint="Check both methods return the sum of all their parameters.",
            code_requires=["static int total(int a, int b)", "static int total(int a, int b, int c)"],
            code_requires_hint="Both methods must be named total — that's the overloading. Different names won't do.",
        ),
    },

    "t4m17": {
        "title": "Overloading By Type",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "overloading by parameter type",
        "teach": (
            "Overloads can also differ by TYPE, not just count. Same number of "
            "parameters, different types, and Java still knows which one you meant.\n\n"
            "WORKED EXAMPLE:\n"
            "public static String describe(int n) {\n"
            "    return \"NUMBER \" + n;\n"
            "}\n"
            "\n"
            "public static String describe(String s) {\n"
            "    return \"TEXT \" + s;\n"
            "}\n"
            "System.out.println(describe(7));        // NUMBER 7\n"
            "System.out.println(describe(\"GO\"));     // TEXT GO\n\n"
            "The compiler matches on the type of the argument you passed. describe(7) can "
            "only mean the int version, so that's what gets called.\n\n"
            "Where it gets awkward is when a value could fit more than one overload — "
            "describe(7) when there's an int version AND a double version. Java picks the "
            "closest match (int), and if there's genuinely no best answer it refuses to "
            "compile with 'reference to describe is ambiguous'.\n\n"
            "COMMON MISTAKE: overloading a method to do two unrelated things. The "
            "overloads should mean the same thing for different inputs — otherwise give "
            "them different names and spare everyone the guessing."
        ),
        "briefing": "Write two methods both called describe, both returning a String: one takes an int and returns NUMBER followed by the value, the other takes a String and returns TEXT followed by the value. In main(), print describe(7) then describe(\"GO\"). Two lines: NUMBER 7, then TEXT GO.",
        "hints": [
            "Same name, same return type, one parameter each — the only difference is int versus String.",
            "return \"NUMBER \" + n; in one, return \"TEXT \" + s; in the other.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static String describe(int n) {
        return "NUMBER " + n;
    }

    public static String describe(String s) {
        return "TEXT " + s;
    }

    public static void main(String[] args) {
        System.out.println(describe(7));
        System.out.println(describe("GO"));
    }
}''',
        "check": make_checker(
            expected_output="NUMBER 7\nTEXT GO",
            success_line="Matched by type, at compile time. Same idea, two kinds of input.",
            missing_hint="I need two lines: NUMBER 7 then TEXT GO.",
            mismatch_hint="Check the labels and the single space before each value.",
            code_requires=["describe(int n)", "describe(String s)"],
            code_requires_hint="Both methods must be called describe — one taking an int, one taking a String.",
        ),
    },

    "t4m18": {
        "title": "A Method That Calls Itself",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "recursion and the base case",
        "teach": (
            "A method is allowed to call ITSELF. That's RECURSION, and it's the clearest "
            "demonstration of why the call stack exists.\n\n"
            "Every recursive method needs two parts:\n"
            "  BASE CASE       the situation where it stops and just returns\n"
            "  RECURSIVE CASE  where it calls itself with a SMALLER problem\n\n"
            "Miss the base case and it never stops — the stack fills with frames until "
            "the JVM gives up with StackOverflowError.\n\n"
            "WORKED EXAMPLE — counting down to zero:\n"
            "public static int countdown(int n) {\n"
            "    return n == 0 ? 0 : countdown(n - 1);\n"
            "}\n"
            "System.out.println(countdown(3));\n"
            "-> prints: 0\n\n"
            "The ternary IS the base case: 'is n zero? then stop and return 0, otherwise "
            "call myself with n - 1'. Trace the frames: countdown(3) waits on "
            "countdown(2), which waits on countdown(1), which waits on countdown(0), "
            "which finally returns 0 — and the answer passes back up through all four.\n\n"
            "You'll usually see this written with an if once you reach Topic 6; a ternary "
            "does exactly the same job here.\n\n"
            "COMMON MISTAKE: a recursive call that doesn't get closer to the base case. "
            "countdown(n) calling countdown(n) recurses forever. The argument must shrink."
        ),
        "briefing": "Write sumTo(int n) that returns the sum of all whole numbers from n down to 1, by calling itself. The base case is n == 0, which returns 0. In main(), print sumTo(5) — which is 5+4+3+2+1, so 15.",
        "hints": [
            "The shape is: return n == 0 ? 0 : n + sumTo(n - 1);",
            "Read it as 'if there's nothing left, add nothing; otherwise add n to the sum of everything below it'.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static int sumTo(int n) {
        return n == 0 ? 0 : n + sumTo(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(sumTo(5));
    }
}''',
        "wrong": [
            # no recursion at all — used the closed-form formula
            '''public class Main {
    public static int sumTo(int n) {
        return n * (n + 1) / 2;
    }

    public static void main(String[] args) {
        System.out.println(sumTo(5));
    }
}''',
        ],
        "check": make_checker(
            expected_output="15",
            success_line="15, computed five frames deep and unwound back to you. That's the stack doing its job.",
            missing_hint="I need 15 printed from a recursive sumTo(5).",
            mismatch_hint="Check the base case returns 0 at n == 0, and that the recursive call is sumTo(n - 1).",
            code_requires=["static int sumTo", "sumTo(n - 1)"],
            code_requires_hint="This one must actually recurse — the body needs to call sumTo(n - 1).",
        ),
    },

    "t4m19": {
        "title": "Factorial",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "the classic recursion, and where it breaks",
        "teach": (
            "Factorial is the textbook recursion because its definition is already "
            "recursive:\n\n"
            "  5! = 5 x 4 x 3 x 2 x 1 = 120\n"
            "  and 5! is just 5 x 4!\n\n"
            "WORKED EXAMPLE:\n"
            "public static int factorial(int n) {\n"
            "    return n <= 1 ? 1 : n * factorial(n - 1);\n"
            "}\n"
            "System.out.println(factorial(4));\n"
            "-> prints: 24\n\n"
            "The base case is n <= 1 rather than n == 1. Using <= guards against 0 and "
            "against negatives — with == 1, factorial(0) would recurse past the base case "
            "forever and blow the stack. Base cases should catch everything at or beyond "
            "the boundary, not just land exactly on it.\n\n"
            "Worth knowing: factorial(13) already overflows an int, silently, and gives a "
            "negative answer. Recursion isn't the fragile part — int is.\n\n"
            "COMMON MISTAKE: writing n * factorial(n) instead of n * factorial(n - 1). "
            "The problem never shrinks and the stack overflows immediately."
        ),
        "briefing": "Write factorial(int n) returning n factorial, recursively, with n <= 1 as the base case returning 1. In main(), print factorial(5) then factorial(1). Two lines: 120, then 1.",
        "hints": [
            "return n <= 1 ? 1 : n * factorial(n - 1);",
            "factorial(1) should hit the base case immediately and return 1 without recursing at all.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static int factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(factorial(1));
    }
}''',
        "check": make_checker(
            expected_output="120\n1",
            success_line="120 and 1. Note the base case caught n = 1 without recursing once — that's what a good boundary does.",
            missing_hint="I need two lines: 120 then 1.",
            mismatch_hint="Check the multiplication is n * factorial(n - 1) and that the base case returns 1.",
            code_requires=["static int factorial", "factorial(n - 1)"],
            code_requires_hint="This must recurse — the body needs n * factorial(n - 1) with a base case.",
        ),
    },

    "t4m20": {
        "title": "BOSS — Recursive Descent",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "overloading and recursion together — no starter code",
        "teach": (
            "No starter code. RECAP of this block:\n\n"
            "OVERLOADING   same name, different parameter list; chosen at compile time\n"
            "              (return type alone is NOT enough to distinguish them)\n"
            "RECURSION     a base case that stops, and a call that shrinks the problem\n\n"
            "A recursive method with no base case doesn't loop forever — it fills the "
            "stack and dies with StackOverflowError."
        ),
        "briefing": (
            "Write the whole program from scratch.\n\n"
            "Define TWO overloaded methods both called power:\n"
            "- power(int base): returns base squared, by calling the other overload\n"
            "- power(int base, int exponent): returns base to that exponent, RECURSIVELY, "
            "with exponent == 0 returning 1\n\n"
            "In main(), print power(2, 8), then power(5), then power(7, 0).\n\n"
            "Expected output:\n"
            "256\n"
            "25\n"
            "1"
        ),
        "hints": [
            "The recursive one: return exponent == 0 ? 1 : base * power(base, exponent - 1);",
            "The one-parameter overload does no maths of its own — it just calls power(base, 2).",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static int power(int base) {
        return power(base, 2);
    }

    public static int power(int base, int exponent) {
        return exponent == 0 ? 1 : base * power(base, exponent - 1);
    }

    public static void main(String[] args) {
        System.out.println(power(2, 8));
        System.out.println(power(5));
        System.out.println(power(7, 0));
    }
}''',
        "wrong": [
            # used Math.pow instead of recursing
            '''public class Main {
    public static int power(int base) {
        return base * base;
    }

    public static int power(int base, int exponent) {
        return (int) Math.pow(base, exponent);
    }

    public static void main(String[] args) {
        System.out.println(power(2, 8));
        System.out.println(power(5));
        System.out.println(power(7, 0));
    }
}''',
        ],
        "check": make_checker(
            expected_output="256\n25\n1",
            success_line="Overloaded and recursive, and the base case handled an exponent of zero cleanly. Block complete.",
            missing_hint="I need three lines: 256, 25, 1. Both methods must be named power.",
            mismatch_hint="Check the base case returns 1 when the exponent hits 0 — that's what makes the last line work.",
            code_requires=["public class Main", "power(int base)", "power(int base, int exponent)",
                           "power(base, exponent - 1)"],
            code_requires_hint="I need both overloads named power, and the two-parameter one must genuinely recurse.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 5 — return as an exit, void vs value, decomposition, and what
    # happens when the stack runs out. Final boss closes the topic.
    # ---------------------------------------------------------------------

    "t4m21": {
        "title": "Return Is An Exit",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "return leaves the method immediately",
        "teach": (
            "return does two things: it hands back a value, AND it ends the method on the "
            "spot. Nothing after it in that method runs.\n\n"
            "WORKED EXAMPLE:\n"
            "public static int check(int n) {\n"
            "    System.out.println(\"CHECKING\");\n"
            "    return n * 2;\n"
            "    // System.out.println(\"NEVER\");   <- unreachable: won't compile\n"
            "}\n"
            "System.out.println(check(4));\n"
            "-> prints:\n"
            "CHECKING\n"
            "8\n\n"
            "Java is strict about this: code that can never run is a compile error, "
            "'unreachable statement'. Other languages let it slide as a warning — Java "
            "stops you.\n\n"
            "In a void method you can still write return; on its own, with no value, "
            "purely to leave early. You'll use that constantly once conditions arrive in "
            "Topic 6 — check for the bad case, return immediately, and the rest of the "
            "method can assume everything is fine.\n\n"
            "COMMON MISTAKE: expecting the lines after a return to run 'later'. They "
            "never run. The frame is gone."
        ),
        "briefing": "Show that return ends the method. Write check(int n) that prints CHECKING, then returns n * 2. In main(), print check(4). Output: CHECKING then 8.",
        "hints": [
            "The println goes BEFORE the return — anything after it would be unreachable and won't compile.",
            "In main, System.out.println(check(4)); prints what comes back.",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static int check(int n) {
        System.out.println("CHECKING");
        return n * 2;
    }

    public static void main(String[] args) {
        System.out.println(check(4));
    }
}''',
        "check": make_checker(
            expected_output="CHECKING\n8",
            success_line="CHECKING, then 8. return hands back a value and closes the frame in the same move.",
            missing_hint="I need CHECKING printed from inside the method, then 8 printed in main.",
            mismatch_hint="Check the order — the print happens inside check(), before the return.",
            code_requires=["static int check", "return n * 2"],
            code_requires_hint="I need a method check(int n) that prints first and then returns n * 2.",
        ),
    },

    "t4m22": {
        "title": "Void Or Value",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "choosing between a void method and one that returns",
        "teach": (
            "Every method you write is one of two kinds, and picking the right one is a "
            "real design decision.\n\n"
            "VOID — it DOES something. Printing, saving, updating. There's no answer to "
            "hand back, and you can't use it in an expression.\n\n"
            "RETURNS — it ANSWERS something. The caller decides what to do with the "
            "answer: print it, store it, pass it on.\n\n"
            "WORKED EXAMPLE — the same idea, both ways:\n"
            "public static void printTotal(int a, int b) {\n"
            "    System.out.println(a + b);       // does it, tells you nothing\n"
            "}\n"
            "\n"
            "public static int total(int a, int b) {\n"
            "    return a + b;                    // answers, lets you choose\n"
            "}\n\n"
            "The returning version is more useful more often, because printing is "
            "something the caller can always do — but a value you never returned is gone.\n\n"
            "The rule of thumb: if the method computes anything, return it. Reserve void "
            "for methods whose whole purpose IS the side effect.\n\n"
            "COMMON MISTAKE: System.out.println(printTotal(2, 3)); — you can't print "
            "what void hands back, because it hands back nothing, and Java says so."
        ),
        "briefing": "Write both shapes of the same operation. banner() is void and prints ==== READY ====. total(int a, int b) returns a + b. In main(), call banner(), then print total(19, 23).",
        "hints": [
            "banner is declared void and does its printing inside itself — main just calls banner();",
            "total returns, so main wraps it: System.out.println(total(19, 23));",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void banner() {
        System.out.println("==== READY ====");
    }

    public static int total(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        banner();
        System.out.println(total(19, 23));
    }
}''',
        "wrong": [
            # made banner return a String but then never printed it
            '''public class Main {
    public static String banner() {
        return "==== READY ====";
    }

    public static int total(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        banner();
        System.out.println(total(19, 23));
    }
}''',
        ],
        "check": make_checker(
            expected_output="==== READY ====\n42",
            success_line="One method acts, one method answers. Knowing which you're writing is half of good design.",
            missing_hint="I need the banner line then 42.",
            mismatch_hint="Check banner prints its own line (it's void) and that total's result gets printed by main.",
            code_requires=["static void banner", "static int total"],
            code_requires_hint="banner must be void and print for itself; total must return a value for main to print.",
        ),
    },

    "t4m23": {
        "title": "Breaking It Down",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "decomposition — small methods instead of one long one",
        "teach": (
            "The real reason methods exist isn't reuse — it's that a long method is hard "
            "to think about. Splitting one job into named steps means each piece fits in "
            "your head, and the names document the process for free.\n\n"
            "WORKED EXAMPLE — one long method:\n"
            "public static void report(int n) {\n"
            "    System.out.println(\"--- REPORT ---\");\n"
            "    System.out.println(\"VALUE: \" + n);\n"
            "    System.out.println(\"DOUBLE: \" + (n * 2));\n"
            "    System.out.println(\"--- END ---\");\n"
            "}\n\n"
            "...and the decomposed version:\n"
            "public static void header()          { System.out.println(\"--- REPORT ---\"); }\n"
            "public static String line(String k, int v) { return k + \": \" + v; }\n"
            "public static void footer()          { System.out.println(\"--- END ---\"); }\n\n"
            "The second version is longer in total, and better: each piece is testable "
            "and reusable on its own, and report() becomes a list of steps you can read "
            "at a glance.\n\n"
            "Good sign you should split: you're about to write a comment explaining what "
            "the next few lines do. That comment is usually the method's name.\n\n"
            "COMMON MISTAKE: over-splitting. A method called addOne that does return n + 1 "
            "earns nothing. Split where there's a NAMEABLE idea."
        ),
        "briefing": (
            "Decompose a report into pieces. Write three methods: header() prints "
            "--- REPORT ---, line(String key, int value) returns key, a colon, a space "
            "and the value, and footer() prints --- END ---.\n\n"
            "In main(): call header(), print line(\"UNITS\", 12), print line(\"SPARE\", 3), "
            "then call footer()."
        ),
        "hints": [
            "header and footer are void and print for themselves; line returns a String for main to print.",
            "line's body is a single return: return key + \": \" + value;",
        ],
        "boilerplate": METHOD_BOILERPLATE,
        "solution": '''public class Main {
    public static void header() {
        System.out.println("--- REPORT ---");
    }

    public static String line(String key, int value) {
        return key + ": " + value;
    }

    public static void footer() {
        System.out.println("--- END ---");
    }

    public static void main(String[] args) {
        header();
        System.out.println(line("UNITS", 12));
        System.out.println(line("SPARE", 3));
        footer();
    }
}''',
        "wrong": [
            # one long method, no decomposition
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("--- REPORT ---");
        System.out.println("UNITS: 12");
        System.out.println("SPARE: 3");
        System.out.println("--- END ---");
    }
}''',
        ],
        "check": make_checker(
            expected_output="--- REPORT ---\nUNITS: 12\nSPARE: 3\n--- END ---",
            success_line="Four lines from three named pieces, and line() got reused immediately. That reuse is the payoff.",
            missing_hint="Output doesn't match. Check all four lines and that the methods sit beside main().",
            mismatch_hint="Check the exact dashes and spacing in the header and footer.",
            code_requires=["static void header", "static String line", "static void footer"],
            code_requires_hint="I need all three methods — header, line and footer — with main() calling them rather than printing everything itself.",
        ),
    },

    "t4m24": {
        "title": "Stack Overflow",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "recursion with no base case, and how to fix it",
        "teach": (
            "The call stack is finite. Each call pushes a frame holding that call's "
            "parameters and locals, and frames are only popped when a call RETURNS. A "
            "recursion that never returns fills it up.\n\n"
            "BROKEN:\n"
            "public static int drain(int n) {\n"
            "    return n + drain(n - 1);    // no base case — never stops\n"
            "}\n"
            "-> Exception in thread \"main\" java.lang.StackOverflowError\n\n"
            "Note it doesn't hang or loop silently — it dies, usually within a fraction "
            "of a second, after roughly ten thousand frames.\n\n"
            "THE FIX — a base case that stops the descent:\n"
            "public static int drain(int n) {\n"
            "    return n <= 0 ? 0 : n + drain(n - 1);\n"
            "}\n\n"
            "When you see StackOverflowError, there are only really two causes: a "
            "recursion with no reachable base case, or one whose argument isn't actually "
            "shrinking.\n\n"
            "COMMON MISTAKE: confusing it with OutOfMemoryError. That's the heap — too "
            "many objects. StackOverflowError is frames, and it means recursion."
        ),
        "briefing": "The editor holds a recursive method with no base case — running it dies with StackOverflowError. Add a base case so that countDown(int n) returns 0 when n is 0 or less, and otherwise returns n added to countDown(n - 1). In main(), print countDown(4). It should print 10.",
        "hints": [
            "A ternary gives you the base case in one line: return n <= 0 ? 0 : n + countDown(n - 1);",
            "4 + 3 + 2 + 1 + 0 is 10 — the base case contributes the final 0 and stops the descent.",
        ],
        "boilerplate": (
            "public class Main {\n"
            "    // no base case — this dies with StackOverflowError. Give it one.\n"
            "    public static int countDown(int n) {\n"
            "        return n + countDown(n - 1);\n"
            "    }\n\n"
            "    public static void main(String[] args) {\n"
            "        System.out.println(countDown(4));\n"
            "    }\n"
            "}"
        ),
        "solution": '''public class Main {
    public static int countDown(int n) {
        return n <= 0 ? 0 : n + countDown(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(countDown(4));
    }
}''',
        "check": make_checker(
            expected_output="10",
            success_line="10, and the stack unwound cleanly. Every recursion needs a way out — that's the whole lesson.",
            missing_hint="Still overflowing, or printing nothing. The method needs a base case that returns without recursing.",
            mismatch_hint="Check the sum: 4 + 3 + 2 + 1, with the base case adding 0 and stopping.",
            code_requires=["countDown(n - 1)", "?"],
            code_requires_hint="Keep the recursion and add a base case to it — a ternary on n <= 0 is the shortest way.",
        ),
    },

    "t4m25": {
        "title": "FINAL BOSS — Mission Control",
        "topic": 4, "topic_name": "Methods, Parameters and the Stack",
        "concept": "the entire topic, from an empty editor",
        "teach": (
            "Empty editor. Everything from Topic 4.\n\n"
            "FULL RECAP:\n"
            "public static void   name()            does something, returns nothing\n"
            "public static TYPE   name(params)      promises a value of that type\n"
            "parameters are copies, matched by position and type\n"
            "locals live in one frame; methods can't see each other's\n"
            "any method may call any other, including itself\n"
            "overloading: same name, different parameter list\n"
            "recursion: a base case, plus a call on a SMALLER problem\n"
            "return ends the method immediately\n\n"
            "Every call pushes a frame. Every return pops one. Nothing else to it."
        ),
        "briefing": (
            "Build mission control from scratch. Four methods plus main():\n\n"
            "- header(): void, prints == MISSION CONTROL ==\n"
            "- fuelNeeded(int distance): returns distance * 3\n"
            "- fuelNeeded(int distance, int crew): OVERLOAD — returns the one-parameter\n"
            "  result plus crew * 10, by calling the other overload\n"
            "- countLegs(int n): RECURSIVE — returns the sum of n down to 1, base case 0\n\n"
            "In main(): call header(), then print fuelNeeded(20), then fuelNeeded(20, 4), "
            "then countLegs(4).\n\n"
            "Expected output:\n"
            "== MISSION CONTROL ==\n"
            "60\n"
            "100\n"
            "10"
        ),
        "hints": [
            "The two-parameter overload must CALL the other one: return fuelNeeded(distance) + crew * 10;",
            "countLegs is the recursion: return n <= 0 ? 0 : n + countLegs(n - 1);",
        ],
        "boilerplate": "",
        "solution": '''public class Main {
    public static void header() {
        System.out.println("== MISSION CONTROL ==");
    }

    public static int fuelNeeded(int distance) {
        return distance * 3;
    }

    public static int fuelNeeded(int distance, int crew) {
        return fuelNeeded(distance) + crew * 10;
    }

    public static int countLegs(int n) {
        return n <= 0 ? 0 : n + countLegs(n - 1);
    }

    public static void main(String[] args) {
        header();
        System.out.println(fuelNeeded(20));
        System.out.println(fuelNeeded(20, 4));
        System.out.println(countLegs(4));
    }
}''',
        "wrong": [
            # overload duplicates the formula instead of calling the other one
            '''public class Main {
    public static void header() {
        System.out.println("== MISSION CONTROL ==");
    }

    public static int fuelNeeded(int distance) {
        return distance * 3;
    }

    public static int fuelNeeded(int distance, int crew) {
        return distance * 3 + crew * 10;
    }

    public static int countLegs(int n) {
        return n * (n + 1) / 2;
    }

    public static void main(String[] args) {
        header();
        System.out.println(fuelNeeded(20));
        System.out.println(fuelNeeded(20, 4));
        System.out.println(countLegs(4));
    }
}''',
        ],
        "check": make_checker(
            expected_output="== MISSION CONTROL ==\n60\n100\n10",
            success_line="Void, overloaded, composed and recursive — every shape a method can take, from an empty editor. Topic Four complete.",
            missing_hint="Output doesn't match, or it didn't compile. Check all four methods are class-level siblings of main().",
            mismatch_hint="Check fuelNeeded(20, 4) is 60 + 40 = 100, and countLegs(4) is 4+3+2+1 = 10.",
            code_requires=["public class Main", "static void header", "fuelNeeded(int distance)",
                           "fuelNeeded(int distance, int crew)", "fuelNeeded(distance)",
                           "countLegs(n - 1)"],
            code_requires_hint="I need both fuelNeeded overloads with the second CALLING the first, plus a genuinely recursive countLegs.",
        ),
    },
}
