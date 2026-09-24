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

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(21), "Two-Factor Flow", Lab.MEDIUM)
            .stretch()
            .after("C02-M028")
            .brief(
                "The VPN portal is moving to two-factor login. A code is only "
                + "asked for once the password is right, and a trusted device "
                + "skips the code next time. Build the flow - including the "
                + "questions it must NOT ask.")
            .practises("Reading input inside a branch", "Nested if", "Validating a code")
            .spec(
                "Prompt Password: and read the line exactly as typed - no trim, because spaces in a password are part of it. The password is Northstar#2026, and capitals matter.",
                "Wrong password: print ACCESS DENIED and ask nothing more.",
                "Right password: prompt 6-digit code: and read it, trimmed. Not exactly six digits: ACCESS DENIED: codes are 6 digits. Six digits but not 481516: ACCESS DENIED: code not accepted.",
                "Right code: print ACCESS GRANTED, then prompt Trust this device (y/n): and print Device trusted for 30 days for y or Y, otherwise Code required next time.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Password: \");",
                "        String password = input.nextLine();",
                "        // stage 1: the password",
                "        // stage 2, only if stage 1 passed: the code",
                "    }",
                "}")
            .hints(
                "A prompt that sits outside every if runs every time. The "
                + "code prompt belongs INSIDE the branch where the password "
                + "was right.",
                "Compare the password with .equals - not == and not "
                + "equalsIgnoreCase - and do not trim it.",
                "A code has the right shape when both of these are true:\n"
                + "\n"
                + "    code.length() == 6 && code.matches(\"[0-9]+\")\n"
                + "\n"
                + "The wrong shape is the opposite: use ! and ||.",
                "Inside the right-code branch: println ACCESS GRANTED, then "
                + "the trust prompt, then an if / else on the answer.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Password: \");",
                "        String password = input.nextLine();",
                "        if (!password.equals(\"Northstar#2026\")) {",
                "            System.out.println(\"ACCESS DENIED\");",
                "        } else {",
                "            System.out.print(\"6-digit code: \");",
                "            String code = input.nextLine().trim();",
                "            if (code.length() != 6 || !code.matches(\"[0-9]+\")) {",
                "                System.out.println(",
                "                        \"ACCESS DENIED: codes are 6 digits\");",
                "            } else if (!code.equals(\"481516\")) {",
                "                System.out.println(",
                "                        \"ACCESS DENIED: code not accepted\");",
                "            } else {",
                "                System.out.println(\"ACCESS GRANTED\");",
                "                System.out.print(\"Trust this device (y/n): \");",
                "                String trust = input.nextLine().trim();",
                "                if (trust.equalsIgnoreCase(\"y\")) {",
                "                    System.out.println(\"Device trusted for 30 days\");",
                "                } else {",
                "                    System.out.println(\"Code required next time\");",
                "                }",
                "            }",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The shape of the program IS the shape of the flow. Each "
                + "stage lives inside the else of the stage before it, so the "
                + "code prompt cannot appear unless the password passed, and "
                + "the trust question cannot appear unless the code passed. "
                + "The hidden tests type only one line for a wrong password: "
                + "a program that asks for a code anyway runs out of input "
                + "and crashes.\n"
                + "\n"
                + "The password is compared exactly. northstar#2026 is a "
                + "different password, and so is one with a space in front - "
                + "trimming a password would quietly accept keys the user "
                + "never set. The code IS trimmed: it is a number the user "
                + "copies from an app, and a stray space is not an attack.\n"
                + "\n"
                + "Checking the shape before the value gives a useful message "
                + "for typos without ever saying how close a guess was.")
            .sample(Lab.typing("Northstar#2026", "481516", "y"),
                "Password: Northstar#2026",
                "6-digit code: 481516",
                "ACCESS GRANTED",
                "Trust this device (y/n): y",
                "Device trusted for 30 days")
            .hidden(Lab.typing("northstar#2026"),
                "Password: northstar#2026",
                "ACCESS DENIED")
            .hidden(Lab.typing(" Northstar#2026"),
                "Password:  Northstar#2026",
                "ACCESS DENIED")
            .hidden(Lab.typing("Northstar#2026", "48151"),
                "Password: Northstar#2026",
                "6-digit code: 48151",
                "ACCESS DENIED: codes are 6 digits")
            .hidden(Lab.typing("Northstar#2026", "48151a"),
                "Password: Northstar#2026",
                "6-digit code: 48151a",
                "ACCESS DENIED: codes are 6 digits")
            .hidden(Lab.typing("Northstar#2026", "123456"),
                "Password: Northstar#2026",
                "6-digit code: 123456",
                "ACCESS DENIED: code not accepted")
            .hidden(Lab.typing("Northstar#2026", " 481516 ", "n"),
                "Password: Northstar#2026",
                "6-digit code:  481516",
                "ACCESS GRANTED",
                "Trust this device (y/n): n",
                "Code required next time")
            .hidden(Lab.typing("Northstar#2026", "481516", "Y"),
                "Password: Northstar#2026",
                "6-digit code: 481516",
                "ACCESS GRANTED",
                "Trust this device (y/n): Y",
                "Device trusted for 30 days"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(22), "IP Class Finder", Lab.MEDIUM)
            .stretch()
            .after("C02-M017")
            .brief(
                "Before a firewall rule is written, someone has to say what "
                + "kind of address it is about. Read an IPv4 address, check "
                + "its first two parts, and report its old-style class and "
                + "whether it is private, public or special.")
            .practises("Guarded substring", "Checking text before parseInt", "Range chains")
            .spec(
                "Prompt IP address: and read the line, trimmed.",
                "Only the first two parts are checked. Each must be 1-3 digits with a dot after it, and 0-255. Anything else: INVALID ADDRESS.",
                "Class: A for 0-127, B for 128-191, C for 192-223, D for 224-239, E for 240-255 (by the first part).",
                "Scope, first match wins: LOOPBACK for 127; PRIVATE for 10, 172.16 to 172.31, or 192.168; MULTICAST for 224-239; RESERVED for 0 or 240 and above; otherwise PUBLIC.",
                "Print Class: <letter> then Scope: <scope>.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"IP address: \");",
                "        String ip = input.nextLine().trim();",
                "        // cut out the first two parts, check them, then decide",
                "    }",
                "}")
            .hints(
                "The first part ends at ip.indexOf(\".\"). Cut everything "
                + "after that dot into a String rest, and the second part ends "
                + "at rest.indexOf(\".\").",
                "Guard every substring. If a dot is missing, indexOf gives "
                + "-1, so use ?: to fall back to an empty String:\n"
                + "\n"
                + "    String part1 = dot1 > 0 ? ip.substring(0, dot1) : \"\";\n"
                + "\n"
                + "An empty part then fails .matches(\"[0-9]+\").",
                "Check length() <= 3 before parseInt: \"99999999999\" is "
                + "all digits but too big for an int.",
                "Private is its own boolean, built from three bracketed "
                + "groups joined with ||. The 172 group needs the second part: "
                + "second >= 16 && second <= 31.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"IP address: \");",
                "        String ip = input.nextLine().trim();",
                "        int dot1 = ip.indexOf(\".\");",
                "        String rest = ip.substring(dot1 + 1);",
                "        int dot2 = rest.indexOf(\".\");",
                "        String part1 = dot1 > 0 ? ip.substring(0, dot1) : \"\";",
                "        String part2 = dot2 > 0 ? rest.substring(0, dot2) : \"\";",
                "        boolean ok = part1.matches(\"[0-9]+\") && part1.length() <= 3",
                "                && part2.matches(\"[0-9]+\") && part2.length() <= 3;",
                "        int first = ok ? Integer.parseInt(part1) : -1;",
                "        int second = ok ? Integer.parseInt(part2) : -1;",
                "        if (!ok || first > 255 || second > 255) {",
                "            System.out.println(\"INVALID ADDRESS\");",
                "        } else {",
                "            String ipClass;",
                "            if (first <= 127) {",
                "                ipClass = \"A\";",
                "            } else if (first <= 191) {",
                "                ipClass = \"B\";",
                "            } else if (first <= 223) {",
                "                ipClass = \"C\";",
                "            } else if (first <= 239) {",
                "                ipClass = \"D\";",
                "            } else {",
                "                ipClass = \"E\";",
                "            }",
                "            boolean isPrivate = first == 10",
                "                    || (first == 172 && second >= 16 && second <= 31)",
                "                    || (first == 192 && second == 168);",
                "            String scope;",
                "            if (first == 127) {",
                "                scope = \"LOOPBACK\";",
                "            } else if (isPrivate) {",
                "                scope = \"PRIVATE\";",
                "            } else if (first >= 224 && first <= 239) {",
                "                scope = \"MULTICAST\";",
                "            } else if (first == 0 || first >= 240) {",
                "                scope = \"RESERVED\";",
                "            } else {",
                "                scope = \"PUBLIC\";",
                "            }",
                "            System.out.println(\"Class: \" + ipClass);",
                "            System.out.println(\"Scope: \" + scope);",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "Everything before the if exists to make parseInt safe. The "
                + "?: guards mean a missing dot produces an empty part rather "
                + "than a crash, the matches check rejects letters and empty "
                + "parts, and the length check rejects numbers too long for an "
                + "int. Only then is parseInt allowed to run - and ok ? ... : "
                + "-1 keeps it from running at all on bad input.\n"
                + "\n"
                + "The class chain only needs upper limits, because each "
                + "else if already knows every smaller number was handled. "
                + "The scope chain is different: its tests overlap (10 is "
                + "private AND class A), so the order is the rule. The hidden "
                + "tests sit on the edges of the 172.16-31 block, where a >= "
                + "that should have been > shows up at once.")
            .sample(Lab.typing("10.0.4.17"),
                "IP address: 10.0.4.17",
                "Class: A",
                "Scope: PRIVATE")
            .hidden(Lab.typing("172.16.0.1"),
                "IP address: 172.16.0.1",
                "Class: B",
                "Scope: PRIVATE")
            .hidden(Lab.typing("172.31.255.1"),
                "IP address: 172.31.255.1",
                "Class: B",
                "Scope: PRIVATE")
            .hidden(Lab.typing("172.32.0.1"),
                "IP address: 172.32.0.1",
                "Class: B",
                "Scope: PUBLIC")
            .hidden(Lab.typing("172.15.9.9"),
                "IP address: 172.15.9.9",
                "Class: B",
                "Scope: PUBLIC")
            .hidden(Lab.typing("192.168.1.20"),
                "IP address: 192.168.1.20",
                "Class: C",
                "Scope: PRIVATE")
            .hidden(Lab.typing("8.8.8.8"),
                "IP address: 8.8.8.8",
                "Class: A",
                "Scope: PUBLIC")
            .hidden(Lab.typing("127.0.0.1"),
                "IP address: 127.0.0.1",
                "Class: A",
                "Scope: LOOPBACK")
            .hidden(Lab.typing("224.0.0.251"),
                "IP address: 224.0.0.251",
                "Class: D",
                "Scope: MULTICAST")
            .hidden(Lab.typing("250.1.1.1"),
                "IP address: 250.1.1.1",
                "Class: E",
                "Scope: RESERVED")
            .hidden(Lab.typing("0.0.0.0"),
                "IP address: 0.0.0.0",
                "Class: A",
                "Scope: RESERVED")
            .hidden(Lab.typing("256.1.1.1"),
                "IP address: 256.1.1.1",
                "INVALID ADDRESS")
            .hidden(Lab.typing("10"),
                "IP address: 10",
                "INVALID ADDRESS")
            .hidden(Lab.typing("web.01.1.1"),
                "IP address: web.01.1.1",
                "INVALID ADDRESS")
            .hidden(Lab.typing("99999999999.1.1.1"),
                "IP address: 99999999999.1.1.1",
                "INVALID ADDRESS"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(23), "HTTP Status Explainer", Lab.MEDIUM)
            .stretch()
            .after("C02-M025")
            .brief(
                "Web logs are full of three-digit status codes. New analysts "
                + "keep asking what they mean and which ones should worry "
                + "them. Build the explainer the team can paste a code into.")
            .practises("Switch expressions", "Integer division as a key", "Optional output")
            .spec(
                "Prompt Status code: and read the line, trimmed. It must be exactly 3 digits and 100-599, otherwise print INVALID STATUS.",
                "Meaning: 200 OK, 201 Created, 301 Moved Permanently, 302 Found, 304 Not Modified, 400 Bad Request, 401 Unauthorized, 403 Forbidden, 404 Not Found, 429 Too Many Requests, 500 Internal Server Error, 503 Service Unavailable; any other code (not on file).",
                "Family by the first digit: 1 Informational, 2 Success, 3 Redirect, 4 Client error, 5 Server error.",
                "Print Status: <code> <meaning> and Family: <family>. Then a note for some codes: 401 and 403 give Note: repeated hits can mean someone is probing, 429 gives Note: rate limit hit - possible brute force, 500-599 give Note: server fault - check the error logs. Other codes print no note line.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Status code: \");",
                "        String text = input.nextLine().trim();",
                "        // validate, then look up the meaning and family",
                "    }",
                "}")
            .hints(
                "Validate in two steps: the shape (length 3 and all digits), "
                + "then the range after parseInt. Using 0 when the shape is "
                + "wrong lets one range check catch both.",
                "code / 100 is the first digit - integer division throws the "
                + "rest away. Switch on that for the family.",
                "The meaning is a switch expression on the whole code, with "
                + "default -> \"(not on file)\".",
                "Set String note = \"\" first, fill it in an if / else if "
                + "chain, and print it only when !note.isEmpty().")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Status code: \");",
                "        String text = input.nextLine().trim();",
                "        boolean shape = text.length() == 3 && text.matches(\"[0-9]+\");",
                "        int code = shape ? Integer.parseInt(text) : 0;",
                "        if (code < 100 || code > 599) {",
                "            System.out.println(\"INVALID STATUS\");",
                "        } else {",
                "            String meaning = switch (code) {",
                "                case 200 -> \"OK\";",
                "                case 201 -> \"Created\";",
                "                case 301 -> \"Moved Permanently\";",
                "                case 302 -> \"Found\";",
                "                case 304 -> \"Not Modified\";",
                "                case 400 -> \"Bad Request\";",
                "                case 401 -> \"Unauthorized\";",
                "                case 403 -> \"Forbidden\";",
                "                case 404 -> \"Not Found\";",
                "                case 429 -> \"Too Many Requests\";",
                "                case 500 -> \"Internal Server Error\";",
                "                case 503 -> \"Service Unavailable\";",
                "                default -> \"(not on file)\";",
                "            };",
                "            String family = switch (code / 100) {",
                "                case 1 -> \"Informational\";",
                "                case 2 -> \"Success\";",
                "                case 3 -> \"Redirect\";",
                "                case 4 -> \"Client error\";",
                "                default -> \"Server error\";",
                "            };",
                "            String note = \"\";",
                "            if (code == 401 || code == 403) {",
                "                note = \"repeated hits can mean someone is probing\";",
                "            } else if (code == 429) {",
                "                note = \"rate limit hit - possible brute force\";",
                "            } else if (code >= 500) {",
                "                note = \"server fault - check the error logs\";",
                "            }",
                "            System.out.println(\"Status: \" + code + \" \" + meaning);",
                "            System.out.println(\"Family: \" + family);",
                "            if (!note.isEmpty()) {",
                "                System.out.println(\"Note: \" + note);",
                "            }",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "Using 0 for a bad shape is a small trick with a big payoff: "
                + "0 is outside 100-599, so a single range check reports both "
                + "\"4O4\" and 600 as INVALID STATUS, and parseInt never sees "
                + "text it cannot read.\n"
                + "\n"
                + "The two switches are lookup tables. The family switch can "
                + "use default for 5 because validation has already "
                + "guaranteed the first digit is 1 to 5 - the default is the "
                + "only case left.\n"
                + "\n"
                + "The note shows a common pattern for output that only "
                + "sometimes appears: build it in a variable, then decide "
                + "once whether to print it. In real monitoring, 401, 403 "
                + "and 429 on their own mean little; hundreds from one "
                + "address in a minute is how brute force and scanning show "
                + "up in web logs.")
            .sample(Lab.typing("403"),
                "Status code: 403",
                "Status: 403 Forbidden",
                "Family: Client error",
                "Note: repeated hits can mean someone is probing")
            .hidden(Lab.typing("200"),
                "Status code: 200",
                "Status: 200 OK",
                "Family: Success")
            .hidden(Lab.typing("404"),
                "Status code: 404",
                "Status: 404 Not Found",
                "Family: Client error")
            .hidden(Lab.typing("418"),
                "Status code: 418",
                "Status: 418 (not on file)",
                "Family: Client error")
            .hidden(Lab.typing("429"),
                "Status code: 429",
                "Status: 429 Too Many Requests",
                "Family: Client error",
                "Note: rate limit hit - possible brute force")
            .hidden(Lab.typing("503"),
                "Status code: 503",
                "Status: 503 Service Unavailable",
                "Family: Server error",
                "Note: server fault - check the error logs")
            .hidden(Lab.typing("302"),
                "Status code: 302",
                "Status: 302 Found",
                "Family: Redirect")
            .hidden(Lab.typing("101"),
                "Status code: 101",
                "Status: 101 (not on file)",
                "Family: Informational")
            .hidden(Lab.typing("99"),
                "Status code: 99",
                "INVALID STATUS")
            .hidden(Lab.typing("600"),
                "Status code: 600",
                "INVALID STATUS")
            .hidden(Lab.typing("4O4"),
                "Status code: 4O4",
                "INVALID STATUS"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(24), "Discount Abuse Check", Lab.MEDIUM)
            .stretch()
            .after("C02-M027")
            .brief(
                "NORTHSTAR's online shop is losing money to people who open "
                + "fresh accounts to reuse welcome codes. Fraud has written "
                + "the rules. Apply them in order and give one clear result "
                + "per checkout.")
            .practises("Switch on text", "Ordered rule chains", "String.format")
            .spec(
                "Prompt and read, in order: Discount code:, Previous orders:, Account age (days):, Basket total:. The code is trimmed and not case-sensitive; the numbers are whole except the basket, which is a decimal.",
                "Codes: WELCOME10 is 10%, STUDENT15 is 15%, SPRING20 is 20%. Any other code: REJECTED: unknown code.",
                "Then, first match wins: WELCOME10 with any previous orders: REJECTED: WELCOME10 is for first orders only. STUDENT15 on an account under 7 days old: REJECTED: account too new for STUDENT15. SPRING20 with a basket under 50: REJECTED: SPRING20 needs a basket of 50.00+.",
                "Any code on a 0-day-old account with a basket of 500 or more: HELD FOR REVIEW: new account, large basket.",
                "Otherwise: ACCEPTED: <percent>% off, pay <amount> with the amount to 2 decimal places.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Discount code: \");",
                "        String code = input.nextLine().trim().toUpperCase();",
                "        // read the other three values, then apply the rules",
                "    }",
                "}")
            .hints(
                "Turn the code into a percentage with a switch expression, "
                + "using default -> 0. A percentage of 0 then means \"unknown "
                + "code\".",
                "Each rule is one else if that tests the code AND its "
                + "condition, for example:\n"
                + "\n"
                + "    code.equals(\"WELCOME10\") && orders > 0",
                "Build the message in a String result inside the chain and "
                + "print it once at the end - it keeps every line short.",
                "The amount to pay is total - total * percent / 100. Format it "
                + "with String.format(\"%.2f\", pay).")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Discount code: \");",
                "        String code = input.nextLine().trim().toUpperCase();",
                "        System.out.print(\"Previous orders: \");",
                "        int orders = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Account age (days): \");",
                "        int age = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Basket total: \");",
                "        double total = Double.parseDouble(input.nextLine().trim());",
                "        int percent = switch (code) {",
                "            case \"WELCOME10\" -> 10;",
                "            case \"STUDENT15\" -> 15;",
                "            case \"SPRING20\" -> 20;",
                "            default -> 0;",
                "        };",
                "        String result;",
                "        if (percent == 0) {",
                "            result = \"REJECTED: unknown code\";",
                "        } else if (code.equals(\"WELCOME10\") && orders > 0) {",
                "            result = \"REJECTED: WELCOME10 is for first orders only\";",
                "        } else if (code.equals(\"STUDENT15\") && age < 7) {",
                "            result = \"REJECTED: account too new for STUDENT15\";",
                "        } else if (code.equals(\"SPRING20\") && total < 50) {",
                "            result = \"REJECTED: SPRING20 needs a basket of 50.00+\";",
                "        } else if (age == 0 && total >= 500) {",
                "            result = \"HELD FOR REVIEW: new account, large basket\";",
                "        } else {",
                "            double pay = total - total * percent / 100;",
                "            result = \"ACCEPTED: \" + percent + \"% off, pay \"",
                "                    + String.format(\"%.2f\", pay);",
                "        }",
                "        System.out.println(result);",
                "    }",
                "}")
            .walkthrough(
                "Upper-casing the code as it is read means every later "
                + "comparison can be an exact .equals, and the switch can map "
                + "all three codes in one table. Folding \"unknown\" into a "
                + "percentage of 0 saves a separate lookup.\n"
                + "\n"
                + "Order is the whole lab. The held-for-review rule is last "
                + "on purpose: a fraudster's WELCOME10 on a second order is "
                + "simply rejected, and review is only for checkouts that "
                + "would otherwise go through. Move it to the top and a real "
                + "rejection turns into a manual review job.\n"
                + "\n"
                + "total * percent / 100 is safe from integer division "
                + "because total is a double, so the whole expression is "
                + "worked out in doubles. The tests include 49.99 and 50 for "
                + "SPRING20 and 500 exactly for the review rule.")
            .sample(Lab.typing("WELCOME10", "0", "3", "100"),
                "Discount code: WELCOME10",
                "Previous orders: 0",
                "Account age (days): 3",
                "Basket total: 100",
                "ACCEPTED: 10% off, pay 90.00")
            .hidden(Lab.typing("welcome10", "2", "400", "80"),
                "Discount code: welcome10",
                "Previous orders: 2",
                "Account age (days): 400",
                "Basket total: 80",
                "REJECTED: WELCOME10 is for first orders only")
            .hidden(Lab.typing("STUDENT15", "4", "3", "80"),
                "Discount code: STUDENT15",
                "Previous orders: 4",
                "Account age (days): 3",
                "Basket total: 80",
                "REJECTED: account too new for STUDENT15")
            .hidden(Lab.typing("Student15", "4", "30", "80"),
                "Discount code: Student15",
                "Previous orders: 4",
                "Account age (days): 30",
                "Basket total: 80",
                "ACCEPTED: 15% off, pay 68.00")
            .hidden(Lab.typing("SPRING20", "1", "90", "49.99"),
                "Discount code: SPRING20",
                "Previous orders: 1",
                "Account age (days): 90",
                "Basket total: 49.99",
                "REJECTED: SPRING20 needs a basket of 50.00+")
            .hidden(Lab.typing("SPRING20", "1", "90", "50"),
                "Discount code: SPRING20",
                "Previous orders: 1",
                "Account age (days): 90",
                "Basket total: 50",
                "ACCEPTED: 20% off, pay 40.00")
            .hidden(Lab.typing("FREEBIE", "0", "0", "20"),
                "Discount code: FREEBIE",
                "Previous orders: 0",
                "Account age (days): 0",
                "Basket total: 20",
                "REJECTED: unknown code")
            .hidden(Lab.typing("WELCOME10", "0", "0", "500"),
                "Discount code: WELCOME10",
                "Previous orders: 0",
                "Account age (days): 0",
                "Basket total: 500",
                "HELD FOR REVIEW: new account, large basket")
            .hidden(Lab.typing(" spring20 ", "0", "0", "499.99"),
                "Discount code:  spring20",
                "Previous orders: 0",
                "Account age (days): 0",
                "Basket total: 499.99",
                "ACCEPTED: 20% off, pay 399.99"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(25), "Triage Router", Lab.BIG)
            .stretch()
            .after("C02-M027")
            .brief(
                "Alerts arrive at the SOC faster than people can read them. "
                + "Build the router that decides, for each alert, which team "
                + "queue gets it, how urgent it is, how fast someone must "
                + "respond, and whether the on-call analyst's phone rings.")
            .practises("Validation before logic", "Scores and overrides", "Switch lookup tables", "Aligned report output")
            .spec(
                "Prompt and read, in order: Alert type:, Severity (1-5):, Asset tier (1-3):, After hours (y/n):. Trim everything; the type is not case-sensitive.",
                "Severity must be a single digit 1-5, otherwise print INVALID: severity must be 1-5 and stop. Then the tier must be a single digit 1-3, otherwise INVALID: tier must be 1-3.",
                "Queue by type: malware or ransomware ENDPOINT, phishing EMAIL, bruteforce IDENTITY, dataleak DLP, anything else GENERAL.",
                "Score = severity, plus 2 for tier 1 or 1 for tier 2. Priority: score 6+ is 1, 5 is 2, 3-4 is 3, below 3 is 4. Then two overrides: ransomware is always priority 1, and dataleak is never lower than priority 2.",
                "SLA: priority 1 15 minutes, 2 1 hour, 3 4 hours, 4 next working day. Page: yes for priority 1, or priority 2 after hours; otherwise no.",
                "Print TRIAGE RESULT, then Queue:, Priority: (as P1-P4), SLA: and Page: with the values starting in column 11, as the sample shows. For a GENERAL alert, finish with Check:    unknown type - confirm by hand.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Alert type: \");",
                "        String type = input.nextLine().trim().toLowerCase();",
                "        // read severity, tier and after hours",
                "        // validate, then queue, score, priority, SLA, page",
                "    }",
                "}")
            .hints(
                "Read severity and tier as text. A single digit is "
                + "length() == 1 && matches(\"[0-9]+\"). Parse it only when "
                + "that is true, using 0 otherwise - then one range check "
                + "catches everything.",
                "Tier bonus with a nested ?:\n"
                + "\n"
                + "    int score = severity\n"
                + "            + (tier == 1 ? 2 : tier == 2 ? 1 : 0);",
                "Work out priority from the score first, THEN apply the "
                + "overrides as separate ifs underneath. For dataleak, "
                + "Math.min(priority, 2) keeps 1 as 1 and turns 3 or 4 into "
                + "2.",
                "Page is one boolean: priority == 1 || (priority == 2 && "
                + "afterHours).",
                "Labels are padded with spaces to 10 characters: "
                + "\"Queue:    \", \"Priority: \", \"SLA:      \", \"Page:     \".")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Alert type: \");",
                "        String type = input.nextLine().trim().toLowerCase();",
                "        System.out.print(\"Severity (1-5): \");",
                "        String sevText = input.nextLine().trim();",
                "        System.out.print(\"Asset tier (1-3): \");",
                "        String tierText = input.nextLine().trim();",
                "        System.out.print(\"After hours (y/n): \");",
                "        boolean afterHours = input.nextLine().trim()",
                "                .equalsIgnoreCase(\"y\");",
                "        boolean sevOk = sevText.length() == 1",
                "                && sevText.matches(\"[0-9]+\");",
                "        boolean tierOk = tierText.length() == 1",
                "                && tierText.matches(\"[0-9]+\");",
                "        int severity = sevOk ? Integer.parseInt(sevText) : 0;",
                "        int tier = tierOk ? Integer.parseInt(tierText) : 0;",
                "        if (severity < 1 || severity > 5) {",
                "            System.out.println(\"INVALID: severity must be 1-5\");",
                "        } else if (tier < 1 || tier > 3) {",
                "            System.out.println(\"INVALID: tier must be 1-3\");",
                "        } else {",
                "            String queue = switch (type) {",
                "                case \"malware\", \"ransomware\" -> \"ENDPOINT\";",
                "                case \"phishing\" -> \"EMAIL\";",
                "                case \"bruteforce\" -> \"IDENTITY\";",
                "                case \"dataleak\" -> \"DLP\";",
                "                default -> \"GENERAL\";",
                "            };",
                "            int score = severity",
                "                    + (tier == 1 ? 2 : tier == 2 ? 1 : 0);",
                "            int priority;",
                "            if (score >= 6) {",
                "                priority = 1;",
                "            } else if (score == 5) {",
                "                priority = 2;",
                "            } else if (score >= 3) {",
                "                priority = 3;",
                "            } else {",
                "                priority = 4;",
                "            }",
                "            if (type.equals(\"ransomware\")) {",
                "                priority = 1;",
                "            }",
                "            if (type.equals(\"dataleak\")) {",
                "                priority = Math.min(priority, 2);",
                "            }",
                "            String sla = switch (priority) {",
                "                case 1 -> \"15 minutes\";",
                "                case 2 -> \"1 hour\";",
                "                case 3 -> \"4 hours\";",
                "                default -> \"next working day\";",
                "            };",
                "            boolean page = priority == 1",
                "                    || (priority == 2 && afterHours);",
                "            System.out.println(\"TRIAGE RESULT\");",
                "            System.out.println(\"Queue:    \" + queue);",
                "            System.out.println(\"Priority: P\" + priority);",
                "            System.out.println(\"SLA:      \" + sla);",
                "            System.out.println(\"Page:     \" + (page ? \"yes\" : \"no\"));",
                "            if (queue.equals(\"GENERAL\")) {",
                "                System.out.println(",
                "                        \"Check:    unknown type - confirm by hand\");",
                "            }",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The program has three layers, and keeping them apart is what "
                + "makes it readable. Layer one reads everything as text. "
                + "Layer two validates - nothing is parsed until its shape is "
                + "known, and bad input stops the program with a message "
                + "that says which field was wrong. Layer three is the "
                + "decision, and it can trust every value it touches.\n"
                + "\n"
                + "Priority is worked out in two steps on purpose: a general "
                + "rule (the score), then specific overrides after it. Each "
                + "override is a plain if, not an else if, because it must "
                + "run whatever the chain above decided. That is how real "
                + "triage policy is written: \"ransomware is always P1\" is "
                + "a rule on top of the scoring, not part of it.\n"
                + "\n"
                + "Math.min(priority, 2) says \"no lower than P2\" without an "
                + "if - a smaller number is a HIGHER priority, which is "
                + "exactly the kind of detail the hidden tests check "
                + "(dataleak at severity 1 must still become P2).")
            .sample(Lab.typing("malware", "4", "1", "n"),
                "Alert type: malware",
                "Severity (1-5): 4",
                "Asset tier (1-3): 1",
                "After hours (y/n): n",
                "TRIAGE RESULT",
                "Queue:    ENDPOINT",
                "Priority: P1",
                "SLA:      15 minutes",
                "Page:     yes")
            .hidden(Lab.typing("phishing", "2", "3", "y"),
                "Alert type: phishing",
                "Severity (1-5): 2",
                "Asset tier (1-3): 3",
                "After hours (y/n): y",
                "TRIAGE RESULT",
                "Queue:    EMAIL",
                "Priority: P4",
                "SLA:      next working day",
                "Page:     no")
            .hidden(Lab.typing("DataLeak", "1", "3", "n"),
                "Alert type: DataLeak",
                "Severity (1-5): 1",
                "Asset tier (1-3): 3",
                "After hours (y/n): n",
                "TRIAGE RESULT",
                "Queue:    DLP",
                "Priority: P2",
                "SLA:      1 hour",
                "Page:     no")
            .hidden(Lab.typing("dataleak", "1", "3", "y"),
                "Alert type: dataleak",
                "Severity (1-5): 1",
                "Asset tier (1-3): 3",
                "After hours (y/n): y",
                "TRIAGE RESULT",
                "Queue:    DLP",
                "Priority: P2",
                "SLA:      1 hour",
                "Page:     yes")
            .hidden(Lab.typing("ransomware", "1", "3", "n"),
                "Alert type: ransomware",
                "Severity (1-5): 1",
                "Asset tier (1-3): 3",
                "After hours (y/n): n",
                "TRIAGE RESULT",
                "Queue:    ENDPOINT",
                "Priority: P1",
                "SLA:      15 minutes",
                "Page:     yes")
            .hidden(Lab.typing("BruteForce", "3", "2", "y"),
                "Alert type: BruteForce",
                "Severity (1-5): 3",
                "Asset tier (1-3): 2",
                "After hours (y/n): y",
                "TRIAGE RESULT",
                "Queue:    IDENTITY",
                "Priority: P3",
                "SLA:      4 hours",
                "Page:     no")
            .hidden(Lab.typing("usb-insert", "5", "3", "n"),
                "Alert type: usb-insert",
                "Severity (1-5): 5",
                "Asset tier (1-3): 3",
                "After hours (y/n): n",
                "TRIAGE RESULT",
                "Queue:    GENERAL",
                "Priority: P2",
                "SLA:      1 hour",
                "Page:     no",
                "Check:    unknown type - confirm by hand")
            .hidden(Lab.typing("phishing", "4", "2", "y"),
                "Alert type: phishing",
                "Severity (1-5): 4",
                "Asset tier (1-3): 2",
                "After hours (y/n): y",
                "TRIAGE RESULT",
                "Queue:    EMAIL",
                "Priority: P2",
                "SLA:      1 hour",
                "Page:     yes")
            .hidden(Lab.typing("malware", "6", "1", "n"),
                "Alert type: malware",
                "Severity (1-5): 6",
                "Asset tier (1-3): 1",
                "After hours (y/n): n",
                "INVALID: severity must be 1-5")
            .hidden(Lab.typing("malware", "high", "1", "n"),
                "Alert type: malware",
                "Severity (1-5): high",
                "Asset tier (1-3): 1",
                "After hours (y/n): n",
                "INVALID: severity must be 1-5")
            .hidden(Lab.typing("malware", "3", "0", "n"),
                "Alert type: malware",
                "Severity (1-5): 3",
                "Asset tier (1-3): 0",
                "After hours (y/n): n",
                "INVALID: tier must be 1-3")
            .hidden(Lab.typing("malware", "", "1", "n"),
                "Alert type: malware",
                "Severity (1-5):",
                "Asset tier (1-3): 1",
                "After hours (y/n): n",
                "INVALID: severity must be 1-5"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(26), "Access Matrix", Lab.BIG)
            .stretch()
            .after("C02-M027")
            .brief(
                "Every request inside NORTHSTAR is a role asking to do an "
                + "action to a resource. Security has drawn up the access "
                + "matrix. Turn it into a program that answers ALLOWED or "
                + "DENIED - and denies anything it does not recognise.")
            .practises("Fail-closed validation", "Global rules before role rules", "Switch expressions giving booleans")
            .spec(
                "Prompt and read Role:, Resource:, Action:. Trim each and make it lower case.",
                "Known roles: admin, analyst, auditor, contractor, guest. Known resources: logs, config, payroll, tickets. Known actions: read, write, delete. Check them in that order; the first unknown one prints DENIED: unknown role <role> (or resource, or action) and nothing else.",
                "Global rule before any role: logs can only be read. Writing or deleting logs prints DENIED: logs are read-only for every role - even for admin.",
                "Then by role. admin: anything. analyst: read anything except payroll, and write tickets. auditor: read anything. contractor: read or write tickets. guest: nothing.",
                "Print ALLOWED: <role> may <action> <resource> or DENIED: <role> may not <action> <resource>. After a DENIED on payroll (from the role rules), also print ALERT: payroll request logged.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Role: \");",
                "        String role = input.nextLine().trim().toLowerCase();",
                "        // read resource and action",
                "        // unknowns first, then the logs rule, then the roles",
                "    }",
                "}")
            .hints(
                "\"Is it known?\" is a switch expression that gives a boolean. "
                + "A case can list several values, and the list can go over "
                + "two lines:\n"
                + "\n"
                + "    case \"admin\", \"analyst\", \"auditor\",\n"
                + "         \"contractor\", \"guest\" -> true;",
                "The outer shape is one chain: unknown role, else if unknown "
                + "resource, else if unknown action, else if the logs rule, "
                + "else the role decision.",
                "Name the small facts first to keep the role rules short:\n"
                + "\n"
                + "    boolean reading = action.equals(\"read\");\n"
                + "    boolean tickets = resource.equals(\"tickets\");",
                "The analyst rule, with brackets so nobody has to remember "
                + "that && binds tighter than ||:\n"
                + "\n"
                + "    (reading && !resource.equals(\"payroll\"))\n"
                + "        || (tickets && action.equals(\"write\"))",
                "Anything not listed for a role is denied, so the role switch "
                + "ends in default -> false. That line is guest's rule.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Role: \");",
                "        String role = input.nextLine().trim().toLowerCase();",
                "        System.out.print(\"Resource: \");",
                "        String resource = input.nextLine().trim().toLowerCase();",
                "        System.out.print(\"Action: \");",
                "        String action = input.nextLine().trim().toLowerCase();",
                "        boolean roleOk = switch (role) {",
                "            case \"admin\", \"analyst\", \"auditor\",",
                "                 \"contractor\", \"guest\" -> true;",
                "            default -> false;",
                "        };",
                "        boolean resourceOk = switch (resource) {",
                "            case \"logs\", \"config\", \"payroll\", \"tickets\" -> true;",
                "            default -> false;",
                "        };",
                "        boolean actionOk = switch (action) {",
                "            case \"read\", \"write\", \"delete\" -> true;",
                "            default -> false;",
                "        };",
                "        boolean reading = action.equals(\"read\");",
                "        boolean tickets = resource.equals(\"tickets\");",
                "        if (!roleOk) {",
                "            System.out.println(\"DENIED: unknown role \" + role);",
                "        } else if (!resourceOk) {",
                "            System.out.println(",
                "                    \"DENIED: unknown resource \" + resource);",
                "        } else if (!actionOk) {",
                "            System.out.println(\"DENIED: unknown action \" + action);",
                "        } else if (resource.equals(\"logs\") && !reading) {",
                "            System.out.println(",
                "                    \"DENIED: logs are read-only for every role\");",
                "        } else {",
                "            boolean allowed = switch (role) {",
                "                case \"admin\" -> true;",
                "                case \"analyst\" ->",
                "                    (reading && !resource.equals(\"payroll\"))",
                "                        || (tickets && action.equals(\"write\"));",
                "                case \"auditor\" -> reading;",
                "                case \"contractor\" ->",
                "                    tickets && !action.equals(\"delete\");",
                "                default -> false;",
                "            };",
                "            String request = action + \" \" + resource;",
                "            if (allowed) {",
                "                System.out.println(",
                "                        \"ALLOWED: \" + role + \" may \" + request);",
                "            } else {",
                "                System.out.println(",
                "                        \"DENIED: \" + role + \" may not \" + request);",
                "                if (resource.equals(\"payroll\")) {",
                "                    System.out.println(",
                "                            \"ALERT: payroll request logged\");",
                "                }",
                "            }",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "This is fail-closed access control, and the program shows "
                + "it in two places. Anything unrecognised is denied before "
                + "any rule runs, so a typo like \"admn\" can never slip "
                + "through a gap. And the role switch ends in default -> "
                + "false: a role that has no line in the matrix gets "
                + "nothing, which is why guest needs no case of its own.\n"
                + "\n"
                + "The logs rule sits BEFORE the role switch because it "
                + "overrides every role, admin included. Audit logs that an "
                + "administrator can edit are worthless as evidence - the "
                + "first thing an intruder with admin rights would do is "
                + "delete their tracks.\n"
                + "\n"
                + "The named booleans (reading, tickets) turn the matrix into "
                + "lines that read almost like the policy. Note that the "
                + "payroll alert only fires for role-rule denials: a request "
                + "to delete logs is denied by the global rule and never "
                + "reaches it.")
            .sample(Lab.typing("analyst", "tickets", "write"),
                "Role: analyst",
                "Resource: tickets",
                "Action: write",
                "ALLOWED: analyst may write tickets")
            .hidden(Lab.typing("admin", "logs", "delete"),
                "Role: admin",
                "Resource: logs",
                "Action: delete",
                "DENIED: logs are read-only for every role")
            .hidden(Lab.typing("admin", "payroll", "write"),
                "Role: admin",
                "Resource: payroll",
                "Action: write",
                "ALLOWED: admin may write payroll")
            .hidden(Lab.typing("analyst", "payroll", "read"),
                "Role: analyst",
                "Resource: payroll",
                "Action: read",
                "DENIED: analyst may not read payroll",
                "ALERT: payroll request logged")
            .hidden(Lab.typing("analyst", "config", "read"),
                "Role: analyst",
                "Resource: config",
                "Action: read",
                "ALLOWED: analyst may read config")
            .hidden(Lab.typing("analyst", "config", "write"),
                "Role: analyst",
                "Resource: config",
                "Action: write",
                "DENIED: analyst may not write config")
            .hidden(Lab.typing("auditor", "payroll", "read"),
                "Role: auditor",
                "Resource: payroll",
                "Action: read",
                "ALLOWED: auditor may read payroll")
            .hidden(Lab.typing("auditor", "tickets", "write"),
                "Role: auditor",
                "Resource: tickets",
                "Action: write",
                "DENIED: auditor may not write tickets")
            .hidden(Lab.typing("contractor", "tickets", "write"),
                "Role: contractor",
                "Resource: tickets",
                "Action: write",
                "ALLOWED: contractor may write tickets")
            .hidden(Lab.typing("contractor", "tickets", "delete"),
                "Role: contractor",
                "Resource: tickets",
                "Action: delete",
                "DENIED: contractor may not delete tickets")
            .hidden(Lab.typing("GUEST", "tickets", "read"),
                "Role: GUEST",
                "Resource: tickets",
                "Action: read",
                "DENIED: guest may not read tickets")
            .hidden(Lab.typing("guest", "payroll", "read"),
                "Role: guest",
                "Resource: payroll",
                "Action: read",
                "DENIED: guest may not read payroll",
                "ALERT: payroll request logged")
            .hidden(Lab.typing("manager", "tickets", "read"),
                "Role: manager",
                "Resource: tickets",
                "Action: read",
                "DENIED: unknown role manager")
            .hidden(Lab.typing("analyst", "backups", "read"),
                "Role: analyst",
                "Resource: backups",
                "Action: read",
                "DENIED: unknown resource backups")
            .hidden(Lab.typing("analyst", "tickets", "execute"),
                "Role: analyst",
                "Resource: tickets",
                "Action: execute",
                "DENIED: unknown action execute")
            .hidden(Lab.typing(" Analyst ", "TICKETS", "Write"),
                "Role:  Analyst",
                "Resource: TICKETS",
                "Action: Write",
                "ALLOWED: analyst may write tickets"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(27), "Alert Deduplicator", Lab.BIG)
            .stretch()
            .after("C02-M018")
            .brief(
                "The same alert fired three times is one problem, not three. "
                + "Analysts are drowning in repeats. Take three alerts, "
                + "decide which are new and which repeat an earlier one, and "
                + "throw out the ones that are not alerts at all.")
            .practises("Normalising text before comparing", "Guarding with booleans", "Counting with ?:")
            .spec(
                "Prompt Alert 1:, Alert 2:, Alert 3: and read each line. An alert is host|rule.",
                "An alert is MALFORMED if it has no | or if the host or the rule is blank.",
                "Two alerts are the same if host and rule match once each is trimmed and lower-cased. Other differences, such as a double space inside the rule, make them different.",
                "Print Alert <n>: then NEW, MALFORMED, or DUPLICATE of alert <m>, where m is the EARLIEST matching alert.",
                "Finish with Summary: <a> new, <b> suppressed, <c> malformed.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Alert 1: \");",
                "        String a1 = input.nextLine();",
                "        System.out.print(\"Alert 2: \");",
                "        String a2 = input.nextLine();",
                "        System.out.print(\"Alert 3: \");",
                "        String a3 = input.nextLine();",
                "        // for each alert: is it well formed? what is its key?",
                "    }",
                "}")
            .hints(
                "For each alert, find the bar and decide ok1, ok2, ok3. "
                + "bar > 0 means there is a bar and something in front of "
                + "it; then check neither side isBlank().",
                "Build a KEY for each good alert - the form that comparisons "
                + "use: the trimmed, lower-cased host, a |, then the trimmed, "
                + "lower-cased rule. Use ?: so a malformed alert gets the key "
                + "\"\" instead:\n"
                + "\n"
                + "    String key1 = ok1 ? ... : \"\";",
                "Alert 3 checks alert 1 BEFORE alert 2, so a repeat of a "
                + "repeat is reported against the earliest one.",
                "Store each status as a String, then count with ?: - for "
                + "example (s1.equals(\"NEW\") ? 1 : 0) + ...",
                "Suppressed means the status startsWith(\"DUPLICATE\").")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Alert 1: \");",
                "        String a1 = input.nextLine();",
                "        System.out.print(\"Alert 2: \");",
                "        String a2 = input.nextLine();",
                "        System.out.print(\"Alert 3: \");",
                "        String a3 = input.nextLine();",
                "",
                "        int bar1 = a1.indexOf(\"|\");",
                "        boolean ok1 = bar1 > 0 && !a1.substring(0, bar1).isBlank()",
                "                && !a1.substring(bar1 + 1).isBlank();",
                "        String key1 = ok1",
                "                ? a1.substring(0, bar1).trim().toLowerCase() + \"|\"",
                "                  + a1.substring(bar1 + 1).trim().toLowerCase()",
                "                : \"\";",
                "        int bar2 = a2.indexOf(\"|\");",
                "        boolean ok2 = bar2 > 0 && !a2.substring(0, bar2).isBlank()",
                "                && !a2.substring(bar2 + 1).isBlank();",
                "        String key2 = ok2",
                "                ? a2.substring(0, bar2).trim().toLowerCase() + \"|\"",
                "                  + a2.substring(bar2 + 1).trim().toLowerCase()",
                "                : \"\";",
                "        int bar3 = a3.indexOf(\"|\");",
                "        boolean ok3 = bar3 > 0 && !a3.substring(0, bar3).isBlank()",
                "                && !a3.substring(bar3 + 1).isBlank();",
                "        String key3 = ok3",
                "                ? a3.substring(0, bar3).trim().toLowerCase() + \"|\"",
                "                  + a3.substring(bar3 + 1).trim().toLowerCase()",
                "                : \"\";",
                "",
                "        String s1 = ok1 ? \"NEW\" : \"MALFORMED\";",
                "        String s2;",
                "        if (!ok2) {",
                "            s2 = \"MALFORMED\";",
                "        } else if (ok1 && key2.equals(key1)) {",
                "            s2 = \"DUPLICATE of alert 1\";",
                "        } else {",
                "            s2 = \"NEW\";",
                "        }",
                "        String s3;",
                "        if (!ok3) {",
                "            s3 = \"MALFORMED\";",
                "        } else if (ok1 && key3.equals(key1)) {",
                "            s3 = \"DUPLICATE of alert 1\";",
                "        } else if (ok2 && key3.equals(key2)) {",
                "            s3 = \"DUPLICATE of alert 2\";",
                "        } else {",
                "            s3 = \"NEW\";",
                "        }",
                "",
                "        int fresh = (s1.equals(\"NEW\") ? 1 : 0)",
                "                + (s2.equals(\"NEW\") ? 1 : 0)",
                "                + (s3.equals(\"NEW\") ? 1 : 0);",
                "        int suppressed = (s2.startsWith(\"DUPLICATE\") ? 1 : 0)",
                "                + (s3.startsWith(\"DUPLICATE\") ? 1 : 0);",
                "        int malformed = 3 - fresh - suppressed;",
                "        System.out.println(\"Alert 1: \" + s1);",
                "        System.out.println(\"Alert 2: \" + s2);",
                "        System.out.println(\"Alert 3: \" + s3);",
                "        System.out.println(\"Summary: \" + fresh + \" new, \"",
                "                + suppressed + \" suppressed, \" + malformed",
                "                + \" malformed\");",
                "    }",
                "}")
            .walkthrough(
                "Deduplication is really a question about KEYS: two alerts "
                + "are the same when their normalised forms are equal. "
                + "Building key1, key2 and key3 once, up front, means every "
                + "comparison afterwards is a plain .equals - no trimming or "
                + "case-folding scattered through the decisions.\n"
                + "\n"
                + "The ok booleans guard everything. bar > 0 makes both "
                + "substrings safe, and a malformed alert gets the key \"\". "
                + "The ok1 && ... checks are belt and braces: an empty key "
                + "could never equal a real one, but writing the guard says "
                + "plainly that a malformed alert is never compared.\n"
                + "\n"
                + "You probably noticed the same four lines written three "
                + "times. That is the honest cost of having only if and "
                + "switch. Campaign 03 turns those four lines into one method "
                + "you call three times, and Campaign 04 lets a loop handle "
                + "three alerts or three thousand. Keep this program: it is "
                + "a good one to rewrite later.")
            .sample(Lab.typing("web-01|SSH brute force", "WEB-01 | ssh brute force", "db-01|SSH brute force"),
                "Alert 1: web-01|SSH brute force",
                "Alert 2: WEB-01 | ssh brute force",
                "Alert 3: db-01|SSH brute force",
                "Alert 1: NEW",
                "Alert 2: DUPLICATE of alert 1",
                "Alert 3: NEW",
                "Summary: 2 new, 1 suppressed, 0 malformed")
            .hidden(Lab.typing("fw-02|port scan", "fw-02|port scan", "FW-02|PORT SCAN"),
                "Alert 1: fw-02|port scan",
                "Alert 2: fw-02|port scan",
                "Alert 3: FW-02|PORT SCAN",
                "Alert 1: NEW",
                "Alert 2: DUPLICATE of alert 1",
                "Alert 3: DUPLICATE of alert 1",
                "Summary: 1 new, 2 suppressed, 0 malformed")
            .hidden(Lab.typing("a|x", "b|x", "b|X"),
                "Alert 1: a|x",
                "Alert 2: b|x",
                "Alert 3: b|X",
                "Alert 1: NEW",
                "Alert 2: NEW",
                "Alert 3: DUPLICATE of alert 2",
                "Summary: 2 new, 1 suppressed, 0 malformed")
            .hidden(Lab.typing("no bar here", "|rule only", "host only|"),
                "Alert 1: no bar here",
                "Alert 2: |rule only",
                "Alert 3: host only|",
                "Alert 1: MALFORMED",
                "Alert 2: MALFORMED",
                "Alert 3: MALFORMED",
                "Summary: 0 new, 0 suppressed, 3 malformed")
            .hidden(Lab.typing("web-01|scan", "   ", "web-01|scan"),
                "Alert 1: web-01|scan",
                "Alert 2:",
                "Alert 3: web-01|scan",
                "Alert 1: NEW",
                "Alert 2: MALFORMED",
                "Alert 3: DUPLICATE of alert 1",
                "Summary: 1 new, 1 suppressed, 1 malformed")
            .hidden(Lab.typing("  |scan", "web-01|scan", " web-01 |scan "),
                "Alert 1:   |scan",
                "Alert 2: web-01|scan",
                "Alert 3:  web-01 |scan",
                "Alert 1: MALFORMED",
                "Alert 2: NEW",
                "Alert 3: DUPLICATE of alert 2",
                "Summary: 1 new, 1 suppressed, 1 malformed")
            .hidden(Lab.typing("web-01|port scan", "web-01|port  scan", "web-02|port scan"),
                "Alert 1: web-01|port scan",
                "Alert 2: web-01|port  scan",
                "Alert 3: web-02|port scan",
                "Alert 1: NEW",
                "Alert 2: NEW",
                "Alert 3: NEW",
                "Summary: 3 new, 0 suppressed, 0 malformed"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(28), "Rule Tester", Lab.BIG)
            .stretch()
            .after("C02-M025")
            .brief(
                "Detection engineers write rules like port >= 1024 or "
                + "country == RU and need to know whether a rule would fire "
                + "on an event before it goes live. Build the tester: read "
                + "one event and one rule, and say MATCH or NO MATCH - or "
                + "explain exactly why the rule is broken.")
            .practises("Parsing a line into parts", "Switch statements with ->", "Text and number operators", "Error messages that help")
            .spec(
                "Prompt and read User:, Port:, Country:, Rule:, each trimmed.",
                "If the port is not 1-5 digits, print EVENT ERROR: port must be a number and stop.",
                "A rule is <field> <operator> <value> with a space after the field and after the operator; the value may contain spaces. If either space is missing: RULE ERROR: use field operator value.",
                "Field port (not case-sensitive) takes a value of 1-5 digits, else RULE ERROR: port rules need a number, and operators == != > >= < <=. Any other operator: RULE ERROR: <op> does not work on numbers.",
                "Fields user and country compare without caring about case, with operators == != starts contains. Any other operator: RULE ERROR: <op> does not work on text. Any other field: RULE ERROR: unknown field <field>.",
                "Otherwise print MATCH - alert fires for <user> or NO MATCH.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"User: \");",
                "        String user = input.nextLine().trim();",
                "        // read port, country and rule",
                "        // split the rule into field, operator and value",
                "        // then evaluate it against the event",
                "    }",
                "}")
            .hints(
                "Split with two indexOf calls. After the first space, keep "
                + "the rest of the rule in its own String and look for the "
                + "next space in THAT:\n"
                + "\n"
                + "    int space1 = rule.indexOf(\" \");\n"
                + "    String rest = rule.substring(space1 + 1).trim();\n"
                + "    int space2 = rest.indexOf(\" \");",
                "Keep two variables, String error = \"\" and boolean match = "
                + "false. The evaluation sets one of them, and ONE if / else "
                + "chain at the end prints the result.",
                "The arrow switch works as a statement, one action per case:\n"
                + "\n"
                + "    switch (op) {\n"
                + "        case \">\" -> match = port > limit;\n"
                + "        default -> error = op + \" does not work on numbers\";\n"
                + "    }",
                "For text, lower-case both sides once, then == is .equals, "
                + "starts is startsWith and contains is contains.",
                "Pick the event value with ?: -\n"
                + "\n"
                + "    String actual = field.equals(\"user\") ? user : country;")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"User: \");",
                "        String user = input.nextLine().trim();",
                "        System.out.print(\"Port: \");",
                "        String portText = input.nextLine().trim();",
                "        System.out.print(\"Country: \");",
                "        String country = input.nextLine().trim();",
                "        System.out.print(\"Rule: \");",
                "        String rule = input.nextLine().trim();",
                "        int space1 = rule.indexOf(\" \");",
                "        String rest = rule.substring(space1 + 1).trim();",
                "        int space2 = rest.indexOf(\" \");",
                "        if (!portText.matches(\"[0-9]+\") || portText.length() > 5) {",
                "            System.out.println(\"EVENT ERROR: port must be a number\");",
                "        } else if (space1 == -1 || space2 == -1) {",
                "            System.out.println(",
                "                    \"RULE ERROR: use field operator value\");",
                "        } else {",
                "            int port = Integer.parseInt(portText);",
                "            String field = rule.substring(0, space1).toLowerCase();",
                "            String op = rest.substring(0, space2);",
                "            String value = rest.substring(space2 + 1).trim();",
                "            String error = \"\";",
                "            boolean match = false;",
                "            if (field.equals(\"port\")) {",
                "                if (!value.matches(\"[0-9]+\") || value.length() > 5) {",
                "                    error = \"port rules need a number\";",
                "                } else {",
                "                    int limit = Integer.parseInt(value);",
                "                    switch (op) {",
                "                        case \"==\" -> match = port == limit;",
                "                        case \"!=\" -> match = port != limit;",
                "                        case \">\" -> match = port > limit;",
                "                        case \">=\" -> match = port >= limit;",
                "                        case \"<\" -> match = port < limit;",
                "                        case \"<=\" -> match = port <= limit;",
                "                        default -> error = op",
                "                                + \" does not work on numbers\";",
                "                    }",
                "                }",
                "            } else if (field.equals(\"user\")",
                "                    || field.equals(\"country\")) {",
                "                String actual = field.equals(\"user\") ? user : country;",
                "                String a = actual.toLowerCase();",
                "                String v = value.toLowerCase();",
                "                switch (op) {",
                "                    case \"==\" -> match = a.equals(v);",
                "                    case \"!=\" -> match = !a.equals(v);",
                "                    case \"starts\" -> match = a.startsWith(v);",
                "                    case \"contains\" -> match = a.contains(v);",
                "                    default -> error = op + \" does not work on text\";",
                "                }",
                "            } else {",
                "                error = \"unknown field \" + field;",
                "            }",
                "            if (!error.isEmpty()) {",
                "                System.out.println(\"RULE ERROR: \" + error);",
                "            } else if (match) {",
                "                System.out.println(\"MATCH - alert fires for \" + user);",
                "            } else {",
                "                System.out.println(\"NO MATCH\");",
                "            }",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The program is a tiny interpreter: it reads a rule written "
                + "as text and works out what it means. That happens in "
                + "three steps - split the text, check each part makes sense, "
                + "then run it - and real detection engines (Sigma, Splunk, "
                + "firewall rule languages) are built the same way, only "
                + "bigger.\n"
                + "\n"
                + "Splitting uses rest because the second space must be found "
                + "AFTER the first. Taking the value as everything after the "
                + "second space lets it contain spaces, which is why country "
                + "== united kingdom works.\n"
                + "\n"
                + "The error / match pair is the key design choice. Each "
                + "branch only records what it found, and one chain at the "
                + "end decides what to print, so there is exactly one "
                + "println for each outcome. A rule tester that said NO MATCH "
                + "for a broken rule would be dangerous: the engineer would "
                + "think the rule was fine and the event was harmless.")
            .sample(Lab.typing("jsmith", "4444", "RU", "port >= 1024"),
                "User: jsmith",
                "Port: 4444",
                "Country: RU",
                "Rule: port >= 1024",
                "MATCH - alert fires for jsmith")
            .hidden(Lab.typing("jsmith", "4444", "RU", "country == ru"),
                "User: jsmith",
                "Port: 4444",
                "Country: RU",
                "Rule: country == ru",
                "MATCH - alert fires for jsmith")
            .hidden(Lab.typing("jsmith", "443", "GB", "country != GB"),
                "User: jsmith",
                "Port: 443",
                "Country: GB",
                "Rule: country != GB",
                "NO MATCH")
            .hidden(Lab.typing("svc-backup", "22", "GB", "user starts SVC-"),
                "User: svc-backup",
                "Port: 22",
                "Country: GB",
                "Rule: user starts SVC-",
                "MATCH - alert fires for svc-backup")
            .hidden(Lab.typing("jsmith", "22", "GB", "user contains admin"),
                "User: jsmith",
                "Port: 22",
                "Country: GB",
                "Rule: user contains admin",
                "NO MATCH")
            .hidden(Lab.typing("jsmith", "4444", "GB", "port < 1024"),
                "User: jsmith",
                "Port: 4444",
                "Country: GB",
                "Rule: port < 1024",
                "NO MATCH")
            .hidden(Lab.typing("jsmith", "22", "GB", "PORT == 22"),
                "User: jsmith",
                "Port: 22",
                "Country: GB",
                "Rule: PORT == 22",
                "MATCH - alert fires for jsmith")
            .hidden(Lab.typing("m.reyes", "443", "United Kingdom", "country == united kingdom"),
                "User: m.reyes",
                "Port: 443",
                "Country: United Kingdom",
                "Rule: country == united kingdom",
                "MATCH - alert fires for m.reyes")
            .hidden(Lab.typing("jsmith", "22", "GB", "port contains 2"),
                "User: jsmith",
                "Port: 22",
                "Country: GB",
                "Rule: port contains 2",
                "RULE ERROR: contains does not work on numbers")
            .hidden(Lab.typing("jsmith", "22", "GB", "user > a"),
                "User: jsmith",
                "Port: 22",
                "Country: GB",
                "Rule: user > a",
                "RULE ERROR: > does not work on text")
            .hidden(Lab.typing("jsmith", "22", "GB", "host == web-01"),
                "User: jsmith",
                "Port: 22",
                "Country: GB",
                "Rule: host == web-01",
                "RULE ERROR: unknown field host")
            .hidden(Lab.typing("jsmith", "22", "GB", "port >= high"),
                "User: jsmith",
                "Port: 22",
                "Country: GB",
                "Rule: port >= high",
                "RULE ERROR: port rules need a number")
            .hidden(Lab.typing("jsmith", "22", "GB", "port>=1024"),
                "User: jsmith",
                "Port: 22",
                "Country: GB",
                "Rule: port>=1024",
                "RULE ERROR: use field operator value")
            .hidden(Lab.typing("jsmith", "ssh", "GB", "port == 22"),
                "User: jsmith",
                "Port: ssh",
                "Country: GB",
                "Rule: port == 22",
                "EVENT ERROR: port must be a number"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(29), "Change Approval Gate", Lab.BIG)
            .stretch()
            .after("C02-M027")
            .brief(
                "Most outages - and plenty of breaches - start with a change "
                + "someone pushed without the right checks. NORTHSTAR's change "
                + "board has a policy. Your program is the gate every change "
                + "request has to pass.")
            .practises("Long ordered rule chains", "Adding up requirements", "Validating several fields")
            .spec(
                "Prompt and read, in order: Change type:, Risk:, System tier (1-3):, Approvals:, Freeze in effect (y/n):, Rollback plan (y/n):. Trim everything; type and risk are not case-sensitive.",
                "Check in this order and print the FIRST that applies: unknown type (standard, normal, emergency) BLOCKED: unknown change type; unknown risk (low, medium, high) BLOCKED: unknown risk level; tier not a single digit 1-3 BLOCKED: tier must be 1-3; approvals not 1-3 digits BLOCKED: approvals must be a number.",
                "Then: a standard change that is not low risk BLOCKED: a standard change must be low risk; a freeze and not emergency BLOCKED: change freeze in effect; no rollback plan and not low risk BLOCKED: no rollback plan.",
                "Approvals needed: standard 0, normal 1, emergency 1, plus 1 for high risk, plus 1 for tier 1. Too few: BLOCKED: needs <n> approvals, has <m>.",
                "Otherwise APPROVED, then NOTE: emergency change - review within 48 hours for an emergency, or NOTE: pre-approved standard change for a standard one.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Change type: \");",
                "        String type = input.nextLine().trim().toLowerCase();",
                "        // read the other five answers",
                "        // then one chain of checks, in the order given",
                "    }",
                "}")
            .hints(
                "Read tier and approvals as text, work out tierOk and "
                + "approvalsOk from their shape, and only parse them when the "
                + "shape is right (0 otherwise).",
                "The approvals needed is a sum of three parts:\n"
                + "\n"
                + "    int needed = (type.equals(\"standard\") ? 0 : 1)\n"
                + "            + (risk.equals(\"high\") ? 1 : 0)\n"
                + "            + (tier == 1 ? 1 : 0);",
                "Work out needed BEFORE the chain - but it is only meaningful "
                + "once type, risk and tier are known to be valid, and the "
                + "chain only uses it after those checks.",
                "Keep one String result, set it in each branch, and print it "
                + "once. Build the NOTE text the same way, and print it only "
                + "when result is APPROVED and the note is not empty.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Change type: \");",
                "        String type = input.nextLine().trim().toLowerCase();",
                "        System.out.print(\"Risk: \");",
                "        String risk = input.nextLine().trim().toLowerCase();",
                "        System.out.print(\"System tier (1-3): \");",
                "        String tierText = input.nextLine().trim();",
                "        System.out.print(\"Approvals: \");",
                "        String approvalsText = input.nextLine().trim();",
                "        System.out.print(\"Freeze in effect (y/n): \");",
                "        boolean freeze = input.nextLine().trim()",
                "                .equalsIgnoreCase(\"y\");",
                "        System.out.print(\"Rollback plan (y/n): \");",
                "        boolean rollback = input.nextLine().trim()",
                "                .equalsIgnoreCase(\"y\");",
                "        boolean typeOk = switch (type) {",
                "            case \"standard\", \"normal\", \"emergency\" -> true;",
                "            default -> false;",
                "        };",
                "        boolean riskOk = switch (risk) {",
                "            case \"low\", \"medium\", \"high\" -> true;",
                "            default -> false;",
                "        };",
                "        boolean tierShape = tierText.length() == 1",
                "                && tierText.matches(\"[0-9]+\");",
                "        int tier = tierShape ? Integer.parseInt(tierText) : 0;",
                "        boolean approvalsOk = approvalsText.length() <= 3",
                "                && approvalsText.matches(\"[0-9]+\");",
                "        int approvals = approvalsOk",
                "                ? Integer.parseInt(approvalsText) : 0;",
                "        boolean low = risk.equals(\"low\");",
                "        int needed = (type.equals(\"standard\") ? 0 : 1)",
                "                + (risk.equals(\"high\") ? 1 : 0)",
                "                + (tier == 1 ? 1 : 0);",
                "        String result;",
                "        if (!typeOk) {",
                "            result = \"BLOCKED: unknown change type\";",
                "        } else if (!riskOk) {",
                "            result = \"BLOCKED: unknown risk level\";",
                "        } else if (tier < 1 || tier > 3) {",
                "            result = \"BLOCKED: tier must be 1-3\";",
                "        } else if (!approvalsOk) {",
                "            result = \"BLOCKED: approvals must be a number\";",
                "        } else if (type.equals(\"standard\") && !low) {",
                "            result = \"BLOCKED: a standard change must be low risk\";",
                "        } else if (freeze && !type.equals(\"emergency\")) {",
                "            result = \"BLOCKED: change freeze in effect\";",
                "        } else if (!rollback && !low) {",
                "            result = \"BLOCKED: no rollback plan\";",
                "        } else if (approvals < needed) {",
                "            result = \"BLOCKED: needs \" + needed",
                "                    + \" approvals, has \" + approvals;",
                "        } else {",
                "            result = \"APPROVED\";",
                "        }",
                "        String note = \"\";",
                "        if (type.equals(\"emergency\")) {",
                "            note = \"emergency change - review within 48 hours\";",
                "        } else if (type.equals(\"standard\")) {",
                "            note = \"pre-approved standard change\";",
                "        }",
                "        System.out.println(result);",
                "        if (result.equals(\"APPROVED\") && !note.isEmpty()) {",
                "            System.out.println(\"NOTE: \" + note);",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The chain reads top to bottom like the policy document, "
                + "which is the point: when the change board updates a rule, "
                + "someone can find the line that implements it. The order "
                + "is validation first, then the hard stops (wrong kind of "
                + "change, freeze, no way back), then the approval count.\n"
                + "\n"
                + "needed is worked out before the chain even when the input "
                + "is invalid. That is safe because it is only a number: it "
                + "is never printed unless every validation check above it "
                + "has passed. Doing it up front keeps the chain itself to "
                + "one short condition per rule.\n"
                + "\n"
                + "Two details the hidden tests probe: a low-risk change "
                + "needs no rollback plan, and an emergency change passes a "
                + "freeze but still needs its approvals - emergency means "
                + "fast, not unchecked.")
            .sample(Lab.typing("normal", "high", "1", "3", "n", "y"),
                "Change type: normal",
                "Risk: high",
                "System tier (1-3): 1",
                "Approvals: 3",
                "Freeze in effect (y/n): n",
                "Rollback plan (y/n): y",
                "APPROVED")
            .hidden(Lab.typing("normal", "high", "1", "2", "n", "y"),
                "Change type: normal",
                "Risk: high",
                "System tier (1-3): 1",
                "Approvals: 2",
                "Freeze in effect (y/n): n",
                "Rollback plan (y/n): y",
                "BLOCKED: needs 3 approvals, has 2")
            .hidden(Lab.typing("Standard", "medium", "3", "0", "n", "y"),
                "Change type: Standard",
                "Risk: medium",
                "System tier (1-3): 3",
                "Approvals: 0",
                "Freeze in effect (y/n): n",
                "Rollback plan (y/n): y",
                "BLOCKED: a standard change must be low risk")
            .hidden(Lab.typing("standard", "LOW", "3", "0", "n", "n"),
                "Change type: standard",
                "Risk: LOW",
                "System tier (1-3): 3",
                "Approvals: 0",
                "Freeze in effect (y/n): n",
                "Rollback plan (y/n): n",
                "APPROVED",
                "NOTE: pre-approved standard change")
            .hidden(Lab.typing("normal", "medium", "2", "1", "y", "y"),
                "Change type: normal",
                "Risk: medium",
                "System tier (1-3): 2",
                "Approvals: 1",
                "Freeze in effect (y/n): y",
                "Rollback plan (y/n): y",
                "BLOCKED: change freeze in effect")
            .hidden(Lab.typing("emergency", "high", "1", "3", "y", "y"),
                "Change type: emergency",
                "Risk: high",
                "System tier (1-3): 1",
                "Approvals: 3",
                "Freeze in effect (y/n): y",
                "Rollback plan (y/n): y",
                "APPROVED",
                "NOTE: emergency change - review within 48 hours")
            .hidden(Lab.typing("emergency", "high", "2", "1", "Y", "y"),
                "Change type: emergency",
                "Risk: high",
                "System tier (1-3): 2",
                "Approvals: 1",
                "Freeze in effect (y/n): Y",
                "Rollback plan (y/n): y",
                "BLOCKED: needs 2 approvals, has 1")
            .hidden(Lab.typing("normal", "low", "3", "1", "n", "n"),
                "Change type: normal",
                "Risk: low",
                "System tier (1-3): 3",
                "Approvals: 1",
                "Freeze in effect (y/n): n",
                "Rollback plan (y/n): n",
                "APPROVED")
            .hidden(Lab.typing("normal", "medium", "3", "5", "n", "n"),
                "Change type: normal",
                "Risk: medium",
                "System tier (1-3): 3",
                "Approvals: 5",
                "Freeze in effect (y/n): n",
                "Rollback plan (y/n): n",
                "BLOCKED: no rollback plan")
            .hidden(Lab.typing("urgent", "low", "3", "1", "n", "y"),
                "Change type: urgent",
                "Risk: low",
                "System tier (1-3): 3",
                "Approvals: 1",
                "Freeze in effect (y/n): n",
                "Rollback plan (y/n): y",
                "BLOCKED: unknown change type")
            .hidden(Lab.typing("normal", "critical", "3", "1", "n", "y"),
                "Change type: normal",
                "Risk: critical",
                "System tier (1-3): 3",
                "Approvals: 1",
                "Freeze in effect (y/n): n",
                "Rollback plan (y/n): y",
                "BLOCKED: unknown risk level")
            .hidden(Lab.typing("normal", "low", "4", "1", "n", "y"),
                "Change type: normal",
                "Risk: low",
                "System tier (1-3): 4",
                "Approvals: 1",
                "Freeze in effect (y/n): n",
                "Rollback plan (y/n): y",
                "BLOCKED: tier must be 1-3")
            .hidden(Lab.typing("normal", "low", "3", "two", "n", "y"),
                "Change type: normal",
                "Risk: low",
                "System tier (1-3): 3",
                "Approvals: two",
                "Freeze in effect (y/n): n",
                "Rollback plan (y/n): y",
                "BLOCKED: approvals must be a number"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(30), "Firewall Rule Checker", Lab.CAPSTONE)
            .after("C02-M030")
            .brief(
                "CAPSTONE. NORTHSTAR's edge firewall has seven rules, checked "
                + "top to bottom, first match wins - exactly like a real one. "
                + "Build the checker the network team uses to ask \"what "
                + "would the firewall do with THIS packet, and which rule "
                + "decided it?\" Everything in this campaign goes in: "
                + "validation, guards, switches, ordered chains and exact "
                + "output.")
            .practises("First-match rule order", "Validating every field", "Conditional input", "Switch lookups", "A complete decision program")
            .spec(
                "Prompt and read Direction (in/out):, Protocol:, Source IP:, then Port: - but NOT the port for ICMP. Trim everything; direction and protocol are not case-sensitive.",
                "Validate in order, printing INVALID PACKET: and the reason: direction must be in or out; protocol must be TCP, UDP or ICMP; bad source address (the IP must be only digits and exactly 3 dots, not starting or ending with a dot, no .. and at most 15 characters); port must be 1-65535 (1-5 digits, not for ICMP).",
                "Rules, first match wins. 1 in, source starting 203.0.113.: DENY, blocklisted source range. 2 in, TCP port 80 or 443: ALLOW, public web. 3 in, TCP port 22, source starting 10.: ALLOW, SSH from the internal network. 4 in, port 23, 445 or 3389 (TCP or UDP): DENY, risky service telnet, SMB or RDP. 5 out, TCP 80 or 443 or UDP 53: ALLOW, web and DNS out. 6 out, ICMP: ALLOW, outbound ping. 7 anything else: DENY, default deny.",
                "Print Packet:  then direction in capitals, protocol, IP, and port <n> unless ICMP. Then Verdict: ALLOW or DENY, then Rule:    <number> - <reason>. Rules 1 and 4 add Alert:   raised for the SOC.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Direction (in/out): \");",
                "        String direction = input.nextLine().trim().toLowerCase();",
                "        System.out.print(\"Protocol: \");",
                "        String protocol = input.nextLine().trim().toUpperCase();",
                "        // read the source IP, and the port unless ICMP",
                "        // validate every field",
                "        // walk the seven rules; the first match decides",
                "    }",
                "}")
            .hints(
                "Only ask for the port when it exists:\n"
                + "\n"
                + "    boolean icmp = protocol.equals(\"ICMP\");\n"
                + "    String portText = \"\";\n"
                + "    if (!icmp) {\n"
                + "        // prompt and read the port\n"
                + "    }",
                "Count dots without a loop: remove them with replace and "
                + "compare lengths.\n"
                + "\n"
                + "    String digits = ip.replace(\".\", \"\");\n"
                + "    int dots = ip.length() - digits.length();\n"
                + "\n"
                + "digits must then be all digits.",
                "Collect validation into String error = \"\" with an if / "
                + "else if chain, so the rule checking can sit in one else "
                + "and trust every field.",
                "Leave the port as 0 for ICMP. Then web, dns and the "
                + "risky-service switch are automatically false or empty for "
                + "ICMP, and the rules need no special cases for it.",
                "Name the building blocks before the rules:\n"
                + "\n"
                + "    boolean in = direction.equals(\"in\");\n"
                + "    boolean web = tcp && (port == 80 || port == 443);\n"
                + "    boolean dns = udp && port == 53;",
                "Each rule branch sets int rule and String reason. Then a "
                + "switch on the rule number gives the verdict: case 2, 3, "
                + "5, 6 -> \"ALLOW\"; default -> \"DENY\".")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Direction (in/out): \");",
                "        String direction = input.nextLine().trim().toLowerCase();",
                "        System.out.print(\"Protocol: \");",
                "        String protocol = input.nextLine().trim().toUpperCase();",
                "        System.out.print(\"Source IP: \");",
                "        String ip = input.nextLine().trim();",
                "        boolean icmp = protocol.equals(\"ICMP\");",
                "        String portText = \"\";",
                "        if (!icmp) {",
                "            System.out.print(\"Port: \");",
                "            portText = input.nextLine().trim();",
                "        }",
                "",
                "        boolean knownProtocol = switch (protocol) {",
                "            case \"TCP\", \"UDP\", \"ICMP\" -> true;",
                "            default -> false;",
                "        };",
                "        String digits = ip.replace(\".\", \"\");",
                "        int dots = ip.length() - digits.length();",
                "        boolean ipOk = dots == 3 && digits.matches(\"[0-9]+\")",
                "                && !ip.startsWith(\".\") && !ip.endsWith(\".\")",
                "                && !ip.contains(\"..\") && ip.length() <= 15;",
                "        boolean portShape = portText.length() <= 5",
                "                && portText.matches(\"[0-9]+\");",
                "        int port = portShape ? Integer.parseInt(portText) : 0;",
                "",
                "        String error = \"\";",
                "        if (!direction.equals(\"in\") && !direction.equals(\"out\")) {",
                "            error = \"direction must be in or out\";",
                "        } else if (!knownProtocol) {",
                "            error = \"protocol must be TCP, UDP or ICMP\";",
                "        } else if (!ipOk) {",
                "            error = \"bad source address\";",
                "        } else if (!icmp && (port < 1 || port > 65535)) {",
                "            error = \"port must be 1-65535\";",
                "        }",
                "",
                "        if (!error.isEmpty()) {",
                "            System.out.println(\"INVALID PACKET: \" + error);",
                "        } else {",
                "            boolean in = direction.equals(\"in\");",
                "            boolean tcp = protocol.equals(\"TCP\");",
                "            boolean udp = protocol.equals(\"UDP\");",
                "            boolean web = tcp && (port == 80 || port == 443);",
                "            boolean dns = udp && port == 53;",
                "            String risky = switch (port) {",
                "                case 23 -> \"telnet\";",
                "                case 445 -> \"SMB\";",
                "                case 3389 -> \"RDP\";",
                "                default -> \"\";",
                "            };",
                "            int rule;",
                "            String reason;",
                "            if (in && ip.startsWith(\"203.0.113.\")) {",
                "                rule = 1;",
                "                reason = \"blocklisted source range\";",
                "            } else if (in && web) {",
                "                rule = 2;",
                "                reason = \"public web\";",
                "            } else if (in && tcp && port == 22",
                "                    && ip.startsWith(\"10.\")) {",
                "                rule = 3;",
                "                reason = \"SSH from the internal network\";",
                "            } else if (in && !risky.isEmpty()) {",
                "                rule = 4;",
                "                reason = \"risky service \" + risky;",
                "            } else if (!in && (web || dns)) {",
                "                rule = 5;",
                "                reason = \"web and DNS out\";",
                "            } else if (!in && icmp) {",
                "                rule = 6;",
                "                reason = \"outbound ping\";",
                "            } else {",
                "                rule = 7;",
                "                reason = \"default deny\";",
                "            }",
                "            String verdict = switch (rule) {",
                "                case 2, 3, 5, 6 -> \"ALLOW\";",
                "                default -> \"DENY\";",
                "            };",
                "            String packet = direction.toUpperCase() + \" \" + protocol",
                "                    + \" \" + ip + (icmp ? \"\" : \" port \" + port);",
                "            System.out.println(\"Packet:  \" + packet);",
                "            System.out.println(\"Verdict: \" + verdict);",
                "            System.out.println(\"Rule:    \" + rule + \" - \" + reason);",
                "            if (rule == 1 || rule == 4) {",
                "                System.out.println(\"Alert:   raised for the SOC\");",
                "            }",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "This is how a real stateless firewall works: rules in a "
                + "fixed order, the first rule that matches decides, and a "
                + "default deny at the bottom catches everything nobody "
                + "thought of. The if / else if chain IS that model - Java "
                + "stops at the first true condition exactly as the firewall "
                + "stops at the first matching rule.\n"
                + "\n"
                + "Order carries the security. Rule 1 comes before rule 2, so "
                + "a blocklisted address is refused even on port 443. SSH "
                + "from outside matches nothing and falls to rule 7. Rule 4 "
                + "adds no protection on its own - rule 7 would deny those "
                + "ports anyway - but it names the service and raises an "
                + "alert, because someone knocking on RDP or SMB from the "
                + "internet is worth a look.\n"
                + "\n"
                + "The validation block reuses every trick in the campaign: "
                + "counting dots with replace, guarding parseInt behind a "
                + "shape check, and a port of 0 for ICMP so that web, dns "
                + "and risky are false or empty without any extra code. Then "
                + "the rules only ever see clean values.\n"
                + "\n"
                + "One honest limit: the IP check is about shape, so "
                + "999.1.1.1 gets through. Checking all four parts properly "
                + "is much tidier with a loop - which is exactly where "
                + "Campaign 04 picks up.")
            .sample(Lab.typing("in", "TCP", "198.51.100.7", "443"),
                "Direction (in/out): in",
                "Protocol: TCP",
                "Source IP: 198.51.100.7",
                "Port: 443",
                "Packet:  IN TCP 198.51.100.7 port 443",
                "Verdict: ALLOW",
                "Rule:    2 - public web")
            .hidden(Lab.typing("in", "tcp", "203.0.113.9", "443"),
                "Direction (in/out): in",
                "Protocol: tcp",
                "Source IP: 203.0.113.9",
                "Port: 443",
                "Packet:  IN TCP 203.0.113.9 port 443",
                "Verdict: DENY",
                "Rule:    1 - blocklisted source range",
                "Alert:   raised for the SOC")
            .hidden(Lab.typing("in", "TCP", "10.2.3.4", "22"),
                "Direction (in/out): in",
                "Protocol: TCP",
                "Source IP: 10.2.3.4",
                "Port: 22",
                "Packet:  IN TCP 10.2.3.4 port 22",
                "Verdict: ALLOW",
                "Rule:    3 - SSH from the internal network")
            .hidden(Lab.typing("in", "TCP", "198.51.100.7", "22"),
                "Direction (in/out): in",
                "Protocol: TCP",
                "Source IP: 198.51.100.7",
                "Port: 22",
                "Packet:  IN TCP 198.51.100.7 port 22",
                "Verdict: DENY",
                "Rule:    7 - default deny")
            .hidden(Lab.typing("IN", " tcp ", "198.51.100.7", "3389"),
                "Direction (in/out): IN",
                "Protocol:  tcp",
                "Source IP: 198.51.100.7",
                "Port: 3389",
                "Packet:  IN TCP 198.51.100.7 port 3389",
                "Verdict: DENY",
                "Rule:    4 - risky service RDP",
                "Alert:   raised for the SOC")
            .hidden(Lab.typing("in", "UDP", "198.51.100.7", "445"),
                "Direction (in/out): in",
                "Protocol: UDP",
                "Source IP: 198.51.100.7",
                "Port: 445",
                "Packet:  IN UDP 198.51.100.7 port 445",
                "Verdict: DENY",
                "Rule:    4 - risky service SMB",
                "Alert:   raised for the SOC")
            .hidden(Lab.typing("in", "TCP", "10.0.0.1", "23"),
                "Direction (in/out): in",
                "Protocol: TCP",
                "Source IP: 10.0.0.1",
                "Port: 23",
                "Packet:  IN TCP 10.0.0.1 port 23",
                "Verdict: DENY",
                "Rule:    4 - risky service telnet",
                "Alert:   raised for the SOC")
            .hidden(Lab.typing("out", "udp", "10.0.0.5", "53"),
                "Direction (in/out): out",
                "Protocol: udp",
                "Source IP: 10.0.0.5",
                "Port: 53",
                "Packet:  OUT UDP 10.0.0.5 port 53",
                "Verdict: ALLOW",
                "Rule:    5 - web and DNS out")
            .hidden(Lab.typing("out", "TCP", "10.0.0.5", "8080"),
                "Direction (in/out): out",
                "Protocol: TCP",
                "Source IP: 10.0.0.5",
                "Port: 8080",
                "Packet:  OUT TCP 10.0.0.5 port 8080",
                "Verdict: DENY",
                "Rule:    7 - default deny")
            .hidden(Lab.typing("out", "TCP", "10.0.0.5", "443"),
                "Direction (in/out): out",
                "Protocol: TCP",
                "Source IP: 10.0.0.5",
                "Port: 443",
                "Packet:  OUT TCP 10.0.0.5 port 443",
                "Verdict: ALLOW",
                "Rule:    5 - web and DNS out")
            .hidden(Lab.typing("out", "ICMP", "10.0.0.5"),
                "Direction (in/out): out",
                "Protocol: ICMP",
                "Source IP: 10.0.0.5",
                "Packet:  OUT ICMP 10.0.0.5",
                "Verdict: ALLOW",
                "Rule:    6 - outbound ping")
            .hidden(Lab.typing("in", "icmp", "198.51.100.7"),
                "Direction (in/out): in",
                "Protocol: icmp",
                "Source IP: 198.51.100.7",
                "Packet:  IN ICMP 198.51.100.7",
                "Verdict: DENY",
                "Rule:    7 - default deny")
            .hidden(Lab.typing("sideways", "TCP", "10.0.0.1", "80"),
                "Direction (in/out): sideways",
                "Protocol: TCP",
                "Source IP: 10.0.0.1",
                "Port: 80",
                "INVALID PACKET: direction must be in or out")
            .hidden(Lab.typing("in", "SCTP", "10.0.0.1", "80"),
                "Direction (in/out): in",
                "Protocol: SCTP",
                "Source IP: 10.0.0.1",
                "Port: 80",
                "INVALID PACKET: protocol must be TCP, UDP or ICMP")
            .hidden(Lab.typing("in", "TCP", "10.0.0", "80"),
                "Direction (in/out): in",
                "Protocol: TCP",
                "Source IP: 10.0.0",
                "Port: 80",
                "INVALID PACKET: bad source address")
            .hidden(Lab.typing("in", "TCP", "10..0.1", "80"),
                "Direction (in/out): in",
                "Protocol: TCP",
                "Source IP: 10..0.1",
                "Port: 80",
                "INVALID PACKET: bad source address")
            .hidden(Lab.typing("in", "TCP", "10.0.0.x", "80"),
                "Direction (in/out): in",
                "Protocol: TCP",
                "Source IP: 10.0.0.x",
                "Port: 80",
                "INVALID PACKET: bad source address")
            .hidden(Lab.typing("in", "TCP", "10.0.0.1", "70000"),
                "Direction (in/out): in",
                "Protocol: TCP",
                "Source IP: 10.0.0.1",
                "Port: 70000",
                "INVALID PACKET: port must be 1-65535")
            .hidden(Lab.typing("in", "TCP", "10.0.0.1", "0"),
                "Direction (in/out): in",
                "Protocol: TCP",
                "Source IP: 10.0.0.1",
                "Port: 0",
                "INVALID PACKET: port must be 1-65535"));
    }
}
