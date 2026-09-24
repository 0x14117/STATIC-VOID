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
    }
}
