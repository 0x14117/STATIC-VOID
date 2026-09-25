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

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(11), "Password Score", Lab.MEDIUM)
            .after("C03-M016")
            .brief(
                "The sign-up page needs a password meter, and the security "
                + "team has written the scoring rules. Each rule is one small "
                + "method; a scoring method adds them up; a rating method "
                + "turns the number into a word.")
            .practises("Small single-job methods", "Methods calling methods", "Boolean helpers")
            .spec(
                "Prompt Username: (trimmed) and Password: (exactly as typed). The username will not be blank.",
                "Points: 2 for 12 or more characters, or 1 for 8 to 11; 1 if it has an upper-case letter; 1 if it has a lower-case letter; 1 if it does NOT contain the username, ignoring case. A password on the common list scores 0, whatever else is true.",
                "The common list, ignoring case: password, qwerty, letmein, 123456, northstar, welcome.",
                "Rating: 0-2 WEAK, 3-4 FAIR, 5 STRONG. Print Score: <n>/5 and Rating: <rating>, then Note: on the common-password list for a common password.")
            .needsMethod("static boolean isCommon(String)")
            .needsMethod("static int lengthPoints(String)")
            .needsMethod("static boolean hasUpper(String)")
            .needsMethod("static boolean hasLower(String)")
            .needsMethod("static int score(String, String)")
            .needsMethod("static String rating(int)")
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
                "        // score, rate, report",
                "    }",
                "",
                "    // one method per rule, then score and rating",
                "}")
            .hints(
                "Text has an upper-case letter exactly when lower-casing it "
                + "changes it:  !s.equals(s.toLowerCase())  - and the same "
                + "trick with toUpperCase finds lower-case letters.",
                "isCommon is a switch expression on password.toLowerCase(), "
                + "with the six words in one case and default -> false.",
                "score starts with a guard - if (isCommon(password)) return "
                + "0; - and then adds the parts:\n"
                + "\n"
                + "    int points = lengthPoints(password);\n"
                + "    if (hasUpper(password)) { points++; }",
                "The username check lower-cases both sides:\n"
                + "\n"
                + "    password.toLowerCase().contains(user.toLowerCase())",
                "rating needs two guards and a last return: <= 2 WEAK, <= 4 "
                + "FAIR, otherwise STRONG.")
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
                "        int points = score(user, password);",
                "        System.out.println(\"Score: \" + points + \"/5\");",
                "        System.out.println(\"Rating: \" + rating(points));",
                "        if (isCommon(password)) {",
                "            System.out.println(\"Note: on the common-password list\");",
                "        }",
                "    }",
                "",
                "    static boolean isCommon(String password) {",
                "        return switch (password.toLowerCase()) {",
                "            case \"password\", \"qwerty\", \"letmein\",",
                "                 \"123456\", \"northstar\", \"welcome\" -> true;",
                "            default -> false;",
                "        };",
                "    }",
                "",
                "    static int lengthPoints(String password) {",
                "        if (password.length() >= 12) {",
                "            return 2;",
                "        }",
                "        if (password.length() >= 8) {",
                "            return 1;",
                "        }",
                "        return 0;",
                "    }",
                "",
                "    static boolean hasUpper(String s) {",
                "        return !s.equals(s.toLowerCase());",
                "    }",
                "",
                "    static boolean hasLower(String s) {",
                "        return !s.equals(s.toUpperCase());",
                "    }",
                "",
                "    static int score(String user, String password) {",
                "        if (isCommon(password)) {",
                "            return 0;",
                "        }",
                "        int points = lengthPoints(password);",
                "        if (hasUpper(password)) {",
                "            points++;",
                "        }",
                "        if (hasLower(password)) {",
                "            points++;",
                "        }",
                "        String lower = password.toLowerCase();",
                "        if (!lower.contains(user.toLowerCase())) {",
                "            points++;",
                "        }",
                "        return points;",
                "    }",
                "",
                "    static String rating(int points) {",
                "        if (points <= 2) {",
                "            return \"WEAK\";",
                "        }",
                "        if (points <= 4) {",
                "            return \"FAIR\";",
                "        }",
                "        return \"STRONG\";",
                "    }",
                "}")
            .walkthrough(
                "Six methods, each one rule. Read score and you read the "
                + "policy: common passwords get nothing, then length, upper "
                + "case, lower case and not-the-username each add points. "
                + "Change a rule and exactly one small method changes.\n"
                + "\n"
                + "hasUpper uses a neat trick that needs no loop: if "
                + "lower-casing the text changes it, there was an upper-case "
                + "letter in it. The common-list check lower-cases first, so "
                + "PASSWORD is caught too - attackers' wordlists try every "
                + "capitalisation.\n"
                + "\n"
                + "Real password meters work like this, and NIST guidance now "
                + "puts the most weight on length and on refusing known "
                + "passwords - the two rules that carry the most points "
                + "here.")
            .sample(Lab.typing("jsmith", "Correct-Horse-7"),
                "Username: jsmith",
                "Password: Correct-Horse-7",
                "Score: 5/5",
                "Rating: STRONG")
            .hidden(Lab.typing("jsmith", "password"),
                "Username: jsmith",
                "Password: password",
                "Score: 0/5",
                "Rating: WEAK",
                "Note: on the common-password list")
            .hidden(Lab.typing("jsmith", "PassWord"),
                "Username: jsmith",
                "Password: PassWord",
                "Score: 0/5",
                "Rating: WEAK",
                "Note: on the common-password list")
            .hidden(Lab.typing("jsmith", "jsmith2024!"),
                "Username: jsmith",
                "Password: jsmith2024!",
                "Score: 2/5",
                "Rating: WEAK")
            .hidden(Lab.typing("m.reyes", "SUMMER"),
                "Username: m.reyes",
                "Password: SUMMER",
                "Score: 2/5",
                "Rating: WEAK")
            .hidden(Lab.typing("m.reyes", "Tr0ub4dor"),
                "Username: m.reyes",
                "Password: Tr0ub4dor",
                "Score: 4/5",
                "Rating: FAIR")
            .hidden(Lab.typing("admin", "Admin12345678"),
                "Username: admin",
                "Password: Admin12345678",
                "Score: 4/5",
                "Rating: FAIR")
            .hidden(Lab.typing("m.reyes", "correcthorsebatterystaple"),
                "Username: m.reyes",
                "Password: correcthorsebatterystaple",
                "Score: 4/5",
                "Rating: FAIR"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(12), "Validation Library", Lab.MEDIUM)
            .after("C03-M026")
            .brief(
                "Before the remote-access request form goes live, every field "
                + "it accepts needs a validator - and the form needs one "
                + "method that combines them. Build the small library the "
                + "whole team will share.")
            .practises("A validation library", "Combining validators with &&", "Allow-list rules")
            .spec(
                "Prompt Username:, Port: and Host:, reading each line trimmed.",
                "isValidUsername: 3 to 20 characters, no spaces, and the first character is a letter.",
                "isValidPort: 1 to 5 digits, value 1 to 65535.",
                "isValidHost: not blank, at most 253 characters, no spaces, does not start or end with - or ., and has no .. anywhere.",
                "isValidRequest(user, port, host) is true only when all three are valid.",
                "Print username:, port: and host: each followed by OK or INVALID, then REQUEST: ACCEPTED or REQUEST: REJECTED.")
            .needsMethod("static boolean isValidUsername(String)")
            .needsMethod("static boolean isValidPort(String)")
            .needsMethod("static boolean isValidHost(String)")
            .needsMethod("static boolean isValidRequest(String, String, String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String user = input.nextLine().trim();",
                "        System.out.print(\"Port: \");",
                "        String port = input.nextLine().trim();",
                "        System.out.print(\"Host: \");",
                "        String host = input.nextLine().trim();",
                "        // report each field, then the request",
                "    }",
                "",
                "    // the four validators go here",
                "}")
            .hints(
                "A tiny helper keeps main short:\n"
                + "\n"
                + "    static String verdict(boolean ok) {\n"
                + "        return ok ? \"OK\" : \"INVALID\";\n"
                + "    }",
                "isValidUsername needs a length guard BEFORE charAt(0) - an "
                + "empty name has no first character. Then "
                + "Character.isLetter(name.charAt(0)).",
                "isValidHost is one long && chain of \"not\" rules: "
                + "!host.isBlank(), !host.contains(\" \"), "
                + "!host.startsWith(\"-\") and so on.",
                "isValidRequest just combines:  return isValidUsername(user) "
                + "&& isValidPort(port) && isValidHost(host);")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String user = input.nextLine().trim();",
                "        System.out.print(\"Port: \");",
                "        String port = input.nextLine().trim();",
                "        System.out.print(\"Host: \");",
                "        String host = input.nextLine().trim();",
                "        String u = verdict(isValidUsername(user));",
                "        System.out.println(\"username: \" + u);",
                "        System.out.println(\"port: \" + verdict(isValidPort(port)));",
                "        System.out.println(\"host: \" + verdict(isValidHost(host)));",
                "        boolean ok = isValidRequest(user, port, host);",
                "        String decision = ok ? \"ACCEPTED\" : \"REJECTED\";",
                "        System.out.println(\"REQUEST: \" + decision);",
                "    }",
                "",
                "    static String verdict(boolean ok) {",
                "        return ok ? \"OK\" : \"INVALID\";",
                "    }",
                "",
                "    static boolean isValidUsername(String name) {",
                "        if (name.length() < 3 || name.length() > 20) {",
                "            return false;",
                "        }",
                "        return !name.contains(\" \")",
                "                && Character.isLetter(name.charAt(0));",
                "    }",
                "",
                "    static boolean isValidPort(String text) {",
                "        if (text.length() > 5 || !text.matches(\"[0-9]+\")) {",
                "            return false;",
                "        }",
                "        int port = Integer.parseInt(text);",
                "        return port >= 1 && port <= 65535;",
                "    }",
                "",
                "    static boolean isValidHost(String host) {",
                "        return !host.isBlank() && host.length() <= 253",
                "                && !host.contains(\" \") && !host.contains(\"..\")",
                "                && !host.startsWith(\"-\") && !host.endsWith(\"-\")",
                "                && !host.startsWith(\".\") && !host.endsWith(\".\");",
                "    }",
                "",
                "    static boolean isValidRequest(String user, String port,",
                "                                  String host) {",
                "        return isValidUsername(user) && isValidPort(port)",
                "                && isValidHost(host);",
                "    }",
                "}")
            .walkthrough(
                "Each validator answers one question with guards and a final "
                + "rule, and isValidRequest contains no rules of its own - it "
                + "only combines. Tighten the host rule once and every form "
                + "that uses the library is tighter.\n"
                + "\n"
                + "The username guard checks the length before charAt(0), so an "
                + "empty name is refused instead of crashing. These are "
                + "allow-list rules: they describe what a good value looks "
                + "like, instead of trying to list every bad one. The hidden "
                + "tests aim at each rule in turn - a 20-character name that "
                + "must pass and a 21-character one that must not, a host "
                + "ending in a dot, a name starting with a digit.")
            .sample(Lab.typing("jsmith", "443", "web-01.northstar.example"),
                "Username: jsmith",
                "Port: 443",
                "Host: web-01.northstar.example",
                "username: OK",
                "port: OK",
                "host: OK",
                "REQUEST: ACCEPTED")
            .hidden(Lab.typing("js", "443", "web"),
                "Username: js",
                "Port: 443",
                "Host: web",
                "username: INVALID",
                "port: OK",
                "host: OK",
                "REQUEST: REJECTED")
            .hidden(Lab.typing("jsmith", "0", "web"),
                "Username: jsmith",
                "Port: 0",
                "Host: web",
                "username: OK",
                "port: INVALID",
                "host: OK",
                "REQUEST: REJECTED")
            .hidden(Lab.typing("jsmith", "22", "-web"),
                "Username: jsmith",
                "Port: 22",
                "Host: -web",
                "username: OK",
                "port: OK",
                "host: INVALID",
                "REQUEST: REJECTED")
            .hidden(Lab.typing("jsmith", "22", "web..example"),
                "Username: jsmith",
                "Port: 22",
                "Host: web..example",
                "username: OK",
                "port: OK",
                "host: INVALID",
                "REQUEST: REJECTED")
            .hidden(Lab.typing("j smith", "22", "web"),
                "Username: j smith",
                "Port: 22",
                "Host: web",
                "username: INVALID",
                "port: OK",
                "host: OK",
                "REQUEST: REJECTED")
            .hidden(Lab.typing("9lives", "22", "web"),
                "Username: 9lives",
                "Port: 22",
                "Host: web",
                "username: INVALID",
                "port: OK",
                "host: OK",
                "REQUEST: REJECTED")
            .hidden(Lab.typing("jsmith", "65535", "db-01."),
                "Username: jsmith",
                "Port: 65535",
                "Host: db-01.",
                "username: OK",
                "port: OK",
                "host: INVALID",
                "REQUEST: REJECTED")
            .hidden(Lab.typing("abcdefghijklmnopqrst", "8080", "a"),
                "Username: abcdefghijklmnopqrst",
                "Port: 8080",
                "Host: a",
                "username: OK",
                "port: OK",
                "host: OK",
                "REQUEST: ACCEPTED")
            .hidden(Lab.typing("abcdefghijklmnopqrstu", "8080", "a"),
                "Username: abcdefghijklmnopqrstu",
                "Port: 8080",
                "Host: a",
                "username: INVALID",
                "port: OK",
                "host: OK",
                "REQUEST: REJECTED")
            .hidden(Lab.typing("", "", ""),
                "Username:",
                "Port:",
                "Host:",
                "username: INVALID",
                "port: INVALID",
                "host: INVALID",
                "REQUEST: REJECTED"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(13), "Ticket ID Generator", Lab.MEDIUM)
            .stretch()
            .after("C03-M021")
            .brief(
                "Every incident gets a ticket number, INC-1001 upwards, and a "
                + "category taken from its title. The number must carry on "
                + "from one incident to the next - which is a job for the "
                + "one kind of variable that remembers between calls.")
            .practises("A static field", "Methods that share state", "Guard clauses")
            .spec(
                "Keep the next ticket number in a static int field, starting at 1001.",
                "Prompt Incident 1:, Incident 2: and Incident 3:, reading each title and trimming it. After each one, call logIncident(title).",
                "logIncident prints SKIPPED: empty title for a blank title and uses NO number. Otherwise it prints <id> [<category>] <title>, where the id comes from nextTicketId.",
                "nextTicketId returns INC- followed by the current number, then moves the number on by one.",
                "category: EMAIL if the title contains phish, IDENTITY if it contains password or login, ENDPOINT if it contains malware or virus, otherwise GENERAL - checked in that order, ignoring case.",
                "Finish with Tickets opened: <n>.")
            .needsMethod("static String nextTicketId()")
            .needsMethod("static String category(String)")
            .needsMethod("static void logIncident(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    // the next ticket number lives here",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Incident 1: \");",
                "        logIncident(input.nextLine().trim());",
                "        System.out.print(\"Incident 2: \");",
                "        logIncident(input.nextLine().trim());",
                "        System.out.print(\"Incident 3: \");",
                "        logIncident(input.nextLine().trim());",
                "        // print how many tickets were opened",
                "    }",
                "",
                "    static void logIncident(String title) {",
                "        int number = 1001;",
                "        System.out.println(\"INC-\" + number + \" \" + title);",
                "        number++;",
                "    }",
                "}")
            .hints(
                "The starter's number is a LOCAL, so every call starts again "
                + "at 1001. Move it up to the class:  static int nextNumber = "
                + "1001;",
                "nextTicketId builds the id first, then adds one:\n"
                + "\n"
                + "    String id = \"INC-\" + nextNumber;\n"
                + "    nextNumber++;\n"
                + "    return id;",
                "category lower-cases the title once, then returns from a "
                + "chain of ifs using contains.",
                "Tickets opened is how far the number has moved: nextNumber - "
                + "1001.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static int nextNumber = 1001;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Incident 1: \");",
                "        logIncident(input.nextLine().trim());",
                "        System.out.print(\"Incident 2: \");",
                "        logIncident(input.nextLine().trim());",
                "        System.out.print(\"Incident 3: \");",
                "        logIncident(input.nextLine().trim());",
                "        System.out.println(\"Tickets opened: \" + (nextNumber - 1001));",
                "    }",
                "",
                "    static void logIncident(String title) {",
                "        if (title.isBlank()) {",
                "            System.out.println(\"SKIPPED: empty title\");",
                "            return;",
                "        }",
                "        String id = nextTicketId();",
                "        String tag = \" [\" + category(title) + \"] \";",
                "        System.out.println(id + tag + title);",
                "    }",
                "",
                "    static String nextTicketId() {",
                "        String id = \"INC-\" + nextNumber;",
                "        nextNumber++;",
                "        return id;",
                "    }",
                "",
                "    static String category(String title) {",
                "        String t = title.toLowerCase();",
                "        if (t.contains(\"phish\")) {",
                "            return \"EMAIL\";",
                "        }",
                "        if (t.contains(\"password\") || t.contains(\"login\")) {",
                "            return \"IDENTITY\";",
                "        }",
                "        if (t.contains(\"malware\") || t.contains(\"virus\")) {",
                "            return \"ENDPOINT\";",
                "        }",
                "        return \"GENERAL\";",
                "    }",
                "}")
            .walkthrough(
                "The starter shows mission 12's trap: a local counter is "
                + "created fresh on every call, so every ticket would be "
                + "INC-1001. Moving the number into a static field gives it one "
                + "home for the whole program, and nextTicketId is the ONLY "
                + "method that changes it - which keeps the shared state "
                + "under control.\n"
                + "\n"
                + "The blank-title guard returns before nextTicketId is "
                + "called, so a skipped incident never burns a number. That "
                + "matters in real ticketing: gaps in the sequence look like "
                + "deleted tickets, which is the first thing an auditor asks "
                + "about.")
            .sample(Lab.typing("Phishing mail reported", "Password reset abuse", "Laptop running slow"),
                "Incident 1: Phishing mail reported",
                "INC-1001 [EMAIL] Phishing mail reported",
                "Incident 2: Password reset abuse",
                "INC-1002 [IDENTITY] Password reset abuse",
                "Incident 3: Laptop running slow",
                "INC-1003 [GENERAL] Laptop running slow",
                "Tickets opened: 3")
            .hidden(Lab.typing("", "Malware on WEB-01", "  VIRUS alert  "),
                "Incident 1:",
                "SKIPPED: empty title",
                "Incident 2: Malware on WEB-01",
                "INC-1001 [ENDPOINT] Malware on WEB-01",
                "Incident 3:   VIRUS alert",
                "INC-1002 [ENDPOINT] VIRUS alert",
                "Tickets opened: 2")
            .hidden(Lab.typing("Login storm from one IP", "   ", "PHISH kit hosted internally"),
                "Incident 1: Login storm from one IP",
                "INC-1001 [IDENTITY] Login storm from one IP",
                "Incident 2:",
                "SKIPPED: empty title",
                "Incident 3: PHISH kit hosted internally",
                "INC-1002 [EMAIL] PHISH kit hosted internally",
                "Tickets opened: 2")
            .hidden(Lab.typing("", "", ""),
                "Incident 1:",
                "SKIPPED: empty title",
                "Incident 2:",
                "SKIPPED: empty title",
                "Incident 3:",
                "SKIPPED: empty title",
                "Tickets opened: 0"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(14), "Risk Calculator", Lab.MEDIUM)
            .after("C03-M016")
            .brief(
                "The risk register scores every threat as likelihood times "
                + "impact, each on a scale of 1 to 5. Build the calculator as "
                + "a set of methods - parse, score, band, action - so the "
                + "same rules can later drive a dashboard.")
            .practises("Parsing with a sentinel value", "Methods in a pipeline", "Switch on a returned value")
            .spec(
                "Prompt Likelihood (1-5): and Impact (1-5):, reading each line trimmed.",
                "parseScale returns the number when the text is a single digit from 1 to 5, and -1 for anything else.",
                "If either value is -1, print INVALID INPUT: use 1 to 5 and stop.",
                "riskScore returns likelihood times impact. riskBand returns LOW for 1-4, MEDIUM for 5-9, HIGH for 10-16 and CRITICAL above that.",
                "action returns accept for LOW, monitor for MEDIUM, treat for HIGH and escalate now for CRITICAL.",
                "Print Risk score: <n>, Band: <band> and Action: <action>.")
            .needsMethod("static int parseScale(String)")
            .needsMethod("static int riskScore(int, int)")
            .needsMethod("static String riskBand(int)")
            .needsMethod("static String action(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Likelihood (1-5): \");",
                "        String l = input.nextLine().trim();",
                "        System.out.print(\"Impact (1-5): \");",
                "        String i = input.nextLine().trim();",
                "        // parse both, then score, band and action",
                "    }",
                "",
                "    // parseScale, riskScore, riskBand, action",
                "}")
            .hints(
                "parseScale: guard the shape first - length() == 1 and "
                + "matches(\"[0-9]+\") - then parse, then return -1 if the "
                + "number is below 1 or above 5.",
                "-1 is a SENTINEL: a value that can never be a real answer, so "
                + "it can safely mean 'bad input'.",
                "riskBand is a guard chain with upper limits: <= 4, <= 9, "
                + "<= 16, otherwise CRITICAL.",
                "action is a switch expression on the band text, with default "
                + "-> \"escalate now\".")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Likelihood (1-5): \");",
                "        int likelihood = parseScale(input.nextLine().trim());",
                "        System.out.print(\"Impact (1-5): \");",
                "        int impact = parseScale(input.nextLine().trim());",
                "        if (likelihood == -1 || impact == -1) {",
                "            System.out.println(\"INVALID INPUT: use 1 to 5\");",
                "        } else {",
                "            int score = riskScore(likelihood, impact);",
                "            String band = riskBand(score);",
                "            System.out.println(\"Risk score: \" + score);",
                "            System.out.println(\"Band: \" + band);",
                "            System.out.println(\"Action: \" + action(band));",
                "        }",
                "    }",
                "",
                "    static int parseScale(String text) {",
                "        if (text.length() != 1 || !text.matches(\"[0-9]+\")) {",
                "            return -1;",
                "        }",
                "        int value = Integer.parseInt(text);",
                "        if (value < 1 || value > 5) {",
                "            return -1;",
                "        }",
                "        return value;",
                "    }",
                "",
                "    static int riskScore(int likelihood, int impact) {",
                "        return likelihood * impact;",
                "    }",
                "",
                "    static String riskBand(int score) {",
                "        if (score <= 4) {",
                "            return \"LOW\";",
                "        }",
                "        if (score <= 9) {",
                "            return \"MEDIUM\";",
                "        }",
                "        if (score <= 16) {",
                "            return \"HIGH\";",
                "        }",
                "        return \"CRITICAL\";",
                "    }",
                "",
                "    static String action(String band) {",
                "        return switch (band) {",
                "            case \"LOW\" -> \"accept\";",
                "            case \"MEDIUM\" -> \"monitor\";",
                "            case \"HIGH\" -> \"treat\";",
                "            default -> \"escalate now\";",
                "        };",
                "    }",
                "}")
            .walkthrough(
                "The data flows through a pipeline of methods: text becomes a "
                + "scale value, two values become a score, the score becomes a "
                + "band, and the band becomes an action. Each step can be "
                + "tested alone, and a dashboard could reuse any of them.\n"
                + "\n"
                + "parseScale returns -1 for bad input - a sentinel, a value "
                + "no real answer can take - so main needs one check for both "
                + "fields. action's default is escalate now: if a new band is "
                + "ever added and forgotten here, the program fails towards "
                + "MORE caution, not less. That is failing closed.")
            .sample(Lab.typing("4", "5"),
                "Likelihood (1-5): 4",
                "Impact (1-5): 5",
                "Risk score: 20",
                "Band: CRITICAL",
                "Action: escalate now")
            .hidden(Lab.typing("1", "1"),
                "Likelihood (1-5): 1",
                "Impact (1-5): 1",
                "Risk score: 1",
                "Band: LOW",
                "Action: accept")
            .hidden(Lab.typing("2", "2"),
                "Likelihood (1-5): 2",
                "Impact (1-5): 2",
                "Risk score: 4",
                "Band: LOW",
                "Action: accept")
            .hidden(Lab.typing("1", "5"),
                "Likelihood (1-5): 1",
                "Impact (1-5): 5",
                "Risk score: 5",
                "Band: MEDIUM",
                "Action: monitor")
            .hidden(Lab.typing("3", "3"),
                "Likelihood (1-5): 3",
                "Impact (1-5): 3",
                "Risk score: 9",
                "Band: MEDIUM",
                "Action: monitor")
            .hidden(Lab.typing("2", "5"),
                "Likelihood (1-5): 2",
                "Impact (1-5): 5",
                "Risk score: 10",
                "Band: HIGH",
                "Action: treat")
            .hidden(Lab.typing("4", "4"),
                "Likelihood (1-5): 4",
                "Impact (1-5): 4",
                "Risk score: 16",
                "Band: HIGH",
                "Action: treat")
            .hidden(Lab.typing("5", "5"),
                "Likelihood (1-5): 5",
                "Impact (1-5): 5",
                "Risk score: 25",
                "Band: CRITICAL",
                "Action: escalate now")
            .hidden(Lab.typing("0", "3"),
                "Likelihood (1-5): 0",
                "Impact (1-5): 3",
                "INVALID INPUT: use 1 to 5")
            .hidden(Lab.typing("3", "six"),
                "Likelihood (1-5): 3",
                "Impact (1-5): six",
                "INVALID INPUT: use 1 to 5")
            .hidden(Lab.typing("", "2"),
                "Likelihood (1-5):",
                "Impact (1-5): 2",
                "INVALID INPUT: use 1 to 5")
            .hidden(Lab.typing("12", "1"),
                "Likelihood (1-5): 12",
                "Impact (1-5): 1",
                "INVALID INPUT: use 1 to 5"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(15), "Guard Clauses", Lab.MEDIUM)
            .stretch()
            .after("C03-M027")
            .brief(
                "The payments team's transfer check is five ifs deep, and two "
                + "of its limits are off by one - nobody noticed, because "
                + "nobody can read it. Rewrite it as guard clauses, in the "
                + "policy's order, and the bugs have nowhere to hide.")
            .practises("Rewriting nested ifs as guards", "Fail-closed order", "Boundaries")
            .spec(
                "Prompt User: (trimmed), Amount: (a whole number), MFA passed (y/n): and Already sent today: (a whole number).",
                "checkTransfer returns the FIRST that applies: blank user - REFUSED: no user; amount 0 or less - REFUSED: amount must be positive; amount over 10000 - REFUSED: over the single-transfer limit; no MFA and amount over 1000 - REFUSED: MFA needed over 1000; today's total including this transfer over 20000 - REFUSED: daily limit reached; otherwise APPROVED.",
                "An amount of exactly 1000 needs no MFA, and the daily total may reach exactly 20000.",
                "Print the result.")
            .needsMethod("static String checkTransfer(String, int, boolean, int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"User: \");",
                "        String user = input.nextLine().trim();",
                "        System.out.print(\"Amount: \");",
                "        int amount = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"MFA passed (y/n): \");",
                "        boolean mfa = input.nextLine().trim().equalsIgnoreCase(\"y\");",
                "        System.out.print(\"Already sent today: \");",
                "        int sent = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(checkTransfer(user, amount, mfa, sent));",
                "    }",
                "",
                "    static String checkTransfer(String user, int amount,",
                "                                boolean mfa, int sentToday) {",
                "        if (!user.isBlank()) {",
                "            if (amount > 0) {",
                "                if (amount <= 10000) {",
                "                    if (mfa || amount < 1000) {",
                "                        if (sentToday + amount < 20000) {",
                "                            return \"APPROVED\";",
                "                        }",
                "                        return \"REFUSED: daily limit reached\";",
                "                    }",
                "                    return \"REFUSED: MFA needed over 1000\";",
                "                }",
                "                return \"REFUSED: over the single-transfer limit\";",
                "            }",
                "            return \"REFUSED: amount must be positive\";",
                "        }",
                "        return \"REFUSED: no user\";",
                "    }",
                "}")
            .hints(
                "Turn each level inside out: instead of 'if the user is not "
                + "blank, go deeper', write 'if the user IS blank, return the "
                + "refusal'.",
                "Keep the policy's order: user, amount, single limit, MFA, "
                + "daily limit. APPROVED is the last line, with no if around "
                + "it.",
                "Read the spec's boundaries carefully. 'Over 1000' means "
                + "> 1000, so the MFA guard is  !mfa && amount > 1000.",
                "'Over 20000' also means >: the daily guard is  sentToday + "
                + "amount > 20000.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"User: \");",
                "        String user = input.nextLine().trim();",
                "        System.out.print(\"Amount: \");",
                "        int amount = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"MFA passed (y/n): \");",
                "        boolean mfa = input.nextLine().trim().equalsIgnoreCase(\"y\");",
                "        System.out.print(\"Already sent today: \");",
                "        int sent = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(checkTransfer(user, amount, mfa, sent));",
                "    }",
                "",
                "    static String checkTransfer(String user, int amount,",
                "                                boolean mfa, int sentToday) {",
                "        if (user.isBlank()) {",
                "            return \"REFUSED: no user\";",
                "        }",
                "        if (amount <= 0) {",
                "            return \"REFUSED: amount must be positive\";",
                "        }",
                "        if (amount > 10000) {",
                "            return \"REFUSED: over the single-transfer limit\";",
                "        }",
                "        if (!mfa && amount > 1000) {",
                "            return \"REFUSED: MFA needed over 1000\";",
                "        }",
                "        if (sentToday + amount > 20000) {",
                "            return \"REFUSED: daily limit reached\";",
                "        }",
                "        return \"APPROVED\";",
                "    }",
                "}")
            .walkthrough(
                "Flattened, each rule is one guard: a condition that "
                + "describes the BAD case and returns its refusal. Reading "
                + "top to bottom is now reading the policy, and APPROVED sits "
                + "alone at the bottom, reachable only past every check - "
                + "fail closed by construction.\n"
                + "\n"
                + "The two bugs were the inverted conditions: amount < 1000 "
                + "refused exactly 1000 without MFA, and < 20000 refused a "
                + "day that lands exactly on the limit. Nested, the "
                + "conditions describe the GOOD case, so their boundaries are "
                + "the opposite of the policy's wording - an easy place for "
                + "an off-by-one to hide. As guards, each condition reads "
                + "just like the rule it enforces.")
            .sample(Lab.typing("jsmith", "500", "n", "0"),
                "User: jsmith",
                "Amount: 500",
                "MFA passed (y/n): n",
                "Already sent today: 0",
                "APPROVED")
            .hidden(Lab.typing("", "500", "y", "0"),
                "User:",
                "Amount: 500",
                "MFA passed (y/n): y",
                "Already sent today: 0",
                "REFUSED: no user")
            .hidden(Lab.typing("jsmith", "0", "y", "0"),
                "User: jsmith",
                "Amount: 0",
                "MFA passed (y/n): y",
                "Already sent today: 0",
                "REFUSED: amount must be positive")
            .hidden(Lab.typing("jsmith", "10001", "y", "0"),
                "User: jsmith",
                "Amount: 10001",
                "MFA passed (y/n): y",
                "Already sent today: 0",
                "REFUSED: over the single-transfer limit")
            .hidden(Lab.typing("jsmith", "10000", "y", "0"),
                "User: jsmith",
                "Amount: 10000",
                "MFA passed (y/n): y",
                "Already sent today: 0",
                "APPROVED")
            .hidden(Lab.typing("jsmith", "1000", "n", "0"),
                "User: jsmith",
                "Amount: 1000",
                "MFA passed (y/n): n",
                "Already sent today: 0",
                "APPROVED")
            .hidden(Lab.typing("jsmith", "1001", "n", "0"),
                "User: jsmith",
                "Amount: 1001",
                "MFA passed (y/n): n",
                "Already sent today: 0",
                "REFUSED: MFA needed over 1000")
            .hidden(Lab.typing("jsmith", "5000", "y", "15000"),
                "User: jsmith",
                "Amount: 5000",
                "MFA passed (y/n): y",
                "Already sent today: 15000",
                "APPROVED")
            .hidden(Lab.typing("jsmith", "5001", "y", "15000"),
                "User: jsmith",
                "Amount: 5001",
                "MFA passed (y/n): y",
                "Already sent today: 15000",
                "REFUSED: daily limit reached")
            .hidden(Lab.typing("   ", "50", "Y", "0"),
                "User:",
                "Amount: 50",
                "MFA passed (y/n): Y",
                "Already sent today: 0",
                "REFUSED: no user"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(16), "Unit Conversion Kit", Lab.MEDIUM)
            .stretch()
            .after("C03-M016")
            .brief(
                "The storage report shows raw byte counts nobody can read, "
                + "and the quota check has been quietly wrong since quotas "
                + "went above 1 GB. Build the conversion kit: human-readable "
                + "sizes, and a quota calculation that cannot overflow.")
            .practises("long and int", "Methods calling methods", "Formatting decimals")
            .spec(
                "Prompt Bytes used: and read a whole number (it can be larger than an int holds, so read it with Long.parseLong). Then prompt Quota (GB): and read a whole number from 0 to 1000.",
                "humanSize(bytes): under 1024 gives <n> B; under 1024 x 1024 gives KB; under 1024 x 1024 x 1024 gives MB; otherwise GB. KB, MB and GB show one decimal place, using oneDecimal.",
                "oneDecimal(value) returns the value with exactly one decimal place. gbToBytes(gb) returns the quota in bytes, as a long.",
                "Print Used: <humanSize>, Quota: <gb> GB (<bytes> bytes) and Over quota: yes if the bytes used are more than the quota, otherwise no.")
            .needsMethod("static String humanSize(long)")
            .needsMethod("static String oneDecimal(double)")
            .needsMethod("static long gbToBytes(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Bytes used: \");",
                "        long used = Long.parseLong(input.nextLine().trim());",
                "        System.out.print(\"Quota (GB): \");",
                "        int gb = Integer.parseInt(input.nextLine().trim());",
                "        long quota = gbToBytes(gb);",
                "        System.out.println(\"Quota: \" + gb + \" GB (\" + quota",
                "                + \" bytes)\");",
                "    }",
                "",
                "    static long gbToBytes(int gb) {",
                "        return gb * 1024 * 1024 * 1024;",
                "    }",
                "}")
            .hints(
                "Run the starter with a quota of 2. The answer is negative: "
                + "gb * 1024 * 1024 * 1024 is worked out in INT arithmetic and "
                + "overflows before it ever becomes a long.",
                "Make the first value a long so the whole calculation is done "
                + "in longs:  return gb * 1024L * 1024 * 1024;",
                "oneDecimal is one line:  return String.format(\"%.1f\", "
                + "value);",
                "humanSize is a guard chain. Divide by 1024.0 (a double), not "
                + "1024, so 1536 bytes gives 1.5 and not 1.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Bytes used: \");",
                "        long used = Long.parseLong(input.nextLine().trim());",
                "        System.out.print(\"Quota (GB): \");",
                "        int gb = Integer.parseInt(input.nextLine().trim());",
                "        long quota = gbToBytes(gb);",
                "        System.out.println(\"Used: \" + humanSize(used));",
                "        System.out.println(\"Quota: \" + gb + \" GB (\" + quota",
                "                + \" bytes)\");",
                "        String over = used > quota ? \"yes\" : \"no\";",
                "        System.out.println(\"Over quota: \" + over);",
                "    }",
                "",
                "    static long gbToBytes(int gb) {",
                "        return gb * 1024L * 1024 * 1024;",
                "    }",
                "",
                "    static String oneDecimal(double value) {",
                "        return String.format(\"%.1f\", value);",
                "    }",
                "",
                "    static String humanSize(long bytes) {",
                "        if (bytes < 1024) {",
                "            return bytes + \" B\";",
                "        }",
                "        if (bytes < 1024L * 1024) {",
                "            return oneDecimal(bytes / 1024.0) + \" KB\";",
                "        }",
                "        if (bytes < 1024L * 1024 * 1024) {",
                "            return oneDecimal(bytes / (1024.0 * 1024)) + \" MB\";",
                "        }",
                "        return oneDecimal(bytes / (1024.0 * 1024 * 1024)) + \" GB\";",
                "    }",
                "}")
            .walkthrough(
                "The starter's bug is Campaign 01's integer overflow, hiding "
                + "inside a method whose return type LOOKS safe. The return "
                + "type is long, but gb * 1024 * 1024 * 1024 is worked out "
                + "entirely in ints - the widening to long only happens after "
                + "the damage. 1024L makes the first multiplication a long "
                + "one, and every step after it follows.\n"
                + "\n"
                + "One hidden test uses 1073741825 bytes against a 1 GB quota: "
                + "the report shows 1.0 GB, yet the quota IS exceeded, by one "
                + "byte. Rounded display values are for people; decisions "
                + "must use the exact numbers - which is why the comparison "
                + "uses used and quota, never the formatted text.")
            .sample(Lab.typing("1610612736", "2"),
                "Bytes used: 1610612736",
                "Quota (GB): 2",
                "Used: 1.5 GB",
                "Quota: 2 GB (2147483648 bytes)",
                "Over quota: no")
            .hidden(Lab.typing("512", "1"),
                "Bytes used: 512",
                "Quota (GB): 1",
                "Used: 512 B",
                "Quota: 1 GB (1073741824 bytes)",
                "Over quota: no")
            .hidden(Lab.typing("1536", "1"),
                "Bytes used: 1536",
                "Quota (GB): 1",
                "Used: 1.5 KB",
                "Quota: 1 GB (1073741824 bytes)",
                "Over quota: no")
            .hidden(Lab.typing("1048576", "1"),
                "Bytes used: 1048576",
                "Quota (GB): 1",
                "Used: 1.0 MB",
                "Quota: 1 GB (1073741824 bytes)",
                "Over quota: no")
            .hidden(Lab.typing("1073741824", "1"),
                "Bytes used: 1073741824",
                "Quota (GB): 1",
                "Used: 1.0 GB",
                "Quota: 1 GB (1073741824 bytes)",
                "Over quota: no")
            .hidden(Lab.typing("1073741825", "1"),
                "Bytes used: 1073741825",
                "Quota (GB): 1",
                "Used: 1.0 GB",
                "Quota: 1 GB (1073741824 bytes)",
                "Over quota: yes")
            .hidden(Lab.typing("5368709120", "4"),
                "Bytes used: 5368709120",
                "Quota (GB): 4",
                "Used: 5.0 GB",
                "Quota: 4 GB (4294967296 bytes)",
                "Over quota: yes")
            .hidden(Lab.typing("0", "0"),
                "Bytes used: 0",
                "Quota (GB): 0",
                "Used: 0 B",
                "Quota: 0 GB (0 bytes)",
                "Over quota: no"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(17), "Stack Trace Detective", Lab.MEDIUM)
            .after("C03-M018")
            .brief(
                "The response-time report crashes most nights, and the only "
                + "clue is a stack trace in the job log. The code works on "
                + "the sample. Your job: run it on the hidden cases, read the "
                + "traces, find the lines, and guard them.")
            .practises("Reading a stack trace", "Guard clauses", "Fixing the cause, not the symptom")
            .spec(
                "Prompt URL:, Total response ms: and Requests:. The URL is trimmed; the two numbers are whole numbers.",
                "hostOf(url) returns the part before the first /, or the whole URL if there is no /. If that part is empty, it returns (unknown).",
                "average(total, count) returns total / count, or 0 when count is 0.",
                "Print Host: <host> and Average: <average> ms.")
            .needsMethod("static String hostOf(String)")
            .needsMethod("static int average(int, int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"URL: \");",
                "        String url = input.nextLine().trim();",
                "        System.out.print(\"Total response ms: \");",
                "        int total = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Requests: \");",
                "        int count = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(\"Host: \" + hostOf(url));",
                "        int avg = average(total, count);",
                "        System.out.println(\"Average: \" + avg + \" ms\");",
                "    }",
                "",
                "    static String hostOf(String url) {",
                "        return url.substring(0, url.indexOf(\"/\"));",
                "    }",
                "",
                "    static int average(int total, int count) {",
                "        return total / count;",
                "    }",
                "}")
            .hints(
                "Use Run my program myself with a URL that has no / in it, "
                + "such as db-02. Read the trace top down: the first 'at' "
                + "line in Main names the method and the line.",
                "The first trace is a StringIndexOutOfBoundsException in "
                + "hostOf: indexOf returned -1, and substring(0, -1) is "
                + "impossible. Guard it: find the slash first, and return "
                + "the whole URL when it is -1.",
                "Now try 0 requests: ArithmeticException: / by zero, in "
                + "average. One guard: if (count == 0) return 0;",
                "The (unknown) case: a URL starting with / leaves nothing "
                + "before it. Check host.isEmpty() before returning.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"URL: \");",
                "        String url = input.nextLine().trim();",
                "        System.out.print(\"Total response ms: \");",
                "        int total = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Requests: \");",
                "        int count = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(\"Host: \" + hostOf(url));",
                "        int avg = average(total, count);",
                "        System.out.println(\"Average: \" + avg + \" ms\");",
                "    }",
                "",
                "    static String hostOf(String url) {",
                "        int slash = url.indexOf(\"/\");",
                "        String host = slash == -1 ? url : url.substring(0, slash);",
                "        if (host.isEmpty()) {",
                "            return \"(unknown)\";",
                "        }",
                "        return host;",
                "    }",
                "",
                "    static int average(int total, int count) {",
                "        if (count == 0) {",
                "            return 0;",
                "        }",
                "        return total / count;",
                "    }",
                "}")
            .walkthrough(
                "Each crash leaves a trace whose top 'at Main...' line names "
                + "the method and the line: hostOf's substring, and "
                + "average's division. The message names the cause - "
                + "'begin 0, end -1' says indexOf found nothing; '/ by zero' "
                + "needs no explanation.\n"
                + "\n"
                + "The fix is always a guard BEFORE the dangerous line, never "
                + "a change to main: every caller of hostOf and average is now "
                + "safe, not just this one. Note what the guards return - a "
                + "sensible value the spec defines, not a made-up one. A "
                + "report that says Average: 0 ms for zero requests is "
                + "honest; one that crashes every night reports nothing at "
                + "all.")
            .sample(Lab.typing("web-01.northstar.example/login", "900", "3"),
                "URL: web-01.northstar.example/login",
                "Total response ms: 900",
                "Requests: 3",
                "Host: web-01.northstar.example",
                "Average: 300 ms")
            .hidden(Lab.typing("db-02", "500", "5"),
                "URL: db-02",
                "Total response ms: 500",
                "Requests: 5",
                "Host: db-02",
                "Average: 100 ms")
            .hidden(Lab.typing("api.northstar.example/v1/users", "0", "0"),
                "URL: api.northstar.example/v1/users",
                "Total response ms: 0",
                "Requests: 0",
                "Host: api.northstar.example",
                "Average: 0 ms")
            .hidden(Lab.typing("/admin", "10", "2"),
                "URL: /admin",
                "Total response ms: 10",
                "Requests: 2",
                "Host: (unknown)",
                "Average: 5 ms")
            .hidden(Lab.typing("cdn-7", "7", "0"),
                "URL: cdn-7",
                "Total response ms: 7",
                "Requests: 0",
                "Host: cdn-7",
                "Average: 0 ms"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(18), "Recursion Countdown", Lab.MEDIUM)
            .stretch()
            .after("C03-M019")
            .brief(
                "After a failed connection, the agent waits a few seconds and "
                + "counts down before retrying. There are no loops yet - but a "
                + "method that calls itself can count down just as well, as "
                + "long as it knows when to stop.")
            .practises("Recursion", "A base case", "Validating before recursing")
            .spec(
                "Prompt Retry delay (0-10): and read a whole number.",
                "isValidDelay returns true for 0 to 10. For anything else, print INVALID DELAY and nothing more.",
                "countdown(n) prints Retry in <n>... for each number from n down to 1, then Retrying now. It must call itself - no loops.")
            .needsMethod("static void countdown(int)")
            .needsMethod("static boolean isValidDelay(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Retry delay (0-10): \");",
                "        int delay = Integer.parseInt(input.nextLine().trim());",
                "        countdown(delay);",
                "    }",
                "",
                "    static void countdown(int n) {",
                "        System.out.println(\"Retry in \" + n + \"...\");",
                "        // what happens next?",
                "    }",
                "}")
            .hints(
                "Every recursive method needs a base case. Here it is n == 0: "
                + "print Retrying now and return.",
                "Otherwise print this step, then call countdown(n - 1) - the "
                + "same job, one smaller.",
                "Validate in main BEFORE calling countdown. A negative delay "
                + "would step 0 and never reach it: -1, -2, -3... until "
                + "StackOverflowError.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Retry delay (0-10): \");",
                "        int delay = Integer.parseInt(input.nextLine().trim());",
                "        if (isValidDelay(delay)) {",
                "            countdown(delay);",
                "        } else {",
                "            System.out.println(\"INVALID DELAY\");",
                "        }",
                "    }",
                "",
                "    static boolean isValidDelay(int n) {",
                "        return n >= 0 && n <= 10;",
                "    }",
                "",
                "    static void countdown(int n) {",
                "        if (n == 0) {",
                "            System.out.println(\"Retrying now\");",
                "            return;",
                "        }",
                "        System.out.println(\"Retry in \" + n + \"...\");",
                "        countdown(n - 1);",
                "    }",
                "}")
            .walkthrough(
                "countdown(3) prints its line and calls countdown(2), which "
                + "calls countdown(1), which calls countdown(0) - the base "
                + "case, which prints Retrying now and returns. Then the "
                + "frames pop, one by one, with nothing left to do.\n"
                + "\n"
                + "Validation protects the recursion. countdown(-1) would skip "
                + "straight past 0 and never stop - a StackOverflowError after "
                + "thousands of frames. Checking the input before recursing "
                + "is the same defence real parsers use: refuse input that "
                + "would send the recursion somewhere it can never come back "
                + "from.")
            .sample(Lab.typing("3"),
                "Retry delay (0-10): 3",
                "Retry in 3...",
                "Retry in 2...",
                "Retry in 1...",
                "Retrying now")
            .hidden(Lab.typing("0"),
                "Retry delay (0-10): 0",
                "Retrying now")
            .hidden(Lab.typing("1"),
                "Retry delay (0-10): 1",
                "Retry in 1...",
                "Retrying now")
            .hidden(Lab.typing("10"),
                "Retry delay (0-10): 10",
                "Retry in 10...",
                "Retry in 9...",
                "Retry in 8...",
                "Retry in 7...",
                "Retry in 6...",
                "Retry in 5...",
                "Retry in 4...",
                "Retry in 3...",
                "Retry in 2...",
                "Retry in 1...",
                "Retrying now")
            .hidden(Lab.typing("11"),
                "Retry delay (0-10): 11",
                "INVALID DELAY")
            .hidden(Lab.typing("-1"),
                "Retry delay (0-10): -1",
                "INVALID DELAY"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(19), "Recursive Sum of Digits", Lab.MEDIUM)
            .stretch()
            .after("C03-M019")
            .brief(
                "Old check-digit schemes start by adding up a number's digits, "
                + "then adding up THAT, until one digit is left - its digital "
                + "root. Both are natural recursions: a number is its last "
                + "digit plus the rest.")
            .practises("Recursion with a return value", "Recursion calling recursion", "% and / on digits")
            .spec(
                "Prompt Number: and read the line, trimmed. It must be 1 to 9 digits; otherwise print INVALID NUMBER and stop.",
                "digitSum(n): a number under 10 is its own sum; otherwise it is the last digit (n % 10) plus digitSum of the rest (n / 10).",
                "digitalRoot(n): a number under 10 is its own root; otherwise it is digitalRoot of digitSum(n).",
                "Print Digit sum: <sum> and Digital root: <root>.")
            .needsMethod("static boolean isDigits(String)")
            .needsMethod("static int digitSum(int)")
            .needsMethod("static int digitalRoot(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Number: \");",
                "        String text = input.nextLine().trim();",
                "        // validate, then use the two recursive methods",
                "    }",
                "",
                "    // isDigits, digitSum, digitalRoot",
                "}")
            .hints(
                "isDigits: length 1 to 9 and matches(\"[0-9]+\"). Nine digits "
                + "always fit in an int.",
                "n % 10 is the last digit and n / 10 is everything before it: "
                + "4096 % 10 is 6, 4096 / 10 is 409.",
                "digitSum's base case is n < 10. Otherwise:  return n % 10 + "
                + "digitSum(n / 10);",
                "digitalRoot's recursive case calls digitSum first, then "
                + "itself:  return digitalRoot(digitSum(n));")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Number: \");",
                "        String text = input.nextLine().trim();",
                "        if (!isDigits(text)) {",
                "            System.out.println(\"INVALID NUMBER\");",
                "        } else {",
                "            int n = Integer.parseInt(text);",
                "            System.out.println(\"Digit sum: \" + digitSum(n));",
                "            System.out.println(\"Digital root: \" + digitalRoot(n));",
                "        }",
                "    }",
                "",
                "    static boolean isDigits(String text) {",
                "        return text.length() >= 1 && text.length() <= 9",
                "                && text.matches(\"[0-9]+\");",
                "    }",
                "",
                "    static int digitSum(int n) {",
                "        if (n < 10) {",
                "            return n;",
                "        }",
                "        return n % 10 + digitSum(n / 10);",
                "    }",
                "",
                "    static int digitalRoot(int n) {",
                "        if (n < 10) {",
                "            return n;",
                "        }",
                "        return digitalRoot(digitSum(n));",
                "    }",
                "}")
            .walkthrough(
                "digitSum(4096) is 6 + digitSum(409), which is 9 + "
                + "digitSum(40), which is 0 + digitSum(4) - the base case. "
                + "The frames return 4, 4, 13 and finally 19. Every call "
                + "works on a smaller number, so the base case is always "
                + "reached.\n"
                + "\n"
                + "digitalRoot recurses on a DIFFERENT method's answer: 19 "
                + "becomes 10, then 1. The nine-digit limit keeps parseInt "
                + "safe; the hidden tests include 999999999, the largest "
                + "allowed, and a ten-digit number that must be refused. "
                + "(Digit sums are too weak for real integrity checks - the "
                + "Luhn check in lab 29 is the next step up.)")
            .sample(Lab.typing("4096"),
                "Number: 4096",
                "Digit sum: 19",
                "Digital root: 1")
            .hidden(Lab.typing("7"),
                "Number: 7",
                "Digit sum: 7",
                "Digital root: 7")
            .hidden(Lab.typing("999999999"),
                "Number: 999999999",
                "Digit sum: 81",
                "Digital root: 9")
            .hidden(Lab.typing("1000"),
                "Number: 1000",
                "Digit sum: 1",
                "Digital root: 1")
            .hidden(Lab.typing("0"),
                "Number: 0",
                "Digit sum: 0",
                "Digital root: 0")
            .hidden(Lab.typing("12a"),
                "Number: 12a",
                "INVALID NUMBER")
            .hidden(Lab.typing(""),
                "Number:",
                "INVALID NUMBER")
            .hidden(Lab.typing("1234567890"),
                "Number: 1234567890",
                "INVALID NUMBER"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(20), "Refactor the Mess", Lab.MEDIUM)
            .after("C03-M025")
            .brief(
                "The account status page copies the same lockout rule three "
                + "times, with a bare 5 in each - and one copy says > where "
                + "the others say >=. The page and its own summary line now "
                + "disagree. Refactor it: one constant, one rule, used "
                + "everywhere.")
            .practises("Refactoring", "Extracting methods", "Class constants")
            .spec(
                "Prompt jsmith failures:, m.reyes failures: and svc-backup failures:, reading whole numbers.",
                "An account is locked at 5 failures or more. Keep the 5 in a constant, MAX_FAILURES.",
                "isLocked(failures) answers the rule; status(failures) returns LOCKED or active using it; lockedCount(a, b, c) returns how many of the three are locked, also using it.",
                "Print jsmith: <status>, m.reyes: <status>, svc-backup: <status>, then Locked accounts: <count>.")
            .needsMethod("static boolean isLocked(int)")
            .needsMethod("static String status(int)")
            .needsMethod("static int lockedCount(int, int, int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"jsmith failures: \");",
                "        int a = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"m.reyes failures: \");",
                "        int b = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"svc-backup failures: \");",
                "        int c = Integer.parseInt(input.nextLine().trim());",
                "        String sa = a >= 5 ? \"LOCKED\" : \"active\";",
                "        String sb = b >= 5 ? \"LOCKED\" : \"active\";",
                "        String sc = c > 5 ? \"LOCKED\" : \"active\";",
                "        System.out.println(\"jsmith: \" + sa);",
                "        System.out.println(\"m.reyes: \" + sb);",
                "        System.out.println(\"svc-backup: \" + sc);",
                "        int locked = (a >= 5 ? 1 : 0) + (b >= 5 ? 1 : 0)",
                "                + (c >= 5 ? 1 : 0);",
                "        System.out.println(\"Locked accounts: \" + locked);",
                "    }",
                "}")
            .hints(
                "Before changing anything, run the starter with 7, 2 and 5. "
                + "svc-backup shows active - but the summary counts it as "
                + "locked. That disagreement is the bug.",
                "Step 1: add  static final int MAX_FAILURES = 5;  to the "
                + "class. Step 2: isLocked returns failures >= MAX_FAILURES.",
                "Step 3: status uses isLocked:  return isLocked(failures) ? "
                + "\"LOCKED\" : \"active\";",
                "Step 4: lockedCount adds three (isLocked(x) ? 1 : 0) terms. "
                + "Then main just calls the methods.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int MAX_FAILURES = 5;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"jsmith failures: \");",
                "        int a = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"m.reyes failures: \");",
                "        int b = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"svc-backup failures: \");",
                "        int c = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(\"jsmith: \" + status(a));",
                "        System.out.println(\"m.reyes: \" + status(b));",
                "        System.out.println(\"svc-backup: \" + status(c));",
                "        int locked = lockedCount(a, b, c);",
                "        System.out.println(\"Locked accounts: \" + locked);",
                "    }",
                "",
                "    static boolean isLocked(int failures) {",
                "        return failures >= MAX_FAILURES;",
                "    }",
                "",
                "    static String status(int failures) {",
                "        return isLocked(failures) ? \"LOCKED\" : \"active\";",
                "    }",
                "",
                "    static int lockedCount(int a, int b, int c) {",
                "        return (isLocked(a) ? 1 : 0) + (isLocked(b) ? 1 : 0)",
                "                + (isLocked(c) ? 1 : 0);",
                "    }",
                "}")
            .walkthrough(
                "After the refactor, the rule exists in exactly one line - "
                + "isLocked - and both status and lockedCount go through it. "
                + "They cannot disagree any more, because there is nothing "
                + "left to disagree about. The magic 5 is now MAX_FAILURES, "
                + "so the next policy change is a one-line edit.\n"
                + "\n"
                + "This refactoring also FIXED a bug, which a pure refactoring "
                + "would not: the copy-paste > was the bug, and removing the "
                + "copies removed it. That is common - duplicated rules drift, "
                + "and the drift is where security holes appear. The hidden "
                + "tests put accounts exactly on 5 to check every path agrees.")
            .sample(Lab.typing("7", "2", "5"),
                "jsmith failures: 7",
                "m.reyes failures: 2",
                "svc-backup failures: 5",
                "jsmith: LOCKED",
                "m.reyes: active",
                "svc-backup: LOCKED",
                "Locked accounts: 2")
            .hidden(Lab.typing("0", "0", "0"),
                "jsmith failures: 0",
                "m.reyes failures: 0",
                "svc-backup failures: 0",
                "jsmith: active",
                "m.reyes: active",
                "svc-backup: active",
                "Locked accounts: 0")
            .hidden(Lab.typing("5", "5", "5"),
                "jsmith failures: 5",
                "m.reyes failures: 5",
                "svc-backup failures: 5",
                "jsmith: LOCKED",
                "m.reyes: LOCKED",
                "svc-backup: LOCKED",
                "Locked accounts: 3")
            .hidden(Lab.typing("4", "6", "4"),
                "jsmith failures: 4",
                "m.reyes failures: 6",
                "svc-backup failures: 4",
                "jsmith: active",
                "m.reyes: LOCKED",
                "svc-backup: active",
                "Locked accounts: 1")
            .hidden(Lab.typing("100", "4", "6"),
                "jsmith failures: 100",
                "m.reyes failures: 4",
                "svc-backup failures: 6",
                "jsmith: LOCKED",
                "m.reyes: active",
                "svc-backup: LOCKED",
                "Locked accounts: 2"));
    }
}
