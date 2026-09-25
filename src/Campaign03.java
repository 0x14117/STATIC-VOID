/**
 * CAMPAIGN 03 - METHODS
 * Programs built from named pieces. Thirty missions.
 *
 * Campaign 02 ended with decision programs that worked but repeated
 * themselves: the same validation pasted three times, the same four lines
 * for every alert. This campaign gives that code a name, a place and a
 * contract - and shows what Java does underneath when one method calls
 * another.
 *
 * Loops are Campaign 04, so a mission here may use everything from
 * Campaigns 00 to 02, plus whatever this campaign has already introduced -
 * and nothing else.
 *
 * From here on, a code snippet may show the inside of a class - methods
 * side by side, main among them - without the class line around it.
 */
public class Campaign03 {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(1), "The Same Code, Three Times", 2)
            .brief(
                "The alert report prints a divider between its sections. "
                + "Right now the same println is pasted in three places. When "
                + "the SOC asks for a wider divider, someone has to find and "
                + "change every copy - and one day they will miss one.\n\n"
                + "Java has a way to write something once, give it a name, "
                + "and use it anywhere.")
            .willLearn("Methods")
            .whyUseful(
                "Every serious program is built from methods. A check "
                + "written once, in one named place, can be tested, fixed and "
                + "trusted - instead of five copies that slowly drift apart.")
            .concept("Methods",
                "You have been using methods since your first program:\n"
                + "\n"
                + "    System.out.println(\"hi\")   prints a line\n"
                + "    text.length()              counts characters\n"
                + "    Integer.parseInt(\"42\")     turns text into a number\n"
                + "    Math.max(a, b)             picks the larger\n"
                + "\n"
                + "A METHOD is a named block of code. You CALL it by writing "
                + "its name followed by brackets, and Java runs the code "
                + "inside. The brackets are what make it a call: they hold "
                + "whatever the method needs, or stay empty.\n"
                + "\n"
                + "And you have been WRITING one all along: main is a method. "
                + "Java calls it for you when the program starts.\n"
                + "\n"
                + "This campaign is about writing your own. Here is the "
                + "smallest possible one:\n"
                + "\n"
                + "    static void printRule() {\n"
                + "        System.out.println(\"----------\");\n"
                + "    }\n"
                + "\n"
                + "It is written once, inside the class, next to main. Then "
                + "every  printRule();  in main runs it. Change the dashes in "
                + "one place and every divider changes.\n"
                + "\n"
                + "The next mission takes that first line apart word by word. "
                + "For now, notice the shape: a name, brackets, and a body in "
                + "braces - just like main.")
            .example(
                "public class Main {",
                "    static void printRule() {",
                "        System.out.println(\"----------\");",
                "    }",
                "",
                "    public static void main(String[] args) {",
                "        System.out.println(\"ALERT REPORT\");",
                "        printRule();",
                "        System.out.println(\"3 failed logins\");",
                "        printRule();",
                "        System.out.println(\"1 port scan\");",
                "        printRule();",
                "    }",
                "}")
            .exampleOutput(
                "ALERT REPORT",
                "----------",
                "3 failed logins",
                "----------",
                "1 port scan",
                "----------")
            .lineByLine(
                new String[]{"static void printRule() {",
                    "Declares a method called printRule. Nothing runs yet - "
                    + "this only describes it."},
                new String[]{"System.out.println(\"----------\");",
                    "The method's BODY: what happens each time it is called."},
                new String[]{"printRule();",
                    "A call. Java runs the body, then carries on with the next "
                    + "line of main."},
                new String[]{"public static void main",
                    "main is a method too. Java calls it to start the program."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void beep() {",
                    "    System.out.println(\"BEEP\");",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(\"start\");",
                    "    beep();",
                    "    beep();",
                    "    System.out.println(\"end\");",
                    "}")
                .accept("start BEEP BEEP end")
                .hints("main runs from its top. Each beep(); runs the body once.",
                       "Two calls, two BEEPs, between start and end.")
                .explain(
                    "    start\n"
                    + "    BEEP\n"
                    + "    BEEP\n"
                    + "    end\n"
                    + "\n"
                    + "The body of beep is written once and runs twice - once "
                    + "per call.")
                .xp(10))
            .practice(new Task(Task.CHOICE,
                    "Which of these is NOT a method call?")
                .choices("printRule();", "text.length()", "Math.max(3, 9)",
                         "int count = 3;")
                .accept("4", "d")
                .hints("A call is a name followed by brackets.",
                       "Which one has no brackets at all?")
                .explain(
                    "int count = 3; declares a variable. The other three are "
                    + "calls: a name, then brackets holding whatever the method "
                    + "needs.")
                .xp(10))
            .objective(
                "Put a divider under the report title.")
            .starter(
                "public class Main {",
                "    static void printRule() {",
                "        System.out.println(\"----------\");",
                "    }",
                "",
                "    public static void main(String[] args) {",
                "        System.out.println(\"ALERT REPORT\");",
                "        // call printRule here",
                "        System.out.println(\"3 failed logins\");",
                "    }",
                "}")
            .yourTask(
                "Write the line that calls printRule, so a divider appears "
                + "under the title.")
            .mainTask(new Task(Task.WRITE,
                    "Write the call to printRule.")
                .accept("printRule();")
                .hints(
                    "A call is the method's name followed by brackets.",
                    "It is a statement on its own, so it ends with a "
                    + "semicolon.",
                    "printRule();")
                .solution(
                    "public class Main {",
                    "    static void printRule() {",
                    "        System.out.println(\"----------\");",
                    "    }",
                    "",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"ALERT REPORT\");",
                    "        printRule();",
                    "        System.out.println(\"3 failed logins\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "printRule(); tells Java to run the body of printRule "
                    + "right here. The body prints the dashes, and then main "
                    + "carries on with the next line.\n"
                    + "\n"
                    + "The brackets are essential. Without them, printRule; is "
                    + "just a name, and javac rejects it with 'not a "
                    + "statement'. The name must also match exactly: Java is "
                    + "case-sensitive, so PrintRule() is a different method "
                    + "that does not exist.")
                .explain(
                    "The name, empty brackets, and a semicolon.")
                .xp(20))
            .mistakes(
                new String[]{"Forgetting the brackets",
                    "printRule; is not a call. javac says 'not a statement'."},
                new String[]{"Wrong capitals",
                    "PrintRule() and printRule() are different names."},
                new String[]{"Expecting it to run on its own",
                    "Only main runs by itself. Every other method waits to be "
                    + "called."})
            .cyber(
                "Duplicated security logic is one of the commonest sources of "
                + "real vulnerabilities. A web application checks permissions "
                + "on the edit page, someone copies that check onto the "
                + "delete page - and nobody adds it to the export page built "
                + "a year later. Attackers look for exactly that: the one path "
                + "where the copy is missing or out of date.\n"
                + "\n"
                + "When the check lives in a single method, every path calls "
                + "the same code. Fix it once and every path is fixed.")
            .check(new Task(Task.CHOICE,
                    "Where is a method like printRule written?")
                .choices("Inside main", "Inside the class, next to main",
                         "Outside the class", "In a separate file")
                .accept("2", "b")
                .hints("Look at the example's braces.",
                       "printRule closes before main starts.")
                .explain(
                    "Inside the class, next to main. Methods sit side by side; "
                    + "the next mission shows why one can never go inside "
                    + "another.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void rule() {",
                    "    System.out.println(\"==\");",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    rule();",
                    "    System.out.println(\"OK\");",
                    "    rule();",
                    "}")
                .accept("== OK ==")
                .hints("Follow main from the top.",
                       "Each rule(); prints ==.")
                .explain(
                    "    ==\n"
                    + "    OK\n"
                    + "    ==\n"
                    + "\n"
                    + "Call, print, call.")
                .xp(10))
            .recap(
                "A method is a named block of code.\n"
                + "\n"
                + "    static void printRule() { ... }   write it once\n"
                + "    printRule();                     call it anywhere\n"
                + "\n"
                + "main is a method too. Write the code once; call it as often "
                + "as it is needed.")
            .next("Next: the first line of a method, word by word."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(2), "Writing Your Own", 3)
            .brief(
                "Every shift starts with the same banner on the SOC console. "
                + "It is time to stop copying it and write it as a method - "
                + "which means knowing exactly what each word of a method's "
                + "first line does.")
            .willLearn("void", "Method headers")
            .whyUseful(
                "The first line of a method is a promise about what it needs "
                + "and what it gives back. Reading that line correctly is how "
                + "you use a method without reading its whole body.")
            .concept("Method headers",
                "The first line of a method is its HEADER. It tells Java "
                + "everything needed to call it:\n"
                + "\n"
                + "    static void printBanner() {\n"
                + "\n"
                + "    static        belongs to the class. For now,\n"
                + "                  every method you write has it.\n"
                + "    void          gives nothing back\n"
                + "    printBanner   the name, camelCase like variables\n"
                + "    ()            what it needs - nothing, here\n"
                + "    {             the body starts\n"
                + "\n"
                + "The body runs from { to the matching }. It can hold any "
                + "statements main can: variables, ifs, switches, printlns.\n"
                + "\n"
                + "WHERE IT GOES. Methods sit side by side inside the class. A "
                + "method can never be written inside another method - not "
                + "even inside main:\n"
                + "\n"
                + "    public class Main {\n"
                + "        static void a() { }       fine\n"
                + "        public static void main(String[] args) {\n"
                + "            static void b() { }   will not compile\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "The order of methods in the class does not matter: a method "
                + "can be above main or below it.\n"
                + "\n"
                + "VOID means the method DOES something - prints, for example - "
                + "and hands no answer back. Methods that answer questions "
                + "come in mission 6. main also says public, which is about "
                + "who else may call it; that matters once a program has "
                + "several classes, in Campaign 06.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        printBanner();",
                "        System.out.println(\"Shift started\");",
                "    }",
                "",
                "    static void printBanner() {",
                "        System.out.println(\"=================\");",
                "        System.out.println(\"= NORTHSTAR SOC =\");",
                "        System.out.println(\"=================\");",
                "    }",
                "}")
            .exampleOutput(
                "=================",
                "= NORTHSTAR SOC =",
                "=================",
                "Shift started")
            .lineByLine(
                new String[]{"printBanner();",
                    "Called from main, even though the method is written "
                    + "further down. Order in the file does not matter."},
                new String[]{"static void printBanner() {",
                    "The header: static, gives nothing back, needs nothing."},
                new String[]{"three printlns",
                    "The body. A method can hold as many statements as it "
                    + "needs."},
                new String[]{"}",
                    "The body ends. Java goes back to main."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void unused() {",
                    "    System.out.println(\"never\");",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(\"only this\");",
                    "}")
                .accept("only this")
                .hints("Declaring a method is not the same as running it.",
                       "Does anything call unused?")
                .explain(
                    "only this. unused is declared, but nothing calls it, so "
                    + "its body never runs.")
                .xp(10))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "public static void main(String[] args) {",
                    "    System.out.println(\"start\");",
                    "    static void helper() {",
                    "        System.out.println(\"help\");",
                    "    }",
                    "}")
                .accept("3", "line 3")
                .hints("Where is helper being declared?",
                       "Can a method go inside another method?")
                .explain(
                    "Line 3: 'illegal start of expression'. helper is declared "
                    + "inside main, and methods cannot nest. Move it out, next "
                    + "to main.")
                .xp(20))
            .objective(
                "Declare the method that prints the shift handover note.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        printHandover();",
                "    }",
                "",
                "    // write the header line for printHandover here",
                "        System.out.println(\"HANDOVER - read before starting\");",
                "    }",
                "}")
            .yourTask(
                "Write the header: a static method called printHandover that "
                + "needs nothing and gives nothing back.")
            .mainTask(new Task(Task.WRITE,
                    "Write the header line for printHandover.")
                .accept("static void printHandover() {",
                        "static void printHandover(){",
                        "public static void printHandover() {",
                        "public static void printHandover(){")
                .hints(
                    "Copy the shape of  static void printBanner() {",
                    "static, then void, then the name, then empty brackets "
                    + "and an opening brace.",
                    "static void printHandover() {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        printHandover();",
                    "    }",
                    "",
                    "    static void printHandover() {",
                    "        System.out.println(\"HANDOVER - read before starting\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "static void printHandover() { declares a method main can "
                    + "call: static so it belongs to the class, void because "
                    + "it hands nothing back, empty brackets because it needs "
                    + "nothing. The { opens the body, and the } already in "
                    + "the starter closes it.\n"
                    + "\n"
                    + "The method is written below main and called from above "
                    + "- that is fine. Java reads the whole class before "
                    + "running anything.")
                .explain(
                    "static void, the name, (), and an opening brace.")
                .xp(20))
            .mistakes(
                new String[]{"A method inside main",
                    "Methods never nest. javac says 'illegal start of "
                    + "expression'."},
                new String[]{"Leaving out void",
                    "Every method states what it gives back. Without it, javac "
                    + "says 'invalid method declaration; return type "
                    + "required'."},
                new String[]{"A semicolon after the brackets",
                    "static void a(); { ... } is not a method with a body. "
                    + "javac says 'missing method body'."})
            .cyber(
                "A method is also a unit of review. Security reviewers read "
                + "code one method at a time, and a good name tells them what "
                + "to expect: checkPassword, logFailure, printBanner. When a "
                + "method called printBanner also quietly opens a network "
                + "connection, that mismatch is exactly what a reviewer - or a "
                + "malware analyst - is trained to spot.\n"
                + "\n"
                + "Keep each method doing what its name says, and nothing "
                + "else. Code like that is easier to trust, and anything "
                + "hiding in it stands out.")
            .check(new Task(Task.CHOICE,
                    "What does void mean in a method header?")
                .choices("The method is empty", "The method gives nothing back",
                         "The method cannot be called", "The method is secret")
                .accept("2", "b")
                .hints("It is about what comes back.",
                       "printBanner prints, but hands nothing to main.")
                .explain(
                    "The method gives nothing back. It does its work - "
                    + "printing, here - and main receives no value from it.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Where can a method be declared?")
                .choices("Inside main", "Inside the class, above or below main",
                         "Only above main", "Only in its own file")
                .accept("2", "b")
                .hints("Methods cannot nest.",
                       "Does order in the class matter?")
                .explain(
                    "Inside the class, above or below main. Never inside "
                    + "another method.")
                .xp(10))
            .recap(
                "    static void name() {\n"
                + "        body\n"
                + "    }\n"
                + "\n"
                + "static for now, void when nothing comes back, a camelCase "
                + "name, brackets, a body. Methods sit side by side in the "
                + "class, never inside each other. Declaring a method does not "
                + "run it; calling it does.")
            .next("Next: exactly where Java goes when you call a method."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(3), "Where Execution Goes", 3)
            .brief(
                "A junior analyst reads a program top to bottom, as if it were "
                + "a document, and gets the output wrong. Programs with "
                + "methods do not run in the order they are written. They run "
                + "in the order they are CALLED.")
            .willLearn("Method calls", "Flow of control")
            .whyUseful(
                "Knowing exactly where Java goes at each call - and where it "
                + "comes back to - is how you read any program with methods, "
                + "predict its output, and find the line that is misbehaving.")
            .concept("Method calls",
                "A program runs one statement at a time, starting at the top "
                + "of main. A CALL interrupts that:\n"
                + "\n"
                + "    1. Java pauses main at the call\n"
                + "    2. jumps to the method and runs its body\n"
                + "    3. at the method's closing }, it returns\n"
                + "    4. main carries on with the line AFTER the call\n"
                + "\n"
                + "    main                   checkDisk\n"
                + "    println(\"start\")\n"
                + "    checkDisk();  ------>  println(\"disk ok\")\n"
                + "                  <------  }\n"
                + "    println(\"end\")\n"
                + "\n"
                + "Every call is a round trip. Call a method twice and its body "
                + "runs twice. Never call it and it never runs, however much "
                + "code is inside.\n"
                + "\n"
                + "A call inside an if only happens when the if's condition is "
                + "true - the call is just another statement, and Java only "
                + "reaches it if the program's flow gets there.\n"
                + "\n"
                + "The same rule holds when one method calls another: go in, "
                + "run, come back to exactly where you left. Mission 16 shows "
                + "how Java keeps track of all those places to come back to.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"main: start\");",
                "        checkDisk();",
                "        System.out.println(\"main: between checks\");",
                "        checkDisk();",
                "        System.out.println(\"main: end\");",
                "    }",
                "",
                "    static void checkDisk() {",
                "        System.out.println(\"  checkDisk: disk ok\");",
                "    }",
                "}")
            .exampleOutput(
                "main: start",
                "  checkDisk: disk ok",
                "main: between checks",
                "  checkDisk: disk ok",
                "main: end")
            .lineByLine(
                new String[]{"checkDisk();",
                    "main pauses. Java runs checkDisk's body."},
                new String[]{"System.out.println(\"  checkDisk: disk ok\");",
                    "The body's only line."},
                new String[]{"} of checkDisk",
                    "Back to main, to the line after the call."},
                new String[]{"the second checkDisk();",
                    "The same round trip again."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void scan() {",
                    "    System.out.println(\"scanning\");",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(\"ready\");",
                    "    scan();",
                    "    System.out.println(\"done\");",
                    "    scan();",
                    "}")
                .accept("ready scanning done scanning")
                .hints("Start at the top of main, not the top of the snippet.",
                       "Each scan(); is a round trip.")
                .explain(
                    "    ready\n"
                    + "    scanning\n"
                    + "    done\n"
                    + "    scanning\n"
                    + "\n"
                    + "scan is written first but runs only when called.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "How many lines does this print?")
                .code(
                    "static void alarm() {",
                    "    System.out.println(\"ALARM\");",
                    "    System.out.println(\"Paging on-call\");",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    int failures = 2;",
                    "    if (failures >= 5) {",
                    "        alarm();",
                    "    }",
                    "    System.out.println(\"checked\");",
                    "}")
                .accept("1", "one")
                .hints("Is failures >= 5?",
                       "If the call is never reached, the body never runs.")
                .explain(
                    "1. The condition is false, so alarm() is never called and "
                    + "its two printlns never run. Only checked is printed.")
                .xp(15))
            .objective(
                "Make the audit log print its header first.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // call printHeader here, so it prints first",
                "        System.out.println(\"jsmith: 3 logins\");",
                "        printFooter();",
                "    }",
                "",
                "    static void printFooter() {",
                "        System.out.println(\"AUDIT END\");",
                "    }",
                "",
                "    static void printHeader() {",
                "        System.out.println(\"AUDIT START\");",
                "    }",
                "}")
            .yourTask(
                "printHeader is written at the bottom of the class, but it "
                + "must print first. Write the call that goes at the top of "
                + "main.")
            .mainTask(new Task(Task.WRITE,
                    "Write the call that prints the header.")
                .accept("printHeader();")
                .hints(
                    "What runs first is decided by main, not by where the "
                    + "method is written.",
                    "The name, brackets, semicolon.",
                    "printHeader();")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        printHeader();",
                    "        System.out.println(\"jsmith: 3 logins\");",
                    "        printFooter();",
                    "    }",
                    "",
                    "    static void printFooter() {",
                    "        System.out.println(\"AUDIT END\");",
                    "    }",
                    "",
                    "    static void printHeader() {",
                    "        System.out.println(\"AUDIT START\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "main decides the order. Its first statement is now the "
                    + "call to printHeader, so AUDIT START prints first, then "
                    + "the body line, then printFooter runs and prints AUDIT "
                    + "END.\n"
                    + "\n"
                    + "printHeader being the last method in the file changes "
                    + "nothing: methods run when they are called, not where "
                    + "they are written.")
                .explain(
                    "printHeader(); as main's first line.")
                .xp(20))
            .mistakes(
                new String[]{"Reading top to bottom",
                    "Start at main and follow the calls, not the file."},
                new String[]{"Thinking main stops at a call",
                    "It pauses, then carries on with the line after the call."},
                new String[]{"Forgetting calls inside ifs",
                    "A call only happens if the flow of the program reaches "
                    + "it."})
            .cyber(
                "Following calls is how code is read in security work. An "
                + "analyst reverse engineering malware, or a reviewer tracing "
                + "how user input reaches a database, starts at the entry "
                + "point and FOLLOWS each call into its method and back out "
                + "again. Debuggers exist to do exactly that, one step at a "
                + "time.\n"
                + "\n"
                + "It also explains a trick attackers use: code that is "
                + "present but only called under a rare condition - a date, "
                + "a hostname, a hidden command. A logic bomb is just a call "
                + "behind an if.")
            .check(new Task(Task.CHOICE,
                    "After a method's body finishes, where does Java carry on?")
                .choices("At the top of main", "At the line after the call",
                         "At the next method in the file", "The program ends")
                .accept("2", "b")
                .hints("A call is a round trip.",
                       "Where did main pause?")
                .explain(
                    "At the line after the call - exactly where main paused.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void tick() {",
                    "    System.out.println(\"tick\");",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    boolean online = false;",
                    "    System.out.println(\"check\");",
                    "    if (online) {",
                    "        tick();",
                    "    } else {",
                    "        System.out.println(\"offline\");",
                    "    }",
                    "    tick();",
                    "}")
                .accept("check offline tick")
                .hints("online is false, so which branch runs?",
                       "The last tick(); is outside the if.")
                .explain(
                    "    check\n"
                    + "    offline\n"
                    + "    tick\n"
                    + "\n"
                    + "The call inside the if is skipped; the one after it is "
                    + "not.")
                .xp(15))
            .recap(
                "A call is a round trip: pause, jump into the method, run the "
                + "body, come back to the line after the call.\n"
                + "\n"
                + "Each call runs the body once. A method nobody calls never "
                + "runs. Order in the file does not matter; order of calls "
                + "does.")
            .next("Next: handing a method a value to work with."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(4), "One Parameter", 3)
            .brief(
                "printRule always prints the same thing. The alert method the "
                + "SOC needs is different every time: an alert on web-01, "
                + "then db-02, then fw-3. One method, many hosts. The method "
                + "needs to be TOLD which host.")
            .willLearn("Parameters", "Arguments")
            .whyUseful(
                "Parameters turn a method from a fixed script into a tool. "
                + "lockAccount(user), alert(host), checkPort(port) - one "
                + "method, used on any value.")
            .concept("Parameters",
                "A PARAMETER is a variable declared in the method's "
                + "brackets:\n"
                + "\n"
                + "    static void alert(String host) {\n"
                + "        System.out.println(\"ALERT on \" + host);\n"
                + "    }\n"
                + "\n"
                + "The call supplies its value, called the ARGUMENT:\n"
                + "\n"
                + "    alert(\"web-01\");    host is \"web-01\" this time\n"
                + "    alert(\"db-02\");     host is \"db-02\" this time\n"
                + "\n"
                + "On every call, Java copies the argument into the parameter, "
                + "then runs the body. Inside the method, host is an ordinary "
                + "variable holding whatever this call passed in.\n"
                + "\n"
                + "    parameter   in the HEADER:  String host\n"
                + "    argument    in the CALL:    \"web-01\"\n"
                + "\n"
                + "An argument can be any expression of the right type: a "
                + "literal, a variable, or a calculation, such as "
                + "alert(\"fw-\" + 3).\n"
                + "\n"
                + "The TYPE must match. alert(22) does not compile: 22 is an "
                + "int and host is a String. That is Java protecting you - a "
                + "method can rely on its parameter being the type it "
                + "declared.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        alert(\"web-01\");",
                "        String server = \"db-02\";",
                "        alert(server);",
                "        alert(\"fw-\" + 3);",
                "    }",
                "",
                "    static void alert(String host) {",
                "        System.out.println(\"ALERT on \" + host);",
                "    }",
                "}")
            .exampleOutput(
                "ALERT on web-01",
                "ALERT on db-02",
                "ALERT on fw-3")
            .lineByLine(
                new String[]{"alert(\"web-01\");",
                    "The argument \"web-01\" is copied into host, then the "
                    + "body runs."},
                new String[]{"alert(server);",
                    "The argument is a variable. Its value, \"db-02\", is what "
                    + "gets copied."},
                new String[]{"alert(\"fw-\" + 3);",
                    "The argument is worked out first - \"fw-3\" - then "
                    + "copied."},
                new String[]{"static void alert(String host)",
                    "host is the parameter: a String variable that exists "
                    + "while the method runs."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void lock(String user) {",
                    "    System.out.println(user + \" locked\");",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    String name = \"jsmith\";",
                    "    lock(name);",
                    "    lock(\"admin\");",
                    "}")
                .accept("jsmith locked admin locked")
                .hints("Each call copies its argument into user.",
                       "First call: jsmith. Second: admin.")
                .explain(
                    "    jsmith locked\n"
                    + "    admin locked\n"
                    + "\n"
                    + "Same body, different value in user each time.")
                .xp(10))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "static void alert(String host) {",
                    "    System.out.println(\"ALERT on \" + host);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    alert(\"web-01\");",
                    "    alert(443);",
                    "}")
                .accept("7", "line 7")
                .hints("What type does alert expect?",
                       "Is 443 a String?")
                .explain(
                    "Line 7. 443 is an int, and host is a String. javac says "
                    + "the method cannot be applied to the given types. "
                    + "alert(\"443\") would compile.")
                .xp(20))
            .objective(
                "Declare a method that takes the user to lock.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        lockAccount(\"jsmith\");",
                "        lockAccount(\"m.reyes\");",
                "    }",
                "",
                "    // write the header: lockAccount, one String called user",
                "        System.out.println(\"LOCKED: \" + user);",
                "    }",
                "}")
            .yourTask(
                "Write the header for lockAccount. It takes one String "
                + "parameter called user, and gives nothing back.")
            .mainTask(new Task(Task.WRITE,
                    "Write the header line for lockAccount.")
                .accept("static void lockAccount(String user) {",
                        "static void lockAccount(String user){",
                        "public static void lockAccount(String user) {",
                        "public static void lockAccount(String user){")
                .hints(
                    "Same shape as before, but the brackets are not empty.",
                    "Inside the brackets goes a declaration: the type, then "
                    + "the name.",
                    "static void lockAccount(String user) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        lockAccount(\"jsmith\");",
                    "        lockAccount(\"m.reyes\");",
                    "    }",
                    "",
                    "    static void lockAccount(String user) {",
                    "        System.out.println(\"LOCKED: \" + user);",
                    "    }",
                    "}")
                .whyItWorks(
                    "String user in the brackets declares a parameter: a "
                    + "variable that each call fills in. The first call copies "
                    + "\"jsmith\" into user and prints LOCKED: jsmith; the "
                    + "second copies \"m.reyes\" and prints LOCKED: m.reyes.\n"
                    + "\n"
                    + "The body already used a variable called user. Without "
                    + "the parameter it would not exist, and the starter could "
                    + "never compile.")
                .explain(
                    "The type and name go in the brackets: (String user).")
                .xp(20))
            .mistakes(
                new String[]{"Writing the type in the call",
                    "lockAccount(String \"jsmith\") is not Java. Types go in "
                    + "the header only."},
                new String[]{"Passing the wrong type",
                    "An int cannot go into a String parameter."},
                new String[]{"Using the parameter outside its method",
                    "user only exists inside lockAccount."})
            .cyber(
                "Parameters are where outside data enters a method, and in "
                + "security the question to ask about every one is: where did "
                + "this value come from? A method called with a host name from "
                + "a config file is fine. The same method called with text a "
                + "user typed into a web form may receive anything - empty "
                + "text, ten thousand characters, or input crafted to break "
                + "it.\n"
                + "\n"
                + "Security people call that TAINTED data: untrusted until it "
                + "has been checked. Later missions in this campaign write "
                + "methods that check their parameters before they use them.")
            .check(new Task(Task.CHOICE,
                    "Header: static void scan(String host). Call: "
                    + "scan(\"db-01\"). Which is the ARGUMENT?")
                .choices("String", "host", "\"db-01\"", "scan")
                .accept("3", "c")
                .hints("The argument is in the call.",
                       "It is the value handed over.")
                .explain(
                    "\"db-01\" - the value in the call. host is the "
                    + "parameter: the variable in the header that receives it.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void show(int port) {",
                    "    System.out.println(\"port \" + port);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    int base = 8000;",
                    "    show(base + 80);",
                    "}")
                .accept("port 8080")
                .hints("The argument is worked out before the call.",
                       "8000 + 80.")
                .explain(
                    "port 8080. base + 80 is calculated first, then 8080 is "
                    + "copied into port.")
                .xp(10))
            .recap(
                "    static void alert(String host) { ... }   parameter\n"
                + "    alert(\"web-01\");                        argument\n"
                + "\n"
                + "Each call copies its argument into the parameter, then runs "
                + "the body. The types must match.")
            .next("Next: methods that need more than one value."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(5), "Several Parameters", 4)
            .brief(
                "Logging a failed login needs three facts: who, from where, "
                + "and how many times. A method can take all three - but Java "
                + "matches them up in a way that has caught out experienced "
                + "programmers, and quietly broken security checks.")
            .willLearn("Parameter order")
            .whyUseful(
                "Most real methods take several values. Knowing exactly how "
                + "arguments are matched to parameters is what stops a call "
                + "from compiling, running and silently doing the wrong "
                + "thing.")
            .concept("Parameter order",
                "A method can take several parameters, separated by commas. "
                + "Each has its own type:\n"
                + "\n"
                + "    static void report(String host, int port) {\n"
                + "        System.out.println(host + \":\" + port);\n"
                + "    }\n"
                + "\n"
                + "    report(\"web-01\", 443);    prints web-01:443\n"
                + "\n"
                + "Arguments are matched to parameters BY POSITION, never by "
                + "name:\n"
                + "\n"
                + "    first argument   ->  first parameter   host\n"
                + "    second argument  ->  second parameter  port\n"
                + "\n"
                + "Three rules, all checked by the compiler:\n"
                + "\n"
                + "    count   two parameters need two arguments\n"
                + "    order   each argument goes to the same position\n"
                + "    type    each argument must fit its parameter\n"
                + "\n"
                + "report(443, \"web-01\") does not compile: 443 cannot go "
                + "into a String.\n"
                + "\n"
                + "The DANGEROUS case is two parameters of the same type. If a "
                + "method takes (String user, String host) and a call swaps "
                + "the arguments, it compiles, runs, and quietly does the "
                + "wrong thing. Even a variable called user passed second "
                + "goes into host - Java does not look at names. Clear "
                + "parameter names and a careful look at every call are the "
                + "only defence.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        logFailure(\"jsmith\", \"10.0.0.7\", 3);",
                "        logFailure(\"m.reyes\", \"203.0.113.9\", 7);",
                "    }",
                "",
                "    static void logFailure(String user, String ip, int attempts) {",
                "        String line = user + \" from \" + ip;",
                "        System.out.println(line + \": \" + attempts + \" failures\");",
                "    }",
                "}")
            .exampleOutput(
                "jsmith from 10.0.0.7: 3 failures",
                "m.reyes from 203.0.113.9: 7 failures")
            .lineByLine(
                new String[]{"logFailure(\"jsmith\", \"10.0.0.7\", 3);",
                    "Three arguments, in order: user, ip, attempts."},
                new String[]{"(String user, String ip, int attempts)",
                    "Three parameters, each with its own type, separated by "
                    + "commas."},
                new String[]{"String line = user + \" from \" + ip;",
                    "Inside the method, all three are ordinary variables."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void login(String user, String host) {",
                    "    System.out.println(user + \" logged in to \" + host);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    String user = \"jsmith\";",
                    "    String host = \"web-01\";",
                    "    login(host, user);",
                    "}")
                .accept("web-01 logged in to jsmith")
                .hints("Arguments match by position, not by name.",
                       "The first argument is host's value.")
                .explain(
                    "web-01 logged in to jsmith. The first argument - the "
                    + "variable host, holding \"web-01\" - goes into the first "
                    + "parameter, user. The names matching up means nothing "
                    + "to Java.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "static void report(String host, int port) {",
                    "    System.out.println(host + \":\" + port);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    report(\"web-01\", 443);",
                    "    report(22, \"db-01\");",
                    "}")
                .accept("7", "line 7")
                .hints("Check each argument against its position.",
                       "What type is the first parameter?")
                .explain(
                    "Line 7. The first argument, 22, is an int, but the first "
                    + "parameter is a String. When the types differ, the "
                    + "compiler catches a swap. When they are the same, it "
                    + "cannot.")
                .xp(20))
            .objective(
                "Call the firewall method to block RDP on fw-01.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // call blockPort for host fw-01, port 3389",
                "    }",
                "",
                "    static void blockPort(String host, int port) {",
                "        System.out.println(\"BLOCK \" + port + \" on \" + host);",
                "    }",
                "}")
            .yourTask(
                "Write the call to blockPort for the host fw-01 and the port "
                + "3389.")
            .mainTask(new Task(Task.WRITE,
                    "Write the call to blockPort.")
                .accept("blockPort(\"fw-01\", 3389);",
                        "blockPort(\"fw-01\",3389);")
                .hints(
                    "Look at the header to see which comes first.",
                    "The host is text, so it needs quotes. The port is a "
                    + "number. Separate them with a comma.",
                    "blockPort(\"fw-01\", 3389);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        blockPort(\"fw-01\", 3389);",
                    "    }",
                    "",
                    "    static void blockPort(String host, int port) {",
                    "        System.out.println(\"BLOCK \" + port + \" on \" + host);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The header says host first, then port, so the call "
                    + "passes \"fw-01\" first and 3389 second. The program "
                    + "prints BLOCK 3389 on fw-01.\n"
                    + "\n"
                    + "The body prints the port before the host, but that "
                    + "changes nothing about the call: the ORDER OF THE "
                    + "HEADER decides the order of the arguments, whatever the "
                    + "body does with them.")
                .explain(
                    "Header order: blockPort(\"fw-01\", 3389);")
                .xp(20))
            .mistakes(
                new String[]{"Swapping two arguments of the same type",
                    "It compiles and runs. Only reading the header catches "
                    + "it."},
                new String[]{"The wrong number of arguments",
                    "Two parameters need exactly two arguments."},
                new String[]{"Thinking names are matched",
                    "A variable called host passed first goes into the first "
                    + "parameter, whatever that is called."})
            .cyber(
                "Swapped same-type arguments are a real source of security "
                + "bugs. Many security methods take several values of one "
                + "type: (expected, actual), (user, role), (key, data). Swap "
                + "them and the code still compiles - but a check might grant "
                + "the role \"jsmith\" to a user called \"admin\", or compare "
                + "a password with itself.\n"
                + "\n"
                + "That is why reviewers check the ORDER of arguments at "
                + "every call to a security-sensitive method. The compiler "
                + "only checks types.")
            .check(new Task(Task.CHOICE,
                    "Header: static void grant(String user, String role). "
                    + "Which call gives jsmith the admin role?")
                .choices("grant(\"admin\", \"jsmith\");",
                         "grant(\"jsmith\", \"admin\");",
                         "grant(role = \"admin\", user = \"jsmith\");",
                         "grant(String \"jsmith\", String \"admin\");")
                .accept("2", "b")
                .hints("User first, role second.",
                       "Java has no named arguments.")
                .explain(
                    "grant(\"jsmith\", \"admin\"). Option a compiles too - and "
                    + "gives a user called admin the role jsmith. Options c "
                    + "and d are not Java.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void gap(int a, int b) {",
                    "    System.out.println(a - b);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    int b = 10;",
                    "    int a = 3;",
                    "    gap(b, a);",
                    "}")
                .accept("7")
                .hints("Inside gap, a is the FIRST argument.",
                       "The first argument is main's b: 10.")
                .explain(
                    "7. main's b (10) goes into gap's a, and main's a (3) "
                    + "goes into gap's b. 10 - 3 is 7. The same names in two "
                    + "methods are two different variables.")
                .xp(15))
            .recap(
                "    static void report(String host, int port)\n"
                + "    report(\"web-01\", 443);\n"
                + "\n"
                + "Arguments match parameters by POSITION. Count, order and "
                + "type must fit. Two parameters of the same type can be "
                + "swapped without any error - check the header.")
            .next("Next: a method that hands an answer back."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(6), "An Answer Back", 4)
            .brief(
                "The risk dashboard needs a score for each alert: severity "
                + "times exposure. A void method could PRINT the score - but "
                + "the dashboard needs to compare it, add it up and sort by "
                + "it. The method has to hand the number BACK.")
            .willLearn("return", "Return values")
            .whyUseful(
                "Most useful methods answer a question: how risky, how many, "
                + "is it valid. Returning a value lets the caller decide what "
                + "to do with the answer instead of the method deciding for "
                + "it.")
            .concept("return",
                "A method can hand a value back to its caller. Two things "
                + "change:\n"
                + "\n"
                + "    static int riskScore(int severity, int exposure) {\n"
                + "        return severity * exposure;\n"
                + "    }\n"
                + "\n"
                + "    int        the RETURN TYPE, where void used to be\n"
                + "    return     works out the value and hands it back\n"
                + "\n"
                + "The call then BECOMES that value, wherever it is written:\n"
                + "\n"
                + "    int score = riskScore(7, 3);      score is 21\n"
                + "    System.out.println(riskScore(2, 5));  prints 10\n"
                + "\n"
                + "return also ENDS the method on the spot. Nothing after it "
                + "runs, and a statement straight after a return does not even "
                + "compile: 'unreachable statement'.\n"
                + "\n"
                + "A method with a return type MUST return a value on every "
                + "path through it. If it can reach its closing } without "
                + "one, javac reports 'missing return statement'.\n"
                + "\n"
                + "The caller is free to ignore the value - riskScore(7, 3); "
                + "on its own line compiles, and the 21 is simply thrown "
                + "away. That is legal, and almost always a mistake.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int score = riskScore(7, 3);",
                "        System.out.println(\"Risk: \" + score);",
                "        System.out.println(\"Doubled: \" + riskScore(7, 3) * 2);",
                "    }",
                "",
                "    static int riskScore(int severity, int exposure) {",
                "        return severity * exposure;",
                "    }",
                "}")
            .exampleOutput(
                "Risk: 21",
                "Doubled: 42")
            .lineByLine(
                new String[]{"static int riskScore(...)",
                    "int instead of void: this method hands back an int."},
                new String[]{"return severity * exposure;",
                    "Works out 21 and hands it back. The method ends here."},
                new String[]{"int score = riskScore(7, 3);",
                    "The call becomes 21, which is stored in score."},
                new String[]{"riskScore(7, 3) * 2",
                    "A call can sit inside a calculation: 21 * 2 is 42."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int square(int n) {",
                    "    return n * n;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    int a = square(3);",
                    "    System.out.println(a + square(2));",
                    "}")
                .accept("13")
                .hints("a is 9.",
                       "square(2) becomes 4.")
                .explain(
                    "13. square(3) returns 9 into a, square(2) becomes 4 "
                    + "inside the println, and 9 + 4 is 13.")
                .xp(10))
            .practice(new Task(Task.DEBUG,
                    "Which line does javac report?")
                .code(
                    "static int bonus(int level) {",
                    "    int points = level * 10;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(bonus(2));",
                    "}")
                .accept("3", "line 3")
                .hints("bonus promises an int. Does it hand one back?",
                       "Where does the method end without returning?")
                .explain(
                    "Line 3: 'missing return statement'. bonus works out "
                    + "points but never returns it, so it reaches its closing "
                    + "brace with nothing to hand back. javac points at that "
                    + "brace. The fix is return points; - or just return "
                    + "level * 10;")
                .xp(20))
            .objective(
                "Make the method hand back the total number of failures.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int total = totalFailures(4, 9);",
                "        System.out.println(\"Total failures: \" + total);",
                "    }",
                "",
                "    static int totalFailures(int ssh, int vpn) {",
                "        // return the sum of both",
                "    }",
                "}")
            .yourTask(
                "Write the return line: totalFailures must hand back ssh "
                + "plus vpn.")
            .mainTask(new Task(Task.WRITE,
                    "Write the return statement.")
                .accept("return ssh + vpn;", "return ssh+vpn;",
                        "return vpn + ssh;", "return vpn+ssh;",
                        "return (ssh + vpn);")
                .hints(
                    "The keyword return, then the value to hand back.",
                    "The value is a calculation using both parameters.",
                    "return ssh + vpn;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int total = totalFailures(4, 9);",
                    "        System.out.println(\"Total failures: \" + total);",
                    "    }",
                    "",
                    "    static int totalFailures(int ssh, int vpn) {",
                    "        return ssh + vpn;",
                    "    }",
                    "}")
                .whyItWorks(
                    "return ssh + vpn; adds the two parameters and hands the "
                    + "result back. In main, the call totalFailures(4, 9) "
                    + "becomes 13, which is stored in total and printed.\n"
                    + "\n"
                    + "Without the return, the starter does not compile at "
                    + "all: the header promises an int, and javac holds the "
                    + "method to that promise.")
                .explain(
                    "return, the sum, and a semicolon.")
                .xp(20))
            .mistakes(
                new String[]{"Printing instead of returning",
                    "A println shows the value to a person. return hands it to "
                    + "the program."},
                new String[]{"No return on some path",
                    "javac: 'missing return statement'."},
                new String[]{"Code after return",
                    "It can never run. javac: 'unreachable statement'."})
            .cyber(
                "Returning a value instead of printing it is what makes a "
                + "check REUSABLE. A method that prints \"RISK: HIGH\" can "
                + "only ever tell a person. A method that returns 21 can feed "
                + "a dashboard, trigger a page, be compared to a threshold, "
                + "and be tested automatically.\n"
                + "\n"
                + "It also separates deciding from acting. Security tools are "
                + "safer when the method that decides (is this risky?) is "
                + "separate from the method that acts (block, alert, "
                + "delete) - each can be checked on its own.")
            .check(new Task(Task.CHOICE,
                    "What is the return type of  static double "
                    + "average(int a, int b) ?")
                .choices("int", "double", "void", "static")
                .accept("2", "b")
                .hints("It sits just before the name.",
                       "What was void's position?")
                .explain(
                    "double. The word before the method's name is the type of "
                    + "value it hands back.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static String label(int n) {",
                    "    return \"N\" + n;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    label(5);",
                    "    System.out.println(label(7));",
                    "}")
                .accept("N7")
                .hints("label(5); on its own does not print anything.",
                       "Its value is thrown away.")
                .explain(
                    "N7. The first call works out \"N5\" and throws it away - "
                    + "returning is not printing. Only the second value "
                    + "reaches a println.")
                .xp(15))
            .recap(
                "    static int riskScore(int s, int e) {\n"
                + "        return s * e;\n"
                + "    }\n"
                + "\n"
                + "The return type replaces void. return hands the value back "
                + "and ends the method. The call becomes the value. Every path "
                + "must return.")
            .next("Next: returning doubles, booleans and Strings."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(7), "Return Types", 4)
            .brief(
                "The log toolkit needs four answers: a cleaned-up host name, "
                + "a failure RATE, whether a port is privileged, and a count "
                + "of minutes. Four questions, four kinds of answer. The "
                + "return type has to match what each one gives back.")
            .willLearn("Return types")
            .whyUseful(
                "The return type is the method's promise about its answer. "
                + "Get it right and the compiler checks every caller for you; "
                + "get it wrong and numbers get silently rounded or code "
                + "refuses to compile.")
            .concept("Return types",
                "Any type can be a return type. The value after return must "
                + "fit it:\n"
                + "\n"
                + "    static String normalise(String host)\n"
                + "        return host.trim().toLowerCase();\n"
                + "\n"
                + "    static double rate(int failed, int total)\n"
                + "        return (double) failed / total;\n"
                + "\n"
                + "    static boolean isPrivileged(int port)\n"
                + "        return port < 1024;\n"
                + "\n"
                + "    static int minutes(int hours)\n"
                + "        return hours * 60;\n"
                + "\n"
                + "The same rules as assigning to a variable apply:\n"
                + "\n"
                + "    int into double      fine - widened automatically\n"
                + "    double into int      will not compile: 'possible\n"
                + "                         lossy conversion'\n"
                + "    int into String      will not compile\n"
                + "\n"
                + "A TRAP: the return type does not change how the value is "
                + "worked out. In a method declared double, return n / 2; "
                + "with an int n still does INTEGER division first. For 7, "
                + "that is 3, which is then widened to 3.0. The cast in rate "
                + "above is there on purpose.\n"
                + "\n"
                + "A boolean method can return a condition directly: "
                + "return port < 1024; hands back true or false.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(normalise(\"  WEB-01 \"));",
                "        System.out.println(rate(3, 40));",
                "        System.out.println(isPrivileged(22));",
                "        System.out.println(minutes(2));",
                "    }",
                "",
                "    static String normalise(String host) {",
                "        return host.trim().toLowerCase();",
                "    }",
                "",
                "    static double rate(int failed, int total) {",
                "        return (double) failed / total;",
                "    }",
                "",
                "    static boolean isPrivileged(int port) {",
                "        return port < 1024;",
                "    }",
                "",
                "    static int minutes(int hours) {",
                "        return hours * 60;",
                "    }",
                "}")
            .exampleOutput(
                "web-01",
                "0.075",
                "true",
                "120")
            .lineByLine(
                new String[]{"return host.trim().toLowerCase();",
                    "A String expression, for a String method."},
                new String[]{"return (double) failed / total;",
                    "The cast makes it decimal division: 3.0 / 40 is 0.075."},
                new String[]{"return port < 1024;",
                    "The condition itself is the boolean answer."},
                new String[]{"return hours * 60;",
                    "An int for an int method."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static double half(int n) {",
                    "    return n / 2;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(half(7));",
                    "}")
                .accept("3.0")
                .hints("n and 2 are both ints.",
                       "Integer division happens BEFORE the value becomes a "
                       + "double.")
                .explain(
                    "3.0. n / 2 is int division: 7 / 2 is 3. Only then is 3 "
                    + "widened to the double 3.0. The return type cannot "
                    + "bring the .5 back - return n / 2.0; would.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "static int average(int a, int b) {",
                    "    return (a + b) / 2.0;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(average(3, 4));",
                    "}")
                .accept("2", "line 2")
                .hints("What type is (a + b) / 2.0?",
                       "Can a double go into an int without a cast?")
                .explain(
                    "Line 2: 'possible lossy conversion from double to int'. "
                    + "Dividing by 2.0 makes a double, and the method promises "
                    + "an int. Either declare it double, or divide by 2 and "
                    + "accept the rounding down.")
                .xp(20))
            .objective(
                "Declare a method that answers: is this role an admin?")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(isAdmin(\"admin\"));",
                "        System.out.println(isAdmin(\"guest\"));",
                "    }",
                "",
                "    // write the header: isAdmin, takes a String role",
                "        return role.equals(\"admin\");",
                "    }",
                "}")
            .yourTask(
                "Write the header for isAdmin. It takes a String called role "
                + "and gives back true or false.")
            .mainTask(new Task(Task.WRITE,
                    "Write the header line for isAdmin.")
                .accept("static boolean isAdmin(String role) {",
                        "static boolean isAdmin(String role){",
                        "public static boolean isAdmin(String role) {",
                        "public static boolean isAdmin(String role){")
                .hints(
                    "What type does role.equals(\"admin\") give?",
                    "That type goes where void used to go.",
                    "static boolean isAdmin(String role) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(isAdmin(\"admin\"));",
                    "        System.out.println(isAdmin(\"guest\"));",
                    "    }",
                    "",
                    "    static boolean isAdmin(String role) {",
                    "        return role.equals(\"admin\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "equals gives a boolean, so the method's return type is "
                    + "boolean. The program prints true, then false.\n"
                    + "\n"
                    + "Choosing the return type is choosing what kind of "
                    + "answer the method gives. Declare it int or String here "
                    + "and javac refuses, because a boolean does not fit "
                    + "either.")
                .explain(
                    "static boolean isAdmin(String role) {")
                .xp(20))
            .mistakes(
                new String[]{"Expecting the return type to fix division",
                    "n / 2 in a double method is still int division."},
                new String[]{"Returning a double from an int method",
                    "javac: 'possible lossy conversion'."},
                new String[]{"Returning a String like \"true\"",
                    "\"true\" is text, not a boolean."})
            .cyber(
                "Types in return values are a quiet safety feature. A method "
                + "that returns boolean can only ever answer yes or no - "
                + "there is no third, unexpected value for a caller to "
                + "misread. A method that returned the String \"true\", "
                + "\"True\" or \"yes\" would need every caller to guess which "
                + "spellings mean allowed.\n"
                + "\n"
                + "The integer-division trap matters too. A failure rate that "
                + "should be 0.9 but comes back as 0.0 will never cross any "
                + "alert threshold - the attack happens, the dashboard shows "
                + "nothing.")
            .check(new Task(Task.CHOICE,
                    "A method returns host.length(). Its return type should "
                    + "be:")
                .choices("String", "int", "boolean", "void")
                .accept("2", "b")
                .hints("What does length() give?",
                       "A count of characters.")
                .explain(
                    "int. length() gives an int, so that is what the method "
                    + "hands back.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which return fits  static String level(int n) ?")
                .choices("return n;", "return \"L\" + n;", "return n > 3;",
                         "return;")
                .accept("2", "b")
                .hints("The value must be a String.",
                       "Joining text to a number gives text.")
                .explain(
                    "return \"L\" + n; - joining a String and an int gives a "
                    + "String. n is an int, n > 3 is a boolean, and a bare "
                    + "return; gives nothing at all.")
                .xp(10))
            .recap(
                "Any type can be returned. The value must fit the type: int "
                + "widens to double, double never quietly narrows to int.\n"
                + "\n"
                + "The return type does not change the arithmetic - int "
                + "division still happens first. Boolean methods can return a "
                + "condition directly.")
            .next("Next: using a returned value in a decision."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(8), "Using the Answer", 4)
            .brief(
                "The lockout code asks a method whether an account is over "
                + "its limit, then decides. The answer never needs a variable "
                + "of its own: a call can sit right inside the if. But "
                + "writing the same call twice has a cost worth knowing.")
            .willLearn("Calls in conditions")
            .whyUseful(
                "Real code chains methods: if (isBlocked(ip)), "
                + "alert(normalise(host)). Reading those lines means knowing "
                + "what runs first, and how many times.")
            .concept("Calls in conditions",
                "A call that returns a value can go anywhere a value of that "
                + "type can go:\n"
                + "\n"
                + "    if (overLimit(failures)) { ... }     a boolean\n"
                + "    int total = count(a) + count(b);     two ints\n"
                + "    alert(normalise(host));              a String\n"
                + "\n"
                + "NESTED CALLS run from the inside out. In "
                + "alert(normalise(host)), normalise runs first; its answer "
                + "becomes alert's argument. An argument is always worked out "
                + "before the method it goes to is called.\n"
                + "\n"
                + "A boolean method reads best on its own:\n"
                + "\n"
                + "    if (overLimit(failures))            clear\n"
                + "    if (overLimit(failures) == true)    same, longer\n"
                + "    if (!overLimit(failures))           the opposite\n"
                + "\n"
                + "EVERY CALL RUNS THE METHOD. Write the same call twice and "
                + "the body runs twice. That wastes work, and if the method "
                + "prints, reads input or changes something, it happens "
                + "twice. When a result is needed more than once, call once "
                + "and keep it in a variable.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failures = 6;",
                "        if (overLimit(failures)) {",
                "            System.out.println(\"LOCK \" + label(\"jsmith\"));",
                "        } else {",
                "            System.out.println(\"OK\");",
                "        }",
                "    }",
                "",
                "    static boolean overLimit(int failures) {",
                "        return failures >= 5;",
                "    }",
                "",
                "    static String label(String user) {",
                "        return \"[\" + user.toUpperCase() + \"]\";",
                "    }",
                "}")
            .exampleOutput(
                "LOCK [JSMITH]")
            .lineByLine(
                new String[]{"if (overLimit(failures))",
                    "overLimit runs and returns true. The if takes that "
                    + "branch."},
                new String[]{"\"LOCK \" + label(\"jsmith\")",
                    "label runs first and becomes \"[JSMITH]\"; then the "
                    + "text is joined and printed."},
                new String[]{"return failures >= 5;",
                    "The condition is the answer: 6 >= 5 is true."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int twice(int n) {",
                    "    return n * 2;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(twice(twice(3)) + 1);",
                    "}")
                .accept("13")
                .hints("Start with the innermost call.",
                       "twice(3) is 6, then twice(6).")
                .explain(
                    "13. The inner twice(3) returns 6, the outer twice(6) "
                    + "returns 12, and 12 + 1 is 13.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int load() {",
                    "    System.out.println(\"loading\");",
                    "    return 5;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    if (load() > 3) {",
                    "        System.out.println(\"high: \" + load());",
                    "    }",
                    "}")
                .accept("loading loading high: 5")
                .hints("How many times is load() called?",
                       "The second call runs before the println can print.")
                .explain(
                    "    loading\n"
                    + "    loading\n"
                    + "    high: 5\n"
                    + "\n"
                    + "Two calls, two runs of the body. Storing the result "
                    + "once - int level = load(); - would print loading "
                    + "once.")
                .xp(20))
            .objective(
                "Drop traffic from the blocklisted range.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String ip = \"203.0.113.50\";",
                "        // write the if line: ask isBlocked about ip",
                "            System.out.println(\"DROP \" + ip);",
                "        } else {",
                "            System.out.println(\"PASS \" + ip);",
                "        }",
                "    }",
                "",
                "    static boolean isBlocked(String ip) {",
                "        return ip.startsWith(\"203.0.113.\");",
                "    }",
                "}")
            .yourTask(
                "Write the if line that takes the DROP branch when isBlocked "
                + "says yes for ip.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line.")
                .accept("if (isBlocked(ip)) {", "if(isBlocked(ip)) {",
                        "if (isBlocked(ip)){", "if (isBlocked(ip) == true) {")
                .hints(
                    "isBlocked(ip) already gives a boolean.",
                    "The call goes straight inside the if's brackets.",
                    "if (isBlocked(ip)) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String ip = \"203.0.113.50\";",
                    "        if (isBlocked(ip)) {",
                    "            System.out.println(\"DROP \" + ip);",
                    "        } else {",
                    "            System.out.println(\"PASS \" + ip);",
                    "        }",
                    "    }",
                    "",
                    "    static boolean isBlocked(String ip) {",
                    "        return ip.startsWith(\"203.0.113.\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "The call isBlocked(ip) runs and becomes true, because "
                    + "the address starts with 203.0.113. The if needs a "
                    + "boolean and gets one, so DROP 203.0.113.50 is "
                    + "printed.\n"
                    + "\n"
                    + "Writing == true also works, but adds nothing: the call "
                    + "is already true or false. A well-named boolean method "
                    + "makes the if read like a sentence: if is blocked.")
                .explain(
                    "The boolean call goes straight inside the if.")
                .xp(20))
            .mistakes(
                new String[]{"Calling twice for one answer",
                    "Each call runs the body again. Store the result."},
                new String[]{"Reading nested calls outside-in",
                    "The innermost call runs first."},
                new String[]{"== true",
                    "Not wrong, just noise. The call is already a boolean."})
            .cyber(
                "The call-twice cost is a real security bug class, called "
                + "TIME OF CHECK TO TIME OF USE (TOCTOU). Code asks "
                + "\"is this file safe?\" with one call, then opens the file "
                + "with another. In between, an attacker swaps the file. The "
                + "check was true; the use got something else.\n"
                + "\n"
                + "The defence starts with the habit from this mission: get "
                + "an answer once, and make sure the thing you act on is the "
                + "thing you checked.")
            .check(new Task(Task.CHOICE,
                    "In  alert(normalise(host)) , which method runs first?")
                .choices("alert", "normalise", "Both at the same time",
                         "It depends on the host")
                .accept("2", "b")
                .hints("An argument is worked out before its method is "
                       + "called.",
                       "Inside out.")
                .explain(
                    "normalise. Its result is alert's argument, and arguments "
                    + "are always worked out first.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static String tag(String s) {",
                    "    return \"<\" + s + \">\";",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    String t = tag(\"a\") + tag(\"b\");",
                    "    System.out.println(t.length());",
                    "}")
                .accept("6")
                .hints("t is \"<a>\" joined to \"<b>\".",
                       "Count the characters of <a><b>.")
                .explain(
                    "6. The two calls give \"<a>\" and \"<b>\", joined into "
                    + "\"<a><b>\", which is 6 characters long.")
                .xp(15))
            .recap(
                "A call can go anywhere its value can: in an if, a "
                + "calculation, or another call's brackets. Nested calls run "
                + "inside out.\n"
                + "\n"
                + "Every call runs the body. Need an answer twice? Call once "
                + "and keep it.")
            .next("Next: leaving a method early when something is wrong."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(9), "Leaving Early", 4)
            .brief(
                "The port labeller must refuse nonsense ports before it does "
                + "anything else. Wrapping all its real work in a giant else "
                + "makes it hard to read. There is a cleaner shape: deal with "
                + "every bad case first, and leave.")
            .willLearn("Guard clauses")
            .whyUseful(
                "Guard clauses keep the rejection rules at the top of a "
                + "method, where a reviewer sees them first, and keep the main "
                + "logic flat and readable underneath.")
            .concept("Guard clauses",
                "A method may contain several return statements. The FIRST "
                + "one reached ends the method, and the rest never run.\n"
                + "\n"
                + "That allows a shape called a GUARD CLAUSE: check for a bad "
                + "case, and return straight away:\n"
                + "\n"
                + "    static String portLabel(int port) {\n"
                + "        if (port < 1 || port > 65535) {\n"
                + "            return \"INVALID\";\n"
                + "        }\n"
                + "        if (port < 1024) {\n"
                + "            return \"SYSTEM\";\n"
                + "        }\n"
                + "        return \"USER\";\n"
                + "    }\n"
                + "\n"
                + "No else is needed: if the first if returned, nothing below "
                + "it runs. By the last line, the method KNOWS the port is "
                + "valid and not a system port.\n"
                + "\n"
                + "A void method can leave early too, with return; and no "
                + "value:\n"
                + "\n"
                + "    if (user.isBlank()) {\n"
                + "        return;\n"
                + "    }\n"
                + "\n"
                + "Guards go FIRST, and each one handles one problem. The "
                + "happy path - the normal case - comes last, with nothing "
                + "wrapped around it.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(portLabel(22));",
                "        System.out.println(portLabel(70000));",
                "        System.out.println(portLabel(8080));",
                "    }",
                "",
                "    static String portLabel(int port) {",
                "        if (port < 1 || port > 65535) {",
                "            return \"INVALID\";",
                "        }",
                "        if (port < 1024) {",
                "            return \"SYSTEM\";",
                "        }",
                "        return \"USER\";",
                "    }",
                "}")
            .exampleOutput(
                "SYSTEM",
                "INVALID",
                "USER")
            .lineByLine(
                new String[]{"return \"INVALID\";",
                    "For 70000 the method ends here. Nothing below runs."},
                new String[]{"return \"SYSTEM\";",
                    "Only reached by valid ports. 22 ends here."},
                new String[]{"return \"USER\";",
                    "Only reached by valid, non-system ports like 8080."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void notify(String user) {",
                    "    if (user.isBlank()) {",
                    "        System.out.println(\"no user\");",
                    "        return;",
                    "    }",
                    "    System.out.println(\"notified \" + user);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    notify(\" \");",
                    "    notify(\"jsmith\");",
                    "}")
                .accept("no user notified jsmith")
                .hints("\" \" is blank.",
                       "return; leaves before the last println.")
                .explain(
                    "    no user\n"
                    + "    notified jsmith\n"
                    + "\n"
                    + "The first call hits the guard and leaves. The second "
                    + "passes it and reaches the end.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "static int limit() {",
                    "    return 5;",
                    "    System.out.println(\"limit set\");",
                    "}")
                .accept("3", "line 3")
                .hints("Can anything run after return?",
                       "javac refuses code that can never run.")
                .explain(
                    "Line 3: 'unreachable statement'. return always ends the "
                    + "method, so the println could never run. javac treats "
                    + "that as a mistake rather than ignoring it.")
                .xp(20))
            .objective(
                "Reject an empty user name before it reaches the account "
                + "system.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(checkUser(\"   \"));",
                "        System.out.println(checkUser(\" jsmith \"));",
                "    }",
                "",
                "    static String checkUser(String user) {",
                "        if (user.isBlank()) {",
                "            // hand back REJECTED: empty",
                "        }",
                "        return \"OK: \" + user.trim();",
                "    }",
                "}")
            .yourTask(
                "Write the guard's return line, handing back the text "
                + "REJECTED: empty.")
            .mainTask(new Task(Task.WRITE,
                    "Write the return line inside the guard.")
                .accept("return \"REJECTED: empty\";")
                .hints(
                    "return, then the value, then a semicolon.",
                    "The value is text, so it needs quotes.",
                    "return \"REJECTED: empty\";")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(checkUser(\"   \"));",
                    "        System.out.println(checkUser(\" jsmith \"));",
                    "    }",
                    "",
                    "    static String checkUser(String user) {",
                    "        if (user.isBlank()) {",
                    "            return \"REJECTED: empty\";",
                    "        }",
                    "        return \"OK: \" + user.trim();",
                    "    }",
                    "}")
                .whyItWorks(
                    "For \"   \" the guard's condition is true, and the "
                    + "return hands back REJECTED: empty and ends the method "
                    + "- the last line never runs. For \" jsmith \" the guard "
                    + "is skipped and the method reaches its happy path, "
                    + "returning OK: jsmith.\n"
                    + "\n"
                    + "Without the return, the empty name would fall through "
                    + "the guard and be accepted as OK: - an empty account "
                    + "name let straight into the system.")
                .explain(
                    "return \"REJECTED: empty\"; ends the method early.")
                .xp(20))
            .mistakes(
                new String[]{"A guard that does not return",
                    "Printing an error but carrying on is the classic bug. "
                    + "The guard must leave."},
                new String[]{"Code after a return in the same block",
                    "javac: 'unreachable statement'."},
                new String[]{"Burying the happy path",
                    "Many nested elses hide the main logic. Guards keep it "
                    + "flat."})
            .cyber(
                "A guard that prints an error but forgets to return is one of "
                + "the most common real vulnerabilities there is. The check "
                + "runs, the warning is logged, and then the code carries on "
                + "and does the dangerous thing anyway. Web frameworks have "
                + "shipped exactly this bug: an access check that redirected "
                + "the user to a login page but did not stop the protected "
                + "action from running.\n"
                + "\n"
                + "The rule: a guard rejects AND leaves. If you see a check "
                + "without a return, throw or else, ask what happens next.")
            .check(new Task(Task.CHOICE,
                    "What must a guard clause do after detecting a bad "
                    + "case?")
                .choices("Print a warning and carry on",
                         "Return, so the rest of the method does not run",
                         "Call main again", "Nothing - the if is enough")
                .accept("2", "b")
                .hints("What stops the dangerous code below it?",
                       "Reject AND leave.")
                .explain(
                    "Return. A warning without a return lets the method "
                    + "carry on with the bad value.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int clamp(int n) {",
                    "    if (n > 10) {",
                    "        return 10;",
                    "    }",
                    "    if (n < 0) {",
                    "        return 0;",
                    "    }",
                    "    return n;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(clamp(42) + clamp(-3) + clamp(4));",
                    "}")
                .accept("14")
                .hints("Work out each call on its own.",
                       "10, 0 and 4.")
                .explain(
                    "14. clamp(42) leaves at the first guard with 10, "
                    + "clamp(-3) at the second with 0, and clamp(4) passes "
                    + "both and returns 4.")
                .xp(15))
            .recap(
                "The first return reached ends the method. Guard clauses use "
                + "that: bad case, return - no else needed. void methods leave "
                + "with return; on its own.\n"
                + "\n"
                + "Guards first, happy path last. A guard that does not leave "
                + "is not a guard.")
            .next("Next: methods that answer yes or no."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(10), "Yes-or-No Methods", 4)
            .brief(
                "Three different programs need to know whether a port typed "
                + "by a user is valid. Each has its own slightly different "
                + "check - and one of them accepts port 0. Time to write the "
                + "check once, as a method that answers yes or no.")
            .willLearn("Boolean methods")
            .whyUseful(
                "Validation rules belong in boolean methods: isValidPort, "
                + "isPrivateIp, hasMfa. The rule lives in one place, and every "
                + "if that uses it reads like a sentence.")
            .concept("Boolean methods",
                "A method that returns boolean answers a yes-or-no question. "
                + "By convention its name asks the question: isValidPort, "
                + "hasMfa, canDelete.\n"
                + "\n"
                + "Return the CONDITION itself, not true and false by hand:\n"
                + "\n"
                + "    if (failures >= 5) {        works, but long\n"
                + "        return true;\n"
                + "    } else {\n"
                + "        return false;\n"
                + "    }\n"
                + "\n"
                + "    return failures >= 5;       the same, in one line\n"
                + "\n"
                + "A condition is already true or false, so it can be "
                + "returned directly.\n"
                + "\n"
                + "Guard clauses fit boolean methods well: return false for "
                + "each thing that rules the value out, then return the final "
                + "test:\n"
                + "\n"
                + "    static boolean isValidPort(String text) {\n"
                + "        if (text.length() > 5\n"
                + "                || !text.matches(\"[0-9]+\")) {\n"
                + "            return false;\n"
                + "        }\n"
                + "        int port = Integer.parseInt(text);\n"
                + "        return port >= 1 && port <= 65535;\n"
                + "    }\n"
                + "\n"
                + "The guard makes parseInt safe; the last line is the rule. "
                + "Every caller now writes if (isValidPort(input)) - and "
                + "every caller gets the same, correct answer.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String input = \"8080\";",
                "        if (isValidPort(input)) {",
                "            System.out.println(\"Port accepted: \" + input);",
                "        } else {",
                "            System.out.println(\"Port rejected\");",
                "        }",
                "        System.out.println(isValidPort(\"0\"));",
                "        System.out.println(isValidPort(\"99999999999\"));",
                "    }",
                "",
                "    static boolean isValidPort(String text) {",
                "        if (text.length() > 5",
                "                || !text.matches(\"[0-9]+\")) {",
                "            return false;",
                "        }",
                "        int port = Integer.parseInt(text);",
                "        return port >= 1 && port <= 65535;",
                "    }",
                "}")
            .exampleOutput(
                "Port accepted: 8080",
                "false",
                "false")
            .lineByLine(
                new String[]{"if (isValidPort(input))",
                    "Reads as a sentence: if the input is a valid port."},
                new String[]{"return false;",
                    "The guard: too long or not all digits. 99999999999 "
                    + "leaves here, before parseInt could overflow."},
                new String[]{"return port >= 1 && port <= 65535;",
                    "The rule itself, returned directly. 0 fails it."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static boolean isAdmin(String role) {",
                    "    return role.equalsIgnoreCase(\"admin\");",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(isAdmin(\"ADMIN\") && !isAdmin(\"guest\"));",
                    "}")
                .accept("true")
                .hints("isAdmin(\"ADMIN\") ignores case.",
                       "true && !false.")
                .explain(
                    "true. The first call is true (case ignored), the second "
                    + "is false, and !false is true. true && true is true.")
                .xp(10))
            .practice(new Task(Task.CHOICE,
                    "Accounts lock at 5 failures. Which is the best body for "
                    + "static boolean isLocked(int failures)?")
                .choices("if (failures >= 5) { return true; } else "
                         + "{ return false; }",
                         "return failures >= 5;",
                         "return failures > 5;",
                         "return \"failures >= 5\";")
                .accept("2", "b")
                .hints("Two of them are correct; one is shorter.",
                       "Check the boundary and the type too.")
                .explain(
                    "return failures >= 5; - the same answer as a, in one "
                    + "line. c gets the boundary wrong (5 would not lock), "
                    + "and d returns text, which does not compile.")
                .xp(15))
            .objective(
                "Write the rule for internal addresses.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(isInternal(\"10.4.2.1\"));",
                "        System.out.println(isInternal(\"8.8.8.8\"));",
                "    }",
                "",
                "    static boolean isInternal(String ip) {",
                "        // one line: true when ip starts with 10.",
                "    }",
                "}")
            .yourTask(
                "Write isInternal's body in one return line: true when ip "
                + "starts with the text 10.")
            .mainTask(new Task(Task.WRITE,
                    "Write the return line.")
                .accept("return ip.startsWith(\"10.\");")
                .hints(
                    "Which String method checks the beginning of text?",
                    "startsWith already gives a boolean - return it.",
                    "return ip.startsWith(\"10.\");")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(isInternal(\"10.4.2.1\"));",
                    "        System.out.println(isInternal(\"8.8.8.8\"));",
                    "    }",
                    "",
                    "    static boolean isInternal(String ip) {",
                    "        return ip.startsWith(\"10.\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "startsWith gives true or false, which is exactly what "
                    + "the method promises, so it can be returned as it is. "
                    + "The program prints true, then false.\n"
                    + "\n"
                    + "The dot matters: startsWith(\"10\") would also say "
                    + "yes to 100.1.1.1, a public address. A rule in one "
                    + "method means that kind of fix is made once.")
                .explain(
                    "Return the startsWith answer directly.")
                .xp(20))
            .mistakes(
                new String[]{"if-true-else-false",
                    "Return the condition itself."},
                new String[]{"Names that do not ask",
                    "status(port) could mean anything. isValidPort(port) "
                    + "cannot."},
                new String[]{"Returning \"true\"",
                    "Text is not a boolean."})
            .cyber(
                "The brief's bug - three copies of a port check, one of them "
                + "wrong - is how real validation gaps appear. An API "
                + "validates input on one endpoint but uses a subtly "
                + "different check on another; attackers probe every "
                + "endpoint to find the weakest.\n"
                + "\n"
                + "Security libraries are, to a large extent, collections of "
                + "well-tested boolean methods: is this a valid email, a safe "
                + "file name, an allowed redirect. Mission 26 builds a small "
                + "library of your own.")
            .check(new Task(Task.CHOICE,
                    "Which name best suits a method that returns boolean?")
                .choices("mfaStatus", "hasMfaEnabled", "processMfa", "mfa2")
                .accept("2", "b")
                .hints("It should read as a yes-or-no question.",
                       "if (___(user)) should read as a sentence.")
                .explain(
                    "hasMfaEnabled - if (hasMfaEnabled(user)) reads as a "
                    + "question with a yes-or-no answer.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static boolean isEven(int n) {",
                    "    return n % 2 == 0;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    int n = 7;",
                    "    System.out.println(isEven(n) ? \"even\" : \"odd\");",
                    "}")
                .accept("odd")
                .hints("7 % 2 is 1.",
                       "isEven(7) is false.")
                .explain(
                    "odd. 7 % 2 is 1, so the method returns false and the "
                    + "conditional operator picks \"odd\".")
                .xp(10))
            .recap(
                "    static boolean isValidPort(String text)\n"
                + "\n"
                + "Name it as a question. Return the condition directly. "
                + "Guards return false first; the last line is the rule. Write "
                + "the check once and every caller shares it.")
            .next("Next: choosing names that make methods readable."));
    }
}
