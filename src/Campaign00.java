/**
 * CAMPAIGN 00 - INIT
 * Setup and Java orientation. Ten missions.
 *
 * Assumes nothing. By the end the player knows what Java is, what the JDK and
 * JVM do, how a program is compiled and run, what a class and main are, how
 * to print, how to comment, how statements end, what escape sequences do, and
 * how to read a compiler error.
 *
 * No variables, no conditions, no loops. Those are Campaign 01.
 */
public class Campaign00 {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(1), "What Java Actually Is", 1)
            .brief(
                "First day on the security desk at NORTHSTAR SYSTEMS.\n\n"
                + "The tools you will be maintaining here are written in Java. "
                + "Before you touch one, you need to know what that actually "
                + "means: what Java is, and what happens between you typing "
                + "something and the computer doing it.")
            .willLearn("What Java is", "JDK", "JVM", "Source code and bytecode")
            .whyUseful(
                "Almost every confusing error a beginner hits comes from not "
                + "knowing which of these three steps failed. Knowing the steps "
                + "turns 'it does not work' into 'it failed at compile time', "
                + "which is a question you can answer.")
            .concept("How Java runs",
                "Java takes THREE steps to get from what you type to something "
                + "happening.\n"
                + "\n"
                + "STEP 1 - You write SOURCE CODE.\n"
                + "That is a plain text file ending in .java. It is for humans. "
                + "The computer cannot run it.\n"
                + "\n"
                + "STEP 2 - The COMPILER turns it into BYTECODE.\n"
                + "The tool is called javac (java compiler). It reads your .java "
                + "file and writes a .class file. If you made a spelling or "
                + "grammar mistake, javac refuses and tells you. Nothing runs.\n"
                + "\n"
                + "STEP 3 - The JVM runs the bytecode.\n"
                + "JVM means Java Virtual Machine. It reads the .class file and "
                + "actually does the work. The tool is called java.\n"
                + "\n"
                + "    Main.java  --javac-->  Main.class  --java-->  it runs\n"
                + "\n"
                + "Two names people mix up:\n"
                + "\n"
                + "    JDK   Java Development Kit. Everything needed to WRITE\n"
                + "          Java. Includes javac. You need this.\n"
                + "    JRE   Java Runtime Environment. Enough only to RUN Java\n"
                + "          that someone else compiled. No javac.\n"
                + "\n"
                + "That is why installing 'Java' is sometimes not enough. If you "
                + "have only a JRE, you can run Java programs and you cannot "
                + "build one.")
            .example(
                "// You type this, in a file called Main.java",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"NORTHSTAR ONLINE\");",
                "    }",
                "}")
            .exampleOutput("NORTHSTAR ONLINE")
            .lineByLine(
                new String[]{"Main.java",
                    "The source file. Plain text. This is the only part you write."},
                new String[]{"javac Main.java",
                    "The compiler reads your source and produces Main.class. If it "
                    + "reports errors, no .class file is made and nothing can run."},
                new String[]{"Main.class",
                    "Bytecode. Not readable by you, not machine code either. It is "
                    + "instructions for the JVM."},
                new String[]{"java Main",
                    "The JVM loads Main.class and runs it. Note there is no .class "
                    + "on the end of the command."})
            .predict(new Task(Task.CHOICE,
                    "You edit Main.java and run  java Main  straight away, without "
                    + "compiling. What happens?")
                .choices(
                    "The new version runs, because java reads the source file",
                    "The OLD version runs, because Main.class was never rebuilt",
                    "Java automatically recompiles before running",
                    "It refuses to run at all")
                .accept("2", "b")
                .hints(
                    "Which file does the java command actually read?",
                    "java reads Main.class. Editing Main.java does not change "
                    + "Main.class until javac runs again.")
                .explain(
                    "java reads the .class file, not your source. Until javac runs "
                    + "again, the .class file still holds the old version.\n\n"
                    + "This catches everybody at least once: you fix a bug, run it, "
                    + "see the bug again, and conclude your fix did not work. "
                    + "Recompile.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "You have installed something called Java, but typing  javac "
                    + "-version  says the command is not found. What is most likely?")
                .choices(
                    "Java is broken and needs reinstalling",
                    "You installed a JRE, which can run Java but not compile it",
                    "javac is only available on Linux",
                    "You need to restart the computer")
                .accept("2", "b")
                .hints(
                    "Which of the two kits contains the compiler?",
                    "The JDK contains javac. The JRE does not.")
                .explain(
                    "A JRE runs Java that someone else built. Only the JDK can "
                    + "compile. This is the single most common setup problem "
                    + "there is.")
                .xp(15))
            .objective(
                "Show that you can follow the three steps in the right order.")
            .yourTask(
                "In the correct order, which command comes FIRST when you want to "
                + "run a program you have just written?\n\n"
                + "Type the whole command you would use to compile Main.java.")
            .mainTask(new Task(Task.WRITE,
                    "Type the command that compiles Main.java.")
                .accept("javac Main.java")
                .hints(
                    "The compiler's name is javac.",
                    "You give it the name of the source file, including .java")
                .solution("javac Main.java")
                .whyItWorks(
                    "javac is the compiler. You hand it the SOURCE file, with the "
                    + ".java on the end, because that is the file it reads.\n\n"
                    + "Afterwards you run the result with  java Main  - no .java "
                    + "and no .class, because there you are naming the CLASS, not a "
                    + "file.")
                .explain(
                    "Compile first, run second. The compiler takes the .java file; "
                    + "the JVM takes the class name.")
                .xp(20))
            .mistakes(
                new String[]{"Running  java Main.java",
                    "This names a file where a class name belongs. Modern Java will "
                    + "sometimes accept it for a single file, which makes it more "
                    + "confusing, not less. The reliable habit is: javac takes the "
                    + "file, java takes the class name."},
                new String[]{"Editing the source and not recompiling",
                    "The .class file still holds the old version, so you see the old "
                    + "behaviour and conclude your change did nothing."},
                new String[]{"Installing a JRE and expecting to compile",
                    "No javac. Install a JDK."})
            .cyber(
                "Why a security analyst should care about this:\n"
                + "\n"
                + "Most enterprise security tooling is Java. SIEM platforms, log "
                + "processors, agents on servers. You will be asked to read, "
                + "patch and run them.\n"
                + "\n"
                + "The compile step also matters for a second reason. A .class "
                + "file is not the source. When you are handed a Java program to "
                + "assess, what you receive is often bytecode, and what it does "
                + "is not obvious by looking. Knowing that source and bytecode are "
                + "different things is where Java malware analysis starts.")
            .check(new Task(Task.RECALL,
                    "What is the name of the tool that compiles Java source code?")
                .accept("javac")
                .hints("It is short for java compiler.",
                       "Four letters, all lowercase.")
                .explain("javac reads .java and writes .class.")
                .xp(10))
            .check(new Task(Task.RECALL,
                    "What does JVM stand for?")
                .accept("java virtual machine")
                .hints("Three words, and the J is Java.",
                       "Java Virtual ...")
                .explain(
                    "The Java Virtual Machine runs the bytecode. It is what makes "
                    + "the same .class file work on Windows, Mac and Linux without "
                    + "being rebuilt.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which file does javac PRODUCE?")
                .choices("Main.java", "Main.class", "Main.exe", "Main.txt")
                .accept("2", "b")
                .hints("It takes the source in and puts something else out.",
                       "The output ends in .class")
                .explain("javac reads Main.java and writes Main.class.")
                .xp(10))
            .recap(
                "Today you learned what actually happens when Java runs.\n"
                + "\n"
                + "    source (.java) -> javac -> bytecode (.class) -> JVM\n"
                + "\n"
                + "You also learned the difference between the JDK, which can "
                + "compile, and the JRE, which cannot.\n"
                + "\n"
                + "The habit worth keeping: after every edit, compile before you "
                + "run. When a change seems to have no effect, that is the first "
                + "thing to check.")
            .next("Next you write your first actual program."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(2), "Your First Program", 1)
            .brief(
                "Every tool on this desk starts from the same few lines. Before "
                + "you can change one, you need to recognise that skeleton and "
                + "know what each part of it is for.")
            .willLearn("class", "main method", "System.out.println", "Program structure")
            .whyUseful(
                "Java will not let you write loose instructions in a file. "
                + "Everything lives inside a class, and the program starts inside "
                + "a method called main. Until that shape is automatic, it gets in "
                + "the way of everything else you are trying to learn.")
            .concept("The shape of a Java program",
                "A Java program is built from two wrappers and then your "
                + "instructions.\n"
                + "\n"
                + "    public class Main {\n"
                + "        public static void main(String[] args) {\n"
                + "            // your instructions go here\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "THE CLASS is the outer container. Every line of Java code lives "
                + "inside one. The class here is called Main, and the file must be "
                + "called Main.java to match. Java enforces that.\n"
                + "\n"
                + "THE MAIN METHOD is where the JVM starts. When you run a program, "
                + "the JVM looks for a method with exactly this shape and begins "
                + "there. Not near it. Exactly it.\n"
                + "\n"
                + "To print a line, use:\n"
                + "\n"
                + "    System.out.println(\"some text\");\n"
                + "\n"
                + "Capitals matter everywhere in Java. System is a real thing; "
                + "system is not.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"TERMINAL READY\");",
                "    }",
                "}")
            .exampleOutput("TERMINAL READY")
            .lineByLine(
                new String[]{"public class Main {",
                    "Opens the class. public means other things can see it. class "
                    + "means this is a class. Main is its name, and the file must be "
                    + "Main.java. The { opens the body."},
                new String[]{"public static void main(String[] args) {",
                    "The starting point. public so the JVM outside can see it. "
                    + "static so it runs without building anything first. void "
                    + "because it hands nothing back. main is the exact name the JVM "
                    + "looks for. String[] args holds anything typed after the "
                    + "program name. You will not use it for a long time; it still "
                    + "has to be there."},
                new String[]{"System.out.println(\"TERMINAL READY\");",
                    "System is a toolbox Java provides. out is the way to your "
                    + "screen. println prints the text then moves to the next line. "
                    + "The text sits in double quotes. The semicolon ends the "
                    + "instruction."},
                new String[]{"}  }",
                    "The first } closes main, the second closes the class. Every { "
                    + "needs its partner."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"ACCESS GRANTED\");",
                    "    }",
                    "}")
                .accept("ACCESS GRANTED")
                .hints(
                    "Look only at what is between the double quotes.",
                    "Whatever is in the quotes is printed exactly as written.")
                .explain(
                    "The text inside the quotes comes out exactly. Java adds "
                    + "nothing, removes nothing, and does not change your capitals.")
                .xp(10))
            .practice(new Task(Task.CHOICE,
                    "Your class is called Main. What must the file be called?")
                .choices("main.java", "Main.java", "Main.class", "anything you like")
                .accept("2", "b")
                .hints(
                    "The file name has to match the public class name.",
                    "Including the capital letter.")
                .explain(
                    "A public class must live in a file with exactly its own name, "
                    + "capital included, plus .java. Name it main.java and javac "
                    + "refuses before it reads a single instruction.")
                .xp(10))
            .objective(
                "Print a status line from inside a working program.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // print SYSTEM ONLINE here",
                "    }",
                "}")
            .yourTask(
                "Write the one line that goes where the comment is, so the program "
                + "prints:\n\n"
                + "    SYSTEM ONLINE\n\n"
                + "Type the whole statement, semicolon included.")
            .mainTask(new Task(Task.WRITE,
                    "Type the line that prints SYSTEM ONLINE.")
                .accept(
                    "System.out.println(\"SYSTEM ONLINE\");",
                    "System.out.println(\"SYSTEM ONLINE\")")
                .hints(
                    "Start with System.out.println and open a round bracket.",
                    "The text goes in double quotes inside the brackets.",
                    "Close the bracket, then end the statement with a semicolon.")
                .solution("System.out.println(\"SYSTEM ONLINE\");")
                .whyItWorks(
                    "System.out is the path to your screen. println sends the text "
                    + "there and then moves to a new line.\n"
                    + "\n"
                    + "The double quotes mark where the text starts and stops, so "
                    + "Java knows SYSTEM ONLINE is text to print rather than "
                    + "instructions to follow.\n"
                    + "\n"
                    + "The semicolon tells Java the instruction is finished. Java "
                    + "ignores line breaks entirely, so without it the compiler "
                    + "keeps reading onto the next line and then complains.")
                .explain(
                    "That is the instruction you will write more than any other.")
                .xp(25))
            .mistakes(
                new String[]{"system.out.println(...)",
                    "Lowercase s. System is a class name and class names start with "
                    + "a capital. The compiler says 'cannot find symbol'."},
                new String[]{"System.out.printLine(...)",
                    "There is no such method. It is println - print line, shortened. "
                    + "Also 'cannot find symbol'."},
                new String[]{"Forgetting the semicolon",
                    "The compiler says \"';' expected\" and often points at the NEXT "
                    + "line, because that is where it finally realised something was "
                    + "missing."})
            .cyber(
                "Every security tool you meet reports what it found, and this is "
                + "how the simplest ones do it.\n"
                + "\n"
                + "A scanner that detects a problem and reports it badly is a "
                + "scanner nobody acts on. Output is not an afterthought in "
                + "security work - it is the entire product of the tool. The "
                + "detection happens in memory and vanishes; what survives is the "
                + "line someone reads.")
            .check(new Task(Task.CHOICE,
                    "Which method name is correct?")
                .choices("printline", "printLine", "println", "printLn")
                .accept("3", "c")
                .hints("It is short for print line.",
                       "All lowercase, no capital in the middle.")
                .explain(
                    "println - all lowercase. Java has no printLine and no "
                    + "Println. A wrong capital gives you cannot find symbol, "
                    + "which is the compiler saying it looked and there is no "
                    + "such method.")
                .xp(10))
            .check(new Task(Task.RECALL,
                    "What character ends a Java statement?")
                .accept(";", "semicolon", "a semicolon")
                .hints("It is a single punctuation mark.",
                       "It looks like a full stop with a comma above it.")
                .explain(
                    "The semicolon. Java does not care about line breaks, so this "
                    + "is the only thing marking the end of an instruction.")
                .xp(10))
            .check(new Task(Task.DEBUG,
                    "Which line number stops this compiling?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"SCAN START\")",
                    "    }",
                    "}")
                .accept("3", "line 3")
                .hints("Compare it with the example you were shown.",
                       "Something is missing from the end of one line.")
                .explain(
                    "Line 3 has no semicolon. The compiler will probably point at "
                    + "line 4, because that is where it gave up - but the fix "
                    + "belongs on line 3.")
                .xp(15))
            .recap(
                "You now know the skeleton every Java program has:\n"
                + "\n"
                + "    public class Main {\n"
                + "        public static void main(String[] args) {\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "and the instruction that prints a line:\n"
                + "\n"
                + "    System.out.println(\"text\");\n"
                + "\n"
                + "The file must be named after the public class. Capitals matter. "
                + "Statements end in semicolons.")
            .next("Next: compiling and running it yourself, and what it looks "
                + "like when that goes wrong."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(3), "Compile, Run, Repeat", 2)
            .brief(
                "A colleague has sent you a patched version of a log tool and "
                + "asked you to test it. Knowing the edit-compile-run cycle, and "
                + "where each step can fail, is the difference between testing it "
                + "and wasting an afternoon.")
            .willLearn("javac", "java", "The edit-compile-run cycle",
                       "Compile time vs run time")
            .whyUseful(
                "There are two completely different kinds of failure in Java, and "
                + "they need different responses. Telling them apart immediately is "
                + "one of the most useful habits a beginner can build.")
            .concept("The cycle, and its two kinds of failure",
                "Working on Java code is a loop of three actions:\n"
                + "\n"
                + "    1. EDIT     change the .java file\n"
                + "    2. COMPILE  javac -d out src/Main.java\n"
                + "    3. RUN      java -cp out Main\n"
                + "\n"
                + "The -d out part tells javac to put the .class files in a folder "
                + "called out. The -cp out part tells java to look for classes "
                + "there. cp is short for classpath.\n"
                + "\n"
                + "Failures come in two kinds, and which one you have tells you "
                + "where to look.\n"
                + "\n"
                + "COMPILE-TIME FAILURE\n"
                + "javac refuses. Nothing runs at all. The message names a file and "
                + "a line number. Misspelling, missing semicolon, missing brace.\n"
                + "\n"
                + "RUN-TIME FAILURE\n"
                + "It compiled fine, started running, and then something went wrong "
                + "partway through. Java prints an exception and a stack trace.\n"
                + "\n"
                + "The test is simple: did anything print before it broke? If yes, "
                + "it ran, so it was a run-time problem.")
            .example(
                "// in the project folder",
                "javac -d out src/Main.java",
                "java -cp out Main")
            .exampleOutput("SYSTEM ONLINE")
            .lineByLine(
                new String[]{"javac -d out src/Main.java",
                    "Compile the file src/Main.java and put the resulting .class "
                    + "file in the out folder. Silence means it worked; javac says "
                    + "nothing when it succeeds."},
                new String[]{"java -cp out Main",
                    "Run the class called Main, looking for it in out. Note: Main, "
                    + "not Main.class and not Main.java. Here you are naming the "
                    + "class, not a file."})
            .predict(new Task(Task.CHOICE,
                    "You run a program. It prints three lines and then stops with "
                    + "an error message mentioning an exception. Which kind of "
                    + "failure is this?")
                .choices(
                    "Compile-time: javac rejected the code",
                    "Run-time: it compiled and started, then hit a problem",
                    "Both at once",
                    "Neither, that is normal")
                .accept("2", "b")
                .hints(
                    "Did anything print before it failed?",
                    "If output appeared, the program was already running - so javac "
                    + "must have accepted it.")
                .explain(
                    "Output appeared, so it compiled and started. That makes it a "
                    + "run-time failure.\n\n"
                    + "This distinction narrows your search immediately. A "
                    + "compile-time error is a typing or grammar problem; a run-time "
                    + "error is usually about the DATA the program met.")
                .xp(20))
            .practice(new Task(Task.CHOICE,
                    "javac prints nothing at all after you run it. What happened?")
                .choices(
                    "It failed silently",
                    "It succeeded - javac says nothing when there is nothing wrong",
                    "It is still working",
                    "The file was empty")
                .accept("2", "b")
                .hints(
                    "Think about what a compiler has to say when everything is fine.",
                    "Silence is success. Errors would have been printed.")
                .explain(
                    "javac only speaks up when something is wrong. No output means "
                    + "your .class file was written and you can run it.")
                .xp(10))
            .objective(
                "Run a class you have already compiled into the out folder.")
            .starter(
                "// you have already run:",
                "javac -d out src/Main.java",
                "",
                "// now run it")
            .yourTask(
                "Type the command that RUNS the compiled Main class, looking for "
                + "classes in the out folder.\n\n"
                + "Remember: here you name the class, not a file.")
            .mainTask(new Task(Task.WRITE,
                    "Type the command that runs the program.")
                .accept("java -cp out Main")
                .hints(
                    "The tool is java, not javac - you are running, not compiling.",
                    "-cp out tells it where to look for the class files.",
                    "Then the CLASS name, with no file extension at all.")
                .solution("java -cp out Main")
                .whyItWorks(
                    "java starts the JVM.\n"
                    + "\n"
                    + "-cp out sets the classpath - the list of places the JVM "
                    + "searches for classes. Your Main.class is in out, so that is "
                    + "where it must look.\n"
                    + "\n"
                    + "Main is the CLASS name. No .class on the end, because you are "
                    + "not naming a file - you are telling the JVM which class holds "
                    + "the main method it should start from. The JVM works out the "
                    + "filename itself.")
                .explain(
                    "javac takes a file. java takes a class name. That asymmetry "
                    + "trips up nearly everyone at first.")
                .xp(25))
            .mistakes(
                new String[]{"java -cp out Main.class",
                    "Adding .class turns a class name into something that looks like "
                    + "a file, and the JVM then looks for a class called 'class' "
                    + "inside a package called 'Main'. The error is confusing "
                    + "because it is technically answering a different question."},
                new String[]{"Forgetting -cp out",
                    "The JVM looks in the current folder, does not find Main.class, "
                    + "and says 'Could not find or load main class Main'."},
                new String[]{"Assuming an error means your logic is wrong",
                    "Check first WHICH kind of error it is. A compile error means "
                    + "nothing ran, so your logic has not even been tested yet."})
            .cyber(
                "Being able to build and run someone else's Java from source is a "
                + "practical security skill.\n"
                + "\n"
                + "When a vulnerability is published with proof-of-concept code, it "
                + "arrives as source. Reproducing an issue in a lab - safely, on "
                + "systems you own - means compiling and running it yourself.\n"
                + "\n"
                + "It matters in the other direction too. If you cannot rebuild a "
                + "tool from its source, you cannot be sure the binary you were "
                + "handed matches the source you reviewed. That gap is exactly "
                + "where supply chain attacks live.")
            .check(new Task(Task.RECALL,
                    "Which command compiles: java or javac?")
                .accept("javac")
                .hints("One of them has an extra letter on the end.",
                       "The c stands for compiler.")
                .explain(
                    "javac compiles, java runs. The c on the end is for "
                    + "compiler. javac takes the .java FILE; java takes the "
                    + "CLASS NAME with no extension at all.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "A program prints nothing and reports an error naming line 7. "
                    + "Which is it?")
                .choices("Run-time failure", "Compile-time failure",
                         "Impossible to tell", "A warning, safe to ignore")
                .accept("2", "b")
                .hints("Did any of the program's own output appear?",
                       "Nothing printed, and a line number was named.")
                .explain(
                    "Nothing printed and a source line was named, so javac rejected "
                    + "it before anything ran. Compile-time.")
                .xp(15))
            .recap(
                "The cycle is edit, compile, run - and you go round it constantly.\n"
                + "\n"
                + "    javac -d out src/Main.java     compile\n"
                + "    java -cp out Main              run\n"
                + "\n"
                + "javac takes a FILE. java takes a CLASS NAME.\n"
                + "\n"
                + "Two kinds of failure: compile-time, where nothing runs, and "
                + "run-time, where it started and then broke. Ask whether anything "
                + "printed before the error - that tells you which one you have.")
            .next("Next: leaving notes in code that Java ignores."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(4), "Notes Java Ignores", 1)
            .brief(
                "The log tool you inherited has no explanation in it anywhere. "
                + "The analyst who wrote it left two years ago.\n\n"
                + "Comments are how code explains itself to the next person, who "
                + "is very often you in three weeks.")
            .willLearn("Comments", "Line and block comments", "Commenting out code")
            .whyUseful(
                "Code says WHAT it does. Only a comment can say WHY. In security "
                + "work the why is usually the important part: why this threshold, "
                + "why this address is excluded, which incident caused this rule.")
            .concept("Comments",
                "A comment is text in your source that Java ignores completely. "
                + "It is there for humans.\n"
                + "\n"
                + "There are two forms.\n"
                + "\n"
                + "LINE COMMENT - two forward slashes. Everything after them on "
                + "that line is ignored.\n"
                + "\n"
                + "    // this whole line is ignored\n"
                + "    System.out.println(\"HI\");   // and this bit too\n"
                + "\n"
                + "BLOCK COMMENT - starts with /* and ends with */. It can cover "
                + "as many lines as you like.\n"
                + "\n"
                + "    /* This is ignored,\n"
                + "       and so is this. */\n"
                + "\n"
                + "The second, very common use of a block comment is switching "
                + "code off without deleting it:\n"
                + "\n"
                + "    /* System.out.println(\"DEBUG\"); */\n"
                + "\n"
                + "The line is still in the file. It simply never runs.\n"
                + "\n"
                + "A comment never produces output. If you expect a comment to "
                + "print something, you have misunderstood what it is for.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // alert the operator that the scan began",
                "        System.out.println(\"SCAN START\");",
                "        /* System.out.println(\"DEBUG MODE\"); */",
                "    }",
                "}")
            .exampleOutput("SCAN START")
            .lineByLine(
                new String[]{"// alert the operator that the scan began",
                    "A line comment. Java skips it entirely. Note it says WHY the "
                    + "line below exists, not what it does - 'print SCAN START' "
                    + "would be useless, because the code already says that."},
                new String[]{"/* System.out.println(\"DEBUG MODE\"); */",
                    "A real instruction wrapped in a block comment, so it never "
                    + "runs. The code is kept in case it is wanted again."})
            .predict(new Task(Task.PREDICT,
                    "How many lines does this print?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        // System.out.println(\"ONE\");",
                    "        System.out.println(\"TWO\");",
                    "        /* System.out.println(\"THREE\"); */",
                    "    }",
                    "}")
                .accept("1", "one")
                .hints(
                    "Two of those three lines are commented out.",
                    "Only the line with no // and no /* */ actually runs.")
                .explain(
                    "Only TWO prints. The other two lines are inside comments, so "
                    + "Java never sees them as instructions at all.\n\n"
                    + "This is exactly how a debug line gets left switched off by "
                    + "accident and someone spends an hour wondering why their "
                    + "logging vanished.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Which of these is a GOOD comment?")
                .choices(
                    "// print the username",
                    "// threshold is 5 because policy PS-114 requires lockout at 5",
                    "// this is a variable",
                    "// System.out.println")
                .accept("2", "b")
                .hints(
                    "Which one tells you something the code cannot tell you itself?",
                    "The code already shows what it does. Only one of these explains "
                    + "why.")
                .explain(
                    "The code already says it prints the username. What the code "
                    + "cannot say is why the threshold is 5 - that came from a "
                    + "policy document, and without the comment the next person "
                    + "changes it and breaks compliance without knowing.")
                .xp(15))
            .objective(
                "Silence a debug line without deleting it, and explain why the "
                + "remaining line is there.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"AUDIT START\");",
                "        System.out.println(\"DEBUG: entering loop\");",
                "    }",
                "}")
            .yourTask(
                "The DEBUG line should not run in production, but the next analyst "
                + "may want it back.\n\n"
                + "What do you wrap around that line so it stays in the file but "
                + "never runs? Type the two characters that OPEN a block comment.")
            .mainTask(new Task(Task.WRITE,
                    "Type the two characters that open a block comment.")
                .accept("/*")
                .hints(
                    "It is a forward slash and one other character.",
                    "The closing form is */ - so what is the opening form?")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"AUDIT START\");",
                    "        /* System.out.println(\"DEBUG: entering loop\"); */",
                    "    }",
                    "}")
                .whyItWorks(
                    "Everything between /* and */ is invisible to Java. The debug "
                    + "line is still sitting in the file where the next person can "
                    + "see it and switch it back on, but it produces no output.\n"
                    + "\n"
                    + "Deleting it would also have stopped the output, and would "
                    + "have thrown away the knowledge of what was once worth "
                    + "logging.")
                .explain(
                    "/* opens, */ closes. Forget the closing one and Java swallows "
                    + "the rest of your file, giving the baffling error 'reached "
                    + "end of file while parsing'.")
                .xp(20))
            .mistakes(
                new String[]{"Forgetting the closing */",
                    "Everything after /* is treated as a comment until a */ is "
                    + "found. If there is none, the whole rest of the file "
                    + "disappears and you get 'reached end of file while parsing'."},
                new String[]{"Comments that restate the code",
                    "// add one to the counter, above counter++, adds nothing. Say "
                    + "why, not what."},
                new String[]{"Leaving commented-out code for months",
                    "A file full of switched-off code is hard to read. Comment out "
                    + "to test something, then decide: keep it or delete it."})
            .cyber(
                "Comments carry the reasoning behind a security control.\n"
                + "\n"
                + "A threshold of 5, an address excluded from alerting, a check "
                + "that looks redundant - each of those was a decision, usually "
                + "after an incident. Without a comment the reason dies with "
                + "whoever left, and the next person removes the control because "
                + "it looks pointless.\n"
                + "\n"
                + "One warning. Comments are part of the source code, so never put "
                + "a password, key or token in one. People do, and source code "
                + "ends up in places you did not plan for.")
            .check(new Task(Task.RECALL,
                    "What two characters start a line comment?")
                .accept("//")
                .hints("The same character twice.", "A forward slash, doubled.")
                .explain("// ignores the rest of that line.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Should a password ever be written in a comment?")
                .choices(
                    "Yes, comments are not compiled so they are safe",
                    "No - a comment is still part of the source file and travels "
                    + "everywhere the source goes",
                    "Only if the file is private",
                    "Only in test code")
                .accept("2", "b")
                .hints(
                    "Where does source code end up? Repositories, backups, shared "
                    + "folders.",
                    "The comment is not compiled, but the FILE still contains it.")
                .explain(
                    "The comment lives in the source file, and source files get "
                    + "copied, committed, backed up and shared. Credentials in "
                    + "comments are a routine finding in real code audits.")
                .xp(15))
            .recap(
                "Comments are notes Java ignores.\n"
                + "\n"
                + "    //            ignores the rest of the line\n"
                + "    /* ... */     ignores everything between, over any\n"
                + "                  number of lines, and is how you switch\n"
                + "                  code off\n"
                + "\n"
                + "Write comments that say WHY. The code already says what.\n"
                + "\n"
                + "Never put a secret in one.")
            .next("Next: why the semicolon matters more than the line break."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(5), "Where a Statement Ends", 2)
            .brief(
                "A patch you were sent will not compile. The error names a line "
                + "that looks completely fine.\n\n"
                + "Understanding where Java thinks one instruction stops and the "
                + "next begins will explain it.")
            .willLearn("Statements", "Semicolons", "Why Java ignores line breaks")
            .whyUseful(
                "The 'missing semicolon' error usually points at the wrong line, "
                + "and that confuses people for months. Once you know why, the "
                + "error becomes easy to read.")
            .concept("Statements and semicolons",
                "A STATEMENT is one complete instruction. In Java, a statement "
                + "ends at a SEMICOLON - not at the end of the line.\n"
                + "\n"
                + "Java does not care about line breaks at all. These two are "
                + "identical to the compiler:\n"
                + "\n"
                + "    System.out.println(\"A\"); System.out.println(\"B\");\n"
                + "\n"
                + "    System.out.println(\"A\");\n"
                + "    System.out.println(\"B\");\n"
                + "\n"
                + "Both are two statements and both print two lines.\n"
                + "\n"
                + "So why put one statement per line? Because errors are reported "
                + "by line number. Three statements crammed onto line 4 means "
                + "'error on line 4' tells you almost nothing.\n"
                + "\n"
                + "Here is the part that confuses everyone. When you leave out a "
                + "semicolon, Java does not stop at the end of the line - it keeps "
                + "reading onto the NEXT line looking for the end of your "
                + "statement. It only complains when what it finds makes no sense.\n"
                + "\n"
                + "That is why the reported line number is often one MORE than the "
                + "line you need to fix.")
            .example(
                "System.out.println(\"FIRST\")",
                "System.out.println(\"SECOND\");")
            .exampleOutput("(does not compile)")
            .lineByLine(
                new String[]{"System.out.println(\"FIRST\")",
                    "No semicolon. Java does not stop here. It carries on reading, "
                    + "still trying to finish this one statement."},
                new String[]{"System.out.println(\"SECOND\");",
                    "Java arrives here still expecting the end of the previous "
                    + "statement, finds the start of a new one instead, and gives "
                    + "up. The error usually names THIS line - but the fix belongs "
                    + "on the line above."})
            .predict(new Task(Task.PREDICT,
                    "How many lines of output does this produce?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"ALPHA\"); System.out.println(\"BRAVO\");",
                    "    }",
                    "}")
                .accept("2", "two")
                .hints(
                    "Count the semicolons, not the lines.",
                    "There are two complete statements on that one source line.")
                .explain(
                    "Two statements, so two lines of output, even though they share "
                    + "one line of source. The semicolon is the boundary; the line "
                    + "break means nothing to Java.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "javac reports a missing semicolon on line 9. Where is the fix "
                    + "most likely to belong?")
                .choices(
                    "Line 9",
                    "Line 8 - Java read past the end of it looking for the semicolon",
                    "Line 10",
                    "Anywhere in the file")
                .accept("2", "b")
                .hints(
                    "Java keeps reading past a missing semicolon onto the next line.",
                    "It reports where it gave up, not where the problem started.")
                .explain(
                    "The error names where Java noticed, which is usually one line "
                    + "after the line that is actually missing something. When a "
                    + "semicolon error points at a line that looks perfect, check "
                    + "the line above it.")
                .xp(20))
            .objective(
                "Prove you know where a statement really ends.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"HOST CHECK\")",
                "        System.out.println(\"COMPLETE\");",
                "    }",
                "}")
            .yourTask(
                "This will not compile. javac will probably name line 4.\n\n"
                + "Which line number actually needs fixing? Type just the number.")
            .mainTask(new Task(Task.DEBUG,
                    "Which line number needs the fix?")
                .accept("3", "line 3")
                .hints(
                    "Look for the statement that never ends.",
                    "One of the two println lines has no semicolon.",
                    "Java reports where it gave up, which is a line later than the "
                    + "problem.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"HOST CHECK\");",
                    "        System.out.println(\"COMPLETE\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "Line 3 had no semicolon, so Java kept reading onto line 4 "
                    + "still trying to finish that statement. On line 4 it found "
                    + "the start of a new instruction where it expected an ending, "
                    + "and reported the error there.\n"
                    + "\n"
                    + "Adding the semicolon to line 3 closes the statement, and "
                    + "line 4 is then read as the separate instruction it always "
                    + "was.")
                .explain(
                    "Line 3 is missing its semicolon. The error message points at "
                    + "line 4 because that is where Java finally realised.")
                .xp(25))
            .mistakes(
                new String[]{"Trusting the reported line number exactly",
                    "For a missing semicolon it is usually one line late. Check the "
                    + "line above before you change anything."},
                new String[]{"Adding a semicolon after every brace",
                    "A { } block does not take one. if (x) { } ; is legal and "
                    + "almost never what you meant."},
                new String[]{"Fixing errors from the bottom up",
                    "Always fix the FIRST error and recompile. One missing "
                    + "semicolon can invent a dozen fake errors below it that "
                    + "vanish on their own."})
            .cyber(
                "This is a debugging habit, and debugging is most of security "
                + "engineering.\n"
                + "\n"
                + "When a monitoring tool fails at 3am, you will be reading an "
                + "error message written by a machine that is describing where it "
                + "noticed a problem, not where the problem is. Knowing the "
                + "difference between those two things - the symptom and the cause "
                + "- is the whole skill, and it starts here with a semicolon.")
            .check(new Task(Task.CHOICE,
                    "What ends a statement in Java?")
                .choices("The end of the line", "A semicolon", "A full stop",
                         "A closing brace")
                .accept("2", "b")
                .hints("Java ignores line breaks entirely.",
                       "It is a punctuation mark you type yourself.")
                .explain(
                    "The semicolon, always. Java ignores line breaks "
                    + "completely, so the semicolon is the only thing marking "
                    + "where one instruction stops and the next begins.")
                .xp(10))
            .check(new Task(Task.RECALL,
                    "When you have several compiler errors, which one should you "
                    + "fix first? Answer: first or last.")
                .accept("first", "the first", "first one")
                .hints("One error can cause several others below it.",
                       "Fix the earliest one and recompile.")
                .explain(
                    "The first. Later errors are often consequences of it and "
                    + "disappear once it is fixed.")
                .xp(15))
            .recap(
                "A statement ends at a semicolon, not at the end of a line. Java "
                + "ignores line breaks completely.\n"
                + "\n"
                + "When a semicolon is missing, Java reads onto the next line and "
                + "reports the error THERE. The fix usually belongs one line "
                + "earlier.\n"
                + "\n"
                + "Fix the first error, then recompile.")
            .next("Next: printing that does not start a new line."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(6), "Staying on the Line", 2)
            .brief(
                "The report your tool produces puts every value on its own line. "
                + "Forty hosts becomes two hundred lines and nobody reads it.\n\n"
                + "Real reports put related facts on one row.")
            .willLearn("print vs println", "Building one line from pieces")
            .whyUseful(
                "A report a human can scan is worth more than a report that is "
                + "merely correct. Getting several values onto one row is the "
                + "difference between a table and a wall of text.")
            .concept("print and println",
                "You already know println. There is a second version.\n"
                + "\n"
                + "    System.out.println(\"text\")   prints, then moves to a\n"
                + "                                 new line\n"
                + "    System.out.print(\"text\")     prints, and stays where\n"
                + "                                 it is\n"
                + "\n"
                + "The ln on the end means 'line'. Without it, the cursor does not "
                + "move, so whatever you print next lands immediately after.\n"
                + "\n"
                + "That is how several statements build ONE line of output:\n"
                + "\n"
                + "    System.out.print(\"HOST: \");\n"
                + "    System.out.println(\"WEB-01\");\n"
                + "\n"
                + "gives one line: HOST: WEB-01\n"
                + "\n"
                + "Note the space after the colon is INSIDE the quotes. Nothing is "
                + "added for you. If you leave it out you get HOST:WEB-01.\n"
                + "\n"
                + "The last statement in a row should normally be println, to "
                + "close the line. If every statement is print, the line never "
                + "ends and the next thing your program writes joins onto it.")
            .example(
                "System.out.print(\"USER: \");",
                "System.out.print(\"jsmith\");",
                "System.out.println(\"  STATUS: locked\");")
            .exampleOutput("USER: jsmith  STATUS: locked")
            .lineByLine(
                new String[]{"System.out.print(\"USER: \");",
                    "Prints and stays put. The cursor is now sitting just after the "
                    + "space following the colon."},
                new String[]{"System.out.print(\"jsmith\");",
                    "Prints right where the cursor was, so it lands on the same "
                    + "line."},
                new String[]{"System.out.println(\"  STATUS: locked\");",
                    "Prints the rest and then ends the line. The two spaces at the "
                    + "start are inside the quotes, and are what separates the "
                    + "columns."})
            .predict(new Task(Task.PREDICT,
                    "What does this print? Write it exactly, on one line.")
                .code(
                    "System.out.print(\"ALERT\");",
                    "System.out.print(\"-\");",
                    "System.out.println(\"HIGH\");")
                .accept("ALERT-HIGH")
                .hints(
                    "Only the last statement ends the line.",
                    "The three pieces join with nothing between them except what is "
                    + "inside the quotes.")
                .explain(
                    "One line: ALERT-HIGH. Nothing is inserted between the pieces, "
                    + "because print adds nothing of its own - only what is inside "
                    + "your quotes reaches the screen.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "How many LINES does this produce?")
                .code(
                    "System.out.println(\"A\");",
                    "System.out.print(\"B\");",
                    "System.out.println(\"C\");")
                .accept("2", "two")
                .hints(
                    "Count the println statements, not the total statements.",
                    "A ends a line. Then B and C share the next one.")
                .explain(
                    "Two lines: A on the first, then BC together on the second. "
                    + "Only println ends a line, so the print in the middle left B "
                    + "and C sharing a row.")
                .xp(15))
            .objective(
                "Produce a single-line status row for a host.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // build one line: HOST: DB-01 STATUS: online",
                "    }",
                "}")
            .yourTask(
                "You want ONE line of output:\n\n"
                + "    HOST: DB-01 STATUS: online\n\n"
                + "Which method do you use for the FIRST piece, so the line is not "
                + "broken? Type just the method call as it would appear before the "
                + "brackets, for example  System.out.something")
            .mainTask(new Task(Task.WRITE,
                    "Type the method you use for the first piece: "
                    + "System.out.what?")
                .accept("System.out.print", "print")
                .hints(
                    "You do not want a new line after the first piece.",
                    "It is println without the ln.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.print(\"HOST: DB-01 \");",
                    "        System.out.println(\"STATUS: online\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "The first statement uses print, so after it runs the cursor is "
                    + "still on the same line, sitting just after the space.\n"
                    + "\n"
                    + "The second statement then prints in that spot, and because "
                    + "it is println it finishes the line properly.\n"
                    + "\n"
                    + "The space before the closing quote of the first piece is "
                    + "what separates DB-01 from STATUS. Leave it out and you get "
                    + "DB-01STATUS.")
                .explain(
                    "print keeps the cursor on the line. println closes it. A row "
                    + "usually ends with exactly one println.")
                .xp(20))
            .mistakes(
                new String[]{"Using println for every piece",
                    "Each one ends the line, so instead of a row you get a column."},
                new String[]{"Expecting a space between pieces",
                    "Nothing is added for you. Any space you want must be inside "
                    + "the quotes."},
                new String[]{"Never calling println at all",
                    "The line is never finished, so the next output - or your "
                    + "command prompt - joins onto the end of it."})
            .cyber(
                "Security output is read under pressure, often at night, often by "
                + "someone scanning for the one line that matters.\n"
                + "\n"
                + "One event per row, with the fields always in the same order, is "
                + "what makes a log skimmable. It is also what makes it machine "
                + "readable - every log analysis tool in existence assumes one "
                + "event per line.\n"
                + "\n"
                + "A tool that scatters one event across five lines cannot be "
                + "parsed by anything, including the next tool you write.")
            .check(new Task(Task.CHOICE,
                    "What does the ln in println mean?")
                .choices("long", "line", "listen", "length")
                .accept("2", "b")
                .hints("It describes what happens after the text is printed.",
                       "The cursor moves to a new one of these.")
                .explain("Line. println prints and then ends the line.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What exactly does this print?")
                .code(
                    "System.out.print(\"PORT:\");",
                    "System.out.println(\"443\");")
                .accept("PORT:443")
                .hints("Is there a space inside either pair of quotes?",
                       "No space was written, so no space appears.")
                .explain(
                    "PORT:443 with no gap. The space has to be inside the quotes; "
                    + "print will not invent one.")
                .xp(15))
            .recap(
                "    println    prints and ends the line\n"
                + "    print      prints and stays on the line\n"
                + "\n"
                + "Several print statements build one row, and a final println "
                + "closes it.\n"
                + "\n"
                + "Spaces must be inside your quotes. Nothing is added for you.")
            .next("Next: characters you cannot simply type."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(7), "Characters You Cannot Type", 3)
            .brief(
                "The incident report needs quotation marks around the attacker's "
                + "username, and the columns need to line up.\n\n"
                + "Neither can be done by pressing the obvious key.")
            .willLearn("Escape sequences", "\\n", "\\t", "\\\"")
            .whyUseful(
                "Quotation marks end a string, so printing one needs a trick. Tabs "
                + "line up columns without counting spaces. Both appear constantly "
                + "in report output.")
            .concept("Escape sequences",
                "Some characters cannot be typed directly inside a string.\n"
                + "\n"
                + "A double quote is the obvious problem. This does not compile:\n"
                + "\n"
                + "    System.out.println(\"He said stop\" and left\");\n"
                + "\n"
                + "Java sees the string end at the quote before 'and', then finds "
                + "nonsense afterwards.\n"
                + "\n"
                + "The answer is an ESCAPE SEQUENCE: a backslash followed by one "
                + "character. The backslash means 'treat the next character "
                + "specially'.\n"
                + "\n"
                + "    \\\"     a double quote that is part of the text\n"
                + "    \\n     a new line, in the middle of a string\n"
                + "    \\t     a tab, which jumps to the next tab stop\n"
                + "    \\\\     a single actual backslash\n"
                + "\n"
                + "Each of these is TWO characters in your source and ONE character "
                + "in the output.\n"
                + "\n"
                + "\\n is worth noticing: it lets one println produce several "
                + "lines.\n"
                + "\n"
                + "    System.out.println(\"A\\nB\");\n"
                + "\n"
                + "prints A, then B, on two lines - from one statement.\n"
                + "\n"
                + "It must be a BACKSLASH. A forward slash does nothing special; "
                + "\"/n\" just prints the two characters / and n.")
            .example(
                "System.out.println(\"USER\\tRESULT\");",
                "System.out.println(\"jsmith\\tFAIL\");",
                "System.out.println(\"DC-01 reported \\\"locked\\\"\");")
            .exampleOutput(
                "USER    RESULT",
                "jsmith  FAIL",
                "DC-01 reported \"locked\"")
            .lineByLine(
                new String[]{"\\t",
                    "A tab. It jumps to the next tab stop, which is how the two "
                    + "columns line up even though USER and jsmith are different "
                    + "lengths."},
                new String[]{"\\\"",
                    "A double quote that is part of the text rather than the end of "
                    + "the string. Note the final \" on that line is the real one, "
                    + "closing the string."})
            .predict(new Task(Task.PREDICT,
                    "How many LINES does this print?")
                .code("System.out.println(\"ALPHA\\nBRAVO\\nCHARLIE\");")
                .accept("3", "three")
                .hints(
                    "\\n is a line break inside the string.",
                    "Count the pieces the two \\n characters split the text into.")
                .explain(
                    "Three lines from one statement. Each \\n is a line break "
                    + "inside the text, and println adds one more at the end.")
                .xp(20))
            .practice(new Task(Task.CHOICE,
                    "Which prints:   He typed \"exit\"")
                .choices(
                    "System.out.println(\"He typed \"exit\"\");",
                    "System.out.println(\"He typed \\\"exit\\\"\");",
                    "System.out.println(\"He typed /\"exit/\"\");",
                    "System.out.println(He typed \"exit\");")
                .accept("2", "b")
                .hints(
                    "The quotes that should appear in the output need escaping.",
                    "A backslash before each one, not a forward slash.")
                .explain(
                    "Each quote that is part of the text gets a backslash. The "
                    + "first option ends the string early and will not compile; the "
                    + "third uses forward slashes, which mean nothing to Java.")
                .xp(20))
            .objective(
                "Produce a two-column report header.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // print:  HOST    STATUS   with the columns aligned",
                "    }",
                "}")
            .yourTask(
                "You want HOST and STATUS separated so that a column of values "
                + "underneath will line up.\n\n"
                + "Type the two characters that produce a tab.")
            .mainTask(new Task(Task.WRITE,
                    "Type the escape sequence for a tab.")
                .accept("\\t")
                .hints(
                    "It is a backslash and one letter.",
                    "The letter is the first letter of the word tab.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"HOST\\tSTATUS\");",
                    "        System.out.println(\"WEB-01\\tonline\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "\\t is a single tab character written as two characters in "
                    + "your source.\n"
                    + "\n"
                    + "A tab jumps to the next fixed tab stop rather than moving a "
                    + "fixed number of spaces. That is why the columns line up even "
                    + "though HOST is four characters and WEB-01 is six.\n"
                    + "\n"
                    + "Typing spaces by hand would need a different number on every "
                    + "row, and would break the moment a value changed length.")
                .explain(
                    "\\t is a tab. Backslash, then t. Two characters in the source, "
                    + "one in the output.")
                .xp(20))
            .mistakes(
                new String[]{"Using a forward slash",
                    "/n and /t mean nothing. Only the backslash starts an escape "
                    + "sequence, and the wrong one prints literally."},
                new String[]{"Expecting \\t to be a fixed number of spaces",
                    "It moves to the next tab stop, so how far it travels depends "
                    + "on where the cursor already was. That is what makes it line "
                    + "columns up."},
                new String[]{"Forgetting that \\\" still needs a real closing quote",
                    "\"He said \\\"hi\\\"\" has four quote characters: two escaped "
                    + "ones in the text and two real ones around the whole string."})
            .cyber(
                "Escaping is a genuine security topic, not just a formatting one.\n"
                + "\n"
                + "The general problem is the same everywhere: some characters mean "
                + "something special to whatever is reading the text, and when data "
                + "containing those characters is not escaped, the reader mistakes "
                + "data for instructions.\n"
                + "\n"
                + "That single idea is behind SQL injection, cross-site scripting "
                + "and command injection. Here you are escaping a quote so Java "
                + "does not mistake it for the end of a string. It is the same "
                + "mistake, at a much smaller scale, and meeting it now makes the "
                + "later versions far easier to understand.")
            .check(new Task(Task.RECALL,
                    "What escape sequence produces a new line?")
                .accept("\\n")
                .hints("Backslash and one letter.", "n for new line.")
                .explain("\\n breaks the line inside a string.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(\"C:\\\\logs\");")
                .accept("C:\\logs")
                .hints(
                    "Two backslashes in the source produce how many in the output?",
                    "\\\\ is the escape sequence for one real backslash.")
                .explain(
                    "C:\\logs - the double backslash produces a single one. Written "
                    + "with one backslash, \\l would be an unknown escape sequence "
                    + "and would not compile.")
                .xp(20))
            .recap(
                "Escape sequences are a backslash plus one character. They are two "
                + "characters in your source and one in the output.\n"
                + "\n"
                + "    \\n    new line\n"
                + "    \\t    tab, for lining up columns\n"
                + "    \\\"    a quote inside text\n"
                + "    \\\\    one real backslash\n"
                + "\n"
                + "Backslash, never forward slash.")
            .next("Next: reading a compiler error properly."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(8), "Reading the Error", 3)
            .brief(
                "You are on call. A monitoring tool has stopped building and the "
                + "night shift needs it back.\n\n"
                + "The compiler has already told you what is wrong. The skill is "
                + "reading what it said.")
            .willLearn("Reading compiler errors", "cannot find symbol",
                       "A method for debugging")
            .whyUseful(
                "Beginners treat error messages as noise to get past. They are "
                + "instructions. Reading them properly is the single biggest speed "
                + "difference between a new programmer and an experienced one.")
            .concept("How to read a compiler error",
                "A javac error has four parts:\n"
                + "\n"
                + "    Main.java:4: error: cannot find symbol\n"
                + "            system.out.println(\"HI\");\n"
                + "            ^\n"
                + "      symbol:   variable system\n"
                + "\n"
                + "    Main.java:4       the file and the LINE NUMBER\n"
                + "    error: ...        what went wrong\n"
                + "    the source line   copied out for you\n"
                + "    ^                 points at the exact spot\n"
                + "    symbol: ...       extra detail on what it could not find\n"
                + "\n"
                + "The three you will meet most:\n"
                + "\n"
                + "CANNOT FIND SYMBOL\n"
                + "A name Java does not recognise. Misspelled, wrong capitals, or "
                + "something you never declared. The ^ points at the word.\n"
                + "\n"
                + "';' EXPECTED\n"
                + "A missing semicolon. Usually the line ABOVE the one reported.\n"
                + "\n"
                + "REACHED END OF FILE WHILE PARSING\n"
                + "A missing closing brace. Count your { against your }.\n"
                + "\n"
                + "A METHOD FOR ANY ERROR:\n"
                + "\n"
                + "    1. Read the FIRST error only. Ignore the rest for now.\n"
                + "    2. Go to the line it names.\n"
                + "    3. Look at what the ^ is pointing at.\n"
                + "    4. Ask what Java expected to find there.\n"
                + "    5. Make the smallest possible change.\n"
                + "    6. Recompile.\n"
                + "\n"
                + "Step 1 matters most. One mistake often produces a cascade of "
                + "errors below it that vanish on their own once the first is "
                + "fixed.")
            .example(
                "Main.java:3: error: cannot find symbol",
                "        System.out.printLine(\"SCAN\");",
                "                  ^",
                "  symbol:   method printLine(String)")
            .exampleOutput("(this is the error, not the program's output)")
            .lineByLine(
                new String[]{"Main.java:3",
                    "The problem is on line 3 of Main.java. Go there first."},
                new String[]{"cannot find symbol",
                    "Java met a name it does not know."},
                new String[]{"^  under printLine",
                    "The caret points at the exact word. It is not System and it is "
                    + "not out - it is printLine."},
                new String[]{"symbol: method printLine(String)",
                    "It was looking for a METHOD called printLine that takes a "
                    + "String. No such method exists; the real one is println."})
            .predict(new Task(Task.CHOICE,
                    "You compile and get 14 errors. What do you do first?")
                .choices(
                    "Fix the last one, since it is where it stopped",
                    "Fix the first one and recompile",
                    "Fix all 14 before recompiling",
                    "Start the file again")
                .accept("2", "b")
                .hints(
                    "Errors are reported in order through the file.",
                    "One early mistake can confuse the compiler about everything "
                    + "after it.")
                .explain(
                    "Fix the first, then recompile. A single missing brace can "
                    + "generate a dozen errors underneath it, and all of them "
                    + "disappear together when the real one is fixed.\n\n"
                    + "Fixing the later ones first means changing code that was "
                    + "never wrong.")
                .xp(20))
            .practice(new Task(Task.CHOICE,
                    "The error is  reached end of file while parsing.  What is "
                    + "wrong?")
                .choices(
                    "A missing semicolon",
                    "A missing closing brace }",
                    "The file is empty",
                    "A misspelled method")
                .accept("2", "b")
                .hints(
                    "Java read to the end of the file still waiting for something.",
                    "It was waiting for a block to be closed.")
                .explain(
                    "A missing }. Java reached the end of the file with a block "
                    + "still open. Count your opening braces against your closing "
                    + "ones - they must match exactly.")
                .xp(20))
            .objective(
                "Diagnose a real compiler error without being told the answer.")
            .starter(
                "Main.java:4: error: cannot find symbol",
                "        System.Out.println(\"ALERT\");",
                "              ^",
                "  symbol:   variable Out",
                "  location: class System")
            .yourTask(
                "Work through the method: read the line, look at what the caret "
                + "points at, and ask what Java expected.\n\n"
                + "The caret is under Out. What should that word be? Type just the "
                + "corrected word.")
            .mainTask(new Task(Task.WRITE,
                    "Type the corrected word.")
                .accept("out")
                .hints(
                    "Compare it with the spelling you have used in every mission so "
                    + "far.",
                    "It is the same letters. Only one of them is wrong.",
                    "Java is case sensitive, and this word is not a class name.")
                .solution("System.out.println(\"ALERT\");")
                .whyItWorks(
                    "The caret pointed at Out, and the message said 'variable Out, "
                    + "location: class System' - meaning it looked inside System for "
                    + "something named Out and found nothing.\n"
                    + "\n"
                    + "The correct name is out, all lowercase. Java is case "
                    + "sensitive, so Out and out are different names, and only one "
                    + "of them exists.\n"
                    + "\n"
                    + "Notice the error told you almost everything: which line, "
                    + "which word, what kind of thing it was looking for, and where "
                    + "it looked. Reading it carefully was faster than staring at "
                    + "the code.")
                .explain(
                    "out, lowercase. System is a class so it is capitalised; out is "
                    + "a field inside it, so it is not.")
                .xp(25))
            .mistakes(
                new String[]{"Ignoring the caret",
                    "The ^ points at the exact character where Java gave up. It is "
                    + "the most precise information in the whole message and it is "
                    + "routinely skipped."},
                new String[]{"Changing several things at once",
                    "Then you do not know which change fixed it, or whether you "
                    + "introduced something new. One change, recompile, look again."},
                new String[]{"Assuming the error means your logic is wrong",
                    "A compile error means the program never ran, so your logic has "
                    + "not been tested yet. It is a grammar problem, not a thinking "
                    + "problem."})
            .cyber(
                "Incident response is this method applied to a bigger mess.\n"
                + "\n"
                + "    read the alert   ->   go to what it names\n"
                + "    ->   look at exactly what it points at\n"
                + "    ->   form one hypothesis   ->   test it\n"
                + "    ->   change one thing   ->   look again\n"
                + "\n"
                + "The habit of changing one thing at a time and re-checking is "
                + "what separates an investigation from guessing. Learning it on "
                + "compiler errors is cheap; learning it during a live incident is "
                + "expensive.")
            .check(new Task(Task.CHOICE,
                    "What does the ^ in an error message point at?")
                .choices(
                    "The end of the file",
                    "The exact spot where the compiler gave up",
                    "The line number",
                    "Nothing useful")
                .accept("2", "b")
                .hints("It sits underneath the source line.",
                       "It marks a position in that line.")
                .explain(
                    "The exact character position. Look there before anywhere else.")
                .xp(10))
            .check(new Task(Task.RECALL,
                    "Which error message means a missing semicolon? Type the two "
                    + "words.")
                .accept("';' expected", "expected", "semicolon expected",
                        "; expected")
                .hints("The message quotes the character it wanted.",
                       "It is the character, then the word expected.")
                .explain(
                    "';' expected - and remember it usually points one line below "
                    + "the real problem.")
                .xp(15))
            .recap(
                "An error message names the file, the line, the problem, and points "
                + "at the exact spot with a caret.\n"
                + "\n"
                + "    cannot find symbol         a name Java does not know\n"
                + "    ';' expected               missing semicolon, line above\n"
                + "    reached end of file        missing closing brace\n"
                + "\n"
                + "Fix the FIRST error. Change one thing. Recompile. Look again.")
            .next("Next: putting the whole campaign together."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(9), "The Terminal Banner", 3)
            .brief(
                "NORTHSTAR needs a login banner on every server. Legal have "
                + "supplied the wording and it must appear exactly as written.\n\n"
                + "This is your first real deliverable. Everything you need you "
                + "already have.")
            .willLearn("Combining print, println and escapes",
                       "Producing exact output")
            .whyUseful(
                "Most early programming work is producing output that matches a "
                + "specification exactly. Close is not correct, and the discipline "
                + "of matching a spec character for character starts here.")
            .concept("Putting the pieces together",
                "Nothing new in this mission. You are combining what you already "
                + "know:\n"
                + "\n"
                + "    System.out.println(\"text\");    a line\n"
                + "    System.out.print(\"text\");      stay on the line\n"
                + "    System.out.println();          an empty line\n"
                + "    \\n   \\t   \\\"                    escapes\n"
                + "    //   /* */                     comments\n"
                + "\n"
                + "Two things worth knowing:\n"
                + "\n"
                + "println with NOTHING in the brackets prints an empty line. It "
                + "is how you put a gap between sections.\n"
                + "\n"
                + "And when output must match a specification exactly, build it "
                + "one line at a time and check each one. Trying to write all of "
                + "it and then hunting for the difference is much slower.")
            .example(
                "System.out.println(\"=== NOTICE ===\");",
                "System.out.println();",
                "System.out.println(\"Authorised users only.\");")
            .exampleOutput(
                "=== NOTICE ===",
                "",
                "Authorised users only.")
            .lineByLine(
                new String[]{"System.out.println();",
                    "Empty brackets. Prints nothing, then ends the line - which "
                    + "produces one blank line. Note there is no version of print() "
                    + "with empty brackets, because printing nothing and not moving "
                    + "would do nothing at all."})
            .predict(new Task(Task.PREDICT,
                    "How many lines of output does this produce? Count blank lines "
                    + "too.")
                .code(
                    "System.out.println(\"A\");",
                    "System.out.println();",
                    "System.out.println(\"B\");")
                .accept("3", "three")
                .hints(
                    "A blank line is still a line.",
                    "Three println statements, each ending a line.")
                .explain(
                    "Three: A, a blank one, then B. The empty println produced a "
                    + "real line that happens to contain nothing.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print? Answer on one line.")
                .code("System.out.println(\"LEVEL\\t\\\"HIGH\\\"\");")
                .accept("LEVEL\t\"HIGH\"", "LEVEL    \"HIGH\"", "LEVEL \"HIGH\"")
                .hints(
                    "There are two different escape sequences in there.",
                    "\\t is a tab, and \\\" is a quote character that appears in "
                    + "the output.")
                .explain(
                    "LEVEL, then a tab, then \"HIGH\" with real quote marks around "
                    + "it. Three escape sequences, each producing one character.")
                .xp(20))
            .objective(
                "Produce the NORTHSTAR login banner, exactly as Legal specified.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // build the banner here",
                "    }",
                "}")
            .yourTask(
                "The banner must be exactly:\n\n"
                + "    === NORTHSTAR SYSTEMS ===\n"
                + "    (blank line)\n"
                + "    Authorised access only.\n"
                + "    All activity is monitored.\n\n"
                + "Which statement produces the blank line? Type it in full, "
                + "semicolon included.")
            .mainTask(new Task(Task.WRITE,
                    "Type the statement that prints an empty line.")
                .accept("System.out.println();", "System.out.println()")
                .hints(
                    "You want to end a line without printing anything first.",
                    "It is println with nothing between the brackets.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        // login banner - wording supplied by Legal, do not edit",
                    "        System.out.println(\"=== NORTHSTAR SYSTEMS ===\");",
                    "        System.out.println();",
                    "        System.out.println(\"Authorised access only.\");",
                    "        System.out.println(\"All activity is monitored.\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "Four statements produce four lines, and the second one is "
                    + "empty.\n"
                    + "\n"
                    + "println() with nothing in the brackets prints no text and "
                    + "then ends the line, which is exactly what a blank line is.\n"
                    + "\n"
                    + "The comment at the top matters as much as the code. It tells "
                    + "the next analyst that this wording came from Legal and is "
                    + "not theirs to tidy up - which is the kind of thing that is "
                    + "obvious today and forgotten in six months.")
                .explain(
                    "println() with empty brackets. There is no print() with empty "
                    + "brackets, because printing nothing without moving would "
                    + "achieve nothing.")
                .xp(25))
            .mistakes(
                new String[]{"Using println(\" \") for a blank line",
                    "That prints a line containing a space, which is not the same "
                    + "thing. It will not match a specification that expects an "
                    + "empty line."},
                new String[]{"Editing wording supplied by someone else",
                    "Legal wording, compliance text and policy messages are often "
                    + "exact for a reason. Improving the grammar can break the "
                    + "thing it exists to do."},
                new String[]{"Writing it all before testing any of it",
                    "Build and check one line at a time. Finding one wrong "
                    + "character in four lines you have already written is slower "
                    + "than never writing it wrong."})
            .cyber(
                "A login banner is a legal control, not decoration.\n"
                + "\n"
                + "In many jurisdictions, monitoring people without telling them "
                + "creates a problem for the organisation, and prosecuting an "
                + "intruder is harder if nothing ever said the system was private. "
                + "The banner is what establishes both.\n"
                + "\n"
                + "That is why the wording comes from Legal and why it must appear "
                + "exactly. A developer who rewords it to sound friendlier has "
                + "quietly weakened a control, and nothing in the code will warn "
                + "anyone.")
            .check(new Task(Task.CHOICE,
                    "Which produces a genuinely blank line?")
                .choices(
                    "System.out.print();",
                    "System.out.println();",
                    "System.out.println(\" \");",
                    "System.out.println(\"\\\\n\");")
                .accept("2", "b")
                .hints("It must end the line without printing anything.",
                       "Empty brackets on println.")
                .explain(
                    "println() - empty brackets. print() with empty brackets does "
                    + "not compile, and println(\" \") prints a space.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Why must the banner wording be exact?")
                .choices(
                    "Java requires it",
                    "It is a legal notice, and changing it can weaken the control "
                    + "it exists to provide",
                    "Shorter text runs faster",
                    "It does not need to be exact")
                .accept("2", "b")
                .hints("Who supplied the wording, and why?",
                       "It exists to establish something legally.")
                .explain(
                    "It establishes that the system is private and monitored. "
                    + "Reword it and you may have removed the thing that made "
                    + "monitoring defensible.")
                .xp(15))
            .recap(
                "You produced output matching a specification exactly, using only "
                + "what this campaign taught.\n"
                + "\n"
                + "    println(\"text\")   a line\n"
                + "    println()         a blank line\n"
                + "    print(\"text\")     stay on the line\n"
                + "    \\n \\t \\\"           escapes\n"
                + "    // /* */          comments, for the WHY\n"
                + "\n"
                + "Build one line at a time and check as you go.")
            .next("Last mission of the campaign: everything, with nothing "
                + "explained first."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(10), "INIT COMPLETE", 4)
            .brief(
                "Final orientation check. Your supervisor wants to know you can "
                + "work without being walked through it.\n\n"
                + "Nothing new is taught in this mission. Every question comes "
                + "from the nine before it, and you are expected to remember.")
            .willLearn("Recall of the whole campaign")
            .whyUseful(
                "Being told something once is not learning it. This mission is "
                + "deliberately a test rather than a lesson - if a question is "
                + "hard, the mission it came from is the one to replay.")
            .concept("Recall",
                "No new material.\n"
                + "\n"
                + "What you should be able to do now, from memory:\n"
                + "\n"
                + "    - say what javac and the JVM each do\n"
                + "    - write the class and main skeleton\n"
                + "    - print a line, and print without ending the line\n"
                + "    - write both kinds of comment\n"
                + "    - know that statements end at semicolons\n"
                + "    - use \\n, \\t and \\\"\n"
                + "    - read a compiler error and find the real line\n"
                + "\n"
                + "If any of those feel uncertain, that is useful information. Go "
                + "back to that mission from TRAINING rather than pushing on - the "
                + "next campaign assumes all of it.")
            .example(
                "// everything in this campaign, in one program",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"=== REPORT ===\");",
                "        System.out.println();",
                "        System.out.print(\"HOST\\t\");",
                "        System.out.println(\"STATUS\");",
                "        System.out.println(\"WEB-01\\tonline\");",
                "    }",
                "}")
            .exampleOutput(
                "=== REPORT ===",
                "",
                "HOST    STATUS",
                "WEB-01  online")
            .predict(new Task(Task.PREDICT,
                    "From memory. How many lines does this print?")
                .code(
                    "System.out.print(\"A\");",
                    "System.out.println(\"B\\nC\");",
                    "// System.out.println(\"D\");",
                    "System.out.println();")
                .accept("3", "three")
                .hints(
                    "Work through it one statement at a time and track where the "
                    + "cursor is.",
                    "A and B share a line because of print. \\n starts a new one for "
                    + "C. D is commented out. Then an empty line.")
                .explain(
                    "Three lines: AB, then C, then a blank one.\n\n"
                    + "The print left the cursor on the line so B joined A. The \\n "
                    + "inside the string ended that line and started C's. The "
                    + "commented line produced nothing. The empty println made the "
                    + "third line.")
                .xp(30))
            .practice(new Task(Task.DEBUG,
                    "From memory. This has two separate problems. Which line "
                    + "number has the MISSING SEMICOLON?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        system.out.println(\"START\");",
                    "        System.out.println(\"END\")",
                    "    }",
                    "}")
                .accept("4", "line 4")
                .hints(
                    "One line has a capitalisation problem and a different line has "
                    + "no semicolon.",
                    "Look at the end of each println line.")
                .explain(
                    "Line 4 has no semicolon. Line 3 has the other problem - a "
                    + "lowercase system - and javac would report that one first, "
                    + "because errors come in file order.\n\n"
                    + "Fix the first, recompile, then deal with the second.")
                .xp(30))
            .objective(
                "Produce an exact report header with no guidance.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "",
                "    }",
                "}")
            .yourTask(
                "Required output, exactly:\n\n"
                + "    ALERT: \"brute force\"\n"
                + "    SOURCE\tCOUNT\n\n"
                + "The first line contains real quotation marks. The second "
                + "separates the two words with a tab.\n\n"
                + "Type the FIRST of those two statements in full, semicolon "
                + "included.")
            .mainTask(new Task(Task.WRITE,
                    "Type the statement that prints:   ALERT: \"brute force\"")
                .accept(
                    "System.out.println(\"ALERT: \\\"brute force\\\"\");",
                    "System.out.println(\"ALERT: \\\"brute force\\\"\")")
                .hints(
                    "The quotation marks that appear in the output are part of the "
                    + "text, so they need escaping.",
                    "A backslash before each of the two inner quotes.",
                    "The outer pair of quotes is real and marks the start and end "
                    + "of the string.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"ALERT: \\\"brute force\\\"\");",
                    "        System.out.println(\"SOURCE\\tCOUNT\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "The string contains four quote characters in the source and "
                    + "two in the output.\n"
                    + "\n"
                    + "The first and last are real - they mark where the string "
                    + "begins and ends. The two in the middle are written \\\" so "
                    + "Java treats them as text rather than as the end of the "
                    + "string.\n"
                    + "\n"
                    + "Without the backslashes, Java would read the string as "
                    + "ending at the quote before brute, then find the word brute "
                    + "where it expected an operator, and refuse to compile.")
                .explain(
                    "Escape each quote that should appear in the output. The outer "
                    + "pair stays plain.")
                .xp(40))
            .mistakes(
                new String[]{"Pushing on when recall was shaky",
                    "Campaign 01 assumes every one of these. A gap here becomes two "
                    + "gaps there. Replaying a mission costs ten minutes."},
                new String[]{"Guessing at escapes rather than reasoning",
                    "Count the quote characters. Two belong to the string itself; "
                    + "any others are text and need a backslash."})
            .cyber(
                "You have finished orientation.\n"
                + "\n"
                + "What you can do now is small: produce exact text output, read an "
                + "error, and work the edit-compile-run cycle. That is genuinely "
                + "the foundation of every tool on this desk, because every one of "
                + "them ends by telling a human something.\n"
                + "\n"
                + "From the next campaign the programs start to hold data and make "
                + "decisions, which is where they stop being print statements and "
                + "start being security tools.")
            .check(new Task(Task.RECALL,
                    "Which tool compiles Java source?")
                .accept("javac")
                .hints("From mission 1.", "Java compiler, shortened.")
                .explain("javac. The JVM, started with java, runs the result.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "A compiler error names line 12, but line 12 looks correct. "
                    + "What is the most likely explanation?")
                .choices(
                    "The compiler is wrong",
                    "Line 11 is missing a semicolon, and Java read on to line 12",
                    "Line 12 needs deleting",
                    "The file is corrupted")
                .accept("2", "b")
                .hints("Java does not stop at line ends.",
                       "It reports where it gave up, not where the problem started.")
                .explain(
                    "Java read past the missing semicolon on line 11 and only "
                    + "realised on line 12. Check the line above.")
                .xp(20))
            .check(new Task(Task.RECALL,
                    "What does \\t produce in the output?")
                .accept("a tab", "tab")
                .hints("It is used for lining up columns.",
                       "One keyboard key that moves across.")
                .explain(
                    "A tab. It jumps to the next tab stop, which is why columns "
                    + "line up even when the values are different lengths.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "You edit Main.java, run  java -cp out Main,  and see the old "
                    + "behaviour. Why?")
                .choices(
                    "The edit did not save",
                    "You did not recompile, so Main.class is still the old version",
                    "Java caches programs",
                    "The out folder is corrupted")
                .accept("2", "b")
                .hints("Which file does java actually read?",
                       "It reads the .class file, which javac writes.")
                .explain(
                    "java reads Main.class, and javac is what updates it. Edit, "
                    + "compile, run - in that order, every time.")
                .xp(20))
            .recap(
                "CAMPAIGN 00 COMPLETE.\n"
                + "\n"
                + "You can now:\n"
                + "\n"
                + "    - explain what javac and the JVM do, and why a JRE\n"
                + "      is not enough\n"
                + "    - write the class and main skeleton from memory\n"
                + "    - print lines, partial lines and blank lines\n"
                + "    - comment code, and say why rather than what\n"
                + "    - use \\n, \\t and \\\"\n"
                + "    - read a compiler error, find the real line, and fix the\n"
                + "      first error first\n"
                + "\n"
                + "Everything so far has been fixed text. The programs cannot yet "
                + "remember anything or make a decision.\n"
                + "\n"
                + "That changes next.")
            .next("CAMPAIGN 01 - JAVA ZERO. Variables, types, and programs that "
                + "hold onto something."));
    }
}
