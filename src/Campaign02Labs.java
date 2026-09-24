/**
 * LABS - CAMPAIGN 02 - CONDITIONAL
 *
 * Programs that decide. Every lab here reads input and has hidden tests,
 * and the hidden tests aim at the boundaries, because that is where
 * decisions go wrong. Loops are Campaign 04, so each program makes its
 * decision once.
 */
public class Campaign02Labs {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(1), "Over the Threshold", Lab.SMALL)
            .after("C02-M002")
            .brief(
                "The first decision the audit tool ever makes: read an account's "
                + "failed-login count and raise an alert only if it is over five. "
                + "Whatever the count, it confirms the check ran.")
            .practises("if", "Comparison operators", "Reading a number")
            .spec(
                "Prompt Failed logins: and read a whole number.",
                "If the count is more than 5, print ALERT: <n> failed logins.",
                "Whatever the count, finish with Check complete.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Failed logins: \");",
                "        int failures = Integer.parseInt(input.nextLine().trim());",
                "        // the alert, then the closing line",
                "    }",
                "}")
            .hints(
                "'More than 5' is > 5, not >= 5.",
                "The alert line goes inside an if block. Check complete goes "
                + "after the block, so it always prints.",
                "    if (failures > 5) {\n"
                + "        System.out.println(\"ALERT: \" + failures\n"
                + "                + \" failed logins\");\n"
                + "    }")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Failed logins: \");",
                "        int failures = Integer.parseInt(input.nextLine().trim());",
                "        if (failures > 5) {",
                "            System.out.println(\"ALERT: \" + failures",
                "                    + \" failed logins\");",
                "        }",
                "        System.out.println(\"Check complete\");",
                "    }",
                "}")
            .walkthrough(
                "The if controls only the alert. The last println sits after the "
                + "closing brace, so it runs whatever the count.\n"
                + "\n"
                + "The hidden tests include exactly 5, the boundary: 'more than "
                + "5' means 5 itself does not alert. A >= would fire one failure "
                + "early - and a stray semicolon after the condition would fire "
                + "on every count, including 0.")
            .sample(Lab.typing("7"), 
                "Failed logins: 7",
                "ALERT: 7 failed logins",
                "Check complete")
            .hidden(Lab.typing("5"), 
                "Failed logins: 5",
                "Check complete")
            .hidden(Lab.typing("6"), 
                "Failed logins: 6",
                "ALERT: 6 failed logins",
                "Check complete")
            .hidden(Lab.typing("0"), 
                "Failed logins: 0",
                "Check complete"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(2), "Lock or Warn", Lab.SMALL)
            .after("C02-M006")
            .brief(
                "The lockout policy has three outcomes. Five or more failures "
                + "locks the account; three or four earns a warning; anything "
                + "less is fine. Read the count and give exactly one verdict.")
            .practises("if / else if / else", "Ordering thresholds", ">= boundaries")
            .spec(
                "Prompt Failed logins: and read a whole number.",
                "5 or more: print LOCK.",
                "3 or 4: print WARN.",
                "Anything else: print OK.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Failed logins: \");",
                "        int failures = Integer.parseInt(input.nextLine().trim());",
                "        // one verdict",
                "    }",
                "}")
            .hints(
                "Three outcomes, exactly one printed: an if / else if / else "
                + "chain.",
                "Test the HIGHEST threshold first. If you test >= 3 first, 7 "
                + "stops there and never reaches LOCK.",
                "    if (failures >= 5) {\n"
                + "        ...\n"
                + "    } else if (failures >= 3) {\n"
                + "        ...\n"
                + "    } else {\n"
                + "        ...\n"
                + "    }")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Failed logins: \");",
                "        int failures = Integer.parseInt(input.nextLine().trim());",
                "        if (failures >= 5) {",
                "            System.out.println(\"LOCK\");",
                "        } else if (failures >= 3) {",
                "            System.out.println(\"WARN\");",
                "        } else {",
                "            System.out.println(\"OK\");",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The chain is checked top to bottom and stops at the first true "
                + "test. Putting >= 5 first means the WARN test only ever sees "
                + "counts below 5, so it does not need to say '3 or 4' in full - "
                + ">= 3 is enough.\n"
                + "\n"
                + "The hidden tests sit on every edge: 2, 3, 4 and 5. Those four "
                + "values are the only ones that can tell a right chain from a "
                + "wrong one.")
            .sample(Lab.typing("4"), 
                "Failed logins: 4",
                "WARN")
            .hidden(Lab.typing("5"), 
                "Failed logins: 5",
                "LOCK")
            .hidden(Lab.typing("3"), 
                "Failed logins: 3",
                "WARN")
            .hidden(Lab.typing("2"), 
                "Failed logins: 2",
                "OK")
            .hidden(Lab.typing("40"), 
                "Failed logins: 40",
                "LOCK"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(3), "Password Length Gate", Lab.SMALL)
            .after("C02-M005")
            .brief(
                "The sign-up form enforces the policy: at least 12 characters. A "
                + "refusal should tell the person exactly how many more they "
                + "need, rather than just saying no.")
            .practises("if / else", "Boundary values", "length()")
            .spec(
                "Prompt Password: and read the line exactly as typed.",
                "12 or more characters: print ACCEPTED.",
                "Fewer: print TOO SHORT - <n> more needed, where n is how many are missing.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        final int MIN_LENGTH = 12;",
                "        System.out.print(\"Password: \");",
                "        String password = input.nextLine();",
                "        // accept or refuse",
                "    }",
                "}")
            .hints(
                "'At least 12' includes 12: >=.",
                "The number missing is MIN_LENGTH - password.length(). It only "
                + "makes sense in the refusal branch, where it is always "
                + "positive.",
                "    if (password.length() >= MIN_LENGTH) {")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        final int MIN_LENGTH = 12;",
                "        System.out.print(\"Password: \");",
                "        String password = input.nextLine();",
                "        if (password.length() >= MIN_LENGTH) {",
                "            System.out.println(\"ACCEPTED\");",
                "        } else {",
                "            int missing = MIN_LENGTH - password.length();",
                "            System.out.println(\"TOO SHORT - \" + missing",
                "                    + \" more needed\");",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The comparison uses >= because the policy says 'at least'. The "
                + "hidden tests use exactly 11 and exactly 12 characters - the "
                + "pair that exposes > in place of >=.\n"
                + "\n"
                + "missing is declared inside the else, because that is the only "
                + "place it means anything. In Campaign 01 this needed Math.max "
                + "to stop it going negative; here the if guarantees it never "
                + "can.")
            .sample(Lab.typing("shortpass"), 
                "Password: shortpass",
                "TOO SHORT - 3 more needed")
            .hidden(Lab.typing("elevenchars"), 
                "Password: elevenchars",
                "TOO SHORT - 1 more needed")
            .hidden(Lab.typing("twelve chars"), 
                "Password: twelve chars",
                "ACCEPTED")
            .hidden(Lab.typing(""), 
                "Password:",
                "TOO SHORT - 12 more needed"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(4), "Severity Grader", Lab.SMALL)
            .after("C02-M008")
            .brief(
                "Turn a vulnerability score into its CVSS band. Scores run from "
                + "0.0 to 10.0; anything outside that range is not a score at "
                + "all and must be refused rather than graded.")
            .practises("else if chains", "Highest threshold first", "Rejecting bad input with ||")
            .spec(
                "Prompt Score: and read a decimal number.",
                "Below 0 or above 10: print INVALID SCORE.",
                "Otherwise: 9.0+ CRITICAL, 7.0+ HIGH, 4.0+ MEDIUM, above 0 LOW, exactly 0 NONE.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Score: \");",
                "        double score = Double.parseDouble(input.nextLine().trim());",
                "        // grade it",
                "    }",
                "}")
            .hints(
                "Refuse bad input first: score < 0 || score > 10.",
                "Then the bands, highest first, each as an else if.",
                "After 4.0, the only scores left are 0.0 up to 3.9. 'score > 0' "
                + "separates LOW from NONE.",
                "    if (score < 0 || score > 10) {\n"
                + "        System.out.println(\"INVALID SCORE\");\n"
                + "    } else if (score >= 9.0) {")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Score: \");",
                "        double score = Double.parseDouble(input.nextLine().trim());",
                "        if (score < 0 || score > 10) {",
                "            System.out.println(\"INVALID SCORE\");",
                "        } else if (score >= 9.0) {",
                "            System.out.println(\"CRITICAL\");",
                "        } else if (score >= 7.0) {",
                "            System.out.println(\"HIGH\");",
                "        } else if (score >= 4.0) {",
                "            System.out.println(\"MEDIUM\");",
                "        } else if (score > 0) {",
                "            System.out.println(\"LOW\");",
                "        } else {",
                "            System.out.println(\"NONE\");",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "Validation comes first, so the grading chain only ever sees "
                + "scores it knows how to grade. Outside a range is an ||: below "
                + "the bottom OR above the top.\n"
                + "\n"
                + "The bands run highest first, so each test can rely on the "
                + "ones above having failed. The hidden tests hit 9.0, 6.9, 0.0, "
                + "10.0 and -1 - the edges of the bands and of the range.")
            .sample(Lab.typing("7.5"), 
                "Score: 7.5",
                "HIGH")
            .hidden(Lab.typing("9.0"), 
                "Score: 9.0",
                "CRITICAL")
            .hidden(Lab.typing("6.9"), 
                "Score: 6.9",
                "MEDIUM")
            .hidden(Lab.typing("0.0"), 
                "Score: 0.0",
                "NONE")
            .hidden(Lab.typing("10"), 
                "Score: 10",
                "CRITICAL")
            .hidden(Lab.typing("-1"), 
                "Score: -1",
                "INVALID SCORE"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(5), "Port Validator", Lab.SMALL)
            .after("C02-M007")
            .brief(
                "Port numbers fall into three official ranges, and firewall "
                + "reviewers want to know which one a port is in. Read a port, "
                + "refuse anything impossible, and name its range.")
            .practises("Range checks with &&", "Outside a range with ||", "else if")
            .spec(
                "Prompt Port: and read a whole number.",
                "Below 1 or above 65535: INVALID PORT.",
                "1 to 1023: WELL-KNOWN. 1024 to 49151: REGISTERED. 49152 to 65535: DYNAMIC.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Port: \");",
                "        int port = Integer.parseInt(input.nextLine().trim());",
                "        // classify it",
                "    }",
                "}")
            .hints(
                "First refuse the impossible: port < 1 || port > 65535.",
                "Once the port is known to be valid, each range only needs its "
                + "upper edge: port <= 1023, then port <= 49151.",
                "Whatever is left after those is DYNAMIC - a plain else.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Port: \");",
                "        int port = Integer.parseInt(input.nextLine().trim());",
                "        if (port < 1 || port > 65535) {",
                "            System.out.println(\"INVALID PORT\");",
                "        } else if (port <= 1023) {",
                "            System.out.println(\"WELL-KNOWN\");",
                "        } else if (port <= 49151) {",
                "            System.out.println(\"REGISTERED\");",
                "        } else {",
                "            System.out.println(\"DYNAMIC\");",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "Rejecting the invalid values first turns the rest into a simple "
                + "ladder. Each else if only needs one comparison, because the "
                + "ones before it have already ruled out everything smaller.\n"
                + "\n"
                + "The hidden tests are all edges: 0, 1, 1023, 1024, 49152, 65535 "
                + "and 65536. A single < for <= anywhere moves one port into the "
                + "wrong range, and exactly one of these tests catches it.")
            .sample(Lab.typing("443"), 
                "Port: 443",
                "WELL-KNOWN")
            .hidden(Lab.typing("0"), 
                "Port: 0",
                "INVALID PORT")
            .hidden(Lab.typing("1023"), 
                "Port: 1023",
                "WELL-KNOWN")
            .hidden(Lab.typing("1024"), 
                "Port: 1024",
                "REGISTERED")
            .hidden(Lab.typing("49152"), 
                "Port: 49152",
                "DYNAMIC")
            .hidden(Lab.typing("65535"), 
                "Port: 65535",
                "DYNAMIC")
            .hidden(Lab.typing("65536"), 
                "Port: 65536",
                "INVALID PORT"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(6), "Office Hours", Lab.SMALL)
            .stretch()
            .after("C02-M008")
            .brief(
                "Logins outside office hours get a second look. Office hours run "
                + "from 08:00 up to, but not including, 18:00. Read the hour of "
                + "a login and say whether it needs reviewing.")
            .practises("Inside and outside a range", "&& and ||", "Validating first")
            .spec(
                "Prompt Hour (0-23): and read a whole number.",
                "Below 0 or above 23: INVALID HOUR.",
                "8 up to but not including 18: OFFICE HOURS.",
                "Any other hour: OUT OF HOURS - review.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Hour (0-23): \");",
                "        int hour = Integer.parseInt(input.nextLine().trim());",
                "        // validate, then decide",
                "    }",
                "}")
            .hints(
                "Invalid hours first: hour < 0 || hour > 23.",
                "Inside office hours is hour >= 8 && hour < 18 - note < 18, "
                + "because 18:00 is already after hours.",
                "Everything left over is out of hours: an else.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Hour (0-23): \");",
                "        int hour = Integer.parseInt(input.nextLine().trim());",
                "        if (hour < 0 || hour > 23) {",
                "            System.out.println(\"INVALID HOUR\");",
                "        } else if (hour >= 8 && hour < 18) {",
                "            System.out.println(\"OFFICE HOURS\");",
                "        } else {",
                "            System.out.println(\"OUT OF HOURS - review\");",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "Two ranges, two shapes: the invalid check is OUTSIDE 0 to 23, "
                + "so it is an ||; office hours is INSIDE 8 to 17, so it is an "
                + "&&. Mixing them up gives a rule that never fires or always "
                + "does.\n"
                + "\n"
                + "'Up to but not including 18' is < 18. The hidden tests try 7, "
                + "8, 17, 18 and 24 - both edges of the window and one past the "
                + "valid range.")
            .sample(Lab.typing("22"), 
                "Hour (0-23): 22",
                "OUT OF HOURS - review")
            .hidden(Lab.typing("8"), 
                "Hour (0-23): 8",
                "OFFICE HOURS")
            .hidden(Lab.typing("17"), 
                "Hour (0-23): 17",
                "OFFICE HOURS")
            .hidden(Lab.typing("18"), 
                "Hour (0-23): 18",
                "OUT OF HOURS - review")
            .hidden(Lab.typing("7"), 
                "Hour (0-23): 7",
                "OUT OF HOURS - review")
            .hidden(Lab.typing("24"), 
                "Hour (0-23): 24",
                "INVALID HOUR"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(7), "Safe First Character", Lab.SMALL)
            .stretch()
            .after("C02-M010")
            .brief(
                "The config loader reads one line at a time. A line starting "
                + "with # is a comment. An empty line is just spacing. Anything "
                + "else is a setting. Blank lines must never crash it.")
            .practises("Short-circuit guards", "charAt safely", "isEmpty")
            .spec(
                "Prompt Line: and read the line exactly as typed.",
                "An empty line: print BLANK.",
                "A line whose first character is #: print COMMENT.",
                "Anything else: print SETTING: followed by the line.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Line: \");",
                "        String line = input.nextLine();",
                "        // classify the line without ever crashing",
                "    }",
                "}")
            .hints(
                "charAt(0) crashes on an empty line. Deal with empty first.",
                "Once the chain has ruled out empty, charAt(0) is safe in the "
                + "next condition.",
                "    if (line.isEmpty()) {\n"
                + "        ...\n"
                + "    } else if (line.charAt(0) == '#') {")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Line: \");",
                "        String line = input.nextLine();",
                "        if (line.isEmpty()) {",
                "            System.out.println(\"BLANK\");",
                "        } else if (line.charAt(0) == '#') {",
                "            System.out.println(\"COMMENT\");",
                "        } else {",
                "            System.out.println(\"SETTING: \" + line);",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The else-if chain is itself a guard: charAt(0) is only reached "
                + "when isEmpty was false, so there is always a character at 0. "
                + "That is the same protection as length() > 0 && charAt(0), "
                + "spread across two branches.\n"
                + "\n"
                + "'#' is in single quotes because charAt gives a char, and "
                + "chars are compared with ==. The hidden tests include an empty "
                + "line and a # that is not the first character.")
            .sample(Lab.typing("# timeout settings"), 
                "Line: # timeout settings",
                "COMMENT")
            .hidden(Lab.typing(""), 
                "Line:",
                "BLANK")
            .hidden(Lab.typing("timeout=30"), 
                "Line: timeout=30",
                "SETTING: timeout=30")
            .hidden(Lab.typing("colour=#00ff00"), 
                "Line: colour=#00ff00",
                "SETTING: colour=#00ff00"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(8), "Username Match", Lab.SMALL)
            .after("C02-M011")
            .brief(
                "The console knows one analyst, a.okafor. Whatever someone "
                + "types - capitals, stray spaces - must be cleaned and then "
                + "compared properly. This is the lab where == stops being an "
                + "option for text.")
            .practises(".equals()", "Normalising before comparing", "if / else")
            .spec(
                "Prompt Username: and read the line.",
                "Trim it and convert it to lower case.",
                "If it is a.okafor, print WELCOME a.okafor. Otherwise print UNKNOWN USER.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        final String KNOWN = \"a.okafor\";",
                "        System.out.print(\"Username: \");",
                "        String typed = input.nextLine();",
                "        // clean it, then compare",
                "    }",
                "}")
            .hints(
                "Clean first: typed.trim().toLowerCase().",
                "The cleaned text is built at run time, so == would compare "
                + "locations and fail. Use equals.",
                "    String user = typed.trim().toLowerCase();\n"
                + "    if (user.equals(KNOWN)) {")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        final String KNOWN = \"a.okafor\";",
                "        System.out.print(\"Username: \");",
                "        String typed = input.nextLine();",
                "        String user = typed.trim().toLowerCase();",
                "        if (user.equals(KNOWN)) {",
                "            System.out.println(\"WELCOME \" + user);",
                "        } else {",
                "            System.out.println(\"UNKNOWN USER\");",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "Every typed value is a new String, so this is exactly the case "
                + "where == fails and equals works. Cleaning it first means "
                + "'  A.Okafor ' and 'a.okafor' become the same text before "
                + "anything compares them.\n"
                + "\n"
                + "Try it with == and the sample test fails even for a perfect "
                + "match - the most direct way there is to see why the rule has "
                + "no exceptions.")
            .sample(Lab.typing("a.okafor"), 
                "Username: a.okafor",
                "WELCOME a.okafor")
            .hidden(Lab.typing("  A.Okafor "), 
                "Username:   A.Okafor",
                "WELCOME a.okafor")
            .hidden(Lab.typing("a.okafor2"), 
                "Username: a.okafor2",
                "UNKNOWN USER")
            .hidden(Lab.typing("okafor"), 
                "Username: okafor",
                "UNKNOWN USER"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(9), "Case-Blind Blocklist", Lab.SMALL)
            .stretch()
            .after("C02-M012")
            .brief(
                "Three usernames are reserved and can never be registered: "
                + "admin, root and administrator - in any capitals, because "
                + "'Admin' in a log looks exactly like the real thing to a "
                + "tired analyst.")
            .practises("equalsIgnoreCase", "Combining checks with ||", "trim")
            .spec(
                "Prompt Requested name: and read the line, then trim it.",
                "If it is admin, root or administrator, ignoring case, print BLOCKED: reserved name.",
                "Otherwise print AVAILABLE: followed by the trimmed name.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Requested name: \");",
                "        String name = input.nextLine().trim();",
                "        // check it against the three reserved names",
                "    }",
                "}")
            .hints(
                "Three separate comparisons, any one of which blocks: join them "
                + "with ||.",
                "equalsIgnoreCase treats Admin and ADMIN as admin.",
                "    if (name.equalsIgnoreCase(\"admin\")\n"
                + "            || name.equalsIgnoreCase(\"root\")\n"
                + "            || name.equalsIgnoreCase(\"administrator\")) {")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Requested name: \");",
                "        String name = input.nextLine().trim();",
                "        if (name.equalsIgnoreCase(\"admin\")",
                "                || name.equalsIgnoreCase(\"root\")",
                "                || name.equalsIgnoreCase(\"administrator\")) {",
                "            System.out.println(\"BLOCKED: reserved name\");",
                "        } else {",
                "            System.out.println(\"AVAILABLE: \" + name);",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "Any one match is enough to block, so the three comparisons are "
                + "joined with ||. equalsIgnoreCase closes the capital-letter "
                + "loophole; trim closes the trailing-space one.\n"
                + "\n"
                + "Notice what it does NOT block: 'admin1' or 'adm1n'. Look-alike "
                + "names need stricter rules - which is why the hidden tests "
                + "check that near misses are allowed, so you can see exactly "
                + "where this rule's protection ends.")
            .sample(Lab.typing("Admin"), 
                "Requested name: Admin",
                "BLOCKED: reserved name")
            .hidden(Lab.typing(" ROOT "), 
                "Requested name:  ROOT",
                "BLOCKED: reserved name")
            .hidden(Lab.typing("AdministratoR"), 
                "Requested name: AdministratoR",
                "BLOCKED: reserved name")
            .hidden(Lab.typing("admin1"), 
                "Requested name: admin1",
                "AVAILABLE: admin1")
            .hidden(Lab.typing("m.reyes"), 
                "Requested name: m.reyes",
                "AVAILABLE: m.reyes"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(10), "Extension Gate", Lab.SMALL)
            .stretch()
            .after("C02-M016")
            .brief(
                "The mail gateway decides what to do with each attachment by "
                + "its real extension - the part after the LAST dot, in lower "
                + "case. Programs and scripts are quarantined. A file with no "
                + "extension at all is suspicious and held.")
            .practises("Checking for -1", "lastIndexOf", "equals with ||")
            .spec(
                "Prompt Attachment: and read the file name, then trim it.",
                "If there is no dot: print HOLD - no extension.",
                "If the extension is .exe, .bat, .js or .scr (any capitals): print QUARANTINE <ext>.",
                "Otherwise print DELIVER <ext>. <ext> is always shown in lower case with its dot.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Attachment: \");",
                "        String name = input.nextLine().trim();",
                "        int dot = name.lastIndexOf(\".\");",
                "        // decide",
                "    }",
                "}")
            .hints(
                "lastIndexOf gives -1 when there is no dot. Check that before "
                + "any substring.",
                "Only in the else is it safe to cut: "
                + "name.substring(dot).toLowerCase().",
                "Four dangerous extensions, any one of which quarantines: join "
                + "four equals checks with ||.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Attachment: \");",
                "        String name = input.nextLine().trim();",
                "        int dot = name.lastIndexOf(\".\");",
                "        if (dot == -1) {",
                "            System.out.println(\"HOLD - no extension\");",
                "        } else {",
                "            String ext = name.substring(dot).toLowerCase();",
                "            if (ext.equals(\".exe\") || ext.equals(\".bat\")",
                "                    || ext.equals(\".js\") || ext.equals(\".scr\")) {",
                "                System.out.println(\"QUARANTINE \" + ext);",
                "            } else {",
                "                System.out.println(\"DELIVER \" + ext);",
                "            }",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The -1 check comes first and the cut lives only in the else, so "
                + "a name with no dot can never reach substring. Inside, the "
                + "extension is lower-cased once and compared four times.\n"
                + "\n"
                + "This is a blocklist, and it shows the weakness: .vbs, .ps1, "
                + ".hta and dozens more would be delivered. A real gateway "
                + "would deliver only an ALLOWLIST of safe types and hold "
                + "everything else - the hidden test with .ps1 is there to make "
                + "that gap visible.")
            .sample(Lab.typing("Invoice.PDF.EXE"), 
                "Attachment: Invoice.PDF.EXE",
                "QUARANTINE .exe")
            .hidden(Lab.typing("report.pdf"), 
                "Attachment: report.pdf",
                "DELIVER .pdf")
            .hidden(Lab.typing("README"), 
                "Attachment: README",
                "HOLD - no extension")
            .hidden(Lab.typing("update.Js"), 
                "Attachment: update.Js",
                "QUARANTINE .js")
            .hidden(Lab.typing("tool.ps1"), 
                "Attachment: tool.ps1",
                "DELIVER .ps1"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(11), "Leap-Year Log Rotation", Lab.MEDIUM)
            .stretch()
            .after("C02-M008")
            .brief(
                "Audit logs are kept for one calendar year, rotated daily, so the "
                + "storage plan needs the number of days in the year. Leap years "
                + "follow a rule with an exception to the exception - a small, "
                + "classic test of combining && and ||.")
            .practises("&& and || together", "The remainder operator", "Brackets for clarity")
            .spec(
                "Prompt Year: and read a whole number.",
                "A year is a leap year if it divides by 4 but not by 100 - or if it divides by 400.",
                "Print <year>: leap year, 366 days of logs  or  <year>: 365 days of logs.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Year: \");",
                "        int year = Integer.parseInt(input.nextLine().trim());",
                "        // decide whether it is a leap year",
                "    }",
                "}")
            .hints(
                "'Divides by 4' is year % 4 == 0: no remainder.",
                "The rule has two parts joined by OR: (divides by 4 AND not by "
                + "100) OR (divides by 400).",
                "Bracket the && part so nobody has to remember precedence:\n"
                + "\n"
                + "    boolean leap = (year % 4 == 0 && year % 100 != 0)\n"
                + "            || year % 400 == 0;")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Year: \");",
                "        int year = Integer.parseInt(input.nextLine().trim());",
                "        boolean leap = (year % 4 == 0 && year % 100 != 0)",
                "                || year % 400 == 0;",
                "        if (leap) {",
                "            System.out.println(year",
                "                    + \": leap year, 366 days of logs\");",
                "        } else {",
                "            System.out.println(year + \": 365 days of logs\");",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The rule is written straight from the specification: divisible "
                + "by 4 AND not by 100, OR divisible by 400. % gives the "
                + "remainder, so == 0 means 'divides exactly'.\n"
                + "\n"
                + "Storing the answer in a named boolean first keeps the if "
                + "readable. The hidden tests are the four kinds of year: 2024 "
                + "(normal leap), 2023 (not), 1900 (divisible by 100, so not) and "
                + "2000 (divisible by 400, so leap after all). Get either "
                + "exception wrong and one of them fails.")
            .sample(Lab.typing("2024"), 
                "Year: 2024",
                "2024: leap year, 366 days of logs")
            .hidden(Lab.typing("2023"), 
                "Year: 2023",
                "2023: 365 days of logs")
            .hidden(Lab.typing("1900"), 
                "Year: 1900",
                "1900: 365 days of logs")
            .hidden(Lab.typing("2000"), 
                "Year: 2000",
                "2000: leap year, 366 days of logs"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(12), "Login Decision Engine", Lab.MEDIUM)
            .after("C02-M015")
            .brief(
                "A login passes four checks in order: the account exists, it is "
                + "not locked, the password is right, the second factor passed. "
                + "The audit log must record exactly which check failed. The "
                + "person logging in must NOT be told - one generic message "
                + "for every failure, so attackers cannot learn which usernames "
                + "exist.")
            .practises("Nested decisions or else-if chains", "Reading y/n answers",
                       "Separating audit detail from user messages")
            .spec(
                "Ask, in this order, each answered y or n: Account exists (y/n):, Locked (y/n):, Password correct (y/n):, MFA passed (y/n):",
                "Treat y or Y (with any spaces) as yes; anything else as no.",
                "Print AUDIT: with the FIRST failing check: no such account, account locked, wrong password, MFA failed - or access granted.",
                "Then print USER SEES: Welcome if granted, otherwise USER SEES: Login failed.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // 1. ask the four questions",
                "        // 2. find the first failing check",
                "        // 3. print the audit line and the user line",
                "    }",
                "}")
            .hints(
                "Turn each answer into a boolean as you read it:\n"
                + "\n"
                + "    System.out.print(\"Account exists (y/n): \");\n"
                + "    boolean exists = input.nextLine().trim()\n"
                + "            .equalsIgnoreCase(\"y\");",
                "An else-if chain in check order finds the FIRST failure: "
                + "!exists, then locked, then !passwordOk, then !mfaOk, then "
                + "the final else is success.",
                "Set a String reason in each branch, and a boolean granted only "
                + "in the last one. Print both lines after the chain.",
                "The user line is one ?: away:\n"
                + "\n"
                + "    System.out.println(\"USER SEES: \"\n"
                + "            + (granted ? \"Welcome\" : \"Login failed\"));")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Account exists (y/n): \");",
                "        boolean exists = input.nextLine().trim()",
                "                .equalsIgnoreCase(\"y\");",
                "        System.out.print(\"Locked (y/n): \");",
                "        boolean locked = input.nextLine().trim()",
                "                .equalsIgnoreCase(\"y\");",
                "        System.out.print(\"Password correct (y/n): \");",
                "        boolean passwordOk = input.nextLine().trim()",
                "                .equalsIgnoreCase(\"y\");",
                "        System.out.print(\"MFA passed (y/n): \");",
                "        boolean mfaOk = input.nextLine().trim().equalsIgnoreCase(\"y\");",
                "",
                "        String reason;",
                "        boolean granted = false;",
                "        if (!exists) {",
                "            reason = \"no such account\";",
                "        } else if (locked) {",
                "            reason = \"account locked\";",
                "        } else if (!passwordOk) {",
                "            reason = \"wrong password\";",
                "        } else if (!mfaOk) {",
                "            reason = \"MFA failed\";",
                "        } else {",
                "            reason = \"access granted\";",
                "            granted = true;",
                "        }",
                "        System.out.println(\"AUDIT: \" + reason);",
                "        String shown = granted ? \"Welcome\" : \"Login failed\";",
                "        System.out.println(\"USER SEES: \" + shown);",
                "    }",
                "}")
            .walkthrough(
                "Each answer becomes a boolean the moment it is read, so the "
                + "decision code never deals with text. Anything that is not a "
                + "y counts as no - including a typo - which is the safe way "
                + "round.\n"
                + "\n"
                + "The else-if chain checks in the order the policy lists, and "
                + "stops at the first failure: a locked account is reported as "
                + "locked even if its password is also wrong. reason is "
                + "declared before the chain and assigned in every branch, so "
                + "it is definitely assigned (mission 19).\n"
                + "\n"
                + "The two output lines are the real lesson. The audit line is "
                + "precise, for analysts. The user line is identical for every "
                + "failure, so nobody can use the login form to discover which "
                + "accounts exist - the user enumeration problem from mission "
                + "15.")
            .sample(Lab.typing("y", "n", "y", "n"), 
                "Account exists (y/n): y",
                "Locked (y/n): n",
                "Password correct (y/n): y",
                "MFA passed (y/n): n",
                "AUDIT: MFA failed",
                "USER SEES: Login failed")
            .hidden(Lab.typing("n", "n", "y", "y"), 
                "Account exists (y/n): n",
                "Locked (y/n): n",
                "Password correct (y/n): y",
                "MFA passed (y/n): y",
                "AUDIT: no such account",
                "USER SEES: Login failed")
            .hidden(Lab.typing("Y", "y", "y", "y"), 
                "Account exists (y/n): Y",
                "Locked (y/n): y",
                "Password correct (y/n): y",
                "MFA passed (y/n): y",
                "AUDIT: account locked",
                "USER SEES: Login failed")
            .hidden(Lab.typing("y", "n", "n", "y"), 
                "Account exists (y/n): y",
                "Locked (y/n): n",
                "Password correct (y/n): n",
                "MFA passed (y/n): y",
                "AUDIT: wrong password",
                "USER SEES: Login failed")
            .hidden(Lab.typing(" y ", "N", "Y", "y"), 
                "Account exists (y/n):  y",
                "Locked (y/n): N",
                "Password correct (y/n): Y",
                "MFA passed (y/n): y",
                "AUDIT: access granted",
                "USER SEES: Welcome"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(13), "Protocol by Port", Lab.MEDIUM)
            .after("C02-M024")
            .brief(
                "The exposure report names the service behind each open port and "
                + "flags the ones that send everything - passwords included - "
                + "in clear text. A switch maps the port to its name; a second "
                + "decision adds the warning.")
            .practises("Switch expressions", "Several values per case", "A decision on the result")
            .spec(
                "Prompt Port: and read a whole number.",
                "Print Service: with the name: 20 or 21 FTP, 22 SSH, 23 TELNET, 25 SMTP, 53 DNS, 80 or 8080 HTTP, 443 HTTPS, 3389 RDP, anything else UNKNOWN.",
                "For FTP or TELNET, also print WARNING: cleartext protocol.",
                "For UNKNOWN, also print REVIEW: unrecognised service.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Port: \");",
                "        int port = Integer.parseInt(input.nextLine().trim());",
                "        // map the port to a service, then warn if needed",
                "    }",
                "}")
            .hints(
                "A switch expression gives the name in one statement:\n"
                + "\n"
                + "    String service = switch (port) {\n"
                + "        case 20, 21 -> \"FTP\";\n"
                + "        ...\n"
                + "        default -> \"UNKNOWN\";\n"
                + "    };",
                "The warnings depend on the service NAME, so decide them after "
                + "the switch, comparing Strings with equals.",
                "    if (service.equals(\"FTP\") || service.equals(\"TELNET\")) {")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Port: \");",
                "        int port = Integer.parseInt(input.nextLine().trim());",
                "        String service = switch (port) {",
                "            case 20, 21 -> \"FTP\";",
                "            case 22 -> \"SSH\";",
                "            case 23 -> \"TELNET\";",
                "            case 25 -> \"SMTP\";",
                "            case 53 -> \"DNS\";",
                "            case 80, 8080 -> \"HTTP\";",
                "            case 443 -> \"HTTPS\";",
                "            case 3389 -> \"RDP\";",
                "            default -> \"UNKNOWN\";",
                "        };",
                "        System.out.println(\"Service: \" + service);",
                "        if (service.equals(\"FTP\") || service.equals(\"TELNET\")) {",
                "            System.out.println(\"WARNING: cleartext protocol\");",
                "        } else if (service.equals(\"UNKNOWN\")) {",
                "            System.out.println(\"REVIEW: unrecognised service\");",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The switch expression is a lookup table: one line per service, "
                + "commas where several ports share a name, and a default that "
                + "turns everything unlisted into UNKNOWN rather than ignoring "
                + "it. No breaks, so no fall-through.\n"
                + "\n"
                + "The warnings are a separate decision on the RESULT. Keeping "
                + "the mapping and the judgement apart means adding a port "
                + "never risks changing which services get flagged. Telnet and "
                + "FTP send credentials unencrypted; anyone on the network path "
                + "can read them - which is why finding either one open is an "
                + "immediate finding in any audit.")
            .sample(Lab.typing("22"), 
                "Port: 22",
                "Service: SSH")
            .hidden(Lab.typing("23"), 
                "Port: 23",
                "Service: TELNET",
                "WARNING: cleartext protocol")
            .hidden(Lab.typing("21"), 
                "Port: 21",
                "Service: FTP",
                "WARNING: cleartext protocol")
            .hidden(Lab.typing("8080"), 
                "Port: 8080",
                "Service: HTTP")
            .hidden(Lab.typing("9999"), 
                "Port: 9999",
                "Service: UNKNOWN",
                "REVIEW: unrecognised service"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(14), "Command Router", Lab.MEDIUM)
            .stretch()
            .after("C02-M023")
            .brief(
                "The analyst console takes one command at a time. It must accept "
                + "commands in any capitals with stray spaces, answer each known "
                + "command, and for anything else repeat back exactly what was "
                + "typed so the analyst can spot the mistake.")
            .practises("switch on a String", "Normalising the switched value", "A helpful default")
            .spec(
                "Prompt Command: and read the line.",
                "Compare it trimmed and in lower case: status, scan, lock, help and quit.",
                "status: All systems monitored.  scan: Scan queued.  lock: Account locked.  help: Commands: status scan lock help quit.  quit: Session closed.",
                "Anything else: Unknown command: followed by what was typed, trimmed but with its original capitals.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Command: \");",
                "        String typed = input.nextLine().trim();",
                "        // route the command",
                "    }",
                "}")
            .hints(
                "Switch on the normalised copy, typed.toLowerCase(), and keep "
                + "typed itself for the unknown message.",
                "Either form of switch works. The arrow form needs no breaks:\n"
                + "\n"
                + "    switch (typed.toLowerCase()) {\n"
                + "        case \"status\" -> System.out.println(\"...\");\n"
                + "        ...\n"
                + "    }",
                "The default prints typed, not the lower-case copy - the "
                + "specification asks for the original capitals.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Command: \");",
                "        String typed = input.nextLine().trim();",
                "        switch (typed.toLowerCase()) {",
                "            case \"status\" ->",
                "                System.out.println(\"All systems monitored\");",
                "            case \"scan\" -> System.out.println(\"Scan queued\");",
                "            case \"lock\" -> System.out.println(\"Account locked\");",
                "            case \"help\" ->",
                "                System.out.println(",
                "                        \"Commands: status scan lock help quit\");",
                "            case \"quit\" -> System.out.println(\"Session closed\");",
                "            default ->",
                "                System.out.println(\"Unknown command: \" + typed);",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The switch examines a normalised copy, so STATUS, Status and "
                + "'  status ' all route the same way. The original, trimmed "
                + "text is kept for the one place it matters: telling the "
                + "analyst exactly what they typed when it was not understood.\n"
                + "\n"
                + "The arrow form runs one statement per case and cannot fall "
                + "through, so there is no break to forget. And the command set "
                + "is a fixed list - an allowlist - so nothing typed can make "
                + "the console do anything that is not on it.")
            .sample(Lab.typing("status"), 
                "Command: status",
                "All systems monitored")
            .hidden(Lab.typing("  SCAN "), 
                "Command:   SCAN",
                "Scan queued")
            .hidden(Lab.typing("Help"), 
                "Command: Help",
                "Commands: status scan lock help quit")
            .hidden(Lab.typing("Restart WEB-01"), 
                "Command: Restart WEB-01",
                "Unknown command: Restart WEB-01"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(15), "Safe Email Parser", Lab.MEDIUM)
            .after("C02-M016")
            .brief(
                "Campaign 01's email splitter trusted every address to be "
                + "well-formed. The phishing triage tool gets pasted text of "
                + "every kind, so the new version checks the shape first and "
                + "says precisely what is wrong - and only splits an address "
                + "that has exactly one @ with something on each side.")
            .practises("Validation before use", "indexOf and lastIndexOf", "An ordered chain of checks")
            .spec(
                "Prompt Email: and read the line, then trim it.",
                "Refuse, in this order: blank - REQUIRED;  no @ - MISSING @;  @ first - MISSING USER;  @ last - MISSING DOMAIN;  more than one @ - TOO MANY @.",
                "Otherwise print User: as typed and Domain: in lower case, lined up as the sample shows.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Email: \");",
                "        String email = input.nextLine().trim();",
                "        int at = email.indexOf(\"@\");",
                "        // check the shape, then split",
                "    }",
                "}")
            .hints(
                "The checks form one else-if chain, in the order given, with the "
                + "split in the final else.",
                "@ first means at == 0. @ last means at == email.length() - 1.",
                "Two or more @ signs means the first one and the last one are in "
                + "different places:\n"
                + "\n"
                + "    at != email.lastIndexOf(\"@\")",
                "Check blank before anything else - isEmpty after trimming, or "
                + "isBlank.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Email: \");",
                "        String email = input.nextLine().trim();",
                "        int at = email.indexOf(\"@\");",
                "        if (email.isEmpty()) {",
                "            System.out.println(\"REQUIRED\");",
                "        } else if (at == -1) {",
                "            System.out.println(\"MISSING @\");",
                "        } else if (at == 0) {",
                "            System.out.println(\"MISSING USER\");",
                "        } else if (at == email.length() - 1) {",
                "            System.out.println(\"MISSING DOMAIN\");",
                "        } else if (at != email.lastIndexOf(\"@\")) {",
                "            System.out.println(\"TOO MANY @\");",
                "        } else {",
                "            System.out.println(\"User:   \" + email.substring(0, at));",
                "            String domain = email.substring(at + 1).toLowerCase();",
                "            System.out.println(\"Domain: \" + domain);",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "Each gate relies on the ones before it. By the time the chain "
                + "asks whether the @ is first or last, it already knows there "
                + "is one. By the time it cuts, it knows there is exactly one, "
                + "with at least one character on each side - so neither "
                + "substring can crash or come back empty.\n"
                + "\n"
                + "indexOf finds the first @ and lastIndexOf the last; if they "
                + "differ, there are at least two. That one comparison catches "
                + "addresses like a@b@evil.example, a classic trick for "
                + "confusing a parser about which domain it is looking at.\n"
                + "\n"
                + "The hidden tests send one input for each refusal, plus a "
                + "good address with odd capitals.")
            .sample(Lab.typing("a.okafor@NorthStar.example"), 
                "Email: a.okafor@NorthStar.example",
                "User:   a.okafor",
                "Domain: northstar.example")
            .hidden(Lab.typing("   "), 
                "Email:",
                "REQUIRED")
            .hidden(Lab.typing("call me urgently"), 
                "Email: call me urgently",
                "MISSING @")
            .hidden(Lab.typing("@northstar.example"), 
                "Email: @northstar.example",
                "MISSING USER")
            .hidden(Lab.typing("jsmith@"), 
                "Email: jsmith@",
                "MISSING DOMAIN")
            .hidden(Lab.typing("jsmith@northstar.example@evil.example"), 
                "Email: jsmith@northstar.example@evil.example",
                "TOO MANY @"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(16), "Validated Number Input", Lab.MEDIUM)
            .stretch()
            .after("C02-M017")
            .brief(
                "Every numeric field in the admin console goes through the same "
                + "gatekeeper before anything uses it. Build it: refuse empty "
                + "input, anything that is not digits, and anything too long to "
                + "be sure it fits an int - and only then convert.")
            .practises("A validation pipeline", "matches", "Converting only when safe")
            .spec(
                "Prompt Value: and read the line, then trim it.",
                "Empty: REQUIRED.  Not one or more digits: NOT A NUMBER.  More than 9 digits: TOO LARGE.",
                "Otherwise print Value: and the number, then Doubled: and twice the number.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Value: \");",
                "        String text = input.nextLine().trim();",
                "        // the gates, then the conversion",
                "    }",
                "}")
            .hints(
                "Three rejecting gates in an else-if chain, and the conversion "
                + "in the final else.",
                "The digits gate rejects what does NOT match: "
                + "!text.matches(\"[0-9]+\").",
                "Nine digits is at most 999999999; doubled, that still fits in "
                + "an int.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Value: \");",
                "        String text = input.nextLine().trim();",
                "        if (text.isEmpty()) {",
                "            System.out.println(\"REQUIRED\");",
                "        } else if (!text.matches(\"[0-9]+\")) {",
                "            System.out.println(\"NOT A NUMBER\");",
                "        } else if (text.length() > 9) {",
                "            System.out.println(\"TOO LARGE\");",
                "        } else {",
                "            int value = Integer.parseInt(text);",
                "            System.out.println(\"Value: \" + value);",
                "            System.out.println(\"Doubled: \" + value * 2);",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The pipeline from mission 29, as a reusable gatekeeper. Every "
                + "gate describes a reason to REFUSE, and parseInt only runs in "
                + "the final else, where all three have passed - so this "
                + "program cannot crash on anything typed.\n"
                + "\n"
                + "The length gate matters: ten digits match [0-9]+ and still "
                + "overflow an int. One hidden test sends exactly nine digits "
                + "and another ten - the boundary - and another sends a number "
                + "with a minus sign, which the pattern rightly refuses because "
                + "the field only takes counts.")
            .sample(Lab.typing("42"), 
                "Value: 42",
                "Value: 42",
                "Doubled: 84")
            .hidden(Lab.typing(""), 
                "Value:",
                "REQUIRED")
            .hidden(Lab.typing("4 2"), 
                "Value: 4 2",
                "NOT A NUMBER")
            .hidden(Lab.typing("-7"), 
                "Value: -7",
                "NOT A NUMBER")
            .hidden(Lab.typing("999999999"), 
                "Value: 999999999",
                "Value: 999999999",
                "Doubled: 1999999998")
            .hidden(Lab.typing("1000000000"), 
                "Value: 1000000000",
                "TOO LARGE"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(17), "Risk Band", Lab.MEDIUM)
            .stretch()
            .after("C02-M020")
            .brief(
                "A vulnerability on a critical asset - the domain controller, "
                + "the payroll database - is more urgent than the same "
                + "vulnerability on a test laptop. Weight the score for critical "
                + "assets, cap it at 10, and band it.")
            .practises("?: to choose a value", "Math.min as a cap", "Bands on doubles")
            .spec(
                "Prompt Severity (0-10): and read a decimal; then Critical asset (y/n): .",
                "A severity below 0 or above 10: print INVALID SEVERITY and nothing else.",
                "Risk is the severity times 1.5 for a critical asset, unchanged otherwise, never more than 10.",
                "Print Risk: to one decimal place, then Band: CRITICAL (9+), HIGH (7+), MEDIUM (4+) or LOW.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Severity (0-10): \");",
                "        double severity = Double.parseDouble(input.nextLine().trim());",
                "        System.out.print(\"Critical asset (y/n): \");",
                "        boolean critical = input.nextLine().trim()",
                "                .equalsIgnoreCase(\"y\");",
                "        // validate, weight, cap, band",
                "    }",
                "}")
            .hints(
                "Validate first; everything else goes in the else.",
                "Weighting is choosing a value: "
                + "critical ? severity * 1.5 : severity.",
                "Math.min(risk, 10.0) caps it.",
                "Band with an else-if chain, highest first, and print Risk: "
                + "with printf and %.1f.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Severity (0-10): \");",
                "        double severity = Double.parseDouble(input.nextLine().trim());",
                "        System.out.print(\"Critical asset (y/n): \");",
                "        boolean critical = input.nextLine().trim()",
                "                .equalsIgnoreCase(\"y\");",
                "        if (severity < 0 || severity > 10) {",
                "            System.out.println(\"INVALID SEVERITY\");",
                "        } else {",
                "            double risk = critical ? severity * 1.5 : severity;",
                "            risk = Math.min(risk, 10.0);",
                "            String band;",
                "            if (risk >= 9.0) {",
                "                band = \"CRITICAL\";",
                "            } else if (risk >= 7.0) {",
                "                band = \"HIGH\";",
                "            } else if (risk >= 4.0) {",
                "                band = \"MEDIUM\";",
                "            } else {",
                "                band = \"LOW\";",
                "            }",
                "            System.out.printf(\"Risk: %.1f%n\", risk);",
                "            System.out.println(\"Band: \" + band);",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "Three steps, each one a tool from this campaign or the last: "
                + "?: chooses the weighting, Math.min caps it, and an else-if "
                + "chain bands it. band is declared before the chain and set in "
                + "every branch.\n"
                + "\n"
                + "The cap matters: a 7.0 on a critical asset would otherwise "
                + "score 10.5, off the scale. The hidden tests include exactly "
                + "that case, a 6.0 that becomes 9.0 (moving from MEDIUM to "
                + "CRITICAL - the point of weighting), and an invalid score. "
                + "The bands compare with >=, so values that land exactly on a "
                + "threshold behave as the policy says.")
            .sample(Lab.typing("5.0", "n"), 
                "Severity (0-10): 5.0",
                "Critical asset (y/n): n",
                "Risk: 5.0",
                "Band: MEDIUM")
            .hidden(Lab.typing("6.0", "y"), 
                "Severity (0-10): 6.0",
                "Critical asset (y/n): y",
                "Risk: 9.0",
                "Band: CRITICAL")
            .hidden(Lab.typing("7.0", "Y"), 
                "Severity (0-10): 7.0",
                "Critical asset (y/n): Y",
                "Risk: 10.0",
                "Band: CRITICAL")
            .hidden(Lab.typing("2.5", "y"), 
                "Severity (0-10): 2.5",
                "Critical asset (y/n): y",
                "Risk: 3.8",
                "Band: LOW")
            .hidden(Lab.typing("11", "n"), 
                "Severity (0-10): 11",
                "Critical asset (y/n): n",
                "INVALID SEVERITY"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(18), "Account Status Report", Lab.MEDIUM)
            .stretch()
            .after("C02-M020")
            .brief(
                "The quarterly access review prints one small report per "
                + "account. Each line is a label and a value chosen from two "
                + "options - exactly what the conditional operator is for.")
            .practises("?: for labels", "Plurals", "Reading several answers")
            .spec(
                "Ask Account:, Failed attempts:, Days since last login: and MFA enabled (y/n): in that order.",
                "Print ACCOUNT, ATTEMPTS, ACTIVITY and MFA lines, labels padded to 9 characters, as the sample shows.",
                "Attempts: 1 failed attempt, but any other number says attempts.",
                "Activity: DORMANT (<n> days) if more than 90 days, otherwise ACTIVE.  MFA: ENABLED, or DISABLED - action needed.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // ask the four questions, then print four lines",
                "    }",
                "}")
            .hints(
                "Read the two numbers with Integer.parseInt and the y/n answer "
                + "into a boolean.",
                "The plural is a ?: inside brackets:\n"
                + "\n"
                + "    failures + \" failed attempt\"\n"
                + "        + (failures == 1 ? \"\" : \"s\")",
                "The activity label needs the number inside it when dormant:\n"
                + "\n"
                + "    days > 90 ? \"DORMANT (\" + days + \" days)\" : \"ACTIVE\"",
                "Labels padded to 9: \"ACCOUNT  \", \"ATTEMPTS \", \"ACTIVITY \", "
                + "\"MFA      \".")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Account: \");",
                "        String account = input.nextLine().trim();",
                "        System.out.print(\"Failed attempts: \");",
                "        int failures = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Days since last login: \");",
                "        int days = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"MFA enabled (y/n): \");",
                "        boolean mfa = input.nextLine().trim().equalsIgnoreCase(\"y\");",
                "",
                "        String attempts = failures + \" failed attempt\"",
                "                + (failures == 1 ? \"\" : \"s\");",
                "        String activity = days > 90",
                "                ? \"DORMANT (\" + days + \" days)\"",
                "                : \"ACTIVE\";",
                "        String mfaState = mfa",
                "                ? \"ENABLED\"",
                "                : \"DISABLED - action needed\";",
                "        System.out.println(\"ACCOUNT  \" + account);",
                "        System.out.println(\"ATTEMPTS \" + attempts);",
                "        System.out.println(\"ACTIVITY \" + activity);",
                "        System.out.println(\"MFA      \" + mfaState);",
                "    }",
                "}")
            .walkthrough(
                "Every line of the report is a value chosen between two "
                + "options, so every line is a ?:. Working the three labels out "
                + "into named Strings first keeps the printing simple and each "
                + "choice easy to check on its own.\n"
                + "\n"
                + "The hidden tests hit the edges: exactly 1 attempt (singular), "
                + "0 attempts (plural - '0 failed attempts'), exactly 90 days "
                + "(still active) and 91 (dormant). Dormant accounts and "
                + "missing MFA are two of the most common findings in any "
                + "access review: accounts nobody uses are accounts nobody "
                + "notices being used by someone else.")
            .sample(Lab.typing("contractor", "3", "120", "n"), 
                "Account: contractor",
                "Failed attempts: 3",
                "Days since last login: 120",
                "MFA enabled (y/n): n",
                "ACCOUNT  contractor",
                "ATTEMPTS 3 failed attempts",
                "ACTIVITY DORMANT (120 days)",
                "MFA      DISABLED - action needed")
            .hidden(Lab.typing("a.okafor", "1", "2", "y"), 
                "Account: a.okafor",
                "Failed attempts: 1",
                "Days since last login: 2",
                "MFA enabled (y/n): y",
                "ACCOUNT  a.okafor",
                "ATTEMPTS 1 failed attempt",
                "ACTIVITY ACTIVE",
                "MFA      ENABLED")
            .hidden(Lab.typing("m.reyes", "0", "90", "Y"), 
                "Account: m.reyes",
                "Failed attempts: 0",
                "Days since last login: 90",
                "MFA enabled (y/n): Y",
                "ACCOUNT  m.reyes",
                "ATTEMPTS 0 failed attempts",
                "ACTIVITY ACTIVE",
                "MFA      ENABLED")
            .hidden(Lab.typing("svc-backup", "12", "91", "n"), 
                "Account: svc-backup",
                "Failed attempts: 12",
                "Days since last login: 91",
                "MFA enabled (y/n): n",
                "ACCOUNT  svc-backup",
                "ATTEMPTS 12 failed attempts",
                "ACTIVITY DORMANT (91 days)",
                "MFA      DISABLED - action needed"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(19), "Password Rule Checker", Lab.MEDIUM)
            .after("C02-M017")
            .brief(
                "The new password policy has five rules, checked in order, and "
                + "a refusal must name the FIRST rule broken so the person can "
                + "fix it. It is the validation pipeline again, applied to the "
                + "field attackers care about most.")
            .practises("An ordered validation chain", "contains with normalising",
                       "switch as a small blocklist")
            .spec(
                "Prompt Username: then Password: , reading each line. Trim the username; use the password exactly as typed.",
                "Check in order: empty - REJECTED: empty;  fewer than 12 characters - REJECTED: too short;  only digits - REJECTED: digits only.",
                "Then: contains the username, ignoring case - REJECTED: contains username;  one of password1234, qwerty123456, letmein12345 (ignoring case) - REJECTED: too common.",
                "Otherwise print ACCEPTED.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String user = input.nextLine().trim();",
                "        System.out.print(\"Password: \");",
                "        String password = input.nextLine();",
                "        // five rules, in order",
                "    }",
                "}")
            .hints(
                "One else-if chain, one rule per branch, in the order given, "
                + "ending in else ACCEPTED.",
                "'Only digits' is password.matches(\"[0-9]+\").",
                "To ignore case in contains, lower-case both sides:\n"
                + "\n"
                + "    password.toLowerCase().contains(user.toLowerCase())",
                "The common-password check can be a switch expression that "
                + "gives a boolean:\n"
                + "\n"
                + "    boolean common = switch (password.toLowerCase()) {\n"
                + "        case \"password1234\", \"qwerty123456\",\n"
                + "             \"letmein12345\" -> true;\n"
                + "        default -> false;\n"
                + "    };")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String user = input.nextLine().trim();",
                "        System.out.print(\"Password: \");",
                "        String password = input.nextLine();",
                "        String lower = password.toLowerCase();",
                "        boolean common = switch (lower) {",
                "            case \"password1234\", \"qwerty123456\",",
                "                 \"letmein12345\" -> true;",
                "            default -> false;",
                "        };",
                "        if (password.isEmpty()) {",
                "            System.out.println(\"REJECTED: empty\");",
                "        } else if (password.length() < 12) {",
                "            System.out.println(\"REJECTED: too short\");",
                "        } else if (password.matches(\"[0-9]+\")) {",
                "            System.out.println(\"REJECTED: digits only\");",
                "        } else if (lower.contains(user.toLowerCase())) {",
                "            System.out.println(\"REJECTED: contains username\");",
                "        } else if (common) {",
                "            System.out.println(\"REJECTED: too common\");",
                "        } else {",
                "            System.out.println(\"ACCEPTED\");",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "A chain of gates, each naming one rule, so the first failure is "
                + "the one reported. The password is never trimmed - spaces are "
                + "legitimate password characters - but the username is, so "
                + "' jsmith' cannot slip its name past the contains check.\n"
                + "\n"
                + "Lower-casing both sides before contains means JSmith2024isfine "
                + "is still caught. The common-password list is a tiny "
                + "switch-based blocklist; real checkers compare against lists "
                + "of millions of breached passwords, which needs the "
                + "collections and files of later campaigns.\n"
                + "\n"
                + "The hidden tests break each rule once, including a password "
                + "that is long enough but all digits, and one that is common "
                + "but in capitals.")
            .sample(Lab.typing("jsmith", "Jsmith-was-here"), 
                "Username: jsmith",
                "Password: Jsmith-was-here",
                "REJECTED: contains username")
            .hidden(Lab.typing("jsmith", ""), 
                "Username: jsmith",
                "Password:",
                "REJECTED: empty")
            .hidden(Lab.typing("jsmith", "tiny"), 
                "Username: jsmith",
                "Password: tiny",
                "REJECTED: too short")
            .hidden(Lab.typing("jsmith", "123456789012"), 
                "Username: jsmith",
                "Password: 123456789012",
                "REJECTED: digits only")
            .hidden(Lab.typing("jsmith", "QWERTY123456"), 
                "Username: jsmith",
                "Password: QWERTY123456",
                "REJECTED: too common")
            .hidden(Lab.typing("jsmith", "correct horse battery"), 
                "Username: jsmith",
                "Password: correct horse battery",
                "ACCEPTED"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(20), "Time-Window Rule", Lab.MEDIUM)
            .stretch()
            .after("C02-M019")
            .brief(
                "Contractors may only log in between 07:30 and 19:00. The "
                + "gateway receives the login time as text - and some clients "
                + "send it badly formed. Validate the format completely, then "
                + "decide.")
            .practises("Validating a fixed format", "Minutes since midnight", "Boundaries on a window")
            .spec(
                "Prompt Login time (HH:MM): and read the line, then trim it.",
                "Valid means exactly 5 characters, a colon in the middle, digits on each side, hours 0-23 and minutes 0-59. Anything else: INVALID TIME.",
                "07:30 up to but not including 19:00: ALLOWED. Otherwise: BLOCKED - outside 07:30-19:00.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Login time (HH:MM): \");",
                "        String time = input.nextLine().trim();",
                "        // validate the format, then check the window",
                "    }",
                "}")
            .hints(
                "Check the SHAPE first, guarded so nothing can crash: length "
                + "5, then charAt(2) == ':', then each side matches [0-9]+.",
                "Only after the shape is right is it safe to parseInt the two "
                + "halves. Then check hours <= 23 and minutes <= 59.",
                "Convert to minutes since midnight: hours * 60 + minutes. 07:30 "
                + "is 450 and 19:00 is 1140.",
                "Inside the window: minutes >= 450 && minutes < 1140.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Login time (HH:MM): \");",
                "        String time = input.nextLine().trim();",
                "        boolean shape = time.length() == 5 && time.charAt(2) == ':'",
                "                && time.substring(0, 2).matches(\"[0-9]+\")",
                "                && time.substring(3).matches(\"[0-9]+\");",
                "        if (!shape) {",
                "            System.out.println(\"INVALID TIME\");",
                "        } else {",
                "            int hours = Integer.parseInt(time.substring(0, 2));",
                "            int minutes = Integer.parseInt(time.substring(3));",
                "            if (hours > 23 || minutes > 59) {",
                "                System.out.println(\"INVALID TIME\");",
                "            } else {",
                "                int total = hours * 60 + minutes;",
                "                if (total >= 450 && total < 1140) {",
                "                    System.out.println(\"ALLOWED\");",
                "                } else {",
                "                    System.out.println(",
                "                            \"BLOCKED - outside 07:30-19:00\");",
                "                }",
                "            }",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The shape check is one long && chain, and short-circuiting "
                + "makes it safe: charAt(2) and the substrings are only reached "
                + "once the length is known to be 5. Only when the shape is "
                + "right does parseInt run, and only when the numbers are real "
                + "times does the window get checked.\n"
                + "\n"
                + "Converting to minutes turns 'between 07:30 and 19:00' into one "
                + "range check on one number. The hidden tests sit on both edges "
                + "(07:29, 07:30, 18:59, 19:00) and send three kinds of broken "
                + "time: a missing leading zero, an impossible hour and letters. "
                + "Refusing malformed input outright is fail-closed: a gateway "
                + "that guessed what 7:30 meant would one day guess wrong.")
            .sample(Lab.typing("08:15"), 
                "Login time (HH:MM): 08:15",
                "ALLOWED")
            .hidden(Lab.typing("07:29"), 
                "Login time (HH:MM): 07:29",
                "BLOCKED - outside 07:30-19:00")
            .hidden(Lab.typing("07:30"), 
                "Login time (HH:MM): 07:30",
                "ALLOWED")
            .hidden(Lab.typing("18:59"), 
                "Login time (HH:MM): 18:59",
                "ALLOWED")
            .hidden(Lab.typing("19:00"), 
                "Login time (HH:MM): 19:00",
                "BLOCKED - outside 07:30-19:00")
            .hidden(Lab.typing("7:30"), 
                "Login time (HH:MM): 7:30",
                "INVALID TIME")
            .hidden(Lab.typing("25:00"), 
                "Login time (HH:MM): 25:00",
                "INVALID TIME")
            .hidden(Lab.typing("ab:cd"), 
                "Login time (HH:MM): ab:cd",
                "INVALID TIME"));
    }
}
