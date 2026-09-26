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

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(6), "Just the Values", 3)
            .brief(
                "The bandwidth report adds up the bytes each session sent. "
                + "It never asks WHICH session - only how much in total. The "
                + "index i is noise here, and noise is where bugs hide. Java "
                + "has a shorter loop for exactly this.")
            .willLearn("Enhanced for")
            .whyUseful(
                "The enhanced for visits every value in an array with no "
                + "index to set up, compare or get wrong. When you need the "
                + "values and not their positions, it is shorter and safer.")
            .concept("Enhanced for",
                "The ENHANCED FOR (also called for-each) hands you each value "
                + "in turn:\n"
                + "\n"
                + "    for (int count : failures) {\n"
                + "        total += count;\n"
                + "    }\n"
                + "\n"
                + "Read the colon as 'in': for each int count IN failures. "
                + "The first pass, count is failures[0]; the next, "
                + "failures[1]; and so on to the last slot. No i, no length, "
                + "no i++ - so no off-by-one.\n"
                + "\n"
                + "    int count    a new variable, the type of each slot\n"
                + "    :            'in'\n"
                + "    failures     the array to walk through\n"
                + "\n"
                + "It has limits, and they decide when to use it:\n"
                + "\n"
                + "    no position   you never learn which index you are on\n"
                + "    read only     count is a COPY; changing it does not\n"
                + "                  change the array\n"
                + "    all, in order first to last, every slot\n"
                + "\n"
                + "The rule: need only the values? Enhanced for. Need the "
                + "position, need to change slots, or need to skip, step or "
                + "go backwards? The index loop from mission 5.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] failures = {3, 0, 7, 1, 5};",
                "        int total = 0;",
                "        int worst = 0;",
                "        for (int count : failures) {",
                "            total += count;",
                "            if (count > worst) {",
                "                worst = count;",
                "            }",
                "        }",
                "        System.out.println(\"Total: \" + total);",
                "        System.out.println(\"Worst: \" + worst);",
                "    }",
                "}")
            .exampleOutput(
                "Total: 16",
                "Worst: 7")
            .lineByLine(
                new String[]{"for (int count : failures)",
                    "count takes each value of failures in turn: 3, 0, 7, "
                    + "1, 5."},
                new String[]{"total += count;",
                    "The Campaign 04 accumulator - fed a value, not an "
                    + "index."},
                new String[]{"if (count > worst)",
                    "The running maximum, also from Campaign 04. The loop "
                    + "never needed to know where 7 was."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {2, 7, 3};",
                    "int count = 0;",
                    "for (int x : a) {",
                    "    if (x > 2) {",
                    "        count++;",
                    "    }",
                    "}",
                    "System.out.println(count);")
                .accept("2")
                .hints("x is 2, then 7, then 3.",
                       "Which of those are greater than 2?")
                .explain(
                    "2. x takes each value: 2 is not over 2, but 7 and 3 are.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "int[] ports = {22, 80, 443};",
                    "for (String p : ports) {",
                    "    System.out.println(p);",
                    "}")
                .accept("2", "line 2")
                .hints("What type is each slot of ports?",
                       "What type is p declared as?")
                .explain(
                    "Line 2: 'incompatible types: int cannot be converted to "
                    + "String'. The loop variable must have the type of the "
                    + "slots - here, int p.")
                .xp(20))
            .objective(
                "Total the bytes every session sent.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] sizes = {512, 2048, 128, 4096};",
                "        int total = 0;",
                "        // the enhanced for header: each size in sizes",
                "            total += size;",
                "        }",
                "        System.out.println(\"Bytes sent: \" + total);",
                "    }",
                "}")
            .yourTask(
                "Write the enhanced for header that gives each value of "
                + "sizes, one at a time, in an int called size.")
            .mainTask(new Task(Task.WRITE,
                    "Write the enhanced for header.")
                .accept("for (int size : sizes) {",
                        "for(int size : sizes) {",
                        "for (int size: sizes) {",
                        "for (int size : sizes){")
                .hints(
                    "for (type name : array) {",
                    "Each slot is an int; the variable is called size.",
                    "for (int size : sizes) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] sizes = {512, 2048, 128, 4096};",
                    "        int total = 0;",
                    "        for (int size : sizes) {",
                    "            total += size;",
                    "        }",
                    "        System.out.println(\"Bytes sent: \" + total);",
                    "    }",
                    "}")
                .whyItWorks(
                    "size is 512, then 2048, then 128, then 4096, and each "
                    + "is added to total: 6784.\n"
                    + "\n"
                    + "The loop never mentions an index or a length, so there "
                    + "is no boundary to get wrong - and a fifth session "
                    + "needs no change to it.")
                .explain(
                    "for (int size : sizes) { - each int in sizes, in turn.")
                .xp(15))
            .mistakes(
                new String[]{"Assigning to the loop variable",
                    "count = 0; changes the copy, not the array."},
                new String[]{"Writing a[count]",
                    "count is already the value, not an index."},
                new String[]{"Using it when you need the position",
                    "It cannot tell you 'host 3'. Use the index loop."})
            .cyber(
                "Many checks only care about the values: is ANY session over "
                + "the transfer limit, how many requests in total, does any "
                + "entry match the blocklist. The enhanced for fits them "
                + "exactly, and every index a loop does not have is one it "
                + "cannot get wrong. Reviewers favour it for that reason: it "
                + "is obvious at a glance that every entry is checked.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {4, 1, 6};",
                    "for (int x : a) {",
                    "    x = x * 2;",
                    "}",
                    "System.out.println(a[0] + \" \" + a[2]);")
                .accept("4 6")
                .hints("x is a copy of each value.",
                       "Did anything change a[0] or a[2]?")
                .explain(
                    "4 6. Doubling x changed only the copy. The array itself "
                    + "was never touched - changing slots needs a[i] = ...")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which job NEEDS the index loop, not the enhanced for?")
                .choices("Totalling every count",
                         "Printing every username",
                         "Printing 'Host 3: 7' for each host",
                         "Finding the largest count")
                .accept("3", "c")
                .hints("Which one needs to know the position?",
                       "'Host 3' is the index.")
                .explain(
                    "c. The host number is the index, and the enhanced for "
                    + "never tells you the index. The others need only values.")
                .xp(10))
            .recap(
                "    for (int count : failures) {\n"
                + "        ... count ...\n"
                + "    }\n"
                + "\n"
                + "Every value, first to last, no index. count is a copy: "
                + "read it, do not assign to it. Need the position or to "
                + "change slots? Use for (int i = 0; ...).")
            .next("Next: what happens when an index goes too far."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(7), "One Past the End", 4)
            .brief(
                "The overnight report crashed after printing three hosts. "
                + "The trace names ArrayIndexOutOfBoundsException, and the "
                + "loop looks almost right. Almost is the problem: one "
                + "character, < written as <=, asked for a slot that does "
                + "not exist.")
            .willLearn("ArrayIndexOutOfBoundsException")
            .whyUseful(
                "Every Java programmer meets this crash. Knowing what it "
                + "means, reading its message and knowing the three usual "
                + "causes turns it from a mystery into a two-minute fix.")
            .concept("ArrayIndexOutOfBoundsException",
                "An array of length 3 has indexes 0, 1 and 2. Nothing else. "
                + "Java checks EVERY index, every time, and asking for any "
                + "other slot stops the program:\n"
                + "\n"
                + "    int[] a = {3, 0, 7};\n"
                + "    a[3]      crash: past the end\n"
                + "    a[-1]     crash: before the start\n"
                + "\n"
                + "The crash is an EXCEPTION (Campaign 07), named "
                + "ArrayIndexOutOfBoundsException. Its message says which "
                + "index was asked for and how long the array is:\n"
                + "\n"
                + "    Index 3 out of bounds for length 3\n"
                + "\n"
                + "Three causes account for almost all of them:\n"
                + "\n"
                + "    i <= a.length    the loop runs one pass too many\n"
                + "    a[a.length]      the last slot is a[a.length - 1]\n"
                + "    an index from input   a user typed 9 for 5 slots\n"
                + "\n"
                + "The fixes are the ones you know: < length in loops, "
                + "length - 1 for the last slot, and a guard before using "
                + "an index that came from outside:\n"
                + "\n"
                + "    if (i >= 0 && i < a.length) {\n"
                + "        System.out.println(a[i]);\n"
                + "    }\n"
                + "\n"
                + "The trace's 'at' line points at the line that asked, just "
                + "like the stack traces of Campaign 03.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] failures = {3, 0, 7};",
                "        for (int i = 0; i <= failures.length; i++) {",
                "            System.out.println(\"Host \" + i + \": \" + failures[i]);",
                "        }",
                "        System.out.println(\"Report done\");",
                "    }",
                "}")
            .exampleOutput(
                "Host 0: 3",
                "Host 1: 0",
                "Host 2: 7",
                "Exception in thread \"main\" "
                + "java.lang.ArrayIndexOutOfBoundsException: "
                + "Index 3 out of bounds for length 3",
                "        at Main.main(Main.java:5)")
            .lineByLine(
                new String[]{"i <= failures.length",
                    "The bug: i reaches 3, and failures has no slot 3."},
                new String[]{"Index 3 out of bounds for length 3",
                    "What was asked for, and how long the array really is. "
                    + "(Your terminal will wrap this long line.)"},
                new String[]{"at Main.main(Main.java:5)",
                    "Where: line 5, the println that read failures[i]."},
                new String[]{"no 'Report done'",
                    "The crash ended the program, so the rest never ran."})
            .predict(new Task(Task.PREDICT,
                    "Which values appear on screen before the crash?")
                .code(
                    "int[] a = {5, 6};",
                    "for (int i = 0; i <= a.length; i++) {",
                    "    System.out.println(a[i]);",
                    "}")
                .accept("5 6")
                .hints("Slots 0 and 1 exist.",
                       "The crash comes when i is 2.")
                .explain(
                    "5 then 6. When i reaches 2, a[2] does not exist and the "
                    + "program stops before printing anything more.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "int[] hosts = new int[8]; Which of these crashes?")
                .choices("hosts[0]",
                         "hosts[7]",
                         "hosts[8]",
                         "hosts[hosts.length - 1]")
                .accept("3", "c")
                .hints("Indexes run from 0 to length - 1.",
                       "length is 8.")
                .explain(
                    "c. Eight slots are numbered 0 to 7; hosts[8] is one past "
                    + "the end. d is hosts[7], the last real slot.")
                .xp(10))
            .objective(
                "Guard an index that came from the user.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] failures = {3, 0, 7, 1, 5};",
                "        Scanner in = new Scanner(System.in);",
                "        System.out.print(\"Host number: \");",
                "        int host = in.nextInt();",
                "        // the if: host is at least 0 and below failures.length",
                "            System.out.println(\"Failures: \" + failures[host]);",
                "        } else {",
                "            System.out.println(\"No such host\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if that lets host through only when it is a real "
                + "index: at least 0, and less than failures.length.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line.")
                .accept("if (host >= 0 && host < failures.length) {",
                        "if(host >= 0 && host < failures.length) {",
                        "if (host >= 0 && host < failures.length){",
                        "if (host < failures.length && host >= 0) {",
                        "if (0 <= host && host < failures.length) {")
                .hints(
                    "Two conditions, joined by &&.",
                    "host >= 0, and host < failures.length.",
                    "if (host >= 0 && host < failures.length) {")
                .solution(
                    "import java.util.Scanner;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] failures = {3, 0, 7, 1, 5};",
                    "        Scanner in = new Scanner(System.in);",
                    "        System.out.print(\"Host number: \");",
                    "        int host = in.nextInt();",
                    "        if (host >= 0 && host < failures.length) {",
                    "            System.out.println(\"Failures: \" + failures[host]);",
                    "        } else {",
                    "            System.out.println(\"No such host\");",
                    "        }",
                    "    }",
                    "}")
                .input("9")
                .whyItWorks(
                    "Typing 2 prints 'Failures: 7'. Typing 9 or -1 fails the "
                    + "guard and prints 'No such host' - the program carries "
                    + "on instead of crashing.\n"
                    + "\n"
                    + "Both halves matter: without host >= 0, typing -1 would "
                    + "crash; without host < failures.length, typing 5 would.")
                .explain(
                    "if (host >= 0 && host < failures.length) {")
                .xp(20))
            .mistakes(
                new String[]{"Checking only the top",
                    "Negative input crashes too. Check both ends."},
                new String[]{"host <= failures.length",
                    "Lets 5 through for 5 slots: still a crash."},
                new String[]{"Trusting a typed index",
                    "Anything from outside the program needs a guard."})
            .cyber(
                "In C, reading past the end of an array does not stop the "
                + "program - it quietly reads whatever memory is next. "
                + "Heartbleed (2014) was exactly that: a server trusted a "
                + "length sent by the client and returned memory holding "
                + "private keys and passwords. Java's bounds check makes "
                + "that impossible, but a crash is still an outage. Any index "
                + "or length that arrives from outside must be checked "
                + "against the real size before use.")
            .check(new Task(Task.CHOICE,
                    "Which trace message comes from reading a[a.length] on "
                    + "an array of length 4?")
                .choices("Index 3 out of bounds for length 4",
                         "Index 4 out of bounds for length 4",
                         "Index 5 out of bounds for length 4",
                         "Index -1 out of bounds for length 4")
                .accept("2", "b")
                .hints("What is a.length here?",
                       "The index asked for is a.length itself.")
                .explain(
                    "b. a.length is 4, and index 4 is one past the last slot, "
                    + "which is 3.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "This compiles, but crashes. On which line?")
                .code(
                    "int[] a = {1, 2, 3};",
                    "int last = a[a.length - 1];",
                    "int first = a[0];",
                    "int after = a[a.length];",
                    "System.out.println(first + last + after);")
                .accept("4", "line 4")
                .hints("Which index is not between 0 and 2?",
                       "a.length is 3.")
                .explain(
                    "Line 4. a[a.length] is a[3], one past the end. Line 2 "
                    + "is the correct way to reach the last slot.")
                .xp(10))
            .recap(
                "    valid indexes     0 to a.length - 1\n"
                + "    a[a.length]       one past the end: crash\n"
                + "    a[-1]             before the start: crash\n"
                + "\n"
                + "Read the message (which index, what length) and the 'at' "
                + "line. Guard any index from outside with\n"
                + "i >= 0 && i < a.length.")
            .next("Next: arrays of text."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(8), "A List of Names", 3)
            .brief(
                "Identity team wants to know how many service accounts "
                + "exist. Their names all start with svc_, and they are "
                + "spread among ordinary users. The account names are text, "
                + "so the array holding them is a String[] - the same type "
                + "you have written in every main since Campaign 00.")
            .willLearn("Arrays of Strings")
            .whyUseful(
                "Usernames, hostnames, file paths, the fields of a log line: "
                + "most of what a security tool handles is text. A String[] "
                + "holds a batch of it, and every String method you know "
                + "works on each slot.")
            .concept("Arrays of Strings",
                "A String[] works like an int[], with text in the slots:\n"
                + "\n"
                + "    String[] accounts = {\"jsmith\", \"svc_backup\",\n"
                + "            \"admin\"};\n"
                + "\n"
                + "Each slot is a String, so String methods work on it "
                + "directly:\n"
                + "\n"
                + "    accounts[1].length()            10\n"
                + "    accounts[1].startsWith(\"svc_\")  true\n"
                + "    accounts[2].toUpperCase()       ADMIN\n"
                + "\n"
                + "Three things to keep in mind:\n"
                + "\n"
                + "    compare with equals   == compares where the text is,\n"
                + "                          not what it says (Campaign 02)\n"
                + "    new String[n]         every slot starts as null\n"
                + "    null.length()         crashes - fill slots first\n"
                + "\n"
                + "And now String[] args can be explained: main receives an "
                + "array of Strings, one for each word typed after the class "
                + "name on the command line. Run 'java Main alpha beta' and "
                + "args[0] is \"alpha\", args[1] is \"beta\".")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] accounts = {\"jsmith\", \"svc_backup\",",
                "                \"admin\", \"svc_web\", \"mpatel\"};",
                "        int services = 0;",
                "        for (String name : accounts) {",
                "            if (name.startsWith(\"svc_\")) {",
                "                services++;",
                "                System.out.println(\"Service: \" + name);",
                "            }",
                "        }",
                "        System.out.println(\"Service accounts: \" + services",
                "                + \" of \" + accounts.length);",
                "    }",
                "}")
            .exampleOutput(
                "Service: svc_backup",
                "Service: svc_web",
                "Service accounts: 2 of 5")
            .lineByLine(
                new String[]{"String[] accounts = {...};",
                    "Five Strings. The list can continue on the next line, "
                    + "like any long statement."},
                new String[]{"for (String name : accounts)",
                    "The enhanced for, with String as the type of each "
                    + "slot."},
                new String[]{"name.startsWith(\"svc_\")",
                    "An ordinary String method, called on one slot's "
                    + "value."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] hosts = {\"web1\", \"db\", \"fw\"};",
                    "System.out.println(hosts[0].length() + hosts.length);")
                .accept("7")
                .hints("hosts[0].length() counts the characters of \"web1\".",
                       "hosts.length counts the slots.")
                .explain(
                    "7. \"web1\" has 4 characters and the array has 3 slots: "
                    + "4 + 3. Note length() for the String, length for the "
                    + "array.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] names = new String[3];",
                    "names[0] = \"root\";",
                    "int filled = 0;",
                    "for (String n : names) {",
                    "    if (n != null) {",
                    "        filled++;",
                    "    }",
                    "}",
                    "System.out.println(filled);")
                .accept("1")
                .hints("What is in a new String[] slot?",
                       "Only slot 0 was given a value.")
                .explain(
                    "1. Slots 1 and 2 are still null. Checking for null first "
                    + "is what keeps a loop over half-filled text from "
                    + "crashing.")
                .xp(15))
            .objective(
                "Count the accounts on the admin list.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] roles = {\"user\", \"admin\", \"user\",",
                "                \"Admin\", \"guest\"};",
                "        int admins = 0;",
                "        for (String role : roles) {",
                "            // the if: role is admin, whatever its case",
                "                admins++;",
                "            }",
                "        }",
                "        System.out.println(\"Admins: \" + admins);",
                "    }",
                "}")
            .yourTask(
                "Write the if that counts a role when it says admin in any "
                + "mix of upper and lower case.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line.")
                .accept("if (role.equalsIgnoreCase(\"admin\")) {",
                        "if(role.equalsIgnoreCase(\"admin\")) {",
                        "if (role.equalsIgnoreCase(\"admin\")){",
                        "if (\"admin\".equalsIgnoreCase(role)) {")
                .hints(
                    "== is wrong for text. Which method compares contents?",
                    "Case must not matter: equalsIgnoreCase.",
                    "if (role.equalsIgnoreCase(\"admin\")) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String[] roles = {\"user\", \"admin\", \"user\",",
                    "                \"Admin\", \"guest\"};",
                    "        int admins = 0;",
                    "        for (String role : roles) {",
                    "            if (role.equalsIgnoreCase(\"admin\")) {",
                    "                admins++;",
                    "            }",
                    "        }",
                    "        System.out.println(\"Admins: \" + admins);",
                    "    }",
                    "}")
                .whyItWorks(
                    "role is each String in turn. equalsIgnoreCase compares "
                    + "the letters and ignores their case, so both \"admin\" "
                    + "and \"Admin\" count: Admins: 2.\n"
                    + "\n"
                    + "With equals, \"Admin\" would slip past uncounted - an "
                    + "audit that misses an administrator is worse than no "
                    + "audit.")
                .explain(
                    "if (role.equalsIgnoreCase(\"admin\")) {")
                .xp(15))
            .mistakes(
                new String[]{"role == \"admin\"",
                    "Compares where the text is kept, not what it says."},
                new String[]{"Calling a method on a null slot",
                    "new String[n] starts with nulls: check != null first."},
                new String[]{"length() on the array",
                    "Arrays use length; Strings use length()."})
            .cyber(
                "Account audits are String[] work: every username checked "
                + "against a rule, every role compared with a list. Case is a "
                + "classic gap - if one check treats Admin and admin as the "
                + "same account and another does not, an attacker can "
                + "register the variant the second check misses. Decide "
                + "deliberately whether case matters, and use the matching "
                + "method everywhere.")
            .check(new Task(Task.CHOICE,
                    "Which is the right test that slot i of users holds "
                    + "\"root\"?")
                .choices("users[i] == \"root\"",
                         "users[i].equals(\"root\")",
                         "users.equals(\"root\")",
                         "users[i] = \"root\"")
                .accept("2", "b")
                .hints("Text is compared with a method, not ==.",
                       "Compare one slot, not the whole array.")
                .explain(
                    "b. a compares references, c compares the whole array to "
                    + "one String, and d assigns instead of comparing.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] parts = {\"10\", \"0\", \"0\", \"5\"};",
                    "System.out.println(parts[0] + parts[3]);")
                .accept("105")
                .hints("The slots hold text, not numbers.",
                       "+ between Strings joins them.")
                .explain(
                    "105. \"10\" + \"5\" joins text. Adding them as numbers "
                    + "needs Integer.parseInt on each slot first.")
                .xp(10))
            .recap(
                "    String[] names = {\"a\", \"b\"};\n"
                + "    names[i].length()     String methods on a slot\n"
                + "    names[i].equals(x)    compare text, never ==\n"
                + "\n"
                + "new String[n] starts full of null. main's String[] args "
                + "holds the words typed after 'java Main'.")
            .next("Next: passing arrays to methods, and getting them back."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(9), "Arrays In, Arrays Out", 4)
            .brief(
                "Three reports each total an array of counts, each with its "
                + "own copy of the same loop. One of the copies has a bug the "
                + "others do not. Campaign 03's answer applies: write the "
                + "loop once, in a method, and pass the array in.")
            .willLearn("Arrays as parameters")
            .whyUseful(
                "A method that takes an array can analyse any batch - five "
                + "hosts or five thousand - and one that returns an array "
                + "can hand back many results at once. Tested once, used "
                + "everywhere.")
            .concept("Arrays as parameters",
                "An array parameter is declared with its type, like any "
                + "other:\n"
                + "\n"
                + "    static int total(int[] counts) {\n"
                + "        int sum = 0;\n"
                + "        for (int c : counts) {\n"
                + "            sum += c;\n"
                + "        }\n"
                + "        return sum;\n"
                + "    }\n"
                + "\n"
                + "The call passes the array by its name alone - no [ ] and "
                + "no type:\n"
                + "\n"
                + "    total(failures)        right\n"
                + "    total(failures[])      does not compile\n"
                + "    total(int[] failures)  does not compile\n"
                + "\n"
                + "A method can also RETURN an array. Its return type says "
                + "so, and it usually builds a new one inside:\n"
                + "\n"
                + "    static boolean[] over(int[] counts, int limit) {\n"
                + "        boolean[] flags = new boolean[counts.length];\n"
                + "        ...\n"
                + "        return flags;\n"
                + "    }\n"
                + "\n"
                + "A method returns only ONE value - but an array is one "
                + "value holding many, so returning one is how a method "
                + "hands back a whole set of results.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] failures = {3, 0, 7, 1, 5};",
                "        System.out.println(\"Total: \" + total(failures));",
                "        boolean[] flagged = over(failures, 4);",
                "        for (int i = 0; i < flagged.length; i++) {",
                "            if (flagged[i]) {",
                "                System.out.println(\"Review host \" + i);",
                "            }",
                "        }",
                "    }",
                "",
                "    static int total(int[] counts) {",
                "        int sum = 0;",
                "        for (int c : counts) {",
                "            sum += c;",
                "        }",
                "        return sum;",
                "    }",
                "",
                "    static boolean[] over(int[] counts, int limit) {",
                "        boolean[] flags = new boolean[counts.length];",
                "        for (int i = 0; i < counts.length; i++) {",
                "            flags[i] = counts[i] > limit;",
                "        }",
                "        return flags;",
                "    }",
                "}")
            .exampleOutput(
                "Total: 16",
                "Review host 2",
                "Review host 4")
            .lineByLine(
                new String[]{"total(failures)",
                    "The whole array goes in, by name."},
                new String[]{"static boolean[] over(...)",
                    "The return type boolean[] promises an array of "
                    + "booleans back."},
                new String[]{"new boolean[counts.length]",
                    "One flag per count - sized from the input, never "
                    + "typed."},
                new String[]{"flags[i] = counts[i] > limit;",
                    "The comparison is already true or false, so it is "
                    + "stored directly."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] a = {4, 9, 2};",
                    "        System.out.println(first(a) + last(a));",
                    "    }",
                    "",
                    "    static int first(int[] v) {",
                    "        return v[0];",
                    "    }",
                    "",
                    "    static int last(int[] v) {",
                    "        return v[v.length - 1];",
                    "    }",
                    "}")
                .accept("6")
                .hints("first(a) is a[0]; last(a) is a[2].",
                       "Both are ints, so + adds them.")
                .explain(
                    "6. first returns 4 and last returns 2, and two ints "
                    + "joined by + are added.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] counts = {3, 8};",
                    "        System.out.println(total(counts[]));",
                    "    }",
                    "",
                    "    static int total(int[] c) {",
                    "        return c[0] + c[1];",
                    "    }",
                    "}")
                .accept("4", "line 4")
                .hints("How is an array passed to a method?",
                       "By its name alone.")
                .explain(
                    "Line 4. An array is passed by name: total(counts). The "
                    + "[ ] belongs in the parameter's type, not in the call.")
                .xp(20))
            .objective(
                "Write the header of a reusable counting method.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] failures = {3, 0, 7, 1, 5};",
                "        int[] rejects = {9, 12, 2, 6};",
                "        System.out.println(countOver(failures, 4));",
                "        System.out.println(countOver(rejects, 4));",
                "    }",
                "",
                "    // the header: countOver takes counts and a limit",
                "        int over = 0;",
                "        for (int c : counts) {",
                "            if (c > limit) {",
                "                over++;",
                "            }",
                "        }",
                "        return over;",
                "    }",
                "}")
            .yourTask(
                "Write the header: a static method countOver that returns "
                + "an int and takes an int array called counts and an int "
                + "called limit.")
            .mainTask(new Task(Task.WRITE,
                    "Write the method header.")
                .accept("static int countOver(int[] counts, int limit) {",
                        "static int countOver(int[] counts, int limit){",
                        "public static int countOver(int[] counts, "
                        + "int limit) {",
                        "private static int countOver(int[] counts, "
                        + "int limit) {")
                .hints(
                    "static, return type, name, then the parameters.",
                    "The first parameter's type is int[].",
                    "static int countOver(int[] counts, int limit) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] failures = {3, 0, 7, 1, 5};",
                    "        int[] rejects = {9, 12, 2, 6};",
                    "        System.out.println(countOver(failures, 4));",
                    "        System.out.println(countOver(rejects, 4));",
                    "    }",
                    "",
                    "    static int countOver(int[] counts, int limit) {",
                    "        int over = 0;",
                    "        for (int c : counts) {",
                    "            if (c > limit) {",
                    "                over++;",
                    "            }",
                    "        }",
                    "        return over;",
                    "    }",
                    "}")
                .whyItWorks(
                    "The same method answers for two different arrays of "
                    + "different lengths: 2 hosts over 4 failures, then 3 "
                    + "sources over 4 rejects.\n"
                    + "\n"
                    + "Nothing in countOver knows how long the array is, "
                    + "because the enhanced for does not need to. Any array "
                    + "of ints, any limit.")
                .explain(
                    "static int countOver(int[] counts, int limit) {")
                .xp(20))
            .mistakes(
                new String[]{"total(failures[])",
                    "Pass the array by its name alone."},
                new String[]{"Returning int for many results",
                    "Return an array - int[], boolean[] - instead."},
                new String[]{"A fixed size inside the method",
                    "Size new arrays from the parameter's length."})
            .cyber(
                "Detection code is written once and fed many batches: this "
                + "hour's logins, that server's rejects, yesterday's alerts. "
                + "A method taking an array is the unit that gets reviewed "
                + "and tested - including with an empty array, which is a "
                + "real input when a quiet hour produces no events. A total "
                + "of an empty array is 0; code that reads counts[0] without "
                + "checking would crash on the quietest hour of the night.")
            .check(new Task(Task.CHOICE,
                    "Which header returns an array of Strings?")
                .choices("static String names(String[] list)",
                         "static String[] names(String list)",
                         "static void names(String[] list)",
                         "static String names[](String list)")
                .accept("2", "b")
                .hints("The return type comes before the name.",
                       "It must say String[].")
                .explain(
                    "b. The return type, String[], is written before the "
                    + "method name. a takes an array but returns one String.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] r = pair(5);",
                    "        System.out.println(r[0] + \" \" + r[1]);",
                    "    }",
                    "",
                    "    static int[] pair(int n) {",
                    "        return new int[]{n, n * 2};",
                    "    }",
                    "}")
                .accept("5 10")
                .hints("pair builds a 2-slot array.",
                       "The slots hold n and n * 2.")
                .explain(
                    "5 10. The method returns one array holding two "
                    + "results, and main reads both slots.")
                .xp(10))
            .recap(
                "    static int total(int[] counts)     array in\n"
                + "    static int[] build(int n)          array out\n"
                + "    total(failures)                    call: name only\n"
                + "\n"
                + "Size any new array from the input's length, and "
                + "remember an empty array is a real input.")
            .next("Next: the surprise when a method changes an array."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(10), "Two Names, One Array", 4)
            .brief(
                "An analyst made a 'working copy' of the evidence counts to "
                + "experiment on, zeroed a few slots - and the original "
                + "counts changed too. Nobody touched the original. Or so "
                + "they thought: with arrays, = does not copy what you think "
                + "it copies.")
            .willLearn("References")
            .whyUseful(
                "Arrays behave differently from ints when assigned or "
                + "passed to a method. Knowing why explains a whole family "
                + "of bugs - and a useful power: a method can change the "
                + "caller's array.")
            .concept("References",
                "An int variable holds its number. An array variable does "
                + "NOT hold the array - it holds a REFERENCE: where the "
                + "array is kept in memory.\n"
                + "\n"
                + "    int[] original = {3, 0, 7};\n"
                + "    int[] copy = original;\n"
                + "\n"
                + "The second line copies the reference, not the slots. Now "
                + "two names lead to ONE array:\n"
                + "\n"
                + "    original --\\\n"
                + "                 [ 3 | 0 | 7 ]\n"
                + "    copy -----/\n"
                + "\n"
                + "So copy[0] = 99; changes what original[0] reads, too. "
                + "Compare ints: int b = a; copies the number, and changing "
                + "b leaves a alone.\n"
                + "\n"
                + "The same happens when an array is passed to a method: the "
                + "parameter receives a copy of the reference, so the method "
                + "works on the CALLER's array. Changes it makes to slots "
                + "stay after it returns.\n"
                + "\n"
                + "And == on two arrays asks 'the same array?', not 'the "
                + "same contents?' - two arrays holding equal values are "
                + "still !=.\n"
                + "\n"
                + "A real, separate copy needs a new array: mission 11.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] original = {3, 0, 7};",
                "        int[] copy = original;",
                "        copy[0] = 99;",
                "        System.out.println(\"original[0]: \" + original[0]);",
                "        System.out.println(\"Same array: \" + (copy == original));",
                "        int[] twin = {99, 0, 7};",
                "        System.out.println(\"Same as twin: \" + (twin == original));",
                "        clear(original);",
                "        System.out.println(\"After clear: \" + original[2]);",
                "    }",
                "",
                "    static void clear(int[] counts) {",
                "        for (int i = 0; i < counts.length; i++) {",
                "            counts[i] = 0;",
                "        }",
                "    }",
                "}")
            .exampleOutput(
                "original[0]: 99",
                "Same array: true",
                "Same as twin: false",
                "After clear: 0")
            .lineByLine(
                new String[]{"int[] copy = original;",
                    "Copies the reference. One array, two names."},
                new String[]{"copy[0] = 99;",
                    "Changes the one array - so original[0] reads 99."},
                new String[]{"twin == original",
                    "Equal contents, but two separate arrays: false."},
                new String[]{"clear(original);",
                    "The method gets the reference, so it zeroes main's "
                    + "own array."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {1, 2, 3};",
                    "int[] b = a;",
                    "b[1] = 50;",
                    "System.out.println(a[1]);")
                .accept("50")
                .hints("Is b a new array, or another name?",
                       "b = a copied the reference.")
                .explain(
                    "50. a and b are two names for one array, so the change "
                    + "made through b is seen through a.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int x = 5;",
                    "int y = x;",
                    "y = 8;",
                    "int[] p = {5};",
                    "int[] q = p;",
                    "q[0] = 8;",
                    "System.out.println(x + \" \" + p[0]);")
                .accept("5 8")
                .hints("y = x copies a number.",
                       "q = p copies a reference.")
                .explain(
                    "5 8. Changing y leaves x alone, because ints are "
                    + "copied. Changing q[0] changes p's array, because it "
                    + "is the same array.")
                .xp(15))
            .objective(
                "Write a method that resets the caller's counters.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] attempts = {4, 2, 6};",
                "        System.out.println(\"Before: \" + attempts[2]);",
                "        reset(attempts);",
                "        System.out.println(\"After: \" + attempts[2]);",
                "    }",
                "",
                "    static void reset(int[] counts) {",
                "        for (int i = 0; i < counts.length; i++) {",
                "            // set this slot to 0",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the line inside the loop that sets slot i of counts "
                + "to 0. The method returns nothing - the change must reach "
                + "main through the reference.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line inside the loop.")
                .accept("counts[i] = 0;",
                        "counts[i]=0;")
                .hints(
                    "Assign to a slot, not to a copy of its value.",
                    "Slot i of counts.",
                    "counts[i] = 0;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] attempts = {4, 2, 6};",
                    "        System.out.println(\"Before: \" + attempts[2]);",
                    "        reset(attempts);",
                    "        System.out.println(\"After: \" + attempts[2]);",
                    "    }",
                    "",
                    "    static void reset(int[] counts) {",
                    "        for (int i = 0; i < counts.length; i++) {",
                    "            counts[i] = 0;",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "reset(attempts) hands the method a reference to main's "
                    + "array, so counts and attempts are two names for the "
                    + "same slots. Setting counts[i] to 0 zeroes attempts: "
                    + "Before: 6, then After: 0.\n"
                    + "\n"
                    + "An enhanced for could not do this: its variable is a "
                    + "copy of each value, and assigning to it changes "
                    + "nothing.")
                .explain(
                    "counts[i] = 0; - a slot of the caller's own array.")
                .xp(15))
            .mistakes(
                new String[]{"b = a to make a backup",
                    "It is the same array. Change one, change both."},
                new String[]{"a == b to compare contents",
                    "== asks 'same array?'. Compare slot by slot."},
                new String[]{"Surprise changes after a call",
                    "A method given your array can change it."})
            .cyber(
                "Evidence must not change once collected. A 'backup' made "
                + "with = is no backup at all: the first edit to the working "
                + "copy alters the evidence, and nothing records that it "
                + "happened. The same trap hides in methods - a helper that "
                + "'normalises' an array it was given edits the caller's "
                + "data. When the original must be kept, make a real copy "
                + "first (next mission), and work only on that.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] a = {1, 2};",
                    "        change(a);",
                    "        System.out.println(a[0]);",
                    "    }",
                    "",
                    "    static void change(int[] v) {",
                    "        v = new int[]{9, 9};",
                    "    }",
                    "}")
                .accept("1")
                .hints("v = new int[] points v at a NEW array.",
                       "Did anything change a slot of main's array?")
                .explain(
                    "1. v first refers to main's array, but v = new ... "
                    + "points v somewhere else. Only v changed; main's array "
                    + "was never touched. Changing a SLOT, v[0] = 9, would "
                    + "have reached it.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "int[] a = {1, 2}; int[] b = {1, 2}; What is a == b?")
                .choices("true, the contents match",
                         "false, they are two separate arrays",
                         "It does not compile",
                         "It crashes")
                .accept("2", "b")
                .hints("What does == compare for arrays?",
                       "Each { } makes a new array.")
                .explain(
                    "b. == compares references. a and b hold equal values "
                    + "but are two different arrays, so == is false.")
                .xp(10))
            .recap(
                "    int[] b = a;       b is another name for a's array\n"
                + "    a == b             same array? (not same contents)\n"
                + "    method(a)          the method can change a's slots\n"
                + "\n"
                + "Ints are copied; arrays are shared. A real copy needs a "
                + "new array.")
            .next("Next: copying an array properly."));
    }
}
