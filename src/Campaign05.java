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

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(11), "A Real Copy", 3)
            .brief(
                "After mission 10, the analyst wants a working copy that "
                + "really is separate: change it freely, and the evidence "
                + "counts stay exactly as collected. That takes a second "
                + "array with its own slots - and a quick way to print both "
                + "and prove it.")
            .willLearn("Copying arrays", "Arrays.toString")
            .whyUseful(
                "A true copy lets a program experiment, sort or clean data "
                + "without destroying the original. Arrays.toString shows a "
                + "whole array on one line, which makes checking your work "
                + "- and debugging - far quicker.")
            .concept("Copying arrays",
                "A real copy is a NEW array, filled slot by slot:\n"
                + "\n"
                + "    int[] copy = new int[original.length];\n"
                + "    for (int i = 0; i < original.length; i++) {\n"
                + "        copy[i] = original[i];\n"
                + "    }\n"
                + "\n"
                + "Now there are two arrays. Changing copy[0] leaves "
                + "original[0] alone.\n"
                + "\n"
                + "Java's library does the same in one line. It lives in the "
                + "class Arrays, which needs an import, like Scanner:\n"
                + "\n"
                + "    import java.util.Arrays;\n"
                + "\n"
                + "    int[] copy = Arrays.copyOf(original, original.length);\n"
                + "\n"
                + "The second argument is the new length. Shorter keeps only "
                + "the first slots; longer adds default-valued slots at the "
                + "end - the usual way to 'grow' an array.\n"
                + "\n"
                + "Two more from the same class:\n"
                + "\n"
                + "    Arrays.toString(a)    \"[3, 0, 7]\" - for printing\n"
                + "    Arrays.equals(a, b)   same length, same values?\n"
                + "\n"
                + "Printing an array directly shows only a code like "
                + "[I@1b6d3586 - where it is, not what it holds. Use "
                + "Arrays.toString.")
            .example(
                "import java.util.Arrays;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] evidence = {3, 0, 7};",
                "        int[] work = Arrays.copyOf(evidence, evidence.length);",
                "        work[0] = 0;",
                "        System.out.println(\"Evidence: \" + Arrays.toString(evidence));",
                "        System.out.println(\"Working:  \" + Arrays.toString(work));",
                "        System.out.println(\"Same array: \" + (work == evidence));",
                "        int[] longer = Arrays.copyOf(evidence, 5);",
                "        System.out.println(\"Longer:   \" + Arrays.toString(longer));",
                "    }",
                "}")
            .exampleOutput(
                "Evidence: [3, 0, 7]",
                "Working:  [0, 0, 7]",
                "Same array: false",
                "Longer:   [3, 0, 7, 0, 0]")
            .lineByLine(
                new String[]{"Arrays.copyOf(evidence, evidence.length)",
                    "A new array, same length, same values."},
                new String[]{"work[0] = 0;",
                    "Changes only the copy. Evidence still starts with 3."},
                new String[]{"Arrays.toString(evidence)",
                    "The whole array as text: [3, 0, 7]."},
                new String[]{"Arrays.copyOf(evidence, 5)",
                    "Two extra slots at the end, holding the default 0."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {5, 6};",
                    "int[] b = new int[a.length];",
                    "for (int i = 0; i < a.length; i++) {",
                    "    b[i] = a[i];",
                    "}",
                    "b[1] = 60;",
                    "System.out.println(a[1] + \" \" + b[1]);")
                .accept("6 60")
                .hints("b is a new array.",
                       "Changing b does not reach a.")
                .explain(
                    "6 60. The loop copied the values into a separate array, "
                    + "so b[1] = 60 leaves a[1] as 6.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "int[] a = {1, 2}; int[] b = {1, 2}; Which is true?")
                .choices("a == b",
                         "Arrays.equals(a, b)",
                         "a.equals(b) compares the values",
                         "None of them")
                .accept("2", "b")
                .hints("== asks 'same array?'.",
                       "Arrays has a method that compares values.")
                .explain(
                    "b. Arrays.equals compares length and every slot. == and "
                    + "a.equals(b) both only ask whether a and b are the same "
                    + "array.")
                .xp(10))
            .objective(
                "Keep the evidence safe while cleaning a copy.")
            .starter(
                "import java.util.Arrays;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] evidence = {4, -1, 9, -1, 2};",
                "        // work: a real copy of evidence, the same length",
                "        for (int i = 0; i < work.length; i++) {",
                "            if (work[i] < 0) {",
                "                work[i] = 0;",
                "            }",
                "        }",
                "        System.out.println(Arrays.toString(evidence));",
                "        System.out.println(Arrays.toString(work));",
                "    }",
                "}")
            .yourTask(
                "Declare work as a real copy of evidence, using "
                + "Arrays.copyOf, so cleaning work leaves evidence as it was.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line that declares work.")
                .accept("int[] work = Arrays.copyOf(evidence, evidence.length);",
                        "int[] work=Arrays.copyOf(evidence, evidence.length);",
                        "int[] work = Arrays.copyOf(evidence,evidence.length);",
                        "int[] work = Arrays.copyOf(evidence, 5);")
                .hints(
                    "Arrays.copyOf takes the array and the new length.",
                    "The length should match: evidence.length.",
                    "int[] work = Arrays.copyOf(evidence, evidence.length);")
                .solution(
                    "import java.util.Arrays;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] evidence = {4, -1, 9, -1, 2};",
                    "        int[] work = Arrays.copyOf(evidence, evidence.length);",
                    "        for (int i = 0; i < work.length; i++) {",
                    "            if (work[i] < 0) {",
                    "                work[i] = 0;",
                    "            }",
                    "        }",
                    "        System.out.println(Arrays.toString(evidence));",
                    "        System.out.println(Arrays.toString(work));",
                    "    }",
                    "}")
                .whyItWorks(
                    "copyOf builds a second array with the same five values. "
                    + "The loop replaces the -1 readings in work only, so "
                    + "the output is [4, -1, 9, -1, 2] and then "
                    + "[4, 0, 9, 0, 2].\n"
                    + "\n"
                    + "With int[] work = evidence; both lines would show the "
                    + "cleaned values - and the original readings would be "
                    + "gone.")
                .explain(
                    "int[] work = Arrays.copyOf(evidence, evidence.length);")
                .xp(15))
            .mistakes(
                new String[]{"int[] copy = original;",
                    "Another name, not a copy (mission 10)."},
                new String[]{"Printing an array directly",
                    "Shows [I@... - use Arrays.toString."},
                new String[]{"Forgetting the import",
                    "Arrays needs import java.util.Arrays;"})
            .cyber(
                "Forensics has a rule: never work on the original. "
                + "Investigators image a disk and analyse the copy, so the "
                + "evidence can be shown in court exactly as found. The same "
                + "habit in code - copy first, clean the copy - means a "
                + "mistake in the cleaning can always be undone, and the raw "
                + "data is there to check the result against.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {7, 8, 9};",
                    "int[] b = java.util.Arrays.copyOf(a, 2);",
                    "System.out.println(java.util.Arrays.toString(b));")
                .accept("[7, 8]")
                .hints("The new length is 2.",
                       "Only the first two slots are kept.")
                .explain(
                    "[7, 8]. A shorter length keeps the first slots and drops "
                    + "the rest. (java.util.Arrays is the full name, used "
                    + "here instead of an import.)")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "What does System.out.println(a) show for an int[] a?")
                .choices("The values, like [1, 2, 3]",
                         "A code like [I@1b6d3586",
                         "Nothing",
                         "It does not compile")
                .accept("2", "b")
                .hints("println does not know how to list an array.",
                       "It shows the type and where the array is.")
                .explain(
                    "b. The code means 'int array, at this place'. To see "
                    + "the values, print Arrays.toString(a).")
                .xp(10))
            .recap(
                "    import java.util.Arrays;\n"
                + "    Arrays.copyOf(a, a.length)   a real, separate copy\n"
                + "    Arrays.copyOf(a, n)          shorter or longer copy\n"
                + "    Arrays.toString(a)           [1, 2, 3]\n"
                + "    Arrays.equals(a, b)          same values?\n"
                + "\n"
                + "Copy first, then change the copy.")
            .next("Next: finding a value in an array."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(12), "Is It on the List?", 4)
            .brief(
                "Before a connection is allowed, the gateway asks one "
                + "question: is this address on the blocklist? The list is "
                + "an array; the answer means looking at each entry until "
                + "one matches - or the list runs out.")
            .willLearn("Linear search")
            .whyUseful(
                "Searching is the most common thing done with an array: "
                + "is this user on the watchlist, which slot holds this "
                + "host, does any entry match. The pattern is short, and "
                + "getting its two exits right is the whole skill.")
            .concept("Linear search",
                "A LINEAR SEARCH checks each slot in turn, from the first, "
                + "and stops at the first match:\n"
                + "\n"
                + "    static int indexOf(String[] list, String target) {\n"
                + "        for (int i = 0; i < list.length; i++) {\n"
                + "            if (list[i].equals(target)) {\n"
                + "                return i;       found: stop now\n"
                + "            }\n"
                + "        }\n"
                + "        return -1;              checked all: none\n"
                + "    }\n"
                + "\n"
                + "Two exits, and their places matter:\n"
                + "\n"
                + "    return i     INSIDE the if - the first match ends it\n"
                + "    return -1    AFTER the loop - only once every slot\n"
                + "                 has been checked and none matched\n"
                + "\n"
                + "-1 means 'not found' because it can never be a real "
                + "index - the same promise String's indexOf makes.\n"
                + "\n"
                + "When only yes or no matters, return a boolean instead: "
                + "true inside the if, false after the loop.\n"
                + "\n"
                + "The classic bug is an else inside the loop that returns "
                + "false (or -1). Then the method gives up after checking "
                + "only the FIRST slot.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] blocked = {\"10.0.0.66\", \"172.16.4.9\",",
                "                \"192.168.7.13\"};",
                "        System.out.println(isBlocked(blocked, \"192.168.7.13\"));",
                "        System.out.println(isBlocked(blocked, \"10.0.0.5\"));",
                "        System.out.println(indexOf(blocked, \"172.16.4.9\"));",
                "    }",
                "",
                "    static boolean isBlocked(String[] list, String ip) {",
                "        for (String entry : list) {",
                "            if (entry.equals(ip)) {",
                "                return true;",
                "            }",
                "        }",
                "        return false;",
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
            .exampleOutput(
                "true",
                "false",
                "1")
            .lineByLine(
                new String[]{"return true;",
                    "The first match ends the search at once."},
                new String[]{"return false;",
                    "Reached only after every entry was checked."},
                new String[]{"return i;",
                    "When the position matters too, return the index."},
                new String[]{"return -1;",
                    "Never a real index, so it can only mean 'not found'."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] ports = {22, 80, 443, 80};",
                    "int at = -1;",
                    "for (int i = 0; i < ports.length; i++) {",
                    "    if (ports[i] == 80) {",
                    "        at = i;",
                    "        break;",
                    "    }",
                    "}",
                    "System.out.println(at);")
                .accept("1")
                .hints("break stops the loop at the first match.",
                       "Where is the first 80?")
                .explain(
                    "1. The first 80 is at index 1, and break stops the loop "
                    + "before it reaches the second 80 at index 3.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String[] w = {\"guest\", \"root\"};",
                    "        System.out.println(has(w, \"root\"));",
                    "    }",
                    "",
                    "    static boolean has(String[] list, String t) {",
                    "        for (String s : list) {",
                    "            if (s.equals(t)) {",
                    "                return true;",
                    "            } else {",
                    "                return false;",
                    "            }",
                    "        }",
                    "        return false;",
                    "    }",
                    "}")
                .accept("false")
                .hints("What happens on the very first pass?",
                       "\"guest\" does not match - and the else returns.")
                .explain(
                    "false - although root IS on the list. The else returns "
                    + "after checking only \"guest\". 'Not found' can only be "
                    + "decided after the loop.")
                .xp(20))
            .objective(
                "Finish the watchlist search.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] watch = {\"svc_old\", \"temp01\", \"jdoe\"};",
                "        System.out.println(onWatch(watch, \"jdoe\"));",
                "        System.out.println(onWatch(watch, \"asmith\"));",
                "    }",
                "",
                "    static boolean onWatch(String[] list, String user) {",
                "        for (String name : list) {",
                "            if (name.equals(user)) {",
                "                return true;",
                "            }",
                "        }",
                "        // the answer when no name matched",
                "    }",
                "}")
            .yourTask(
                "Write the line that runs after the loop - once every name "
                + "has been checked and none matched.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line after the loop.")
                .accept("return false;")
                .hints(
                    "The loop found no match. What is the answer?",
                    "The method returns a boolean.",
                    "return false;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String[] watch = {\"svc_old\", \"temp01\", \"jdoe\"};",
                    "        System.out.println(onWatch(watch, \"jdoe\"));",
                    "        System.out.println(onWatch(watch, \"asmith\"));",
                    "    }",
                    "",
                    "    static boolean onWatch(String[] list, String user) {",
                    "        for (String name : list) {",
                    "            if (name.equals(user)) {",
                    "                return true;",
                    "            }",
                    "        }",
                    "        return false;",
                    "    }",
                    "}")
                .whyItWorks(
                    "jdoe matches on the third pass, so return true ends the "
                    + "method. asmith matches nothing: the loop finishes, and "
                    + "only then does return false run. Output: true, then "
                    + "false.\n"
                    + "\n"
                    + "Without that line the method does not compile - Java "
                    + "insists every path through a non-void method returns "
                    + "something.")
                .explain(
                    "return false; - after the loop, once every name failed.")
                .xp(15))
            .mistakes(
                new String[]{"else { return false; } in the loop",
                    "Gives up after the first slot."},
                new String[]{"== to compare the Strings",
                    "Use equals, or matches are missed."},
                new String[]{"Returning 0 for 'not found'",
                    "0 is a real index. Use -1."})
            .cyber(
                "Blocklists, watchlists and revoked-certificate lists are "
                + "all searched this way. The early-exit bug is a security "
                + "bug: a check that only compares the first entry reports "
                + "'not blocked' for everything else on the list, and "
                + "nothing looks wrong until a blocked address gets in. "
                + "Test a search with a match first, last, in the middle "
                + "and absent.")
            .check(new Task(Task.CHOICE,
                    "Where must 'return -1;' go in a linear search?")
                .choices("Inside the if",
                         "In an else inside the loop",
                         "After the loop",
                         "Before the loop")
                .accept("3", "c")
                .hints("When do you KNOW the value is not there?",
                       "Only after every slot has been checked.")
                .explain(
                    "c. After the loop is the only place every slot has "
                    + "already been checked. Anywhere earlier decides too "
                    + "soon.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "To test a search method, which set of cases is best?")
                .choices("One match in the middle",
                         "Match at the start, the end, and not present",
                         "Only a value that is not present",
                         "The same value three times")
                .accept("2", "b")
                .hints("Bugs hide at the edges.",
                       "And in the 'not found' path.")
                .explain(
                    "b. The first slot, the last slot and 'absent' are where "
                    + "search bugs live: early exits, off-by-one limits and a "
                    + "wrong 'not found' answer.")
                .xp(10))
            .recap(
                "    for each slot:\n"
                + "        if it matches: return it (or true)\n"
                + "    after the loop: return -1 (or false)\n"
                + "\n"
                + "Never decide 'not found' inside the loop.")
            .next("Next: the biggest, the smallest and the average."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(13), "Highest, Lowest, Average", 4)
            .brief(
                "The capacity report needs three numbers from a day of "
                + "response times: the fastest, the slowest and the average. "
                + "Campaign 04 found a running maximum as values arrived. "
                + "With the values in an array, all three come from one "
                + "pass - if they start from the right place.")
            .willLearn("Max, min and average")
            .whyUseful(
                "Summary statistics are how a baseline is described: normal "
                + "is around the average, and anything far above the "
                + "maximum of a normal day is worth a look. Getting them "
                + "right on every input, including awkward ones, matters.")
            .concept("Max, min and average",
                "Start the maximum and minimum at the FIRST slot, then "
                + "compare the rest:\n"
                + "\n"
                + "    int max = a[0];\n"
                + "    int min = a[0];\n"
                + "    int sum = 0;\n"
                + "    for (int v : a) {\n"
                + "        if (v > max) { max = v; }\n"
                + "        if (v < min) { min = v; }\n"
                + "        sum += v;\n"
                + "    }\n"
                + "    double avg = (double) sum / a.length;\n"
                + "\n"
                + "Why a[0] and not 0? Start min at 0 and it stays 0 when "
                + "every value is bigger. Start max at 0 and it stays 0 when "
                + "every value is negative. a[0] is a real value, so it is "
                + "always a fair starting point.\n"
                + "\n"
                + "The average needs two cares from Campaign 01:\n"
                + "\n"
                + "    (double) sum   or int division drops the fraction\n"
                + "    a.length > 0   or dividing by 0 (and a[0] crashes)\n"
                + "\n"
                + "So guard the empty array FIRST - an empty day has no "
                + "maximum, and pretending it is 0 would be a lie in the "
                + "report.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] ms = {120, 95, 310, 88, 142};",
                "        int max = ms[0];",
                "        int min = ms[0];",
                "        int sum = 0;",
                "        for (int v : ms) {",
                "            if (v > max) {",
                "                max = v;",
                "            }",
                "            if (v < min) {",
                "                min = v;",
                "            }",
                "            sum += v;",
                "        }",
                "        double avg = (double) sum / ms.length;",
                "        System.out.println(\"Fastest: \" + min + \" ms\");",
                "        System.out.println(\"Slowest: \" + max + \" ms\");",
                "        System.out.println(\"Average: \" + avg + \" ms\");",
                "    }",
                "}")
            .exampleOutput(
                "Fastest: 88 ms",
                "Slowest: 310 ms",
                "Average: 151.0 ms")
            .lineByLine(
                new String[]{"int max = ms[0];",
                    "Start from a real value, not from 0."},
                new String[]{"two separate ifs",
                    "One value can be checked against both. Neither is an "
                    + "else of the other."},
                new String[]{"(double) sum / ms.length",
                    "755 / 5 as a double: 151.0, fraction kept."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] t = {-4, -9, -2};",
                    "int max = 0;",
                    "for (int v : t) {",
                    "    if (v > max) {",
                    "        max = v;",
                    "    }",
                    "}",
                    "System.out.println(max);")
                .accept("0")
                .hints("Is any value bigger than 0?",
                       "max never changes.")
                .explain(
                    "0 - which is not in the array at all. Every reading is "
                    + "negative, so none beats the starting 0. Start at t[0] "
                    + "and the answer is -2.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {3, 4};",
                    "int sum = a[0] + a[1];",
                    "System.out.println(sum / a.length);")
                .accept("3")
                .hints("sum is 7, and a.length is 2.",
                       "Both are ints.")
                .explain(
                    "3. int divided by int drops the fraction: 7 / 2 is 3. "
                    + "(double) sum / a.length gives 3.5.")
                .xp(15))
            .objective(
                "Find the busiest hour's count safely.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(peak(new int[]{14, 3, 41, 9}));",
                "        System.out.println(peak(new int[]{}));",
                "    }",
                "",
                "    static int peak(int[] hourly) {",
                "        if (hourly.length == 0) {",
                "            return -1;",
                "        }",
                "        // max: start from the first real value",
                "        for (int v : hourly) {",
                "            if (v > max) {",
                "                max = v;",
                "            }",
                "        }",
                "        return max;",
                "    }",
                "}")
            .yourTask(
                "Declare max, starting at the first slot of hourly. The "
                + "guard above it already handles the empty array.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line that declares max.")
                .accept("int max = hourly[0];",
                        "int max=hourly[0];")
                .hints(
                    "Not 0 - a real value from the array.",
                    "The first slot is index 0.",
                    "int max = hourly[0];")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(peak(new int[]{14, 3, 41, 9}));",
                    "        System.out.println(peak(new int[]{}));",
                    "    }",
                    "",
                    "    static int peak(int[] hourly) {",
                    "        if (hourly.length == 0) {",
                    "            return -1;",
                    "        }",
                    "        int max = hourly[0];",
                    "        for (int v : hourly) {",
                    "            if (v > max) {",
                    "                max = v;",
                    "            }",
                    "        }",
                    "        return max;",
                    "    }",
                    "}")
                .whyItWorks(
                    "max begins at 14, and the loop raises it to 41. For "
                    + "the empty array the guard returns -1 before hourly[0] "
                    + "is ever read, so there is no crash. Output: 41, then "
                    + "-1.\n"
                    + "\n"
                    + "The order matters: the guard first, then hourly[0]. "
                    + "Swap them and the empty array crashes.")
                .explain(
                    "int max = hourly[0]; - a real value, read after the guard.")
                .xp(20))
            .mistakes(
                new String[]{"Starting max or min at 0",
                    "Wrong when all values are negative, or all positive."},
                new String[]{"sum / a.length with ints",
                    "The fraction is lost. Cast sum to double."},
                new String[]{"No empty-array guard",
                    "a[0] crashes, and / 0 has no answer."})
            .cyber(
                "Anomaly detection starts with a baseline: the average and "
                + "the usual range of logins, bytes or requests. A spike far "
                + "above a normal day's maximum is worth an alert. A wrong "
                + "baseline hides attacks - a maximum stuck at 0, or an "
                + "average with its fraction dropped, shifts every threshold "
                + "built on it. And an empty hour must be reported as empty, "
                + "not as a quiet zero.")
            .check(new Task(Task.CHOICE,
                    "Why start min at a[0] instead of 0?")
                .choices("It is faster",
                         "0 may be smaller than every real value",
                         "a[0] is always the smallest",
                         "Java requires it")
                .accept("2", "b")
                .hints("Try values 5, 8, 3 with min starting at 0.",
                       "min would stay 0.")
                .explain(
                    "b. If every value is above 0, a min starting at 0 never "
                    + "changes and reports a value that is not in the data.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {2, 3, 3};",
                    "int sum = 0;",
                    "for (int v : a) {",
                    "    sum += v;",
                    "}",
                    "System.out.println((double) sum / a.length);")
                .accept("2.6666666666666665")
                .hints("sum is 8.",
                       "8.0 / 3.")
                .explain(
                    "2.6666666666666665. The cast keeps the fraction; the "
                    + "last digit shows the tiny rounding that doubles "
                    + "always carry (Campaign 02).")
                .xp(10))
            .recap(
                "    guard: if (a.length == 0) ...\n"
                + "    int max = a[0];  int min = a[0];\n"
                + "    one pass: compare both, add to sum\n"
                + "    double avg = (double) sum / a.length\n"
                + "\n"
                + "Start from real data; divide as a double.")
            .next("Next: counting how often each value appears."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(14), "A Tally for Every Hour", 4)
            .brief(
                "Are logins happening at 3 a.m.? The login log gives the "
                + "hour of each one, and the question is how many fell in "
                + "each of the 24 hours. Twenty-four counter variables would "
                + "be absurd. One array of 24 counters - with the hour "
                + "itself as the index - does it in a single line.")
            .willLearn("Tally arrays")
            .whyUseful(
                "A tally array counts how often each value appears, in one "
                + "pass, however long the data. Hours of the day, status "
                + "codes, days of the week: whenever the values are small "
                + "whole numbers, the value can be the index.")
            .concept("Tally arrays",
                "Campaign 04 counted ONE kind of thing with one counter. "
                + "To count MANY kinds at once, use an array of counters and "
                + "let the value pick the slot:\n"
                + "\n"
                + "    int[] perHour = new int[24];   24 counters, all 0\n"
                + "    for (int h : loginHours) {\n"
                + "        perHour[h]++;              count this hour\n"
                + "    }\n"
                + "\n"
                + "A login at hour 9 adds 1 to perHour[9]; one at 23 adds 1 "
                + "to perHour[23]. Afterwards, perHour[3] is the number of "
                + "3 a.m. logins. The new int[24] starting at all zeros "
                + "(mission 3) is exactly what a set of counters needs.\n"
                + "\n"
                + "The value often needs a small step to become an index:\n"
                + "\n"
                + "    status code 404   code / 100   slot 4 (4xx)\n"
                + "    day 1 to 7        day - 1      slots 0 to 6\n"
                + "\n"
                + "One danger: the data now chooses the index. A value "
                + "outside the range - hour 24, or a corrupt -1 - is an "
                + "ArrayIndexOutOfBoundsException (mission 7). Data from "
                + "outside needs a range check before it becomes an "
                + "index.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] loginHours = {9, 23, 9, 14, 3, 23, 23, 9};",
                "        int[] perHour = new int[24];",
                "        for (int h : loginHours) {",
                "            perHour[h]++;",
                "        }",
                "        for (int hour = 0; hour < perHour.length; hour++) {",
                "            if (perHour[hour] > 0) {",
                "                System.out.println(\"Hour \" + hour + \": \"",
                "                        + perHour[hour]);",
                "            }",
                "        }",
                "    }",
                "}")
            .exampleOutput(
                "Hour 3: 1",
                "Hour 9: 3",
                "Hour 14: 1",
                "Hour 23: 3")
            .lineByLine(
                new String[]{"new int[24]",
                    "One counter for each hour, 0 to 23, all starting at 0."},
                new String[]{"perHour[h]++;",
                    "The hour is the index: the login is counted in its own "
                    + "slot."},
                new String[]{"if (perHour[hour] > 0)",
                    "Prints only the hours that had any logins."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] rolls = {2, 0, 2, 1, 2};",
                    "int[] seen = new int[3];",
                    "for (int r : rolls) {",
                    "    seen[r]++;",
                    "}",
                    "System.out.println(seen[0] + \" \" + seen[1] + \" \" + seen[2]);")
                .accept("1 1 3")
                .hints("Count how many 0s, 1s and 2s there are.",
                       "Each value adds 1 to its own slot.")
                .explain(
                    "1 1 3. One 0, one 1 and three 2s - each counted in the "
                    + "slot with its own number.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Days are numbered 1 (Monday) to 7 (Sunday). Which line "
                    + "counts day d in int[] perDay = new int[7]?")
                .choices("perDay[d]++;",
                         "perDay[d - 1]++;",
                         "perDay[d + 1]++;",
                         "perDay[7]++;")
                .accept("2", "b")
                .hints("Seven slots are numbered 0 to 6.",
                       "Day 7 must land in slot 6.")
                .explain(
                    "b. d - 1 turns 1..7 into 0..6. perDay[d] would crash on "
                    + "Sunday, when d is 7.")
                .xp(15))
            .objective(
                "Tally web responses by their class: 2xx, 3xx, 4xx, 5xx.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] codes = {200, 404, 200, 500, 301, 403, 200};",
                "        int[] byClass = new int[6];",
                "        for (int code : codes) {",
                "            // add 1 to the slot for this code's class",
                "        }",
                "        for (int c = 2; c <= 5; c++) {",
                "            System.out.println(c + \"xx: \" + byClass[c]);",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the line that adds 1 to the counter for the code's "
                + "class. The class is the first digit, which is code / 100 "
                + "(int division).")
            .mainTask(new Task(Task.WRITE,
                    "Write the line inside the first loop.")
                .accept("byClass[code / 100]++;",
                        "byClass[code/100]++;",
                        "byClass[code / 100] += 1;",
                        "byClass[code / 100] = byClass[code / 100] + 1;")
                .hints(
                    "404 / 100 is 4 in int division.",
                    "Use that as the index, and add 1.",
                    "byClass[code / 100]++;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int[] codes = {200, 404, 200, 500, 301, 403, 200};",
                    "        int[] byClass = new int[6];",
                    "        for (int code : codes) {",
                    "            byClass[code / 100]++;",
                    "        }",
                    "        for (int c = 2; c <= 5; c++) {",
                    "            System.out.println(c + \"xx: \" + byClass[c]);",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "200 / 100 is 2, 404 / 100 is 4, and so on, so each code "
                    + "lands in its class's slot: 2xx: 3, 3xx: 1, 4xx: 2, "
                    + "5xx: 1.\n"
                    + "\n"
                    + "The array has 6 slots so that indexes 2 to 5 exist; "
                    + "slots 0 and 1 are simply never used. A code of 600 or "
                    + "more would crash it - real log data would need a range "
                    + "check first.")
                .explain(
                    "byClass[code / 100]++; - the class digit is the index.")
                .xp(20))
            .mistakes(
                new String[]{"An array one too small",
                    "Values 0 to 23 need new int[24]."},
                new String[]{"Forgetting the offset",
                    "Values 1 to 7 go in slots 0 to 6: d - 1."},
                new String[]{"Trusting the data as an index",
                    "An out-of-range value crashes: check it first."})
            .cyber(
                "Tallies turn a flood of events into a picture. Logins per "
                + "hour show an account used at 3 a.m. when its owner "
                + "works days. Responses per class show a burst of 4xx - "
                + "someone probing for pages that do not exist. And because "
                + "the data picks the index, a crafted or corrupt value is "
                + "an easy way to crash a naive analyser: validate the range "
                + "before counting.")
            .check(new Task(Task.CHOICE,
                    "Minutes are 0 to 59. How big must a tally array for "
                    + "minutes be?")
                .choices("59", "60", "61", "100")
                .accept("2", "b")
                .hints("The biggest value must be a valid index.",
                       "The last index is length - 1.")
                .explain(
                    "b. 60 slots are numbered 0 to 59, one for every "
                    + "possible minute.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] codes = {404, 404, 200, 403};",
                    "int[] byClass = new int[6];",
                    "for (int c : codes) {",
                    "    byClass[c / 100]++;",
                    "}",
                    "System.out.println(byClass[4]);")
                .accept("3")
                .hints("Which codes start with 4?",
                       "404, 404 and 403.")
                .explain(
                    "3. Three of the codes divide down to 4 - two 404s and "
                    + "a 403.")
                .xp(10))
            .recap(
                "    int[] tally = new int[range];\n"
                + "    for (int v : data) {\n"
                + "        tally[v]++;      (or tally[v - 1], v / 100...)\n"
                + "    }\n"
                + "\n"
                + "The value is the index. Size the array for every value, "
                + "and range-check data from outside.")
            .next("Next: two arrays, kept side by side."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(15), "Side by Side", 4)
            .brief(
                "The failed-login report has two columns: who, and how many "
                + "times. Names are Strings and counts are ints, and an "
                + "array holds only one type. The simplest answer is two "
                + "arrays, lined up so that the same index means the same "
                + "account - and knowing its weak spot.")
            .willLearn("Parallel arrays")
            .whyUseful(
                "Parallel arrays keep related facts about each item - a "
                + "name and a count, a host and a port - using only what you "
                + "know so far. Their fragility is also the best argument "
                + "for Campaign 06's classes.")
            .concept("Parallel arrays",
                "PARALLEL ARRAYS are two (or more) arrays where the same "
                + "index describes the same thing:\n"
                + "\n"
                + "    String[] users = {\"jsmith\", \"admin\", \"mpatel\"};\n"
                + "    int[] fails    = {2,        14,      0};\n"
                + "\n"
                + "    index 1:   users[1] is admin, fails[1] is 14\n"
                + "\n"
                + "One index loop reads both:\n"
                + "\n"
                + "    for (int i = 0; i < users.length; i++) {\n"
                + "        System.out.println(users[i] + \": \" + fails[i]);\n"
                + "    }\n"
                + "\n"
                + "This needs the INDEX loop - an enhanced for over users "
                + "gives names, but no position to look up the count.\n"
                + "\n"
                + "To find the account with the most failures, track the "
                + "best INDEX, not the best value. The index then answers "
                + "both questions: who (users[worst]) and how many "
                + "(fails[worst]).\n"
                + "\n"
                + "The rules that keep them in step:\n"
                + "\n"
                + "    same length       one entry each, always\n"
                + "    change together   add, remove, reorder: both arrays\n"
                + "\n"
                + "Break either rule and every count after the break belongs "
                + "to the wrong person - with no error to warn you.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] users = {\"jsmith\", \"admin\", \"mpatel\", \"svc_web\"};",
                "        int[] fails = {2, 14, 0, 6};",
                "        for (int i = 0; i < users.length; i++) {",
                "            if (fails[i] >= 5) {",
                "                System.out.println(\"Review \" + users[i] + \": \"",
                "                        + fails[i]);",
                "            }",
                "        }",
                "        int worst = 0;",
                "        for (int i = 1; i < fails.length; i++) {",
                "            if (fails[i] > fails[worst]) {",
                "                worst = i;",
                "            }",
                "        }",
                "        System.out.println(\"Most failures: \" + users[worst]);",
                "    }",
                "}")
            .exampleOutput(
                "Review admin: 14",
                "Review svc_web: 6",
                "Most failures: admin")
            .lineByLine(
                new String[]{"users[i] + \": \" + fails[i]",
                    "The same i reads the name and its count."},
                new String[]{"int worst = 0;",
                    "The position of the worst so far, starting with the "
                    + "first account."},
                new String[]{"fails[i] > fails[worst]",
                    "Compare counts, but remember the index."},
                new String[]{"users[worst]",
                    "The index finds the name in the other array."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] hosts = {\"web\", \"db\", \"fw\"};",
                    "int[] ports = {443, 5432, 22};",
                    "System.out.println(hosts[2] + \":\" + ports[2]);")
                .accept("fw:22")
                .hints("Index 2 in both arrays.",
                       "The third entry of each.")
                .explain(
                    "fw:22. Slot 2 of each array describes the same host.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "A new account is added to users, but nobody adds its "
                    + "count to fails. What happens?")
                .choices("Java refuses to compile",
                         "The lengths differ, and a loop to users.length "
                         + "crashes on fails",
                         "fails grows by itself",
                         "Nothing changes")
                .accept("2", "b")
                .hints("Arrays never grow by themselves.",
                       "Which array is shorter now?")
                .explain(
                    "b. users is one longer, so the last i is a valid index "
                    + "for users but one past the end of fails.")
                .xp(15))
            .objective(
                "Track the index of the busiest source address.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] sources = {\"10.0.0.4\", \"10.0.0.9\",",
                "                \"10.0.0.17\", \"10.0.0.23\"};",
                "        int[] requests = {120, 4800, 95, 310};",
                "        int top = 0;",
                "        for (int i = 1; i < requests.length; i++) {",
                "            // the if: this count beats the one at top",
                "                top = i;",
                "            }",
                "        }",
                "        System.out.println(\"Busiest: \" + sources[top]);",
                "        System.out.println(\"Requests: \" + requests[top]);",
                "    }",
                "}")
            .yourTask(
                "Write the if that is true when the count at i is greater "
                + "than the count at the index top.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line.")
                .accept("if (requests[i] > requests[top]) {",
                        "if(requests[i] > requests[top]) {",
                        "if (requests[i] > requests[top]){",
                        "if (requests[top] < requests[i]) {")
                .hints(
                    "Compare two counts from requests.",
                    "One at i, one at top.",
                    "if (requests[i] > requests[top]) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String[] sources = {\"10.0.0.4\", \"10.0.0.9\",",
                    "                \"10.0.0.17\", \"10.0.0.23\"};",
                    "        int[] requests = {120, 4800, 95, 310};",
                    "        int top = 0;",
                    "        for (int i = 1; i < requests.length; i++) {",
                    "            if (requests[i] > requests[top]) {",
                    "                top = i;",
                    "            }",
                    "        }",
                    "        System.out.println(\"Busiest: \" + sources[top]);",
                    "        System.out.println(\"Requests: \" + requests[top]);",
                    "    }",
                    "}")
                .whyItWorks(
                    "top follows the position of the biggest count: it moves "
                    + "to 1 when 4800 beats 120, and nothing beats 4800 "
                    + "after that. That one index then reads both arrays: "
                    + "Busiest: 10.0.0.9, Requests: 4800.\n"
                    + "\n"
                    + "Tracking the VALUE 4800 instead would tell you how "
                    + "many, but not who.")
                .explain(
                    "if (requests[i] > requests[top]) { - compare by index.")
                .xp(20))
            .mistakes(
                new String[]{"Tracking the best value",
                    "You lose who it belonged to. Track the index."},
                new String[]{"Enhanced for over one array",
                    "No index, so no way into the other array."},
                new String[]{"Reordering only one array",
                    "Every entry after it now belongs to someone else."})
            .cyber(
                "Misaligned parallel arrays are a quiet, dangerous bug: the "
                + "report still prints names and numbers, just the wrong "
                + "pairs. The 14 failures blamed on mpatel really belong to "
                + "admin, and the investigation follows the wrong person. "
                + "It is one reason real tools keep each record's fields "
                + "together in one object - which is where Campaign 06 "
                + "begins.")
            .check(new Task(Task.CHOICE,
                    "fails is sorted into order, but users is left as it "
                    + "was. What is the result?")
                .choices("A compile error",
                         "A crash",
                         "Counts paired with the wrong users, silently",
                         "Both arrays are sorted")
                .accept("3", "c")
                .hints("Nothing checks that the arrays still line up.",
                       "The lengths still match.")
                .explain(
                    "c. The lengths still match, so nothing crashes - but "
                    + "index i no longer means the same account in both.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] u = {\"ann\", \"bob\", \"cy\"};",
                    "int[] n = {5, 9, 7};",
                    "int best = 0;",
                    "for (int i = 1; i < n.length; i++) {",
                    "    if (n[i] > n[best]) {",
                    "        best = i;",
                    "    }",
                    "}",
                    "System.out.println(u[best]);")
                .accept("bob")
                .hints("Which index holds the biggest n?",
                       "9 is at index 1.")
                .explain(
                    "bob. best becomes 1 when 9 beats 5, and 7 does not beat "
                    + "9. u[1] is bob.")
                .xp(10))
            .recap(
                "    same index = same record\n"
                + "    loop with i; read users[i] and fails[i]\n"
                + "    track the best INDEX, then read both arrays\n"
                + "\n"
                + "Keep the lengths equal and change both together. "
                + "Campaign 06 replaces this with objects.")
            .next("Next: a grid of values - two-dimensional arrays."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(16), "Rows and Columns", 4)
            .brief(
                "The weekly login report is a table: one row per day, one "
                + "column per shift - night, day, evening. A table is an "
                + "array of rows, and each row is an array of numbers. Java "
                + "calls it a two-dimensional array, and Campaign 04's "
                + "nested loops were made for it.")
            .willLearn("Two-dimensional arrays")
            .whyUseful(
                "Many security views are grids: days by hours, hosts by "
                + "ports, users by systems. A 2D array holds the whole grid, "
                + "and a row or a column can be totalled with one loop "
                + "inside another.")
            .concept("Two-dimensional arrays",
                "A TWO-DIMENSIONAL array is an array whose slots are "
                + "arrays - rows of columns:\n"
                + "\n"
                + "    int[][] logins = {\n"
                + "        {4, 30, 12},     row 0: Monday\n"
                + "        {9, 28, 10},     row 1: Tuesday\n"
                + "        {41, 25, 11}     row 2: Wednesday\n"
                + "    };\n"
                + "\n"
                + "Two indexes pick one number: ROW first, then COLUMN.\n"
                + "\n"
                + "    logins[2][0]         41 (Wednesday, night)\n"
                + "    logins.length        3, the number of rows\n"
                + "    logins[0].length     3, the columns in row 0\n"
                + "    new int[7][24]       7 rows of 24, all 0\n"
                + "\n"
                + "Visiting every cell takes a loop over the rows with a "
                + "loop over the columns inside it:\n"
                + "\n"
                + "    for (int r = 0; r < logins.length; r++) {\n"
                + "        for (int c = 0; c < logins[r].length; c++) {\n"
                + "            ... logins[r][c] ...\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "The inner limit is logins[r].length - the length of THIS "
                + "row. Mixing up r and c is the classic bug: it reads the "
                + "wrong cell, or runs off the end of a row.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[][] logins = {",
                "            {4, 30, 12},",
                "            {9, 28, 10},",
                "            {41, 25, 11}",
                "        };",
                "        for (int r = 0; r < logins.length; r++) {",
                "            int total = 0;",
                "            for (int c = 0; c < logins[r].length; c++) {",
                "                total += logins[r][c];",
                "            }",
                "            System.out.println(\"Day \" + r + \": \" + total);",
                "        }",
                "        int night = 0;",
                "        for (int r = 0; r < logins.length; r++) {",
                "            night += logins[r][0];",
                "        }",
                "        System.out.println(\"Night shift: \" + night);",
                "    }",
                "}")
            .exampleOutput(
                "Day 0: 46",
                "Day 1: 47",
                "Day 2: 77",
                "Night shift: 54")
            .lineByLine(
                new String[]{"int[][] logins = {...};",
                    "Three rows, each an array of three counts."},
                new String[]{"int total = 0; inside the row loop",
                    "Restarts at 0 for each day, so every row gets its own "
                    + "total."},
                new String[]{"logins[r][c]",
                    "Row r, column c."},
                new String[]{"logins[r][0]",
                    "Column 0 of every row: the night shift, down the "
                    + "table."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[][] g = {{1, 2, 3}, {4, 5, 6}};",
                    "System.out.println(g[1][0] + \" \" + g[0][2]);")
                .accept("4 3")
                .hints("Row first, then column.",
                       "g[1] is {4, 5, 6}; g[0] is {1, 2, 3}.")
                .explain(
                    "4 3. g[1][0] is row 1, column 0: 4. g[0][2] is row 0, "
                    + "column 2: 3.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "int[][] week = new int[7][24]; What is week.length, "
                    + "and what is week[0].length?")
                .choices("24 and 7", "7 and 24", "168 and 24", "7 and 7")
                .accept("2", "b")
                .hints("The first size is the number of rows.",
                       "Each row has the second size.")
                .explain(
                    "b. 7 rows (days), each an array of 24 (hours). The "
                    + "grid holds 168 counts, but no length is 168.")
                .xp(10))
            .objective(
                "Total a host's blocked connections across every port.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // rows: hosts; columns: ports 22, 80, 443",
                "        int[][] blocked = {",
                "            {12, 0, 3},",
                "            {0, 7, 1},",
                "            {40, 2, 9}",
                "        };",
                "        int host = 2;",
                "        int sum = 0;",
                "        for (int c = 0; c < blocked[host].length; c++) {",
                "            // add this host's count for column c",
                "        }",
                "        System.out.println(\"Host \" + host + \" blocked: \" + sum);",
                "    }",
                "}")
            .yourTask(
                "Write the line that adds the count at row host, column c "
                + "to sum.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line inside the loop.")
                .accept("sum += blocked[host][c];",
                        "sum+=blocked[host][c];",
                        "sum = sum + blocked[host][c];")
                .hints(
                    "Row first, then column.",
                    "The row is host; the column is c.",
                    "sum += blocked[host][c];")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        // rows: hosts; columns: ports 22, 80, 443",
                    "        int[][] blocked = {",
                    "            {12, 0, 3},",
                    "            {0, 7, 1},",
                    "            {40, 2, 9}",
                    "        };",
                    "        int host = 2;",
                    "        int sum = 0;",
                    "        for (int c = 0; c < blocked[host].length; c++) {",
                    "            sum += blocked[host][c];",
                    "        }",
                    "        System.out.println(\"Host \" + host + \" blocked: \" + sum);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The row stays fixed at host 2 while c walks its three "
                    + "columns: 40 + 2 + 9. Output: Host 2 blocked: 51.\n"
                    + "\n"
                    + "Swapping the indexes, blocked[c][host], would total "
                    + "COLUMN 2 instead - port 443 across all hosts - and "
                    + "print 13 with no error to warn you.")
                .explain(
                    "sum += blocked[host][c]; - row host, column c.")
                .xp(20))
            .mistakes(
                new String[]{"g[c][r] instead of g[r][c]",
                    "Reads the wrong cell - or crashes on a narrow grid."},
                new String[]{"Inner limit g.length",
                    "Use g[r].length, the length of the row."},
                new String[]{"Total declared outside the row loop",
                    "Rows add up into one running number."})
            .cyber(
                "Grids are how analysts spot patterns a list hides. A "
                + "day-by-hour table of logins makes a 3 a.m. burst stand "
                + "out; a host-by-port table of blocked connections shows "
                + "one host being probed on every port - a scan - where a "
                + "flat total would only show 'a lot'. Row totals and "
                + "column totals answer different questions, so get the "
                + "indexes the right way round.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[][] g = {{1, 2}, {3, 4}, {5, 6}};",
                    "int sum = 0;",
                    "for (int r = 0; r < g.length; r++) {",
                    "    sum += g[r][1];",
                    "}",
                    "System.out.println(sum);")
                .accept("12")
                .hints("Column 1 of every row.",
                       "2 + 4 + 6.")
                .explain(
                    "12. The column stays at 1 while r walks the rows: "
                    + "2 + 4 + 6.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "In a nested loop over a 2D array g, what should the "
                    + "inner loop's limit be?")
                .choices("g.length",
                         "g[r].length",
                         "g[c].length",
                         "g.length - 1")
                .accept("2", "b")
                .hints("The inner loop walks one row.",
                       "How long is row r?")
                .explain(
                    "b. The inner loop visits the columns of row r, and row "
                    + "r has g[r].length of them.")
                .xp(10))
            .recap(
                "    int[][] g = new int[rows][cols];\n"
                + "    g[r][c]          row first, then column\n"
                + "    g.length         rows\n"
                + "    g[r].length      columns in row r\n"
                + "\n"
                + "Every cell: a loop over rows, a loop over columns "
                + "inside it.")
            .next("Next: turning one line of text into an array."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(17), "From a Line to an Array", 4)
            .brief(
                "Each line of the VPN log holds four fields separated by "
                + "commas: time, user, source address, result. Campaign 01 "
                + "pulled fields out with indexOf and substring, one careful "
                + "cut at a time. split cuts the whole line at once and "
                + "hands back an array of the pieces.")
            .willLearn("split")
            .whyUseful(
                "Nearly every log, CSV export and config file is lines of "
                + "fields with a separator. split turns each line into a "
                + "String[] in one call - and knowing its two traps keeps "
                + "a parser from breaking on real data.")
            .concept("split",
                "split is a String method. It cuts the text at every "
                + "separator and returns the pieces as an array:\n"
                + "\n"
                + "    String line = \"09:14,jsmith,10.0.0.5,FAIL\";\n"
                + "    String[] parts = line.split(\",\");\n"
                + "\n"
                + "    parts[0]  \"09:14\"     parts[2]  \"10.0.0.5\"\n"
                + "    parts[1]  \"jsmith\"    parts[3]  \"FAIL\"\n"
                + "\n"
                + "The pieces are Strings. A number field still needs "
                + "Integer.parseInt(parts[i]).\n"
                + "\n"
                + "TRAP 1: a few characters have a special meaning to split, "
                + "notably the dot and the bar. To split on them, put two "
                + "backslashes in front:\n"
                + "\n"
                + "    \"10.0.0.5\".split(\"\\\\.\")     4 pieces\n"
                + "    \"10.0.0.5\".split(\".\")       0 pieces - a bug\n"
                + "    \"a|b\".split(\"\\\\|\")          2 pieces\n"
                + "\n"
                + "(split reads its separator as a REGULAR EXPRESSION, a "
                + "pattern language beyond this module. Commas, colons, "
                + "spaces and = are safe as they are.)\n"
                + "\n"
                + "TRAP 2: a line may not have as many fields as you expect "
                + "- truncated, corrupt, or crafted. Reading parts[3] from a "
                + "2-piece line is mission 7's crash. Check parts.length "
                + "before reading the fields.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String line = \"09:14,jsmith,10.0.0.5,FAIL\";",
                "        String[] parts = line.split(\",\");",
                "        if (parts.length == 4) {",
                "            System.out.println(\"User: \" + parts[1]);",
                "            System.out.println(\"From: \" + parts[2]);",
                "            System.out.println(\"Result: \" + parts[3]);",
                "            String[] octets = parts[2].split(\"\\\\.\");",
                "            int last = Integer.parseInt(octets[3]);",
                "            System.out.println(\"Host number: \" + last);",
                "        } else {",
                "            System.out.println(\"Malformed line\");",
                "        }",
                "    }",
                "}")
            .exampleOutput(
                "User: jsmith",
                "From: 10.0.0.5",
                "Result: FAIL",
                "Host number: 5")
            .lineByLine(
                new String[]{"line.split(\",\")",
                    "Four pieces, one for each field between the commas."},
                new String[]{"if (parts.length == 4)",
                    "Only read the fields once you know they are there."},
                new String[]{"split(\"\\\\.\")",
                    "The dot needs two backslashes. \"10.0.0.5\" becomes "
                    + "four octets."},
                new String[]{"Integer.parseInt(octets[3])",
                    "The piece \"5\" is text until it is parsed."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String cfg = \"port=8443\";",
                    "String[] kv = cfg.split(\"=\");",
                    "System.out.println(kv.length + \" \" + kv[1]);")
                .accept("2 8443")
                .hints("One = means two pieces.",
                       "kv[1] is what comes after the =.")
                .explain(
                    "2 8443. The = cuts the text into \"port\" and \"8443\".")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String ip = \"192.168.1.20\";",
                    "System.out.println(ip.split(\".\").length);")
                .accept("0")
                .hints("The dot is one of the special characters.",
                       "Unescaped, it matches every character.")
                .explain(
                    "0. An unescaped dot means 'any character', so every "
                    + "character is a separator and nothing is left. "
                    + "split(\"\\\\.\") gives the 4 octets.")
                .xp(20))
            .objective(
                "Split an auth log line and check it has every field.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String line = \"2026-03-02 22:41:07 admin DENIED\";",
                "        // parts: the line split at each space",
                "        if (parts.length == 4) {",
                "            System.out.println(parts[2] + \" -> \" + parts[3]);",
                "        } else {",
                "            System.out.println(\"Malformed: \" + parts.length",
                "                    + \" fields\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Declare parts: the line split at each space.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line that declares parts.")
                .accept("String[] parts = line.split(\" \");",
                        "String[] parts=line.split(\" \");")
                .hints(
                    "split returns a String[].",
                    "The separator is a single space, in quotes.",
                    "String[] parts = line.split(\" \");")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String line = \"2026-03-02 22:41:07 admin DENIED\";",
                    "        String[] parts = line.split(\" \");",
                    "        if (parts.length == 4) {",
                    "            System.out.println(parts[2] + \" -> \" + parts[3]);",
                    "        } else {",
                    "            System.out.println(\"Malformed: \" + parts.length",
                    "                    + \" fields\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "Three spaces cut the line into four pieces: the date, "
                    + "the time, the user and the result. The length check "
                    + "passes, so it prints admin -> DENIED.\n"
                    + "\n"
                    + "Feed it a truncated line such as \"2026-03-02 "
                    + "22:41:07\" and it reports 'Malformed: 2 fields' "
                    + "instead of crashing on parts[2].")
                .explain(
                    "String[] parts = line.split(\" \"); - cut at each space.")
                .xp(15))
            .mistakes(
                new String[]{"split(\".\") on an address",
                    "Returns nothing. Use split(\"\\\\.\")."},
                new String[]{"Reading fields without a length check",
                    "A short line crashes the parser."},
                new String[]{"Treating a piece as a number",
                    "It is a String until Integer.parseInt."})
            .cyber(
                "Parsers are attacked through their input. A log line with "
                + "a missing field, an extra separator inside a username, or "
                + "nothing at all will reach your split sooner or later - "
                + "sometimes sent on purpose to crash the monitoring tool "
                + "so that what follows goes unrecorded. Check parts.length, "
                + "and decide what to do with a malformed line (count it, "
                + "report it) instead of letting it take the tool down.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String s = \"a,b,,d\";",
                    "String[] p = s.split(\",\");",
                    "System.out.println(p.length + \" [\" + p[2] + \"]\");")
                .accept("4 []")
                .hints("Two commas side by side have nothing between them.",
                       "That nothing is still a piece.")
                .explain(
                    "4 []. The empty field between the two commas is a real "
                    + "piece: an empty String. Missing values in logs look "
                    + "exactly like this.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which call splits \"10.0.0.5\" into its four numbers?")
                .choices("split(\".\")",
                         "split(\"\\\\.\")",
                         "split(\",\")",
                         "split(\" \")")
                .accept("2", "b")
                .hints("The dot is special to split.",
                       "It needs two backslashes in front.")
                .explain(
                    "b. \\\\. means a real dot. A plain \".\" means any "
                    + "character, and c and d find no separator at all, "
                    + "giving one piece.")
                .xp(10))
            .recap(
                "    String[] parts = line.split(\",\");\n"
                + "    if (parts.length == 4) { ... parts[3] ... }\n"
                + "\n"
                + "Dots and bars need \\\\. and \\\\|. Pieces are text: "
                + "parse numbers. Always check the length first.")
            .next("Next: a list that grows - ArrayList."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(18), "A List That Grows", 3)
            .brief(
                "The night shift builds a list of accounts to review as the "
                + "log is read. Nobody knows at the start how many there "
                + "will be - none, or forty. An array must be given its "
                + "size up front and never grows. Java's ArrayList starts "
                + "empty and grows with every add.")
            .willLearn("ArrayList")
            .whyUseful(
                "Most real collections have no size known in advance: "
                + "suspicious addresses, alerts raised, users found. "
                + "ArrayList handles the growing for you, and it is the "
                + "list type used throughout real Java code.")
            .concept("ArrayList",
                "ArrayList lives in java.util, so it needs an import:\n"
                + "\n"
                + "    import java.util.ArrayList;\n"
                + "\n"
                + "Creating an empty list of Strings:\n"
                + "\n"
                + "    ArrayList<String> review = new ArrayList<>();\n"
                + "\n"
                + "    ArrayList<String>   a list of Strings\n"
                + "    new ArrayList<>()   an empty one; <> repeats the type\n"
                + "\n"
                + "The type in the angle brackets says what the list holds. "
                + "Add to the end with add, and ask the size with size():\n"
                + "\n"
                + "    review.add(\"jsmith\");      size() is 1\n"
                + "    review.add(\"svc_old\");     size() is 2\n"
                + "\n"
                + "Unlike an array, a list prints its contents directly:\n"
                + "\n"
                + "    System.out.println(review);    [jsmith, svc_old]\n"
                + "\n"
                + "Note the differences from arrays: size() with brackets "
                + "(length belongs to arrays), and methods instead of "
                + "square brackets. The list only holds objects - "
                + "ArrayList<String> is fine, but numbers need "
                + "ArrayList<Integer>, which mission 22 explains.")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] results = {\"jsmith:OK\", \"admin:FAIL\",",
                "                \"mpatel:OK\", \"temp01:FAIL\"};",
                "        ArrayList<String> review = new ArrayList<>();",
                "        System.out.println(\"Start: \" + review.size());",
                "        for (String r : results) {",
                "            String[] parts = r.split(\":\");",
                "            if (parts[1].equals(\"FAIL\")) {",
                "                review.add(parts[0]);",
                "            }",
                "        }",
                "        System.out.println(\"To review: \" + review.size());",
                "        System.out.println(review);",
                "    }",
                "}")
            .exampleOutput(
                "Start: 0",
                "To review: 2",
                "[admin, temp01]")
            .lineByLine(
                new String[]{"new ArrayList<>()",
                    "An empty list. No size is given - it grows as needed."},
                new String[]{"review.add(parts[0]);",
                    "Adds the user to the end of the list."},
                new String[]{"review.size()",
                    "How many items the list holds right now."},
                new String[]{"System.out.println(review);",
                    "Lists print their contents, in order, in [ ]."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<String> hosts = new java.util.ArrayList<>();",
                    "hosts.add(\"web1\");",
                    "hosts.add(\"db1\");",
                    "hosts.add(\"web1\");",
                    "System.out.println(hosts.size() + \" \" + hosts);")
                .accept("3 [web1, db1, web1]")
                .hints("Every add puts one more item at the end.",
                       "A list may hold the same value twice.")
                .explain(
                    "3 [web1, db1, web1]. Three adds, three items, kept in the "
                    + "order they were added - duplicates included.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Which asks how many items the list alerts holds?")
                .choices("alerts.length",
                         "alerts.length()",
                         "alerts.size()",
                         "alerts.count")
                .accept("3", "c")
                .hints("It is a method, so it has brackets.",
                       "length is for arrays and Strings.")
                .explain(
                    "c. ArrayList uses size(). length belongs to arrays, and "
                    + "length() to Strings.")
                .xp(10))
            .objective(
                "Collect every address that was refused.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] ips = {\"10.0.0.4\", \"10.0.0.9\", \"10.0.0.12\"};",
                "        boolean[] refused = {false, true, true};",
                "        // blocked: a new, empty ArrayList of Strings",
                "        for (int i = 0; i < ips.length; i++) {",
                "            if (refused[i]) {",
                "                blocked.add(ips[i]);",
                "            }",
                "        }",
                "        System.out.println(blocked);",
                "    }",
                "}")
            .yourTask(
                "Declare blocked as a new, empty ArrayList that holds "
                + "Strings.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line that declares blocked.")
                .accept("ArrayList<String> blocked = new ArrayList<>();",
                        "ArrayList<String> blocked = new ArrayList<String>();",
                        "ArrayList<String> blocked=new ArrayList<>();")
                .hints(
                    "The type is ArrayList<String>.",
                    "new ArrayList<>() makes an empty one.",
                    "ArrayList<String> blocked = new ArrayList<>();")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String[] ips = {\"10.0.0.4\", \"10.0.0.9\", \"10.0.0.12\"};",
                    "        boolean[] refused = {false, true, true};",
                    "        ArrayList<String> blocked = new ArrayList<>();",
                    "        for (int i = 0; i < ips.length; i++) {",
                    "            if (refused[i]) {",
                    "                blocked.add(ips[i]);",
                    "            }",
                    "        }",
                    "        System.out.println(blocked);",
                    "    }",
                    "}")
                .whyItWorks(
                    "blocked starts empty. Two of the three addresses were "
                    + "refused, so two adds run and the list grows to hold "
                    + "exactly them: [10.0.0.9, 10.0.0.12].\n"
                    + "\n"
                    + "With an array you would have had to guess a size - or "
                    + "count first and fill second. The list simply grows.")
                .explain(
                    "ArrayList<String> blocked = new ArrayList<>(); - empty.")
                .xp(15))
            .mistakes(
                new String[]{"Forgetting the import",
                    "import java.util.ArrayList; at the top."},
                new String[]{"list.length",
                    "Lists use size()."},
                new String[]{"ArrayList<int>",
                    "Only object types: ArrayList<Integer> (mission 22)."})
            .cyber(
                "Findings arrive one at a time and their number is unknown "
                + "until the scan ends - which is why real security tools "
                + "collect them in lists. One caution carries over from "
                + "Campaign 04: a list that grows with the input can grow "
                + "with an attacker's input too. A tool that adds every "
                + "request to a list forever will run out of memory; cap "
                + "what you keep.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<String> a = new java.util.ArrayList<>();",
                    "System.out.println(a.size() + \" \" + a);")
                .accept("0 []")
                .hints("Nothing has been added.",
                       "An empty list prints as [].")
                .explain(
                    "0 []. A new list is empty, and prints as a pair of "
                    + "brackets with nothing inside.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Why use an ArrayList instead of an array for a list of "
                    + "suspicious addresses?")
                .choices("It is always faster",
                         "The number found is not known in advance",
                         "Arrays cannot hold Strings",
                         "Lists never run out of memory")
                .accept("2", "b")
                .hints("What can an array never do?",
                       "An array's size is fixed when it is made.")
                .explain(
                    "b. An array needs its size up front. A list grows as "
                    + "each address is found - though, as the cyber note "
                    + "says, not without limit.")
                .xp(10))
            .recap(
                "    import java.util.ArrayList;\n"
                + "    ArrayList<String> list = new ArrayList<>();\n"
                + "    list.add(x)        add to the end\n"
                + "    list.size()        how many now\n"
                + "    println(list)      [a, b, c]\n"
                + "\n"
                + "Starts empty, grows with every add.")
            .next("Next: reading and changing items in a list."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(19), "Reading and Changing a List", 3)
            .brief(
                "The incident queue is an ArrayList of ticket titles, most "
                + "urgent first. The lead needs to read the top one, relabel "
                + "the second, and push a new critical ticket in at the very "
                + "front. With an array, that last one means shifting every "
                + "slot by hand. A list does it in one call.")
            .willLearn("get and set")
            .whyUseful(
                "Reading one item and replacing one item are the everyday "
                + "list operations. Inserting at a position - which an "
                + "array cannot do at all without a copy - is one method "
                + "call.")
            .concept("get and set",
                "List positions are indexes from 0, like arrays - but they "
                + "are reached with methods, not square brackets:\n"
                + "\n"
                + "    array            ArrayList\n"
                + "    a[i]             list.get(i)\n"
                + "    a[i] = x;        list.set(i, x);\n"
                + "    a.length         list.size()\n"
                + "\n"
                + "add has a second form that INSERTS at a position; "
                + "everything from there on moves up one place:\n"
                + "\n"
                + "    list.add(\"x\");        to the end\n"
                + "    list.add(0, \"x\");     to the front; the rest shift\n"
                + "\n"
                + "The last item is list.get(list.size() - 1). An index "
                + "outside 0 to size() - 1 crashes, as with arrays - here "
                + "the exception is IndexOutOfBoundsException.\n"
                + "\n"
                + "One more helper: list.isEmpty() is true when size() is "
                + "0. Use it before get(0) - an empty list has no first "
                + "item.\n"
                + "\n"
                + "The index loop from mission 5 works unchanged, with "
                + "size() and get(i):\n"
                + "\n"
                + "    for (int i = 0; i < list.size(); i++) {\n"
                + "        System.out.println(i + \": \" + list.get(i));\n"
                + "    }")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> queue = new ArrayList<>();",
                "        queue.add(\"Phishing report\");",
                "        queue.add(\"Disk alert\");",
                "        queue.add(\"Password reset\");",
                "        System.out.println(\"Top: \" + queue.get(0));",
                "        queue.set(1, \"Disk alert (db2)\");",
                "        queue.add(0, \"Ransomware note found\");",
                "        for (int i = 0; i < queue.size(); i++) {",
                "            System.out.println(i + \": \" + queue.get(i));",
                "        }",
                "    }",
                "}")
            .exampleOutput(
                "Top: Phishing report",
                "0: Ransomware note found",
                "1: Phishing report",
                "2: Disk alert (db2)",
                "3: Password reset")
            .lineByLine(
                new String[]{"queue.get(0)",
                    "The item at index 0, read without removing it."},
                new String[]{"queue.set(1, \"Disk alert (db2)\")",
                    "Replaces the item at index 1. The size stays 3."},
                new String[]{"queue.add(0, \"Ransomware note found\")",
                    "Inserts at the front. Every other ticket moves up one: "
                    + "the size is now 4."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<String> a = new java.util.ArrayList<>();",
                    "a.add(\"x\");",
                    "a.add(\"y\");",
                    "a.add(1, \"z\");",
                    "System.out.println(a);")
                .accept("[x, z, y]")
                .hints("add(1, ...) inserts at index 1.",
                       "y moves up to make room.")
                .explain(
                    "[x, z, y]. z goes into index 1, and y shifts from index "
                    + "1 to index 2.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "A list holds 4 items. Which call crashes?")
                .choices("list.get(0)",
                         "list.get(3)",
                         "list.get(list.size() - 1)",
                         "list.get(4)")
                .accept("4", "d")
                .hints("Indexes run from 0 to size() - 1.",
                       "size() is 4.")
                .explain(
                    "d. Four items sit at indexes 0 to 3. get(4) throws "
                    + "IndexOutOfBoundsException.")
                .xp(10))
            .objective(
                "Mark a ticket as closed, in place.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> tickets = new ArrayList<>();",
                "        tickets.add(\"OPEN phishing\");",
                "        tickets.add(\"OPEN malware\");",
                "        tickets.add(\"OPEN disk\");",
                "        int done = 1;",
                "        String old = tickets.get(done);",
                "        // replace the item at index done with its CLOSED version",
                "        System.out.println(tickets);",
                "    }",
                "}")
            .yourTask(
                "Replace the item at index done with \"CLOSED \" followed by "
                + "old.substring(5) - the ticket's name without OPEN.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line that replaces the item.")
                .accept("tickets.set(done, \"CLOSED \" + old.substring(5));",
                        "tickets.set(done,\"CLOSED \" + old.substring(5));",
                        "tickets.set(1, \"CLOSED \" + old.substring(5));")
                .hints(
                    "set(index, newValue) replaces an item.",
                    "The new value is \"CLOSED \" + old.substring(5).",
                    "tickets.set(done, \"CLOSED \" + old.substring(5));")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        ArrayList<String> tickets = new ArrayList<>();",
                    "        tickets.add(\"OPEN phishing\");",
                    "        tickets.add(\"OPEN malware\");",
                    "        tickets.add(\"OPEN disk\");",
                    "        int done = 1;",
                    "        String old = tickets.get(done);",
                    "        tickets.set(done, \"CLOSED \" + old.substring(5));",
                    "        System.out.println(tickets);",
                    "    }",
                    "}")
                .whyItWorks(
                    "old is \"OPEN malware\"; substring(5) skips \"OPEN \" "
                    + "and leaves \"malware\". set puts \"CLOSED malware\" at "
                    + "index 1 in place of the old text, and the list keeps "
                    + "its size and order: [OPEN phishing, CLOSED malware, "
                    + "OPEN disk].\n"
                    + "\n"
                    + "add(done, ...) would have INSERTED a fourth ticket "
                    + "and left the open one behind it.")
                .explain(
                    "tickets.set(done, \"CLOSED \" + old.substring(5));")
                .xp(15))
            .mistakes(
                new String[]{"list[i]",
                    "Lists use get(i). Square brackets are for arrays."},
                new String[]{"add(i, x) to replace",
                    "add inserts and shifts; set replaces."},
                new String[]{"get(0) on an empty list",
                    "Check isEmpty() first."})
            .cyber(
                "An incident queue in the wrong order is an incident "
                + "handled late. Inserting at the front puts the critical "
                + "item where the next analyst will look, and set changes "
                + "a status in place without losing its position. The "
                + "difference between set and add(i, x) is exactly the kind "
                + "of slip that leaves a closed ticket AND an open one - "
                + "and two people working the same incident.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<String> a = new java.util.ArrayList<>();",
                    "a.add(\"p\");",
                    "a.add(\"q\");",
                    "a.set(0, \"r\");",
                    "System.out.println(a.get(0) + a.size());")
                .accept("r2")
                .hints("set replaces; the size does not change.",
                       "Two adds, so size() is 2.")
                .explain(
                    "r2. set swapped p for r in index 0, and the list still "
                    + "holds two items.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "How do you read the LAST item of a non-empty list?")
                .choices("list.get(list.size())",
                         "list.get(list.size() - 1)",
                         "list.get(-1)",
                         "list.last")
                .accept("2", "b")
                .hints("Indexes start at 0.",
                       "The last index is size() - 1.")
                .explain(
                    "b. As with arrays, the last index is one less than the "
                    + "count. get(list.size()) is one past the end.")
                .xp(10))
            .recap(
                "    list.get(i)          read\n"
                + "    list.set(i, x)       replace\n"
                + "    list.add(i, x)       insert; the rest shift up\n"
                + "    list.isEmpty()       size() == 0\n"
                + "\n"
                + "Valid indexes: 0 to size() - 1.")
            .next("Next: removing and finding items."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(20), "Remove, Contains, IndexOf", 4)
            .brief(
                "A contractor's project ended yesterday. Their account "
                + "temp01 must come off the admin list today, and the audit "
                + "wants proof: is temp01 still on it, and where? Mission 12 "
                + "wrote a linear search by hand. ArrayList has one built "
                + "in - and removal that closes the gap for you.")
            .willLearn("remove, contains and indexOf")
            .whyUseful(
                "Revoking access, checking membership and finding a "
                + "position are the core of every allowlist and blocklist. "
                + "The list methods do the loop for you, using equals, so "
                + "text is compared properly.")
            .concept("remove, contains and indexOf",
                "Three methods search the list for you, comparing with "
                + "equals - never == - so text matches by content:\n"
                + "\n"
                + "    list.contains(x)   true if x is anywhere in it\n"
                + "    list.indexOf(x)    the first position of x, or -1\n"
                + "    list.remove(x)     removes the first x; true if\n"
                + "                       one was found\n"
                + "\n"
                + "remove also has a form that takes an INDEX, and returns "
                + "the item it took out:\n"
                + "\n"
                + "    String gone = list.remove(0);   removes the first\n"
                + "\n"
                + "After any remove, everything behind the gap moves DOWN "
                + "one place, and size() drops by one. Indexes you worked "
                + "out before the removal may now point at a different item.\n"
                + "\n"
                + "remove(x) takes out only the FIRST match. If a value can "
                + "appear more than once, keep removing while contains says "
                + "it is still there:\n"
                + "\n"
                + "    while (list.contains(\"temp01\")) {\n"
                + "        list.remove(\"temp01\");\n"
                + "    }")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> admins = new ArrayList<>();",
                "        admins.add(\"root\");",
                "        admins.add(\"temp01\");",
                "        admins.add(\"jsmith\");",
                "        System.out.println(\"temp01 at: \" + admins.indexOf(\"temp01\"));",
                "        boolean removed = admins.remove(\"temp01\");",
                "        System.out.println(\"Removed: \" + removed);",
                "        boolean still = admins.contains(\"temp01\");",
                "        System.out.println(\"Still admin: \" + still);",
                "        System.out.println(\"jsmith at: \" + admins.indexOf(\"jsmith\"));",
                "        System.out.println(admins);",
                "    }",
                "}")
            .exampleOutput(
                "temp01 at: 1",
                "Removed: true",
                "Still admin: false",
                "jsmith at: 1",
                "[root, jsmith]")
            .lineByLine(
                new String[]{"admins.indexOf(\"temp01\")",
                    "A built-in linear search: 1, or -1 if absent."},
                new String[]{"admins.remove(\"temp01\")",
                    "Takes out the first match and reports true."},
                new String[]{"admins.contains(\"temp01\")",
                    "The audit's proof that the account is gone."},
                new String[]{"jsmith at: 1",
                    "jsmith was at 2. After the removal it moved down to 1."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<String> a = new java.util.ArrayList<>();",
                    "a.add(\"ann\");",
                    "a.add(\"bob\");",
                    "a.add(\"cy\");",
                    "String gone = a.remove(0);",
                    "System.out.println(gone + \" \" + a.get(0));")
                .accept("ann bob")
                .hints("remove(0) returns what it removed.",
                       "Then everything moves down one place.")
                .explain(
                    "ann bob. remove(0) takes out and returns ann, and bob "
                    + "moves down into index 0.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<String> a = new java.util.ArrayList<>();",
                    "a.add(\"x\");",
                    "a.add(\"y\");",
                    "a.add(\"x\");",
                    "a.remove(\"x\");",
                    "System.out.println(a.contains(\"x\") + \" \" + a);")
                .accept("true [y, x]")
                .hints("remove(value) takes out only the first match.",
                       "The second x is still there.")
                .explain(
                    "true [y, x]. Only the first x went. A revoked account "
                    + "listed twice is still on the list - keep removing "
                    + "while contains is true.")
                .xp(20))
            .objective(
                "Revoke an account, however many times it is listed.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> access = new ArrayList<>();",
                "        access.add(\"temp01\");",
                "        access.add(\"root\");",
                "        access.add(\"temp01\");",
                "        // the while header: temp01 is still in access",
                "            access.remove(\"temp01\");",
                "        }",
                "        System.out.println(access);",
                "    }",
                "}")
            .yourTask(
                "Write the while header that keeps the loop going as long "
                + "as access still contains \"temp01\".")
            .mainTask(new Task(Task.WRITE,
                    "Write the while header.")
                .accept("while (access.contains(\"temp01\")) {",
                        "while(access.contains(\"temp01\")) {",
                        "while (access.contains(\"temp01\")){",
                        "while (access.indexOf(\"temp01\") != -1) {",
                        "while (access.indexOf(\"temp01\") >= 0) {")
                .hints(
                    "Which method answers 'is it in the list?'",
                    "contains returns a boolean - a perfect condition.",
                    "while (access.contains(\"temp01\")) {")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        ArrayList<String> access = new ArrayList<>();",
                    "        access.add(\"temp01\");",
                    "        access.add(\"root\");",
                    "        access.add(\"temp01\");",
                    "        while (access.contains(\"temp01\")) {",
                    "            access.remove(\"temp01\");",
                    "        }",
                    "        System.out.println(access);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The first pass removes the temp01 at index 0; contains "
                    + "is still true, so a second pass removes the other one. "
                    + "Then contains is false and the loop ends: [root].\n"
                    + "\n"
                    + "One remove would have left a copy behind - and a "
                    + "revocation that misses one entry has not revoked "
                    + "anything.")
                .explain(
                    "while (access.contains(\"temp01\")) {")
                .xp(20))
            .mistakes(
                new String[]{"One remove for a repeated value",
                    "Only the first match goes."},
                new String[]{"Reusing an index after remove",
                    "Everything behind the gap moved down one."},
                new String[]{"Ignoring what remove returns",
                    "false means nothing was there to remove."})
            .cyber(
                "Deprovisioning - removing access when someone leaves - is "
                + "one of the most audited controls in any organisation, "
                + "because forgotten accounts are a favourite way in. The "
                + "code must remove EVERY entry, and the audit must prove "
                + "it: contains afterwards, or a false from remove, is the "
                + "evidence. An account that survives on a second line of "
                + "the list is still an open door.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<String> a = new java.util.ArrayList<>();",
                    "a.add(\"web\");",
                    "System.out.println(a.indexOf(\"db\") + \" \" + a.remove(\"db\"));")
                .accept("-1 false")
                .hints("db is not in the list.",
                       "Nothing found: -1, and nothing removed.")
                .explain(
                    "-1 false. indexOf reports 'not found' with -1, and "
                    + "remove returns false because there was nothing to "
                    + "take out.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "How does contains decide that two Strings match?")
                .choices("With ==",
                         "With equals",
                         "With equalsIgnoreCase",
                         "By length")
                .accept("2", "b")
                .hints("It compares the text, not where it is kept.",
                       "Case still matters.")
                .explain(
                    "b. The list methods use equals: same characters, same "
                    + "case. \"Root\" does not match \"root\".")
                .xp(10))
            .recap(
                "    list.contains(x)     true / false\n"
                + "    list.indexOf(x)      first position, or -1\n"
                + "    list.remove(x)       first match; true if found\n"
                + "    list.remove(i)       by index; returns the item\n"
                + "\n"
                + "All compare with equals. After a remove, the rest move "
                + "down one.")
            .next("Next: looping over a list - and removing safely."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(21), "Walking a List", 4)
            .brief(
                "Every hour, expired sessions must be cleared from the "
                + "active list. The first version looped forward and removed "
                + "each expired one - and the next audit still found expired "
                + "sessions in the list. Nothing crashed. The loop simply "
                + "stepped over some of them.")
            .willLearn("Looping over an ArrayList")
            .whyUseful(
                "Reading every item of a list is easy - both loops you know "
                + "work. Removing items WHILE looping is where lists bite, "
                + "and the safe way to do it is short once you see why the "
                + "obvious way fails.")
            .concept("Looping over an ArrayList",
                "Both loops work for READING a list:\n"
                + "\n"
                + "    for (String s : sessions) { ... s ... }\n"
                + "\n"
                + "    for (int i = 0; i < sessions.size(); i++) {\n"
                + "        ... sessions.get(i) ...\n"
                + "    }\n"
                + "\n"
                + "REMOVING while looping is different.\n"
                + "\n"
                + "Enhanced for: Java forbids changing a list while an "
                + "enhanced for walks it, and usually stops the program with "
                + "a ConcurrentModificationException.\n"
                + "\n"
                + "Forward index loop: no crash, but a silent skip. Remove "
                + "index 1 and the item from index 2 slides into index 1 - "
                + "then i++ moves on to 2, and the slid item is never "
                + "checked.\n"
                + "\n"
                + "    before   [ok, EXP, EXP, ok]   i = 1: remove\n"
                + "    after    [ok, EXP, ok]        i = 2: skipped one\n"
                + "\n"
                + "The fix: walk BACKWARDS. Removing at i only moves items "
                + "that come after i - and those have already been "
                + "checked:\n"
                + "\n"
                + "    for (int i = list.size() - 1; i >= 0; i--) {\n"
                + "        if (should go) {\n"
                + "            list.remove(i);\n"
                + "        }\n"
                + "    }")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> a = new ArrayList<>();",
                "        ArrayList<String> b = new ArrayList<>();",
                "        String[] start = {\"ok\", \"EXP\", \"EXP\", \"ok\", \"EXP\"};",
                "        for (String s : start) {",
                "            a.add(s);",
                "            b.add(s);",
                "        }",
                "        for (int i = 0; i < a.size(); i++) {",
                "            if (a.get(i).equals(\"EXP\")) {",
                "                a.remove(i);",
                "            }",
                "        }",
                "        for (int i = b.size() - 1; i >= 0; i--) {",
                "            if (b.get(i).equals(\"EXP\")) {",
                "                b.remove(i);",
                "            }",
                "        }",
                "        System.out.println(\"Forward:   \" + a);",
                "        System.out.println(\"Backwards: \" + b);",
                "    }",
                "}")
            .exampleOutput(
                "Forward:   [ok, EXP, ok]",
                "Backwards: [ok, ok]")
            .lineByLine(
                new String[]{"the forward loop",
                    "Removing index 1 slides the second EXP into index 1, "
                    + "and i++ steps past it."},
                new String[]{"i = b.size() - 1; i >= 0; i--",
                    "Starts at the last item and works towards the front."},
                new String[]{"b.remove(i)",
                    "Only items already checked move. Nothing is skipped."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<String> h = new java.util.ArrayList<>();",
                    "h.add(\"web\");",
                    "h.add(\"db\");",
                    "h.add(\"fw\");",
                    "int total = 0;",
                    "for (String s : h) {",
                    "    total += s.length();",
                    "}",
                    "System.out.println(total);")
                .accept("7")
                .hints("Reading with an enhanced for is fine.",
                       "3 + 2 + 2.")
                .explain(
                    "7. The loop reads each name's length. Reading a list "
                    + "with an enhanced for is safe; only changing it during "
                    + "the loop is not.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "A forward index loop removes items as it goes. What "
                    + "goes wrong?")
                .choices("It never compiles",
                         "It removes every item",
                         "The item after each removal is not checked",
                         "It checks the last item twice")
                .accept("3", "c")
                .hints("What moves into the gap?",
                       "Then what does i++ do?")
                .explain(
                    "c. The next item slides into the removed slot, and i++ "
                    + "moves past it - so it is never tested.")
                .xp(15))
            .objective(
                "Clear every expired session.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> sessions = new ArrayList<>();",
                "        sessions.add(\"s1:EXP\");",
                "        sessions.add(\"s2:EXP\");",
                "        sessions.add(\"s3:LIVE\");",
                "        sessions.add(\"s4:EXP\");",
                "        // the for header: from the last index down to 0",
                "            if (sessions.get(i).endsWith(\":EXP\")) {",
                "                sessions.remove(i);",
                "            }",
                "        }",
                "        System.out.println(sessions);",
                "    }",
                "}")
            .yourTask(
                "Write the for header that walks sessions backwards: i "
                + "starts at the last index and goes down to 0.")
            .mainTask(new Task(Task.WRITE,
                    "Write the for header.")
                .accept("for (int i = sessions.size() - 1; i >= 0; i--) {",
                        "for(int i = sessions.size() - 1; i >= 0; i--) {",
                        "for (int i = sessions.size() - 1; i >= 0; i--){",
                        "for (int i = sessions.size() - 1; i > -1; i--) {")
                .hints(
                    "The last index is size() - 1.",
                    "Keep going while i >= 0, and count down.",
                    "for (int i = sessions.size() - 1; i >= 0; i--) {")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        ArrayList<String> sessions = new ArrayList<>();",
                    "        sessions.add(\"s1:EXP\");",
                    "        sessions.add(\"s2:EXP\");",
                    "        sessions.add(\"s3:LIVE\");",
                    "        sessions.add(\"s4:EXP\");",
                    "        for (int i = sessions.size() - 1; i >= 0; i--) {",
                    "            if (sessions.get(i).endsWith(\":EXP\")) {",
                    "                sessions.remove(i);",
                    "            }",
                    "        }",
                    "        System.out.println(sessions);",
                    "    }",
                    "}")
                .whyItWorks(
                    "i visits 3, 2, 1, 0. s4 goes first; s3 stays; then s2 "
                    + "and s1 go. Each removal only shifts items that were "
                    + "already checked, so all three expired sessions are "
                    + "cleared: [s3:LIVE].\n"
                    + "\n"
                    + "Looping forward, s2 would slide into index 0 just "
                    + "after s1 was removed, and survive.")
                .explain(
                    "for (int i = sessions.size() - 1; i >= 0; i--) {")
                .xp(20))
            .mistakes(
                new String[]{"Removing inside an enhanced for",
                    "ConcurrentModificationException."},
                new String[]{"Removing in a forward index loop",
                    "Skips the item after each removal."},
                new String[]{"Starting at size()",
                    "The last index is size() - 1."})
            .cyber(
                "Clean-up code is security code: expired sessions, revoked "
                + "tokens, stale firewall rules. A clean-up that silently "
                + "skips entries leaves exactly the ones that should be "
                + "gone - a session token that still works after expiry is "
                + "a gift to anyone who stole it. Walk backwards when you "
                + "remove, and check the result afterwards.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<String> a = new java.util.ArrayList<>();",
                    "a.add(\"x\");",
                    "a.add(\"x\");",
                    "a.add(\"y\");",
                    "for (int i = 0; i < a.size(); i++) {",
                    "    if (a.get(i).equals(\"x\")) {",
                    "        a.remove(i);",
                    "    }",
                    "}",
                    "System.out.println(a);")
                .accept("[x, y]")
                .hints("After removing index 0, what is at index 0?",
                       "i is 1 next.")
                .explain(
                    "[x, y]. The second x slid into index 0 just as i moved "
                    + "on to 1, so it was never checked.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which loop can safely remove items from a list?")
                .choices("for (String s : list)",
                         "for (int i = 0; i < list.size(); i++)",
                         "for (int i = list.size() - 1; i >= 0; i--)",
                         "None of them")
                .accept("3", "c")
                .hints("Which one only shifts checked items?",
                       "Backwards.")
                .explain(
                    "c. Going backwards, a removal only moves items behind "
                    + "i, which have already been checked.")
                .xp(10))
            .recap(
                "    reading:   either loop\n"
                + "    removing:  for (int i = list.size() - 1; i >= 0; i--)\n"
                + "\n"
                + "Never remove inside an enhanced for. A forward index loop "
                + "skips the item after each removal.")
            .next("Next: why a list of numbers says Integer, not int."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(22), "Numbers in a List", 4)
            .brief(
                "The port monitor wants a growing list of every port that "
                + "received traffic. ArrayList<int> will not compile. Lists "
                + "hold objects, and int is not one - so Java wraps each "
                + "number in a small object called Integer. Mostly you will "
                + "not notice. The two places you will, matter.")
            .willLearn("Wrapper classes")
            .whyUseful(
                "Lists of numbers are everywhere - ports, counts, response "
                + "times. Knowing what Integer is explains the type you "
                + "write, and the two traps it brings: comparing with == "
                + "and the empty value null.")
            .concept("Wrapper classes",
                "Each primitive type has a WRAPPER CLASS - an object that "
                + "holds one value of it:\n"
                + "\n"
                + "    int      Integer         double   Double\n"
                + "    boolean  Boolean         char     Character\n"
                + "\n"
                + "A list of numbers is written with the wrapper:\n"
                + "\n"
                + "    ArrayList<Integer> ports = new ArrayList<>();\n"
                + "\n"
                + "Java converts between the two by itself:\n"
                + "\n"
                + "    ports.add(443);          int -> Integer (AUTOBOXING)\n"
                + "    int p = ports.get(0);    Integer -> int (UNBOXING)\n"
                + "\n"
                + "So arithmetic, comparisons with < and >, and enhanced for "
                + "loops with int all just work. Two things do not:\n"
                + "\n"
                + "TRAP 1: == on two Integers compares the OBJECTS, like == "
                + "on Strings. Java keeps one shared object for each small "
                + "value (-128 to 127), so == seems to work in testing - "
                + "then fails for 443 or 8080. Use equals, or unbox to int "
                + "first.\n"
                + "\n"
                + "TRAP 2: an Integer can be null. Unboxing null into an int "
                + "crashes with a NullPointerException.\n"
                + "\n"
                + "You have met the wrapper classes before: Integer.parseInt "
                + "is a static method of Integer, and Character.isDigit one "
                + "of Character.")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<Integer> ports = new ArrayList<>();",
                "        ports.add(22);",
                "        ports.add(8080);",
                "        ports.add(8080);",
                "        int total = 0;",
                "        for (int p : ports) {",
                "            total += p;",
                "        }",
                "        System.out.println(ports + \" sum \" + total);",
                "        Integer a = ports.get(1);",
                "        Integer b = ports.get(2);",
                "        System.out.println(\"== says \" + (a == b));",
                "        System.out.println(\"equals says \" + a.equals(b));",
                "        int x = a;",
                "        int y = b;",
                "        System.out.println(\"ints say \" + (x == y));",
                "    }",
                "}")
            .exampleOutput(
                "[22, 8080, 8080] sum 16182",
                "== says false",
                "equals says true",
                "ints say true")
            .lineByLine(
                new String[]{"ArrayList<Integer>",
                    "The wrapper in the angle brackets; int is not allowed "
                    + "there."},
                new String[]{"ports.add(22);",
                    "Autoboxing: the int 22 is wrapped for you."},
                new String[]{"a == b",
                    "Two separate Integer objects that both hold 8080: "
                    + "false."},
                new String[]{"int x = a;",
                    "Unboxing. Two ints compare by value, so == is right "
                    + "again."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<Integer> t = new java.util.ArrayList<>();",
                    "t.add(120);",
                    "t.add(80);",
                    "int first = t.get(0);",
                    "System.out.println(first + t.get(1));")
                .accept("200")
                .hints("Unboxed Integers are just numbers.",
                       "120 + 80.")
                .explain(
                    "200. get(1) is unboxed for the addition, so + adds the "
                    + "two numbers.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "java.util.ArrayList<Integer> a = new java.util.ArrayList<>();",
                    "java.util.ArrayList<int> b = new java.util.ArrayList<>();",
                    "a.add(5);",
                    "System.out.println(a);")
                .accept("2", "line 2")
                .hints("What may go in the angle brackets?",
                       "Only object types.")
                .explain(
                    "Line 2: 'unexpected type'. A list's type must be a "
                    + "class, so a list of ints is ArrayList<Integer>.")
                .xp(20))
            .objective(
                "Check a port against the list - correctly for any number.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<Integer> open = new ArrayList<>();",
                "        open.add(443);",
                "        open.add(8443);",
                "        Integer seen = 8443;",
                "        // the if: open.get(1) and seen hold the same number",
                "            System.out.println(\"Port \" + seen + \" is expected\");",
                "        } else {",
                "            System.out.println(\"Port \" + seen + \" is NOT expected\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if that compares open.get(1) with seen by VALUE, "
                + "using equals.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line.")
                .accept("if (open.get(1).equals(seen)) {",
                        "if(open.get(1).equals(seen)) {",
                        "if (open.get(1).equals(seen)){",
                        "if (seen.equals(open.get(1))) {")
                .hints(
                    "== would compare two objects.",
                    "Call equals on one Integer, passing the other.",
                    "if (open.get(1).equals(seen)) {")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        ArrayList<Integer> open = new ArrayList<>();",
                    "        open.add(443);",
                    "        open.add(8443);",
                    "        Integer seen = 8443;",
                    "        if (open.get(1).equals(seen)) {",
                    "            System.out.println(\"Port \" + seen + \" is expected\");",
                    "        } else {",
                    "            System.out.println(\"Port \" + seen + \" is NOT expected\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "equals compares the numbers inside the two Integer "
                    + "objects: 8443 and 8443, so it prints 'Port 8443 is "
                    + "expected'.\n"
                    + "\n"
                    + "With == the two objects are different, and a port that "
                    + "IS on the list would be reported as unexpected. The "
                    + "same test with port 80 would have passed with == - "
                    + "which is how this bug survives testing.")
                .explain(
                    "if (open.get(1).equals(seen)) { - compares the values.")
                .xp(20))
            .mistakes(
                new String[]{"ArrayList<int>",
                    "Use the wrapper: ArrayList<Integer>."},
                new String[]{"== between two Integers",
                    "Right for small numbers only. Use equals."},
                new String[]{"Unboxing a null",
                    "NullPointerException. Check for null first."})
            .cyber(
                "The == trap is dangerous because it passes the tests. "
                + "Checks written against small test values - user 1, "
                + "port 22, role 3 - behave, and the same code compares "
                + "wrongly in production with user 48213 or port 8443. An "
                + "access check that fails wrongly locks people out; one "
                + "written as 'not equal means allowed' lets them in. Use "
                + "equals, or compare ints.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "Integer a = 100;",
                    "Integer b = 100;",
                    "Integer c = 1000;",
                    "Integer d = 1000;",
                    "System.out.println((a == b) + \" \" + (c == d));")
                .accept("true false")
                .hints("Small values share one object.",
                       "1000 is outside -128 to 127.")
                .explain(
                    "true false. 100 comes from Java's shared set of small "
                    + "Integers, so a and b are the same object; 1000 is not, "
                    + "so c and d are two objects. This is why == only "
                    + "SEEMS to work.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "What is the wrapper class for double?")
                .choices("Dbl", "Double", "double", "Float")
                .accept("2", "b")
                .hints("Wrapper names start with a capital.",
                       "Most are just the primitive name, capitalised.")
                .explain(
                    "b. Double. Only int (Integer) and char (Character) have "
                    + "wrapper names that are not simply capitalised.")
                .xp(10))
            .recap(
                "    ArrayList<Integer>    not ArrayList<int>\n"
                + "    add(5)                autoboxing\n"
                + "    int x = list.get(0)   unboxing\n"
                + "    a.equals(b)           compare Integers by value\n"
                + "\n"
                + "== on Integers is only safe for -128 to 127. Unboxing "
                + "null crashes.")
            .next("Next: the trap in remove with a list of numbers."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(23), "The remove(int) Trap", 4)
            .brief(
                "Port 1 was closed, so the engineer wrote ports.remove(1) "
                + "to take it off the open-ports list. The code ran without "
                + "complaint - and port 80 vanished instead, while port 1 "
                + "stayed. With a list of Integers, remove has two meanings, "
                + "and Java picked the other one.")
            .willLearn("The remove(int) trap")
            .whyUseful(
                "This is one of the best-known traps in Java: it compiles, "
                + "it usually runs, and it removes the wrong thing. Once you "
                + "know the rule behind it, the fix is one word.")
            .concept("The remove(int) trap",
                "Mission 20 met remove's two forms:\n"
                + "\n"
                + "    remove(int index)       removes the item AT index\n"
                + "    remove(Object value)    removes the first match\n"
                + "\n"
                + "With a list of Strings, remove(\"temp01\") can only mean "
                + "the value. With a list of Integers, remove(1) could mean "
                + "either - and Java always picks the int form, because 1 "
                + "is an int and needs no boxing. So:\n"
                + "\n"
                + "    ports = [1, 80, 443]\n"
                + "    ports.remove(1)     removes INDEX 1: the 80\n"
                + "    ports.remove(443)   index 443: crash\n"
                + "\n"
                + "To remove a VALUE, hand remove an Integer object, not an "
                + "int:\n"
                + "\n"
                + "    ports.remove(Integer.valueOf(1))    removes the 1\n"
                + "\n"
                + "Integer.valueOf(1) makes the wrapped value, so the Object "
                + "form is the one chosen.\n"
                + "\n"
                + "contains and indexOf have no second form, so contains(1) "
                + "and indexOf(1) do look for the value 1. Only remove is a "
                + "trap.")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<Integer> ports = new ArrayList<>();",
                "        ports.add(1);",
                "        ports.add(80);",
                "        ports.add(443);",
                "        ports.remove(1);",
                "        System.out.println(\"remove(1):  \" + ports);",
                "        ports.add(1, 80);",
                "        ports.remove(Integer.valueOf(1));",
                "        System.out.println(\"valueOf(1): \" + ports);",
                "    }",
                "}")
            .exampleOutput(
                "remove(1):  [1, 443]",
                "valueOf(1): [80, 443]")
            .lineByLine(
                new String[]{"ports.remove(1);",
                    "1 is an int, so it is taken as an INDEX: the 80 goes."},
                new String[]{"ports.add(1, 80);",
                    "Puts 80 back at index 1 to try again."},
                new String[]{"ports.remove(Integer.valueOf(1));",
                    "An Integer object is a VALUE: the first 1 goes."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<Integer> a = new java.util.ArrayList<>();",
                    "a.add(5);",
                    "a.add(0);",
                    "a.add(9);",
                    "a.remove(0);",
                    "System.out.println(a);")
                .accept("[0, 9]")
                .hints("remove(0) with an int argument...",
                       "...removes index 0.")
                .explain(
                    "[0, 9]. The 5 at index 0 went; the value 0 is still "
                    + "there. Integer.valueOf(0) would have removed the 0.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "ids is [7, 3, 12]. Which call removes the value 3?")
                .choices("ids.remove(3)",
                         "ids.remove(1)",
                         "ids.remove(Integer.valueOf(3))",
                         "Both b and c")
                .accept("4", "d")
                .hints("remove(1) removes index 1 - what is there?",
                       "valueOf(3) removes the value 3.")
                .explain(
                    "d. 3 happens to sit at index 1, so both work here. But "
                    + "only c says what it means - and a would crash, since "
                    + "index 3 does not exist.")
                .xp(15))
            .objective(
                "Close a port by its number, not its position.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<Integer> open = new ArrayList<>();",
                "        open.add(2);",
                "        open.add(22);",
                "        open.add(0);",
                "        int closing = 2;",
                "        // remove the VALUE closing from open",
                "        System.out.println(open);",
                "    }",
                "}")
            .yourTask(
                "Write the line that removes the port number held in "
                + "closing - the value, not the item at that index.")
            .mainTask(new Task(Task.WRITE,
                    "Write the remove line.")
                .accept("open.remove(Integer.valueOf(closing));",
                        "open.remove((Integer) closing);")
                .hints(
                    "open.remove(closing) would remove index 2.",
                    "Wrap closing in an Integer first.",
                    "open.remove(Integer.valueOf(closing));")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        ArrayList<Integer> open = new ArrayList<>();",
                    "        open.add(2);",
                    "        open.add(22);",
                    "        open.add(0);",
                    "        int closing = 2;",
                    "        open.remove(Integer.valueOf(closing));",
                    "        System.out.println(open);",
                    "    }",
                    "}")
                .whyItWorks(
                    "Integer.valueOf(closing) is an Integer holding 2, so "
                    + "remove looks for the value 2 and takes it out: "
                    + "[22, 0].\n"
                    + "\n"
                    + "open.remove(closing) would also compile and run - but "
                    + "it removes index 2, the 0, and prints [2, 22]. Port 2 "
                    + "stays open and a different entry disappears.")
                .explain(
                    "open.remove(Integer.valueOf(closing)); - by value.")
                .xp(20))
            .mistakes(
                new String[]{"remove(port) on an Integer list",
                    "Removes the item at that INDEX."},
                new String[]{"Assuming contains has the trap",
                    "contains and indexOf always look for the value."},
                new String[]{"Testing with values that are also indexes",
                    "The bug hides. Test with 443 and it crashes."})
            .cyber(
                "A firewall tool that removes the wrong entry does two "
                + "kinds of damage at once: the port that should be closed "
                + "stays open, and an unrelated rule silently disappears. "
                + "No error is raised, so nothing prompts anyone to look. "
                + "Code that edits security lists should say exactly what "
                + "it removes, and check the list afterwards.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<Integer> a = new java.util.ArrayList<>();",
                    "a.add(10);",
                    "a.add(20);",
                    "System.out.println(a.contains(20) + \" \" + a.indexOf(10));")
                .accept("true 0")
                .hints("contains and indexOf look for values.",
                       "10 is at index 0.")
                .explain(
                    "true 0. Unlike remove, contains and indexOf have no "
                    + "index form, so they always search for the value.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Why does list.remove(1) on an ArrayList<Integer> "
                    + "remove by index?")
                .choices("Lists cannot remove values",
                         "1 is an int, and remove(int index) fits it without "
                         + "boxing",
                         "Java picks at random",
                         "Integers are always indexes")
                .accept("2", "b")
                .hints("Which form needs no conversion?",
                       "An int fits remove(int) exactly.")
                .explain(
                    "b. Java prefers the form that fits the argument as it "
                    + "is. An int matches remove(int index) directly; the "
                    + "value form would need boxing first.")
                .xp(10))
            .recap(
                "    list.remove(i)                   by INDEX (int)\n"
                + "    list.remove(Integer.valueOf(v))  by VALUE\n"
                + "\n"
                + "Only a list of Integers has this trap. contains and "
                + "indexOf always search by value.")
            .next("Next: choosing between an array and a list."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(24), "Array or List?", 3)
            .brief(
                "Two new tools are being designed. One keeps a count for "
                + "each of the 24 hours of the day. The other collects every "
                + "address that failed a check tonight. Both are 'many "
                + "values', but the right container is different - and "
                + "real programs often move data from one to the other.")
            .willLearn("Arrays or ArrayList")
            .whyUseful(
                "Choosing the right container makes code shorter and "
                + "clearer. And since split and many library methods give "
                + "back arrays, turning an array into a list - and back - "
                + "is a daily job.")
            .concept("Arrays or ArrayList",
                "Choose by asking what the data does:\n"
                + "\n"
                + "    ARRAY when                 ARRAYLIST when\n"
                + "    the size is fixed          the size changes\n"
                + "    and known up front         as you go\n"
                + "    primitives, like int[]     adding and removing\n"
                + "    a grid: int[][]            searching with contains\n"
                + "\n"
                + "24 hours, 7 days, the 4 fields of a log line: arrays. "
                + "Tonight's failures, a watchlist, an incident queue: "
                + "lists.\n"
                + "\n"
                + "ARRAY TO LIST - add each item in a loop, often filtering "
                + "as you go:\n"
                + "\n"
                + "    for (String p : parts) {\n"
                + "        if (!p.isEmpty()) {\n"
                + "            list.add(p);\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "LIST TO ARRAY - make an array of the list's size, then "
                + "copy by index:\n"
                + "\n"
                + "    int[] out = new int[list.size()];\n"
                + "    for (int i = 0; i < list.size(); i++) {\n"
                + "        out[i] = list.get(i);\n"
                + "    }\n"
                + "\n"
                + "A common shape: collect into a list while the size is "
                + "unknown, then turn it into an array once it is final.")
            .example(
                "import java.util.ArrayList;",
                "import java.util.Arrays;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String line = \"22,,80,443,,8080\";",
                "        String[] parts = line.split(\",\");",
                "        ArrayList<Integer> ports = new ArrayList<>();",
                "        for (String p : parts) {",
                "            if (!p.isEmpty()) {",
                "                ports.add(Integer.parseInt(p));",
                "            }",
                "        }",
                "        int[] fixed = new int[ports.size()];",
                "        for (int i = 0; i < ports.size(); i++) {",
                "            fixed[i] = ports.get(i);",
                "        }",
                "        System.out.println(parts.length + \" pieces\");",
                "        System.out.println(ports.size() + \" ports: \" + ports);",
                "        System.out.println(Arrays.toString(fixed));",
                "    }",
                "}")
            .exampleOutput(
                "6 pieces",
                "4 ports: [22, 80, 443, 8080]",
                "[22, 80, 443, 8080]")
            .lineByLine(
                new String[]{"line.split(\",\")",
                    "Six pieces, two of them empty (mission 17)."},
                new String[]{"if (!p.isEmpty())",
                    "Skips the empty pieces, so only real ports go in."},
                new String[]{"new int[ports.size()]",
                    "Now the count is known, a fixed array fits exactly."},
                new String[]{"fixed[i] = ports.get(i);",
                    "Each Integer is unboxed into an int slot."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] words = {\"a\", \"\", \"b\", \"\"};",
                    "java.util.ArrayList<String> kept = new java.util.ArrayList<>();",
                    "for (String w : words) {",
                    "    if (!w.isEmpty()) {",
                    "        kept.add(w);",
                    "    }",
                    "}",
                    "System.out.println(words.length + \" \" + kept.size());")
                .accept("4 2")
                .hints("The array keeps every slot.",
                       "The list only got the non-empty ones.")
                .explain(
                    "4 2. The array still has 4 slots; the list grew to "
                    + "exactly the 2 items that passed the filter.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Which is the best fit for an ARRAY?")
                .choices("Accounts to disable, found during a scan",
                         "Failed logins per day of the week",
                         "Alerts waiting for an analyst",
                         "Addresses added to a blocklist over time")
                .accept("2", "b")
                .hints("Which has a size fixed in advance?",
                       "There are always 7 days.")
                .explain(
                    "b. Seven days, always - a tally array of 7 ints. The "
                    + "others grow and shrink, which is a list's job.")
                .xp(10))
            .objective(
                "Collect the flagged users into a list.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] users = {\"ann\", \"bob\", \"cy\", \"dee\"};",
                "        int[] fails = {0, 6, 1, 9};",
                "        ArrayList<String> flagged = new ArrayList<>();",
                "        for (int i = 0; i < users.length; i++) {",
                "            if (fails[i] > 5) {",
                "                // add this user to flagged",
                "            }",
                "        }",
                "        System.out.println(flagged);",
                "    }",
                "}")
            .yourTask(
                "Write the line that adds the user at index i to the list "
                + "flagged.")
            .mainTask(new Task(Task.WRITE,
                    "Write the add line.")
                .accept("flagged.add(users[i]);")
                .hints(
                    "Lists grow with add.",
                    "The user at index i is users[i].",
                    "flagged.add(users[i]);")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String[] users = {\"ann\", \"bob\", \"cy\", \"dee\"};",
                    "        int[] fails = {0, 6, 1, 9};",
                    "        ArrayList<String> flagged = new ArrayList<>();",
                    "        for (int i = 0; i < users.length; i++) {",
                    "            if (fails[i] > 5) {",
                    "                flagged.add(users[i]);",
                    "            }",
                    "        }",
                    "        System.out.println(flagged);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The arrays are fixed data, read by index. The list "
                    + "starts empty and gains an entry only when a user is "
                    + "over the limit: [bob, dee].\n"
                    + "\n"
                    + "An array for the result would need its size before "
                    + "the loop - which is exactly what nobody knows yet.")
                .explain(
                    "flagged.add(users[i]); - the list grows by one.")
                .xp(15))
            .mistakes(
                new String[]{"An array for results of unknown size",
                    "Guessing a size wastes slots or runs out."},
                new String[]{"A list for a fixed tally",
                    "int[24] is simpler and clearer."},
                new String[]{"out[i] = list.get(i) into a too-small array",
                    "Size it from list.size()."})
            .cyber(
                "Collection choice shows up in security reviews. A fixed "
                + "array for findings of unknown size either drops results "
                + "when it fills - silently losing alerts - or crashes. A "
                + "list with no limit can be grown by an attacker until "
                + "memory runs out. Pick the container for how the data "
                + "really behaves, and put a sensible cap on anything that "
                + "grows with outside input.")
            .check(new Task(Task.CHOICE,
                    "How do you make an int[] from an ArrayList<Integer> "
                    + "called list?")
                .choices("int[] a = list;",
                         "int[] a = new int[list.size()]; then copy with a "
                         + "loop",
                         "int[] a = list.toString();",
                         "int[] a = new int[list.length];")
                .accept("2", "b")
                .hints("A list is not an array.",
                       "Size the array, then copy each item.")
                .explain(
                    "b. The array is made at the list's size, then filled "
                    + "with a[i] = list.get(i). d fails: lists have size(), "
                    + "not length.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which is the best fit for an ArrayList?")
                .choices("The 4 fields of one log line",
                         "Counts for each of 24 hours",
                         "Usernames that fail tonight's password audit",
                         "A 7 by 24 grid of logins")
                .accept("3", "c")
                .hints("Which one's size is unknown?",
                       "Nobody knows how many will fail.")
                .explain(
                    "c. How many fail is unknown until the audit runs. The "
                    + "others have sizes fixed in advance.")
                .xp(10))
            .recap(
                "    fixed size, primitives, grids   array\n"
                + "    grows and shrinks, searching    ArrayList\n"
                + "\n"
                + "Array to list: loop and add. List to array: new "
                + "int[list.size()], then copy by index.")
            .next("Next: putting things in order."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(25), "Putting Things in Order", 4)
            .brief(
                "The performance review wants the median response time - "
                + "the middle one when they are in order - and the account "
                + "list printed alphabetically. Sorting by hand is a "
                + "classic exercise for later. Java's library already sorts "
                + "arrays and lists in one call, with two surprises worth "
                + "knowing.")
            .willLearn("Sorting")
            .whyUseful(
                "Sorted data makes the smallest, the largest, the middle "
                + "and the duplicates easy to find, and sorted reports are "
                + "far easier to read. The library sort is fast and "
                + "correct - but it sorts text in a way people do not "
                + "expect.")
            .concept("Sorting",
                "Arrays and lists each have a sort, in their own helper "
                + "class:\n"
                + "\n"
                + "    import java.util.Arrays;\n"
                + "    Arrays.sort(ms);            an array, in place\n"
                + "\n"
                + "    import java.util.Collections;\n"
                + "    Collections.sort(users);    a list, in place\n"
                + "\n"
                + "Both sort smallest first, and both change the ORIGINAL - "
                + "nothing is returned. If the old order matters (a log's "
                + "order is its timeline), sort a copy (mission 11).\n"
                + "\n"
                + "Numbers sort as numbers. Text sorts character by "
                + "character, by character code, which brings two "
                + "surprises:\n"
                + "\n"
                + "    capitals first    [Zed, alice, bob]\n"
                + "    digits as text    [10, 100, 9]\n"
                + "\n"
                + "Every capital letter comes before every small letter, and "
                + "\"100\" comes before \"9\" because '1' comes before '9'. "
                + "Numbers stored as text must be parsed to sort as "
                + "numbers.\n"
                + "\n"
                + "Parallel arrays (mission 15) cannot be sorted this way: "
                + "sorting one array leaves the other behind.")
            .example(
                "import java.util.ArrayList;",
                "import java.util.Arrays;",
                "import java.util.Collections;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int[] ms = {310, 95, 120, 88, 142};",
                "        int[] sorted = Arrays.copyOf(ms, ms.length);",
                "        Arrays.sort(sorted);",
                "        System.out.println(\"Sorted: \" + Arrays.toString(sorted));",
                "        System.out.println(\"Median: \" + sorted[sorted.length / 2]);",
                "        System.out.println(\"Log order: \" + Arrays.toString(ms));",
                "        ArrayList<String> users = new ArrayList<>();",
                "        users.add(\"mpatel\");",
                "        users.add(\"Zed\");",
                "        users.add(\"alice\");",
                "        Collections.sort(users);",
                "        System.out.println(users);",
                "    }",
                "}")
            .exampleOutput(
                "Sorted: [88, 95, 120, 142, 310]",
                "Median: 120",
                "Log order: [310, 95, 120, 88, 142]",
                "[Zed, alice, mpatel]")
            .lineByLine(
                new String[]{"Arrays.sort(sorted);",
                    "Sorts the copy in place. Nothing is returned."},
                new String[]{"sorted[sorted.length / 2]",
                    "5 / 2 is 2: the middle of five sorted values."},
                new String[]{"Log order: ...",
                    "The original is untouched, because a copy was sorted."},
                new String[]{"[Zed, alice, mpatel]",
                    "Capital Z sorts before every small letter."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] v = {\"9\", \"10\", \"100\"};",
                    "java.util.Arrays.sort(v);",
                    "System.out.println(java.util.Arrays.toString(v));")
                .accept("[10, 100, 9]")
                .hints("These are Strings, compared character by character.",
                       "'1' comes before '9'.")
                .explain(
                    "[10, 100, 9]. Text is compared from the first "
                    + "character: both 1s beat 9, and \"10\" is a prefix of "
                    + "\"100\", so it comes first.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {5, 1, 4};",
                    "int[] b = a;",
                    "java.util.Arrays.sort(b);",
                    "System.out.println(a[0]);")
                .accept("1")
                .hints("Is b a copy? (mission 10)",
                       "b = a shares one array.")
                .explain(
                    "1. b is just another name for a's array, so sorting "
                    + "\"b\" sorted a. A real copy needs Arrays.copyOf.")
                .xp(20))
            .objective(
                "Print the allowlist in order.")
            .starter(
                "import java.util.ArrayList;",
                "import java.util.Collections;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> allow = new ArrayList<>();",
                "        allow.add(\"web02\");",
                "        allow.add(\"db01\");",
                "        allow.add(\"web01\");",
                "        // sort allow",
                "        System.out.println(allow);",
                "    }",
                "}")
            .yourTask(
                "Write the line that sorts the list allow.")
            .mainTask(new Task(Task.WRITE,
                    "Write the sort line.")
                .accept("Collections.sort(allow);")
                .hints(
                    "Lists are sorted by Collections, not Arrays.",
                    "It takes the list and returns nothing.",
                    "Collections.sort(allow);")
                .solution(
                    "import java.util.ArrayList;",
                    "import java.util.Collections;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        ArrayList<String> allow = new ArrayList<>();",
                    "        allow.add(\"web02\");",
                    "        allow.add(\"db01\");",
                    "        allow.add(\"web01\");",
                    "        Collections.sort(allow);",
                    "        System.out.println(allow);",
                    "    }",
                    "}")
                .whyItWorks(
                    "Collections.sort rearranges the list itself: [db01, "
                    + "web01, web02]. d comes before w, and web01 before "
                    + "web02 because '1' comes before '2'.\n"
                    + "\n"
                    + "The zero in web01 matters: with names like web2 and "
                    + "web10, text order would put web10 first. Padding "
                    + "numbers in names keeps text order sensible.")
                .explain(
                    "Collections.sort(allow); - sorts the list in place.")
                .xp(15))
            .mistakes(
                new String[]{"allow = Collections.sort(allow);",
                    "sort returns nothing: it changes the list itself."},
                new String[]{"Sorting the original log",
                    "Its order was the timeline. Sort a copy."},
                new String[]{"Sorting numbers kept as text",
                    "\"100\" sorts before \"9\". Parse them first."})
            .cyber(
                "Order is evidence. A log sorted in place has lost the "
                + "sequence of events - which login came before which "
                + "download - and an incident timeline can no longer be "
                + "rebuilt from it. Sort copies for reports, keep the "
                + "original as recorded. And watch text order in reports: "
                + "sorting 'Admin' away from 'admin' can hide that two "
                + "look-alike accounts exist.")
            .check(new Task(Task.CHOICE,
                    "Which call sorts an int[] called times?")
                .choices("Collections.sort(times);",
                         "Arrays.sort(times);",
                         "times.sort();",
                         "times = Arrays.sort(times);")
                .accept("2", "b")
                .hints("Arrays for arrays, Collections for lists.",
                       "sort returns nothing.")
                .explain(
                    "b. Arrays.sort sorts an array in place. d does not "
                    + "compile, because sort returns nothing to assign.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {7, 2, 9, 4};",
                    "java.util.Arrays.sort(a);",
                    "System.out.println(a[0] + \" \" + a[a.length - 1]);")
                .accept("2 9")
                .hints("After sorting, the smallest is first.",
                       "And the largest is last.")
                .explain(
                    "2 9. Once sorted, the minimum and maximum sit at the "
                    + "two ends of the array.")
                .xp(10))
            .recap(
                "    Arrays.sort(array)          import java.util.Arrays\n"
                + "    Collections.sort(list)      import java.util.Collections\n"
                + "\n"
                + "Both sort in place and return nothing. Text: capitals "
                + "first, digits as characters. Sort a copy to keep the "
                + "original order.")
            .next("Next: putting it together - a live watchlist."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(26), "A Live Watchlist", 5)
            .brief(
                "The SOC keeps a watchlist of accounts under observation. "
                + "Analysts send it short commands through the shift: ADD "
                + "an account, DEL one, CHECK whether one is watched. Each "
                + "command is one line of text. Build the watchlist that "
                + "reads them - split, ArrayList and methods, together.")
            .willLearn("Watchlists")
            .whyUseful(
                "Most small security tools are this shape: a list kept in "
                + "memory, changed by commands, asked questions. Writing one "
                + "brings the campaign's list methods together with split "
                + "and the careful checks that make it safe to feed.")
            .concept("Watchlists",
                "A watchlist is a list with RULES about what may go in:\n"
                + "\n"
                + "    no duplicates   ADD checks contains first\n"
                + "    one spelling    names are lower-cased on the way in\n"
                + "    a size cap      a list fed from outside needs one\n"
                + "    full removal    DEL removes every copy\n"
                + "\n"
                + "Each command line is split into a command and a name:\n"
                + "\n"
                + "    String[] parts = line.split(\" \");\n"
                + "    if (parts.length != 2)   reject it - malformed\n"
                + "\n"
                + "Then one method per command keeps main short. Putting the "
                + "rules INSIDE the methods means no caller can forget "
                + "them:\n"
                + "\n"
                + "    static String add(ArrayList<String> w, String n)\n"
                + "        lower-case n; refuse if full or present;\n"
                + "        otherwise add, and report what happened\n"
                + "\n"
                + "The list is passed to each method - and because a list "
                + "variable is a reference (mission 10), the methods change "
                + "the caller's list.\n"
                + "\n"
                + "Every command gets a reply, including the refusals. A "
                + "tool that silently ignores a bad command leaves the "
                + "analyst believing it worked.")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    static final int MAX = 3;",
                "",
                "    public static void main(String[] args) {",
                "        ArrayList<String> watch = new ArrayList<>();",
                "        String[] commands = {\"ADD jsmith\", \"ADD JSmith\",",
                "                \"CHECK jsmith\", \"ADD temp01\", \"DEL jsmith\",",
                "                \"CHECK jsmith\", \"PURGE\"};",
                "        for (String line : commands) {",
                "            System.out.println(line + \" -> \" + run(watch, line));",
                "        }",
                "        System.out.println(\"Watching: \" + watch);",
                "    }",
                "",
                "    static String run(ArrayList<String> w, String line) {",
                "        String[] parts = line.split(\" \");",
                "        if (parts.length != 2) {",
                "            return \"MALFORMED\";",
                "        }",
                "        String name = parts[1].toLowerCase();",
                "        if (parts[0].equals(\"ADD\")) {",
                "            if (w.contains(name)) {",
                "                return \"ALREADY WATCHED\";",
                "            }",
                "            if (w.size() >= MAX) {",
                "                return \"FULL\";",
                "            }",
                "            w.add(name);",
                "            return \"ADDED\";",
                "        }",
                "        if (parts[0].equals(\"DEL\")) {",
                "            boolean found = false;",
                "            while (w.remove(name)) {",
                "                found = true;",
                "            }",
                "            return found ? \"REMOVED\" : \"NOT WATCHED\";",
                "        }",
                "        if (parts[0].equals(\"CHECK\")) {",
                "            return w.contains(name) ? \"WATCHED\" : \"CLEAR\";",
                "        }",
                "        return \"UNKNOWN COMMAND\";",
                "    }",
                "}")
            .exampleOutput(
                "ADD jsmith -> ADDED",
                "ADD JSmith -> ALREADY WATCHED",
                "CHECK jsmith -> WATCHED",
                "ADD temp01 -> ADDED",
                "DEL jsmith -> REMOVED",
                "CHECK jsmith -> CLEAR",
                "PURGE -> MALFORMED",
                "Watching: [temp01]")
            .lineByLine(
                new String[]{"parts.length != 2",
                    "PURGE has one piece: refused before parts[1] is read."},
                new String[]{"parts[1].toLowerCase()",
                    "JSmith and jsmith become one spelling, so the "
                    + "duplicate is caught."},
                new String[]{"while (w.remove(name))",
                    "remove returns true while it finds a copy - so this "
                    + "removes them all."},
                new String[]{"return \"UNKNOWN COMMAND\";",
                    "Anything not recognised is refused out loud."})
            .predict(new Task(Task.PREDICT,
                    "Using the example's run method with an empty list, "
                    + "what does run(watch, \"CHECK a b\") return?")
                .accept("MALFORMED")
                .hints("How many pieces does split give?",
                       "Three pieces is not two.")
                .explain(
                    "MALFORMED. \"CHECK a b\" splits into three pieces, and "
                    + "the length check refuses anything but two.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Why does ADD lower-case the name before checking "
                    + "contains?")
                .choices("Lists cannot hold capitals",
                         "So JSmith and jsmith count as the same account",
                         "It makes the list sort faster",
                         "contains ignores case anyway")
                .accept("2", "b")
                .hints("contains uses equals - which cares about case.",
                       "Two spellings, one person.")
                .explain(
                    "b. contains compares with equals, which is case "
                    + "sensitive. Normalising first stops one account being "
                    + "listed twice under two spellings.")
                .xp(15))
            .objective(
                "Write the watchlist's CHECK method.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> watch = new ArrayList<>();",
                "        watch.add(\"temp01\");",
                "        watch.add(\"svc_old\");",
                "        System.out.println(check(watch, \"TEMP01\"));",
                "        System.out.println(check(watch, \"jsmith\"));",
                "    }",
                "",
                "    static String check(ArrayList<String> w, String n) {",
                "        // return WATCHED if w contains n in lower case,",
                "        // otherwise CLEAR",
                "    }",
                "}")
            .yourTask(
                "Write the single line that returns \"WATCHED\" when w "
                + "contains n.toLowerCase(), and \"CLEAR\" otherwise. The "
                + "ternary operator from Campaign 02 fits it on one line.")
            .mainTask(new Task(Task.WRITE,
                    "Write the return line.")
                .accept("return w.contains(n.toLowerCase()) ? \"WATCHED\" "
                        + ": \"CLEAR\";",
                        "return w.contains(n.toLowerCase())?\"WATCHED\""
                        + ":\"CLEAR\";",
                        "return !w.contains(n.toLowerCase()) ? \"CLEAR\" "
                        + ": \"WATCHED\";")
                .hints(
                    "condition ? valueIfTrue : valueIfFalse",
                    "The condition is w.contains(n.toLowerCase()).",
                    "return w.contains(n.toLowerCase()) ? \"WATCHED\" "
                    + ": \"CLEAR\";")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        ArrayList<String> watch = new ArrayList<>();",
                    "        watch.add(\"temp01\");",
                    "        watch.add(\"svc_old\");",
                    "        System.out.println(check(watch, \"TEMP01\"));",
                    "        System.out.println(check(watch, \"jsmith\"));",
                    "    }",
                    "",
                    "    static String check(ArrayList<String> w, String n) {",
                    "        return w.contains(n.toLowerCase()) ? \"WATCHED\" : \"CLEAR\";",
                    "    }",
                    "}")
                .whyItWorks(
                    "TEMP01 is lower-cased to temp01 before the search, so "
                    + "it is found: WATCHED. jsmith is not on the list: "
                    + "CLEAR.\n"
                    + "\n"
                    + "Without toLowerCase, an analyst typing TEMP01 would be "
                    + "told the account is clear - the most dangerous wrong "
                    + "answer a watchlist can give.")
                .explain(
                    "Normalise first, then search: TEMP01 is found as temp01.")
                .xp(30))
            .mistakes(
                new String[]{"Normalising on ADD but not on CHECK",
                    "Stored and searched spellings must match."},
                new String[]{"No length check on the command",
                    "A one-word line crashes on parts[1]."},
                new String[]{"Silently ignoring bad commands",
                    "Always reply - refusals included."})
            .cyber(
                "Watchlists, blocklists and deny lists fail in the same few "
                + "ways: an entry stored under one spelling and searched "
                + "under another, a removal that leaves a copy behind, a "
                + "list that grows without limit, and a malformed command "
                + "that crashes the tool. Each rule here closes one of "
                + "those. Keeping the rules inside the methods means they "
                + "hold for every caller, not just the careful ones.")
            .check(new Task(Task.CHOICE,
                    "The list is full (MAX entries). What should ADD for a "
                    + "new name do?")
                .choices("Add it anyway",
                         "Remove the oldest entry silently",
                         "Refuse, and say FULL",
                         "Crash")
                .accept("3", "c")
                .hints("The analyst must know it was not added.",
                       "Refuse out loud.")
                .explain(
                    "c. Refusing with a clear reply keeps the cap and tells "
                    + "the analyst to act. Silently dropping an old entry "
                    + "would stop watching someone without anyone knowing.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<String> w = new java.util.ArrayList<>();",
                    "w.add(\"x\");",
                    "w.add(\"x\");",
                    "int n = 0;",
                    "while (w.remove(\"x\")) {",
                    "    n++;",
                    "}",
                    "System.out.println(n + \" \" + w.size());")
                .accept("2 0")
                .hints("remove returns true each time it finds one.",
                       "The third call finds none.")
                .explain(
                    "2 0. Two successful removals, then remove returns false "
                    + "and the loop ends with the list empty.")
                .xp(15))
            .recap(
                "    split the command; check parts.length\n"
                + "    normalise names on the way in AND when searching\n"
                + "    ADD: refuse duplicates and a full list\n"
                + "    DEL: while (list.remove(x)) - every copy\n"
                + "    reply to every command")
            .next("Next: keeping one of each."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(27), "One of Each", 5)
            .brief(
                "Overnight, 9 failed logins came from the internet. Were "
                + "they 9 different attackers, or one address trying 9 "
                + "times? Those are very different incidents. The answer is "
                + "the list of DISTINCT addresses - every one, once.")
            .willLearn("Removing duplicates")
            .whyUseful(
                "'How many different...' is one of the most common "
                + "questions in log analysis: distinct users, sources, "
                + "hosts. Building a list of unique values is the answer, "
                + "and doing it without destroying the original keeps the "
                + "full count too.")
            .concept("Removing duplicates",
                "To keep one of each, build a NEW list and add each value "
                + "only if it is not there yet:\n"
                + "\n"
                + "    ArrayList<String> unique = new ArrayList<>();\n"
                + "    for (String ip : sources) {\n"
                + "        if (!unique.contains(ip)) {\n"
                + "            unique.add(ip);\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "The first time an address appears it goes in; every later "
                + "copy is skipped. The unique list keeps the order of FIRST "
                + "appearance - useful: the first source seen is listed "
                + "first.\n"
                + "\n"
                + "The original is untouched, so both answers are available: "
                + "sources.size() events, from unique.size() sources.\n"
                + "\n"
                + "'Same' needs deciding first. \"Admin\" and \"admin\" are "
                + "different Strings but, for accounts, the same user - "
                + "normalise (toLowerCase, trim) BEFORE the contains check.\n"
                + "\n"
                + "contains checks the whole list each time, so this gets "
                + "slow for very large lists. Campaign 11's HashSet does the "
                + "same job fast; for a night's log, this is fine.")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] sources = {\"203.0.113.9\", \"198.51.100.4\",",
                "                \"203.0.113.9\", \"203.0.113.9\", \"192.0.2.77\",",
                "                \"203.0.113.9\", \"198.51.100.4\", \"203.0.113.9\",",
                "                \"203.0.113.9\"};",
                "        ArrayList<String> unique = new ArrayList<>();",
                "        for (String ip : sources) {",
                "            if (!unique.contains(ip)) {",
                "                unique.add(ip);",
                "            }",
                "        }",
                "        System.out.println(sources.length + \" events from \"",
                "                + unique.size() + \" sources\");",
                "        for (String ip : unique) {",
                "            int n = 0;",
                "            for (String s : sources) {",
                "                if (s.equals(ip)) {",
                "                    n++;",
                "                }",
                "            }",
                "            System.out.println(\"  \" + ip + \"  x\" + n);",
                "        }",
                "    }",
                "}")
            .exampleOutput(
                "9 events from 3 sources",
                "  203.0.113.9  x6",
                "  198.51.100.4  x2",
                "  192.0.2.77  x1")
            .lineByLine(
                new String[]{"if (!unique.contains(ip))",
                    "Only the first copy of each address gets past this."},
                new String[]{"sources.length and unique.size()",
                    "The original keeps every event; the new list has one "
                    + "of each."},
                new String[]{"the inner loop",
                    "For each distinct address, count its copies in the "
                    + "original. One source made two-thirds of the noise."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] u = {\"b\", \"a\", \"b\", \"c\", \"a\"};",
                    "java.util.ArrayList<String> one = new java.util.ArrayList<>();",
                    "for (String s : u) {",
                    "    if (!one.contains(s)) {",
                    "        one.add(s);",
                    "    }",
                    "}",
                    "System.out.println(one);")
                .accept("[b, a, c]")
                .hints("Each value goes in the first time it is seen.",
                       "The order is the order of first appearance.")
                .explain(
                    "[b, a, c]. b first, then a, then c; the later b and a "
                    + "are skipped.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] u = {\"Admin\", \"admin\", \"ADMIN\"};",
                    "java.util.ArrayList<String> one = new java.util.ArrayList<>();",
                    "for (String s : u) {",
                    "    if (!one.contains(s)) {",
                    "        one.add(s);",
                    "    }",
                    "}",
                    "System.out.println(one.size());")
                .accept("3")
                .hints("contains uses equals.",
                       "Is \"Admin\" equal to \"admin\"?")
                .explain(
                    "3 - one account counted three times. Adding "
                    + "s.toLowerCase() and checking that would give 1.")
                .xp(20))
            .objective(
                "Count the distinct users behind a burst of alerts.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] users = {\"jsmith\", \"JSmith\", \"mpatel\",",
                "                \" jsmith\", \"mpatel\"};",
                "        ArrayList<String> distinct = new ArrayList<>();",
                "        for (String u : users) {",
                "            String name = u.trim().toLowerCase();",
                "            // the if: distinct does not contain name yet",
                "                distinct.add(name);",
                "            }",
                "        }",
                "        System.out.println(users.length + \" alerts, \"",
                "                + distinct.size() + \" users: \" + distinct);",
                "    }",
                "}")
            .yourTask(
                "Write the if that lets name through only when distinct "
                + "does not already contain it.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line.")
                .accept("if (!distinct.contains(name)) {",
                        "if(!distinct.contains(name)) {",
                        "if (!distinct.contains(name)){",
                        "if (distinct.contains(name) == false) {",
                        "if (distinct.indexOf(name) == -1) {")
                .hints(
                    "contains answers 'already there?'.",
                    "You want the opposite: !",
                    "if (!distinct.contains(name)) {")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String[] users = {\"jsmith\", \"JSmith\", \"mpatel\",",
                    "                \" jsmith\", \"mpatel\"};",
                    "        ArrayList<String> distinct = new ArrayList<>();",
                    "        for (String u : users) {",
                    "            String name = u.trim().toLowerCase();",
                    "            if (!distinct.contains(name)) {",
                    "                distinct.add(name);",
                    "            }",
                    "        }",
                    "        System.out.println(users.length + \" alerts, \"",
                    "                + distinct.size() + \" users: \" + distinct);",
                    "    }",
                    "}")
                .whyItWorks(
                    "Each name is trimmed and lower-cased first, so \"JSmith\" "
                    + "and \" jsmith\" both become \"jsmith\" - already in the "
                    + "list, so skipped. Output: 5 alerts, 2 users: [jsmith, "
                    + "mpatel].\n"
                    + "\n"
                    + "Without the normalising line, the same person would "
                    + "count as three users, and the report would overstate "
                    + "the incident.")
                .explain(
                    "if (!distinct.contains(name)) { - only if not seen yet.")
                .xp(30))
            .mistakes(
                new String[]{"Removing from the original",
                    "You lose the event count. Build a new list."},
                new String[]{"Not normalising first",
                    "Admin, admin and \" admin\" count as three."},
                new String[]{"contains without the !",
                    "Adds only the copies - the opposite result."})
            .cyber(
                "'Nine failures' means little until you know how many "
                + "sources and how many targets. One source against many "
                + "accounts is password spraying; many sources against one "
                + "account is a distributed attack on it; one against one "
                + "is often a user with a stale saved password. Distinct "
                + "counts separate them - but only if 'same' was decided "
                + "properly first.")
            .check(new Task(Task.CHOICE,
                    "Failed logins: 40 events, 1 distinct source address, "
                    + "40 distinct usernames. What does that pattern "
                    + "suggest?")
                .choices("One user who forgot a password",
                         "Password spraying: one source trying many accounts",
                         "Forty separate attackers",
                         "Nothing unusual")
                .accept("2", "b")
                .hints("One source, many targets.",
                       "Each account tried a small number of times.")
                .explain(
                    "b. A single source working through many accounts is "
                    + "the spraying pattern - which per-account lockouts "
                    + "alone may never notice.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] s = {\"x\", \"y\", \"x\", \"x\"};",
                    "java.util.ArrayList<String> u = new java.util.ArrayList<>();",
                    "for (String v : s) {",
                    "    if (!u.contains(v)) {",
                    "        u.add(v);",
                    "    }",
                    "}",
                    "System.out.println(s.length + \" \" + u.size());")
                .accept("4 2")
                .hints("The array keeps all four.",
                       "The list keeps x and y once each.")
                .explain(
                    "4 2. The original still has every event; the new list "
                    + "has one of each value.")
                .xp(15))
            .recap(
                "    new list; for each value:\n"
                + "        normalise it\n"
                + "        if (!unique.contains(v)) unique.add(v)\n"
                + "\n"
                + "Keep the original for the total. Order of first "
                + "appearance is kept.")
            .next("Next: counting per user, and finding the worst."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(28), "Top Offenders", 6)
            .brief(
                "The morning report's headline is the account with the most "
                + "failed logins overnight. The log is a list of lines like "
                + "'FAIL jsmith'. Nobody knows in advance which users will "
                + "appear, so a fixed tally array cannot be used. Two lists "
                + "that grow together can: names, and a count for each.")
            .willLearn("Counting per key")
            .whyUseful(
                "'How many per user / per address / per host' is the core "
                + "of log analysis. This mission builds it from the "
                + "campaign's pieces: split, indexOf, parallel lists and "
                + "tracking the best index.")
            .concept("Counting per key",
                "A tally array (mission 14) needs the values to be small "
                + "numbers. For text keys like usernames, keep two lists in "
                + "step - parallel lists (mission 15), but growable:\n"
                + "\n"
                + "    ArrayList<String> names    who\n"
                + "    ArrayList<Integer> counts  how many, same index\n"
                + "\n"
                + "For each event:\n"
                + "\n"
                + "    int at = names.indexOf(user);\n"
                + "    if (at == -1) {             new user\n"
                + "        names.add(user);\n"
                + "        counts.add(1);\n"
                + "    } else {                    seen before\n"
                + "        counts.set(at, counts.get(at) + 1);\n"
                + "    }\n"
                + "\n"
                + "A new user is added to BOTH lists at once, so they stay "
                + "the same length; a known user's count is replaced with "
                + "one more. Nothing else ever changes either list, which "
                + "keeps them in step.\n"
                + "\n"
                + "Then find the top offender by INDEX, as in mission 15, "
                + "and read both lists with it.\n"
                + "\n"
                + "(Campaign 11's HashMap stores key-count pairs directly. "
                + "The idea is exactly this.)")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] log = {\"FAIL jsmith\", \"OK mpatel\",",
                "                \"FAIL admin\", \"FAIL admin\", \"FAIL jsmith\",",
                "                \"FAIL admin\", \"OK jsmith\"};",
                "        ArrayList<String> names = new ArrayList<>();",
                "        ArrayList<Integer> counts = new ArrayList<>();",
                "        for (String line : log) {",
                "            String[] p = line.split(\" \");",
                "            if (p.length != 2 || !p[0].equals(\"FAIL\")) {",
                "                continue;",
                "            }",
                "            int at = names.indexOf(p[1]);",
                "            if (at == -1) {",
                "                names.add(p[1]);",
                "                counts.add(1);",
                "            } else {",
                "                counts.set(at, counts.get(at) + 1);",
                "            }",
                "        }",
                "        int top = 0;",
                "        for (int i = 1; i < counts.size(); i++) {",
                "            if (counts.get(i) > counts.get(top)) {",
                "                top = i;",
                "            }",
                "        }",
                "        System.out.println(names + \" \" + counts);",
                "        System.out.println(\"Top: \" + names.get(top)",
                "                + \" (\" + counts.get(top) + \")\");",
                "    }",
                "}")
            .exampleOutput(
                "[jsmith, admin] [2, 3]",
                "Top: admin (3)")
            .lineByLine(
                new String[]{"p.length != 2 || !p[0].equals(\"FAIL\")",
                    "Malformed lines and successes are skipped. The length "
                    + "check comes first, so p[0] is safe."},
                new String[]{"names.indexOf(p[1])",
                    "Where this user sits in names - or -1 if new."},
                new String[]{"names.add(...); counts.add(1);",
                    "A new user joins both lists together: same length, "
                    + "same index."},
                new String[]{"counts.set(at, counts.get(at) + 1)",
                    "Read the count, add one, put it back."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<String> k = new java.util.ArrayList<>();",
                    "java.util.ArrayList<Integer> n = new java.util.ArrayList<>();",
                    "String[] ev = {\"a\", \"b\", \"a\"};",
                    "for (String e : ev) {",
                    "    int at = k.indexOf(e);",
                    "    if (at == -1) {",
                    "        k.add(e);",
                    "        n.add(1);",
                    "    } else {",
                    "        n.set(at, n.get(at) + 1);",
                    "    }",
                    "}",
                    "System.out.println(k + \" \" + n);")
                .accept("[a, b] [2, 1]")
                .hints("a is new, b is new, then a is seen again.",
                       "a's count goes to 2.")
                .explain(
                    "[a, b] [2, 1]. The second a finds index 0 and raises "
                    + "that count; the lists stay the same length.")
                .xp(20))
            .practice(new Task(Task.CHOICE,
                    "A new user is added to names, but the programmer "
                    + "forgets counts.add(1). What happens next time a count "
                    + "is read?")
                .choices("Nothing - Java adds the count",
                         "Counts belong to the wrong users, or a crash",
                         "A compile error",
                         "The user is ignored")
                .accept("2", "b")
                .hints("The lists are now different lengths.",
                       "The same index no longer means the same user.")
                .explain(
                    "b. names is now one longer. Every later new user's "
                    + "count lands at the wrong index, and reading the last "
                    + "name's count runs off the end of counts.")
                .xp(20))
            .objective(
                "Complete the count update for a user already seen.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] blocked = {\"10.0.0.9\", \"10.0.0.4\", \"10.0.0.9\"};",
                "        ArrayList<String> ips = new ArrayList<>();",
                "        ArrayList<Integer> hits = new ArrayList<>();",
                "        for (String ip : blocked) {",
                "            int at = ips.indexOf(ip);",
                "            if (at == -1) {",
                "                ips.add(ip);",
                "                hits.add(1);",
                "            } else {",
                "                // add 1 to the count at index at",
                "            }",
                "        }",
                "        System.out.println(ips + \" \" + hits);",
                "    }",
                "}")
            .yourTask(
                "Write the line that replaces the count at index at with "
                + "one more than it was.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line in the else.")
                .accept("hits.set(at, hits.get(at) + 1);",
                        "hits.set(at,hits.get(at)+1);",
                        "hits.set(at, hits.get(at)+1);",
                        "hits.set(at, 1 + hits.get(at));")
                .hints(
                    "set(index, newValue).",
                    "The new value is the old count, hits.get(at), plus 1.",
                    "hits.set(at, hits.get(at) + 1);")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String[] blocked = {\"10.0.0.9\", \"10.0.0.4\", \"10.0.0.9\"};",
                    "        ArrayList<String> ips = new ArrayList<>();",
                    "        ArrayList<Integer> hits = new ArrayList<>();",
                    "        for (String ip : blocked) {",
                    "            int at = ips.indexOf(ip);",
                    "            if (at == -1) {",
                    "                ips.add(ip);",
                    "                hits.add(1);",
                    "            } else {",
                    "                hits.set(at, hits.get(at) + 1);",
                    "            }",
                    "        }",
                    "        System.out.println(ips + \" \" + hits);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The third address, 10.0.0.9, is found at index 0, so "
                    + "its count is read (1), increased and put back (2): "
                    + "[10.0.0.9, 10.0.0.4] [2, 1].\n"
                    + "\n"
                    + "hits.get(at)++ would not compile - get gives back a "
                    + "value, not a slot to change. A list's item is changed "
                    + "only with set.")
                .explain(
                    "hits.set(at, hits.get(at) + 1); - read, add one, store.")
                .xp(30))
            .mistakes(
                new String[]{"Adding to only one list",
                    "The lists fall out of step."},
                new String[]{"hits.get(at)++",
                    "Does not compile. Use set with the new value."},
                new String[]{"Reading p[1] before checking p.length",
                    "A malformed line crashes the whole report."})
            .cyber(
                "Top offender lists drive the first hour of most "
                + "investigations: the most-attacked accounts, the noisiest "
                + "sources, the hosts with the most alerts. Two things make "
                + "one trustworthy: every line is counted under the right "
                + "key (normalised, with malformed lines skipped and "
                + "counted separately), and the counts can never drift away "
                + "from their names.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<Integer> c = new java.util.ArrayList<>();",
                    "c.add(4);",
                    "c.add(9);",
                    "c.add(9);",
                    "int top = 0;",
                    "for (int i = 1; i < c.size(); i++) {",
                    "    if (c.get(i) > c.get(top)) {",
                    "        top = i;",
                    "    }",
                    "}",
                    "System.out.println(top);")
                .accept("1")
                .hints("> is strict.",
                       "The second 9 does not beat the first.")
                .explain(
                    "1. The first 9 takes the lead at index 1; the second 9 "
                    + "only ties, and a tie does not replace it. (Here > "
                    + "on two Integers unboxes them, so it is safe.)")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Why can a tally array (mission 14) not count per "
                    + "username?")
                .choices("Arrays cannot hold ints",
                         "A username cannot be used as an index",
                         "Tally arrays are too slow",
                         "It can")
                .accept("2", "b")
                .hints("What picks the slot in a tally array?",
                       "Indexes are ints.")
                .explain(
                    "b. A tally array uses the value itself as the index, "
                    + "which only works for small whole numbers. Text keys "
                    + "need indexOf on a list of names.")
                .xp(15))
            .recap(
                "    at = names.indexOf(key)\n"
                + "    -1:   names.add(key); counts.add(1);\n"
                + "    else: counts.set(at, counts.get(at) + 1);\n"
                + "    top:  track the best index, read both lists\n"
                + "\n"
                + "Change both lists together, or not at all.")
            .next("Next: the allowlist - deny unless listed."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(29), "An Allowlist Check", 6)
            .brief(
                "Outbound web traffic from the payment servers may only go "
                + "to a short list of approved domains. Everything else is "
                + "refused. The first draft used contains on the domain "
                + "text, and a tester got through with "
                + "pay.example.com.attacker.net. The rule is right; the "
                + "matching was wrong.")
            .willLearn("Allowlists")
            .whyUseful(
                "An allowlist - deny everything except what is listed - is "
                + "one of the strongest controls there is, and one of the "
                + "easiest to weaken by matching loosely. Writing one "
                + "correctly uses almost every idea in this campaign.")
            .concept("Allowlists",
                "An ALLOWLIST says what is permitted; everything else is "
                + "denied. (A blocklist is the opposite, and always "
                + "incomplete - new bad things appear every day.)\n"
                + "\n"
                + "Four rules make an allowlist check sound:\n"
                + "\n"
                + "    deny by default   return false unless a match\n"
                + "                      was found\n"
                + "    exact matching    equals, never String contains,\n"
                + "                      startsWith or endsWith\n"
                + "    normalise first   trim and lower-case the input,\n"
                + "                      the way the list was stored\n"
                + "    empty means none  an empty list allows NOTHING\n"
                + "\n"
                + "Why exact? Loose matches accept look-alikes:\n"
                + "\n"
                + "    text.contains(\"pay.example.com\")\n"
                + "        accepts pay.example.com.attacker.net\n"
                + "    text.endsWith(\"example.com\")\n"
                + "        accepts badexample.com\n"
                + "\n"
                + "With a list, the exact check is one call - the list's own "
                + "contains, which compares each entry with equals:\n"
                + "\n"
                + "    return allow.contains(host.trim().toLowerCase());\n"
                + "\n"
                + "That single line meets all four rules: false unless an "
                + "entry is EQUAL, after normalising, and false for an "
                + "empty list.")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> allow = new ArrayList<>();",
                "        allow.add(\"pay.example.com\");",
                "        allow.add(\"api.example.com\");",
                "        String[] tries = {\"pay.example.com\", \" API.Example.com \",",
                "                \"pay.example.com.attacker.net\", \"example.com\"};",
                "        for (String t : tries) {",
                "            String verdict = isAllowed(allow, t) ? \"ALLOW\" : \"DENY\";",
                "            System.out.println(verdict + \"  [\" + t + \"]\");",
                "        }",
                "    }",
                "",
                "    static boolean isAllowed(ArrayList<String> allow, String host) {",
                "        String h = host.trim().toLowerCase();",
                "        return allow.contains(h);",
                "    }",
                "}")
            .exampleOutput(
                "ALLOW  [pay.example.com]",
                "ALLOW  [ API.Example.com ]",
                "DENY  [pay.example.com.attacker.net]",
                "DENY  [example.com]")
            .lineByLine(
                new String[]{"host.trim().toLowerCase()",
                    "Spaces and capitals cannot sneak a listed host past, "
                    + "or keep a real one out."},
                new String[]{"allow.contains(h)",
                    "The LIST's contains: each entry compared with equals. "
                    + "Exact match or nothing."},
                new String[]{"DENY  [pay.example.com.attacker.net]",
                    "It starts with a listed name, but is not equal to one: "
                    + "denied."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String host = \"pay.example.com.attacker.net\";",
                    "System.out.println(host.contains(\"pay.example.com\"));")
                .accept("true")
                .hints("Does the text appear inside host?",
                       "At the very start.")
                .explain(
                    "true - which is why String contains is the wrong test. "
                    + "The attacker controls the text after the approved "
                    + "name.")
                .xp(20))
            .practice(new Task(Task.CHOICE,
                    "The allowlist is empty because a config file failed to "
                    + "load. What should isAllowed return for every host?")
                .choices("true - nothing is blocked",
                         "false - nothing is allowed",
                         "It should crash",
                         "true for known hosts only")
                .accept("2", "b")
                .hints("Deny by default.",
                       "Fail closed (Campaign 03).")
                .explain(
                    "b. An empty allowlist allows nothing. Failing open "
                    + "here would switch the control off exactly when "
                    + "something has already gone wrong.")
                .xp(20))
            .objective(
                "Write the allowlist check with a loop, deny by default.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<String> allow = new ArrayList<>();",
                "        allow.add(\"updates.example.com\");",
                "        System.out.println(ok(allow, \"Updates.Example.com\"));",
                "        System.out.println(ok(allow, \"updates.example.com.evil.io\"));",
                "        System.out.println(ok(new ArrayList<>(), \"x.com\"));",
                "    }",
                "",
                "    static boolean ok(ArrayList<String> allow, String host) {",
                "        String h = host.trim().toLowerCase();",
                "        for (String entry : allow) {",
                "            if (entry.equals(h)) {",
                "                return true;",
                "            }",
                "        }",
                "        // deny by default",
                "    }",
                "}")
            .yourTask(
                "Write the last line of ok: the answer when no entry "
                + "matched - including when the list is empty.")
            .mainTask(new Task(Task.WRITE,
                    "Write the last line.")
                .accept("return false;")
                .hints(
                    "Nothing matched.",
                    "Deny by default.",
                    "return false;")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        ArrayList<String> allow = new ArrayList<>();",
                    "        allow.add(\"updates.example.com\");",
                    "        System.out.println(ok(allow, \"Updates.Example.com\"));",
                    "        System.out.println(ok(allow, \"updates.example.com.evil.io\"));",
                    "        System.out.println(ok(new ArrayList<>(), \"x.com\"));",
                    "    }",
                    "",
                    "    static boolean ok(ArrayList<String> allow, String host) {",
                    "        String h = host.trim().toLowerCase();",
                    "        for (String entry : allow) {",
                    "            if (entry.equals(h)) {",
                    "                return true;",
                    "            }",
                    "        }",
                    "        return false;",
                    "    }",
                    "}")
                .whyItWorks(
                    "The normalised host is compared with each entry using "
                    + "equals. Only an exact match returns true; reaching the "
                    + "end of the loop means none matched, so the answer is "
                    + "false. Output: true, false, false.\n"
                    + "\n"
                    + "The empty list never enters the loop at all, and goes "
                    + "straight to return false: deny. The loop is the same "
                    + "check allow.contains(h) makes - mission 12's search, "
                    + "with this mission's rules.")
                .explain(
                    "return false; - no match, or no list, means deny.")
                .xp(30))
            .mistakes(
                new String[]{"host.contains(entry)",
                    "Look-alike hosts get through."},
                new String[]{"Returning true after the loop",
                    "Fails open: everything is allowed."},
                new String[]{"Normalising the list but not the input",
                    "Real hosts are refused, or tricks slip past."})
            .cyber(
                "Loose matching has undone countless allowlists: redirect "
                + "checks that trusted any URL containing the company's "
                + "name, email filters that trusted any sender ending in "
                + "the right word, CORS rules that trusted any origin "
                + "starting with the right text. Each let attackers "
                + "register a look-alike and walk in. Deny by default, "
                + "match exactly, normalise both sides - and test with the "
                + "look-alikes yourself.")
            .check(new Task(Task.CHOICE,
                    "The list holds example.com. Which test accepts "
                    + "badexample.com?")
                .choices("allow.contains(h)",
                         "h.endsWith(\"example.com\")",
                         "h.equals(\"example.com\")",
                         "None of them")
                .accept("2", "b")
                .hints("Does badexample.com END with example.com?",
                       "Yes.")
                .explain(
                    "b. endsWith accepts any host whose text finishes the "
                    + "same way - anyone can register badexample.com. The "
                    + "exact tests refuse it.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Why is an allowlist usually stronger than a blocklist?")
                .choices("It is shorter",
                         "Anything not yet known is denied by default",
                         "It runs faster",
                         "It needs no normalising")
                .accept("2", "b")
                .hints("What happens to a brand-new bad domain?",
                       "A blocklist has never heard of it.")
                .explain(
                    "b. A blocklist must know every bad thing in advance. "
                    + "An allowlist only needs to know the good ones; "
                    + "everything new is refused.")
                .xp(15))
            .recap(
                "    normalise: h = host.trim().toLowerCase()\n"
                + "    match exactly: list.contains(h) or equals\n"
                + "    deny by default: false unless a match\n"
                + "    empty list: allows nothing\n"
                + "\n"
                + "Never String contains, startsWith or endsWith for trust.")
            .next("Next: the campaign checkpoint."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(30), "COLLECTIONS COMPLETE", 6)
            .brief(
                "Thirty missions ago, a program could hold a handful of "
                + "values in a handful of variables. Now it keeps whole "
                + "batches - every reading, every user, every field of a "
                + "line - searches them, counts them, cleans them and "
                + "reports on them.\n\n"
                + "This checkpoint mixes the whole campaign. No new Java.")
            .willLearn("Recall of the whole campaign")
            .whyUseful(
                "Collection bugs come from ideas meeting: a reference where "
                + "a copy was meant, a removal inside a loop, an index read "
                + "before a length check. This is the practice for exactly "
                + "those.")
            .concept("Everything, together",
                "The campaign in one page.\n"
                + "\n"
                + "ARRAYS\n"
                + "    int[] a = new int[n];   or   {1, 2, 3}\n"
                + "    a[i], a.length, indexes 0 to length - 1\n"
                + "    defaults: 0, 0.0, false, null\n"
                + "    int[][] g: g[r][c], g.length, g[r].length\n"
                + "\n"
                + "LOOPS\n"
                + "    for (int i = 0; i < a.length; i++)  position + value\n"
                + "    for (int v : a)                     values only\n"
                + "    remove from a list: loop backwards\n"
                + "\n"
                + "REFERENCES\n"
                + "    b = a shares one array; methods can change it\n"
                + "    Arrays.copyOf for a real copy; == asks 'same one?'\n"
                + "\n"
                + "ARRAYLIST\n"
                + "    add, add(i, x), get, set, size, isEmpty\n"
                + "    remove, contains, indexOf - all use equals\n"
                + "    ArrayList<Integer>; remove(Integer.valueOf(v))\n"
                + "\n"
                + "PATTERNS\n"
                + "    search, max/min/average, tally, parallel arrays,\n"
                + "    split, distinct values, count per key, sort\n"
                + "\n"
                + "SECURITY\n"
                + "    check lengths and indexes from outside\n"
                + "    work on copies; keep the original as evidence\n"
                + "    normalise, match exactly, deny by default\n"
                + "    cap anything that grows with outside input")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String line = \"22,80,x,443,80,99999\";",
                "        String[] parts = line.split(\",\");",
                "        ArrayList<Integer> ports = new ArrayList<>();",
                "        int bad = 0;",
                "        for (String p : parts) {",
                "            if (!isPort(p)) {",
                "                bad++;",
                "            } else if (!ports.contains(Integer.parseInt(p))) {",
                "                ports.add(Integer.parseInt(p));",
                "            }",
                "        }",
                "        System.out.println(\"Ports: \" + ports + \", rejected: \" + bad);",
                "    }",
                "",
                "    static boolean isPort(String s) {",
                "        if (s.isEmpty() || s.length() > 5) {",
                "            return false;",
                "        }",
                "        for (int i = 0; i < s.length(); i++) {",
                "            if (!Character.isDigit(s.charAt(i))) {",
                "                return false;",
                "            }",
                "        }",
                "        int n = Integer.parseInt(s);",
                "        return n >= 1 && n <= 65535;",
                "    }",
                "}")
            .exampleOutput(
                "Ports: [22, 80, 443], rejected: 2")
            .lineByLine(
                new String[]{"line.split(\",\")",
                    "Mission 17: six pieces, not all of them trustworthy."},
                new String[]{"isPort(p)",
                    "Campaigns 03 and 04: a validator that checks every "
                    + "character before parseInt, and the range after."},
                new String[]{"!ports.contains(Integer.parseInt(p))",
                    "Missions 22 and 27: one of each, compared as values."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[] a = {3, 1, 2};",
                    "int[] b = a;",
                    "int[] c = java.util.Arrays.copyOf(a, a.length);",
                    "b[0] = 9;",
                    "c[1] = 8;",
                    "System.out.println(a[0] + \" \" + a[1]);")
                .accept("9 1")
                .hints("b shares a's array; c is a real copy.",
                       "Only the change through b reaches a.")
                .explain(
                    "9 1. Missions 10 and 11: b = a is one array with two "
                    + "names, while copyOf made a separate one.")
                .xp(20))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "java.util.ArrayList<Integer> v = new java.util.ArrayList<>();",
                    "v.add(3);",
                    "v.add(0);",
                    "v.add(3);",
                    "v.remove(0);",
                    "v.remove(Integer.valueOf(3));",
                    "System.out.println(v);")
                .accept("[0]")
                .hints("remove(0) removes INDEX 0, not the value 0.",
                       "Then valueOf(3) removes the value 3.")
                .explain(
                    "[0]. remove(0) took index 0 - the first 3 - leaving "
                    + "[0, 3]; removing the VALUE 3 then left [0]. Mission "
                    + "23's trap, both ways round.")
                .xp(20))
            .objective(
                "Report each failing user once, in order.")
            .starter(
                "import java.util.ArrayList;",
                "import java.util.Collections;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String[] log = {\"FAIL Zed\", \"OK ann\", \"FAIL bob\",",
                "                \"FAIL zed\", \"broken\", \"FAIL bob\"};",
                "        ArrayList<String> users = new ArrayList<>();",
                "        for (String line : log) {",
                "            String[] p = line.split(\" \");",
                "            // the if: exactly 2 parts, and the first is FAIL",
                "                String u = p[1].toLowerCase();",
                "                if (!users.contains(u)) {",
                "                    users.add(u);",
                "                }",
                "            }",
                "        }",
                "        Collections.sort(users);",
                "        System.out.println(users);",
                "    }",
                "}")
            .yourTask(
                "Write the if that lets a line through only when it split "
                + "into exactly 2 parts AND its first part is FAIL. The "
                + "length check must come first.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line.")
                .accept("if (p.length == 2 && p[0].equals(\"FAIL\")) {",
                        "if(p.length == 2 && p[0].equals(\"FAIL\")) {",
                        "if (p.length == 2 && p[0].equals(\"FAIL\")){",
                        "if (p.length == 2 && \"FAIL\".equals(p[0])) {")
                .hints(
                    "Two conditions joined by &&, the guard on the left.",
                    "Compare text with equals.",
                    "if (p.length == 2 && p[0].equals(\"FAIL\")) {")
                .solution(
                    "import java.util.ArrayList;",
                    "import java.util.Collections;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String[] log = {\"FAIL Zed\", \"OK ann\", \"FAIL bob\",",
                    "                \"FAIL zed\", \"broken\", \"FAIL bob\"};",
                    "        ArrayList<String> users = new ArrayList<>();",
                    "        for (String line : log) {",
                    "            String[] p = line.split(\" \");",
                    "            if (p.length == 2 && p[0].equals(\"FAIL\")) {",
                    "                String u = p[1].toLowerCase();",
                    "                if (!users.contains(u)) {",
                    "                    users.add(u);",
                    "                }",
                    "            }",
                    "        }",
                    "        Collections.sort(users);",
                    "        System.out.println(users);",
                    "    }",
                    "}")
                .whyItWorks(
                    "\"broken\" has one part, so the length check stops it "
                    + "before p[0] matters - and && never looks at the "
                    + "right side. OK ann is not a failure. Zed and zed are "
                    + "normalised to one user, bob is kept once, and the "
                    + "sort gives [bob, zed].\n"
                    + "\n"
                    + "Six missions in one line and the lines around it: "
                    + "split (17), a length guard (7), equals on text (8), "
                    + "normalising and distinct values (27), and sorting "
                    + "(25).")
                .explain(
                    "The length guard first, then the text compared with equals.")
                .xp(40))
            .mistakes(
                new String[]{"Reading fields before the length check",
                    "One broken line crashes the whole report."},
                new String[]{"b = a as a backup",
                    "Two names, one array. Use Arrays.copyOf."},
                new String[]{"Removing inside a forward loop",
                    "Items are skipped. Loop backwards."})
            .cyber(
                "Almost every security tool is a collections program: it "
                + "reads a batch of events, splits them into fields, keeps "
                + "lists of what it has seen, counts per user and per "
                + "source, and checks each against allowlists and "
                + "watchlists. Everything in this campaign is about doing "
                + "that without crashing on bad input, losing the original "
                + "evidence, or matching loosely.\n"
                + "\n"
                + "What these programs still juggle clumsily is RECORDS - a "
                + "user AND their count AND their last address, kept in "
                + "parallel lists that can drift apart. Campaign 06 gives "
                + "each record a type of its own: classes and objects.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int[][] g = {{1, 2}, {3, 4}};",
                    "int t = 0;",
                    "for (int[] row : g) {",
                    "    for (int v : row) {",
                    "        t += v;",
                    "    }",
                    "}",
                    "System.out.println(t + \" \" + g[1][0]);")
                .accept("10 3")
                .hints("Each row of an int[][] is an int[].",
                       "1 + 2 + 3 + 4, then row 1, column 0.")
                .explain(
                    "10 3. The enhanced for works on 2D arrays too: each "
                    + "row is an int[], and each v an int (missions 6 "
                    + "and 16).")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Which loop removes every \"EXP\" from a list safely?")
                .choices("for (String s : list) { if (...) list.remove(s); }",
                         "for (int i = 0; i < list.size(); i++) ...",
                         "for (int i = list.size() - 1; i >= 0; i--) ...",
                         "Any of them")
                .accept("3", "c")
                .hints("Mission 21.",
                       "Which one only moves items already checked?")
                .explain(
                    "c. Backwards. a crashes with a "
                    + "ConcurrentModificationException, and b skips the item "
                    + "after each removal.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "An allowlist holds pay.example.com. Which check is "
                    + "correct?")
                .choices("host.contains(\"pay.example.com\")",
                         "host.startsWith(\"pay.example.com\")",
                         "allow.contains(host.trim().toLowerCase())",
                         "host.endsWith(\"example.com\")")
                .accept("3", "c")
                .hints("Exact matching, after normalising.",
                       "Mission 29.")
                .explain(
                    "c. The list's contains compares each entry with equals: "
                    + "exact matches only, and false when nothing matches.")
                .xp(15))
            .recap(
                "CAMPAIGN 05 - COLLECTIONS complete.\n"
                + "\n"
                + "Your programs hold batches of data in arrays and lists, "
                + "search and count them, copy and sort them safely, and "
                + "parse lines into fields without trusting them.\n"
                + "\n"
                + "Next they get types of their own: classes and objects.")
            .next("Next: CAMPAIGN 06 - OBJECTS."));
    }
}
