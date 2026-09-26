/**
 * CAMPAIGN 05 - COLLECTIONS
 * Programs that remember many things at once. Thirty missions.
 *
 * Campaign 04's loops could process a thousand log lines - but only one at
 * a time, forgetting each as the next arrived. Arrays and ArrayList let a
 * program keep them all: every host's count, every user on a watchlist,
 * every field of a line.
 *
 * Classes of your own are Campaign 06, so a mission here may use
 * everything from Campaigns 00 to 04, plus whatever this campaign has
 * already introduced - and nothing else.
 */
public class Campaign05 {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(1), "One Name, Many Values", 3)
            .brief(
                "The morning report tracks failed logins on five hosts, in "
                + "five variables: web1, web2, db1, db2, fw1. Adding a sixth "
                + "host means a new variable and new code in every place that "
                + "totals them. There is a better container.")
            .willLearn("Arrays")
            .whyUseful(
                "Arrays hold many values of one type under one name, in "
                + "numbered slots. A loop can then visit every slot - so the "
                + "same code works for five hosts or five thousand.")
            .concept("Arrays",
                "An ARRAY is a row of numbered slots, all holding the same "
                + "type, under one name:\n"
                + "\n"
                + "    int[] failures = {3, 0, 7, 1, 5};\n"
                + "\n"
                + "    int[]        an array of ints\n"
                + "    failures     its name\n"
                + "    {3, 0, ...}  its starting values, in order\n"
                + "\n"
                + "Each slot has an INDEX, counting from 0 - just like the "
                + "characters of a String:\n"
                + "\n"
                + "    index    0   1   2   3   4\n"
                + "    value    3   0   7   1   5\n"
                + "\n"
                + "failures[2] means 'the slot at index 2' - here, 7. It "
                + "works like a variable: you can read it, print it, or give "
                + "it a new value with failures[2] = 8;\n"
                + "\n"
                + "Two rules to remember from the start:\n"
                + "\n"
                + "    one type     every slot holds the same type\n"
                + "    fixed size   an array never grows or shrinks\n"
                + "\n"
                + "The fixed size is why this campaign also meets ArrayList "
                + "(mission 18): a list that can grow.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] failures = {3, 0, 7, 1, 5};",
                "        System.out.println(\"First host: \" + failures[0]);",
                "        System.out.println(\"Third host: \" + failures[2]);",
                "        failures[2] = 8;",
                "        System.out.println(\"Third host now: \" + failures[2]);",
                "        int total = 0;",
                "        for (int i = 0; i < 5; i++) {",
                "            total += failures[i];",
                "        }",
                "        System.out.println(\"Total: \" + total);",
                "    }",
                "}")
            .exampleOutput(
                "First host: 3",
                "Third host: 7",
                "Third host now: 8",
                "Total: 17")
            .lineByLine(
                new String[]{"int[] failures = {3, 0, 7, 1, 5};",
                    "Five ints, one name. Slots 0 to 4."},
                new String[]{"failures[2] = 8;",
                    "A slot works like a variable: it can be changed."},
                new String[]{"total += failures[i];",
                    "The loop uses i as the index - every slot, one pass "
                    + "each."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {4, 8, 15};",
                    "System.out.println(a[1] + a[2]);")
                .accept("23")
                .hints("Indexes start at 0.",
                       "a[1] is 8 and a[2] is 15.")
                .explain(
                    "23. a[1] is the SECOND value, 8, and a[2] the third, "
                    + "15.")
                .xp(10))
            .practice(new Task(Task.CHOICE,
                    "In  int[] ports = {22, 80, 443};  what is the index of "
                    + "80?")
                .choices("0", "1", "2", "80")
                .accept("2", "b")
                .hints("Counting starts at 0.",
                       "22 is at index 0.")
                .explain(
                    "1. 22 is at index 0, 80 at 1, 443 at 2.")
                .xp(10))
            .objective(
                "Print the third host's failure count.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] failures = {3, 0, 7, 1, 5};",
                "        // print the count for the third host",
                "    }",
                "}")
            .yourTask(
                "Write the line that prints the third host's count - the "
                + "value in the third slot.")
            .mainTask(new Task(Task.WRITE,
                    "Write the println.")
                .accept("System.out.println(failures[2]);")
                .hints(
                    "The third slot is not index 3.",
                    "Counting from 0: first is 0, second 1, third 2.",
                    "System.out.println(failures[2]);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] failures = {3, 0, 7, 1, 5};",
                    "        System.out.println(failures[2]);",
                    "    }",
                    "}")
                .whyItWorks(
                    "failures[2] is the slot at index 2, which is the THIRD "
                    + "value because counting starts at 0. It holds 7.\n"
                    + "\n"
                    + "Mixing up 'the third' with 'index 3' is the array "
                    + "version of the off-by-one error. failures[3] would "
                    + "quietly print 1 - the fourth host's count.")
                .explain(
                    "The third slot is index 2: failures[2].")
                .xp(15))
            .mistakes(
                new String[]{"Counting from 1",
                    "The first slot is index 0."},
                new String[]{"Mixing types",
                    "An int[] holds only ints."},
                new String[]{"Expecting it to grow",
                    "An array's size is fixed when it is made."})
            .cyber(
                "Almost every security tool keeps collections of values: "
                + "counts per host, the last hundred log lines, a list of "
                + "blocked addresses. Arrays are the simplest such "
                + "collection, and the model for all the others.\n"
                + "\n"
                + "Their fixed size also matters for security. In languages "
                + "like C, writing past the end of an array silently "
                + "overwrites other memory - the root of countless exploits. "
                + "Java checks every index, as mission 7 shows.")
            .check(new Task(Task.CHOICE,
                    "What can one int[] array hold?")
                .choices("Any mix of types",
                         "Only ints, in a fixed number of slots",
                         "As many ints as you add later",
                         "One int")
                .accept("2", "b")
                .hints("One type.",
                       "Fixed size.")
                .explain(
                    "Only ints, in a number of slots fixed when the array is "
                    + "created.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {5, 2, 9};",
                    "a[0] = a[2] - a[1];",
                    "System.out.println(a[0]);")
                .accept("7")
                .hints("a[2] is 9 and a[1] is 2.",
                       "The result goes into slot 0.")
                .explain(
                    "7. 9 - 2 is stored in slot 0, replacing the 5.")
                .xp(10))
            .recap(
                "    int[] failures = {3, 0, 7, 1, 5};\n"
                + "    failures[2]        the slot at index 2\n"
                + "\n"
                + "One name, many slots, one type, fixed size. Indexes start "
                + "at 0. A slot is read and written like a variable.")
            .next("Next: creating an empty array of any size."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(2), "Making an Array", 3)
            .brief(
                "The alert counter does not know its values in advance: it "
                + "needs 24 empty slots, one per hour, to fill in as alerts "
                + "arrive. An array can be created empty, at any size - "
                + "including a size worked out while the program runs.")
            .willLearn("Creating arrays")
            .whyUseful(
                "Most arrays are created empty and filled in by a loop or "
                + "from input. Knowing both ways to make one - with values, "
                + "or with a size - covers nearly every case.")
            .concept("Creating arrays",
                "Two ways to create an array:\n"
                + "\n"
                + "    int[] ports = {22, 80, 443};      values known\n"
                + "    int[] hourly = new int[24];       size known\n"
                + "\n"
                + "new int[24] makes an array of 24 slots, numbered 0 to 23. "
                + "The size goes in the brackets and can be any int "
                + "expression - a variable, or a value read from input:\n"
                + "\n"
                + "    int hosts = 50;\n"
                + "    int[] counts = new int[hosts];\n"
                + "\n"
                + "Once created, the size is FIXED. Assigning a new array to "
                + "the variable replaces the whole thing; it does not resize "
                + "the old one.\n"
                + "\n"
                + "The { ... } form only works in the declaration. Later, you "
                + "need new with it:\n"
                + "\n"
                + "    ports = {1, 2};              does not compile\n"
                + "    ports = new int[]{1, 2};     compiles\n"
                + "\n"
                + "Any type can be an array: double[], boolean[], char[], "
                + "String[].")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] hourly = new int[24];",
                "        hourly[9] = 14;",
                "        hourly[10] = 3;",
                "        System.out.println(\"Slots: \" + hourly.length);",
                "        System.out.println(\"09:00 alerts: \" + hourly[9]);",
                "        String[] shift = new String[3];",
                "        shift[0] = \"Adaeze\";",
                "        System.out.println(\"First analyst: \" + shift[0]);",
                "    }",
                "}")
            .exampleOutput(
                "Slots: 24",
                "09:00 alerts: 14",
                "First analyst: Adaeze")
            .lineByLine(
                new String[]{"int[] hourly = new int[24];",
                    "24 empty slots, 0 to 23."},
                new String[]{"hourly[9] = 14;",
                    "Filled in one slot at a time."},
                new String[]{"hourly.length",
                    "Every array knows its size (mission 4)."},
                new String[]{"new String[3]",
                    "Arrays of any type work the same way."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int n = 3;",
                    "int[] a = new int[n * 2];",
                    "a[5] = 9;",
                    "System.out.println(a.length + \" \" + a[5]);")
                .accept("6 9")
                .hints("The size is worked out: 3 * 2.",
                       "Index 5 is the last slot.")
                .explain(
                    "6 9. The array has 6 slots, 0 to 5, and the last one "
                    + "holds 9.")
                .xp(10))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "int[] ports = {22, 80};",
                    "ports = {443, 8443};",
                    "System.out.println(ports[0]);")
                .accept("2", "line 2")
                .hints("Where can the { } form be used?",
                       "Only in a declaration.")
                .explain(
                    "Line 2: 'illegal start of expression'. After the "
                    + "declaration, write ports = new int[]{443, 8443};")
                .xp(20))
            .objective(
                "Create space for ten scores.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // declare scores: a new int array with 10 slots",
                "        scores[9] = 100;",
                "        System.out.println(scores.length + \" slots\");",
                "    }",
                "}")
            .yourTask(
                "Declare scores as a new int array with 10 slots.")
            .mainTask(new Task(Task.WRITE,
                    "Write the declaration.")
                .accept("int[] scores = new int[10];",
                        "int[] scores=new int[10];")
                .hints(
                    "The type is int[].",
                    "new, the element type, and the size in brackets.",
                    "int[] scores = new int[10];")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] scores = new int[10];",
                    "        scores[9] = 100;",
                    "        System.out.println(scores.length + \" slots\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "new int[10] creates ten slots, indexes 0 to 9, so "
                    + "scores[9] - the last slot - exists. The program prints "
                    + "10 slots.\n"
                    + "\n"
                    + "The size is fixed from here on. A program that later "
                    + "needs an eleventh score must create a new, bigger "
                    + "array - or use an ArrayList.")
                .explain(
                    "int[] scores = new int[10]; - ten slots, 0 to 9.")
                .xp(15))
            .mistakes(
                new String[]{"{ } after the declaration",
                    "Use new int[]{ ... } instead."},
                new String[]{"Thinking new int[10] has a slot 10",
                    "It has 0 to 9."},
                new String[]{"Expecting assignment to resize",
                    "It replaces the whole array."})
            .cyber(
                "Array sizes chosen from input need care. A program that "
                + "reads 'how many records follow?' and creates new "
                + "int[count] can be told to allocate billions of slots and "
                + "run out of memory - or be given a negative number, which "
                + "crashes. Checking the size against a sensible maximum "
                + "before creating the array is the same defence as limiting "
                + "a loop.")
            .check(new Task(Task.CHOICE,
                    "What are the valid indexes of new int[5]?")
                .choices("1 to 5", "0 to 5", "0 to 4", "0 to 6")
                .accept("3", "c")
                .hints("Five slots.",
                       "Starting at 0.")
                .explain(
                    "0 to 4 - five slots, counted from 0.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which creates an array of 3 Strings?")
                .choices("String[] s = new String(3);",
                         "String[] s = new String[3];",
                         "String s = new String[3];",
                         "String[3] s = new String[];")
                .accept("2", "b")
                .hints("The type needs [], and so does new.",
                       "The size goes in new's brackets.")
                .explain(
                    "String[] s = new String[3]; - the type is String[], and "
                    + "the size goes in new's brackets.")
                .xp(10))
            .recap(
                "    int[] a = {1, 2, 3};         values known\n"
                + "    int[] b = new int[24];       size known\n"
                + "\n"
                + "The size can be any int expression, but is fixed once "
                + "created. { } only in a declaration; later, new int[]{ }.")
            .next("Next: what is in the slots before you fill them."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(3), "What Is in an Empty Slot?", 4)
            .brief(
                "The hourly counter printed 0 for every hour nobody touched - "
                + "fine. The analyst list printed null, and then the program "
                + "crashed when it tried to capitalise a name that was never "
                + "there. New arrays are not empty; they are full of default "
                + "values.")
            .willLearn("Default values")
            .whyUseful(
                "Knowing what an untouched slot holds explains a whole "
                + "family of bugs - counts that start at 0 correctly, and "
                + "Strings that crash because they were never set.")
            .concept("Default values",
                "When new creates an array, every slot gets a DEFAULT "
                + "value straight away:\n"
                + "\n"
                + "    int, long       0\n"
                + "    double          0.0\n"
                + "    boolean         false\n"
                + "    char            the character with code 0\n"
                + "    String          null\n"
                + "\n"
                + "For counters that is exactly right: new int[24] is 24 "
                + "zeros, ready to count.\n"
                + "\n"
                + "NULL means 'no object here at all' - not an empty String, "
                + "nothing. Printing it shows the word null. But calling a "
                + "method on it - names[0].length() - has nothing to call "
                + "the method on, and the program stops with a "
                + "NullPointerException.\n"
                + "\n"
                + "    String[] names = new String[3];\n"
                + "    System.out.println(names[0]);       prints null\n"
                + "    names[0].length();                  crashes\n"
                + "\n"
                + "Two defences: fill every slot before using it, or check "
                + "names[i] != null before calling a method on it. A null "
                + "check uses ==, because null is not a String to compare "
                + "with equals.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] counts = new int[3];",
                "        double[] rates = new double[2];",
                "        boolean[] locked = new boolean[2];",
                "        String[] names = new String[2];",
                "        names[0] = \"jsmith\";",
                "        System.out.println(counts[0] + \" \" + rates[1]",
                "                + \" \" + locked[0]);",
                "        System.out.println(names[0] + \" \" + names[1]);",
                "        if (names[1] != null) {",
                "            System.out.println(names[1].length());",
                "        } else {",
                "            System.out.println(\"slot 1 is empty\");",
                "        }",
                "    }",
                "}")
            .exampleOutput(
                "0 0.0 false",
                "jsmith null",
                "slot 1 is empty")
            .lineByLine(
                new String[]{"counts[0], rates[1], locked[0]",
                    "Never set: 0, 0.0 and false."},
                new String[]{"names[1]",
                    "Never set: null, which prints as the word null."},
                new String[]{"if (names[1] != null)",
                    "The null check that stops .length() from crashing."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] users = new String[2];",
                    "users[1] = \"admin\";",
                    "System.out.println(users[0] + \"/\" + users[1]);")
                .accept("null/admin")
                .hints("users[0] was never set.",
                       "An unset String slot is null.")
                .explain(
                    "null/admin. Joining null into text shows the word null.")
                .xp(10))
            .practice(new Task(Task.CHOICE,
                    "What is in every slot of  new boolean[4]  ?")
                .choices("true", "false", "null", "Nothing - it is empty")
                .accept("2", "b")
                .hints("Each type has a default.",
                       "Numbers default to 0; booleans to...")
                .explain(
                    "false - the default for boolean.")
                .xp(10))
            .objective(
                "Guard against an empty name slot.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] names = new String[2];",
                "        names[0] = \"jsmith\";",
                "        // the if line: only when names[1] is not null",
                "            System.out.println(names[1].toUpperCase());",
                "        } else {",
                "            System.out.println(\"no second user\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line: true only when names[1] holds a String - "
                + "that is, is not null.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line.")
                .accept("if (names[1] != null) {", "if(names[1] != null) {",
                        "if (names[1] != null){", "if (null != names[1]) {")
                .hints(
                    "null is compared with ==, or != for 'not'.",
                    "No equals - there may be no String to call it on.",
                    "if (names[1] != null) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String[] names = new String[2];",
                    "        names[0] = \"jsmith\";",
                    "        if (names[1] != null) {",
                    "            System.out.println(names[1].toUpperCase());",
                    "        } else {",
                    "            System.out.println(\"no second user\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "names[1] was never set, so it is null, the condition is "
                    + "false, and the program prints no second user instead "
                    + "of crashing.\n"
                    + "\n"
                    + "names[1].equals(null) would not work: with null in the "
                    + "slot, there is no object to call equals on, and that "
                    + "call itself would crash.")
                .explain(
                    "if (names[1] != null) { - check before calling methods.")
                .xp(20))
            .mistakes(
                new String[]{"Calling a method on a null slot",
                    "NullPointerException. Check first."},
                new String[]{"Comparing with equals(null)",
                    "Use == null or != null."},
                new String[]{"Confusing null with \"\"",
                    "\"\" is an empty String; null is no String at all."})
            .cyber(
                "NullPointerException is one of the most common crashes in "
                + "Java, and a crash is a denial of service. Input that "
                + "leaves an expected field empty - a missing header, a "
                + "missing user name - can reach code that assumes it is "
                + "there. Defensive code checks for null at the boundary, "
                + "exactly where it validates everything else.")
            .check(new Task(Task.CHOICE,
                    "names is new String[3] and nothing has been assigned. "
                    + "What happens on  names[0].length()  ?")
                .choices("It returns 0", "It returns 4",
                         "NullPointerException - the program crashes",
                         "It does not compile")
                .accept("3", "c")
                .hints("What is in names[0]?",
                       "Can you call a method on nothing?")
                .explain(
                    "It compiles, then crashes with NullPointerException: "
                    + "names[0] is null.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] c = new int[3];",
                    "c[1]++;",
                    "c[1]++;",
                    "System.out.println(c[0] + c[1] + c[2]);")
                .accept("2")
                .hints("The slots start at 0.",
                       "Only c[1] is changed.")
                .explain(
                    "2. All three start at 0; c[1] is counted up twice.")
                .xp(10))
            .recap(
                "New arrays are filled with defaults: 0, 0.0, false - and "
                + "null for Strings. null prints as null, but calling a "
                + "method on it crashes. Check with != null before using a "
                + "String slot that might be empty.")
            .next("Next: every array's length, and its last index."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(4), "How Long Is It?", 3)
            .brief(
                "The report code hard-codes 5 everywhere - five hosts, loops "
                + "to 5, the last host at index 4. When the sixth host "
                + "arrives, three of those 5s get updated and one does not. "
                + "Every array already knows its own size.")
            .willLearn("Array length")
            .whyUseful(
                "Using length instead of a typed number means loops and "
                + "indexes follow the array automatically - add a host and "
                + "every loop covers it.")
            .concept("Array length",
                "Every array has a LENGTH: the number of slots it was "
                + "created with.\n"
                + "\n"
                + "    int[] failures = {3, 0, 7, 1, 5};\n"
                + "    failures.length          5\n"
                + "\n"
                + "Note: NO brackets. For an array it is length; for a "
                + "String it is length(). Mixing them up does not compile:\n"
                + "\n"
                + "    failures.length()     error - arrays have no\n"
                + "                          length method\n"
                + "    text.length           error - Strings do\n"
                + "\n"
                + "The last index is always length - 1:\n"
                + "\n"
                + "    failures[failures.length - 1]     the last slot\n"
                + "\n"
                + "That expression is right for any size, which is the "
                + "point: write length, not 5, and the code keeps working "
                + "when the array changes.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] failures = {3, 0, 7, 1, 5};",
                "        System.out.println(\"Hosts: \" + failures.length);",
                "        System.out.println(\"Last: \" + failures[failures.length - 1]);",
                "        String host = \"web-01\";",
                "        System.out.println(\"Name length: \" + host.length());",
                "    }",
                "}")
            .exampleOutput(
                "Hosts: 5",
                "Last: 5",
                "Name length: 6")
            .lineByLine(
                new String[]{"failures.length",
                    "An array's size: no brackets."},
                new String[]{"failures[failures.length - 1]",
                    "The last slot, whatever the size."},
                new String[]{"host.length()",
                    "A String's size: brackets. The difference matters."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {9, 4, 6, 2};",
                    "System.out.println(a[a.length - 1] + a.length);")
                .accept("6")
                .hints("The last value is 2.",
                       "The length is 4.")
                .explain(
                    "6. a[3] is 2, and a.length is 4.")
                .xp(10))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "int[] ports = {22, 80, 443};",
                    "String name = \"fw\";",
                    "System.out.println(name.length());",
                    "System.out.println(ports.length());")
                .accept("4", "line 4")
                .hints("Which one is an array?",
                       "Arrays use length without brackets.")
                .explain(
                    "Line 4. ports is an array, so it is ports.length - no "
                    + "brackets. Line 3 is right: Strings use length().")
                .xp(15))
            .objective(
                "Read the most recent reading, whatever the array's size.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] readings = {41, 43, 40, 47, 52};",
                "        // declare last: the value in the final slot",
                "        System.out.println(\"Latest: \" + last);",
                "    }",
                "}")
            .yourTask(
                "Declare last, holding the value in readings' final slot - "
                + "worked out from length, not typed as 4.")
            .mainTask(new Task(Task.WRITE,
                    "Write the declaration.")
                .accept("int last = readings[readings.length - 1];")
                .hints(
                    "The last index is length - 1.",
                    "Put that inside the brackets.",
                    "int last = readings[readings.length - 1];")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] readings = {41, 43, 40, 47, 52};",
                    "        int last = readings[readings.length - 1];",
                    "        System.out.println(\"Latest: \" + last);",
                    "    }",
                    "}")
                .whyItWorks(
                    "readings.length is 5, so the index is 4, the final "
                    + "slot, holding 52.\n"
                    + "\n"
                    + "Tomorrow the array may hold 500 readings. readings[4] "
                    + "would then be a very old reading; readings[readings."
                    + "length - 1] is still the latest.")
                .explain(
                    "readings[readings.length - 1] - the last slot, any "
                    + "size.")
                .xp(15))
            .mistakes(
                new String[]{"length() on an array",
                    "Arrays use length, no brackets."},
                new String[]{"array[array.length]",
                    "One past the end - the last index is length - 1."},
                new String[]{"Typed sizes",
                    "Use length so code follows the array."})
            .cyber(
                "Hard-coded sizes are a classic source of both bugs and "
                + "vulnerabilities: a buffer sized for 5 entries meets input "
                + "with 6. In Java the result is a crash rather than memory "
                + "corruption, but a crash in a security service is still an "
                + "outage. Deriving every bound from length keeps code and "
                + "data in step.")
            .check(new Task(Task.CHOICE,
                    "What is the last valid index of an array of length 10?")
                .choices("10", "9", "11", "0")
                .accept("2", "b")
                .hints("Indexes start at 0.",
                       "length - 1.")
                .explain(
                    "9. Indexes run from 0 to length - 1, so 10 slots end at 9.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which is correct for an array called hosts?")
                .choices("hosts.length()", "hosts.length", "hosts.size()",
                         "length(hosts)")
                .accept("2", "b")
                .hints("Arrays are not Strings.",
                       "No brackets.")
                .explain(
                    "hosts.length. size() belongs to ArrayList (mission 19).")
                .xp(10))
            .recap(
                "    array.length          the number of slots (no ())\n"
                + "    array[array.length - 1]   the last slot\n"
                + "\n"
                + "Strings use length(). Derive bounds from length, never "
                + "from a typed number.")
            .next("Next: a loop over every slot."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(5), "Every Slot, One Loop", 4)
            .brief(
                "Five hosts, then fifty, then five hundred: the report must "
                + "print every host's count and the total, whatever the "
                + "size. One for loop over the indexes does it - the "
                + "Campaign 04 loop you already know, with length as its "
                + "limit.")
            .willLearn("Looping over arrays")
            .whyUseful(
                "Loops and arrays are made for each other. Summing, "
                + "printing, searching, counting - all of this campaign's "
                + "patterns start with a loop over every slot.")
            .concept("Looping over arrays",
                "To visit every slot, loop over the indexes 0 to length - 1:\n"
                + "\n"
                + "    for (int i = 0; i < failures.length; i++) {\n"
                + "        System.out.println(\"Host \" + i + \": \"\n"
                + "                + failures[i]);\n"
                + "    }\n"
                + "\n"
                + "It is exactly the String loop from Campaign 04, with "
                + "failures[i] instead of text.charAt(i), and length instead "
                + "of length().\n"
                + "\n"
                + "    start 0              the first slot\n"
                + "    i < failures.length  stop before length\n"
                + "    i++                  every slot, in order\n"
                + "\n"
                + "Inside the loop, i is the POSITION and failures[i] is the "
                + "VALUE. Many programs need both: 'host 2 has 7 failures'.\n"
                + "\n"
                + "The Campaign 04 patterns all apply: an accumulator for a "
                + "total, a counter for matches, a running maximum - each "
                + "reading failures[i] once per pass.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] failures = {3, 0, 7, 1, 5};",
                "        int total = 0;",
                "        for (int i = 0; i < failures.length; i++) {",
                "            System.out.println(\"Host \" + i + \": \" + failures[i]);",
                "            total += failures[i];",
                "        }",
                "        System.out.println(\"Total: \" + total);",
                "    }",
                "}")
            .exampleOutput(
                "Host 0: 3",
                "Host 1: 0",
                "Host 2: 7",
                "Host 3: 1",
                "Host 4: 5",
                "Total: 16")
            .lineByLine(
                new String[]{"i < failures.length",
                    "Every index, 0 to 4 - and still right when the array "
                    + "grows."},
                new String[]{"\"Host \" + i",
                    "i is the position..."},
                new String[]{"failures[i]",
                    "...and failures[i] is the value there."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {2, 5, 1, 8};",
                    "int sum = 0;",
                    "for (int i = 0; i < a.length; i += 2) {",
                    "    sum += a[i];",
                    "}",
                    "System.out.println(sum);")
                .accept("3")
                .hints("i goes 0, 2.",
                       "a[0] + a[2].")
                .explain(
                    "3. Stepping by 2 visits indexes 0 and 2: 2 + 1.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Which loop visits every slot of an array a exactly "
                    + "once?")
                .choices("for (int i = 1; i <= a.length; i++)",
                         "for (int i = 0; i < a.length; i++)",
                         "for (int i = 0; i <= a.length; i++)",
                         "for (int i = 0; i < a.length - 1; i++)")
                .accept("2", "b")
                .hints("Start at 0.",
                       "Stop before length.")
                .explain(
                    "b. a starts at 1 and goes one past the end; c goes one "
                    + "past the end; d misses the last slot.")
                .xp(10))
            .objective(
                "Loop over every host's count.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] counts = {12, 4, 9};",
                "        // the for header: every index of counts",
                "            System.out.println(\"Host \" + i + \": \" + counts[i]);",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the for header that visits every index of counts, "
                + "using length.")
            .mainTask(new Task(Task.WRITE,
                    "Write the for header.")
                .accept("for (int i = 0; i < counts.length; i++) {",
                        "for(int i = 0; i < counts.length; i++) {",
                        "for (int i = 0; i < counts.length; i++){")
                .hints(
                    "Start at 0.",
                    "Stop before counts.length.",
                    "for (int i = 0; i < counts.length; i++) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] counts = {12, 4, 9};",
                    "        for (int i = 0; i < counts.length; i++) {",
                    "            System.out.println(\"Host \" + i + \": \" + counts[i]);",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "i runs 0, 1, 2 - every index of a 3-slot array - and "
                    + "each pass prints the position and the value.\n"
                    + "\n"
                    + "Because the limit is counts.length, adding a fourth "
                    + "host to the array needs no change to the loop.")
                .explain(
                    "for (int i = 0; i < counts.length; i++) {")
                .xp(15))
            .mistakes(
                new String[]{"<= length",
                    "One past the end: a crash (mission 7)."},
                new String[]{"Starting at 1",
                    "Skips the first slot."},
                new String[]{"Confusing i with a[i]",
                    "i is where; a[i] is what is there."})
            .cyber(
                "A loop over an array is the heart of batch analysis: every "
                + "reading, every count, every host. Most detection logic in "
                + "this campaign is a loop like this with a different body - "
                + "summing, searching, flagging. Writing the header from "
                + "length, never a typed size, is what keeps that analysis "
                + "complete when the data grows.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {3, 9, 4};",
                    "int big = 0;",
                    "for (int i = 0; i < a.length; i++) {",
                    "    if (a[i] > 5) {",
                    "        big++;",
                    "    }",
                    "}",
                    "System.out.println(big);")
                .accept("1")
                .hints("Which values are over 5?",
                       "Only 9.")
                .explain(
                    "1. Only a[1], which holds 9, is greater than 5; 3 and 4 fail.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {1, 2, 3};",
                    "for (int i = a.length - 1; i >= 0; i--) {",
                    "    System.out.print(a[i]);",
                    "}",
                    "System.out.println();")
                .accept("321")
                .hints("The loop starts at the last index.",
                       "print keeps them on one line.")
                .explain(
                    "321 - the same slots, visited backwards.")
                .xp(10))
            .recap(
                "    for (int i = 0; i < a.length; i++) {\n"
                + "        ... a[i] ...\n"
                + "    }\n"
                + "\n"
                + "i is the position, a[i] the value. Totals, counts and "
                + "maximums from Campaign 04 all work inside this loop.")
            .next("Next: a shorter loop when you only need the values."));
    }
}
