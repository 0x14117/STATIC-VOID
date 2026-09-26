/**
 * LABS - CAMPAIGN 04 - LOOP//CONTROL
 *
 * Programs that repeat. Every lab names the methods it needs, so the loops
 * live inside small, named methods, as Campaign 03 taught. Arrays are
 * Campaign 05, so a lab that must remember several values keeps them in a
 * few variables or builds them into a String.
 */
public class Campaign04Labs {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(1), "Countdown", Lab.SMALL)
            .after("C04-M001")
            .brief(
                "Before an idle session is closed, the console counts down so "
                + "the analyst can save their work. Read how many seconds are "
                + "left and count down to the end - with a loop, inside a "
                + "method.")
            .practises("while or for", "A loop inside a method", "Validating before looping")
            .spec(
                "Prompt Seconds left (0-20): and read a whole number.",
                "Outside 0 to 20: print INVALID and stop.",
                "Otherwise countdown(n) prints Closing in <n>... for each number from n down to 1, then Session closed.")
            .needsMethod("static void countdown(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Seconds left (0-20): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        // validate, then count down",
                "    }",
                "",
                "    // declare countdown(int seconds) here",
                "}")
            .hints(
                "Check the range in main, before calling countdown.",
                "A for loop can count DOWN: start at seconds, keep going "
                + "while it is at least 1, and use -- as the update.",
                "for (int s = seconds; s >= 1; s--) { ... }",
                "Session closed goes after the loop, not inside it.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Seconds left (0-20): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        if (n < 0 || n > 20) {",
                "            System.out.println(\"INVALID\");",
                "        } else {",
                "            countdown(n);",
                "        }",
                "    }",
                "",
                "    static void countdown(int seconds) {",
                "        for (int s = seconds; s >= 1; s--) {",
                "            System.out.println(\"Closing in \" + s + \"...\");",
                "        }",
                "        System.out.println(\"Session closed\");",
                "    }",
                "}")
            .walkthrough(
                "The loop counts down from seconds to 1, one line per pass, "
                + "and the final message sits after the loop so it prints "
                + "exactly once. With 0 seconds the test s >= 1 is false at "
                + "once: no countdown lines, just Session closed - the loop "
                + "handles the edge case with no extra code.\n"
                + "\n"
                + "Validation happens before the loop is ever reached. A loop "
                + "driven by input should never be handed a value that would "
                + "make it run for ever or a million times.")
            .sample(Lab.typing("3"),
                "Seconds left (0-20): 3",
                "Closing in 3...",
                "Closing in 2...",
                "Closing in 1...",
                "Session closed")
            .hidden(Lab.typing("0"),
                "Seconds left (0-20): 0",
                "Session closed")
            .hidden(Lab.typing("1"),
                "Seconds left (0-20): 1",
                "Closing in 1...",
                "Session closed")
            .hidden(Lab.typing("20"),
                "Seconds left (0-20): 20",
                "Closing in 20...",
                "Closing in 19...",
                "Closing in 18...",
                "Closing in 17...",
                "Closing in 16...",
                "Closing in 15...",
                "Closing in 14...",
                "Closing in 13...",
                "Closing in 12...",
                "Closing in 11...",
                "Closing in 10...",
                "Closing in 9...",
                "Closing in 8...",
                "Closing in 7...",
                "Closing in 6...",
                "Closing in 5...",
                "Closing in 4...",
                "Closing in 3...",
                "Closing in 2...",
                "Closing in 1...",
                "Session closed")
            .hidden(Lab.typing("21"),
                "Seconds left (0-20): 21",
                "INVALID")
            .hidden(Lab.typing("-5"),
                "Seconds left (0-20): -5",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(2), "Sum to N", Lab.SMALL)
            .after("C04-M009")
            .brief(
                "A capacity estimate needs the total of 1 + 2 + ... + n for "
                + "large n. Write it as an accumulator loop inside a method - "
                + "and choose a type that will not overflow.")
            .practises("Accumulators", "Choosing long", "Returning a loop's result")
            .spec(
                "Prompt N: and read a whole number from 1 to 100000. Anything else: INVALID.",
                "sumTo(n) adds 1 + 2 + ... + n with a loop and returns the total as a long.",
                "Print Sum 1 to <n>: <total>.")
            .needsMethod("static long sumTo(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"N: \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        System.out.println(\"Sum 1 to \" + n + \": \" + sumTo(n));",
                "    }",
                "",
                "    static long sumTo(int n) {",
                "        int total = 0;",
                "        for (int i = 1; i < n; i++) {",
                "            total += i;",
                "        }",
                "        return total;",
                "    }",
                "}")
            .hints(
                "The starter has three problems: no validation, an "
                + "off-by-one, and a total that is too small a type.",
                "i < n leaves out n itself. The last number to add is n: "
                + "use <=.",
                "The sum to 100000 is about 5 billion - more than an int "
                + "holds. Make total a long.",
                "Validate in main: if n < 1 || n > 100000, print INVALID "
                + "instead.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"N: \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        if (n < 1 || n > 100000) {",
                "            System.out.println(\"INVALID\");",
                "        } else {",
                "            System.out.println(\"Sum 1 to \" + n + \": \" + sumTo(n));",
                "        }",
                "    }",
                "",
                "    static long sumTo(int n) {",
                "        long total = 0;",
                "        for (int i = 1; i <= n; i++) {",
                "            total += i;",
                "        }",
                "        return total;",
                "    }",
                "}")
            .walkthrough(
                "Three bugs in eight lines, all from this campaign and "
                + "Campaign 01. i < n was an off-by-one: the sum to 10 came "
                + "out as 45, missing the 10. int total overflowed silently "
                + "for large n - the sum to 100000 is 5,000,050,000, over an "
                + "int's 2.1 billion. And nothing stopped a negative n.\n"
                + "\n"
                + "The hidden tests include n = 1 (a single pass) and n = "
                + "100000 (the overflow). Loops that accumulate need a type "
                + "big enough for the LAST total, not the first.")
            .sample(Lab.typing("10"),
                "N: 10",
                "Sum 1 to 10: 55")
            .hidden(Lab.typing("1"),
                "N: 1",
                "Sum 1 to 1: 1")
            .hidden(Lab.typing("100"),
                "N: 100",
                "Sum 1 to 100: 5050")
            .hidden(Lab.typing("100000"),
                "N: 100000",
                "Sum 1 to 100000: 5000050000")
            .hidden(Lab.typing("0"),
                "N: 0",
                "INVALID")
            .hidden(Lab.typing("100001"),
                "N: 100001",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(3), "Times Table", Lab.SMALL)
            .stretch()
            .after("C04-M007")
            .brief(
                "A warm-up for the for loop: print any times table from 1 to "
                + "12, lined up in columns. Short, but it exercises the "
                + "start, test and update - and String.format for alignment.")
            .practises("for loops", "String.format", "A method that prints")
            .spec(
                "Prompt Table: and read a whole number from 1 to 12. Anything else: INVALID.",
                "printTable(n) prints twelve lines, i from 1 to 12, each as n x i = answer - with i and the answer right-aligned in 2 and 3 characters.",
                "For example, the 7 table's third line is 7 x  3 =  21.")
            .needsMethod("static void printTable(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Table: \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        // validate, then print the table",
                "    }",
                "",
                "    // declare printTable(int n) here",
                "}")
            .hints(
                "for (int i = 1; i <= 12; i++)",
                "printf formats one line:  System.out.printf(\"%d x %2d = "
                + "%3d%n\", n, i, n * i);  - %n ends the line.",
                "%2d pads a number to 2 characters on the left - so 3 "
                + "becomes \" 3\" and the columns line up.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Table: \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        if (n < 1 || n > 12) {",
                "            System.out.println(\"INVALID\");",
                "        } else {",
                "            printTable(n);",
                "        }",
                "    }",
                "",
                "    static void printTable(int n) {",
                "        for (int i = 1; i <= 12; i++) {",
                "            System.out.printf(\"%d x %2d = %3d%n\", n, i, n * i);",
                "        }",
                "    }",
                "}")
            .walkthrough(
                "The for header says the whole range at a glance - 1 to 12 "
                + "inclusive - and the body is one formatted line. %2d and "
                + "%3d pad the numbers on the left, so single and double "
                + "digits line up in columns.\n"
                + "\n"
                + "Formatting output into fixed columns is how reports stay "
                + "readable when numbers change length - the same skill the "
                + "later report labs lean on.")
            .sample(Lab.typing("7"),
                "Table: 7",
                "7 x  1 =   7",
                "7 x  2 =  14",
                "7 x  3 =  21",
                "7 x  4 =  28",
                "7 x  5 =  35",
                "7 x  6 =  42",
                "7 x  7 =  49",
                "7 x  8 =  56",
                "7 x  9 =  63",
                "7 x 10 =  70",
                "7 x 11 =  77",
                "7 x 12 =  84")
            .hidden(Lab.typing("1"),
                "Table: 1",
                "1 x  1 =   1",
                "1 x  2 =   2",
                "1 x  3 =   3",
                "1 x  4 =   4",
                "1 x  5 =   5",
                "1 x  6 =   6",
                "1 x  7 =   7",
                "1 x  8 =   8",
                "1 x  9 =   9",
                "1 x 10 =  10",
                "1 x 11 =  11",
                "1 x 12 =  12")
            .hidden(Lab.typing("12"),
                "Table: 12",
                "12 x  1 =  12",
                "12 x  2 =  24",
                "12 x  3 =  36",
                "12 x  4 =  48",
                "12 x  5 =  60",
                "12 x  6 =  72",
                "12 x  7 =  84",
                "12 x  8 =  96",
                "12 x  9 = 108",
                "12 x 10 = 120",
                "12 x 11 = 132",
                "12 x 12 = 144")
            .hidden(Lab.typing("13"),
                "Table: 13",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(4), "Read Until Done", Lab.SMALL)
            .after("C04-M005")
            .brief(
                "The scan queue takes host names one per line until the "
                + "analyst types done - in any capitals. Blank lines are "
                + "ignored. At the end, report how many hosts were queued.")
            .practises("Sentinel loops", "Skipping input", "Case-insensitive sentinels")
            .spec(
                "Repeatedly prompt Host (or done): and read the line, trimmed.",
                "isDone(text) is true for done in any capitals. When it is, stop.",
                "A blank line is skipped without a message. Any other line prints Queued <host>.",
                "Finish with Hosts queued: <n>.")
            .needsMethod("static boolean isDone(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int count = 0;",
                "        // loop: prompt, read, stop on done, skip blanks, queue",
                "        System.out.println(\"Hosts queued: \" + count);",
                "    }",
                "",
                "    // declare isDone(String text) here",
                "}")
            .hints(
                "while (true) with a break fits: prompt and read at the top "
                + "of the body.",
                "isDone:  return text.equalsIgnoreCase(\"done\");",
                "Order in the body: read, then check isDone (break), then "
                + "check isEmpty (continue), then queue and count.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int count = 0;",
                "        while (true) {",
                "            System.out.print(\"Host (or done): \");",
                "            String host = input.nextLine().trim();",
                "            if (isDone(host)) {",
                "                break;",
                "            }",
                "            if (host.isEmpty()) {",
                "                continue;",
                "            }",
                "            count++;",
                "            System.out.println(\"Queued \" + host);",
                "        }",
                "        System.out.println(\"Hosts queued: \" + count);",
                "    }",
                "",
                "    static boolean isDone(String text) {",
                "        return text.equalsIgnoreCase(\"done\");",
                "    }",
                "}")
            .walkthrough(
                "while (true) with the read at the top means one read, no "
                + "priming read, and a continue that cannot skip anything "
                + "important. The sentinel check comes first so DONE is never "
                + "queued; the blank check comes next so empty lines are "
                + "never counted.\n"
                + "\n"
                + "Accepting DONE and Done as well as done is kinder to people "
                + "- but a sentinel should still be something no real host is "
                + "called. The hidden tests include an immediate done (zero "
                + "hosts) and blank lines between real ones.")
            .sample(Lab.typing("web-01", "db-02", "done"),
                "Host (or done): web-01",
                "Queued web-01",
                "Host (or done): db-02",
                "Queued db-02",
                "Host (or done): done",
                "Hosts queued: 2")
            .hidden(Lab.typing("done"),
                "Host (or done): done",
                "Hosts queued: 0")
            .hidden(Lab.typing("fw-01", "", "mail-01", "", "DONE"),
                "Host (or done): fw-01",
                "Queued fw-01",
                "Host (or done):",
                "Host (or done): mail-01",
                "Queued mail-01",
                "Host (or done):",
                "Host (or done): DONE",
                "Hosts queued: 2")
            .hidden(Lab.typing("  a  ", "Done"),
                "Host (or done):   a",
                "Queued a",
                "Host (or done): Done",
                "Hosts queued: 1"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(5), "Retry the Port", Lab.SMALL)
            .after("C04-M012")
            .brief(
                "The connection tool asks for a port and gives three chances "
                + "to type a valid one. After three bad answers it gives up. "
                + "A do-while with a limit, and Campaign 03's validator.")
            .practises("do-while", "Retry limits", "A validation method")
            .spec(
                "Prompt Port (1-65535): and read the line, trimmed. isValidPort decides: 1 to 5 digits, value 1 to 65535.",
                "After each invalid answer print Not a valid port. Ask at most 3 times in all.",
                "Finish with Using port <port> after a valid answer, or Too many invalid attempts after three bad ones.")
            .needsMethod("static boolean isValidPort(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // ask up to three times",
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
            .hints(
                "Declare the loop's variables before the do: String text; "
                + "boolean valid; int tries = 0;",
                "Inside: prompt, read, tries++, valid = isValidPort(text), "
                + "and print Not a valid port when !valid.",
                "} while (!valid && tries < 3);",
                "After the loop, valid says which reason stopped it.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        String text;",
                "        boolean valid;",
                "        int tries = 0;",
                "        do {",
                "            System.out.print(\"Port (1-65535): \");",
                "            text = input.nextLine().trim();",
                "            tries++;",
                "            valid = isValidPort(text);",
                "            if (!valid) {",
                "                System.out.println(\"Not a valid port\");",
                "            }",
                "        } while (!valid && tries < 3);",
                "        if (valid) {",
                "            System.out.println(\"Using port \" + text);",
                "        } else {",
                "            System.out.println(\"Too many invalid attempts\");",
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
                "do-while fits because the question must be asked at least "
                + "once. The condition needs both halves: !valid alone would "
                + "retry for ever; tries < 3 alone would ask three times even "
                + "after a good answer. The variables it uses are declared "
                + "before the do, or the while could not see them.\n"
                + "\n"
                + "The hidden tests type exactly as many lines as a correct "
                + "program asks for: one for a good first answer, three for "
                + "three bad ones. A program that asks a fourth time runs out "
                + "of input and crashes - the test notices.")
            .sample(Lab.typing("http", "70000", "8080"),
                "Port (1-65535): http",
                "Not a valid port",
                "Port (1-65535): 70000",
                "Not a valid port",
                "Port (1-65535): 8080",
                "Using port 8080")
            .hidden(Lab.typing("443"),
                "Port (1-65535): 443",
                "Using port 443")
            .hidden(Lab.typing("0", "", "x"),
                "Port (1-65535): 0",
                "Not a valid port",
                "Port (1-65535):",
                "Not a valid port",
                "Port (1-65535): x",
                "Not a valid port",
                "Too many invalid attempts")
            .hidden(Lab.typing("65536", "65535"),
                "Port (1-65535): 65536",
                "Not a valid port",
                "Port (1-65535): 65535",
                "Using port 65535"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(6), "Count the Digits", Lab.SMALL)
            .after("C04-M017")
            .brief(
                "Support staff keep pasting text into the ticket system that "
                + "turns out to contain card numbers. A first, rough check: "
                + "how many digits does the text contain, and is it enough "
                + "to be a card number?")
            .practises("Looping over a String", "A counting method", "Character.isDigit")
            .spec(
                "Prompt Text: and read the line exactly as typed.",
                "countDigits(text) returns how many characters are digits, using a loop.",
                "Print Digits: <d> of <length>. If d is 13 or more, also print WARNING: may contain a card number.")
            .needsMethod("static int countDigits(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Text: \");",
                "        String text = input.nextLine();",
                "        // count, report, warn",
                "    }",
                "",
                "    // declare countDigits(String text) here",
                "}")
            .hints(
                "A counter, starting at 0, before the loop.",
                "for (int i = 0; i < text.length(); i++)",
                "Character.isDigit(text.charAt(i)) says whether this "
                + "character is a digit.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Text: \");",
                "        String text = input.nextLine();",
                "        int digits = countDigits(text);",
                "        System.out.println(\"Digits: \" + digits + \" of \"",
                "                + text.length());",
                "        if (digits >= 13) {",
                "            System.out.println(\"WARNING: may contain a card number\");",
                "        }",
                "    }",
                "",
                "    static int countDigits(String text) {",
                "        int count = 0;",
                "        for (int i = 0; i < text.length(); i++) {",
                "            if (Character.isDigit(text.charAt(i))) {",
                "                count++;",
                "            }",
                "        }",
                "        return count;",
                "    }",
                "}")
            .walkthrough(
                "countDigits hides a loop behind a clear name: main asks one "
                + "question and gets one number. Counting all digits "
                + "anywhere in the text - rather than looking for 16 in a "
                + "row - catches card numbers typed with spaces or dashes.\n"
                + "\n"
                + "It is a rough check, and deliberately so: data-loss "
                + "prevention tools start with cheap tests like this and only "
                + "run the expensive ones (such as the Luhn check from "
                + "Campaign 03) when the cheap test fires. The hidden tests "
                + "include empty text and exactly 13 digits.")
            .sample(Lab.typing("Card 4111 1111 1111 1111 please refund"),
                "Text: Card 4111 1111 1111 1111 please refund",
                "Digits: 16 of 38",
                "WARNING: may contain a card number")
            .hidden(Lab.typing(""),
                "Text:",
                "Digits: 0 of 0")
            .hidden(Lab.typing("Call me on 0151 231 2000"),
                "Text: Call me on 0151 231 2000",
                "Digits: 11 of 24")
            .hidden(Lab.typing("1234567890123"),
                "Text: 1234567890123",
                "Digits: 13 of 13",
                "WARNING: may contain a card number")
            .hidden(Lab.typing("no numbers here"),
                "Text: no numbers here",
                "Digits: 0 of 15"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(7), "Reverse a String", Lab.SMALL)
            .stretch()
            .after("C04-M008")
            .brief(
                "Some obfuscated scripts store their commands backwards so a "
                + "simple text search will not find them. Write the method "
                + "that turns text round - character by character, with a "
                + "loop.")
            .practises("Looping backwards", "Building a String", "Returning new text")
            .spec(
                "Prompt Text: and read the line exactly as typed.",
                "reverse(text) returns the characters in reverse order, built with a loop.",
                "Print Reversed: [<reversed>] - the brackets show any spaces.")
            .needsMethod("static String reverse(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Text: \");",
                "        String text = input.nextLine();",
                "        System.out.println(\"Reversed: [\" + reverse(text) + \"]\");",
                "    }",
                "",
                "    static String reverse(String text) {",
                "        return text;",
                "    }",
                "}")
            .hints(
                "Start with an empty String and add one character per pass.",
                "Loop from the last position down to 0:\n"
                + "\n"
                + "    for (int i = text.length() - 1; i >= 0; i--)",
                "result += text.charAt(i);")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Text: \");",
                "        String text = input.nextLine();",
                "        System.out.println(\"Reversed: [\" + reverse(text) + \"]\");",
                "    }",
                "",
                "    static String reverse(String text) {",
                "        String result = \"\";",
                "        for (int i = text.length() - 1; i >= 0; i--) {",
                "            result += text.charAt(i);",
                "        }",
                "        return result;",
                "    }",
                "}")
            .walkthrough(
                "The loop starts at the last position, length - 1, and stops "
                + "after 0 - the backwards version of the usual for header. "
                + "Each character is joined on to result, so the last "
                + "character of the input comes first.\n"
                + "\n"
                + "Empty text gives an empty result without any special "
                + "case: the loop simply runs zero times. The hidden tests "
                + "include spaces at both ends, which the brackets make "
                + "visible.")
            .sample(Lab.typing("gol.metsys"),
                "Text: gol.metsys",
                "Reversed: [system.log]")
            .hidden(Lab.typing(""),
                "Text:",
                "Reversed: []")
            .hidden(Lab.typing("a"),
                "Text: a",
                "Reversed: [a]")
            .hidden(Lab.typing(" ab c"),
                "Text:  ab c",
                "Reversed: [c ba ]"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(8), "Largest Reading", Lab.SMALL)
            .stretch()
            .after("C04-M010")
            .brief(
                "A temperature sensor in the server room sends readings, "
                + "and sometimes sends garbage. Track the highest and lowest "
                + "VALID readings until the feed ends - and say so clearly if "
                + "there were none.")
            .practises("Maximum and minimum", "Sentinel loops", "Ignoring invalid data")
            .spec(
                "Repeatedly prompt Reading (or -1): and read a whole number. -1 ends the feed.",
                "isValidReading(n) is true for 0 to 60. An invalid reading prints Ignored: <n> and is not used.",
                "At the end, print Highest: <max> and Lowest: <min> of the valid readings - or No valid readings if there were none.")
            .needsMethod("static boolean isValidReading(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read until -1, track highest and lowest valid",
                "    }",
                "",
                "    // declare isValidReading(int n) here",
                "}")
            .hints(
                "Start the maximum at Integer.MIN_VALUE and the minimum at "
                + "Integer.MAX_VALUE, and keep a count of valid readings.",
                "while (true): prompt, read, break on -1.",
                "Invalid: print Ignored and continue. Valid: count it and "
                + "update max and min with Math.max and Math.min.",
                "After the loop, a count of 0 means No valid readings.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int highest = Integer.MIN_VALUE;",
                "        int lowest = Integer.MAX_VALUE;",
                "        int count = 0;",
                "        while (true) {",
                "            System.out.print(\"Reading (or -1): \");",
                "            int n = Integer.parseInt(input.nextLine().trim());",
                "            if (n == -1) {",
                "                break;",
                "            }",
                "            if (!isValidReading(n)) {",
                "                System.out.println(\"Ignored: \" + n);",
                "                continue;",
                "            }",
                "            count++;",
                "            highest = Math.max(highest, n);",
                "            lowest = Math.min(lowest, n);",
                "        }",
                "        if (count == 0) {",
                "            System.out.println(\"No valid readings\");",
                "        } else {",
                "            System.out.println(\"Highest: \" + highest);",
                "            System.out.println(\"Lowest: \" + lowest);",
                "        }",
                "    }",
                "",
                "    static boolean isValidReading(int n) {",
                "        return n >= 0 && n <= 60;",
                "    }",
                "}")
            .walkthrough(
                "Starting the maximum at the smallest possible int and the "
                + "minimum at the largest means the first valid reading "
                + "always replaces both - no special case for the first. "
                + "Invalid readings are reported and skipped with continue, so "
                + "they can never become a record.\n"
                + "\n"
                + "The count is what makes 'no valid readings' honest: without "
                + "it, the program would print Integer.MIN_VALUE as the "
                + "highest temperature. A dashboard showing -2147483648 "
                + "degrees is funny once and dangerous if anyone acts on it.")
            .sample(Lab.typing("22", "31", "999", "19", "-1"),
                "Reading (or -1): 22",
                "Reading (or -1): 31",
                "Reading (or -1): 999",
                "Ignored: 999",
                "Reading (or -1): 19",
                "Reading (or -1): -1",
                "Highest: 31",
                "Lowest: 19")
            .hidden(Lab.typing("-1"),
                "Reading (or -1): -1",
                "No valid readings")
            .hidden(Lab.typing("75", "61", "-1"),
                "Reading (or -1): 75",
                "Ignored: 75",
                "Reading (or -1): 61",
                "Ignored: 61",
                "Reading (or -1): -1",
                "No valid readings")
            .hidden(Lab.typing("0", "60", "-1"),
                "Reading (or -1): 0",
                "Reading (or -1): 60",
                "Reading (or -1): -1",
                "Highest: 60",
                "Lowest: 0")
            .hidden(Lab.typing("40", "-1"),
                "Reading (or -1): 40",
                "Reading (or -1): -1",
                "Highest: 40",
                "Lowest: 40"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(9), "Average Until Blank", Lab.MEDIUM)
            .stretch()
            .after("C04-M009")
            .brief(
                "The response-time report takes values pasted in one per line "
                + "and ends at the first blank line. Some pasted lines are "
                + "not numbers at all. Average the good ones, report the bad "
                + "ones, and never divide by zero.")
            .practises("Accumulators", "Validating each item", "Averages without crashes")
            .spec(
                "Repeatedly prompt Value: and read the line, trimmed. A blank line ends the input.",
                "isWholeNumber(text) is true for 1 to 9 digits. Other lines print Skipped: <text> and are not counted.",
                "At the end print Count: <n>, then Average: <avg> to 2 decimal places - or No values instead of both lines if nothing was counted.")
            .needsMethod("static boolean isWholeNumber(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read until a blank line; total and count the numbers",
                "    }",
                "",
                "    // declare isWholeNumber(String text) here",
                "}")
            .hints(
                "isWholeNumber: length 1 to 9 and matches(\"[0-9]+\").",
                "Keep the total in a long and the count in an int, both "
                + "before the loop.",
                "The average needs a cast:  (double) total / count  - and "
                + "only when count is not 0.",
                "String.format(\"%.2f\", average) gives two decimal places.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        long total = 0;",
                "        int count = 0;",
                "        while (true) {",
                "            System.out.print(\"Value: \");",
                "            String text = input.nextLine().trim();",
                "            if (text.isEmpty()) {",
                "                break;",
                "            }",
                "            if (!isWholeNumber(text)) {",
                "                System.out.println(\"Skipped: \" + text);",
                "                continue;",
                "            }",
                "            total += Integer.parseInt(text);",
                "            count++;",
                "        }",
                "        if (count == 0) {",
                "            System.out.println(\"No values\");",
                "        } else {",
                "            System.out.println(\"Count: \" + count);",
                "            double average = (double) total / count;",
                "            String shown = String.format(\"%.2f\", average);",
                "            System.out.println(\"Average: \" + shown);",
                "        }",
                "    }",
                "",
                "    static boolean isWholeNumber(String text) {",
                "        return text.length() >= 1 && text.length() <= 9",
                "                && text.matches(\"[0-9]+\");",
                "    }",
                "}")
            .walkthrough(
                "The blank line is the sentinel, and every other line is "
                + "validated BEFORE parseInt ever sees it, so '12ms' or 'n/a' "
                + "is reported instead of crashing the report. total is a "
                + "long because many large values can add up past an int.\n"
                + "\n"
                + "The average is guarded twice: a count of 0 means there is "
                + "nothing to average, and the cast to double keeps the "
                + "fraction. The hidden tests include a feed that is all "
                + "garbage - which must print No values, not crash on a "
                + "division by zero.")
            .sample(Lab.typing("120", "95", "n/a", "210", ""),
                "Value: 120",
                "Value: 95",
                "Value: n/a",
                "Skipped: n/a",
                "Value: 210",
                "Value:",
                "Count: 3",
                "Average: 141.67")
            .hidden(Lab.typing(""),
                "Value:",
                "No values")
            .hidden(Lab.typing("abc", "12ms", ""),
                "Value: abc",
                "Skipped: abc",
                "Value: 12ms",
                "Skipped: 12ms",
                "Value:",
                "No values")
            .hidden(Lab.typing("1", "2", ""),
                "Value: 1",
                "Value: 2",
                "Value:",
                "Count: 2",
                "Average: 1.50")
            .hidden(Lab.typing("999999999", "999999999", "999999999", ""),
                "Value: 999999999",
                "Value: 999999999",
                "Value: 999999999",
                "Value:",
                "Count: 3",
                "Average: 999999999.00"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(10), "Password Strength", Lab.MEDIUM)
            .after("C04-M018")
            .brief(
                "The sign-up page's meter needs rebuilding with real counts: "
                + "loop over the password once, classify every character, "
                + "and score it - with length carrying the most weight, as "
                + "current guidance recommends.")
            .practises("Classifying characters", "Several counting methods", "A score built from counts")
            .spec(
                "Prompt Password: and read the line exactly as typed.",
                "countUpper, countLower, countDigits and countSymbols each return how many characters of that kind there are. Symbols are anything that is not a letter or digit.",
                "score: 3 for 16 or more characters, 2 for 12 to 15, 1 for 8 to 11, 0 below 8 - plus 1 for each of the four kinds that appears at least once. The maximum is 7.",
                "Print Upper: <u>  Lower: <l>  Digits: <d>  Symbols: <s>, then Score: <n>/7, then Rating: WEAK (0-3), FAIR (4-5) or STRONG (6-7).")
            .needsMethod("static int countUpper(String)")
            .needsMethod("static int countLower(String)")
            .needsMethod("static int countDigits(String)")
            .needsMethod("static int countSymbols(String)")
            .needsMethod("static int score(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Password: \");",
                "        String pw = input.nextLine();",
                "        // counts, score, rating",
                "    }",
                "",
                "    // the four counting methods and score",
                "}")
            .hints(
                "Each counting method is the same loop with a different "
                + "Character test: isUpperCase, isLowerCase, isDigit, and "
                + "!isLetterOrDigit for symbols.",
                "score starts with length points from an if / else if "
                + "chain, then adds one for each count that is above 0.",
                "The ratings are ranges of the score: <= 3 WEAK, <= 5 FAIR, "
                + "otherwise STRONG.",
                "Two spaces separate the counts on the first line.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Password: \");",
                "        String pw = input.nextLine();",
                "        System.out.println(\"Upper: \" + countUpper(pw)",
                "                + \"  Lower: \" + countLower(pw)",
                "                + \"  Digits: \" + countDigits(pw)",
                "                + \"  Symbols: \" + countSymbols(pw));",
                "        int points = score(pw);",
                "        System.out.println(\"Score: \" + points + \"/7\");",
                "        String rating;",
                "        if (points <= 3) {",
                "            rating = \"WEAK\";",
                "        } else if (points <= 5) {",
                "            rating = \"FAIR\";",
                "        } else {",
                "            rating = \"STRONG\";",
                "        }",
                "        System.out.println(\"Rating: \" + rating);",
                "    }",
                "",
                "    static int countUpper(String s) {",
                "        int n = 0;",
                "        for (int i = 0; i < s.length(); i++) {",
                "            if (Character.isUpperCase(s.charAt(i))) {",
                "                n++;",
                "            }",
                "        }",
                "        return n;",
                "    }",
                "",
                "    static int countLower(String s) {",
                "        int n = 0;",
                "        for (int i = 0; i < s.length(); i++) {",
                "            if (Character.isLowerCase(s.charAt(i))) {",
                "                n++;",
                "            }",
                "        }",
                "        return n;",
                "    }",
                "",
                "    static int countDigits(String s) {",
                "        int n = 0;",
                "        for (int i = 0; i < s.length(); i++) {",
                "            if (Character.isDigit(s.charAt(i))) {",
                "                n++;",
                "            }",
                "        }",
                "        return n;",
                "    }",
                "",
                "    static int countSymbols(String s) {",
                "        int n = 0;",
                "        for (int i = 0; i < s.length(); i++) {",
                "            if (!Character.isLetterOrDigit(s.charAt(i))) {",
                "                n++;",
                "            }",
                "        }",
                "        return n;",
                "    }",
                "",
                "    static int score(String pw) {",
                "        int points;",
                "        if (pw.length() >= 16) {",
                "            points = 3;",
                "        } else if (pw.length() >= 12) {",
                "            points = 2;",
                "        } else if (pw.length() >= 8) {",
                "            points = 1;",
                "        } else {",
                "            points = 0;",
                "        }",
                "        if (countUpper(pw) > 0) {",
                "            points++;",
                "        }",
                "        if (countLower(pw) > 0) {",
                "            points++;",
                "        }",
                "        if (countDigits(pw) > 0) {",
                "            points++;",
                "        }",
                "        if (countSymbols(pw) > 0) {",
                "            points++;",
                "        }",
                "        return points;",
                "    }",
                "}")
            .walkthrough(
                "Four small counting methods share one shape - a loop and a "
                + "Character test - and score is built on top of them. Length "
                + "can earn up to 3 of the 7 points on its own, which matches "
                + "current guidance: a long passphrase of plain words scores "
                + "as well as a short jumble of symbols, and a passphrase "
                + "with a capital and a number in it beats both.\n"
                + "\n"
                + "The four loops could be one loop with four counters, which "
                + "is faster. Four methods are easier to read, test and "
                + "reuse, and for a password the speed difference is nothing - "
                + "a trade-off worth making on purpose. (Campaign 05's arrays "
                + "offer a way to have both.)")
            .sample(Lab.typing("Tr0ub4dor&3"),
                "Password: Tr0ub4dor&3",
                "Upper: 1  Lower: 6  Digits: 3  Symbols: 1",
                "Score: 5/7",
                "Rating: FAIR")
            .hidden(Lab.typing("password"),
                "Password: password",
                "Upper: 0  Lower: 8  Digits: 0  Symbols: 0",
                "Score: 2/7",
                "Rating: WEAK")
            .hidden(Lab.typing("correct horse battery staple"),
                "Password: correct horse battery staple",
                "Upper: 0  Lower: 25  Digits: 0  Symbols: 3",
                "Score: 5/7",
                "Rating: FAIR")
            .hidden(Lab.typing(""),
                "Password:",
                "Upper: 0  Lower: 0  Digits: 0  Symbols: 0",
                "Score: 0/7",
                "Rating: WEAK")
            .hidden(Lab.typing("Ab1!"),
                "Password: Ab1!",
                "Upper: 1  Lower: 1  Digits: 1  Symbols: 1",
                "Score: 4/7",
                "Rating: FAIR")
            .hidden(Lab.typing("Blue-Kettle-Rain-42"),
                "Password: Blue-Kettle-Rain-42",
                "Upper: 3  Lower: 11  Digits: 2  Symbols: 3",
                "Score: 7/7",
                "Rating: STRONG"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(11), "Three Strikes Login", Lab.MEDIUM)
            .after("C04-M021")
            .brief(
                "The maintenance console gives an administrator three tries "
                + "at the password. Each wrong answer says how many tries "
                + "are left; the third wrong answer locks the console. A "
                + "correct answer at any point lets them in.")
            .practises("A loop with a limit", "Early exit on success", "Lockout state")
            .spec(
                "The password is Northstar#2026, compared exactly - no trim. checkPassword(text) answers whether it is right.",
                "Prompt Password: and read the line, up to 3 times.",
                "Right: print ACCESS GRANTED and ask nothing more. Wrong: print Wrong password - tries left: <n>, where n is 2, then 1.",
                "After the third wrong answer print ACCOUNT LOCKED instead of the tries-left line.")
            .needsMethod("static boolean checkPassword(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int MAX_TRIES = 3;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // up to MAX_TRIES attempts",
                "    }",
                "",
                "    // declare checkPassword(String text) here",
                "}")
            .hints(
                "A for loop over the tries works: for (int t = 1; t <= "
                + "MAX_TRIES; t++).",
                "On a right password, print and break - the loop must not "
                + "ask again.",
                "Tries left after try t is MAX_TRIES - t. When that is 0, "
                + "the account is locked.",
                "checkPassword:  return text.equals(\"Northstar#2026\");")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int MAX_TRIES = 3;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        for (int t = 1; t <= MAX_TRIES; t++) {",
                "            System.out.print(\"Password: \");",
                "            String text = input.nextLine();",
                "            if (checkPassword(text)) {",
                "                System.out.println(\"ACCESS GRANTED\");",
                "                break;",
                "            }",
                "            int left = MAX_TRIES - t;",
                "            if (left == 0) {",
                "                System.out.println(\"ACCOUNT LOCKED\");",
                "            } else {",
                "                String msg = \"Wrong password - tries left: \" + left;",
                "                System.out.println(msg);",
                "            }",
                "        }",
                "    }",
                "",
                "    static boolean checkPassword(String text) {",
                "        return text.equals(\"Northstar#2026\");",
                "    }",
                "}")
            .walkthrough(
                "The for loop sets the hard limit: at most three passes, "
                + "whatever is typed. break ends it early on success, so a "
                + "correct second attempt is not followed by a third "
                + "prompt - the hidden tests type exactly as many lines as a "
                + "correct program asks for.\n"
                + "\n"
                + "The password is compared exactly: northstar#2026 and a "
                + "version with a stray space are both wrong. A real console "
                + "would also record the lock somewhere that survives a "
                + "restart - otherwise closing and reopening it would reset "
                + "the three tries.")
            .sample(Lab.typing("admin", "Northstar#2026"),
                "Password: admin",
                "Wrong password - tries left: 2",
                "Password: Northstar#2026",
                "ACCESS GRANTED")
            .hidden(Lab.typing("Northstar#2026"),
                "Password: Northstar#2026",
                "ACCESS GRANTED")
            .hidden(Lab.typing("a", "b", "c"),
                "Password: a",
                "Wrong password - tries left: 2",
                "Password: b",
                "Wrong password - tries left: 1",
                "Password: c",
                "ACCOUNT LOCKED")
            .hidden(Lab.typing("northstar#2026", " Northstar#2026", "Northstar#2026"),
                "Password: northstar#2026",
                "Wrong password - tries left: 2",
                "Password:  Northstar#2026",
                "Wrong password - tries left: 1",
                "Password: Northstar#2026",
                "ACCESS GRANTED"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(12), "Menu Loop", Lab.MEDIUM)
            .after("C04-M023")
            .brief(
                "The alert console needs a small menu: show the status, "
                + "acknowledge an alert, show how many have been "
                + "acknowledged, or exit. The menu comes back after every "
                + "choice, and nothing typed - however odd - crashes it.")
            .practises("do-while menus", "switch on text", "State that lasts across passes")
            .spec(
                "Each time round, printMenu prints 1 Status  2 Ack  3 Count  9 Exit > (no line break), then the choice is read, trimmed.",
                "1 prints Queue: normal. 2 adds one to the acknowledged count and prints Alert acknowledged. 3 prints Acknowledged: <n>. 9 prints Session closed and ends the loop. Anything else prints Unknown option: <choice>.")
            .needsMethod("static void printMenu()")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // the menu loop",
                "    }",
                "",
                "    // declare printMenu() here",
                "}")
            .hints(
                "Declare the choice and the counter before the do.",
                "do { printMenu(); read; switch } while (!choice.equals(\"9\"));",
                "The count must be declared before the loop, or every pass "
                + "resets it.",
                "Use print, not println, for the menu line - the typed "
                + "choice appears on the same line.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        String choice;",
                "        int acked = 0;",
                "        do {",
                "            printMenu();",
                "            choice = input.nextLine().trim();",
                "            switch (choice) {",
                "                case \"1\" -> System.out.println(\"Queue: normal\");",
                "                case \"2\" -> {",
                "                    acked++;",
                "                    System.out.println(\"Alert acknowledged\");",
                "                }",
                "                case \"3\" ->",
                "                    System.out.println(\"Acknowledged: \" + acked);",
                "                case \"9\" -> System.out.println(\"Session closed\");",
                "                default ->",
                "                    System.out.println(\"Unknown option: \" + choice);",
                "            }",
                "        } while (!choice.equals(\"9\"));",
                "    }",
                "",
                "    static void printMenu() {",
                "        System.out.print(\"1 Status  2 Ack  3 Count  9 Exit > \");",
                "    }",
                "}")
            .walkthrough(
                "do-while shows the menu before there is anything to test. "
                + "The counter lives before the loop, so acknowledgements add "
                + "up across passes. An arrow case can run a block in braces "
                + "when it needs more than one statement, as case 2 does.\n"
                + "\n"
                + "Reading the choice as text means 5, x, or an empty line "
                + "all land in default - answered, never crashed on. The "
                + "hidden tests try exactly those, and an immediate exit.")
            .sample(Lab.typing("1", "2", "2", "3", "9"),
                "1 Status  2 Ack  3 Count  9 Exit > 1",
                "Queue: normal",
                "1 Status  2 Ack  3 Count  9 Exit > 2",
                "Alert acknowledged",
                "1 Status  2 Ack  3 Count  9 Exit > 2",
                "Alert acknowledged",
                "1 Status  2 Ack  3 Count  9 Exit > 3",
                "Acknowledged: 2",
                "1 Status  2 Ack  3 Count  9 Exit > 9",
                "Session closed")
            .hidden(Lab.typing("9"),
                "1 Status  2 Ack  3 Count  9 Exit > 9",
                "Session closed")
            .hidden(Lab.typing("x", "", "3", "9"),
                "1 Status  2 Ack  3 Count  9 Exit > x",
                "Unknown option: x",
                "1 Status  2 Ack  3 Count  9 Exit >",
                "Unknown option:",
                "1 Status  2 Ack  3 Count  9 Exit > 3",
                "Acknowledged: 0",
                "1 Status  2 Ack  3 Count  9 Exit > 9",
                "Session closed")
            .hidden(Lab.typing("2", "5", "3", "9"),
                "1 Status  2 Ack  3 Count  9 Exit > 2",
                "Alert acknowledged",
                "1 Status  2 Ack  3 Count  9 Exit > 5",
                "Unknown option: 5",
                "1 Status  2 Ack  3 Count  9 Exit > 3",
                "Acknowledged: 1",
                "1 Status  2 Ack  3 Count  9 Exit > 9",
                "Session closed"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(13), "Higher or Lower", Lab.MEDIUM)
            .stretch()
            .after("C04-M013")
            .brief(
                "A classic game with a lesson inside it: the player guesses "
                + "a number from 1 to 100 and is told higher or lower. With "
                + "seven tries, a player who always guesses the middle of "
                + "what is left can ALWAYS win. Build the game.")
            .practises("Loops with two exits", "A comparison method", "Validation inside a loop")
            .spec(
                "The secret is the constant SECRET = 42. Allow at most 7 guesses.",
                "Prompt Guess 1-100: and read the line, trimmed. A guess that is not a whole number from 1 to 100 prints Not a valid guess and does NOT use up a try.",
                "compare(guess, secret) returns Higher, Lower or Correct. Print it. Correct ends the game with Guesses used: <n>.",
                "After 7 wrong valid guesses print Out of guesses - it was 42.")
            .needsMethod("static String compare(int, int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int SECRET = 42;",
                "    static final int MAX_GUESSES = 7;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // the guessing loop",
                "    }",
                "",
                "    // declare compare(int guess, int secret) here",
                "}")
            .hints(
                "A while loop on the number of VALID guesses so far: while "
                + "(guesses < MAX_GUESSES).",
                "Validate the text before parseInt: 1 to 3 digits, then the "
                + "range. An invalid guess prints its message and continues "
                + "without counting.",
                "compare returns \"Higher\" when the secret is bigger than "
                + "the guess.",
                "Keep a boolean found, set on Correct, and use it after the "
                + "loop to decide whether to print Out of guesses.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int SECRET = 42;",
                "    static final int MAX_GUESSES = 7;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int guesses = 0;",
                "        boolean found = false;",
                "        while (guesses < MAX_GUESSES) {",
                "            System.out.print(\"Guess 1-100: \");",
                "            String text = input.nextLine().trim();",
                "            if (text.length() > 3 || !text.matches(\"[0-9]+\")",
                "                    || Integer.parseInt(text) < 1",
                "                    || Integer.parseInt(text) > 100) {",
                "                System.out.println(\"Not a valid guess\");",
                "                continue;",
                "            }",
                "            guesses++;",
                "            String result = compare(Integer.parseInt(text), SECRET);",
                "            System.out.println(result);",
                "            if (result.equals(\"Correct\")) {",
                "                found = true;",
                "                System.out.println(\"Guesses used: \" + guesses);",
                "                break;",
                "            }",
                "        }",
                "        if (!found) {",
                "            System.out.println(\"Out of guesses - it was \" + SECRET);",
                "        }",
                "    }",
                "",
                "    static String compare(int guess, int secret) {",
                "        if (guess < secret) {",
                "            return \"Higher\";",
                "        }",
                "        if (guess > secret) {",
                "            return \"Lower\";",
                "        }",
                "        return \"Correct\";",
                "    }",
                "}")
            .walkthrough(
                "The loop has two exits: running out of guesses (the "
                + "condition) and finding the number (the break). found "
                + "records which one happened. Invalid guesses use continue "
                + "BEFORE the count goes up, so typos do not cost a try - "
                + "and the short-circuit || means parseInt only runs on text "
                + "that is already known to be digits.\n"
                + "\n"
                + "Guessing the middle each time - 50, 25, 37, 43, 40, 41, 42 "
                + "- halves what is left, so 7 guesses always cover 100 "
                + "numbers. That halving idea is BINARY SEARCH, one of the "
                + "most important algorithms there is (Campaign 12).")
            .sample(Lab.typing("50", "25", "37", "43", "40", "41", "42"),
                "Guess 1-100: 50",
                "Lower",
                "Guess 1-100: 25",
                "Higher",
                "Guess 1-100: 37",
                "Higher",
                "Guess 1-100: 43",
                "Lower",
                "Guess 1-100: 40",
                "Higher",
                "Guess 1-100: 41",
                "Higher",
                "Guess 1-100: 42",
                "Correct",
                "Guesses used: 7")
            .hidden(Lab.typing("42"),
                "Guess 1-100: 42",
                "Correct",
                "Guesses used: 1")
            .hidden(Lab.typing("1", "2", "3", "4", "5", "6", "7"),
                "Guess 1-100: 1",
                "Higher",
                "Guess 1-100: 2",
                "Higher",
                "Guess 1-100: 3",
                "Higher",
                "Guess 1-100: 4",
                "Higher",
                "Guess 1-100: 5",
                "Higher",
                "Guess 1-100: 6",
                "Higher",
                "Guess 1-100: 7",
                "Higher",
                "Out of guesses - it was 42")
            .hidden(Lab.typing("abc", "0", "101", "42"),
                "Guess 1-100: abc",
                "Not a valid guess",
                "Guess 1-100: 0",
                "Not a valid guess",
                "Guess 1-100: 101",
                "Not a valid guess",
                "Guess 1-100: 42",
                "Correct",
                "Guesses used: 1"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(14), "Character Frequency", Lab.MEDIUM)
            .stretch()
            .after("C04-M015")
            .brief(
                "Counting how often each letter appears is the first step in "
                + "breaking simple substitution ciphers - 'e' is the most "
                + "common letter in English, and it shows. Build a frequency "
                + "table for any text, with no arrays: a loop over the "
                + "alphabet, and a loop over the text inside it.")
            .practises("Nested loops", "Looping over characters", "Counting")
            .spec(
                "Prompt Text: and read the line exactly as typed.",
                "countChar(text, c) returns how many times the letter c appears in the text, ignoring case.",
                "For each letter from a to z, in order, print <letter>: <count> - but only letters that appear at least once. If no letters appear, print No letters.")
            .needsMethod("static int countChar(String, char)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Text: \");",
                "        String text = input.nextLine();",
                "        // for every letter a to z, count and report",
                "    }",
                "",
                "    // declare countChar(String text, char c) here",
                "}")
            .hints(
                "A char can be a loop variable. chars are numbers underneath, "
                + "so ++ moves to the next letter:\n"
                + "\n"
                + "    for (char c = 'a'; c <= 'z'; c++)",
                "countChar lower-cases the text once, then counts matches "
                + "with a loop over its characters.",
                "Keep a boolean any, set when a count is above 0, to decide "
                + "whether to print No letters.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Text: \");",
                "        String text = input.nextLine();",
                "        boolean any = false;",
                "        for (char c = 'a'; c <= 'z'; c++) {",
                "            int n = countChar(text, c);",
                "            if (n > 0) {",
                "                System.out.println(c + \": \" + n);",
                "                any = true;",
                "            }",
                "        }",
                "        if (!any) {",
                "            System.out.println(\"No letters\");",
                "        }",
                "    }",
                "",
                "    static int countChar(String text, char c) {",
                "        String lower = text.toLowerCase();",
                "        int count = 0;",
                "        for (int i = 0; i < lower.length(); i++) {",
                "            if (lower.charAt(i) == c) {",
                "                count++;",
                "            }",
                "        }",
                "        return count;",
                "    }",
                "}")
            .walkthrough(
                "The outer loop walks the alphabet; for each letter, "
                + "countChar runs a whole loop over the text. That is a "
                + "nested loop hidden inside a method call: 26 passes, each "
                + "reading the full text.\n"
                + "\n"
                + "A char loop variable works because chars are numbers "
                + "underneath - 'a' to 'z' are consecutive, so c++ steps "
                + "through the alphabet. Lower-casing once inside countChar "
                + "makes the count ignore case. Frequency analysis like this "
                + "broke substitution ciphers for centuries, which is why "
                + "modern ciphers are designed so that every output symbol "
                + "is equally likely.")
            .sample(Lab.typing("Attack at Dawn"),
                "Text: Attack at Dawn",
                "a: 4",
                "c: 1",
                "d: 1",
                "k: 1",
                "n: 1",
                "t: 3",
                "w: 1")
            .hidden(Lab.typing("12345 !?"),
                "Text: 12345 !?",
                "No letters")
            .hidden(Lab.typing("Zz Zz"),
                "Text: Zz Zz",
                "z: 4")
            .hidden(Lab.typing("the quick brown fox"),
                "Text: the quick brown fox",
                "b: 1",
                "c: 1",
                "e: 1",
                "f: 1",
                "h: 1",
                "i: 1",
                "k: 1",
                "n: 1",
                "o: 2",
                "q: 1",
                "r: 1",
                "t: 1",
                "u: 1",
                "w: 1",
                "x: 1"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(15), "Box Drawer", Lab.MEDIUM)
            .stretch()
            .after("C04-M025")
            .brief(
                "Console dashboards draw boxes around important numbers. "
                + "Build the box maker: any width, any height, with a title "
                + "on the top line - two small methods that each build one "
                + "kind of row.")
            .practises("Building Strings in loops", "Methods that return rows", "Validating sizes")
            .spec(
                "Prompt Width: and Height: and read whole numbers. Width must be 4 to 40 and height 2 to 10, otherwise print INVALID SIZE.",
                "edge(width) returns + then width - 2 dashes then +. middle(width) returns | then width - 2 spaces then |.",
                "Print the top edge, then height - 2 middle rows, then the bottom edge.")
            .needsMethod("static String edge(int)")
            .needsMethod("static String middle(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Width: \");",
                "        int width = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Height: \");",
                "        int height = Integer.parseInt(input.nextLine().trim());",
                "        // validate, then draw",
                "    }",
                "",
                "    // edge(int width) and middle(int width)",
                "}")
            .hints(
                "Each row method builds a String: start with the corner or "
                + "side, loop width - 2 times adding one piece, then add the "
                + "other end.",
                "The rows between the edges: for (int r = 0; r < height - 2; "
                + "r++).",
                "A height of 2 means just the two edges - the middle loop "
                + "runs zero times.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Width: \");",
                "        int width = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Height: \");",
                "        int height = Integer.parseInt(input.nextLine().trim());",
                "        if (width < 4 || width > 40 || height < 2 || height > 10) {",
                "            System.out.println(\"INVALID SIZE\");",
                "            return;",
                "        }",
                "        System.out.println(edge(width));",
                "        for (int r = 0; r < height - 2; r++) {",
                "            System.out.println(middle(width));",
                "        }",
                "        System.out.println(edge(width));",
                "    }",
                "",
                "    static String edge(int width) {",
                "        String row = \"+\";",
                "        for (int i = 0; i < width - 2; i++) {",
                "            row += \"-\";",
                "        }",
                "        return row + \"+\";",
                "    }",
                "",
                "    static String middle(int width) {",
                "        String row = \"|\";",
                "        for (int i = 0; i < width - 2; i++) {",
                "            row += \" \";",
                "        }",
                "        return row + \"|\";",
                "    }",
                "}")
            .walkthrough(
                "Each row is built piece by piece and returned, so main just "
                + "decides the order: edge, some middles, edge. The width "
                + "includes the two corner characters, which is why the "
                + "loops run width - 2 times - a fencepost detail the hidden "
                + "tests check with the smallest box, 4 by 2.\n"
                + "\n"
                + "Validation matters even here: a width of a million would "
                + "build a million-character String, and a negative height "
                + "would silently draw nothing. Limits keep a loop driven by "
                + "input from doing something absurd.")
            .sample(Lab.typing("12", "4"),
                "Width: 12",
                "Height: 4",
                "+----------+",
                "|          |",
                "|          |",
                "+----------+")
            .hidden(Lab.typing("4", "2"),
                "Width: 4",
                "Height: 2",
                "+--+",
                "+--+")
            .hidden(Lab.typing("40", "3"),
                "Width: 40",
                "Height: 3",
                "+--------------------------------------+",
                "|                                      |",
                "+--------------------------------------+")
            .hidden(Lab.typing("3", "5"),
                "Width: 3",
                "Height: 5",
                "INVALID SIZE")
            .hidden(Lab.typing("10", "11"),
                "Width: 10",
                "Height: 11",
                "INVALID SIZE"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(16), "Prime Checker", Lab.MEDIUM)
            .stretch()
            .after("C04-M013")
            .brief(
                "Public-key cryptography, the kind that protects HTTPS, is "
                + "built on prime numbers. Write the checker: find a number's "
                + "smallest divisor with a loop, and stop as soon as you "
                + "know the answer.")
            .practises("Loops that stop early", "Searching for a divisor", "Stopping at the square root")
            .spec(
                "Prompt Number: and read a whole number. Below 2, print <n> is not prime (primes start at 2).",
                "smallestDivisor(n) returns the smallest d of 2 or more that divides n exactly, trying d while d * d <= n - or n itself if none is found.",
                "If the smallest divisor is n, print <n> is prime. Otherwise print <n> is not prime (divisible by <d>).")
            .needsMethod("static int smallestDivisor(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Number: \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        // check and report",
                "    }",
                "",
                "    // declare smallestDivisor(int n) here",
                "}")
            .hints(
                "d divides n exactly when n % d == 0.",
                "for (int d = 2; d * d <= n; d++) - if d divides n, return "
                + "d straight away. That return is the early exit.",
                "If the loop finishes without finding one, n has no divisor "
                + "up to its square root, so return n.",
                "Handle n below 2 in main, before calling the method.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Number: \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        if (n < 2) {",
                "            System.out.println(n",
                "                    + \" is not prime (primes start at 2)\");",
                "            return;",
                "        }",
                "        int d = smallestDivisor(n);",
                "        if (d == n) {",
                "            System.out.println(n + \" is prime\");",
                "        } else {",
                "            System.out.println(n + \" is not prime (divisible by \"",
                "                    + d + \")\");",
                "        }",
                "    }",
                "",
                "    static int smallestDivisor(int n) {",
                "        for (int d = 2; d * d <= n; d++) {",
                "            if (n % d == 0) {",
                "                return d;",
                "            }",
                "        }",
                "        return n;",
                "    }",
                "}")
            .walkthrough(
                "The loop only needs to reach the square root: if n = a x b, "
                + "one of a and b is at most the square root, so a divisor "
                + "would already have been found. d * d <= n says 'up to the "
                + "square root' without any decimals. For 1,000,003 that is "
                + "about 1000 checks instead of a million.\n"
                + "\n"
                + "return inside the loop is an early exit: the first "
                + "divisor found is the smallest, and nothing more is needed. "
                + "Real cryptography uses primes hundreds of digits long - "
                + "far too big for this loop - and much cleverer tests, but "
                + "the idea of stopping as soon as you know is the same.")
            .sample(Lab.typing("97"),
                "Number: 97",
                "97 is prime")
            .hidden(Lab.typing("91"),
                "Number: 91",
                "91 is not prime (divisible by 7)")
            .hidden(Lab.typing("2"),
                "Number: 2",
                "2 is prime")
            .hidden(Lab.typing("1"),
                "Number: 1",
                "1 is not prime (primes start at 2)")
            .hidden(Lab.typing("1000003"),
                "Number: 1000003",
                "1000003 is prime")
            .hidden(Lab.typing("49"),
                "Number: 49",
                "49 is not prime (divisible by 7)"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(17), "FizzBuzz, Security Edition", Lab.SMALL)
            .stretch()
            .after("C04-M007")
            .brief(
                "The maintenance rota: every 3rd day is a SCAN day, every "
                + "5th a PATCH day, and days that are both get SCAN+PATCH. "
                + "Print the rota for any number of days - the famous "
                + "FizzBuzz puzzle, in uniform.")
            .practises("for loops", "Order of conditions", "A labelling method")
            .spec(
                "Prompt Days: and read a whole number from 1 to 60. Anything else: INVALID.",
                "label(day) returns SCAN+PATCH for multiples of both 3 and 5, SCAN for other multiples of 3, PATCH for other multiples of 5, and the day number otherwise.",
                "Print Day <d>: <label> for every day from 1 to the number typed.")
            .needsMethod("static String label(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Days: \");",
                "        int days = Integer.parseInt(input.nextLine().trim());",
                "        // print the rota",
                "    }",
                "",
                "    static String label(int day) {",
                "        if (day % 3 == 0) {",
                "            return \"SCAN\";",
                "        }",
                "        if (day % 5 == 0) {",
                "            return \"PATCH\";",
                "        }",
                "        if (day % 15 == 0) {",
                "            return \"SCAN+PATCH\";",
                "        }",
                "        return \"\" + day;",
                "    }",
                "}")
            .hints(
                "The starter's label never returns SCAN+PATCH. Why? Day 15 "
                + "is a multiple of 3, so the first return wins.",
                "Check the most specific rule FIRST: multiples of 15 before "
                + "multiples of 3 or 5.",
                "for (int d = 1; d <= days; d++) prints each line.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Days: \");",
                "        int days = Integer.parseInt(input.nextLine().trim());",
                "        if (days < 1 || days > 60) {",
                "            System.out.println(\"INVALID\");",
                "            return;",
                "        }",
                "        for (int d = 1; d <= days; d++) {",
                "            System.out.println(\"Day \" + d + \": \" + label(d));",
                "        }",
                "    }",
                "",
                "    static String label(int day) {",
                "        if (day % 15 == 0) {",
                "            return \"SCAN+PATCH\";",
                "        }",
                "        if (day % 3 == 0) {",
                "            return \"SCAN\";",
                "        }",
                "        if (day % 5 == 0) {",
                "            return \"PATCH\";",
                "        }",
                "        return \"\" + day;",
                "    }",
                "}")
            .walkthrough(
                "The starter's bug is rule order - the same lesson as a "
                + "firewall's first-match rules. Day 15 matches the % 3 rule "
                + "first and returns SCAN, so the combined rule is "
                + "unreachable. Putting the most specific rule first fixes "
                + "it.\n"
                + "\n"
                + "FizzBuzz is famous as an interview question precisely "
                + "because so many people get the order wrong. The hidden "
                + "tests run to day 15 and day 30, where the combined label "
                + "must appear.")
            .sample(Lab.typing("15"),
                "Days: 15",
                "Day 1: 1",
                "Day 2: 2",
                "Day 3: SCAN",
                "Day 4: 4",
                "Day 5: PATCH",
                "Day 6: SCAN",
                "Day 7: 7",
                "Day 8: 8",
                "Day 9: SCAN",
                "Day 10: PATCH",
                "Day 11: 11",
                "Day 12: SCAN",
                "Day 13: 13",
                "Day 14: 14",
                "Day 15: SCAN+PATCH")
            .hidden(Lab.typing("1"),
                "Days: 1",
                "Day 1: 1")
            .hidden(Lab.typing("30"),
                "Days: 30",
                "Day 1: 1",
                "Day 2: 2",
                "Day 3: SCAN",
                "Day 4: 4",
                "Day 5: PATCH",
                "Day 6: SCAN",
                "Day 7: 7",
                "Day 8: 8",
                "Day 9: SCAN",
                "Day 10: PATCH",
                "Day 11: 11",
                "Day 12: SCAN",
                "Day 13: 13",
                "Day 14: 14",
                "Day 15: SCAN+PATCH",
                "Day 16: 16",
                "Day 17: 17",
                "Day 18: SCAN",
                "Day 19: 19",
                "Day 20: PATCH",
                "Day 21: SCAN",
                "Day 22: 22",
                "Day 23: 23",
                "Day 24: SCAN",
                "Day 25: PATCH",
                "Day 26: 26",
                "Day 27: SCAN",
                "Day 28: 28",
                "Day 29: 29",
                "Day 30: SCAN+PATCH")
            .hidden(Lab.typing("0"),
                "Days: 0",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(18), "Palindrome Check", Lab.MEDIUM)
            .stretch()
            .after("C04-M024")
            .brief(
                "A palindrome reads the same backwards: level, or 'Never odd "
                + "or even' once spaces and capitals are ignored. Check one "
                + "with two positions walking in from both ends - a loop "
                + "with two moving parts.")
            .practises("Two-index loops", "Cleaning text before comparing", "Stopping at the first mismatch")
            .spec(
                "Prompt Text: and read the line exactly as typed.",
                "lettersOnly(text) returns the text's letters only, in lower case, built with a loop.",
                "isPalindrome(text) cleans the text with lettersOnly, then compares characters from both ends, moving inwards, stopping at the first mismatch.",
                "Print Palindrome: yes or Palindrome: no. Text with no letters counts as a palindrome.")
            .needsMethod("static String lettersOnly(String)")
            .needsMethod("static boolean isPalindrome(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Text: \");",
                "        String text = input.nextLine();",
                "        // check and report",
                "    }",
                "",
                "    // lettersOnly and isPalindrome",
                "}")
            .hints(
                "lettersOnly: loop over the characters; add a character to "
                + "the result only when Character.isLetter says so. Lower-case "
                + "the result at the end.",
                "Two indexes: int left = 0; int right = s.length() - 1;",
                "while (left < right): if the characters at left and right "
                + "differ, return false; otherwise move left up and right "
                + "down.",
                "If the loop finishes, every pair matched: return true.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Text: \");",
                "        String text = input.nextLine();",
                "        String answer = isPalindrome(text) ? \"yes\" : \"no\";",
                "        System.out.println(\"Palindrome: \" + answer);",
                "    }",
                "",
                "    static String lettersOnly(String text) {",
                "        String result = \"\";",
                "        for (int i = 0; i < text.length(); i++) {",
                "            if (Character.isLetter(text.charAt(i))) {",
                "                result += text.charAt(i);",
                "            }",
                "        }",
                "        return result.toLowerCase();",
                "    }",
                "",
                "    static boolean isPalindrome(String text) {",
                "        String s = lettersOnly(text);",
                "        int left = 0;",
                "        int right = s.length() - 1;",
                "        while (left < right) {",
                "            if (s.charAt(left) != s.charAt(right)) {",
                "                return false;",
                "            }",
                "            left++;",
                "            right--;",
                "        }",
                "        return true;",
                "    }",
                "}")
            .walkthrough(
                "Two indexes walk towards each other, comparing one pair per "
                + "pass; the first mismatch returns false straight away. "
                + "left < right stops them when they meet or cross, which "
                + "handles odd and even lengths - and empty text, where right "
                + "starts at -1 and the loop never runs.\n"
                + "\n"
                + "Cleaning first is the real lesson: comparisons on raw "
                + "input fail for reasons that have nothing to do with the "
                + "question. Normalising before comparing is the same idea "
                + "as normalising user names before checking a blocklist.")
            .sample(Lab.typing("Never odd or even"),
                "Text: Never odd or even",
                "Palindrome: yes")
            .hidden(Lab.typing("level"),
                "Text: level",
                "Palindrome: yes")
            .hidden(Lab.typing("northstar"),
                "Text: northstar",
                "Palindrome: no")
            .hidden(Lab.typing(""),
                "Text:",
                "Palindrome: yes")
            .hidden(Lab.typing("ab"),
                "Text: ab",
                "Palindrome: no")
            .hidden(Lab.typing("A man, a plan, a canal: Panama!"),
                "Text: A man, a plan, a canal: Panama!",
                "Palindrome: yes"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(19), "Caesar Cipher", Lab.MEDIUM)
            .after("C04-M017")
            .brief(
                "Campaign 03 shifted a single letter. Now shift a whole "
                + "message: a loop over the characters, calling the one-letter "
                + "method on each. Then prove the cipher is reversible by "
                + "decrypting with the opposite shift.")
            .practises("A loop that calls a method", "Building the result", "Reversible transformations")
            .spec(
                "Prompt Mode (e/d): Shift: and Message:. The mode is e to encrypt or d to decrypt; anything else prints INVALID MODE and stops. The shift is a whole number.",
                "shift(c, k) moves a letter k places round the alphabet, keeping its case; anything that is not a letter comes back unchanged.",
                "caesar(text, k) applies shift to every character. Decrypting uses the shift -k.",
                "Print Result: <text>.")
            .needsMethod("static char shift(char, int)")
            .needsMethod("static String caesar(String, int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final String LOWER = \"abcdefghijklmnopqrstuvwxyz\";",
                "    static final String UPPER = \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\";",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Mode (e/d): \");",
                "        String mode = input.nextLine().trim();",
                "        // check the mode, read shift and message, print the result",
                "    }",
                "",
                "    static char shift(char c, int k) {",
                "        int p = LOWER.indexOf(c);",
                "        if (p != -1) {",
                "            return LOWER.charAt(((p + k) % 26 + 26) % 26);",
                "        }",
                "        p = UPPER.indexOf(c);",
                "        if (p != -1) {",
                "            return UPPER.charAt(((p + k) % 26 + 26) % 26);",
                "        }",
                "        return c;",
                "    }",
                "",
                "    // declare caesar(String text, int k) here",
                "}")
            .hints(
                "shift is done for you - it is Campaign 03's lab 28. The new "
                + "work is the loop around it.",
                "caesar builds a String: start with \"\", and add "
                + "shift(text.charAt(i), k) for every position.",
                "In main, check the mode FIRST - an invalid mode must stop "
                + "before the shift and message are asked for.",
                "For d, call caesar(message, -shift).")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final String LOWER = \"abcdefghijklmnopqrstuvwxyz\";",
                "    static final String UPPER = \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\";",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Mode (e/d): \");",
                "        String mode = input.nextLine().trim();",
                "        if (!mode.equals(\"e\") && !mode.equals(\"d\")) {",
                "            System.out.println(\"INVALID MODE\");",
                "            return;",
                "        }",
                "        System.out.print(\"Shift: \");",
                "        int k = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Message: \");",
                "        String message = input.nextLine();",
                "        int used = mode.equals(\"e\") ? k : -k;",
                "        System.out.println(\"Result: \" + caesar(message, used));",
                "    }",
                "",
                "    static char shift(char c, int k) {",
                "        int p = LOWER.indexOf(c);",
                "        if (p != -1) {",
                "            return LOWER.charAt(((p + k) % 26 + 26) % 26);",
                "        }",
                "        p = UPPER.indexOf(c);",
                "        if (p != -1) {",
                "            return UPPER.charAt(((p + k) % 26 + 26) % 26);",
                "        }",
                "        return c;",
                "    }",
                "",
                "    static String caesar(String text, int k) {",
                "        String result = \"\";",
                "        for (int i = 0; i < text.length(); i++) {",
                "            result += shift(text.charAt(i), k);",
                "        }",
                "        return result;",
                "    }",
                "}")
            .walkthrough(
                "The loop does the repeating and shift does the work - "
                + "mission 17's split. Decryption needs no new code at all: "
                + "shifting by -k undoes shifting by k, which the hidden "
                + "tests prove by decrypting a message that was encrypted "
                + "with the same key.\n"
                + "\n"
                + "Spaces and punctuation pass through unchanged, which is "
                + "one of the Caesar cipher's many weaknesses: word lengths "
                + "and punctuation leak straight through, and there are only "
                + "25 useful keys. It is a teaching cipher. Real encryption "
                + "hides everything, and the key space is astronomically "
                + "large.")
            .sample(Lab.typing("e", "3", "Meet at the gate, 21:00"),
                "Mode (e/d): e",
                "Shift: 3",
                "Message: Meet at the gate, 21:00",
                "Result: Phhw dw wkh jdwh, 21:00")
            .hidden(Lab.typing("d", "3", "Phhw dw wkh jdwh, 21:00"),
                "Mode (e/d): d",
                "Shift: 3",
                "Message: Phhw dw wkh jdwh, 21:00",
                "Result: Meet at the gate, 21:00")
            .hidden(Lab.typing("e", "-1", "abc XYZ"),
                "Mode (e/d): e",
                "Shift: -1",
                "Message: abc XYZ",
                "Result: zab WXY")
            .hidden(Lab.typing("e", "26", "Unchanged!"),
                "Mode (e/d): e",
                "Shift: 26",
                "Message: Unchanged!",
                "Result: Unchanged!")
            .hidden(Lab.typing("x"),
                "Mode (e/d): x",
                "INVALID MODE"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(20), "Vowel Stripper", Lab.SMALL)
            .stretch()
            .after("C04-M014")
            .brief(
                "Old SMS gateways charged per character, so alert texts were "
                + "squeezed by dropping vowels: 'Dsk flng on wb-01'. Build "
                + "the squeezer with a loop that skips what it does not "
                + "need.")
            .practises("continue", "Building a String", "A character test method")
            .spec(
                "Prompt Message: and read the line exactly as typed.",
                "isVowel(c) is true for a, e, i, o and u in either case.",
                "stripVowels(text) returns the text without its vowels, using continue to skip them.",
                "Print Short: <stripped> and Saved: <n> characters.")
            .needsMethod("static boolean isVowel(char)")
            .needsMethod("static String stripVowels(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Message: \");",
                "        String text = input.nextLine();",
                "        // strip, report",
                "    }",
                "",
                "    // isVowel and stripVowels",
                "}")
            .hints(
                "isVowel: lower-case the char with Character.toLowerCase(c), "
                + "then compare it with each vowel using ||.",
                "In stripVowels' loop: if isVowel, continue; otherwise add "
                + "the character to the result.",
                "Saved is the original length minus the new length.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Message: \");",
                "        String text = input.nextLine();",
                "        String shortText = stripVowels(text);",
                "        System.out.println(\"Short: \" + shortText);",
                "        int saved = text.length() - shortText.length();",
                "        System.out.println(\"Saved: \" + saved + \" characters\");",
                "    }",
                "",
                "    static boolean isVowel(char c) {",
                "        char l = Character.toLowerCase(c);",
                "        return l == 'a' || l == 'e' || l == 'i'",
                "                || l == 'o' || l == 'u';",
                "    }",
                "",
                "    static String stripVowels(String text) {",
                "        String result = \"\";",
                "        for (int i = 0; i < text.length(); i++) {",
                "            char c = text.charAt(i);",
                "            if (isVowel(c)) {",
                "                continue;",
                "            }",
                "            result += c;",
                "        }",
                "        return result;",
                "    }",
                "}")
            .walkthrough(
                "continue skips a vowel's pass before it can be added; "
                + "everything else reaches the line that builds the result. "
                + "In a for loop that is safe, because the update lives in "
                + "the header. Lower-casing inside isVowel means A and a are "
                + "treated the same without a second list.\n"
                + "\n"
                + "Squeezed alerts are still readable to a person because "
                + "consonants carry most of the meaning - which is also why "
                + "'remove the vowels' is a weak way to hide anything. It "
                + "shortens text; it does not protect it.")
            .sample(Lab.typing("Disk failing on web-01"),
                "Message: Disk failing on web-01",
                "Short: Dsk flng n wb-01",
                "Saved: 6 characters")
            .hidden(Lab.typing(""),
                "Message:",
                "Short:",
                "Saved: 0 characters")
            .hidden(Lab.typing("AEIOU aeiou"),
                "Message: AEIOU aeiou",
                "Short:",
                "Saved: 10 characters")
            .hidden(Lab.typing("rhythm 42"),
                "Message: rhythm 42",
                "Short: rhythm 42",
                "Saved: 0 characters"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(21), "Rate Limiter", Lab.BIG)
            .stretch()
            .after("C04-M022")
            .brief(
                "The password-reset endpoint needs its limiter: at most 3 "
                + "requests in each 10-second window. Requests arrive as "
                + "timestamps. Some clients send times that go backwards - "
                + "those are refused outright. At the end, report the "
                + "totals.")
            .practises("Fixed-window rate limiting", "State across passes", "Rejecting out-of-order input")
            .spec(
                "Repeatedly prompt Request at second (or -1): and read a whole number. -1 ends the input.",
                "windowOf(time) returns time / 10. A new window resets the allowance.",
                "A time earlier than the previous accepted time prints REJECTED - out of order and is not counted anywhere else.",
                "Otherwise, if the window has had fewer than 3 allowed requests, print ALLOW and count it; if not, print DENY. Denied requests do not use up the allowance.",
                "Finish with Allowed: <a>  Denied: <d>  Rejected: <r> (two spaces between).")
            .needsMethod("static int windowOf(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int LIMIT = 3;",
                "    static final int WINDOW_SECONDS = 10;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // the limiter loop, then the totals",
                "    }",
                "",
                "    // declare windowOf(int time) here",
                "}")
            .hints(
                "State before the loop: the current window (start -1), the "
                + "count in it, the last accepted time (start -1), and three "
                + "totals.",
                "Order in the body: read; -1 breaks; out of order? reject "
                + "and continue; then the window check; then allow or deny.",
                "A new window: if (windowOf(time) != current) { current = "
                + "windowOf(time); count = 0; }",
                "Remember to update the last accepted time for every request "
                + "that is not rejected.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int LIMIT = 3;",
                "    static final int WINDOW_SECONDS = 10;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int current = -1;",
                "        int count = 0;",
                "        int last = -1;",
                "        int allowed = 0;",
                "        int denied = 0;",
                "        int rejected = 0;",
                "        while (true) {",
                "            System.out.print(\"Request at second (or -1): \");",
                "            int time = Integer.parseInt(input.nextLine().trim());",
                "            if (time == -1) {",
                "                break;",
                "            }",
                "            if (time < last) {",
                "                System.out.println(\"REJECTED - out of order\");",
                "                rejected++;",
                "                continue;",
                "            }",
                "            last = time;",
                "            if (windowOf(time) != current) {",
                "                current = windowOf(time);",
                "                count = 0;",
                "            }",
                "            if (count < LIMIT) {",
                "                count++;",
                "                allowed++;",
                "                System.out.println(\"ALLOW\");",
                "            } else {",
                "                denied++;",
                "                System.out.println(\"DENY\");",
                "            }",
                "        }",
                "        System.out.println(\"Allowed: \" + allowed",
                "                + \"  Denied: \" + denied",
                "                + \"  Rejected: \" + rejected);",
                "    }",
                "",
                "    static int windowOf(int time) {",
                "        return time / WINDOW_SECONDS;",
                "    }",
                "}")
            .walkthrough(
                "Six variables carry state from one pass to the next, all "
                + "declared before the loop. Each request goes through the "
                + "checks in a fixed order: end of input, out of order, new "
                + "window, then the allowance. Refusing out-of-order times "
                + "first stops a client from sending an old timestamp to "
                + "land in a window that has already been reset.\n"
                + "\n"
                + "The hidden tests hit the window boundary - seconds 9 and "
                + "10 are in different windows - and a burst of requests "
                + "all in the same second, which is exactly the traffic a "
                + "limiter exists to slow down.")
            .sample(Lab.typing("1", "2", "3", "4", "12", "13", "25", "-1"),
                "Request at second (or -1): 1",
                "ALLOW",
                "Request at second (or -1): 2",
                "ALLOW",
                "Request at second (or -1): 3",
                "ALLOW",
                "Request at second (or -1): 4",
                "DENY",
                "Request at second (or -1): 12",
                "ALLOW",
                "Request at second (or -1): 13",
                "ALLOW",
                "Request at second (or -1): 25",
                "ALLOW",
                "Request at second (or -1): -1",
                "Allowed: 6  Denied: 1  Rejected: 0")
            .hidden(Lab.typing("-1"),
                "Request at second (or -1): -1",
                "Allowed: 0  Denied: 0  Rejected: 0")
            .hidden(Lab.typing("9", "9", "9", "9", "10", "10", "-1"),
                "Request at second (or -1): 9",
                "ALLOW",
                "Request at second (or -1): 9",
                "ALLOW",
                "Request at second (or -1): 9",
                "ALLOW",
                "Request at second (or -1): 9",
                "DENY",
                "Request at second (or -1): 10",
                "ALLOW",
                "Request at second (or -1): 10",
                "ALLOW",
                "Request at second (or -1): -1",
                "Allowed: 5  Denied: 1  Rejected: 0")
            .hidden(Lab.typing("20", "5", "21", "-1"),
                "Request at second (or -1): 20",
                "ALLOW",
                "Request at second (or -1): 5",
                "REJECTED - out of order",
                "Request at second (or -1): 21",
                "ALLOW",
                "Request at second (or -1): -1",
                "Allowed: 2  Denied: 0  Rejected: 1")
            .hidden(Lab.typing("0", "0", "0", "0", "0", "0", "-1"),
                "Request at second (or -1): 0",
                "ALLOW",
                "Request at second (or -1): 0",
                "ALLOW",
                "Request at second (or -1): 0",
                "ALLOW",
                "Request at second (or -1): 0",
                "DENY",
                "Request at second (or -1): 0",
                "DENY",
                "Request at second (or -1): 0",
                "DENY",
                "Request at second (or -1): -1",
                "Allowed: 3  Denied: 3  Rejected: 0"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(22), "Lockout Time Calculator", Lab.BIG)
            .stretch()
            .after("C04-M020")
            .brief(
                "The badge-system review needs numbers, not opinions. For a "
                + "PIN of a given length and a lockout policy, work out how "
                + "many PINs are possible, and the WORST-CASE time to try "
                + "them all with the lockout in place - the figure that "
                + "tells the review whether the policy is good enough.")
            .practises("Product accumulators", "long arithmetic", "Rounding up with integers")
            .spec(
                "Prompt PIN length (1-8):, Tries per lockout (1-10): and Lockout minutes (1-1440):, reading whole numbers. Any value out of range prints INVALID POLICY and stops.",
                "keySpace(length) returns 10 multiplied together length times, as a long.",
                "periodsNeeded(space, tries) returns how many lockout periods are needed to try every PIN, rounding UP: (space + tries - 1) / tries.",
                "Print Possible PINs: <space>, Lockout periods: <periods>, and Worst case: <minutes> minutes (about <days> days), where minutes is periods times the lockout minutes and days is minutes / 1440.")
            .needsMethod("static long keySpace(int)")
            .needsMethod("static long periodsNeeded(long, int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read and check the policy, then calculate",
                "    }",
                "",
                "    // keySpace(int length) and periodsNeeded(long space, int tries)",
                "}")
            .hints(
                "keySpace is a product accumulator: long space = 1; then "
                + "multiply by 10 once per digit.",
                "Integer division rounds DOWN. Adding tries - 1 before "
                + "dividing makes it round UP: 10 PINs at 3 tries needs 4 "
                + "periods, not 3.",
                "Keep minutes and days as long - they get big.",
                "Validate all three inputs before any arithmetic.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"PIN length (1-8): \");",
                "        int length = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Tries per lockout (1-10): \");",
                "        int tries = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Lockout minutes (1-1440): \");",
                "        int lockMinutes = Integer.parseInt(input.nextLine().trim());",
                "        if (length < 1 || length > 8 || tries < 1 || tries > 10",
                "                || lockMinutes < 1 || lockMinutes > 1440) {",
                "            System.out.println(\"INVALID POLICY\");",
                "            return;",
                "        }",
                "        long space = keySpace(length);",
                "        long periods = periodsNeeded(space, tries);",
                "        long minutes = periods * lockMinutes;",
                "        System.out.println(\"Possible PINs: \" + space);",
                "        System.out.println(\"Lockout periods: \" + periods);",
                "        System.out.println(\"Worst case: \" + minutes",
                "                + \" minutes (about \" + minutes / 1440 + \" days)\");",
                "    }",
                "",
                "    static long keySpace(int length) {",
                "        long space = 1;",
                "        for (int i = 0; i < length; i++) {",
                "            space *= 10;",
                "        }",
                "        return space;",
                "    }",
                "",
                "    static long periodsNeeded(long space, int tries) {",
                "        return (space + tries - 1) / tries;",
                "    }",
                "}")
            .walkthrough(
                "keySpace is mission 20's product accumulator: each digit "
                + "multiplies the possibilities by ten, in a long because an "
                + "8-digit PIN already has 100 million. periodsNeeded rounds "
                + "UP with a classic integer trick - adding tries - 1 before "
                + "dividing - because a final, partly used lockout period "
                + "still has to be waited out.\n"
                + "\n"
                + "The numbers make the policy argument for you: a 4-digit "
                + "PIN with 3 tries per 30-minute lockout takes about 69 "
                + "days in the worst case, while a 6-digit PIN with the same "
                + "policy takes around 19 years. Length and lockout together "
                + "are what make a short secret acceptable.")
            .sample(Lab.typing("4", "3", "30"),
                "PIN length (1-8): 4",
                "Tries per lockout (1-10): 3",
                "Lockout minutes (1-1440): 30",
                "Possible PINs: 10000",
                "Lockout periods: 3334",
                "Worst case: 100020 minutes (about 69 days)")
            .hidden(Lab.typing("6", "3", "30"),
                "PIN length (1-8): 6",
                "Tries per lockout (1-10): 3",
                "Lockout minutes (1-1440): 30",
                "Possible PINs: 1000000",
                "Lockout periods: 333334",
                "Worst case: 10000020 minutes (about 6944 days)")
            .hidden(Lab.typing("2", "10", "5"),
                "PIN length (1-8): 2",
                "Tries per lockout (1-10): 10",
                "Lockout minutes (1-1440): 5",
                "Possible PINs: 100",
                "Lockout periods: 10",
                "Worst case: 50 minutes (about 0 days)")
            .hidden(Lab.typing("8", "5", "1440"),
                "PIN length (1-8): 8",
                "Tries per lockout (1-10): 5",
                "Lockout minutes (1-1440): 1440",
                "Possible PINs: 100000000",
                "Lockout periods: 20000000",
                "Worst case: 28800000000 minutes (about 20000000 days)")
            .hidden(Lab.typing("9", "3", "30"),
                "PIN length (1-8): 9",
                "Tries per lockout (1-10): 3",
                "Lockout minutes (1-1440): 30",
                "INVALID POLICY")
            .hidden(Lab.typing("4", "0", "30"),
                "PIN length (1-8): 4",
                "Tries per lockout (1-10): 0",
                "Lockout minutes (1-1440): 30",
                "INVALID POLICY"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(23), "Log Scanner", Lab.BIG)
            .after("C04-M028")
            .brief(
                "The overnight authentication log needs a first pass before "
                + "the analysts arrive. Each line is RESULT USER ADDRESS. "
                + "Skip the noise, count what matters, flag failures from "
                + "outside the network, and report the longest run of "
                + "failures in a row.")
            .practises("A scanner loop", "Validating each record", "Tracking a streak")
            .spec(
                "Repeatedly prompt > and read the line, trimmed, until END.",
                "isNoise(line): blank, or starting with #. Noise is skipped silently.",
                "isWellFormed(line): exactly two spaces, no two side by side, and a first word of OK or FAIL. Other lines print MALFORMED: <line> and are counted as malformed.",
                "isExternal(ip): true unless the address starts with 10. or 192.168. A FAIL from an external address prints FLAG: <line>.",
                "Track the longest run of FAIL lines in a row (an OK ends a run; noise and malformed lines do not).",
                "Finish with OK: <n>  FAIL: <n>  Malformed: <n>, then Longest failure run: <n>.")
            .needsMethod("static boolean isNoise(String)")
            .needsMethod("static boolean isWellFormed(String)")
            .needsMethod("static boolean isExternal(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // the scanner loop, then the summary",
                "    }",
                "",
                "    // isNoise, isWellFormed, isExternal",
                "}")
            .hints(
                "Count spaces without a loop, or with one: the number of "
                + "spaces is line.length() - line.replace(\" \", \"\").length().",
                "The address is everything after the last space: "
                + "line.substring(line.lastIndexOf(\" \") + 1).",
                "Two variables for streaks: the current run, and the longest "
                + "so far. FAIL: run++ and update the longest. OK: run = 0.",
                "Order in the body: END, noise, well-formed, then count and "
                + "classify.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int ok = 0;",
                "        int fail = 0;",
                "        int malformed = 0;",
                "        int run = 0;",
                "        int longest = 0;",
                "        while (true) {",
                "            System.out.print(\"> \");",
                "            String line = input.nextLine().trim();",
                "            if (line.equals(\"END\")) {",
                "                break;",
                "            }",
                "            if (isNoise(line)) {",
                "                continue;",
                "            }",
                "            if (!isWellFormed(line)) {",
                "                System.out.println(\"MALFORMED: \" + line);",
                "                malformed++;",
                "                continue;",
                "            }",
                "            if (line.startsWith(\"OK \")) {",
                "                ok++;",
                "                run = 0;",
                "                continue;",
                "            }",
                "            fail++;",
                "            run++;",
                "            longest = Math.max(longest, run);",
                "            String ip = line.substring(line.lastIndexOf(\" \") + 1);",
                "            if (isExternal(ip)) {",
                "                System.out.println(\"FLAG: \" + line);",
                "            }",
                "        }",
                "        System.out.println(\"OK: \" + ok + \"  FAIL: \" + fail",
                "                + \"  Malformed: \" + malformed);",
                "        System.out.println(\"Longest failure run: \" + longest);",
                "    }",
                "",
                "    static boolean isNoise(String line) {",
                "        return line.isEmpty() || line.startsWith(\"#\");",
                "    }",
                "",
                "    static boolean isWellFormed(String line) {",
                "        int spaces = line.length() - line.replace(\" \", \"\").length();",
                "        if (spaces != 2 || line.contains(\"  \")) {",
                "            return false;",
                "        }",
                "        return line.startsWith(\"OK \") || line.startsWith(\"FAIL \");",
                "    }",
                "",
                "    static boolean isExternal(String ip) {",
                "        return !ip.startsWith(\"10.\") && !ip.startsWith(\"192.168.\");",
                "    }",
                "}")
            .walkthrough(
                "The loop body is a sequence of filters, each with a "
                + "continue: stop at END, drop noise, report and drop "
                + "malformed lines, handle OK. Whatever is left is a "
                + "well-formed FAIL, so the rest of the body can trust its "
                + "shape - which is what makes the substring safe.\n"
                + "\n"
                + "The streak uses two variables: run is the current run of "
                + "failures, reset by an OK; longest is the best seen, kept "
                + "with Math.max. Noise and malformed lines leave the run "
                + "alone, so a comment between two failures does not hide a "
                + "streak - an attacker cannot break up the pattern by "
                + "slipping junk lines into the log.")
            .sample(Lab.typing("# night", "OK jsmith 10.0.0.7", "FAIL admin 203.0.113.9",
                               "FAIL admin 203.0.113.9", "OK admin 10.0.0.2", "END"),
                "> # night",
                "> OK jsmith 10.0.0.7",
                "> FAIL admin 203.0.113.9",
                "FLAG: FAIL admin 203.0.113.9",
                "> FAIL admin 203.0.113.9",
                "FLAG: FAIL admin 203.0.113.9",
                "> OK admin 10.0.0.2",
                "> END",
                "OK: 2  FAIL: 2  Malformed: 0",
                "Longest failure run: 2")
            .hidden(Lab.typing("END"),
                "> END",
                "OK: 0  FAIL: 0  Malformed: 0",
                "Longest failure run: 0")
            .hidden(Lab.typing("FAIL a 10.0.0.1", "", "FAIL a 192.168.1.5",
                               "# x", "FAIL a 10.0.0.9", "END"),
                "> FAIL a 10.0.0.1",
                ">",
                "> FAIL a 192.168.1.5",
                "> # x",
                "> FAIL a 10.0.0.9",
                "> END",
                "OK: 0  FAIL: 3  Malformed: 0",
                "Longest failure run: 3")
            .hidden(Lab.typing("LOGIN jsmith 10.0.0.7", "FAIL  bob 1.2.3.4",
                               "FAIL bob", "OK bob 1.2.3.4", "END"),
                "> LOGIN jsmith 10.0.0.7",
                "MALFORMED: LOGIN jsmith 10.0.0.7",
                "> FAIL  bob 1.2.3.4",
                "MALFORMED: FAIL  bob 1.2.3.4",
                "> FAIL bob",
                "MALFORMED: FAIL bob",
                "> OK bob 1.2.3.4",
                "> END",
                "OK: 1  FAIL: 0  Malformed: 3",
                "Longest failure run: 0")
            .hidden(Lab.typing("FAIL eve 198.51.100.4", "OK eve 198.51.100.4",
                               "FAIL eve 198.51.100.4", "END"),
                "> FAIL eve 198.51.100.4",
                "FLAG: FAIL eve 198.51.100.4",
                "> OK eve 198.51.100.4",
                "> FAIL eve 198.51.100.4",
                "FLAG: FAIL eve 198.51.100.4",
                "> END",
                "OK: 1  FAIL: 2  Malformed: 0",
                "Longest failure run: 1"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(24), "Firewall Range Report", Lab.BIG)
            .stretch()
            .after("C04-M029")
            .brief(
                "The quarterly audit asks: across a range of ports, which "
                + "does the edge firewall actually ALLOW? The rules are "
                + "fixed; the range is typed in. Report the allowed ports as "
                + "a tidy list, and how many out of how many.")
            .practises("Range checks", "First-match rules", "Building a list with separators")
            .spec(
                "Prompt First port: and Last port: and read whole numbers. Both must be 1 to 65535, first must not be more than last, and the range may cover at most 200 ports; otherwise print INVALID RANGE.",
                "isAllowed(port), first match wins: 23 and 445 are denied; 22, 80 and 443 are allowed; 8000 to 8099 are allowed; everything else is denied.",
                "Print Allowed: followed by the allowed ports separated by comma and space - or Allowed: none. Then Ports allowed: <a> of <n>.")
            .needsMethod("static boolean isAllowed(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read and check the range, then audit it",
                "    }",
                "",
                "    // declare isAllowed(int port) here",
                "}")
            .hints(
                "The range size is last - first + 1 - the fencepost count.",
                "isAllowed is a list of ifs that return, explicit denies "
                + "first, ending with return false.",
                "Build the list with mission 25's pattern: a separator before "
                + "every item except the first - if (count > 0).",
                "If nothing was allowed, the list is still empty at the end: "
                + "print none instead.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"First port: \");",
                "        int first = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"Last port: \");",
                "        int last = Integer.parseInt(input.nextLine().trim());",
                "        if (first < 1 || last > 65535 || first > last",
                "                || last - first + 1 > 200) {",
                "            System.out.println(\"INVALID RANGE\");",
                "            return;",
                "        }",
                "        String list = \"\";",
                "        int count = 0;",
                "        for (int port = first; port <= last; port++) {",
                "            if (isAllowed(port)) {",
                "                if (count > 0) {",
                "                    list += \", \";",
                "                }",
                "                list += port;",
                "                count++;",
                "            }",
                "        }",
                "        String shown = count == 0 ? \"none\" : list;",
                "        System.out.println(\"Allowed: \" + shown);",
                "        int size = last - first + 1;",
                "        System.out.println(\"Ports allowed: \" + count + \" of \" + size);",
                "    }",
                "",
                "    static boolean isAllowed(int port) {",
                "        if (port == 23 || port == 445) {",
                "            return false;",
                "        }",
                "        if (port == 22 || port == 80 || port == 443) {",
                "            return true;",
                "        }",
                "        return port >= 8000 && port <= 8099;",
                "    }",
                "}")
            .walkthrough(
                "The loop asks the rules about every port in the range and "
                + "builds the answer as it goes. Denies are checked first, so "
                + "a later, broader allow rule could never open 23 or 445 by "
                + "accident; anything no rule mentions falls to the final "
                + "false - default deny.\n"
                + "\n"
                + "The 200-port limit is not about the firewall; it is about "
                + "the tool. A loop driven by typed input needs a sensible "
                + "cap, or a range of 1 to 65535 would print a 300-kilobyte "
                + "line. The hidden tests cover a range with nothing allowed, "
                + "a single port, and a reversed range.")
            .sample(Lab.typing("20", "30"),
                "First port: 20",
                "Last port: 30",
                "Allowed: 22",
                "Ports allowed: 1 of 11")
            .hidden(Lab.typing("8095", "8105"),
                "First port: 8095",
                "Last port: 8105",
                "Allowed: 8095, 8096, 8097, 8098, 8099",
                "Ports allowed: 5 of 11")
            .hidden(Lab.typing("440", "446"),
                "First port: 440",
                "Last port: 446",
                "Allowed: 443",
                "Ports allowed: 1 of 7")
            .hidden(Lab.typing("100", "120"),
                "First port: 100",
                "Last port: 120",
                "Allowed: none",
                "Ports allowed: 0 of 21")
            .hidden(Lab.typing("443", "443"),
                "First port: 443",
                "Last port: 443",
                "Allowed: 443",
                "Ports allowed: 1 of 1")
            .hidden(Lab.typing("30", "20"),
                "First port: 30",
                "Last port: 20",
                "INVALID RANGE")
            .hidden(Lab.typing("1", "1000"),
                "First port: 1",
                "Last port: 1000",
                "INVALID RANGE"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(25), "Run-Length Encoder", Lab.BIG)
            .stretch()
            .after("C04-M025")
            .brief(
                "Log shippers compress repetitive data before sending it. The "
                + "simplest scheme, run-length encoding, writes each run of "
                + "the same character once, followed by its length: "
                + "aaabcc becomes a3b1c2. Build the encoder - a loop that "
                + "tracks where each run starts and ends.")
            .practises("Tracking runs in a loop", "Building output", "Comparing sizes")
            .spec(
                "Prompt Data: and read the line exactly as typed. It will not contain digits.",
                "encode(text) returns, for each run of identical characters, the character followed by the run's length. Empty text encodes to empty text.",
                "Print Encoded: <encoded>, then Original: <n> chars  Encoded: <m> chars, then Smaller: yes or Smaller: no.")
            .needsMethod("static String encode(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Data: \");",
                "        String text = input.nextLine();",
                "        // encode and compare",
                "    }",
                "",
                "    // declare encode(String text) here",
                "}")
            .hints(
                "Walk with an index i. At each run's start, remember the "
                + "character, then move a second index j forward while the "
                + "characters still match.",
                "The inner loop needs a guard: while (j < text.length() && "
                + "text.charAt(j) == c).",
                "The run length is j - i. Add c and the length to the result, "
                + "then continue from i = j.",
                "Use a while loop for i, because i jumps by a whole run, not "
                + "by one.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Data: \");",
                "        String text = input.nextLine();",
                "        String encoded = encode(text);",
                "        System.out.println(\"Encoded: \" + encoded);",
                "        System.out.println(\"Original: \" + text.length()",
                "                + \" chars  Encoded: \" + encoded.length() + \" chars\");",
                "        boolean smaller = encoded.length() < text.length();",
                "        System.out.println(\"Smaller: \" + (smaller ? \"yes\" : \"no\"));",
                "    }",
                "",
                "    static String encode(String text) {",
                "        String result = \"\";",
                "        int i = 0;",
                "        while (i < text.length()) {",
                "            char c = text.charAt(i);",
                "            int j = i;",
                "            while (j < text.length() && text.charAt(j) == c) {",
                "                j++;",
                "            }",
                "            result += c + \"\" + (j - i);",
                "            i = j;",
                "        }",
                "        return result;",
                "    }",
                "}")
            .walkthrough(
                "Two indexes do the work: i marks the start of a run, and the "
                + "inner loop pushes j forward to the first different "
                + "character. The run's length is j - i, and the outer loop "
                + "jumps straight to j - so every character is looked at "
                + "once, even with a loop inside a loop. The inner loop's "
                + "guard comes first (mission 24), so the last run ends "
                + "safely at the end of the text.\n"
                + "\n"
                + "c + \"\" + (j - i) forces text joining; c + (j - i) alone "
                + "would ADD the character's code to the number. The size "
                + "comparison is the honest part: run-length encoding only "
                + "helps repetitive data, and makes varied text twice as "
                + "long.")
            .sample(Lab.typing("aaaaabbbcccccccc"),
                "Data: aaaaabbbcccccccc",
                "Encoded: a5b3c8",
                "Original: 16 chars  Encoded: 6 chars",
                "Smaller: yes")
            .hidden(Lab.typing(""),
                "Data:",
                "Encoded:",
                "Original: 0 chars  Encoded: 0 chars",
                "Smaller: no")
            .hidden(Lab.typing("abc"),
                "Data: abc",
                "Encoded: a1b1c1",
                "Original: 3 chars  Encoded: 6 chars",
                "Smaller: no")
            .hidden(Lab.typing("zzzzzzzzzzzz"),
                "Data: zzzzzzzzzzzz",
                "Encoded: z12",
                "Original: 12 chars  Encoded: 3 chars",
                "Smaller: yes")
            .hidden(Lab.typing("aabba"),
                "Data: aabba",
                "Encoded: a2b2a1",
                "Original: 5 chars  Encoded: 6 chars",
                "Smaller: no"));
    }
}
