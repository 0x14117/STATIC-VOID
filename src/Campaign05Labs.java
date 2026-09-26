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
    }
}
