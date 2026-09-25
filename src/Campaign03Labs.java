/**
 * LABS - CAMPAIGN 03 - METHODS
 *
 * Programs built from methods. Every lab names the methods it needs, and
 * the game checks they are declared - with the right return and parameter
 * types - before running any test, so a lab about methods cannot be passed
 * by writing everything in main. Loops are Campaign 04; where something
 * must repeat, these labs use a method called more than once, or
 * recursion.
 */
public class Campaign03Labs {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(1), "Banner Method", Lab.SMALL)
            .after("C03-M002")
            .brief(
                "Every SOC console session opens with the same banner, greets "
                + "the analyst by name, and closes the greeting with the "
                + "banner again. Write the banner ONCE, as a method, and call "
                + "it twice.")
            .practises("Writing a static void method", "Calling a method twice")
            .spec(
                "Declare a method printBanner that prints three lines: =================, then = NORTHSTAR SOC =, then ================= again.",
                "In main, prompt Analyst name: and read the line, trimmed.",
                "Then call printBanner, print Welcome, <name>, and call printBanner again.")
            .needsMethod("static void printBanner()")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Analyst name: \");",
                "        String name = input.nextLine().trim();",
                "        // call printBanner, greet, call printBanner again",
                "    }",
                "",
                "    // declare printBanner here",
                "}")
            .hints(
                "The method goes inside the class but outside main - where "
                + "the comment is.",
                "Its header is  static void printBanner() {  and its body is "
                + "three printlns.",
                "main then needs three statements: printBanner(); the Welcome "
                + "println, and printBanner(); again.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Analyst name: \");",
                "        String name = input.nextLine().trim();",
                "        printBanner();",
                "        System.out.println(\"Welcome, \" + name);",
                "        printBanner();",
                "    }",
                "",
                "    static void printBanner() {",
                "        System.out.println(\"=================\");",
                "        System.out.println(\"= NORTHSTAR SOC =\");",
                "        System.out.println(\"=================\");",
                "    }",
                "}")
            .walkthrough(
                "The banner's three lines are written once, inside "
                + "printBanner, and main calls it twice. Each call is a round "
                + "trip: main pauses, the three lines print, and main carries "
                + "on with the line after the call.\n"
                + "\n"
                + "printBanner is written below main and called from above it. "
                + "That is fine - Java reads the whole class before running "
                + "anything. If the SOC changes its banner, one method "
                + "changes, and both copies on screen change with it.")
            .sample(Lab.typing("Adaeze"),
                "Analyst name: Adaeze",
                "=================",
                "= NORTHSTAR SOC =",
                "=================",
                "Welcome, Adaeze",
                "=================",
                "= NORTHSTAR SOC =",
                "=================")
            .hidden(Lab.typing("  m.reyes  "),
                "Analyst name:   m.reyes",
                "=================",
                "= NORTHSTAR SOC =",
                "=================",
                "Welcome, m.reyes",
                "=================",
                "= NORTHSTAR SOC =",
                "=================")
            .hidden(Lab.typing("J Smith"),
                "Analyst name: J Smith",
                "=================",
                "= NORTHSTAR SOC =",
                "=================",
                "Welcome, J Smith",
                "=================",
                "= NORTHSTAR SOC =",
                "================="));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(2), "Square and Cube", Lab.SMALL)
            .stretch()
            .after("C03-M006")
            .brief(
                "Capacity planning keeps asking the same two questions about "
                + "grid storage: what is n squared, and what is n cubed? Write "
                + "each as a method that RETURNS its answer, so main decides "
                + "how to show it.")
            .practises("Returning a value", "Using a returned value", "One method calling another")
            .spec(
                "Prompt Grid size: and read a whole number (it will be between -1000 and 1000).",
                "Declare square, which returns n times n, and cube, which returns n times n times n.",
                "Print Square: <square> and then Cube: <cube>.")
            .needsMethod("static int square(int)")
            .needsMethod("static int cube(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Grid size: \");",
                "        int size = Integer.parseInt(input.nextLine().trim());",
                "        // print the square and the cube, using your methods",
                "    }",
                "",
                "    // declare square and cube here",
                "}")
            .hints(
                "A method that hands back an int has int where void would go: "
                + "static int square(int n) {",
                "Its body is one line: return n * n;",
                "cube can reuse square:  return n * square(n);",
                "In main:  System.out.println(\"Square: \" + square(size));")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Grid size: \");",
                "        int size = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(\"Square: \" + square(size));",
                "        System.out.println(\"Cube: \" + cube(size));",
                "    }",
                "",
                "    static int square(int n) {",
                "        return n * n;",
                "    }",
                "",
                "    static int cube(int n) {",
                "        return n * square(n);",
                "    }",
                "}")
            .walkthrough(
                "Each method answers one question and returns the answer. "
                + "Neither prints anything - that is main's job - so either "
                + "one could be reused anywhere a square or a cube is needed.\n"
                + "\n"
                + "cube calls square: a method calling a method. The largest "
                + "size, 1000, cubes to 1,000,000,000 - just inside an int's "
                + "limit of about 2.1 billion. A size of 1300 would overflow "
                + "silently, which is why the spec states the range.")
            .sample(Lab.typing("3"),
                "Grid size: 3",
                "Square: 9",
                "Cube: 27")
            .hidden(Lab.typing("0"),
                "Grid size: 0",
                "Square: 0",
                "Cube: 0")
            .hidden(Lab.typing("12"),
                "Grid size: 12",
                "Square: 144",
                "Cube: 1728")
            .hidden(Lab.typing("-4"),
                "Grid size: -4",
                "Square: 16",
                "Cube: -64")
            .hidden(Lab.typing("1000"),
                "Grid size: 1000",
                "Square: 1000000",
                "Cube: 1000000000"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(3), "isValidPort", Lab.SMALL)
            .after("C03-M010")
            .brief(
                "Three tools, three different port checks - and one of them "
                + "accepts port 0. Write THE port check: one boolean method "
                + "every tool can share, and a main that tries it on whatever "
                + "is typed.")
            .practises("Boolean methods", "Guard clauses", "Checking text before parseInt")
            .spec(
                "Prompt Port: and read the line, trimmed.",
                "Declare isValidPort: true only when the text is 1 to 5 digits AND its value is 1 to 65535. Anything else - letters, a minus sign, nothing at all - is false.",
                "Print VALID PORT or INVALID PORT.")
            .needsMethod("static boolean isValidPort(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Port: \");",
                "        String text = input.nextLine().trim();",
                "        // ask isValidPort, print the verdict",
                "    }",
                "",
                "    // declare isValidPort here",
                "}")
            .hints(
                "Guard first: if the text is longer than 5 or not all digits, "
                + "return false. text.matches(\"[0-9]+\") is false for empty "
                + "text too.",
                "Only after the guard is parseInt safe.",
                "The last line returns the range test itself:  return port >= "
                + "1 && port <= 65535;",
                "In main:  if (isValidPort(text)) { ... } else { ... }")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Port: \");",
                "        String text = input.nextLine().trim();",
                "        if (isValidPort(text)) {",
                "            System.out.println(\"VALID PORT\");",
                "        } else {",
                "            System.out.println(\"INVALID PORT\");",
                "        }",
                "    }",
                "",
                "    static boolean isValidPort(String text) {",
                "        if (text.length() > 5 || !text.matches(\"[0-9]+\")) {",
                "            return false;",
                "        }",
                "        int port = Integer.parseInt(text);",
                "        return port >= 1 && port <= 65535;",
                "    }",
                "}")
            .walkthrough(
                "The guard does two jobs. It rejects anything that is not "
                + "digits - letters, a minus sign, an empty line - and it "
                + "rejects anything longer than five digits, which also means "
                + "parseInt can never be handed a number too big for an int. "
                + "By the last line, the method knows it has a small whole "
                + "number and only needs the range rule.\n"
                + "\n"
                + "The hidden tests sit on every edge: 0 and 1, 65535 and "
                + "65536, plus an empty line, a negative number and an "
                + "eleven-digit number that would crash a program that "
                + "parsed first and checked later.")
            .sample(Lab.typing("443"),
                "Port: 443",
                "VALID PORT")
            .hidden(Lab.typing("0"),
                "Port: 0",
                "INVALID PORT")
            .hidden(Lab.typing("1"),
                "Port: 1",
                "VALID PORT")
            .hidden(Lab.typing("65535"),
                "Port: 65535",
                "VALID PORT")
            .hidden(Lab.typing("65536"),
                "Port: 65536",
                "INVALID PORT")
            .hidden(Lab.typing(""),
                "Port:",
                "INVALID PORT")
            .hidden(Lab.typing("http"),
                "Port: http",
                "INVALID PORT")
            .hidden(Lab.typing("-1"),
                "Port: -1",
                "INVALID PORT")
            .hidden(Lab.typing("99999999999"),
                "Port: 99999999999",
                "INVALID PORT")
            .hidden(Lab.typing(" 8080 "),
                "Port:  8080",
                "VALID PORT"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(4), "Severity Label", Lab.SMALL)
            .after("C03-M007")
            .brief(
                "The vulnerability scanner scores every finding from 0 to 10. "
                + "The weekly report needs words, not numbers. Write the "
                + "method that turns a score into a label - one place, one "
                + "rule - and use it.")
            .practises("Returning a String", "Guard clauses", "Range boundaries")
            .spec(
                "Prompt Score (0-10): and read a whole number.",
                "Declare severityLabel, which returns NONE for 0, LOW for 1-3, MEDIUM for 4-6, HIGH for 7-8, CRITICAL for 9-10, and INVALID for anything outside 0-10.",
                "Print Severity: <label>.")
            .needsMethod("static String severityLabel(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Score (0-10): \");",
                "        int score = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(\"Severity: \" + severityLabel(score));",
                "    }",
                "",
                "    static String severityLabel(int score) {",
                "        return \"TODO\";",
                "    }",
                "}")
            .hints(
                "Start with the guard: if score < 0 || score > 10, return "
                + "\"INVALID\".",
                "Then one if per label, each returning straight away. Because "
                + "each if returns, you only need upper limits.",
                "if (score == 0) return NONE; if (score <= 3) return LOW; and "
                + "so on - with braces.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Score (0-10): \");",
                "        int score = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(\"Severity: \" + severityLabel(score));",
                "    }",
                "",
                "    static String severityLabel(int score) {",
                "        if (score < 0 || score > 10) {",
                "            return \"INVALID\";",
                "        }",
                "        if (score == 0) {",
                "            return \"NONE\";",
                "        }",
                "        if (score <= 3) {",
                "            return \"LOW\";",
                "        }",
                "        if (score <= 6) {",
                "            return \"MEDIUM\";",
                "        }",
                "        if (score <= 8) {",
                "            return \"HIGH\";",
                "        }",
                "        return \"CRITICAL\";",
                "    }",
                "}")
            .walkthrough(
                "The guard handles impossible scores first. After it, every if "
                + "returns, so reaching the next one already means the score "
                + "is above the previous limit - which is why each check only "
                + "needs an upper bound. The last line needs no if at all: "
                + "only 9 and 10 can get there.\n"
                + "\n"
                + "main contains no rules. It reads, asks the method, and "
                + "prints. The hidden tests hit both sides of every band, "
                + "where a < that should have been <= shows up at once.")
            .sample(Lab.typing("7"),
                "Score (0-10): 7",
                "Severity: HIGH")
            .hidden(Lab.typing("0"),
                "Score (0-10): 0",
                "Severity: NONE")
            .hidden(Lab.typing("3"),
                "Score (0-10): 3",
                "Severity: LOW")
            .hidden(Lab.typing("4"),
                "Score (0-10): 4",
                "Severity: MEDIUM")
            .hidden(Lab.typing("6"),
                "Score (0-10): 6",
                "Severity: MEDIUM")
            .hidden(Lab.typing("8"),
                "Score (0-10): 8",
                "Severity: HIGH")
            .hidden(Lab.typing("9"),
                "Score (0-10): 9",
                "Severity: CRITICAL")
            .hidden(Lab.typing("10"),
                "Score (0-10): 10",
                "Severity: CRITICAL")
            .hidden(Lab.typing("11"),
                "Score (0-10): 11",
                "Severity: INVALID")
            .hidden(Lab.typing("-1"),
                "Score (0-10): -1",
                "Severity: INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(5), "Normalise Username", Lab.SMALL)
            .after("C03-M014")
            .brief(
                "User names arrive typed by humans: stray spaces, random "
                + "capitals. The directory stores each name one way only. "
                + "Write the method that normalises a name - and remember "
                + "that a String method has to RETURN its result.")
            .practises("Strings as arguments", "Returning new text", "Comparing before and after")
            .spec(
                "Prompt Username: and read the line exactly as typed.",
                "Declare normalise, which returns the text trimmed and in lower case.",
                "If the normalised name is empty, print REJECTED: empty username and nothing else.",
                "Otherwise print Stored as: [<name>], then Note: input was cleaned if the normalised name differs from what was typed, or Note: no change needed if it does not.")
            .needsMethod("static String normalise(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String typed = input.nextLine();",
                "        normalise(typed);",
                "        System.out.println(\"Stored as: [\" + typed + \"]\");",
                "    }",
                "",
                "    static String normalise(String text) {",
                "        text = text.trim().toLowerCase();",
                "        return text;",
                "    }",
                "}")
            .hints(
                "The starter's method is fine. Look at main: what happens to "
                + "the value normalise returns?",
                "Store it:  String name = normalise(typed);",
                "Compare with equals:  !name.equals(typed)  means it was "
                + "cleaned.",
                "Check name.isEmpty() first, and only print the other lines "
                + "when it is not.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String typed = input.nextLine();",
                "        String name = normalise(typed);",
                "        if (name.isEmpty()) {",
                "            System.out.println(\"REJECTED: empty username\");",
                "        } else {",
                "            System.out.println(\"Stored as: [\" + name + \"]\");",
                "            if (name.equals(typed)) {",
                "                System.out.println(\"Note: no change needed\");",
                "            } else {",
                "                System.out.println(\"Note: input was cleaned\");",
                "            }",
                "        }",
                "    }",
                "",
                "    static String normalise(String text) {",
                "        return text.trim().toLowerCase();",
                "    }",
                "}")
            .walkthrough(
                "The starter's bug is the one from mission 14: normalise "
                + "returns the clean name, and main throws it away. typed "
                + "never changes, because Strings cannot change and the "
                + "method only changed its own variable. Storing the "
                + "result in a new variable fixes that - and keeping typed "
                + "untouched is exactly what makes the before-and-after "
                + "comparison possible.\n"
                + "\n"
                + "The empty check runs on the NORMALISED name, so a line of "
                + "spaces is rejected too. Storing names one way only matters "
                + "for security: JSmith and jsmith being two accounts is how "
                + "look-alike account attacks start.")
            .sample(Lab.typing("  JSmith "),
                "Username:   JSmith",
                "Stored as: [jsmith]",
                "Note: input was cleaned")
            .hidden(Lab.typing("m.reyes"),
                "Username: m.reyes",
                "Stored as: [m.reyes]",
                "Note: no change needed")
            .hidden(Lab.typing("   "),
                "Username:",
                "REJECTED: empty username")
            .hidden(Lab.typing("ADMIN"),
                "Username: ADMIN",
                "Stored as: [admin]",
                "Note: input was cleaned")
            .hidden(Lab.typing("svc-backup "),
                "Username: svc-backup",
                "Stored as: [svc-backup]",
                "Note: input was cleaned"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(6), "Minutes to Hours", Lab.SMALL)
            .stretch()
            .after("C03-M007")
            .brief(
                "VPN session lengths arrive in minutes. The weekly report "
                + "wants them as hours and minutes - 2h 05m - and flags any "
                + "session longer than an eight-hour shift. Three small "
                + "methods, one job each.")
            .practises("Several small methods", "Integer division and remainder", "String.format")
            .spec(
                "Prompt Session minutes: and read a whole number, 0 or more.",
                "Declare wholeHours (minutes / 60), leftoverMinutes (minutes % 60) and formatDuration, which returns text like 2h 05m - minutes always two digits.",
                "Print Session: <formatted>. If the session is longer than 480 minutes, also print Note: longer than a shift.")
            .needsMethod("static int wholeHours(int)")
            .needsMethod("static int leftoverMinutes(int)")
            .needsMethod("static String formatDuration(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Session minutes: \");",
                "        int minutes = Integer.parseInt(input.nextLine().trim());",
                "        // print the formatted session, and the note if needed",
                "    }",
                "",
                "    // declare wholeHours, leftoverMinutes and formatDuration",
                "}")
            .hints(
                "Integer division gives whole hours: 125 / 60 is 2. The "
                + "remainder gives what is left: 125 % 60 is 5.",
                "formatDuration calls the other two and formats the result:\n"
                + "\n"
                + "    String.format(\"%dh %02dm\", h, m)\n"
                + "\n"
                + "%02d pads with a zero to two digits.",
                "Longer than 480 means > 480, not >=: exactly eight hours is "
                + "one shift.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Session minutes: \");",
                "        int minutes = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(\"Session: \" + formatDuration(minutes));",
                "        if (minutes > 480) {",
                "            System.out.println(\"Note: longer than a shift\");",
                "        }",
                "    }",
                "",
                "    static int wholeHours(int minutes) {",
                "        return minutes / 60;",
                "    }",
                "",
                "    static int leftoverMinutes(int minutes) {",
                "        return minutes % 60;",
                "    }",
                "",
                "    static String formatDuration(int minutes) {",
                "        int h = wholeHours(minutes);",
                "        int m = leftoverMinutes(minutes);",
                "        return String.format(\"%dh %02dm\", h, m);",
                "    }",
                "}")
            .walkthrough(
                "Integer division and the remainder operator split minutes "
                + "into hours and what is left - the pair from Campaign 01, "
                + "now each with a name. formatDuration builds on them "
                + "instead of repeating the arithmetic, and returns text "
                + "rather than printing it, so main stays in charge of "
                + "output.\n"
                + "\n"
                + "%02d is what turns 5 into 05. The note uses > 480 because "
                + "exactly eight hours is a normal shift; the hidden tests "
                + "include 480 and 481 to check that boundary.")
            .sample(Lab.typing("125"),
                "Session minutes: 125",
                "Session: 2h 05m")
            .hidden(Lab.typing("59"),
                "Session minutes: 59",
                "Session: 0h 59m")
            .hidden(Lab.typing("60"),
                "Session minutes: 60",
                "Session: 1h 00m")
            .hidden(Lab.typing("0"),
                "Session minutes: 0",
                "Session: 0h 00m")
            .hidden(Lab.typing("480"),
                "Session minutes: 480",
                "Session: 8h 00m")
            .hidden(Lab.typing("481"),
                "Session minutes: 481",
                "Session: 8h 01m",
                "Note: longer than a shift")
            .hidden(Lab.typing("1500"),
                "Session minutes: 1500",
                "Session: 25h 00m",
                "Note: longer than a shift"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(7), "Safe Log Line", Lab.SMALL)
            .after("C03-M014")
            .brief(
                "The audit log writes one line per event, with fields split by "
                + "a bar: user=jsmith|result=FAIL. Someone signs up as "
                + "bob|result=OK and their failed logins start looking like "
                + "successes. Write the method that makes a name safe for the "
                + "log.")
            .practises("A sanitising method", "Guard clauses", "replace and substring")
            .spec(
                "Prompt Username: and read the line exactly as typed - do not trim it.",
                "Declare forLog, which returns: (empty) for blank text; otherwise the text with every | replaced by /, cut to its first 16 characters followed by ... if it is longer than 16.",
                "Print LOG user=<forLog(name)>|result=FAIL.")
            .needsMethod("static String forLog(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String name = input.nextLine();",
                "        System.out.println(\"LOG user=\" + name + \"|result=FAIL\");",
                "    }",
                "",
                "    // declare forLog, and use it in the println",
                "}")
            .hints(
                "Guard first: if (text.isBlank()) return \"(empty)\";",
                "text.replace(\"|\", \"/\") builds the new text. Store it.",
                "Then: if clean.length() > 16, make clean the first 16 "
                + "characters plus \"...\". substring(0, 16) gives exactly "
                + "16.",
                "Return clean, and call forLog(name) inside the println.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String name = input.nextLine();",
                "        String safe = forLog(name);",
                "        System.out.println(\"LOG user=\" + safe + \"|result=FAIL\");",
                "    }",
                "",
                "    static String forLog(String text) {",
                "        if (text.isBlank()) {",
                "            return \"(empty)\";",
                "        }",
                "        String clean = text.replace(\"|\", \"/\");",
                "        if (clean.length() > 16) {",
                "            clean = clean.substring(0, 16) + \"...\";",
                "        }",
                "        return clean;",
                "    }",
                "}")
            .walkthrough(
                "The bar is this log's field separator, so a bar inside a "
                + "value lets the value pretend to be several fields - "
                + "bob|result=OK would end one field early and add a fake "
                + "one. Replacing it makes the value inert: bob/result=OK is "
                + "obviously just a strange name.\n"
                + "\n"
                + "The length cap stops a huge name from swamping the log, and "
                + "the ... marks that something was cut. (empty) makes a blank "
                + "name visible instead of silently missing. The name is not "
                + "trimmed, on purpose: the log should record what was "
                + "actually sent. The hidden tests include exactly 16 and 17 "
                + "characters, to check the > 16 boundary.")
            .sample(Lab.typing("jsmith"),
                "Username: jsmith",
                "LOG user=jsmith|result=FAIL")
            .hidden(Lab.typing("bob|result=OK"),
                "Username: bob|result=OK",
                "LOG user=bob/result=OK|result=FAIL")
            .hidden(Lab.typing("   "),
                "Username:",
                "LOG user=(empty)|result=FAIL")
            .hidden(Lab.typing("a|b|c|d"),
                "Username: a|b|c|d",
                "LOG user=a/b/c/d|result=FAIL")
            .hidden(Lab.typing("abcdefghijklmnop"),
                "Username: abcdefghijklmnop",
                "LOG user=abcdefghijklmnop|result=FAIL")
            .hidden(Lab.typing("abcdefghijklmnopq"),
                "Username: abcdefghijklmnopq",
                "LOG user=abcdefghijklmnop...|result=FAIL")
            .hidden(Lab.typing("averyveryverylongusername"),
                "Username: averyveryverylongusername",
                "LOG user=averyveryverylon...|result=FAIL"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(8), "Overloaded Formatter", Lab.SMALL)
            .stretch()
            .after("C03-M016")
            .brief(
                "Scan targets are written host or host:port, and the tools "
                + "that print them keep disagreeing about capitals. Write two "
                + "methods with ONE name - target - where the version with a "
                + "port hands its work to the version without.")
            .practises("Overloading", "Delegation", "Choosing a version by its arguments")
            .spec(
                "Prompt Host: and read it, trimmed. Then prompt Port (blank for none): and read it, trimmed. A port that is not blank will be digits.",
                "Declare target(String host), returning the host in lower case, and target(String host, int port), returning target(host) + \":\" + port.",
                "Print Target: <result>, using the one-parameter version when the port is blank and the two-parameter version otherwise.")
            .needsMethod("static String target(String)")
            .needsMethod("static String target(String, int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Host: \");",
                "        String host = input.nextLine().trim();",
                "        System.out.print(\"Port (blank for none): \");",
                "        String port = input.nextLine().trim();",
                "        // choose a version of target, and print its result",
                "    }",
                "",
                "    // declare both versions of target here",
                "}")
            .hints(
                "Two headers, same name, different parameter lists: (String "
                + "host) and (String host, int port).",
                "The second one delegates:  return target(host) + \":\" + "
                + "port;  - so lower-casing lives in one place.",
                "In main, port.isEmpty() decides which to call. Parse the "
                + "port with Integer.parseInt only in the other branch.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Host: \");",
                "        String host = input.nextLine().trim();",
                "        System.out.print(\"Port (blank for none): \");",
                "        String port = input.nextLine().trim();",
                "        String result;",
                "        if (port.isEmpty()) {",
                "            result = target(host);",
                "        } else {",
                "            result = target(host, Integer.parseInt(port));",
                "        }",
                "        System.out.println(\"Target: \" + result);",
                "    }",
                "",
                "    static String target(String host) {",
                "        return host.toLowerCase();",
                "    }",
                "",
                "    static String target(String host, int port) {",
                "        return target(host) + \":\" + port;",
                "    }",
                "}")
            .walkthrough(
                "Java picks a version of target by its arguments: one String "
                + "gives the first, a String and an int give the second. The "
                + "second calls the first rather than repeating toLowerCase, "
                + "so the two can never format a host differently - which was "
                + "the whole complaint in the brief.\n"
                + "\n"
                + "parseInt only runs in the branch where the port is not "
                + "blank. Calling it on an empty line would crash; the if "
                + "makes sure it never sees one.")
            .sample(Lab.typing("WEB-01", "443"),
                "Host: WEB-01",
                "Port (blank for none): 443",
                "Target: web-01:443")
            .hidden(Lab.typing("db-02", ""),
                "Host: db-02",
                "Port (blank for none):",
                "Target: db-02")
            .hidden(Lab.typing("FW-Edge", "22"),
                "Host: FW-Edge",
                "Port (blank for none): 22",
                "Target: fw-edge:22")
            .hidden(Lab.typing("  Mail.Northstar.Example ", " 25 "),
                "Host:   Mail.Northstar.Example",
                "Port (blank for none):  25",
                "Target: mail.northstar.example:25"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(9), "Domain Of", Lab.SMALL)
            .stretch()
            .after("C03-M009")
            .brief(
                "Phishing triage starts with one question: which domain did "
                + "this come from? Write a method that pulls the domain out of "
                + "an email address - and refuses, politely, when the address "
                + "is not really an address.")
            .practises("Guard clauses", "indexOf and lastIndexOf", "Returning a fallback value")
            .spec(
                "Prompt Email: and read it, trimmed.",
                "Declare domainOf, which returns (none) when there is no @, when the @ is the first or last character, or when there is more than one @. Otherwise it returns everything after the @, in lower case.",
                "Print Domain: <domain>. When the domain is not (none), also print Internal: yes if it is northstar.example, otherwise Internal: no.")
            .needsMethod("static String domainOf(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Email: \");",
                "        String email = input.nextLine().trim();",
                "        String domain = email.substring(email.indexOf(\"@\") + 1);",
                "        System.out.println(\"Domain: \" + domain);",
                "    }",
                "}")
            .hints(
                "Find the @ once:  int at = email.indexOf(\"@\");",
                "Each bad case is its own guard returning \"(none)\": at == "
                + "-1, at == 0, at == email.length() - 1.",
                "More than one @ means the first and last are in different "
                + "places:  at != email.lastIndexOf(\"@\")",
                "In main, compare with equals:  domain.equals(\"(none)\")")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Email: \");",
                "        String email = input.nextLine().trim();",
                "        String domain = domainOf(email);",
                "        System.out.println(\"Domain: \" + domain);",
                "        if (!domain.equals(\"(none)\")) {",
                "            boolean internal = domain.equals(\"northstar.example\");",
                "            String answer = internal ? \"yes\" : \"no\";",
                "            System.out.println(\"Internal: \" + answer);",
                "        }",
                "    }",
                "",
                "    static String domainOf(String email) {",
                "        int at = email.indexOf(\"@\");",
                "        if (at == -1) {",
                "            return \"(none)\";",
                "        }",
                "        if (at == 0 || at == email.length() - 1) {",
                "            return \"(none)\";",
                "        }",
                "        if (at != email.lastIndexOf(\"@\")) {",
                "            return \"(none)\";",
                "        }",
                "        return email.substring(at + 1).toLowerCase();",
                "    }",
                "}")
            .walkthrough(
                "The starter works for good addresses and misbehaves for "
                + "everything else: with no @, indexOf gives -1, so substring(0) "
                + "returns the whole text as the 'domain'. Guard clauses fix "
                + "that one case at a time, and the last line only runs when "
                + "there is exactly one @ with something on each side.\n"
                + "\n"
                + "Lower-casing matters for the comparison: NorthStar.Example "
                + "is the same domain, and phishing filters that compare "
                + "domains case-sensitively are easy to slip past. Two @ "
                + "signs are refused rather than guessed at - different "
                + "mail systems read them differently, and attackers use "
                + "that.")
            .sample(Lab.typing("jsmith@NorthStar.Example"),
                "Email: jsmith@NorthStar.Example",
                "Domain: northstar.example",
                "Internal: yes")
            .hidden(Lab.typing("alerts@evil.example"),
                "Email: alerts@evil.example",
                "Domain: evil.example",
                "Internal: no")
            .hidden(Lab.typing("no-at-sign"),
                "Email: no-at-sign",
                "Domain: (none)")
            .hidden(Lab.typing("@northstar.example"),
                "Email: @northstar.example",
                "Domain: (none)")
            .hidden(Lab.typing("jsmith@"),
                "Email: jsmith@",
                "Domain: (none)")
            .hidden(Lab.typing("a@b@northstar.example"),
                "Email: a@b@northstar.example",
                "Domain: (none)")
            .hidden(Lab.typing("  M.Reyes@Partner.Example  "),
                "Email:   M.Reyes@Partner.Example",
                "Domain: partner.example",
                "Internal: no"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(10), "Clamp", Lab.SMALL)
            .stretch()
            .after("C03-M009")
            .brief(
                "A third-party feed sends severities that are supposed to be "
                + "1 to 10 and sometimes are not: 15, -3, 0. Write a general "
                + "clamp method - pull any value into a range - and use it to "
                + "tidy the feed.")
            .practises("Several parameters", "Guard clauses", "A reusable method")
            .spec(
                "Prompt Raw severity: and read a whole number.",
                "Declare clamp(value, low, high): it returns low if value is below low, high if value is above high, and value otherwise.",
                "Print Clamped: <clamp(raw, 1, 10)>. If that differs from the raw value, also print Note: out of range, adjusted.")
            .needsMethod("static int clamp(int, int, int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Raw severity: \");",
                "        int raw = Integer.parseInt(input.nextLine().trim());",
                "        // clamp raw to 1..10 and report",
                "    }",
                "",
                "    // declare clamp(int value, int low, int high) here",
                "}")
            .hints(
                "Three parameters, all int, in the order value, low, high.",
                "Two guards and a final return:\n"
                + "\n"
                + "    if (value < low) { return low; }\n"
                + "    if (value > high) { return high; }\n"
                + "    return value;",
                "In main, call it once and keep the result, then compare it "
                + "with raw.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Raw severity: \");",
                "        int raw = Integer.parseInt(input.nextLine().trim());",
                "        int clamped = clamp(raw, 1, 10);",
                "        System.out.println(\"Clamped: \" + clamped);",
                "        if (clamped != raw) {",
                "            System.out.println(\"Note: out of range, adjusted\");",
                "        }",
                "    }",
                "",
                "    static int clamp(int value, int low, int high) {",
                "        if (value < low) {",
                "            return low;",
                "        }",
                "        if (value > high) {",
                "            return high;",
                "        }",
                "        return value;",
                "    }",
                "}")
            .walkthrough(
                "clamp knows nothing about severities - the range comes in as "
                + "parameters - so the same method could clamp a port, a "
                + "percentage or a retry count. The order of the three "
                + "parameters matters and they are all ints, so a swapped call "
                + "like clamp(1, raw, 10) would compile and quietly give wrong "
                + "answers.\n"
                + "\n"
                + "Calling clamp once and storing the result means the value "
                + "printed and the value compared are the same one. The tests "
                + "include both ends, 1 and 10, which must NOT be reported as "
                + "adjusted.")
            .sample(Lab.typing("15"),
                "Raw severity: 15",
                "Clamped: 10",
                "Note: out of range, adjusted")
            .hidden(Lab.typing("-3"),
                "Raw severity: -3",
                "Clamped: 1",
                "Note: out of range, adjusted")
            .hidden(Lab.typing("7"),
                "Raw severity: 7",
                "Clamped: 7")
            .hidden(Lab.typing("1"),
                "Raw severity: 1",
                "Clamped: 1")
            .hidden(Lab.typing("10"),
                "Raw severity: 10",
                "Clamped: 10")
            .hidden(Lab.typing("0"),
                "Raw severity: 0",
                "Clamped: 1",
                "Note: out of range, adjusted")
            .hidden(Lab.typing("11"),
                "Raw severity: 11",
                "Clamped: 10",
                "Note: out of range, adjusted"));
    }
}
