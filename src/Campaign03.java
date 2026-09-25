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
                + "run, come back to exactly where you left. Mission 17 shows "
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

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(11), "Names That Tell the Truth", 3)
            .brief(
                "A code review turns up a method called check. It checks a "
                + "password, logs the result, and - it turns out - resets the "
                + "failure counter. Nobody reading if (check(user)) would "
                + "guess the last part. Names are how methods explain "
                + "themselves.")
            .willLearn("Method names")
            .whyUseful(
                "Code is read far more often than it is written. A method "
                + "with an honest, specific name can be used and reviewed "
                + "without opening it; a vague one has to be read every "
                + "time.")
            .concept("Method names",
                "Java only requires a method name to be a legal identifier. "
                + "Programmers follow conventions on top, because names are "
                + "documentation:\n"
                + "\n"
                + "    ACTIONS (void) - a verb\n"
                + "        lockAccount   sendAlert   printReport\n"
                + "\n"
                + "    QUESTIONS (boolean) - is, has, can, should\n"
                + "        isValidPort   hasMfa   shouldLock\n"
                + "\n"
                + "    ANSWERS (a value) - what comes back\n"
                + "        riskScore   failureRate   normaliseHost\n"
                + "\n"
                + "Style: camelCase starting lower case, like variables. "
                + "LockAccount looks like a class; lock_account is not Java "
                + "style.\n"
                + "\n"
                + "Be SPECIFIC. process, handle, doStuff and check say "
                + "nothing. checkPassword is better; isPasswordCorrect is "
                + "better still, because it also says what comes back.\n"
                + "\n"
                + "Be HONEST. The name must cover everything the method does. "
                + "If check also resets a counter, either the name should say "
                + "so or the reset belongs in its own method.\n"
                + "\n"
                + "And remember: Java does not read names. A method called "
                + "isSafe can return true for anything its body says. The "
                + "name is a promise made by a person - which is why "
                + "reviewers check that the body keeps it.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failures = 7;",
                "        if (shouldLock(failures)) {",
                "            lockAccount(\"jsmith\");",
                "        }",
                "    }",
                "",
                "    static boolean shouldLock(int failures) {",
                "        return failures >= 5;",
                "    }",
                "",
                "    static void lockAccount(String user) {",
                "        System.out.println(\"Account \" + user + \" locked\");",
                "    }",
                "}")
            .exampleOutput(
                "Account jsmith locked")
            .lineByLine(
                new String[]{"if (shouldLock(failures))",
                    "A question-name makes the if read as a sentence."},
                new String[]{"lockAccount(\"jsmith\");",
                    "A verb-name says exactly what the call does."},
                new String[]{"static boolean shouldLock",
                    "The name and the return type tell the same story."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static boolean isSafe(int port) {",
                    "    return port == 23;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(isSafe(23));",
                    "}")
                .accept("true")
                .hints("Ignore the name. Read the body.",
                       "Is 23 == 23?")
                .explain(
                    "true. Port 23 is telnet - anything but safe - but Java "
                    + "runs the body, not the name. A misleading name is a "
                    + "bug waiting for a reader to trust it.")
                .xp(10))
            .practice(new Task(Task.CHOICE,
                    "A method sends an alert email and returns nothing. Which "
                    + "name is best?")
                .choices("alert", "sendAlertEmail", "alertEmailDone", "doIt")
                .accept("2", "b")
                .hints("It is an action, so a verb first.",
                       "Specific beats short.")
                .explain(
                    "sendAlertEmail - a verb, and specific. alert could mean "
                    + "many things; alertEmailDone sounds like a question; "
                    + "doIt says nothing.")
                .xp(10))
            .objective(
                "Give the blocklist check an honest, readable name.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String ip = \"203.0.113.9\";",
                "        if (isBlocklisted(ip)) {",
                "            System.out.println(\"DROP \" + ip);",
                "        }",
                "    }",
                "",
                "    // header: isBlocklisted, a yes-or-no answer about ip",
                "        return ip.startsWith(\"203.0.113.\");",
                "    }",
                "}")
            .yourTask(
                "Write the header for isBlocklisted: it takes a String called "
                + "ip and answers yes or no.")
            .mainTask(new Task(Task.WRITE,
                    "Write the header line for isBlocklisted.")
                .accept("static boolean isBlocklisted(String ip) {",
                        "static boolean isBlocklisted(String ip){",
                        "public static boolean isBlocklisted(String ip) {",
                        "public static boolean isBlocklisted(String ip){")
                .hints(
                    "A question-name returns a boolean.",
                    "The parameter is a String called ip.",
                    "static boolean isBlocklisted(String ip) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String ip = \"203.0.113.9\";",
                    "        if (isBlocklisted(ip)) {",
                    "            System.out.println(\"DROP \" + ip);",
                    "        }",
                    "    }",
                    "",
                    "    static boolean isBlocklisted(String ip) {",
                    "        return ip.startsWith(\"203.0.113.\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "The name is a question, so the return type is boolean, "
                    + "and the body returns startsWith's true-or-false answer. "
                    + "The program prints DROP 203.0.113.9.\n"
                    + "\n"
                    + "Read main without looking at the method: if is "
                    + "blocklisted, drop. That is what a good name buys - the "
                    + "caller makes sense on its own.")
                .explain(
                    "static boolean isBlocklisted(String ip) {")
                .xp(20))
            .mistakes(
                new String[]{"Vague names",
                    "check, process, handle - say what is checked or handled."},
                new String[]{"Names that hide side effects",
                    "If it also resets, deletes or sends, the name must say "
                    + "so."},
                new String[]{"Trusting a name",
                    "Java runs the body. Read it before you rely on it."})
            .cyber(
                "Misleading names are a real concern in security review, and "
                + "a deliberate trick in malicious code. Backdoors have been "
                + "hidden in methods with names like updateCache or "
                + "validateInput, relying on reviewers to skim past anything "
                + "that sounds routine.\n"
                + "\n"
                + "The defence is the reviewer's habit: for security-relevant "
                + "code, read the body and check it does what the name says - "
                + "and nothing more.")
            .check(new Task(Task.CHOICE,
                    "A method named printReport also deletes old log files. "
                    + "What is wrong?")
                .choices("Nothing - it compiles",
                         "The name hides part of what it does",
                         "It should return a boolean",
                         "Method names must be shorter")
                .accept("2", "b")
                .hints("Would a caller expect files to disappear?",
                       "Names are promises.")
                .explain(
                    "The name hides a destructive side effect. Split the "
                    + "deletion into its own honestly named method.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which follows Java's naming style for methods?")
                .choices("LockAccount", "lock_account", "lockAccount",
                         "LOCKACCOUNT")
                .accept("3", "c")
                .hints("Same style as variables.",
                       "Lower case first, capital for each new word.")
                .explain(
                    "lockAccount - camelCase starting lower case.")
                .xp(10))
            .recap(
                "Actions get verbs (lockAccount). Questions get "
                + "is/has/can/should (isValidPort). Values are named for what "
                + "comes back (riskScore).\n"
                + "\n"
                + "camelCase, specific, honest. Java runs the body, not the "
                + "name.")
            .next("Next: where a method's variables live."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(12), "Variables That Stay Home", 4)
            .brief(
                "Two methods in the audit tool both have a variable called "
                + "count. An analyst assumes they are the same variable and "
                + "spends an hour chasing a number that 'should' have "
                + "changed. They are not the same - and Java keeps them apart "
                + "on purpose.")
            .willLearn("Local variables")
            .whyUseful(
                "Knowing where a variable lives tells you what can change "
                + "it. That is the difference between a method you can "
                + "understand on its own and one you have to understand "
                + "together with the whole program.")
            .concept("Local variables",
                "A variable declared inside a method is LOCAL to it. So is "
                + "every parameter. A local variable:\n"
                + "\n"
                + "    - is created when the method is called\n"
                + "    - can only be used inside that method\n"
                + "    - disappears when the method returns\n"
                + "\n"
                + "Campaign 02's scope rule, one level up: a variable lives in "
                + "the block where it was declared, and a method's body is a "
                + "block.\n"
                + "\n"
                + "So two methods can each have a variable called count, and "
                + "they are two DIFFERENT variables that never touch:\n"
                + "\n"
                + "    main:     int count = 100;\n"
                + "    addOne:   int count = 0;    a separate variable\n"
                + "\n"
                + "A method cannot see its caller's variables either. If "
                + "report needs main's total, main must hand it over as an "
                + "argument. Using total inside report without that gives "
                + "'cannot find symbol'.\n"
                + "\n"
                + "And locals have NO MEMORY between calls. Every call starts "
                + "fresh: a counter declared inside a method is back to its "
                + "starting value each time. (Mission 21 shows the variable "
                + "that does remember.)\n"
                + "\n"
                + "This isolation is a feature. A method's locals can only be "
                + "changed by that method, so it can be read, tested and "
                + "trusted on its own.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int count = 100;",
                "        addOne();",
                "        addOne();",
                "        System.out.println(\"main's count: \" + count);",
                "    }",
                "",
                "    static void addOne() {",
                "        int count = 0;",
                "        count++;",
                "        System.out.println(\"addOne's count: \" + count);",
                "    }",
                "}")
            .exampleOutput(
                "addOne's count: 1",
                "addOne's count: 1",
                "main's count: 100")
            .lineByLine(
                new String[]{"int count = 100;",
                    "main's own count."},
                new String[]{"int count = 0;",
                    "addOne's own count, created fresh on every call."},
                new String[]{"addOne's count: 1 (twice)",
                    "No memory between calls: each call starts at 0."},
                new String[]{"main's count: 100",
                    "Untouched. addOne never had access to it."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void visit() {",
                    "    int visits = 0;",
                    "    visits++;",
                    "    System.out.println(\"visit \" + visits);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    visit();",
                    "    visit();",
                    "    visit();",
                    "}")
                .accept("visit 1 visit 1 visit 1")
                .hints("Where is visits declared?",
                       "Each call creates it again, starting at 0.")
                .explain(
                    "    visit 1\n"
                    + "    visit 1\n"
                    + "    visit 1\n"
                    + "\n"
                    + "visits is local, so it is created at 0 on every call "
                    + "and thrown away at the end. It never reaches 2.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "static void report() {",
                    "    System.out.println(total);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    int total = 5;",
                    "    report();",
                    "}")
                .accept("2", "line 2")
                .hints("Where is total declared?",
                       "Can report see inside main?")
                .explain(
                    "Line 2: 'cannot find symbol'. total is local to main. "
                    + "report has no variable by that name - main has to pass "
                    + "the value in.")
                .xp(20))
            .objective(
                "Get main's total into the report method.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int total = 5;",
                "        // call report, handing it total",
                "    }",
                "",
                "    static void report(int count) {",
                "        System.out.println(\"Total alerts: \" + count);",
                "    }",
                "}")
            .yourTask(
                "report cannot see main's total, but it has a parameter. "
                + "Write the call that hands total over.")
            .mainTask(new Task(Task.WRITE,
                    "Write the call to report.")
                .accept("report(total);")
                .hints(
                    "Values travel between methods as arguments.",
                    "The argument is main's variable.",
                    "report(total);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int total = 5;",
                    "        report(total);",
                    "    }",
                    "",
                    "    static void report(int count) {",
                    "        System.out.println(\"Total alerts: \" + count);",
                    "    }",
                    "}")
                .whyItWorks(
                    "report(total) copies main's 5 into report's own local "
                    + "parameter, count, and the method prints Total alerts: "
                    + "5.\n"
                    + "\n"
                    + "The two methods never share a variable. main owns "
                    + "total; report owns count. The only connection is the "
                    + "value handed across in the call - which is exactly "
                    + "what makes each one easy to reason about.")
                .explain(
                    "Pass the value in: report(total);")
                .xp(20))
            .mistakes(
                new String[]{"Using another method's variable",
                    "It is not visible. Pass it as an argument."},
                new String[]{"Thinking same name means same variable",
                    "Each method's count is its own."},
                new String[]{"Expecting a local to remember",
                    "Every call starts it fresh."})
            .cyber(
                "Isolation between methods is a small version of a big "
                + "security principle: COMPARTMENTALISATION. If data can only "
                + "be changed by the code that owns it, a bug or a "
                + "compromise in one place cannot silently corrupt another.\n"
                + "\n"
                + "Locals also vanish when the method ends, which is why "
                + "sensitive values - a decrypted password, a session key - "
                + "are kept in locals for as short a time as possible, rather "
                + "than in variables that live for the whole program.")
            .check(new Task(Task.CHOICE,
                    "When does a local variable stop existing?")
                .choices("When the program ends",
                         "When its method returns",
                         "Never",
                         "When main ends")
                .accept("2", "b")
                .hints("It lives in its method's block.",
                       "It is created by the call.")
                .explain(
                    "When its method returns. The next call gets a brand new "
                    + "one.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int square(int x) {",
                    "    int result = x * x;",
                    "    return result;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    int result = 2;",
                    "    int x = square(5);",
                    "    System.out.println(result + x);",
                    "}")
                .accept("27")
                .hints("main's result is still 2.",
                       "square(5) returns 25 into main's x.")
                .explain(
                    "27. square's result and main's result are different "
                    + "variables. main's stays 2; its x becomes 25.")
                .xp(15))
            .recap(
                "Variables declared in a method - and its parameters - are "
                + "local: created by the call, visible only inside, gone at "
                + "the return.\n"
                + "\n"
                + "Same name in two methods means two variables. A method "
                + "cannot see its caller's locals; pass values in as "
                + "arguments.")
            .next("Next: what a method really receives when you pass it a "
                + "variable."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(13), "A Copy, Not the Original", 4)
            .brief(
                "addFailure(failures) is supposed to add one to the failure "
                + "count. It runs, it adds one - and main's count does not "
                + "move. The account never locks. The method is not broken; "
                + "it is doing exactly what Java says a method does.")
            .willLearn("Pass by value")
            .whyUseful(
                "Understanding what a method receives explains a whole class "
                + "of 'but I changed it!' bugs, and shows the right way to "
                + "get a changed value back to the caller.")
            .concept("Pass by value",
                "When you pass a variable to a method, Java copies its VALUE "
                + "into the parameter. The method never gets the variable "
                + "itself:\n"
                + "\n"
                + "    main                addFailure\n"
                + "    failures = 3  --->  failures = 3   (a copy)\n"
                + "                        failures++     now 4\n"
                + "                        returns\n"
                + "    failures is still 3\n"
                + "\n"
                + "The parameter is a local variable that happens to start "
                + "with the caller's value. Changing it changes the copy. The "
                + "caller's variable is untouched - even when the names "
                + "match.\n"
                + "\n"
                + "To get a changed value back, RETURN it, and have the "
                + "caller store it:\n"
                + "\n"
                + "    static int withFailure(int failures) {\n"
                + "        return failures + 1;\n"
                + "    }\n"
                + "\n"
                + "    failures = withFailure(failures);   now 4\n"
                + "\n"
                + "This is called PASS BY VALUE, and it is how Java passes "
                + "every argument. For int, double, boolean and char it means "
                + "exactly what you see here. Campaigns 05 and 06 show what "
                + "it means for arrays and objects - where the copied value "
                + "leads back to something shared.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failures = 3;",
                "        addFailure(failures);",
                "        System.out.println(\"After addFailure: \" + failures);",
                "        failures = withFailure(failures);",
                "        System.out.println(\"After withFailure: \" + failures);",
                "    }",
                "",
                "    static void addFailure(int failures) {",
                "        failures++;",
                "    }",
                "",
                "    static int withFailure(int failures) {",
                "        return failures + 1;",
                "    }",
                "}")
            .exampleOutput(
                "After addFailure: 3",
                "After withFailure: 4")
            .lineByLine(
                new String[]{"addFailure(failures);",
                    "Copies 3 into addFailure's own parameter."},
                new String[]{"failures++;",
                    "Changes the copy to 4. The copy is thrown away at the "
                    + "return."},
                new String[]{"failures = withFailure(failures);",
                    "The method returns 4, and main stores it in its own "
                    + "variable."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int bump(int n) {",
                    "    n = n + 10;",
                    "    return n;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    int n = 1;",
                    "    int m = bump(n);",
                    "    System.out.println(n + \" \" + m);",
                    "}")
                .accept("1 11")
                .hints("bump changes its own n.",
                       "main's n is never assigned again.")
                .explain(
                    "1 11. bump's n becomes 11 and is returned into m. main's "
                    + "n was only ever copied, so it is still 1.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Why does addFailure not change main's failures?")
                .choices("++ does not work inside methods",
                         "The method changed its own copy",
                         "main's variable is final",
                         "Methods cannot take ints")
                .accept("2", "b")
                .hints("What does the parameter hold?",
                       "Pass by value.")
                .explain(
                    "The method changed its own copy. The parameter received "
                    + "main's value, not main's variable.")
                .xp(10))
            .objective(
                "Make the score change reach main.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int score = 40;",
                "        raise(score);    // fix this line",
                "        System.out.println(\"Score: \" + score);",
                "    }",
                "",
                "    static int raise(int score) {",
                "        return score + 10;",
                "    }",
                "}")
            .yourTask(
                "The program prints Score: 40, but should print Score: 50. "
                + "Rewrite the marked line so main's score gets the raised "
                + "value.")
            .mainTask(new Task(Task.WRITE,
                    "Rewrite the marked line.")
                .accept("score = raise(score);")
                .hints(
                    "raise already returns the new value.",
                    "The returned value is thrown away. Store it.",
                    "score = raise(score);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int score = 40;",
                    "        score = raise(score);",
                    "        System.out.println(\"Score: \" + score);",
                    "    }",
                    "",
                    "    static int raise(int score) {",
                    "        return score + 10;",
                    "    }",
                    "}")
                .whyItWorks(
                    "raise(score) receives a copy of 40 and returns 50. "
                    + "Assigning that result to score is the only way main's "
                    + "variable can change, because main is the only method "
                    + "that owns it. The program now prints Score: 50.\n"
                    + "\n"
                    + "The pattern x = change(x); is how Java code updates a "
                    + "value through a method.")
                .explain(
                    "Store what the method returns: score = raise(score);")
                .xp(20))
            .mistakes(
                new String[]{"Changing a parameter to change the caller",
                    "It changes the copy only."},
                new String[]{"Ignoring the returned value",
                    "raise(score); on its own throws the new value away."},
                new String[]{"Thinking matching names share a variable",
                    "Two methods, two variables."})
            .cyber(
                "The brief's bug is a real pattern: a security counter that "
                + "never increases because it is updated in a copy. Failed "
                + "logins that do not count mean an account that never locks "
                + "- and brute-force protection that exists only on paper.\n"
                + "\n"
                + "Bugs like this pass casual testing because nothing crashes. "
                + "They are found by testing the OUTCOME: after six failures, "
                + "is the account actually locked?")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void reset(int level) {",
                    "    level = 0;",
                    "    System.out.println(\"in reset: \" + level);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    int level = 9;",
                    "    reset(level);",
                    "    System.out.println(\"in main: \" + level);",
                    "}")
                .accept("in reset: 0 in main: 9")
                .hints("reset changes its own level.",
                       "main's level was only copied.")
                .explain(
                    "    in reset: 0\n"
                    + "    in main: 9\n"
                    + "\n"
                    + "Inside reset, the copy is 0. Back in main, the "
                    + "original is still 9.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "How does a method get a changed number back to its "
                    + "caller?")
                .choices("By changing its parameter",
                         "By returning it, for the caller to store",
                         "By printing it",
                         "It cannot")
                .accept("2", "b")
                .hints("Parameters are copies.",
                       "What does return do?")
                .explain(
                    "By returning it. The caller decides whether to store it: "
                    + "x = change(x);")
                .xp(10))
            .recap(
                "Java passes a COPY of each argument's value. Changing a "
                + "parameter changes the copy, never the caller's variable.\n"
                + "\n"
                + "To send a new value back: return it, and store it - "
                + "x = change(x);")
            .next("Next: what happens when the argument is a String."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(14), "Passing Text", 4)
            .brief(
                "The log sanitiser takes a user name, trims it and "
                + "lower-cases it. The method runs, and the name in main is "
                + "still full of spaces and capitals. Strings, methods and "
                + "copies meet - and the fix is the same shape as last "
                + "time.")
            .willLearn("Strings as arguments")
            .whyUseful(
                "Most security helpers work on text: cleaning names, masking "
                + "emails, escaping log lines. Every one of them must be "
                + "written to RETURN its result, and every caller must use "
                + "it.")
            .concept("Strings as arguments",
                "Campaign 01 showed that a String can never be changed. "
                + "Methods like trim and toUpperCase do not alter the text - "
                + "they build a NEW String and return it:\n"
                + "\n"
                + "    String s = \"  Hi \";\n"
                + "    s.trim();          a new \"Hi\", thrown away\n"
                + "    s = s.trim();      s now refers to \"Hi\"\n"
                + "\n"
                + "Put that inside a method and it meets pass by value:\n"
                + "\n"
                + "    static void tidyInPlace(String text) {\n"
                + "        text = text.trim().toLowerCase();\n"
                + "    }\n"
                + "\n"
                + "The new, tidy String is stored in text - the method's OWN "
                + "local variable. The caller's variable still refers to the "
                + "old text. When the method returns, the tidy version is "
                + "lost.\n"
                + "\n"
                + "So a method that transforms text must RETURN the result:\n"
                + "\n"
                + "    static String tidy(String text) {\n"
                + "        return text.trim().toLowerCase();\n"
                + "    }\n"
                + "\n"
                + "    user = tidy(user);\n"
                + "\n"
                + "The header says it all: a void method cannot clean text for "
                + "its caller. A String-returning one can.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String user = \"  JSmith \";",
                "        tidyInPlace(user);",
                "        System.out.println(\"[\" + user + \"]\");",
                "        user = tidy(user);",
                "        System.out.println(\"[\" + user + \"]\");",
                "    }",
                "",
                "    static void tidyInPlace(String text) {",
                "        text = text.trim().toLowerCase();",
                "    }",
                "",
                "    static String tidy(String text) {",
                "        return text.trim().toLowerCase();",
                "    }",
                "}")
            .exampleOutput(
                "[  JSmith ]",
                "[jsmith]")
            .lineByLine(
                new String[]{"tidyInPlace(user);",
                    "The method tidies its own copy, then throws it away."},
                new String[]{"[  JSmith ]",
                    "The brackets show the spaces are all still there."},
                new String[]{"user = tidy(user);",
                    "tidy returns the new text, and main stores it."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void shout(String s) {",
                    "    s.toUpperCase();",
                    "    System.out.println(s);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    shout(\"alert\");",
                    "}")
                .accept("alert")
                .hints("What happens to the value toUpperCase returns?",
                       "Is it stored anywhere?")
                .explain(
                    "alert. toUpperCase builds \"ALERT\" and returns it, but "
                    + "nothing stores it. s still refers to \"alert\".")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Which header suits a method that cleans up a user "
                    + "name?")
                .choices("static void clean(String name)",
                         "static String clean(String name)",
                         "static boolean clean(String name)",
                         "static int clean(String name)")
                .accept("2", "b")
                .hints("The caller needs the cleaned text back.",
                       "What type is cleaned text?")
                .explain(
                    "static String clean(String name). Only a returned String "
                    + "can reach the caller; a void version would clean a "
                    + "copy and lose it.")
                .xp(10))
            .objective(
                "Mask an email address before it goes into a shared "
                + "report.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String email = \"jsmith@northstar.example\";",
                "        System.out.println(maskEmail(email));",
                "    }",
                "",
                "    static String maskEmail(String email) {",
                "        int at = email.indexOf(\"@\");",
                "        // return: first letter, then ***, then from the @ on",
                "    }",
                "}")
            .yourTask(
                "Write the return line. The result is the first character, "
                + "then ***, then everything from the @ onwards: "
                + "j***@northstar.example.")
            .mainTask(new Task(Task.WRITE,
                    "Write maskEmail's return line.")
                .accept("return email.charAt(0) + \"***\" + email.substring(at);",
                        "return email.substring(0, 1) + \"***\" + email.substring(at);",
                        "return email.substring(0,1) + \"***\" + email.substring(at);")
                .hints(
                    "Three pieces joined with +.",
                    "The first letter is email.charAt(0). substring(at) "
                    + "gives the @ and everything after it.",
                    "return email.charAt(0) + \"***\" + email.substring(at);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String email = \"jsmith@northstar.example\";",
                    "        System.out.println(maskEmail(email));",
                    "    }",
                    "",
                    "    static String maskEmail(String email) {",
                    "        int at = email.indexOf(\"@\");",
                    "        return email.charAt(0) + \"***\" + email.substring(at);",
                    "    }",
                    "}")
                .whyItWorks(
                    "charAt(0) is 'j'. Joining a char to a String gives text, "
                    + "so 'j' + \"***\" is \"j***\", and substring(at) adds "
                    + "\"@northstar.example\". The method RETURNS the new "
                    + "String; the original email is untouched.\n"
                    + "\n"
                    + "Because it returns, the caller chooses what to do with "
                    + "the masked version - print it, log it, or store it.")
                .explain(
                    "Build the new String and return it.")
                .xp(25))
            .mistakes(
                new String[]{"A void method that transforms text",
                    "The result is lost when the method returns."},
                new String[]{"Calling a String method without storing it",
                    "s.trim(); on its own changes nothing."},
                new String[]{"Not using the returned value",
                    "tidy(user); on its own also changes nothing."})
            .cyber(
                "Sanitising methods that do not return - or whose result "
                + "nobody stores - are a classic source of injection bugs. "
                + "The code LOOKS as if the input is cleaned; a reviewer sees "
                + "sanitise(input) and moves on. But the raw input is what "
                + "reaches the log, the database or the web page.\n"
                + "\n"
                + "Masking, like maskEmail, is standard practice for personal "
                + "data in shared reports and logs: enough to identify a "
                + "record for whoever needs it, not enough to leak the "
                + "address.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static String addTag(String s) {",
                    "    return s + \"!\";",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    String a = \"hi\";",
                    "    addTag(a);",
                    "    String b = addTag(a);",
                    "    System.out.println(a + \" \" + b);",
                    "}")
                .accept("hi hi!")
                .hints("Is a ever assigned again?",
                       "Only b stores a result.")
                .explain(
                    "hi hi!. The first call's result is thrown away. a is "
                    + "never changed; b holds the second call's \"hi!\".")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Why must a text-cleaning method return its result?")
                .choices("Strings cannot change, so it builds a new one",
                         "void methods cannot take Strings",
                         "return is faster than println",
                         "Java requires every method to return")
                .accept("1", "a")
                .hints("What do trim and toLowerCase do?",
                       "They build new text.")
                .explain(
                    "A String cannot be changed, and the parameter is the "
                    + "method's own variable - so the new text has to be "
                    + "handed back.")
                .xp(10))
            .recap(
                "Strings never change; String methods return new ones. A "
                + "method that stores new text in its parameter loses it at "
                + "the return.\n"
                + "\n"
                + "Text transformers return a String, and callers store it: "
                + "user = tidy(user);")
            .next("Next: two methods with the same name."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(15), "Same Name, Different Inputs", 4)
            .brief(
                "Sometimes an alert has a severity, and sometimes it does "
                + "not. Sometimes it is about a host, and sometimes a port. "
                + "alertWithSeverity, alertForPort, alertPlain... or one "
                + "name, alert, that Java picks correctly from what you "
                + "pass.")
            .willLearn("Overloading")
            .whyUseful(
                "Overloading keeps related actions under one clear name. You "
                + "have used it since your first program: println takes a "
                + "String, an int, a double or a boolean, all under one "
                + "name.")
            .concept("Overloading",
                "Two methods in a class can share a name, as long as their "
                + "PARAMETER LISTS differ - in how many parameters there are, "
                + "or in their types:\n"
                + "\n"
                + "    static void alert(String host)\n"
                + "    static void alert(String host, int severity)\n"
                + "    static void alert(int port)\n"
                + "\n"
                + "This is OVERLOADING. At each call, Java looks at the "
                + "arguments and picks the version whose parameters fit:\n"
                + "\n"
                + "    alert(\"web-01\")       the String version\n"
                + "    alert(\"db-02\", 5)     the String, int version\n"
                + "    alert(3389)           the int version\n"
                + "\n"
                + "The name plus the parameter types is called the method's "
                + "SIGNATURE, and no two methods in a class may have the same "
                + "one.\n"
                + "\n"
                + "The RETURN TYPE DOES NOT COUNT. int limit(String role) and "
                + "String limit(String role) have the same signature, so they "
                + "cannot both exist: 'method limit(String) is already "
                + "defined'. Java picks by arguments, and the arguments "
                + "would be the same.\n"
                + "\n"
                + "Parameter NAMES do not count either - only their types and "
                + "order.\n"
                + "\n"
                + "Use overloading for the SAME action on different inputs. "
                + "Two methods that do different things deserve different "
                + "names.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        alert(\"web-01\");",
                "        alert(\"db-02\", 5);",
                "        alert(3389);",
                "    }",
                "",
                "    static void alert(String host) {",
                "        System.out.println(\"ALERT \" + host);",
                "    }",
                "",
                "    static void alert(String host, int severity) {",
                "        System.out.println(\"ALERT \" + host + \" severity \" + severity);",
                "    }",
                "",
                "    static void alert(int port) {",
                "        System.out.println(\"ALERT on port \" + port);",
                "    }",
                "}")
            .exampleOutput(
                "ALERT web-01",
                "ALERT db-02 severity 5",
                "ALERT on port 3389")
            .lineByLine(
                new String[]{"alert(\"web-01\");",
                    "One String argument: Java picks alert(String host)."},
                new String[]{"alert(\"db-02\", 5);",
                    "A String and an int: the two-parameter version."},
                new String[]{"alert(3389);",
                    "One int: alert(int port)."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static String describe(int n) {",
                    "    return \"int \" + n;",
                    "}",
                    "",
                    "static String describe(double d) {",
                    "    return \"double \" + d;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(describe(4));",
                    "    System.out.println(describe(4.0));",
                    "}")
                .accept("int 4 double 4.0")
                .hints("What type is 4? And 4.0?",
                       "Java picks the version that fits exactly.")
                .explain(
                    "    int 4\n"
                    + "    double 4.0\n"
                    + "\n"
                    + "4 is an int literal, so the int version is picked. 4.0 "
                    + "is a double, so the double version is.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does javac report?")
                .code(
                    "static int limit(String role) {",
                    "    return 5;",
                    "}",
                    "",
                    "static String limit(String role) {",
                    "    return \"5\";",
                    "}")
                .accept("5", "line 5")
                .hints("Compare the two signatures: name and parameter "
                       + "types.",
                       "Does the return type make them different?")
                .explain(
                    "Line 5: 'method limit(String) is already defined'. Only "
                    + "the return types differ, and that does not count. A "
                    + "call limit(\"admin\") could not tell them apart.")
                .xp(20))
            .objective(
                "Add a second log method that takes a level.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        log(\"scan started\");",
                "        log(\"port 23 open\", \"WARN\");",
                "    }",
                "",
                "    static void log(String message) {",
                "        System.out.println(\"[INFO] \" + message);",
                "    }",
                "",
                "    // header: another log, with String message, String level",
                "        System.out.println(\"[\" + level + \"] \" + message);",
                "    }",
                "}")
            .yourTask(
                "Write the header for the second log: same name, parameters "
                + "String message then String level, gives nothing back.")
            .mainTask(new Task(Task.WRITE,
                    "Write the second log's header.")
                .accept("static void log(String message, String level) {",
                        "static void log(String message, String level){",
                        "static void log(String message,String level) {",
                        "public static void log(String message, String level) {")
                .hints(
                    "Same name, different parameter list.",
                    "Two parameters, both String, in the order the call "
                    + "uses: message first.",
                    "static void log(String message, String level) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        log(\"scan started\");",
                    "        log(\"port 23 open\", \"WARN\");",
                    "    }",
                    "",
                    "    static void log(String message) {",
                    "        System.out.println(\"[INFO] \" + message);",
                    "    }",
                    "",
                    "    static void log(String message, String level) {",
                    "        System.out.println(\"[\" + level + \"] \" + message);",
                    "    }",
                    "}")
                .whyItWorks(
                    "Both methods are called log, but one takes one String "
                    + "and the other takes two, so their signatures differ. "
                    + "log(\"scan started\") matches the first and prints "
                    + "[INFO] scan started; log(\"port 23 open\", \"WARN\") "
                    + "matches the second and prints [WARN] port 23 open.\n"
                    + "\n"
                    + "Both parameters are Strings, so ORDER matters: the "
                    + "call passes the message first, so the header must "
                    + "declare it first.")
                .explain(
                    "Same name, two String parameters: (String message, "
                    + "String level).")
                .xp(20))
            .mistakes(
                new String[]{"Overloading on return type",
                    "Not allowed. The parameters must differ."},
                new String[]{"Overloading on parameter names",
                    "(String a) and (String b) are the same signature."},
                new String[]{"Same name, different jobs",
                    "Overload only the same action. Different actions get "
                    + "different names."})
            .cyber(
                "Overloading is convenient, but it adds a question to every "
                + "call: WHICH version runs? Security reviewers check this, "
                + "because overloaded methods sometimes do subtly different "
                + "things - a log(String) that sanitises its message and a "
                + "log(String, String) that does not is an injection hole "
                + "hiding behind a familiar name.\n"
                + "\n"
                + "When one overload is safer than another, the safe "
                + "behaviour should live in one place that every version "
                + "shares. Mission 16 shows how.")
            .check(new Task(Task.CHOICE,
                    "Which pair is legal overloading?")
                .choices("int f(int a) and String f(int b)",
                         "void f(int a) and void f(String a)",
                         "void f(int a) and void f(int b)",
                         "void f() and int f()")
                .accept("2", "b")
                .hints("Only the parameter types can make the difference.",
                       "Return types and names do not count.")
                .explain(
                    "void f(int a) and void f(String a) - the parameter types "
                    + "differ. The others differ only by return type or "
                    + "parameter name.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int add(int a, int b) {",
                    "    return a + b;",
                    "}",
                    "",
                    "static String add(String a, String b) {",
                    "    return a + b;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(add(2, 3) + add(\"2\", \"3\"));",
                    "}")
                .accept("523")
                .hints("add(2, 3) is the int version: 5.",
                       "add(\"2\", \"3\") joins text: \"23\".")
                .explain(
                    "523. The int version returns 5, the String version "
                    + "returns \"23\", and 5 + \"23\" joins into \"523\".")
                .xp(20))
            .recap(
                "Methods can share a name if their parameter lists differ in "
                + "number or type. Java picks the version whose parameters "
                + "fit the arguments.\n"
                + "\n"
                + "Return type and parameter names do not count. Overload the "
                + "same action; name different actions differently.")
            .next("Next: how Java keeps track of all these calls."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(16), "Methods Calling Methods", 4)
            .brief(
                "The logger has two versions of log, and they format lines "
                + "slightly differently - because the formatting was typed "
                + "twice. A method can call another method, so one version "
                + "can simply hand its work to the other.")
            .willLearn("Methods calling methods")
            .whyUseful(
                "Real programs are built in layers: small methods that do one "
                + "thing, called by bigger methods that combine them. Each "
                + "piece of logic lives in exactly one place.")
            .concept("Methods calling methods",
                "Any method can call any other method in the class - not only "
                + "main. The round-trip rule applies at every level:\n"
                + "\n"
                + "    main  calls  log(\"scan started\")\n"
                + "      log  calls  log(message, \"INFO\")\n"
                + "        that calls  stamp()  and  clean(message)\n"
                + "\n"
                + "Each call pauses its caller, runs, and returns to exactly "
                + "the point it was called from. Values are returned up the "
                + "chain one level at a time.\n"
                + "\n"
                + "DELEGATION. A common use is one overload handing its work to "
                + "another:\n"
                + "\n"
                + "    static void log(String message) {\n"
                + "        log(message, \"INFO\");\n"
                + "    }\n"
                + "\n"
                + "The one-parameter version fills in a default level and "
                + "calls the two-parameter version, which does the real work. "
                + "The formatting now exists ONCE, so the two can never "
                + "drift apart.\n"
                + "\n"
                + "BUILDING UP. Big jobs become short methods that call small, "
                + "well-named helpers. Read the top-level method and you see "
                + "the steps; open a helper only when you need its detail.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        log(\"scan started\");",
                "        log(\"port 23 open\", \"WARN\");",
                "    }",
                "",
                "    static void log(String message) {",
                "        log(message, \"INFO\");",
                "    }",
                "",
                "    static void log(String message, String level) {",
                "        String line = stamp() + \" [\" + level + \"] \";",
                "        System.out.println(line + clean(message));",
                "    }",
                "",
                "    static String stamp() {",
                "        return \"09:14\";",
                "    }",
                "",
                "    static String clean(String text) {",
                "        return text.replace(\"\\n\", \" \").trim();",
                "    }",
                "}")
            .exampleOutput(
                "09:14 [INFO] scan started",
                "09:14 [WARN] port 23 open")
            .lineByLine(
                new String[]{"log(message, \"INFO\");",
                    "The short version delegates to the full one, adding the "
                    + "default level."},
                new String[]{"stamp() + \" [\" + level + \"] \"",
                    "A call inside an expression. stamp runs and returns "
                    + "\"09:14\" first."},
                new String[]{"clean(message)",
                    "Another helper. Line breaks in a message could fake a "
                    + "second log line, so they become spaces."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void inner() {",
                    "    System.out.println(\"inner\");",
                    "}",
                    "",
                    "static void outer() {",
                    "    System.out.println(\"outer start\");",
                    "    inner();",
                    "    System.out.println(\"outer end\");",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    outer();",
                    "    inner();",
                    "}")
                .accept("outer start inner outer end inner")
                .hints("outer pauses while inner runs.",
                       "Then main calls inner once more.")
                .explain(
                    "    outer start\n"
                    + "    inner\n"
                    + "    outer end\n"
                    + "    inner\n"
                    + "\n"
                    + "Every call returns to the exact point it came from - "
                    + "inner's first return lands in the middle of outer.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int bonus(int sev) {",
                    "    return sev >= 7 ? 10 : 0;",
                    "}",
                    "",
                    "static int score(int sev) {",
                    "    return sev + bonus(sev);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(score(8) + \" \" + score(3));",
                    "}")
                .accept("18 3")
                .hints("score(8) calls bonus(8), which returns 10.",
                       "bonus(3) returns 0.")
                .explain(
                    "18 3. score(8) is 8 + bonus(8) = 8 + 10. score(3) is 3 + "
                    + "0. Each value travels back up one level at a time.")
                .xp(15))
            .objective(
                "Make the short alert hand its work to the full one.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        alert(\"web-01\");",
                "        alert(\"db-02\", 4);",
                "    }",
                "",
                "    static void alert(String host) {",
                "        // call the two-parameter alert, with severity 1",
                "    }",
                "",
                "    static void alert(String host, int severity) {",
                "        System.out.println(\"ALERT \" + host + \" sev \" + severity);",
                "    }",
                "}")
            .yourTask(
                "Write the body of the one-parameter alert: a single call to "
                + "the two-parameter alert, passing host and a severity of 1.")
            .mainTask(new Task(Task.WRITE,
                    "Write the delegating call.")
                .accept("alert(host, 1);", "alert(host,1);")
                .hints(
                    "Call alert with two arguments, so Java picks the "
                    + "two-parameter version.",
                    "The first argument is this method's own parameter.",
                    "alert(host, 1);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        alert(\"web-01\");",
                    "        alert(\"db-02\", 4);",
                    "    }",
                    "",
                    "    static void alert(String host) {",
                    "        alert(host, 1);",
                    "    }",
                    "",
                    "    static void alert(String host, int severity) {",
                    "        System.out.println(\"ALERT \" + host + \" sev \" + severity);",
                    "    }",
                    "}")
                .whyItWorks(
                    "alert(host, 1) has two arguments, so Java calls the "
                    + "two-parameter version with \"web-01\" and 1. The "
                    + "program prints ALERT web-01 sev 1, then ALERT db-02 "
                    + "sev 4.\n"
                    + "\n"
                    + "Only one method formats alerts now. Change the format "
                    + "there and both kinds of call change with it.")
                .explain(
                    "Delegate to the full version: alert(host, 1);")
                .xp(20))
            .mistakes(
                new String[]{"Copying code between overloads",
                    "Delegate instead, so the logic exists once."},
                new String[]{"Losing track of where a return goes",
                    "It goes to the caller, not to main."},
                new String[]{"Giant methods",
                    "Split the steps into helpers with clear names."})
            .cyber(
                "The clean helper in the example is doing real security work. "
                + "If a user name contains a line break, writing it straight "
                + "into a log can create a FAKE log line - an attacker's name "
                + "of \"bob\\nLOGIN OK admin\" becomes two lines, the second "
                + "one forged. That is called LOG INJECTION.\n"
                + "\n"
                + "Because every log line goes through one method, the "
                + "protection is applied everywhere at once. That is the real "
                + "payoff of delegation: a security fix in one place covers "
                + "every path.")
            .check(new Task(Task.CHOICE,
                    "log(message) just calls log(message, \"INFO\"). What is "
                    + "the benefit?")
                .choices("It runs faster",
                         "The formatting lives in one method",
                         "It avoids needing parameters",
                         "Java requires it for overloads")
                .accept("2", "b")
                .hints("How many places format the line?",
                       "Could the two versions drift apart?")
                .explain(
                    "The formatting lives in one method, so every log line is "
                    + "built the same way - and fixed in one place.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static String wrap(String s) {",
                    "    return \"[\" + s + \"]\";",
                    "}",
                    "",
                    "static String label(String s) {",
                    "    return wrap(s.toUpperCase());",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(label(\"warn\") + wrap(\"x\"));",
                    "}")
                .accept("[WARN][x]")
                .hints("label upper-cases first, then wraps.",
                       "wrap(\"x\") is called directly.")
                .explain(
                    "[WARN][x]. label passes \"WARN\" to wrap, which returns "
                    + "\"[WARN]\"; main's own wrap call gives \"[x]\".")
                .xp(15))
            .recap(
                "Any method can call any other. Each call returns to its own "
                + "caller, and values climb back up one level at a time.\n"
                + "\n"
                + "Delegate between overloads, and build big methods from "
                + "small named helpers, so each piece of logic lives in one "
                + "place.")
            .next("Next: how Java remembers where every call came from."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(17), "The Call Stack", 5)
            .brief(
                "main calls report, report calls formatLine, and each one "
                + "has its own variables and its own place to return to. Java "
                + "keeps track of all of it with one simple structure. It is "
                + "also what you will be reading every time a program "
                + "crashes.")
            .willLearn("The call stack", "Stack frames")
            .whyUseful(
                "The call stack explains local variables, returns and "
                + "recursion in one picture - and it is exactly what a crash "
                + "report shows you. Security work leans on it too: stack "
                + "memory is where some of the most famous exploits live.")
            .concept("The call stack",
                "Every call creates a STACK FRAME: a small block of memory "
                + "holding that call's parameters, its local variables, and "
                + "the place to return to.\n"
                + "\n"
                + "Frames are kept on the CALL STACK. A call PUSHES a new "
                + "frame on top; a return POPS it off. Only the top frame is "
                + "running; every frame below it is paused, waiting.\n"
                + "\n"
                + "While formatLine runs in the example, the stack is:\n"
                + "\n"
                + "    +----------------------------------+\n"
                + "    | formatLine  host, count          |  running\n"
                + "    +----------------------------------+\n"
                + "    | report      host, line           |  waiting\n"
                + "    +----------------------------------+\n"
                + "    | main        args                 |  waiting\n"
                + "    +----------------------------------+\n"
                + "\n"
                + "When formatLine returns, its frame is popped - taking its "
                + "local variables with it - and report continues, with its "
                + "own variables exactly as it left them.\n"
                + "\n"
                + "That one picture explains three earlier missions:\n"
                + "\n"
                + "    locals     live in their frame, gone at the pop\n"
                + "    same name  two frames, two variables\n"
                + "    return     pop, and resume the frame below\n"
                + "\n"
                + "main's frame is always at the bottom. When it is popped, "
                + "the program ends.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"main starts\");",
                "        report(\"web-01\");",
                "        System.out.println(\"main ends\");",
                "    }",
                "",
                "    static void report(String host) {",
                "        System.out.println(\"  report starts\");",
                "        String line = formatLine(host, 3);",
                "        System.out.println(\"  report got: \" + line);",
                "    }",
                "",
                "    static String formatLine(String host, int count) {",
                "        System.out.println(\"    formatLine running\");",
                "        return host + \" x\" + count;",
                "    }",
                "}")
            .exampleOutput(
                "main starts",
                "  report starts",
                "    formatLine running",
                "  report got: web-01 x3",
                "main ends")
            .lineByLine(
                new String[]{"report(\"web-01\");",
                    "Push: report's frame goes on top of main's."},
                new String[]{"formatLine(host, 3)",
                    "Push again: three frames. formatLine is running."},
                new String[]{"return host + \" x\" + count;",
                    "Pop: the value goes down to report's frame."},
                new String[]{"} of report",
                    "Pop: back to main, which prints and ends."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void c() {",
                    "    System.out.println(\"c\");",
                    "}",
                    "",
                    "static void b() {",
                    "    System.out.println(\"b in\");",
                    "    c();",
                    "    System.out.println(\"b out\");",
                    "}",
                    "",
                    "static void a() {",
                    "    System.out.println(\"a in\");",
                    "    b();",
                    "    System.out.println(\"a out\");",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    a();",
                    "}")
                .accept("a in b in c b out a out")
                .hints("Frames are popped in the reverse of the order they "
                       + "were pushed.",
                       "c finishes first, then b, then a.")
                .explain(
                    "    a in\n"
                    + "    b in\n"
                    + "    c\n"
                    + "    b out\n"
                    + "    a out\n"
                    + "\n"
                    + "Last in, first out: the last frame pushed is the first "
                    + "one popped.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "In that program, while c is running, how many frames are "
                    + "on the stack?")
                .choices("1", "2", "3", "4")
                .accept("4", "d")
                .hints("Count every method that has been called but has not "
                       + "returned.",
                       "Do not forget main.")
                .explain(
                    "4: main, a, b and c. Every caller is still waiting for "
                    + "its callee to return.")
                .xp(15))
            .objective(
                "Finish the risk method, whose frame waits for the weight.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"Risk: \" + risk(8));",
                "    }",
                "",
                "    static int risk(int severity) {",
                "        int factor = weight(severity);",
                "        // return severity times factor",
                "    }",
                "",
                "    static int weight(int severity) {",
                "        return severity >= 7 ? 3 : 1;",
                "    }",
                "}")
            .yourTask(
                "Write risk's return line: severity multiplied by factor.")
            .mainTask(new Task(Task.WRITE,
                    "Write the return line in risk.")
                .accept("return severity * factor;", "return factor * severity;",
                        "return severity*factor;")
                .hints(
                    "Both values are in risk's own frame.",
                    "Multiply them and return the result.",
                    "return severity * factor;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"Risk: \" + risk(8));",
                    "    }",
                    "",
                    "    static int risk(int severity) {",
                    "        int factor = weight(severity);",
                    "        return severity * factor;",
                    "    }",
                    "",
                    "    static int weight(int severity) {",
                    "        return severity >= 7 ? 3 : 1;",
                    "    }",
                    "}")
                .whyItWorks(
                    "risk's frame holds severity = 8. It pushes weight's "
                    + "frame, which has its OWN severity (also 8, a copy) "
                    + "and returns 3. weight's frame is popped, factor "
                    + "becomes 3, and risk - its frame untouched - returns "
                    + "8 * 3. The program prints Risk: 24.\n"
                    + "\n"
                    + "Two variables called severity, in two frames, never in "
                    + "each other's way.")
                .explain(
                    "return severity * factor; - both live in risk's frame.")
                .xp(20))
            .mistakes(
                new String[]{"Thinking all calls share variables",
                    "Each frame has its own."},
                new String[]{"Popping in the wrong order",
                    "The last call made is the first to return."},
                new String[]{"Forgetting main's frame",
                    "It is always at the bottom."})
            .cyber(
                "The call stack is at the heart of one of the most famous "
                + "attacks in computing: the STACK BUFFER OVERFLOW. In "
                + "languages like C, a frame holds both a program's data and "
                + "the address to return to. Write too much data into a local "
                + "variable and it spills over the return address - and when "
                + "the method returns, it jumps wherever the attacker "
                + "chose.\n"
                + "\n"
                + "Java checks every write, so this attack does not work "
                + "against Java code itself. But the picture in this mission "
                + "is the one exploit developers and malware analysts work "
                + "with every day.")
            .check(new Task(Task.CHOICE,
                    "What happens to a method's frame when it returns?")
                .choices("It stays for the next call",
                         "It is removed, with its local variables",
                         "It moves to the bottom of the stack",
                         "It is saved for later")
                .accept("2", "b")
                .hints("A return pops.",
                       "Where do locals live?")
                .explain(
                    "It is removed - popped - and its local variables go with "
                    + "it. The next call builds a fresh frame.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which frame is always at the bottom of the stack?")
                .choices("The last method called", "main",
                         "The method that crashed", "The longest method")
                .accept("2", "b")
                .hints("Which method is called first?",
                       "When it is popped, the program ends.")
                .explain(
                    "main - the first frame pushed and the last one popped.")
                .xp(10))
            .recap(
                "Each call pushes a frame: parameters, locals, where to "
                + "return. Each return pops it. Only the top frame runs.\n"
                + "\n"
                + "Last in, first out. main is at the bottom; when it pops, "
                + "the program ends.")
            .next("Next: reading the stack when a program crashes."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(18), "Reading a Stack Trace", 5)
            .brief(
                "The overnight report job crashed and left a few lines of "
                + "text in its log. To most people it looks like noise. To "
                + "anyone who knows the call stack, it says exactly which "
                + "line failed, and every call that led there.")
            .willLearn("Stack traces")
            .whyUseful(
                "A stack trace is the first thing you look at when a program "
                + "crashes - yours, a colleague's, or a server under attack. "
                + "Reading one takes seconds once you know the layout.")
            .concept("Stack traces",
                "When something goes wrong that the program does not handle "
                + "- dividing an int by zero, parsing \"abc\" as a number - "
                + "Java stops and prints a STACK TRACE: the call stack at the "
                + "moment it happened.\n"
                + "\n"
                + "    Exception in thread \"main\"\n"
                + "      java.lang.ArithmeticException: / by zero\n"
                + "        at Main.percent(Main.java:12)\n"
                + "        at Main.report(Main.java:8)\n"
                + "        at Main.main(Main.java:3)\n"
                + "\n"
                + "(Java prints the first two lines as one; it is split here "
                + "to fit.)\n"
                + "\n"
                + "    WHAT      the exception type and its message\n"
                + "    WHERE     the top 'at' line: the frame that was\n"
                + "              running - method and line number\n"
                + "    HOW       each line below: who called it, and\n"
                + "              from which line, down to main\n"
                + "\n"
                + "Read it TOP DOWN. The top frame is where it failed. The "
                + "frames below are the chain of calls that got there - the "
                + "stack from the last mission, printed.\n"
                + "\n"
                + "In bigger programs, the top lines are often inside Java's "
                + "own library - parseInt, for example. Keep reading down to "
                + "the first line in YOUR code: that is usually where to "
                + "look.\n"
                + "\n"
                + "These problems are called EXCEPTIONS. Campaign 07 is about "
                + "catching and handling them. For now: read the trace, find "
                + "the line, and fix the cause - usually with a guard.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"Average: \" + average(120, 0));",
                "    }",
                "",
                "    static int average(int total, int count) {",
                "        return total / count;",
                "    }",
                "}")
            .exampleOutput(
                "Exception in thread \"main\" java.lang.ArithmeticException: / by zero",
                "        at Main.average(Main.java:7)",
                "        at Main.main(Main.java:3)")
            .lineByLine(
                new String[]{"ArithmeticException: / by zero",
                    "What went wrong: an int divided by zero."},
                new String[]{"at Main.average(Main.java:7)",
                    "Where: line 7, the return inside average."},
                new String[]{"at Main.main(Main.java:3)",
                    "How it got there: main called average from line 3."},
                new String[]{"no 'Average:' line",
                    "The crash happened while working out the argument, so "
                    + "println never ran."})
            .predict(new Task(Task.CHOICE,
                    "A trace ends with these lines. Which method was running "
                    + "when the program crashed?")
                .code(
                    "java.lang.ArithmeticException: / by zero",
                    "    at Main.percent(Main.java:12)",
                    "    at Main.report(Main.java:8)",
                    "    at Main.main(Main.java:3)")
                .choices("percent", "report", "main", "println")
                .accept("1", "a")
                .hints("The top frame was running.",
                       "The others were waiting.")
                .explain(
                    "percent - the top frame. report and main were paused, "
                    + "waiting for it to return.")
                .xp(10))
            .practice(new Task(Task.CHOICE,
                    "In that trace, report called percent. From which line of "
                    + "Main.java?")
                .choices("3", "8", "12", "It cannot be told")
                .accept("2", "b")
                .hints("Each frame's line is where IT was when things went "
                       + "wrong.",
                       "report was paused at its call to percent.")
                .explain(
                    "8. report's frame was paused at line 8 - the call to "
                    + "percent. Line 12 is inside percent; line 3 is main's "
                    + "call to report.")
                .xp(15))
            .objective(
                "Fix the crash the trace points at.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(percent(5, 0));",
                "        System.out.println(percent(5, 20));",
                "    }",
                "",
                "    static int percent(int failed, int total) {",
                "        if (total == 0) {",
                "            // return 0 here",
                "        }",
                "        return failed * 100 / total;",
                "    }",
                "}")
            .yourTask(
                "The trace said: / by zero, at Main.percent, on the line "
                + "with the division. Write the guard's return line, so a "
                + "total of 0 gives 0 instead of a crash.")
            .mainTask(new Task(Task.WRITE,
                    "Write the return line inside the guard.")
                .accept("return 0;")
                .hints(
                    "The guard must leave before the division.",
                    "percent returns an int.",
                    "return 0;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(percent(5, 0));",
                    "        System.out.println(percent(5, 20));",
                    "    }",
                    "",
                    "    static int percent(int failed, int total) {",
                    "        if (total == 0) {",
                    "            return 0;",
                    "        }",
                    "        return failed * 100 / total;",
                    "    }",
                    "}")
                .whyItWorks(
                    "With a total of 0, the guard returns 0 and the division "
                    + "never runs. With 20, the guard is skipped and 500 / 20 "
                    + "gives 25. The program prints 0, then 25, and no "
                    + "trace.\n"
                    + "\n"
                    + "That is the usual route from a stack trace to a fix: "
                    + "the top frame names the line, the message names the "
                    + "problem, and a guard stops the bad value reaching it.")
                .explain(
                    "return 0; stops the division by zero.")
                .xp(20))
            .mistakes(
                new String[]{"Reading from the bottom",
                    "main is the bottom. The failure is at the top."},
                new String[]{"Stopping at library lines",
                    "Read down to the first line in your own code."},
                new String[]{"Ignoring the message",
                    "/ by zero or For input string: \"abc\" usually names the "
                    + "cause."})
            .cyber(
                "Stack traces are gold for defenders and attackers alike. For "
                + "an analyst, they pinpoint the failing line in seconds. For "
                + "an attacker probing a web application, a stack trace shown "
                + "on an error page reveals class names, method names, "
                + "libraries and versions - a map of the application's "
                + "insides.\n"
                + "\n"
                + "That is why a secure application LOGS the full trace for "
                + "its developers but shows users only a generic error. "
                + "Leaking stack traces is a recognised weakness in web "
                + "security testing checklists.")
            .check(new Task(Task.CHOICE,
                    "In a stack trace, which frame is printed first, at the "
                    + "top?")
                .choices("main", "Where the problem happened",
                         "The first method in the file", "The shortest method")
                .accept("2", "b")
                .hints("It is the stack, top frame first.",
                       "Which frame was running?")
                .explain(
                    "Where the problem happened - the frame that was running. "
                    + "main is at the bottom.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "A trace line reads  at Main.parse(Main.java:21) . What "
                    + "is 21?")
                .choices("An error code", "A line number in Main.java",
                         "The number of calls so far", "A memory address")
                .accept("2", "b")
                .hints("It comes after the file name.",
                       "It tells you where to look.")
                .explain(
                    "A line number in Main.java: where that frame was when the "
                    + "problem happened.")
                .xp(10))
            .recap(
                "A stack trace is the call stack at the moment of a crash. "
                + "First line: what went wrong. Top 'at' line: where. Lines "
                + "below: the calls that led there, down to main.\n"
                + "\n"
                + "Read top down to the first line of your own code. Log "
                + "traces; never show them to users.")
            .next("Next: a method that calls itself."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(19), "A Method That Calls Itself", 5)
            .brief(
                "If a method can call any method, it can call ITSELF. That "
                + "sounds like a way to get stuck forever - and without care, "
                + "it is. With a stopping rule, it is a surprisingly neat way "
                + "to solve some problems.")
            .willLearn("Recursion", "StackOverflowError")
            .whyUseful(
                "Recursion is the natural way to walk nested structures - "
                + "folders inside folders, JSON inside JSON - and its failure "
                + "mode, running out of stack, is a real denial-of-service "
                + "risk you should recognise.")
            .concept("Recursion",
                "A method that calls itself is RECURSIVE. Every call pushes a "
                + "NEW frame with its own parameters, so each level has its "
                + "own n:\n"
                + "\n"
                + "    static void countdown(int n) {\n"
                + "        if (n == 0) {\n"
                + "            System.out.println(\"Lockout lifted\");\n"
                + "            return;\n"
                + "        }\n"
                + "        System.out.println(n + \"...\");\n"
                + "        countdown(n - 1);\n"
                + "    }\n"
                + "\n"
                + "Two parts, and it needs both:\n"
                + "\n"
                + "    BASE CASE        a situation answered directly,\n"
                + "                     with no further call: n == 0\n"
                + "    RECURSIVE CASE   a call on a SMALLER problem,\n"
                + "                     moving towards the base: n - 1\n"
                + "\n"
                + "countdown(3) pushes frames for 3, 2, 1 and 0. The 0 frame "
                + "hits the base case and returns, then the others pop one by "
                + "one.\n"
                + "\n"
                + "NO BASE CASE - or a step that never reaches it - means "
                + "frames are pushed until the stack runs out of room. Java "
                + "then stops with a StackOverflowError, and the trace is "
                + "hundreds of identical lines.\n"
                + "\n"
                + "Most repeating is better done with loops, in Campaign 04. "
                + "Recursion shines when a problem contains smaller copies of "
                + "itself.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        countdown(3);",
                "    }",
                "",
                "    static void countdown(int n) {",
                "        if (n == 0) {",
                "            System.out.println(\"Lockout lifted\");",
                "            return;",
                "        }",
                "        System.out.println(n + \"...\");",
                "        countdown(n - 1);",
                "    }",
                "}")
            .exampleOutput(
                "3...",
                "2...",
                "1...",
                "Lockout lifted")
            .lineByLine(
                new String[]{"if (n == 0)",
                    "The base case. It answers without calling again."},
                new String[]{"countdown(n - 1);",
                    "The recursive case: the same job, one smaller."},
                new String[]{"four frames",
                    "countdown(3), (2), (1) and (0) are all on the stack at "
                    + "once, before (0) returns."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int total(int n) {",
                    "    if (n == 0) {",
                    "        return 0;",
                    "    }",
                    "    return n + total(n - 1);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(total(4));",
                    "}")
                .accept("10")
                .hints("total(4) is 4 + total(3).",
                       "4 + 3 + 2 + 1 + 0.")
                .explain(
                    "10. Each frame waits for the one above it: total(0) "
                    + "returns 0, total(1) returns 1, then 3, 6, and finally "
                    + "total(4) returns 10.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void show(int n) {",
                    "    if (n == 0) {",
                    "        return;",
                    "    }",
                    "    show(n - 1);",
                    "    System.out.println(n);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    show(3);",
                    "}")
                .accept("1 2 3")
                .hints("The println comes AFTER the recursive call.",
                       "Which frame reaches its println first?")
                .explain(
                    "    1\n"
                    + "    2\n"
                    + "    3\n"
                    + "\n"
                    + "Each frame calls deeper before printing. The deepest "
                    + "non-zero frame, show(1), prints first as the stack "
                    + "unwinds.")
                .xp(20))
            .objective(
                "Give the key-space calculator its base case.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"4-digit PINs: \" + power(10, 4));",
                "        System.out.println(\"8-bit values: \" + power(2, 8));",
                "    }",
                "",
                "    static int power(int base, int exp) {",
                "        if (exp == 0) {",
                "            // base case: anything to the power 0 is 1",
                "        }",
                "        return base * power(base, exp - 1);",
                "    }",
                "}")
            .yourTask(
                "Write the base case's line. Anything to the power 0 is 1, "
                + "and without it the recursion never stops.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line inside the base case.")
                .accept("return 1;")
                .hints(
                    "The base case answers directly, with no call.",
                    "power returns an int.",
                    "return 1;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"4-digit PINs: \" + power(10, 4));",
                    "        System.out.println(\"8-bit values: \" + power(2, 8));",
                    "    }",
                    "",
                    "    static int power(int base, int exp) {",
                    "        if (exp == 0) {",
                    "            return 1;",
                    "        }",
                    "        return base * power(base, exp - 1);",
                    "    }",
                    "}")
                .whyItWorks(
                    "power(10, 4) is 10 * power(10, 3), and so on down to "
                    + "power(10, 0), which now returns 1 instead of calling "
                    + "again. The frames multiply back up: 10, 100, 1000, "
                    + "10000. The program prints 4-digit PINs: 10000 and 8-bit "
                    + "values: 256.\n"
                    + "\n"
                    + "Without return 1; the starter does not even compile - "
                    + "the if would fall through to another call, and the "
                    + "base case would never actually stop anything.")
                .explain(
                    "return 1; stops the recursion at exponent 0.")
                .xp(20))
            .mistakes(
                new String[]{"No base case",
                    "Frames pile up until StackOverflowError."},
                new String[]{"A step that misses the base",
                    "countdown(n - 2) from 3 goes 3, 1, -1... and never hits "
                    + "0."},
                new String[]{"Recursion where a loop fits",
                    "Simple repetition is clearer as a loop (Campaign 04)."})
            .cyber(
                "The key-space numbers in the task are why PINs are weak: "
                + "10,000 possibilities can be tried by a script in seconds. "
                + "Every extra character multiplies the work - which is the "
                + "whole argument for long passwords.\n"
                + "\n"
                + "StackOverflowError is a security issue too. A parser that "
                + "handles nested data recursively - brackets inside brackets "
                + "inside brackets - can be crashed by an attacker who simply "
                + "sends data nested a hundred thousand levels deep. Real "
                + "JSON and XML parsers have had exactly this "
                + "denial-of-service bug, and fix it by limiting the depth.")
            .check(new Task(Task.CHOICE,
                    "What must every recursive method have, so that it "
                    + "stops?")
                .choices("A return type", "A base case",
                         "Two parameters", "A loop")
                .accept("2", "b")
                .hints("Something must answer without calling again.",
                       "In countdown it is n == 0.")
                .explain(
                    "A base case - a situation answered directly - and a "
                    + "recursive step that always moves towards it.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "A recursive method has no base case. What happens when "
                    + "it runs?")
                .choices("It returns 0",
                         "The compiler refuses it",
                         "Frames pile up until StackOverflowError",
                         "Java stops it after 100 calls")
                .accept("3", "c")
                .hints("It compiles fine.",
                       "Each call pushes a frame.")
                .explain(
                    "Frames pile up until the stack has no room left, and "
                    + "Java stops with StackOverflowError. The compiler cannot "
                    + "see the problem.")
                .xp(10))
            .recap(
                "A recursive method calls itself. It needs a base case that "
                + "answers directly, and a recursive case that moves towards "
                + "it. Each call has its own frame.\n"
                + "\n"
                + "No way to stop means StackOverflowError - and deeply nested "
                + "input can cause it on purpose.")
            .next("Next: values every method in the class can share."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(20), "Rules for the Whole Class", 4)
            .brief(
                "The lockout limit of 5 appears in four methods. Security "
                + "decides it should be 3. Someone changes three of the four "
                + "- and one path still allows five guesses. A rule that "
                + "several methods share needs one home, at the class "
                + "level.")
            .willLearn("Class constants")
            .whyUseful(
                "Limits, thresholds, allowed ranges and file names belong in "
                + "named constants. One declaration, one place to change, and "
                + "every method stays in step.")
            .concept("Class constants",
                "Campaign 01 used final for a local constant. A constant "
                + "that several methods need is declared in the CLASS, "
                + "outside every method:\n"
                + "\n"
                + "    public class Main {\n"
                + "        static final int MAX_FAILURES = 5;\n"
                + "\n"
                + "        static boolean shouldLock(int failures) {\n"
                + "            return failures >= MAX_FAILURES;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    static    belongs to the class, like the methods\n"
                + "    final     can never be given a new value\n"
                + "\n"
                + "Every method in the class can use it. Assigning to it "
                + "anywhere is a compile error: 'cannot assign a value to "
                + "final variable'.\n"
                + "\n"
                + "By convention, constant names are UPPER_SNAKE_CASE - "
                + "capitals, with underscores between words. That makes them "
                + "stand out as fixed rules, not working variables.\n"
                + "\n"
                + "Constants replace MAGIC NUMBERS - bare values like 5 or "
                + "1024 whose meaning a reader has to guess. "
                + "failures >= MAX_FAILURES explains itself; failures >= 5 "
                + "does not. And when the rule changes, it changes in one "
                + "line.")
            .example(
                "public class Main {",
                "    static final int MAX_FAILURES = 5;",
                "    static final String BLOCKED_RANGE = \"203.0.113.\";",
                "",
                "    public static void main(String[] args) {",
                "        System.out.println(shouldLock(4));",
                "        System.out.println(shouldLock(MAX_FAILURES));",
                "        System.out.println(isBlocked(\"203.0.113.7\"));",
                "    }",
                "",
                "    static boolean shouldLock(int failures) {",
                "        return failures >= MAX_FAILURES;",
                "    }",
                "",
                "    static boolean isBlocked(String ip) {",
                "        return ip.startsWith(BLOCKED_RANGE);",
                "    }",
                "}")
            .exampleOutput(
                "false",
                "true",
                "true")
            .lineByLine(
                new String[]{"static final int MAX_FAILURES = 5;",
                    "Declared in the class, outside every method."},
                new String[]{"shouldLock(MAX_FAILURES)",
                    "main can use it..."},
                new String[]{"failures >= MAX_FAILURES",
                    "...and so can every other method."},
                new String[]{"BLOCKED_RANGE",
                    "Constants can be any type, including String."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static final int LIMIT = 3;",
                    "",
                    "static String status(int n) {",
                    "    return n > LIMIT ? \"HIGH\" : \"OK\";",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(status(3) + \" \" + status(4));",
                    "}")
                .accept("OK HIGH")
                .hints("Is 3 > 3?", "Is 4 > 3?")
                .explain(
                    "OK HIGH. 3 is not greater than LIMIT; 4 is.")
                .xp(10))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "static final int MAX_FAILURES = 5;",
                    "",
                    "public static void main(String[] args) {",
                    "    MAX_FAILURES = 10;",
                    "    System.out.println(MAX_FAILURES);",
                    "}")
                .accept("4", "line 4")
                .hints("What does final promise?",
                       "Which line tries to change it?")
                .explain(
                    "Line 4: 'cannot assign a value to final variable "
                    + "MAX_FAILURES'. A constant is set once, where it is "
                    + "declared.")
                .xp(15))
            .objective(
                "Give the session timeout one home.")
            .starter(
                "public class Main {",
                "    // declare SESSION_MINUTES: a class constant int, 30",
                "",
                "    public static void main(String[] args) {",
                "        System.out.println(isExpired(45));",
                "        System.out.println(\"Timeout: \" + SESSION_MINUTES);",
                "    }",
                "",
                "    static boolean isExpired(int idleMinutes) {",
                "        return idleMinutes >= SESSION_MINUTES;",
                "    }",
                "}")
            .yourTask(
                "Declare SESSION_MINUTES as a class constant: static, final, "
                + "an int, set to 30.")
            .mainTask(new Task(Task.WRITE,
                    "Write the constant's declaration.")
                .accept("static final int SESSION_MINUTES = 30;",
                        "static final int SESSION_MINUTES=30;",
                        "private static final int SESSION_MINUTES = 30;",
                        "public static final int SESSION_MINUTES = 30;",
                        "final static int SESSION_MINUTES = 30;")
                .hints(
                    "static, then final, then the type.",
                    "Then the name and the value, like a variable.",
                    "static final int SESSION_MINUTES = 30;")
                .solution(
                    "public class Main {",
                    "    static final int SESSION_MINUTES = 30;",
                    "",
                    "    public static void main(String[] args) {",
                    "        System.out.println(isExpired(45));",
                    "        System.out.println(\"Timeout: \" + SESSION_MINUTES);",
                    "    }",
                    "",
                    "    static boolean isExpired(int idleMinutes) {",
                    "        return idleMinutes >= SESSION_MINUTES;",
                    "    }",
                    "}")
                .whyItWorks(
                    "Declared in the class, SESSION_MINUTES is visible to "
                    + "both main and isExpired. The program prints true "
                    + "(45 >= 30) and Timeout: 30.\n"
                    + "\n"
                    + "If policy changes to 15 minutes, one line changes, and "
                    + "the check and the message can never disagree.")
                .explain(
                    "static final int SESSION_MINUTES = 30;")
                .xp(20))
            .mistakes(
                new String[]{"Declaring it inside a method",
                    "Then only that method can see it."},
                new String[]{"Magic numbers",
                    "A bare 5 says nothing. MAX_FAILURES says everything."},
                new String[]{"Trying to change it",
                    "final means set once. javac refuses a second "
                    + "assignment."})
            .cyber(
                "The brief's bug - one path still allowing five guesses - is "
                + "how inconsistent security policy happens. Real audits find "
                + "a password-length check of 12 in the sign-up page and 8 in "
                + "the reset page, or a lockout that applies to the website "
                + "but not the API.\n"
                + "\n"
                + "Security-relevant limits belong in constants, ideally in "
                + "one place for the whole application. Then the question "
                + "'what is our lockout threshold?' has exactly one answer.")
            .check(new Task(Task.CHOICE,
                    "Where is a class constant declared?")
                .choices("Inside main",
                         "In the class, outside any method",
                         "Inside every method that uses it",
                         "In a comment")
                .accept("2", "b")
                .hints("Every method needs to see it.",
                       "Look at the example.")
                .explain(
                    "In the class, outside any method - which is what lets "
                    + "every method use it.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which name follows the convention for a constant?")
                .choices("maxFailures", "MaxFailures", "MAX_FAILURES",
                         "max_failures")
                .accept("3", "c")
                .hints("Capitals, with underscores.",
                       "It should stand out from variables.")
                .explain(
                    "MAX_FAILURES - UPPER_SNAKE_CASE marks a fixed rule.")
                .xp(10))
            .recap(
                "    static final int MAX_FAILURES = 5;\n"
                + "\n"
                + "Declared in the class, used by every method, never "
                + "reassigned. UPPER_SNAKE_CASE. Constants replace magic "
                + "numbers and keep every method on the same rule.")
            .next("Next: a class-level variable that CAN change."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(21), "A Variable the Class Remembers", 5)
            .brief(
                "Every alert the SOC tool raises should get the next number: "
                + "#1, #2, #3. A local counter starts again at 0 on every "
                + "call. The count has to live somewhere that outlasts a "
                + "single call - and that power comes with a warning.")
            .willLearn("Static fields")
            .whyUseful(
                "Some state really is shared: a running total, a count of "
                + "alerts, a flag that says the system is in lockdown. Static "
                + "fields hold it - and knowing their risks keeps you from "
                + "using them for everything.")
            .concept("Static fields",
                "A variable declared in the class, with static but WITHOUT "
                + "final, is a STATIC FIELD:\n"
                + "\n"
                + "    public class Main {\n"
                + "        static int alertsRaised = 0;\n"
                + "        ...\n"
                + "    }\n"
                + "\n"
                + "It is not in any frame. It is created once when the program "
                + "starts and lasts until it ends, so it REMEMBERS between "
                + "calls. Every method in the class can read it and change "
                + "it.\n"
                + "\n"
                + "    local variable     one call, one method\n"
                + "    static field       whole program, every method\n"
                + "    static final       whole program, never changes\n"
                + "\n"
                + "SHADOWING. If a method declares a local with the same name "
                + "as a field, the local HIDES the field inside that method. "
                + "int count = 0; inside a method makes a new local count, "
                + "and the field is left untouched - a quiet, confusing bug.\n"
                + "\n"
                + "THE WARNING. Because any method can change a static field, "
                + "a wrong value could have come from anywhere. Methods that "
                + "take parameters and return results can be understood on "
                + "their own; methods that read and write shared fields "
                + "cannot. Use static fields for state that genuinely belongs "
                + "to the whole program - and pass everything else.")
            .example(
                "public class Main {",
                "    static int alertsRaised = 0;",
                "",
                "    public static void main(String[] args) {",
                "        raise(\"port scan\");",
                "        raise(\"brute force\");",
                "        raise(\"malware\");",
                "        System.out.println(\"Total raised: \" + alertsRaised);",
                "    }",
                "",
                "    static void raise(String name) {",
                "        alertsRaised++;",
                "        System.out.println(\"#\" + alertsRaised + \" \" + name);",
                "    }",
                "}")
            .exampleOutput(
                "#1 port scan",
                "#2 brute force",
                "#3 malware",
                "Total raised: 3")
            .lineByLine(
                new String[]{"static int alertsRaised = 0;",
                    "Created once, when the program starts."},
                new String[]{"alertsRaised++;",
                    "Each call changes the same variable, so the count keeps "
                    + "growing."},
                new String[]{"Total raised: 3",
                    "main sees the value every call left behind."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int count = 10;",
                    "",
                    "static void bump() {",
                    "    int count = 0;",
                    "    count++;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    bump();",
                    "    System.out.println(count);",
                    "}")
                .accept("10")
                .hints("int count = 0; inside bump declares something new.",
                       "Which count does count++ change?")
                .explain(
                    "10. The local count in bump SHADOWS the field. bump "
                    + "changes its own local, and the field stays 10. Deleting "
                    + "the int in bump would make it change the field.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int visits = 0;",
                    "",
                    "static void visit() {",
                    "    visits++;",
                    "    System.out.println(\"visit \" + visits);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    visit();",
                    "    visit();",
                    "}")
                .accept("visit 1 visit 2")
                .hints("Mission 12's version printed visit 1 every time.",
                       "Where does visits live now?")
                .explain(
                    "    visit 1\n"
                    + "    visit 2\n"
                    + "\n"
                    + "visits is a static field, not a local, so it survives "
                    + "between calls.")
                .xp(10))
            .objective(
                "Give the failed-login counter a home that lasts.")
            .starter(
                "public class Main {",
                "    // declare failedLogins: a static int field, starting at 0",
                "",
                "    public static void main(String[] args) {",
                "        recordFailure();",
                "        recordFailure();",
                "        System.out.println(\"Failed logins: \" + failedLogins);",
                "    }",
                "",
                "    static void recordFailure() {",
                "        failedLogins++;",
                "    }",
                "}")
            .yourTask(
                "Declare failedLogins as a static int field, starting at 0 - "
                + "not final, because it has to change.")
            .mainTask(new Task(Task.WRITE,
                    "Write the field's declaration.")
                .accept("static int failedLogins = 0;",
                        "static int failedLogins=0;",
                        "private static int failedLogins = 0;",
                        "static int failedLogins;")
                .hints(
                    "Like a constant, but without final.",
                    "static, the type, the name, the starting value.",
                    "static int failedLogins = 0;")
                .solution(
                    "public class Main {",
                    "    static int failedLogins = 0;",
                    "",
                    "    public static void main(String[] args) {",
                    "        recordFailure();",
                    "        recordFailure();",
                    "        System.out.println(\"Failed logins: \" + failedLogins);",
                    "    }",
                    "",
                    "    static void recordFailure() {",
                    "        failedLogins++;",
                    "    }",
                    "}")
                .whyItWorks(
                    "The field lives for the whole program, so both calls to "
                    + "recordFailure add to the same variable, and main prints "
                    + "Failed logins: 2.\n"
                    + "\n"
                    + "Leaving out = 0 also works for a field: fields start at "
                    + "0 (or false, or null) automatically - unlike locals, "
                    + "which must be given a value before use. Writing = 0 "
                    + "says it on purpose.")
                .explain(
                    "static int failedLogins = 0; - class-level, and not "
                    + "final.")
                .xp(20))
            .mistakes(
                new String[]{"Shadowing a field",
                    "int count inside a method makes a new local and hides "
                    + "the field."},
                new String[]{"Fields for everything",
                    "Pass values and return results. Keep fields for truly "
                    + "shared state."},
                new String[]{"Adding final to a counter",
                    "A final field can never change - the ++ will not "
                    + "compile."})
            .cyber(
                "Shared, changeable state is behind some of the hardest "
                + "security bugs to find. When one field says whether the "
                + "system is in lockdown, every method that can write it is "
                + "a way to lift the lockdown - including ones nobody meant "
                + "to.\n"
                + "\n"
                + "In programs that do several things at once - web servers "
                + "handling many users - shared fields can also be read and "
                + "changed by two requests at the same moment. One user's "
                + "session data leaking into another user's response has "
                + "happened exactly this way.")
            .check(new Task(Task.CHOICE,
                    "What is the difference between static int LIMIT = 5 and "
                    + "static final int LIMIT = 5?")
                .choices("None",
                         "Only the final one can be changed",
                         "Only the non-final one can be changed",
                         "The final one is local")
                .accept("3", "c")
                .hints("What does final forbid?",
                       "Both live in the class.")
                .explain(
                    "Only the non-final one can be changed. final makes it a "
                    + "constant; without it, it is a field any method can "
                    + "change.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Why prefer parameters and return values to static "
                    + "fields?")
                .choices("Fields are slower",
                         "Any method can change a field, so bugs are hard to "
                         + "trace",
                         "Fields cannot hold Strings",
                         "Fields disappear after each call")
                .accept("2", "b")
                .hints("Who can change a field?",
                       "Where would a wrong value have come from?")
                .explain(
                    "Any method can change a field. A method that only uses "
                    + "its parameters can be understood - and trusted - on "
                    + "its own.")
                .xp(10))
            .recap(
                "    static int alertsRaised = 0;\n"
                + "\n"
                + "A static field lives for the whole program and every method "
                + "can change it, so it remembers between calls. A local with "
                + "the same name hides it.\n"
                + "\n"
                + "Use fields for genuinely shared state; pass everything "
                + "else.")
            .next("Next: turning a big job into a set of small methods."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(22), "Breaking a Problem Down", 5)
            .brief(
                "The new task: read a login record like "
                + "jsmith,203.0.113.9,FAIL, pull out its parts, decide "
                + "whether it needs review, and report. Written as one long "
                + "main, it would be forty tangled lines. Written as small "
                + "methods, main reads like the task description.")
            .willLearn("Decomposition")
            .whyUseful(
                "Decomposition is how every program bigger than a page is "
                + "written. It also makes code testable: each small method "
                + "can be checked on its own before the whole is put "
                + "together.")
            .concept("Decomposition",
                "DECOMPOSITION means splitting a job into steps, and giving "
                + "each step its own method. Start from the task, in "
                + "words:\n"
                + "\n"
                + "    1. take a field out of the record\n"
                + "    2. decide whether an address is external\n"
                + "    3. print a report line, and a review note if\n"
                + "       an external login failed\n"
                + "\n"
                + "Each step becomes a method with an honest name - field, "
                + "isExternal, report - and main just calls them in order. "
                + "Reading main tells you WHAT happens; opening a method tells "
                + "you HOW.\n"
                + "\n"
                + "Signs a method should be split:\n"
                + "\n"
                + "    - its name needs AND: parseAndCheckAndPrint\n"
                + "    - it is longer than a screen\n"
                + "    - part of it could be reused somewhere else\n"
                + "    - you want to test part of it on its own\n"
                + "\n"
                + "Good decomposition gives each method ONE job, with its "
                + "inputs as parameters and its answer as a return value. "
                + "Methods like that are the building blocks every later "
                + "campaign stacks on top of.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String entry = \"jsmith,203.0.113.9,FAIL\";",
                "        String user = field(entry, 0);",
                "        String ip = field(entry, 1);",
                "        boolean failed = field(entry, 2).equals(\"FAIL\");",
                "        report(user, ip, failed);",
                "    }",
                "",
                "    static String field(String line, int which) {",
                "        int first = line.indexOf(\",\");",
                "        int last = line.lastIndexOf(\",\");",
                "        if (which == 0) {",
                "            return line.substring(0, first);",
                "        }",
                "        if (which == 1) {",
                "            return line.substring(first + 1, last);",
                "        }",
                "        return line.substring(last + 1);",
                "    }",
                "",
                "    static boolean isExternal(String ip) {",
                "        return !ip.startsWith(\"10.\");",
                "    }",
                "",
                "    static void report(String user, String ip, boolean failed) {",
                "        String outcome = failed ? \"FAILED\" : \"OK\";",
                "        System.out.println(user + \" from \" + ip + \": \" + outcome);",
                "        if (failed && isExternal(ip)) {",
                "            System.out.println(\"  review: external failure\");",
                "        }",
                "    }",
                "}")
            .exampleOutput(
                "jsmith from 203.0.113.9: FAILED",
                "  review: external failure")
            .lineByLine(
                new String[]{"main",
                    "Five lines that read like the task: get the parts, then "
                    + "report."},
                new String[]{"field(entry, 1)",
                    "One method takes out any field, with guards choosing "
                    + "which."},
                new String[]{"isExternal(ip)",
                    "One rule, one name, reusable anywhere."},
                new String[]{"report(user, ip, failed)",
                    "All the output decisions in one place."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static String first(String s) {",
                    "    return s.substring(0, s.indexOf(\",\"));",
                    "}",
                    "",
                    "static String rest(String s) {",
                    "    return s.substring(s.indexOf(\",\") + 1);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    String line = \"a,b,c\";",
                    "    System.out.println(first(rest(line)));",
                    "}")
                .accept("b")
                .hints("rest runs first: what is left after the first "
                       + "comma?",
                       "Then first takes the part before the next comma.")
                .explain(
                    "b. rest(\"a,b,c\") is \"b,c\", and first(\"b,c\") is "
                    + "\"b\". Two tiny methods combine into a new operation: "
                    + "the second field.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "A method called processLogin reads input, checks the "
                    + "password, updates the counter, writes the audit log "
                    + "and prints a menu. What is the main problem?")
                .choices("Its name is too short",
                         "It does too many jobs",
                         "It should return a boolean",
                         "Nothing - one method is simplest")
                .accept("2", "b")
                .hints("Count the jobs.",
                       "Could any part be tested or reused on its own?")
                .explain(
                    "It does five jobs. Split it - readLogin, "
                    + "isPasswordCorrect, recordFailure, writeAudit, "
                    + "printMenu - and each can be read, tested and reused "
                    + "on its own.")
                .xp(10))
            .objective(
                "Put the steps together in main.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String host = \" WEB-01 \";",
                "        int failures = 7;",
                "        // one line: print verdict for the CLEANED host",
                "    }",
                "",
                "    static String clean(String host) {",
                "        return host.trim().toLowerCase();",
                "    }",
                "",
                "    static String verdict(String host, int failures) {",
                "        return host + \": \" + (failures >= 5 ? \"LOCK\" : \"OK\");",
                "    }",
                "}")
            .yourTask(
                "Write one line that prints the verdict for the cleaned host "
                + "and failures: clean goes inside verdict, verdict inside "
                + "the println.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line.")
                .accept("System.out.println(verdict(clean(host), failures));")
                .hints(
                    "Start from the inside: clean(host).",
                    "That result is verdict's first argument.",
                    "System.out.println(verdict(clean(host), failures));")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String host = \" WEB-01 \";",
                    "        int failures = 7;",
                    "        System.out.println(verdict(clean(host), failures));",
                    "    }",
                    "",
                    "    static String clean(String host) {",
                    "        return host.trim().toLowerCase();",
                    "    }",
                    "",
                    "    static String verdict(String host, int failures) {",
                    "        return host + \": \" + (failures >= 5 ? \"LOCK\" : \"OK\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "Java works from the inside out: clean(host) returns "
                    + "\"web-01\", verdict(\"web-01\", 7) returns \"web-01: "
                    + "LOCK\", and println prints it.\n"
                    + "\n"
                    + "Each method does one job and knows nothing about the "
                    + "others. main is where they are combined - which is "
                    + "exactly what decomposition aims for.")
                .explain(
                    "Nest the steps: println(verdict(clean(host), failures)).")
                .xp(25))
            .mistakes(
                new String[]{"One giant main",
                    "Split it into steps with names."},
                new String[]{"Methods that need 'and' in their name",
                    "That is two jobs. Make two methods."},
                new String[]{"Helpers that print instead of return",
                    "A returning helper can be combined; a printing one "
                    + "cannot."})
            .cyber(
                "Decomposed code is reviewable code. A security auditor can "
                + "check isExternal once and trust it everywhere; in a "
                + "forty-line main, the same rule might be written three "
                + "slightly different ways.\n"
                + "\n"
                + "It also shrinks the ATTACK SURFACE of each change. When the "
                + "log format changes, only field changes - the decision "
                + "rules are not touched, so they cannot be broken by "
                + "accident.")
            .check(new Task(Task.CHOICE,
                    "Which is a sign that a method should be split?")
                .choices("It has a parameter",
                         "Its honest name would need the word and",
                         "It returns a value",
                         "It is called from main")
                .accept("2", "b")
                .hints("One method, one job.",
                       "checkAndLog...")
                .explain(
                    "A name that needs 'and' describes two jobs. Two methods "
                    + "will each be simpler.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int tens(int n) {",
                    "    return n / 10;",
                    "}",
                    "",
                    "static int units(int n) {",
                    "    return n % 10;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    int port = 47;",
                    "    System.out.println(tens(port) + units(port));",
                    "}")
                .accept("11")
                .hints("tens(47) is 4, units(47) is 7.",
                       "Both are ints, so + adds.")
                .explain(
                    "11. tens gives 4 and units gives 7; two ints are added, "
                    + "not joined.")
                .xp(15))
            .recap(
                "Decomposition: list the steps, give each step a method with "
                + "one job, and let main combine them.\n"
                + "\n"
                + "Split any method whose name needs 'and', that runs past a "
                + "screen, or that holds a reusable part.")
            .next("Next: proving a method works before trusting it."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(23), "Testing a Method", 5)
            .brief(
                "isValidPort looks right. It compiles. It even works for 22 "
                + "and 8080. But nobody tried port 1 - and port 1 is where "
                + "it is wrong. A few lines in main can prove a method "
                + "correct before anything depends on it.")
            .willLearn("Testing a method")
            .whyUseful(
                "Tests turn 'I think it works' into 'I checked'. They catch "
                + "boundary mistakes before attackers do, and they tell you "
                + "immediately when a later change breaks something.")
            .concept("Testing a method",
                "A TEST calls a method with a known input and compares the "
                + "result to what it SHOULD be:\n"
                + "\n"
                + "    check(\"1 is valid\", isValidPort(1));\n"
                + "    check(\"0 is invalid\", !isValidPort(0));\n"
                + "\n"
                + "A tiny helper prints the verdict:\n"
                + "\n"
                + "    static void check(String name, boolean passed) {\n"
                + "        System.out.println(\n"
                + "            (passed ? \"PASS \" : \"FAIL \") + name);\n"
                + "    }\n"
                + "\n"
                + "WHAT TO TEST - Campaign 02's boundary habit, applied:\n"
                + "\n"
                + "    normal      a typical value: 443\n"
                + "    boundaries  each edge, and one past it:\n"
                + "                0, 1, 65535, 65536\n"
                + "    bad input   values that must be REJECTED\n"
                + "\n"
                + "Testing only values that should pass is not enough: a "
                + "method that says yes to EVERYTHING would pass them all.\n"
                + "\n"
                + "A FAIL has two possible causes: the method is wrong, or "
                + "the expected value in the test is wrong. Check both before "
                + "changing anything.\n"
                + "\n"
                + "Professional Java uses a framework called JUnit for this. "
                + "The idea is exactly the same: known input, expected "
                + "output, compare.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        check(\"1 is valid\", isValidPort(1));",
                "        check(\"0 is invalid\", !isValidPort(0));",
                "        check(\"65535 is valid\", isValidPort(65535));",
                "        check(\"65536 is invalid\", !isValidPort(65536));",
                "    }",
                "",
                "    static boolean isValidPort(int port) {",
                "        return port > 1 && port <= 65535;",
                "    }",
                "",
                "    static void check(String name, boolean passed) {",
                "        System.out.println((passed ? \"PASS \" : \"FAIL \") + name);",
                "    }",
                "}")
            .exampleOutput(
                "FAIL 1 is valid",
                "PASS 0 is invalid",
                "PASS 65535 is valid",
                "PASS 65536 is invalid")
            .lineByLine(
                new String[]{"check(\"0 is invalid\", !isValidPort(0));",
                    "A rejection test: it passes when the method says NO."},
                new String[]{"port > 1",
                    "The bug: > where >= was needed. Port 1 is rejected."},
                new String[]{"FAIL 1 is valid",
                    "The boundary test caught it. 22 and 8080 never would "
                    + "have."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static int doubled(int n) {",
                    "    return n + n;",
                    "}",
                    "",
                    "static void check(String name, boolean passed) {",
                    "    System.out.println((passed ? \"PASS \" : \"FAIL \") + name);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    check(\"doubled(3)\", doubled(3) == 6);",
                    "    check(\"doubled(0)\", doubled(0) == 1);",
                    "}")
                .accept("PASS doubled(3) FAIL doubled(0)")
                .hints("doubled(0) is 0.",
                       "Is 0 == 1?")
                .explain(
                    "    PASS doubled(3)\n"
                    + "    FAIL doubled(0)\n"
                    + "\n"
                    + "The method is right. The TEST is wrong: 0 doubled is 0, "
                    + "not 1. A failing test can point at the expectation.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Which inputs best test  static boolean isAdult(int age) "
                    + " (true from 18)?")
                .choices("20, 30, 40", "17, 18, 19", "0, 100", "18")
                .accept("2", "b")
                .hints("Where can the rule go wrong?",
                       "Test around the boundary.")
                .explain(
                    "17, 18, 19 - just below, on, and just above the "
                    + "boundary. A mistaken > instead of >= fails at 18; "
                    + "20, 30 and 40 would never notice.")
                .xp(10))
            .objective(
                "Fix the bug the test found.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        check(\"1 is valid\", isValidPort(1));",
                "        check(\"0 is invalid\", !isValidPort(0));",
                "    }",
                "",
                "    static boolean isValidPort(int port) {",
                "        return port > 1 && port <= 65535;    // fix this line",
                "    }",
                "",
                "    static void check(String name, boolean passed) {",
                "        System.out.println((passed ? \"PASS \" : \"FAIL \") + name);",
                "    }",
                "}")
            .yourTask(
                "The test says FAIL 1 is valid. Rewrite the marked return "
                + "line so port 1 is valid and port 0 still is not.")
            .mainTask(new Task(Task.WRITE,
                    "Rewrite the return line.")
                .accept("return port >= 1 && port <= 65535;",
                        "return port > 0 && port <= 65535;",
                        "return port>=1 && port<=65535;")
                .hints(
                    "Which operator includes the boundary itself?",
                    "Change > to >=.",
                    "return port >= 1 && port <= 65535;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        check(\"1 is valid\", isValidPort(1));",
                    "        check(\"0 is invalid\", !isValidPort(0));",
                    "    }",
                    "",
                    "    static boolean isValidPort(int port) {",
                    "        return port >= 1 && port <= 65535;",
                    "    }",
                    "",
                    "    static void check(String name, boolean passed) {",
                    "        System.out.println((passed ? \"PASS \" : \"FAIL \") + name);",
                    "    }",
                    "}")
                .whyItWorks(
                    ">= 1 includes 1 itself, so both tests now print PASS. The "
                    + "second test matters as much as the first: a careless "
                    + "fix like port >= 0 would pass '1 is valid' but fail "
                    + "'0 is invalid'.\n"
                    + "\n"
                    + "Keep tests after the fix. The next time anyone touches "
                    + "isValidPort, running them shows at once whether "
                    + "anything broke.")
                .explain(
                    "port >= 1 includes the lower boundary.")
                .xp(20))
            .mistakes(
                new String[]{"Only testing values that should pass",
                    "A method that accepts everything passes those too."},
                new String[]{"Skipping the boundaries",
                    "That is where > and >= differ."},
                new String[]{"Assuming the method is wrong",
                    "Sometimes the expected value is."})
            .cyber(
                "Security testing is largely negative testing: proving that "
                + "things which should be REFUSED are refused. Penetration "
                + "testers spend their time on exactly the inputs developers "
                + "forget - 0, -1, the empty string, one past the limit.\n"
                + "\n"
                + "Keeping tests also guards against REGRESSIONS: a later "
                + "change that quietly reopens a fixed vulnerability. Many "
                + "real vulnerabilities have been reintroduced this way, "
                + "years after being fixed, because no test pinned the fix "
                + "in place.")
            .check(new Task(Task.CHOICE,
                    "A test prints FAIL. What are the two possible causes?")
                .choices("The computer, or the compiler",
                         "The method is wrong, or the expected value is wrong",
                         "The test ran too early, or too late",
                         "There is only one: the method")
                .accept("2", "b")
                .hints("A test compares two things.",
                       "Either side could be the mistake.")
                .explain(
                    "The method, or the expectation. Check which before "
                    + "changing code.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Why test values that should be REJECTED?")
                .choices("They run faster",
                         "A method that accepts everything passes every "
                         + "'valid' test",
                         "Java requires it",
                         "They cannot fail")
                .accept("2", "b")
                .hints("What would a broken validator look like?",
                       "return true; for everything.")
                .explain(
                    "return true; would pass every 'should be valid' test. "
                    + "Only rejection tests catch a validator that lets "
                    + "everything through.")
                .xp(10))
            .recap(
                "A test: known input, expected result, compare, print PASS or "
                + "FAIL. Test normal values, every boundary, and values that "
                + "must be rejected.\n"
                + "\n"
                + "A FAIL means the method or the expectation is wrong. Keep "
                + "tests to catch regressions.")
            .next("Next: comments that document a method's contract."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(24), "Documenting a Method", 4)
            .brief(
                "maskCard works on 16-digit card numbers. Give it a 15-digit "
                + "one and it quietly shows too much. Nothing in its name or "
                + "header says so. A method's assumptions belong where every "
                + "caller will see them: in its documentation.")
            .willLearn("Documentation comments")
            .whyUseful(
                "A documentation comment is a method's contract: what it "
                + "needs, what it returns, and what it assumes. Tools turn "
                + "them into reference pages, and editors show them the "
                + "moment you type a method's name.")
            .concept("Documentation comments",
                "A comment that starts with /** (two stars) and sits directly "
                + "above a method is a DOCUMENTATION COMMENT, often called "
                + "Javadoc:\n"
                + "\n"
                + "    /**\n"
                + "     * Hides all but the last four digits.\n"
                + "     *\n"
                + "     * @param card a 16-digit card number\n"
                + "     * @return twelve * then the last four digits\n"
                + "     */\n"
                + "    static String maskCard(String card) {\n"
                + "\n"
                + "    first sentence   what the method does\n"
                + "    @param name      what one parameter must be\n"
                + "    @return          what comes back\n"
                + "\n"
                + "Java ignores it when running, like any comment. But the "
                + "javadoc tool builds web pages from these comments, and "
                + "editors pop them up when you use the method.\n"
                + "\n"
                + "Document the CONTRACT, not the code: what goes in, what "
                + "comes out, and every ASSUMPTION the caller must meet - "
                + "\"16 digits\", \"already trimmed\", \"never empty\". "
                + "Explaining how each line works is what the code and "
                + "ordinary // comments are for.\n"
                + "\n"
                + "Keep them true. Java does not check comments, so a "
                + "comment that promises one thing while the code does "
                + "another is worse than none.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(maskCard(\"4111111111111111\"));",
                "    }",
                "",
                "    /**",
                "     * Hides all but the last four digits of a card number.",
                "     *",
                "     * @param card a 16-digit card number, digits only",
                "     * @return twelve * characters, then the last four digits",
                "     */",
                "    static String maskCard(String card) {",
                "        String lastFour = card.substring(card.length() - 4);",
                "        return \"************\" + lastFour;",
                "    }",
                "}")
            .exampleOutput(
                "************1111")
            .lineByLine(
                new String[]{"/**",
                    "Two stars: a documentation comment, not an ordinary "
                    + "one."},
                new String[]{"@param card a 16-digit card number",
                    "The assumption, stated where callers look."},
                new String[]{"@return ...",
                    "Exactly what the caller will get back."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "/**",
                    " * Gives the standard HTTPS port.",
                    " * @return 443",
                    " */",
                    "static int httpsPort() {",
                    "    return 8443;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(httpsPort());",
                    "}")
                .accept("8443")
                .hints("Does Java read comments?",
                       "Only the code runs.")
                .explain(
                    "8443. The comment promises 443, but Java only runs code. "
                    + "A comment that lies is a trap for every caller who "
                    + "trusts it.")
                .xp(10))
            .practice(new Task(Task.CHOICE,
                    "Which tag describes what a method hands back?")
                .choices("@param", "@return", "@give", "@output")
                .accept("2", "b")
                .hints("It is named after the keyword.",
                       "What does a method use to hand a value back?")
                .explain(
                    "@return. @param describes one parameter; @give and "
                    + "@output are not Javadoc tags.")
                .xp(10))
            .objective(
                "Finish the lockout method's documentation.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(shouldLock(6));",
                "    }",
                "",
                "    /**",
                "     * Decides whether an account must be locked.",
                "     *",
                "     * @param failures failed logins since the last success",
                "     * (write the @return line here)",
                "     */",
                "    static boolean shouldLock(int failures) {",
                "        return failures >= 5;",
                "    }",
                "}")
            .yourTask(
                "Replace the placeholder with the @return line, worded "
                + "exactly: * @return true if the account should be locked")
            .mainTask(new Task(Task.WRITE,
                    "Write the @return line.")
                .accept("* @return true if the account should be locked",
                        "@return true if the account should be locked")
                .hints(
                    "Every line inside the comment starts with a *.",
                    "Then the tag, then the description.",
                    "* @return true if the account should be locked")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(shouldLock(6));",
                    "    }",
                    "",
                    "    /**",
                    "     * Decides whether an account must be locked.",
                    "     *",
                    "     * @param failures failed logins since the last success",
                    "     * @return true if the account should be locked",
                    "     */",
                    "    static boolean shouldLock(int failures) {",
                    "        return failures >= 5;",
                    "    }",
                    "}")
                .whyItWorks(
                    "The comment now states the whole contract: what the "
                    + "method decides, what failures means, and what true "
                    + "stands for. A caller never has to open the body to use "
                    + "it correctly. The program still prints true - comments "
                    + "change nothing at run time.\n"
                    + "\n"
                    + "Note the @param line says 'since the last success'. "
                    + "That is an assumption a caller must meet; stating it "
                    + "is the point of the comment.")
                .explain(
                    "* @return, then what true means.")
                .xp(15))
            .mistakes(
                new String[]{"One star",
                    "/* ... */ is an ordinary comment. Javadoc needs /**."},
                new String[]{"Describing the code line by line",
                    "Document the contract; the code shows the steps."},
                new String[]{"Letting comments go stale",
                    "Change the code, change the comment."})
            .cyber(
                "Unstated assumptions are where many vulnerabilities live. A "
                + "method written for trusted input gets reused, years later, "
                + "for input from the internet - and nothing warned the new "
                + "caller. Security-relevant documentation says things like "
                + "'the caller must validate this', 'never pass user input', "
                + "or 'the result is safe to show in HTML'.\n"
                + "\n"
                + "Masking, as in maskCard, is itself a standard control: the "
                + "card industry's rules allow at most the first six and last "
                + "four digits of a card number to be displayed.")
            .check(new Task(Task.CHOICE,
                    "What does /** start?")
                .choices("An ordinary comment", "A documentation comment",
                         "A block of code that is skipped", "A string")
                .accept("2", "b")
                .hints("Count the stars.",
                       "It goes above a method.")
                .explain(
                    "A documentation comment - Javadoc - describing the "
                    + "method below it.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "What belongs in a method's documentation comment?")
                .choices("How each line works",
                         "What it needs, returns and assumes",
                         "Who wrote it and when",
                         "The output of a test run")
                .accept("2", "b")
                .hints("It is a contract for callers.",
                       "What must a caller know to use it correctly?")
                .explain(
                    "The contract: what the method does, what each "
                    + "parameter must be, what it returns, and what it "
                    + "assumes.")
                .xp(10))
            .recap(
                "    /**\n"
                + "     * What it does.\n"
                + "     * @param x  what x must be\n"
                + "     * @return  what comes back\n"
                + "     */\n"
                + "\n"
                + "Document the contract and every assumption. Java ignores "
                + "comments - keep them true.")
            .next("Next: improving code without changing what it does."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(25), "Refactoring", 5)
            .brief(
                "The status page works - but it has the same lock rule typed "
                + "three times, and a 5 nobody can explain. The task is to "
                + "clean it up without changing a single character of what "
                + "it prints.")
            .willLearn("Refactoring")
            .whyUseful(
                "Most programming is changing existing code. Refactoring "
                + "safely - improving the structure while keeping the "
                + "behaviour - is how code stays readable, and how security "
                + "fixes get made in one place instead of three.")
            .concept("Refactoring",
                "REFACTORING means changing how code is ORGANISED without "
                + "changing what it DOES. Same inputs, same outputs - just "
                + "clearer code.\n"
                + "\n"
                + "The common moves are all from this campaign:\n"
                + "\n"
                + "    extract method    repeated code becomes one\n"
                + "                      method, called from each place\n"
                + "    rename            vague names become honest ones\n"
                + "    add a constant    a magic number gets a name\n"
                + "    add a guard       nested ifs become early returns\n"
                + "\n"
                + "Before:\n"
                + "\n"
                + "    System.out.println(\"jsmith: \"\n"
                + "        + (7 >= 5 ? \"LOCKED\" : \"active\"));\n"
                + "    ...the same line twice more...\n"
                + "\n"
                + "After:\n"
                + "\n"
                + "    static final int MAX_FAILURES = 5;\n"
                + "    static String status(int failures) {\n"
                + "        return failures >= MAX_FAILURES\n"
                + "            ? \"LOCKED\" : \"active\";\n"
                + "    }\n"
                + "\n"
                + "THE SAFETY RULE: know the output before you start, change "
                + "one thing at a time, and check the output is IDENTICAL "
                + "after each step. Tests from mission 23 are ideal for this. "
                + "A 'refactoring' that changes behaviour is not a "
                + "refactoring - it is a new bug.")
            .example(
                "public class Main {",
                "    static final int MAX_FAILURES = 5;",
                "",
                "    public static void main(String[] args) {",
                "        System.out.println(\"jsmith: \" + status(7));",
                "        System.out.println(\"m.reyes: \" + status(2));",
                "        System.out.println(\"svc-backup: \" + status(5));",
                "    }",
                "",
                "    static String status(int failures) {",
                "        return failures >= MAX_FAILURES ? \"LOCKED\" : \"active\";",
                "    }",
                "}")
            .exampleOutput(
                "jsmith: LOCKED",
                "m.reyes: active",
                "svc-backup: LOCKED")
            .lineByLine(
                new String[]{"static final int MAX_FAILURES = 5;",
                    "The magic 5, named."},
                new String[]{"status(7)",
                    "Three copies of the rule became three calls."},
                new String[]{"return failures >= MAX_FAILURES ...",
                    "The rule, once. The output is identical to before."})
            .predict(new Task(Task.PREDICT,
                    "This is a 'refactored' version of a rule that locked at "
                    + "5 or more. What does it print?")
                .code(
                    "static String status(int failures) {",
                    "    return failures > 5 ? \"LOCKED\" : \"active\";",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(status(5));",
                    "}")
                .accept("active")
                .hints("Is 5 > 5?",
                       "The original used >=.")
                .explain(
                    "active. The refactoring changed >= to > and silently "
                    + "changed behaviour: 5 failures no longer lock. Checking "
                    + "the output before and after - at the boundary - would "
                    + "catch it.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "What must stay the same during a refactoring?")
                .choices("The number of lines",
                         "The method names",
                         "What the program does",
                         "Nothing - that is the point")
                .accept("3", "c")
                .hints("Structure changes; what stays?",
                       "Same inputs, same...")
                .explain(
                    "What the program does. Names, lines and structure can "
                    + "all change; behaviour must not.")
                .xp(10))
            .objective(
                "Pull the repeated rule into one method.")
            .starter(
                "public class Main {",
                "    static final int MAX_FAILURES = 5;",
                "",
                "    public static void main(String[] args) {",
                "        System.out.println(\"jsmith: \" + status(7));",
                "        System.out.println(\"m.reyes: \" + status(2));",
                "        System.out.println(\"svc-backup: \" + status(5));",
                "    }",
                "",
                "    static String status(int failures) {",
                "        // one line: LOCKED at MAX_FAILURES or more, else active",
                "    }",
                "}")
            .yourTask(
                "The three copies of the rule are gone from main. Write "
                + "status's return line so the output is exactly as before: "
                + "LOCKED at MAX_FAILURES or more, otherwise active.")
            .mainTask(new Task(Task.WRITE,
                    "Write status's return line.")
                .accept("return failures >= MAX_FAILURES ? \"LOCKED\" : \"active\";",
                        "return (failures >= MAX_FAILURES) ? \"LOCKED\" : \"active\";",
                        "return failures < MAX_FAILURES ? \"active\" : \"LOCKED\";")
                .hints(
                    "The conditional operator picks one of two values.",
                    "Use the constant, not 5, and >= for 'or more'.",
                    "return failures >= MAX_FAILURES ? \"LOCKED\" : \"active\";")
                .solution(
                    "public class Main {",
                    "    static final int MAX_FAILURES = 5;",
                    "",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"jsmith: \" + status(7));",
                    "        System.out.println(\"m.reyes: \" + status(2));",
                    "        System.out.println(\"svc-backup: \" + status(5));",
                    "    }",
                    "",
                    "    static String status(int failures) {",
                    "        return failures >= MAX_FAILURES ? \"LOCKED\" : \"active\";",
                    "    }",
                    "}")
                .whyItWorks(
                    "The rule now exists once, uses the named constant, and "
                    + "returns rather than prints. The output is exactly the "
                    + "same as the original: LOCKED, active, LOCKED - "
                    + "including svc-backup at exactly 5, the boundary where a "
                    + "careless > would have changed behaviour.\n"
                    + "\n"
                    + "From now on, a change to the lockout rule is a change "
                    + "to one line.")
                .explain(
                    "One rule, using the constant, with >= for 'or more'.")
                .xp(25))
            .mistakes(
                new String[]{"Changing behaviour while tidying",
                    "Check the output is identical after every step."},
                new String[]{"Refactoring everything at once",
                    "One small change at a time, checked each time."},
                new String[]{"Refactoring without tests",
                    "Without them, you cannot tell whether behaviour "
                    + "changed."})
            .cyber(
                "Refactoring is how security debt gets paid down. A security "
                + "review finds the same check written in five places, three "
                + "of them subtly wrong; the fix is to extract one correct "
                + "method and call it everywhere.\n"
                + "\n"
                + "The safety rule matters twice over here. A refactoring that "
                + "quietly changes a boundary - as in the prediction task - "
                + "can open exactly the hole the review was meant to close. "
                + "Security teams insist on tests for every refactored check "
                + "for that reason.")
            .check(new Task(Task.CHOICE,
                    "What should you have before you start refactoring?")
                .choices("A new feature to add",
                         "A way to check the behaviour has not changed",
                         "A second copy of Java",
                         "Permission to change the output")
                .accept("2", "b")
                .hints("How will you know nothing broke?",
                       "Mission 23.")
                .explain(
                    "A way to check behaviour - tests, or at least the "
                    + "known output - so every step can be verified.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Three methods each contain the same four lines. Which "
                    + "refactoring fits?")
                .choices("Rename", "Extract method", "Add a constant",
                         "Delete two of the methods")
                .accept("2", "b")
                .hints("Repeated code...",
                       "...becomes one method, called three times.")
                .explain(
                    "Extract method: move the four lines into a new, well-"
                    + "named method and call it from all three places.")
                .xp(10))
            .recap(
                "Refactoring changes structure, never behaviour: extract "
                + "methods, rename, name magic numbers, add guards.\n"
                + "\n"
                + "Know the output first, change one thing at a time, and "
                + "check the output is identical after each step.")
            .next("Next: a small library of validation methods."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(26), "A Validation Library", 5)
            .brief(
                "Every NORTHSTAR tool asks the same questions about its "
                + "input: is this a sensible user name, a real port, a "
                + "plausible host name? Instead of each tool inventing its "
                + "own answer, the team wants one set of checks everyone "
                + "shares.")
            .willLearn("Validation helpers")
            .whyUseful(
                "A shared set of small validators means every part of a "
                + "system applies the same rules. Fix or tighten a rule once "
                + "and every caller is protected.")
            .concept("Validation helpers",
                "A VALIDATION LIBRARY is a group of boolean methods, each "
                + "answering one question about one kind of input:\n"
                + "\n"
                + "    isValidUsername(String name)\n"
                + "    isValidPort(String text)\n"
                + "    isValidHost(String host)\n"
                + "\n"
                + "Each one follows the pattern from mission 10: guards "
                + "return false for anything that rules the value out, and "
                + "the last line returns the final rule. Each one gets "
                + "tests (mission 23) and a doc comment (mission 24).\n"
                + "\n"
                + "Small validators COMBINE into bigger ones:\n"
                + "\n"
                + "    static boolean isValidRequest(String user,\n"
                + "                                  String port) {\n"
                + "        return isValidUsername(user)\n"
                + "            && isValidPort(port);\n"
                + "    }\n"
                + "\n"
                + "&& short-circuits, so the second check only runs if the "
                + "first passed - handy when a later check assumes an "
                + "earlier one.\n"
                + "\n"
                + "WHERE TO VALIDATE: at the BOUNDARY - as soon as data comes "
                + "into the program, before anything uses it. Code further in "
                + "can then rely on its inputs being sensible.\n"
                + "\n"
                + "ALLOW-LISTS beat block-lists: describe what IS allowed "
                + "(3-20 characters, no spaces) rather than trying to list "
                + "every bad thing. Attackers are better at inventing bad "
                + "input than defenders are at listing it.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(isValidUsername(\"jsmith\"));",
                "        System.out.println(isValidUsername(\"js\"));",
                "        System.out.println(isValidUsername(\"j smith\"));",
                "        System.out.println(isValidPort(\"443\"));",
                "        System.out.println(isValidHost(\"-web01\"));",
                "    }",
                "",
                "    static boolean isValidUsername(String name) {",
                "        return name.length() >= 3 && name.length() <= 20",
                "                && !name.contains(\" \");",
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
                "                && !host.startsWith(\"-\") && !host.contains(\" \");",
                "    }",
                "}")
            .exampleOutput(
                "true",
                "false",
                "false",
                "true",
                "false")
            .lineByLine(
                new String[]{"isValidUsername(\"js\")",
                    "Too short: false."},
                new String[]{"isValidUsername(\"j smith\")",
                    "Contains a space: false."},
                new String[]{"isValidHost(\"-web01\")",
                    "Host names may not start with a hyphen: false."},
                new String[]{"each method",
                    "One question, guards first, the rule last."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static boolean isShortEnough(String s) {",
                    "    return s.length() <= 8;",
                    "}",
                    "",
                    "static boolean hasNoSpaces(String s) {",
                    "    return !s.contains(\" \");",
                    "}",
                    "",
                    "static boolean isValidTag(String s) {",
                    "    return !s.isEmpty() && isShortEnough(s) && hasNoSpaces(s);",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(isValidTag(\"prod\"));",
                    "    System.out.println(isValidTag(\"prod env\"));",
                    "}")
                .accept("true false")
                .hints("\"prod\" passes all three.",
                       "\"prod env\" has a space.")
                .explain(
                    "true false. \"prod\" is not empty, short enough and has "
                    + "no spaces. \"prod env\" is 8 characters - short enough "
                    + "- but fails hasNoSpaces.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Where should input be validated?")
                .choices("Just before it is printed",
                         "As soon as it enters the program",
                         "Only if it looks suspicious",
                         "At the end of main")
                .accept("2", "b")
                .hints("Before anything uses it.",
                       "At the boundary.")
                .explain(
                    "As soon as it enters the program - at the boundary - so "
                    + "nothing further in ever handles an unchecked value.")
                .xp(10))
            .objective(
                "Combine two validators into one request check.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(isValidRequest(\"jsmith\", \"443\"));",
                "        System.out.println(isValidRequest(\"jsmith\", \"0\"));",
                "        System.out.println(isValidRequest(\"j\", \"443\"));",
                "    }",
                "",
                "    static boolean isValidRequest(String user, String port) {",
                "        // one line: both the user name and the port are valid",
                "    }",
                "",
                "    static boolean isValidUsername(String name) {",
                "        return name.length() >= 3 && name.length() <= 20",
                "                && !name.contains(\" \");",
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
            .yourTask(
                "Write isValidRequest's return line: true only when "
                + "isValidUsername says yes for user AND isValidPort says yes "
                + "for port.")
            .mainTask(new Task(Task.WRITE,
                    "Write the return line.")
                .accept("return isValidUsername(user) && isValidPort(port);",
                        "return isValidPort(port) && isValidUsername(user);")
                .hints(
                    "Both must be true: &&.",
                    "Each side is a call to one of the validators.",
                    "return isValidUsername(user) && isValidPort(port);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(isValidRequest(\"jsmith\", \"443\"));",
                    "        System.out.println(isValidRequest(\"jsmith\", \"0\"));",
                    "        System.out.println(isValidRequest(\"j\", \"443\"));",
                    "    }",
                    "",
                    "    static boolean isValidRequest(String user, String port) {",
                    "        return isValidUsername(user) && isValidPort(port);",
                    "    }",
                    "",
                    "    static boolean isValidUsername(String name) {",
                    "        return name.length() >= 3 && name.length() <= 20",
                    "                && !name.contains(\" \");",
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
                .whyItWorks(
                    "The request is valid only when both parts are, so the "
                    + "two calls are joined with &&. The program prints true "
                    + "(both valid), false (port 0), false (a one-letter "
                    + "user).\n"
                    + "\n"
                    + "isValidRequest contains no rules of its own - it only "
                    + "combines existing validators. Tighten the user name "
                    + "rule once, and this check tightens with it.")
                .explain(
                    "Combine the two validators with &&.")
                .xp(20))
            .mistakes(
                new String[]{"Re-writing a rule inside a new method",
                    "Call the existing validator instead."},
                new String[]{"Block-lists",
                    "Describe what is allowed, not every bad thing."},
                new String[]{"Validating late",
                    "Check at the boundary, before anything uses the value."})
            .cyber(
                "Input validation is the first line of defence against "
                + "injection attacks, and the rules from this mission are the "
                + "ones security standards teach: validate at the boundary, "
                + "prefer allow-lists, and use one shared, tested "
                + "implementation.\n"
                + "\n"
                + "Validation is not the whole defence - a valid user name "
                + "can still be used in an attack if later code builds "
                + "commands or queries from it carelessly. But it shrinks "
                + "what an attacker can send down to what the program "
                + "actually expects.")
            .check(new Task(Task.CHOICE,
                    "Why do all the tools share one set of validators?")
                .choices("It uses less memory",
                         "Every part of the system applies the same rules",
                         "Java needs validators in one place",
                         "Each tool runs faster")
                .accept("2", "b")
                .hints("Remember the three port checks, one of them wrong.",
                       "One rule, many callers.")
                .explain(
                    "Every caller gets the same, tested rule - and a fix in "
                    + "one place protects all of them.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "What is an allow-list?")
                .choices("A list of known attacks to refuse",
                         "A description of what valid input looks like",
                         "A list of users who may skip validation",
                         "A list of allowed error messages")
                .accept("2", "b")
                .hints("Allowed, rather than blocked.",
                       "3 to 20 characters, no spaces...")
                .explain(
                    "A description of what IS allowed. Anything outside it is "
                    + "refused - including attacks nobody has thought of "
                    + "yet.")
                .xp(10))
            .recap(
                "A validation library: small boolean methods, one question "
                + "each, guards then rule, tested and documented. Combine "
                + "them with &&.\n"
                + "\n"
                + "Validate at the boundary. Describe what is allowed.")
            .next("Next: a method that refuses bad requests in the right "
                + "order."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(27), "Failing Fast, Failing Closed", 5)
            .brief(
                "The login method checks the password, then checks whether "
                + "the account is locked. That order has a flaw: a locked "
                + "account still answers 'wrong password' or 'right "
                + "password' - so an attacker can keep guessing forever. The "
                + "checks are right. Their order is not.")
            .willLearn("Fail fast", "Fail closed")
            .whyUseful(
                "Deciding the ORDER of guards, and what happens when nothing "
                + "matches, is how a method turns a list of rules into a "
                + "security control that cannot be walked around.")
            .concept("Fail fast",
                "A method that handles a request should FAIL FAST: check "
                + "everything that could make the request invalid at the top, "
                + "and return as soon as one fails.\n"
                + "\n"
                + "    static String login(String user, String password,\n"
                + "                        int failures) {\n"
                + "        if (user.isBlank()) {\n"
                + "            return \"REFUSED: no user name\";\n"
                + "        }\n"
                + "        if (failures >= MAX_FAILURES) {\n"
                + "            return \"REFUSED: account locked\";\n"
                + "        }\n"
                + "        if (!password.equals(SECRET)) {\n"
                + "            return \"REFUSED: wrong password\";\n"
                + "        }\n"
                + "        return \"WELCOME \" + user;\n"
                + "    }\n"
                + "\n"
                + "ORDER THE GUARDS ON PURPOSE:\n"
                + "\n"
                + "    shape first    is there anything to check at all?\n"
                + "    state next     is this account allowed to try?\n"
                + "    secrets last   only then compare the password\n"
                + "\n"
                + "A locked account must be refused BEFORE the password is "
                + "looked at, or every guess still gets an answer.\n"
                + "\n"
                + "FAIL CLOSED. The only way to reach the success line is to "
                + "pass every guard. If a new rule is added and forgotten, "
                + "or a value is strange in a way nobody predicted, the "
                + "method's default is to refuse. The opposite - allowing "
                + "unless a rule says no - is FAIL OPEN, and it is how "
                + "access controls get bypassed.")
            .example(
                "public class Main {",
                "    static final int MAX_FAILURES = 5;",
                "    static final String SECRET = \"Northstar#2026\";",
                "",
                "    public static void main(String[] args) {",
                "        System.out.println(login(\"\", \"x\", 0));",
                "        System.out.println(login(\"jsmith\", SECRET, 6));",
                "        System.out.println(login(\"jsmith\", \"guess\", 1));",
                "        System.out.println(login(\"jsmith\", SECRET, 1));",
                "    }",
                "",
                "    static String login(String user, String password, int failures) {",
                "        if (user.isBlank()) {",
                "            return \"REFUSED: no user name\";",
                "        }",
                "        if (failures >= MAX_FAILURES) {",
                "            return \"REFUSED: account locked\";",
                "        }",
                "        if (!password.equals(SECRET)) {",
                "            return \"REFUSED: wrong password\";",
                "        }",
                "        return \"WELCOME \" + user;",
                "    }",
                "}")
            .exampleOutput(
                "REFUSED: no user name",
                "REFUSED: account locked",
                "REFUSED: wrong password",
                "WELCOME jsmith")
            .lineByLine(
                new String[]{"login(\"jsmith\", SECRET, 6)",
                    "The RIGHT password - and still refused, because the lock "
                    + "is checked first."},
                new String[]{"return \"WELCOME \" + user;",
                    "The only success, reachable only past every guard."},
                new String[]{"three guards, in order",
                    "Shape, then state, then the secret."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static String access(boolean locked, boolean passwordOk) {",
                    "    if (!passwordOk) {",
                    "        return \"wrong password\";",
                    "    }",
                    "    if (locked) {",
                    "        return \"locked\";",
                    "    }",
                    "    return \"granted\";",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(access(true, false));",
                    "    System.out.println(access(true, true));",
                    "}")
                .accept("wrong password locked")
                .hints("The password is checked first here.",
                       "What does a locked account reveal about each guess?")
                .explain(
                    "    wrong password\n"
                    + "    locked\n"
                    + "\n"
                    + "The account is locked both times, but the answers "
                    + "differ - so an attacker learns which guess was right. "
                    + "Checking locked first would print locked twice.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "What does 'fail closed' mean?")
                .choices("Close the program when an error happens",
                         "When in doubt, refuse",
                         "Check the password last",
                         "Lock every account after one failure")
                .accept("2", "b")
                .hints("What is the default outcome?",
                       "Success must be earned by passing every check.")
                .explain(
                    "When in doubt, refuse. Access is only granted by "
                    + "passing every check; anything unexpected ends in a "
                    + "refusal.")
                .xp(10))
            .objective(
                "Put the lock check where it belongs.")
            .starter(
                "public class Main {",
                "    static final int MAX_FAILURES = 5;",
                "    static final String SECRET = \"Northstar#2026\";",
                "",
                "    public static void main(String[] args) {",
                "        System.out.println(login(\"jsmith\", SECRET, 6));",
                "        System.out.println(login(\"jsmith\", SECRET, 0));",
                "    }",
                "",
                "    static String login(String user, String password, int failures) {",
                "        if (user.isBlank()) {",
                "            return \"REFUSED: no user name\";",
                "        }",
                "        // the lock guard's if line goes here",
                "            return \"REFUSED: account locked\";",
                "        }",
                "        if (!password.equals(SECRET)) {",
                "            return \"REFUSED: wrong password\";",
                "        }",
                "        return \"WELCOME \" + user;",
                "    }",
                "}")
            .yourTask(
                "Write the if line for the lock guard: it refuses when "
                + "failures has reached MAX_FAILURES. It sits before the "
                + "password check.")
            .mainTask(new Task(Task.WRITE,
                    "Write the lock guard's if line.")
                .accept("if (failures >= MAX_FAILURES) {",
                        "if(failures >= MAX_FAILURES) {",
                        "if (failures >= MAX_FAILURES){")
                .hints(
                    "Reaching the limit counts: >=.",
                    "Use the constant, not 5.",
                    "if (failures >= MAX_FAILURES) {")
                .solution(
                    "public class Main {",
                    "    static final int MAX_FAILURES = 5;",
                    "    static final String SECRET = \"Northstar#2026\";",
                    "",
                    "    public static void main(String[] args) {",
                    "        System.out.println(login(\"jsmith\", SECRET, 6));",
                    "        System.out.println(login(\"jsmith\", SECRET, 0));",
                    "    }",
                    "",
                    "    static String login(String user, String password, int failures) {",
                    "        if (user.isBlank()) {",
                    "            return \"REFUSED: no user name\";",
                    "        }",
                    "        if (failures >= MAX_FAILURES) {",
                    "            return \"REFUSED: account locked\";",
                    "        }",
                    "        if (!password.equals(SECRET)) {",
                    "            return \"REFUSED: wrong password\";",
                    "        }",
                    "        return \"WELCOME \" + user;",
                    "    }",
                    "}")
                .whyItWorks(
                    "With 6 failures, the guard returns before the password "
                    + "is ever compared - so even the right password gets "
                    + "REFUSED: account locked, and an attacker learns "
                    + "nothing. With 0 failures, every guard passes and the "
                    + "program prints WELCOME jsmith.\n"
                    + "\n"
                    + "Using the constant keeps this guard in step with every "
                    + "other place that talks about the lockout limit.")
                .explain(
                    "if (failures >= MAX_FAILURES) { before the password "
                    + "check.")
                .xp(20))
            .mistakes(
                new String[]{"Checking secrets before state",
                    "A locked account must not answer password guesses."},
                new String[]{"Failing open",
                    "Success should be the last line, reached only past every "
                    + "guard."},
                new String[]{"Messages that leak",
                    "Real systems often say 'login failed' for every case, "
                    + "and keep the detail in the audit log."})
            .cyber(
                "Guard order and fail-closed design are core ideas in "
                + "authentication. Real attacks have used the flaw from the "
                + "brief: a lockout that still reports whether a password was "
                + "right lets attackers keep guessing, and only stops them "
                + "logging in once they have the answer.\n"
                + "\n"
                + "Messages are part of it too. REFUSED: wrong password tells "
                + "an attacker the user name exists. Production systems "
                + "usually show one message for every failure - and keep the "
                + "real reason in the audit log, as Campaign 02's login lab "
                + "did.")
            .check(new Task(Task.CHOICE,
                    "Why must the lock be checked before the password?")
                .choices("It is faster",
                         "Otherwise a locked account still reveals whether "
                         + "guesses are right",
                         "Passwords cannot be checked first in Java",
                         "It makes the method shorter")
                .accept("2", "b")
                .hints("What does each guess teach an attacker?",
                       "Look at the prediction task.")
                .explain(
                    "If the password is checked first, a locked account still "
                    + "answers every guess - and the lockout protects "
                    + "nothing.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static String route(String role) {",
                    "    if (role.equals(\"admin\")) {",
                    "        return \"console\";",
                    "    }",
                    "    if (role.equals(\"analyst\")) {",
                    "        return \"dashboard\";",
                    "    }",
                    "    return \"denied\";",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(route(\"Admin\") + \" \" + route(\"analyst\"));",
                    "}")
                .accept("denied dashboard")
                .hints("equals cares about capitals.",
                       "Which line do unknown roles reach?")
                .explain(
                    "denied dashboard. \"Admin\" matches no rule and falls to "
                    + "the default: denied. That is failing closed - an "
                    + "unrecognised role gets nothing.")
                .xp(15))
            .recap(
                "Fail fast: every guard at the top, return on the first "
                + "failure. Order them: shape, then state, then secrets.\n"
                + "\n"
                + "Fail closed: success is the last line, reached only past "
                + "every check. Anything unexpected is refused.")
            .next("Next: making text safe to write into a log."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(28), "Cleaning Text for the Log", 5)
            .brief(
                "An attacker signs up with a user name containing a line "
                + "break. When the failed login is logged, the audit file "
                + "shows TWO lines - and the second one reads LOGIN OK "
                + "admin. The log has been forged. One small method stops "
                + "it.")
            .willLearn("Output sanitising")
            .whyUseful(
                "Logs are evidence. If attackers can shape what they say, "
                + "investigations are misled and alerts can be hidden. A "
                + "sanitiser that every log line passes through keeps the "
                + "evidence honest.")
            .concept("Output sanitising",
                "SANITISING means making a value safe for the place it is "
                + "going. For a log line, three things matter:\n"
                + "\n"
                + "    line breaks   \\n and \\r start a new line, so an\n"
                + "                  attacker can forge a whole entry\n"
                + "    length        a 10 MB user name fills disks and\n"
                + "                  pushes real entries out of view\n"
                + "    emptiness     a blank value looks like a missing\n"
                + "                  field, and hides what happened\n"
                + "\n"
                + "One method handles all three, and every log line goes "
                + "through it:\n"
                + "\n"
                + "    static String forLog(String text) {\n"
                + "        if (text.isBlank()) {\n"
                + "            return \"(empty)\";\n"
                + "        }\n"
                + "        String clean = text.replace(\"\\r\", \" \")\n"
                + "                           .replace(\"\\n\", \" \");\n"
                + "        if (clean.length() > MAX_LENGTH) {\n"
                + "            clean = clean.substring(0, MAX_LENGTH)\n"
                + "                    + \"...\";\n"
                + "        }\n"
                + "        return clean;\n"
                + "    }\n"
                + "\n"
                + "Mission 14's rule applies: it RETURNS the cleaned text, and "
                + "callers use what comes back.\n"
                + "\n"
                + "Sanitising depends on the DESTINATION. Text safe for a log "
                + "may still be dangerous in a web page, a database query or "
                + "a shell command - each has its own special characters, "
                + "and its own sanitiser.")
            .example(
                "public class Main {",
                "    static final int MAX_LENGTH = 20;",
                "",
                "    public static void main(String[] args) {",
                "        System.out.println(\"user=\" + forLog(\"jsmith\"));",
                "        System.out.println(\"user=\" + forLog(\"bob\\nLOGIN OK admin\"));",
                "        System.out.println(\"user=\" + forLog(\"   \"));",
                "        String huge = \"aaaaaaaaaaaaaaaaaaaaaaaaa\";",
                "        System.out.println(\"user=\" + forLog(huge));",
                "    }",
                "",
                "    static String forLog(String text) {",
                "        if (text.isBlank()) {",
                "            return \"(empty)\";",
                "        }",
                "        String clean = text.replace(\"\\r\", \" \").replace(\"\\n\", \" \");",
                "        if (clean.length() > MAX_LENGTH) {",
                "            clean = clean.substring(0, MAX_LENGTH) + \"...\";",
                "        }",
                "        return clean;",
                "    }",
                "}")
            .exampleOutput(
                "user=jsmith",
                "user=bob LOGIN OK admin",
                "user=(empty)",
                "user=aaaaaaaaaaaaaaaaaaaa...")
            .lineByLine(
                new String[]{"forLog(\"bob\\nLOGIN OK admin\")",
                    "The forged line becomes part of one harmless line."},
                new String[]{"return \"(empty)\";",
                    "A blank value is made visible instead of silent."},
                new String[]{"clean.substring(0, MAX_LENGTH) + \"...\"",
                    "Over-long input is cut, and the cut is marked."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static String forLog(String text) {",
                    "    return text.replace(\"\\n\", \" | \");",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(forLog(\"a\\nb\"));",
                    "}")
                .accept("a | b")
                .hints("\\n is one character: a line break.",
                       "replace swaps it for \" | \".")
                .explain(
                    "a | b. The line break is replaced, so the output is one "
                    + "line.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "How many lines does this print?")
                .code(
                    "String user = \"bob\\nLOGIN OK admin\";",
                    "System.out.println(\"FAILED login: \" + user);")
                .accept("2", "two")
                .hints("What does \\n do inside a String?",
                       "Nothing sanitises it.")
                .explain(
                    "2. The \\n in the user name starts a new line, so the log "
                    + "shows 'FAILED login: bob' and then a forged 'LOGIN OK "
                    + "admin'.")
                .xp(15))
            .objective(
                "Write the core of a log sanitiser.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String user = \"bob\\nLOGIN OK admin\";",
                "        System.out.println(\"FAILED login: \" + forLog(user));",
                "    }",
                "",
                "    static String forLog(String text) {",
                "        // return text with every \\n replaced by a space",
                "    }",
                "}")
            .yourTask(
                "Write forLog's return line: the text with every line break "
                + "(\\n) replaced by a single space.")
            .mainTask(new Task(Task.WRITE,
                    "Write the return line.")
                .accept("return text.replace(\"\\n\", \" \");")
                .hints(
                    "replace takes what to find, then what to put instead.",
                    "A line break is written \"\\n\" in Java.",
                    "return text.replace(\"\\n\", \" \");")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String user = \"bob\\nLOGIN OK admin\";",
                    "        System.out.println(\"FAILED login: \" + forLog(user));",
                    "    }",
                    "",
                    "    static String forLog(String text) {",
                    "        return text.replace(\"\\n\", \" \");",
                    "    }",
                    "}")
                .whyItWorks(
                    "replace builds a new String with every line break turned "
                    + "into a space, and forLog returns it. The log now shows "
                    + "one line: FAILED login: bob LOGIN OK admin - obviously "
                    + "one strange user name, not a successful admin login.\n"
                    + "\n"
                    + "A full sanitiser also handles \\r, over-long text and "
                    + "blank values, as in the example. Because it is one "
                    + "method, every addition protects every log line.")
                .explain(
                    "text.replace(\"\\n\", \" \"), returned.")
                .xp(20))
            .mistakes(
                new String[]{"Only replacing \\n",
                    "\\r can break lines too. Handle both."},
                new String[]{"Sanitising in some places",
                    "Every log line must go through the same method."},
                new String[]{"One sanitiser for every destination",
                    "Logs, web pages and databases each need their own."})
            .cyber(
                "Log injection - also called log forging - is a recognised "
                + "weakness in secure-coding standards. Besides fake entries, "
                + "attackers have used it to hide their own activity among "
                + "junk lines and to attack the tools that later display the "
                + "logs.\n"
                + "\n"
                + "The same idea, applied to other destinations, gives you "
                + "defences against much bigger attacks: escaping for HTML "
                + "stops cross-site scripting, and keeping data out of SQL "
                + "text stops SQL injection. Different characters, same "
                + "principle: make data safe for where it is going.")
            .check(new Task(Task.CHOICE,
                    "What is log injection?")
                .choices("Adding too many log lines",
                         "Input that creates fake or misleading log entries",
                         "Deleting the log file",
                         "Logging in with a stolen password")
                .accept("2", "b")
                .hints("What did the line break do?",
                       "The log said something that never happened.")
                .explain(
                    "Input crafted so the log shows entries that never "
                    + "happened - usually by smuggling in line breaks.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Why cap the length of text written to a log?")
                .choices("Long text cannot be logged",
                         "Huge input can fill disks and push real entries out "
                         + "of view",
                         "Java strings have a maximum of 20 characters",
                         "To save typing")
                .accept("2", "b")
                .hints("What if a user name is 10 MB?",
                       "Think of the disk, and the analyst reading it.")
                .explain(
                    "Enormous values can exhaust storage and bury the entries "
                    + "that matter. Cutting them - and marking the cut - keeps "
                    + "the log usable.")
                .xp(10))
            .recap(
                "A log sanitiser: blank becomes (empty), line breaks become "
                + "spaces, long text is cut and marked. It returns the clean "
                + "text, and every log line uses it.\n"
                + "\n"
                + "Sanitise for the destination: logs, HTML and queries each "
                + "have their own dangers.")
            .next("Next: putting the whole toolkit together."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(29), "A Small Security Toolkit", 6)
            .brief(
                "Validators, guards, a sanitiser, constants - each has been "
                + "built on its own. Now they work together: one method "
                + "handles a login request from start to finish by calling "
                + "the right helper at each step. This is what a real piece "
                + "of security code looks like.")
            .willLearn("Designing a toolkit")
            .whyUseful(
                "Real security code is rarely one clever method. It is a set "
                + "of small, trustworthy pieces - each tested and documented "
                + "- combined by a short method that reads like the policy.")
            .concept("Designing a toolkit",
                "A TOOLKIT is a set of methods designed to work together. "
                + "Each one does one job:\n"
                + "\n"
                + "    constants     MAX_FAILURES, BLOCKED_RANGE\n"
                + "    validators    isValidUsername\n"
                + "    rules         isBlocked, shouldLock\n"
                + "    sanitiser     forLog\n"
                + "    decision      handle - calls the others\n"
                + "\n"
                + "The decision method is short, and reads as the POLICY:\n"
                + "\n"
                + "    if (!isValidUsername(user))  refuse\n"
                + "    if (isBlocked(ip))            refuse\n"
                + "    if (shouldLock(failures))     refuse\n"
                + "    otherwise                     allow\n"
                + "\n"
                + "Every refusal and every success is logged through forLog, "
                + "so nothing an attacker types can forge the audit trail.\n"
                + "\n"
                + "The design rules from the whole campaign, in one list:\n"
                + "\n"
                + "    one job per method, named honestly\n"
                + "    inputs as parameters, answers as returns\n"
                + "    shared rules as constants\n"
                + "    guards first, fail closed\n"
                + "    validate at the boundary, sanitise at output\n"
                + "    test the boundaries; document the contract\n"
                + "\n"
                + "None of the helpers knows about the others. That is the "
                + "point: each can be tested, fixed and trusted alone.")
            .example(
                "public class Main {",
                "    static final int MAX_FAILURES = 5;",
                "    static final String BLOCKED_RANGE = \"203.0.113.\";",
                "",
                "    public static void main(String[] args) {",
                "        handle(\"jsmith\", \"10.0.0.7\", 0);",
                "        handle(\"eve\\nOK\", \"10.0.0.8\", 0);",
                "        handle(\"m.reyes\", \"203.0.113.5\", 0);",
                "        handle(\"svc-backup\", \"10.0.0.9\", 5);",
                "    }",
                "",
                "    static void handle(String user, String ip, int failures) {",
                "        String who = forLog(user) + \"@\" + ip;",
                "        if (!isValidUsername(user)) {",
                "            System.out.println(\"DENY bad name: \" + who);",
                "        } else if (isBlocked(ip)) {",
                "            System.out.println(\"DENY blocked range: \" + who);",
                "        } else if (shouldLock(failures)) {",
                "            System.out.println(\"DENY locked: \" + who);",
                "        } else {",
                "            System.out.println(\"ALLOW \" + who);",
                "        }",
                "    }",
                "",
                "    static boolean isValidUsername(String name) {",
                "        return name.length() >= 3 && name.length() <= 20",
                "                && !name.contains(\" \") && !name.contains(\"\\n\");",
                "    }",
                "",
                "    static boolean isBlocked(String ip) {",
                "        return ip.startsWith(BLOCKED_RANGE);",
                "    }",
                "",
                "    static boolean shouldLock(int failures) {",
                "        return failures >= MAX_FAILURES;",
                "    }",
                "",
                "    static String forLog(String text) {",
                "        return text.replace(\"\\r\", \" \").replace(\"\\n\", \" \");",
                "    }",
                "}")
            .exampleOutput(
                "ALLOW jsmith@10.0.0.7",
                "DENY bad name: eve OK@10.0.0.8",
                "DENY blocked range: m.reyes@203.0.113.5",
                "DENY locked: svc-backup@10.0.0.9")
            .lineByLine(
                new String[]{"String who = forLog(user) + \"@\" + ip;",
                    "Sanitised once, before any line is printed."},
                new String[]{"the else-if chain",
                    "The policy in four lines: name, range, lock, allow."},
                new String[]{"DENY bad name: eve OK@10.0.0.8",
                    "The forged line break was refused AND neutralised in the "
                    + "log."})
            .predict(new Task(Task.PREDICT,
                    "Using the example's methods, what does this print?")
                .code(
                    "static final int MAX_FAILURES = 5;",
                    "",
                    "static boolean shouldLock(int failures) {",
                    "    return failures >= MAX_FAILURES;",
                    "}",
                    "",
                    "static String verdict(int failures) {",
                    "    return shouldLock(failures) ? \"DENY\" : \"ALLOW\";",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    System.out.println(verdict(4) + \" \" + verdict(5));",
                    "}")
                .accept("ALLOW DENY")
                .hints("4 is below the limit.",
                       "5 reaches it.")
                .explain(
                    "ALLOW DENY. shouldLock(4) is false and shouldLock(5) is "
                    + "true - the boundary, handled by one rule.")
                .xp(10))
            .practice(new Task(Task.CHOICE,
                    "In the example, why is forLog called before the "
                    + "if-chain rather than inside each branch?")
                .choices("It is faster to call it early",
                         "Every branch prints, so every line is protected at "
                         + "once",
                         "forLog changes user",
                         "Java requires it")
                .accept("2", "b")
                .hints("How many branches print the user?",
                       "Could a new branch forget to sanitise?")
                .explain(
                    "Every branch prints who, so cleaning it once protects "
                    + "all four lines - and any branch added later.")
                .xp(15))
            .objective(
                "Add the blocklist step to the request handler.")
            .starter(
                "public class Main {",
                "    static final String BLOCKED_RANGE = \"203.0.113.\";",
                "",
                "    public static void main(String[] args) {",
                "        System.out.println(decide(\"jsmith\", \"203.0.113.5\"));",
                "        System.out.println(decide(\"jsmith\", \"10.0.0.7\"));",
                "    }",
                "",
                "    static String decide(String user, String ip) {",
                "        if (user.isBlank()) {",
                "            return \"DENY\";",
                "        }",
                "        // the blocklist guard's if line goes here",
                "            return \"DENY\";",
                "        }",
                "        return \"ALLOW\";",
                "    }",
                "",
                "    static boolean isBlocked(String ip) {",
                "        return ip.startsWith(BLOCKED_RANGE);",
                "    }",
                "}")
            .yourTask(
                "Write the if line of the blocklist guard, using the "
                + "isBlocked helper rather than repeating its rule.")
            .mainTask(new Task(Task.WRITE,
                    "Write the blocklist guard's if line.")
                .accept("if (isBlocked(ip)) {", "if(isBlocked(ip)) {",
                        "if (isBlocked(ip)){")
                .hints(
                    "The rule already exists as a method.",
                    "Call it inside the if.",
                    "if (isBlocked(ip)) {")
                .solution(
                    "public class Main {",
                    "    static final String BLOCKED_RANGE = \"203.0.113.\";",
                    "",
                    "    public static void main(String[] args) {",
                    "        System.out.println(decide(\"jsmith\", \"203.0.113.5\"));",
                    "        System.out.println(decide(\"jsmith\", \"10.0.0.7\"));",
                    "    }",
                    "",
                    "    static String decide(String user, String ip) {",
                    "        if (user.isBlank()) {",
                    "            return \"DENY\";",
                    "        }",
                    "        if (isBlocked(ip)) {",
                    "            return \"DENY\";",
                    "        }",
                    "        return \"ALLOW\";",
                    "    }",
                    "",
                    "    static boolean isBlocked(String ip) {",
                    "        return ip.startsWith(BLOCKED_RANGE);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The guard asks the existing helper, so the blocklist rule "
                    + "is still written in exactly one place. The program "
                    + "prints DENY for the blocked address and ALLOW for the "
                    + "internal one.\n"
                    + "\n"
                    + "Writing ip.startsWith(\"203.0.113.\") here instead would "
                    + "work today - and drift the day someone updates "
                    + "BLOCKED_RANGE.")
                .explain(
                    "Reuse the helper: if (isBlocked(ip)) {")
                .xp(20))
            .mistakes(
                new String[]{"Repeating a helper's rule inline",
                    "Call the helper. One rule, one place."},
                new String[]{"Sanitising in some branches only",
                    "Clean once, before the branches."},
                new String[]{"A decision method full of details",
                    "Keep it reading like the policy; push details into "
                    + "helpers."})
            .cyber(
                "This is the shape of real authentication and access-control "
                + "code: a short decision method that reads like the policy, "
                + "backed by helpers that each do one job well. Security "
                + "reviews and audits are far easier on code like this - "
                + "each helper is checked once, and the policy is checked by "
                + "reading a handful of lines.\n"
                + "\n"
                + "It is also DEFENCE IN DEPTH in miniature: the bad user name "
                + "is refused by the validator AND neutralised by the "
                + "sanitiser. If one layer is ever wrong, the other still "
                + "helps.")
            .check(new Task(Task.CHOICE,
                    "What should the decision method in a toolkit mostly "
                    + "contain?")
                .choices("All the detailed rules, written out",
                         "Calls to helpers, in the order the policy states",
                         "Only print statements",
                         "Nothing - main should decide")
                .accept("2", "b")
                .hints("It should read like the policy.",
                       "Details live in helpers.")
                .explain(
                    "Calls to helpers, in policy order. The details live in "
                    + "the helpers, where they are tested once.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "The bad name is refused by the validator AND cleaned by "
                    + "the sanitiser. What is this called?")
                .choices("Duplication", "Defence in depth", "Overloading",
                         "Recursion")
                .accept("2", "b")
                .hints("Layers of protection.",
                       "If one fails, another still helps.")
                .explain(
                    "Defence in depth - several independent layers, so one "
                    + "mistake does not open the door.")
                .xp(10))
            .recap(
                "A toolkit: constants, validators, rules and a sanitiser, "
                + "each doing one job - and a short decision method that "
                + "calls them in the order the policy states.\n"
                + "\n"
                + "One rule, one place. Clean once. Fail closed. Layers.")
            .next("Next: the campaign checkpoint."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(30), "METHODS COMPLETE", 6)
            .brief(
                "Thirty missions ago, every program was one long main. Now "
                + "they are built from named, tested, documented pieces - and "
                + "you know what Java does underneath every call.\n\n"
                + "This checkpoint mixes the whole campaign. No new Java.")
            .willLearn("Recall of the whole campaign")
            .whyUseful(
                "Methods go wrong where ideas meet: a copy changed instead of "
                + "the original, a result nobody stored, a guard in the wrong "
                + "order. This is practice for exactly those.")
            .concept("Everything, together",
                "The campaign in one page.\n"
                + "\n"
                + "WRITING\n"
                + "    static type name(Type param, ...) { ... }\n"
                + "    void - nothing back;  return - a value back\n"
                + "    methods sit side by side, never nested\n"
                + "\n"
                + "CALLING\n"
                + "    round trip: pause, run, come back after\n"
                + "    arguments match parameters BY POSITION\n"
                + "    nested calls run inside out\n"
                + "    overloads: same name, different parameters\n"
                + "\n"
                + "VALUES\n"
                + "    locals live in their frame only\n"
                + "    pass by value: the method gets a copy\n"
                + "    Strings: return the new text, store it\n"
                + "    static final - constants;  static - shared\n"
                + "\n"
                + "THE STACK\n"
                + "    call pushes a frame, return pops it\n"
                + "    stack trace: top = where, below = how\n"
                + "    recursion needs a base case\n"
                + "\n"
                + "DESIGN\n"
                + "    one job, honest name, documented contract\n"
                + "    guards first, fail closed\n"
                + "    validate at the boundary, sanitise output\n"
                + "    test boundaries; refactor with tests")
            .example(
                "public class Main {",
                "    static final int LIMIT = 3;",
                "",
                "    public static void main(String[] args) {",
                "        int tries = 0;",
                "        tries = attempt(tries, \"guess1\");",
                "        tries = attempt(tries, \"guess2\");",
                "        System.out.println(\"Tries: \" + tries);",
                "    }",
                "",
                "    static int attempt(int tries, String guess) {",
                "        if (tries >= LIMIT) {",
                "            return tries;",
                "        }",
                "        System.out.println(\"checking \" + guess);",
                "        return tries + 1;",
                "    }",
                "}")
            .exampleOutput(
                "checking guess1",
                "checking guess2",
                "Tries: 2")
            .lineByLine(
                new String[]{"tries = attempt(tries, \"guess1\");",
                    "Mission 13: the method gets a copy, so the new count is "
                    + "returned and stored."},
                new String[]{"if (tries >= LIMIT)",
                    "Missions 9 and 20: a guard using a class constant."},
                new String[]{"return tries + 1;",
                    "Mission 6: the answer goes back to the caller."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static void addOne(int n) {",
                    "    n++;",
                    "}",
                    "",
                    "static int plusOne(int n) {",
                    "    return n + 1;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    int x = 5;",
                    "    addOne(x);",
                    "    x = plusOne(x);",
                    "    System.out.println(x);",
                    "}")
                .accept("6")
                .hints("addOne changes a copy.",
                       "plusOne's result is stored.")
                .explain(
                    "6. addOne changes its own copy and loses it. plusOne "
                    + "returns 6, which main stores. Mission 13, still the "
                    + "most common method bug.")
                .xp(20))
            .practice(new Task(Task.DEBUG,
                    "Which line does javac report?")
                .code(
                    "static String grade(int score) {",
                    "    if (score >= 90) {",
                    "        return \"HIGH\";",
                    "    } else if (score >= 50) {",
                    "        return \"MEDIUM\";",
                    "    }",
                    "}")
                .accept("7", "line 7")
                .hints("What does grade return for a score of 10?",
                       "Every path must return.")
                .explain(
                    "Line 7: 'missing return statement'. A score below 50 "
                    + "reaches the closing brace with nothing to return. A "
                    + "final return \"LOW\"; - or an else - fixes it "
                    + "(mission 6).")
                .xp(25))
            .objective(
                "Finish a documented, guarded validator.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(isStrongEnough(\"   \"));",
                "        System.out.println(isStrongEnough(\"correct-horse-battery\"));",
                "    }",
                "",
                "    /**",
                "     * Checks the minimum password rule.",
                "     *",
                "     * @param password the password exactly as typed",
                "     * @return true if it is not blank and has 12+ characters",
                "     */",
                "    static boolean isStrongEnough(String password) {",
                "        if (password.isBlank()) {",
                "            return false;",
                "        }",
                "        // the final rule: 12 or more characters",
                "    }",
                "}")
            .yourTask(
                "Write the last line: return whether the password has at "
                + "least 12 characters.")
            .mainTask(new Task(Task.WRITE,
                    "Write the final return line.")
                .accept("return password.length() >= 12;",
                        "return password.length() > 11;",
                        "return 12 <= password.length();")
                .hints(
                    "Return the condition itself.",
                    "'At least 12' includes 12: >=.",
                    "return password.length() >= 12;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(isStrongEnough(\"   \"));",
                    "        System.out.println(isStrongEnough(\"correct-horse-battery\"));",
                    "    }",
                    "",
                    "    /**",
                    "     * Checks the minimum password rule.",
                    "     *",
                    "     * @param password the password exactly as typed",
                    "     * @return true if it is not blank and has 12+ characters",
                    "     */",
                    "    static boolean isStrongEnough(String password) {",
                    "        if (password.isBlank()) {",
                    "            return false;",
                    "        }",
                    "        return password.length() >= 12;",
                    "    }",
                    "}")
                .whyItWorks(
                    "The guard refuses blank passwords first; the last line "
                    + "returns the rule directly. The program prints false, "
                    + "then true.\n"
                    + "\n"
                    + "Five missions in one method: a documented contract "
                    + "(24), a guard clause (9), a boolean method returning "
                    + "its condition (10), the boundary included with >= "
                    + "(23), and no trimming - the password is checked "
                    + "exactly as typed.")
                .explain(
                    "return password.length() >= 12; - the rule, returned "
                    + "directly.")
                .xp(40))
            .mistakes(
                new String[]{"Changing a copy",
                    "Return the new value and store it."},
                new String[]{"A missing return path",
                    "Every path through a non-void method must return."},
                new String[]{"Guards in the wrong order",
                    "State before secrets. Fail closed."})
            .cyber(
                "Everything in security tooling is built from what this "
                + "campaign taught: validators, sanitisers, decision methods, "
                + "constants for policy, tests for boundaries, and stack "
                + "traces to debug them - safely, never shown to users.\n"
                + "\n"
                + "What these programs still cannot do is REPEAT. A lockout "
                + "should count attempts as they happen; a log parser should "
                + "read every line in a file. Campaign 04 adds loops - and "
                + "every method you can now write becomes something a loop "
                + "can call a thousand times.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "static String tidy(String s) {",
                    "    return s.trim().toUpperCase();",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    String host = \" web-01 \";",
                    "    tidy(host);",
                    "    System.out.println(\"[\" + tidy(host) + \"|\" + host.trim() + \"]\");",
                    "}")
                .accept("[WEB-01|web-01]")
                .hints("The first tidy result is thrown away.",
                       "host itself never changes.")
                .explain(
                    "[WEB-01|web-01]. The second tidy call is used directly; "
                    + "host is still \" web-01 \", so host.trim() gives "
                    + "lower-case web-01 (mission 14).")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "A trace ends:  at Main.parse(Main.java:14)  then  at "
                    + "Main.main(Main.java:5). Where did the problem "
                    + "happen?")
                .choices("Line 5, in main", "Line 14, in parse",
                         "In both at once", "It cannot be told")
                .accept("2", "b")
                .hints("The top frame was running.",
                       "main was waiting at line 5.")
                .explain(
                    "Line 14, in parse - the top frame. main was paused at "
                    + "line 5, where it called parse (mission 18).")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Which pair can NOT both exist in one class?")
                .choices("void log(String m) and void log(String m, int n)",
                         "int size(String s) and String size(String t)",
                         "void scan(int p) and void scan(String h)",
                         "int twice(int n) and double twice(double d)")
                .accept("2", "b")
                .hints("Compare parameter types only.",
                       "Names and return types do not count.")
                .explain(
                    "int size(String s) and String size(String t) - both take "
                    + "one String, so their signatures are the same (mission "
                    + "15).")
                .xp(15))
            .recap(
                "CAMPAIGN 03 - METHODS complete.\n"
                + "\n"
                + "Your programs are built from named, tested, documented "
                + "methods - and you can follow every call down the stack "
                + "and back.\n"
                + "\n"
                + "Next they learn to repeat: loops.")
            .next("Next: CAMPAIGN 04 - LOOP//CONTROL."));
    }
}
