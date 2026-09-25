/**
 * CAMPAIGN 04 - LOOP//CONTROL
 * Programs that repeat. Thirty missions.
 *
 * Every program so far has done its work once. Campaign 03 could call a
 * method many times, but only by writing the call many times, or by
 * recursion. Loops are how real tools get through a log file, a port
 * range, or a thousand login attempts.
 *
 * Arrays and ArrayList are Campaign 05, so a mission here may use
 * everything from Campaigns 00 to 03, plus whatever this campaign has
 * already introduced - and nothing else.
 */
public class Campaign04 {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(1), "Again and Again", 3)
            .brief(
                "The lockout test needs to simulate five login attempts. Five "
                + "copies of the same lines would work - until someone asks "
                + "for five hundred. Programs repeat work with LOOPS, and the "
                + "simplest one is while.")
            .willLearn("while loop")
            .whyUseful(
                "Almost every security tool is a loop: read the next log line, "
                + "try the next port, check the next file. Without loops, a "
                + "program can only ever handle what you wrote out by hand.")
            .concept("while loop",
                "A while loop repeats a block for as long as a condition is "
                + "true:\n"
                + "\n"
                + "    int attempt = 1;\n"
                + "    while (attempt <= 3) {\n"
                + "        System.out.println(\"Attempt \" + attempt);\n"
                + "        attempt++;\n"
                + "    }\n"
                + "\n"
                + "    while (attempt <= 3)   the condition: keep going?\n"
                + "    { ... }                the BODY: what repeats\n"
                + "\n"
                + "Java checks the condition. If it is true, the body runs "
                + "once, and Java goes back and checks the condition AGAIN. "
                + "When it is false, the loop ends and the program carries on "
                + "after the closing brace.\n"
                + "\n"
                + "Each run of the body is called an ITERATION or a PASS. Here "
                + "attempt is 1, 2, then 3; after the third pass it becomes "
                + "4, the condition is false, and the loop is over.\n"
                + "\n"
                + "The condition is checked BEFORE every pass - including the "
                + "first. If it is false from the start, the body never runs "
                + "at all.\n"
                + "\n"
                + "It is just like an if, with one difference: an if runs its "
                + "block at most once; a while keeps coming back.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int attempt = 1;",
                "        while (attempt <= 3) {",
                "            System.out.println(\"Attempt \" + attempt + \": failed\");",
                "            attempt++;",
                "        }",
                "        System.out.println(\"Account locked\");",
                "    }",
                "}")
            .exampleOutput(
                "Attempt 1: failed",
                "Attempt 2: failed",
                "Attempt 3: failed",
                "Account locked")
            .lineByLine(
                new String[]{"int attempt = 1;",
                    "Set up before the loop starts."},
                new String[]{"while (attempt <= 3)",
                    "Checked before every pass. True for 1, 2 and 3."},
                new String[]{"attempt++;",
                    "Moves the loop forward. After the third pass, attempt is "
                    + "4."},
                new String[]{"Account locked",
                    "4 <= 3 is false, so the loop ends and this line runs."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int n = 1;",
                    "while (n < 4) {",
                    "    System.out.println(n);",
                    "    n++;",
                    "}",
                    "System.out.println(\"end\");")
                .accept("1 2 3 end")
                .hints("The body runs while n is less than 4.",
                       "When n becomes 4, the loop stops.")
                .explain(
                    "    1\n"
                    + "    2\n"
                    + "    3\n"
                    + "    end\n"
                    + "\n"
                    + "Three passes, for n = 1, 2 and 3. Then 4 < 4 is false.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "How many lines does this print?")
                .code(
                    "int n = 10;",
                    "while (n < 5) {",
                    "    System.out.println(\"inside\");",
                    "    n++;",
                    "}",
                    "System.out.println(\"after\");")
                .accept("1", "one")
                .hints("Is 10 < 5 before the first pass?",
                       "The condition is checked before the body ever runs.")
                .explain(
                    "1. 10 < 5 is false straight away, so the body never runs. "
                    + "Only after is printed.")
                .xp(15))
            .objective(
                "Scan ports 20 to 25 with one loop.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int port = 20;",
                "        // write the while line: keep going while port is 25 or less",
                "            System.out.println(\"Scanning port \" + port);",
                "            port++;",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the while line so the loop scans every port from 20 up "
                + "to and including 25.")
            .mainTask(new Task(Task.WRITE,
                    "Write the while line.")
                .accept("while (port <= 25) {", "while(port <= 25) {",
                        "while (port <= 25){", "while (port < 26) {")
                .hints(
                    "while, then the condition in brackets, then {.",
                    "25 must be included: <=.",
                    "while (port <= 25) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int port = 20;",
                    "        while (port <= 25) {",
                    "            System.out.println(\"Scanning port \" + port);",
                    "            port++;",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "The condition is true for 20, 21, 22, 23, 24 and 25, so "
                    + "the body runs six times, printing each port and then "
                    + "moving port on by one. When port reaches 26, the "
                    + "condition is false and the loop ends.\n"
                    + "\n"
                    + "Three lines now scan six ports - and changing 25 to "
                    + "1024 would scan a thousand with no extra code.")
                .explain(
                    "while (port <= 25) { - 25 is included.")
                .xp(20))
            .mistakes(
                new String[]{"A semicolon after the condition",
                    "while (x < 5); { ... } makes an empty loop that never "
                    + "ends. No semicolon before the brace."},
                new String[]{"Forgetting the update",
                    "Without port++, the condition never changes. Mission 4."},
                new String[]{"< where <= was meant",
                    "The last value is left out."})
            .cyber(
                "Loops are what make automation - and automated attacks - "
                + "possible. A password-guessing script is a loop that tries "
                + "the next password. A port scanner is a loop over port "
                + "numbers. A log monitor is a loop over lines. The same "
                + "three lines that scan six ports scan sixty thousand.\n"
                + "\n"
                + "That scale is the point on both sides. Defences like "
                + "lockouts and rate limits (missions 21 and 22) exist "
                + "because an attacker's loop never gets tired.")
            .check(new Task(Task.CHOICE,
                    "When is a while loop's condition checked?")
                .choices("Only once, at the start",
                         "Before every pass, including the first",
                         "After every pass",
                         "Only when the body changes a variable")
                .accept("2", "b")
                .hints("Could the body run zero times?",
                       "It is checked before each iteration.")
                .explain(
                    "Before every pass - so if it is false at the start, the "
                    + "body never runs.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int total = 0;",
                    "int i = 1;",
                    "while (i <= 3) {",
                    "    total = total + i;",
                    "    i++;",
                    "}",
                    "System.out.println(total);")
                .accept("6")
                .hints("total gains 1, then 2, then 3.",
                       "Only the final value is printed.")
                .explain(
                    "6. The println is after the loop, so it runs once, with "
                    + "total = 1 + 2 + 3.")
                .xp(15))
            .recap(
                "    while (condition) {\n"
                + "        body\n"
                + "    }\n"
                + "\n"
                + "Checked before every pass; the body repeats while it is "
                + "true. False at the start means zero passes. Something in "
                + "the body must eventually make it false.")
            .next("Next: the part of a loop that moves it forward."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(2), "Making Progress", 3)
            .brief(
                "When a login fails, the client waits before retrying - 1 "
                + "second, then 2, then 4, doubling each time so a struggling "
                + "server is not hammered. The loop does not count up by one; "
                + "its UPDATE does the doubling.")
            .willLearn("Loop update", "Loop condition")
            .whyUseful(
                "Every loop has a start, a test and an update. Seeing those "
                + "three parts in any loop - and checking they fit together - "
                + "is how you know it will end, and end in the right place.")
            .concept("Loop update",
                "Nearly every loop has three parts:\n"
                + "\n"
                + "    int delay = 1;            START   set up before\n"
                + "    while (delay <= 16) {     TEST    keep going?\n"
                + "        ...\n"
                + "        delay = delay * 2;    UPDATE  move on\n"
                + "    }\n"
                + "\n"
                + "The UPDATE is what eventually makes the test false. It "
                + "can be anything - add one, subtract, double, halve - as "
                + "long as it moves TOWARDS the end:\n"
                + "\n"
                + "    delay:  1, 2, 4, 8, 16, then 32 - stop\n"
                + "\n"
                + "Where the update sits in the body matters. Update before "
                + "printing and every value printed is one step further on:\n"
                + "\n"
                + "    while (n < 3) {           while (n < 3) {\n"
                + "        print(n);                 n++;\n"
                + "        n++;                      print(n);\n"
                + "    }                         }\n"
                + "    prints 0 1 2              prints 1 2 3\n"
                + "\n"
                + "(Starting from n = 0 in both.) To check a loop, ask three "
                + "questions: what is the first value used? what is the last? "
                + "does every pass move towards the end?")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int delay = 1;",
                "        while (delay <= 16) {",
                "            System.out.println(\"Retry after \" + delay + \"s\");",
                "            delay = delay * 2;",
                "        }",
                "        System.out.println(\"Giving up\");",
                "    }",
                "}")
            .exampleOutput(
                "Retry after 1s",
                "Retry after 2s",
                "Retry after 4s",
                "Retry after 8s",
                "Retry after 16s",
                "Giving up")
            .lineByLine(
                new String[]{"int delay = 1;",
                    "Start: the first value used."},
                new String[]{"while (delay <= 16)",
                    "Test: 16 passes, 32 does not."},
                new String[]{"delay = delay * 2;",
                    "Update: doubling, not adding. Still moves towards the "
                    + "end."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int n = 0;",
                    "while (n < 3) {",
                    "    n++;",
                    "    System.out.println(n);",
                    "}")
                .accept("1 2 3")
                .hints("The update comes BEFORE the println.",
                       "The first value printed is after one ++.")
                .explain(
                    "    1\n"
                    + "    2\n"
                    + "    3\n"
                    + "\n"
                    + "n is increased before each print. On the last pass, n "
                    + "goes from 2 to 3, prints 3, and 3 < 3 ends the loop.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "level starts at 5. Which update makes  while (level > 0) "
                    + " end?")
                .choices("level++;", "level--;", "level = level;",
                         "level = level * 2;")
                .accept("2", "b")
                .hints("The loop ends when level is 0 or less.",
                       "Which one moves level DOWN?")
                .explain(
                    "level--. The others keep level at 5 or push it up, so "
                    + "level > 0 stays true for ever.")
                .xp(10))
            .objective(
                "Make the back-off double each time.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int delay = 1;",
                "        while (delay <= 16) {",
                "            System.out.println(\"Retry after \" + delay + \"s\");",
                "            // the update: double the delay",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the update line that doubles delay. Without it, the "
                + "loop never ends.")
            .mainTask(new Task(Task.WRITE,
                    "Write the update line.")
                .accept("delay = delay * 2;", "delay *= 2;",
                        "delay = 2 * delay;", "delay=delay*2;")
                .hints(
                    "delay gets a new value based on its old one.",
                    "Multiply by 2.",
                    "delay = delay * 2;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int delay = 1;",
                    "        while (delay <= 16) {",
                    "            System.out.println(\"Retry after \" + delay + \"s\");",
                    "            delay = delay * 2;",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "Each pass doubles delay: 1, 2, 4, 8, 16. After printing "
                    + "16 it becomes 32, and 32 <= 16 is false, so the loop "
                    + "ends after five retries.\n"
                    + "\n"
                    + "The update is what connects the body to the test. "
                    + "Doubling reaches the limit in five steps instead of "
                    + "sixteen - which is exactly why back-off grows so "
                    + "quickly.")
                .explain(
                    "delay = delay * 2; - or delay *= 2;")
                .xp(20))
            .mistakes(
                new String[]{"An update that moves the wrong way",
                    "Counting up towards a lower limit never ends."},
                new String[]{"Updating in the wrong place",
                    "Before or after the print changes every value shown."},
                new String[]{"Starting at the wrong value",
                    "Check the first value used, not just the last."})
            .cyber(
                "Exponential back-off - waiting 1, 2, 4, 8 seconds - is a "
                + "real defence on both sides. Clients use it so a failing "
                + "server gets room to recover instead of a flood of "
                + "retries. Servers use the same idea against attackers: each "
                + "wrong password makes the next attempt wait twice as long, "
                + "so a guessing loop that could try thousands of passwords a "
                + "minute slows to a crawl.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int x = 100;",
                    "while (x > 10) {",
                    "    x = x / 2;",
                    "}",
                    "System.out.println(x);")
                .accept("6")
                .hints("100, 50, 25, 12, ...",
                       "Integer division: 25 / 2 is 12, 12 / 2 is 6.")
                .explain(
                    "6. x goes 100, 50, 25, 12, 6. 6 > 10 is false, so the "
                    + "loop stops and prints 6.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Which part of a loop eventually makes the condition "
                    + "false?")
                .choices("The start", "The test", "The update",
                         "The closing brace")
                .accept("3", "c")
                .hints("Something inside the body must change.",
                       "It moves the loop forward.")
                .explain(
                    "The update. Without it, the test would give the same "
                    + "answer for ever.")
                .xp(10))
            .recap(
                "Start, test, update. The update moves towards the end - by "
                + "adding, subtracting, doubling, anything that gets there. "
                + "Its position in the body changes the values used.\n"
                + "\n"
                + "Check any loop: first value? last value? always moving "
                + "on?")
            .next("Next: loops that count."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(3), "Counting As You Go", 3)
            .brief(
                "The audit wants to know how many of the ports from 1 to 1024 "
                + "are round hundreds - 100, 200, and so on - because those "
                + "are where the old test services live. A loop can check "
                + "every port and COUNT the ones that match.")
            .willLearn("Counters")
            .whyUseful(
                "Detection is mostly counting: failed logins, matching lines, "
                + "open ports. A counter inside a loop is how a program turns "
                + "'look at everything' into a number you can compare to a "
                + "threshold.")
            .concept("Counters",
                "A COUNTER is a variable that starts at 0 and goes up by one "
                + "each time something happens:\n"
                + "\n"
                + "    int count = 0;                  before the loop\n"
                + "    while (...) {\n"
                + "        if (something happened) {\n"
                + "            count++;                inside, when it does\n"
                + "        }\n"
                + "    }\n"
                + "    System.out.println(count);      after the loop\n"
                + "\n"
                + "Three places, and each matters:\n"
                + "\n"
                + "    BEFORE   declare it and set it to 0 - once\n"
                + "    INSIDE   add one when the thing happens\n"
                + "    AFTER    use the total once the loop is done\n"
                + "\n"
                + "Declare the counter INSIDE the loop and it is reset to 0 "
                + "on every pass - it can never get past 1.\n"
                + "\n"
                + "The loop variable itself can count in steps other than one: "
                + "i += 5 visits 0, 5, 10... and i-- counts down. A loop "
                + "variable says WHERE you are; a counter says HOW MANY "
                + "matched.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int count = 0;",
                "        int port = 1;",
                "        while (port <= 1024) {",
                "            if (port % 100 == 0) {",
                "                count++;",
                "            }",
                "            port++;",
                "        }",
                "        System.out.println(count + \" round-hundred ports\");",
                "    }",
                "}")
            .exampleOutput(
                "10 round-hundred ports")
            .lineByLine(
                new String[]{"int count = 0;",
                    "The counter, set once, before the loop."},
                new String[]{"if (port % 100 == 0)",
                    "True for 100, 200, ... 1000."},
                new String[]{"count++;",
                    "One more match."},
                new String[]{"System.out.println(...)",
                    "After the loop: 1024 ports checked, one line printed."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int i = 0;",
                    "while (i <= 20) {",
                    "    System.out.println(i);",
                    "    i += 5;",
                    "}")
                .accept("0 5 10 15 20")
                .hints("i goes up in steps of 5.",
                       "Is 20 <= 20?")
                .explain(
                    "0 5 10 15 20 (one per line). The step is 5, and 20 is "
                    + "still allowed by <=.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int i = 10;",
                    "int passes = 0;",
                    "while (i > 0) {",
                    "    i -= 3;",
                    "    passes++;",
                    "}",
                    "System.out.println(passes);")
                .accept("4")
                .hints("i goes 10, 7, 4, 1, ...",
                       "After the pass that makes it -2, the loop stops.")
                .explain(
                    "4. The passes take i to 7, 4, 1 and -2. Then -2 > 0 is "
                    + "false.")
                .xp(15))
            .objective(
                "Count the even-numbered hosts in a range.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int evens = 0;",
                "        int n = 1;",
                "        while (n <= 10) {",
                "            if (n % 2 == 0) {",
                "                // count this one",
                "            }",
                "            n++;",
                "        }",
                "        System.out.println(\"Even hosts: \" + evens);",
                "    }",
                "}")
            .yourTask(
                "Write the line that adds one to the counter evens.")
            .mainTask(new Task(Task.WRITE,
                    "Write the counting line.")
                .accept("evens++;", "evens = evens + 1;", "evens += 1;",
                        "++evens;")
                .hints(
                    "Add one to evens.",
                    "The shortest way is ++.",
                    "evens++;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int evens = 0;",
                    "        int n = 1;",
                    "        while (n <= 10) {",
                    "            if (n % 2 == 0) {",
                    "                evens++;",
                    "            }",
                    "            n++;",
                    "        }",
                    "        System.out.println(\"Even hosts: \" + evens);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The if lets the counter move only for even n: 2, 4, 6, 8 "
                    + "and 10. The program prints Even hosts: 5.\n"
                    + "\n"
                    + "Two variables change here, for different reasons. n "
                    + "moves every pass - it is WHERE the loop is. evens moves "
                    + "only on a match - it is HOW MANY were found.")
                .explain(
                    "evens++; inside the if, so only matches count.")
                .xp(20))
            .mistakes(
                new String[]{"Declaring the counter inside the loop",
                    "It resets to 0 every pass."},
                new String[]{"Counting outside the if",
                    "Then it counts every pass, not every match."},
                new String[]{"Printing inside the loop",
                    "You get a running total on every line instead of one "
                    + "answer."})
            .cyber(
                "Counting inside a loop is the core of most detection rules. "
                + "'More than 5 failed logins for one user' is a counter. "
                + "'More than 100 ports touched by one address' - a port "
                + "scan - is a counter. 'More than 20 password resets in a "
                + "minute' is a counter compared with a threshold.\n"
                + "\n"
                + "The bugs matter too: a counter reset in the wrong place is "
                + "a detection rule that can never fire.")
            .check(new Task(Task.CHOICE,
                    "Where must a counter be declared and set to 0?")
                .choices("Inside the loop body", "Before the loop",
                         "After the loop", "Inside the if")
                .accept("2", "b")
                .hints("It must survive every pass.",
                       "Once, before any counting.")
                .explain(
                    "Before the loop. Inside, it would be reset on every "
                    + "pass.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int n = 3;",
                    "while (n > 0) {",
                    "    System.out.print(n + \" \");",
                    "    n--;",
                    "}",
                    "System.out.println(\"go\");")
                .accept("3 2 1 go")
                .hints("print, not println: all on one line.",
                       "n counts down 3, 2, 1.")
                .explain(
                    "3 2 1 go - counting down with n--, on one line because "
                    + "of print.")
                .xp(10))
            .recap(
                "A counter: 0 before the loop, ++ inside when something "
                + "matches, used after the loop. The loop variable says where "
                + "you are and can step by any amount; the counter says how "
                + "many matched.")
            .next("Next: the loop that never stops."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(4), "The Loop That Never Ends", 4)
            .brief(
                "The disk-wipe tool was supposed to wipe three blocks. It has "
                + "been running for ten minutes, printing ever-larger block "
                + "numbers. Someone wrote ++ where -- was meant. Every loop "
                + "can do this - and some attackers try to make yours do it "
                + "on purpose.")
            .willLearn("Infinite loops")
            .whyUseful(
                "An infinite loop hangs a program, burns a CPU and can take a "
                + "service down. Spotting one before it runs - and knowing "
                + "how to stop one that is running - is basic survival.")
            .concept("Infinite loops",
                "A loop whose condition never becomes false runs for ever: an "
                + "INFINITE LOOP. The usual causes:\n"
                + "\n"
                + "    no update        the body never changes the test\n"
                + "    wrong direction  remaining++ when counting down\n"
                + "    stepping over    i != 5 with i += 2: 4, 6, 8...\n"
                + "    stray semicolon  while (n < 5); { ... }\n"
                + "\n"
                + "The third is sneaky. With !=, the loop only stops if the "
                + "variable lands EXACTLY on the target. Using < or <= "
                + "instead stops it as soon as it gets there OR goes past - "
                + "which is why < is the safer habit.\n"
                + "\n"
                + "The fourth is a classic: the semicolon ends the while, "
                + "making an empty loop that checks n < 5 for ever. The block "
                + "below it is not part of the loop at all.\n"
                + "\n"
                + "TO STOP a program stuck in a loop, press Ctrl and C "
                + "together in the terminal. The game's labs stop any program "
                + "after five seconds for the same reason.\n"
                + "\n"
                + "A loop whose end depends on outside data - user input, a "
                + "network message - should also have a LIMIT, so that bad "
                + "data cannot keep it going for ever.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int remaining = 3;",
                "        while (remaining > 0) {",
                "            System.out.println(\"Wiping block \" + remaining);",
                "            remaining--;",
                "        }",
                "        System.out.println(\"Wipe complete\");",
                "    }",
                "}")
            .exampleOutput(
                "Wiping block 3",
                "Wiping block 2",
                "Wiping block 1",
                "Wipe complete")
            .lineByLine(
                new String[]{"while (remaining > 0)",
                    "The loop needs remaining to fall to 0."},
                new String[]{"remaining--;",
                    "Moves it DOWN, towards 0. With ++ it would climb for "
                    + "ever."},
                new String[]{"Wipe complete",
                    "Reached only because the loop can end."})
            .predict(new Task(Task.CHOICE,
                    "Which of these loops never ends?")
                .choices("int i = 0; while (i < 5) { i++; }",
                         "int i = 0; while (i != 5) { i += 2; }",
                         "int i = 10; while (i > 0) { i -= 3; }",
                         "int i = 1; while (i <= 100) { i *= 2; }")
                .accept("2", "b")
                .hints("Trace the values of i in each.",
                       "Does i ever land EXACTLY on 5?")
                .explain(
                    "b: i goes 0, 2, 4, 6, 8... and never equals 5, so != 5 "
                    + "stays true. The others all reach or pass their limit.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "A program is stuck in a loop in your terminal. How do you "
                    + "stop it?")
                .choices("Press ENTER", "Press Ctrl and C together",
                         "Type exit", "Wait - it will stop on its own")
                .accept("2", "b")
                .hints("It interrupts the running program.",
                       "Two keys together.")
                .explain(
                    "Ctrl+C interrupts the program. It will not stop on its "
                    + "own - that is what infinite means.")
                .xp(10))
            .objective(
                "Fix the wipe loop that never ends.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int remaining = 3;",
                "        while (remaining > 0) {",
                "            System.out.println(\"Wiping block \" + remaining);",
                "            remaining++;    // fix this line",
                "        }",
                "        System.out.println(\"Wipe complete\");",
                "    }",
                "}")
            .yourTask(
                "The loop counts the wrong way. Rewrite the marked line so "
                + "remaining moves towards 0.")
            .mainTask(new Task(Task.WRITE,
                    "Rewrite the marked line.")
                .accept("remaining--;", "remaining = remaining - 1;",
                        "remaining -= 1;", "--remaining;")
                .hints(
                    "The condition is remaining > 0.",
                    "To reach 0, remaining must go down.",
                    "remaining--;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int remaining = 3;",
                    "        while (remaining > 0) {",
                    "            System.out.println(\"Wiping block \" + remaining);",
                    "            remaining--;",
                    "        }",
                    "        System.out.println(\"Wipe complete\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "remaining now goes 3, 2, 1, 0. At 0, remaining > 0 is "
                    + "false, the loop ends, and Wipe complete prints.\n"
                    + "\n"
                    + "With ++, remaining climbed 4, 5, 6... and stayed above "
                    + "0 for ever. (Eventually an int overflows to a negative "
                    + "number - Campaign 01 - but only after about two "
                    + "billion passes.)")
                .explain(
                    "remaining--; moves it towards the end.")
                .xp(20))
            .mistakes(
                new String[]{"Updating the wrong way",
                    "Check the update moves TOWARDS the condition becoming "
                    + "false."},
                new String[]{"!= with a step bigger than 1",
                    "It can skip the target. Use < or <=."},
                new String[]{"A semicolon after while (...)",
                    "It makes an empty loop. The block is not part of it."})
            .cyber(
                "Making a server loop for ever is a denial-of-service attack. "
                + "Real examples include regular-expression patterns that "
                + "take effectively for ever on crafted input (ReDoS), and "
                + "parsers that loop without end on a malformed file.\n"
                + "\n"
                + "The defence is a habit: any loop whose end depends on data "
                + "someone else controls gets a hard limit - at most 1000 "
                + "lines, at most 10 retries, at most 5 seconds - so the worst "
                + "an attacker can do is hit the limit.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int i = 0;",
                    "while (i < 5) {",
                    "    i += 2;",
                    "}",
                    "System.out.println(i);")
                .accept("6")
                .hints("i goes 0, 2, 4, then...",
                       "With <, overshooting still stops the loop.")
                .explain(
                    "6. i goes 0, 2, 4, 6. 6 < 5 is false, so the loop stops "
                    + "even though i never equalled 5.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Why is i < 5 a safer condition than i != 5?")
                .choices("It is faster",
                         "It still stops if i jumps past 5",
                         "!= does not compile in a while",
                         "It only works with ++")
                .accept("2", "b")
                .hints("Think about i += 2 from 0.",
                       "What if i never lands exactly on 5?")
                .explain(
                    "< stops as soon as i reaches OR passes 5. != only stops "
                    + "on an exact hit.")
                .xp(10))
            .recap(
                "A loop whose condition never turns false never ends. Watch "
                + "for: no update, the wrong direction, != with a big step, "
                + "and a semicolon after while (...).\n"
                + "\n"
                + "Ctrl+C stops a runaway program. Loops driven by outside "
                + "data need a limit.")
            .next("Next: a loop that reads until it is told to stop."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(5), "Read Until Done", 4)
            .brief(
                "The analyst types host names into the scan queue - one, "
                + "three, twenty, nobody knows how many in advance - and "
                + "types done when finished. The loop cannot count to a fixed "
                + "number. It has to watch for a special value.")
            .willLearn("Sentinel values")
            .whyUseful(
                "Reading until a stop marker is how programs handle input of "
                + "unknown length: commands until quit, lines until the end, "
                + "numbers until -1.")
            .concept("Sentinel values",
                "A SENTINEL is a special input that means 'stop', such as "
                + "done or -1. A sentinel loop has a shape worth learning "
                + "exactly:\n"
                + "\n"
                + "    String host = input.nextLine();       1. read\n"
                + "    while (!host.equals(\"done\")) {       2. test\n"
                + "        ... use host ...                 3. use\n"
                + "        host = input.nextLine();         4. read again\n"
                + "    }\n"
                + "\n"
                + "The first read comes BEFORE the loop - a 'priming read' - "
                + "so the test has something to check. The next read comes "
                + "at the END of the body, so the test checks the new value "
                + "before it is used. The sentinel itself is never processed "
                + "as data.\n"
                + "\n"
                + "Rules for choosing a sentinel:\n"
                + "\n"
                + "    - it must never be a real value: -1 for a count,\n"
                + "      done for a host name nobody would use\n"
                + "    - compare text with equals, never ==\n"
                + "\n"
                + "Forget the read inside the loop and the test checks the "
                + "same value for ever: an infinite loop. If the very first "
                + "input is the sentinel, the body runs zero times - which is "
                + "correct.")
            .example(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int count = 0;",
                "        System.out.print(\"Host (or done): \");",
                "        String host = input.nextLine().trim();",
                "        while (!host.equals(\"done\")) {",
                "            count++;",
                "            System.out.println(\"Queued \" + host);",
                "            System.out.print(\"Host (or done): \");",
                "            host = input.nextLine().trim();",
                "        }",
                "        System.out.println(count + \" hosts queued\");",
                "    }",
                "}")
            .exampleInput("web-01", "db-02", "done")
            .exampleOutput(
                "Host (or done): web-01",
                "Queued web-01",
                "Host (or done): db-02",
                "Queued db-02",
                "Host (or done): done",
                "2 hosts queued")
            .lineByLine(
                new String[]{"String host = input.nextLine().trim();",
                    "The priming read, before the loop."},
                new String[]{"while (!host.equals(\"done\"))",
                    "Keep going while the input is not the sentinel."},
                new String[]{"host = input.nextLine().trim();",
                    "Read again, last thing in the body. The test checks it "
                    + "next."},
                new String[]{"2 hosts queued",
                    "done was read, but never queued."})
            .predict(new Task(Task.PREDICT,
                    "The person types 5, then 3, then -1. What does this "
                    + "print?")
                .code(
                    "Scanner input = new Scanner(System.in);",
                    "int total = 0;",
                    "int n = Integer.parseInt(input.nextLine());",
                    "while (n != -1) {",
                    "    total += n;",
                    "    n = Integer.parseInt(input.nextLine());",
                    "}",
                    "System.out.println(\"Total: \" + total);")
                .input("5", "3", "-1")
                .accept("Total: 8")
                .hints("-1 is the sentinel.",
                       "Only 5 and 3 are added.")
                .explain(
                    "Total: 8. The -1 stops the loop and is never added.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "What happens if the loop body forgets to read the next "
                    + "line?")
                .choices("The loop runs once",
                         "The loop never ends",
                         "Java reads it automatically",
                         "It does not compile")
                .accept("2", "b")
                .hints("What does the test check on the second pass?",
                       "The same value as the first.")
                .explain(
                    "It never ends: the test keeps checking the same first "
                    + "value, which is not the sentinel.")
                .xp(10))
            .objective(
                "Run commands until the analyst types quit.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"> \");",
                "        String command = input.nextLine().trim();",
                "        // the while line: keep going until the command is quit",
                "            System.out.println(\"Running \" + command);",
                "            System.out.print(\"> \");",
                "            command = input.nextLine().trim();",
                "        }",
                "        System.out.println(\"Session closed\");",
                "    }",
                "}")
            .yourTask(
                "Write the while line: the loop keeps going while command is "
                + "NOT the text quit.")
            .mainTask(new Task(Task.WRITE,
                    "Write the while line.")
                .accept("while (!command.equals(\"quit\")) {",
                        "while(!command.equals(\"quit\")) {",
                        "while (!command.equals(\"quit\")){",
                        "while (!\"quit\".equals(command)) {")
                .hints(
                    "Text is compared with equals.",
                    "Keep going while it is NOT quit: put ! in front.",
                    "while (!command.equals(\"quit\")) {")
                .solution(
                    "import java.util.Scanner;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Scanner input = new Scanner(System.in);",
                    "        System.out.print(\"> \");",
                    "        String command = input.nextLine().trim();",
                    "        while (!command.equals(\"quit\")) {",
                    "            System.out.println(\"Running \" + command);",
                    "            System.out.print(\"> \");",
                    "            command = input.nextLine().trim();",
                    "        }",
                    "        System.out.println(\"Session closed\");",
                    "    }",
                    "}")
                .input("status", "scan", "quit")
                .whyItWorks(
                    "The priming read gets the first command. While it is "
                    + "not quit, the body runs it and reads the next one. "
                    + "Typing status, scan and quit runs two commands and then "
                    + "prints Session closed.\n"
                    + "\n"
                    + "! turns 'is quit' into 'is not quit'. == would compare "
                    + "text by location and never match what Scanner read - "
                    + "and the session could never be closed.")
                .explain(
                    "while (!command.equals(\"quit\")) {")
                .xp(20))
            .mistakes(
                new String[]{"No priming read",
                    "The test has nothing to check the first time."},
                new String[]{"No read at the end of the body",
                    "The loop checks the same value for ever."},
                new String[]{"Using == on text",
                    "Compare the sentinel with equals."})
            .cyber(
                "Sentinel loops are how command shells, network protocols and "
                + "file parsers read input of unknown length - and every one "
                + "of them has to ask: what if the stop marker never comes? "
                + "A client that never sends quit, or a file with no end "
                + "marker, must not be able to tie the program up for ever.\n"
                + "\n"
                + "That is why real servers combine the sentinel with a limit "
                + "- a maximum number of commands or a timeout - and why a "
                + "sentinel must be impossible as real data, or an attacker "
                + "could end your loop early.")
            .check(new Task(Task.CHOICE,
                    "Why is -1 a good sentinel when reading failed-login "
                    + "counts?")
                .choices("It is the smallest number",
                         "A real count can never be -1",
                         "Java treats -1 specially",
                         "It is easy to type")
                .accept("2", "b")
                .hints("A sentinel must never be real data.",
                       "Can a count be negative?")
                .explain(
                    "No real count is negative, so -1 can only mean stop.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "The person types done straight away. What does this "
                    + "print?")
                .code(
                    "Scanner input = new Scanner(System.in);",
                    "int count = 0;",
                    "String host = input.nextLine();",
                    "while (!host.equals(\"done\")) {",
                    "    count++;",
                    "    host = input.nextLine();",
                    "}",
                    "System.out.println(count + \" hosts\");")
                .input("done")
                .accept("0 hosts")
                .hints("The test is false before the first pass.",
                       "The body never runs.")
                .explain(
                    "0 hosts. The first input is the sentinel, so the body "
                    + "runs zero times - exactly right.")
                .xp(10))
            .recap(
                "    read\n"
                + "    while (not the sentinel) {\n"
                + "        use it\n"
                + "        read again\n"
                + "    }\n"
                + "\n"
                + "Prime before the loop, read again at the end of the body. "
                + "The sentinel must never be real data, and is never "
                + "processed.")
            .next("Next: the loop that runs one time too many."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(6), "One Too Many", 4)
            .brief(
                "Policy: an account locks after its 5th failed attempt. The "
                + "test harness shows it locking after the 4th. One character "
                + "- < instead of <= - and every account locks early. Loops "
                + "go wrong at their edges more than anywhere else.")
            .willLearn("Off-by-one errors")
            .whyUseful(
                "Off-by-one mistakes are the most common loop bug there is. "
                + "They rarely crash - they quietly do one pass too many or "
                + "too few, which is worse.")
            .concept("Off-by-one errors",
                "An OFF-BY-ONE error is a loop that runs one pass too many "
                + "or too few. It comes from three decisions that must agree:\n"
                + "\n"
                + "    where it starts      0 or 1?\n"
                + "    how it stops         < or <=?\n"
                + "    what it counts       inclusive or exclusive?\n"
                + "\n"
                + "Two common, correct pairings:\n"
                + "\n"
                + "    start 0, < 5     0 1 2 3 4    five passes\n"
                + "    start 1, <= 5    1 2 3 4 5    five passes\n"
                + "\n"
                + "Mixing them - start 1 with < 5, or start 0 with <= 5 - "
                + "gives four or six.\n"
                + "\n"
                + "A quick check: how many values from a to b INCLUSIVE? "
                + "b - a + 1. From 3 to 7 is 5 values, not 4 - the FENCEPOST "
                + "problem: a 4-metre fence with posts every metre needs 5 "
                + "posts.\n"
                + "\n"
                + "To test a loop, check its first pass and its last pass "
                + "by hand. If both are right, the middle almost always is.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int i = 0;",
                "        System.out.print(\"start 0, < 5:  \");",
                "        while (i < 5) {",
                "            System.out.print(i + \" \");",
                "            i++;",
                "        }",
                "        System.out.println();",
                "        i = 1;",
                "        System.out.print(\"start 1, <= 5: \");",
                "        while (i <= 5) {",
                "            System.out.print(i + \" \");",
                "            i++;",
                "        }",
                "        System.out.println();",
                "    }",
                "}")
            .exampleOutput(
                "start 0, < 5:  0 1 2 3 4",
                "start 1, <= 5: 1 2 3 4 5")
            .lineByLine(
                new String[]{"while (i < 5) from 0",
                    "Five passes, 0 to 4. The 5 itself is excluded."},
                new String[]{"while (i <= 5) from 1",
                    "Five passes, 1 to 5. The 5 is included."},
                new String[]{"System.out.println();",
                    "An empty println just ends the line."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int i = 1;",
                    "int count = 0;",
                    "while (i < 10) {",
                    "    count++;",
                    "    i++;",
                    "}",
                    "System.out.println(count);")
                .accept("9")
                .hints("i takes the values 1 to 9.",
                       "10 is excluded by <.")
                .explain(
                    "9. From 1 up to but not including 10 is nine values. "
                    + "Starting at 0 would have given 10.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Retry at most 3 times. attempts starts at 0 and goes up "
                    + "by one each pass. Which condition?")
                .choices("attempts <= 3", "attempts < 3", "attempts < 4",
                         "attempts == 3")
                .accept("2", "b")
                .hints("Starting at 0, which values should run?",
                       "0, 1 and 2 are three passes.")
                .explain(
                    "attempts < 3 runs for 0, 1 and 2 - three passes. <= 3 "
                    + "would give four; == 3 would never run at all.")
                .xp(15))
            .objective(
                "Make the lockout test include the fifth attempt.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int attempt = 1;",
                "        while (attempt < 5) {    // fix this line",
                "            System.out.println(\"Failed attempt \" + attempt);",
                "            attempt++;",
                "        }",
                "        System.out.println(\"Account locked\");",
                "    }",
                "}")
            .yourTask(
                "The loop should show attempts 1 to 5, then lock. It stops at "
                + "4. Rewrite the while line.")
            .mainTask(new Task(Task.WRITE,
                    "Rewrite the while line.")
                .accept("while (attempt <= 5) {", "while(attempt <= 5) {",
                        "while (attempt <= 5){", "while (attempt < 6) {")
                .hints(
                    "Which value is missing from the output?",
                    "5 must be included: <=.",
                    "while (attempt <= 5) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int attempt = 1;",
                    "        while (attempt <= 5) {",
                    "            System.out.println(\"Failed attempt \" + attempt);",
                    "            attempt++;",
                    "        }",
                    "        System.out.println(\"Account locked\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "Starting at 1 with <= 5 gives exactly five passes: 1, 2, "
                    + "3, 4, 5. The last pass printed is the fifth attempt, "
                    + "and only then is the account locked - as the policy "
                    + "says.\n"
                    + "\n"
                    + "The check that finds bugs like this: trace the LAST "
                    + "pass by hand. With < 5, the last value printed was 4.")
                .explain(
                    "while (attempt <= 5) { - start 1, stop after 5.")
                .xp(20))
            .mistakes(
                new String[]{"Mixing start 1 with <",
                    "Leaves the last value out."},
                new String[]{"Mixing start 0 with <=",
                    "Adds an extra pass."},
                new String[]{"Only testing the middle",
                    "Bugs live on the first and last pass."})
            .cyber(
                "Off-by-one errors are behind real security bugs. A lockout "
                + "that allows 6 guesses instead of 5 is a minor one. In "
                + "languages like C, writing one element past the end of a "
                + "buffer - a loop that runs one pass too many - is how "
                + "classic memory-corruption exploits begin. Java stops that "
                + "particular attack (Campaign 05 shows how), but it cannot "
                + "stop a lockout, a rate limit or a retention period from "
                + "being off by one.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int i = 0;",
                    "int passes = 0;",
                    "while (i <= 10) {",
                    "    i += 2;",
                    "    passes++;",
                    "}",
                    "System.out.println(passes);")
                .accept("6")
                .hints("Which values of i start a pass?",
                       "0, 2, 4, 6, 8, 10.")
                .explain(
                    "6. Passes start with i at 0, 2, 4, 6, 8 and 10 - six in "
                    + "all, because <= includes 10.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "How many whole numbers are there from 3 to 7, including "
                    + "both?")
                .choices("4", "5", "6", "7")
                .accept("2", "b")
                .hints("b - a + 1.",
                       "3, 4, 5, 6, 7.")
                .explain(
                    "5: 7 - 3 + 1. Forgetting the + 1 is the fencepost "
                    + "error.")
                .xp(10))
            .recap(
                "Start 0 with <, or start 1 with <= - both give n passes; "
                + "mixing them gives one too few or too many. Inclusive "
                + "counts are b - a + 1.\n"
                + "\n"
                + "Trace the first and last pass of every loop.")
            .next("Next: a loop built for counting."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(7), "The Counting Loop", 4)
            .brief(
                "Most loops so far have the same three pieces scattered over "
                + "three lines: a start before the loop, a test in the while, "
                + "an update at the bottom of the body. Java has a loop that "
                + "puts all three on one line, where nobody can forget one.")
            .willLearn("for loop")
            .whyUseful(
                "for is the loop you will write most often: whenever you know "
                + "how many passes there are, or which range to cover. Having "
                + "start, test and update together makes off-by-one mistakes "
                + "easier to spot.")
            .concept("for loop",
                "A for loop packs the start, the test and the update into its "
                + "header:\n"
                + "\n"
                + "    for (int i = 1; i <= 5; i++) {\n"
                + "        System.out.println(\"Pinging 10.0.0.\" + i);\n"
                + "    }\n"
                + "\n"
                + "    int i = 1    START   runs once, before anything\n"
                + "    i <= 5       TEST    checked before every pass\n"
                + "    i++          UPDATE  runs after every pass\n"
                + "\n"
                + "It does exactly what this while does:\n"
                + "\n"
                + "    int i = 1;\n"
                + "    while (i <= 5) {\n"
                + "        System.out.println(\"Pinging 10.0.0.\" + i);\n"
                + "        i++;\n"
                + "    }\n"
                + "\n"
                + "with one difference: a variable declared in the for header "
                + "only exists INSIDE the loop. After the closing brace, i is "
                + "gone - using it is 'cannot find symbol'. That is a good "
                + "thing: the counter cannot leak into the rest of the "
                + "program.\n"
                + "\n"
                + "The update can be anything: i--, i += 10, i *= 2. The "
                + "three parts are separated by semicolons, not commas.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        for (int i = 1; i <= 5; i++) {",
                "            System.out.println(\"Pinging 10.0.0.\" + i);",
                "        }",
                "        System.out.println(\"Sweep done\");",
                "    }",
                "}")
            .exampleOutput(
                "Pinging 10.0.0.1",
                "Pinging 10.0.0.2",
                "Pinging 10.0.0.3",
                "Pinging 10.0.0.4",
                "Pinging 10.0.0.5",
                "Sweep done")
            .lineByLine(
                new String[]{"int i = 1",
                    "Runs once. i exists only inside this loop."},
                new String[]{"i <= 5",
                    "Checked before each pass, like a while."},
                new String[]{"i++",
                    "Runs after each pass, before the next test."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "for (int i = 10; i > 0; i -= 4) {",
                    "    System.out.println(i);",
                    "}")
                .accept("10 6 2")
                .hints("i starts at 10 and drops by 4.",
                       "After 2 comes -2, which fails the test.")
                .explain(
                    "10 6 2 (one per line). The update runs after each pass: "
                    + "10, 6, 2, then -2 fails i > 0.")
                .xp(10))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "for (int i = 0; i < 3; i++) {",
                    "    System.out.println(i);",
                    "}",
                    "System.out.println(i);")
                .accept("4", "line 4")
                .hints("Where was i declared?",
                       "Does it exist after the loop?")
                .explain(
                    "Line 4: 'cannot find symbol'. i was declared in the for "
                    + "header, so it only exists inside the loop.")
                .xp(20))
            .objective(
                "Sweep the first five addresses with a for loop.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // the for header: i from 1 to 5 inclusive",
                "            System.out.println(\"Pinging 10.0.0.\" + i);",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the for header: i starts at 1, runs while i is 5 or "
                + "less, and goes up by one.")
            .mainTask(new Task(Task.WRITE,
                    "Write the for header.")
                .accept("for (int i = 1; i <= 5; i++) {",
                        "for(int i = 1; i <= 5; i++) {",
                        "for (int i = 1; i <= 5; i++){",
                        "for (int i = 1; i < 6; i++) {",
                        "for (int i=1; i<=5; i++) {",
                        "for (int i = 1; i <= 5; ++i) {")
                .hints(
                    "for, then three parts in brackets, separated by "
                    + "semicolons.",
                    "Start: int i = 1. Test: i <= 5. Update: i++.",
                    "for (int i = 1; i <= 5; i++) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        for (int i = 1; i <= 5; i++) {",
                    "            System.out.println(\"Pinging 10.0.0.\" + i);",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "The header declares i, starts it at 1, tests it before "
                    + "each pass and adds one after each pass. The body runs "
                    + "for 1 to 5 and pings 10.0.0.1 to 10.0.0.5.\n"
                    + "\n"
                    + "All three parts are on one line, so a reader can check "
                    + "the range at a glance - start 1, include 5 - without "
                    + "hunting for the update at the bottom of the body.")
                .explain(
                    "for (int i = 1; i <= 5; i++) { - start, test, update.")
                .xp(20))
            .mistakes(
                new String[]{"Commas instead of semicolons",
                    "for (int i = 0, i < 5, i++) does not compile."},
                new String[]{"Using i after the loop",
                    "It only exists inside. Declare it before the loop if you "
                    + "need it later."},
                new String[]{"Also writing i++ in the body",
                    "Then i goes up by two each pass."})
            .cyber(
                "The for loop is the shape of every sweep: ports 1 to 1024, "
                + "addresses 1 to 254 in a subnet, PINs 0000 to 9999. Reading "
                + "a for header tells you the whole range at a glance - which "
                + "is exactly what an analyst reading a suspicious script "
                + "wants to know: what is this code about to touch, and how "
                + "much of it?")
            .check(new Task(Task.CHOICE,
                    "In a for loop, when does the update part run?")
                .choices("Once, at the start",
                         "Before each test",
                         "After each pass of the body",
                         "Only when the test is false")
                .accept("3", "c")
                .hints("Think of where i++ sat in the while version.",
                       "At the bottom of the body.")
                .explain(
                    "After each pass of the body, and then the test is "
                    + "checked again.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int sum = 0;",
                    "for (int i = 1; i <= 4; i++) {",
                    "    sum += i;",
                    "}",
                    "System.out.println(sum);")
                .accept("10")
                .hints("1 + 2 + 3 + 4.",
                       "sum was declared before the loop, so it survives.")
                .explain(
                    "10. sum lives outside the loop, so it keeps its value; i "
                    + "does not.")
                .xp(10))
            .recap(
                "    for (start; test; update) { body }\n"
                + "\n"
                + "Start runs once; test before every pass; update after "
                + "every pass. A variable declared in the header exists only "
                + "inside the loop. Use for when the range is known.")
            .next("Next: looping over the characters of a String."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(8), "One Character at a Time", 4)
            .brief(
                "Checking a password for digits, or a user name for strange "
                + "characters, means looking at EVERY character - and the "
                + "program does not know the length in advance. A for loop "
                + "over the positions of a String does exactly that.")
            .willLearn("Looping over a String")
            .whyUseful(
                "Character-by-character loops are behind every text check a "
                + "tool makes: counting digits, finding a forbidden symbol, "
                + "reversing, masking. They are the building block of the "
                + "rest of this campaign.")
            .concept("Looping over a String",
                "A String's characters sit at positions 0 to length() - 1 "
                + "(Campaign 01). A for loop visits every one:\n"
                + "\n"
                + "    for (int i = 0; i < text.length(); i++) {\n"
                + "        char c = text.charAt(i);\n"
                + "        ...\n"
                + "    }\n"
                + "\n"
                + "    start 0              the first position\n"
                + "    i < text.length()    stop BEFORE length - the\n"
                + "                         last position is length - 1\n"
                + "\n"
                + "Write <= text.length() and the last pass asks for a "
                + "position that does not exist: the program crashes with "
                + "StringIndexOutOfBoundsException.\n"
                + "\n"
                + "Backwards works the same way, starting at the last "
                + "position and stopping after 0:\n"
                + "\n"
                + "    for (int i = text.length() - 1; i >= 0; i--)\n"
                + "\n"
                + "Because the loop asks the String for its length, it works "
                + "for any text: empty, one character, or a thousand. An "
                + "empty String has length 0, so the body runs zero times - "
                + "no special case needed.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String user = \"root\";",
                "        for (int i = 0; i < user.length(); i++) {",
                "            char c = user.charAt(i);",
                "            System.out.println(\"Position \" + i + \": \" + c);",
                "        }",
                "    }",
                "}")
            .exampleOutput(
                "Position 0: r",
                "Position 1: o",
                "Position 2: o",
                "Position 3: t")
            .lineByLine(
                new String[]{"int i = 0",
                    "Positions start at 0."},
                new String[]{"i < user.length()",
                    "length() is 4; the last position is 3."},
                new String[]{"char c = user.charAt(i);",
                    "The character at this pass's position."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String s = \"abc\";",
                    "for (int i = s.length() - 1; i >= 0; i--) {",
                    "    System.out.print(s.charAt(i));",
                    "}",
                    "System.out.println();")
                .accept("cba")
                .hints("The loop starts at the last position.",
                       "print keeps it on one line.")
                .explain(
                    "cba. i goes 2, 1, 0, so the characters come out in "
                    + "reverse.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Which condition visits every character of text exactly "
                    + "once, starting from i = 0?")
                .choices("i <= text.length()", "i < text.length()",
                         "i < text.length() - 1", "i != text.length() + 1")
                .accept("2", "b")
                .hints("The last position is length - 1.",
                       "Stop BEFORE length.")
                .explain(
                    "i < text.length(). <= crashes on the last pass; "
                    + "length() - 1 misses the last character.")
                .xp(10))
            .objective(
                "Show every character of a suspicious user name.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String word = \"adm1n\";",
                "        // the for header: every position of word",
                "            System.out.println(i + \": \" + word.charAt(i));",
                "        }",
                "    }",
                "}")
            .yourTask(
                "Write the for header that visits every position of word, "
                + "from 0 to the last.")
            .mainTask(new Task(Task.WRITE,
                    "Write the for header.")
                .accept("for (int i = 0; i < word.length(); i++) {",
                        "for(int i = 0; i < word.length(); i++) {",
                        "for (int i = 0; i < word.length(); i++){",
                        "for (int i = 0; i <= word.length() - 1; i++) {")
                .hints(
                    "Start at 0.",
                    "Stop before word.length().",
                    "for (int i = 0; i < word.length(); i++) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String word = \"adm1n\";",
                    "        for (int i = 0; i < word.length(); i++) {",
                    "            System.out.println(i + \": \" + word.charAt(i));",
                    "        }",
                    "    }",
                    "}")
                .whyItWorks(
                    "word has length 5, so i runs 0 to 4, and each pass "
                    + "prints one position and its character - including the "
                    + "digit 1 hiding where an i should be.\n"
                    + "\n"
                    + "Using word.length() rather than 5 means the loop is "
                    + "right for any word, which is the point: the next name "
                    + "checked will be a different length.")
                .explain(
                    "for (int i = 0; i < word.length(); i++) {")
                .xp(20))
            .mistakes(
                new String[]{"<= length()",
                    "Crashes: the last position is length - 1."},
                new String[]{"Starting at 1",
                    "Skips the first character."},
                new String[]{"A fixed number instead of length()",
                    "Breaks on the next String of a different length."})
            .cyber(
                "Look-alike names - adm1n, paypa1, rn for m - fool people "
                + "reading quickly, and a character-by-character loop is how "
                + "a program sees them. Username filters, phishing-domain "
                + "checkers and password rules all walk the text one "
                + "character at a time, asking a question about each.\n"
                + "\n"
                + "The <= crash matters too: a text parser that throws on a "
                + "crafted input length is a parser an attacker can knock "
                + "over at will.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String s = \"banana\";",
                    "int count = 0;",
                    "for (int i = 0; i < s.length(); i++) {",
                    "    if (s.charAt(i) == 'a') {",
                    "        count++;",
                    "    }",
                    "}",
                    "System.out.println(count);")
                .accept("3")
                .hints("chars are compared with ==.",
                       "b-a-n-a-n-a.")
                .explain(
                    "3. The loop checks every character, and the counter "
                    + "goes up for each 'a'.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "A String has length 6. What is its last valid "
                    + "position?")
                .choices("6", "5", "7", "0")
                .accept("2", "b")
                .hints("Positions start at 0.",
                       "length - 1.")
                .explain(
                    "5. Positions run 0 to 5 - six characters.")
                .xp(10))
            .recap(
                "    for (int i = 0; i < text.length(); i++) {\n"
                + "        char c = text.charAt(i);\n"
                + "    }\n"
                + "\n"
                + "0 up to, not including, length. Backwards: from length - "
                + "1 down to 0. <= length crashes.")
            .next("Next: adding things up as the loop goes."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(9), "Adding It Up", 4)
            .brief(
                "Data-loss monitoring wants the total bytes sent in a batch "
                + "of sessions, and the average per session. The numbers "
                + "arrive one at a time. A loop can keep a running total and "
                + "a count, and work out the average at the end.")
            .willLearn("Accumulators")
            .whyUseful(
                "Totals and averages are how raw events become something to "
                + "act on: total data out per user, average failures per "
                + "hour. Every dashboard number starts as an accumulator in "
                + "a loop.")
            .concept("Accumulators",
                "An ACCUMULATOR is a counter's big brother: instead of adding "
                + "1, it adds each VALUE as it arrives.\n"
                + "\n"
                + "    int total = 0;            before: start empty\n"
                + "    while (...) {\n"
                + "        total += bytes;       inside: add this value\n"
                + "    }\n"
                + "\n"
                + "Keep a counter alongside it and you have everything for "
                + "an average:\n"
                + "\n"
                + "    double average = (double) total / count;\n"
                + "\n"
                + "Two traps from Campaign 01 meet here:\n"
                + "\n"
                + "    total / count        int division loses the\n"
                + "                         fraction - cast first\n"
                + "    count could be 0     division by zero - check\n"
                + "                         before dividing\n"
                + "\n"
                + "The starting value depends on the operation. A SUM starts "
                + "at 0, because adding 0 changes nothing. A PRODUCT starts "
                + "at 1, because multiplying by 1 changes nothing - start a "
                + "product at 0 and it stays 0 for ever.")
            .example(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int total = 0;",
                "        int count = 0;",
                "        System.out.print(\"Bytes (or -1): \");",
                "        int bytes = Integer.parseInt(input.nextLine().trim());",
                "        while (bytes != -1) {",
                "            total += bytes;",
                "            count++;",
                "            System.out.print(\"Bytes (or -1): \");",
                "            bytes = Integer.parseInt(input.nextLine().trim());",
                "        }",
                "        System.out.println(\"Total: \" + total);",
                "        if (count > 0) {",
                "            System.out.println(\"Average: \" + (double) total / count);",
                "        }",
                "    }",
                "}")
            .exampleInput("1200", "800", "4000", "-1")
            .exampleOutput(
                "Bytes (or -1): 1200",
                "Bytes (or -1): 800",
                "Bytes (or -1): 4000",
                "Bytes (or -1): -1",
                "Total: 6000",
                "Average: 2000.0")
            .lineByLine(
                new String[]{"total += bytes;",
                    "The accumulator: add this session's bytes."},
                new String[]{"count++;",
                    "The counter, for the average."},
                new String[]{"if (count > 0)",
                    "No sessions means no average - and no division by zero."},
                new String[]{"(double) total / count",
                    "Cast first, so the fraction survives."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int sum = 0;",
                    "for (int i = 1; i <= 5; i++) {",
                    "    sum += i;",
                    "}",
                    "System.out.println(sum);")
                .accept("15")
                .hints("1 + 2 + 3 + 4 + 5.",
                       "sum grows every pass.")
                .explain(
                    "15. The accumulator adds 1, then 2, up to 5.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "The person types 4, 6, then -1. What does this print?")
                .code(
                    "Scanner input = new Scanner(System.in);",
                    "int sum = 0;",
                    "int count = 0;",
                    "int n = Integer.parseInt(input.nextLine());",
                    "while (n != -1) {",
                    "    sum += n;",
                    "    count++;",
                    "    n = Integer.parseInt(input.nextLine());",
                    "}",
                    "System.out.println((double) sum / count);")
                .input("4", "6", "-1")
                .accept("5.0")
                .hints("sum is 10 and count is 2.",
                       "A double division prints with a decimal point.")
                .explain(
                    "5.0. 10 / 2 as a double. Without the cast it would print "
                    + "5 - and 4 and 7 would print 5 instead of 5.5.")
                .xp(15))
            .objective(
                "Total the bytes sent in a batch of sessions.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int total = 0;",
                "        System.out.print(\"Bytes (or -1): \");",
                "        int bytes = Integer.parseInt(input.nextLine().trim());",
                "        while (bytes != -1) {",
                "            // add bytes to the total",
                "            System.out.print(\"Bytes (or -1): \");",
                "            bytes = Integer.parseInt(input.nextLine().trim());",
                "        }",
                "        System.out.println(\"Total: \" + total);",
                "    }",
                "}")
            .yourTask(
                "Write the accumulator line that adds this session's bytes "
                + "to total.")
            .mainTask(new Task(Task.WRITE,
                    "Write the accumulator line.")
                .accept("total += bytes;", "total = total + bytes;",
                        "total = bytes + total;")
                .hints(
                    "total gets bigger by bytes.",
                    "+= adds to a variable.",
                    "total += bytes;")
                .solution(
                    "import java.util.Scanner;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Scanner input = new Scanner(System.in);",
                    "        int total = 0;",
                    "        System.out.print(\"Bytes (or -1): \");",
                    "        int bytes = Integer.parseInt(input.nextLine().trim());",
                    "        while (bytes != -1) {",
                    "            total += bytes;",
                    "            System.out.print(\"Bytes (or -1): \");",
                    "            bytes = Integer.parseInt(input.nextLine().trim());",
                    "        }",
                    "        System.out.println(\"Total: \" + total);",
                    "    }",
                    "}")
                .input("1200", "800", "-1")
                .whyItWorks(
                    "Each pass adds the value just read to the running "
                    + "total, and the next read happens after it. Typing "
                    + "1200, 800 and -1 prints Total: 2000; the sentinel is "
                    + "never added.\n"
                    + "\n"
                    + "Where the line sits matters: after the next read, it "
                    + "would add the NEXT value - including the -1.")
                .explain(
                    "total += bytes; before the next read.")
                .xp(20))
            .mistakes(
                new String[]{"Integer division for an average",
                    "Cast to double first."},
                new String[]{"Dividing when count is 0",
                    "Check first. No data means no average."},
                new String[]{"A product starting at 0",
                    "Multiplying by 0 stays 0. Start at 1."})
            .cyber(
                "Data-exfiltration detection is an accumulator: total bytes "
                + "leaving the network per user, per hour, compared with that "
                + "user's usual average. One large transfer is obvious; an "
                + "attacker who sends a little at a time is only caught by "
                + "adding it all up.\n"
                + "\n"
                + "Averages hide things too. A normal average can contain one "
                + "enormous outlier - which is why the next mission tracks "
                + "the maximum as well.")
            .check(new Task(Task.CHOICE,
                    "What must be checked before dividing a total by a "
                    + "count?")
                .choices("That the total is positive", "That count is not 0",
                         "That count is even", "Nothing")
                .accept("2", "b")
                .hints("What if no values arrived?",
                       "Division by zero.")
                .explain(
                    "That count is not 0. With no data, there is no average "
                    + "- and an int division by zero crashes.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int product = 1;",
                    "for (int i = 1; i <= 4; i++) {",
                    "    product *= i;",
                    "}",
                    "System.out.println(product);")
                .accept("24")
                .hints("1 * 1 * 2 * 3 * 4.",
                       "A product starts at 1.")
                .explain(
                    "24. Starting at 1 lets the first multiplication count. "
                    + "Starting at 0 would print 0.")
                .xp(10))
            .recap(
                "An accumulator starts empty (0 for sums, 1 for products) and "
                + "adds each value inside the loop. With a counter, it gives "
                + "an average: cast to double, and check the count is not 0 "
                + "first.")
            .next("Next: the biggest and smallest value seen."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(10), "Biggest and Smallest", 4)
            .brief(
                "The average response time looks healthy, but one request "
                + "took four seconds - the kind of spike that can mean a "
                + "server under attack. The loop needs to remember the "
                + "slowest and fastest values it has seen, not just the "
                + "total.")
            .willLearn("Maximum and minimum")
            .whyUseful(
                "Maximums and minimums catch what averages hide: the largest "
                + "transfer, the fastest burst of logins, the oldest "
                + "unpatched host. Tracking them is a two-line pattern inside "
                + "any loop.")
            .concept("Maximum and minimum",
                "To find the largest value, keep a variable holding the "
                + "biggest seen so far, and replace it whenever something "
                + "bigger arrives:\n"
                + "\n"
                + "    if (value > max) {\n"
                + "        max = value;\n"
                + "    }\n"
                + "\n"
                + "Minimum is the same with <. Math.max and Math.min do it in "
                + "one line: max = Math.max(max, value);\n"
                + "\n"
                + "THE STARTING VALUE is the tricky part. Starting max at 0 "
                + "works only if every value is positive - with temperatures "
                + "of -5, -2 and -9, max would stay 0, a value that never "
                + "appeared. Two safe choices:\n"
                + "\n"
                + "    the first value    read one, and start both max\n"
                + "                       and min at it\n"
                + "    the extremes       max = Integer.MIN_VALUE;\n"
                + "                       min = Integer.MAX_VALUE;\n"
                + "\n"
                + "Starting with the first real value is usually clearest - "
                + "but then an empty input needs handling on its own, because "
                + "there is no first value.")
            .example(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Response ms (or -1): \");",
                "        int ms = Integer.parseInt(input.nextLine().trim());",
                "        int slowest = ms;",
                "        int fastest = ms;",
                "        while (ms != -1) {",
                "            if (ms > slowest) {",
                "                slowest = ms;",
                "            }",
                "            if (ms < fastest) {",
                "                fastest = ms;",
                "            }",
                "            System.out.print(\"Response ms (or -1): \");",
                "            ms = Integer.parseInt(input.nextLine().trim());",
                "        }",
                "        System.out.println(\"Slowest: \" + slowest);",
                "        System.out.println(\"Fastest: \" + fastest);",
                "    }",
                "}")
            .exampleInput("120", "340", "95", "210", "-1")
            .exampleOutput(
                "Response ms (or -1): 120",
                "Response ms (or -1): 340",
                "Response ms (or -1): 95",
                "Response ms (or -1): 210",
                "Response ms (or -1): -1",
                "Slowest: 340",
                "Fastest: 95")
            .lineByLine(
                new String[]{"int slowest = ms;",
                    "Start both at the first real value."},
                new String[]{"if (ms > slowest)",
                    "Only a bigger value replaces the maximum."},
                new String[]{"if (ms < fastest)",
                    "Only a smaller value replaces the minimum."},
                new String[]{"(one gap)",
                    "If the very first value is -1, both would be -1. A full "
                    + "program checks for that - the lab does."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int best = 0;",
                    "for (int i = 1; i <= 5; i++) {",
                    "    int score = (i * 7) % 10;",
                    "    if (score > best) {",
                    "        best = score;",
                    "    }",
                    "}",
                    "System.out.println(best);")
                .accept("8")
                .hints("The scores are 7, 4, 1, 8, 5.",
                       "best only ever goes up.")
                .explain(
                    "8. best becomes 7, stays 7 for 4 and 1, becomes 8, and "
                    + "stays 8 for 5.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Temperatures are -5, -2 and -9. max starts at 0. What "
                    + "does the loop report as the maximum?")
                .choices("-2", "0", "-9", "-5")
                .accept("2", "b")
                .hints("Is any temperature bigger than 0?",
                       "max never changes.")
                .explain(
                    "0 - a value that never appeared. Starting at the first "
                    + "value, or Integer.MIN_VALUE, would give -2.")
                .xp(15))
            .objective(
                "Track the largest transfer in a batch.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        int largest = Integer.MIN_VALUE;",
                "        System.out.print(\"MB (or -1): \");",
                "        int mb = Integer.parseInt(input.nextLine().trim());",
                "        while (mb != -1) {",
                "            // the if line: is this bigger than largest?",
                "                largest = mb;",
                "            }",
                "            System.out.print(\"MB (or -1): \");",
                "            mb = Integer.parseInt(input.nextLine().trim());",
                "        }",
                "        System.out.println(\"Largest: \" + largest + \" MB\");",
                "    }",
                "}")
            .yourTask(
                "Write the if line: replace largest only when mb is bigger "
                + "than it.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line.")
                .accept("if (mb > largest) {", "if(mb > largest) {",
                        "if (mb > largest){", "if (largest < mb) {")
                .hints(
                    "Compare the new value with the best so far.",
                    "Bigger means >.",
                    "if (mb > largest) {")
                .solution(
                    "import java.util.Scanner;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Scanner input = new Scanner(System.in);",
                    "        int largest = Integer.MIN_VALUE;",
                    "        System.out.print(\"MB (or -1): \");",
                    "        int mb = Integer.parseInt(input.nextLine().trim());",
                    "        while (mb != -1) {",
                    "            if (mb > largest) {",
                    "                largest = mb;",
                    "            }",
                    "            System.out.print(\"MB (or -1): \");",
                    "            mb = Integer.parseInt(input.nextLine().trim());",
                    "        }",
                    "        System.out.println(\"Largest: \" + largest + \" MB\");",
                    "    }",
                    "}")
                .input("40", "900", "12", "-1")
                .whyItWorks(
                    "largest starts at Integer.MIN_VALUE, the smallest int "
                    + "there is, so the first real value always replaces it. "
                    + "After that, only a bigger value does. Typing 40, 900, "
                    + "12 and -1 prints Largest: 900 MB.\n"
                    + "\n"
                    + "Using >= would also work here, but > keeps the FIRST of "
                    + "two equal maximums, which matters when you also record "
                    + "which session it was.")
                .explain(
                    "if (mb > largest) { - replace only on a bigger value.")
                .xp(20))
            .mistakes(
                new String[]{"Starting max at 0",
                    "Wrong whenever every value is negative."},
                new String[]{"Updating max with < by mistake",
                    "Then it tracks the minimum."},
                new String[]{"Forgetting the empty case",
                    "No values means no max. Check before reporting."})
            .cyber(
                "Maximums are how outliers get noticed. The largest single "
                + "transfer of the day, the most logins from one address in a "
                + "minute, the longest session - each is a classic trigger "
                + "for investigation. Averages smooth attacks away; the "
                + "maximum points straight at them.\n"
                + "\n"
                + "Minimums matter too: the fastest time between two logins "
                + "from different countries is how 'impossible travel' "
                + "alerts work.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int low = 100;",
                    "for (int i = 5; i <= 20; i += 5) {",
                    "    low = Math.min(low, 30 - i);",
                    "}",
                    "System.out.println(low);")
                .accept("10")
                .hints("30 - i gives 25, 20, 15, 10.",
                       "Math.min keeps the smaller.")
                .explain(
                    "10. Each pass keeps the smaller of low and 30 - i, "
                    + "ending with 10.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Which starting value for max works whatever the "
                    + "values?")
                .choices("0", "Integer.MIN_VALUE", "Integer.MAX_VALUE", "-1")
                .accept("2", "b")
                .hints("Every real value must beat it.",
                       "The smallest int there is.")
                .explain(
                    "Integer.MIN_VALUE: every int is at least as big, so the "
                    + "first value always replaces it.")
                .xp(10))
            .recap(
                "    if (value > max) { max = value; }\n"
                + "\n"
                + "Start max and min at the first value, or at "
                + "Integer.MIN_VALUE and Integer.MAX_VALUE - never at a guess "
                + "like 0. Handle the case with no values at all.")
            .next("Next: a loop that always runs at least once."));
    }
}
