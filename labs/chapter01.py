"""
Chapter 1 — Breaking the Surface.

The book's quick dip: the structure every Java program needs, printing, and a
first look at if and while. No methods of your own and no objects yet — those
are Chapters 2 and 4 — so everything here lives inside main().

Security thread: the report header, and the first counting of failed logins.
"""

from .common import MAIN_ONLY, make_checker

LABS = {

    "ch01-lab01": {
        "chapter": 1,
        "chapter_title": "Breaking the Surface",
        "title": "The Shape of a Java Program",
        "idea": "Every Java program is a class containing a main method, and println prints one line",

        "learn": (
            "The structure Java requires around every program you write, and how "
            "to print a line of text."
        ),

        "matters": (
            "Every security tool you will build ends with a human reading its "
            "output. A scanner that finds a problem and reports it badly is a "
            "scanner nobody acts on. The report starts here, with the structure "
            "Java demands before it will run anything at all."
        ),

        "explain": (
            "Java will not let you write loose instructions in a file. Every "
            "line of code has to live inside a class, and the code that runs "
            "first has to live inside a method called main.\n\n"
            "    public class Main {\n"
            "        public static void main(String[] args) {\n"
            "            // your instructions go here\n"
            "        }\n"
            "    }\n\n"
            "That outer wrapper is not decoration. When you run a Java program, "
            "the Java Virtual Machine looks for one specific thing: a method "
            "called main that takes String[] args. It starts there. If it cannot "
            "find exactly that, it refuses to start and tells you the main "
            "method was not found.\n\n"
            "To print a line, use System.out.println with your text in double "
            "quotes:\n\n"
            "    System.out.println(\"SCAN COMPLETE\");\n\n"
            "Read it as a path: the System, its output, print a line. The line "
            "ends with a semicolon, which is how Java knows one instruction has "
            "finished.\n\n"
            "Java is strict about spelling and about capital letters. System "
            "with a capital S is a real thing; system with a small s does not "
            "exist. Neither does printLine, or Println. There is no guessing and "
            "no nearly.\n\n"
            "THE MISTAKE TO EXPECT: a missing semicolon, or a capital letter in "
            "the wrong place. Both stop the program compiling, and the message "
            "you get back points at the line. Read that message rather than "
            "staring at the code — it is usually telling you the answer."
        ),

        "example": (
            "A complete program that prints two lines:\n\n"
            "    public class Main {\n"
            "        public static void main(String[] args) {\n"
            "            System.out.println(\"AUDIT TOOL v1\");\n"
            "            System.out.println(\"READY\");\n"
            "        }\n"
            "    }\n\n"
            "prints:\n\n"
            "    AUDIT TOOL v1\n"
            "    READY\n\n"
            "Two println statements, two lines of output, in the order they are "
            "written. Each one prints its text and then moves to the next line, "
            "which is what the ln on the end of println means."
        ),

        "recap": [
            "Every Java program needs a class, and inside it a main method.",
            "The JVM starts at main(String[] args) and nothing else.",
            "System.out.println(\"text\") prints one line.",
            "Every instruction ends with a semicolon.",
            "Capital letters matter: System is not system.",
        ],

        "tasks": [
            {
                "id": "t1",
                "role": "warm-up",
                "brief": (
                    "The structure is already written for you. Add one line "
                    "inside main that prints the start of a report.\n\n"
                    "Expected output:\n"
                    "LOG REVIEW STARTED"
                ),
                "starter": MAIN_ONLY,
                "hints": [
                    "Use System.out.println with your text inside double quotes.",
                    "System.out.println(\"LOG REVIEW STARTED\");",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("LOG REVIEW STARTED");
    }
}''',
                "check": make_checker(
                    expected_output="LOG REVIEW STARTED",
                    wrong_hint="I need exactly one line: LOG REVIEW STARTED",
                    requires=["System.out.println"],
                    requires_hint="Use System.out.println to print the line.",
                ),
                "explanation": (
                    "That is a complete Java program. Everything you write from "
                    "here sits inside that same wrapper.\n\n"
                    "The text printed exactly as you typed it, including the "
                    "capitals. Java does not tidy your output or add anything of "
                    "its own — what is inside the quotes is what comes out."
                ),
            },

            {
                "id": "t2",
                "role": "variation",
                "brief": (
                    "A report needs more than one line. Print three lines, in "
                    "this order.\n\n"
                    "Expected output:\n"
                    "LOG REVIEW\n"
                    "SOURCE: auth.log\n"
                    "STATUS: running"
                ),
                "starter": MAIN_ONLY,
                "hints": [
                    "One println statement for each line you want.",
                    "Three statements, one under the other, each ending in a semicolon.",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("LOG REVIEW");
        System.out.println("SOURCE: auth.log");
        System.out.println("STATUS: running");
    }
}''',
                "check": make_checker(
                    expected_output="LOG REVIEW\nSOURCE: auth.log\nSTATUS: running",
                    wrong_hint="I need three lines: LOG REVIEW, then SOURCE: auth.log, then STATUS: running.",
                    close_hint="Some lines are right. Check the spelling, the capitals and the order.",
                ),
                "explanation": (
                    "Statements run in the order you write them, top to bottom. "
                    "Swap two of those lines and the output swaps with them.\n\n"
                    "This is the whole of how a program works for now: a list of "
                    "instructions, carried out in order, one at a time."
                ),
            },

            {
                "id": "t3",
                "role": "apply",
                "brief": (
                    "Real reports use a separator line so a person can find the "
                    "start of a section quickly.\n\n"
                    "Print a header made of four lines: a row of dashes, the "
                    "title, another row of dashes, then a blank line saying what "
                    "is being reviewed.\n\n"
                    "Expected output:\n"
                    "--------------------\n"
                    "AUTHENTICATION AUDIT\n"
                    "--------------------\n"
                    "FILE: auth.log"
                ),
                "starter": MAIN_ONLY,
                "hints": [
                    "The dashes are just text inside quotes, like any other line.",
                    "Count them: twenty dashes, the same on both rows.",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("--------------------");
        System.out.println("AUTHENTICATION AUDIT");
        System.out.println("--------------------");
        System.out.println("FILE: auth.log");
    }
}''',
                "check": make_checker(
                    expected_output="--------------------\nAUTHENTICATION AUDIT\n--------------------\nFILE: auth.log",
                    wrong_hint="I need four lines: dashes, the title, dashes, then FILE: auth.log",
                    close_hint="Close. Count the dashes — twenty on each row — and check the title's spelling.",
                ),
                "explanation": (
                    "Dashes are text like anything else. Java has no idea it is "
                    "drawing a line; it prints the characters you asked for.\n\n"
                    "Notice you had to count them to make both rows match. That "
                    "is tedious and it is exactly the sort of thing a loop does "
                    "for you — which is the last lab in this chapter."
                ),
            },

            {
                "id": "t4",
                "role": "combine",
                "brief": (
                    "Put the whole thing together from an empty editor. Write "
                    "the class and the main method yourself this time — no "
                    "starter code.\n\n"
                    "Print this report header exactly:\n\n"
                    "=== SECURITY REPORT ===\n"
                    "GENERATED: 2026-03-14\n"
                    "ANALYST: on duty\n"
                    "=======================\n\n"
                    "The last line is twenty-three equals signs, matching the "
                    "width of the first line."
                ),
                "starter": "",
                "hints": [
                    "Start with public class Main { and then public static void main(String[] args) {",
                    "Four println statements inside main, then close both braces with }",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("=== SECURITY REPORT ===");
        System.out.println("GENERATED: 2026-03-14");
        System.out.println("ANALYST: on duty");
        System.out.println("=======================");
    }
}''',
                "check": make_checker(
                    expected_output="=== SECURITY REPORT ===\nGENERATED: 2026-03-14\nANALYST: on duty\n=======================",
                    wrong_hint="Output does not match, or the program did not compile. Check the class and main structure first, then each line.",
                    close_hint="Some lines are right. Check the equals signs — three, a space, the title, a space, three on the top row.",
                    requires=["public class Main", "public static void main"],
                    requires_hint="This one has no starter code — you need to write the class and the main method yourself.",
                ),
                "explanation": (
                    "You wrote the whole structure from nothing. That wrapper "
                    "will become automatic, and it needs to, because every later "
                    "idea sits on top of it.\n\n"
                    "If it did not compile the first time, the most likely causes "
                    "are a missing semicolon or a missing closing brace. Every "
                    "opening brace needs a partner."
                ),
            },

            {
                "id": "t5",
                "role": "repair",
                "brief": (
                    "This program has three separate mistakes and will not "
                    "compile. Fix all three so it prints the two lines below.\n\n"
                    "Expected output:\n"
                    "SCANNER ONLINE\n"
                    "AWAITING INPUT\n\n"
                    "Run it and read the first error message. Fix that one, then "
                    "run it again. Working from the top down is faster than "
                    "trying to spot everything at once."
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        system.out.println(\"SCANNER ONLINE\");\n"
                    "        System.out.printLine(\"AWAITING INPUT\")\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Look at the capital letters on the first line, and at the method name on the second.",
                    "System needs a capital S. The method is println, not printLine. And one line is missing its semicolon.",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("SCANNER ONLINE");
        System.out.println("AWAITING INPUT");
    }
}''',
                "check": make_checker(
                    expected_output="SCANNER ONLINE\nAWAITING INPUT",
                    wrong_hint="Still not compiling, or the output is wrong. Read the first error message and fix that one first.",
                    close_hint="One line is right. Check the other for its method name and its semicolon.",
                ),
                "explanation": (
                    "Three mistakes, three different error messages, each one "
                    "naming the line it was on.\n\n"
                    "The lowercase system gave you a message about a package or "
                    "symbol not being found. printLine gave you cannot find "
                    "symbol, because no such method exists. The missing semicolon "
                    "gave you ';' expected.\n\n"
                    "Those three cover most of what you will hit for weeks. The "
                    "compiler is not being difficult — it is telling you where to "
                    "look, and reading it is faster than rereading your own code."
                ),
            },
        ],
    },

    "ch01-lab02": {
        "chapter": 1,
        "chapter_title": "Breaking the Surface",
        "title": "Building One Line From Pieces",
        "idea": "print() leaves the cursor on the line, and + joins pieces of text together",

        "learn": (
            "How to build a single line of output from several pieces, using "
            "print and the + operator."
        ),

        "matters": (
            "A log line is one row holding several facts: a user, an address, a "
            "reason. Producing that shape is most of what a reporting tool does. "
            "Being able to assemble a line from pieces is how a count, a name and "
            "a verdict end up on one readable row instead of scattered down the "
            "screen."
        ),

        "explain": (
            "println prints your text and then moves to the next line. print "
            "does the same thing without moving. That one difference lets "
            "several statements build one line between them.\n\n"
            "    System.out.print(\"USER: \");\n"
            "    System.out.println(\"jsmith\");\n\n"
            "prints one line: USER: jsmith\n\n"
            "The other way to build a line is the + operator. Between two pieces "
            "of text it joins them end to end:\n\n"
            "    System.out.println(\"USER: \" + \"jsmith\");\n\n"
            "Same result, one statement.\n\n"
            "+ joins exactly what you give it and adds nothing. If you want a "
            "space between two pieces, the space has to be inside one of them. "
            "\"USER:\" + \"jsmith\" prints USER:jsmith with no gap, because you "
            "never asked for one. The spaces around the + in your code are just "
            "formatting for human eyes and never reach the output.\n\n"
            "+ also works when one side is a number. Java turns the number into "
            "text and joins it:\n\n"
            "    System.out.println(\"FAILURES: \" + 3);\n\n"
            "prints FAILURES: 3\n\n"
            "THE MISTAKE TO EXPECT: expecting + to put a space in for you. It "
            "does not. If two words come out stuck together, the space is missing "
            "from inside your quotes."
        ),

        "example": (
            "A complete program building one line two different ways:\n\n"
            "    public class Main {\n"
            "        public static void main(String[] args) {\n"
            "            System.out.print(\"HOST: \");\n"
            "            System.out.println(\"gateway-02\");\n"
            "\n"
            "            System.out.println(\"PORTS OPEN: \" + 7);\n"
            "        }\n"
            "    }\n\n"
            "prints:\n\n"
            "    HOST: gateway-02\n"
            "    PORTS OPEN: 7\n\n"
            "The first line was built by two statements, because print did not "
            "move to the next line. The second was built by one statement using "
            "+ to join text to a number."
        ),

        "recap": [
            "println prints and moves to the next line; print stays where it is.",
            "Several print statements can build one line between them.",
            "+ joins pieces of text end to end.",
            "+ adds no spaces of its own — put them inside the quotes.",
            "+ turns a number into text when the other side is text.",
        ],

        "tasks": [
            {
                "id": "t1",
                "role": "warm-up",
                "brief": (
                    "Build one line from two statements. Use print for the "
                    "label, then println for the value.\n\n"
                    "Expected output:\n"
                    "SOURCE: firewall.log"
                ),
                "starter": MAIN_ONLY,
                "hints": [
                    "The first statement uses print, so the cursor stays on the line.",
                    "System.out.print(\"SOURCE: \"); then System.out.println(\"firewall.log\");",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.print("SOURCE: ");
        System.out.println("firewall.log");
    }
}''',
                "wrong": [
                    '''public class Main {
    public static void main(String[] args) {
        System.out.println("SOURCE: ");
        System.out.println("firewall.log");
    }
}''',
                ],
                "check": make_checker(
                    expected_output="SOURCE: firewall.log",
                    wrong_hint="I need a single line reading SOURCE: firewall.log",
                    close_hint="If you got two lines, the first statement used println — use print so the cursor stays put.",
                    requires=["System.out.print("],
                    requires_hint="Use print for the first part, so the line is not broken.",
                ),
                "explanation": (
                    "Two statements, one line. print left the cursor sitting "
                    "after the colon and space, so the next thing printed landed "
                    "right there.\n\n"
                    "Had both been println, you would have got two lines. The ln "
                    "is the only difference between them."
                ),
            },

            {
                "id": "t2",
                "role": "variation",
                "brief": (
                    "Now build the same shape of line with one statement "
                    "instead of two, using + to join the pieces.\n\n"
                    "Expected output:\n"
                    "PROTOCOL: ssh"
                ),
                "starter": MAIN_ONLY,
                "hints": [
                    "One println, with the two pieces joined by +.",
                    "System.out.println(\"PROTOCOL: \" + \"ssh\");",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("PROTOCOL: " + "ssh");
    }
}''',
                "check": make_checker(
                    expected_output="PROTOCOL: ssh",
                    wrong_hint="I need one line reading PROTOCOL: ssh",
                    close_hint="Check the space — it has to be inside the quotes, because + adds none.",
                    requires=["+"],
                    requires_hint="This one wants the two pieces joined with +, rather than typed as one piece of text.",
                ),
                "explanation": (
                    "One statement, same line. The space after the colon had to "
                    "be inside the quotes.\n\n"
                    "Write \"PROTOCOL:\" + \"ssh\" without that space and you get "
                    "PROTOCOL:ssh. The gap you can see around the + in your code "
                    "is invisible to Java — it never reaches the output."
                ),
            },

            {
                "id": "t3",
                "role": "apply",
                "brief": (
                    "Report a count. Numbers written without quotes are numbers, "
                    "and + joins them onto text.\n\n"
                    "Print one line reporting that there were 12 failed logins.\n\n"
                    "Expected output:\n"
                    "FAILED LOGINS: 12\n\n"
                    "Write the 12 as a number, without quotes around it."
                ),
                "starter": MAIN_ONLY,
                "hints": [
                    "The number has no quotes: \"FAILED LOGINS: \" + 12",
                    "Java turns the number into text because the other side of the + is text.",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("FAILED LOGINS: " + 12);
    }
}''',
                "check": make_checker(
                    expected_output="FAILED LOGINS: 12",
                    wrong_hint="I need one line reading FAILED LOGINS: 12",
                    close_hint="Check the label's spelling and the space after the colon.",
                    requires=["+ 12"],
                    requires_hint="Write the 12 as a real number joined with + — not as part of the text in quotes.",
                ),
                "explanation": (
                    "The 12 had no quotes, so it was a number, and + turned it "
                    "into text to join it on.\n\n"
                    "It looks identical to typing \"12\" inside the quotes, and it "
                    "is not. A number can be counted with and compared; text "
                    "cannot. That difference does nothing for you yet, and it is "
                    "the whole subject of Chapter 3."
                ),
            },

            {
                "id": "t4",
                "role": "combine",
                "brief": (
                    "Build a full log summary block from an empty editor. Write "
                    "the class and main yourself.\n\n"
                    "Use print and + to produce exactly these three lines:\n\n"
                    "USER: jsmith  IP: 10.14.22.9\n"
                    "FAILED: 3  OK: 1\n"
                    "VERDICT: review\n\n"
                    "Note there are two spaces before IP: and two before OK:.\n"
                    "The numbers 3 and 1 must be written as numbers, not inside "
                    "quotes."
                ),
                "starter": "",
                "hints": [
                    "Build each line with + joining the pieces: \"USER: \" + \"jsmith\" + \"  IP: \" + \"10.14.22.9\"",
                    "For line two the numbers go in unquoted: \"FAILED: \" + 3 + \"  OK: \" + 1",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.println("USER: " + "jsmith" + "  IP: " + "10.14.22.9");
        System.out.println("FAILED: " + 3 + "  OK: " + 1);
        System.out.println("VERDICT: review");
    }
}''',
                "check": make_checker(
                    expected_output="USER: jsmith  IP: 10.14.22.9\nFAILED: 3  OK: 1\nVERDICT: review",
                    wrong_hint="Output does not match, or it did not compile. Check the class and main structure, then each line.",
                    close_hint="Close. Check the double spaces before IP: and before OK:.",
                    requires=["public class Main", "+ 3", "+ 1"],
                    requires_hint="Write the class and main yourself, and put the 3 and the 1 in as real numbers joined with +.",
                ),
                "explanation": (
                    "That is the shape of a real log summary: several facts on "
                    "one row, in a fixed order, so a person can scan a hundred of "
                    "them quickly.\n\n"
                    "The double spaces matter for the same reason. Lining columns "
                    "up by hand like this works for three facts and falls apart "
                    "beyond that, which is why Chapter 10 has a better tool for "
                    "it.\n\n"
                    "You now have the header from the last lab and a summary line "
                    "from this one. That is the skeleton of the report."
                ),
            },

            {
                "id": "t5",
                "role": "repair",
                "brief": (
                    "This program should print one line, but it prints three, "
                    "and two words are stuck together.\n\n"
                    "Fix it so it prints exactly:\n"
                    "ALERT: brute force from 10.14.22.9\n\n"
                    "Do not rewrite it as a single piece of text — keep the "
                    "pieces and fix how they are joined."
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        System.out.println(\"ALERT: \");\n"
                    "        System.out.println(\"brute force\" + \"from \");\n"
                    "        System.out.println(\"10.14.22.9\");\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Only the last statement should be println. The others should be print.",
                    "One space is missing: \"brute force\" + \"from \" has no gap between force and from.",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        System.out.print("ALERT: ");
        System.out.print("brute force " + "from ");
        System.out.println("10.14.22.9");
    }
}''',
                "check": make_checker(
                    expected_output="ALERT: brute force from 10.14.22.9",
                    wrong_hint="I need one line: ALERT: brute force from 10.14.22.9",
                    close_hint="Getting closer. Check for a missing space between two of the pieces, and that only the last statement is println.",
                    requires=["System.out.print("],
                    requires_hint="Keep the pieces and use print so they land on the same line.",
                ),
                "explanation": (
                    "Two different bugs that looked like one. The line breaks "
                    "came from println where print was wanted; the missing space "
                    "came from a gap that was never inside any quotes.\n\n"
                    "The second is the harder one to see, because the code looks "
                    "spaced out and the output is not. When two words come out "
                    "stuck together, count the spaces inside the quotes rather "
                    "than around the +."
                ),
            },
        ],
    },

    "ch01-lab03": {
        "chapter": 1,
        "chapter_title": "Breaking the Surface",
        "title": "Deciding What Matters",
        "idea": "if runs a block only when a test is true, and else runs one when it is not",

        "learn": (
            "How to make a program take one path or another, depending on a "
            "value it is holding."
        ),

        "matters": (
            "Detection is a decision. Three failed logins is a Tuesday; thirty "
            "is an attack. A tool that prints every event equally is not a "
            "detector, it is a printer. The moment your code can say 'this one "
            "is different' is the moment it starts being useful, and that moment "
            "is the if statement."
        ),

        "explain": (
            "An if statement runs a block of code only when a test comes out "
            "true.\n\n"
            "    if (failures > 10) {\n"
            "        System.out.println(\"ALERT\");\n"
            "    }\n\n"
            "The test goes in round brackets. The code that may or may not run "
            "goes in curly braces. If the test is false, Java skips the whole "
            "block and carries on below it.\n\n"
            "The tests you can write are the comparisons:\n\n"
            "    a > b     greater than\n"
            "    a < b     less than\n"
            "    a >= b    greater than or equal to\n"
            "    a <= b    less than or equal to\n"
            "    a == b    equal to        (two equals signs)\n"
            "    a != b    not equal to\n\n"
            "Add else to say what happens when the test is false:\n\n"
            "    if (failures > 10) {\n"
            "        System.out.println(\"ALERT\");\n"
            "    } else {\n"
            "        System.out.println(\"NORMAL\");\n"
            "    }\n\n"
            "Exactly one of those two blocks runs. Never both, never neither.\n\n"
            "Note the two equals signs in a test. One equals sign means 'put "
            "this value in here', which is an instruction, not a question. They "
            "are different things and Java treats them differently.\n\n"
            "THE MISTAKE TO EXPECT: a semicolon after the test, like this:\n\n"
            "    if (failures > 10);\n"
            "    {\n"
            "        System.out.println(\"ALERT\");\n"
            "    }\n\n"
            "That compiles. The semicolon becomes the entire body of the if, so "
            "the if does nothing at all, and the block below it then runs every "
            "single time. It looks completely normal and it is always wrong."
        ),

        "example": (
            "A complete program that checks one value:\n\n"
            "    public class Main {\n"
            "        public static void main(String[] args) {\n"
            "            int openPorts = 2;\n"
            "\n"
            "            if (openPorts > 5) {\n"
            "                System.out.println(\"TOO MANY PORTS OPEN\");\n"
            "            } else {\n"
            "                System.out.println(\"PORT COUNT ACCEPTABLE\");\n"
            "            }\n"
            "        }\n"
            "    }\n\n"
            "prints:\n\n"
            "    PORT COUNT ACCEPTABLE\n\n"
            "The test was 2 > 5, which is false, so the else block ran and the "
            "first block was skipped entirely. Change the 2 to a 9 and the other "
            "line prints instead. The code does not change — only the value it "
            "is looking at."
        ),

        "recap": [
            "if (test) { } runs the block only when the test is true.",
            "else { } runs when the test is false; exactly one of the two runs.",
            "Tests are the comparisons: > < >= <= == !=",
            "== asks whether two things are equal; = puts a value into something.",
            "A semicolon straight after if (test) makes the if do nothing.",
        ],

        "tasks": [
            {
                "id": "t1",
                "role": "warm-up",
                "brief": (
                    "The variable is already declared. Add an if statement that "
                    "prints LOCKOUT TRIGGERED when failedAttempts is greater "
                    "than 3.\n\n"
                    "failedAttempts is 5, so the message should appear.\n\n"
                    "Expected output:\n"
                    "LOCKOUT TRIGGERED"
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        int failedAttempts = 5;\n\n"
                    "        // add your if statement here\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "The test goes in round brackets, the message in curly braces.",
                    "if (failedAttempts > 3) { System.out.println(\"LOCKOUT TRIGGERED\"); }",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        int failedAttempts = 5;

        if (failedAttempts > 3) {
            System.out.println("LOCKOUT TRIGGERED");
        }
    }
}''',
                "wrong": [
                    '''public class Main {
    public static void main(String[] args) {
        int failedAttempts = 5;

        System.out.println("LOCKOUT TRIGGERED");
    }
}''',
                ],
                "check": make_checker(
                    expected_output="LOCKOUT TRIGGERED",
                    wrong_hint="I need the line LOCKOUT TRIGGERED, printed by an if statement.",
                    requires=["if (", "failedAttempts"],
                    requires_hint="This needs a real if statement testing failedAttempts — not a plain println.",
                ),
                "explanation": (
                    "The message printed because the test was true. That is the "
                    "first time your code has decided anything.\n\n"
                    "Change the 5 to a 2 and run it again: nothing prints at all, "
                    "and that is correct. A detector that stays quiet when there "
                    "is nothing to report is doing its job."
                ),
            },

            {
                "id": "t2",
                "role": "variation",
                "brief": (
                    "Now report both outcomes. Using if and else, print "
                    "ACCOUNT LOCKED when failedAttempts is 3 or more, and "
                    "ACCOUNT ACTIVE when it is not.\n\n"
                    "failedAttempts is 2.\n\n"
                    "Expected output:\n"
                    "ACCOUNT ACTIVE"
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        int failedAttempts = 2;\n\n"
                    "        // add your if and else here\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Three or more means >= 3, not > 3.",
                    "if (failedAttempts >= 3) { ... } else { ... }",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        int failedAttempts = 2;

        if (failedAttempts >= 3) {
            System.out.println("ACCOUNT LOCKED");
        } else {
            System.out.println("ACCOUNT ACTIVE");
        }
    }
}''',
                "check": make_checker(
                    expected_output="ACCOUNT ACTIVE",
                    wrong_hint="I need exactly one line, chosen by the test.",
                    close_hint="Only one of the two messages should print. Check the test — 2 is not 3 or more.",
                    requires=["if (", "else"],
                    requires_hint="This one needs both an if and an else.",
                ),
                "explanation": (
                    "One line printed, not two. With if and else there is always "
                    "exactly one winner.\n\n"
                    "The detail worth keeping is >= rather than >. 'Three or "
                    "more' includes three. Written as > 3, an account with "
                    "exactly three failures would stay active, and that "
                    "off-by-one is the most common bug in every threshold rule "
                    "you will ever write."
                ),
            },

            {
                "id": "t3",
                "role": "apply",
                "brief": (
                    "A real check looks at the same value more than once to give "
                    "a graded answer.\n\n"
                    "failedAttempts is 12. Print SEVERITY: followed by a word, "
                    "chosen like this:\n"
                    "  10 or more    critical\n"
                    "  4 to 9        warning\n"
                    "  3 or fewer    normal\n\n"
                    "Expected output:\n"
                    "SEVERITY: critical\n\n"
                    "Use if, else if, and else. An else if is an extra test that "
                    "is only tried when the ones above it were false."
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        int failedAttempts = 12;\n\n"
                    "        // add your if / else if / else here\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Test the biggest threshold first: if (failedAttempts >= 10) then else if (failedAttempts >= 4) then else.",
                    "Because the tests are tried in order, the second one does not need an upper limit — anything 10 or more never reaches it.",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        int failedAttempts = 12;

        if (failedAttempts >= 10) {
            System.out.println("SEVERITY: critical");
        } else if (failedAttempts >= 4) {
            System.out.println("SEVERITY: warning");
        } else {
            System.out.println("SEVERITY: normal");
        }
    }
}''',
                "wrong": [
                    '''public class Main {
    public static void main(String[] args) {
        int failedAttempts = 12;

        if (failedAttempts >= 4) {
            System.out.println("SEVERITY: warning");
        } else if (failedAttempts >= 10) {
            System.out.println("SEVERITY: critical");
        } else {
            System.out.println("SEVERITY: normal");
        }
    }
}''',
                ],
                "check": make_checker(
                    expected_output="SEVERITY: critical",
                    wrong_hint="I need one line: SEVERITY: critical",
                    close_hint="Wrong word chosen. Test the highest threshold first — 12 is 10 or more.",
                    requires=["else if"],
                    requires_hint="This one wants an else if chain, so the tests are tried in order.",
                ),
                "explanation": (
                    "The order of the tests decided the answer. Java tries them "
                    "top to bottom and stops at the first one that is true.\n\n"
                    "Put >= 4 first and 12 would print warning, because 12 is "
                    "indeed 4 or more and the chain would stop there and never "
                    "reach the critical test. For thresholds that climb, always "
                    "test the highest first.\n\n"
                    "That is also why the middle test needed no upper limit."
                ),
            },

            {
                "id": "t4",
                "role": "combine",
                "brief": (
                    "Put the whole chapter together. Write the class and main "
                    "yourself — no starter code.\n\n"
                    "Declare an int called failedLogins set to 7 and a String "
                    "called account set to jsmith.\n\n"
                    "Print a header line, then a line built with + reporting the "
                    "account and the count, then a verdict chosen with if and "
                    "else: LOCK ACCOUNT when the count is 5 or more, otherwise "
                    "MONITOR ONLY.\n\n"
                    "Expected output:\n"
                    "--- ACCOUNT CHECK ---\n"
                    "ACCOUNT: jsmith  FAILED: 7\n"
                    "LOCK ACCOUNT\n\n"
                    "There are two spaces before FAILED:. The count must come "
                    "from the variable, not be typed into the text."
                ),
                "starter": "",
                "hints": [
                    "Build the middle line with +: \"ACCOUNT: \" + account + \"  FAILED: \" + failedLogins",
                    "Then if (failedLogins >= 5) { ... } else { ... } for the verdict.",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        int failedLogins = 7;
        String account = "jsmith";

        System.out.println("--- ACCOUNT CHECK ---");
        System.out.println("ACCOUNT: " + account + "  FAILED: " + failedLogins);

        if (failedLogins >= 5) {
            System.out.println("LOCK ACCOUNT");
        } else {
            System.out.println("MONITOR ONLY");
        }
    }
}''',
                "check": make_checker(
                    expected_output="--- ACCOUNT CHECK ---\nACCOUNT: jsmith  FAILED: 7\nLOCK ACCOUNT",
                    wrong_hint="Output does not match, or it did not compile. Check the three lines one at a time.",
                    close_hint="Close. Check the two spaces before FAILED:, and that the verdict comes from an if.",
                    requires=["public class Main", "failedLogins", "account", "if (", "else"],
                    requires_hint="I need the class and main written yourself, both variables used in the line, and an if/else for the verdict.",
                ),
                "explanation": (
                    "Header, data line, verdict — that is a report. Small, but "
                    "the same three parts a real one has.\n\n"
                    "The count came from the variable, so changing 7 to 2 changes "
                    "both the data line and the verdict, with no other edits. "
                    "Typing the 7 into the text would have left the report lying "
                    "about itself the moment the number changed.\n\n"
                    "The next lab removes the last hardcoded part: the count "
                    "itself."
                ),
            },

            {
                "id": "t5",
                "role": "repair",
                "brief": (
                    "This check is supposed to stay silent, because 1 failure is "
                    "not worth an alert. It prints the alert anyway.\n\n"
                    "The code compiles and looks correct. Find the reason and fix "
                    "it, so the program prints only:\n"
                    "CHECK COMPLETE"
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        int failures = 1;\n\n"
                    "        if (failures > 5);\n"
                    "        {\n"
                    "            System.out.println(\"INTRUSION ALERT\");\n"
                    "        }\n\n"
                    "        System.out.println(\"CHECK COMPLETE\");\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Look at the end of the line holding the test. Read it character by character.",
                    "There is a semicolon straight after if (failures > 5). Remove it.",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        int failures = 1;

        if (failures > 5) {
            System.out.println("INTRUSION ALERT");
        }

        System.out.println("CHECK COMPLETE");
    }
}''',
                "check": make_checker(
                    expected_output="CHECK COMPLETE",
                    wrong_hint="I need exactly one line: CHECK COMPLETE",
                    close_hint="INTRUSION ALERT is still printing, so the block is still not attached to the if.",
                    requires=["if ("],
                    requires_hint="Keep the if statement and fix it — do not delete the check.",
                ),
                "explanation": (
                    "One character. The semicolon after the test became the "
                    "entire body of the if, so the if ran, did nothing, and "
                    "finished — and the block underneath it was then just an "
                    "ordinary block that always runs.\n\n"
                    "Nothing about that looks wrong. It compiles without a "
                    "warning and the indentation tells you a comforting lie.\n\n"
                    "This exact bug has shipped in real security code, where a "
                    "check that appeared to guard something guarded nothing. When "
                    "an if seems to be ignored, look at the end of its test line "
                    "first."
                ),
            },
        ],
    },

    "ch01-lab04": {
        "chapter": 1,
        "chapter_title": "Breaking the Surface",
        "title": "Doing It Again",
        "idea": "a while loop repeats a block for as long as its test stays true",

        "learn": (
            "How to make a program repeat work, and how to count while it does."
        ),

        "matters": (
            "A log has thousands of lines and you are not going to write "
            "thousands of if statements. Everything in security automation is "
            "the same shape: go through the events, count what matters, stop "
            "when you run out. That is a loop, and it is the difference between "
            "checking one account by hand and checking every account while you "
            "make coffee."
        ),

        "explain": (
            "A while loop repeats a block for as long as its test is true.\n\n"
            "    int count = 0;\n"
            "    while (count < 3) {\n"
            "        System.out.println(count);\n"
            "        count = count + 1;\n"
            "    }\n\n"
            "prints 0, then 1, then 2.\n\n"
            "Java checks the test, runs the block, then checks again. When the "
            "test is finally false, it stops and carries on below the loop.\n\n"
            "Three things have to be true or the loop is broken:\n\n"
            "    1. the variable exists before the loop starts\n"
            "    2. the test can eventually become false\n"
            "    3. something inside the block moves the variable towards that\n\n"
            "Miss the third and the test never changes, so the loop runs for "
            "ever. Your program does not crash — it simply never stops, and you "
            "have to interrupt it yourself with Ctrl+C.\n\n"
            "Adding one to a counter is so common that Java has a short way to "
            "write it:\n\n"
            "    count = count + 1;     the long way\n"
            "    count++;               exactly the same thing\n\n"
            "count = count + 1 looks like nonsense as a statement of fact, and it "
            "is not one. The = does not mean 'equals'. It means 'work out the "
            "right-hand side, then put the answer in the variable on the left'.\n\n"
            "THE MISTAKE TO EXPECT: forgetting to change the counter inside the "
            "loop. The test stays true for ever and the program hangs. If your "
            "program prints the same line endlessly, that is what happened."
        ),

        "example": (
            "A complete program that counts down:\n\n"
            "    public class Main {\n"
            "        public static void main(String[] args) {\n"
            "            int retries = 3;\n"
            "\n"
            "            while (retries > 0) {\n"
            "                System.out.println(\"RETRIES LEFT: \" + retries);\n"
            "                retries--;\n"
            "            }\n"
            "\n"
            "            System.out.println(\"GIVING UP\");\n"
            "        }\n"
            "    }\n\n"
            "prints:\n\n"
            "    RETRIES LEFT: 3\n"
            "    RETRIES LEFT: 2\n"
            "    RETRIES LEFT: 1\n"
            "    GIVING UP\n\n"
            "The loop ran three times. On the fourth check retries was 0, the "
            "test 0 > 0 was false, and Java carried on to the line below. Note "
            "retries-- counts down, the mirror of retries++."
        ),

        "recap": [
            "while (test) { } repeats the block while the test is true.",
            "The test is checked before each run, so a false test means zero runs.",
            "Something in the block must move the counter, or the loop never ends.",
            "count++ is the short way to write count = count + 1.",
            "= puts a value into a variable; it does not mean 'equals'.",
        ],

        "tasks": [
            {
                "id": "t1",
                "role": "warm-up",
                "brief": (
                    "Print a line five times, numbered from 1.\n\n"
                    "The counter is declared for you, starting at 1.\n\n"
                    "Expected output:\n"
                    "ATTEMPT 1\n"
                    "ATTEMPT 2\n"
                    "ATTEMPT 3\n"
                    "ATTEMPT 4\n"
                    "ATTEMPT 5"
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        int attempt = 1;\n\n"
                    "        // add your while loop here\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "The loop should keep going while attempt is less than or equal to 5.",
                    "while (attempt <= 5) { System.out.println(\"ATTEMPT \" + attempt); attempt++; }",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        int attempt = 1;

        while (attempt <= 5) {
            System.out.println("ATTEMPT " + attempt);
            attempt++;
        }
    }
}''',
                "wrong": [
                    '''public class Main {
    public static void main(String[] args) {
        int attempt = 1;

        System.out.println("ATTEMPT 1");
        System.out.println("ATTEMPT 2");
        System.out.println("ATTEMPT 3");
        System.out.println("ATTEMPT 4");
        System.out.println("ATTEMPT 5");
    }
}''',
                ],
                "check": make_checker(
                    expected_output="ATTEMPT 1\nATTEMPT 2\nATTEMPT 3\nATTEMPT 4\nATTEMPT 5",
                    wrong_hint="I need five numbered lines, from ATTEMPT 1 to ATTEMPT 5.",
                    close_hint="Close. Check where the counter starts and whether the test uses <= or <.",
                    requires=["while (", "attempt"],
                    requires_hint="This needs a real while loop using the attempt variable — not five println statements.",
                ),
                "explanation": (
                    "Five lines from two statements inside a loop. Writing them "
                    "out by hand would have worked for five and not for five "
                    "thousand.\n\n"
                    "The test had to be <= 5 rather than < 5. With < you get four "
                    "lines, because the loop stops the moment attempt reaches 5 "
                    "instead of after it. Whether the last value is included is "
                    "the thing to check every single time you write a loop."
                ),
            },

            {
                "id": "t2",
                "role": "variation",
                "brief": (
                    "Loops can count down as well as up.\n\n"
                    "Starting from 3, print a countdown, then a final line after "
                    "the loop has finished.\n\n"
                    "Expected output:\n"
                    "LOCKOUT IN 3\n"
                    "LOCKOUT IN 2\n"
                    "LOCKOUT IN 1\n"
                    "ACCOUNT LOCKED"
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        int seconds = 3;\n\n"
                    "        // your loop here, then the final line\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Keep going while seconds is greater than 0, and use seconds-- to count down.",
                    "The ACCOUNT LOCKED line goes AFTER the loop's closing brace, so it prints once.",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        int seconds = 3;

        while (seconds > 0) {
            System.out.println("LOCKOUT IN " + seconds);
            seconds--;
        }

        System.out.println("ACCOUNT LOCKED");
    }
}''',
                "wrong": [
                    '''public class Main {
    public static void main(String[] args) {
        int seconds = 3;

        while (seconds > 0) {
            System.out.println("LOCKOUT IN " + seconds);
            System.out.println("ACCOUNT LOCKED");
            seconds--;
        }
    }
}''',
                ],
                "check": make_checker(
                    expected_output="LOCKOUT IN 3\nLOCKOUT IN 2\nLOCKOUT IN 1\nACCOUNT LOCKED",
                    wrong_hint="I need three countdown lines and then one ACCOUNT LOCKED line.",
                    close_hint="If ACCOUNT LOCKED printed more than once, it is inside the loop — move it below the closing brace.",
                    requires=["while ("],
                    requires_hint="This needs a while loop counting down.",
                ),
                "explanation": (
                    "The last line printed once because it sat outside the "
                    "braces. Inside them it would have printed three times.\n\n"
                    "Which side of the closing brace a statement is on decides "
                    "whether it happens once or many times, and the indentation "
                    "is only a hint — the braces are what count.\n\n"
                    "That distinction becomes the difference between a summary "
                    "printed once and a summary printed after every log line."
                ),
            },

            {
                "id": "t3",
                "role": "apply",
                "brief": (
                    "Counting is what detection is made of. A loop can count "
                    "while it works.\n\n"
                    "Go through attempts numbered 1 to 6. Print a line for each "
                    "one saying whether it failed — attempts 2, 3 and 5 failed. "
                    "Keep a running total of failures and print it at the end.\n\n"
                    "For this task, treat an attempt as failed when its number is "
                    "2, 3 or 5. Use an if inside the loop.\n\n"
                    "Expected output:\n"
                    "ATTEMPT 1 OK\n"
                    "ATTEMPT 2 FAIL\n"
                    "ATTEMPT 3 FAIL\n"
                    "ATTEMPT 4 OK\n"
                    "ATTEMPT 5 FAIL\n"
                    "ATTEMPT 6 OK\n"
                    "TOTAL FAILURES: 3"
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        int attempt = 1;\n"
                    "        int failures = 0;\n\n"
                    "        // your loop here, then the total\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Inside the loop, test whether attempt is 2, 3 or 5 — you can write that as three tests joined by || which means 'or'.",
                    "if (attempt == 2 || attempt == 3 || attempt == 5) { print FAIL; failures++; } else { print OK; }",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        int attempt = 1;
        int failures = 0;

        while (attempt <= 6) {
            if (attempt == 2 || attempt == 3 || attempt == 5) {
                System.out.println("ATTEMPT " + attempt + " FAIL");
                failures++;
            } else {
                System.out.println("ATTEMPT " + attempt + " OK");
            }
            attempt++;
        }

        System.out.println("TOTAL FAILURES: " + failures);
    }
}''',
                "wrong": [
                    '''public class Main {
    public static void main(String[] args) {
        int attempt = 1;
        int failures = 0;

        while (attempt <= 6) {
            if (attempt == 2 || attempt == 3 || attempt == 5) {
                System.out.println("ATTEMPT " + attempt + " FAIL");
                failures++;
            } else {
                System.out.println("ATTEMPT " + attempt + " OK");
            }
            System.out.println("TOTAL FAILURES: " + failures);
            attempt++;
        }
    }
}''',
                ],
                "check": make_checker(
                    expected_output=(
                        "ATTEMPT 1 OK\nATTEMPT 2 FAIL\nATTEMPT 3 FAIL\n"
                        "ATTEMPT 4 OK\nATTEMPT 5 FAIL\nATTEMPT 6 OK\n"
                        "TOTAL FAILURES: 3"
                    ),
                    wrong_hint="I need six attempt lines and then the total.",
                    close_hint="Close. Check the total prints once, after the loop, and that it counts 3.",
                    requires=["while (", "if (", "failures"],
                    requires_hint="This needs a loop, an if inside it, and a failures counter that goes up.",
                ),
                "explanation": (
                    "Two counters doing different jobs. One walks through the "
                    "attempts; the other only moves when something is wrong.\n\n"
                    "That second counter is the whole idea behind detection. The "
                    "loop sees every event; the counter remembers only the ones "
                    "that matter, and the number at the end is what a person "
                    "actually reads.\n\n"
                    "The || you used means 'or' — true when any one side is true. "
                    "Chapter 5 covers it properly."
                ),
            },

            {
                "id": "t4",
                "role": "combine",
                "brief": (
                    "The full chapter, from an empty editor. Write the class and "
                    "main yourself.\n\n"
                    "Print the header line --- LOGIN AUDIT ---\n\n"
                    "Then loop through attempts 1 to 8. Attempts 3, 4, 6 and 7 "
                    "failed; count them. Print nothing per attempt.\n\n"
                    "After the loop, print the total, then a verdict: BRUTE FORCE "
                    "SUSPECTED when the failure count is 4 or more, otherwise "
                    "NORMAL ACTIVITY.\n\n"
                    "Expected output:\n"
                    "--- LOGIN AUDIT ---\n"
                    "FAILURES: 4\n"
                    "BRUTE FORCE SUSPECTED\n\n"
                    "The count must be worked out by the loop, not typed in."
                ),
                "starter": "",
                "hints": [
                    "The loop body needs only the if and the counter — nothing is printed inside it.",
                    "After the loop: print the total, then if (failures >= 4) { ... } else { ... }",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        int attempt = 1;
        int failures = 0;

        System.out.println("--- LOGIN AUDIT ---");

        while (attempt <= 8) {
            if (attempt == 3 || attempt == 4 || attempt == 6 || attempt == 7) {
                failures++;
            }
            attempt++;
        }

        System.out.println("FAILURES: " + failures);

        if (failures >= 4) {
            System.out.println("BRUTE FORCE SUSPECTED");
        } else {
            System.out.println("NORMAL ACTIVITY");
        }
    }
}''',
                "check": make_checker(
                    expected_output="--- LOGIN AUDIT ---\nFAILURES: 4\nBRUTE FORCE SUSPECTED",
                    wrong_hint="Output does not match, or it did not compile. Three lines: header, count, verdict.",
                    close_hint="Close. The count has to come from the loop, and nothing should print inside the loop.",
                    requires=["public class Main", "while (", "if (", "failures"],
                    requires_hint="I need the class and main written yourself, a while loop that counts the failures, and an if for the verdict.",
                ),
                "explanation": (
                    "A loop that prints nothing. It did its work silently and "
                    "left one number behind, and the report is three lines long "
                    "no matter how many attempts it examined.\n\n"
                    "That is the shape of every log analyser: read a lot, say "
                    "little. Change the 8 to 8000 and the report stays three "
                    "lines.\n\n"
                    "You now have the header, the counting and the verdict. In "
                    "the next chapters those stop being hardcoded numbers and "
                    "start being real log lines."
                ),
            },

            {
                "id": "t5",
                "role": "repair",
                "brief": (
                    "This loop never stops. It prints SCANNING for ever, and you "
                    "would have to press Ctrl+C to end it.\n\n"
                    "Here it is stopped for you after a moment, so nothing will "
                    "hang. Find why it never ends and fix it, so it prints "
                    "exactly three lines:\n\n"
                    "SCANNING 1\n"
                    "SCANNING 2\n"
                    "SCANNING 3"
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        int scan = 1;\n\n"
                    "        while (scan <= 3) {\n"
                    "            System.out.println(\"SCANNING \" + scan);\n"
                    "        }\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Look at what changes between one run of the block and the next. Nothing does.",
                    "The counter is never increased. Add scan++ inside the loop.",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        int scan = 1;

        while (scan <= 3) {
            System.out.println("SCANNING " + scan);
            scan++;
        }
    }
}''',
                "check": make_checker(
                    expected_output="SCANNING 1\nSCANNING 2\nSCANNING 3",
                    wrong_hint="I need exactly three lines, SCANNING 1 to SCANNING 3.",
                    close_hint="Close. Check where the counter goes up and that the test uses <=.",
                    requires=["while (", "scan"],
                    requires_hint="Keep the while loop and fix it — the counter needs to change inside the block.",
                ),
                "explanation": (
                    "The test was fine and the block was fine. The problem was "
                    "that nothing in the block ever changed what the test was "
                    "looking at, so scan stayed 1 and 1 <= 3 was true for ever.\n\n"
                    "An endless loop does not crash and does not warn you. The "
                    "program just never finishes, and the only sign is output "
                    "that will not stop.\n\n"
                    "When that happens, the question is always the same: what "
                    "inside this block is supposed to move the test towards "
                    "false?"
                ),
            },
        ],
    },
}
