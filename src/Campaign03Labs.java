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

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(21), "Mask a Card Number", Lab.MEDIUM)
            .stretch()
            .after("C03-M024")
            .brief(
                "Support staff need to confirm WHICH card a customer means "
                + "without ever seeing the full number. Build the masking "
                + "helper set: clean the input, check its shape, name the "
                + "card brand, and show only the last four digits.")
            .practises("Several small helpers", "Validating before using", "Masking sensitive data")
            .spec(
                "Prompt Card number: and read the line. People type spaces and hyphens, so digitsOnly removes both.",
                "isValidCardShape: 13 to 19 characters, all digits. If the cleaned number fails, print INVALID CARD NUMBER and stop.",
                "brand: VISA if it starts with 4; MASTERCARD if its first two digits are 51 to 55; AMEX if it starts with 34 or 37; otherwise OTHER.",
                "mask returns **** followed by a space and the last four digits.",
                "Print Brand: <brand>, Card: <mask> and Length: <n> digits.")
            .needsMethod("static String digitsOnly(String)")
            .needsMethod("static boolean isValidCardShape(String)")
            .needsMethod("static String brand(String)")
            .needsMethod("static String mask(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Card number: \");",
                "        String typed = input.nextLine();",
                "        System.out.println(\"Card: \" + typed);",
                "    }",
                "",
                "    // digitsOnly, isValidCardShape, brand, mask",
                "}")
            .hints(
                "digitsOnly is two replace calls in a row: remove \" \", then "
                + "remove \"-\".",
                "isValidCardShape: a length check with && matches(\"[0-9]+\").",
                "For Mastercard, turn the first two digits into a number:\n"
                + "\n"
                + "    int two = Integer.parseInt(card.substring(0, 2));\n"
                + "\n"
                + "then check 51 to 55. That is safe, because brand is only "
                + "called on numbers that passed isValidCardShape.",
                "mask:  return \"**** \" + card.substring(card.length() - 4);")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Card number: \");",
                "        String card = digitsOnly(input.nextLine());",
                "        if (!isValidCardShape(card)) {",
                "            System.out.println(\"INVALID CARD NUMBER\");",
                "        } else {",
                "            System.out.println(\"Brand: \" + brand(card));",
                "            System.out.println(\"Card: \" + mask(card));",
                "            int length = card.length();",
                "            System.out.println(\"Length: \" + length + \" digits\");",
                "        }",
                "    }",
                "",
                "    /**",
                "     * @param typed a card number as a person typed it",
                "     * @return the same text with spaces and hyphens removed",
                "     */",
                "    static String digitsOnly(String typed) {",
                "        return typed.replace(\" \", \"\").replace(\"-\", \"\");",
                "    }",
                "",
                "    static boolean isValidCardShape(String card) {",
                "        return card.length() >= 13 && card.length() <= 19",
                "                && card.matches(\"[0-9]+\");",
                "    }",
                "",
                "    /** @param card a number that passed isValidCardShape */",
                "    static String brand(String card) {",
                "        if (card.startsWith(\"4\")) {",
                "            return \"VISA\";",
                "        }",
                "        int two = Integer.parseInt(card.substring(0, 2));",
                "        if (two >= 51 && two <= 55) {",
                "            return \"MASTERCARD\";",
                "        }",
                "        if (two == 34 || two == 37) {",
                "            return \"AMEX\";",
                "        }",
                "        return \"OTHER\";",
                "    }",
                "",
                "    static String mask(String card) {",
                "        return \"**** \" + card.substring(card.length() - 4);",
                "    }",
                "}")
            .walkthrough(
                "The helpers form a pipeline - clean, check, then use - and "
                + "the later ones rely on the earlier ones. brand parses the "
                + "first two characters and mask takes the last four; both "
                + "would crash on short or non-numeric text. The doc comment "
                + "on brand states that assumption, and main guarantees it by "
                + "only calling brand on numbers that passed the shape "
                + "check.\n"
                + "\n"
                + "Card rules (PCI DSS) allow at most the first six and last "
                + "four digits to be shown; this shows fewer. The full number "
                + "exists only inside the program, never on screen - which is "
                + "the point of masking.")
            .sample(Lab.typing("4111 1111 1111 1111"),
                "Card number: 4111 1111 1111 1111",
                "Brand: VISA",
                "Card: **** 1111",
                "Length: 16 digits")
            .hidden(Lab.typing("5500-0000-0000-0004"),
                "Card number: 5500-0000-0000-0004",
                "Brand: MASTERCARD",
                "Card: **** 0004",
                "Length: 16 digits")
            .hidden(Lab.typing("340000000000009"),
                "Card number: 340000000000009",
                "Brand: AMEX",
                "Card: **** 0009",
                "Length: 15 digits")
            .hidden(Lab.typing("6011000000000004"),
                "Card number: 6011000000000004",
                "Brand: OTHER",
                "Card: **** 0004",
                "Length: 16 digits")
            .hidden(Lab.typing("56000000000000000"),
                "Card number: 56000000000000000",
                "Brand: OTHER",
                "Card: **** 0000",
                "Length: 17 digits")
            .hidden(Lab.typing("4111"),
                "Card number: 4111",
                "INVALID CARD NUMBER")
            .hidden(Lab.typing("4111 1111 1111 111a"),
                "Card number: 4111 1111 1111 111a",
                "INVALID CARD NUMBER")
            .hidden(Lab.typing(""),
                "Card number:",
                "INVALID CARD NUMBER")
            .hidden(Lab.typing("12345678901234567890"),
                "Card number: 12345678901234567890",
                "INVALID CARD NUMBER"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(22), "Time Formatter", Lab.MEDIUM)
            .stretch()
            .after("C03-M022")
            .brief(
                "The badge-reader log stores times as seconds since midnight. "
                + "Investigators want 09:14:02 - and which shift was on duty. "
                + "Break the job into small methods: validate, pad, format, "
                + "classify.")
            .practises("Decomposition", "Integer division and remainder", "Boundaries between ranges")
            .spec(
                "Prompt Seconds since midnight: and read a whole number.",
                "isValidSeconds: 0 to 86399. Otherwise print INVALID TIME and stop.",
                "twoDigits(n) returns n as two characters, with a leading 0 below 10. clock(seconds) returns HH:MM:SS.",
                "shift(seconds): NIGHT before 08:00:00, DAY from 08:00:00 up to but not including 16:00:00, EVENING from 16:00:00.",
                "Print Time: <clock> and Shift: <shift>.")
            .needsMethod("static boolean isValidSeconds(int)")
            .needsMethod("static String twoDigits(int)")
            .needsMethod("static String clock(int)")
            .needsMethod("static String shift(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Seconds since midnight: \");",
                "        int seconds = Integer.parseInt(input.nextLine().trim());",
                "        // validate, then print the clock time and the shift",
                "    }",
                "",
                "    // isValidSeconds, twoDigits, clock, shift",
                "}")
            .hints(
                "Hours are seconds / 3600. Minutes are (seconds % 3600) / 60. "
                + "Seconds are seconds % 60.",
                "twoDigits:  return n < 10 ? \"0\" + n : \"\" + n;  - or "
                + "String.format(\"%02d\", n).",
                "clock joins three twoDigits calls with colons.",
                "shift compares seconds with 8 * 3600 and 16 * 3600 - using "
                + "< for 'before'.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Seconds since midnight: \");",
                "        int seconds = Integer.parseInt(input.nextLine().trim());",
                "        if (!isValidSeconds(seconds)) {",
                "            System.out.println(\"INVALID TIME\");",
                "        } else {",
                "            System.out.println(\"Time: \" + clock(seconds));",
                "            System.out.println(\"Shift: \" + shift(seconds));",
                "        }",
                "    }",
                "",
                "    static boolean isValidSeconds(int seconds) {",
                "        return seconds >= 0 && seconds <= 86399;",
                "    }",
                "",
                "    static String twoDigits(int n) {",
                "        return n < 10 ? \"0\" + n : \"\" + n;",
                "    }",
                "",
                "    static String clock(int seconds) {",
                "        int h = seconds / 3600;",
                "        int m = (seconds % 3600) / 60;",
                "        int s = seconds % 60;",
                "        return twoDigits(h) + \":\" + twoDigits(m) + \":\" + twoDigits(s);",
                "    }",
                "",
                "    static String shift(int seconds) {",
                "        if (seconds < 8 * 3600) {",
                "            return \"NIGHT\";",
                "        }",
                "        if (seconds < 16 * 3600) {",
                "            return \"DAY\";",
                "        }",
                "        return \"EVENING\";",
                "    }",
                "}")
            .walkthrough(
                "Each method does one small job, and clock is built from "
                + "twoDigits three times - exactly the kind of repetition a "
                + "method removes. shift works on the raw seconds rather than "
                + "the formatted text: comparing numbers is exact, comparing "
                + "strings like \"08:00:00\" only works by luck of formatting.\n"
                + "\n"
                + "The hidden tests sit on every boundary: 28799 and 28800 "
                + "(07:59:59 and 08:00:00), 57599 and 57600, and both ends of "
                + "the valid range. In an investigation, an event at 07:59:59 "
                + "belongs to the night shift - getting that second wrong "
                + "points at the wrong people.")
            .sample(Lab.typing("33242"),
                "Seconds since midnight: 33242",
                "Time: 09:14:02",
                "Shift: DAY")
            .hidden(Lab.typing("0"),
                "Seconds since midnight: 0",
                "Time: 00:00:00",
                "Shift: NIGHT")
            .hidden(Lab.typing("28799"),
                "Seconds since midnight: 28799",
                "Time: 07:59:59",
                "Shift: NIGHT")
            .hidden(Lab.typing("28800"),
                "Seconds since midnight: 28800",
                "Time: 08:00:00",
                "Shift: DAY")
            .hidden(Lab.typing("57599"),
                "Seconds since midnight: 57599",
                "Time: 15:59:59",
                "Shift: DAY")
            .hidden(Lab.typing("57600"),
                "Seconds since midnight: 57600",
                "Time: 16:00:00",
                "Shift: EVENING")
            .hidden(Lab.typing("86399"),
                "Seconds since midnight: 86399",
                "Time: 23:59:59",
                "Shift: EVENING")
            .hidden(Lab.typing("86400"),
                "Seconds since midnight: 86400",
                "INVALID TIME")
            .hidden(Lab.typing("-1"),
                "Seconds since midnight: -1",
                "INVALID TIME"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(23), "Report Builder", Lab.BIG)
            .stretch()
            .after("C03-M022")
            .brief(
                "The shift lead wants a neat boxed summary for each host at "
                + "handover. Every row has the same layout, long values must "
                + "not break the box, and the status line follows a rule. "
                + "Build it from methods so the layout lives in one place.")
            .practises("Decomposition", "String.format padding", "Keeping output inside fixed widths")
            .spec(
                "Prompt Host:, Alerts today:, Critical: and Analyst:. Host and analyst are trimmed; the counts are whole numbers.",
                "If either count is negative, or critical is more than alerts, print INVALID COUNTS and stop.",
                "status: ACTION NEEDED if there is any critical alert; BUSY if there are more than 50 alerts; otherwise NORMAL.",
                "fit(text, width) returns text unchanged if it is at most width characters; otherwise its first width - 3 characters followed by ... .",
                "printBorder prints + then 30 dashes then +. row(label, value) returns | , the label padded to 10, a space, fit(value, 17) padded to 17, then  | - 32 characters in all.",
                "Print a border, rows HOST, ALERTS, CRITICAL, STATUS and ANALYST, and a border.")
            .needsMethod("static void printBorder()")
            .needsMethod("static String row(String, String)")
            .needsMethod("static String fit(String, int)")
            .needsMethod("static String status(int, int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Host: \");",
                "        String host = input.nextLine().trim();",
                "        System.out.print(\"Alerts today: \");",
                "        int alerts = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Critical: \");",
                "        int critical = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Analyst: \");",
                "        String analyst = input.nextLine().trim();",
                "        // validate, then build the box from your methods",
                "    }",
                "",
                "    // printBorder, row, fit, status",
                "}")
            .hints(
                "printBorder can print one fixed String literal of + , 30 "
                + "dashes and + - count them carefully, or build it once as a "
                + "constant.",
                "String.format pads for you:  \"%-10s\"  left-aligns text in "
                + "10 characters. row is one line:\n"
                + "\n"
                + "    return String.format(\"| %-10s %-17s |\", label,\n"
                + "                         fit(value, 17));",
                "fit is a guard and a cut:  if (text.length() <= width) "
                + "return text;  then  return text.substring(0, width - 3) + "
                + "\"...\";",
                "Numbers go into row as text: \"\" + alerts.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final String BORDER = \"+------------------------------+\";",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Host: \");",
                "        String host = input.nextLine().trim();",
                "        System.out.print(\"Alerts today: \");",
                "        int alerts = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Critical: \");",
                "        int critical = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Analyst: \");",
                "        String analyst = input.nextLine().trim();",
                "        if (alerts < 0 || critical < 0 || critical > alerts) {",
                "            System.out.println(\"INVALID COUNTS\");",
                "            return;",
                "        }",
                "        printBorder();",
                "        System.out.println(row(\"HOST\", host));",
                "        System.out.println(row(\"ALERTS\", \"\" + alerts));",
                "        System.out.println(row(\"CRITICAL\", \"\" + critical));",
                "        System.out.println(row(\"STATUS\", status(alerts, critical)));",
                "        System.out.println(row(\"ANALYST\", analyst));",
                "        printBorder();",
                "    }",
                "",
                "    static void printBorder() {",
                "        System.out.println(BORDER);",
                "    }",
                "",
                "    static String row(String label, String value) {",
                "        String shown = fit(value, 17);",
                "        return String.format(\"| %-10s %-17s |\", label, shown);",
                "    }",
                "",
                "    static String fit(String text, int width) {",
                "        if (text.length() <= width) {",
                "            return text;",
                "        }",
                "        return text.substring(0, width - 3) + \"...\";",
                "    }",
                "",
                "    static String status(int alerts, int critical) {",
                "        if (critical > 0) {",
                "            return \"ACTION NEEDED\";",
                "        }",
                "        if (alerts > 50) {",
                "            return \"BUSY\";",
                "        }",
                "        return \"NORMAL\";",
                "    }",
                "}")
            .walkthrough(
                "The box's layout exists in exactly two places: BORDER and "
                + "row's format string. Every row goes through row, and every "
                + "value goes through fit, so no host name - however long - "
                + "can push the right-hand edge out of line. Widen the box "
                + "and you change two lines, not ten.\n"
                + "\n"
                + "main uses return; to stop early on invalid counts - a "
                + "guard clause in main itself. status checks critical first "
                + "because one critical alert matters more than any number of "
                + "routine ones; a busy day with a critical alert is ACTION "
                + "NEEDED, not BUSY. The hidden tests include 50 and 51 "
                + "alerts, and values of exactly and just over 17 "
                + "characters.")
            .sample(Lab.typing("web-01", "12", "0", "Adaeze Okafor"),
                "Host: web-01",
                "Alerts today: 12",
                "Critical: 0",
                "Analyst: Adaeze Okafor",
                "+------------------------------+",
                "| HOST       web-01            |",
                "| ALERTS     12                |",
                "| CRITICAL   0                 |",
                "| STATUS     NORMAL            |",
                "| ANALYST    Adaeze Okafor     |",
                "+------------------------------+")
            .hidden(Lab.typing("db-02.northstar.example", "80", "2", "m.reyes"),
                "Host: db-02.northstar.example",
                "Alerts today: 80",
                "Critical: 2",
                "Analyst: m.reyes",
                "+------------------------------+",
                "| HOST       db-02.northsta... |",
                "| ALERTS     80                |",
                "| CRITICAL   2                 |",
                "| STATUS     ACTION NEEDED     |",
                "| ANALYST    m.reyes           |",
                "+------------------------------+")
            .hidden(Lab.typing("fw-01", "51", "0", "J"),
                "Host: fw-01",
                "Alerts today: 51",
                "Critical: 0",
                "Analyst: J",
                "+------------------------------+",
                "| HOST       fw-01             |",
                "| ALERTS     51                |",
                "| CRITICAL   0                 |",
                "| STATUS     BUSY              |",
                "| ANALYST    J                 |",
                "+------------------------------+")
            .hidden(Lab.typing("fw-01", "50", "0", "J"),
                "Host: fw-01",
                "Alerts today: 50",
                "Critical: 0",
                "Analyst: J",
                "+------------------------------+",
                "| HOST       fw-01             |",
                "| ALERTS     50                |",
                "| CRITICAL   0                 |",
                "| STATUS     NORMAL            |",
                "| ANALYST    J                 |",
                "+------------------------------+")
            .hidden(Lab.typing("mail-gateway-0001", "0", "0", "Adaeze Okafor-Reyes"),
                "Host: mail-gateway-0001",
                "Alerts today: 0",
                "Critical: 0",
                "Analyst: Adaeze Okafor-Reyes",
                "+------------------------------+",
                "| HOST       mail-gateway-0001 |",
                "| ALERTS     0                 |",
                "| CRITICAL   0                 |",
                "| STATUS     NORMAL            |",
                "| ANALYST    Adaeze Okafor-... |",
                "+------------------------------+")
            .hidden(Lab.typing("x", "-1", "0", "a"),
                "Host: x",
                "Alerts today: -1",
                "Critical: 0",
                "Analyst: a",
                "INVALID COUNTS")
            .hidden(Lab.typing("x", "3", "4", "a"),
                "Host: x",
                "Alerts today: 3",
                "Critical: 4",
                "Analyst: a",
                "INVALID COUNTS"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(24), "Input Helpers", Lab.BIG)
            .stretch()
            .after("C03-M026")
            .brief(
                "Every onboarding form repeats the same fiddly work: blanks "
                + "that mean 'use the default', numbers that might be words, "
                + "yes-or-no answers typed six different ways. Write the "
                + "helpers once, then build the new-analyst form with them.")
            .practises("Reusable helpers", "Fallback values", "Validation before parsing")
            .spec(
                "Prompt Name:, Team (blank for SOC):, Shift hours (blank for 8): and On-call (y/n):.",
                "orDefault(text, fallback) returns the trimmed text, or fallback if it is blank. isWholeNumber(text) is true for 1 to 9 digits. parseOr(text, fallback) returns the trimmed text as an int when it is a whole number, otherwise fallback. isYes(text) is true for y, yes or true, ignoring case and spaces.",
                "Shift hours must be 1 to 12. Blank, not a whole number, or out of range: use 8 and add (default used).",
                "Print Name: (orDefault, fallback (unknown)), Team: (fallback SOC), Shift: <n> hours, and On-call: yes or no.")
            .needsMethod("static String orDefault(String, String)")
            .needsMethod("static boolean isWholeNumber(String)")
            .needsMethod("static int parseOr(String, int)")
            .needsMethod("static boolean isYes(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Name: \");",
                "        String name = input.nextLine();",
                "        System.out.print(\"Team (blank for SOC): \");",
                "        String team = input.nextLine();",
                "        System.out.print(\"Shift hours (blank for 8): \");",
                "        String hours = input.nextLine();",
                "        System.out.print(\"On-call (y/n): \");",
                "        String onCall = input.nextLine();",
                "        // build the record with your helpers",
                "    }",
                "",
                "    // orDefault, isWholeNumber, parseOr, isYes",
                "}")
            .hints(
                "orDefault:  return text.isBlank() ? fallback : text.trim();",
                "parseOr trims, then asks isWholeNumber before it ever calls "
                + "parseInt - that is what makes it impossible to crash.",
                "isYes is a switch expression on text.trim().toLowerCase(), "
                + "with case \"y\", \"yes\", \"true\" -> true.",
                "For the shift, parse with a fallback of -1, then check the "
                + "range: anything outside 1 to 12 - including the -1 - means "
                + "the default.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Name: \");",
                "        String name = input.nextLine();",
                "        System.out.print(\"Team (blank for SOC): \");",
                "        String team = input.nextLine();",
                "        System.out.print(\"Shift hours (blank for 8): \");",
                "        String hours = input.nextLine();",
                "        System.out.print(\"On-call (y/n): \");",
                "        String onCall = input.nextLine();",
                "",
                "        int shift = parseOr(hours, -1);",
                "        String note = \"\";",
                "        if (shift < 1 || shift > 12) {",
                "            shift = 8;",
                "            note = \" (default used)\";",
                "        }",
                "        System.out.println(\"Name: \" + orDefault(name, \"(unknown)\"));",
                "        System.out.println(\"Team: \" + orDefault(team, \"SOC\"));",
                "        System.out.println(\"Shift: \" + shift + \" hours\" + note);",
                "        String answer = isYes(onCall) ? \"yes\" : \"no\";",
                "        System.out.println(\"On-call: \" + answer);",
                "    }",
                "",
                "    static String orDefault(String text, String fallback) {",
                "        return text.isBlank() ? fallback : text.trim();",
                "    }",
                "",
                "    static boolean isWholeNumber(String text) {",
                "        return text.length() >= 1 && text.length() <= 9",
                "                && text.matches(\"[0-9]+\");",
                "    }",
                "",
                "    static int parseOr(String text, int fallback) {",
                "        String t = text.trim();",
                "        if (!isWholeNumber(t)) {",
                "            return fallback;",
                "        }",
                "        return Integer.parseInt(t);",
                "    }",
                "",
                "    static boolean isYes(String text) {",
                "        return switch (text.trim().toLowerCase()) {",
                "            case \"y\", \"yes\", \"true\" -> true;",
                "            default -> false;",
                "        };",
                "    }",
                "}")
            .walkthrough(
                "Each helper hides one fiddly detail behind a clear name, and "
                + "none of them can crash: parseOr refuses anything "
                + "isWholeNumber does not approve, so parseInt only ever sees "
                + "one to nine digits. The fallback parameter lets the CALLER "
                + "choose what 'no answer' means - -1 here, so main can tell "
                + "a missing number from a real one.\n"
                + "\n"
                + "isYes is an allow-list: only three spellings mean yes, and "
                + "everything else - including nope, a typo, or nothing - "
                + "means no. For a question like on-call that is the safe "
                + "default; for a question like 'delete everything?' it is "
                + "essential.")
            .sample(Lab.typing("Adaeze Okafor", "", "10", "yes"),
                "Name: Adaeze Okafor",
                "Team (blank for SOC):",
                "Shift hours (blank for 8): 10",
                "On-call (y/n): yes",
                "Name: Adaeze Okafor",
                "Team: SOC",
                "Shift: 10 hours",
                "On-call: yes")
            .hidden(Lab.typing("", "Red Team", "", "n"),
                "Name:",
                "Team (blank for SOC): Red Team",
                "Shift hours (blank for 8):",
                "On-call (y/n): n",
                "Name: (unknown)",
                "Team: Red Team",
                "Shift: 8 hours (default used)",
                "On-call: no")
            .hidden(Lab.typing("jsmith", "DFIR", "twelve", "Y"),
                "Name: jsmith",
                "Team (blank for SOC): DFIR",
                "Shift hours (blank for 8): twelve",
                "On-call (y/n): Y",
                "Name: jsmith",
                "Team: DFIR",
                "Shift: 8 hours (default used)",
                "On-call: yes")
            .hidden(Lab.typing("jsmith", "   ", "13", " TRUE "),
                "Name: jsmith",
                "Team (blank for SOC):",
                "Shift hours (blank for 8): 13",
                "On-call (y/n):  TRUE",
                "Name: jsmith",
                "Team: SOC",
                "Shift: 8 hours (default used)",
                "On-call: yes")
            .hidden(Lab.typing("m.reyes", "IR", "0", "nope"),
                "Name: m.reyes",
                "Team (blank for SOC): IR",
                "Shift hours (blank for 8): 0",
                "On-call (y/n): nope",
                "Name: m.reyes",
                "Team: IR",
                "Shift: 8 hours (default used)",
                "On-call: no")
            .hidden(Lab.typing("m.reyes", "IR", " 12 ", "y"),
                "Name: m.reyes",
                "Team (blank for SOC): IR",
                "Shift hours (blank for 8):  12",
                "On-call (y/n): y",
                "Name: m.reyes",
                "Team: IR",
                "Shift: 12 hours",
                "On-call: yes")
            .hidden(Lab.typing("m.reyes", "IR", "99999999999", "y"),
                "Name: m.reyes",
                "Team (blank for SOC): IR",
                "Shift hours (blank for 8): 99999999999",
                "On-call (y/n): y",
                "Name: m.reyes",
                "Team: IR",
                "Shift: 8 hours (default used)",
                "On-call: yes"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(25), "Log Line Toolkit", Lab.BIG)
            .stretch()
            .after("C03-M028")
            .brief(
                "Authentication logs arrive as one line per event: date, time, "
                + "result, user and source address, separated by single "
                + "spaces. Build the toolkit that checks a line is well formed, "
                + "pulls out any field, and decides whether it needs an "
                + "alert.")
            .practises("Recursion on text", "Validating structure", "Combining helpers")
            .spec(
                "Prompt Log line: and read it, trimmed. The format is <date> <time> <result> <user> <ip>.",
                "countSpaces returns how many spaces the line holds. A line is well formed when it has exactly 4 spaces, no two spaces side by side, and a result of OK or FAIL. Otherwise print MALFORMED LOG LINE and stop.",
                "field(line, n) returns field number n, counting from 0. It must work by calling itself: field n of a line is field n - 1 of everything after the first space.",
                "isExternal(ip) is true unless the address starts with 10. or 192.168.",
                "Print Date:, Time:, User:, Result:, then Source: EXTERNAL or INTERNAL followed by the address in brackets, then Alert: YES for a FAIL from an external source, otherwise Alert: NO.")
            .needsMethod("static int countSpaces(String)")
            .needsMethod("static String field(String, int)")
            .needsMethod("static boolean isExternal(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Log line: \");",
                "        String line = input.nextLine().trim();",
                "        // check the shape, then report each field",
                "    }",
                "",
                "    // countSpaces, field, isExternal",
                "}")
            .hints(
                "countSpaces without a loop: remove every space and see how "
                + "much shorter the line got.\n"
                + "\n"
                + "    line.length() - line.replace(\" \", \"\").length()",
                "field's base case is n == 0: return everything before the "
                + "first space (or the whole line if there is none).",
                "field's recursive case drops the first field and asks for "
                + "one fewer:\n"
                + "\n"
                + "    return field(line.substring(space + 1), n - 1);",
                "Check the result with equals, after the space checks - "
                + "field(line, 2) is only safe once the shape is known.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Log line: \");",
                "        String line = input.nextLine().trim();",
                "        if (countSpaces(line) != 4 || line.contains(\"  \")) {",
                "            System.out.println(\"MALFORMED LOG LINE\");",
                "            return;",
                "        }",
                "        String result = field(line, 2);",
                "        if (!result.equals(\"OK\") && !result.equals(\"FAIL\")) {",
                "            System.out.println(\"MALFORMED LOG LINE\");",
                "            return;",
                "        }",
                "        String ip = field(line, 4);",
                "        boolean external = isExternal(ip);",
                "        System.out.println(\"Date: \" + field(line, 0));",
                "        System.out.println(\"Time: \" + field(line, 1));",
                "        System.out.println(\"User: \" + field(line, 3));",
                "        System.out.println(\"Result: \" + result);",
                "        String where = external ? \"EXTERNAL\" : \"INTERNAL\";",
                "        System.out.println(\"Source: \" + where + \" (\" + ip + \")\");",
                "        boolean alert = result.equals(\"FAIL\") && external;",
                "        System.out.println(\"Alert: \" + (alert ? \"YES\" : \"NO\"));",
                "    }",
                "",
                "    static int countSpaces(String line) {",
                "        return line.length() - line.replace(\" \", \"\").length();",
                "    }",
                "",
                "    static String field(String line, int n) {",
                "        int space = line.indexOf(\" \");",
                "        if (n == 0) {",
                "            return space == -1 ? line : line.substring(0, space);",
                "        }",
                "        return field(line.substring(space + 1), n - 1);",
                "    }",
                "",
                "    static boolean isExternal(String ip) {",
                "        return !ip.startsWith(\"10.\") && !ip.startsWith(\"192.168.\");",
                "    }",
                "}")
            .walkthrough(
                "field is recursion on text: to find field 3, drop the first "
                + "field and find field 2 of what is left, and so on until "
                + "n reaches 0. Each call works on a shorter line, so it "
                + "always stops - as long as the line really has enough "
                + "fields, which is why the shape check runs first.\n"
                + "\n"
                + "The shape check itself needs no loop: counting spaces by "
                + "removing them, and refusing double spaces, guarantees "
                + "exactly five non-empty fields. The result is checked "
                + "against an allow-list of OK and FAIL. A parser that "
                + "guessed its way through malformed lines is how attackers "
                + "slip events past log-based detection - refusing them "
                + "loudly is safer.")
            .sample(Lab.typing("2024-03-11 09:14:02 FAIL jsmith 203.0.113.9"),
                "Log line: 2024-03-11 09:14:02 FAIL jsmith 203.0.113.9",
                "Date: 2024-03-11",
                "Time: 09:14:02",
                "User: jsmith",
                "Result: FAIL",
                "Source: EXTERNAL (203.0.113.9)",
                "Alert: YES")
            .hidden(Lab.typing("2024-03-11 09:15:40 OK m.reyes 10.0.0.7"),
                "Log line: 2024-03-11 09:15:40 OK m.reyes 10.0.0.7",
                "Date: 2024-03-11",
                "Time: 09:15:40",
                "User: m.reyes",
                "Result: OK",
                "Source: INTERNAL (10.0.0.7)",
                "Alert: NO")
            .hidden(Lab.typing("2024-03-11 23:59:59 FAIL svc-backup 192.168.1.20"),
                "Log line: 2024-03-11 23:59:59 FAIL svc-backup 192.168.1.20",
                "Date: 2024-03-11",
                "Time: 23:59:59",
                "User: svc-backup",
                "Result: FAIL",
                "Source: INTERNAL (192.168.1.20)",
                "Alert: NO")
            .hidden(Lab.typing("2024-03-12 00:00:01 OK admin 198.51.100.4"),
                "Log line: 2024-03-12 00:00:01 OK admin 198.51.100.4",
                "Date: 2024-03-12",
                "Time: 00:00:01",
                "User: admin",
                "Result: OK",
                "Source: EXTERNAL (198.51.100.4)",
                "Alert: NO")
            .hidden(Lab.typing("garbage"),
                "Log line: garbage",
                "MALFORMED LOG LINE")
            .hidden(Lab.typing("2024-03-11 09:14:02 FAIL jsmith"),
                "Log line: 2024-03-11 09:14:02 FAIL jsmith",
                "MALFORMED LOG LINE")
            .hidden(Lab.typing("2024-03-11 09:14:02 MAYBE jsmith 10.0.0.1"),
                "Log line: 2024-03-11 09:14:02 MAYBE jsmith 10.0.0.1",
                "MALFORMED LOG LINE")
            .hidden(Lab.typing("2024-03-11  09:14:02 FAIL jsmith"),
                "Log line: 2024-03-11  09:14:02 FAIL jsmith",
                "MALFORMED LOG LINE")
            .hidden(Lab.typing("  2024-03-13 12:00:00 FAIL eve 100.64.0.1  "),
                "Log line:   2024-03-13 12:00:00 FAIL eve 100.64.0.1",
                "Date: 2024-03-13",
                "Time: 12:00:00",
                "User: eve",
                "Result: FAIL",
                "Source: EXTERNAL (100.64.0.1)",
                "Alert: YES"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(26), "Access Rule Methods", Lab.BIG)
            .stretch()
            .after("C03-M027")
            .brief(
                "Campaign 02's access matrix was one long chain inside main. "
                + "Now it gets a time rule too - contractors only in working "
                + "hours - and it has to be shared by three services. Rebuild "
                + "it as methods: checks that know one thing each, and one "
                + "decide method that applies them in order.")
            .practises("Fail-closed ordering", "Boolean helper methods", "A decision method")
            .spec(
                "Prompt Role:, Resource:, Action: (each trimmed and lower-cased) and Hour (0-23): (a whole number).",
                "Known roles: admin, analyst, auditor, contractor. Known resources: logs, tickets, config, payroll. Known actions: read, write, delete.",
                "decide checks, in order: unknown role, resource or action - DENIED: unknown role <role> (or resource, or action); hour outside 0-23 - DENIED: bad hour; writing or deleting logs - DENIED: logs are read-only; a contractor outside 8 to 17 - DENIED: contractors work 08:00-17:59.",
                "Then canAccess: admin anything; analyst reads anything except payroll and writes tickets; auditor reads anything; contractor reads or writes tickets. Allowed prints ALLOWED: <role> may <action> <resource>; otherwise DENIED: <role> may not <action> <resource>.")
            .needsMethod("static boolean isKnownRole(String)")
            .needsMethod("static boolean isKnownResource(String)")
            .needsMethod("static boolean isKnownAction(String)")
            .needsMethod("static boolean canAccess(String, String, String)")
            .needsMethod("static String decide(String, String, String, int)")
            .starter(
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
                "        System.out.print(\"Hour (0-23): \");",
                "        int hour = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(decide(role, resource, action, hour));",
                "    }",
                "",
                "    static String decide(String role, String resource,",
                "                         String action, int hour) {",
                "        return \"ALLOWED: \" + role + \" may \" + action + \" \" + resource;",
                "    }",
                "}")
            .hints(
                "The starter's decide fails OPEN: it allows everything. Every "
                + "guard you add makes it stricter, and ALLOWED must end up "
                + "as a result you can only reach past every check.",
                "Each isKnown... method is a switch expression giving a "
                + "boolean, with default -> false.",
                "decide is a list of guards in the spec's order, each "
                + "returning its DENIED message. The last lines ask "
                + "canAccess and build the ALLOWED or DENIED text.",
                "canAccess is a switch on the role, one case per role, with "
                + "default -> false - an unlisted role gets nothing.")
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
                "        System.out.print(\"Hour (0-23): \");",
                "        int hour = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(decide(role, resource, action, hour));",
                "    }",
                "",
                "    static boolean isKnownRole(String role) {",
                "        return switch (role) {",
                "            case \"admin\", \"analyst\", \"auditor\", \"contractor\" -> true;",
                "            default -> false;",
                "        };",
                "    }",
                "",
                "    static boolean isKnownResource(String resource) {",
                "        return switch (resource) {",
                "            case \"logs\", \"tickets\", \"config\", \"payroll\" -> true;",
                "            default -> false;",
                "        };",
                "    }",
                "",
                "    static boolean isKnownAction(String action) {",
                "        return switch (action) {",
                "            case \"read\", \"write\", \"delete\" -> true;",
                "            default -> false;",
                "        };",
                "    }",
                "",
                "    static boolean canAccess(String role, String resource,",
                "                             String action) {",
                "        boolean reading = action.equals(\"read\");",
                "        boolean tickets = resource.equals(\"tickets\");",
                "        return switch (role) {",
                "            case \"admin\" -> true;",
                "            case \"analyst\" -> (reading && !resource.equals(\"payroll\"))",
                "                    || (tickets && action.equals(\"write\"));",
                "            case \"auditor\" -> reading;",
                "            case \"contractor\" -> tickets && !action.equals(\"delete\");",
                "            default -> false;",
                "        };",
                "    }",
                "",
                "    static String decide(String role, String resource,",
                "                         String action, int hour) {",
                "        if (!isKnownRole(role)) {",
                "            return \"DENIED: unknown role \" + role;",
                "        }",
                "        if (!isKnownResource(resource)) {",
                "            return \"DENIED: unknown resource \" + resource;",
                "        }",
                "        if (!isKnownAction(action)) {",
                "            return \"DENIED: unknown action \" + action;",
                "        }",
                "        if (hour < 0 || hour > 23) {",
                "            return \"DENIED: bad hour\";",
                "        }",
                "        if (resource.equals(\"logs\") && !action.equals(\"read\")) {",
                "            return \"DENIED: logs are read-only\";",
                "        }",
                "        if (role.equals(\"contractor\") && (hour < 8 || hour > 17)) {",
                "            return \"DENIED: contractors work 08:00-17:59\";",
                "        }",
                "        String request = action + \" \" + resource;",
                "        if (canAccess(role, resource, action)) {",
                "            return \"ALLOWED: \" + role + \" may \" + request;",
                "        }",
                "        return \"DENIED: \" + role + \" may not \" + request;",
                "    }",
                "}")
            .walkthrough(
                "Compared with the Campaign 02 version, the rules are the same "
                + "but their HOME is different. Each isKnown... method knows "
                + "one list; canAccess knows only the matrix; decide knows "
                + "only the ORDER. Three services can now call decide and "
                + "get identical answers - and a new role is a change to two "
                + "small methods.\n"
                + "\n"
                + "The order is the security. Unknown values are refused "
                + "before anything else looks at them; the read-only logs "
                + "rule beats every role, admin included; the time rule "
                + "applies before the matrix, so a contractor at 18:00 is "
                + "refused even for something they could normally do. The "
                + "starter showed the opposite, a decide that fails open - "
                + "exactly the bug a missing guard creates.")
            .sample(Lab.typing("analyst", "tickets", "write", "10"),
                "Role: analyst",
                "Resource: tickets",
                "Action: write",
                "Hour (0-23): 10",
                "ALLOWED: analyst may write tickets")
            .hidden(Lab.typing("contractor", "tickets", "write", "7"),
                "Role: contractor",
                "Resource: tickets",
                "Action: write",
                "Hour (0-23): 7",
                "DENIED: contractors work 08:00-17:59")
            .hidden(Lab.typing("contractor", "tickets", "write", "17"),
                "Role: contractor",
                "Resource: tickets",
                "Action: write",
                "Hour (0-23): 17",
                "ALLOWED: contractor may write tickets")
            .hidden(Lab.typing("contractor", "tickets", "write", "18"),
                "Role: contractor",
                "Resource: tickets",
                "Action: write",
                "Hour (0-23): 18",
                "DENIED: contractors work 08:00-17:59")
            .hidden(Lab.typing("admin", "logs", "delete", "12"),
                "Role: admin",
                "Resource: logs",
                "Action: delete",
                "Hour (0-23): 12",
                "DENIED: logs are read-only")
            .hidden(Lab.typing("admin", "payroll", "write", "3"),
                "Role: admin",
                "Resource: payroll",
                "Action: write",
                "Hour (0-23): 3",
                "ALLOWED: admin may write payroll")
            .hidden(Lab.typing("auditor", "payroll", "read", "23"),
                "Role: auditor",
                "Resource: payroll",
                "Action: read",
                "Hour (0-23): 23",
                "ALLOWED: auditor may read payroll")
            .hidden(Lab.typing("auditor", "config", "write", "9"),
                "Role: auditor",
                "Resource: config",
                "Action: write",
                "Hour (0-23): 9",
                "DENIED: auditor may not write config")
            .hidden(Lab.typing("analyst", "payroll", "read", "9"),
                "Role: analyst",
                "Resource: payroll",
                "Action: read",
                "Hour (0-23): 9",
                "DENIED: analyst may not read payroll")
            .hidden(Lab.typing("intern", "tickets", "read", "9"),
                "Role: intern",
                "Resource: tickets",
                "Action: read",
                "Hour (0-23): 9",
                "DENIED: unknown role intern")
            .hidden(Lab.typing("analyst", "backups", "read", "9"),
                "Role: analyst",
                "Resource: backups",
                "Action: read",
                "Hour (0-23): 9",
                "DENIED: unknown resource backups")
            .hidden(Lab.typing("analyst", "tickets", "execute", "9"),
                "Role: analyst",
                "Resource: tickets",
                "Action: execute",
                "Hour (0-23): 9",
                "DENIED: unknown action execute")
            .hidden(Lab.typing("analyst", "tickets", "read", "24"),
                "Role: analyst",
                "Resource: tickets",
                "Action: read",
                "Hour (0-23): 24",
                "DENIED: bad hour")
            .hidden(Lab.typing(" ADMIN ", "CONFIG", "Delete", "0"),
                "Role:  ADMIN",
                "Resource: CONFIG",
                "Action: Delete",
                "Hour (0-23): 0",
                "ALLOWED: admin may delete config"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(27), "Tested Calculator", Lab.BIG)
            .stretch()
            .after("C03-M023")
            .brief(
                "The incident team's calculator gets used for timestamps and "
                + "byte counts, where overflow and division by zero are not "
                + "theoretical. Build it with its own self-test: type "
                + "selftest and it checks itself before anyone trusts it.")
            .practises("Testing from main", "Overflow detection with long", "Error results instead of crashes")
            .spec(
                "Prompt First number (or selftest): and read the line, trimmed. If it is selftest, run selfTest and stop. Otherwise read Operator: and Second number: - the numbers will be whole numbers that fit in an int.",
                "calculate(a, op, b) returns ERROR: unknown operator for anything but + - * / %; ERROR: division by zero for / or % by 0; ERROR: overflow when the true answer does not fit in an int; otherwise = <answer>. Work the answer out in long so overflow can be seen.",
                "fitsInt(value) is true when a long is within the int range.",
                "check(name, passed) prints PASS <name> or FAIL <name>. selfTest checks: 2 + 3 gives = 5; 7 / 2 gives = 3; -7 % 3 gives = -1; 1 / 0 gives the division error; 2147483647 + 1 gives the overflow error; 1 ^ 2 gives the operator error - in that order, then prints Self-test: <n>/6 passed.",
                "For a calculation, print the result of calculate.")
            .needsMethod("static String calculate(int, String, int)")
            .needsMethod("static boolean fitsInt(long)")
            .needsMethod("static void check(String, boolean)")
            .needsMethod("static void selfTest()")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"First number (or selftest): \");",
                "        String first = input.nextLine().trim();",
                "        System.out.print(\"Operator: \");",
                "        String op = input.nextLine().trim();",
                "        System.out.print(\"Second number: \");",
                "        int b = Integer.parseInt(input.nextLine().trim());",
                "        int a = Integer.parseInt(first);",
                "        System.out.println(\"= \" + (a + b));",
                "    }",
                "}")
            .hints(
                "Guards first in calculate: the operator check, then division "
                + "by zero. Only then work the answer out.",
                "Cast before the arithmetic, so it happens in long:  (long) a "
                + "+ b. The int answer would already have wrapped round.",
                "fitsInt:  return value >= Integer.MIN_VALUE && value <= "
                + "Integer.MAX_VALUE;",
                "Count passes in a static int field that check adds to, so "
                + "selfTest can print the total at the end.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static int passed = 0;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"First number (or selftest): \");",
                "        String first = input.nextLine().trim();",
                "        if (first.equals(\"selftest\")) {",
                "            selfTest();",
                "            return;",
                "        }",
                "        System.out.print(\"Operator: \");",
                "        String op = input.nextLine().trim();",
                "        System.out.print(\"Second number: \");",
                "        int b = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(calculate(Integer.parseInt(first), op, b));",
                "    }",
                "",
                "    static String calculate(int a, String op, int b) {",
                "        boolean known = switch (op) {",
                "            case \"+\", \"-\", \"*\", \"/\", \"%\" -> true;",
                "            default -> false;",
                "        };",
                "        if (!known) {",
                "            return \"ERROR: unknown operator\";",
                "        }",
                "        if ((op.equals(\"/\") || op.equals(\"%\")) && b == 0) {",
                "            return \"ERROR: division by zero\";",
                "        }",
                "        long result = switch (op) {",
                "            case \"+\" -> (long) a + b;",
                "            case \"-\" -> (long) a - b;",
                "            case \"*\" -> (long) a * b;",
                "            case \"/\" -> (long) a / b;",
                "            default -> (long) a % b;",
                "        };",
                "        if (!fitsInt(result)) {",
                "            return \"ERROR: overflow\";",
                "        }",
                "        return \"= \" + result;",
                "    }",
                "",
                "    static boolean fitsInt(long value) {",
                "        return value >= Integer.MIN_VALUE",
                "                && value <= Integer.MAX_VALUE;",
                "    }",
                "",
                "    static void check(String name, boolean ok) {",
                "        if (ok) {",
                "            passed++;",
                "        }",
                "        System.out.println((ok ? \"PASS \" : \"FAIL \") + name);",
                "    }",
                "",
                "    static void selfTest() {",
                "        check(\"2 + 3\", calculate(2, \"+\", 3).equals(\"= 5\"));",
                "        check(\"7 / 2\", calculate(7, \"/\", 2).equals(\"= 3\"));",
                "        check(\"-7 % 3\", calculate(-7, \"%\", 3).equals(\"= -1\"));",
                "        check(\"1 / 0\", calculate(1, \"/\", 0)",
                "                .equals(\"ERROR: division by zero\"));",
                "        check(\"2147483647 + 1\", calculate(2147483647, \"+\", 1)",
                "                .equals(\"ERROR: overflow\"));",
                "        check(\"1 ^ 2\", calculate(1, \"^\", 2)",
                "                .equals(\"ERROR: unknown operator\"));",
                "        System.out.println(\"Self-test: \" + passed + \"/6 passed\");",
                "    }",
                "}")
            .walkthrough(
                "calculate never crashes and never lies. Unknown operators "
                + "and division by zero are refused by guards before any "
                + "arithmetic; everything else is worked out in long, where "
                + "an int's overflow cannot happen, and fitsInt then decides "
                + "whether the true answer can be given as an int. "
                + "-2147483648 / -1 is the sneaky case: its true answer is "
                + "one more than an int can hold.\n"
                + "\n"
                + "The self-test is mission 23 built into the product. Its "
                + "tests include the cases most likely to break - integer "
                + "division, a negative remainder, and every error path - and "
                + "anyone can run it before trusting the numbers.")
            .sample(Lab.typing("12", "*", "12"),
                "First number (or selftest): 12",
                "Operator: *",
                "Second number: 12",
                "= 144")
            .hidden(Lab.typing("selftest"),
                "First number (or selftest): selftest",
                "PASS 2 + 3",
                "PASS 7 / 2",
                "PASS -7 % 3",
                "PASS 1 / 0",
                "PASS 2147483647 + 1",
                "PASS 1 ^ 2",
                "Self-test: 6/6 passed")
            .hidden(Lab.typing("7", "/", "2"),
                "First number (or selftest): 7",
                "Operator: /",
                "Second number: 2",
                "= 3")
            .hidden(Lab.typing("7", "%", "0"),
                "First number (or selftest): 7",
                "Operator: %",
                "Second number: 0",
                "ERROR: division by zero")
            .hidden(Lab.typing("2147483647", "+", "1"),
                "First number (or selftest): 2147483647",
                "Operator: +",
                "Second number: 1",
                "ERROR: overflow")
            .hidden(Lab.typing("-2147483648", "/", "-1"),
                "First number (or selftest): -2147483648",
                "Operator: /",
                "Second number: -1",
                "ERROR: overflow")
            .hidden(Lab.typing("100000", "*", "100000"),
                "First number (or selftest): 100000",
                "Operator: *",
                "Second number: 100000",
                "ERROR: overflow")
            .hidden(Lab.typing("5", "^", "2"),
                "First number (or selftest): 5",
                "Operator: ^",
                "Second number: 2",
                "ERROR: unknown operator")
            .hidden(Lab.typing("-7", "%", "3"),
                "First number (or selftest): -7",
                "Operator: %",
                "Second number: 3",
                "= -1")
            .hidden(Lab.typing("0", "-", "2147483647"),
                "First number (or selftest): 0",
                "Operator: -",
                "Second number: 2147483647",
                "= -2147483647"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(28), "Caesar Shift, One Letter", Lab.MEDIUM)
            .stretch()
            .after("C03-M020")
            .brief(
                "The Caesar cipher shifts every letter a fixed number of "
                + "places along the alphabet: with a shift of 3, a becomes d "
                + "and x wraps round to a. Encrypting a whole message needs a "
                + "loop - but the heart of it is one method that shifts ONE "
                + "letter. Build that, and prove it decrypts.")
            .practises("Class constants", "indexOf and charAt as a lookup", "Wrapping round with %")
            .spec(
                "Prompt Letter: and read the line, trimmed. It must be exactly one character; otherwise print INVALID INPUT and stop. Then prompt Shift: and read a whole number, which may be negative or larger than 26.",
                "shift(c, k) moves a letter k places, wrapping round the alphabet and keeping its case. Anything that is not a letter comes back unchanged.",
                "Print Encrypted: <shifted>. For a letter, then print Decrypts back: yes if shifting the result by -k gives the original letter, otherwise no. For anything else, print Note: not a letter - unchanged instead.")
            .needsMethod("static char shift(char, int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final String LOWER = \"abcdefghijklmnopqrstuvwxyz\";",
                "    static final String UPPER = \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\";",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Letter: \");",
                "        String text = input.nextLine().trim();",
                "        // validate, read the shift, encrypt, check it decrypts",
                "    }",
                "",
                "    // declare shift(char c, int k) here",
                "}")
            .hints(
                "The alphabet Strings are a lookup table. LOWER.indexOf(c) "
                + "gives a letter's position, 0 to 25, or -1 if c is not in "
                + "it. LOWER.charAt(position) turns a position back into a "
                + "letter.",
                "Wrapping: (position + k) % 26. But % keeps the sign in Java, "
                + "so -1 % 26 is -1. Adding 26 and taking % again fixes "
                + "negatives:\n"
                + "\n"
                + "    int moved = ((position + k) % 26 + 26) % 26;",
                "Try LOWER first, then UPPER, and return c itself if it is in "
                + "neither.",
                "Decrypting is shifting back:  shift(encrypted, -k) == c  - "
                + "chars compare with ==.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final String LOWER = \"abcdefghijklmnopqrstuvwxyz\";",
                "    static final String UPPER = \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\";",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Letter: \");",
                "        String text = input.nextLine().trim();",
                "        if (text.length() != 1) {",
                "            System.out.println(\"INVALID INPUT\");",
                "            return;",
                "        }",
                "        System.out.print(\"Shift: \");",
                "        int k = Integer.parseInt(input.nextLine().trim());",
                "        char c = text.charAt(0);",
                "        char encrypted = shift(c, k);",
                "        System.out.println(\"Encrypted: \" + encrypted);",
                "        if (encrypted == c && !Character.isLetter(c)) {",
                "            System.out.println(\"Note: not a letter - unchanged\");",
                "        } else {",
                "            boolean back = shift(encrypted, -k) == c;",
                "            String answer = back ? \"yes\" : \"no\";",
                "            System.out.println(\"Decrypts back: \" + answer);",
                "        }",
                "    }",
                "",
                "    static char shift(char c, int k) {",
                "        int position = LOWER.indexOf(c);",
                "        if (position != -1) {",
                "            return LOWER.charAt(((position + k) % 26 + 26) % 26);",
                "        }",
                "        position = UPPER.indexOf(c);",
                "        if (position != -1) {",
                "            return UPPER.charAt(((position + k) % 26 + 26) % 26);",
                "        }",
                "        return c;",
                "    }",
                "}")
            .walkthrough(
                "The two constants turn the alphabet into a lookup table: "
                + "indexOf maps a letter to a number, arithmetic moves the "
                + "number, and charAt maps it back. Keeping case is simply a "
                + "matter of using the table the letter came from.\n"
                + "\n"
                + "The double % is the important detail. Java's % keeps the "
                + "sign of the left side, so a shift of -3 from 'c' gives -1, "
                + "not 25; adding 26 and taking % again brings every result "
                + "into 0 to 25, for any shift, however large or negative. "
                + "The decrypt check proves shift is reversible - a cipher "
                + "you cannot undo is just data loss.\n"
                + "\n"
                + "A Caesar cipher is trivially broken - there are only 25 "
                + "keys to try - but every real cipher is built from the same "
                + "idea: a reversible transformation controlled by a key.")
            .sample(Lab.typing("a", "3"),
                "Letter: a",
                "Shift: 3",
                "Encrypted: d",
                "Decrypts back: yes")
            .hidden(Lab.typing("x", "3"),
                "Letter: x",
                "Shift: 3",
                "Encrypted: a",
                "Decrypts back: yes")
            .hidden(Lab.typing("Z", "1"),
                "Letter: Z",
                "Shift: 1",
                "Encrypted: A",
                "Decrypts back: yes")
            .hidden(Lab.typing("m", "13"),
                "Letter: m",
                "Shift: 13",
                "Encrypted: z",
                "Decrypts back: yes")
            .hidden(Lab.typing("c", "-3"),
                "Letter: c",
                "Shift: -3",
                "Encrypted: z",
                "Decrypts back: yes")
            .hidden(Lab.typing("b", "27"),
                "Letter: b",
                "Shift: 27",
                "Encrypted: c",
                "Decrypts back: yes")
            .hidden(Lab.typing("Q", "-26"),
                "Letter: Q",
                "Shift: -26",
                "Encrypted: Q",
                "Decrypts back: yes")
            .hidden(Lab.typing("7", "5"),
                "Letter: 7",
                "Shift: 5",
                "Encrypted: 7",
                "Note: not a letter - unchanged")
            .hidden(Lab.typing("ab"),
                "Letter: ab",
                "INVALID INPUT")
            .hidden(Lab.typing(""),
                "Letter:",
                "INVALID INPUT"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(29), "Checksum Digit", Lab.MEDIUM)
            .stretch()
            .after("C03-M019")
            .brief(
                "Card numbers end in a check digit chosen by the Luhn "
                + "algorithm, so a single mistyped digit is caught before any "
                + "payment system sees it. Luhn walks the digits from the "
                + "right - and with no loops yet, a method that calls itself "
                + "can walk them for you.")
            .practises("Recursion on text", "Alternating state through parameters", "Checksums")
            .spec(
                "Prompt Card number: and read it; remove spaces. It must be 8 to 19 digits, otherwise print INVALID INPUT and stop.",
                "luhnSum(digits, doubleIt): the sum of the digits from the right, where every second digit - starting with the one given doubleIt - is doubled, subtracting 9 from any doubled value over 9. It must call itself on the digits without their last one, with doubleIt flipped.",
                "A number is valid when luhnSum(number, false) is a multiple of 10. checkDigitFor(partial) returns the digit that would make partial valid: (10 - luhnSum(partial, true) % 10) % 10.",
                "Print Luhn sum: <sum>, then Checksum: VALID or Checksum: INVALID, then Check digit for the first <n - 1> digits: <d>.")
            .needsMethod("static boolean isCardDigits(String)")
            .needsMethod("static int luhnSum(String, boolean)")
            .needsMethod("static int checkDigitFor(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Card number: \");",
                "        String digits = input.nextLine().replace(\" \", \"\");",
                "        // validate, sum, check, and find the check digit",
                "    }",
                "",
                "    // isCardDigits, luhnSum, checkDigitFor",
                "}")
            .hints(
                "The base case is an empty String: its sum is 0.",
                "The last digit as a number, without chars:\n"
                + "\n"
                + "    int last = Integer.parseInt(\n"
                + "            digits.substring(digits.length() - 1));",
                "Double it if doubleIt is true, subtract 9 if that went over "
                + "9, then add the sum of the rest:\n"
                + "\n"
                + "    return value + luhnSum(rest, !doubleIt);",
                "The partial number is everything but the last digit: "
                + "digits.substring(0, digits.length() - 1).")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Card number: \");",
                "        String digits = input.nextLine().replace(\" \", \"\");",
                "        if (!isCardDigits(digits)) {",
                "            System.out.println(\"INVALID INPUT\");",
                "            return;",
                "        }",
                "        int sum = luhnSum(digits, false);",
                "        System.out.println(\"Luhn sum: \" + sum);",
                "        String verdict = sum % 10 == 0 ? \"VALID\" : \"INVALID\";",
                "        System.out.println(\"Checksum: \" + verdict);",
                "        String partial = digits.substring(0, digits.length() - 1);",
                "        System.out.println(\"Check digit for the first \"",
                "                + partial.length()",
                "                + \" digits: \" + checkDigitFor(partial));",
                "    }",
                "",
                "    static boolean isCardDigits(String digits) {",
                "        return digits.length() >= 8 && digits.length() <= 19",
                "                && digits.matches(\"[0-9]+\");",
                "    }",
                "",
                "    static int luhnSum(String digits, boolean doubleIt) {",
                "        if (digits.isEmpty()) {",
                "            return 0;",
                "        }",
                "        int end = digits.length() - 1;",
                "        int value = Integer.parseInt(digits.substring(end));",
                "        if (doubleIt) {",
                "            value = value * 2;",
                "            if (value > 9) {",
                "                value = value - 9;",
                "            }",
                "        }",
                "        return value + luhnSum(digits.substring(0, end), !doubleIt);",
                "    }",
                "",
                "    static int checkDigitFor(String partial) {",
                "        return (10 - luhnSum(partial, true) % 10) % 10;",
                "    }",
                "}")
            .walkthrough(
                "Each call handles the LAST digit and hands the rest to "
                + "another call, with doubleIt flipped - so the doubling "
                + "alternates without any counter. The empty String is the "
                + "base case, and every call is one digit shorter, so the "
                + "recursion always ends after at most 19 frames.\n"
                + "\n"
                + "checkDigitFor passes true because, once a check digit is "
                + "added after it, the partial number's last digit becomes "
                + "the second from the right - the first to be doubled. Luhn "
                + "catches every single-digit typo and most swapped pairs. It "
                + "is an error check, NOT security: anyone can compute a valid "
                + "check digit, as this method just did.")
            .sample(Lab.typing("4111 1111 1111 1111"),
                "Card number: 4111 1111 1111 1111",
                "Luhn sum: 30",
                "Checksum: VALID",
                "Check digit for the first 15 digits: 1")
            .hidden(Lab.typing("4111 1111 1111 1112"),
                "Card number: 4111 1111 1111 1112",
                "Luhn sum: 31",
                "Checksum: INVALID",
                "Check digit for the first 15 digits: 1")
            .hidden(Lab.typing("79927398713"),
                "Card number: 79927398713",
                "Luhn sum: 70",
                "Checksum: VALID",
                "Check digit for the first 10 digits: 3")
            .hidden(Lab.typing("5500 0000 0000 0004"),
                "Card number: 5500 0000 0000 0004",
                "Luhn sum: 10",
                "Checksum: VALID",
                "Check digit for the first 15 digits: 4")
            .hidden(Lab.typing("340000000000009"),
                "Card number: 340000000000009",
                "Luhn sum: 20",
                "Checksum: VALID",
                "Check digit for the first 14 digits: 9")
            .hidden(Lab.typing("1234"),
                "Card number: 1234",
                "INVALID INPUT")
            .hidden(Lab.typing("4111-1111-1111-1111"),
                "Card number: 4111-1111-1111-1111",
                "INVALID INPUT"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(30), "Security Toolkit", Lab.CAPSTONE)
            .after("C03-M030")
            .brief(
                "CAPSTONE. The VPN gateway needs its login check rebuilt from "
                + "trustworthy parts: validators for every field, a blocklist, "
                + "a lockout, a password and an MFA code - checked in a "
                + "fail-closed order, with an audit line nothing typed can "
                + "forge. Everything in this campaign goes in.")
            .practises("Validation helpers", "Fail-fast, fail-closed ordering", "Sanitised audit output", "Conditional prompts", "A decision built from small methods")
            .spec(
                "Constants: MAX_FAILURES 5, BLOCKED_RANGE 203.0.113., SECRET Northstar#2026, MFA_CODE 481516.",
                "Prompt Username: (read as typed), Source IP: (trimmed) and Failed attempts: (trimmed).",
                "preCheck(user, ip, failures) returns the FIRST problem, or an empty String: invalid username (3-20 characters, no spaces, starts with a letter, checked on the trimmed, lower-cased name); invalid address (only digits and exactly three dots, no .., not starting or ending with ., at most 15 characters); blocked range; bad failure count (parseCount gives -1 unless the text is 1 to 3 digits); account locked (MAX_FAILURES or more).",
                "Only if preCheck finds nothing, prompt Password: (as typed). Wrong password: wrong password. Otherwise prompt MFA code: (trimmed): not six digits - bad MFA code format; six digits but wrong - wrong MFA code.",
                "Print DECISION: ALLOW or DECISION: DENY - never the reason. Then the audit record AUDIT|<u>|<i>|<ALLOW or DENY>|<reason, or ok>, where u and i are the trimmed username and the address passed through forLog: (empty) if blank, every | replaced by /, cut to 20 characters plus ... if longer.")
            .needsMethod("static String normalise(String)")
            .needsMethod("static boolean isValidUsername(String)")
            .needsMethod("static boolean isValidIp(String)")
            .needsMethod("static boolean isBlocked(String)")
            .needsMethod("static int parseCount(String)")
            .needsMethod("static boolean shouldLock(int)")
            .needsMethod("static boolean isValidCode(String)")
            .needsMethod("static String forLog(String)")
            .needsMethod("static String preCheck(String, String, int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int MAX_FAILURES = 5;",
                "    static final String BLOCKED_RANGE = \"203.0.113.\";",
                "    static final String SECRET = \"Northstar#2026\";",
                "    static final String MFA_CODE = \"481516\";",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String user = input.nextLine();",
                "        System.out.print(\"Source IP: \");",
                "        String ip = input.nextLine().trim();",
                "        System.out.print(\"Failed attempts: \");",
                "        String failures = input.nextLine().trim();",
                "        // preCheck; then, only if it passes, password and MFA",
                "        // then DECISION and AUDIT",
                "    }",
                "",
                "    // the helpers, one job each",
                "}")
            .hints(
                "Build and test the helpers one at a time - most of them are "
                + "from earlier missions and labs: normalise (lab 5), "
                + "isValidUsername (lab 12), forLog (lab 7), the IP shape "
                + "(Campaign 02's capstone), shouldLock (mission 20).",
                "isValidIp without a loop: remove the dots and compare "
                + "lengths to count them, then check the rest is digits:\n"
                + "\n"
                + "    String digits = ip.replace(\".\", \"\");\n"
                + "    int dots = ip.length() - digits.length();",
                "preCheck is a list of guards returning reason texts, ending "
                + "in  return \"\";  - an empty reason means no problem.",
                "In main, keep one String reason. Set it from preCheck; only "
                + "if it is empty, ask for the password; only if that is "
                + "right, ask for the code. At the end, an empty reason means "
                + "ALLOW - which is the only way to get it.",
                "The audit record joins five parts with | - and only forLog's "
                + "output goes between the bars, so no value can add a bar "
                + "of its own.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int MAX_FAILURES = 5;",
                "    static final String BLOCKED_RANGE = \"203.0.113.\";",
                "    static final String SECRET = \"Northstar#2026\";",
                "    static final String MFA_CODE = \"481516\";",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String user = input.nextLine();",
                "        System.out.print(\"Source IP: \");",
                "        String ip = input.nextLine().trim();",
                "        System.out.print(\"Failed attempts: \");",
                "        String failures = input.nextLine().trim();",
                "",
                "        String reason = preCheck(user, ip, parseCount(failures));",
                "        if (reason.isEmpty()) {",
                "            System.out.print(\"Password: \");",
                "            String password = input.nextLine();",
                "            if (!password.equals(SECRET)) {",
                "                reason = \"wrong password\";",
                "            } else {",
                "                System.out.print(\"MFA code: \");",
                "                String code = input.nextLine().trim();",
                "                if (!isValidCode(code)) {",
                "                    reason = \"bad MFA code format\";",
                "                } else if (!code.equals(MFA_CODE)) {",
                "                    reason = \"wrong MFA code\";",
                "                }",
                "            }",
                "        }",
                "",
                "        String result = reason.isEmpty() ? \"ALLOW\" : \"DENY\";",
                "        System.out.println(\"DECISION: \" + result);",
                "        String why = reason.isEmpty() ? \"ok\" : reason;",
                "        System.out.println(\"AUDIT|\" + forLog(user.trim()) + \"|\"",
                "                + forLog(ip) + \"|\" + result + \"|\" + why);",
                "    }",
                "",
                "    static String normalise(String text) {",
                "        return text.trim().toLowerCase();",
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
                "    static boolean isValidIp(String ip) {",
                "        String digits = ip.replace(\".\", \"\");",
                "        int dots = ip.length() - digits.length();",
                "        return dots == 3 && digits.matches(\"[0-9]+\")",
                "                && !ip.contains(\"..\") && !ip.startsWith(\".\")",
                "                && !ip.endsWith(\".\") && ip.length() <= 15;",
                "    }",
                "",
                "    static boolean isBlocked(String ip) {",
                "        return ip.startsWith(BLOCKED_RANGE);",
                "    }",
                "",
                "    static int parseCount(String text) {",
                "        if (text.length() > 3 || !text.matches(\"[0-9]+\")) {",
                "            return -1;",
                "        }",
                "        return Integer.parseInt(text);",
                "    }",
                "",
                "    static boolean shouldLock(int failures) {",
                "        return failures >= MAX_FAILURES;",
                "    }",
                "",
                "    static boolean isValidCode(String code) {",
                "        return code.length() == 6 && code.matches(\"[0-9]+\");",
                "    }",
                "",
                "    static String forLog(String text) {",
                "        if (text.isBlank()) {",
                "            return \"(empty)\";",
                "        }",
                "        String clean = text.replace(\"|\", \"/\");",
                "        if (clean.length() > 20) {",
                "            clean = clean.substring(0, 20) + \"...\";",
                "        }",
                "        return clean;",
                "    }",
                "",
                "    static String preCheck(String user, String ip, int failures) {",
                "        if (!isValidUsername(normalise(user))) {",
                "            return \"invalid username\";",
                "        }",
                "        if (!isValidIp(ip)) {",
                "            return \"invalid address\";",
                "        }",
                "        if (isBlocked(ip)) {",
                "            return \"blocked range\";",
                "        }",
                "        if (failures == -1) {",
                "            return \"bad failure count\";",
                "        }",
                "        if (shouldLock(failures)) {",
                "            return \"account locked\";",
                "        }",
                "        return \"\";",
                "    }",
                "}")
            .walkthrough(
                "Every rule lives in a small method with one job, and preCheck "
                + "reads like the gateway's policy: who, from where, and "
                + "whether the account may try at all - all BEFORE the "
                + "password is looked at. A locked account or a blocked range "
                + "never gets to test a password, so it learns nothing.\n"
                + "\n"
                + "The flow fails closed. reason starts as whatever preCheck "
                + "found, every later step can only ADD a reason, and ALLOW is "
                + "printed only when nothing did. The password and MFA "
                + "prompts appear only when the step before succeeded - the "
                + "hidden tests type exactly as many lines as a correct "
                + "program asks for.\n"
                + "\n"
                + "The two output lines split what people see from what "
                + "defenders see: DECISION never says why, so an attacker "
                + "cannot tell a wrong password from a locked account, while "
                + "AUDIT records the reason - through forLog, so a user name "
                + "like eve|1.2.3.4|ALLOW|ok cannot forge a record in the "
                + "evidence. "
                + "That is defence in depth, built from methods you can test "
                + "one at a time.")
            .sample(Lab.typing("jsmith", "10.0.0.7", "0", "Northstar#2026", "481516"),
                "Username: jsmith",
                "Source IP: 10.0.0.7",
                "Failed attempts: 0",
                "Password: Northstar#2026",
                "MFA code: 481516",
                "DECISION: ALLOW",
                "AUDIT|jsmith|10.0.0.7|ALLOW|ok")
            .hidden(Lab.typing(" JSmith ", "10.0.0.7", "2", "Northstar#2026", "481516"),
                "Username:  JSmith",
                "Source IP: 10.0.0.7",
                "Failed attempts: 2",
                "Password: Northstar#2026",
                "MFA code: 481516",
                "DECISION: ALLOW",
                "AUDIT|JSmith|10.0.0.7|ALLOW|ok")
            .hidden(Lab.typing("js", "10.0.0.7", "0"),
                "Username: js",
                "Source IP: 10.0.0.7",
                "Failed attempts: 0",
                "DECISION: DENY",
                "AUDIT|js|10.0.0.7|DENY|invalid username")
            .hidden(Lab.typing("jsmith", "10.0.0", "0"),
                "Username: jsmith",
                "Source IP: 10.0.0",
                "Failed attempts: 0",
                "DECISION: DENY",
                "AUDIT|jsmith|10.0.0|DENY|invalid address")
            .hidden(Lab.typing("jsmith", "203.0.113.9", "0"),
                "Username: jsmith",
                "Source IP: 203.0.113.9",
                "Failed attempts: 0",
                "DECISION: DENY",
                "AUDIT|jsmith|203.0.113.9|DENY|blocked range")
            .hidden(Lab.typing("jsmith", "10.0.0.7", "many"),
                "Username: jsmith",
                "Source IP: 10.0.0.7",
                "Failed attempts: many",
                "DECISION: DENY",
                "AUDIT|jsmith|10.0.0.7|DENY|bad failure count")
            .hidden(Lab.typing("jsmith", "10.0.0.7", "5"),
                "Username: jsmith",
                "Source IP: 10.0.0.7",
                "Failed attempts: 5",
                "DECISION: DENY",
                "AUDIT|jsmith|10.0.0.7|DENY|account locked")
            .hidden(Lab.typing("jsmith", "10.0.0.7", "4", "northstar#2026"),
                "Username: jsmith",
                "Source IP: 10.0.0.7",
                "Failed attempts: 4",
                "Password: northstar#2026",
                "DECISION: DENY",
                "AUDIT|jsmith|10.0.0.7|DENY|wrong password")
            .hidden(Lab.typing("jsmith", "10.0.0.7", "0", "Northstar#2026", "48151"),
                "Username: jsmith",
                "Source IP: 10.0.0.7",
                "Failed attempts: 0",
                "Password: Northstar#2026",
                "MFA code: 48151",
                "DECISION: DENY",
                "AUDIT|jsmith|10.0.0.7|DENY|bad MFA code format")
            .hidden(Lab.typing("jsmith", "10.0.0.7", "0", "Northstar#2026", "123456"),
                "Username: jsmith",
                "Source IP: 10.0.0.7",
                "Failed attempts: 0",
                "Password: Northstar#2026",
                "MFA code: 123456",
                "DECISION: DENY",
                "AUDIT|jsmith|10.0.0.7|DENY|wrong MFA code")
            .hidden(Lab.typing("eve|1.2.3.4|ALLOW|ok", "10.0.0.7", "0", "guess"),
                "Username: eve|1.2.3.4|ALLOW|ok",
                "Source IP: 10.0.0.7",
                "Failed attempts: 0",
                "Password: guess",
                "DECISION: DENY",
                "AUDIT|eve/1.2.3.4/ALLOW/ok|10.0.0.7|DENY|wrong password")
            .hidden(Lab.typing("averyveryverylongusername1", "10.0.0.7", "0"),
                "Username: averyveryverylongusername1",
                "Source IP: 10.0.0.7",
                "Failed attempts: 0",
                "DECISION: DENY",
                "AUDIT|averyveryverylonguse...|10.0.0.7|DENY|invalid username")
            .hidden(Lab.typing("   ", "10..0.1", "0"),
                "Username:",
                "Source IP: 10..0.1",
                "Failed attempts: 0",
                "DECISION: DENY",
                "AUDIT|(empty)|10..0.1|DENY|invalid username")
            .hidden(Lab.typing("jsmith", "10.0.0.7", ""),
                "Username: jsmith",
                "Source IP: 10.0.0.7",
                "Failed attempts:",
                "DECISION: DENY",
                "AUDIT|jsmith|10.0.0.7|DENY|bad failure count")
            .hidden(Lab.typing("9lives", "10.0.0.7", "0"),
                "Username: 9lives",
                "Source IP: 10.0.0.7",
                "Failed attempts: 0",
                "DECISION: DENY",
                "AUDIT|9lives|10.0.0.7|DENY|invalid username"));
    }
}
