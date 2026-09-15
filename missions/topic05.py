"""
Topic 5 — Basic I/O & File I/O.

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
}
