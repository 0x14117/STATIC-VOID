/**
 * LABS - CAMPAIGN 00 - INIT
 *
 * Ten small labs. Campaign 00 teaches printing, escapes, comments and
 * reading compiler errors, so every lab here is about producing exact output
 * or getting a program to build. Nothing reads input yet.
 */
public class Campaign00Labs {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(1), "First Contact", Lab.SMALL)
            .after("C00-M002")
            .brief(
                "Your first program at NORTHSTAR SYSTEMS. The terminal on the "
                + "security desk should greet the analyst with two lines when "
                + "it starts.\n\n"
                + "Small, but it is the whole cycle: write, compile, run, check "
                + "the output character by character.")
            .practises("The class and main skeleton", "System.out.println",
                       "Exact output")
            .spec(
                "The class must be called Main.",
                "Print NORTHSTAR SYSTEMS on the first line.",
                "Print Analyst terminal online on the second line.",
                "Nothing else. Capitals and spaces exactly as shown.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // print the two lines here",
                "    }",
                "}")
            .hints(
                "Each line of output is one println statement. You need two.",
                "The text goes inside double quotes, inside the brackets, and "
                + "the statement ends with a semicolon:\n"
                + "\n"
                + "    System.out.println(\"...\");",
                "The first one in full:\n"
                + "\n"
                + "    System.out.println(\"NORTHSTAR SYSTEMS\");\n"
                + "\n"
                + "The second has the same shape.",
                "If a test fails, compare your line with the expected one "
                + "letter by letter. 'Analyst Terminal Online' with capitals is "
                + "a different line from 'Analyst terminal online'.")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"NORTHSTAR SYSTEMS\");",
                "        System.out.println(\"Analyst terminal online\");",
                "    }",
                "}")
            .walkthrough(
                "Every Java program needs a class, and running it starts at the "
                + "main method inside that class. Those two wrappers were in the "
                + "starter.\n"
                + "\n"
                + "Each println prints the text between the quotes and then moves "
                + "to a new line, so two println statements make two lines, in "
                + "the order they are written.\n"
                + "\n"
                + "The test compares every character. That strictness is not "
                + "pedantry: output that other programs read, such as logs and "
                + "reports, only works when it is exactly right.")
            .sample(Lab.NO_INPUT,
                "NORTHSTAR SYSTEMS",
                "Analyst terminal online"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(2), "The Login Banner", Lab.SMALL)
            .after("C00-M006")
            .brief(
                "Every NORTHSTAR server shows a banner before anyone logs in. "
                + "Legal have approved the exact wording, spacing and blank "
                + "line, so it must come out precisely as written - a banner "
                + "that differs from the approved one does not count as a "
                + "warning in court.")
            .practises("println", "Blank lines with println()", "Exact output")
            .spec(
                "Print the banner exactly as the sample run shows.",
                "The rows of = signs are 30 characters long.",
                "The two middle lines start with two spaces.",
                "There is one blank line before the last line.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"==============================\");",
                "        // the rest of the banner",
                "    }",
                "}")
            .hints(
                "Count the lines in the sample run, including the blank one. "
                + "That is how many println statements you need.",
                "Spaces inside the quotes are printed exactly. For the indented "
                + "lines, put two spaces straight after the opening quote.",
                "A blank line is println with nothing in the brackets:\n"
                + "\n"
                + "    System.out.println();",
                "The line of = signs appears twice. The starter already has one "
                + "you can copy.")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"==============================\");",
                "        System.out.println(\"  NORTHSTAR SYSTEMS\");",
                "        System.out.println(\"  Authorised access only\");",
                "        System.out.println(\"==============================\");",
                "        System.out.println();",
                "        System.out.println(\"Activity on this system is monitored.\");",
                "    }",
                "}")
            .walkthrough(
                "Six lines of output, six println statements, in order. The "
                + "indentation of the two middle lines is two spaces INSIDE the "
                + "quotes, because only what is inside the quotes is printed - "
                + "the spaces used to indent the Java code itself never appear.\n"
                + "\n"
                + "println() with nothing in the brackets prints nothing and "
                + "then moves to a new line, which is exactly a blank line.")
            .sample(Lab.NO_INPUT,
                "==============================",
                "  NORTHSTAR SYSTEMS",
                "  Authorised access only",
                "==============================",
                "",
                "Activity on this system is monitored."));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(3), "Fix the Build", Lab.SMALL)
            .after("C00-M008")
            .brokenStarter()
            .brief(
                "The scan announcer was written in a hurry and does not "
                + "compile. It has exactly three mistakes, one on each print "
                + "line.\n\n"
                + "Do not rewrite it. Use Test to see what the compiler says, "
                + "fix the first error it reports, and test again. Reading "
                + "errors is the skill this lab is for.")
            .practises("Reading compiler errors", "Semicolons",
                       "Spelling and capitals in Java names")
            .spec(
                "Fix the three mistakes so the program compiles.",
                "Do not change the text inside the quotes.",
                "The output must be the three lines in the sample run.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"SCAN STARTED\")",
                "        System.out.printn(\"HOSTS: 6\");",
                "        system.out.println(\"SCAN COMPLETE\");",
                "    }",
                "}")
            .hints(
                "Choose Test. The compiler lists errors with a line number after "
                + "Main.java: - start with the first one.",
                "'; expected' means a statement is missing its semicolon. Java "
                + "often reports it at the END of the line where the statement "
                + "should have stopped.",
                "'cannot find symbol' means Java does not recognise a name. "
                + "Compare printn and system with the working line above them, "
                + "letter by letter.",
                "The three fixes: a semicolon at the end of line 3, println "
                + "instead of printn on line 4, and a capital S on System on "
                + "line 5.")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"SCAN STARTED\");",
                "        System.out.println(\"HOSTS: 6\");",
                "        System.out.println(\"SCAN COMPLETE\");",
                "    }",
                "}")
            .walkthrough(
                "Three different errors, three different messages:\n"
                + "\n"
                + "    line 3   ';' expected        missing semicolon\n"
                + "    line 4   cannot find symbol  printn is not a method\n"
                + "    line 5   package system does not exist\n"
                + "\n"
                + "The last one reads strangely: with a small s, Java guesses "
                + "that system might be a package name. The message is wrong "
                + "about why, but right about WHERE, and that is the part to "
                + "trust.\n"
                + "\n"
                + "Fixing one error at a time, from the top, matters. One mistake "
                + "can confuse the compiler into reporting others that are not "
                + "really there.")
            .sample(Lab.NO_INPUT,
                "SCAN STARTED",
                "HOSTS: 6",
                "SCAN COMPLETE"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(4), "One Line, Four Pieces", Lab.SMALL)
            .stretch()
            .after("C00-M006")
            .brief(
                "The account monitor writes one status line per account. Later "
                + "each piece will come from a different part of the program, "
                + "so the line has to be built from separate pieces now.\n\n"
                + "Build it with four print statements and finish the line with "
                + "one println.")
            .practises("print against println", "Building one line from pieces")
            .spec(
                "Print USER: jsmith | HOST: WEB-01 | STATUS: LOCKED as a single line.",
                "Use print for the first three pieces and println for the last.",
                "Then print Monitor done on its own line.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.print(\"USER: jsmith\");",
                "        // three more pieces, then the last line",
                "    }",
                "}")
            .hints(
                "print does not move to a new line, so the next piece carries "
                + "straight on from where the last one stopped.",
                "The separators and their spaces belong to a piece. The second "
                + "piece could be \" | HOST: WEB-01\" - note the leading space.",
                "The fourth piece uses println, which ends the line after "
                + "printing.",
                "Monitor done is a separate println after that.")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.print(\"USER: jsmith\");",
                "        System.out.print(\" | HOST: WEB-01\");",
                "        System.out.print(\" | STATUS: \");",
                "        System.out.println(\"LOCKED\");",
                "        System.out.println(\"Monitor done\");",
                "    }",
                "}")
            .walkthrough(
                "print leaves the cursor where the text ended, so each piece "
                + "joins on to the one before. Where the spaces go is up to you, "
                + "as long as each one is printed exactly once.\n"
                + "\n"
                + "The println on LOCKED is what ends the line. Without it, "
                + "Monitor done would be glued to the end of the status line.")
            .sample(Lab.NO_INPUT,
                "USER: jsmith | HOST: WEB-01 | STATUS: LOCKED",
                "Monitor done"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(5), "Host Table", Lab.SMALL)
            .after("C00-M007")
            .brief(
                "The morning report lists each host and its status in two "
                + "columns. Host names are different lengths, so spaces alone "
                + "will not line them up neatly - tabs will.")
            .practises("The tab escape \\t", "Columns of output")
            .spec(
                "Print a header row HOST and STATUS, separated by a tab.",
                "Then one row each for DC-01 online, WEB-01 online and DB-01 offline, in that order.",
                "Use \\t between the columns, not spaces.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"HOST\\tSTATUS\");",
                "        // one row per host",
                "    }",
                "}")
            .hints(
                "\\t inside the quotes is a tab: it jumps to the next column "
                + "stop.",
                "Every row has the same shape as the header: a host name, then "
                + "\\t, then the status.",
                "    System.out.println(\"DC-01\\tonline\");")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"HOST\\tSTATUS\");",
                "        System.out.println(\"DC-01\\tonline\");",
                "        System.out.println(\"WEB-01\\tonline\");",
                "        System.out.println(\"DB-01\\toffline\");",
                "    }",
                "}")
            .walkthrough(
                "A tab moves to the next tab stop, and terminals put a stop "
                + "every 8 columns. Every name here is shorter than 8 "
                + "characters, so every status lands in the same column, "
                + "whatever the length of the name before it.\n"
                + "\n"
                + "That is also its limit: a host name of 8 or more characters "
                + "pushes its status to the NEXT stop and the column breaks. "
                + "printf, in Campaign 01, is the tool that solves that "
                + "properly.")
            .sample(Lab.NO_INPUT,
                "HOST\tSTATUS",
                "DC-01\tonline",
                "WEB-01\tonline",
                "DB-01\toffline"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(6), "Quote the Alert", Lab.SMALL)
            .after("C00-M007")
            .brief(
                "An alert has to quote the account name exactly and give the "
                + "Windows folder the login came from. Quotes end a String and "
                + "backslashes start an escape, so both need care.")
            .practises("Escaping quotes with \\\"", "Escaping backslashes with \\\\")
            .spec(
                "Print the two lines in the sample run exactly.",
                "The account name admin appears inside double quotes.",
                "The path uses single backslashes, as Windows shows it.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // line 1: the alert with the quoted account name",
                "        // line 2: the path",
                "    }",
                "}")
            .hints(
                "Inside a String, \\\" prints a double quote without ending "
                + "the String.",
                "A backslash starts an escape, so to print ONE backslash you "
                + "write two: \\\\",
                "The first line in full:\n"
                + "\n"
                + "    System.out.println(\"ALERT: \\\"admin\\\" logged in\");",
                "The path C:\\Users\\Public is written in the code as "
                + "C:\\\\Users\\\\Public.")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"ALERT: \\\"admin\\\" logged in\");",
                "        System.out.println(\"FROM: C:\\\\Users\\\\Public\");",
                "    }",
                "}")
            .walkthrough(
                "\\\" puts a quote character into the text instead of ending "
                + "the String. The quote that really ends it is the last one "
                + "on the line, just before the bracket.\n"
                + "\n"
                + "\\\\ puts a single backslash into the text. Written with one "
                + "backslash, \\U and \\P would be escape sequences Java does "
                + "not know, and the program would not compile.")
            .sample(Lab.NO_INPUT,
                "ALERT: \"admin\" logged in",
                "FROM: C:\\Users\\Public"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(7), "Silence the Debug Lines", Lab.SMALL)
            .stretch()
            .after("C00-M004")
            .brief(
                "A colleague left debug messages in the patch report. The "
                + "report must go out without them - but they may be needed "
                + "again tomorrow, so comment them out rather than deleting "
                + "them.")
            .practises("Line comments //", "Block comments /* */",
                       "Commenting code out")
            .spec(
                "The output must be only the three REPORT lines, in order.",
                "Comment out the DEBUG lines instead of deleting them.",
                "Use // on at least one line and /* */ around at least one.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"DEBUG: loading host list\");",
                "        System.out.println(\"REPORT: 6 hosts checked\");",
                "        System.out.println(\"DEBUG: patch table read\");",
                "        System.out.println(\"DEBUG: 3 rows\");",
                "        System.out.println(\"REPORT: 2 hosts need patching\");",
                "        System.out.println(\"REPORT: next window Friday 02:00\");",
                "    }",
                "}")
            .hints(
                "// at the start of a line makes Java ignore the rest of that "
                + "line.",
                "/* starts a comment that runs until */, however many lines "
                + "later. It is the quick way to switch off a block.",
                "The two DEBUG lines in the middle sit together, so one /* */ "
                + "around both of them works. The first DEBUG line can take a "
                + "//.")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // System.out.println(\"DEBUG: loading host list\");",
                "        System.out.println(\"REPORT: 6 hosts checked\");",
                "        /*",
                "        System.out.println(\"DEBUG: patch table read\");",
                "        System.out.println(\"DEBUG: 3 rows\");",
                "        */",
                "        System.out.println(\"REPORT: 2 hosts need patching\");",
                "        System.out.println(\"REPORT: next window Friday 02:00\");",
                "    }",
                "}")
            .walkthrough(
                "Java never sees commented-out code, so the DEBUG lines do "
                + "nothing - but they are still there to switch back on by "
                + "removing the comment marks.\n"
                + "\n"
                + "The test can only check the output, so it cannot tell whether "
                + "you commented or deleted. That part of the specification is "
                + "about habit: in a real incident, the debug line someone "
                + "deleted last month is the one you wish you still had.")
            .sample(Lab.NO_INPUT,
                "REPORT: 6 hosts checked",
                "REPORT: 2 hosts need patching",
                "REPORT: next window Friday 02:00"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(8), "Read the Error", Lab.SMALL)
            .after("C00-M008")
            .brokenStarter()
            .brief(
                "Another broken program, and this time the compiler's messages "
                + "are less obvious. One String is never closed, and the file "
                + "ends too early.\n\n"
                + "Test it, read what javac says, and fix it without retyping "
                + "the whole program.")
            .practises("unclosed string literal", "reached end of file while parsing",
                       "Matching braces")
            .spec(
                "Make the program compile without changing any of the text it prints.",
                "The output must match the sample run.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"Checking hosts...);",
                "        System.out.println(\"DC-01 ok\");",
                "        System.out.println(\"WEB-01 ok\");",
                "        System.out.println(\"Check complete\");",
                "    }")
            .hints(
                "'unclosed string literal' means a String was opened with a "
                + "quote and never closed with another one. Look at the line the "
                + "error names.",
                "The closing quote must come before the bracket: \"text\") not "
                + "\"text).",
                "'reached end of file while parsing' means Java ran out of file "
                + "while a { was still open. Count the { and } in the file.",
                "Two { are opened - the class and main - but only one } closes. "
                + "Add a } on a new last line.")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"Checking hosts...\");",
                "        System.out.println(\"DC-01 ok\");",
                "        System.out.println(\"WEB-01 ok\");",
                "        System.out.println(\"Check complete\");",
                "    }",
                "}")
            .walkthrough(
                "Line 3 opened a String and never closed it, so Java could not "
                + "tell where the text ended. One quote before the bracket fixes "
                + "it.\n"
                + "\n"
                + "The file also ended with the class still open. Every { needs "
                + "a matching }, and 'reached end of file while parsing' is how "
                + "the compiler says it got to the end still waiting for one.\n"
                + "\n"
                + "Notice you may only have seen the second error after fixing "
                + "the first. Compilers often stop at the worst problem, so "
                + "fixing one error can reveal the next - that is progress, not "
                + "a step backwards.")
            .sample(Lab.NO_INPUT,
                "Checking hosts...",
                "DC-01 ok",
                "WEB-01 ok",
                "Check complete"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(9), "Analyst Badge", Lab.SMALL)
            .stretch()
            .after("C00-M009")
            .brief(
                "The terminal prints a badge when an analyst signs in. It is "
                + "ASCII art, so every character counts - including the "
                + "backslashes in the corners, which need escaping.")
            .practises("Exact output", "Escaping backslashes",
                       "Planning output line by line")
            .spec(
                "Print the badge exactly as the sample run shows, five lines.",
                "Each line of the badge is 22 characters wide.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\" /------------------\\\\\");",
                "        // the rest of the badge",
                "    }",
                "}")
            .hints(
                "Copy each line of the sample run into its own println, then "
                + "fix the escapes.",
                "Every \\ in the badge must be written \\\\ in your code. "
                + "Forward slashes / need nothing special.",
                "The middle lines start with a | and a space, and end with "
                + "spaces and a |. Count the spaces in the sample run carefully - "
                + "every line is 22 characters.",
                "The last line mirrors the first: a \\ then dashes then a /.")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\" /------------------\\\\\");",
                "        System.out.println(\" | NORTHSTAR  SOC   |\");",
                "        System.out.println(\" | ANALYST  jsmith  |\");",
                "        System.out.println(\" | CLEARANCE  L1    |\");",
                "        System.out.println(\" \\\\------------------/\");",
                "    }",
                "}")
            .walkthrough(
                "Five println statements, one per line of the picture. The only "
                + "trap is the backslash: in Java code it begins an escape, so a "
                + "real backslash is written twice. The first line's \\\\ at "
                + "the end prints one \\, and the last line's \\\\ at the start "
                + "prints one \\.\n"
                + "\n"
                + "Drawing a box is really an exercise in counting. When the "
                + "output is compared exactly, a missing space breaks the right "
                + "edge - and the test catches it long before a person would.")
            .sample(Lab.NO_INPUT,
                " /------------------\\",
                " | NORTHSTAR  SOC   |",
                " | ANALYST  jsmith  |",
                " | CLEARANCE  L1    |",
                " \\------------------/"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(10), "Shift Handover Note", Lab.SMALL)
            .after("C00-M010")
            .brief(
                "At the end of the night shift, the outgoing analyst leaves a "
                + "handover note for the incoming one. It uses everything from "
                + "Campaign 00: println, print, blank lines, tabs, quotes and "
                + "exact spacing.")
            .practises("println and print together", "\\t and \\\"",
                       "Planning a whole output")
            .spec(
                "Print the note exactly as the sample run shows.",
                "Use \\t after Outgoing: and Incoming: so the names line up.",
                "Build the Status line from two pieces: print \"Status: \" "
                + "then println the rest.",
                "The account name contractor appears in double quotes.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"SHIFT HANDOVER - NIGHT\");",
                "        System.out.println(\"----------------------\");",
                "        // the rest of the note",
                "    }",
                "}")
            .hints(
                "Work down the sample run one line at a time. Each line is one "
                + "println, except Status, which is a print and a println.",
                "Outgoing:\\ta.okafor - the \\t goes straight after the colon, "
                + "with no space.",
                "The open items start with two spaces, then the number and a "
                + "full stop.",
                "The quoted account: \\\"contractor\\\" inside the String.")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"SHIFT HANDOVER - NIGHT\");",
                "        System.out.println(\"----------------------\");",
                "        System.out.println(\"Outgoing:\\ta.okafor\");",
                "        System.out.println(\"Incoming:\\tm.reyes\");",
                "        System.out.println();",
                "        System.out.println(\"Open items:\");",
                "        System.out.println(\"  1. \\\"contractor\\\" locked at 03:12\");",
                "        System.out.println(\"  2. WEB-01 patch moved to Friday\");",
                "        System.out.print(\"Status: \");",
                "        System.out.println(\"ALL SYSTEMS MONITORED\");",
                "    }",
                "}")
            .walkthrough(
                "Most of the note is one println per line. Three details carry "
                + "the Campaign 00 lessons:\n"
                + "\n"
                + "    \\t          lines the two names up in one column\n"
                + "    \\\"          quotes the account inside the text\n"
                + "    print       leaves the Status line open for the rest\n"
                + "\n"
                + "The empty println() makes the blank line that separates the "
                + "people from the open items. Short, but every piece of this "
                + "campaign is in it - which is what a capstone is for.")
            .sample(Lab.NO_INPUT,
                "SHIFT HANDOVER - NIGHT",
                "----------------------",
                "Outgoing:\ta.okafor",
                "Incoming:\tm.reyes",
                "",
                "Open items:",
                "  1. \"contractor\" locked at 03:12",
                "  2. WEB-01 patch moved to Friday",
                "Status: ALL SYSTEMS MONITORED"));
    }
}
