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
    }
}
