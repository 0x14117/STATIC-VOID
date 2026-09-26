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
    }
}
