/**
 * LABS - CAMPAIGN 01 - JAVA ZERO
 *
 * Labs 1 to 4 use values written into the program, because keyboard input
 * is not taught until mission 21. From lab 5 on, programs read input, and
 * every one of them has hidden tests with different values.
 */
public class Campaign01Labs {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(1), "Running Total", Lab.SMALL)
            .after("C01-M011")
            .brief(
                "The overnight audit counts failed logins host by host. The "
                + "report should show the running total after each host, so a "
                + "reader can see where the numbers came from.\n\n"
                + "The counts are already in variables. Your job is the total.")
            .practises("int variables", "Compound assignment +=", "Joining text and numbers")
            .spec(
                "Start total at 0 and add each host's failures with +=.",
                "After each addition, print the running total exactly as the sample shows.",
                "Finish with the line TOTAL: and the final total.",
                "Do not type the totals 12, 19 or 24 anywhere - they must be worked out.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int webFailures = 12;",
                "        int dbFailures = 7;",
                "        int fileFailures = 5;",
                "        int total = 0;",
                "        // add each host's failures to total, printing after each",
                "    }",
                "}")
            .hints(
                "Four prints in all: one after each of the three additions, "
                + "and the TOTAL line.",
                "total += webFailures; adds the web server's count to whatever "
                + "total already holds.",
                "The spaces after the colons line the numbers up. They are "
                + "inside the quotes:\n"
                + "\n"
                + "    System.out.println(\"After WEB-01:  \" + total);",
                "The whole pattern, for the first host:\n"
                + "\n"
                + "    total += webFailures;\n"
                + "    System.out.println(\"After WEB-01:  \" + total);\n"
                + "\n"
                + "Repeat for DB-01 (three spaces) and FILE-01 (one space).")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int webFailures = 12;",
                "        int dbFailures = 7;",
                "        int fileFailures = 5;",
                "        int total = 0;",
                "        total += webFailures;",
                "        System.out.println(\"After WEB-01:  \" + total);",
                "        total += dbFailures;",
                "        System.out.println(\"After DB-01:   \" + total);",
                "        total += fileFailures;",
                "        System.out.println(\"After FILE-01: \" + total);",
                "        System.out.println(\"TOTAL: \" + total);",
                "    }",
                "}")
            .walkthrough(
                "total starts at 0 and each += adds one host's count to what is "
                + "already there, so after three additions it holds 24.\n"
                + "\n"
                + "Printing total after each step gives the running figures "
                + "12, 19 and 24 without anyone typing them. Change one host's "
                + "count and every line below it updates on its own - that is "
                + "the reason not to type the totals. A report whose numbers "
                + "are typed by hand is right once and then wrong forever.")
            .sample(Lab.NO_INPUT,
                "After WEB-01:  12",
                "After DB-01:   19",
                "After FILE-01: 24",
                "TOTAL: 24"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(2), "Alert Split", Lab.SMALL)
            .after("C01-M010")
            .brief(
                "Seventeen alerts came in overnight and five analysts are on "
                + "shift. Everyone takes an equal share, and whatever is left "
                + "over goes to the shift lead. Work out both.")
            .practises("Integer division /", "The remainder operator %")
            .spec(
                "Use the variables alerts and analysts already in the starter.",
                "Print the three lines exactly as the sample shows.",
                "Work out the share and the leftover with / and %. Do not type 3 or 2.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int alerts = 17;",
                "        int analysts = 5;",
                "        // print the three lines",
                "    }",
                "}")
            .hints(
                "The first line joins both variables into the text: alerts, "
                + "then \" alerts, \", then analysts, then \" analysts\".",
                "Dividing two ints throws the fraction away, which is exactly "
                + "an equal whole-number share: alerts / analysts.",
                "What is left after sharing out is the remainder: "
                + "alerts % analysts.",
                "    System.out.println(\"Each analyst takes \"\n"
                + "            + alerts / analysts);\n"
                + "    System.out.println(\"Left for the lead: \"\n"
                + "            + alerts % analysts);")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int alerts = 17;",
                "        int analysts = 5;",
                "        System.out.println(alerts + \" alerts, \" + analysts",
                "                + \" analysts\");",
                "        System.out.println(\"Each analyst takes \" + alerts / analysts);",
                "        System.out.println(\"Left for the lead: \" + alerts % analysts);",
                "    }",
                "}")
            .walkthrough(
                "17 / 5 is 3 because both are ints and the fraction is thrown "
                + "away - which for sharing out whole alerts is exactly right. "
                + "17 % 5 is 2, the part that division threw away.\n"
                + "\n"
                + "The first line starts with a number: alerts + \" alerts, \". "
                + "Because the second piece is text, + joins rather than adds. "
                + "In the other lines, / and % run before the + that joins, "
                + "because they have higher precedence, so the arithmetic "
                + "happens first and its result is joined on.")
            .sample(Lab.NO_INPUT,
                "17 alerts, 5 analysts",
                "Each analyst takes 3",
                "Left for the lead: 2"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(3), "Overflow Watch", Lab.SMALL)
            .stretch()
            .after("C01-M015")
            .brief(
                "A byte counter on the firewall sits at the largest value an "
                + "int can hold. One more byte arrives. Show the team what "
                + "happens to an int counter and to a long one, side by side.")
            .practises("Integer.MAX_VALUE", "Integer overflow", "long")
            .spec(
                "Start an int and a long both at Integer.MAX_VALUE.",
                "Print the int before adding anything.",
                "Add one to each with ++, then print both.",
                "Match the labels and spacing in the sample exactly.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int small = Integer.MAX_VALUE;",
                "        long large = Integer.MAX_VALUE;",
                "        // print, add one to each, print again",
                "    }",
                "}")
            .hints(
                "Print small first, before anything changes it.",
                "small++; and large++; each add exactly one.",
                "The int wraps round to the most negative int. The long simply "
                + "goes one higher. You do not need to type either number - "
                + "print the variables.",
                "    System.out.println(\"int before:  \" + small);\n"
                + "    small++;\n"
                + "    large++;\n"
                + "    System.out.println(\"int after:   \" + small);\n"
                + "    System.out.println(\"long after:  \" + large);")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int small = Integer.MAX_VALUE;",
                "        long large = Integer.MAX_VALUE;",
                "        System.out.println(\"int before:  \" + small);",
                "        small++;",
                "        large++;",
                "        System.out.println(\"int after:   \" + small);",
                "        System.out.println(\"long after:  \" + large);",
                "    }",
                "}")
            .walkthrough(
                "Both boxes start with the same value, 2147483647. One more "
                + "does not fit in an int, so the int wraps round to "
                + "-2147483648 with no error at all. The long has room to spare "
                + "and becomes 2147483648.\n"
                + "\n"
                + "That silent wrap is why counters that grow without limit - "
                + "bytes, packets, milliseconds - belong in a long. The program "
                + "never complained; only the output shows anything went "
                + "wrong.")
            .sample(Lab.NO_INPUT,
                "int before:  2147483647",
                "int after:   -2147483648",
                "long after:  2147483648"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(4), "Username Inspector", Lab.SMALL)
            .after("C01-M019")
            .brief(
                "Before an account is audited, the tool prints a short profile "
                + "of its name: how long it is, what it starts and ends with, "
                + "and how it looks in capitals. Every value must come from "
                + "the String itself, so the same code works for any name.")
            .practises("length()", "charAt() and indexes", "toUpperCase()")
            .spec(
                "Use the variable username from the starter.",
                "Print the five lines exactly as the sample shows.",
                "Every value after a label must come from a String method, not typed in.",
                "The last character must be found with length() - 1, not a fixed number.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String username = \"contractor\";",
                "        // print the five lines",
                "    }",
                "}")
            .hints(
                "username.length() gives the number of characters.",
                "The first character is username.charAt(0). Indexes start at 0.",
                "The last character is at index length() - 1:\n"
                + "\n"
                + "    username.charAt(username.length() - 1)",
                "toUpperCase() hands back a new String in capitals. The labels "
                + "each pad to the same width with spaces, inside the quotes.")
            .solution(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String username = \"contractor\";",
                "        System.out.println(\"Username: \" + username);",
                "        System.out.println(\"Length:   \" + username.length());",
                "        System.out.println(\"First:    \" + username.charAt(0));",
                "        int lastIndex = username.length() - 1;",
                "        System.out.println(\"Last:     \" + username.charAt(lastIndex));",
                "        System.out.println(\"Upper:    \" + username.toUpperCase());",
                "    }",
                "}")
            .walkthrough(
                "Every value is asked of the String: its length, the character "
                + "at 0, the character at length() - 1, and an upper-case copy. "
                + "Change contractor to jsmith and every line is still right.\n"
                + "\n"
                + "charAt(username.length() - 1) is the pattern worth "
                + "remembering. charAt(10) happens to work for contractor and "
                + "crashes for any shorter name - the kind of bug that passes "
                + "your own test and fails on the first real account.")
            .sample(Lab.NO_INPUT,
                "Username: contractor",
                "Length:   10",
                "First:    c",
                "Last:     r",
                "Upper:    CONTRACTOR"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(5), "Hello, Analyst", Lab.SMALL)
            .after("C01-M021")
            .brief(
                "The terminal asks for the analyst's name and welcomes them. "
                + "Your first lab that reads the keyboard - and the first with "
                + "hidden tests, which type names you have not seen.")
            .practises("Scanner and nextLine()", "Prompts with print", "Joining input into output")
            .spec(
                "Print the prompt Analyst name: with a space after the colon, using print.",
                "Read the whole line the analyst types.",
                "Print Welcome, <name>. Clearance: TRAINEE using exactly what was typed.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // 1. print the prompt",
                "        // 2. read the name",
                "        // 3. print the welcome line",
                "    }",
                "}")
            .hints(
                "The prompt uses print, not println, so the name is typed on "
                + "the same line:\n"
                + "\n"
                + "    System.out.print(\"Analyst name: \");",
                "input.nextLine() waits for ENTER and hands back what was typed, "
                + "as a String. Store it:\n"
                + "\n"
                + "    String name = input.nextLine();",
                "The welcome line joins text, the name and more text. Watch the "
                + "full stop straight after the name.",
                "    System.out.println(\"Welcome, \" + name\n"
                + "            + \". Clearance: TRAINEE\");")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Analyst name: \");",
                "        String name = input.nextLine();",
                "        System.out.println(\"Welcome, \" + name",
                "                + \". Clearance: TRAINEE\");",
                "    }",
                "}")
            .walkthrough(
                "print leaves the cursor after the prompt, so the name appears on "
                + "the same line as the question - that is the Analyst name: "
                + "jsmith line in the sample.\n"
                + "\n"
                + "nextLine() returns whatever was typed, and the welcome line "
                + "joins it into the text. The hidden tests type other names; "
                + "because your program prints the variable, not the word "
                + "jsmith, it works for all of them. A program that printed "
                + "Welcome, jsmith directly would pass the sample and fail every "
                + "hidden test.")
            .sample(Lab.typing("jsmith"),
                "Analyst name: jsmith",
                "Welcome, jsmith. Clearance: TRAINEE")
            .hidden(Lab.typing("m.reyes"),
                "Analyst name: m.reyes",
                "Welcome, m.reyes. Clearance: TRAINEE")
            .hidden(Lab.typing("a.okafor"),
                "Analyst name: a.okafor",
                "Welcome, a.okafor. Clearance: TRAINEE"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(6), "Queue the Scan", Lab.SMALL)
            .stretch()
            .after("C01-M021")
            .brief(
                "Analysts type host names however they like - web-01, Web-01, "
                + "WEB-01. The scanner's queue uses capitals only. Read the "
                + "host and queue it in the standard form.")
            .practises("Reading input", "toUpperCase()", "Normalising text")
            .spec(
                "Prompt with Host: and a space.",
                "Read the host name.",
                "Print Scan queued for <HOST> with the host in capitals.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // prompt, read, and queue the scan",
                "    }",
                "}")
            .hints(
                "Same shape as the last lab: print a prompt, then nextLine().",
                "toUpperCase() hands back a NEW String. Either store it, or use "
                + "it directly inside the println.",
                "    String host = input.nextLine();\n"
                + "    System.out.println(\"Scan queued for \"\n"
                + "            + host.toUpperCase());")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Host: \");",
                "        String host = input.nextLine();",
                "        System.out.println(\"Scan queued for \" + host.toUpperCase());",
                "    }",
                "}")
            .walkthrough(
                "The typed text is stored as it was typed, and the output uses "
                + "an upper-case copy of it. Every spelling of the same host "
                + "becomes one form, so the queue never holds web-01 and WEB-01 "
                + "as two different jobs.\n"
                + "\n"
                + "Writing host.toUpperCase(); on a line by itself would change "
                + "nothing - Strings never change - and the hidden tests with "
                + "lower-case names would catch it.")
            .sample(Lab.typing("web-01"),
                "Host: web-01",
                "Scan queued for WEB-01")
            .hidden(Lab.typing("db-01"),
                "Host: db-01",
                "Scan queued for DB-01")
            .hidden(Lab.typing("File-01"),
                "Host: File-01",
                "Scan queued for FILE-01"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(7), "Neighbouring Ports", Lab.SMALL)
            .after("C01-M022")
            .brief(
                "A service moved to a different port overnight, and the team "
                + "wants to check the ports either side of it too. Read a port "
                + "number and print its two neighbours.\n\n"
                + "Watch out: analysts sometimes type a space before the number.")
            .practises("Integer.parseInt", "trim() before converting", "Arithmetic on input")
            .spec(
                "Prompt with Port: and a space, and read the line.",
                "Convert it to an int. It may have spaces around it.",
                "Print Below: with the port minus one, then Above: with the port plus one.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Port: \");",
                "        String text = input.nextLine();",
                "        // convert text to a number, then print the neighbours",
                "    }",
                "}")
            .hints(
                "text is a String. \"443\" + 1 would be \"4431\". Convert it "
                + "first.",
                "Integer.parseInt turns text into an int - but it crashes on a "
                + "space. trim() the text first.",
                "    int port = Integer.parseInt(text.trim());",
                "Put the arithmetic in brackets inside the println, or + will "
                + "join instead of adding:\n"
                + "\n"
                + "    System.out.println(\"Below: \" + (port - 1));")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Port: \");",
                "        String text = input.nextLine();",
                "        int port = Integer.parseInt(text.trim());",
                "        System.out.println(\"Below: \" + (port - 1));",
                "        System.out.println(\"Above: \" + (port + 1));",
                "    }",
                "}")
            .walkthrough(
                "trim() removes any spaces at the ends, then parseInt turns the "
                + "digits into an int, so the arithmetic is real arithmetic.\n"
                + "\n"
                + "The brackets matter. \"Above: \" + port + 1 is worked out left "
                + "to right: text plus port is text, and text plus 1 joins a 1 "
                + "on the end - 4431. (port + 1) is added first, as a number, "
                + "and then joined.\n"
                + "\n"
                + "One hidden test types a space before the number. Without trim "
                + "that test crashes with a NumberFormatException - the most "
                + "common way a program meets real input and loses.")
            .sample(Lab.typing("443"),
                "Port: 443",
                "Below: 442",
                "Above: 444")
            .hidden(Lab.typing(" 8080"),
                "Port:  8080",
                "Below: 8079",
                "Above: 8081")
            .hidden(Lab.typing("22 "),
                "Port: 22",
                "Below: 21",
                "Above: 23"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(8), "Seconds to Clock", Lab.SMALL)
            .after("C01-M022")
            .brief(
                "Session lengths are logged in seconds, which nobody can read "
                + "at a glance. 3725 means nothing; 1h 2m 5s means something. "
                + "Read a number of seconds and show it as hours, minutes and "
                + "seconds.")
            .practises("Integer division /", "Remainder %", "Reading a number")
            .spec(
                "Prompt with Seconds: and a space, and read a whole number.",
                "Work out the hours, the minutes left over, and the seconds left over.",
                "Print <n> seconds is <h>h <m>m <s>s exactly as the sample shows.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Seconds: \");",
                "        int total = Integer.parseInt(input.nextLine().trim());",
                "        // work out hours, minutes and seconds, then print",
                "    }",
                "}")
            .hints(
                "An hour is 3600 seconds. total / 3600 gives whole hours.",
                "What is left after the hours is total % 3600. Minutes come from "
                + "that: divide it by 60.",
                "The seconds left over are total % 60.",
                "    int hours = total / 3600;\n"
                + "    int minutes = total % 3600 / 60;\n"
                + "    int seconds = total % 60;")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Seconds: \");",
                "        int total = Integer.parseInt(input.nextLine().trim());",
                "        int hours = total / 3600;",
                "        int minutes = total % 3600 / 60;",
                "        int seconds = total % 60;",
                "        System.out.println(total + \" seconds is \" + hours + \"h \"",
                "                + minutes + \"m \" + seconds + \"s\");",
                "    }",
                "}")
            .walkthrough(
                "Integer division and remainder work as a pair. total / 3600 is "
                + "the number of whole hours; total % 3600 is what is left once "
                + "those hours are taken out. The same trick with 60 splits "
                + "that remainder into minutes and seconds.\n"
                + "\n"
                + "    3725 / 3600        1   hour\n"
                + "    3725 % 3600        125 seconds left\n"
                + "    125 / 60           2   minutes\n"
                + "    3725 % 60          5   seconds\n"
                + "\n"
                + "total % 3600 / 60 needs no brackets: % and / have the same "
                + "precedence and run left to right. The hidden tests try "
                + "values like 59 and 86399 to check the edges.")
            .sample(Lab.typing("3725"),
                "Seconds: 3725",
                "3725 seconds is 1h 2m 5s")
            .hidden(Lab.typing("59"),
                "Seconds: 59",
                "59 seconds is 0h 0m 59s")
            .hidden(Lab.typing("86399"),
                "Seconds: 86399",
                "86399 seconds is 23h 59m 59s")
            .hidden(Lab.typing("3600"),
                "Seconds: 3600",
                "3600 seconds is 1h 0m 0s"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(9), "Clamp the Request", Lab.SMALL)
            .after("C01-M023")
            .brief(
                "Callers of the audit API say how many records they want. The "
                + "server never sends more than 100 at once, however many are "
                + "asked for. Read the request and report what will actually "
                + "be sent.")
            .practises("Math.min as a ceiling", "final constants", "Reading a number")
            .spec(
                "Declare a constant MAX_PAGE set to 100.",
                "Prompt with Records requested: and a space, and read a whole number.",
                "Print Sending <n> of <requested> requested, where n is never more than MAX_PAGE.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // the constant, the prompt, reading, and the reply",
                "    }",
                "}")
            .hints(
                "    final int MAX_PAGE = 100;",
                "Read the number the same way as the last lab: nextLine, trim, "
                + "Integer.parseInt.",
                "A ceiling is the SMALLER of what was asked for and the limit: "
                + "Math.min.",
                "    int sending = Math.min(requested, MAX_PAGE);\n"
                + "    System.out.println(\"Sending \" + sending\n"
                + "            + \" of \" + requested + \" requested\");")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        final int MAX_PAGE = 100;",
                "        System.out.print(\"Records requested: \");",
                "        int requested = Integer.parseInt(input.nextLine().trim());",
                "        int sending = Math.min(requested, MAX_PAGE);",
                "        System.out.println(\"Sending \" + sending + \" of \" + requested",
                "                + \" requested\");",
                "    }",
                "}")
            .walkthrough(
                "Math.min(requested, MAX_PAGE) picks whichever is smaller. Ask "
                + "for 5000 and the answer is 100; ask for 20 and it is 20. The "
                + "ceiling never gets in the way of an honest request.\n"
                + "\n"
                + "Keeping the original requested value in the output matters "
                + "too: someone asking for five thousand records is worth "
                + "noticing, and the reply shows it. The hidden tests include "
                + "100 and 101 - the boundary, where a wrong operator shows up.")
            .sample(Lab.typing("5000"),
                "Records requested: 5000",
                "Sending 100 of 5000 requested")
            .hidden(Lab.typing("20"),
                "Records requested: 20",
                "Sending 20 of 20 requested")
            .hidden(Lab.typing("100"),
                "Records requested: 100",
                "Sending 100 of 100 requested")
            .hidden(Lab.typing("101"),
                "Records requested: 101",
                "Sending 100 of 101 requested"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(10), "Clock Drift", Lab.SMALL)
            .stretch()
            .after("C01-M023")
            .brief(
                "Log timestamps from two machines only line up if their clocks "
                + "agree. Read each clock as seconds since midnight and report "
                + "how far apart they are - whichever one is ahead.")
            .practises("Math.abs", "Reading two numbers", "Subtraction")
            .spec(
                "Prompt Server clock: then Laptop clock: , each followed by a space, reading a number after each.",
                "Print Drift: <n> seconds, where n is the difference and never negative.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read both clocks, then print the drift",
                "    }",
                "}")
            .hints(
                "Two prompts, two reads. Each read is nextLine, trim, "
                + "Integer.parseInt.",
                "server - laptop is negative when the laptop is ahead. You want "
                + "the size of the difference, not its direction.",
                "Math.abs removes the minus sign:\n"
                + "\n"
                + "    int drift = Math.abs(server - laptop);")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Server clock: \");",
                "        int server = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Laptop clock: \");",
                "        int laptop = Integer.parseInt(input.nextLine().trim());",
                "        int drift = Math.abs(server - laptop);",
                "        System.out.println(\"Drift: \" + drift + \" seconds\");",
                "    }",
                "}")
            .walkthrough(
                "server - laptop is positive when the server is ahead and "
                + "negative when the laptop is. Math.abs turns either into the "
                + "distance between them, which is the only thing that matters "
                + "for lining logs up.\n"
                + "\n"
                + "One hidden test gives the laptop the larger value; another "
                + "gives equal clocks. Without abs, the first prints a negative "
                + "drift - a number that looks like a bug report rather than a "
                + "measurement.")
            .sample(Lab.typing("36000", "36042"),
                "Server clock: 36000",
                "Laptop clock: 36042",
                "Drift: 42 seconds")
            .hidden(Lab.typing("50000", "49990"),
                "Server clock: 50000",
                "Laptop clock: 49990",
                "Drift: 10 seconds")
            .hidden(Lab.typing("100", "100"),
                "Server clock: 100",
                "Laptop clock: 100",
                "Drift: 0 seconds"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(11), "Score Rounding", Lab.SMALL)
            .stretch()
            .after("C01-M024")
            .brief(
                "The weekly report shows each vulnerability score twice: to the "
                + "nearest whole number for the summary table, and to one "
                + "decimal place for the detail. Read a score and produce both.")
            .practises("Double.parseDouble", "Math.round", "Rounding to one decimal place")
            .spec(
                "Prompt with Score: and a space, and read a decimal number.",
                "Print Nearest whole: and the score rounded to the nearest whole number.",
                "Print One decimal:   and the score rounded to one decimal place.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Score: \");",
                "        // read the score, then print both roundings",
                "    }",
                "}")
            .hints(
                "The score has a decimal point, so convert it with "
                + "Double.parseDouble, not Integer.parseInt.",
                "Math.round(score) gives the nearest whole number. A cast "
                + "(int) would chop instead of rounding.",
                "For one decimal place: move the point, round, move it back.\n"
                + "\n"
                + "    Math.round(score * 10) / 10.0",
                "Divide by 10.0, not 10. Dividing by a plain 10 is integer "
                + "division and throws the decimal away again.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Score: \");",
                "        double score = Double.parseDouble(input.nextLine().trim());",
                "        System.out.println(\"Nearest whole: \" + Math.round(score));",
                "        System.out.println(\"One decimal:   \"",
                "                + Math.round(score * 10) / 10.0);",
                "    }",
                "}")
            .walkthrough(
                "Math.round goes to the nearest whole number, halves going up, "
                + "so 7.46 becomes 7 and 6.5 becomes 7.\n"
                + "\n"
                + "Rounding to one place uses the same method on a shifted "
                + "number: 7.46 * 10 is 74.6, which rounds to 75, and 75 / 10.0 "
                + "is 7.5. The .0 is what keeps that last division decimal - "
                + "Math.round hands back a long, and long divided by int would "
                + "be integer division, giving 7.")
            .sample(Lab.typing("7.46"),
                "Score: 7.46",
                "Nearest whole: 7",
                "One decimal:   7.5")
            .hidden(Lab.typing("6.5"),
                "Score: 6.5",
                "Nearest whole: 7",
                "One decimal:   6.5")
            .hidden(Lab.typing("8.25"),
                "Score: 8.25",
                "Nearest whole: 8",
                "One decimal:   8.3")
            .hidden(Lab.typing("0.44"),
                "Score: 0.44",
                "Nearest whole: 0",
                "One decimal:   0.4"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(12), "Email Splitter", Lab.MEDIUM)
            .after("C01-M025")
            .brief(
                "Phishing reports arrive with the sender's email address, typed "
                + "or pasted any old way. The triage tool needs the part before "
                + "the @ as it was written, and the domain in lower case so "
                + "that NorthStar.Example and northstar.example count as the "
                + "same place.")
            .practises("indexOf", "substring", "trim and toLowerCase")
            .spec(
                "Prompt with Email: and a space, and read the address.",
                "Remove any spaces from the ends before doing anything else.",
                "Print User: and everything before the @, as typed.",
                "Print Domain: and everything after the @, in lower case.",
                "Line the two values up exactly as the sample shows.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Email: \");",
                "        String email = input.nextLine();",
                "        // clean it, find the @, split it",
                "    }",
                "}")
            .hints(
                "Clean first: email = email.trim(); - otherwise a pasted space "
                + "ends up inside the user or the domain.",
                "indexOf(\"@\") gives the position of the @. Store it in an int.",
                "Before the @ is substring(0, at). After it is substring(at + 1) "
                + "- the + 1 skips the @ itself.",
                "    int at = email.indexOf(\"@\");\n"
                + "    String user = email.substring(0, at);\n"
                + "    String domain = email.substring(at + 1).toLowerCase();")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Email: \");",
                "        String email = input.nextLine().trim();",
                "        int at = email.indexOf(\"@\");",
                "        String user = email.substring(0, at);",
                "        String domain = email.substring(at + 1).toLowerCase();",
                "        System.out.println(\"User:   \" + user);",
                "        System.out.println(\"Domain: \" + domain);",
                "    }",
                "}")
            .walkthrough(
                "Three steps, in an order that matters: clean, find, cut.\n"
                + "\n"
                + "trim() comes first so a pasted space never becomes part of a "
                + "value. indexOf finds the @ wherever it is - a long name or a "
                + "short one - and the two substrings cut either side of it. "
                + "Only the domain is lower-cased: domains are not case "
                + "sensitive, but the part before the @ can be, so it is kept "
                + "exactly as written.\n"
                + "\n"
                + "This program trusts that there IS an @. With none, indexOf "
                + "gives -1 and substring(0, -1) crashes. Checking first needs "
                + "an if, which is the first thing Campaign 02 teaches.")
            .sample(Lab.typing("a.okafor@northstar.example"),
                "Email: a.okafor@northstar.example",
                "User:   a.okafor",
                "Domain: northstar.example")
            .hidden(Lab.typing("M.Reyes@NorthStar.Example"),
                "Email: M.Reyes@NorthStar.Example",
                "User:   M.Reyes",
                "Domain: northstar.example")
            .hidden(Lab.typing("  jsmith@Partner.EXAMPLE "),
                "Email:   jsmith@Partner.EXAMPLE",
                "User:   jsmith",
                "Domain: partner.example"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(13), "File Extension", Lab.SMALL)
            .stretch()
            .after("C01-M025")
            .brief(
                "The upload filter needs a file's real extension - the part "
                + "after the LAST dot, because invoice.pdf.exe is a program, "
                + "not a PDF. Read a file name and report its extension in "
                + "lower case.")
            .practises("lastIndexOf", "substring", "toLowerCase")
            .spec(
                "Prompt with File: and a space, and read the name.",
                "Print Extension: followed by everything from the last dot onwards, dot included, in lower case.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"File: \");",
                "        String name = input.nextLine();",
                "        // find the extension",
                "    }",
                "}")
            .hints(
                "indexOf finds the FIRST dot. You want the last one.",
                "lastIndexOf(\".\") gives the position of the last dot.",
                "substring(dot) runs from that position to the end, so the dot "
                + "itself is included - which is what the specification asks "
                + "for.",
                "    int dot = name.lastIndexOf(\".\");\n"
                + "    System.out.println(\"Extension: \"\n"
                + "            + name.substring(dot).toLowerCase());")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"File: \");",
                "        String name = input.nextLine();",
                "        int dot = name.lastIndexOf(\".\");",
                "        System.out.println(\"Extension: \"",
                "                + name.substring(dot).toLowerCase());",
                "    }",
                "}")
            .walkthrough(
                "lastIndexOf searches from the end, so it finds the dot that "
                + "actually decides what kind of file this is. Windows looks at "
                + "exactly that dot when it decides whether to run a file.\n"
                + "\n"
                + "Lower-casing matters for the filter that uses this value: "
                + ".EXE, .Exe and .exe are the same thing to Windows, so they "
                + "must become the same text before anything compares them.")
            .sample(Lab.typing("invoice.pdf.EXE"),
                "File: invoice.pdf.EXE",
                "Extension: .exe")
            .hidden(Lab.typing("report.docx"),
                "File: report.docx",
                "Extension: .docx")
            .hidden(Lab.typing("backup.tar.GZ"),
                "File: backup.tar.GZ",
                "Extension: .gz"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(14), "Executable Check", Lab.SMALL)
            .stretch()
            .after("C01-M026")
            .brief(
                "Before an attachment reaches anyone's inbox, the gateway asks "
                + "two questions about its name. Is it a program? And does it "
                + "mention .pdf anywhere, to look like a document? Answer both "
                + "as true or false, whatever mix of capitals the sender used.")
            .practises("endsWith and contains", "Normalising before comparing",
                       "Printing booleans")
            .spec(
                "Prompt with File: and a space, and read the name.",
                "Print Executable: then true or false - does it end with .exe, ignoring case?",
                "Print Mentions pdf: then true or false - does .pdf appear anywhere, ignoring case?")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"File: \");",
                "        String name = input.nextLine();",
                "        // answer both questions",
                "    }",
                "}")
            .hints(
                "Both checks must ignore case. Make a lower-case copy once and "
                + "ask both questions of that.",
                "endsWith answers the first question, contains the second. Each "
                + "gives true or false, which println can print directly.",
                "    String lower = name.toLowerCase();\n"
                + "    System.out.println(\"Executable: \"\n"
                + "            + lower.endsWith(\".exe\"));")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"File: \");",
                "        String name = input.nextLine();",
                "        String lower = name.toLowerCase();",
                "        System.out.println(\"Executable: \" + lower.endsWith(\".exe\"));",
                "        System.out.println(\"Mentions pdf: \" + lower.contains(\".pdf\"));",
                "    }",
                "}")
            .walkthrough(
                "Lower-casing once, then asking both questions of the copy, "
                + "means the sender's choice of capitals cannot matter. "
                + "Payroll.PDF.EXE becomes payroll.pdf.exe: it ends with .exe "
                + "and contains .pdf, so both answers are true - the classic "
                + "shape of a disguised program.\n"
                + "\n"
                + "Next campaign, these booleans go into an if and the gateway "
                + "actually holds the file.")
            .sample(Lab.typing("Payroll.PDF.EXE"),
                "File: Payroll.PDF.EXE",
                "Executable: true",
                "Mentions pdf: true")
            .hidden(Lab.typing("notes.txt"),
                "File: notes.txt",
                "Executable: false",
                "Mentions pdf: false")
            .hidden(Lab.typing("Scan.PDF"),
                "File: Scan.PDF",
                "Executable: false",
                "Mentions pdf: true"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(15), "Password Redactor", Lab.MEDIUM)
            .after("C01-M027")
            .brief(
                "A debug log has been recording passwords in plain text. Before "
                + "the log can be shared with the vendor, every copy of the "
                + "password must be replaced with eight asterisks - including "
                + "the ones you have not noticed.")
            .practises("replace", "Reading two lines", "Strings never change")
            .spec(
                "Prompt Log line: and read the line, then prompt Secret: and read the secret.",
                "Print Safe: followed by the log line with EVERY copy of the secret replaced by ********.",
                "If the secret does not appear, print the line unchanged.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read the line and the secret, then print the safe line",
                "    }",
                "}")
            .hints(
                "Two prompts and two reads, in the order the sample shows.",
                "replace swaps every copy, not just the first. There is no need "
                + "to find them yourself.",
                "replace hands back a NEW String. Store it or print it directly - "
                + "calling it on its own line changes nothing.",
                "    String safe = line.replace(secret, \"********\");\n"
                + "    System.out.println(\"Safe: \" + safe);")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Log line: \");",
                "        String line = input.nextLine();",
                "        System.out.print(\"Secret: \");",
                "        String secret = input.nextLine();",
                "        String safe = line.replace(secret, \"********\");",
                "        System.out.println(\"Safe: \" + safe);",
                "    }",
                "}")
            .walkthrough(
                "replace goes through the whole line and swaps every copy of the "
                + "secret, so a password that appears twice is hidden twice. "
                + "When the secret is not there, replace simply hands back the "
                + "same text - no special case needed.\n"
                + "\n"
                + "Always eight asterisks, whatever the password's length, is "
                + "deliberate: printing one asterisk per character would tell "
                + "the reader how long the password is, which is a clue worth "
                + "not giving away.")
            .sample(Lab.typing("login jsmith pass=Winter2024 retry pass=Winter2024",
                               "Winter2024"),
                "Log line: login jsmith pass=Winter2024 retry pass=Winter2024",
                "Secret: Winter2024",
                "Safe: login jsmith pass=******** retry pass=********")
            .hidden(Lab.typing("token=abc123 user=admin", "abc123"),
                "Log line: token=abc123 user=admin",
                "Secret: abc123",
                "Safe: token=******** user=admin")
            .hidden(Lab.typing("no secrets here", "hunter2"),
                "Log line: no secrets here",
                "Secret: hunter2",
                "Safe: no secrets here"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(16), "Report Row", Lab.SMALL)
            .stretch()
            .after("C01-M028")
            .brief(
                "The morning table has one row per host: name, alert count, "
                + "score. Rows written with + never line up. Use printf so every "
                + "row has the same columns whatever the values.")
            .practises("printf", "Widths: %-8s and %5d", "Decimal places: %5.1f")
            .spec(
                "Read a host with the prompt Host: , a whole number with Alerts: , and a decimal with Score: .",
                "Print one row: the host left-aligned in 8 columns, a space, the alerts right-aligned in 5, a space, the score in 5 columns with 1 decimal place.",
                "In printf terms: %-8s %5d %5.1f and a line break.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read three values, then print the row with printf",
                "    }",
                "}")
            .hints(
                "Read the alerts with Integer.parseInt and the score with "
                + "Double.parseDouble. Trim both first.",
                "The format string is given in the specification. The values go "
                + "after it, in the same order as the gaps.",
                "printf does not add a line break by itself. End the format "
                + "with %n.",
                "    System.out.printf(\"%-8s %5d %5.1f%n\",\n"
                + "            host, alerts, score);")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Host: \");",
                "        String host = input.nextLine().trim();",
                "        System.out.print(\"Alerts: \");",
                "        int alerts = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Score: \");",
                "        double score = Double.parseDouble(input.nextLine().trim());",
                "        System.out.printf(\"%-8s %5d %5.1f%n\", host, alerts, score);",
                "    }",
                "}")
            .walkthrough(
                "Each specifier reserves a fixed width, so the columns land in "
                + "the same place on every row:\n"
                + "\n"
                + "    %-8s    the host, padded on the right to 8\n"
                + "    %5d     the count, padded on the left to 5\n"
                + "    %5.1f   the score, 1 decimal place, 5 wide\n"
                + "\n"
                + "The hidden tests use a longer and a shorter host name and a "
                + "three-digit count. With +, each would shift the columns; with "
                + "printf they stay put.")
            .sample(Lab.typing("WEB-01", "12", "7.46"),
                "Host: WEB-01",
                "Alerts: 12",
                "Score: 7.46",
                "WEB-01      12   7.5")
            .hidden(Lab.typing("FILE-01", "140", "9.8"),
                "Host: FILE-01",
                "Alerts: 140",
                "Score: 9.8",
                "FILE-01    140   9.8")
            .hidden(Lab.typing("DC-01", "3", "4"),
                "Host: DC-01",
                "Alerts: 3",
                "Score: 4",
                "DC-01        3   4.0"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(17), "Failure Rate", Lab.SMALL)
            .stretch()
            .after("C01-M028")
            .brief(
                "Five failures out of forty logins is not alarming. Five out of "
                + "six is. The rate matters more than the count, so the audit "
                + "shows failed logins as a percentage.")
            .practises("Casting to double", "Percentages", "printf with %.1f")
            .spec(
                "Read the failed logins with the prompt Failed: and the total with Total: .",
                "Print Failure rate: then the percentage to 1 decimal place, then a space and the word percent.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read both numbers, then print the rate",
                "    }",
                "}")
            .hints(
                "failed / total with two ints is integer division: 5 / 40 is 0. "
                + "One side must be a double BEFORE dividing.",
                "(double) failed / total keeps the fraction. Multiply by 100 for "
                + "a percentage.",
                "    double rate = (double) failed / total * 100;\n"
                + "    System.out.printf(\"Failure rate: %.1f percent%n\", rate);")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Failed: \");",
                "        int failed = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Total: \");",
                "        int total = Integer.parseInt(input.nextLine().trim());",
                "        double rate = (double) failed / total * 100;",
                "        System.out.printf(\"Failure rate: %.1f percent%n\", rate);",
                "    }",
                "}")
            .walkthrough(
                "The cast is the whole lab. failed / total on two ints gives 0 "
                + "for 5 / 40, and multiplying 0 by 100 is still 0 - a report "
                + "that says nothing is failing while a fifth of logins fail. "
                + "(double) failed makes the division decimal, and 0.125 * 100 "
                + "is 12.5.\n"
                + "\n"
                + "%.1f then rounds to one place for display, so 1 out of 3 "
                + "shows as 33.3 rather than 33.33333333333333.")
            .sample(Lab.typing("5", "40"),
                "Failed: 5",
                "Total: 40",
                "Failure rate: 12.5 percent")
            .hidden(Lab.typing("1", "3"),
                "Failed: 1",
                "Total: 3",
                "Failure rate: 33.3 percent")
            .hidden(Lab.typing("0", "10"),
                "Failed: 0",
                "Total: 10",
                "Failure rate: 0.0 percent")
            .hidden(Lab.typing("7", "7"),
                "Failed: 7",
                "Total: 7",
                "Failure rate: 100.0 percent"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(18), "Firewall Line Parser", Lab.MEDIUM)
            .after("C01-M029")
            .brief(
                "The firewall logs one line per decision: an action, a source "
                + "address and a port, separated by single spaces. Addresses "
                + "are different lengths on every line, so fixed positions "
                + "will not work. Take a line apart into its three fields, with "
                + "the port as a real number.")
            .practises("indexOf with a starting point", "substring between separators",
                       "Integer.parseInt on a field")
            .spec(
                "Prompt Log line: and read the line.",
                "Print Action:, Source: and Port: with the three fields, lined up as the sample shows.",
                "Print Next: with the port plus one, to prove the port is a number.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Log line: \");",
                "        String line = input.nextLine();",
                "        // find the two spaces, cut out the fields",
                "    }",
                "}")
            .hints(
                "Find the first space with indexOf(\" \"). Find the second by "
                + "searching again, starting just after the first:\n"
                + "\n"
                + "    int second = line.indexOf(\" \", first + 1);",
                "The action is before the first space. The source is between the "
                + "spaces. The port is after the second space.",
                "    String action = line.substring(0, first);\n"
                + "    String source = line.substring(first + 1, second);\n"
                + "    int port = Integer.parseInt(line.substring(second + 1));",
                "Print port + 1 inside brackets so it adds rather than joins.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Log line: \");",
                "        String line = input.nextLine();",
                "        int first = line.indexOf(\" \");",
                "        int second = line.indexOf(\" \", first + 1);",
                "        String action = line.substring(0, first);",
                "        String source = line.substring(first + 1, second);",
                "        int port = Integer.parseInt(line.substring(second + 1));",
                "        System.out.println(\"Action: \" + action);",
                "        System.out.println(\"Source: \" + source);",
                "        System.out.println(\"Port:   \" + port);",
                "        System.out.println(\"Next:   \" + (port + 1));",
                "    }",
                "}")
            .walkthrough(
                "Finding separators instead of counting positions is what makes "
                + "this work for every line. The second search starts at "
                + "first + 1 - from first itself it would find the same space "
                + "again.\n"
                + "\n"
                + "Each + 1 in the substrings steps over a space. And because the "
                + "port was converted with parseInt, port + 1 is arithmetic: the "
                + "hidden tests use 8080 and 53, and a port kept as text would "
                + "print 80801.\n"
                + "\n"
                + "Real parsers also have to survive lines with missing fields. "
                + "That needs checks, which Campaign 02 provides.")
            .sample(Lab.typing("BLOCK 10.0.4.17 443"),
                "Log line: BLOCK 10.0.4.17 443",
                "Action: BLOCK",
                "Source: 10.0.4.17",
                "Port:   443",
                "Next:   444")
            .hidden(Lab.typing("ALLOW 192.168.100.200 8080"),
                "Log line: ALLOW 192.168.100.200 8080",
                "Action: ALLOW",
                "Source: 192.168.100.200",
                "Port:   8080",
                "Next:   8081")
            .hidden(Lab.typing("DROP 8.8.8.8 53"),
                "Log line: DROP 8.8.8.8 53",
                "Action: DROP",
                "Source: 8.8.8.8",
                "Port:   53",
                "Next:   54"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(19), "Auth Line Parser", Lab.MEDIUM)
            .stretch()
            .after("C01-M029")
            .brief(
                "The authentication service logs a result, a username and the "
                + "number of attempts so far. Pull the three fields out and "
                + "work out what the count will be if the next attempt fails "
                + "too.")
            .practises("Parsing by separators", "Converting a field to a number")
            .spec(
                "Prompt Auth line: and read a line like FAIL m.reyes 7.",
                "Print Result:, User: and Attempts: with the three fields, lined up as the sample shows.",
                "Print If the next fails: with the attempts plus one.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Auth line: \");",
                "        String line = input.nextLine();",
                "        // take the line apart",
                "    }",
                "}")
            .hints(
                "Same shape as the firewall parser: two spaces, three fields.",
                "Search for the second space starting at first + 1.",
                "The attempts field needs Integer.parseInt before you can add "
                + "one to it.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Auth line: \");",
                "        String line = input.nextLine();",
                "        int first = line.indexOf(\" \");",
                "        int second = line.indexOf(\" \", first + 1);",
                "        String result = line.substring(0, first);",
                "        String user = line.substring(first + 1, second);",
                "        int attempts = Integer.parseInt(line.substring(second + 1));",
                "        System.out.println(\"Result:   \" + result);",
                "        System.out.println(\"User:     \" + user);",
                "        System.out.println(\"Attempts: \" + attempts);",
                "        System.out.println(\"If the next fails: \" + (attempts + 1));",
                "    }",
                "}")
            .walkthrough(
                "The same find-then-cut pattern as the firewall line, on a "
                + "different log. Once you have written a parser like this "
                + "twice, the shape stops being something to remember and "
                + "becomes something you just see: find each separator, cut "
                + "between them, convert what should be numeric.\n"
                + "\n"
                + "The hidden tests use a longer username and a two-digit count, "
                + "so any position typed in by hand fails.")
            .sample(Lab.typing("FAIL m.reyes 7"),
                "Auth line: FAIL m.reyes 7",
                "Result:   FAIL",
                "User:     m.reyes",
                "Attempts: 7",
                "If the next fails: 8")
            .hidden(Lab.typing("FAIL contractor 14"),
                "Auth line: FAIL contractor 14",
                "Result:   FAIL",
                "User:     contractor",
                "Attempts: 14",
                "If the next fails: 15")
            .hidden(Lab.typing("OK jsmith 1"),
                "Auth line: OK jsmith 1",
                "Result:   OK",
                "User:     jsmith",
                "Attempts: 1",
                "If the next fails: 2"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(20), "Username Normaliser", Lab.SMALL)
            .stretch()
            .after("C01-M021")
            .brief(
                "Usernames are stored in one standard form: no spaces at the "
                + "ends, all lower case. Read a name as someone typed it and "
                + "show what will actually be stored.")
            .practises("trim", "toLowerCase", "Chaining String methods")
            .spec(
                "Prompt with Username: and a space, and read the line.",
                "Print Stored as: and the name trimmed and lower-cased.",
                "Print Length: and the length of the stored form.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String typed = input.nextLine();",
                "        // normalise and report",
                "    }",
                "}")
            .hints(
                "trim() removes spaces from both ends; toLowerCase() settles the "
                + "case.",
                "Both hand back new Strings. You can chain them:\n"
                + "\n"
                + "    String stored = typed.trim().toLowerCase();",
                "The length must be of the stored form, not what was typed - the "
                + "spaces should not count.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String typed = input.nextLine();",
                "        String stored = typed.trim().toLowerCase();",
                "        System.out.println(\"Stored as: \" + stored);",
                "        System.out.println(\"Length: \" + stored.length());",
                "    }",
                "}")
            .walkthrough(
                "trim() first, then toLowerCase() on what trim handed back. The "
                + "order does not change the result here, but trimming first is "
                + "the habit: clean the edges, then settle the case.\n"
                + "\n"
                + "The length is asked of stored, so the hidden test with spaces "
                + "either side reports 6 for jsmith, not 9. Every later "
                + "comparison against this name will see those same 6 "
                + "characters - which is the point of storing one standard "
                + "form.")
            .sample(Lab.typing("  JSmith "),
                "Username:   JSmith",
                "Stored as: jsmith",
                "Length: 6")
            .hidden(Lab.typing("A.Okafor"),
                "Username: A.Okafor",
                "Stored as: a.okafor",
                "Length: 8")
            .hidden(Lab.typing("   CONTRACTOR"),
                "Username:    CONTRACTOR",
                "Stored as: contractor",
                "Length: 10"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(21), "Analyst Initials", Lab.SMALL)
            .stretch()
            .after("C01-M022")
            .brief(
                "New analysts get a badge code built from their name: the first "
                + "three letters of the surname in capitals, a dash, and the "
                + "first initial. People type names in any case, so the code "
                + "must not depend on that.")
            .practises("substring from the start", "toUpperCase", "Building text from pieces")
            .spec(
                "Prompt First name: and then Last name: , reading each and removing spaces at the ends.",
                "Print Initials: and the two initials in capitals.",
                "Print Badge: and the first three letters of the last name in capitals, a dash, and the first initial in capitals.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read both names, then build the initials and the badge",
                "    }",
                "}")
            .hints(
                "The first letter of a String is substring(0, 1) - a String of "
                + "one character, which can be upper-cased.",
                "The first three letters are substring(0, 3).",
                "Join the pieces first, then upper-case the result, or "
                + "upper-case each piece - either works.",
                "    String badge = last.substring(0, 3).toUpperCase() + \"-\"\n"
                + "            + first.substring(0, 1).toUpperCase();")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"First name: \");",
                "        String first = input.nextLine().trim();",
                "        System.out.print(\"Last name: \");",
                "        String last = input.nextLine().trim();",
                "        String initials = first.substring(0, 1)",
                "                + last.substring(0, 1);",
                "        String badge = last.substring(0, 3) + \"-\"",
                "                + first.substring(0, 1);",
                "        System.out.println(\"Initials: \" + initials.toUpperCase());",
                "        System.out.println(\"Badge: \" + badge.toUpperCase());",
                "    }",
                "}")
            .walkthrough(
                "substring(0, 1) is the first character as a String, and "
                + "substring(0, 3) the first three. Both end indexes are "
                + "excluded, so the lengths are 1 and 3.\n"
                + "\n"
                + "Upper-casing the finished text once is simpler than "
                + "upper-casing every piece, and the dash is unaffected - only "
                + "letters have a case. The hidden tests type names in lower "
                + "case and in capitals; the badge comes out the same either "
                + "way.")
            .sample(Lab.typing("adaeze", "okafor"), 
                "First name: adaeze",
                "Last name: okafor",
                "Initials: AO",
                "Badge: OKA-A")
            .hidden(Lab.typing("MARIA", "REYES"), 
                "First name: MARIA",
                "Last name: REYES",
                "Initials: MR",
                "Badge: REY-M")
            .hidden(Lab.typing(" John ", "Smith"), 
                "First name:  John",
                "Last name: Smith",
                "Initials: JS",
                "Badge: SMI-J"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(22), "First Octet", Lab.SMALL)
            .stretch()
            .after("C01-M025")
            .brief(
                "Network rules often start with the first number of an IPv4 "
                + "address: 10 is internal, 192 is usually a home or office "
                + "router. Read an address, pull out that first number as a "
                + "real int, and show the rest separately.")
            .practises("indexOf", "substring", "parseInt on part of a String")
            .spec(
                "Prompt with IP address: and read the address.",
                "Print First octet: and the number before the first dot.",
                "Print Rest: and everything after the first dot.",
                "Print Octet plus one: to prove the octet is a number.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"IP address: \");",
                "        String ip = input.nextLine().trim();",
                "        // split at the first dot",
                "    }",
                "}")
            .hints(
                "indexOf(\".\") finds the FIRST dot, which is the one you want.",
                "The octet is substring(0, dot); the rest is substring(dot + 1).",
                "Convert the octet with Integer.parseInt before adding one.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"IP address: \");",
                "        String ip = input.nextLine().trim();",
                "        int dot = ip.indexOf(\".\");",
                "        int octet = Integer.parseInt(ip.substring(0, dot));",
                "        String rest = ip.substring(dot + 1);",
                "        System.out.println(\"First octet: \" + octet);",
                "        System.out.println(\"Rest: \" + rest);",
                "        System.out.println(\"Octet plus one: \" + (octet + 1));",
                "    }",
                "}")
            .walkthrough(
                "The first dot splits the address into the first octet and "
                + "everything else. Its position changes with the octet's "
                + "length - 8, 10 and 192 are one, two and three digits - which "
                + "is why it is found, not counted.\n"
                + "\n"
                + "Converting the octet to an int is what lets a rule compare "
                + "it with a number later. As text, \"10\" and \"100\" would sort "
                + "and compare in all the wrong ways.")
            .sample(Lab.typing("10.0.4.17"), 
                "IP address: 10.0.4.17",
                "First octet: 10",
                "Rest: 0.4.17",
                "Octet plus one: 11")
            .hidden(Lab.typing("192.168.1.20"), 
                "IP address: 192.168.1.20",
                "First octet: 192",
                "Rest: 168.1.20",
                "Octet plus one: 193")
            .hidden(Lab.typing("8.8.8.8"), 
                "IP address: 8.8.8.8",
                "First octet: 8",
                "Rest: 8.8.8",
                "Octet plus one: 9"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(23), "Timestamp Cutter", Lab.MEDIUM)
            .stretch()
            .after("C01-M022")
            .brief(
                "Every event in the security log carries a timestamp in one "
                + "fixed format: 2024-03-11 09:14:02. To compare events you "
                + "need the pieces separately, and the time of day as a single "
                + "number: minutes since midnight.")
            .practises("Fixed-position substring", "parseInt with leading zeros",
                       "Arithmetic on parsed fields")
            .spec(
                "Prompt with Timestamp: and read a line in the form YYYY-MM-DD HH:MM:SS.",
                "Print Date: and Time: with the two halves.",
                "Print Hour: with the hour as a number, so 09 prints as 9.",
                "Print Minutes since midnight: with hours times 60 plus minutes.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Timestamp: \");",
                "        String stamp = input.nextLine().trim();",
                "        // cut it up",
                "    }",
                "}")
            .hints(
                "The format never changes, so positions are safe here. Write the "
                + "indexes out:\n"
                + "\n"
                + "    2024-03-11 09:14:02\n"
                + "    0123456789012345678",
                "The date is indexes 0 to 9: substring(0, 10). The time starts "
                + "at 11.",
                "The hour is substring(11, 13) and the minutes substring(14, 16). "
                + "Integer.parseInt(\"09\") is 9 - leading zeros are fine.",
                "    int minutes = hour * 60 + minute;")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Timestamp: \");",
                "        String stamp = input.nextLine().trim();",
                "        String date = stamp.substring(0, 10);",
                "        String time = stamp.substring(11);",
                "        int hour = Integer.parseInt(stamp.substring(11, 13));",
                "        int minute = Integer.parseInt(stamp.substring(14, 16));",
                "        System.out.println(\"Date: \" + date);",
                "        System.out.println(\"Time: \" + time);",
                "        System.out.println(\"Hour: \" + hour);",
                "        System.out.println(\"Minutes since midnight: \"",
                "                + (hour * 60 + minute));",
                "    }",
                "}")
            .walkthrough(
                "A fixed format is the one case where cutting by position is "
                + "right: the hour is always characters 11 and 12, whatever the "
                + "date. Writing the index row out, as the hint does, turns "
                + "guessing into reading.\n"
                + "\n"
                + "parseInt accepts leading zeros, so 09 becomes 9. Turning a "
                + "time into minutes since midnight gives a single number that "
                + "can be compared and subtracted - 554 is 09:14 - which is how "
                + "the next lab measures a session's length.")
            .sample(Lab.typing("2024-03-11 09:14:02"), 
                "Timestamp: 2024-03-11 09:14:02",
                "Date: 2024-03-11",
                "Time: 09:14:02",
                "Hour: 9",
                "Minutes since midnight: 554")
            .hidden(Lab.typing("2025-12-31 23:59:59"), 
                "Timestamp: 2025-12-31 23:59:59",
                "Date: 2025-12-31",
                "Time: 23:59:59",
                "Hour: 23",
                "Minutes since midnight: 1439")
            .hidden(Lab.typing("2024-01-01 00:00:07"), 
                "Timestamp: 2024-01-01 00:00:07",
                "Date: 2024-01-01",
                "Time: 00:00:07",
                "Hour: 0",
                "Minutes since midnight: 0"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(24), "Session Length", Lab.MEDIUM)
            .stretch()
            .after("C01-M022")
            .brief(
                "A VPN session that lasts all night is worth a look. Read the "
                + "login and logout times of one session, on the same day, and "
                + "report how long it lasted.")
            .practises("Parsing HH:MM", "Minutes since midnight", "/ and % together")
            .spec(
                "Prompt Login (HH:MM): and Logout (HH:MM): and read both times.",
                "Print Session: as hours and minutes, like 8h 35m.",
                "Print Minutes: with the total length in minutes.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read both times, then work out the length",
                "    }",
                "}")
            .hints(
                "Turn each time into minutes since midnight: hours * 60 + "
                + "minutes. The hours are substring(0, 2), the minutes "
                + "substring(3, 5).",
                "The length in minutes is logout minus login.",
                "Hours are length / 60, and the minutes left over are "
                + "length % 60.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Login (HH:MM): \");",
                "        String in = input.nextLine().trim();",
                "        System.out.print(\"Logout (HH:MM): \");",
                "        String out = input.nextLine().trim();",
                "        int start = Integer.parseInt(in.substring(0, 2)) * 60",
                "                + Integer.parseInt(in.substring(3, 5));",
                "        int end = Integer.parseInt(out.substring(0, 2)) * 60",
                "                + Integer.parseInt(out.substring(3, 5));",
                "        int length = end - start;",
                "        System.out.println(\"Session: \" + length / 60 + \"h \"",
                "                + length % 60 + \"m\");",
                "        System.out.println(\"Minutes: \" + length);",
                "    }",
                "}")
            .walkthrough(
                "Times cannot be subtracted as text, and not easily as hours "
                + "and minutes either - 17:20 minus 08:45 has to borrow an "
                + "hour. Converting both to minutes since midnight turns it "
                + "into a single subtraction, 1040 - 525, and / and % turn the "
                + "515 back into 8h 35m.\n"
                + "\n"
                + "That convert, calculate, convert back pattern is how almost "
                + "all time arithmetic is done in real code. The next campaign "
                + "handles sessions that cross midnight, which need a decision.")
            .sample(Lab.typing("08:45", "17:20"), 
                "Login (HH:MM): 08:45",
                "Logout (HH:MM): 17:20",
                "Session: 8h 35m",
                "Minutes: 515")
            .hidden(Lab.typing("09:00", "09:45"), 
                "Login (HH:MM): 09:00",
                "Logout (HH:MM): 09:45",
                "Session: 0h 45m",
                "Minutes: 45")
            .hidden(Lab.typing("00:00", "23:59"), 
                "Login (HH:MM): 00:00",
                "Logout (HH:MM): 23:59",
                "Session: 23h 59m",
                "Minutes: 1439"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(25), "Data Allowance", Lab.MEDIUM)
            .stretch()
            .after("C01-M022")
            .brief(
                "Each contractor laptop may upload a fixed number of gigabytes "
                + "a month. The firewall counts bytes. Work out how much of the "
                + "allowance is left - and use a type big enough, because these "
                + "numbers are far past what an int can hold.")
            .practises("long", "Long.parseLong", "The L suffix")
            .spec(
                "Prompt Allowance (GB): and read a whole number of gigabytes.",
                "Prompt Used (bytes): and read the bytes used so far.",
                "A gigabyte here is 1000000000 bytes.",
                "Print Remaining bytes: and Remaining GB: (whole gigabytes, rounded down).")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read both values and work out what is left",
                "    }",
                "}")
            .hints(
                "Bytes used can be hundreds of billions: read it with "
                + "Long.parseLong into a long.",
                "The allowance in bytes is allowance * 1000000000L. The L makes "
                + "the multiplication happen in long; without it, int arithmetic "
                + "overflows first.",
                "Whole gigabytes left is the remaining bytes / 1000000000L - "
                + "integer division rounds down, which is what you want.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Allowance (GB): \");",
                "        long allowance = Long.parseLong(input.nextLine().trim());",
                "        System.out.print(\"Used (bytes): \");",
                "        long used = Long.parseLong(input.nextLine().trim());",
                "        long remaining = allowance * 1000000000L - used;",
                "        System.out.println(\"Remaining bytes: \" + remaining);",
                "        System.out.println(\"Remaining GB: \"",
                "                + remaining / 1000000000L);",
                "    }",
                "}")
            .walkthrough(
                "500 GB is 500000000000 bytes - more than two hundred times what "
                + "an int can hold. With int, the multiplication would overflow "
                + "silently and the program would report a nonsense allowance "
                + "with total confidence.\n"
                + "\n"
                + "Reading with Long.parseLong and multiplying by 1000000000L "
                + "keeps every step in long. The hidden tests include a 2000 GB "
                + "allowance, where an int version goes badly wrong.")
            .sample(Lab.typing("500", "432109876543"), 
                "Allowance (GB): 500",
                "Used (bytes): 432109876543",
                "Remaining bytes: 67890123457",
                "Remaining GB: 67")
            .hidden(Lab.typing("1", "1"), 
                "Allowance (GB): 1",
                "Used (bytes): 1",
                "Remaining bytes: 999999999",
                "Remaining GB: 0")
            .hidden(Lab.typing("2000", "1999000000000"), 
                "Allowance (GB): 2000",
                "Used (bytes): 1999000000000",
                "Remaining bytes: 1000000000",
                "Remaining GB: 1"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(26), "Password Length Advisor", Lab.SMALL)
            .stretch()
            .after("C01-M023")
            .brief(
                "Policy says passwords must be at least 12 characters. Before "
                + "the full check exists, the sign-up form tells people how "
                + "many more characters they need - and must never tell them "
                + "they need a negative number.")
            .practises("length()", "Math.max as a floor", "final constants")
            .spec(
                "Declare a constant MIN_LENGTH set to 12.",
                "Prompt with Password: and read the line exactly as typed.",
                "Print Length: and its length.",
                "Print Still needed: and how many more characters are needed, never below 0.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // constant, prompt, read, report",
                "    }",
                "}")
            .hints(
                "MIN_LENGTH - length is how many are missing - but for a long "
                + "password that goes negative.",
                "A floor of zero is Math.max(0, ...): the larger of zero and "
                + "the shortfall.",
                "    int needed = Math.max(0,\n"
                + "            MIN_LENGTH - password.length());")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        final int MIN_LENGTH = 12;",
                "        System.out.print(\"Password: \");",
                "        String password = input.nextLine();",
                "        int needed = Math.max(0, MIN_LENGTH - password.length());",
                "        System.out.println(\"Length: \" + password.length());",
                "        System.out.println(\"Still needed: \" + needed);",
                "    }",
                "}")
            .walkthrough(
                "MIN_LENGTH - length gives the shortfall, which is negative "
                + "once the password is long enough. Math.max(0, shortfall) "
                + "keeps the larger value, so anything below zero becomes zero "
                + "- a floor, the mirror image of the ceiling from mission 23.\n"
                + "\n"
                + "The password is not trimmed on purpose: a space is a valid "
                + "password character. The hidden tests include exactly 12 "
                + "characters, 20 characters, and an empty line.")
            .sample(Lab.typing("cyberops1"), 
                "Password: cyberops1",
                "Length: 9",
                "Still needed: 3")
            .hidden(Lab.typing("correcthorse"), 
                "Password: correcthorse",
                "Length: 12",
                "Still needed: 0")
            .hidden(Lab.typing("battery staple horse"), 
                "Password: battery staple horse",
                "Length: 20",
                "Still needed: 0")
            .hidden(Lab.typing(""), 
                "Password:",
                "Length: 0",
                "Still needed: 12"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(27), "Unit Converter", Lab.MEDIUM)
            .stretch()
            .after("C01-M028")
            .brief(
                "Disk and transfer figures arrive in raw bytes. The report shows "
                + "them in kilobytes, megabytes and gigabytes, each to two "
                + "decimal places. Here a kilobyte is 1024 bytes, a megabyte "
                + "1024 kilobytes, and a gigabyte 1024 megabytes.")
            .practises("long", "Dividing by a double", "printf with %.2f")
            .spec(
                "Prompt with Bytes: and read a whole number, which may be very large.",
                "Print KB:, MB: and GB: each followed by a space and the value to 2 decimal places.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read the bytes, then print three conversions",
                "    }",
                "}")
            .hints(
                "Read with Long.parseLong - a few gigabytes is already past an "
                + "int.",
                "Divide by a double so the fraction survives: bytes / 1024.0.",
                "A megabyte is 1024 * 1024 bytes and a gigabyte 1024 * 1024 * "
                + "1024. Write them as 1024.0 * 1024 and so on.",
                "    System.out.printf(\"KB: %.2f%n\", bytes / 1024.0);")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Bytes: \");",
                "        long bytes = Long.parseLong(input.nextLine().trim());",
                "        System.out.printf(\"KB: %.2f%n\", bytes / 1024.0);",
                "        System.out.printf(\"MB: %.2f%n\", bytes / (1024.0 * 1024));",
                "        System.out.printf(\"GB: %.2f%n\",",
                "                bytes / (1024.0 * 1024 * 1024));",
                "    }",
                "}")
            .walkthrough(
                "Each division has a double on one side, so the fraction is kept "
                + "and %.2f rounds it for display. The brackets round "
                + "1024.0 * 1024 matter: without them, bytes / 1024.0 * 1024 "
                + "divides and then multiplies, and you get the bytes back.\n"
                + "\n"
                + "Writing 1024.0 at the start of the product makes the whole "
                + "product a double, so even the gigabyte divisor, "
                + "1073741824, is computed without any int overflow.")
            .sample(Lab.typing("1536000"), 
                "Bytes: 1536000",
                "KB: 1500.00",
                "MB: 1.46",
                "GB: 0.00")
            .hidden(Lab.typing("1073741824"), 
                "Bytes: 1073741824",
                "KB: 1048576.00",
                "MB: 1024.00",
                "GB: 1.00")
            .hidden(Lab.typing("0"), 
                "Bytes: 0",
                "KB: 0.00",
                "MB: 0.00",
                "GB: 0.00"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(28), "Risk Score", Lab.MEDIUM)
            .stretch()
            .after("C01-M028")
            .brief(
                "The team ranks findings with a simple risk score: severity "
                + "times exposure times asset value, scaled back to 0 to 10. "
                + "Read the three inputs, work out the score, and show it to "
                + "one decimal place and rounded to a whole number.")
            .practises("Mixed arithmetic", "Operator precedence", "printf and Math.round")
            .spec(
                "Read Severity (0-10): as a decimal, then Exposure (1-3): and Asset value (1-5): as whole numbers.",
                "The score is severity * exposure * asset / 15.",
                "Print Risk score: with the score to 1 decimal place and the words out of 10.",
                "Print Rounded: with the score rounded to the nearest whole number.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read three values, then work out and print the score",
                "    }",
                "}")
            .hints(
                "Severity is a double, so severity * exposure * asset is already "
                + "a double - the division by 15 keeps its fraction.",
                "* and / have the same precedence and run left to right, so no "
                + "brackets are needed for severity * exposure * asset / 15.",
                "    System.out.printf(\"Risk score: %.1f out of 10%n\", risk);\n"
                + "    System.out.println(\"Rounded: \" + Math.round(risk));")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Severity (0-10): \");",
                "        double severity = Double.parseDouble(input.nextLine().trim());",
                "        System.out.print(\"Exposure (1-3): \");",
                "        int exposure = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Asset value (1-5): \");",
                "        int asset = Integer.parseInt(input.nextLine().trim());",
                "        double risk = severity * exposure * asset / 15;",
                "        System.out.printf(\"Risk score: %.1f out of 10%n\", risk);",
                "        System.out.println(\"Rounded: \" + Math.round(risk));",
                "    }",
                "}")
            .walkthrough(
                "The largest possible product is 10 * 3 * 5 = 150, and dividing "
                + "by 15 scales it back to 10 - so the score sits on the same "
                + "scale as the severity everyone already understands.\n"
                + "\n"
                + "Because severity is a double, the whole product is a double "
                + "from the first multiplication on, and / 15 keeps the "
                + "fraction. If severity were an int, 4 * 1 * 2 / 15 would be "
                + "0 - a real risk scored as none.")
            .sample(Lab.typing("7.5", "3", "4"), 
                "Severity (0-10): 7.5",
                "Exposure (1-3): 3",
                "Asset value (1-5): 4",
                "Risk score: 6.0 out of 10",
                "Rounded: 6")
            .hidden(Lab.typing("9.8", "3", "5"), 
                "Severity (0-10): 9.8",
                "Exposure (1-3): 3",
                "Asset value (1-5): 5",
                "Risk score: 9.8 out of 10",
                "Rounded: 10")
            .hidden(Lab.typing("4", "1", "2"), 
                "Severity (0-10): 4",
                "Exposure (1-3): 1",
                "Asset value (1-5): 2",
                "Risk score: 0.5 out of 10",
                "Rounded: 1"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(29), "Alert Summary Card", Lab.BIG)
            .stretch()
            .after("C01-M029")
            .brief(
                "The alerting system sends one compact line per alert, fields "
                + "separated by | characters:\n"
                + "\n"
                + "    HIGH|WEB-01|jsmith|12\n"
                + "\n"
                + "severity, host, account, and failed attempts in the last 24 "
                + "hours. The on-call analyst wants a neat card instead. Parse "
                + "the line and draw the card, every row exactly the same "
                + "width.")
            .practises("Parsing three separators", "printf widths", "String.format",
                       "Planning a layout")
            .spec(
                "Prompt with Alert: and read one line in the form SEVERITY|HOST|ACCOUNT|ATTEMPTS.",
                "Draw the card exactly as the sample shows. Every line is 31 characters wide.",
                "Each field row is printed with the format | %-8s : %-17s| and a line break.",
                "Per hour is the attempts divided by 24, to one decimal place.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Alert: \");",
                "        String line = input.nextLine().trim();",
                "        // 1. find the three | characters",
                "        // 2. cut out the four fields",
                "        // 3. draw the card",
                "    }",
                "}")
            .hints(
                "Find the three separators one after another, each search "
                + "starting just after the last:\n"
                + "\n"
                + "    int a = line.indexOf(\"|\");\n"
                + "    int b = line.indexOf(\"|\", a + 1);\n"
                + "    int c = line.indexOf(\"|\", b + 1);",
                "The four fields are before a, between a and b, between b and c, "
                + "and after c.",
                "The border is a + then 29 dashes then a +. The title row is "
                + "printed with | %-28s| so it pads to the same width.",
                "The value column is a String, so turn the per-hour figure into "
                + "one first:\n"
                + "\n"
                + "    String perHour = String.format(\"%.1f\", attempts / 24.0);",
                "Keep the row format in one constant so every row is identical:\n"
                + "\n"
                + "    final String ROW = \"| %-8s : %-17s|%n\";\n"
                + "    System.out.printf(ROW, \"Severity\", severity);")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Alert: \");",
                "        String line = input.nextLine().trim();",
                "        int a = line.indexOf(\"|\");",
                "        int b = line.indexOf(\"|\", a + 1);",
                "        int c = line.indexOf(\"|\", b + 1);",
                "        String severity = line.substring(0, a);",
                "        String host = line.substring(a + 1, b);",
                "        String account = line.substring(b + 1, c);",
                "        int attempts = Integer.parseInt(line.substring(c + 1));",
                "        String perHour = String.format(\"%.1f\", attempts / 24.0);",
                "",
                "        final String BORDER = \"+-----------------------------+\";",
                "        final String ROW = \"| %-8s : %-17s|%n\";",
                "        System.out.println(BORDER);",
                "        System.out.printf(\"| %-28s|%n\", \"ALERT CARD\");",
                "        System.out.println(BORDER);",
                "        System.out.printf(ROW, \"Severity\", severity);",
                "        System.out.printf(ROW, \"Host\", host);",
                "        System.out.printf(ROW, \"Account\", account);",
                "        System.out.printf(ROW, \"Attempts\", attempts);",
                "        System.out.printf(ROW, \"Per hour\", perHour);",
                "        System.out.println(BORDER);",
                "    }",
                "}")
            .walkthrough(
                "Two separate jobs, done one after the other.\n"
                + "\n"
                + "PARSING. Three separators found in turn, each search starting "
                + "one past the last, then four substrings between them. The "
                + "attempts field is converted, because it feeds a calculation.\n"
                + "\n"
                + "LAYOUT. Every row goes through the same format, | %-8s : "
                + "%-17s|, so every row is the same width however long the "
                + "values are: 2 + 8 + 3 + 17 + 1 = 31 characters. The border "
                + "and title are built to match. Keeping the format in one "
                + "constant means a width change happens in one place, not "
                + "five.\n"
                + "\n"
                + "%-17s accepts the int attempts too: %s turns any value into "
                + "text. A value longer than 17 characters would push the right "
                + "edge out - the hidden tests stay inside the width, but a real "
                + "tool would need to cut long values, which takes an if.")
            .sample(Lab.typing("HIGH|WEB-01|jsmith|12"), 
                "Alert: HIGH|WEB-01|jsmith|12",
                "+-----------------------------+",
                "| ALERT CARD                  |",
                "+-----------------------------+",
                "| Severity : HIGH             |",
                "| Host     : WEB-01           |",
                "| Account  : jsmith           |",
                "| Attempts : 12               |",
                "| Per hour : 0.5              |",
                "+-----------------------------+")
            .hidden(Lab.typing("CRITICAL|DC-01|svc-backup|240"), 
                "Alert: CRITICAL|DC-01|svc-backup|240",
                "+-----------------------------+",
                "| ALERT CARD                  |",
                "+-----------------------------+",
                "| Severity : CRITICAL         |",
                "| Host     : DC-01            |",
                "| Account  : svc-backup       |",
                "| Attempts : 240              |",
                "| Per hour : 10.0             |",
                "+-----------------------------+")
            .hidden(Lab.typing("LOW|FILE-01|m.reyes|3"), 
                "Alert: LOW|FILE-01|m.reyes|3",
                "+-----------------------------+",
                "| ALERT CARD                  |",
                "+-----------------------------+",
                "| Severity : LOW              |",
                "| Host     : FILE-01          |",
                "| Account  : m.reyes          |",
                "| Attempts : 3                |",
                "| Per hour : 0.1              |",
                "+-----------------------------+"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(30), "Incident Ticket Formatter", Lab.CAPSTONE)
            .after("C01-M030")
            .brief(
                "The capstone for JAVA ZERO. When an analyst contains an "
                + "incident, they fill in seven answers and the tool produces a "
                + "standard ticket for the record. People type carelessly - "
                + "stray spaces, any capitals - so the tool cleans every "
                + "answer, works out the figures itself, and lays the ticket "
                + "out exactly.\n"
                + "\n"
                + "It uses almost everything in the campaign: input, trim and "
                + "case, replace, substring, parseInt, arithmetic, Math.round, "
                + "printf and escaped quotes.")
            .practises("Everything in Campaign 01", "Cleaning input", "Planning a larger program")
            .spec(
                "Ask, in this order: Reporter:, Host:, Account:, Category:, Detected (HH:MM):, Contained (HH:MM):, Severity (0-10): - each followed by a space.",
                "Trim every answer. The host and category go to upper case; the account goes to lower case.",
                "The ticket ID is INC-, the host with its dashes removed, a dash, and the detected time without its colon: INC-WEB01-0914.",
                "Open for is the minutes from detected to contained, on the same day.",
                "Severity shows the score to 1 decimal place and, in brackets, rounded to a whole number.",
                "The summary line puts the account in double quotes.",
                "The = and - lines are 34 characters. Match every label and space in the sample exactly.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "",
                "        // 1. ask the seven questions, cleaning each answer",
                "",
                "        // 2. work out the ticket ID and the minutes open",
                "",
                "        // 3. print the ticket",
                "    }",
                "}")
            .hints(
                "Do it in the three stages the starter lists, and test after "
                + "each one. Print the cleaned values on their own first, to "
                + "check stage 1 before building the ticket.",
                "Each answer is read, trimmed and converted in one line, for "
                + "example:\n"
                + "\n"
                + "    System.out.print(\"Host: \");\n"
                + "    String host = input.nextLine().trim().toUpperCase();",
                "The ticket ID uses replace to drop characters:\n"
                + "\n"
                + "    String id = \"INC-\" + host.replace(\"-\", \"\") + \"-\"\n"
                + "            + detected.replace(\":\", \"\");",
                "Minutes open: turn each HH:MM into minutes since midnight, as in "
                + "the Session Length lab, and subtract.",
                "For the severity line, printf with %.1f for the score and %d "
                + "for Math.round(severity) - Math.round gives a long, which %d "
                + "accepts:\n"
                + "\n"
                + "    System.out.printf(\" Severity  : %.1f (rounded %d)%n\",\n"
                + "            severity, Math.round(severity));",
                "The summary needs escaped quotes around the account:\n"
                + "\n"
                + "    System.out.println(\" \" + category + \" on \" + host\n"
                + "            + \" against \\\"\" + account + \"\\\"\");")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "",
                "        System.out.print(\"Reporter: \");",
                "        String reporter = input.nextLine().trim();",
                "        System.out.print(\"Host: \");",
                "        String host = input.nextLine().trim().toUpperCase();",
                "        System.out.print(\"Account: \");",
                "        String account = input.nextLine().trim().toLowerCase();",
                "        System.out.print(\"Category: \");",
                "        String category = input.nextLine().trim().toUpperCase();",
                "        System.out.print(\"Detected (HH:MM): \");",
                "        String detected = input.nextLine().trim();",
                "        System.out.print(\"Contained (HH:MM): \");",
                "        String contained = input.nextLine().trim();",
                "        System.out.print(\"Severity (0-10): \");",
                "        double severity = Double.parseDouble(input.nextLine().trim());",
                "",
                "        String id = \"INC-\" + host.replace(\"-\", \"\") + \"-\"",
                "                + detected.replace(\":\", \"\");",
                "        int start = Integer.parseInt(detected.substring(0, 2)) * 60",
                "                + Integer.parseInt(detected.substring(3, 5));",
                "        int end = Integer.parseInt(contained.substring(0, 2)) * 60",
                "                + Integer.parseInt(contained.substring(3, 5));",
                "        int minutesOpen = end - start;",
                "",
                "        final String DOUBLE = \"==================================\";",
                "        final String SINGLE = \"----------------------------------\";",
                "        System.out.println(DOUBLE);",
                "        System.out.println(\" INCIDENT \" + id);",
                "        System.out.println(DOUBLE);",
                "        System.out.println(\" Reporter  : \" + reporter);",
                "        System.out.println(\" Host      : \" + host);",
                "        System.out.println(\" Account   : \" + account);",
                "        System.out.println(\" Category  : \" + category);",
                "        System.out.println(\" Detected  : \" + detected);",
                "        System.out.println(\" Contained : \" + contained);",
                "        System.out.println(\" Open for  : \" + minutesOpen + \" min\");",
                "        System.out.printf(\" Severity  : %.1f (rounded %d)%n\",",
                "                severity, Math.round(severity));",
                "        System.out.println(SINGLE);",
                "        System.out.println(\" \" + category + \" on \" + host",
                "                + \" against \\\"\" + account + \"\\\"\");",
                "        System.out.println(DOUBLE);",
                "    }",
                "}")
            .walkthrough(
                "A program this size is only manageable in stages, and the "
                + "solution keeps them visibly apart.\n"
                + "\n"
                + "STAGE 1 - INPUT. Every answer is cleaned the moment it arrives: "
                + "trimmed, and put in the case the ticket uses. Nothing later "
                + "has to worry about stray spaces or capitals, because none can "
                + "get past this point. That is the habit from missions 17 and "
                + "18, applied to a whole form.\n"
                + "\n"
                + "STAGE 2 - FIGURES. The ID is built with replace, removing the "
                + "dash from the host and the colon from the time. The minutes "
                + "open use the minutes-since-midnight conversion, so 09:14 to "
                + "10:02 is 602 - 554 = 48.\n"
                + "\n"
                + "STAGE 3 - OUTPUT. Fixed labels padded with spaces, printf for "
                + "the severity line so the score has one decimal place and the "
                + "rounded value sits beside it, and \\\" to quote the account in "
                + "the summary.\n"
                + "\n"
                + "The hidden tests type the host with spaces, the account in "
                + "capitals and the severity as a whole number - the cleaning "
                + "in stage 1 is what makes every one of them come out tidy. "
                + "What this program still cannot do is refuse bad answers: a "
                + "time with no colon, or contained before detected. That is "
                + "what the next campaign is for.")
            .sample(Lab.typing("a.okafor", "web-01", "Contractor", "brute force",
                               "09:14", "10:02", "7.5"), 
                "Reporter: a.okafor",
                "Host: web-01",
                "Account: Contractor",
                "Category: brute force",
                "Detected (HH:MM): 09:14",
                "Contained (HH:MM): 10:02",
                "Severity (0-10): 7.5",
                "==================================",
                " INCIDENT INC-WEB01-0914",
                "==================================",
                " Reporter  : a.okafor",
                " Host      : WEB-01",
                " Account   : contractor",
                " Category  : BRUTE FORCE",
                " Detected  : 09:14",
                " Contained : 10:02",
                " Open for  : 48 min",
                " Severity  : 7.5 (rounded 8)",
                "----------------------------------",
                " BRUTE FORCE on WEB-01 against \"contractor\"",
                "==================================")
            .hidden(Lab.typing("m.reyes", "  db-01 ", "ADMIN", "Data Exfiltration",
                               "23:05", "23:59", "9.8"), 
                "Reporter: m.reyes",
                "Host:   db-01",
                "Account: ADMIN",
                "Category: Data Exfiltration",
                "Detected (HH:MM): 23:05",
                "Contained (HH:MM): 23:59",
                "Severity (0-10): 9.8",
                "==================================",
                " INCIDENT INC-DB01-2305",
                "==================================",
                " Reporter  : m.reyes",
                " Host      : DB-01",
                " Account   : admin",
                " Category  : DATA EXFILTRATION",
                " Detected  : 23:05",
                " Contained : 23:59",
                " Open for  : 54 min",
                " Severity  : 9.8 (rounded 10)",
                "----------------------------------",
                " DATA EXFILTRATION on DB-01 against \"admin\"",
                "==================================")
            .hidden(Lab.typing("jsmith", "file-01", "svc-backup", "malware",
                               "00:00", "00:07", "4"), 
                "Reporter: jsmith",
                "Host: file-01",
                "Account: svc-backup",
                "Category: malware",
                "Detected (HH:MM): 00:00",
                "Contained (HH:MM): 00:07",
                "Severity (0-10): 4",
                "==================================",
                " INCIDENT INC-FILE01-0000",
                "==================================",
                " Reporter  : jsmith",
                " Host      : FILE-01",
                " Account   : svc-backup",
                " Category  : MALWARE",
                " Detected  : 00:00",
                " Contained : 00:07",
                " Open for  : 7 min",
                " Severity  : 4.0 (rounded 4)",
                "----------------------------------",
                " MALWARE on FILE-01 against \"svc-backup\"",
                "=================================="));
    }
}
