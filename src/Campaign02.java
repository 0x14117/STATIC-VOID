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
    }
}
