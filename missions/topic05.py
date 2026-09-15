"""
Topic 5 — Basic I/O & File I/O.

Mission content for this topic. See missions/__init__.py for how the
per-topic modules are stitched into the single MISSIONS mapping the
server and the checkers use, and missions/common.py for the shared
checker factories and boilerplate constants.
"""

from .common import (
    BASIC_BOILERPLATE,
    FILE_IO_BOILERPLATE,
    SCANNER_BOILERPLATE,
    make_checker,
    make_file_checker,
)

MISSIONS = {

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
        "solution": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int clearance = sc.nextInt();
        System.out.println("CLEARANCE: " + clearance);
    }
}''',
        "wrong": [
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("CLEARANCE: 7");
    }
}''',
        ],
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
        "solution": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double rating = sc.nextDouble();
        System.out.println("RATING: " + rating);
    }
}''',
        "wrong": [
            '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String rating = sc.nextLine();
        System.out.println("RATING: " + rating);
    }
}''',
        ],
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
        "solution": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        System.out.println("MESSAGE: " + message);
    }
}''',
        "wrong": [
            '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = sc.next();
        System.out.println("MESSAGE: " + message);
    }
}''',
        ],
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
        "solution": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.next();
        System.out.println("FIRST WORD: " + first);
    }
}''',
        "wrong": [
            '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.nextLine();
        System.out.println("FIRST WORD: " + first);
    }
}''',
        ],
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
        "solution": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int clearance = sc.nextInt();
        sc.nextLine();
        String codename = sc.nextLine();
        System.out.println("CLEARANCE: " + clearance);
        System.out.println("CODENAME: " + codename);
    }
}''',
        "check": make_checker(
            expected_output="CLEARANCE: 7\nCODENAME: Ghost Protocol",
            success_line="Trap avoided. You'll hit this bug again in real coursework — now you'll recognise it in seconds.",
            missing_hint="I need both lines — CLEARANCE then CODENAME.",
            mismatch_hint="If CODENAME came out empty, you hit the buffer trap — add a throwaway sc.nextLine() after nextInt().",
            code_requires=["nextInt()", "nextLine()"],
            code_requires_hint="This one needs both nextInt() and nextLine().",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 2 — finishing console input: several values, prompts, booleans.
    # ---------------------------------------------------------------------

    "t5m6": {
        "title": "Two Readings",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "reading several values and computing with them",
        "teach": (
            "A Scanner reads values one after another, in the order they arrive. Call "
            "nextInt() twice and you get the first two numbers — Scanner remembers its "
            "place in the stream.\n\n"
            "WORKED EXAMPLE (input: 8 then 5):\n"
            "Scanner sc = new Scanner(System.in);\n"
            "int width = sc.nextInt();\n"
            "int height = sc.nextInt();\n"
            "System.out.println(\"AREA: \" + (width * height));\n"
            "-> prints: AREA: 40\n\n"
            "Values can be separated by spaces or by line breaks — Scanner treats both as "
            "gaps between tokens, so '8 5' on one line and 8 and 5 on two lines are read "
            "identically by nextInt().\n\n"
            "Note the brackets round width * height. The concatenation trap from Topic 3 "
            "is waiting for you here exactly as before.\n\n"
            "COMMON MISTAKE: reading into the same variable twice. int n = sc.nextInt(); "
            "n = sc.nextInt(); leaves you with only the second value — the first is gone."
        ),
        "briefing": "Two sensor readings arrive in sequence. Read both as ints, then print SUM: followed by them added together.",
        "inputs": ["12", "30"],
        "hints": [
            "Two separate variables, two calls: int first = sc.nextInt(); int second = sc.nextInt();",
            "System.out.println(\"SUM: \" + (first + second)); — brackets, or you'll get 1230.",
        ],
        "boilerplate": SCANNER_BOILERPLATE,
        "solution": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int first = sc.nextInt();
        int second = sc.nextInt();
        System.out.println("SUM: " + (first + second));
    }
}''',
        "wrong": [
            # no brackets — joins instead of adding
            '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int first = sc.nextInt();
        int second = sc.nextInt();
        System.out.println("SUM: " + first + second);
    }
}''',
        ],
        "check": make_checker(
            expected_output="SUM: 42",
            success_line="Both values read, in order, and added properly. Scanner keeps its own place in the stream.",
            missing_hint="I need SUM: 42 — both values read with nextInt() and added.",
            mismatch_hint="If you got SUM: 1230, the addition got swallowed by the joining — bracket it.",
            code_requires=["new Scanner", "nextInt()"],
            code_requires_hint="Read both values from the Scanner rather than hardcoding them.",
        ),
    },

    "t5m7": {
        "title": "Ask First",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "prompting before reading",
        "teach": (
            "A program that waits for input without saying so looks frozen. Always print "
            "a prompt first.\n\n"
            "Use print() rather than println() so the cursor stays on the same line as "
            "the question — it's what every command-line tool does.\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.print(\"Enter depth: \");\n"
            "int depth = sc.nextInt();\n"
            "System.out.println(\"DEPTH SET TO \" + depth);\n"
            "-> the user sees:\n"
            "Enter depth: 300\n"
            "DEPTH SET TO 300\n\n"
            "(The 300 on the first line is what they typed — it appears after your prompt "
            "because print() didn't end the line.)\n\n"
            "The prompt is part of your program's OUTPUT, so it counts towards what a "
            "mission checks. Get the wording exactly right.\n\n"
            "COMMON MISTAKE: prompting after reading. The order in your source is the "
            "order it happens — a prompt printed after nextInt() arrives far too late to "
            "be useful."
        ),
        "briefing": "Ask before you take. Print the prompt CODE: (using print, not println, so there's no line break), then read an int, then print on a new line ACCEPTED: followed by the value.",
        "inputs": ["4471"],
        "hints": [
            "System.out.print(\"CODE: \"); — print, so the cursor stays put.",
            "Then read with nextInt(), then System.out.println(\"ACCEPTED: \" + code);",
        ],
        "boilerplate": SCANNER_BOILERPLATE,
        "solution": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("CODE: ");
        int code = sc.nextInt();
        System.out.println("ACCEPTED: " + code);
    }
}''',
        "wrong": [
            # used println for the prompt, breaking the line
            '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("CODE: ");
        int code = sc.nextInt();
        System.out.println("ACCEPTED: " + code);
    }
}''',
        ],
        "check": make_checker(
            expected_output="CODE: ACCEPTED: 4471",
            success_line="Prompted, then read. Note the prompt is output too — it's part of what your program says.",
            missing_hint="I need the prompt CODE: then ACCEPTED: 4471.",
            mismatch_hint="Use print() for the prompt, not println() — the line break puts ACCEPTED on its own line.",
            code_requires=["System.out.print(", "nextInt()"],
            code_requires_hint="The prompt needs System.out.print (no ln) so the line isn't broken.",
        ),
    },

    "t5m8": {
        "title": "Yes Or No",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "Scanner + nextBoolean()",
        "teach": (
            "Scanner has a read method for every primitive type, booleans included.\n\n"
            "nextInt()      a whole number\n"
            "nextDouble()   a decimal\n"
            "nextBoolean()  true or false\n"
            "next()         one word\n"
            "nextLine()     the whole line\n\n"
            "WORKED EXAMPLE (input: true):\n"
            "boolean armed = sc.nextBoolean();\n"
            "System.out.println(\"ARMED: \" + armed);\n"
            "-> prints: ARMED: true\n\n"
            "nextBoolean() accepts the words true and false, in any capitalisation — TRUE "
            "and True both work. It does NOT accept yes, no, 1 or 0; anything else throws "
            "InputMismatchException.\n\n"
            "COMMON MISTAKE: reading a boolean with next() and comparing the String to "
            "\"true\" with ==. That's two bugs at once — the wrong read method, and the "
            "String comparison problem you'll meet properly in Topic 6."
        ),
        "briefing": "Read the arming status directly as a boolean. Read it with nextBoolean() and print ARMED: followed by the value.",
        "inputs": ["true"],
        "hints": [
            "boolean armed = sc.nextBoolean(); — the right method reads it as a real boolean, no conversion needed.",
            "System.out.println(\"ARMED: \" + armed);",
        ],
        "boilerplate": SCANNER_BOILERPLATE,
        "solution": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean armed = sc.nextBoolean();
        System.out.println("ARMED: " + armed);
    }
}''',
        "check": make_checker(
            expected_output="ARMED: true",
            success_line="Read as a real boolean, not as text that looks like one.",
            missing_hint="I need ARMED: true, read with nextBoolean().",
            mismatch_hint="Check the label and spacing — 'ARMED: ' then the value.",
            code_requires=["nextBoolean()"],
            code_requires_hint="This one specifically wants nextBoolean().",
        ),
    },

    "t5m9": {
        "title": "Mixed Intake",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "reading several different types in sequence",
        "teach": (
            "Real input is rarely all one type. Scanner handles a mixture — you just have "
            "to call the method matching whatever comes NEXT, in the right order.\n\n"
            "WORKED EXAMPLE (input: BOLT 4 1.5):\n"
            "String part = sc.next();        // BOLT\n"
            "int quantity = sc.nextInt();    // 4\n"
            "double price = sc.nextDouble(); // 1.5\n"
            "System.out.println(part + \" x\" + quantity + \" @ \" + price);\n"
            "-> prints: BOLT x4 @ 1.5\n\n"
            "Each call consumes exactly one token and leaves the rest waiting. Get the "
            "order wrong and you'll ask for an int when a word is next — "
            "InputMismatchException, immediately.\n\n"
            "Note this works because next(), nextInt() and nextDouble() all skip "
            "whitespace and stop at the end of a token. nextLine() is the odd one out, "
            "which is exactly why the buffer trap exists.\n\n"
            "COMMON MISTAKE: assuming the input is wrong when you get "
            "InputMismatchException. Nine times in ten it's your read ORDER, not the data."
        ),
        "briefing": "A supply record arrives as a word, then a whole number, then a decimal. Read all three with the matching methods and print PART: then the word, QTY: then the number, and COST: then the decimal — three lines.",
        "inputs": ["BOLT 4 1.5"],
        "hints": [
            "Three reads in order: next() for the word, nextInt() for the count, nextDouble() for the price.",
            "They're all on one line, but that doesn't matter — these methods skip whitespace between tokens.",
        ],
        "boilerplate": SCANNER_BOILERPLATE,
        "solution": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String part = sc.next();
        int quantity = sc.nextInt();
        double price = sc.nextDouble();
        System.out.println("PART: " + part);
        System.out.println("QTY: " + quantity);
        System.out.println("COST: " + price);
    }
}''',
        "check": make_checker(
            expected_output="PART: BOLT\nQTY: 4\nCOST: 1.5",
            success_line="Three types, one line of input, read in the right order. That ordering is the whole skill.",
            missing_hint="I need three lines: PART, QTY and COST.",
            mismatch_hint="Check each read method matches the kind of value coming next — word, int, then decimal.",
            code_requires=["next()", "nextInt()", "nextDouble()"],
            code_requires_hint="I need all three read methods: next(), nextInt() and nextDouble().",
        ),
    },

    "t5m10": {
        "title": "BOSS — Interrogation Terminal",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "prompts, mixed reads and the buffer trap — no starter code",
        "teach": (
            "No starter code — including the import, which you now write yourself.\n\n"
            "RECAP of console input:\n"
            "import java.util.Scanner;              at the top of the file\n"
            "Scanner sc = new Scanner(System.in);   inside main\n"
            "next() nextInt() nextDouble() nextBoolean()   one token each\n"
            "nextLine()                             the rest of the line\n"
            "sc.nextLine();                         throwaway, after nextInt(), to eat\n"
            "                                       the leftover newline\n"
            "System.out.print(\"prompt: \")           ask before you read\n\n"
            "The buffer trap is waiting in this one. You've been warned."
        ),
        "briefing": (
            "Write the interrogation terminal from scratch, import included.\n\n"
            "Print the prompt ID:  (with print, no line break), read an int.\n"
            "Print the prompt NAME:  (with print), read a full line of text.\n"
            "Then print two lines:\n"
            "AGENT 88\n"
            "NAME: Vesper Lynd\n\n"
            "The name arrives as a full line with a space in it, and it comes straight "
            "after a number. Plan accordingly."
        ),
        "inputs": ["88", "Vesper Lynd"],
        "hints": [
            "After nextInt(), add a throwaway sc.nextLine(); before reading the real line — otherwise the name comes out empty.",
            "The prompts are part of the output: \"ID: \" then \"NAME: \" both with print(), so they sit on the same line as what follows.",
        ],
        "boilerplate": "",
        "solution": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("NAME: ");
        String name = sc.nextLine();

        System.out.println("AGENT " + id);
        System.out.println("NAME: " + name);
    }
}''',
        "wrong": [
            # hit the buffer trap — name comes out empty
            '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        System.out.print("NAME: ");
        String name = sc.nextLine();

        System.out.println("AGENT " + id);
        System.out.println("NAME: " + name);
    }
}''',
        ],
        "check": make_checker(
            expected_output="ID: NAME: AGENT 88\nNAME: Vesper Lynd",
            success_line="Prompted, read, and the buffer trap dodged. Block complete.",
            missing_hint="Output doesn't match, or it didn't compile. Check both prompts use print() and that you wrote the import yourself.",
            mismatch_hint="If the name came out blank, that's the buffer trap — you need a throwaway sc.nextLine() after nextInt().",
            code_requires=["import java.util.Scanner", "nextInt()", "nextLine()"],
            code_requires_hint="I need the import, a nextInt() for the id, and nextLine() for the name — plus the throwaway read between them.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 3 — formatted output. printf is the other half of "I/O", and
    # it's what makes a decimal fit to be shown to a human.
    # ---------------------------------------------------------------------

    "t5m11": {
        "title": "Placeholders",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "System.out.printf with %s and %d",
        "teach": (
            "printf prints using a TEMPLATE. You write the line once, with placeholders "
            "where values go, and pass the values after it.\n\n"
            "%s   a String (or anything, converted to text)\n"
            "%d   a whole number (int, long)\n"
            "%n   a line break\n\n"
            "WORKED EXAMPLE:\n"
            "String unit = \"RAVEN\";\n"
            "int count = 3;\n"
            "System.out.printf(\"UNIT %s HAS %d MEMBERS%n\", unit, count);\n"
            "-> prints: UNIT RAVEN HAS 3 MEMBERS\n\n"
            "The placeholders are filled left to right from the arguments that follow. "
            "Compare it with the concatenated version:\n"
            "System.out.println(\"UNIT \" + unit + \" HAS \" + count + \" MEMBERS\");\n"
            "Same output; the printf version keeps the shape of the line visible instead "
            "of chopped into pieces by + signs.\n\n"
            "printf does NOT add a line break of its own — you have to include %n (or \\n) "
            "or everything runs together.\n\n"
            "COMMON MISTAKE: %d with a decimal value. printf is strict about types and "
            "throws IllegalFormatConversionException — use %f for decimals."
        ),
        "briefing": "Use a template instead of concatenation. With a String unit holding RAVEN and an int count holding 3, use ONE printf to print: UNIT RAVEN HAS 3 MEMBERS — remembering the line break.",
        "hints": [
            "System.out.printf(\"UNIT %s HAS %d MEMBERS%n\", unit, count);",
            "%s takes the String, %d takes the number, and %n ends the line.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        String unit = "RAVEN";
        int count = 3;
        System.out.printf("UNIT %s HAS %d MEMBERS%n", unit, count);
    }
}''',
        "wrong": [
            # concatenation, not printf
            '''public class Main {
    public static void main(String[] args) {
        String unit = "RAVEN";
        int count = 3;
        System.out.println("UNIT " + unit + " HAS " + count + " MEMBERS");
    }
}''',
        ],
        "check": make_checker(
            expected_output="UNIT RAVEN HAS 3 MEMBERS",
            success_line="One template, two values. The shape of the line stays readable in the source.",
            missing_hint="I need the line UNIT RAVEN HAS 3 MEMBERS, printed with printf.",
            mismatch_hint="Check the placeholders are in the right order and that you included %n for the line break.",
            code_requires=["printf", "%s", "%d"],
            code_requires_hint="This one wants System.out.printf with %s and %d placeholders, not concatenation.",
        ),
    },

    "t5m12": {
        "title": "Two Decimal Places",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "%.2f — rounding for display",
        "teach": (
            "This is the single most useful thing printf does. A double prints its full "
            "stored value, which is rarely what a human wants to read:\n\n"
            "double total = 19.99 * 3;\n"
            "System.out.println(total);\n"
            "-> prints: 59.97000000000001\n\n"
            "%f is the decimal placeholder, and a number between the % and the f says how "
            "many decimal places to show:\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.printf(\"TOTAL %.2f%n\", total);\n"
            "-> prints: TOTAL 59.97\n\n"
            "%.2f      two decimal places\n"
            "%.1f      one\n"
            "%.0f      none — rounds to a whole number\n\n"
            "Crucially this only changes how the value is DISPLAYED. The variable still "
            "holds the full messy number; you haven't rounded anything, you've chosen "
            "what to show. That's usually exactly right — round at the edges, keep full "
            "precision in the middle of a calculation.\n\n"
            "COMMON MISTAKE: assuming printf changed the value. It didn't. If you need "
            "the rounded number itself, that's Math.round."
        ),
        "briefing": "Tidy a messy total for display. Declare double total as 19.99 * 3 (which is 59.97000000000001), then use printf to print TOTAL 59.97 — two decimal places.",
        "hints": [
            "Let Java do the multiplication: double total = 19.99 * 3;",
            "System.out.printf(\"TOTAL %.2f%n\", total); — the .2 is what does the work.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        double total = 19.99 * 3;
        System.out.printf("TOTAL %.2f%n", total);
    }
}''',
        "wrong": [
            # plain println — prints the full ugly value
            '''public class Main {
    public static void main(String[] args) {
        double total = 19.99 * 3;
        System.out.println("TOTAL " + total);
    }
}''',
        ],
        "check": make_checker(
            expected_output="TOTAL 59.97",
            success_line="59.97. Full precision kept in the variable, two decimal places shown to the human.",
            missing_hint="I need the line TOTAL 59.97.",
            mismatch_hint="If you see a long trail of digits, use %.2f rather than plain concatenation.",
            code_requires=["printf", "%.2f", "19.99 * 3"],
            code_requires_hint="Calculate 19.99 * 3 and display it with printf using %.2f.",
        ),
    },

    "t5m13": {
        "title": "Format Without Printing",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "String.format builds the text instead of printing it",
        "teach": (
            "String.format uses exactly the same placeholders as printf, but instead of "
            "printing, it RETURNS the finished String.\n\n"
            "WORKED EXAMPLE:\n"
            "double rate = 0.8756;\n"
            "String line = String.format(\"RATE %.1f%%\", rate * 100);\n"
            "System.out.println(line);\n"
            "-> prints: RATE 87.6%\n\n"
            "(Note %% — that's how you print a literal percent sign, since a single % "
            "starts a placeholder.)\n\n"
            "This is the version you want whenever the text isn't going straight to the "
            "screen: writing it to a file, returning it from a method, storing it, or "
            "using it twice. printf is String.format plus an immediate print.\n\n"
            "COMMON MISTAKE: calling String.format and ignoring the result. It doesn't "
            "print, and it doesn't modify anything — if you don't store or use what comes "
            "back, nothing happened at all."
        ),
        "briefing": "Build the line before you print it. Declare double rate as 0.8756, then use String.format to build a String called line reading RATE 87.6% — one decimal place, with a literal percent sign — and print it.",
        "hints": [
            "Multiply by 100 inside the format call: String.format(\"RATE %.1f%%\", rate * 100)",
            "Two percent signs in a row, %%, produce one literal % in the output.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        double rate = 0.8756;
        String line = String.format("RATE %.1f%%", rate * 100);
        System.out.println(line);
    }
}''',
        "check": make_checker(
            expected_output="RATE 87.6%",
            success_line="Built as a String first, printed second. That's the version you'll want the moment text goes anywhere but the screen.",
            missing_hint="I need the line RATE 87.6% built with String.format.",
            mismatch_hint="Check the rounding (one decimal place) and that the percent sign appears — you need %% for a literal one.",
            code_requires=["String.format"],
            code_requires_hint="This one wants String.format to build the text, then a separate println to show it.",
        ),
    },

    "t5m14": {
        "title": "Aligned Columns",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "field widths in printf",
        "teach": (
            "A number between the % and the letter sets a minimum WIDTH, padding with "
            "spaces so columns line up even when the values are different lengths.\n\n"
            "%8s    right-align text in 8 characters\n"
            "%-8s   LEFT-align text in 8 characters (the minus flips it)\n"
            "%5d    right-align a number in 5 characters\n\n"
            "WORKED EXAMPLE:\n"
            "System.out.printf(\"%-8s%5d%n\", \"BOLTS\", 120);\n"
            "System.out.printf(\"%-8s%5d%n\", \"NUTS\", 7);\n"
            "-> prints:\n"
            "BOLTS     120\n"
            "NUTS        7\n\n"
            "The numbers line up on their right edge because each got 5 characters "
            "whatever its length. That's something \\t can't reliably do — a tab jumps to "
            "the next stop, so a long value pushes the column out of alignment.\n\n"
            "COMMON MISTAKE: expecting the width to TRUNCATE. It's a minimum, not a "
            "maximum — a value longer than the width prints in full and pushes the rest "
            "of the line along."
        ),
        "briefing": "Line up a two-row stock table. Using printf with a left-aligned 8-character text column and a right-aligned 5-character number column, print:\nBOLTS     120\nNUTS        7",
        "hints": [
            "The format string is \"%-8s%5d%n\" — minus for left-aligned text, plain number for right-aligned digits.",
            "Two printf calls with the same format string and different values.",
        ],
        "boilerplate": BASIC_BOILERPLATE,
        "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.printf("%-8s%5d%n", "BOLTS", 120);
        System.out.printf("%-8s%5d%n", "NUTS", 7);
    }
}''',
        "wrong": [
            # typed the spaces by hand instead of using widths
            '''public class Main {
    public static void main(String[] args) {
        System.out.println("BOLTS     120");
        System.out.println("NUTS        7");
    }
}''',
        ],
        "check": make_checker(
            expected_output="BOLTS     120\nNUTS        7",
            success_line="Both columns aligned, whatever the value lengths. That's what field widths buy you.",
            missing_hint="I need the two rows, with the numbers right-aligned under each other.",
            mismatch_hint="Check the widths — 8 for the left-aligned name, 5 for the right-aligned number.",
            code_requires=["printf", "%-8s", "%5d"],
            code_requires_hint="Use printf with the width specifiers %-8s and %5d rather than typing the spaces yourself.",
        ),
    },

    "t5m15": {
        "title": "BOSS — Formatted Report",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "input plus formatted output — no starter code",
        "teach": (
            "No starter code. RECAP of formatting:\n\n"
            "%s    text          %d   whole number      %f   decimal\n"
            "%.2f  two decimals  %-8s left-aligned      %5d right-aligned\n"
            "%%    a literal %   %n   line break\n\n"
            "printf prints it; String.format hands it back. Same placeholders in both.\n\n"
            "Remember printf adds no line break of its own."
        ),
        "briefing": (
            "Write the formatted report from scratch, import included.\n\n"
            "Read a word (the part name), then an int (the quantity), then a double (the "
            "unit price).\n\n"
            "Then print exactly two lines using printf:\n"
            "PART    x  12\n"
            "COST 47.40\n\n"
            "The first line is the part name left-aligned in 8 characters, then 'x', then "
            "the quantity right-aligned in 4. The second is the word COST then the "
            "quantity times the price, to two decimal places."
        ),
        "inputs": ["PART 12 3.95"],
        "hints": [
            "First line format: \"%-8sx%4d%n\" with the name and quantity.",
            "Second line: \"COST %.2f%n\" with quantity * price — 12 x 3.95 is 47.4, shown as 47.40.",
        ],
        "boilerplate": "",
        "solution": '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int quantity = sc.nextInt();
        double price = sc.nextDouble();

        System.out.printf("%-8sx%4d%n", name, quantity);
        System.out.printf("COST %.2f%n", quantity * price);
    }
}''',
        "wrong": [
            # plain concatenation — loses both the alignment and the 2dp
            '''import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int quantity = sc.nextInt();
        double price = sc.nextDouble();

        System.out.println(name + "x" + quantity);
        System.out.println("COST " + quantity * price);
    }
}''',
        ],
        "check": make_checker(
            expected_output="PART    x  12\nCOST 47.40",
            success_line="Read, calculated, and formatted for a human to read. Block complete.",
            missing_hint="Output doesn't match, or it didn't compile. Check the import, the three reads, and both printf calls.",
            mismatch_hint="Check the field widths on line one, and that line two shows exactly two decimal places.",
            code_requires=["import java.util.Scanner", "printf", "%.2f"],
            code_requires_hint="I need the Scanner import, the three reads, and printf with %.2f for the cost.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 4 — writing files. These are graded on the file the code
    # actually left behind (see make_file_checker), not on a printed claim
    # that it wrote one.
    # ---------------------------------------------------------------------

    "t5m16": {
        "title": "First Write",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "FileWriter — putting text on disk",
        "teach": (
            "Everything you've printed so far vanished when the program ended. A file "
            "persists.\n\n"
            "The simplest way to write one is FileWriter. Three steps, always:\n"
            "1. open it     FileWriter out = new FileWriter(\"notes.txt\");\n"
            "2. write       out.write(\"some text\");\n"
            "3. CLOSE it    out.close();\n\n"
            "WORKED EXAMPLE:\n"
            "FileWriter out = new FileWriter(\"notes.txt\");\n"
            "out.write(\"FIRST LINE\");\n"
            "out.close();\n"
            "-> creates notes.txt containing: FIRST LINE\n\n"
            "Two things to know about that close(). It isn't politeness — writes are "
            "BUFFERED, meaning Java holds them in memory and writes them out in batches. "
            "Skip close() and your text may never reach the disk at all. It also releases "
            "the file so other programs can use it.\n\n"
            "Notice the method is write(), not println() — FileWriter has no println, and "
            "write() adds no line break of its own.\n\n"
            "You also need two new things at the top of the file: import java.io.*; and "
            "throws IOException on main. Writing to a disk can fail in ways printing "
            "can't — the disk is full, the file is read-only — and Java forces you to "
            "acknowledge that. Topic 10 covers handling it properly; for now, throws "
            "IOException passes the problem upwards.\n\n"
            "COMMON MISTAKE: forgetting close() and finding an empty file. The code looked "
            "like it worked, printed no error, and wrote nothing."
        ),
        "briefing": "Commit something to disk. Write the text ARCHIVE ENTRY ONE into a file called log.txt using FileWriter, and close it properly. Then print SAVED to the console.",
        "hints": [
            "FileWriter out = new FileWriter(\"log.txt\"); then out.write(\"ARCHIVE ENTRY ONE\");",
            "Don't forget out.close(); — without it the file can end up empty. Then System.out.println(\"SAVED\");",
        ],
        "boilerplate": FILE_IO_BOILERPLATE,
        "solution": '''import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter out = new FileWriter("log.txt");
        out.write("ARCHIVE ENTRY ONE");
        out.close();
        System.out.println("SAVED");
    }
}''',
        "wrong": [
            # claimed success without writing anything
            '''import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("SAVED");
    }
}''',
            # forgot close() — the buffered text never reaches the disk
            '''import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter out = new FileWriter("log.txt");
        out.write("ARCHIVE ENTRY ONE");
        System.out.println("SAVED");
    }
}''',
        ],
        "check": make_file_checker(
            filename="log.txt",
            expected_contents="ARCHIVE ENTRY ONE",
            expected_output="SAVED",
            success_line="Written and closed. That text outlived your program — Cipher checked the file itself, not your word for it.",
            missing_hint="There's no log.txt after your program ran. Check you created a FileWriter for that exact filename — and that you closed it.",
            mismatch_hint="log.txt exists but its contents are wrong. It should hold exactly ARCHIVE ENTRY ONE.",
            code_requires=["FileWriter", "close()"],
            code_requires_hint="I need a real FileWriter, and a close() call so the text actually reaches the disk.",
        ),
    },

    "t5m17": {
        "title": "Writing Lines",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "PrintWriter — println() for files",
        "teach": (
            "FileWriter's write() has no idea what a line is. To get line breaks you'd "
            "have to add \\n yourself every time.\n\n"
            "PrintWriter wraps a FileWriter and gives you the methods you already know — "
            "println(), print(), printf() — writing to a file instead of the screen.\n\n"
            "WORKED EXAMPLE:\n"
            "PrintWriter out = new PrintWriter(new FileWriter(\"report.txt\"));\n"
            "out.println(\"LINE ONE\");\n"
            "out.println(\"LINE TWO\");\n"
            "out.close();\n"
            "-> creates report.txt containing:\n"
            "LINE ONE\n"
            "LINE TWO\n\n"
            "Read that first line from the inside out: a FileWriter opens the file, and a "
            "PrintWriter wraps it to add the convenient methods. This nesting of streams "
            "is a pattern you'll see all over Java's I/O.\n\n"
            "Everything you know about println() transfers exactly — including printf() "
            "with %s and %.2f, which is how formatted reports get written to disk.\n\n"
            "COMMON MISTAKE: still forgetting close(). PrintWriter buffers just as "
            "eagerly as FileWriter, and silently loses your output the same way."
        ),
        "briefing": "Write a two-line manifest. Using a PrintWriter, write ALPHA on the first line and BRAVO on the second into manifest.txt, then close it. Print DONE to the console.",
        "hints": [
            "PrintWriter out = new PrintWriter(new FileWriter(\"manifest.txt\")); — the FileWriter goes inside.",
            "Then out.println(\"ALPHA\"); out.println(\"BRAVO\"); out.close();",
        ],
        "boilerplate": FILE_IO_BOILERPLATE,
        "solution": '''import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("manifest.txt"));
        out.println("ALPHA");
        out.println("BRAVO");
        out.close();
        System.out.println("DONE");
    }
}''',
        "wrong": [
            # printed to the console instead of to the file
            '''import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("ALPHA");
        System.out.println("BRAVO");
        System.out.println("DONE");
    }
}''',
        ],
        "check": make_file_checker(
            filename="manifest.txt",
            expected_contents="ALPHA\nBRAVO",
            expected_output="DONE",
            success_line="Two lines, written with the same println you've used since mission one — just pointed at a file.",
            missing_hint="There's no manifest.txt. Check the filename, and that you're writing to the PrintWriter rather than to System.out.",
            mismatch_hint="manifest.txt exists but doesn't hold ALPHA then BRAVO on separate lines.",
            code_requires=["PrintWriter", "close()"],
            code_requires_hint="This one wants a PrintWriter (wrapping a FileWriter), with println() for each line.",
        ),
    },

    "t5m18": {
        "title": "Closing Itself",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "try-with-resources",
        "teach": (
            "Forgetting close() is such a common bug that Java added syntax to make it "
            "impossible. Put the resource in brackets after try, and it closes itself the "
            "moment the block ends — whatever happens inside.\n\n"
            "WORKED EXAMPLE:\n"
            "try (PrintWriter out = new PrintWriter(new FileWriter(\"data.txt\"))) {\n"
            "    out.println(\"SAFE\");\n"
            "}\n"
            "// out is already closed here — no close() call anywhere\n\n"
            "This is called TRY-WITH-RESOURCES, and it's the form you should use for "
            "every file from now on. The advantage isn't just saving a line: if something "
            "goes wrong midway through writing, a manual close() further down the method "
            "gets skipped and the file is left open and incomplete. The automatic one "
            "still runs.\n\n"
            "Anything that needs closing can go in those brackets — writers, readers, "
            "Scanners, database connections.\n\n"
            "(You'll meet the rest of try in Topic 10, where it's about catching errors. "
            "This form needs no catch block at all.)\n\n"
            "COMMON MISTAKE: declaring the writer outside the brackets — try (out) { } "
            "won't compile. The declaration itself goes inside."
        ),
        "briefing": "Write a file that closes itself. Using try-with-resources, write the single line AUTO CLOSED into safe.txt with a PrintWriter — with no close() call anywhere in your code. Print OK to the console afterwards.",
        "hints": [
            "The declaration goes inside the brackets: try (PrintWriter out = new PrintWriter(new FileWriter(\"safe.txt\"))) {",
            "Write your line inside the block, then close the block with } — no close() call needed at all.",
        ],
        "boilerplate": FILE_IO_BOILERPLATE,
        "solution": '''import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("safe.txt"))) {
            out.println("AUTO CLOSED");
        }
        System.out.println("OK");
    }
}''',
        "wrong": [
            # manual close, not try-with-resources
            '''import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("safe.txt"));
        out.println("AUTO CLOSED");
        out.close();
        System.out.println("OK");
    }
}''',
        ],
        "check": make_file_checker(
            filename="safe.txt",
            expected_contents="AUTO CLOSED",
            expected_output="OK",
            success_line="Closed automatically, at the brace. Use this form for every file from here on.",
            missing_hint="There's no safe.txt. Check the filename and that the writer is declared inside the try brackets.",
            mismatch_hint="safe.txt exists but doesn't contain exactly AUTO CLOSED.",
            code_requires=["try (", "PrintWriter"],
            code_requires_hint="This one specifically wants try-with-resources — the writer declared inside try ( ... ) with no close() call.",
        ),
    },

    "t5m19": {
        "title": "Reading It Back",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "Scanner on a File",
        "teach": (
            "The Scanner you've used for keyboard input reads files too. The only "
            "difference is what you hand it: System.in for the console, or a File for a "
            "file on disk.\n\n"
            "WORKED EXAMPLE:\n"
            "Scanner fileIn = new Scanner(new File(\"orders.txt\"));\n"
            "String first = fileIn.nextLine();\n"
            "System.out.println(first);\n"
            "fileIn.close();\n\n"
            "Every method you already know works exactly the same — nextLine(), nextInt(), "
            "next(), nextDouble(). The buffer trap behaves identically too.\n\n"
            "You need both imports for this: java.io.* for File, and java.util.Scanner "
            "for the Scanner itself.\n\n"
            "Reading a file whose length you don't know needs a loop, which arrives in "
            "Topic 7. For now the missions tell you how many lines to expect, so you can "
            "call nextLine() that many times.\n\n"
            "COMMON MISTAKE: new Scanner(\"orders.txt\") without the File. That compiles "
            "and is completely wrong — it makes a Scanner over the TEXT 'orders.txt', so "
            "next() hands you back the filename instead of the file's contents."
        ),
        "briefing": "A two-line orders file is already on disk. Open orders.txt with a Scanner, read both lines, and print the first with the label FIRST: and the second with SECOND:.",
        "seed_files": {"orders.txt": "HOLD POSITION\nAWAIT SIGNAL\n"},
        "hints": [
            "Scanner fileIn = new Scanner(new File(\"orders.txt\")); — the File wrapper is what makes it read the file.",
            "Two nextLine() calls, in order, then print each with its label.",
        ],
        "boilerplate": (
            "import java.io.*;\n"
            "import java.util.Scanner;\n\n"
            "public class Main {\n"
            "    public static void main(String[] args) throws IOException {\n"
            "        // YOUR CODE HERE\n"
            "    }\n"
            "}"
        ),
        "solution": '''import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner fileIn = new Scanner(new File("orders.txt"));
        String first = fileIn.nextLine();
        String second = fileIn.nextLine();
        fileIn.close();
        System.out.println("FIRST: " + first);
        System.out.println("SECOND: " + second);
    }
}''',
        "wrong": [
            # hardcoded the contents instead of reading the file
            '''import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("FIRST: HOLD POSITION");
        System.out.println("SECOND: AWAIT SIGNAL");
    }
}''',
        ],
        "check": make_checker(
            expected_output="FIRST: HOLD POSITION\nSECOND: AWAIT SIGNAL",
            success_line="Read straight off the disk. Same Scanner, same methods — only the source changed.",
            missing_hint="I need both lines labelled FIRST: and SECOND:, read from orders.txt.",
            mismatch_hint="Check the labels and that you read the two lines in order.",
            code_requires=["new Scanner(new File", "nextLine()"],
            code_requires_hint="Open the file with new Scanner(new File(\"orders.txt\")) and read it — don't type the contents in yourself.",
        ),
    },

    "t5m20": {
        "title": "BOSS — Log Writer",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "read the console, write a file — no starter code",
        "teach": (
            "No starter code, imports included. RECAP of file writing:\n\n"
            "import java.io.*;                          File, writers, IOException\n"
            "public static void main(...) throws IOException\n"
            "new FileWriter(\"f.txt\")                    opens for writing\n"
            "new PrintWriter(new FileWriter(\"f.txt\"))   adds println/printf\n"
            "try ( ... ) { }                            closes it for you\n"
            "new Scanner(new File(\"f.txt\"))             reads it back\n\n"
            "A file mission is graded on the FILE, not on what you printed. Saying you "
            "saved it isn't saving it."
        ),
        "briefing": (
            "Write the log writer from scratch.\n\n"
            "Read a codename (one word) and a clearance level (an int) from the console.\n\n"
            "Then write TWO lines into a file called agent.txt using try-with-resources:\n"
            "CODENAME: VESPER\n"
            "CLEARANCE: 7\n\n"
            "Finally print LOGGED to the console. The console gets one line; the file "
            "gets two."
        ),
        "inputs": ["VESPER 7"],
        "hints": [
            "Read both values first with sc.next() and sc.nextInt(), then open the file.",
            "Inside the try block, out.println(\"CODENAME: \" + codename); and the same shape for the clearance line.",
        ],
        "boilerplate": "",
        "solution": '''import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String codename = sc.next();
        int clearance = sc.nextInt();

        try (PrintWriter out = new PrintWriter(new FileWriter("agent.txt"))) {
            out.println("CODENAME: " + codename);
            out.println("CLEARANCE: " + clearance);
        }

        System.out.println("LOGGED");
    }
}''',
        "wrong": [
            # wrote the report to the console instead of the file
            '''import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String codename = sc.next();
        int clearance = sc.nextInt();

        System.out.println("CODENAME: " + codename);
        System.out.println("CLEARANCE: " + clearance);
        System.out.println("LOGGED");
    }
}''',
        ],
        "check": make_file_checker(
            filename="agent.txt",
            expected_contents="CODENAME: VESPER\nCLEARANCE: 7",
            expected_output="LOGGED",
            success_line="Console in, file out, closed automatically. Block complete.",
            missing_hint="There's no agent.txt. The two report lines go into the FILE — only LOGGED goes to the console.",
            mismatch_hint="agent.txt exists but its two lines aren't right. Check the labels and that the values came from the input.",
            code_requires=["import java.io", "Scanner", "try ("],
            code_requires_hint="I need both imports, a Scanner reading the console, and try-with-resources around the PrintWriter.",
        ),
    },

    # ---------------------------------------------------------------------
    # Batch 5 — BufferedReader, appending, numbers from disk, and a full
    # round trip. Final boss closes the topic.
    # ---------------------------------------------------------------------

    "t5m21": {
        "title": "The Other Reader",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "BufferedReader and readLine()",
        "teach": (
            "There's a second way to read a file, and you'll meet it constantly in real "
            "Java code: BufferedReader.\n\n"
            "WORKED EXAMPLE:\n"
            "BufferedReader reader = new BufferedReader(new FileReader(\"orders.txt\"));\n"
            "String line = reader.readLine();\n"
            "System.out.println(line);\n"
            "reader.close();\n\n"
            "Same nesting pattern as writing: a FileReader opens the file, a "
            "BufferedReader wraps it to add readLine().\n\n"
            "Scanner vs BufferedReader — when to use which:\n"
            "  Scanner        convenient. nextInt(), nextDouble(), token by token.\n"
            "  BufferedReader faster on big files, but lines only. Everything comes\n"
            "                 back as a String, so numbers need converting yourself\n"
            "                 with Integer.parseInt(line).\n\n"
            "The detail that matters most: readLine() returns null when there are no "
            "lines left. That's how you know a file has ended — and it's what a reading "
            "loop tests, once you have loops in Topic 7.\n\n"
            "COMMON MISTAKE: expecting readLine() to include the line break. It doesn't — "
            "the newline is stripped off, which is exactly what you want."
        ),
        "briefing": "Read a file the other way. A two-line file orders.txt is on disk. Open it with a BufferedReader, read both lines with readLine(), and print them with the labels ONE: and TWO:.",
        "seed_files": {"orders.txt": "HOLD POSITION\nAWAIT SIGNAL\n"},
        "hints": [
            "BufferedReader reader = new BufferedReader(new FileReader(\"orders.txt\"));",
            "Two readLine() calls into two String variables, then print each with its label.",
        ],
        "boilerplate": FILE_IO_BOILERPLATE,
        "solution": '''import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("orders.txt"));
        String one = reader.readLine();
        String two = reader.readLine();
        reader.close();

        System.out.println("ONE: " + one);
        System.out.println("TWO: " + two);
    }
}''',
        "wrong": [
            # read the same line twice into both variables
            '''import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("orders.txt"));
        String one = reader.readLine();
        reader.close();

        System.out.println("ONE: " + one);
        System.out.println("TWO: " + one);
    }
}''',
        ],
        "check": make_checker(
            expected_output="ONE: HOLD POSITION\nTWO: AWAIT SIGNAL",
            success_line="Both lines, read with the reader you'll see most often in real code. readLine() strips the newline for you.",
            missing_hint="I need both lines labelled ONE: and TWO:, read from orders.txt.",
            mismatch_hint="If both lines are the same, you only called readLine() once — each call advances to the next line.",
            code_requires=["BufferedReader", "readLine()"],
            code_requires_hint="This one specifically wants a BufferedReader with readLine(), not a Scanner.",
        ),
    },

    "t5m22": {
        "title": "Append, Don't Erase",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "FileWriter's append flag",
        "teach": (
            "By default, opening a file for writing DESTROYS whatever was in it. The old "
            "contents are gone before you write a single character.\n\n"
            "new FileWriter(\"log.txt\")          wipes it first\n"
            "new FileWriter(\"log.txt\", true)    adds to the end\n\n"
            "That second argument is the APPEND flag.\n\n"
            "WORKED EXAMPLE — log.txt already contains 'ENTRY ONE':\n"
            "PrintWriter out = new PrintWriter(new FileWriter(\"log.txt\", true));\n"
            "out.println(\"ENTRY TWO\");\n"
            "out.close();\n"
            "-> log.txt now contains:\n"
            "ENTRY ONE\n"
            "ENTRY TWO\n\n"
            "Without the true, the file would hold only ENTRY TWO, and ENTRY ONE would be "
            "unrecoverable.\n\n"
            "This is the difference between a log file and a scratch file, and it's worth "
            "being careful with — there's no undo, no recycle bin, and no warning.\n\n"
            "COMMON MISTAKE: testing a program that writes a file, again and again, and "
            "not realising each run silently wipes the last run's output. If your file "
            "only ever has one line in it, this is why."
        ),
        "briefing": "Add to a log without destroying it. log.txt already contains the line ENTRY ONE. Append the line ENTRY TWO to it, so the file ends up holding both lines in order. Print APPENDED to the console.",
        "seed_files": {"log.txt": "ENTRY ONE\n"},
        "hints": [
            "The append flag is a second argument to FileWriter: new FileWriter(\"log.txt\", true)",
            "Without that true, you'll wipe ENTRY ONE and Cipher will see only one line in the file.",
        ],
        "boilerplate": FILE_IO_BOILERPLATE,
        "solution": '''import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("log.txt", true))) {
            out.println("ENTRY TWO");
        }
        System.out.println("APPENDED");
    }
}''',
        "wrong": [
            # no append flag — wipes ENTRY ONE
            '''import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("log.txt"))) {
            out.println("ENTRY TWO");
        }
        System.out.println("APPENDED");
    }
}''',
        ],
        "check": make_file_checker(
            filename="log.txt",
            expected_contents="ENTRY ONE\nENTRY TWO",
            expected_output="APPENDED",
            success_line="Both entries survived. One boolean is all that stands between a log file and a scratch file.",
            missing_hint="There's no log.txt after your program ran — check the filename.",
            mismatch_hint="If only ENTRY TWO is in the file, the append flag is missing — new FileWriter(\"log.txt\", true).",
            code_requires=["true"],
            code_requires_hint="You need the append flag: new FileWriter(\"log.txt\", true) — the second argument.",
        ),
    },

    "t5m23": {
        "title": "Numbers On Disk",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "reading numeric data from a file",
        "teach": (
            "A file is just text — but a Scanner over a file has all the same typed read "
            "methods, so numbers come back as numbers rather than as Strings you'd have "
            "to convert.\n\n"
            "WORKED EXAMPLE — readings.txt contains '15 27':\n"
            "Scanner fileIn = new Scanner(new File(\"readings.txt\"));\n"
            "int first = fileIn.nextInt();\n"
            "int second = fileIn.nextInt();\n"
            "System.out.println(\"SUM: \" + (first + second));\n"
            "fileIn.close();\n"
            "-> prints: SUM: 42\n\n"
            "Whether the numbers are on one line separated by spaces, or on separate "
            "lines, makes no difference to nextInt() — whitespace is whitespace.\n\n"
            "With a BufferedReader you'd get the line as a String and convert it yourself:\n"
            "int value = Integer.parseInt(reader.readLine());\n"
            "Worth recognising, because you'll see it everywhere.\n\n"
            "COMMON MISTAKE: assuming the file's values are trustworthy. A stray letter "
            "in a file of numbers throws InputMismatchException, and a real program has "
            "to handle that — which is Topic 10."
        ),
        "briefing": "Process telemetry from disk. The file readings.txt holds three whole numbers. Read all three with a Scanner, and print TOTAL: followed by their sum, then MAX: followed by the largest (use Math.max twice).",
        "seed_files": {"readings.txt": "15 27 9\n"},
        "hints": [
            "Three nextInt() calls into three variables — the spacing in the file doesn't matter.",
            "Math.max only compares two at a time, so nest it: Math.max(a, Math.max(b, c))",
        ],
        "boilerplate": (
            "import java.io.*;\n"
            "import java.util.Scanner;\n\n"
            "public class Main {\n"
            "    public static void main(String[] args) throws IOException {\n"
            "        // YOUR CODE HERE\n"
            "    }\n"
            "}"
        ),
        "solution": '''import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner fileIn = new Scanner(new File("readings.txt"));
        int a = fileIn.nextInt();
        int b = fileIn.nextInt();
        int c = fileIn.nextInt();
        fileIn.close();

        System.out.println("TOTAL: " + (a + b + c));
        System.out.println("MAX: " + Math.max(a, Math.max(b, c)));
    }
}''',
        "wrong": [
            # hardcoded rather than reading the file
            '''import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("TOTAL: 51");
        System.out.println("MAX: 27");
    }
}''',
        ],
        "check": make_checker(
            expected_output="TOTAL: 51\nMAX: 27",
            success_line="51 and 27, both computed from data your program had never seen until it opened the file.",
            missing_hint="I need TOTAL: and MAX: lines, computed from the three numbers in readings.txt.",
            mismatch_hint="Check the sum (15 + 27 + 9) and that the nested Math.max compares all three.",
            code_requires=["new Scanner(new File", "nextInt()", "Math.max"],
            code_requires_hint="Read the three values from the file with nextInt(), and find the largest with Math.max.",
        ),
    },

    "t5m24": {
        "title": "Round Trip",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "writing a file and reading it back in one program",
        "teach": (
            "Writing and reading are two halves of the same skill, and putting them "
            "together is how data actually survives between runs of a program.\n\n"
            "The rule that catches everyone: you must CLOSE the writer before opening the "
            "reader. Writes are buffered, so until close() (or the end of a "
            "try-with-resources block) the text may still be sitting in memory — and your "
            "reader will find an empty file.\n\n"
            "WORKED EXAMPLE:\n"
            "try (PrintWriter out = new PrintWriter(new FileWriter(\"note.txt\"))) {\n"
            "    out.println(\"STORED\");\n"
            "}                                   // closed HERE — text is on disk now\n"
            "\n"
            "Scanner in = new Scanner(new File(\"note.txt\"));\n"
            "System.out.println(\"READ BACK: \" + in.nextLine());\n"
            "in.close();\n"
            "-> prints: READ BACK: STORED\n\n"
            "That closing brace is doing real work. Move the reading inside the try block "
            "and you'd likely read nothing at all.\n\n"
            "COMMON MISTAKE: opening the reader while the writer is still open, then "
            "spending an hour convinced the write failed. It didn't — it just hadn't "
            "been flushed yet."
        ),
        "briefing": "Store something and retrieve it. Using try-with-resources, write the line SIGNAL STORED into cache.txt. After the block closes, open cache.txt with a Scanner and print RETRIEVED: followed by what you read back.",
        "hints": [
            "Two separate stages: the try block writes and closes, then the Scanner opens the same filename.",
            "The reading must happen AFTER the try block's closing brace — not inside it.",
        ],
        "boilerplate": (
            "import java.io.*;\n"
            "import java.util.Scanner;\n\n"
            "public class Main {\n"
            "    public static void main(String[] args) throws IOException {\n"
            "        // YOUR CODE HERE\n"
            "    }\n"
            "}"
        ),
        "solution": '''import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("cache.txt"))) {
            out.println("SIGNAL STORED");
        }

        Scanner in = new Scanner(new File("cache.txt"));
        String recovered = in.nextLine();
        in.close();

        System.out.println("RETRIEVED: " + recovered);
    }
}''',
        "wrong": [
            # never wrote anything, just printed the expected line
            '''import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("RETRIEVED: SIGNAL STORED");
    }
}''',
        ],
        "check": make_file_checker(
            filename="cache.txt",
            expected_contents="SIGNAL STORED",
            expected_output="RETRIEVED: SIGNAL STORED",
            success_line="Written, closed, reopened, read. That's data outliving the moment it was created.",
            missing_hint="There's no cache.txt. The program has to genuinely write the file before reading it back.",
            mismatch_hint="cache.txt exists but doesn't hold exactly SIGNAL STORED.",
            code_requires=["PrintWriter", "new Scanner(new File"],
            code_requires_hint="I need a real write (PrintWriter) and a real read-back (Scanner over the File) — not a printed line.",
        ),
    },

    "t5m25": {
        "title": "FINAL BOSS — Data Terminal",
        "topic": 5, "topic_name": "Basic I/O & File I/O",
        "concept": "the entire topic, from an empty editor",
        "teach": (
            "Empty editor. Everything from Topic 5.\n\n"
            "FULL RECAP:\n"
            "CONSOLE IN   new Scanner(System.in) — next nextInt nextDouble nextBoolean\n"
            "             nextLine, plus a throwaway nextLine() after nextInt()\n"
            "CONSOLE OUT  println, print, printf(\"%s %d %.2f%n\", ...)\n"
            "FILE OUT     new PrintWriter(new FileWriter(\"f.txt\"))\n"
            "             second argument true to APPEND instead of wiping\n"
            "             try ( ... ) { } closes it for you\n"
            "FILE IN      new Scanner(new File(\"f.txt\"))\n"
            "             new BufferedReader(new FileReader(\"f.txt\")) + readLine()\n"
            "IMPORTS      java.io.* and java.util.Scanner, throws IOException on main\n\n"
            "Close the writer before you read the file back."
        ),
        "briefing": (
            "Build the data terminal from scratch, imports and all.\n\n"
            "A file readings.txt already holds two whole numbers.\n\n"
            "1. Read both numbers from the FILE with a Scanner.\n"
            "2. Write a report into report.txt using try-with-resources, containing:\n"
            "   TOTAL: 46\n"
            "   AVERAGE: 23.00\n"
            "   (the average to two decimal places — use printf on the PrintWriter)\n"
            "3. Print to the CONSOLE: REPORT WRITTEN\n\n"
            "The console gets one line. The file gets two."
        ),
        "seed_files": {"readings.txt": "18 28\n"},
        "hints": [
            "Read the file first and close that Scanner before you start writing the report.",
            "PrintWriter has printf too: out.printf(\"AVERAGE: %.2f%n\", (a + b) / 2.0); — and note the 2.0, or you'll get int division.",
        ],
        "boilerplate": "",
        "solution": '''import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner fileIn = new Scanner(new File("readings.txt"));
        int a = fileIn.nextInt();
        int b = fileIn.nextInt();
        fileIn.close();

        try (PrintWriter out = new PrintWriter(new FileWriter("report.txt"))) {
            out.println("TOTAL: " + (a + b));
            out.printf("AVERAGE: %.2f%n", (a + b) / 2.0);
        }

        System.out.println("REPORT WRITTEN");
    }
}''',
        "wrong": [
            # int division — average comes out 23.00 only by luck elsewhere, here it loses the decimals entirely
            '''import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner fileIn = new Scanner(new File("readings.txt"));
        int a = fileIn.nextInt();
        int b = fileIn.nextInt();
        fileIn.close();

        try (PrintWriter out = new PrintWriter(new FileWriter("report.txt"))) {
            out.println("TOTAL: " + (a + b));
            out.println("AVERAGE: " + (a + b) / 2);
        }

        System.out.println("REPORT WRITTEN");
    }
}''',
        ],
        "check": make_file_checker(
            filename="report.txt",
            expected_contents="TOTAL: 46\nAVERAGE: 23.00",
            expected_output="REPORT WRITTEN",
            success_line="File in, file out, formatted, closed, confirmed. Topic Five complete — your programs can now talk to the world in both directions.",
            missing_hint="There's no report.txt. Check you wrote the report to the file rather than the console.",
            mismatch_hint="report.txt exists but doesn't match. Check the total, and that the average shows exactly two decimal places.",
            code_requires=["new Scanner(new File", "try (", "printf", "%.2f"],
            code_requires_hint="I need the file read with a Scanner, a try-with-resources PrintWriter, and printf with %.2f for the average.",
        ),
    },
}
