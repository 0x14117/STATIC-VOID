/**
 * LABS - CAMPAIGN 05 - COLLECTIONS
 *
 * Programs that remember many values at once. Every lab names the methods
 * it needs, and most of those methods take or return an array or a list,
 * so the collection is passed around rather than rebuilt. Classes are
 * Campaign 06: a lab that must keep several facts per item uses parallel
 * arrays or lists.
 */
public class Campaign05Labs {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(1), "Five Readings", Lab.SMALL)
            .after("C05-M009")
            .brief(
                "A sensor in the server room sends five temperature readings "
                + "an hour. Store all five in an array, print them back on "
                + "one line, and total them with a method that takes the "
                + "array.")
            .practises("Creating arrays", "Looping over arrays", "Arrays as parameters")
            .spec(
                "Prompt Reading 1: to Reading 5: and read a whole number after each.",
                "Keep the five readings in one int array.",
                "Print Readings: followed by the five values, each after a space.",
                "total(readings) returns their sum. Print Total: <sum>.")
            .needsMethod("static int total(int[])")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int[] readings = new int[5];",
                "        // read the five readings, then print them and the total",
                "    }",
                "",
                "    // declare total(int[] values) here",
                "}")
            .hints(
                "One for loop can prompt and fill every slot: i from 0 to "
                + "readings.length - 1, prompting with i + 1.",
                "Print \"Readings:\" first, then \" \" + readings[i] for each "
                + "slot with print, then an empty println to end the line.",
                "total needs an accumulator: int sum = 0; add each value; "
                + "return sum.",
                "for (int v : values) { sum += v; } is the whole loop.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int[] readings = new int[5];",
                "        for (int i = 0; i < readings.length; i++) {",
                "            System.out.print(\"Reading \" + (i + 1) + \": \");",
                "            readings[i] = Integer.parseInt(input.nextLine().trim());",
                "        }",
                "        System.out.print(\"Readings:\");",
                "        for (int r : readings) {",
                "            System.out.print(\" \" + r);",
                "        }",
                "        System.out.println();",
                "        System.out.println(\"Total: \" + total(readings));",
                "    }",
                "",
                "    static int total(int[] values) {",
                "        int sum = 0;",
                "        for (int v : values) {",
                "            sum += v;",
                "        }",
                "        return sum;",
                "    }",
                "}")
            .walkthrough(
                "The first loop uses an index because it must fill each slot "
                + "and prompt with its number; i + 1 turns the 0-based index "
                + "into the 1-based number people expect. The printing loop "
                + "and total only need values, so they use the enhanced for.\n"
                + "\n"
                + "total knows nothing about sensors or how many readings "
                + "there are: it works for any int array, which is the point "
                + "of passing the array in. Negative readings and zeros need "
                + "no special handling - they are just values.")
            .sample(Lab.typing("21", "22", "24", "23", "21"),
                "Reading 1: 21",
                "Reading 2: 22",
                "Reading 3: 24",
                "Reading 4: 23",
                "Reading 5: 21",
                "Readings: 21 22 24 23 21",
                "Total: 111")
            .hidden(Lab.typing("0", "0", "0", "0", "0"),
                "Reading 1: 0",
                "Reading 2: 0",
                "Reading 3: 0",
                "Reading 4: 0",
                "Reading 5: 0",
                "Readings: 0 0 0 0 0",
                "Total: 0")
            .hidden(Lab.typing("-3", "5", "-2", "0", "10"),
                "Reading 1: -3",
                "Reading 2: 5",
                "Reading 3: -2",
                "Reading 4: 0",
                "Reading 5: 10",
                "Readings: -3 5 -2 0 10",
                "Total: 10")
            .hidden(Lab.typing(" 40", "41 ", "39", "45", "50"),
                "Reading 1:  40",
                "Reading 2: 41",
                "Reading 3: 39",
                "Reading 4: 45",
                "Reading 5: 50",
                "Readings: 40 41 39 45 50",
                "Total: 215"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(2), "Reverse the List", Lab.SMALL)
            .stretch()
            .after("C05-M009")
            .brief(
                "The route trace lists hops from this office out to the "
                + "internet. The incident report needs them the other way "
                + "round: from the far end back home. Read the hops, build a "
                + "NEW reversed array with a method, and print both.")
            .practises("Arrays of Strings", "Returning an array", "Index arithmetic")
            .spec(
                "Prompt Hops (1-8): and read a whole number. Anything outside 1 to 8: print INVALID and stop.",
                "Prompt Hop 1: to Hop n: and read a name after each (trim it).",
                "reversed(hops) returns a new array holding the same names in the opposite order. It must not change hops.",
                "Print Out:  and Back: lines, each listing its array's names separated by \" -> \".")
            .needsMethod("static String[] reversed(String[])")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Hops (1-8): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        // validate, read the hops, then print both directions",
                "    }",
                "",
                "    // declare reversed(String[] items) here",
                "}")
            .hints(
                "The new array has the same length: new String[items.length].",
                "Slot i of the result takes the item i places from the END "
                + "of the original.",
                "The last index is items.length - 1, so slot i gets "
                + "items[items.length - 1 - i].",
                "A small joining method - or a loop that prints \" -> \" "
                + "before every item except the first - keeps main short.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Hops (1-8): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        if (n < 1 || n > 8) {",
                "            System.out.println(\"INVALID\");",
                "            return;",
                "        }",
                "        String[] hops = new String[n];",
                "        for (int i = 0; i < n; i++) {",
                "            System.out.print(\"Hop \" + (i + 1) + \": \");",
                "            hops[i] = input.nextLine().trim();",
                "        }",
                "        System.out.println(\"Out:  \" + joined(hops));",
                "        System.out.println(\"Back: \" + joined(reversed(hops)));",
                "    }",
                "",
                "    static String[] reversed(String[] items) {",
                "        String[] out = new String[items.length];",
                "        for (int i = 0; i < items.length; i++) {",
                "            out[i] = items[items.length - 1 - i];",
                "        }",
                "        return out;",
                "    }",
                "",
                "    static String joined(String[] items) {",
                "        String text = \"\";",
                "        for (int i = 0; i < items.length; i++) {",
                "            if (i > 0) {",
                "                text += \" -> \";",
                "            }",
                "            text += items[i];",
                "        }",
                "        return text;",
                "    }",
                "}")
            .walkthrough(
                "reversed builds a second array of the same length and fills "
                + "slot i from the mirror position, items.length - 1 - i. "
                + "When i is 0 that is the last index; when i is the last "
                + "index it is 0 - no slot is missed and none goes out of "
                + "bounds.\n"
                + "\n"
                + "Because it returns a NEW array, hops is untouched and the "
                + "Out: line can still be printed in the original order. "
                + "Reversing in place would have lost it - mission 10's "
                + "reference lesson, applied. The joining helper uses "
                + "Campaign 04's separator pattern: a separator before every "
                + "item except the first.")
            .sample(Lab.typing("3", "office-gw", "isp-edge", "cdn-7"),
                "Hops (1-8): 3",
                "Hop 1: office-gw",
                "Hop 2: isp-edge",
                "Hop 3: cdn-7",
                "Out:  office-gw -> isp-edge -> cdn-7",
                "Back: cdn-7 -> isp-edge -> office-gw")
            .hidden(Lab.typing("1", "office-gw"),
                "Hops (1-8): 1",
                "Hop 1: office-gw",
                "Out:  office-gw",
                "Back: office-gw")
            .hidden(Lab.typing("4", " a ", "b", "c", "d"),
                "Hops (1-8): 4",
                "Hop 1:  a",
                "Hop 2: b",
                "Hop 3: c",
                "Hop 4: d",
                "Out:  a -> b -> c -> d",
                "Back: d -> c -> b -> a")
            .hidden(Lab.typing("0"),
                "Hops (1-8): 0",
                "INVALID")
            .hidden(Lab.typing("9"),
                "Hops (1-8): 9",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(3), "Maximum and Minimum", Lab.SMALL)
            .after("C05-M013")
            .brief(
                "The cooling report wants the biggest rise and the biggest "
                + "fall in temperature across the day's readings. The "
                + "changes can be negative - which is exactly where a "
                + "maximum or minimum that starts at 0 gives the wrong "
                + "answer.")
            .practises("Max, min and average", "Arrays as parameters", "Validating a count")
            .spec(
                "Prompt Readings (1-10): and read a whole number. Anything outside 1 to 10: INVALID.",
                "Prompt Change 1: to Change n: and read a whole number (it may be negative) after each.",
                "highest(values) and lowest(values) return the largest and smallest value.",
                "Print Highest: <h> and Lowest: <l>.")
            .needsMethod("static int highest(int[])")
            .needsMethod("static int lowest(int[])")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Readings (1-10): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        // validate, read the changes, then report",
                "    }",
                "",
                "    // declare highest(int[] values) and lowest(int[] values)",
                "}")
            .hints(
                "Start both at values[0], the first real value - never at 0.",
                "Loop over every value and replace the best so far when one "
                + "beats it.",
                "main guarantees at least one reading, so values[0] always "
                + "exists.",
                "Try all-negative changes, such as -4 -9 -2: highest must be "
                + "-2, not 0.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Readings (1-10): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        if (n < 1 || n > 10) {",
                "            System.out.println(\"INVALID\");",
                "            return;",
                "        }",
                "        int[] changes = new int[n];",
                "        for (int i = 0; i < n; i++) {",
                "            System.out.print(\"Change \" + (i + 1) + \": \");",
                "            changes[i] = Integer.parseInt(input.nextLine().trim());",
                "        }",
                "        System.out.println(\"Highest: \" + highest(changes));",
                "        System.out.println(\"Lowest: \" + lowest(changes));",
                "    }",
                "",
                "    static int highest(int[] values) {",
                "        int best = values[0];",
                "        for (int v : values) {",
                "            if (v > best) {",
                "                best = v;",
                "            }",
                "        }",
                "        return best;",
                "    }",
                "",
                "    static int lowest(int[] values) {",
                "        int best = values[0];",
                "        for (int v : values) {",
                "            if (v < best) {",
                "                best = v;",
                "            }",
                "        }",
                "        return best;",
                "    }",
                "}")
            .walkthrough(
                "Both methods start from values[0], a value that really is in "
                + "the data, so the answer is always one of the readings. "
                + "Starting highest at 0 would report 0 for a day of only "
                + "falls; starting lowest at 0 would report 0 for a day of "
                + "only rises. The hidden tests include both.\n"
                + "\n"
                + "values[0] is safe because main refuses a count below 1 "
                + "before the array is made. The guard and the method work "
                + "as a pair: the method's assumption is the caller's "
                + "check.")
            .sample(Lab.typing("4", "2", "-3", "5", "0"),
                "Readings (1-10): 4",
                "Change 1: 2",
                "Change 2: -3",
                "Change 3: 5",
                "Change 4: 0",
                "Highest: 5",
                "Lowest: -3")
            .hidden(Lab.typing("3", "-4", "-9", "-2"),
                "Readings (1-10): 3",
                "Change 1: -4",
                "Change 2: -9",
                "Change 3: -2",
                "Highest: -2",
                "Lowest: -9")
            .hidden(Lab.typing("3", "7", "3", "12"),
                "Readings (1-10): 3",
                "Change 1: 7",
                "Change 2: 3",
                "Change 3: 12",
                "Highest: 12",
                "Lowest: 3")
            .hidden(Lab.typing("1", "-6"),
                "Readings (1-10): 1",
                "Change 1: -6",
                "Highest: -6",
                "Lowest: -6")
            .hidden(Lab.typing("11"),
                "Readings (1-10): 11",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(4), "Count Above Threshold", Lab.SMALL)
            .after("C05-M013")
            .brief(
                "Data-loss prevention flags any file upload larger than a "
                + "threshold. Read a batch of upload sizes and the "
                + "threshold, then report how many were over it - with the "
                + "counting done by a method that takes the array.")
            .practises("Counting matches", "Arrays as parameters", "Validating input")
            .spec(
                "Prompt Uploads (1-10): and read a whole number. Outside 1 to 10: INVALID.",
                "Prompt Size 1 (KB): to Size n (KB): and read a whole number after each.",
                "Prompt Threshold (KB): and read a whole number.",
                "countAbove(sizes, limit) returns how many sizes are strictly greater than limit.",
                "Print Over <limit> KB: <count> of <n>.")
            .needsMethod("static int countAbove(int[], int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Uploads (1-10): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        // validate, read sizes and the threshold, then report",
                "    }",
                "",
                "    // declare countAbove(int[] sizes, int limit) here",
                "}")
            .hints(
                "A counter that starts at 0 and goes up by one per match.",
                "Strictly greater means >, not >=. A size equal to the "
                + "threshold is not over it.",
                "for (int s : sizes) { if (s > limit) { count++; } }",
                "Read the threshold after all the sizes, as the spec lists.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Uploads (1-10): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        if (n < 1 || n > 10) {",
                "            System.out.println(\"INVALID\");",
                "            return;",
                "        }",
                "        int[] sizes = new int[n];",
                "        for (int i = 0; i < n; i++) {",
                "            System.out.print(\"Size \" + (i + 1) + \" (KB): \");",
                "            sizes[i] = Integer.parseInt(input.nextLine().trim());",
                "        }",
                "        System.out.print(\"Threshold (KB): \");",
                "        int limit = Integer.parseInt(input.nextLine().trim());",
                "        int over = countAbove(sizes, limit);",
                "        System.out.println(\"Over \" + limit + \" KB: \" + over",
                "                + \" of \" + n);",
                "    }",
                "",
                "    static int countAbove(int[] sizes, int limit) {",
                "        int count = 0;",
                "        for (int s : sizes) {",
                "            if (s > limit) {",
                "                count++;",
                "            }",
                "        }",
                "        return count;",
                "    }",
                "}")
            .walkthrough(
                "countAbove is Campaign 04's counter, fed from an array "
                + "instead of from input. It takes the limit as a parameter "
                + "rather than using a fixed number, so the same method "
                + "serves any policy.\n"
                + "\n"
                + "The boundary matters: a 500 KB upload against a 500 KB "
                + "threshold is AT the limit, not over it, and the hidden "
                + "test checks exactly that. Whether a limit is inclusive "
                + "is a policy decision; the code must match what the "
                + "policy says, here 'strictly greater'.")
            .sample(Lab.typing("5", "120", "4800", "500", "90", "730", "500"),
                "Uploads (1-10): 5",
                "Size 1 (KB): 120",
                "Size 2 (KB): 4800",
                "Size 3 (KB): 500",
                "Size 4 (KB): 90",
                "Size 5 (KB): 730",
                "Threshold (KB): 500",
                "Over 500 KB: 2 of 5")
            .hidden(Lab.typing("3", "500", "500", "500", "500"),
                "Uploads (1-10): 3",
                "Size 1 (KB): 500",
                "Size 2 (KB): 500",
                "Size 3 (KB): 500",
                "Threshold (KB): 500",
                "Over 500 KB: 0 of 3")
            .hidden(Lab.typing("2", "9000", "12000", "0"),
                "Uploads (1-10): 2",
                "Size 1 (KB): 9000",
                "Size 2 (KB): 12000",
                "Threshold (KB): 0",
                "Over 0 KB: 2 of 2")
            .hidden(Lab.typing("0"),
                "Uploads (1-10): 0",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(5), "Find the Host", Lab.SMALL)
            .after("C05-M012")
            .brief(
                "The asset register is an array of host names in rack order. "
                + "An engineer types a name and wants its rack slot - or a "
                + "clear 'not found'. Write the linear search as a method "
                + "that returns the index, or -1.")
            .practises("Linear search", "Returning -1 for not found", "Normalising input")
            .spec(
                "The program holds this array: web1, web2, db1, db2, fw1, vpn1 (in that order).",
                "Prompt Host: and read a name. Trim it and make it lower case.",
                "indexOf(hosts, name) returns the index of the first exact match, or -1.",
                "Found: print <name> is in slot <index>. Not found: print <name> not found. Use the tidied name.")
            .needsMethod("static int indexOf(String[], String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] hosts = {\"web1\", \"web2\", \"db1\", \"db2\",",
                "                \"fw1\", \"vpn1\"};",
                "        Scanner input = new Scanner(System.in);",
                "        // prompt, tidy the name, search, report",
                "    }",
                "",
                "    // declare indexOf(String[] list, String target) here",
                "}")
            .hints(
                "Tidy first: input.nextLine().trim().toLowerCase().",
                "In indexOf, return i the moment list[i].equals(target).",
                "return -1 goes AFTER the loop - only once every slot has "
                + "been checked.",
                "Compare with equals, never ==.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] hosts = {\"web1\", \"web2\", \"db1\", \"db2\",",
                "                \"fw1\", \"vpn1\"};",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Host: \");",
                "        String name = input.nextLine().trim().toLowerCase();",
                "        int at = indexOf(hosts, name);",
                "        if (at == -1) {",
                "            System.out.println(name + \" not found\");",
                "        } else {",
                "            System.out.println(name + \" is in slot \" + at);",
                "        }",
                "    }",
                "",
                "    static int indexOf(String[] list, String target) {",
                "        for (int i = 0; i < list.length; i++) {",
                "            if (list[i].equals(target)) {",
                "                return i;",
                "            }",
                "        }",
                "        return -1;",
                "    }",
                "}")
            .walkthrough(
                "The search has two exits: return i inside the if, the "
                + "moment a match is seen, and return -1 after the loop, "
                + "reached only when every slot failed. -1 can never be a "
                + "real index, so main can tell the two apart with one "
                + "comparison.\n"
                + "\n"
                + "The hidden tests check the edges where search bugs live: "
                + "the first slot, the last slot, a name that is absent, and "
                + "a name typed with capitals and spaces. Tidying the input "
                + "once, before the search, is what lets \"  FW1 \" find "
                + "fw1.")
            .sample(Lab.typing("db1"),
                "Host: db1",
                "db1 is in slot 2")
            .hidden(Lab.typing("web1"),
                "Host: web1",
                "web1 is in slot 0")
            .hidden(Lab.typing("vpn1"),
                "Host: vpn1",
                "vpn1 is in slot 5")
            .hidden(Lab.typing("  FW1 "),
                "Host:   FW1",
                "fw1 is in slot 4")
            .hidden(Lab.typing("db9"),
                "Host: db9",
                "db9 not found"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(6), "Split the Line", Lab.SMALL)
            .after("C05-M017")
            .brief(
                "The VPN gateway writes one line per connection: time, user, "
                + "source address and result, separated by commas. Parse a "
                + "line into its four fields - and refuse lines that do not "
                + "have exactly four, or that leave a field empty, instead "
                + "of crashing on them.")
            .practises("split", "Checking parts.length", "Trimming each field")
            .spec(
                "Prompt Line: and read one line.",
                "fields(line) splits it at every comma, trims each piece, and returns the pieces as an array.",
                "Not exactly 4 fields: print MALFORMED: <count> fields.",
                "4 fields but any of them empty after trimming: print MALFORMED: empty field.",
                "Otherwise print Time: , User: , Source: and Result: lines, one field each.")
            .needsMethod("static String[] fields(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Line: \");",
                "        String line = input.nextLine();",
                "        // split into fields, check them, then print them",
                "    }",
                "",
                "    // declare fields(String line) here",
                "}")
            .hints(
                "line.split(\",\") gives the pieces; commas need no "
                + "backslashes.",
                "Trim in place: for each index i, parts[i] = parts[i].trim();",
                "Check parts.length BEFORE reading parts[3].",
                "A second loop, or four isEmpty() checks joined by ||, "
                + "finds an empty field.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Line: \");",
                "        String line = input.nextLine();",
                "        String[] f = fields(line);",
                "        if (f.length != 4) {",
                "            System.out.println(\"MALFORMED: \" + f.length + \" fields\");",
                "            return;",
                "        }",
                "        for (String part : f) {",
                "            if (part.isEmpty()) {",
                "                System.out.println(\"MALFORMED: empty field\");",
                "                return;",
                "            }",
                "        }",
                "        System.out.println(\"Time: \" + f[0]);",
                "        System.out.println(\"User: \" + f[1]);",
                "        System.out.println(\"Source: \" + f[2]);",
                "        System.out.println(\"Result: \" + f[3]);",
                "    }",
                "",
                "    static String[] fields(String line) {",
                "        String[] parts = line.split(\",\");",
                "        for (int i = 0; i < parts.length; i++) {",
                "            parts[i] = parts[i].trim();",
                "        }",
                "        return parts;",
                "    }",
                "}")
            .walkthrough(
                "fields does the mechanical work - split, then trim every "
                + "piece in place with an index loop (an enhanced for could "
                + "not store the trimmed text back). It returns the same "
                + "array it trimmed.\n"
                + "\n"
                + "main then checks in order: the count first, because "
                + "reading f[3] from a 3-piece line would crash; then "
                + "emptiness, because two commas side by side produce a "
                + "real but empty piece. Only a line that passes both is "
                + "trusted. The malformed messages say what was wrong, which "
                + "is what an analyst needs to find the bad line.")
            .sample(Lab.typing("09:14,jsmith,10.0.0.5,FAIL"),
                "Line: 09:14,jsmith,10.0.0.5,FAIL",
                "Time: 09:14",
                "User: jsmith",
                "Source: 10.0.0.5",
                "Result: FAIL")
            .hidden(Lab.typing(" 22:03 , mpatel ,192.168.4.20, OK"),
                "Line:  22:03 , mpatel ,192.168.4.20, OK",
                "Time: 22:03",
                "User: mpatel",
                "Source: 192.168.4.20",
                "Result: OK")
            .hidden(Lab.typing("09:14,jsmith,FAIL"),
                "Line: 09:14,jsmith,FAIL",
                "MALFORMED: 3 fields")
            .hidden(Lab.typing("09:14,jsmith,10.0.0.5,FAIL,extra"),
                "Line: 09:14,jsmith,10.0.0.5,FAIL,extra",
                "MALFORMED: 5 fields")
            .hidden(Lab.typing("09:14,,10.0.0.5,FAIL"),
                "Line: 09:14,,10.0.0.5,FAIL",
                "MALFORMED: empty field")
            .hidden(Lab.typing("09:14, ,10.0.0.5,FAIL"),
                "Line: 09:14, ,10.0.0.5,FAIL",
                "MALFORMED: empty field"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(7), "Parallel Arrays Report", Lab.MEDIUM)
            .stretch()
            .after("C05-M015")
            .brief(
                "The identity team sends a list of accounts and each one's "
                + "failed logins this week. Keep the names and the counts in "
                + "two arrays that line up, flag every account at or over "
                + "the review limit, and name the worst one.")
            .practises("Parallel arrays", "Tracking the best index", "Formatted reports")
            .spec(
                "Prompt Accounts (1-8): and read a whole number. Outside 1 to 8: INVALID.",
                "For each account i from 1, prompt Name i: then Failures i: and read a name (trim it) and a whole number.",
                "Print one line per account: the name, a colon and a space, the count - and  REVIEW after it when the count is 5 or more.",
                "worstIndex(counts) returns the index of the highest count (the first one, on a tie).",
                "Print Worst: <name> (<count>).")
            .needsMethod("static int worstIndex(int[])")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int REVIEW = 5;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Accounts (1-8): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        // validate; fill names and counts; report",
                "    }",
                "",
                "    // declare worstIndex(int[] counts) here",
                "}")
            .hints(
                "Two arrays of the same length n: String[] names and "
                + "int[] counts. Fill slot i of both in the same pass.",
                "The report needs the position, so use an index loop and "
                + "read names[i] and counts[i] together.",
                "worstIndex starts with best = 0 and moves it when "
                + "counts[i] > counts[best]. Strict > keeps the first on a "
                + "tie.",
                "Then names[w] and counts[w] name the worst account.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int REVIEW = 5;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Accounts (1-8): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        if (n < 1 || n > 8) {",
                "            System.out.println(\"INVALID\");",
                "            return;",
                "        }",
                "        String[] names = new String[n];",
                "        int[] counts = new int[n];",
                "        for (int i = 0; i < n; i++) {",
                "            System.out.print(\"Name \" + (i + 1) + \": \");",
                "            names[i] = input.nextLine().trim();",
                "            System.out.print(\"Failures \" + (i + 1) + \": \");",
                "            counts[i] = Integer.parseInt(input.nextLine().trim());",
                "        }",
                "        for (int i = 0; i < n; i++) {",
                "            String line = names[i] + \": \" + counts[i];",
                "            if (counts[i] >= REVIEW) {",
                "                line += \"  REVIEW\";",
                "            }",
                "            System.out.println(line);",
                "        }",
                "        int w = worstIndex(counts);",
                "        System.out.println(\"Worst: \" + names[w] + \" (\"",
                "                + counts[w] + \")\");",
                "    }",
                "",
                "    static int worstIndex(int[] counts) {",
                "        int best = 0;",
                "        for (int i = 1; i < counts.length; i++) {",
                "            if (counts[i] > counts[best]) {",
                "                best = i;",
                "            }",
                "        }",
                "        return best;",
                "    }",
                "}")
            .walkthrough(
                "The two arrays are filled in the same pass, so slot i of "
                + "each always describes the same account - the one rule "
                + "parallel arrays depend on. The report loop uses the index "
                + "for the same reason: an enhanced for over names would "
                + "have no way to reach the matching count.\n"
                + "\n"
                + "worstIndex returns a POSITION, not a count. The position "
                + "answers both questions - who and how many - by reading "
                + "each array once. Strict > means a later account with an "
                + "equal count does not replace the first; the hidden tie "
                + "test checks that the rule in the spec is the one "
                + "implemented.")
            .sample(Lab.typing("3", "jsmith", "2", "admin", "14", "svc_web", "6"),
                "Accounts (1-8): 3",
                "Name 1: jsmith",
                "Failures 1: 2",
                "Name 2: admin",
                "Failures 2: 14",
                "Name 3: svc_web",
                "Failures 3: 6",
                "jsmith: 2",
                "admin: 14  REVIEW",
                "svc_web: 6  REVIEW",
                "Worst: admin (14)")
            .hidden(Lab.typing("1", "mpatel", "0"),
                "Accounts (1-8): 1",
                "Name 1: mpatel",
                "Failures 1: 0",
                "mpatel: 0",
                "Worst: mpatel (0)")
            .hidden(Lab.typing("3", "a", "7", "b", "9", "c", "9"),
                "Accounts (1-8): 3",
                "Name 1: a",
                "Failures 1: 7",
                "Name 2: b",
                "Failures 2: 9",
                "Name 3: c",
                "Failures 3: 9",
                "a: 7  REVIEW",
                "b: 9  REVIEW",
                "c: 9  REVIEW",
                "Worst: b (9)")
            .hidden(Lab.typing("2", " temp01 ", "5", "root", "4"),
                "Accounts (1-8): 2",
                "Name 1:  temp01",
                "Failures 1: 5",
                "Name 2: root",
                "Failures 2: 4",
                "temp01: 5  REVIEW",
                "root: 4",
                "Worst: temp01 (5)")
            .hidden(Lab.typing("9"),
                "Accounts (1-8): 9",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(8), "Grid of Alerts", Lab.MEDIUM)
            .stretch()
            .after("C05-M016")
            .brief(
                "Three servers, four six-hour shifts: the alert counts form "
                + "a grid. The duty manager wants each server's day total, "
                + "each shift's total across servers, and the single worst "
                + "server-and-shift. Read the grid a row at a time and let "
                + "methods total it.")
            .practises("Two-dimensional arrays", "split", "Row and column totals")
            .spec(
                "For each server r from 0 to 2, prompt Server <r>: and read a line of 4 whole numbers separated by single spaces.",
                "A line that does not split into exactly 4 pieces: print INVALID and stop at once.",
                "rowTotal(grid, r) and colTotal(grid, c) return the totals of row r and column c.",
                "Print Server <r>: <total> for each row, then Shift <c>: <total> for each column.",
                "Print Worst: server <r>, shift <c> (<count>) for the largest cell - the first one found, reading rows top to bottom and each row left to right, on a tie.")
            .needsMethod("static int rowTotal(int[][], int)")
            .needsMethod("static int colTotal(int[][], int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int SERVERS = 3;",
                "    static final int SHIFTS = 4;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int[][] grid = new int[SERVERS][SHIFTS];",
                "        // read each row, then print totals and the worst cell",
                "    }",
                "",
                "    // declare rowTotal and colTotal here",
                "}")
            .hints(
                "For each row: String[] p = input.nextLine().trim().split(\" \"); "
                + "check p.length, then parse p[c] into grid[r][c].",
                "rowTotal keeps r fixed and loops over the columns; colTotal "
                + "keeps c fixed and loops over the rows.",
                "For the worst cell, track TWO indexes, bestR and bestC, "
                + "starting at 0 and 0.",
                "Strict > while scanning row by row keeps the first "
                + "largest cell.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int SERVERS = 3;",
                "    static final int SHIFTS = 4;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int[][] grid = new int[SERVERS][SHIFTS];",
                "        for (int r = 0; r < SERVERS; r++) {",
                "            System.out.print(\"Server \" + r + \": \");",
                "            String[] p = input.nextLine().trim().split(\" \");",
                "            if (p.length != SHIFTS) {",
                "                System.out.println(\"INVALID\");",
                "                return;",
                "            }",
                "            for (int c = 0; c < SHIFTS; c++) {",
                "                grid[r][c] = Integer.parseInt(p[c]);",
                "            }",
                "        }",
                "        for (int r = 0; r < SERVERS; r++) {",
                "            int total = rowTotal(grid, r);",
                "            System.out.println(\"Server \" + r + \": \" + total);",
                "        }",
                "        for (int c = 0; c < SHIFTS; c++) {",
                "            int total = colTotal(grid, c);",
                "            System.out.println(\"Shift \" + c + \": \" + total);",
                "        }",
                "        int bestR = 0;",
                "        int bestC = 0;",
                "        for (int r = 0; r < SERVERS; r++) {",
                "            for (int c = 0; c < SHIFTS; c++) {",
                "                if (grid[r][c] > grid[bestR][bestC]) {",
                "                    bestR = r;",
                "                    bestC = c;",
                "                }",
                "            }",
                "        }",
                "        System.out.println(\"Worst: server \" + bestR + \", shift \"",
                "                + bestC + \" (\" + grid[bestR][bestC] + \")\");",
                "    }",
                "",
                "    static int rowTotal(int[][] g, int r) {",
                "        int sum = 0;",
                "        for (int c = 0; c < g[r].length; c++) {",
                "            sum += g[r][c];",
                "        }",
                "        return sum;",
                "    }",
                "",
                "    static int colTotal(int[][] g, int c) {",
                "        int sum = 0;",
                "        for (int r = 0; r < g.length; r++) {",
                "            sum += g[r][c];",
                "        }",
                "        return sum;",
                "    }",
                "}")
            .walkthrough(
                "Each input line becomes one row: split, check there are "
                + "exactly four pieces, then parse them into grid[r][0] to "
                + "grid[r][3]. The length check comes before any parsing, so "
                + "a short line is refused rather than crashing on p[3].\n"
                + "\n"
                + "The two total methods are mirror images: rowTotal holds "
                + "the row and walks the columns, colTotal holds the column "
                + "and walks the rows. Mixing up the indexes is the classic "
                + "2D bug, and here it would print plausible wrong numbers. "
                + "The worst cell needs two indexes, since one number no "
                + "longer identifies a position.")
            .sample(Lab.typing("4 30 12 2", "9 28 10 1", "41 25 11 3"),
                "Server 0: 4 30 12 2",
                "Server 1: 9 28 10 1",
                "Server 2: 41 25 11 3",
                "Server 0: 48",
                "Server 1: 48",
                "Server 2: 80",
                "Shift 0: 54",
                "Shift 1: 83",
                "Shift 2: 33",
                "Shift 3: 6",
                "Worst: server 2, shift 0 (41)")
            .hidden(Lab.typing("0 0 0 0", "0 0 0 0", "0 0 0 0"),
                "Server 0: 0 0 0 0",
                "Server 1: 0 0 0 0",
                "Server 2: 0 0 0 0",
                "Server 0: 0",
                "Server 1: 0",
                "Server 2: 0",
                "Shift 0: 0",
                "Shift 1: 0",
                "Shift 2: 0",
                "Shift 3: 0",
                "Worst: server 0, shift 0 (0)")
            .hidden(Lab.typing("1 2 3 9", "9 1 1 1", "0 0 9 0"),
                "Server 0: 1 2 3 9",
                "Server 1: 9 1 1 1",
                "Server 2: 0 0 9 0",
                "Server 0: 15",
                "Server 1: 12",
                "Server 2: 9",
                "Shift 0: 10",
                "Shift 1: 3",
                "Shift 2: 13",
                "Shift 3: 10",
                "Worst: server 0, shift 3 (9)")
            .hidden(Lab.typing("1 2 3", "4 5 6 7", "8 9 10 11"),
                "Server 0: 1 2 3",
                "INVALID")
            .hidden(Lab.typing("1 2 3 4", "5 6 7 8 9"),
                "Server 0: 1 2 3 4",
                "Server 1: 5 6 7 8 9",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(9), "Watchlist", Lab.MEDIUM)
            .after("C05-M026")
            .brief(
                "Build the SOC watchlist from mission 26 as a real tool. It "
                + "reads commands until END, keeps accounts in an ArrayList "
                + "under clear rules - one spelling, no duplicates, a size "
                + "cap - and answers every command, including the ones it "
                + "refuses.")
            .practises("ArrayList", "Watchlists", "remove, contains and indexOf")
            .spec(
                "Repeatedly prompt > and read a command line (trim it), until the command is END.",
                "Split the line at single spaces. The first piece is the command; for ADD, DEL and CHECK the second piece is a name, stored and searched in lower case.",
                "ADD: ALREADY WATCHED if present, FULL if the list already holds 3, otherwise add it and reply ADDED.",
                "DEL: remove every copy; reply REMOVED, or NOT WATCHED if there was none. CHECK: WATCHED or CLEAR.",
                "LIST: reply Watching: followed by the list as println shows it, e.g. [a, b].",
                "ADD, DEL or CHECK without exactly 2 pieces, or LIST with more than 1: MALFORMED. Any other command: UNKNOWN COMMAND.",
                "END: print Watching: and the list, then stop.",
                "run(watch, line) returns the reply for one command (not END); main prints it.")
            .needsMethod("static String run(ArrayList<String>, String)")
            .starter(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int MAX = 3;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        ArrayList<String> watch = new ArrayList<>();",
                "        // loop: prompt, read, stop on END, otherwise print run(...)",
                "    }",
                "",
                "    // declare run(ArrayList<String> w, String line) here",
                "}")
            .hints(
                "A while (true) loop with break on END is the menu loop "
                + "from Campaign 04.",
                "In run: String[] p = line.split(\" \"); then decide by "
                + "p[0], checking p.length before reading p[1].",
                "Lower-case the name ONCE, right after the length check, and "
                + "use that everywhere - ADD, DEL and CHECK must agree.",
                "while (w.remove(name)) { found = true; } removes every "
                + "copy and tells you whether there was one.")
            .solution(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static final int MAX = 3;",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        ArrayList<String> watch = new ArrayList<>();",
                "        while (true) {",
                "            System.out.print(\"> \");",
                "            String line = input.nextLine().trim();",
                "            if (line.equals(\"END\")) {",
                "                System.out.println(\"Watching: \" + watch);",
                "                break;",
                "            }",
                "            System.out.println(run(watch, line));",
                "        }",
                "    }",
                "",
                "    static String run(ArrayList<String> w, String line) {",
                "        String[] p = line.split(\" \");",
                "        String cmd = p[0];",
                "        if (cmd.equals(\"LIST\")) {",
                "            return p.length == 1 ? \"Watching: \" + w : \"MALFORMED\";",
                "        }",
                "        boolean known = cmd.equals(\"ADD\") || cmd.equals(\"DEL\")",
                "                || cmd.equals(\"CHECK\");",
                "        if (!known) {",
                "            return \"UNKNOWN COMMAND\";",
                "        }",
                "        if (p.length != 2) {",
                "            return \"MALFORMED\";",
                "        }",
                "        String name = p[1].toLowerCase();",
                "        if (cmd.equals(\"ADD\")) {",
                "            if (w.contains(name)) {",
                "                return \"ALREADY WATCHED\";",
                "            }",
                "            if (w.size() >= MAX) {",
                "                return \"FULL\";",
                "            }",
                "            w.add(name);",
                "            return \"ADDED\";",
                "        }",
                "        if (cmd.equals(\"DEL\")) {",
                "            boolean found = false;",
                "            while (w.remove(name)) {",
                "                found = true;",
                "            }",
                "            return found ? \"REMOVED\" : \"NOT WATCHED\";",
                "        }",
                "        return w.contains(name) ? \"WATCHED\" : \"CLEAR\";",
                "    }",
                "}")
            .walkthrough(
                "main owns the loop and the END test; run owns the rules. "
                + "Because the list variable is a reference, every add and "
                + "remove inside run changes main's list - no return value "
                + "is needed for that.\n"
                + "\n"
                + "run decides in a careful order: LIST first (it has no "
                + "name), then unknown commands, then the length check - so "
                + "p[1] is only ever read when it exists. The name is "
                + "lower-cased once and the same spelling is used to add, "
                + "remove and search, which is what makes JSmith and jsmith "
                + "one account. FULL is checked after ALREADY WATCHED, so "
                + "adding someone already on a full list gets the more "
                + "useful answer.")
            .sample(Lab.typing("ADD jsmith", "ADD JSmith", "CHECK jsmith", "LIST", "END"),
                "> ADD jsmith",
                "ADDED",
                "> ADD JSmith",
                "ALREADY WATCHED",
                "> CHECK jsmith",
                "WATCHED",
                "> LIST",
                "Watching: [jsmith]",
                "> END",
                "Watching: [jsmith]")
            .hidden(Lab.typing("ADD a", "ADD b", "ADD c", "ADD d", "ADD a", "END"),
                "> ADD a",
                "ADDED",
                "> ADD b",
                "ADDED",
                "> ADD c",
                "ADDED",
                "> ADD d",
                "FULL",
                "> ADD a",
                "ALREADY WATCHED",
                "> END",
                "Watching: [a, b, c]")
            .hidden(Lab.typing("DEL ghost", "ADD temp01", "DEL TEMP01", "CHECK temp01", "END"),
                "> DEL ghost",
                "NOT WATCHED",
                "> ADD temp01",
                "ADDED",
                "> DEL TEMP01",
                "REMOVED",
                "> CHECK temp01",
                "CLEAR",
                "> END",
                "Watching: []")
            .hidden(Lab.typing("ADD", "ADD two names", "LIST all", "PURGE", "list", "END"),
                "> ADD",
                "MALFORMED",
                "> ADD two names",
                "MALFORMED",
                "> LIST all",
                "MALFORMED",
                "> PURGE",
                "UNKNOWN COMMAND",
                "> list",
                "UNKNOWN COMMAND",
                "> END",
                "Watching: []")
            .hidden(Lab.typing("END"),
                "> END",
                "Watching: []"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(10), "Remove Duplicates", Lab.MEDIUM)
            .stretch()
            .after("C05-M027")
            .brief(
                "An alert burst lists the usernames involved, comma "
                + "separated, straight from several tools - so the same "
                + "person appears in different cases, with stray spaces, "
                + "and more than once. Report how many names were seen, and "
                + "the distinct users in the order they first appeared.")
            .practises("Removing duplicates", "split", "Normalising text")
            .spec(
                "Prompt Users: and read one line. Split it at commas.",
                "Each piece is trimmed and lower-cased. Pieces that are empty after trimming are ignored.",
                "distinct(pieces) returns an ArrayList of the tidied, non-empty names, each once, in order of first appearance.",
                "Print Names seen: <non-empty count>, then Distinct: <size>.",
                "Print List: followed by the distinct names separated by \", \" - or List: (none) when there are none.")
            .needsMethod("static ArrayList<String> distinct(String[])")
            .starter(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Users: \");",
                "        String[] pieces = input.nextLine().split(\",\");",
                "        // count the non-empty names; build the distinct list",
                "    }",
                "",
                "    // declare distinct(String[] pieces) here",
                "}")
            .hints(
                "Tidy each piece first: String name = "
                + "piece.trim().toLowerCase();",
                "Skip it if name.isEmpty(); otherwise add it only if "
                + "!unique.contains(name).",
                "Names seen counts the non-empty pieces - a separate "
                + "counter, or a small helper method.",
                "The list prints as [a, b]; for \"a, b\" build the text with "
                + "a separator loop instead.")
            .solution(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Users: \");",
                "        String[] pieces = input.nextLine().split(\",\");",
                "        int seen = 0;",
                "        for (String piece : pieces) {",
                "            if (!piece.trim().isEmpty()) {",
                "                seen++;",
                "            }",
                "        }",
                "        ArrayList<String> unique = distinct(pieces);",
                "        System.out.println(\"Names seen: \" + seen);",
                "        System.out.println(\"Distinct: \" + unique.size());",
                "        if (unique.isEmpty()) {",
                "            System.out.println(\"List: (none)\");",
                "            return;",
                "        }",
                "        String text = \"\";",
                "        for (int i = 0; i < unique.size(); i++) {",
                "            if (i > 0) {",
                "                text += \", \";",
                "            }",
                "            text += unique.get(i);",
                "        }",
                "        System.out.println(\"List: \" + text);",
                "    }",
                "",
                "    static ArrayList<String> distinct(String[] pieces) {",
                "        ArrayList<String> unique = new ArrayList<>();",
                "        for (String piece : pieces) {",
                "            String name = piece.trim().toLowerCase();",
                "            if (!name.isEmpty() && !unique.contains(name)) {",
                "                unique.add(name);",
                "            }",
                "        }",
                "        return unique;",
                "    }",
                "}")
            .walkthrough(
                "distinct builds a new list and adds each tidied name only "
                + "the first time it is seen, so the list keeps the order of "
                + "first appearance. Tidying BEFORE the contains check is "
                + "the whole trick: \"JSmith\", \" jsmith\" and \"jsmith\" all "
                + "become one key.\n"
                + "\n"
                + "The original array is never changed, so the total can "
                + "still be counted from it - two answers from one input, "
                + "which is what an incident report needs: how noisy, and "
                + "how many people. Empty pieces from doubled or trailing "
                + "commas are ignored in both counts, since they are not "
                + "names at all.")
            .sample(Lab.typing("jsmith, JSmith,mpatel, jsmith ,admin,MPATEL"),
                "Users: jsmith, JSmith,mpatel, jsmith ,admin,MPATEL",
                "Names seen: 6",
                "Distinct: 3",
                "List: jsmith, mpatel, admin")
            .hidden(Lab.typing("root"),
                "Users: root",
                "Names seen: 1",
                "Distinct: 1",
                "List: root")
            .hidden(Lab.typing("a,,b, ,a,"),
                "Users: a,,b, ,a,",
                "Names seen: 3",
                "Distinct: 2",
                "List: a, b")
            .hidden(Lab.typing(" , ,"),
                "Users:  , ,",
                "Names seen: 0",
                "Distinct: 0",
                "List: (none)")
            .hidden(Lab.typing("Zed,zed,ZED,ann,Ann"),
                "Users: Zed,zed,ZED,ann,Ann",
                "Names seen: 5",
                "Distinct: 2",
                "List: zed, ann"));
    }
}
