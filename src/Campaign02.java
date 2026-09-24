/**
 * CAMPAIGN 02 - CONDITIONAL
 * Programs that decide. Thirty missions.
 *
 * Campaign 01 ended with programs that could store, convert and take text
 * apart, but ran every line every time, whatever the data said. Its
 * missions kept pointing at the same gap: a -1 nobody checked, input
 * nobody validated, a threshold nothing acted on. This campaign closes it.
 *
 * Writing methods is Campaign 03 and loops are Campaign 04, so a mission
 * here may use everything from Campaigns 00 and 01, plus whatever this
 * campaign has already introduced - and nothing else.
 */
public class Campaign02 {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(1), "Is It More Than Five?", 2)
            .brief(
                "The lockout policy says an account with more than five failed "
                + "logins is suspicious. The audit tool holds the count. What it "
                + "cannot yet do is ask the question.\n\n"
                + "Every decision a program makes starts as a comparison that "
                + "comes out true or false.")
            .willLearn("Comparison operators", "== against =", "Comparisons give booleans")
            .whyUseful(
                "Thresholds, limits, matches and mismatches - every rule a "
                + "security tool enforces is a comparison underneath. Before a "
                + "program can act on a rule, it has to be able to state one.")
            .concept("Comparison operators",
                "Six operators compare two values:\n"
                + "\n"
                + "    a == b    equal to\n"
                + "    a != b    not equal to\n"
                + "    a <  b    less than\n"
                + "    a >  b    greater than\n"
                + "    a <= b    less than or equal to\n"
                + "    a >= b    greater than or equal to\n"
                + "\n"
                + "Each one produces a BOOLEAN - the true/false type from "
                + "Campaign 01. So a comparison can be stored, printed, or "
                + "used anywhere a boolean fits:\n"
                + "\n"
                + "    boolean suspicious = failures > 5;\n"
                + "\n"
                + "The two-character operators are written with no space, and "
                + "the = always comes second: >= not =>.\n"
                + "\n"
                + "THE ONE EVERYONE GETS WRONG ONCE:\n"
                + "\n"
                + "    =     assignment - put a value in a box\n"
                + "    ==    comparison - are these two equal?\n"
                + "\n"
                + "failures = 5 CHANGES failures. failures == 5 ASKS about it.\n"
                + "\n"
                + "These work on numbers, chars and booleans. An int can be "
                + "compared with a double; the usual promotion happens first. "
                + "Text is different - comparing Strings with == is a real and "
                + "common security bug, and it has a mission of its own later "
                + "in this campaign.\n"
                + "\n"
                + "Comparisons run AFTER arithmetic, so failures + 1 > 5 adds "
                + "first and compares second - usually what you want. But they "
                + "also run after the + that joins text, which catches people "
                + "out when printing. Put brackets round a comparison inside a "
                + "println.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failures = 7;",
                "        final int LIMIT = 5;",
                "        boolean over = failures > LIMIT;",
                "        System.out.println(\"OVER LIMIT: \" + over);",
                "        System.out.println(\"EXACTLY:    \" + (failures == LIMIT));",
                "    }",
                "}")
            .exampleOutput("OVER LIMIT: true", "EXACTLY:    false")
            .lineByLine(
                new String[]{"failures > LIMIT",
                    "Is 7 greater than 5? Yes, so this is true, and true goes "
                    + "into the boolean over."},
                new String[]{"failures == LIMIT",
                    "Is 7 equal to 5? No: false. Two equals signs - a question, "
                    + "not an assignment."},
                new String[]{"The brackets round (failures == LIMIT)",
                    "Without them, the + would join the text and the number "
                    + "first, then try to compare a String with an int. That "
                    + "does not compile."},
                new String[]{"failures afterwards",
                    "Still 7. Comparing never changes anything."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int port = 443;",
                    "System.out.println(port != 80);")
                .accept("true")
                .hints("!= means 'is not equal to'.",
                       "Is 443 different from 80?")
                .explain(
                    "true. 443 is not equal to 80, so 'not equal' is true.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "The difference that decides a policy. What are the TWO lines "
                    + "of output?")
                .code(
                    "System.out.println(5 >= 5);",
                    "System.out.println(5 > 5);")
                .accept("true false", "true, false", "true and false")
                .hints(
                    ">= includes the value itself.",
                    "Is 5 greater than 5?")
                .explain(
                    "    true\n"
                    + "    false\n"
                    + "\n"
                    + "5 is greater than OR EQUAL TO 5, so the first is true. 5 is "
                    + "not greater than itself, so the second is false. One "
                    + "character apart, and they disagree about exactly one "
                    + "value - the one on the line.")
                .xp(20))
            .objective(
                "State the 'suspicious account' rule as a boolean.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failures = 12;",
                "        final int THRESHOLD = 10;",
                "        // declare suspicious here",
                "        System.out.println(\"SUSPICIOUS: \" + suspicious);",
                "    }",
                "}")
            .yourTask(
                "An account is suspicious when its failures are greater than "
                + "THRESHOLD. Declare a boolean called suspicious holding that "
                + "comparison.")
            .mainTask(new Task(Task.WRITE,
                    "Declare suspicious: failures greater than THRESHOLD.")
                .accept("boolean suspicious = failures > THRESHOLD;",
                        "boolean suspicious = failures > THRESHOLD",
                        "boolean suspicious = THRESHOLD < failures;",
                        "boolean suspicious = THRESHOLD < failures")
                .hints(
                    "A comparison produces true or false, so the type is boolean.",
                    "'Greater than' is >.",
                    "boolean suspicious = failures > THRESHOLD;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int failures = 12;",
                    "        final int THRESHOLD = 10;",
                    "        boolean suspicious = failures > THRESHOLD;",
                    "        System.out.println(\"SUSPICIOUS: \" + suspicious);",
                    "    }",
                    "}")
                .whyItWorks(
                    "failures > THRESHOLD asks whether 12 is greater than 10. It "
                    + "is, so the comparison produces true, and a boolean box "
                    + "holds it.\n"
                    + "\n"
                    + "The rule now exists as a value the program can pass around "
                    + "and - from the next mission - act on. Written with = "
                    + "instead of >, the line would try to store THRESHOLD in "
                    + "failures and then put an int into a boolean, and Java "
                    + "would refuse. Most of the time, mixing up = and == is "
                    + "caught like that. The next mission shows the one case "
                    + "where it is not.")
                .explain(
                    "A comparison is a boolean, so it goes in a boolean box.")
                .xp(25))
            .mistakes(
                new String[]{"= where you meant ==",
                    "= assigns, == compares. if (x = 5) on an int does not "
                    + "compile; on a boolean it quietly assigns."},
                new String[]{"=> or =<",
                    "Not Java operators. The = always comes second: >= and <=."},
                new String[]{"A comparison inside println without brackets",
                    "\"X: \" + a == b joins first, then compares a String with "
                    + "an int. Write \"X: \" + (a == b)."})
            .cyber(
                "Strip away the dashboards and the machine learning, and "
                + "detection is comparison. Failed logins greater than a "
                + "threshold. Bytes out greater than a baseline. A file hash "
                + "equal to a known-bad one. A login hour less than 7.\n"
                + "\n"
                + "That makes the choice of operator a security decision. The "
                + "difference between > and >= is one character in the code "
                + "and one extra guess, one extra gigabyte or one extra hour "
                + "for an attacker - on every single occasion the rule is "
                + "applied. Rules are written in English and enforced in "
                + "operators, and the translation between them is where many "
                + "real gaps live.")
            .check(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "int a = 3;",
                    "int b = 3;",
                    "System.out.println(\"EQUAL: \" + a == b);")
                .accept("3", "line 3")
                .hints(
                    "Which runs first here: the + or the ==?",
                    "After the +, what is on the left of the ==?")
                .explain(
                    "Line 3. The + runs first and builds the String \"EQUAL: 3\". "
                    + "Then == tries to compare that String with the int b, which "
                    + "Java will not do. Brackets fix it: \"EQUAL: \" + (a == b).")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "Which operator means 'is not equal to'?")
                .choices("=!", "!=", "<>", "=/=")
                .accept("2", "b")
                .hints("It starts with an exclamation mark.",
                       "The = comes second, as always.")
                .explain(
                    "!= - read the ! as 'not'. <> means not-equal in some other "
                    + "languages, but not in Java.")
                .xp(10))
            .recap(
                "    ==  !=  <  >  <=  >=\n"
                + "\n"
                + "Each gives a boolean: true or false.\n"
                + "\n"
                + "=  assigns.   ==  compares.\n"
                + "\n"
                + "Put brackets round a comparison inside a println. Strings "
                + "are compared differently - later this campaign.")
            .next("Next: making the program act on a comparison."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(2), "Only If", 3)
            .brief(
                "The audit tool now knows when an account is suspicious. It "
                + "prints 'true' and moves on. What the team wants is an "
                + "alert - and only when there is something to alert about.\n\n"
                + "For the first time, some of your program's lines will not "
                + "run.")
            .willLearn("if", "Blocks and braces", "The stray semicolon")
            .whyUseful(
                "if is how a program chooses. From here on, code runs because "
                + "the data says it should - which is what makes a program a "
                + "control and not just a calculator.")
            .concept("if",
                "An if statement runs a block of code only when a condition "
                + "is true:\n"
                + "\n"
                + "    if (failures > 5) {\n"
                + "        System.out.println(\"ALERT\");\n"
                + "    }\n"
                + "\n"
                + "    if              the keyword\n"
                + "    (failures > 5)  the CONDITION, in round brackets.\n"
                + "                    It must be a boolean.\n"
                + "    { ... }         the BLOCK: the lines controlled\n"
                + "\n"
                + "If the condition is true, the block runs. If it is false, "
                + "the whole block is skipped. Either way the program then "
                + "carries on with the line after the closing brace.\n"
                + "\n"
                + "The lines inside are indented four spaces. Java ignores the "
                + "indentation completely - it is for people, so the shape of "
                + "the code shows which lines are controlled.\n"
                + "\n"
                + "Note there is NO semicolon after the condition. This is a "
                + "trap:\n"
                + "\n"
                + "    if (failures > 5); {\n"
                + "\n"
                + "That semicolon is an empty statement - 'do nothing' - and it "
                + "is what the if controls. The block after it is just a block, "
                + "and it runs EVERY time. It compiles without complaint.\n"
                + "\n"
                + "Java lets you leave the braces off when the block is one "
                + "line. Then the if controls exactly ONE statement, whatever "
                + "the indentation suggests. Always write the braces. The "
                + "cyber section of this mission is about what happens when "
                + "people do not.\n"
                + "\n"
                + "One more consequence of = against ==. For an int, "
                + "if (x = 5) does not compile, because 5 is not a boolean. "
                + "For a boolean it DOES:\n"
                + "\n"
                + "    if (locked = true) {\n"
                + "\n"
                + "That stores true in locked, and the condition is the value "
                + "just stored - always true. Write if (locked) instead; a "
                + "boolean already is a condition.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failures = 7;",
                "        if (failures > 5) {",
                "            System.out.println(\"ALERT: brute force suspected\");",
                "        }",
                "        System.out.println(\"check complete\");",
                "    }",
                "}")
            .exampleOutput("ALERT: brute force suspected", "check complete")
            .lineByLine(
                new String[]{"if (failures > 5) {",
                    "7 > 5 is true, so the block will run. No semicolon here."},
                new String[]{"The indented println",
                    "Inside the block. It runs only because the condition was "
                    + "true."},
                new String[]{"}",
                    "The block ends. Everything after this runs regardless."},
                new String[]{"\"check complete\"",
                    "Outside the if. It prints whether or not there was an "
                    + "alert."})
            .predict(new Task(Task.PREDICT,
                    "Same program, different count. What does this print?")
                .code(
                    "int failures = 3;",
                    "if (failures > 5) {",
                    "    System.out.println(\"ALERT\");",
                    "}",
                    "System.out.println(\"check complete\");")
                .accept("check complete")
                .hints(
                    "Is 3 greater than 5?",
                    "A false condition skips the whole block.")
                .explain(
                    "check complete - and nothing else. 3 > 5 is false, so the "
                    + "block is skipped; the last line is outside it and always "
                    + "runs.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "Look very closely at line 2. What are the TWO lines of "
                    + "output?")
                .code(
                    "int failures = 2;",
                    "if (failures > 5); {",
                    "    System.out.println(\"ALERT\");",
                    "}",
                    "System.out.println(\"done\");")
                .accept("ALERT done", "ALERT, done", "ALERT and done")
                .hints(
                    "What is directly after the closing bracket of the "
                    + "condition?",
                    "That semicolon is the entire thing the if controls.")
                .explain(
                    "    ALERT\n"
                    + "    done\n"
                    + "\n"
                    + "The semicolon after the condition is an empty statement, "
                    + "and THAT is what the if controls. The block below is not "
                    + "attached to anything, so it always runs. A false "
                    + "condition, an alert anyway, and no error from Java.")
                .xp(30))
            .objective(
                "Flag hosts with an unusual number of open ports.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int openPorts = 34;",
                "        // write the if line here",
                "            System.out.println(\"REVIEW: too many open ports\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the line that opens the if block: the review message "
                + "should print only when openPorts is greater than 20.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line: openPorts greater than 20.")
                .accept("if (openPorts > 20) {", "if(openPorts > 20) {",
                        "if (openPorts > 20){", "if(openPorts > 20){",
                        "if (20 < openPorts) {")
                .hints(
                    "The keyword, then the condition in round brackets, then an "
                    + "opening brace.",
                    "No semicolon anywhere on this line.",
                    "if (openPorts > 20) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int openPorts = 34;",
                    "        if (openPorts > 20) {",
                    "            System.out.println(\"REVIEW: too many open ports\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "The condition openPorts > 20 is a boolean - true here, "
                    + "since 34 is greater than 20 - and the brace opens the "
                    + "block it controls. The closing brace is already in the "
                    + "starter.\n"
                    + "\n"
                    + "A semicolon at the end of this line would still compile. "
                    + "It would make the review message print for EVERY host, "
                    + "including ones with two open ports - a rule that fires "
                    + "on everything, which in practice means a rule someone "
                    + "switches off.")
                .explain(
                    "if, the condition in brackets, an opening brace, and no "
                    + "semicolon.")
                .xp(25))
            .mistakes(
                new String[]{"A semicolon after the condition",
                    "if (x > 5); { ... } compiles, and the block always runs."},
                new String[]{"Leaving the braces off",
                    "Then only the next ONE statement is controlled, whatever "
                    + "the indentation says. Always write braces."},
                new String[]{"if (flag = true)",
                    "Assigns, and is always true. Write if (flag)."})
            .cyber(
                "In 2014 Apple shipped a bug in the code that checked TLS "
                + "certificates on iPhones and Macs. Part of it looked like "
                + "this:\n"
                + "\n"
                + "    if ((err = check(...)) != 0)\n"
                + "        goto fail;\n"
                + "        goto fail;\n"
                + "\n"
                + "The line had been duplicated. Without braces, the if "
                + "controlled only the first one. The second ran every time and "
                + "jumped past the final signature check - so connections to "
                + "sites with forged certificates were accepted as genuine. The "
                + "indentation made it look controlled. It was not.\n"
                + "\n"
                + "It became known as 'goto fail', and it is the standing "
                + "argument for writing braces on every if, every time, even "
                + "when the block is one line.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "boolean locked = false;",
                    "if (locked = true) {",
                    "    System.out.println(\"LOCKED\");",
                    "}")
                .accept("LOCKED")
                .hints(
                    "Count the equals signs in the condition.",
                    "One = assigns. What value does the condition end up with?")
                .explain(
                    "LOCKED. locked = true stores true and the condition is that "
                    + "stored value, so it is always true - and locked has been "
                    + "changed as a side effect. Writing if (locked) avoids the "
                    + "trap entirely.")
                .xp(25))
            .check(new Task(Task.CHOICE,
                    "An if written WITHOUT braces controls how many statements?")
                .choices("None", "Exactly one", "All the indented ones",
                         "Everything up to the next blank line")
                .accept("2", "b")
                .hints("Indentation means nothing to Java.",
                       "Think of goto fail.")
                .explain(
                    "Exactly one - the next statement. Indentation is invisible "
                    + "to the compiler, which is precisely how goto fail "
                    + "happened.")
                .xp(15))
            .recap(
                "    if (condition) {\n"
                + "        runs only when the condition is true\n"
                + "    }\n"
                + "    runs either way\n"
                + "\n"
                + "The condition must be a boolean. No semicolon after it. "
                + "Always write the braces.")
            .next("Next: doing something else when the condition is false."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(3), "Otherwise", 3)
            .brief(
                "The VPN gateway checks a second factor before letting a user "
                + "in. With a plain if, it can say GRANTED when the check "
                + "passes - and says nothing at all when it fails.\n\n"
                + "An access decision needs two outcomes, and exactly one of "
                + "them every time.")
            .willLearn("if / else", "Booleans as conditions", "Default branches")
            .whyUseful(
                "Allow or deny. Alert or stay quiet. Quarantine or deliver. "
                + "Most decisions have exactly two outcomes, and if/else "
                + "guarantees one of them happens and never both.")
            .concept("if / else",
                "else gives the if a second block, for when the condition is "
                + "false:\n"
                + "\n"
                + "    if (mfaPassed) {\n"
                + "        System.out.println(\"ACCESS GRANTED\");\n"
                + "    } else {\n"
                + "        System.out.println(\"ACCESS DENIED\");\n"
                + "    }\n"
                + "\n"
                + "Exactly ONE of the two blocks runs. Never both, never "
                + "neither.\n"
                + "\n"
                + "else has no condition of its own. It means 'in every other "
                + "case', so there is nothing to test. else (x < 5) is an "
                + "error.\n"
                + "\n"
                + "The } else { line closes the first block and opens the "
                + "second. Writing it on one line like that is the Java "
                + "convention.\n"
                + "\n"
                + "Notice the condition: if (mfaPassed), not "
                + "if (mfaPassed == true). A boolean variable already IS true "
                + "or false, so comparing it with true adds nothing - and "
                + "risks the single-= trap from last mission.\n"
                + "\n"
                + "Choosing which case goes in the if and which goes in the "
                + "else is a design decision. The else is what happens when "
                + "the condition is not met - including when it is not met "
                + "for reasons you never thought of. Put the safe outcome "
                + "there.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String user = \"m.reyes\";",
                "        boolean mfaPassed = false;",
                "        if (mfaPassed) {",
                "            System.out.println(user + \": ACCESS GRANTED\");",
                "        } else {",
                "            System.out.println(user + \": ACCESS DENIED\");",
                "        }",
                "    }",
                "}")
            .exampleOutput("m.reyes: ACCESS DENIED")
            .lineByLine(
                new String[]{"if (mfaPassed)",
                    "The boolean itself is the condition. false, so the first "
                    + "block is skipped."},
                new String[]{"} else {",
                    "Closes the first block, opens the second. No condition."},
                new String[]{"The DENIED println",
                    "Runs because the condition was false."},
                new String[]{"Only one line printed",
                    "Exactly one block runs, every time."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "double score = 6.5;",
                    "if (score >= 7.0) {",
                    "    System.out.println(\"HIGH\");",
                    "} else {",
                    "    System.out.println(\"NOT HIGH\");",
                    "}")
                .accept("NOT HIGH")
                .hints("Is 6.5 at least 7.0?",
                       "False runs the else block.")
                .explain(
                    "NOT HIGH. 6.5 >= 7.0 is false, so the else runs.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "This does not compile. Which line does javac report first?")
                .code(
                    "int failures = 3;",
                    "if (failures > 5) {",
                    "    System.out.println(\"LOCK\");",
                    "} else (failures <= 5) {",
                    "    System.out.println(\"OK\");",
                    "}")
                .accept("4", "line 4")
                .hints(
                    "One part of an if/else never has a condition.",
                    "else means 'every other case'.")
                .explain(
                    "Line 4. else takes no condition - it already means 'when "
                    + "the if's condition is false'. Java reads (failures <= 5) "
                    + "as a stray expression and rejects it: 'not a statement'.")
                .xp(20))
            .objective(
                "Only allow connections to servers with a valid certificate.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        boolean certificateValid = false;",
                "        // write the if line here",
                "            System.out.println(\"CONNECTION ALLOWED\");",
                "        } else {",
                "            System.out.println(\"CONNECTION REFUSED\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line so that ALLOWED prints only when "
                + "certificateValid is true. Use the boolean directly - do not "
                + "compare it with anything.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line, using certificateValid as the condition.")
                .accept("if (certificateValid) {", "if(certificateValid) {",
                        "if (certificateValid){", "if(certificateValid){")
                .hints(
                    "A boolean is already a condition.",
                    "No == true needed.",
                    "if (certificateValid) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        boolean certificateValid = false;",
                    "        if (certificateValid) {",
                    "            System.out.println(\"CONNECTION ALLOWED\");",
                    "        } else {",
                    "            System.out.println(\"CONNECTION REFUSED\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "certificateValid is false here, so the if block is skipped "
                    + "and the else prints REFUSED. Flip it to true and ALLOWED "
                    + "prints instead. One of the two, always.\n"
                    + "\n"
                    + "The structure also puts the safe outcome in the else. "
                    + "The only way to reach ALLOWED is for certificateValid to "
                    + "be true. Anything else at all - false, or a value that "
                    + "was never set properly upstream - lands on REFUSED. "
                    + "That is called failing closed.")
                .explain(
                    "A boolean needs no comparison - it is the condition.")
                .xp(25))
            .mistakes(
                new String[]{"A condition on else",
                    "else (x < 5) does not compile. else already means 'in "
                    + "every other case'."},
                new String[]{"if (flag == true)",
                    "Works, but says nothing extra, and one missing = turns it "
                    + "into an assignment."},
                new String[]{"The safe outcome in the if",
                    "Put DENY in the else. Then anything unexpected is denied "
                    + "rather than allowed."})
            .cyber(
                "Security engineers talk about systems that FAIL OPEN and "
                + "systems that FAIL CLOSED. A door that unlocks during a power "
                + "cut fails open. A firewall that passes all traffic when its "
                + "rules fail to load fails open. A login check that grants "
                + "access when the password database is unreachable fails "
                + "open.\n"
                + "\n"
                + "In code, the difference is often just which branch is the "
                + "else. if (denied) { deny } else { allow } allows everything "
                + "the check did not specifically catch - including every "
                + "situation nobody anticipated. if (allowed) { allow } else "
                + "{ deny } denies them. Same two blocks, swapped, and a "
                + "completely different security posture.")
            .check(new Task(Task.CHOICE,
                    "In an if/else, how many of the two blocks run?")
                .choices("Always both", "Exactly one", "One or none",
                         "Depends on the else's condition")
                .accept("2", "b")
                .hints("The else catches every case the if does not.",
                       "Never both, never neither.")
                .explain(
                    "Exactly one. If the condition is true the first block runs; "
                    + "in every other case the else does.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "One if, then one if/else. What are the TWO lines of output?")
                .code(
                    "int n = 10;",
                    "if (n > 5) {",
                    "    System.out.println(\"A\");",
                    "}",
                    "if (n > 8) {",
                    "    System.out.println(\"B\");",
                    "} else {",
                    "    System.out.println(\"C\");",
                    "}")
                .accept("A B", "A, B", "A and B")
                .hints(
                    "These are two separate decisions, one after the other.",
                    "Check each condition on its own.")
                .explain(
                    "    A\n"
                    + "    B\n"
                    + "\n"
                    + "The first if prints A because 10 > 5. The second is a "
                    + "separate decision: 10 > 8, so B, and its else is "
                    + "skipped.")
                .xp(20))
            .recap(
                "    if (condition) {\n"
                + "        when true\n"
                + "    } else {\n"
                + "        in every other case\n"
                + "    }\n"
                + "\n"
                + "Exactly one block runs. else has no condition.\n"
                + "\n"
                + "Put the safe outcome in the else: fail closed.")
            .next("Next: the one-character difference between > and >=."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(4), "More Than, or At Least?", 3)
            .brief(
                "The policy document says: 'Accounts are locked once they "
                + "reach five failed logins.' The code says failures > 5.\n\n"
                + "On the fifth failure, the policy locks the account. The code "
                + "does not. Every attacker gets a sixth guess, on every "
                + "account, and nothing anywhere reports an error.")
            .willLearn(">= vs >", "Reading a policy precisely", "Translating words to operators")
            .whyUseful(
                "Rules arrive as sentences. Code enforces operators. The "
                + "translation between them is where a large share of real "
                + "security gaps come from - and it is a skill you can learn "
                + "deliberately.")
            .concept("Words to operators",
                "Whether the threshold value itself counts is the whole "
                + "difference:\n"
                + "\n"
                + "    more than 5            > 5     6, 7, 8...\n"
                + "    at least 5             >= 5    5, 6, 7...\n"
                + "    5 or more              >= 5\n"
                + "    reaches 5              >= 5\n"
                + "    exceeds 5              > 5\n"
                + "    less than 5            < 5     ...3, 4\n"
                + "    at most 5              <= 5    ...4, 5\n"
                + "    no more than 5         <= 5\n"
                + "    fewer than 5           < 5\n"
                + "\n"
                + "The test for which to use: ask what should happen AT the "
                + "number itself. With exactly 5 failures, is the account "
                + "locked? If yes, 5 must make the condition true, so it is "
                + ">=.\n"
                + "\n"
                + "Some sentences hide the number. 'Locked after five failed "
                + "attempts' - locked after the fifth, so at five: >= 5. "
                + "'Allowed five attempts' - the fifth is still allowed, so "
                + "locked at six: > 5. The same five, opposite operators. "
                + "When a policy is genuinely ambiguous, the right move is to "
                + "ask whoever wrote it, not to guess.\n"
                + "\n"
                + "Note also which side is safe to get wrong. For a lockout, "
                + "an operator that locks one attempt early inconveniences a "
                + "user. One that locks one attempt late helps an attacker, "
                + "every time.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failures = 5;",
                "        System.out.println(\"more than 5: \" + (failures > 5));",
                "        System.out.println(\"at least 5:  \" + (failures >= 5));",
                "    }",
                "}")
            .exampleOutput("more than 5: false", "at least 5:  true")
            .lineByLine(
                new String[]{"failures = 5",
                    "Deliberately the value on the line - the only value where "
                    + "the two rules disagree."},
                new String[]{"failures > 5",
                    "5 is not more than 5: false. Not locked."},
                new String[]{"failures >= 5",
                    "5 is at least 5: true. Locked."},
                new String[]{"At 4 or at 6",
                    "Both rules would agree. They only ever differ at the "
                    + "boundary itself."})
            .predict(new Task(Task.PREDICT,
                    "The code says > 5. The account has exactly 5 failures. What "
                    + "does this print?")
                .code(
                    "int failures = 5;",
                    "if (failures > 5) {",
                    "    System.out.println(\"LOCKED\");",
                    "} else {",
                    "    System.out.println(\"ACTIVE\");",
                    "}")
                .accept("ACTIVE")
                .hints("Is 5 more than 5?",
                       "A false condition runs the else.")
                .explain(
                    "ACTIVE. 5 > 5 is false, so on the fifth failure the account "
                    + "stays open - one guess more than 'locked once they reach "
                    + "five' allows.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "'Passwords must be at least 12 characters.' Which condition "
                    + "means the password is acceptable?")
                .choices("len > 12", "len >= 12", "len < 12", "len <= 12")
                .accept("2", "b")
                .hints("Is a 12-character password acceptable?",
                       "At least includes the number itself.")
                .explain(
                    "len >= 12. A password of exactly 12 meets 'at least 12', "
                    + "so 12 must make the condition true. len > 12 would "
                    + "reject it - a policy stricter than the one written.")
                .xp(20))
            .objective(
                "Enforce the lockout policy exactly as written.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failures = 5;",
                "        final int LOCKOUT = 5;",
                "        // Policy: locked once they reach LOCKOUT failures.",
                "        // write the if line here",
                "            System.out.println(\"ACCOUNT LOCKED\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line for the policy in the comment. Use LOCKOUT, "
                + "not the number. Decide first: with exactly LOCKOUT failures, "
                + "is the account locked?")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line: failures reaches LOCKOUT.")
                .accept("if (failures >= LOCKOUT) {", "if(failures >= LOCKOUT) {",
                        "if (failures >= LOCKOUT){", "if(failures >= LOCKOUT){",
                        "if (LOCKOUT <= failures) {")
                .hints(
                    "'Once they reach' - at the number itself, it is locked.",
                    "So LOCKOUT must make the condition true: >=.",
                    "if (failures >= LOCKOUT) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int failures = 5;",
                    "        final int LOCKOUT = 5;",
                    "        // Policy: locked once they reach LOCKOUT failures.",
                    "        if (failures >= LOCKOUT) {",
                    "            System.out.println(\"ACCOUNT LOCKED\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "Reaching five means that on the fifth failure the account "
                    + "locks. With failures at exactly 5, failures >= LOCKOUT is "
                    + "true and the account is locked, as the policy says.\n"
                    + "\n"
                    + "failures > LOCKOUT would compile, run, pass every test "
                    + "that uses 3 or 8, and quietly give every attacker one "
                    + "more guess than the policy allows. The starter sets "
                    + "failures to exactly 5 on purpose: the boundary is the "
                    + "only value that tells the two apart, so it is the value "
                    + "to test with.")
                .explain(
                    "'Reach' includes the number itself: >=.")
                .xp(30))
            .mistakes(
                new String[]{"Using > for 'at least'",
                    "At least 5 includes 5. > 5 leaves it out."},
                new String[]{"Using >= for 'more than'",
                    "More than 5 starts at 6. >= 5 fires one step early."},
                new String[]{"Hard-coding the number",
                    "Compare against the constant. The policy will change, and "
                    + "it should change in one place."})
            .cyber(
                "One extra guess sounds harmless. It is one extra guess per "
                + "account, per lockout window. Against a password-spraying "
                + "attack that tries the most common passwords across "
                + "thousands of accounts, an off-by-one lockout raises the "
                + "number of passwords tried before anyone is locked out by "
                + "a fifth - and with it the number of accounts compromised.\n"
                + "\n"
                + "Auditors check exactly this. A penetration tester will "
                + "count the attempts it takes to trigger a lockout and "
                + "compare the number with the written policy. When they "
                + "disagree, the finding is not 'wrong operator'. It is 'the "
                + "control does not do what the organisation believes it "
                + "does' - which is a much harder conversation.")
            .check(new Task(Task.CHOICE,
                    "'Sessions may stay idle for no more than 30 minutes.' Which "
                    + "condition means the session must be ended?")
                .choices("idle >= 30", "idle > 30", "idle < 30", "idle <= 30")
                .accept("2", "b")
                .hints(
                    "Is exactly 30 idle minutes allowed?",
                    "'No more than 30' allows 30. What must happen to END it?")
                .explain(
                    "idle > 30. 'No more than 30' means 30 is still allowed, so "
                    + "the session ends only when idle goes past 30. The "
                    + "condition for ending is the opposite of the condition "
                    + "for allowing.")
                .xp(20))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int attempts = 3;",
                    "final int MAX = 3;",
                    "if (attempts < MAX) {",
                    "    System.out.println(\"TRY AGAIN\");",
                    "} else {",
                    "    System.out.println(\"LOCKED\");",
                    "}")
                .accept("LOCKED")
                .hints("Is 3 less than 3?",
                       "Test the boundary value exactly.")
                .explain(
                    "LOCKED. 3 < 3 is false, so the else runs. At the boundary, "
                    + "< and <= give different answers - here, whether the "
                    + "third attempt is the last.")
                .xp(15))
            .recap(
                "    more than, exceeds         >\n"
                + "    at least, reaches, or more >=\n"
                + "    less than, fewer than      <\n"
                + "    at most, no more than      <=\n"
                + "\n"
                + "Ask: what happens AT the number? That decides whether the "
                + "= belongs.")
            .next("Next: testing the edges, because that is where the bugs "
                + "are."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(5), "Testing the Edges", 3)
            .brief(
                "The new password rule passed testing. The tester tried a "
                + "5-character password - rejected - and a 20-character one - "
                + "accepted. Both correct.\n\n"
                + "In production, every 12-character password was rejected. "
                + "The one length that mattered was the one nobody tried.")
            .willLearn("Boundary conditions", "Boundary testing", "Off-by-one errors")
            .whyUseful(
                "Testing is choosing which values to try. Choosing well means "
                + "finding the bugs; choosing badly means confirming the code "
                + "works on the values where it was never going to fail.")
            .concept("Boundary testing",
                "A condition splits values into groups - allowed and refused, "
                + "alert and quiet. Values deep inside a group almost never "
                + "reveal a bug. Values at the EDGE between groups do.\n"
                + "\n"
                + "The BOUNDARY is the value where the answer changes. For a "
                + "rule like length >= 12, test three values:\n"
                + "\n"
                + "    11    just below   should be refused\n"
                + "    12    on it        should be accepted\n"
                + "    13    just above   should be accepted\n"
                + "\n"
                + "If the code wrongly said > 12, only one of those three - the "
                + "12 - would catch it. 5 and 20 never would.\n"
                + "\n"
                + "A mistake of exactly one at a boundary is called an "
                + "OFF-BY-ONE error. It is probably the most common bug in "
                + "programming: > for >=, < for <=, length where it should be "
                + "length - 1.\n"
                + "\n"
                + "Ranges have two boundaries, so test both ends. A valid port "
                + "is 1 to 65535:\n"
                + "\n"
                + "    0      1      65535    65536\n"
                + "    no     yes    yes      no\n"
                + "\n"
                + "Write down, before you run anything, what each boundary "
                + "value SHOULD produce. Deciding after you see the output is "
                + "how wrong answers get accepted as correct.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        final int MIN_LENGTH = 12;",
                "        System.out.println(\"11: \" + (11 >= MIN_LENGTH));",
                "        System.out.println(\"12: \" + (12 >= MIN_LENGTH));",
                "        System.out.println(\"13: \" + (13 >= MIN_LENGTH));",
                "    }",
                "}")
            .exampleOutput("11: false", "12: true", "13: true")
            .lineByLine(
                new String[]{"11",
                    "Just below. Must be refused - and is."},
                new String[]{"12",
                    "Exactly on the boundary. The value that separates >= from "
                    + ">. Must be accepted - and is."},
                new String[]{"13",
                    "Just above. Confirms nothing strange happens past the "
                    + "line."},
                new String[]{"Why not 5 and 20?",
                    "They would print false and true for > 12 as well. They "
                    + "cannot tell a right rule from a wrong one."})
            .predict(new Task(Task.PREDICT,
                    "The code that shipped. A user picks a 12-character "
                    + "password. What does this print?")
                .code(
                    "int len = 12;",
                    "if (len > 12) {",
                    "    System.out.println(\"ACCEPTED\");",
                    "} else {",
                    "    System.out.println(\"TOO SHORT\");",
                    "}")
                .accept("TOO SHORT")
                .hints("Is 12 greater than 12?",
                       "This is the boundary.")
                .explain(
                    "TOO SHORT - for a password that meets the policy exactly. "
                    + "Only a test at the boundary finds this.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "The rule is 'at least 8 characters'. Which set of lengths "
                    + "tests the boundary properly?")
                .choices("1, 8, 100", "7, 8, 9", "8, 16, 32", "0, 4, 8")
                .accept("2", "b")
                .hints(
                    "The boundary is 8.",
                    "Just below, on, just above.")
                .explain(
                    "7, 8, 9: just below, exactly on, just above. The other sets "
                    + "each miss at least one side of the line, so a wrong "
                    + "operator could pass them.")
                .xp(20))
            .objective(
                "Fix the password length check that failed in production.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String password = \"correcthorse\";",
                "        // Policy: at least 12 characters.",
                "        if (password.length() > 12) {",
                "            System.out.println(\"ACCEPTED\");",
                "        } else {",
                "            System.out.println(\"TOO SHORT\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "The test password is exactly 12 characters, and it is being "
                + "refused. Write the corrected if line.")
            .mainTask(new Task(Task.WRITE,
                    "Write the corrected if line for 'at least 12'.")
                .accept("if (password.length() >= 12) {",
                        "if(password.length() >= 12) {",
                        "if (password.length() >= 12){",
                        "if(password.length() >= 12){",
                        "if (12 <= password.length()) {")
                .hints(
                    "The only thing wrong is one character.",
                    "At least includes 12 itself.",
                    "if (password.length() >= 12) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String password = \"correcthorse\";",
                    "        // Policy: at least 12 characters.",
                    "        if (password.length() >= 12) {",
                    "            System.out.println(\"ACCEPTED\");",
                    "        } else {",
                    "            System.out.println(\"TOO SHORT\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "correcthorse is exactly 12 characters, and 12 >= 12 is "
                    + "true, so it is accepted - as the policy requires.\n"
                    + "\n"
                    + "Notice that the starter's test value was chosen to sit on "
                    + "the boundary. That is the whole technique: the bug was "
                    + "invisible with 5 and with 20, and obvious the moment "
                    + "someone tried 12. When you fix an off-by-one, the value "
                    + "that exposed it becomes a test that must keep passing.")
                .explain(
                    "One character: > becomes >=, so that 12 itself is "
                    + "accepted.")
                .xp(25))
            .mistakes(
                new String[]{"Testing only easy values",
                    "Far-from-the-line values pass with right and wrong code "
                    + "alike. Test at the boundary."},
                new String[]{"Testing only one end of a range",
                    "A range has two boundaries. 1 to 65535 needs 0, 1, 65535 "
                    + "and 65536."},
                new String[]{"Deciding the right answer after running",
                    "Write down the expected result first, or the output will "
                    + "persuade you it was right."})
            .cyber(
                "Attackers test boundaries because developers so often do "
                + "not. An upload limit of 10 MB: what about exactly 10 MB, "
                + "or 10 MB and one byte? A field that accepts up to 255 "
                + "characters: what about 256? A discount allowed for "
                + "quantities under 100: what about -1?\n"
                + "\n"
                + "Each boundary is a place where two pieces of code - the one "
                + "that checks and the one that acts - might disagree by one. "
                + "When they do, the value in between passes the check and "
                + "breaks the thing behind it. Buffer overflows, integer "
                + "overflows and authorisation bypasses have all been found by "
                + "someone simply trying the number right next to the limit.")
            .check(new Task(Task.RECALL,
                    "For the rule  x >= 10 , name the three values you would "
                    + "test, lowest first, separated by spaces.")
                .accept("9 10 11", "9, 10, 11", "9,10,11")
                .hints("Just below, on, just above.",
                       "The boundary is 10.")
                .explain(
                    "9 10 11. One on each side of the boundary, and the "
                    + "boundary itself.")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "Valid ports are 1 to 65535. Which value is NOT a boundary "
                    + "test?")
                .choices("0", "1", "8080", "65536")
                .accept("3", "c")
                .hints(
                    "Boundary tests sit right next to the ends of the range.",
                    "Which value is comfortably in the middle?")
                .explain(
                    "8080. It is a perfectly valid port, deep inside the range - "
                    + "right and wrong code both accept it. 0, 1 and 65536 each "
                    + "sit at an edge.")
                .xp(15))
            .recap(
                "The boundary is where the answer changes.\n"
                + "\n"
                + "    rule x >= 12    test 11, 12, 13\n"
                + "    range 1..65535  test 0, 1, 65535, 65536\n"
                + "\n"
                + "Decide the expected answer BEFORE running. Off-by-one "
                + "errors only show at the edge.")
            .next("Next: more than two outcomes."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(6), "Grading Severity", 3)
            .brief(
                "The vulnerability scanner produces a score from 0.0 to 10.0. "
                + "The team does not triage by number; it triages by band: "
                + "CRITICAL, HIGH, MEDIUM, LOW.\n\n"
                + "Four outcomes. if/else only gives you two.")
            .willLearn("if / else if / else", "Order of tests", "Unreachable branches")
            .whyUseful(
                "Grading, classifying and routing all mean choosing one of "
                + "several outcomes. The else-if chain is how it is done - and "
                + "the order you write the tests in decides whether it works.")
            .concept("else if chains",
                "else if adds more tests to an if/else:\n"
                + "\n"
                + "    if (score >= 9.0) {\n"
                + "        CRITICAL\n"
                + "    } else if (score >= 7.0) {\n"
                + "        HIGH\n"
                + "    } else if (score >= 4.0) {\n"
                + "        MEDIUM\n"
                + "    } else {\n"
                + "        LOW\n"
                + "    }\n"
                + "\n"
                + "Java checks the conditions IN ORDER, top to bottom, and runs "
                + "the block of the FIRST one that is true. Then it skips "
                + "everything else in the chain. At most one block runs.\n"
                + "\n"
                + "The final else is optional. With it, exactly one block "
                + "always runs - it catches every case no test matched. "
                + "Without it, possibly none does.\n"
                + "\n"
                + "Because the first true test wins, ORDER MATTERS. A score of "
                + "9.5 passes >= 9.0, >= 7.0 AND >= 4.0. Written in the order "
                + "above, it hits CRITICAL first. Written the other way "
                + "round:\n"
                + "\n"
                + "    if (score >= 4.0) {         9.5 stops here: MEDIUM\n"
                + "    } else if (score >= 7.0) {  never reached by 9.5\n"
                + "    } else if (score >= 9.0) {  never reached by anything\n"
                + "\n"
                + "Anything that could reach the >= 9.0 test has already been "
                + "caught by >= 4.0. That branch is UNREACHABLE - it compiles, "
                + "and it can never run.\n"
                + "\n"
                + "The rule for 'at least' chains: test the HIGHEST threshold "
                + "first and work down. Each test can then rely on the ones "
                + "above it having failed - HIGH does not need to say 'and "
                + "less than 9.0', because anything 9.0 or above never gets "
                + "that far.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        double score = 8.1;",
                "        if (score >= 9.0) {",
                "            System.out.println(\"CRITICAL\");",
                "        } else if (score >= 7.0) {",
                "            System.out.println(\"HIGH\");",
                "        } else if (score >= 4.0) {",
                "            System.out.println(\"MEDIUM\");",
                "        } else {",
                "            System.out.println(\"LOW\");",
                "        }",
                "    }",
                "}")
            .exampleOutput("HIGH")
            .lineByLine(
                new String[]{"score >= 9.0",
                    "8.1 fails the first test, so Java moves on."},
                new String[]{"score >= 7.0",
                    "8.1 passes. HIGH prints, and the rest of the chain is "
                    + "skipped entirely."},
                new String[]{"score >= 4.0",
                    "Also true for 8.1 - but never checked. The chain already "
                    + "stopped."},
                new String[]{"The final else",
                    "Catches everything below 4.0 without needing a test of "
                    + "its own."})
            .predict(new Task(Task.PREDICT,
                    "The same chain as the example, with score = 9.5. What "
                    + "does it print?")
                .code(
                    "double score = 9.5;",
                    "if (score >= 9.0) {",
                    "    System.out.println(\"CRITICAL\");",
                    "} else if (score >= 7.0) {",
                    "    System.out.println(\"HIGH\");",
                    "} else {",
                    "    System.out.println(\"LOWER\");",
                    "}")
                .accept("CRITICAL")
                .hints("Check the tests top to bottom.",
                       "The first true one wins, and only that one prints.")
                .explain(
                    "CRITICAL - and only CRITICAL. 9.5 would pass the second "
                    + "test too, but the chain stops at the first true one.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "Someone wrote the chain upside down. What does THIS print "
                    + "for 9.5?")
                .code(
                    "double score = 9.5;",
                    "if (score >= 4.0) {",
                    "    System.out.println(\"MEDIUM\");",
                    "} else if (score >= 7.0) {",
                    "    System.out.println(\"HIGH\");",
                    "} else if (score >= 9.0) {",
                    "    System.out.println(\"CRITICAL\");",
                    "}")
                .accept("MEDIUM")
                .hints(
                    "Which test does Java check first?",
                    "Is 9.5 at least 4.0?")
                .explain(
                    "MEDIUM. The first test, >= 4.0, is true for 9.5, so the "
                    + "chain stops there. The CRITICAL branch can never run for "
                    + "any score - everything that could reach it is caught "
                    + "earlier. It compiles, looks thorough, and is dead code.")
                .xp(30))
            .objective(
                "Complete the severity grading chain.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        double score = 7.4;",
                "        if (score >= 9.0) {",
                "            System.out.println(\"CRITICAL\");",
                "        // write the HIGH line here: 7.0 and above",
                "            System.out.println(\"HIGH\");",
                "        } else if (score >= 4.0) {",
                "            System.out.println(\"MEDIUM\");",
                "        } else {",
                "            System.out.println(\"LOW\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the missing line that closes the CRITICAL block and "
                + "opens the HIGH one, for scores of 7.0 and above.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line that starts the HIGH branch.")
                .accept("} else if (score >= 7.0) {", "}else if (score >= 7.0) {",
                        "} else if(score >= 7.0) {", "} else if (score >= 7.0){",
                        "} else if (score >= 7) {")
                .hints(
                    "It closes one block and opens the next, like } else {.",
                    "But this branch has a test, so it is else if with a "
                    + "condition.",
                    "} else if (score >= 7.0) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        double score = 7.4;",
                    "        if (score >= 9.0) {",
                    "            System.out.println(\"CRITICAL\");",
                    "        } else if (score >= 7.0) {",
                    "            System.out.println(\"HIGH\");",
                    "        } else if (score >= 4.0) {",
                    "            System.out.println(\"MEDIUM\");",
                    "        } else {",
                    "            System.out.println(\"LOW\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "The brace closes CRITICAL's block, and else if opens a new "
                    + "test that is only reached when the one above failed. 7.4 "
                    + "fails >= 9.0, passes >= 7.0, and prints HIGH.\n"
                    + "\n"
                    + "The condition does not need 'and less than 9.0'. The "
                    + "chain's order already guarantees it: any score that "
                    + "reaches this line has already failed the CRITICAL test. "
                    + "That is why the highest threshold goes first - each "
                    + "branch gets to lean on everything above it.")
                .explain(
                    "Close the previous block and open a tested branch: "
                    + "} else if (...) {")
                .xp(30))
            .mistakes(
                new String[]{"Lowest threshold first",
                    "With >= tests, the lowest catches everything and the "
                    + "higher branches become unreachable. Start at the top."},
                new String[]{"Separate ifs instead of a chain",
                    "Three independent ifs can ALL run. A 9.5 would print "
                    + "CRITICAL, HIGH and MEDIUM."},
                new String[]{"No final else",
                    "Then some values match nothing and silently fall through. "
                    + "Decide what they should do."})
            .cyber(
                "The standard vulnerability scoring system, CVSS, publishes "
                + "exactly this chain: 9.0 and above critical, 7.0 to 8.9 high, "
                + "4.0 to 6.9 medium, 0.1 to 3.9 low, 0.0 none. Patch "
                + "deadlines, change freezes and who gets paged at night all "
                + "hang off the band, not the number.\n"
                + "\n"
                + "A chain in the wrong order does not crash. It files every "
                + "critical vulnerability as medium, and the backlog looks "
                + "calm. The unreachable branch is the dangerous kind of dead "
                + "code: it reassures anyone who reads it that critical "
                + "findings are handled, while guaranteeing that they are "
                + "not.")
            .check(new Task(Task.PREDICT,
                    "What does the example's chain print for score = 0.0?")
                .code(
                    "double score = 0.0;",
                    "if (score >= 9.0) {",
                    "    System.out.println(\"CRITICAL\");",
                    "} else if (score >= 4.0) {",
                    "    System.out.println(\"MEDIUM\");",
                    "} else {",
                    "    System.out.println(\"LOW\");",
                    "}")
                .accept("LOW")
                .hints("Does 0.0 pass either test?",
                       "The else catches everything the tests did not.")
                .explain(
                    "LOW. Neither test is true, so the final else runs. Without "
                    + "that else, nothing at all would print.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "In an if / else if / else chain, how many blocks run?")
                .choices("Every block whose test is true", "Exactly one",
                         "At most one", "All of them")
                .accept("2", "b")
                .hints("The chain stops at the first true test.",
                       "And the final else catches the rest.")
                .explain(
                    "Exactly one - with a final else there is always a block to "
                    + "run, and the chain stops at the first match. Without the "
                    + "final else it would be at most one.")
                .xp(15))
            .recap(
                "    if (a) { ... }\n"
                + "    else if (b) { ... }\n"
                + "    else { ... }\n"
                + "\n"
                + "Tested top to bottom. The FIRST true test wins; the rest "
                + "are skipped.\n"
                + "\n"
                + "For 'at least' bands, start with the highest. A wrong order "
                + "makes branches unreachable, silently.")
            .next("Next: conditions that need two things to be true."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(7), "Both Must Be True", 3)
            .brief(
                "Remote access needs a correct password AND a passed second "
                + "factor. The firewall accepts a port if it is at least 1 AND "
                + "at most 65535.\n\n"
                + "One condition at a time cannot say 'and'.")
            .willLearn("Logical operators", "&&", "Range checks")
            .whyUseful(
                "Real rules have several parts. && joins them so that all "
                + "must hold - the shape of every multi-factor check and every "
                + "range test you will write.")
            .concept("&& (AND)",
                "&& joins two booleans. The result is true only when BOTH are "
                + "true:\n"
                + "\n"
                + "    a       b       a && b\n"
                + "    true    true    true\n"
                + "    true    false   false\n"
                + "    false   true    false\n"
                + "    false   false   false\n"
                + "\n"
                + "    if (passwordOk && mfaOk) {\n"
                + "\n"
                + "Comparisons run BEFORE &&, so this needs no extra "
                + "brackets:\n"
                + "\n"
                + "    port >= 1 && port <= 65535\n"
                + "\n"
                + "That is a RANGE CHECK: both ends of the range, joined. It "
                + "has to be written out in full. The maths shorthand does "
                + "not work:\n"
                + "\n"
                + "    1 <= port <= 65535    does NOT compile\n"
                + "\n"
                + "Java works out 1 <= port first, gets a boolean, and then "
                + "tries boolean <= 65535, which means nothing.\n"
                + "\n"
                + "&& can join more than two: a && b && c is true only when "
                + "all three are.\n"
                + "\n"
                + "Every extra && makes a condition HARDER to satisfy. For an "
                + "allow rule, that is usually what you want.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        boolean passwordOk = true;",
                "        boolean mfaOk = false;",
                "        if (passwordOk && mfaOk) {",
                "            System.out.println(\"ACCESS GRANTED\");",
                "        } else {",
                "            System.out.println(\"ACCESS DENIED\");",
                "        }",
                "    }",
                "}")
            .exampleOutput("ACCESS DENIED")
            .lineByLine(
                new String[]{"passwordOk && mfaOk",
                    "true && false is false. One failed factor is enough to "
                    + "deny."},
                new String[]{"The else",
                    "Runs for every combination except both true - three cases "
                    + "out of four."},
                new String[]{"The safe default",
                    "Denied unless every factor passes. That is what && in an "
                    + "allow rule gives you."},
                new String[]{"Swapping the two",
                    "mfaOk && passwordOk gives the same answer. For now the "
                    + "order does not change the result; mission 10 shows "
                    + "when it matters."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "boolean patched = true;",
                    "boolean monitored = false;",
                    "System.out.println(patched && monitored);")
                .accept("false")
                .hints("&& needs both.",
                       "Is monitored true?")
                .explain(
                    "false. One side is false, so the whole && is false.")
                .xp(10))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "int port = 8080;",
                    "if (1 <= port <= 65535) {",
                    "    System.out.println(\"VALID\");",
                    "}")
                .accept("2", "line 2")
                .hints(
                    "Work out 1 <= port first. What type is the result?",
                    "Can a boolean be compared with 65535?")
                .explain(
                    "Line 2. 1 <= port becomes true, and then Java is asked "
                    + "whether true <= 65535 - a boolean against an int. 'bad "
                    + "operand types'. Write both halves: port >= 1 && port <= "
                    + "65535.")
                .xp(25))
            .objective(
                "Reject port numbers that cannot exist.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int port = 70000;",
                "        // write the if line here: 1 to 65535 inclusive",
                "            System.out.println(\"PORT OK\");",
                "        } else {",
                "            System.out.println(\"INVALID PORT\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line so PORT OK prints only for ports from 1 to "
                + "65535, both ends included.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line: port is at least 1 and at most 65535.")
                .accept("if (port >= 1 && port <= 65535) {",
                        "if(port >= 1 && port <= 65535) {",
                        "if (port >= 1 && port <= 65535){",
                        "if (port <= 65535 && port >= 1) {",
                        "if (port > 0 && port < 65536) {",
                        "if (port > 0 && port <= 65535) {",
                        "if (port >= 1 && port < 65536) {")
                .hints(
                    "Two conditions, and both must hold.",
                    "Write each end in full. There is no 1 <= port <= 65535.",
                    "if (port >= 1 && port <= 65535) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int port = 70000;",
                    "        if (port >= 1 && port <= 65535) {",
                    "            System.out.println(\"PORT OK\");",
                    "        } else {",
                    "            System.out.println(\"INVALID PORT\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "70000 passes port >= 1 and fails port <= 65535, so the && "
                    + "is false and INVALID PORT prints. 0 would fail the "
                    + "first half; 443 passes both.\n"
                    + "\n"
                    + "Check the boundaries from mission 5: 0 and 65536 are "
                    + "refused, 1 and 65535 accepted. A range has two edges and "
                    + "a range check has two halves - forget either and one "
                    + "whole side of invalid values walks straight in.")
                .explain(
                    "Both ends of the range, joined with &&.")
                .xp(30))
            .mistakes(
                new String[]{"1 <= x <= 10",
                    "Does not compile. Write x >= 1 && x <= 10."},
                new String[]{"A single & ",
                    "& is a different operator. For conditions, use &&."},
                new String[]{"Checking only one end",
                    "port <= 65535 alone accepts 0 and -4000."})
            .cyber(
                "Multi-factor authentication is && written into policy: "
                + "something you know && something you have. Its strength "
                + "comes from requiring every part.\n"
                + "\n"
                + "Range checks are the other half of this mission, and a "
                + "one-sided range check is a classic flaw. A web shop checks "
                + "that the quantity ordered is at most 100 and forgets it "
                + "must be at least 1 - so -5 items are ordered and the "
                + "account is credited. A server checks a requested offset is "
                + "below the file size but not that it is above zero, and "
                + "reads memory before the buffer. Every range has two edges, "
                + "and attackers try both.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int hour = 23;",
                    "System.out.println(hour >= 8 && hour <= 18);")
                .accept("false")
                .hints("Is 23 at least 8? Is it at most 18?",
                       "Both must be true.")
                .explain(
                    "false. 23 >= 8 is true, but 23 <= 18 is false, and && "
                    + "needs both.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Which combination makes  a && b  true?")
                .choices("a true, b false", "a false, b true",
                         "a true, b true", "Either one true")
                .accept("3", "c")
                .hints("AND.", "Both.")
                .explain("Only both true. Any false makes && false.")
                .xp(10))
            .recap(
                "    a && b    true only if both are true\n"
                + "\n"
                + "Range check, both ends in full:\n"
                + "\n"
                + "    x >= low && x <= high\n"
                + "\n"
                + "1 <= x <= 10 does not compile. Every && makes a condition "
                + "harder to satisfy.")
            .next("Next: conditions where either one will do."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(8), "Either Will Do", 3)
            .brief(
                "Logins outside office hours get a second look: anything "
                + "before 07:00 OR after 19:00.\n\n"
                + "No single hour is both before 7 and after 19. An && here "
                + "would never fire. This rule needs the other joining "
                + "operator.")
            .willLearn("Logical operators", "||", "Mixing && and ||")
            .whyUseful(
                "Alert rules usually fire on any one of several signals, and "
                + "'outside a range' is always an or. Knowing when to use || "
                + "instead of && - and what happens when you mix them - is "
                + "central to writing rules that do what they say.")
            .concept("|| (OR)",
                "|| joins two booleans. The result is true when AT LEAST ONE "
                + "is true:\n"
                + "\n"
                + "    a       b       a || b\n"
                + "    true    true    true\n"
                + "    true    false   true\n"
                + "    false   true    true\n"
                + "    false   false   false\n"
                + "\n"
                + "The symbol is two vertical bars, usually on the key beside "
                + "the left shift or above ENTER.\n"
                + "\n"
                + "Note the first row: both true gives true. Java's or means "
                + "'one or the other or both'.\n"
                + "\n"
                + "OUTSIDE a range is an ||:\n"
                + "\n"
                + "    hour < 7 || hour > 19     outside office hours\n"
                + "    hour >= 7 && hour <= 19   inside office hours\n"
                + "\n"
                + "Mixing up the two gives a condition that can never be "
                + "true - hour < 7 && hour > 19 - or one that is always true - "
                + "hour > 7 || hour < 19.\n"
                + "\n"
                + "Every extra || makes a condition EASIER to satisfy.\n"
                + "\n"
                + "MIXING. && binds tighter than ||, just as * binds tighter "
                + "than +:\n"
                + "\n"
                + "    a || b && c      means   a || (b && c)\n"
                + "\n"
                + "That is rarely obvious to a reader, and sometimes not what "
                + "the writer meant. Whenever && and || appear together, add "
                + "brackets so the grouping is written down.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int hour = 4;",
                "        if (hour < 7 || hour > 19) {",
                "            System.out.println(\"OUT OF HOURS LOGIN - review\");",
                "        } else {",
                "            System.out.println(\"office hours\");",
                "        }",
                "    }",
                "}")
            .exampleOutput("OUT OF HOURS LOGIN - review")
            .lineByLine(
                new String[]{"hour < 7",
                    "4 < 7 is true."},
                new String[]{"||",
                    "One true side is enough. The whole condition is true."},
                new String[]{"hour > 19",
                    "False for 4, but it does not matter."},
                new String[]{"hour = 12",
                    "Both sides false, so the else runs. Only a time inside "
                    + "the range makes || false."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "boolean onWatchlist = false;",
                    "boolean newCountry = true;",
                    "System.out.println(onWatchlist || newCountry);")
                .accept("true")
                .hints("|| needs at least one.",
                       "Is either of them true?")
                .explain("true. newCountry is true, and one is enough.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "&& and || together, no brackets. What does this print?")
                .code("System.out.println(true || false && false);")
                .accept("true")
                .hints(
                    "&& binds tighter than ||, so it is worked out first.",
                    "false && false first. Then true || that.")
                .explain(
                    "true. && goes first: false && false is false. Then "
                    + "true || false is true.\n\n"
                    + "Reading strictly left to right - (true || false) && "
                    + "false - would give false. Same symbols, opposite answer, "
                    + "and that is exactly why mixed conditions get brackets.")
                .xp(30))
            .objective(
                "Flag logins outside office hours.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int hour = 22;",
                "        // write the if line here: before 7 or after 19",
                "            System.out.println(\"REVIEW LOGIN\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line: REVIEW LOGIN prints when hour is less than "
                + "7 or greater than 19.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line: hour before 7 or after 19.")
                .accept("if (hour < 7 || hour > 19) {", "if(hour < 7 || hour > 19) {",
                        "if (hour < 7 || hour > 19){",
                        "if (hour > 19 || hour < 7) {")
                .hints(
                    "No hour is both, so this is not &&.",
                    "Either condition on its own is enough: ||.",
                    "if (hour < 7 || hour > 19) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int hour = 22;",
                    "        if (hour < 7 || hour > 19) {",
                    "            System.out.println(\"REVIEW LOGIN\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "22 < 7 is false but 22 > 19 is true, and || needs only one, "
                    + "so the login is flagged. At 12 both halves are false and "
                    + "nothing prints.\n"
                    + "\n"
                    + "With && the same halves describe an hour that is "
                    + "simultaneously before 7 and after 19. No such hour exists, "
                    + "so the rule would compile, deploy, and never fire once. "
                    + "A rule that never fires looks exactly like a network "
                    + "where nothing is happening.")
                .explain(
                    "Outside a range is an ||: either end is enough.")
                .xp(30))
            .mistakes(
                new String[]{"&& for 'outside'",
                    "x < 7 && x > 19 can never be true. Outside a range is ||."},
                new String[]{"|| for 'inside'",
                    "x > 7 || x < 19 is true for every number."},
                new String[]{"Mixing without brackets",
                    "a || b && c is a || (b && c). Write the brackets so nobody "
                    + "has to remember."})
            .cyber(
                "|| is the natural shape of a detection rule - alert if this "
                + "OR that OR the other - because any single signal is worth a "
                + "look.\n"
                + "\n"
                + "It is the dangerous shape for an ALLOW rule. "
                + "if (isAdmin || fromOfficeNetwork) lets in every admin from "
                + "anywhere and everyone at all from the office network. An "
                + "allow rule built with || is only as strong as its weakest "
                + "branch, and attackers look for the weakest branch. A rule "
                + "that was meant to be 'admin, and on the office network' "
                + "but was written with || is one of the most common access "
                + "control bugs there is.")
            .check(new Task(Task.CHOICE,
                    "Which condition can NEVER be true?")
                .choices("x < 5 || x > 10", "x > 5 && x < 10",
                         "x < 5 && x > 10", "x > 5 || x < 10")
                .accept("3", "c")
                .hints(
                    "Look for one that needs a number to be two impossible "
                    + "things at once.",
                    "Can a number be below 5 and above 10?")
                .explain(
                    "x < 5 && x > 10 - no number is both. And option D is the "
                    + "opposite failure: every number is greater than 5 or "
                    + "less than 10, so it is always true.")
                .xp(20))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int hour = 19;",
                    "System.out.println(hour < 7 || hour > 19);")
                .accept("false")
                .hints("Test the boundary exactly.",
                       "Is 19 greater than 19?")
                .explain(
                    "false. 19 is not greater than 19, so 19:00 counts as "
                    + "office hours under this rule. Whether it should is a "
                    + "policy question - and exactly the kind to ask.")
                .xp(15))
            .recap(
                "    a || b    true if at least one is true\n"
                + "\n"
                + "Inside a range:   x >= low && x <= high\n"
                + "Outside a range:  x < low || x > high\n"
                + "\n"
                + "&& binds tighter than ||. When they mix, write brackets.")
            .next("Next: turning a condition round."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(9), "Not", 3)
            .brief(
                "The login service should only let a user in when the account "
                + "is NOT locked and the second factor passed.\n\n"
                + "You have 'locked' as a boolean. You need its opposite.")
            .willLearn("Logical operators", "!", "Naming booleans positively")
            .whyUseful(
                "Half of the conditions you will write are about something "
                + "being absent: not locked, not expired, not on the list. ! "
                + "is how you say it - and how easily it can be misread is "
                + "worth knowing.")
            .concept("! (NOT)",
                "! flips a boolean:\n"
                + "\n"
                + "    !true     false\n"
                + "    !false    true\n"
                + "\n"
                + "    if (!locked) {\n"
                + "\n"
                + "Read the ! aloud as 'not': if not locked.\n"
                + "\n"
                + "! binds TIGHTER than anything else in a condition - tighter "
                + "than comparisons. So it grabs only what is right next to "
                + "it:\n"
                + "\n"
                + "    !failures > 5      does NOT compile\n"
                + "\n"
                + "Java tries !failures first, and failures is an int. You "
                + "cannot 'not' a number. To negate a comparison, bracket it - "
                + "or better, just use the opposite operator:\n"
                + "\n"
                + "    !(failures > 5)    works\n"
                + "    failures <= 5      clearer\n"
                + "\n"
                + "!= is not the ! operator; it is one symbol meaning 'not "
                + "equal'.\n"
                + "\n"
                + "NAMING. ! reads cleanly on a positive name - !locked, "
                + "!expired. On a negative name it turns into a puzzle:\n"
                + "\n"
                + "    if (!notDisabled)    means... enabled? disabled?\n"
                + "\n"
                + "Name booleans for the positive fact - locked, enabled, "
                + "patched - and let ! do the negating.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        boolean locked = false;",
                "        boolean mfaPassed = true;",
                "        if (!locked && mfaPassed) {",
                "            System.out.println(\"LOGIN PERMITTED\");",
                "        } else {",
                "            System.out.println(\"LOGIN REFUSED\");",
                "        }",
                "    }",
                "}")
            .exampleOutput("LOGIN PERMITTED")
            .lineByLine(
                new String[]{"!locked",
                    "locked is false, so !locked is true. 'Not locked.'"},
                new String[]{"&& mfaPassed",
                    "Also true. Both halves hold."},
                new String[]{"The ! binds first",
                    "It applies only to locked, not to the whole condition."},
                new String[]{"Reading it aloud",
                    "'If not locked and MFA passed' - a positive name makes "
                    + "the whole line read as a sentence."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "boolean patched = false;",
                    "boolean exposed = true;",
                    "System.out.println(!patched && exposed);")
                .accept("true")
                .hints("! first: what is !patched?",
                       "Then && with exposed.")
                .explain(
                    "true. !patched is true, exposed is true, and true && true "
                    + "is true. An unpatched, exposed host - top of the list.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "int failures = 3;",
                    "boolean ok = !(failures > 5);",
                    "boolean bad = !failures > 5;",
                    "System.out.println(ok);")
                .accept("3", "line 3")
                .hints(
                    "! binds tighter than >.",
                    "On line 3, what is ! applied to?")
                .explain(
                    "Line 3. ! grabs failures on its own, before the > runs, "
                    + "and failures is an int. 'bad operand type int for unary "
                    + "operator'. Line 2 is fine, because the brackets make the "
                    + "comparison happen first.")
                .xp(25))
            .objective(
                "Permit a login only for an unlocked account with MFA passed.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        boolean accountLocked = false;",
                "        boolean mfaPassed = true;",
                "        // write the if line here",
                "            System.out.println(\"LOGIN PERMITTED\");",
                "        } else {",
                "            System.out.println(\"LOGIN REFUSED\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line: permitted when the account is NOT locked "
                + "and MFA passed.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line: not accountLocked, and mfaPassed.")
                .accept("if (!accountLocked && mfaPassed) {",
                        "if(!accountLocked && mfaPassed) {",
                        "if (!accountLocked && mfaPassed){",
                        "if (mfaPassed && !accountLocked) {")
                .hints(
                    "Two conditions, both required: &&.",
                    "'Not locked' is ! in front of the boolean.",
                    "if (!accountLocked && mfaPassed) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        boolean accountLocked = false;",
                    "        boolean mfaPassed = true;",
                    "        if (!accountLocked && mfaPassed) {",
                    "            System.out.println(\"LOGIN PERMITTED\");",
                    "        } else {",
                    "            System.out.println(\"LOGIN REFUSED\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "!accountLocked is true because the account is not locked, "
                    + "mfaPassed is true, and && needs both. Lock the account "
                    + "or fail MFA and the else refuses.\n"
                    + "\n"
                    + "The permission lives in the if and the refusal in the "
                    + "else, so the program fails closed - there is exactly "
                    + "one combination that gets in, and it is the one the "
                    + "policy describes.")
                .explain(
                    "! in front of the boolean, && to require both.")
                .xp(30))
            .mistakes(
                new String[]{"!x > 5",
                    "! binds first and x is not a boolean. Write x <= 5, or "
                    + "!(x > 5)."},
                new String[]{"Negative names",
                    "!notLocked is hard to read correctly. Name booleans for "
                    + "the positive fact."},
                new String[]{"Double negatives",
                    "!!x is just x. If you find one, simplify it."})
            .cyber(
                "! is where blocklists and allowlists meet.\n"
                + "\n"
                + "A BLOCKLIST allows everything except what is on the list: "
                + "if (!onBlocklist) allow. An ALLOWLIST allows only what is "
                + "on the list: if (onAllowlist) allow. They look like mirror "
                + "images. They are not.\n"
                + "\n"
                + "A blocklist lets through every bad thing nobody has listed "
                + "yet - every new malware hash, every new phishing domain, "
                + "every file extension somebody forgot. An allowlist refuses "
                + "those by default. When you catch yourself writing "
                + "if (!something) to grant access, ask whether you are "
                + "building a blocklist, and whether an allowlist would do "
                + "the job instead.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "boolean a = false;",
                    "boolean b = false;",
                    "System.out.println(!(a || b));")
                .accept("true")
                .hints("Brackets first: a || b.",
                       "Then flip it.")
                .explain(
                    "true. a || b is false, and ! flips it to true. 'Neither a "
                    + "nor b.'")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "Which is the clearest way to write 'failures is not more "
                    + "than 5'?")
                .choices("!failures > 5", "!(failures > 5)", "failures <= 5",
                         "failures !> 5")
                .accept("3", "c")
                .hints("One does not compile, one is not Java at all.",
                       "Every comparison has an opposite operator.")
                .explain(
                    "failures <= 5. !(failures > 5) means the same but makes the "
                    + "reader do the flip. The first does not compile and !> is "
                    + "not an operator.")
                .xp(15))
            .recap(
                "    !x    the opposite of x\n"
                + "\n"
                + "! binds tightest, to what is right next to it.\n"
                + "\n"
                + "    !x > 5       does not compile\n"
                + "    x <= 5       say it directly\n"
                + "\n"
                + "Name booleans positively. Prefer allowlists to !onBlocklist.")
            .next("Next: why the order of an && can stop a crash."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(10), "Stopping Early", 4)
            .brief(
                "The log parser checks whether a line is an error by looking "
                + "at its first character. It works on every line - until an "
                + "empty one arrives, charAt(0) finds nothing there, and the "
                + "parser crashes.\n\n"
                + "The fix is not a new tool. It is putting the check you "
                + "already have in the right order.")
            .willLearn("Short-circuit evaluation", "Guard conditions", "Check, then use")
            .whyUseful(
                "Campaign 01 left several crashes waiting: charAt past the end, "
                + "substring with -1, parseInt on nothing. Short-circuiting is "
                + "how one condition protects the next, and it is how most of "
                + "those crashes are prevented.")
            .concept("Short-circuit evaluation",
                "&& and || do not always look at both sides.\n"
                + "\n"
                + "    a && b    if a is false, the answer is false\n"
                + "              whatever b is - so b is never evaluated\n"
                + "\n"
                + "    a || b    if a is true, the answer is true\n"
                + "              whatever b is - so b is never evaluated\n"
                + "\n"
                + "This is called SHORT-CIRCUIT evaluation, and it is "
                + "guaranteed: Java always works left to right and always "
                + "stops as soon as the answer is known.\n"
                + "\n"
                + "It turns the left side into a GUARD for the right:\n"
                + "\n"
                + "    line.length() > 0 && line.charAt(0) == 'E'\n"
                + "\n"
                + "If the line is empty, the left side is false and charAt is "
                + "never called. No crash. Swap the order:\n"
                + "\n"
                + "    line.charAt(0) == 'E' && line.length() > 0\n"
                + "\n"
                + "and charAt runs first, on an empty line, and crashes before "
                + "the length check ever gets a turn. Same two conditions, "
                + "same &&, opposite outcome.\n"
                + "\n"
                + "The rule: put the check that makes the rest SAFE on the "
                + "left. Check, then use.\n"
                + "\n"
                + "The same applies to arithmetic. Dividing an int by zero "
                + "crashes with an ArithmeticException, so:\n"
                + "\n"
                + "    count != 0 && total / count > 10\n"
                + "\n"
                + "never divides when count is 0.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String line = \"\";",
                "        if (line.length() > 0 && line.charAt(0) == 'E') {",
                "            System.out.println(\"ERROR LINE\");",
                "        } else {",
                "            System.out.println(\"not an error line\");",
                "        }",
                "    }",
                "}")
            .exampleOutput("not an error line")
            .lineByLine(
                new String[]{"line.length() > 0",
                    "0 > 0 is false. With &&, the answer is now certain."},
                new String[]{"line.charAt(0) == 'E'",
                    "Never evaluated. Java stopped at the false. That is the "
                    + "only reason there is no crash."},
                new String[]{"'E'",
                    "Single quotes: charAt gives a char, so it is compared "
                    + "with a char. == is fine for chars."},
                new String[]{"The else",
                    "Runs for empty lines and for lines not starting with E "
                    + "alike."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String line = \"W disk nearly full\";",
                    "if (line.length() > 0 && line.charAt(0) == 'E') {",
                    "    System.out.println(\"ERROR\");",
                    "} else {",
                    "    System.out.println(\"OTHER\");",
                    "}")
                .accept("OTHER")
                .hints("The line is not empty, so the second check does run.",
                       "What is its first character?")
                .explain(
                    "OTHER. The guard passes, charAt(0) is 'W', and 'W' == 'E' "
                    + "is false.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "text might be empty. Which condition can NEVER crash?")
                .choices(
                    "text.charAt(0) == '#' && text.length() > 0",
                    "text.length() > 0 && text.charAt(0) == '#'",
                    "text.charAt(0) == '#' || text.length() == 0",
                    "text.charAt(0) == '#'")
                .accept("2", "b")
                .hints(
                    "Java evaluates left to right and stops early.",
                    "Which one checks the length BEFORE calling charAt?")
                .explain(
                    "B. The length check runs first, and when it is false && "
                    + "stops before charAt. Every other option calls charAt(0) "
                    + "first, so an empty String crashes them before anything "
                    + "else is looked at.")
                .xp(25))
            .objective(
                "Skip comment lines in a configuration file without crashing "
                + "on blank ones.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String text = \"\";",
                "        // write the if line here",
                "            System.out.println(\"SKIP COMMENT\");",
                "        } else {",
                "            System.out.println(\"PROCESS\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "A comment line starts with #. Write the if line that is true "
                + "when text starts with '#', guarded so that an empty text "
                + "cannot crash it.")
            .mainTask(new Task(Task.WRITE,
                    "Write the guarded if line: non-empty and starts with '#'.")
                .accept("if (text.length() > 0 && text.charAt(0) == '#') {",
                        "if(text.length() > 0 && text.charAt(0) == '#') {",
                        "if (text.length() > 0 && text.charAt(0) == '#'){",
                        "if (text.length() >= 1 && text.charAt(0) == '#') {",
                        "if (text.length() != 0 && text.charAt(0) == '#') {")
                .hints(
                    "The check that makes charAt safe goes on the left.",
                    "The guard is a length check. Then && and the character "
                    + "test.",
                    "if (text.length() > 0 && text.charAt(0) == '#') {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String text = \"\";",
                    "        if (text.length() > 0 && text.charAt(0) == '#') {",
                    "            System.out.println(\"SKIP COMMENT\");",
                    "        } else {",
                    "            System.out.println(\"PROCESS\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "text is empty, so text.length() > 0 is false and && stops. "
                    + "charAt(0) is never called and the program prints PROCESS "
                    + "instead of crashing. With \"# timeout=30\" both checks "
                    + "run and the line is skipped.\n"
                    + "\n"
                    + "Written the other way round, the starter's own test value "
                    + "- an empty line - would crash it on the first run. Real "
                    + "config files have blank lines; so do real logs; so does "
                    + "anything a person types. The guard is not defensive "
                    + "extra. It is the difference between a parser and a "
                    + "parser that works.")
                .explain(
                    "Guard first, then use: the length check makes charAt safe.")
                .xp(35))
            .mistakes(
                new String[]{"The guard on the right",
                    "a.charAt(0) == 'x' && a.length() > 0 crashes on \"\" "
                    + "before the guard runs."},
                new String[]{"Using || where the guard needs &&",
                    "length() > 0 || charAt(0) == 'E' still calls charAt when "
                    + "the length is 0."},
                new String[]{"Relying on luck",
                    "Code that works on every test line but has no guard is "
                    + "one blank line from a crash."})
            .cyber(
                "'Check, then use' is one of the oldest principles in secure "
                + "coding. Before reading index 0, check there is an index 0. "
                + "Before dividing, check the divisor is not zero. Before "
                + "cutting at a separator, check the separator was found.\n"
                + "\n"
                + "Short-circuiting is how Java lets you write the check and "
                + "the use in one condition, with the guarantee that the use "
                + "never happens unless the check passed. Parsers that skip "
                + "that guarantee are how a single blank, truncated or "
                + "deliberately malformed line takes a monitoring pipeline "
                + "down - an attack that needs no exploit at all, only an "
                + "input the developer did not imagine.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int count = 0;",
                    "int total = 50;",
                    "boolean busy = count != 0 && total / count > 10;",
                    "System.out.println(busy);")
                .accept("false")
                .hints(
                    "Is count != 0?",
                    "If the left of && is false, is the division ever done?")
                .explain(
                    "false - and no crash. count != 0 is false, so && stops "
                    + "and total / count, which would divide by zero, never "
                    + "runs.")
                .xp(25))
            .check(new Task(Task.CHOICE,
                    "In  a || b , when is b NOT evaluated?")
                .choices("When a is false", "When a is true", "Never",
                         "When b would crash")
                .accept("2", "b")
                .hints("With ||, one true is enough.",
                       "If a is true, is the answer already known?")
                .explain(
                    "When a is true. The answer is true whatever b is, so Java "
                    + "stops. For &&, it is the other way round: it stops when "
                    + "a is false.")
                .xp(15))
            .recap(
                "    a && b    b is skipped if a is false\n"
                + "    a || b    b is skipped if a is true\n"
                + "\n"
                + "Put the guard on the LEFT:\n"
                + "\n"
                + "    s.length() > 0 && s.charAt(0) == 'E'\n"
                + "    n != 0 && total / n > 10\n"
                + "\n"
                + "Check, then use.")
            .next("Next: comparing text - and why == is the wrong tool."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(11), "Same Text, Different Box", 4)
            .brief(
                "The admin console compares the typed username with the stored "
                + "one using ==. In testing it worked every time. In production "
                + "it refused every administrator who typed their name in "
                + "capitals, even after the tool lower-cased it.\n\n"
                + "For text, == asks the wrong question. This is one of the most "
                + "famous bugs in Java, and a real security one.")
            .willLearn("String comparison", ".equals()", "== on objects")
            .whyUseful(
                "Every login, every allowlist, every command match compares "
                + "text. Getting it wrong either locks out the right people or "
                + "lets in the wrong ones - and == can do both, depending on "
                + "where the text came from.")
            .concept("== against .equals()",
                "An int variable holds a number. A String variable does not "
                + "hold text directly - it holds a REFERENCE: directions to "
                + "where the text is kept in memory. (Campaign 06 goes into "
                + "this properly.)\n"
                + "\n"
                + "So for Strings, == compares the directions, not the text:\n"
                + "\n"
                + "    a == b         same place in memory?\n"
                + "    a.equals(b)    same characters?\n"
                + "\n"
                + "Two Strings with identical characters can live in two "
                + "different places. Then == says false while equals says "
                + "true:\n"
                + "\n"
                + "    String stored = \"admin\";\n"
                + "    String typed = \"ADMIN\".toLowerCase();\n"
                + "    stored == typed          false\n"
                + "    stored.equals(typed)     true\n"
                + "\n"
                + "toLowerCase built a brand-new String. It has the same "
                + "characters as stored, in a different place.\n"
                + "\n"
                + "WHY THE BUG HIDES. Java reuses identical text written in "
                + "quotes in your code, so \"admin\" == \"admin\" happens to be "
                + "true. Tests that use literals pass. Text that arrives at "
                + "run time - typed, read from a file, built by a method - "
                + "lives somewhere new, and == fails.\n"
                + "\n"
                + "The rule has no exceptions: compare text with equals. "
                + "== is for numbers, chars and booleans.\n"
                + "\n"
                + "To test 'not equal', put ! in front: "
                + "!typed.equals(stored).")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String stored = \"admin\";",
                "        String typed = \"ADMIN\".toLowerCase();",
                "        System.out.println(\"==     : \" + (typed == stored));",
                "        System.out.println(\"equals : \" + typed.equals(stored));",
                "    }",
                "}")
            .exampleOutput("==     : false", "equals : true")
            .lineByLine(
                new String[]{"String typed = \"ADMIN\".toLowerCase();",
                    "Builds a new String, admin, somewhere new in memory - just "
                    + "like typed input would."},
                new String[]{"typed == stored",
                    "Compares where the two Strings live. Different places, so "
                    + "false - even though the text matches."},
                new String[]{"typed.equals(stored)",
                    "Compares the characters, one by one. All five match: true."},
                new String[]{"Which one a login check needs",
                    "Always equals. A user cares whether the text matches, never "
                    + "where Java happened to put it."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String a = \"jsmith\";",
                    "String b = \"JSMITH\".toLowerCase();",
                    "System.out.println(a == b);")
                .accept("false")
                .hints(
                    "Is b a literal written in the code, or built at run time?",
                    "== compares where the Strings live, not their text.")
                .explain(
                    "false. b was built by toLowerCase, so it is a new String in "
                    + "a new place. The text is identical; the location is not. "
                    + "This is exactly what happens to every username a person "
                    + "types.")
                .xp(20))
            .practice(new Task(Task.PREDICT,
                    "The surprising one. What does this print?")
                .code(
                    "String a = \"admin\";",
                    "String b = \"admin\";",
                    "System.out.println(a == b);")
                .accept("true")
                .hints(
                    "Both are literals - text written in quotes in the code.",
                    "Java reuses identical literals.")
                .explain(
                    "true - and that is the problem. Java keeps one copy of each "
                    + "literal and lets both variables point at it, so == "
                    + "happens to work. A test written like this passes, the "
                    + "code ships, and then real input - which never comes from "
                    + "a literal - fails the same check. The bug hides precisely "
                    + "in the kind of test people write first.")
                .xp(25))
            .objective(
                "Fix the admin console's username check.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String stored = \"admin\";",
                "        String typed = \" Admin \".trim().toLowerCase();",
                "        // write the if line here",
                "            System.out.println(\"USERNAME ACCEPTED\");",
                "        } else {",
                "            System.out.println(\"UNKNOWN USER\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line so that ACCEPTED prints when typed has the "
                + "same text as stored. Put typed first.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line comparing typed with stored properly.")
                .accept("if (typed.equals(stored)) {", "if(typed.equals(stored)) {",
                        "if (typed.equals(stored)){", "if(typed.equals(stored)){")
                .hints(
                    "Not ==. Text is compared with a method.",
                    "The String you ask comes first, then .equals, then the other "
                    + "String in brackets.",
                    "if (typed.equals(stored)) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String stored = \"admin\";",
                    "        String typed = \" Admin \".trim().toLowerCase();",
                    "        if (typed.equals(stored)) {",
                    "            System.out.println(\"USERNAME ACCEPTED\");",
                    "        } else {",
                    "            System.out.println(\"UNKNOWN USER\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "typed is built at run time by trim and toLowerCase, so it is "
                    + "a different String from stored even though both say "
                    + "admin. equals compares the characters, finds all five the "
                    + "same, and returns true.\n"
                    + "\n"
                    + "With == the program would compile, and print UNKNOWN USER "
                    + "for the real administrator - a denial of service against "
                    + "your own admins. In code that compares against a "
                    + "blocklist instead, the same bug fails the other way and "
                    + "lets a banned name straight through.")
                .explain(
                    "Text is compared with equals, never ==.")
                .xp(30))
            .mistakes(
                new String[]{"Comparing Strings with ==",
                    "It compares locations, not text. It can pass every test "
                    + "that uses literals and fail on real input."},
                new String[]{"Writing it as a == check with the ! inside",
                    "For 'not equal', write !a.equals(b), not a != b."},
                new String[]{"Trusting a passing test",
                    "\"x\" == \"x\" is true because of literal reuse. It proves "
                    + "nothing about typed input."})
            .cyber(
                "String comparison with == is a classic entry in secure coding "
                + "guides for exactly this reason: it fails silently and "
                + "inconsistently, depending on where the text came from.\n"
                + "\n"
                + "In an authentication check it tends to fail closed - real "
                + "users locked out, which gets noticed and fixed. In a "
                + "blocklist or a 'deny if this is the banned account' check it "
                + "fails OPEN: the comparison quietly says 'not the banned "
                + "name' for the banned name, and nothing is blocked. Failing "
                + "open is the version nobody notices until the incident "
                + "report.")
            .check(new Task(Task.CHOICE,
                    "Which is the right way to compare two Strings a and b?")
                .choices("a == b", "a.equals(b)", "a = b", "a.compare(b)")
                .accept("2", "b")
                .hints("Compare the characters, not the locations.",
                       "It is a method on String.")
                .explain(
                    "a.equals(b). == compares where the Strings are, = assigns, "
                    + "and compare is not a String method.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String role = \"GUEST\".toLowerCase();",
                    "if (!role.equals(\"admin\")) {",
                    "    System.out.println(\"LIMITED ACCESS\");",
                    "} else {",
                    "    System.out.println(\"FULL ACCESS\");",
                    "}")
                .accept("LIMITED ACCESS")
                .hints("role holds guest.",
                       "The ! flips the result of equals.")
                .explain(
                    "LIMITED ACCESS. guest does not equal admin, so equals is "
                    + "false, and ! makes the condition true.")
                .xp(20))
            .recap(
                "    a.equals(b)     same text       use this for Strings\n"
                + "    a == b          same location   numbers, chars, booleans\n"
                + "    !a.equals(b)    different text\n"
                + "\n"
                + "== on Strings can pass tests with literals and fail on real "
                + "input. There are no exceptions to the rule.")
            .next("Next: comparing text when capitals should not matter."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(12), "When Capitals Should Not Matter", 3)
            .brief(
                "Analysts type commands into the console: QUIT, quit, Quit. All "
                + "of them should work. Passwords are different - Winter2024 "
                + "and winter2024 must never be the same password.\n\n"
                + "Deciding which comparisons ignore case is a security "
                + "decision.")
            .willLearn(".equals()", "equalsIgnoreCase", "Case-sensitive or not")
            .whyUseful(
                "Commands, usernames, hostnames and file extensions usually "
                + "ignore case. Passwords, tokens and keys must never. Using the "
                + "right comparison in each place is part of the job.")
            .concept("equalsIgnoreCase",
                "equalsIgnoreCase compares text with capitals treated as the "
                + "same:\n"
                + "\n"
                + "    \"QUIT\".equals(\"quit\")              false\n"
                + "    \"QUIT\".equalsIgnoreCase(\"quit\")    true\n"
                + "\n"
                + "It does the same job as lower-casing both sides and then "
                + "using equals, without making copies.\n"
                + "\n"
                + "WHEN TO USE WHICH\n"
                + "\n"
                + "    ignore case    commands, hostnames, email domains,\n"
                + "                   usernames (after normalising),\n"
                + "                   file extensions\n"
                + "    exact case     passwords, API keys, tokens,\n"
                + "                   anything secret\n"
                + "\n"
                + "A password check that ignores case divides the number of "
                + "possible passwords enormously - every letter becomes one "
                + "possibility instead of two - and makes guessing far easier.\n"
                + "\n"
                + "Be consistent. If usernames ignore case at login, they must "
                + "ignore case everywhere: registration, the blocklist, the "
                + "audit. A system that ignores case in one place and not "
                + "another is the Admin-and-admin problem from Campaign 01.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String command = \"Quit\";",
                "        System.out.println(command.equals(\"quit\"));",
                "        System.out.println(command.equalsIgnoreCase(\"quit\"));",
                "    }",
                "}")
            .exampleOutput("false", "true")
            .lineByLine(
                new String[]{"command.equals(\"quit\")",
                    "Q and q are different characters. false."},
                new String[]{"command.equalsIgnoreCase(\"quit\")",
                    "Capitals treated as equal. true."},
                new String[]{"No copies made",
                    "command is unchanged either way. Neither method alters the "
                    + "String."},
                new String[]{"For a command",
                    "Ignoring case is what the analyst expects."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(\"Admin\".equalsIgnoreCase(\"ADMIN\"));")
                .accept("true")
                .hints("Same letters, different capitals.",
                       "equalsIgnoreCase treats capitals as equal.")
                .explain("true. Same letters; the capitals do not count.")
                .xp(10))
            .practice(new Task(Task.CHOICE,
                    "Which of these should be compared with exact case, using "
                    + "equals?")
                .choices("A menu command like QUIT", "A hostname like WEB-01",
                         "A password", "A file extension like .EXE")
                .accept("3", "c")
                .hints("Which one is a secret?",
                       "Ignoring case makes a secret much easier to guess.")
                .explain(
                    "The password. Treating capitals as equal cuts the number of "
                    + "possible passwords dramatically. The others are names, "
                    + "where ignoring case is what people expect - and for .EXE, "
                    + "what security requires, since Windows ignores case too.")
                .xp(20))
            .objective(
                "Let the console accept quit in any capitals.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String command = \"QUIT\";",
                "        // write the if line here",
                "            System.out.println(\"Session closed.\");",
                "        } else {",
                "            System.out.println(\"Unknown command.\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line so that Session closed prints when command is "
                + "quit in any mix of capitals. Put command first.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line: command is quit, ignoring case.")
                .accept("if (command.equalsIgnoreCase(\"quit\")) {",
                        "if(command.equalsIgnoreCase(\"quit\")) {",
                        "if (command.equalsIgnoreCase(\"quit\")){",
                        "if (command.equalsIgnoreCase(\"QUIT\")) {")
                .hints(
                    "equals would need exactly the same capitals.",
                    "The method that ignores capitals is equalsIgnoreCase.",
                    "if (command.equalsIgnoreCase(\"quit\")) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String command = \"QUIT\";",
                    "        if (command.equalsIgnoreCase(\"quit\")) {",
                    "            System.out.println(\"Session closed.\");",
                    "        } else {",
                    "            System.out.println(\"Unknown command.\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "equalsIgnoreCase compares QUIT with quit letter by letter, "
                    + "treating capitals as the same, and returns true.\n"
                    + "\n"
                    + "For a command that is the friendly choice and costs "
                    + "nothing: there is no secret in the word quit. The same "
                    + "line comparing a password would be a vulnerability, which "
                    + "is why the choice has to be made deliberately each time "
                    + "rather than out of habit.")
                .explain(
                    "equalsIgnoreCase for names and commands; equals for "
                    + "secrets.")
                .xp(25))
            .mistakes(
                new String[]{"Ignoring case on a secret",
                    "Passwords, tokens and keys must match exactly. Use equals."},
                new String[]{"Ignoring case in one place only",
                    "If logins ignore case, registration and blocklists must too, "
                    + "or Admin slips past a rule written for admin."},
                new String[]{"Lower-casing with a call you do not store",
                    "name.toLowerCase(); then name.equals(...) compares the "
                    + "original. equalsIgnoreCase avoids that trap."})
            .cyber(
                "Case handling is a surprisingly common source of real "
                + "vulnerabilities, because different parts of a system make "
                + "different choices.\n"
                + "\n"
                + "A web application blocks the path /admin, comparing exactly. "
                + "The web server underneath treats /ADMIN as the same page. "
                + "Request /ADMIN and the application's check does not match "
                + "while the server serves the admin page anyway. The same "
                + "pattern appears with usernames, email addresses and file "
                + "names.\n"
                + "\n"
                + "The defence is to decide once how each kind of value is "
                + "compared, and make every check agree with the thing it "
                + "protects.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(\"Winter2024\".equals(\"winter2024\"));")
                .accept("false")
                .hints("equals compares exactly.",
                       "Is W the same character as w?")
                .explain(
                    "false. equals is case-sensitive - exactly right for a "
                    + "password.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which comparison should a login use for the PASSWORD?")
                .choices("password.equalsIgnoreCase(stored)",
                         "password.equals(stored)",
                         "password == stored",
                         "password.toLowerCase().equals(stored)")
                .accept("2", "b")
                .hints("Exact case, and compare the text.",
                       "One ignores case, one compares locations.")
                .explain(
                    "password.equals(stored): exact, character by character. "
                    + "Ignoring case weakens the password; == compares "
                    + "locations. (Real systems store a hash, not the password - "
                    + "Campaign 17 - but the rule about case is the same.)")
                .xp(15))
            .recap(
                "    a.equals(b)             exact\n"
                + "    a.equalsIgnoreCase(b)   capitals treated as equal\n"
                + "\n"
                + "Names and commands: ignore case. Secrets: exact.\n"
                + "\n"
                + "Every check must make the same choice as the thing it "
                + "protects.")
            .next("Next: why two decimal numbers that should be equal are "
                + "not."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(13), "Almost Equal", 4)
            .brief(
                "The billing check adds up three charges - 0.1, 0.2 and 0.3 - "
                + "and compares the total with the invoice, 0.6. They never "
                + "match. The finance team is convinced someone is stealing "
                + "fractions of a penny.\n\n"
                + "Nobody is. doubles are not exact, and == on them is a trap.")
            .willLearn("Comparing decimals", "Tolerance", "Whole units instead of decimals")
            .whyUseful(
                "Totals, averages, rates and scores are doubles, and comparing "
                + "them is everywhere. Knowing why 0.1 + 0.2 is not 0.3 - and "
                + "what to do instead - prevents a whole family of baffling "
                + "bugs.")
            .concept("Comparing doubles",
                "A double stores numbers in binary. Most decimal fractions - "
                + "0.1, 0.2, 0.3 - have no exact binary form, the way 1/3 has "
                + "no exact decimal form. So each is stored as the nearest value "
                + "that fits, and tiny errors appear:\n"
                + "\n"
                + "    System.out.println(0.1 + 0.2);\n"
                + "    0.30000000000000004\n"
                + "\n"
                + "So == on doubles that came from arithmetic is unreliable:\n"
                + "\n"
                + "    0.1 + 0.2 == 0.3      false\n"
                + "\n"
                + "COMPARE WITH A TOLERANCE. Ask whether the two are close "
                + "enough:\n"
                + "\n"
                + "    Math.abs(a - b) < 0.000001\n"
                + "\n"
                + "Math.abs gives the size of the gap whichever is larger, and "
                + "the tolerance says how close counts as equal. Choose it to "
                + "suit the data - for money, anything smaller than a tenth of a "
                + "penny.\n"
                + "\n"
                + "OR AVOID FRACTIONS. For money, store whole pennies in a long: "
                + "1099 pennies, not 10.99 pounds. Whole numbers are exact, and "
                + "== works again. Banks and payment systems do exactly this.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        double total = 0.1 + 0.2;",
                "        System.out.println(total);",
                "        System.out.println(total == 0.3);",
                "        System.out.println(Math.abs(total - 0.3) < 0.000001);",
                "    }",
                "}")
            .exampleOutput("0.30000000000000004", "false", "true")
            .lineByLine(
                new String[]{"0.1 + 0.2",
                    "Neither value is exact in binary, and the tiny errors add "
                    + "up."},
                new String[]{"total == 0.3",
                    "0.30000000000000004 is not 0.3. false."},
                new String[]{"Math.abs(total - 0.3)",
                    "The gap: about 0.00000000000000004. Far below the "
                    + "tolerance."},
                new String[]{"< 0.000001",
                    "Close enough counts as equal. true."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(0.1 + 0.2 == 0.3);")
                .accept("false")
                .hints("Is 0.1 + 0.2 exactly 0.3 in binary?",
                       "The sum is 0.30000000000000004.")
                .explain(
                    "false. The sum is a hair above 0.3, and == demands an exact "
                    + "match.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "Whole numbers, stored as pennies. What does this print?")
                .code(
                    "long pennies = 10 + 20;",
                    "System.out.println(pennies == 30);")
                .accept("true")
                .hints("These are whole numbers.",
                       "Whole numbers are exact.")
                .explain(
                    "true. 10p + 20p is exactly 30p. Keeping money as whole "
                    + "pennies makes every comparison exact again - which is why "
                    + "financial systems avoid doubles for money altogether.")
                .xp(20))
            .objective(
                "Make the billing check compare the total sensibly.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        double total = 0.1 + 0.2 + 0.3;",
                "        double invoice = 0.6;",
                "        // write the if line here",
                "            System.out.println(\"INVOICE MATCHES\");",
                "        } else {",
                "            System.out.println(\"MISMATCH - investigate\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line so the invoice matches when total and invoice "
                + "differ by less than 0.0001. Use Math.abs and subtract invoice "
                + "from total.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line: total within 0.0001 of invoice.")
                .accept("if (Math.abs(total - invoice) < 0.0001) {",
                        "if(Math.abs(total - invoice) < 0.0001) {",
                        "if (Math.abs(total - invoice) < 0.0001){",
                        "if (Math.abs(invoice - total) < 0.0001) {")
                .hints(
                    "Not ==. Ask whether the gap is small.",
                    "The gap is Math.abs(total - invoice).",
                    "if (Math.abs(total - invoice) < 0.0001) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        double total = 0.1 + 0.2 + 0.3;",
                    "        double invoice = 0.6;",
                    "        if (Math.abs(total - invoice) < 0.0001) {",
                    "            System.out.println(\"INVOICE MATCHES\");",
                    "        } else {",
                    "            System.out.println(\"MISMATCH - investigate\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "total ends up as 0.6000000000000001. The gap to 0.6 is "
                    + "about one ten-thousand-trillionth - far below 0.0001 - so "
                    + "the check reports a match.\n"
                    + "\n"
                    + "With ==, the program raised a false alarm on every "
                    + "invoice. False alarms are not harmless: a check that "
                    + "cries wolf daily gets ignored, and the day a real "
                    + "mismatch arrives, nobody looks.")
                .explain(
                    "Compare doubles by the size of the gap, not with ==.")
                .xp(30))
            .mistakes(
                new String[]{"== on computed doubles",
                    "Tiny binary errors make it fail. Use a tolerance."},
                new String[]{"Forgetting Math.abs",
                    "total - invoice < 0.0001 is also true when total is far "
                    + "SMALLER. The gap must be measured without its sign."},
                new String[]{"doubles for money",
                    "Store whole pennies in a long, and money is exact."})
            .cyber(
                "Rounding errors have been used deliberately. In the classic "
                + "'salami slicing' fraud, an insider's code rounds each of "
                + "millions of transactions down by a fraction of a penny and "
                + "moves the fractions into an account of their own. Each slice "
                + "is invisible; the total is not.\n"
                + "\n"
                + "It works best where money is kept in doubles and checks are "
                + "loose, because small discrepancies are expected and "
                + "explained away. Exact whole-penny arithmetic, and checks with "
                + "a tolerance chosen on purpose rather than by accident, leave "
                + "no fog for anyone to hide in.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(Math.abs(0.5 - 0.7) < 0.3);")
                .accept("true")
                .hints("0.5 - 0.7 is negative.",
                       "Math.abs makes it positive: about 0.2.")
                .explain(
                    "true. The gap is about 0.2, which is less than 0.3. Math.abs "
                    + "is what makes the order of the subtraction irrelevant.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "What is the safest way to hold money in a program?")
                .choices("A double in pounds", "A long in pennies",
                         "A String", "An int in pounds")
                .accept("2", "b")
                .hints("Which one is exact?",
                       "Whole numbers are exact; fractions in doubles are not.")
                .explain(
                    "A long in pennies. Whole numbers are exact, and a long has "
                    + "room for any realistic sum. An int in pounds cannot hold "
                    + "the pence at all.")
                .xp(15))
            .recap(
                "    0.1 + 0.2 == 0.3                    false\n"
                + "    Math.abs(a - b) < tolerance         close enough\n"
                + "\n"
                + "doubles are approximations. Compare them by the size of the "
                + "gap.\n"
                + "\n"
                + "For money, use whole pennies in a long.")
            .next("Next: asking what kind of character something is."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(14), "What Kind of Character?", 3)
            .brief(
                "The new username rules say a name must start with a letter. "
                + "Port numbers must start with a digit. Access codes must "
                + "start with a capital.\n\n"
                + "Java has ready-made tests for what kind of character "
                + "something is.")
            .willLearn("Character methods", "isDigit, isLetter, isUpperCase", "Validating one character")
            .whyUseful(
                "Validation starts with characters: does this start with a "
                + "letter, is this a digit, is there whitespace. These methods "
                + "answer each question in one call.")
            .concept("Character methods",
                "Character is to char what Integer is to int: a class of "
                + "helpers you call on its name. Each takes a char and answers "
                + "true or false:\n"
                + "\n"
                + "    Character.isDigit(c)        0 to 9\n"
                + "    Character.isLetter(c)       a letter\n"
                + "    Character.isLetterOrDigit(c)\n"
                + "    Character.isUpperCase(c)    a capital letter\n"
                + "    Character.isLowerCase(c)\n"
                + "    Character.isWhitespace(c)   space, tab, line break\n"
                + "\n"
                + "    Character.isDigit('7')      true\n"
                + "    Character.isLetter('7')     false\n"
                + "    Character.isUpperCase('a')  false\n"
                + "\n"
                + "Combined with charAt, they check a position in a String:\n"
                + "\n"
                + "    Character.isLetter(name.charAt(0))\n"
                + "\n"
                + "is true when the name starts with a letter.\n"
                + "\n"
                + "Remember mission 10: charAt(0) on an empty String crashes. "
                + "Guard it:\n"
                + "\n"
                + "    name.length() > 0 && Character.isLetter(name.charAt(0))\n"
                + "\n"
                + "These methods know about far more than English. isLetter is "
                + "true for letters in every alphabet, isDigit for digits in "
                + "many scripts. Usually that is what you want; when it is not, "
                + "compare against the exact characters you allow.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String name = \"7smith\";",
                "        char first = name.charAt(0);",
                "        System.out.println(\"letter: \" + Character.isLetter(first));",
                "        System.out.println(\"digit:  \" + Character.isDigit(first));",
                "    }",
                "}")
            .exampleOutput("letter: false", "digit:  true")
            .lineByLine(
                new String[]{"name.charAt(0)",
                    "The first character, '7', as a char."},
                new String[]{"Character.isLetter(first)",
                    "'7' is not a letter: false. This username breaks the rule."},
                new String[]{"Character.isDigit(first)",
                    "'7' is a digit: true."},
                new String[]{"Called on Character",
                    "Like Integer.parseInt, these belong to the class, so you "
                    + "write the class name first."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(Character.isUpperCase('a'));")
                .accept("false")
                .hints("Is a a capital?", "isUpperCase asks exactly that.")
                .explain(
                    "false. 'a' is lower case, so the answer to 'is it a "
                    + "capital?' is no.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String code = \"X-17\";",
                    "System.out.println(Character.isLetterOrDigit(code.charAt(1)));")
                .accept("false")
                .hints("Which character is at index 1?",
                       "Is a dash a letter or a digit?")
                .explain(
                    "false. Index 1 is the dash, which is neither a letter nor a "
                    + "digit.")
                .xp(20))
            .objective(
                "Enforce the rule that a username starts with a letter.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String name = \"4dmin\";",
                "        // write the if line here - guard against an empty name",
                "            System.out.println(\"VALID\");",
                "        } else {",
                "            System.out.println(\"MUST START WITH A LETTER\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line: VALID only when name is not empty AND its "
                + "first character is a letter. Put the length check first.")
            .mainTask(new Task(Task.WRITE,
                    "Write the guarded if line: non-empty, and starts with a letter.")
                .accept("if (name.length() > 0 && Character.isLetter(name.charAt(0))) {",
                        "if(name.length() > 0 && Character.isLetter(name.charAt(0))) {",
                        "if (name.length() > 0 && Character.isLetter(name.charAt(0))){",
                        "if (name.length() >= 1 && Character.isLetter(name.charAt(0))) {")
                .hints(
                    "Mission 10's guard comes first, on the left of &&.",
                    "The first character is name.charAt(0), and "
                    + "Character.isLetter tests it.",
                    "if (name.length() > 0 && Character.isLetter(name.charAt(0))) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String name = \"4dmin\";",
                    "        if (name.length() > 0 && Character.isLetter(name.charAt(0))) {",
                    "            System.out.println(\"VALID\");",
                    "        } else {",
                    "            System.out.println(\"MUST START WITH A LETTER\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "The length check stops an empty name before charAt can "
                    + "crash. For 4dmin it passes, charAt(0) gives '4', isLetter "
                    + "says false, and the rule refuses it.\n"
                    + "\n"
                    + "4dmin is chosen on purpose. Look-alike names - a digit 4 for "
                    + "an A, a 0 for an O - are how impostor accounts get made. "
                    + "Rules about which characters are allowed where are the "
                    + "first line of defence against them.")
                .explain(
                    "Guard the length, then test the character.")
                .xp(30))
            .mistakes(
                new String[]{"No length guard",
                    "charAt(0) on \"\" crashes. Check length first, on the left "
                    + "of &&."},
                new String[]{"Calling it on the char",
                    "first.isLetter() does not compile. It is "
                    + "Character.isLetter(first)."},
                new String[]{"Assuming English only",
                    "isLetter is true for letters in every alphabet. If only "
                    + "a-z is allowed, test for exactly that."})
            .cyber(
                "The last mistake above is a real attack. Homoglyph attacks "
                + "register names using letters from other alphabets that look "
                + "identical to Latin ones. Cyrillic has its own letter a, "
                + "drawn exactly like the Latin a - so paypal spelled with it is "
                + "a completely different name that looks identical. Both "
                + "versions pass isLetter, and a person reading the name sees "
                + "no difference.\n"
                + "\n"
                + "Where names must be unambiguous - usernames, domains, "
                + "anything shown to other people - the safe rule is an "
                + "allowlist of the exact characters permitted, rather than "
                + "'any letter'.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(Character.isWhitespace(' '));")
                .accept("true")
                .hints("What is inside the quotes?", "A space is whitespace.")
                .explain("true. A space counts as whitespace, as do tabs and "
                    + "line breaks.")
                .xp(10))
            .check(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "char c = 'Q';",
                    "boolean big = Character.isUpperCase(c);",
                    "boolean digit = c.isDigit();",
                    "System.out.println(big);")
                .accept("3", "line 3")
                .hints("How are Character methods called?",
                       "A char has no methods of its own.")
                .explain(
                    "Line 3. A char is a simple value with no methods, so "
                    + "c.isDigit() cannot exist. The method belongs to the "
                    + "Character class: Character.isDigit(c).")
                .xp(20))
            .recap(
                "    Character.isDigit(c)      Character.isLetter(c)\n"
                + "    Character.isUpperCase(c)  Character.isWhitespace(c)\n"
                + "\n"
                + "Called on the class name, with the char in brackets.\n"
                + "\n"
                + "Guard charAt with a length check. For strict rules, allow "
                + "exact characters rather than 'any letter'.")
            .next("Next: an if inside another if."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(15), "An If Inside an If", 4)
            .brief(
                "The login service needs to say WHY it refused someone: no such "
                + "account, account locked, or wrong second factor. Each "
                + "question only makes sense once the one before it has been "
                + "answered.\n\n"
                + "That shape is an if inside another if.")
            .willLearn("Nested if", "else belongs to the nearest if", "Nesting against &&")
            .whyUseful(
                "Decisions often come in layers: only check the password if the "
                + "account exists, only check MFA if the password was right. "
                + "Nesting expresses layers, and gives each one its own "
                + "outcome.")
            .concept("Nested if",
                "Any statement can go inside an if block - including another "
                + "if:\n"
                + "\n"
                + "    if (accountExists) {\n"
                + "        if (locked) {\n"
                + "            System.out.println(\"ACCOUNT LOCKED\");\n"
                + "        } else {\n"
                + "            System.out.println(\"CHECKING PASSWORD\");\n"
                + "        }\n"
                + "    } else {\n"
                + "        System.out.println(\"NO SUCH ACCOUNT\");\n"
                + "    }\n"
                + "\n"
                + "The inner if only runs when the outer condition was true. "
                + "Each level can have its own else, so each failure gets its "
                + "own message.\n"
                + "\n"
                + "NESTING OR &&? These two are the same:\n"
                + "\n"
                + "    if (a) { if (b) { X } }\n"
                + "    if (a && b) { X }\n"
                + "\n"
                + "Use && when there is one outcome. Nest when the levels need "
                + "different outcomes, as above.\n"
                + "\n"
                + "THE DANGLING ELSE. Without braces, an else belongs to the "
                + "NEAREST if above it, whatever the indentation says:\n"
                + "\n"
                + "    if (a)\n"
                + "        if (b)\n"
                + "            X\n"
                + "    else\n"
                + "        Y\n"
                + "\n"
                + "The else looks like it belongs to if (a). It belongs to "
                + "if (b). With a false, nothing runs at all. Braces make the "
                + "structure what you wrote - one more reason to always use "
                + "them.\n"
                + "\n"
                + "Keep nesting shallow. Past three levels, code is hard to "
                + "follow; methods, in Campaign 03, help flatten it.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        boolean accountExists = true;",
                "        boolean locked = true;",
                "        if (accountExists) {",
                "            if (locked) {",
                "                System.out.println(\"ACCOUNT LOCKED\");",
                "            } else {",
                "                System.out.println(\"CHECKING PASSWORD\");",
                "            }",
                "        } else {",
                "            System.out.println(\"NO SUCH ACCOUNT\");",
                "        }",
                "    }",
                "}")
            .exampleOutput("ACCOUNT LOCKED")
            .lineByLine(
                new String[]{"if (accountExists)",
                    "true, so the outer block runs."},
                new String[]{"if (locked)",
                    "Only reached because the account exists. true: LOCKED."},
                new String[]{"The inner else",
                    "Belongs to if (locked). Skipped."},
                new String[]{"The outer else",
                    "Belongs to if (accountExists). Skipped. The braces make it "
                    + "impossible to confuse the two."})
            .predict(new Task(Task.PREDICT,
                    "The same code with different values. What does it print?")
                .code(
                    "boolean accountExists = false;",
                    "boolean locked = true;",
                    "if (accountExists) {",
                    "    if (locked) {",
                    "        System.out.println(\"LOCKED\");",
                    "    }",
                    "} else {",
                    "    System.out.println(\"NO SUCH ACCOUNT\");",
                    "}")
                .accept("NO SUCH ACCOUNT")
                .hints("Start with the outer condition.",
                       "If it is false, is the inner if ever reached?")
                .explain(
                    "NO SUCH ACCOUNT. The outer condition is false, so the inner "
                    + "if never runs - locked being true does not matter. That "
                    + "is the point: an account that does not exist cannot be "
                    + "locked.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "No braces. Read it the way Java does, not the way it is "
                    + "indented. What does it print?")
                .code(
                    "boolean a = false;",
                    "boolean b = true;",
                    "if (a)",
                    "    if (b)",
                    "        System.out.println(\"X\");",
                    "else",
                    "    System.out.println(\"Y\");",
                    "System.out.println(\"done\");")
                .accept("done")
                .hints(
                    "Which if does the else belong to? The nearest one above it.",
                    "The else belongs to if (b), which is inside if (a). Is "
                    + "if (a) true?")
                .explain(
                    "done - and nothing else. The else belongs to if (b), not "
                    + "if (a), so the whole inner if/else is skipped when a is "
                    + "false. The indentation promised Y. Java never reads "
                    + "indentation.")
                .xp(30))
            .objective(
                "Give each login failure its own message.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        boolean passwordOk = true;",
                "        boolean mfaOk = false;",
                "        if (passwordOk) {",
                "            // write the inner if line here",
                "                System.out.println(\"ACCESS GRANTED\");",
                "            } else {",
                "                System.out.println(\"MFA FAILED\");",
                "            }",
                "        } else {",
                "            System.out.println(\"WRONG PASSWORD\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "The password was right. Write the inner if line so that access "
                + "is granted only if the second factor also passed.")
            .mainTask(new Task(Task.WRITE,
                    "Write the inner if line, testing mfaOk.")
                .accept("if (mfaOk) {", "if(mfaOk) {", "if (mfaOk){", "if(mfaOk){")
                .hints(
                    "It sits inside the passwordOk block, so the password is "
                    + "already known to be right.",
                    "A boolean is already a condition.",
                    "if (mfaOk) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        boolean passwordOk = true;",
                    "        boolean mfaOk = false;",
                    "        if (passwordOk) {",
                    "            if (mfaOk) {",
                    "                System.out.println(\"ACCESS GRANTED\");",
                    "            } else {",
                    "                System.out.println(\"MFA FAILED\");",
                    "            }",
                    "        } else {",
                    "            System.out.println(\"WRONG PASSWORD\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "The inner if is only reached once the password is known to "
                    + "be right, so it can focus on the second factor. mfaOk is "
                    + "false, and MFA FAILED prints.\n"
                    + "\n"
                    + "if (passwordOk && mfaOk) would decide access just as "
                    + "correctly, but could only give one refusal message. "
                    + "Nesting lets each layer report its own failure - useful in "
                    + "the audit log, though see the cyber note about what to "
                    + "show the person logging in.")
                .explain(
                    "The inner if tests the second layer only.")
                .xp(25))
            .mistakes(
                new String[]{"Trusting indentation",
                    "Without braces, else belongs to the nearest if. Always use "
                    + "braces."},
                new String[]{"Nesting where && would do",
                    "If every path ends the same way, a flat condition is "
                    + "clearer."},
                new String[]{"Nesting too deep",
                    "Past three levels, readers lose track. Methods help "
                    + "flatten it."})
            .cyber(
                "Detailed failure messages are a double-edged sword. In the "
                + "audit log, 'no such account' against 'wrong password' is "
                + "valuable: a burst of the first means someone is guessing "
                + "usernames.\n"
                + "\n"
                + "Shown to the person logging in, the same difference is a gift "
                + "to attackers: they can find out which usernames exist, then "
                + "concentrate on those. This is called USER ENUMERATION. The "
                + "usual practice is to log the precise reason and show the user "
                + "one generic message - 'username or password incorrect' - "
                + "whichever layer failed.")
            .check(new Task(Task.CHOICE,
                    "Which is the same as  if (a) { if (b) { X } } ?")
                .choices("if (a || b) { X }", "if (a && b) { X }",
                         "if (a) { X } else if (b) { X }", "if (!a && b) { X }")
                .accept("2", "b")
                .hints("X runs only when both are true.",
                       "Both true is &&.")
                .explain(
                    "if (a && b). X runs only when a and b are both true. The "
                    + "nested form is only worth it when the levels need "
                    + "different outcomes.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int failures = 7;",
                    "boolean admin = true;",
                    "if (failures > 5) {",
                    "    if (admin) {",
                    "        System.out.println(\"PAGE ON-CALL\");",
                    "    } else {",
                    "        System.out.println(\"LOG IT\");",
                    "    }",
                    "}")
                .accept("PAGE ON-CALL")
                .hints("Is 7 more than 5?", "Then: is it an admin account?")
                .explain(
                    "PAGE ON-CALL. Both conditions hold. Failures on an admin "
                    + "account are worth waking someone for; on a normal account "
                    + "they are logged.")
                .xp(20))
            .recap(
                "An if can contain another if. The inner one runs only when the "
                + "outer condition was true.\n"
                + "\n"
                + "Nest when levels need different outcomes; use && when they "
                + "share one.\n"
                + "\n"
                + "Without braces, else belongs to the nearest if. Always use "
                + "braces.")
            .next("Next: checking a search found something before you use "
                + "it."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(16), "Did the Search Find Anything?", 4)
            .brief(
                "Campaign 01 left a trap set: when indexOf finds nothing it "
                + "returns -1, and substring quietly produces nonsense or "
                + "crashes. The phishing triage tool just filed a report with "
                + "the domain 'call me urgently' because the 'address' had no @ "
                + "in it.\n\n"
                + "Now the program can check before it cuts.")
            .willLearn("Validating input", "Checking for -1", "Check, then use")
            .whyUseful(
                "Every search can come back empty. Checking the result before "
                + "using it turns silent nonsense and crashes into a clear, "
                + "handled case - which is most of what makes a parser "
                + "robust.")
            .concept("Checking for -1",
                "indexOf returns -1 when the text is not there. So the "
                + "question 'was it found?' is a comparison:\n"
                + "\n"
                + "    int at = email.indexOf(\"@\");\n"
                + "    if (at == -1) {\n"
                + "        System.out.println(\"NOT AN EMAIL ADDRESS\");\n"
                + "    } else {\n"
                + "        String domain = email.substring(at + 1);\n"
                + "        ...\n"
                + "    }\n"
                + "\n"
                + "The substring is now inside the else, so it can only run when "
                + "the @ exists. That is 'check, then use' again, written as an "
                + "if/else instead of an &&.\n"
                + "\n"
                + "Two ways to write the test, both correct:\n"
                + "\n"
                + "    at == -1      not found\n"
                + "    at < 0        not found\n"
                + "    at >= 0       found\n"
                + "\n"
                + "A found position is always 0 or more, so any negative value "
                + "means missing. Some people prefer < 0 because it does not "
                + "depend on remembering the exact value.\n"
                + "\n"
                + "If you only need to know WHETHER text is there, contains is "
                + "simpler. When you need WHERE it is as well, use indexOf and "
                + "check the result.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String email = \"call me urgently\";",
                "        int at = email.indexOf(\"@\");",
                "        if (at == -1) {",
                "            System.out.println(\"NOT AN EMAIL ADDRESS\");",
                "        } else {",
                "            System.out.println(\"DOMAIN: \" + email.substring(at + 1));",
                "        }",
                "    }",
                "}")
            .exampleOutput("NOT AN EMAIL ADDRESS")
            .lineByLine(
                new String[]{"email.indexOf(\"@\")",
                    "No @ in the text, so at is -1."},
                new String[]{"if (at == -1)",
                    "The check. true, so the refusal prints."},
                new String[]{"The substring in the else",
                    "Never runs here. It only ever sees text that really has an "
                    + "@."},
                new String[]{"Without the check",
                    "substring(0) - the whole text - would have been filed as "
                    + "the domain. No crash, a wrong report."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String email = \"a.okafor@northstar.example\";",
                    "int at = email.indexOf(\"@\");",
                    "if (at == -1) {",
                    "    System.out.println(\"NO @\");",
                    "} else {",
                    "    System.out.println(email.substring(0, at));",
                    "}")
                .accept("a.okafor")
                .hints("This time there is an @.",
                       "The else runs: everything before the @.")
                .explain(
                    "a.okafor. The @ is at index 8, so the check fails and the "
                    + "else cuts out the part before it.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "A firewall line with a field missing. What does this print?")
                .code(
                    "String line = \"BLOCK 10.0.4.17\";",
                    "int first = line.indexOf(\" \");",
                    "int second = line.indexOf(\" \", first + 1);",
                    "if (second < 0) {",
                    "    System.out.println(\"MALFORMED LINE\");",
                    "} else {",
                    "    System.out.println(\"PORT \" + line.substring(second + 1));",
                    "}")
                .accept("MALFORMED LINE")
                .hints("How many spaces are there?",
                       "What does indexOf give for the missing second space?")
                .explain(
                    "MALFORMED LINE. There is no second space, so second is -1, "
                    + "which is below 0. The parser from Campaign 01 would have "
                    + "printed PORT BLOCK 10.0.4.17 - its whole input, labelled "
                    + "as a port.")
                .xp(25))
            .objective(
                "Stop the triage tool filing text that is not an email address.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String email = \"urgent invoice attached\";",
                "        int at = email.indexOf(\"@\");",
                "        // write the if line here: true when there is no @",
                "            System.out.println(\"INVALID ADDRESS\");",
                "        } else {",
                "            System.out.println(\"DOMAIN: \" + email.substring(at + 1));",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line that is true when indexOf found no @.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line: at shows the @ was not found.")
                .accept("if (at == -1) {", "if(at == -1) {", "if (at == -1){",
                        "if (at < 0) {", "if(at < 0) {", "if (at < 0){")
                .hints(
                    "What does indexOf return when it finds nothing?",
                    "Compare at with that value.",
                    "if (at == -1) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String email = \"urgent invoice attached\";",
                    "        int at = email.indexOf(\"@\");",
                    "        if (at == -1) {",
                    "            System.out.println(\"INVALID ADDRESS\");",
                    "        } else {",
                    "            System.out.println(\"DOMAIN: \" + email.substring(at + 1));",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "There is no @, so at is -1, the condition is true, and the "
                    + "tool refuses the input instead of inventing a domain.\n"
                    + "\n"
                    + "The substring now lives only in the else, where the @ is "
                    + "guaranteed. The check and the use are tied together by "
                    + "the structure of the code, so nobody can later call the "
                    + "substring without the check by accident.")
                .explain(
                    "Check the search result before cutting with it.")
                .xp(25))
            .mistakes(
                new String[]{"Using the position without checking",
                    "substring(at + 1) with at = -1 is substring(0): the whole "
                    + "text, silently."},
                new String[]{"Testing at > 0 for 'found'",
                    "Something found at the very start is at 0. Found is at >= 0."},
                new String[]{"Checking in one place, cutting in another",
                    "Keep the use inside the branch where the check passed."})
            .cyber(
                "Much of secure parsing is refusing to guess. Input that does "
                + "not have the shape you expect is not 'probably fine' - it is "
                + "either broken or crafted, and either way the safest thing is "
                + "to say so and stop.\n"
                + "\n"
                + "A parser that guesses produces records that look valid and "
                + "are not: a domain that is a sentence, a port that is a whole "
                + "line. Those records feed dashboards, blocklists and reports, "
                + "and the damage spreads a long way from the line that was "
                + "never checked. Rejecting a malformed line - and counting how "
                + "many were rejected - is both safer and more informative.")
            .check(new Task(Task.CHOICE,
                    "at holds the result of indexOf. Which condition means the "
                    + "text WAS found?")
                .choices("at > 0", "at >= 0", "at == 0", "at != 0")
                .accept("2", "b")
                .hints("Found text can be at the very first position.",
                       "The first position is 0.")
                .explain(
                    "at >= 0. Text found at the very start is at index 0, and "
                    + "at > 0 would wrongly call that missing. Only negative "
                    + "means not found.")
                .xp(20))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String cmd = \"#restart web-01\";",
                    "if (cmd.indexOf(\"#\") == 0) {",
                    "    System.out.println(\"COMMENT - ignored\");",
                    "} else {",
                    "    System.out.println(\"RUN: \" + cmd);",
                    "}")
                .accept("COMMENT - ignored")
                .hints("Where is the # ?", "Index 0 is the very first character.")
                .explain(
                    "COMMENT - ignored. The # is at index 0, so the check is "
                    + "true. indexOf == 0 is one way to ask 'does it start "
                    + "with'; startsWith is the clearer one.")
                .xp(15))
            .recap(
                "    int at = s.indexOf(x);\n"
                + "    if (at == -1) {  not found - refuse\n"
                + "    } else {         found - safe to cut\n"
                + "    }\n"
                + "\n"
                + "Found means >= 0. Put the use inside the branch where the "
                + "check passed.")
            .next("Next: checking text really is a number before converting "
                + "it."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(17), "Is It Really a Number?", 4)
            .brief(
                "Integer.parseInt crashes on anything that is not a whole number "
                + "- a letter, a space, an empty line. The port field on the "
                + "config form has been crashing the tool whenever someone "
                + "types 'eighty'.\n\n"
                + "Before converting text, a program can check it matches the "
                + "shape of a number.")
            .willLearn("Validating input", "matches with a pattern", "Allowlist validation")
            .whyUseful(
                "Validating input before using it is the single most important "
                + "habit in secure programming. This mission gives you a tool "
                + "that says exactly what IS allowed - which is safer than "
                + "listing what is not.")
            .concept("Checking text with matches",
                "matches asks whether the WHOLE String fits a pattern:\n"
                + "\n"
                + "    text.matches(\"[0-9]+\")\n"
                + "\n"
                + "Read the pattern in two parts:\n"
                + "\n"
                + "    [0-9]    any one character from 0 to 9\n"
                + "    +        one or more of the thing before it\n"
                + "\n"
                + "So [0-9]+ means 'one or more digits, and nothing else'. "
                + "Patterns like this are called REGULAR EXPRESSIONS. They are "
                + "a language of their own, and this campaign uses only this "
                + "one; later campaigns add more.\n"
                + "\n"
                + "    \"443\".matches(\"[0-9]+\")     true\n"
                + "    \"44a\".matches(\"[0-9]+\")     false - a letter\n"
                + "    \" 44\".matches(\"[0-9]+\")     false - a space\n"
                + "    \"\".matches(\"[0-9]+\")        false - + needs one\n"
                + "    \"-5\".matches(\"[0-9]+\")      false - the minus sign\n"
                + "\n"
                + "Every value parseInt would choke on is refused - apart from "
                + "one. A long run of digits like 99999999999 matches the "
                + "pattern and is still too big for an int. Limit the length "
                + "too:\n"
                + "\n"
                + "    text.matches(\"[0-9]+\") && text.length() <= 9\n"
                + "\n"
                + "Nine digits always fit in an int. Now parseInt is safe.\n"
                + "\n"
                + "This is ALLOWLIST validation: describe exactly what is "
                + "acceptable and refuse everything else. It is far safer than "
                + "trying to list what is not acceptable, because nobody can "
                + "think of everything that is not.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String text = \"eighty\";",
                "        if (text.matches(\"[0-9]+\") && text.length() <= 9) {",
                "            int port = Integer.parseInt(text);",
                "            System.out.println(\"PORT \" + port);",
                "        } else {",
                "            System.out.println(\"NOT A NUMBER: \" + text);",
                "        }",
                "    }",
                "}")
            .exampleOutput("NOT A NUMBER: eighty")
            .lineByLine(
                new String[]{"text.matches(\"[0-9]+\")",
                    "eighty contains letters. false."},
                new String[]{"&& text.length() <= 9",
                    "Not even checked - && already knows the answer."},
                new String[]{"Integer.parseInt(text)",
                    "Inside the if, so it only ever sees text that is safe to "
                    + "convert."},
                new String[]{"The else",
                    "Refuses clearly, instead of crashing."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(\"8080\".matches(\"[0-9]+\"));")
                .accept("true")
                .hints("Is every character a digit?", "And is there at least one?")
                .explain("true. Four digits and nothing else.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "Typed with a space in front. What does this print?")
                .code("System.out.println(\" 22\".matches(\"[0-9]+\"));")
                .accept("false")
                .hints("matches checks the WHOLE String.",
                       "Is a space a digit?")
                .explain(
                    "false. The space is not a digit, and matches needs the whole "
                    + "String to fit. Trim first, then check - the same order as "
                    + "before parseInt.")
                .xp(20))
            .objective(
                "Stop the config tool crashing on a port that is not a number.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String text = \"80a\";",
                "        // write the if line here: digits only",
                "            System.out.println(\"PORT \" + Integer.parseInt(text));",
                "        } else {",
                "            System.out.println(\"INVALID PORT\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line so parseInt only runs when text is one or "
                + "more digits and nothing else. Use matches.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line: text matches one or more digits.")
                .accept("if (text.matches(\"[0-9]+\")) {",
                        "if(text.matches(\"[0-9]+\")) {",
                        "if (text.matches(\"[0-9]+\")){",
                        "if (text.matches(\"[0-9]+\") && text.length() <= 9) {")
                .hints(
                    "The pattern for 'one or more digits' is \"[0-9]+\".",
                    "matches is called on the text, with the pattern in the "
                    + "brackets.",
                    "if (text.matches(\"[0-9]+\")) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String text = \"80a\";",
                    "        if (text.matches(\"[0-9]+\")) {",
                    "            System.out.println(\"PORT \" + Integer.parseInt(text));",
                    "        } else {",
                    "            System.out.println(\"INVALID PORT\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "80a has a letter in it, so it does not match one-or-more "
                    + "digits, and the else refuses it. parseInt is never "
                    + "reached with text it cannot handle.\n"
                    + "\n"
                    + "Notice what the check describes: what a port LOOKS like, "
                    + "not a list of bad things to watch for. Nobody has to "
                    + "predict every wrong thing a person might type - anything "
                    + "not described is refused. Adding && text.length() <= 9 "
                    + "would also refuse numbers too big for an int.")
                .explain(
                    "Describe what is allowed; refuse the rest.")
                .xp(30))
            .mistakes(
                new String[]{"Checking after converting",
                    "By then parseInt has already crashed. Check first."},
                new String[]{"Forgetting the length",
                    "Twelve digits match [0-9]+ and still overflow an int."},
                new String[]{"Listing bad input instead",
                    "A blocklist of letters misses spaces, symbols, and "
                    + "everything else nobody thought of."})
            .cyber(
                "Input validation by allowlist is the first defence against "
                + "almost every injection attack. SQL injection, command "
                + "injection and path traversal all rely on a program accepting "
                + "characters it never needed - a quote, a semicolon, a dot "
                + "dot slash - in a field that should only ever hold a number or "
                + "a name.\n"
                + "\n"
                + "A port field that only accepts [0-9]+ cannot carry any of "
                + "those. Validation is not the only defence - later campaigns "
                + "add others - but it is the cheapest, and it stops a great "
                + "deal before anything else has to.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String text = \"12345678901\";",
                    "boolean safe = text.matches(\"[0-9]+\") && text.length() <= 9;",
                    "System.out.println(safe);")
                .accept("false")
                .hints("It is all digits. How many?",
                       "More than 9, so the second half is false.")
                .explain(
                    "false. It matches the pattern, but eleven digits is too long "
                    + "to be sure it fits an int - and this one does not.")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "What does the pattern [0-9]+ accept?")
                .choices("Any number, including negatives and decimals",
                         "One or more digits and nothing else",
                         "Exactly one digit",
                         "Anything containing a digit")
                .accept("2", "b")
                .hints("[0-9] is one digit. + means one or more.",
                       "matches needs the whole String to fit.")
                .explain(
                    "One or more digits and nothing else. No sign, no point, no "
                    + "spaces, and it must match the whole String.")
                .xp(15))
            .recap(
                "    text.matches(\"[0-9]+\")     digits only, at least one\n"
                + "    && text.length() <= 9      fits in an int\n"
                + "\n"
                + "Check first, convert inside the if.\n"
                + "\n"
                + "Allowlist: describe what is allowed, refuse everything else.")
            .next("Next: text that is empty, or only looks empty."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(18), "Empty, or Just Spaces?", 3)
            .brief(
                "The sign-up form rejects empty usernames. Someone registered "
                + "the username '   ' - three spaces - and now there is an "
                + "account nobody can see in the list.\n\n"
                + "Empty and blank are different questions.")
            .willLearn("isEmpty and isBlank", "Empty against blank", "trim().isEmpty()")
            .whyUseful(
                "Every required field needs a 'was anything actually entered' "
                + "check. Asking the right one of two similar questions is the "
                + "difference between a working form and an invisible account.")
            .concept("isEmpty and isBlank",
                "Two questions, easily confused:\n"
                + "\n"
                + "    isEmpty()    no characters at all\n"
                + "    isBlank()    nothing but whitespace (or nothing)\n"
                + "\n"
                + "    \"\".isEmpty()       true     \"\".isBlank()       true\n"
                + "    \"   \".isEmpty()    false    \"   \".isBlank()    true\n"
                + "    \"ab\".isEmpty()     false    \"ab\".isBlank()     false\n"
                + "\n"
                + "Three spaces are not empty - there are three characters - but "
                + "they are blank. For a required field, blank is almost always "
                + "the question you mean.\n"
                + "\n"
                + "isBlank arrived in Java 11. On older Java, the same check is "
                + "written:\n"
                + "\n"
                + "    text.trim().isEmpty()\n"
                + "\n"
                + "Trim away the edges, then see if anything is left.\n"
                + "\n"
                + "isEmpty() is the same as length() == 0, just easier to read.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String name = \"   \";",
                "        System.out.println(\"empty: \" + name.isEmpty());",
                "        System.out.println(\"blank: \" + name.isBlank());",
                "    }",
                "}")
            .exampleOutput("empty: false", "blank: true")
            .lineByLine(
                new String[]{"name.isEmpty()",
                    "Three characters, so not empty. false."},
                new String[]{"name.isBlank()",
                    "All three are spaces. true."},
                new String[]{"Which one a form needs",
                    "isBlank - three spaces is not a name."},
                new String[]{"On Java 8",
                    "name.trim().isEmpty() gives the same answer."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(\"\".isEmpty());")
                .accept("true")
                .hints("How many characters are between the quotes?",
                       "None at all.")
                .explain("true. The empty String has no characters.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String name = \"  \\t \";",
                    "System.out.println(name.trim().isEmpty());")
                .accept("true")
                .hints("\\t is a tab. What does trim remove?",
                       "trim removes spaces AND tabs at the ends.")
                .explain(
                    "true. The text is spaces and a tab, trim removes all of "
                    + "them, and nothing is left. This is the Java 8 way of "
                    + "asking isBlank.")
                .xp(20))
            .objective(
                "Refuse usernames that are nothing but spaces.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String name = \"   \";",
                "        // write the if line here",
                "            System.out.println(\"USERNAME REQUIRED\");",
                "        } else {",
                "            System.out.println(\"Registered: \" + name.trim());",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line that refuses a name which is empty or only "
                + "whitespace.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line: name is blank.")
                .accept("if (name.isBlank()) {", "if(name.isBlank()) {",
                        "if (name.isBlank()){", "if (name.trim().isEmpty()) {",
                        "if(name.trim().isEmpty()) {")
                .hints(
                    "isEmpty would say three spaces is fine.",
                    "The question is 'is there nothing but whitespace?'",
                    "if (name.isBlank()) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String name = \"   \";",
                    "        if (name.isBlank()) {",
                    "            System.out.println(\"USERNAME REQUIRED\");",
                    "        } else {",
                    "            System.out.println(\"Registered: \" + name.trim());",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "isBlank is true for three spaces, so the form refuses them. "
                    + "A real name is not blank and goes on to be registered - "
                    + "trimmed, so ' jsmith ' and 'jsmith' cannot become two "
                    + "accounts.\n"
                    + "\n"
                    + "isEmpty would have let the three spaces through, creating "
                    + "an account whose name prints as nothing at all: invisible "
                    + "in lists, impossible to search for, and a perfect place "
                    + "to hide.")
                .explain(
                    "Blank, not empty, is the question for a required field.")
                .xp(25))
            .mistakes(
                new String[]{"isEmpty for a required field",
                    "Three spaces pass it. Use isBlank or trim().isEmpty()."},
                new String[]{"isBlank on old Java",
                    "It needs Java 11 or later. trim().isEmpty() works "
                    + "everywhere."},
                new String[]{"Checking but storing the untrimmed value",
                    "Check with isBlank, then store name.trim()."})
            .cyber(
                "Invisible names are a recurring trick. An account called with "
                + "only spaces, or with characters that print as nothing, shows "
                + "up in an admin list as a blank row - easy to overlook, hard "
                + "to search for, and ideal for an attacker who wants a "
                + "foothold to go unnoticed.\n"
                + "\n"
                + "The same check protects every required field in a security "
                + "tool: a firewall rule with a blank source, a ticket with a "
                + "blank owner, an alert with a blank host. Blank values do not "
                + "just look sloppy; they break the filters and reports that "
                + "depend on them.")
            .check(new Task(Task.CHOICE,
                    "For \"   \" (three spaces), which is true?")
                .choices("isEmpty() true, isBlank() true",
                         "isEmpty() false, isBlank() true",
                         "isEmpty() true, isBlank() false",
                         "both false")
                .accept("2", "b")
                .hints("Three characters is not zero characters.",
                       "But all three are whitespace.")
                .explain(
                    "isEmpty() is false - there are three characters. isBlank() "
                    + "is true - they are all whitespace.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(\"a b\".isBlank());")
                .accept("false")
                .hints("Is there anything other than whitespace?",
                       "a and b are letters.")
                .explain(
                    "false. It contains a space, but also letters, so it is not "
                    + "blank.")
                .xp(10))
            .recap(
                "    isEmpty()          no characters\n"
                + "    isBlank()          only whitespace, or nothing\n"
                + "    trim().isEmpty()   the same as isBlank, on any Java\n"
                + "\n"
                + "Required fields ask isBlank. Store the trimmed value.")
            .next("Next: why a variable made inside an if can vanish."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(19), "Where a Variable Lives", 4)
            .brief(
                "The grading tool works out a severity label inside its if "
                + "statement, then tries to print it afterwards. It will not "
                + "compile: 'cannot find symbol'. The variable is right there "
                + "in the code.\n\n"
                + "It is - but not where the println is. Variables have a "
                + "home.")
            .willLearn("Scope", "Declaring before an if", "Definite assignment")
            .whyUseful(
                "As soon as programs make decisions, where a variable is "
                + "declared starts to matter. Understanding scope turns two of "
                + "the most confusing compiler errors into obvious fixes.")
            .concept("Scope",
                "A variable exists from where it is declared to the end of the "
                + "BLOCK it was declared in - the closing brace of the { } "
                + "around it. That region is its SCOPE.\n"
                + "\n"
                + "    if (score >= 7.0) {\n"
                + "        String label = \"HIGH\";\n"
                + "    }\n"
                + "    System.out.println(label);   does NOT compile\n"
                + "\n"
                + "label was born inside the if block and ends at its closing "
                + "brace. By the println, it no longer exists: 'cannot find "
                + "symbol'.\n"
                + "\n"
                + "THE FIX: declare the variable before the if, where the "
                + "println can see it, and only assign inside:\n"
                + "\n"
                + "    String label;\n"
                + "    if (score >= 7.0) {\n"
                + "        label = \"HIGH\";\n"
                + "    } else {\n"
                + "        label = \"NORMAL\";\n"
                + "    }\n"
                + "    System.out.println(label);\n"
                + "\n"
                + "DEFINITE ASSIGNMENT. Java refuses to let you read a variable "
                + "that might never have been given a value. Remove the else "
                + "above and the println fails with 'variable label might not "
                + "have been initialized' - because when the score is low, "
                + "nothing ever put a value in label.\n"
                + "\n"
                + "That rule is a safety feature: every path through the code "
                + "must give the variable a value before anyone reads it.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        double score = 8.2;",
                "        String label;",
                "        if (score >= 7.0) {",
                "            label = \"HIGH\";",
                "        } else {",
                "            label = \"NORMAL\";",
                "        }",
                "        System.out.println(\"SEVERITY: \" + label);",
                "    }",
                "}")
            .exampleOutput("SEVERITY: HIGH")
            .lineByLine(
                new String[]{"String label;",
                    "Declared outside the if, so it lives until the end of main."},
                new String[]{"label = \"HIGH\";",
                    "Assignment, not declaration - no type word. The variable "
                    + "already exists."},
                new String[]{"The else",
                    "Guarantees label gets a value on every path."},
                new String[]{"System.out.println(label)",
                    "label is in scope and definitely assigned. Compiles."})
            .predict(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "int failures = 7;",
                    "if (failures > 5) {",
                    "    String alert = \"BRUTE FORCE\";",
                    "}",
                    "System.out.println(alert);")
                .accept("5", "line 5")
                .hints("Where was alert declared?",
                       "Where does that block end?")
                .explain(
                    "Line 5. alert was declared inside the if block, and that "
                    + "block ended on line 4. On line 5 there is no such "
                    + "variable: 'cannot find symbol'.")
                .xp(20))
            .practice(new Task(Task.DEBUG,
                    "Declared in the right place this time. Which line still "
                    + "does not compile?")
                .code(
                    "int failures = 2;",
                    "String status;",
                    "if (failures > 5) {",
                    "    status = \"LOCKED\";",
                    "}",
                    "System.out.println(status);")
                .accept("6", "line 6")
                .hints(
                    "status is in scope on line 6. Does it always have a value?",
                    "What happens when failures is 5 or less?")
                .explain(
                    "Line 6: 'variable status might not have been initialized'. "
                    + "When the condition is false, nothing ever puts a value in "
                    + "status. Java does not look at the actual number 2 - it "
                    + "checks every possible path, and one path leaves status "
                    + "empty.")
                .xp(30))
            .objective(
                "Make sure the account status always has a value.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failures = 2;",
                "        String status;",
                "        if (failures > 5) {",
                "            status = \"LOCKED\";",
                "        } else {",
                "            // give status a value here",
                "        }",
                "        System.out.println(\"STATUS: \" + status);",
                "    }",
                "}")
            .yourTask(
                "Write the line inside the else that sets status to ACTIVE, so "
                + "every path gives it a value.")
            .mainTask(new Task(Task.WRITE,
                    "Write the assignment for the else branch.")
                .accept("status = \"ACTIVE\";", "status = \"ACTIVE\"")
                .hints(
                    "status is already declared. Do not write String again.",
                    "An assignment: name, =, value.",
                    "status = \"ACTIVE\";")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int failures = 2;",
                    "        String status;",
                    "        if (failures > 5) {",
                    "            status = \"LOCKED\";",
                    "        } else {",
                    "            status = \"ACTIVE\";",
                    "        }",
                    "        System.out.println(\"STATUS: \" + status);",
                    "    }",
                    "}")
                .whyItWorks(
                    "Now both paths assign status, so Java can prove it has a "
                    + "value by the println, and the program compiles and prints "
                    + "STATUS: ACTIVE.\n"
                    + "\n"
                    + "Writing String status = \"ACTIVE\"; inside the else would "
                    + "fail differently: it would declare a SECOND variable "
                    + "called status that only lives inside the else, leaving "
                    + "the outer one still unassigned - and Java refuses to have "
                    + "two variables of the same name overlapping like that.")
                .explain(
                    "Assign the existing variable; do not declare it again.")
                .xp(25))
            .mistakes(
                new String[]{"Declaring inside the block",
                    "The variable ends at the closing brace. Declare before the "
                    + "if."},
                new String[]{"Leaving a path unassigned",
                    "'might not have been initialized'. Every branch must set "
                    + "it - an else usually fixes it."},
                new String[]{"Re-declaring inside a branch",
                    "String status = ... inside the if makes a new variable. "
                    + "Assign the existing one."})
            .cyber(
                "The definite-assignment rule is Java quietly enforcing a "
                + "security principle. A decision variable - allowed, "
                + "clearance, riskLevel - that could be read before any path "
                + "set it is exactly the kind of gap that produces a default "
                + "nobody chose.\n"
                + "\n"
                + "Some languages would give such a variable whatever happened "
                + "to be in memory, or a default like false or zero, and carry "
                + "on. Java refuses to compile until every path decides. When "
                + "you add the missing else, decide it deliberately - and for "
                + "anything that grants access, make the default the safe "
                + "one.")
            .check(new Task(Task.CHOICE,
                    "A variable declared inside an if block can be used...")
                .choices("anywhere in main", "only inside that block",
                         "only after the if", "anywhere in the class")
                .accept("2", "b")
                .hints("Its scope ends at a brace.", "Which brace?")
                .explain(
                    "Only inside that block. Its scope ends at the block's "
                    + "closing brace.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int hour = 22;",
                    "String shift;",
                    "if (hour >= 8 && hour < 20) {",
                    "    shift = \"DAY\";",
                    "} else {",
                    "    shift = \"NIGHT\";",
                    "}",
                    "System.out.println(shift);")
                .accept("NIGHT")
                .hints("Is 22 between 8 and 20?", "The else runs.")
                .explain(
                    "NIGHT. 22 is not below 20, so the else assigns NIGHT. Both "
                    + "paths assign shift, so it compiles.")
                .xp(15))
            .recap(
                "A variable lives from its declaration to the end of its block.\n"
                + "\n"
                + "    String label;          declare before the if\n"
                + "    if (...) { label = ...; } else { label = ...; }\n"
                + "    use label here\n"
                + "\n"
                + "Every path must assign it before it is read.")
            .next("Next: an if that produces a value in one line."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(20), "An If That Gives a Value", 3)
            .brief(
                "The last mission needed five lines to set one label: declare, "
                + "if, assign, else, assign. For choosing between two values, "
                + "Java has a shorter form.\n\n"
                + "It is compact, easy to overuse, and worth knowing well "
                + "enough to recognise.")
            .willLearn("The conditional operator", "? and :", "When not to use it")
            .whyUseful(
                "Short either/or values - a label, a plural, a default - appear "
                + "constantly. The conditional operator writes them in one "
                + "readable line, and you will meet it in almost every codebase "
                + "you read.")
            .concept("condition ? a : b",
                "The CONDITIONAL OPERATOR chooses between two values:\n"
                + "\n"
                + "    condition ? valueIfTrue : valueIfFalse\n"
                + "\n"
                + "    String label = score >= 7.0 ? \"HIGH\" : \"NORMAL\";\n"
                + "\n"
                + "Read it as a question: 'score at least 7? then HIGH, "
                + "otherwise NORMAL'. It does the same as the five-line if/else "
                + "from the last mission.\n"
                + "\n"
                + "It is an EXPRESSION - it produces a value - so it goes where "
                + "a value goes: after an =, inside a println, as an argument:\n"
                + "\n"
                + "    System.out.println(count + \" attempt\"\n"
                + "            + (count == 1 ? \"\" : \"s\"));\n"
                + "\n"
                + "Both values must be the same kind of thing - two Strings, or "
                + "two numbers.\n"
                + "\n"
                + "BRACKETS. It has very low precedence, below + . So\n"
                + "\n"
                + "    \"Status: \" + locked ? \"L\" : \"U\"\n"
                + "\n"
                + "joins \"Status: \" + locked first, then tries to use that "
                + "String as the condition - which does not compile. Put the "
                + "whole ?: in brackets whenever it sits inside a bigger "
                + "expression.\n"
                + "\n"
                + "WHEN NOT TO. It chooses VALUES. For choosing ACTIONS - print "
                + "this, lock that - use if. And never nest them: a ? b : c ? "
                + "d : e is legal, and almost nobody reads it correctly first "
                + "time.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failures = 1;",
                "        String state = failures > 5 ? \"LOCKED\" : \"ACTIVE\";",
                "        System.out.println(state);",
                "        System.out.println(failures + \" failure\"",
                "                + (failures == 1 ? \"\" : \"s\"));",
                "    }",
                "}")
            .exampleOutput("ACTIVE", "1 failure")
            .lineByLine(
                new String[]{"failures > 5 ? \"LOCKED\" : \"ACTIVE\"",
                    "1 > 5 is false, so the value after the colon: ACTIVE."},
                new String[]{"(failures == 1 ? \"\" : \"s\")",
                    "Exactly one, so no s. Bracketed because it sits inside a + "
                    + "chain."},
                new String[]{"Both sides the same type",
                    "Two Strings each time, so the result is a String."},
                new String[]{"No actions inside",
                    "It only chooses values. The printing stays outside it."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "boolean patched = false;",
                    "System.out.println(patched ? \"OK\" : \"VULNERABLE\");")
                .accept("VULNERABLE")
                .hints("Is patched true?", "False picks the value after the colon.")
                .explain("VULNERABLE. The condition is false, so the second value.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int count = 3;",
                    "System.out.println(count + \" alert\" + (count == 1 ? \"\" : \"s\"));")
                .accept("3 alerts")
                .hints("Is count exactly 1?", "Not 1, so an s is added.")
                .explain(
                    "3 alerts. count == 1 is false, so \"s\" is chosen and joined "
                    + "on. With 1, it would print 1 alert.")
                .xp(20))
            .objective(
                "Label a host's patch state in one line.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        boolean patched = true;",
                "        // declare state here",
                "        System.out.println(\"WEB-01: \" + state);",
                "    }",
                "}")
            .yourTask(
                "Declare a String called state that is PATCHED when patched is "
                + "true and VULNERABLE otherwise, using the conditional "
                + "operator.")
            .mainTask(new Task(Task.WRITE,
                    "Declare state with the conditional operator.")
                .accept("String state = patched ? \"PATCHED\" : \"VULNERABLE\";",
                        "String state = patched ? \"PATCHED\" : \"VULNERABLE\"",
                        "String state = (patched ? \"PATCHED\" : \"VULNERABLE\");")
                .hints(
                    "The shape is condition ? valueIfTrue : valueIfFalse.",
                    "patched is already a boolean - it is the condition.",
                    "String state = patched ? \"PATCHED\" : \"VULNERABLE\";")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        boolean patched = true;",
                    "        String state = patched ? \"PATCHED\" : \"VULNERABLE\";",
                    "        System.out.println(\"WEB-01: \" + state);",
                    "    }",
                    "}")
                .whyItWorks(
                    "patched is true, so the operator hands back the value "
                    + "before the colon, PATCHED, and state holds it.\n"
                    + "\n"
                    + "One line instead of five, and still easy to read, because "
                    + "it only chooses between two plain values. The moment "
                    + "either branch needs to DO something - raise an alert, "
                    + "log a reason - an if/else is the right tool again.")
                .explain(
                    "condition ? valueIfTrue : valueIfFalse.")
                .xp(25))
            .mistakes(
                new String[]{"No brackets inside a bigger expression",
                    "\"S: \" + x ? a : b joins first. Write \"S: \" + (x ? a : b)."},
                new String[]{"Two different types",
                    "flag ? \"yes\" : 0 mixes a String and an int. Keep both "
                    + "sides the same kind."},
                new String[]{"Nesting them",
                    "a ? b : c ? d : e compiles and confuses everyone. Use "
                    + "if/else if."})
            .cyber(
                "Short conditional values are common in security code: "
                + "\"ALLOW\" or \"DENY\", a severity label, a masked or unmasked "
                + "value. Their brevity is useful - one line, one decision, easy "
                + "to audit.\n"
                + "\n"
                + "The same brevity is a risk when they are nested or when the "
                + "safe value is on the wrong side of the colon. "
                + "isAdmin ? \"ALLOW\" : \"DENY\" reads naturally; flipping it "
                + "while refactoring is a one-character change that inverts a "
                + "security decision. Keep them short, keep the safe default "
                + "obvious, and give anything complicated a full if/else.")
            .check(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "boolean locked = true;",
                    "String a = locked ? \"LOCKED\" : \"OPEN\";",
                    "String b = \"Status: \" + locked ? \"LOCKED\" : \"OPEN\";")
                .accept("3", "line 3")
                .hints(
                    "?: has lower precedence than +. What does + build first on "
                    + "line 3?",
                    "Can a String be used as the condition?")
                .explain(
                    "Line 3. The + runs first and builds \"Status: true\", a "
                    + "String, which then becomes the condition of ?: - and a "
                    + "condition must be a boolean. Brackets fix it: "
                    + "\"Status: \" + (locked ? \"LOCKED\" : \"OPEN\").")
                .xp(25))
            .check(new Task(Task.CHOICE,
                    "When is the conditional operator the WRONG tool?")
                .choices("Choosing between two labels",
                         "Adding an s for plurals",
                         "Deciding whether to lock an account and alert the team",
                         "Picking a default value")
                .accept("3", "c")
                .hints("?: chooses values.", "Which option is about actions?")
                .explain(
                    "Locking an account and alerting the team are actions, not "
                    + "values. That is what if is for.")
                .xp(15))
            .recap(
                "    condition ? valueIfTrue : valueIfFalse\n"
                + "\n"
                + "An expression: it produces a value. Both sides the same type.\n"
                + "\n"
                + "Bracket it inside a bigger expression. Values only, never "
                + "actions. Never nested.")
            .next("Next: choosing between many exact values with switch."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(21), "Many Exact Values", 3)
            .brief(
                "The port report names the service on each open port: 22 is "
                + "SSH, 80 is HTTP, 443 is HTTPS, anything else is unknown. An "
                + "else-if chain works, but every line repeats port == .\n\n"
                + "When one value is compared against a list of exact "
                + "possibilities, Java has a statement built for it.")
            .willLearn("switch", "case and break", "default")
            .whyUseful(
                "Menus, commands, status codes, ports, message types - "
                + "choosing by exact value is everywhere. switch says 'one of "
                + "these' more clearly than a long chain of ==.")
            .concept("switch",
                "A switch compares ONE value against a list of exact CASES:\n"
                + "\n"
                + "    switch (port) {\n"
                + "        case 22:\n"
                + "            System.out.println(\"SSH\");\n"
                + "            break;\n"
                + "        case 443:\n"
                + "            System.out.println(\"HTTPS\");\n"
                + "            break;\n"
                + "        default:\n"
                + "            System.out.println(\"UNKNOWN\");\n"
                + "    }\n"
                + "\n"
                + "    switch (port)    the value being examined\n"
                + "    case 22:         run from here if port is 22\n"
                + "    break;           leave the switch\n"
                + "    default:         when no case matched\n"
                + "\n"
                + "Java jumps straight to the matching case and runs its lines "
                + "until it meets a break. If nothing matches, it runs default; "
                + "if there is no default, nothing at all.\n"
                + "\n"
                + "LIMITS. Each case is one exact CONSTANT value. No ranges, no "
                + "conditions: case > 1000: does not exist. For ranges, use an "
                + "if/else if chain. And two cases may not have the same value "
                + "- that is a compile error.\n"
                + "\n"
                + "switch works with int, char, String (mission 23) and a few "
                + "other types. It does not work with double or boolean.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int port = 22;",
                "        switch (port) {",
                "            case 22:",
                "                System.out.println(\"SSH\");",
                "                break;",
                "            case 80:",
                "                System.out.println(\"HTTP\");",
                "                break;",
                "            case 443:",
                "                System.out.println(\"HTTPS\");",
                "                break;",
                "            default:",
                "                System.out.println(\"UNKNOWN\");",
                "        }",
                "    }",
                "}")
            .exampleOutput("SSH")
            .lineByLine(
                new String[]{"switch (port)",
                    "Examine port once. Its value is 22."},
                new String[]{"case 22:",
                    "Matches. Java starts running here."},
                new String[]{"break;",
                    "Leaves the switch. Nothing below runs."},
                new String[]{"default:",
                    "Would run for any port not listed - 3389, 25, anything."})
            .predict(new Task(Task.PREDICT,
                    "The example's switch, with port = 443. What does it "
                    + "print?")
                .code(
                    "int port = 443;",
                    "switch (port) {",
                    "    case 22:",
                    "        System.out.println(\"SSH\");",
                    "        break;",
                    "    case 443:",
                    "        System.out.println(\"HTTPS\");",
                    "        break;",
                    "    default:",
                    "        System.out.println(\"UNKNOWN\");",
                    "}")
                .accept("HTTPS")
                .hints("Which case matches 443?", "Then the break ends it.")
                .explain("HTTPS. Java jumps to case 443, prints, and breaks out.")
                .xp(10))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "int port = 80;",
                    "switch (port) {",
                    "    case 80:",
                    "        System.out.println(\"HTTP\");",
                    "        break;",
                    "    case 80:",
                    "        System.out.println(\"ALSO HTTP\");",
                    "        break;",
                    "}")
                .accept("6", "line 6")
                .hints("Look at the case values.",
                       "Can two cases have the same value?")
                .explain(
                    "Line 6: 'duplicate case label'. Each value can appear only "
                    + "once, because a switch must know exactly where to jump. "
                    + "The compiler catching this is useful: two cases with the "
                    + "same value would mean one of them could never run.")
                .xp(20))
            .objective(
                "Add HTTPS to the port report.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int port = 443;",
                "        switch (port) {",
                "            case 22:",
                "                System.out.println(\"SSH\");",
                "                break;",
                "            // write the case line for 443 here",
                "                System.out.println(\"HTTPS\");",
                "                break;",
                "            default:",
                "                System.out.println(\"UNKNOWN\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the case line that makes port 443 print HTTPS.")
            .mainTask(new Task(Task.WRITE,
                    "Write the case label for 443.")
                .accept("case 443:", "case 443 :")
                .hints(
                    "Look at how case 22 is written.",
                    "The keyword case, the value, then a colon - not a "
                    + "semicolon.",
                    "case 443:")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int port = 443;",
                    "        switch (port) {",
                    "            case 22:",
                    "                System.out.println(\"SSH\");",
                    "                break;",
                    "            case 443:",
                    "                System.out.println(\"HTTPS\");",
                    "                break;",
                    "            default:",
                    "                System.out.println(\"UNKNOWN\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "case 443: is a label: a place Java jumps to when the value "
                    + "is 443. From there it runs the println and stops at the "
                    + "break.\n"
                    + "\n"
                    + "Notice the colon. A case is not a statement, so it does "
                    + "not end with a semicolon; it marks where statements begin. "
                    + "Each case added is one line, instead of another "
                    + "} else if (port == ...) {.")
                .explain(
                    "case, the exact value, and a colon.")
                .xp(20))
            .mistakes(
                new String[]{"A semicolon after the case",
                    "case 443; does not compile. Case labels end with a colon."},
                new String[]{"Trying a range",
                    "case > 1000: is not Java. Ranges need if/else if."},
                new String[]{"Switching on a double",
                    "switch does not accept double. Decimals are compared with "
                    + "if and a tolerance."})
            .cyber(
                "switch is a natural fit for protocol handling: message types, "
                + "command codes, status numbers. A server reads a type byte "
                + "from the network and switches on it to decide what to do.\n"
                + "\n"
                + "That makes the default case a security boundary. Every value "
                + "the switch does not list - including values no legitimate "
                + "client ever sends - goes there. A default that quietly treats "
                + "the unknown like a known case, or does nothing and lets "
                + "processing continue, is where fuzzers find crashes and "
                + "attackers find behaviour nobody designed. Mission 25 is "
                + "about getting it right.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int port = 3389;",
                    "switch (port) {",
                    "    case 22:",
                    "        System.out.println(\"SSH\");",
                    "        break;",
                    "    default:",
                    "        System.out.println(\"UNKNOWN - review\");",
                    "}")
                .accept("UNKNOWN - review")
                .hints("Is 3389 listed?", "No case matched.")
                .explain(
                    "UNKNOWN - review. No case matches 3389, so default runs. "
                    + "(3389 is Windows Remote Desktop - worth reviewing indeed.)")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Which can NOT be a case label?")
                .choices("case 80:", "case 'Y':", "case > 1000:", "case \"quit\":")
                .accept("3", "c")
                .hints("Every case is one exact value.",
                       "Which one is a condition?")
                .explain(
                    "case > 1000: is a condition, not a value, and switch has no "
                    + "way to express it. The others are exact values of int, "
                    + "char and String.")
                .xp(15))
            .recap(
                "    switch (value) {\n"
                + "        case 22:  ...  break;\n"
                + "        default:  ...\n"
                + "    }\n"
                + "\n"
                + "One value against exact constants. break leaves; default "
                + "catches the rest. No ranges, no doubles, no duplicates.")
            .next("Next: what happens when a break goes missing."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(22), "Falling Through", 4)
            .brief(
                "The permissions tool uses a switch on the user's level. Level "
                + "1 is a guest and should only get READ. After a code tidy-up, "
                + "guests started receiving DELETE as well.\n\n"
                + "Somebody removed a break.")
            .willLearn("switch", "Fall-through", "Grouping cases on purpose")
            .whyUseful(
                "Fall-through is the single most common switch bug, and in "
                + "permission code it is a privilege escalation. Knowing exactly "
                + "how it works also lets you use it deliberately, which is "
                + "sometimes exactly right.")
            .concept("Fall-through",
                "A case is only a place to START. Java runs from the matching "
                + "case downward, through the following cases, until it meets "
                + "a break or the end of the switch. This is called "
                + "FALL-THROUGH.\n"
                + "\n"
                + "    switch (level) {\n"
                + "        case 1:\n"
                + "            System.out.println(\"READ\");\n"
                + "        case 2:\n"
                + "            System.out.println(\"WRITE\");\n"
                + "            break;\n"
                + "    }\n"
                + "\n"
                + "With level 1, this prints READ and then WRITE: there was no "
                + "break after READ, so Java carried on into case 2's lines. "
                + "The case 2: label does not stop anything - it is only a "
                + "label.\n"
                + "\n"
                + "ON PURPOSE. Stacking labels with nothing between them lets "
                + "several values share one block:\n"
                + "\n"
                + "    case 80:\n"
                + "    case 8080:\n"
                + "        System.out.println(\"HTTP\");\n"
                + "        break;\n"
                + "\n"
                + "That is clear and common. Fall-through from a case that DOES "
                + "something into the next case is almost always a mistake - "
                + "and when it is deliberate, write a comment saying so, "
                + "because the next person will assume it is a bug.\n"
                + "\n"
                + "The last case or default needs no break, but many people add "
                + "one anyway, so that adding a case below it later cannot "
                + "create a fall-through.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int level = 1;",
                "        switch (level) {",
                "            case 1:",
                "                System.out.println(\"grant READ\");",
                "            case 2:",
                "                System.out.println(\"grant WRITE\");",
                "            case 3:",
                "                System.out.println(\"grant DELETE\");",
                "        }",
                "    }",
                "}")
            .exampleOutput("grant READ", "grant WRITE", "grant DELETE")
            .lineByLine(
                new String[]{"case 1:",
                    "Matches level 1. Java starts here."},
                new String[]{"grant READ",
                    "Correct so far."},
                new String[]{"No break",
                    "Java carries straight on through case 2 and case 3. The "
                    + "labels do not stop it."},
                new String[]{"grant DELETE",
                    "A guest just received the highest permission. Nothing "
                    + "crashed and nothing warned."})
            .predict(new Task(Task.PREDICT,
                    "What are the TWO lines of output?")
                .code(
                    "int code = 2;",
                    "switch (code) {",
                    "    case 1:",
                    "        System.out.println(\"ONE\");",
                    "    case 2:",
                    "        System.out.println(\"TWO\");",
                    "    case 3:",
                    "        System.out.println(\"THREE\");",
                    "        break;",
                    "    case 4:",
                    "        System.out.println(\"FOUR\");",
                    "}")
                .accept("TWO THREE", "TWO, THREE", "TWO and THREE")
                .hints(
                    "Start at the matching case.",
                    "Keep going until a break.")
                .explain(
                    "    TWO\n"
                    + "    THREE\n"
                    + "\n"
                    + "Java starts at case 2, has no break, falls into case 3, "
                    + "and stops at the break there. ONE is above the starting "
                    + "point and FOUR is after the break, so neither runs.")
                .xp(25))
            .practice(new Task(Task.PREDICT,
                    "Deliberate grouping. What does this print?")
                .code(
                    "int port = 8080;",
                    "switch (port) {",
                    "    case 80:",
                    "    case 8080:",
                    "        System.out.println(\"HTTP\");",
                    "        break;",
                    "    case 443:",
                    "        System.out.println(\"HTTPS\");",
                    "        break;",
                    "}")
                .accept("HTTP")
                .hints("Which label matches 8080?",
                       "What comes after it, before a break?")
                .explain(
                    "HTTP. case 8080 matches; its lines are shared with case 80 "
                    + "above it; the break stops before HTTPS. Stacked labels "
                    + "with nothing between them are the good kind of "
                    + "fall-through.")
                .xp(20))
            .objective(
                "Fix the guest permissions.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int level = 1;",
                "        switch (level) {",
                "            case 1:",
                "                System.out.println(\"grant READ\");",
                "                // guests must stop here",
                "            case 2:",
                "                System.out.println(\"grant WRITE\");",
                "                break;",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the one line that goes where the comment is, so that "
                + "level 1 gets READ and nothing more.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line that stops the fall-through.")
                .accept("break;", "break")
                .hints(
                    "What ends a case's run?",
                    "One word and a semicolon.",
                    "break;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int level = 1;",
                    "        switch (level) {",
                    "            case 1:",
                    "                System.out.println(\"grant READ\");",
                    "                break;",
                    "            case 2:",
                    "                System.out.println(\"grant WRITE\");",
                    "                break;",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "break leaves the switch immediately after READ is granted, "
                    + "so case 2's lines never run for a guest.\n"
                    + "\n"
                    + "One missing word turned a read-only account into one that "
                    + "could write. The switch looked correct at a glance - "
                    + "every case had the right permission beside it - which is "
                    + "exactly why fall-through bugs survive code review.")
                .explain(
                    "break ends the case; without it, Java falls into the next.")
                .xp(25))
            .mistakes(
                new String[]{"A missing break",
                    "Java runs on into the next case's lines. Every case that "
                    + "does something needs a break."},
                new String[]{"Thinking a case label stops execution",
                    "Labels are only places to start. Only break stops."},
                new String[]{"Deliberate fall-through with no comment",
                    "The next reader will 'fix' it. Say it is intentional."})
            .cyber(
                "Fall-through in a permission switch is a textbook privilege "
                + "escalation: a lower role runs straight into the code meant "
                + "for a higher one. It has turned up in real access-control "
                + "code, and it is exactly the kind of flaw that passes a "
                + "casual review, because each case on its own looks right.\n"
                + "\n"
                + "Two habits prevent it: a break after every case that does "
                + "anything, and ordering permission cases so that falling "
                + "through could only ever grant LESS, never more. The switch "
                + "expression in mission 24 removes the problem entirely.")
            .check(new Task(Task.CHOICE,
                    "What stops Java running on into the next case?")
                .choices("The next case label", "A blank line",
                         "A break statement", "The end of the line")
                .accept("3", "c")
                .hints("Case labels do not stop anything.",
                       "It is a one-word statement.")
                .explain(
                    "break. A case label is only a place to begin; Java keeps "
                    + "going until a break or the end of the switch.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int level = 3;",
                    "switch (level) {",
                    "    case 1:",
                    "        System.out.println(\"READ\");",
                    "        break;",
                    "    case 3:",
                    "        System.out.println(\"DELETE\");",
                    "    default:",
                    "        System.out.println(\"AUDIT\");",
                    "}")
                .accept("DELETE AUDIT", "DELETE, AUDIT", "DELETE and AUDIT")
                .hints("Start at case 3.", "Is there a break before default?")
                .explain(
                    "    DELETE\n"
                    + "    AUDIT\n"
                    + "\n"
                    + "No break after DELETE, so Java falls into default as well. "
                    + "default is just another label.")
                .xp(20))
            .recap(
                "A case is where Java starts, not where it stops. Without a "
                + "break it falls through into the next case's lines.\n"
                + "\n"
                + "    case 80:\n"
                + "    case 8080:   stacked labels share one block - fine\n"
                + "\n"
                + "In permission code, a missing break escalates privilege.")
            .next("Next: switching on text."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(23), "Switching on Text", 3)
            .brief(
                "The analyst console reads commands: status, scan, lock, "
                + "quit. A chain of equals calls works, but a switch reads "
                + "better - and switch works on Strings too.\n\n"
                + "With one catch about capitals.")
            .willLearn("switch", "switch on a String", "Normalising before switching")
            .whyUseful(
                "Command interpreters, protocol verbs like GET and POST, "
                + "message types and configuration keys are all text chosen "
                + "from a fixed list. switch on a String handles all of them.")
            .concept("switch on a String",
                "switch accepts a String, with each case a String literal:\n"
                + "\n"
                + "    switch (command) {\n"
                + "        case \"status\":\n"
                + "            ...\n"
                + "            break;\n"
                + "        case \"quit\":\n"
                + "            ...\n"
                + "            break;\n"
                + "        default:\n"
                + "            System.out.println(\"Unknown command\");\n"
                + "    }\n"
                + "\n"
                + "Behind the scenes it compares with equals, not ==, so it "
                + "matches text correctly however the String was made.\n"
                + "\n"
                + "But equals is EXACT. STATUS does not match \"status\". For "
                + "commands, normalise first - trim and lower-case - and write "
                + "every case in lower case:\n"
                + "\n"
                + "    switch (command.trim().toLowerCase()) {\n"
                + "\n"
                + "The switch examines the cleaned-up copy. The original "
                + "command is unchanged, which is handy for logging exactly "
                + "what was typed.\n"
                + "\n"
                + "Everything from missions 21 and 22 still applies: break after "
                + "each case, and a default for anything not listed.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String command = \"  SCAN \";",
                "        switch (command.trim().toLowerCase()) {",
                "            case \"status\":",
                "                System.out.println(\"All systems monitored\");",
                "                break;",
                "            case \"scan\":",
                "                System.out.println(\"Scan queued\");",
                "                break;",
                "            default:",
                "                System.out.println(\"Unknown command\");",
                "        }",
                "    }",
                "}")
            .exampleOutput("Scan queued")
            .lineByLine(
                new String[]{"command.trim().toLowerCase()",
                    "\"  SCAN \" becomes \"scan\" - the value the switch "
                    + "examines."},
                new String[]{"case \"scan\":",
                    "Compared with equals. Matches."},
                new String[]{"break;",
                    "Leaves the switch, as always."},
                new String[]{"Without normalising",
                    "\"  SCAN \" matches nothing and the analyst gets 'Unknown "
                    + "command' for a command that exists."})
            .predict(new Task(Task.PREDICT,
                    "No normalising this time. What does this print?")
                .code(
                    "String command = \"Quit\";",
                    "switch (command) {",
                    "    case \"quit\":",
                    "        System.out.println(\"Bye\");",
                    "        break;",
                    "    default:",
                    "        System.out.println(\"Unknown command\");",
                    "}")
                .accept("Unknown command")
                .hints("switch compares with equals.",
                       "Does Quit equal quit exactly?")
                .explain(
                    "Unknown command. The comparison is exact, and Q is not q. "
                    + "Lower-casing before the switch would have matched it.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Why does switch on a String work correctly for typed "
                    + "input, when == would not?")
                .choices("It ignores capitals",
                         "It compares with equals behind the scenes",
                         "It trims the text first",
                         "It only works on literals")
                .accept("2", "b")
                .hints("Mission 11: == compares locations.",
                       "switch uses the method that compares characters.")
                .explain(
                    "It compares with equals, so text built at run time matches "
                    + "correctly. It does NOT ignore capitals or trim - those you "
                    + "do yourself before the switch.")
                .xp(20))
            .objective(
                "Make the console accept commands in any capitals.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String command = \"LOCK\";",
                "        // write the switch line here",
                "            case \"lock\":",
                "                System.out.println(\"Account locked\");",
                "                break;",
                "            default:",
                "                System.out.println(\"Unknown command\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the switch line so it examines command lower-cased. The "
                + "cases are all in lower case.")
            .mainTask(new Task(Task.WRITE,
                    "Write the switch line, on command in lower case.")
                .accept("switch (command.toLowerCase()) {",
                        "switch(command.toLowerCase()) {",
                        "switch (command.toLowerCase()){",
                        "switch (command.trim().toLowerCase()) {",
                        "switch(command.trim().toLowerCase()) {")
                .hints(
                    "The switch examines whatever is in its brackets.",
                    "Put the lower-cased copy there.",
                    "switch (command.toLowerCase()) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String command = \"LOCK\";",
                    "        switch (command.toLowerCase()) {",
                    "            case \"lock\":",
                    "                System.out.println(\"Account locked\");",
                    "                break;",
                    "            default:",
                    "                System.out.println(\"Unknown command\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "command.toLowerCase() hands the switch \"lock\", which "
                    + "equals the case exactly. LOCK, Lock and lock all behave "
                    + "the same.\n"
                    + "\n"
                    + "Adding .trim() as well would also forgive stray spaces - "
                    + "for typed commands, usually worth it. Both are accepted "
                    + "here.")
                .explain(
                    "Normalise inside the switch brackets; write cases in lower "
                    + "case.")
                .xp(25))
            .mistakes(
                new String[]{"Forgetting it is exact",
                    "switch on a String is case-sensitive. Normalise first."},
                new String[]{"Mixed-case case labels",
                    "Once the input is lower-cased, a case \"Quit\" can never "
                    + "match. Keep every case in lower case."},
                new String[]{"No default",
                    "Unknown commands then do nothing silently. Tell the user."})
            .cyber(
                "Command handling is a trust boundary: text from a person or a "
                + "network decides what code runs. A switch with an exact list "
                + "of allowed commands and a default that refuses everything "
                + "else is an allowlist - the safe shape.\n"
                + "\n"
                + "The dangerous alternative is building an action from the "
                + "input itself - running whatever command was named, or "
                + "loading whatever file was asked for. Many remote-execution "
                + "vulnerabilities come from exactly that shortcut. A fixed "
                + "switch cannot be talked into running something that is not "
                + "on its list.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String verb = \"post\".toUpperCase();",
                    "switch (verb) {",
                    "    case \"GET\":",
                    "        System.out.println(\"read\");",
                    "        break;",
                    "    case \"POST\":",
                    "        System.out.println(\"write\");",
                    "        break;",
                    "}")
                .accept("write")
                .hints("What is in verb after toUpperCase?",
                       "Which case equals POST?")
                .explain(
                    "write. verb is POST, which equals case \"POST\". Upper case "
                    + "works just as well as lower, as long as input and cases "
                    + "agree.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Which switch accepts status, STATUS and ' Status '?")
                .choices("switch (command)",
                         "switch (command.trim().toLowerCase()) with case \"status\"",
                         "switch (command) with case \"STATUS\"",
                         "switch (command.toUpperCase()) with case \"status\"")
                .accept("2", "b")
                .hints("Remove the spaces and settle the case.",
                       "Then the case must be written in that same case.")
                .explain(
                    "Trimming and lower-casing turns all three into \"status\", "
                    + "which the lower-case case matches. Option D upper-cases "
                    + "the input but writes the case in lower case, so it never "
                    + "matches.")
                .xp(20))
            .recap(
                "    switch (command.trim().toLowerCase()) {\n"
                + "        case \"quit\": ... break;\n"
                + "        default:     ...\n"
                + "    }\n"
                + "\n"
                + "Compared with equals: exact, so normalise first. Cases all in "
                + "the same case as the normalised input.")
            .next("Next: a switch that produces a value, with no breaks to "
                + "forget."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(24), "A Switch That Gives a Value", 4)
            .brief(
                "Most switches in the port report exist to set one variable: "
                + "service = \"SSH\", break, service = \"HTTP\", break. Each "
                + "break is a chance to forget one.\n\n"
                + "Modern Java has a form of switch that gives a value directly "
                + "and cannot fall through.")
            .willLearn("Switch expressions", "case ->", "Exhaustiveness")
            .whyUseful(
                "Switch expressions remove fall-through bugs completely and "
                + "make mappings - port to service, level to permission - "
                + "read like a table. They are what current Java code uses.")
            .concept("Switch expressions",
                "Since Java 14, switch can PRODUCE a value, like the ? : "
                + "operator:\n"
                + "\n"
                + "    String service = switch (port) {\n"
                + "        case 22 -> \"SSH\";\n"
                + "        case 80, 8080 -> \"HTTP\";\n"
                + "        case 443 -> \"HTTPS\";\n"
                + "        default -> \"UNKNOWN\";\n"
                + "    };\n"
                + "\n"
                + "What is different from the old switch:\n"
                + "\n"
                + "    ->         an arrow instead of a colon\n"
                + "    no break   each case runs only its own value -\n"
                + "               fall-through cannot happen\n"
                + "    80, 8080   several values share a case with commas\n"
                + "    };         it is part of a statement, so the\n"
                + "               closing brace needs a semicolon\n"
                + "\n"
                + "EXHAUSTIVE. A switch expression must produce a value for "
                + "EVERY possible input, so for an int or a String it needs a "
                + "default. Leave it out and the program does not compile. "
                + "That is a feature: no value can ever slip through "
                + "unhandled.\n"
                + "\n"
                + "The arrow form also works as a statement, running one "
                + "statement per case:\n"
                + "\n"
                + "    case 22 -> System.out.println(\"SSH\");\n"
                + "\n"
                + "For new code, prefer the arrow form. You still need to read "
                + "the colon-and-break form, because most existing code uses "
                + "it.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int port = 8080;",
                "        String service = switch (port) {",
                "            case 22 -> \"SSH\";",
                "            case 80, 8080 -> \"HTTP\";",
                "            case 443 -> \"HTTPS\";",
                "            default -> \"UNKNOWN\";",
                "        };",
                "        System.out.println(port + \" is \" + service);",
                "    }",
                "}")
            .exampleOutput("8080 is HTTP")
            .lineByLine(
                new String[]{"String service = switch (port) {",
                    "The whole switch produces a value, stored in service."},
                new String[]{"case 80, 8080 -> \"HTTP\";",
                    "Either value gives HTTP. 8080 matches."},
                new String[]{"No break anywhere",
                    "Each arrow case produces its value and the switch is done."},
                new String[]{"};",
                    "The declaration ends here, so it needs its semicolon."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int level = 1;",
                    "String grant = switch (level) {",
                    "    case 1 -> \"READ\";",
                    "    case 2 -> \"WRITE\";",
                    "    default -> \"NONE\";",
                    "};",
                    "System.out.println(grant);")
                .accept("READ")
                .hints("Which case matches 1?",
                       "Can an arrow case fall through?")
                .explain(
                    "READ - only READ. Arrow cases never fall through, so the "
                    + "mission 22 bug cannot be written this way.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "int port = 22;",
                    "String service = switch (port) {",
                    "    case 22 -> \"SSH\";",
                    "    case 443 -> \"HTTPS\";",
                    "};",
                    "System.out.println(service);")
                .accept("2", "line 2")
                .hints("What if port were 25?",
                       "A switch expression must give a value for every input.")
                .explain(
                    "Line 2: 'the switch expression does not cover all possible "
                    + "input values'. With no default, a port like 25 would "
                    + "produce nothing, so Java refuses. The error points at the "
                    + "switch itself, where the value is missing.")
                .xp(25))
            .objective(
                "Map both HTTP ports in the service table.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int port = 80;",
                "        String service = switch (port) {",
                "            case 22 -> \"SSH\";",
                "            // write the HTTP case here: ports 80 and 8080",
                "            case 443 -> \"HTTPS\";",
                "            default -> \"UNKNOWN\";",
                "        };",
                "        System.out.println(service);",
                "    }",
                "}")
            .yourTask(
                "Write one case line that gives HTTP for both 80 and 8080.")
            .mainTask(new Task(Task.WRITE,
                    "Write the case for 80 and 8080.")
                .accept("case 80, 8080 -> \"HTTP\";", "case 8080, 80 -> \"HTTP\";",
                        "case 80,8080 -> \"HTTP\";")
                .hints(
                    "Several values share an arrow case, separated by commas.",
                    "The arrow, then the value, then a semicolon.",
                    "case 80, 8080 -> \"HTTP\";")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int port = 80;",
                    "        String service = switch (port) {",
                    "            case 22 -> \"SSH\";",
                    "            case 80, 8080 -> \"HTTP\";",
                    "            case 443 -> \"HTTPS\";",
                    "            default -> \"UNKNOWN\";",
                    "        };",
                    "        System.out.println(service);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The comma lists both values in one case, and the arrow "
                    + "gives HTTP for either. No stacked labels, no break, and "
                    + "no way for 80 to fall into the HTTPS case below it.\n"
                    + "\n"
                    + "The table reads top to bottom as exactly what it is - a "
                    + "mapping from ports to names - which makes a missing or "
                    + "wrong entry easy to spot in review.")
                .explain(
                    "Values separated by commas, then -> and the result.")
                .xp(25))
            .mistakes(
                new String[]{"Leaving out default",
                    "A switch expression on an int or String must cover every "
                    + "value. Add default."},
                new String[]{"Forgetting the final semicolon",
                    "String s = switch (...) { ... }; - the declaration needs "
                    + "its ;"},
                new String[]{"Mixing -> and : in one switch",
                    "Use one form throughout a switch; Java refuses to mix "
                    + "them."})
            .cyber(
                "Exhaustiveness is a security property. A switch expression "
                + "cannot be compiled if some input would produce no decision, "
                + "so 'we forgot that case' becomes a build failure instead of "
                + "a silent hole.\n"
                + "\n"
                + "Combined with no fall-through, it removes two of the "
                + "classic ways a permission or routing decision goes wrong. "
                + "What remains is choosing the default well - which is the "
                + "whole of the next mission.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String code = \"W\";",
                    "String level = switch (code) {",
                    "    case \"I\" -> \"INFO\";",
                    "    case \"W\" -> \"WARNING\";",
                    "    case \"E\" -> \"ERROR\";",
                    "    default -> \"UNKNOWN\";",
                    "};",
                    "System.out.println(level);")
                .accept("WARNING")
                .hints("Which case matches \"W\"?", "One value, no fall-through.")
                .explain("WARNING. Switch expressions work on Strings too.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Which is NOT true of a switch expression with arrows?")
                .choices("It cannot fall through",
                         "It must handle every possible value",
                         "Each case needs a break",
                         "A case can list several values")
                .accept("3", "c")
                .hints("What does the arrow replace?",
                       "Breaks existed to stop fall-through.")
                .explain(
                    "Each case needs a break - false. Arrow cases never fall "
                    + "through, so there is nothing for a break to stop.")
                .xp(15))
            .recap(
                "    String s = switch (x) {\n"
                + "        case 1, 2 -> \"A\";\n"
                + "        default -> \"B\";\n"
                + "    };\n"
                + "\n"
                + "Arrows, no break, no fall-through. Must cover every value, so "
                + "default is required. Java 14 and later.")
            .next("Next: the case nobody thought of."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(25), "The Case Nobody Thought Of", 3)
            .brief(
                "The access tool switches on a user's role: admin, analyst, "
                + "guest. Then someone's account arrived with the role "
                + "'contractor', from a system nobody had told the security "
                + "team about. The switch had no default, and the code after it "
                + "assumed access had been decided.\n\n"
                + "It had not. The contractor walked in.")
            .willLearn("switch", "default as a safety net", "Failing closed")
            .whyUseful(
                "Real data always contains values the designer did not expect. "
                + "What the default case does with them decides whether "
                + "surprises are refused, reported, or let through.")
            .concept("A default that fails closed",
                "default runs for every value the cases do not list: typos, "
                + "new roles, corrupted data, and input crafted to be "
                + "unexpected. It is the same idea as the else from mission 3.\n"
                + "\n"
                + "For anything that GRANTS something, the default must be the "
                + "safe answer:\n"
                + "\n"
                + "    String access = switch (role) {\n"
                + "        case \"admin\" -> \"FULL\";\n"
                + "        case \"analyst\" -> \"READ-WRITE\";\n"
                + "        case \"guest\" -> \"READ\";\n"
                + "        default -> \"NONE\";\n"
                + "    };\n"
                + "\n"
                + "A role nobody planned for gets nothing. That is FAILING "
                + "CLOSED.\n"
                + "\n"
                + "Three things a good default does:\n"
                + "\n"
                + "    refuse    the safe outcome, not a guess\n"
                + "    report    say what arrived, so someone can look\n"
                + "    never     copy a real case 'to be helpful'\n"
                + "\n"
                + "A default like  default -> \"READ\"  looks harmless and "
                + "means every unknown role - including made-up ones - gets "
                + "access. Writing out a list of allowed values and refusing "
                + "the rest is the allowlist idea again, in switch form.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String role = \"contractor\";",
                "        String access = switch (role) {",
                "            case \"admin\" -> \"FULL\";",
                "            case \"analyst\" -> \"READ-WRITE\";",
                "            case \"guest\" -> \"READ\";",
                "            default -> \"NONE\";",
                "        };",
                "        System.out.println(role + \": \" + access);",
                "    }",
                "}")
            .exampleOutput("contractor: NONE")
            .lineByLine(
                new String[]{"role = \"contractor\"",
                    "A value nobody listed."},
                new String[]{"The three cases",
                    "None match."},
                new String[]{"default -> \"NONE\"",
                    "The unknown gets nothing - the safe outcome."},
                new String[]{"The printout",
                    "Names the role that arrived, so the refusal can be "
                    + "investigated."})
            .predict(new Task(Task.PREDICT,
                    "A typo in the role. What does this print?")
                .code(
                    "String role = \"admn\";",
                    "String access = switch (role) {",
                    "    case \"admin\" -> \"FULL\";",
                    "    case \"guest\" -> \"READ\";",
                    "    default -> \"NONE\";",
                    "};",
                    "System.out.println(access);")
                .accept("NONE")
                .hints("Does admn equal admin?", "No case matches.")
                .explain(
                    "NONE. The typo matches nothing, so the default refuses. A "
                    + "typo costs an admin a minute; the opposite default would "
                    + "cost you the system.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Which default is safest for a switch that grants access?")
                .choices("default -> \"READ\"", "default -> \"FULL\"",
                         "default -> \"NONE\"", "no default at all")
                .accept("3", "c")
                .hints("The default handles values nobody planned for.",
                       "Fail closed.")
                .explain(
                    "default -> \"NONE\". Unknown values get nothing. READ grants "
                    + "something to every made-up role, FULL grants everything, "
                    + "and with no default a switch expression will not even "
                    + "compile.")
                .xp(20))
            .objective(
                "Close the gap in the access switch.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String role = \"contractor\";",
                "        String access = switch (role) {",
                "            case \"admin\" -> \"FULL\";",
                "            case \"guest\" -> \"READ\";",
                "            // write the default here",
                "        };",
                "        System.out.println(role + \": \" + access);",
                "    }",
                "}")
            .yourTask(
                "Write the default case so any role not listed gets DENY.")
            .mainTask(new Task(Task.WRITE,
                    "Write the default that denies unknown roles.")
                .accept("default -> \"DENY\";", "default->\"DENY\";")
                .hints(
                    "Same shape as the cases above it, with default instead of "
                    + "case and a value.",
                    "The safe outcome for an unknown role is DENY.",
                    "default -> \"DENY\";")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String role = \"contractor\";",
                    "        String access = switch (role) {",
                    "            case \"admin\" -> \"FULL\";",
                    "            case \"guest\" -> \"READ\";",
                    "            default -> \"DENY\";",
                    "        };",
                    "        System.out.println(role + \": \" + access);",
                    "    }",
                    "}")
                .whyItWorks(
                    "contractor matches neither case, so the default produces "
                    + "DENY. Every role nobody planned for - today's contractor, "
                    + "tomorrow's typo, an attacker's invented role - is "
                    + "refused.\n"
                    + "\n"
                    + "The switch expression would not even compile without a "
                    + "default. What the compiler cannot do is choose the RIGHT "
                    + "default; that decision is yours, and for access it is "
                    + "always the refusal.")
                .explain(
                    "The default refuses: unknown means no.")
                .xp(20))
            .mistakes(
                new String[]{"A generous default",
                    "Any default that grants something grants it to every value "
                    + "nobody thought of."},
                new String[]{"A silent default",
                    "Refusing without saying what arrived hides the new role "
                    + "from the people who need to know."},
                new String[]{"No default in an old-style switch",
                    "Nothing runs, and the code after carries on as if a "
                    + "decision was made."})
            .cyber(
                "'Deny by default' is one of the oldest principles in security "
                + "design, and firewalls are its classic example: a list of "
                + "allowed traffic, and a final rule that drops everything "
                + "else. A firewall whose last rule is 'allow' is not a "
                + "firewall.\n"
                + "\n"
                + "The same principle applies inside every program you write. "
                + "Wherever code chooses what someone may do, the path for the "
                + "unexpected - the else, the default, the case where a lookup "
                + "found nothing - should lead to refusal, and ideally to a log "
                + "entry that someone reads.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String role = \"GUEST\".toLowerCase();",
                    "String access = switch (role) {",
                    "    case \"guest\" -> \"READ\";",
                    "    default -> \"DENY\";",
                    "};",
                    "System.out.println(access);")
                .accept("READ")
                .hints("What is role after toLowerCase?",
                       "Does it match the case?")
                .explain(
                    "READ. Normalising turned GUEST into guest, which matches. "
                    + "Deny by default works best with normalised input, so "
                    + "legitimate values are not refused just for their "
                    + "capitals.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "What does 'failing closed' mean for an access decision?")
                .choices("Anything unexpected is allowed",
                         "Anything unexpected is refused",
                         "The program crashes on unexpected input",
                         "Unexpected input is ignored")
                .accept("2", "b")
                .hints("Think of a door during a power cut.",
                       "Closed means shut.")
                .explain(
                    "Anything unexpected is refused. Like a door that stays "
                    + "locked when the power fails.")
                .xp(10))
            .recap(
                "default handles every value nobody listed - typos, new data, "
                + "attacks.\n"
                + "\n"
                + "For anything that grants access, default refuses: deny by "
                + "default, fail closed.\n"
                + "\n"
                + "Refuse, report what arrived, never copy a real case.")
            .next("Next: turning a condition inside out without breaking "
                + "it."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(26), "Inside Out", 4)
            .brief(
                "The allow rule for the VPN is simple: MFA passed AND account "
                + "not locked. The audit team wants the opposite - a rule that "
                + "flags every DENIED attempt - and the first draft, "
                + "!mfaOk && locked, missed most of them.\n\n"
                + "Flipping a condition has rules of its own.")
            .willLearn("De Morgan's laws", "Negating && and ||", "Allow rules and deny rules")
            .whyUseful(
                "Every allow rule has a deny rule, every 'valid' test an "
                + "'invalid' one. Flipping a compound condition correctly - "
                + "without leaving a gap - is a skill you use constantly.")
            .concept("De Morgan's laws",
                "To flip a condition built with && or ||, flip each part AND "
                + "swap the operator:\n"
                + "\n"
                + "    !(a && b)    is the same as    !a || !b\n"
                + "    !(a || b)    is the same as    !a && !b\n"
                + "\n"
                + "In words: 'not both' means 'at least one is missing'. 'Not "
                + "either' means 'neither'.\n"
                + "\n"
                + "These are DE MORGAN'S LAWS, and they hold for every "
                + "combination of true and false.\n"
                + "\n"
                + "The allow rule:\n"
                + "\n"
                + "    mfaOk && !locked\n"
                + "\n"
                + "Its exact opposite, the deny rule - flip each part, swap && "
                + "for ||:\n"
                + "\n"
                + "    !mfaOk || locked\n"
                + "\n"
                + "Denied if MFA failed, OR if the account is locked. Either "
                + "alone is enough.\n"
                + "\n"
                + "The common mistake is flipping the parts but keeping the "
                + "operator: !mfaOk && locked. That only catches attempts "
                + "where BOTH went wrong, and misses everyone who failed MFA on "
                + "an unlocked account.\n"
                + "\n"
                + "When in doubt, !( ... ) around the whole allow rule is "
                + "always correct, if less readable.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        boolean mfaOk = false;",
                "        boolean locked = false;",
                "        System.out.println(\"allowed: \" + (mfaOk && !locked));",
                "        System.out.println(\"denied:  \" + (!mfaOk || locked));",
                "        System.out.println(\"wrong:   \" + (!mfaOk && locked));",
                "    }",
                "}")
            .exampleOutput("allowed: false", "denied:  true", "wrong:   false")
            .lineByLine(
                new String[]{"mfaOk && !locked",
                    "MFA failed, so not allowed: false."},
                new String[]{"!mfaOk || locked",
                    "The correct flip. MFA failed is enough: true."},
                new String[]{"!mfaOk && locked",
                    "The wrong flip. It wants the account locked as well, so it "
                    + "says false - calling a denied attempt fine."},
                new String[]{"allowed and denied",
                    "The right pair always disagree. The wrong 'deny' agrees "
                    + "with 'allowed' here - the gap the audit found."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(!(true && false));")
                .accept("true")
                .hints("Work out the brackets first.",
                       "true && false is false. Then flip it.")
                .explain(
                    "true. The same as !true || !false, which is false || true.")
                .xp(10))
            .practice(new Task(Task.CHOICE,
                    "Which is the same as  !(locked || expired) ?")
                .choices("!locked || !expired", "!locked && !expired",
                         "locked && expired", "!locked || expired")
                .accept("2", "b")
                .hints("Flip each part.", "And swap || for &&.")
                .explain(
                    "!locked && !expired: neither locked nor expired. Flip each "
                    + "part and swap the operator.")
                .xp(20))
            .objective(
                "Write the deny rule that flags every refused VPN attempt.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        boolean mfaOk = false;",
                "        boolean locked = false;",
                "        // allow rule: mfaOk && !locked",
                "        // write the if line for its exact opposite",
                "            System.out.println(\"FLAG: denied attempt\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line that is true exactly when the allow rule is "
                + "false. Use De Morgan: flip each part and swap the operator. "
                + "Keep mfaOk first.")
            .mainTask(new Task(Task.WRITE,
                    "Write the deny rule's if line.")
                .accept("if (!mfaOk || locked) {", "if(!mfaOk || locked) {",
                        "if (!mfaOk || locked){", "if (!(mfaOk && !locked)) {")
                .hints(
                    "Flip mfaOk to !mfaOk, and !locked to locked.",
                    "Then swap && for ||.",
                    "if (!mfaOk || locked) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        boolean mfaOk = false;",
                    "        boolean locked = false;",
                    "        if (!mfaOk || locked) {",
                    "            System.out.println(\"FLAG: denied attempt\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "The allow rule needs both parts; so it fails when EITHER "
                    + "part fails. That 'either' is the ||. MFA failed here, so "
                    + "the attempt is flagged even though the account is not "
                    + "locked.\n"
                    + "\n"
                    + "The draft !mfaOk && locked only flagged attempts that "
                    + "failed MFA on an already-locked account - a tiny corner "
                    + "of what was actually being refused. An audit rule with a "
                    + "gap like that produces a quiet report and a false sense "
                    + "that nothing is happening.")
                .explain(
                    "Flip each part, swap && for ||.")
                .xp(30))
            .mistakes(
                new String[]{"Flipping parts but not the operator",
                    "!(a && b) is NOT !a && !b. Swap to ||."},
                new String[]{"Flipping only one part",
                    "Every part flips, including the ones already negated: !x "
                    + "becomes x."},
                new String[]{"Not checking the pair",
                    "The allow and deny rules must disagree for every "
                    + "combination. Test all four."})
            .cyber(
                "Security configurations are full of paired rules: allow and "
                + "deny, alert and suppress, valid and invalid. When one is "
                + "written as the opposite of the other and the flip is wrong, "
                + "a gap opens between them - a set of cases that is neither "
                + "allowed nor denied, neither alerted nor suppressed.\n"
                + "\n"
                + "Those gaps are where incidents hide. De Morgan's laws are "
                + "the tool for closing them, and checking all four "
                + "true/false combinations of two conditions takes a minute "
                + "and settles it for certain.")
            .check(new Task(Task.PREDICT,
                    "For every a and b, this prints the same thing. What?")
                .code(
                    "boolean a = true;",
                    "boolean b = false;",
                    "System.out.println(!(a || b) == (!a && !b));")
                .accept("true")
                .hints("Work out each side.",
                       "De Morgan says the two sides are always equal.")
                .explain(
                    "true. !(true || false) is false; !true && !false is false. "
                    + "Equal, as De Morgan's law promises for any values.")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "The allow rule is  valid && !expired . Which is its deny "
                    + "rule?")
                .choices("!valid && expired", "!valid || expired",
                         "valid || !expired", "!valid || !expired")
                .accept("2", "b")
                .hints("Flip both parts.", "Swap && for ||.")
                .explain(
                    "!valid || expired. Either problem on its own is enough to "
                    + "deny.")
                .xp(15))
            .recap(
                "    !(a && b)   =   !a || !b\n"
                + "    !(a || b)   =   !a && !b\n"
                + "\n"
                + "Flip every part AND swap the operator.\n"
                + "\n"
                + "An allow rule and its deny rule must disagree for every "
                + "combination - or a gap opens between them.")
            .next("Next: a whole access rule, with brackets where they "
                + "matter."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(27), "A Full Access Rule", 4)
            .brief(
                "The written policy for the finance share: the account must not "
                + "be locked, MFA must have passed, and the user must be an "
                + "admin OR it must be office hours.\n\n"
                + "Four conditions, three operators, and one pair of brackets "
                + "that decides whether a locked account can get in.")
            .willLearn("Logical operators", "Grouping with brackets", "Named conditions")
            .whyUseful(
                "Real policies combine several conditions. Writing them so "
                + "they are both correct and readable - and knowing exactly "
                + "where brackets are needed - is what access control code is "
                + "made of.")
            .concept("Building a compound rule",
                "Write a rule in two steps.\n"
                + "\n"
                + "STEP 1 - name each condition in a boolean:\n"
                + "\n"
                + "    boolean isAdmin = role.equals(\"admin\");\n"
                + "    boolean officeHours = hour >= 8 && hour < 18;\n"
                + "\n"
                + "Each name says what it means, so the rule can read like the "
                + "policy.\n"
                + "\n"
                + "STEP 2 - combine them, bracketing every || that sits among "
                + "&&s:\n"
                + "\n"
                + "    if (!locked && mfa && (isAdmin || officeHours)) {\n"
                + "\n"
                + "That reads as the policy: not locked, and MFA, and (admin or "
                + "office hours).\n"
                + "\n"
                + "WHY THE BRACKETS MATTER. && binds tighter than ||. Without "
                + "them,\n"
                + "\n"
                + "    !locked && mfa && isAdmin || officeHours\n"
                + "\n"
                + "is read as\n"
                + "\n"
                + "    (!locked && mfa && isAdmin) || officeHours\n"
                + "\n"
                + "- and then ANYONE gets in during office hours: locked or "
                + "not, MFA or not. The brackets are the difference between the "
                + "policy and a hole.\n"
                + "\n"
                + "Test a compound rule with the cases that should FAIL, one "
                + "condition at a time: locked but otherwise fine, no MFA but "
                + "otherwise fine. Each must be refused.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String role = \"analyst\";",
                "        int hour = 22;",
                "        boolean mfa = true;",
                "        boolean locked = false;",
                "        boolean isAdmin = role.equals(\"admin\");",
                "        boolean officeHours = hour >= 8 && hour < 18;",
                "        if (!locked && mfa && (isAdmin || officeHours)) {",
                "            System.out.println(\"GRANTED\");",
                "        } else {",
                "            System.out.println(\"DENIED\");",
                "        }",
                "    }",
                "}")
            .exampleOutput("DENIED")
            .lineByLine(
                new String[]{"isAdmin and officeHours",
                    "An analyst at 22:00: both false."},
                new String[]{"!locked && mfa",
                    "Both true so far."},
                new String[]{"(isAdmin || officeHours)",
                    "false || false is false, so the whole rule is false."},
                new String[]{"DENIED",
                    "Right: an analyst outside office hours does not get in, even "
                    + "with MFA."})
            .predict(new Task(Task.PREDICT,
                    "Same rule, office hours this time. What does it print?")
                .code(
                    "boolean locked = false;",
                    "boolean mfa = true;",
                    "boolean isAdmin = false;",
                    "boolean officeHours = true;",
                    "System.out.println(!locked && mfa && (isAdmin || officeHours));")
                .accept("true")
                .hints("Work out the brackets first.",
                       "Then the && chain.")
                .explain(
                    "true. Not locked, MFA passed, and office hours makes the "
                    + "bracket true.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "The same rule WITHOUT the brackets, for a locked account. "
                    + "What does it print?")
                .code(
                    "boolean locked = true;",
                    "boolean mfa = true;",
                    "boolean isAdmin = false;",
                    "boolean officeHours = true;",
                    "System.out.println(!locked && mfa && isAdmin || officeHours);")
                .accept("true")
                .hints(
                    "&& binds tighter than ||. Group the && part first.",
                    "(!locked && mfa && isAdmin) is false. Then || officeHours.")
                .explain(
                    "true - a LOCKED account is let in. Without brackets the "
                    + "rule means (not locked and MFA and admin) OR office hours, "
                    + "so office hours alone is enough. One missing pair of "
                    + "brackets, and the lock does nothing between nine and "
                    + "six.")
                .xp(30))
            .objective(
                "Write the finance share's access rule.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        boolean locked = true;",
                "        boolean mfa = true;",
                "        boolean isAdmin = false;",
                "        boolean officeHours = true;",
                "        // the rule: not locked, MFA, and (admin or office hours)",
                "            System.out.println(\"GRANTED\");",
                "        } else {",
                "            System.out.println(\"DENIED\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line for the policy, in the order it is written: "
                + "not locked, then MFA, then admin or office hours. Put the "
                + "brackets where they are needed.")
            .mainTask(new Task(Task.WRITE,
                    "Write the full access rule's if line.")
                .accept("if (!locked && mfa && (isAdmin || officeHours)) {",
                        "if(!locked && mfa && (isAdmin || officeHours)) {",
                        "if (!locked && mfa && (isAdmin || officeHours)){",
                        "if (!locked && mfa && (officeHours || isAdmin)) {")
                .hints(
                    "Three parts joined with &&.",
                    "The third part is itself an ||, so it needs brackets.",
                    "if (!locked && mfa && (isAdmin || officeHours)) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        boolean locked = true;",
                    "        boolean mfa = true;",
                    "        boolean isAdmin = false;",
                    "        boolean officeHours = true;",
                    "        if (!locked && mfa && (isAdmin || officeHours)) {",
                    "            System.out.println(\"GRANTED\");",
                    "        } else {",
                    "            System.out.println(\"DENIED\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "The account is locked, so !locked is false and the whole "
                    + "&& chain is false: DENIED, even in office hours.\n"
                    + "\n"
                    + "The brackets keep 'admin or office hours' as ONE of the "
                    + "three requirements, as the policy says. The starter sets "
                    + "up exactly the case that exposes a missing bracket - "
                    + "locked, during office hours - which is how to test a rule: "
                    + "try the case that should fail.")
                .explain(
                    "Bracket the || that sits among &&s.")
                .xp(35))
            .mistakes(
                new String[]{"No brackets around the ||",
                    "&& binds tighter, so the || swallows the whole rule. A "
                    + "locked account gets in."},
                new String[]{"Testing only the happy path",
                    "A rule that grants the right people can still grant the "
                    + "wrong ones. Test each failing case."},
                new String[]{"One long unnamed condition",
                    "Name the parts: isAdmin, officeHours. The rule then reads "
                    + "like the policy."})
            .cyber(
                "This is policy as code, and it is where the real world's "
                + "access control lives: file shares, VPN gateways, cloud "
                + "permissions, admin panels. The written policy goes to "
                + "auditors; the code is what actually runs.\n"
                + "\n"
                + "Precedence mistakes like the one in this mission turn up in "
                + "real authorisation code, and they are nasty because the "
                + "people who SHOULD get in all still do - nobody complains. "
                + "The only way to find them is to test the refusals "
                + "deliberately, one condition at a time. Security testers "
                + "call it testing the negative cases, and it catches more "
                + "access-control bugs than anything else.")
            .check(new Task(Task.CHOICE,
                    "In  a && b || c , which is worked out first?")
                .choices("b || c", "a && b", "They run left to right equally",
                         "c")
                .accept("2", "b")
                .hints("&& binds tighter than ||.",
                       "Like * before +.")
                .explain(
                    "a && b. The expression is (a && b) || c - so c on its own "
                    + "can make it true.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "No MFA, admin, office hours, not locked. What does this "
                    + "print?")
                .code(
                    "boolean locked = false;",
                    "boolean mfa = false;",
                    "boolean isAdmin = true;",
                    "boolean officeHours = true;",
                    "System.out.println(!locked && mfa && (isAdmin || officeHours));")
                .accept("false")
                .hints("Every && part must be true.", "Is mfa true?")
                .explain(
                    "false. MFA failed, and MFA is required of everyone - admins "
                    + "included. A negative case the rule correctly refuses.")
                .xp(15))
            .recap(
                "Name the conditions, then combine them.\n"
                + "\n"
                + "    !locked && mfa && (isAdmin || officeHours)\n"
                + "\n"
                + "&& binds tighter than ||: bracket every || among &&s.\n"
                + "\n"
                + "Test the cases that should fail, one condition at a time.")
            .next("Next: a whole lockout program, from input to decision."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(28), "The Lockout Decision", 5)
            .brief(
                "Time to put a whole decision together. The lockout service "
                + "reads a username and a failure count, checks the count is a "
                + "real number, and decides: LOCK, WARN or OK. Admin accounts "
                + "are worth more to an attacker, so they lock sooner.")
            .willLearn("if / else if / else", "Combining validation and decisions",
                       "A threshold chosen by ?:")
            .whyUseful(
                "Real programs are not one if - they are input, validation, a "
                + "choice of rule, and a decision, one after the other. This "
                + "mission is that whole shape in one small program.")
            .concept("Input to decision",
                "The program runs in four stages:\n"
                + "\n"
                + "    1. READ       username and count, cleaned up\n"
                + "    2. VALIDATE   is the count really a number?\n"
                + "    3. CHOOSE     which threshold applies to this user?\n"
                + "    4. DECIDE     LOCK, WARN or OK\n"
                + "\n"
                + "Stage 2 guards everything after it: parseInt only runs inside "
                + "the branch where the count passed matches. Bad input gets "
                + "one clear message and nothing else happens.\n"
                + "\n"
                + "Stage 3 picks a number, which is what ?: is for:\n"
                + "\n"
                + "    int limit = user.equals(\"admin\") ? 3 : 5;\n"
                + "\n"
                + "Stage 4 is an else-if chain on that limit, highest threshold "
                + "first:\n"
                + "\n"
                + "    if (failures >= limit)           LOCK\n"
                + "    else if (failures >= limit - 2)  WARN\n"
                + "    else                             OK\n"
                + "\n"
                + "Because the decision uses limit rather than 3 or 5, the same "
                + "three lines serve both kinds of account. Changing the admin "
                + "policy later means changing one number in one place.")
            .example(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Username: \");",
                "        String user = input.nextLine().trim().toLowerCase();",
                "        System.out.print(\"Failed logins: \");",
                "        String text = input.nextLine().trim();",
                "        if (!text.matches(\"[0-9]+\") || text.length() > 6) {",
                "            System.out.println(\"INVALID COUNT\");",
                "        } else {",
                "            int failures = Integer.parseInt(text);",
                "            int limit = user.equals(\"admin\") ? 3 : 5;",
                "            if (failures >= limit) {",
                "                System.out.println(\"LOCK \" + user);",
                "            } else if (failures >= limit - 2) {",
                "                System.out.println(\"WARN \" + user);",
                "            } else {",
                "                System.out.println(\"OK \" + user);",
                "            }",
                "        }",
                "    }",
                "}")
            .exampleInput("Admin", "3")
            .exampleOutput("Username: Admin", "Failed logins: 3", "LOCK admin")
            .lineByLine(
                new String[]{"trim().toLowerCase()",
                    "Admin becomes admin, so the admin rule cannot be dodged with "
                    + "capitals."},
                new String[]{"!text.matches(...) || text.length() > 6",
                    "The deny side of validation - De Morgan from mission 26. "
                    + "Anything not a short run of digits is refused."},
                new String[]{"user.equals(\"admin\") ? 3 : 5",
                    "Chooses the threshold. Admin locks at 3."},
                new String[]{"failures >= limit",
                    "3 >= 3: LOCK. The same line would give a normal user WARN."})
            .predict(new Task(Task.PREDICT,
                    "A normal user types 3. The program reads the name and count "
                    + "without prompts. What does it print?")
                .code(
                    "Scanner in = new Scanner(System.in);",
                    "String user = in.nextLine().trim().toLowerCase();",
                    "int failures = Integer.parseInt(in.nextLine().trim());",
                    "int limit = user.equals(\"admin\") ? 3 : 5;",
                    "if (failures >= limit) {",
                    "    System.out.println(\"LOCK \" + user);",
                    "} else if (failures >= limit - 2) {",
                    "    System.out.println(\"WARN \" + user);",
                    "} else {",
                    "    System.out.println(\"OK \" + user);",
                    "}")
                .input("jsmith", "3")
                .accept("WARN jsmith")
                .hints("jsmith is not admin, so limit is 5.",
                       "3 is below 5, but is it at least 5 - 2?")
                .explain(
                    "WARN jsmith. The limit is 5; 3 is not >= 5, but it is >= 3, "
                    + "so the WARN branch runs. Typed by an admin, the same 3 "
                    + "would lock.")
                .xp(20))
            .practice(new Task(Task.PREDICT,
                    "The count typed is 'five'. What does this print?")
                .code(
                    "String text = \"five\";",
                    "if (!text.matches(\"[0-9]+\") || text.length() > 6) {",
                    "    System.out.println(\"INVALID COUNT\");",
                    "} else {",
                    "    System.out.println(\"COUNT \" + Integer.parseInt(text));",
                    "}")
                .accept("INVALID COUNT")
                .hints("Does five match [0-9]+?",
                       "One true side of || is enough.")
                .explain(
                    "INVALID COUNT. five is not digits, so the first half is "
                    + "true and parseInt, which would have crashed, is never "
                    + "reached.")
                .xp(20))
            .objective(
                "Choose the lockout threshold for the account.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String user = \"admin\";",
                "        int failures = 4;",
                "        // declare limit here: 3 for admin, 5 for everyone else",
                "        if (failures >= limit) {",
                "            System.out.println(\"LOCK \" + user);",
                "        } else {",
                "            System.out.println(\"OK \" + user);",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Declare an int called limit, using the conditional operator: "
                + "3 when user equals admin, otherwise 5.")
            .mainTask(new Task(Task.WRITE,
                    "Declare limit with ?: - 3 for admin, else 5.")
                .accept("int limit = user.equals(\"admin\") ? 3 : 5;",
                        "int limit = user.equals(\"admin\") ? 3 : 5",
                        "int limit = (user.equals(\"admin\") ? 3 : 5);",
                        "int limit = \"admin\".equals(user) ? 3 : 5;")
                .hints(
                    "Compare text with equals.",
                    "condition ? valueIfTrue : valueIfFalse",
                    "int limit = user.equals(\"admin\") ? 3 : 5;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String user = \"admin\";",
                    "        int failures = 4;",
                    "        int limit = user.equals(\"admin\") ? 3 : 5;",
                    "        if (failures >= limit) {",
                    "            System.out.println(\"LOCK \" + user);",
                    "        } else {",
                    "            System.out.println(\"OK \" + user);",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "user equals admin, so limit is 3, and 4 failures locks the "
                    + "account. For any other user the same line gives 5 and "
                    + "4 failures is fine.\n"
                    + "\n"
                    + "Two missions meet in one line: equals, because this is "
                    + "text, and ?:, because the job is choosing a value. The "
                    + "decision below never mentions admin at all - it just uses "
                    + "limit - which keeps each piece of the policy in exactly "
                    + "one place.")
                .explain(
                    "equals for the text, ?: to choose the number.")
                .xp(30))
            .mistakes(
                new String[]{"user == \"admin\"",
                    "Text is compared with equals. With typed input, == would "
                    + "give every admin the normal limit."},
                new String[]{"Parsing before validating",
                    "parseInt on 'five' crashes. Validate, then convert inside "
                    + "the else."},
                new String[]{"Hard-coding 3 and 5 in the decision",
                    "Choose the limit once; let the decision use the variable."})
            .cyber(
                "Tiered thresholds are standard practice: privileged accounts "
                + "get stricter lockout, faster alerting and closer monitoring, "
                + "because compromising one is worth so much more to an "
                + "attacker.\n"
                + "\n"
                + "The normalising at the top of the program matters just as "
                + "much. If the username were not lower-cased, 'Admin' would "
                + "get the relaxed limit of 5 - an attacker trying capital "
                + "variations of the admin account would quietly get two extra "
                + "guesses per variation.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String user = \"ADMIN\".toLowerCase();",
                    "int failures = 2;",
                    "int limit = user.equals(\"admin\") ? 3 : 5;",
                    "if (failures >= limit) {",
                    "    System.out.println(\"LOCK\");",
                    "} else if (failures >= limit - 2) {",
                    "    System.out.println(\"WARN\");",
                    "} else {",
                    "    System.out.println(\"OK\");",
                    "}")
                .accept("WARN")
                .hints("The limit is 3.", "2 is below 3 but at least 1.")
                .explain(
                    "WARN. After lower-casing, user is admin, so limit is 3. "
                    + "2 >= 3 is false; 2 >= 1 is true.")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "Why does parseInt sit inside the else of the validation "
                    + "check?")
                .choices("It runs faster there",
                         "It only runs once the text is known to be safe to convert",
                         "Java requires it",
                         "So it runs twice")
                .accept("2", "b")
                .hints("What happens to parseInt on bad text?",
                       "Check, then use.")
                .explain(
                    "So it only ever sees text that passed validation. Outside "
                    + "the else, 'five' would crash the service.")
                .xp(15))
            .recap(
                "Read, validate, choose, decide.\n"
                + "\n"
                + "    validate first, convert inside the else\n"
                + "    int limit = user.equals(\"admin\") ? 3 : 5;\n"
                + "    decide with limit, highest threshold first\n"
                + "\n"
                + "Normalise names before any rule looks at them.")
            .next("Next: a validation pipeline with a clear message for "
                + "every kind of bad input."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(29), "A Validation Pipeline", 5)
            .brief(
                "The firewall rule editor accepts a port number. People type "
                + "all sorts: nothing, 'http', ' 8080 ', '99999'. Each deserves "
                + "a clear, specific refusal - and only a genuine port from 1 "
                + "to 65535 may reach the rule.\n\n"
                + "One check at a time, in the right order.")
            .willLearn("Validating input", "Ordered checks", "Specific error messages")
            .whyUseful(
                "Every form field, API parameter and config value needs this "
                + "shape. Get the order right and each check can rely on the "
                + "ones before it, and each refusal can say exactly what was "
                + "wrong.")
            .concept("A validation pipeline",
                "Validation is a series of gates. Each gate can reject, and "
                + "each one can rely on every gate before it having passed:\n"
                + "\n"
                + "    if (text.isEmpty())                    REQUIRED\n"
                + "    else if (!text.matches(\"[0-9]+\"))      NOT A NUMBER\n"
                + "    else if (text.length() > 5)            OUT OF RANGE\n"
                + "    else {\n"
                + "        int port = Integer.parseInt(text);\n"
                + "        if (port < 1 || port > 65535)      OUT OF RANGE\n"
                + "        else                               ACCEPTED\n"
                + "    }\n"
                + "\n"
                + "(Trim first, before any gate.)\n"
                + "\n"
                + "WHY THIS ORDER\n"
                + "\n"
                + "    empty first    the clearest message for no input\n"
                + "    digits next    parseInt would crash otherwise\n"
                + "    length next    more than 5 digits cannot be a port,\n"
                + "                   and might overflow an int\n"
                + "    parse          only now is it safe\n"
                + "    range last     needs the number, so it must come\n"
                + "                   after parsing\n"
                + "\n"
                + "The else-if chain guarantees only one message, and that "
                + "parseInt only runs when every format gate has passed. Swap "
                + "two gates and either the messages get worse or the program "
                + "crashes.\n"
                + "\n"
                + "Note the range check uses || - OUT of range means below the "
                + "bottom OR above the top (mission 8).")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String text = \" 70000 \".trim();",
                "        if (text.isEmpty()) {",
                "            System.out.println(\"REQUIRED\");",
                "        } else if (!text.matches(\"[0-9]+\")) {",
                "            System.out.println(\"NOT A NUMBER\");",
                "        } else if (text.length() > 5) {",
                "            System.out.println(\"OUT OF RANGE\");",
                "        } else {",
                "            int port = Integer.parseInt(text);",
                "            if (port < 1 || port > 65535) {",
                "                System.out.println(\"OUT OF RANGE\");",
                "            } else {",
                "                System.out.println(\"ACCEPTED \" + port);",
                "            }",
                "        }",
                "    }",
                "}")
            .exampleOutput("OUT OF RANGE")
            .lineByLine(
                new String[]{"\" 70000 \".trim()",
                    "Cleaned before any gate: 70000."},
                new String[]{"The first three gates",
                    "Not empty, all digits, five characters. All pass."},
                new String[]{"Integer.parseInt(text)",
                    "Safe now: at most five digits, so it cannot fail."},
                new String[]{"port > 65535",
                    "70000 is above the top of the range. OUT OF RANGE."})
            .predict(new Task(Task.PREDICT,
                    "The same pipeline, with text = \"\". What does it print?")
                .code(
                    "String text = \"\";",
                    "if (text.isEmpty()) {",
                    "    System.out.println(\"REQUIRED\");",
                    "} else if (!text.matches(\"[0-9]+\")) {",
                    "    System.out.println(\"NOT A NUMBER\");",
                    "} else {",
                    "    System.out.println(\"FORMAT OK\");",
                    "}")
                .accept("REQUIRED")
                .hints("Which gate comes first?", "Is the text empty?")
                .explain(
                    "REQUIRED. The first gate catches it, so the others are "
                    + "never asked. Without that gate the answer would be NOT A "
                    + "NUMBER - true, but less helpful to someone who typed "
                    + "nothing.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "text = \"8o80\" - with a letter o. What does it print?")
                .code(
                    "String text = \"8o80\";",
                    "if (text.isEmpty()) {",
                    "    System.out.println(\"REQUIRED\");",
                    "} else if (!text.matches(\"[0-9]+\")) {",
                    "    System.out.println(\"NOT A NUMBER\");",
                    "} else if (text.length() > 5) {",
                    "    System.out.println(\"OUT OF RANGE\");",
                    "} else {",
                    "    System.out.println(\"PORT \" + Integer.parseInt(text));",
                    "}")
                .accept("NOT A NUMBER")
                .hints("Is o a digit?", "Look closely: letter o, not zero.")
                .explain(
                    "NOT A NUMBER. The letter o is not a digit - an easy typo to "
                    + "miss by eye, and exactly what the pattern exists to catch "
                    + "before parseInt crashes on it.")
                .xp(20))
            .objective(
                "Add the gate that refuses anything that is not digits.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String text = \"http\";",
                "        if (text.isEmpty()) {",
                "            System.out.println(\"REQUIRED\");",
                "        // write the NOT A NUMBER gate here",
                "            System.out.println(\"NOT A NUMBER\");",
                "        } else {",
                "            System.out.println(\"PORT \" + Integer.parseInt(text));",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the line that closes the REQUIRED block and opens a gate "
                + "for text that is not one or more digits.")
            .mainTask(new Task(Task.WRITE,
                    "Write the else-if line for the NOT A NUMBER gate.")
                .accept("} else if (!text.matches(\"[0-9]+\")) {",
                        "}else if (!text.matches(\"[0-9]+\")) {",
                        "} else if(!text.matches(\"[0-9]+\")) {",
                        "} else if (!text.matches(\"[0-9]+\")){")
                .hints(
                    "It continues the chain: } else if (...) {",
                    "The gate REJECTS, so its condition is the opposite of "
                    + "matching: put ! in front.",
                    "} else if (!text.matches(\"[0-9]+\")) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String text = \"http\";",
                    "        if (text.isEmpty()) {",
                    "            System.out.println(\"REQUIRED\");",
                    "        } else if (!text.matches(\"[0-9]+\")) {",
                    "            System.out.println(\"NOT A NUMBER\");",
                    "        } else {",
                    "            System.out.println(\"PORT \" + Integer.parseInt(text));",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "http is not empty, so the chain moves to the new gate. It "
                    + "does not match one-or-more digits, so !matches is true and "
                    + "NOT A NUMBER prints. parseInt, in the final else, is only "
                    + "reached by text that has passed every gate.\n"
                    + "\n"
                    + "Each gate is written as the condition for REJECTING, "
                    + "which is why it has a ! in front. The pipeline reads as a "
                    + "list of reasons to refuse, and whatever survives all of "
                    + "them is accepted - the allowlist idea, built as a "
                    + "chain.")
                .explain(
                    "The gate rejects what does NOT match: !text.matches(...).")
                .xp(30))
            .mistakes(
                new String[]{"Parsing before the format gates",
                    "parseInt on 'http' crashes. Parse only in the final else."},
                new String[]{"Checking the range as text",
                    "\"9\" > \"65535\" is not a thing. Compare numbers, after "
                    + "parsing."},
                new String[]{"One vague message for everything",
                    "Specific messages help honest users fix typos. (Keep them "
                    + "about the INPUT - never reveal internal details.)"})
            .cyber(
                "Validate early, validate strictly, and give each rejection one "
                + "clear reason. This pipeline is the standard shape for "
                + "handling any value that crosses a trust boundary: a web form, "
                + "an API parameter, a config file, a network message.\n"
                + "\n"
                + "One caution about the messages. 'Port must be 1 to 65535' "
                + "helps a legitimate user and gives an attacker nothing they "
                + "did not know. A message that echoes internal details - a "
                + "stack trace, a database error, a file path - helps only the "
                + "attacker. Tell people what was wrong with what they sent, "
                + "never how the system works inside.")
            .check(new Task(Task.CHOICE,
                    "Why must the range check come after parseInt?")
                .choices("It is faster",
                         "It needs the number, and the number only exists after parsing",
                         "Ranges cannot be checked on ints",
                         "It does not matter")
                .accept("2", "b")
                .hints("What does port < 1 need?", "An int.")
                .explain(
                    "It compares numbers, and there is no number until parseInt "
                    + "has run - which in turn is only safe once the format "
                    + "gates have passed.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int port = 0;",
                    "if (port < 1 || port > 65535) {",
                    "    System.out.println(\"OUT OF RANGE\");",
                    "} else {",
                    "    System.out.println(\"OK\");",
                    "}")
                .accept("OUT OF RANGE")
                .hints("Is 0 below 1?", "One side of || is enough.")
                .explain(
                    "OUT OF RANGE. Port 0 is reserved and not valid here; the "
                    + "boundary test from mission 5, at the bottom edge.")
                .xp(15))
            .recap(
                "Gates in order, each able to rely on the ones before:\n"
                + "\n"
                + "    trim\n"
                + "    empty?          REQUIRED\n"
                + "    not digits?     NOT A NUMBER\n"
                + "    too long?       OUT OF RANGE\n"
                + "    parse           now safe\n"
                + "    out of range?   OUT OF RANGE\n"
                + "    otherwise       ACCEPTED")
            .next("Next: the CONDITIONAL checkpoint."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(30), "CONDITIONAL COMPLETE", 5)
            .brief(
                "Thirty missions ago, your programs ran every line every time. "
                + "Now they compare, decide, validate, refuse and route - the "
                + "core of every security control there is.\n\n"
                + "This checkpoint mixes the whole campaign. No new Java.")
            .willLearn("Recall of the whole campaign")
            .whyUseful(
                "Decisions go wrong where ideas meet: == on text inside a "
                + "compound rule, a boundary inside a switch, a check that runs "
                + "after the use it was meant to guard. This is the practice "
                + "for that.")
            .concept("Everything, together",
                "The campaign in one page.\n"
                + "\n"
                + "COMPARING\n"
                + "    == != < > <= >=  on numbers, chars, booleans\n"
                + "    equals, equalsIgnoreCase  on Strings - never ==\n"
                + "    Math.abs(a - b) < tolerance  on doubles\n"
                + "\n"
                + "DECIDING\n"
                + "    if / else - put the safe outcome in the else\n"
                + "    else if chains - highest threshold first\n"
                + "    switch - exact values, break or ->, default denies\n"
                + "    ? : - choosing values, never actions\n"
                + "\n"
                + "COMBINING\n"
                + "    && both   || either   ! not\n"
                + "    && binds tighter - bracket || among &&s\n"
                + "    guards on the left: check, then use\n"
                + "    De Morgan: flip each part, swap the operator\n"
                + "\n"
                + "VALIDATING\n"
                + "    indexOf found?     >= 0\n"
                + "    digits only?       matches(\"[0-9]+\")\n"
                + "    anything there?    isBlank\n"
                + "    in range?          after parsing\n"
                + "\n"
                + "HABITS\n"
                + "    translate policy words to operators carefully\n"
                + "    test the boundary and the cases that must fail\n"
                + "    deny by default")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String role = \" Analyst \".trim().toLowerCase();",
                "        int failures = 5;",
                "        boolean mfa = true;",
                "        final int LIMIT = 5;",
                "        String decision = switch (role) {",
                "            case \"admin\", \"analyst\" ->",
                "                (failures < LIMIT && mfa) ? \"ALLOW\" : \"DENY\";",
                "            default -> \"DENY\";",
                "        };",
                "        System.out.println(role + \": \" + decision);",
                "    }",
                "}")
            .exampleOutput("analyst: DENY")
            .lineByLine(
                new String[]{"trim().toLowerCase()",
                    "Mission 23: normalise before switching."},
                new String[]{"case \"admin\", \"analyst\" ->",
                    "Mission 24: two values, one arrow case, no fall-through."},
                new String[]{"failures < LIMIT",
                    "Mission 4: at 5 failures the account is at the limit - not "
                    + "below it - so DENY."},
                new String[]{"default -> \"DENY\"",
                    "Mission 25: every role not listed is refused."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String typed = \"GUEST\".toLowerCase();",
                    "System.out.println(typed == \"guest\");",
                    "System.out.println(typed.equals(\"guest\"));")
                .accept("false true", "false, true", "false and true")
                .hints("typed was built at run time.",
                       "== compares locations; equals compares text.")
                .explain(
                    "    false\n"
                    + "    true\n"
                    + "\n"
                    + "Mission 11, still the most important rule in the campaign.")
                .xp(20))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "int failures = 6;",
                    "if (failures > 5) {",
                    "    String action = \"LOCK\";",
                    "} else {",
                    "    String action = \"OK\";",
                    "}",
                    "System.out.println(action);")
                .accept("7", "line 7")
                .hints("Where does each action variable live?",
                       "Both blocks have ended by the println.")
                .explain(
                    "Line 7. Each action was declared inside its own block and "
                    + "vanished at that block's closing brace. Declare it once, "
                    + "before the if (mission 19).")
                .xp(25))
            .objective(
                "Grant the admin console only to an unlocked admin.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String role = \"ADMIN\".toLowerCase();",
                "        boolean locked = false;",
                "        // write the if line: not locked, and role is admin",
                "            System.out.println(\"CONSOLE OPEN\");",
                "        } else {",
                "            System.out.println(\"ACCESS DENIED\");",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the if line: true only when the account is not locked "
                + "and role has the text admin. Lock check first.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line: not locked, and role equals admin.")
                .accept("if (!locked && role.equals(\"admin\")) {",
                        "if(!locked && role.equals(\"admin\")) {",
                        "if (!locked && role.equals(\"admin\")){",
                        "if (!locked && \"admin\".equals(role)) {")
                .hints(
                    "Two conditions, both required.",
                    "Text is compared with equals.",
                    "if (!locked && role.equals(\"admin\")) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String role = \"ADMIN\".toLowerCase();",
                    "        boolean locked = false;",
                    "        if (!locked && role.equals(\"admin\")) {",
                    "            System.out.println(\"CONSOLE OPEN\");",
                    "        } else {",
                    "            System.out.println(\"ACCESS DENIED\");",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "The account is not locked and role, built at run time by "
                    + "toLowerCase, has the text admin - which equals confirms "
                    + "and == would not. Both hold, so the console opens; "
                    + "anything else lands on ACCESS DENIED in the else.\n"
                    + "\n"
                    + "Four missions in one line: ! (9), && (7), equals (11), "
                    + "and the safe outcome in the else (3).")
                .explain(
                    "!locked && role.equals(\"admin\"), with the refusal in the "
                    + "else.")
                .xp(40))
            .mistakes(
                new String[]{"== on text",
                    "The rule with no exceptions: Strings are compared with "
                    + "equals."},
                new String[]{"Missing brackets or breaks",
                    "&& binds before ||; a case without break falls through."},
                new String[]{"A generous default",
                    "Unknown means no: else and default refuse."})
            .cyber(
                "Every security control you will ever build or audit is, "
                + "underneath, what this campaign taught: firewall rules, "
                + "access checks, alert conditions, input filters, lockout "
                + "policies. The bugs are the ones you have now seen: an "
                + "operator one character wrong, text compared by location, a "
                + "bracket missing, a default that says yes, a check placed "
                + "after the use.\n"
                + "\n"
                + "What these programs still cannot do is repeat. A lockout "
                + "needs to count many attempts; a parser needs to read many "
                + "lines. Campaign 03 packages decisions into reusable methods; "
                + "Campaign 04 makes them repeat.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int level = 1;",
                    "switch (level) {",
                    "    case 1:",
                    "        System.out.println(\"READ\");",
                    "    case 2:",
                    "        System.out.println(\"WRITE\");",
                    "        break;",
                    "}")
                .accept("READ WRITE", "READ, WRITE", "READ and WRITE")
                .hints("Is there a break after READ?", "Fall-through.")
                .explain(
                    "    READ\n"
                    + "    WRITE\n"
                    + "\n"
                    + "No break after READ, so level 1 falls into WRITE - the "
                    + "privilege escalation from mission 22.")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "'Accounts lock once they reach 5 failures.' Which "
                    + "condition locks?")
                .choices("failures > 5", "failures >= 5", "failures == 5",
                         "failures < 5")
                .accept("2", "b")
                .hints("At exactly 5, is the account locked?",
                       "Reach includes the number itself.")
                .explain(
                    "failures >= 5. 'Reach' includes 5 itself (mission 4). == 5 "
                    + "would unlock again at 6.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Which is the same as  !(expired || revoked) ?")
                .choices("!expired || !revoked", "!expired && !revoked",
                         "expired && revoked", "!expired || revoked")
                .accept("2", "b")
                .hints("Flip each part.", "Swap || for &&.")
                .explain(
                    "!expired && !revoked - neither expired nor revoked (mission "
                    + "26).")
                .xp(15))
            .recap(
                "CAMPAIGN 02 - CONDITIONAL complete.\n"
                + "\n"
                + "Your programs compare, decide, validate and refuse - and you "
                + "know the ways each of those goes quietly wrong.\n"
                + "\n"
                + "Next they learn to be organised into reusable pieces: "
                + "methods.")
            .next("Next: CAMPAIGN 03 - METHODS."));
    }
}
