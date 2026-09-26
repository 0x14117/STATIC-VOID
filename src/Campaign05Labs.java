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

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(11), "Sorted Scores", Lab.MEDIUM)
            .stretch()
            .after("C05-M025")
            .brief(
                "A vulnerability scan gives each host a risk score from 0 to "
                + "100, in scan order. The report needs the scores sorted "
                + "and the median - the middle value, which one extreme "
                + "host cannot drag around the way it drags an average. "
                + "The scan order is evidence, so it must survive.")
            .practises("Sorting", "Copying arrays", "Validating every piece")
            .spec(
                "Prompt Scores: and read one line of whole numbers separated by single spaces.",
                "Any piece that is not digits only, or is above 100, or an empty line: print INVALID and stop.",
                "sortedCopy(scores) returns a sorted copy and leaves scores unchanged.",
                "median(sorted) returns the middle value of a sorted array as a double; with an even count, the average of the two middle values.",
                "Print As found: , Sorted: (both as Arrays.toString shows them) and Median: <value>.")
            .needsMethod("static int[] sortedCopy(int[])")
            .needsMethod("static double median(int[])")
            .starter(
                "import java.util.Arrays;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Scores: \");",
                "        String[] parts = input.nextLine().trim().split(\" \");",
                "        // validate every piece, parse, then report",
                "    }",
                "",
                "    // declare sortedCopy(int[] a) and median(int[] sorted)",
                "}")
            .hints(
                "An empty line splits into ONE empty piece, so an "
                + "'is it all digits and not empty' check on each piece "
                + "catches it too.",
                "sortedCopy: Arrays.copyOf(a, a.length), then Arrays.sort "
                + "on the copy, then return the copy.",
                "Odd length n: the middle is sorted[n / 2]. Even: average "
                + "sorted[n / 2 - 1] and sorted[n / 2].",
                "Divide by 2.0, not 2, so the half is kept.")
            .solution(
                "import java.util.Arrays;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Scores: \");",
                "        String[] parts = input.nextLine().trim().split(\" \");",
                "        int[] scores = new int[parts.length];",
                "        for (int i = 0; i < parts.length; i++) {",
                "            if (!isScore(parts[i])) {",
                "                System.out.println(\"INVALID\");",
                "                return;",
                "            }",
                "            scores[i] = Integer.parseInt(parts[i]);",
                "        }",
                "        int[] sorted = sortedCopy(scores);",
                "        System.out.println(\"As found: \" + Arrays.toString(scores));",
                "        System.out.println(\"Sorted: \" + Arrays.toString(sorted));",
                "        System.out.println(\"Median: \" + median(sorted));",
                "    }",
                "",
                "    static boolean isScore(String s) {",
                "        if (s.isEmpty() || s.length() > 3) {",
                "            return false;",
                "        }",
                "        for (int i = 0; i < s.length(); i++) {",
                "            if (!Character.isDigit(s.charAt(i))) {",
                "                return false;",
                "            }",
                "        }",
                "        return Integer.parseInt(s) <= 100;",
                "    }",
                "",
                "    static int[] sortedCopy(int[] a) {",
                "        int[] copy = Arrays.copyOf(a, a.length);",
                "        Arrays.sort(copy);",
                "        return copy;",
                "    }",
                "",
                "    static double median(int[] sorted) {",
                "        int n = sorted.length;",
                "        if (n % 2 == 1) {",
                "            return sorted[n / 2];",
                "        }",
                "        return (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0;",
                "    }",
                "}")
            .walkthrough(
                "Every piece is validated before anything is parsed, so "
                + "\"7x\" or an empty line gets INVALID instead of a crash "
                + "from parseInt. Checking the length (at most 3 digits) "
                + "before parsing also keeps a huge number from overflowing "
                + "int before the range check can see it.\n"
                + "\n"
                + "sortedCopy sorts a copy because Arrays.sort works in "
                + "place - sorting scores itself would destroy the As found "
                + "line. median relies on its argument already being sorted; "
                + "for an even count it averages the two middle values, "
                + "dividing by 2.0 so 47.5 is not cut down to 47. The sample "
                + "shows why the median is used: one host scoring 100 barely "
                + "moves it.")
            .sample(Lab.typing("40 12 100 35 38"),
                "Scores: 40 12 100 35 38",
                "As found: [40, 12, 100, 35, 38]",
                "Sorted: [12, 35, 38, 40, 100]",
                "Median: 38.0")
            .hidden(Lab.typing("50 45"),
                "Scores: 50 45",
                "As found: [50, 45]",
                "Sorted: [45, 50]",
                "Median: 47.5")
            .hidden(Lab.typing("7"),
                "Scores: 7",
                "As found: [7]",
                "Sorted: [7]",
                "Median: 7.0")
            .hidden(Lab.typing("0 100 0 100"),
                "Scores: 0 100 0 100",
                "As found: [0, 100, 0, 100]",
                "Sorted: [0, 0, 100, 100]",
                "Median: 50.0")
            .hidden(Lab.typing("40 7x 12"),
                "Scores: 40 7x 12",
                "INVALID")
            .hidden(Lab.typing("40 101"),
                "Scores: 40 101",
                "INVALID")
            .hidden(Lab.typing(""),
                "Scores:",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(12), "Top Offender", Lab.MEDIUM)
            .after("C05-M028")
            .brief(
                "Read the overnight authentication log one line at a time "
                + "until END. Count the failed logins per account in two "
                + "lists that grow together, report every account that "
                + "failed, and name the top offender - counting, not "
                + "crashing on, the lines that are malformed.")
            .practises("Counting per key", "ArrayList", "Skipping malformed input")
            .spec(
                "Keep two static lists: names (ArrayList<String>) and counts (ArrayList<Integer>).",
                "Repeatedly prompt Log: and read a line (trim it) until the line is END.",
                "A line must be exactly two pieces split at a single space: a result (FAIL or OK) and a user. Anything else counts as malformed.",
                "record(user) adds one failure for user (lower-cased): a new user joins both lists with 1; a known one's count goes up by 1.",
                "After END, print <user>: <count> for each user with failures, in order of first failure.",
                "topIndex(counts) returns the index of the highest count (the first on a tie). Print Top: <user> (<count>), or Top: (none) when there were no failures.",
                "Print Malformed: <count>.")
            .needsMethod("static void record(String)")
            .needsMethod("static int topIndex(ArrayList<Integer>)")
            .starter(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static ArrayList<String> names = new ArrayList<>();",
                "    static ArrayList<Integer> counts = new ArrayList<>();",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int malformed = 0;",
                "        // read until END, then report",
                "    }",
                "",
                "    // declare record(String user) and topIndex(...) here",
                "}")
            .hints(
                "Split each line with split(\" \"). Malformed means the "
                + "length is not 2, or the first piece is neither FAIL nor OK.",
                "In record: int at = names.indexOf(user); -1 means new - add "
                + "to BOTH lists.",
                "A known user: counts.set(at, counts.get(at) + 1);",
                "Check counts.isEmpty() before calling topIndex - an empty "
                + "night has no top offender.")
            .solution(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    static ArrayList<String> names = new ArrayList<>();",
                "    static ArrayList<Integer> counts = new ArrayList<>();",
                "",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int malformed = 0;",
                "        while (true) {",
                "            System.out.print(\"Log: \");",
                "            String line = input.nextLine().trim();",
                "            if (line.equals(\"END\")) {",
                "                break;",
                "            }",
                "            String[] p = line.split(\" \");",
                "            String r = p[0];",
                "            boolean ok = p.length == 2",
                "                    && (r.equals(\"FAIL\") || r.equals(\"OK\"));",
                "            if (!ok) {",
                "                malformed++;",
                "            } else if (r.equals(\"FAIL\")) {",
                "                record(p[1].toLowerCase());",
                "            }",
                "        }",
                "        for (int i = 0; i < names.size(); i++) {",
                "            System.out.println(names.get(i) + \": \" + counts.get(i));",
                "        }",
                "        if (counts.isEmpty()) {",
                "            System.out.println(\"Top: (none)\");",
                "        } else {",
                "            int t = topIndex(counts);",
                "            System.out.println(\"Top: \" + names.get(t)",
                "                    + \" (\" + counts.get(t) + \")\");",
                "        }",
                "        System.out.println(\"Malformed: \" + malformed);",
                "    }",
                "",
                "    static void record(String user) {",
                "        int at = names.indexOf(user);",
                "        if (at == -1) {",
                "            names.add(user);",
                "            counts.add(1);",
                "        } else {",
                "            counts.set(at, counts.get(at) + 1);",
                "        }",
                "    }",
                "",
                "    static int topIndex(ArrayList<Integer> c) {",
                "        int best = 0;",
                "        for (int i = 1; i < c.size(); i++) {",
                "            if (c.get(i) > c.get(best)) {",
                "                best = i;",
                "            }",
                "        }",
                "        return best;",
                "    }",
                "}")
            .walkthrough(
                "record is the only code that changes the two lists, and it "
                + "always changes them together: a new user is added to "
                + "names AND counts, a known user's count is replaced with "
                + "one more. With a single place that writes them, the lists "
                + "cannot drift out of step. Keeping them as static fields "
                + "(Campaign 03) lets record reach them without passing both "
                + "on every call.\n"
                + "\n"
                + "split always returns at least one piece, so p[0] is safe "
                + "to read; p[1] is only used once && has confirmed exactly "
                + "two. Malformed lines are counted, not ignored - a burst "
                + "of them is itself worth reporting. Comparing counts with "
                + "> inside topIndex unboxes the two Integers, so it "
                + "compares numbers; == would not have been safe. And an "
                + "empty night is handled before topIndex, which would "
                + "otherwise read get(0) from an empty list.")
            .sample(Lab.typing("FAIL jsmith", "OK mpatel", "FAIL admin", "FAIL Admin", "FAIL jsmith", "FAIL admin", "END"),
                "Log: FAIL jsmith",
                "Log: OK mpatel",
                "Log: FAIL admin",
                "Log: FAIL Admin",
                "Log: FAIL jsmith",
                "Log: FAIL admin",
                "Log: END",
                "jsmith: 2",
                "admin: 3",
                "Top: admin (3)",
                "Malformed: 0")
            .hidden(Lab.typing("OK a", "OK b", "END"),
                "Log: OK a",
                "Log: OK b",
                "Log: END",
                "Top: (none)",
                "Malformed: 0")
            .hidden(Lab.typing("FAIL x", "garbage", "FAIL", "DENY y", "FAIL a b", "FAIL y", "END"),
                "Log: FAIL x",
                "Log: garbage",
                "Log: FAIL",
                "Log: DENY y",
                "Log: FAIL a b",
                "Log: FAIL y",
                "Log: END",
                "x: 1",
                "y: 1",
                "Top: x (1)",
                "Malformed: 4")
            .hidden(Lab.typing("FAIL bob", "FAIL ann", "FAIL ann", "FAIL bob", "END"),
                "Log: FAIL bob",
                "Log: FAIL ann",
                "Log: FAIL ann",
                "Log: FAIL bob",
                "Log: END",
                "bob: 2",
                "ann: 2",
                "Top: bob (2)",
                "Malformed: 0")
            .hidden(Lab.typing("END"),
                "Log: END",
                "Top: (none)",
                "Malformed: 0"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(13), "Allowlist", Lab.MEDIUM)
            .after("C05-M029")
            .brief(
                "The payment servers may only call out to three approved "
                + "domains. Check a batch of requested domains against the "
                + "allowlist: deny by default, normalise first, and match "
                + "exactly - so the look-alikes a tester will try are all "
                + "refused.")
            .practises("Allowlists", "ArrayList", "Exact matching")
            .spec(
                "The program's allowlist holds pay.example.com, api.example.com and updates.example.com.",
                "Prompt Requests (1-6): and read a whole number. Outside 1 to 6: INVALID.",
                "For each request i from 1, prompt Domain i: and read a line. Tidy it: trim and lower-case.",
                "isAllowed(allow, domain) is true only when the tidied domain EQUALS an entry.",
                "After each domain print ALLOW <tidied> or DENY <tidied>.",
                "Finally print Allowed: <a>, denied: <d>.")
            .needsMethod("static boolean isAllowed(ArrayList<String>, String)")
            .starter(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> allow = new ArrayList<>();",
                "        allow.add(\"pay.example.com\");",
                "        allow.add(\"api.example.com\");",
                "        allow.add(\"updates.example.com\");",
                "        Scanner input = new Scanner(System.in);",
                "        // read the count, check each domain, then the totals",
                "    }",
                "",
                "    // declare isAllowed(ArrayList<String> allow, String d)",
                "}")
            .hints(
                "Tidy once in main: input.nextLine().trim().toLowerCase().",
                "The list's contains compares with equals - exact match, "
                + "and false for anything not listed.",
                "Never String's contains, startsWith or endsWith: each "
                + "lets a look-alike through.",
                "Two counters, one for each verdict.")
            .solution(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> allow = new ArrayList<>();",
                "        allow.add(\"pay.example.com\");",
                "        allow.add(\"api.example.com\");",
                "        allow.add(\"updates.example.com\");",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Requests (1-6): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        if (n < 1 || n > 6) {",
                "            System.out.println(\"INVALID\");",
                "            return;",
                "        }",
                "        int allowed = 0;",
                "        int denied = 0;",
                "        for (int i = 1; i <= n; i++) {",
                "            System.out.print(\"Domain \" + i + \": \");",
                "            String d = input.nextLine().trim().toLowerCase();",
                "            if (isAllowed(allow, d)) {",
                "                System.out.println(\"ALLOW \" + d);",
                "                allowed++;",
                "            } else {",
                "                System.out.println(\"DENY \" + d);",
                "                denied++;",
                "            }",
                "        }",
                "        System.out.println(\"Allowed: \" + allowed",
                "                + \", denied: \" + denied);",
                "    }",
                "",
                "    static boolean isAllowed(ArrayList<String> allow, String d) {",
                "        return allow.contains(d);",
                "    }",
                "}")
            .walkthrough(
                "The whole check is allow.contains(d): the LIST's contains, "
                + "which compares the domain with each entry using equals. "
                + "It is true only for an exact match and false for "
                + "everything else - including every look-alike and the "
                + "empty string. That is deny by default in one line.\n"
                + "\n"
                + "Normalising in main, before the check, is what lets "
                + "\" PAY.Example.COM \" through while still refusing "
                + "pay.example.com.attacker.net and badexample.com. The "
                + "hidden tests are the tricks a real tester would try: a "
                + "listed name as a prefix, as a suffix, a parent domain, "
                + "and nothing at all.")
            .sample(Lab.typing("3", "pay.example.com", " API.Example.com ", "pay.example.com.attacker.net"),
                "Requests (1-6): 3",
                "Domain 1: pay.example.com",
                "ALLOW pay.example.com",
                "Domain 2:  API.Example.com",
                "ALLOW api.example.com",
                "Domain 3: pay.example.com.attacker.net",
                "DENY pay.example.com.attacker.net",
                "Allowed: 2, denied: 1")
            .hidden(Lab.typing("4", "example.com", "badpay.example.com", "updates.example.com", "updates.example.co"),
                "Requests (1-6): 4",
                "Domain 1: example.com",
                "DENY example.com",
                "Domain 2: badpay.example.com",
                "DENY badpay.example.com",
                "Domain 3: updates.example.com",
                "ALLOW updates.example.com",
                "Domain 4: updates.example.co",
                "DENY updates.example.co",
                "Allowed: 1, denied: 3")
            .hidden(Lab.typing("2", "", "pay.example.com."),
                "Requests (1-6): 2",
                "Domain 1:",
                "DENY",
                "Domain 2: pay.example.com.",
                "DENY pay.example.com.",
                "Allowed: 0, denied: 2")
            .hidden(Lab.typing("1", "UPDATES.EXAMPLE.COM"),
                "Requests (1-6): 1",
                "Domain 1: UPDATES.EXAMPLE.COM",
                "ALLOW updates.example.com",
                "Allowed: 1, denied: 0")
            .hidden(Lab.typing("7"),
                "Requests (1-6): 7",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(14), "Merge Two Lists", Lab.MEDIUM)
            .stretch()
            .after("C05-M027")
            .brief(
                "Two threat feeds each send a list of bad domains. Before "
                + "merging them into the blocklist, the analyst wants to "
                + "see what each feed saw alone, what both agree on, and "
                + "the merged list - sorted, and with no domain twice.")
            .practises("Removing duplicates", "remove, contains and indexOf", "Sorting")
            .spec(
                "Prompt Feed A: and Feed B: and read one comma-separated line each.",
                "toList(line) splits at commas, trims and lower-cases each piece, skips empty pieces and duplicates, and returns an ArrayList in first-seen order.",
                "merged(a, b) returns a new list holding every domain from a and b once, sorted.",
                "show(list) returns the list's domains sorted and separated by \", \" - or (none) when it is empty - without changing the list.",
                "Print Only A: , Only B: , Both: and Merged: lines, each followed by show of its list.")
            .needsMethod("static ArrayList<String> toList(String)")
            .needsMethod("static String show(ArrayList<String>)")
            .starter(
                "import java.util.ArrayList;",
                "import java.util.Collections;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Feed A: \");",
                "        String lineA = input.nextLine();",
                "        System.out.print(\"Feed B: \");",
                "        String lineB = input.nextLine();",
                "        // build both lists, then the four report lines",
                "    }",
                "",
                "    // declare toList(String line), merged(a, b) and show(list)",
                "}")
            .hints(
                "toList is mission 27's distinct loop over line.split(\",\").",
                "Only A: each item of a that b does not contain. Both: each "
                + "item of a that b DOES contain.",
                "merged: copy a into a new list, add each item of b it does "
                + "not already contain, then Collections.sort it.",
                "A small show(list) helper that sorts a copy and joins with "
                + "\", \" - or returns (none) - keeps main short.")
            .solution(
                "import java.util.ArrayList;",
                "import java.util.Collections;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Feed A: \");",
                "        String lineA = input.nextLine();",
                "        System.out.print(\"Feed B: \");",
                "        String lineB = input.nextLine();",
                "        ArrayList<String> a = toList(lineA);",
                "        ArrayList<String> b = toList(lineB);",
                "        ArrayList<String> onlyA = new ArrayList<>();",
                "        ArrayList<String> both = new ArrayList<>();",
                "        for (String d : a) {",
                "            if (b.contains(d)) {",
                "                both.add(d);",
                "            } else {",
                "                onlyA.add(d);",
                "            }",
                "        }",
                "        ArrayList<String> onlyB = new ArrayList<>();",
                "        for (String d : b) {",
                "            if (!a.contains(d)) {",
                "                onlyB.add(d);",
                "            }",
                "        }",
                "        System.out.println(\"Only A: \" + show(onlyA));",
                "        System.out.println(\"Only B: \" + show(onlyB));",
                "        System.out.println(\"Both: \" + show(both));",
                "        System.out.println(\"Merged: \" + show(merged(a, b)));",
                "    }",
                "",
                "    static ArrayList<String> toList(String line) {",
                "        ArrayList<String> out = new ArrayList<>();",
                "        for (String piece : line.split(\",\")) {",
                "            String d = piece.trim().toLowerCase();",
                "            if (!d.isEmpty() && !out.contains(d)) {",
                "                out.add(d);",
                "            }",
                "        }",
                "        return out;",
                "    }",
                "",
                "    static ArrayList<String> merged(ArrayList<String> a,",
                "            ArrayList<String> b) {",
                "        ArrayList<String> out = new ArrayList<>();",
                "        for (String d : a) {",
                "            out.add(d);",
                "        }",
                "        for (String d : b) {",
                "            if (!out.contains(d)) {",
                "                out.add(d);",
                "            }",
                "        }",
                "        Collections.sort(out);",
                "        return out;",
                "    }",
                "",
                "    static String show(ArrayList<String> list) {",
                "        if (list.isEmpty()) {",
                "            return \"(none)\";",
                "        }",
                "        ArrayList<String> copy = new ArrayList<>();",
                "        for (String d : list) {",
                "            copy.add(d);",
                "        }",
                "        Collections.sort(copy);",
                "        String text = \"\";",
                "        for (int i = 0; i < copy.size(); i++) {",
                "            text += (i > 0 ? \", \" : \"\") + copy.get(i);",
                "        }",
                "        return text;",
                "    }",
                "}")
            .walkthrough(
                "toList turns a messy feed line into a clean list: every "
                + "piece tidied, empty ones dropped, each domain once. After "
                + "that, the three comparisons are just contains in each "
                + "direction - a domain from A that B also holds is in both; "
                + "one it lacks is only in A; and the mirror for B.\n"
                + "\n"
                + "merged starts from a NEW list, so neither feed's list is "
                + "changed by the merge, then adds B's domains only where "
                + "they are missing. show sorts a copy for the same reason: "
                + "printing should never rearrange the data it prints. The "
                + "overlap is often the most useful line - two independent "
                + "feeds agreeing is strong evidence.")
            .sample(Lab.typing("evil.test, Phish.example ,malware.test", "malware.test,cnc.test, evil.test"),
                "Feed A: evil.test, Phish.example ,malware.test",
                "Feed B: malware.test,cnc.test, evil.test",
                "Only A: phish.example",
                "Only B: cnc.test",
                "Both: evil.test, malware.test",
                "Merged: cnc.test, evil.test, malware.test, phish.example")
            .hidden(Lab.typing("a.test,b.test", "c.test,d.test"),
                "Feed A: a.test,b.test",
                "Feed B: c.test,d.test",
                "Only A: a.test, b.test",
                "Only B: c.test, d.test",
                "Both: (none)",
                "Merged: a.test, b.test, c.test, d.test")
            .hidden(Lab.typing("x.test, X.TEST ,,x.test", "x.test"),
                "Feed A: x.test, X.TEST ,,x.test",
                "Feed B: x.test",
                "Only A: (none)",
                "Only B: (none)",
                "Both: x.test",
                "Merged: x.test")
            .hidden(Lab.typing("", "z.test,a.test"),
                "Feed A:",
                "Feed B: z.test,a.test",
                "Only A: (none)",
                "Only B: a.test, z.test",
                "Both: (none)",
                "Merged: a.test, z.test")
            .hidden(Lab.typing(" , ", ""),
                "Feed A:  ,",
                "Feed B:",
                "Only A: (none)",
                "Only B: (none)",
                "Both: (none)",
                "Merged: (none)"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(15), "Rotate an Array", Lab.MEDIUM)
            .stretch()
            .after("C05-M011")
            .brief(
                "The on-call rota is a list of analysts; each week the "
                + "first one moves to the back. After k weeks, who is "
                + "first? Rotating an array by k places - without losing "
                + "anyone, even when k is bigger than the team - is a neat "
                + "use of %.")
            .practises("Returning an array", "Index arithmetic with %", "Copying arrays")
            .spec(
                "Prompt Team: and read one comma-separated line of names. Trim each; if any is empty, print INVALID and stop.",
                "Prompt Weeks: and read a whole number. Negative: print INVALID and stop.",
                "rotated(team, k) returns a NEW array where slot i holds team[(i + k) % team.length]. team is not changed.",
                "Print Now: and Rotated by <k>: lines, each listing names separated by \", \".")
            .needsMethod("static String[] rotated(String[], int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Team: \");",
                "        String[] team = input.nextLine().split(\",\");",
                "        // trim and check the names, read weeks, then report",
                "    }",
                "",
                "    // declare rotated(String[] team, int k) here",
                "}")
            .hints(
                "Trim in place with an index loop: team[i] = team[i].trim();",
                "The new array has team.length slots; fill slot i from "
                + "(i + k) % team.length.",
                "% keeps every index in range however large k is: rotating "
                + "a team of 3 by 7 is the same as by 1.",
                "A join helper with a \", \" separator prints both lines.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Team: \");",
                "        String[] team = input.nextLine().split(\",\");",
                "        for (int i = 0; i < team.length; i++) {",
                "            team[i] = team[i].trim();",
                "            if (team[i].isEmpty()) {",
                "                System.out.println(\"INVALID\");",
                "                return;",
                "            }",
                "        }",
                "        System.out.print(\"Weeks: \");",
                "        int k = Integer.parseInt(input.nextLine().trim());",
                "        if (k < 0) {",
                "            System.out.println(\"INVALID\");",
                "            return;",
                "        }",
                "        System.out.println(\"Now: \" + joined(team));",
                "        System.out.println(\"Rotated by \" + k + \": \"",
                "                + joined(rotated(team, k)));",
                "    }",
                "",
                "    static String[] rotated(String[] team, int k) {",
                "        String[] out = new String[team.length];",
                "        for (int i = 0; i < team.length; i++) {",
                "            out[i] = team[(i + k) % team.length];",
                "        }",
                "        return out;",
                "    }",
                "",
                "    static String joined(String[] items) {",
                "        String text = \"\";",
                "        for (int i = 0; i < items.length; i++) {",
                "            text += (i > 0 ? \", \" : \"\") + items[i];",
                "        }",
                "        return text;",
                "    }",
                "}")
            .walkthrough(
                "Slot i of the rotated rota takes the person k places "
                + "further along the original - and when that runs off the "
                + "end, % wraps it back to the start. For a team of 4 and "
                + "k = 1: slot 0 gets team[1], slot 3 gets team[4 % 4], "
                + "which is team[0]. No index can ever be out of bounds, "
                + "and a k of 9 or 400 works as well as 1.\n"
                + "\n"
                + "Building a new array keeps the Now: line correct; "
                + "shuffling names within team itself would need a "
                + "temporary variable and care not to overwrite anyone "
                + "before they were moved. An empty name is refused before "
                + "anything is printed, since a rota with a blank slot is "
                + "a week nobody is on call.")
            .sample(Lab.typing("ana,ben,cal,dee", "1"),
                "Team: ana,ben,cal,dee",
                "Weeks: 1",
                "Now: ana, ben, cal, dee",
                "Rotated by 1: ben, cal, dee, ana")
            .hidden(Lab.typing("ana, ben ,cal", "0"),
                "Team: ana, ben ,cal",
                "Weeks: 0",
                "Now: ana, ben, cal",
                "Rotated by 0: ana, ben, cal")
            .hidden(Lab.typing("ana,ben,cal", "7"),
                "Team: ana,ben,cal",
                "Weeks: 7",
                "Now: ana, ben, cal",
                "Rotated by 7: ben, cal, ana")
            .hidden(Lab.typing("solo", "5"),
                "Team: solo",
                "Weeks: 5",
                "Now: solo",
                "Rotated by 5: solo")
            .hidden(Lab.typing("ana,,cal", "1"),
                "Team: ana,,cal",
                "INVALID")
            .hidden(Lab.typing("ana,ben", "-1"),
                "Team: ana,ben",
                "Weeks: -1",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(16), "Histogram", Lab.MEDIUM)
            .stretch()
            .after("C05-M014")
            .brief(
                "A list of login hours means little as numbers. As a bar "
                + "chart, a 3 a.m. login jumps off the screen. Tally the "
                + "hours into 24 counters, draw a bar for every hour that "
                + "had logins, and count the night-time ones - ignoring, "
                + "not crashing on, hours that cannot exist.")
            .practises("Tally arrays", "Range checks before indexing", "Building a String in a loop")
            .spec(
                "Prompt Hours: and read one line of whole numbers separated by single spaces.",
                "tally(hours) returns an int[24] where slot h counts how many times hour h appears. Values outside 0 to 23 are skipped.",
                "bar(n) returns a String of n # characters.",
                "For every hour with a count above 0, in order, print the hour as two digits, \" | \", the bar, a space and the count.",
                "Print Night (00-05): <total for hours 0 to 5>, then Ignored: <how many values were out of range>.")
            .needsMethod("static int[] tally(int[])")
            .needsMethod("static String bar(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Hours: \");",
                "        String[] parts = input.nextLine().trim().split(\" \");",
                "        // parse, tally, draw the chart, then the two totals",
                "    }",
                "",
                "    // declare tally(int[] hours) and bar(int n) here",
                "}")
            .hints(
                "Parse every piece into an int[] first; tests only use whole "
                + "numbers here.",
                "In tally: if (h >= 0 && h < 24) { counts[h]++; } - the "
                + "range check keeps a 24 or -1 from crashing.",
                "String.format(\"%02d\", hour) turns 3 into 03.",
                "Ignored is the number of values minus the sum of all 24 "
                + "counters - or count them separately.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Hours: \");",
                "        String[] parts = input.nextLine().trim().split(\" \");",
                "        int[] hours = new int[parts.length];",
                "        for (int i = 0; i < parts.length; i++) {",
                "            hours[i] = Integer.parseInt(parts[i]);",
                "        }",
                "        int[] counts = tally(hours);",
                "        int counted = 0;",
                "        int night = 0;",
                "        for (int h = 0; h < counts.length; h++) {",
                "            counted += counts[h];",
                "            if (h <= 5) {",
                "                night += counts[h];",
                "            }",
                "            if (counts[h] > 0) {",
                "                System.out.println(String.format(\"%02d\", h) + \" | \"",
                "                        + bar(counts[h]) + \" \" + counts[h]);",
                "            }",
                "        }",
                "        System.out.println(\"Night (00-05): \" + night);",
                "        System.out.println(\"Ignored: \" + (hours.length - counted));",
                "    }",
                "",
                "    static int[] tally(int[] hours) {",
                "        int[] counts = new int[24];",
                "        for (int h : hours) {",
                "            if (h >= 0 && h < counts.length) {",
                "                counts[h]++;",
                "            }",
                "        }",
                "        return counts;",
                "    }",
                "",
                "    static String bar(int n) {",
                "        String text = \"\";",
                "        for (int i = 0; i < n; i++) {",
                "            text += \"#\";",
                "        }",
                "        return text;",
                "    }",
                "}")
            .walkthrough(
                "tally is mission 14's pattern: the hour itself is the "
                + "index, so counts[h]++ files each login in its own slot in "
                + "one step. The range check in front of it is what makes "
                + "the tool safe to feed - an hour of 24 or -1 is skipped "
                + "and reported, rather than throwing "
                + "ArrayIndexOutOfBoundsException and losing the whole "
                + "chart.\n"
                + "\n"
                + "One pass over the 24 counters does everything else: "
                + "draws the non-empty bars in hour order, adds up the "
                + "night hours, and totals what was counted so the ignored "
                + "values can be worked out without a second counter.")
            .sample(Lab.typing("9 9 10 3 14 9 23 10"),
                "Hours: 9 9 10 3 14 9 23 10",
                "03 | # 1",
                "09 | ### 3",
                "10 | ## 2",
                "14 | # 1",
                "23 | # 1",
                "Night (00-05): 1",
                "Ignored: 0")
            .hidden(Lab.typing("0 5 6 23"),
                "Hours: 0 5 6 23",
                "00 | # 1",
                "05 | # 1",
                "06 | # 1",
                "23 | # 1",
                "Night (00-05): 2",
                "Ignored: 0")
            .hidden(Lab.typing("24 -1 12 99"),
                "Hours: 24 -1 12 99",
                "12 | # 1",
                "Night (00-05): 0",
                "Ignored: 3")
            .hidden(Lab.typing("2 2 2 2 2 2"),
                "Hours: 2 2 2 2 2 2",
                "02 | ###### 6",
                "Night (00-05): 6",
                "Ignored: 0"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(17), "Frequency Table", Lab.MEDIUM)
            .stretch()
            .after("C05-M025")
            .brief(
                "The web server's status codes for the last minute arrive "
                + "as a line of numbers. Build a frequency table - each "
                + "distinct code once, in order, with how often it appeared "
                + "- and name the most common. Codes run to 599, so this "
                + "time the distinct values come first and the counting "
                + "second.")
            .practises("Removing duplicates", "Sorting", "Returning an array")
            .spec(
                "Prompt Codes: and read one line of whole numbers separated by single spaces.",
                "distinctSorted(codes) returns a new int[] holding each distinct code once, smallest first.",
                "countOf(codes, code) returns how many times code appears in codes.",
                "Print one line per distinct code: the code, \" x\" and its count.",
                "Print Most common: <code> (<count>) - the smallest code, if several share the highest count.")
            .needsMethod("static int[] distinctSorted(int[])")
            .needsMethod("static int countOf(int[], int)")
            .starter(
                "import java.util.Arrays;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Codes: \");",
                "        String[] parts = input.nextLine().trim().split(\" \");",
                "        // parse, find the distinct codes, count each one",
                "    }",
                "",
                "    // declare distinctSorted(int[] a) and countOf(int[] a, int v)",
                "}")
            .hints(
                "Sort a copy first: then equal codes sit next to each other.",
                "Walking the sorted copy, a code is new when it is the first "
                + "one or differs from the one before it.",
                "Count the new ones first to size the result array, then "
                + "fill it - or collect them in an ArrayList<Integer> and "
                + "copy that into an int[].",
                "Scanning distinct codes smallest first with a strict > "
                + "leaves the smallest code on a tie.")
            .solution(
                "import java.util.Arrays;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Codes: \");",
                "        String[] parts = input.nextLine().trim().split(\" \");",
                "        int[] codes = new int[parts.length];",
                "        for (int i = 0; i < parts.length; i++) {",
                "            codes[i] = Integer.parseInt(parts[i]);",
                "        }",
                "        int[] kinds = distinctSorted(codes);",
                "        int best = kinds[0];",
                "        for (int k : kinds) {",
                "            int n = countOf(codes, k);",
                "            System.out.println(k + \" x\" + n);",
                "            if (n > countOf(codes, best)) {",
                "                best = k;",
                "            }",
                "        }",
                "        System.out.println(\"Most common: \" + best + \" (\"",
                "                + countOf(codes, best) + \")\");",
                "    }",
                "",
                "    static int[] distinctSorted(int[] a) {",
                "        int[] s = Arrays.copyOf(a, a.length);",
                "        Arrays.sort(s);",
                "        int kinds = 0;",
                "        for (int i = 0; i < s.length; i++) {",
                "            if (i == 0 || s[i] != s[i - 1]) {",
                "                kinds++;",
                "            }",
                "        }",
                "        int[] out = new int[kinds];",
                "        int next = 0;",
                "        for (int i = 0; i < s.length; i++) {",
                "            if (i == 0 || s[i] != s[i - 1]) {",
                "                out[next] = s[i];",
                "                next++;",
                "            }",
                "        }",
                "        return out;",
                "    }",
                "",
                "    static int countOf(int[] a, int v) {",
                "        int n = 0;",
                "        for (int x : a) {",
                "            if (x == v) {",
                "                n++;",
                "            }",
                "        }",
                "        return n;",
                "    }",
                "}")
            .walkthrough(
                "Sorting a copy puts equal codes side by side, so a code is "
                + "new exactly when it differs from its left neighbour. "
                + "distinctSorted makes two passes: one to count the "
                + "distinct codes, so the result array can be the right "
                + "size, and one to fill it. That is the price of arrays "
                + "having a fixed size - an ArrayList would have grown "
                + "instead.\n"
                + "\n"
                + "The original codes array is never sorted, and countOf "
                + "reads it directly. Walking the distinct codes smallest "
                + "first with a strict > means a later code with an equal "
                + "count never replaces an earlier one, which is the "
                + "tie rule in the spec. The i == 0 guard stops s[i - 1] "
                + "from being read at index -1.")
            .sample(Lab.typing("200 404 200 500 200 404 301"),
                "Codes: 200 404 200 500 200 404 301",
                "200 x3",
                "301 x1",
                "404 x2",
                "500 x1",
                "Most common: 200 (3)")
            .hidden(Lab.typing("503"),
                "Codes: 503",
                "503 x1",
                "Most common: 503 (1)")
            .hidden(Lab.typing("404 200 404 200"),
                "Codes: 404 200 404 200",
                "200 x2",
                "404 x2",
                "Most common: 200 (2)")
            .hidden(Lab.typing("500 500 500 403 401 403"),
                "Codes: 500 500 500 403 401 403",
                "401 x1",
                "403 x2",
                "500 x3",
                "Most common: 500 (3)"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(18), "Second Largest", Lab.SMALL)
            .stretch()
            .after("C05-M013")
            .brief(
                "Capacity planning wants the busiest server's request count "
                + "- and the runner-up, the second-largest DIFFERENT value. "
                + "Two servers tied at the top do not make a runner-up. "
                + "Find it in one pass, without sorting.")
            .practises("Max, min and average", "Tracking two values", "Validating every piece")
            .spec(
                "Prompt Requests: and read one line of whole numbers separated by single spaces. Any piece that is not all digits (or an empty line): INVALID.",
                "hasSecond(values) is true when there are at least two different values.",
                "secondLargest(values) returns the largest value that is smaller than the maximum.",
                "Print Largest: <max>, then Second: <value> - or Second: none when hasSecond is false.")
            .needsMethod("static boolean hasSecond(int[])")
            .needsMethod("static int secondLargest(int[])")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Requests: \");",
                "        String[] parts = input.nextLine().trim().split(\" \");",
                "        // validate and parse, then report",
                "    }",
                "",
                "    // declare hasSecond(int[] v) and secondLargest(int[] v)",
                "}")
            .hints(
                "hasSecond: are any two values different? Compare each with "
                + "values[0].",
                "Find the maximum first. Then the second is the largest "
                + "value that is strictly below it.",
                "In one pass: keep max and second; a new maximum pushes the "
                + "old one down into second.",
                "Start second at -1: counts are never negative, and "
                + "hasSecond guarantees it is replaced.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Requests: \");",
                "        String[] parts = input.nextLine().trim().split(\" \");",
                "        int[] v = new int[parts.length];",
                "        for (int i = 0; i < parts.length; i++) {",
                "            if (!isCount(parts[i])) {",
                "                System.out.println(\"INVALID\");",
                "                return;",
                "            }",
                "            v[i] = Integer.parseInt(parts[i]);",
                "        }",
                "        int max = v[0];",
                "        for (int x : v) {",
                "            max = Math.max(max, x);",
                "        }",
                "        System.out.println(\"Largest: \" + max);",
                "        if (hasSecond(v)) {",
                "            System.out.println(\"Second: \" + secondLargest(v));",
                "        } else {",
                "            System.out.println(\"Second: none\");",
                "        }",
                "    }",
                "",
                "    static boolean isCount(String s) {",
                "        if (s.isEmpty() || s.length() > 9) {",
                "            return false;",
                "        }",
                "        for (int i = 0; i < s.length(); i++) {",
                "            if (!Character.isDigit(s.charAt(i))) {",
                "                return false;",
                "            }",
                "        }",
                "        return true;",
                "    }",
                "",
                "    static boolean hasSecond(int[] v) {",
                "        for (int x : v) {",
                "            if (x != v[0]) {",
                "                return true;",
                "            }",
                "        }",
                "        return false;",
                "    }",
                "",
                "    static int secondLargest(int[] v) {",
                "        int max = v[0];",
                "        int second = -1;",
                "        for (int x : v) {",
                "            if (x > max) {",
                "                second = max;",
                "                max = x;",
                "            } else if (x < max && x > second) {",
                "                second = x;",
                "            }",
                "        }",
                "        return second;",
                "    }",
                "}")
            .walkthrough(
                "secondLargest keeps two running values. A new maximum "
                + "pushes the old maximum down into second, so nothing is "
                + "lost when the biggest value arrives late. A value equal "
                + "to the maximum changes nothing - that is what makes the "
                + "answer the second DIFFERENT value - and anything between "
                + "second and max becomes the new second.\n"
                + "\n"
                + "Starting second at -1 is safe only because these are "
                + "counts, never negative, and because hasSecond is checked "
                + "first: when every value is the same there is no "
                + "runner-up, and the program says none instead of "
                + "printing a -1 that looks like data.")
            .sample(Lab.typing("120 4800 95 310 4800"),
                "Requests: 120 4800 95 310 4800",
                "Largest: 4800",
                "Second: 310")
            .hidden(Lab.typing("7 7 7"),
                "Requests: 7 7 7",
                "Largest: 7",
                "Second: none")
            .hidden(Lab.typing("5"),
                "Requests: 5",
                "Largest: 5",
                "Second: none")
            .hidden(Lab.typing("1 2 3 4 5"),
                "Requests: 1 2 3 4 5",
                "Largest: 5",
                "Second: 4")
            .hidden(Lab.typing("9 0"),
                "Requests: 9 0",
                "Largest: 9",
                "Second: 0")
            .hidden(Lab.typing("12 -4 3"),
                "Requests: 12 -4 3",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(19), "CSV Row Parser", Lab.MEDIUM)
            .after("C05-M017")
            .brief(
                "A firewall export arrives as CSV rows: host, port, "
                + "protocol, bytes. Before any row is trusted, every field "
                + "must be checked - the right count, a real port, a known "
                + "protocol, a number where a number belongs. Report the "
                + "FIRST problem found, or the clean fields.")
            .practises("split", "Validating every field", "Returning a reason")
            .spec(
                "Prompt Row: and read one line. Split it at commas and trim each piece.",
                "problem(fields) returns \"\" when the row is good, otherwise the first problem, checked in this order:",
                "not exactly 4 fields: field count; empty host: host; port not all digits or outside 1 to 65535: port; protocol not tcp or udp (any case): protocol; bytes not all digits: bytes.",
                "A problem: print INVALID <problem>. A good row: print Host: , Port: , Proto: (lower case) and Bytes: lines.",
                "isNumber(s) is true for a non-empty String of digits only, at most 9 long.")
            .needsMethod("static String problem(String[])")
            .needsMethod("static boolean isNumber(String)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Row: \");",
                "        String[] f = input.nextLine().split(\",\");",
                "        // trim each field, then ask problem(f) and report",
                "    }",
                "",
                "    // declare problem(String[] f) and isNumber(String s) here",
                "}")
            .hints(
                "problem is a list of guards, each returning early: the "
                + "field count first, because every later check reads f[3].",
                "Port: check isNumber BEFORE Integer.parseInt, then the "
                + "range.",
                "Protocol: f[2].equalsIgnoreCase(\"tcp\") || "
                + "f[2].equalsIgnoreCase(\"udp\").",
                "Only when every guard has passed does it return \"\".")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Row: \");",
                "        String[] f = input.nextLine().split(\",\");",
                "        for (int i = 0; i < f.length; i++) {",
                "            f[i] = f[i].trim();",
                "        }",
                "        String p = problem(f);",
                "        if (!p.isEmpty()) {",
                "            System.out.println(\"INVALID \" + p);",
                "            return;",
                "        }",
                "        System.out.println(\"Host: \" + f[0]);",
                "        System.out.println(\"Port: \" + f[1]);",
                "        System.out.println(\"Proto: \" + f[2].toLowerCase());",
                "        System.out.println(\"Bytes: \" + f[3]);",
                "    }",
                "",
                "    static String problem(String[] f) {",
                "        if (f.length != 4) {",
                "            return \"field count\";",
                "        }",
                "        if (f[0].isEmpty()) {",
                "            return \"host\";",
                "        }",
                "        if (!isNumber(f[1])) {",
                "            return \"port\";",
                "        }",
                "        int port = Integer.parseInt(f[1]);",
                "        if (port < 1 || port > 65535) {",
                "            return \"port\";",
                "        }",
                "        String proto = f[2].toLowerCase();",
                "        if (!proto.equals(\"tcp\") && !proto.equals(\"udp\")) {",
                "            return \"protocol\";",
                "        }",
                "        if (!isNumber(f[3])) {",
                "            return \"bytes\";",
                "        }",
                "        return \"\";",
                "    }",
                "",
                "    static boolean isNumber(String s) {",
                "        if (s.isEmpty() || s.length() > 9) {",
                "            return false;",
                "        }",
                "        for (int i = 0; i < s.length(); i++) {",
                "            if (!Character.isDigit(s.charAt(i))) {",
                "                return false;",
                "            }",
                "        }",
                "        return true;",
                "    }",
                "}")
            .walkthrough(
                "problem is Campaign 03's fail-fast shape applied to an "
                + "array: one guard per rule, in an order where each guard "
                + "makes the next one safe. The count comes first because "
                + "every other check reads a field; isNumber comes before "
                + "parseInt because parseInt crashes on \"80a\"; the length "
                + "limit inside isNumber stops a 20-digit port from "
                + "overflowing int before the range check sees it.\n"
                + "\n"
                + "Returning the reason, rather than true or false, lets "
                + "main say exactly what was wrong - and \"\" for a good "
                + "row keeps the success case simple. Only after every "
                + "guard has passed are the fields printed, and the "
                + "protocol in one agreed case.")
            .sample(Lab.typing("web1, 443, TCP, 5120"),
                "Row: web1, 443, TCP, 5120",
                "Host: web1",
                "Port: 443",
                "Proto: tcp",
                "Bytes: 5120")
            .hidden(Lab.typing("dns1,53,udp,0"),
                "Row: dns1,53,udp,0",
                "Host: dns1",
                "Port: 53",
                "Proto: udp",
                "Bytes: 0")
            .hidden(Lab.typing("web1,443,tcp"),
                "Row: web1,443,tcp",
                "INVALID field count")
            .hidden(Lab.typing(" ,22,tcp,10"),
                "Row:  ,22,tcp,10",
                "INVALID host")
            .hidden(Lab.typing("db1,70000,tcp,10"),
                "Row: db1,70000,tcp,10",
                "INVALID port")
            .hidden(Lab.typing("db1,0,tcp,10"),
                "Row: db1,0,tcp,10",
                "INVALID port")
            .hidden(Lab.typing("db1,80a,tcp,10"),
                "Row: db1,80a,tcp,10",
                "INVALID port")
            .hidden(Lab.typing("db1,5432,icmp,10"),
                "Row: db1,5432,icmp,10",
                "INVALID protocol")
            .hidden(Lab.typing("db1,5432,tcp,-5"),
                "Row: db1,5432,tcp,-5",
                "INVALID bytes")
            .hidden(Lab.typing("db1,99999999999999999999,tcp,1"),
                "Row: db1,99999999999999999999,tcp,1",
                "INVALID port"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(20), "Queue of Tickets", Lab.MEDIUM)
            .stretch()
            .after("C05-M019")
            .brief(
                "The service desk queue: new tickets join the back, urgent "
                + "ones jump to the front, and an analyst takes the next "
                + "ticket from the front. An ArrayList with add, add(0, x) "
                + "and remove(0) is exactly this - as long as nobody takes "
                + "a ticket from an empty queue.")
            .practises("get and set", "ArrayList", "Commands with a free-text argument")
            .spec(
                "Repeatedly prompt > and read a line (trim it) until END.",
                "NEW <title> adds the title at the back; URGENT <title> adds it at the front. The title is everything after the first space, trimmed; an empty title: MALFORMED. Reply Queued: <title>.",
                "NEXT: take the ticket at the front and print Working on: <title> - or Queue empty.",
                "SHOW: print Queue (<size>): followed by the queue as println shows it.",
                "Anything else: UNKNOWN COMMAND.",
                "add(queue, title, urgent) adds a ticket; next(queue) removes and returns the front one.")
            .needsMethod("static void add(ArrayList<String>, String, boolean)")
            .needsMethod("static String next(ArrayList<String>)")
            .starter(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        ArrayList<String> queue = new ArrayList<>();",
                "        // the command loop",
                "    }",
                "",
                "    // declare add(ArrayList<String> q, String t, boolean urgent)",
                "    // and next(ArrayList<String> q) here",
                "}")
            .hints(
                "Split a line into command and title with indexOf(' ') and "
                + "substring - the title may contain spaces.",
                "No space at all: the command is the whole line and the "
                + "title is empty.",
                "add: when urgent, q.add(0, t) puts it at the front; "
                + "otherwise q.add(t) puts it at the back.",
                "next: remove(0) returns the item it took out. Check "
                + "isEmpty() in main before calling it.")
            .solution(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        ArrayList<String> queue = new ArrayList<>();",
                "        while (true) {",
                "            System.out.print(\"> \");",
                "            String line = input.nextLine().trim();",
                "            if (line.equals(\"END\")) {",
                "                break;",
                "            }",
                "            String cmd = line;",
                "            String title = \"\";",
                "            int space = line.indexOf(' ');",
                "            if (space != -1) {",
                "                cmd = line.substring(0, space);",
                "                title = line.substring(space + 1).trim();",
                "            }",
                "            if (cmd.equals(\"NEW\") || cmd.equals(\"URGENT\")) {",
                "                if (title.isEmpty()) {",
                "                    System.out.println(\"MALFORMED\");",
                "                } else {",
                "                    add(queue, title, cmd.equals(\"URGENT\"));",
                "                    System.out.println(\"Queued: \" + title);",
                "                }",
                "            } else if (cmd.equals(\"NEXT\")) {",
                "                if (queue.isEmpty()) {",
                "                    System.out.println(\"Queue empty\");",
                "                } else {",
                "                    System.out.println(\"Working on: \" + next(queue));",
                "                }",
                "            } else if (cmd.equals(\"SHOW\")) {",
                "                System.out.println(\"Queue (\" + queue.size() + \"): \"",
                "                        + queue);",
                "            } else {",
                "                System.out.println(\"UNKNOWN COMMAND\");",
                "            }",
                "        }",
                "    }",
                "",
                "    static void add(ArrayList<String> q, String t, boolean urgent) {",
                "        if (urgent) {",
                "            q.add(0, t);",
                "        } else {",
                "            q.add(t);",
                "        }",
                "    }",
                "",
                "    static String next(ArrayList<String> q) {",
                "        return q.remove(0);",
                "    }",
                "}")
            .walkthrough(
                "The queue is an ArrayList used from both ends: add puts a "
                + "normal ticket at the back and an urgent one at index 0, "
                + "where every other ticket shifts up one place; next uses "
                + "remove(0), which hands back the ticket it took out, so "
                + "one call both reads and removes.\n"
                + "\n"
                + "Titles may contain spaces, so split would cut them up. "
                + "indexOf finds the FIRST space instead: before it is the "
                + "command, after it the whole title. The empty-queue check "
                + "sits in main, before next is called, because remove(0) "
                + "on an empty list throws IndexOutOfBoundsException - an "
                + "analyst pressing NEXT on a quiet day should get an "
                + "answer, not a crash.")
            .sample(Lab.typing("NEW Printer jam", "URGENT Ransomware note", "SHOW", "NEXT", "NEXT", "NEXT", "END"),
                "> NEW Printer jam",
                "Queued: Printer jam",
                "> URGENT Ransomware note",
                "Queued: Ransomware note",
                "> SHOW",
                "Queue (2): [Ransomware note, Printer jam]",
                "> NEXT",
                "Working on: Ransomware note",
                "> NEXT",
                "Working on: Printer jam",
                "> NEXT",
                "Queue empty",
                "> END")
            .hidden(Lab.typing("NEXT", "SHOW", "END"),
                "> NEXT",
                "Queue empty",
                "> SHOW",
                "Queue (0): []",
                "> END")
            .hidden(Lab.typing("NEW", "URGENT   ", "NEW  Disk full on db2  ", "SHOW", "END"),
                "> NEW",
                "MALFORMED",
                "> URGENT",
                "MALFORMED",
                "> NEW  Disk full on db2",
                "Queued: Disk full on db2",
                "> SHOW",
                "Queue (1): [Disk full on db2]",
                "> END")
            .hidden(Lab.typing("NEW a", "NEW b", "URGENT c", "URGENT d", "SHOW", "PURGE", "new e", "END"),
                "> NEW a",
                "Queued: a",
                "> NEW b",
                "Queued: b",
                "> URGENT c",
                "Queued: c",
                "> URGENT d",
                "Queued: d",
                "> SHOW",
                "Queue (4): [d, c, a, b]",
                "> PURGE",
                "UNKNOWN COMMAND",
                "> new e",
                "UNKNOWN COMMAND",
                "> END"));
    }
}
