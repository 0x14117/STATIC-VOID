import java.util.ArrayList;
import java.util.List;

/**
 * Every mission in CYBER//OPS.
 *
 * Missions are held in the order they should be played. The campaign runs
 * from absolute beginner Java upwards, and each mission only uses ideas that
 * an earlier mission has already taught.
 */
public class MissionLibrary {

    private static final List<Mission> MISSIONS = new ArrayList<>();

    static {
        buildAuthentication();
        buildLogs();
        buildAccessControl();
        buildBoss();
    }

    public static List<Mission> all() {
        return MISSIONS;
    }

    public static Mission byId(String id) {
        for (Mission mission : MISSIONS) {
            if (mission.getId().equalsIgnoreCase(id)) {
                return mission;
            }
        }
        return null;
    }

    /** The first mission the player has not finished, or null if all are done. */
    public static Mission nextFor(Player player) {
        for (Mission mission : MISSIONS) {
            if (!player.hasCompleted(mission.getId())) {
                return mission;
            }
        }
        return null;
    }

    // =====================================================================
    // CAMPAIGN 1 - AUTHENTICATION
    // =====================================================================

    private static void buildAuthentication() {

        MISSIONS.add(new Mission("AUTH-001", "First Contact", "Authentication", 1)
            .java("System.out.println", "String literals")
            .cyber("Security banners")
            .briefing(
                "Welcome to NORTHSTAR SYSTEMS. You are the new junior analyst on "
                + "the security desk.\n\n"
                + "Before anything else you need to understand how the terminal "
                + "tools here are written. Every one of them is a small Java "
                + "program, and every Java program does its talking through one "
                + "instruction.")
            .teaching(
                "System.out.println(\"text\") prints one line to the screen.\n\n"
                + "Read it as a path. System is a toolbox Java gives you. out is "
                + "the way out to your screen. println means print this, then "
                + "start a new line.\n\n"
                + "The text goes inside double quotes. The line ends with a "
                + "semicolon, which is how Java knows one instruction has "
                + "finished. Java does not care about line breaks; it cares "
                + "about semicolons.\n\n"
                + "Capitals matter. System exists. system does not.")
            .objectives(
                "Understand how a line is printed",
                "Recognise the exact spelling System.out.println",
                "Know why a semicolon is needed")
            .mistakes(
                "Writing system with a lowercase s",
                "Writing printLine instead of println",
                "Forgetting the semicolon")
            .task(new Task(Task.PREDICT,
                    "The login banner tool contains this. What does it print?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        System.out.println(\"ACCESS TERMINAL\");",
                    "    }",
                    "}")
                .accept("ACCESS TERMINAL")
                .hints(
                    "Look only at what is inside the double quotes.",
                    "Whatever is between the quotes is printed exactly as written.")
                .explain(
                    "The text between the quotes is printed exactly. Java adds "
                    + "nothing and changes nothing.")
                .xp(10))
            .task(new Task(Task.DEBUG,
                    "This tool will not compile. Which line number is wrong?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        system.out.println(\"LOGIN\");",
                    "    }",
                    "}")
                .accept("3", "line 3")
                .hints(
                    "Compare every word against the correct spelling you just saw.",
                    "One word starts with the wrong kind of letter.")
                .explain(
                    "Line 3 has system with a lowercase s. Java is case "
                    + "sensitive: System is a real class, system is nothing at "
                    + "all. The compiler reports 'cannot find symbol' and points "
                    + "at that word.")
                .xp(10))
            .task(new Task(Task.WRITE,
                    "Write the single line that prints    NORTHSTAR SECURE\n"
                    + "Type the whole statement, semicolon included.")
                .accept(
                    "System.out.println(\"NORTHSTAR SECURE\");",
                    "System.out.println(\"NORTHSTAR SECURE\")")
                .hints(
                    "Start with System.out.println and open a bracket.",
                    "The text goes in double quotes inside the brackets, then close "
                    + "the bracket and add a semicolon.")
                .explain(
                    "That is the instruction you will write more than any other "
                    + "in this game.")
                .xp(15))
            .task(new Task(Task.CHOICE,
                    "Why do security tools print a banner before the login prompt?")
                .choices(
                    "It makes the program run faster",
                    "It warns users that access is monitored, which matters legally",
                    "Java requires every program to print something first",
                    "It stops attackers connecting")
                .accept("2", "b")
                .hints(
                    "Think about what a banner is for, not what it does to the code.",
                    "In many countries, monitoring people without telling them causes "
                    + "legal problems.")
                .explain(
                    "A login banner is a legal notice. It tells anyone connecting "
                    + "that the system is private and monitored, which is what "
                    + "makes later monitoring defensible. It has no effect on the "
                    + "program at all.")
                .xp(10)));

        MISSIONS.add(new Mission("AUTH-002", "Holding a Value", "Authentication", 1)
            .java("Variables", "String", "int")
            .cyber("Credentials")
            .briefing(
                "A login tool has to remember the username someone typed long "
                + "enough to check it. Printing a value is not enough; you need "
                + "somewhere to keep it.")
            .teaching(
                "A variable is a named box that holds one value.\n\n"
                + "    String username = \"jsmith\";\n"
                + "    int failedLogins = 3;\n\n"
                + "Three parts every time. The TYPE says what kind of value it "
                + "holds. The NAME is how you reach it. The VALUE goes in after "
                + "the equals sign.\n\n"
                + "String holds text and needs double quotes. int holds whole "
                + "numbers and takes none.\n\n"
                + "The = does not mean 'equals'. It means 'put this value into "
                + "this box'.\n\n"
                + "You can join text and values with + when printing:\n"
                + "    System.out.println(\"USER: \" + username);")
            .objectives(
                "Declare a String and an int",
                "Understand type, name and value",
                "Join a variable onto text with +")
            .mistakes(
                "Putting quotes around a number meant for an int",
                "Forgetting the type when first declaring a variable",
                "Expecting + to add a space of its own")
            .task(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String user = \"contractor\";",
                    "System.out.println(\"USER: \" + user);")
                .accept("USER: contractor")
                .hints(
                    "The + joins the text on the left to the value in the variable.",
                    "The space after the colon is inside the quotes, so it is printed.")
                .explain(
                    "The variable's value is dropped in where the variable name "
                    + "appears. The space came from inside the quotes, because + "
                    + "never adds one of its own.")
                .xp(10))
            .task(new Task(Task.PREDICT,
                    "Careful with this one. What does it print?")
                .code(
                    "int failed = 3;",
                    "System.out.println(\"FAILED: \" + failed + 1);")
                .accept("FAILED: 31")
                .hints(
                    "Java works left to right. Work out what type each step produces.",
                    "Once one side of a + is text, the + joins instead of adding.")
                .explain(
                    "\"FAILED: \" + 3 makes the text \"FAILED: 3\". That result is "
                    + "still text, so + 1 joins a 1 onto the end rather than "
                    + "adding. You get FAILED: 31.\n\n"
                    + "To add first, use brackets: \"FAILED: \" + (failed + 1)")
                .xp(15))
            .task(new Task(Task.DEBUG,
                    "This will not compile. Which line number is wrong?")
                .code(
                    "String username = \"admin\";",
                    "int attempts = \"5\";",
                    "System.out.println(username + attempts);")
                .accept("2", "line 2")
                .hints(
                    "Check each value against the type of the box it is going into.",
                    "An int holds a number. What is on the right of the equals sign?")
                .explain(
                    "Line 2 puts \"5\" - text - into an int. The quotes make it a "
                    + "String, and a String does not fit in an int box. The "
                    + "compiler says 'incompatible types'. Remove the quotes.")
                .xp(15))
            .task(new Task(Task.WRITE,
                    "Declare a variable to hold the number of failed logins for an "
                    + "account, starting at 0.\n"
                    + "Name it failedLogins. Type the whole line.")
                .accept("int failedLogins = 0;", "int failedLogins = 0")
                .hints(
                    "A count of something is a whole number, so the type is int.",
                    "type name = value;  - with no quotes around a number.")
                .explain(
                    "Counters like this are the backbone of detection. Almost "
                    + "every rule you write later counts something and compares "
                    + "it to a limit.")
                .xp(15)));

        MISSIONS.add(new Mission("AUTH-003", "The Password Check", "Authentication", 2)
            .java("if / else", "String comparison", ".equals()")
            .cyber("Authentication", "Credential checking")
            .briefing(
                "Time to write the check itself. A user has typed a password and "
                + "you must decide whether it matches the stored one.\n\n"
                + "This mission contains the single most expensive mistake a "
                + "beginner makes in Java, and it happens to live exactly here, "
                + "in login code.")
            .teaching(
                "An if statement runs a block only when a test is true:\n\n"
                + "    if (attempts > 3) {\n"
                + "        System.out.println(\"LOCKED\");\n"
                + "    } else {\n"
                + "        System.out.println(\"OK\");\n"
                + "    }\n\n"
                + "Exactly one of those blocks runs.\n\n"
                + "For NUMBERS you compare with  ==  !=  <  >  <=  >=\n\n"
                + "For TEXT you must NOT use ==. On a String, == asks 'are these "
                + "the same object in memory', not 'do these hold the same "
                + "letters'. Use .equals() instead:\n\n"
                + "    if (typed.equals(stored)) { ... }\n\n"
                + "Why it matters so much: == sometimes appears to work, because "
                + "Java reuses identical text written directly in your code. The "
                + "moment the password arrives from a keyboard or a file instead, "
                + "it is a different object and == returns false. The bug passes "
                + "your testing and fails in production.")
            .objectives(
                "Write an if / else",
                "Compare text with .equals()",
                "Explain why == is wrong for passwords")
            .mistakes(
                "Using == to compare two Strings",
                "Putting a semicolon straight after if (...)",
                "Comparing a password with equalsIgnoreCase")
            .task(new Task(Task.PREDICT,
                    "Both variables hold the same letters. What does this print?")
                .code(
                    "String stored = new String(\"falcon\");",
                    "String typed = \"falcon\";",
                    "System.out.println(stored == typed);")
                .accept("false")
                .hints(
                    "== on objects does not compare the contents.",
                    "The word new forces Java to build a second, separate object.")
                .explain(
                    "The letters are identical, so a human says yes. == asks a "
                    + "different question - same object? - and the answer is no, "
                    + "because new built a second one. This is exactly what "
                    + "happens when a password arrives from a keyboard.")
                .xp(15))
            .task(new Task(Task.PREDICT,
                    "Same two variables. What does this print?")
                .code(
                    "String stored = new String(\"falcon\");",
                    "String typed = \"falcon\";",
                    "System.out.println(stored.equals(typed));")
                .accept("true")
                .hints(
                    ".equals() compares the characters.",
                    "The letters are the same in both.")
                .explain(
                    ".equals() asks the question you actually meant: do these "
                    + "hold the same letters? For a login check, this is the only "
                    + "correct comparison.")
                .xp(10))
            .task(new Task(Task.WRITE,
                    "Write the condition line of the login check.\n"
                    + "The stored password is in a variable called stored, and what "
                    + "the user typed is in typed.\n"
                    + "Type the whole line, starting with if and ending with {")
                .accept(
                    "if (stored.equals(typed)) {",
                    "if(stored.equals(typed)) {",
                    "if (stored.equals(typed)){",
                    "if(stored.equals(typed)){")
                .hints(
                    "The test goes inside round brackets, and the block opens with {",
                    "Call .equals() on one variable and pass the other into it.")
                .explain(
                    "Note there is no semicolon after the closing round bracket. "
                    + "Put one there and the if does nothing - its body becomes "
                    + "the empty semicolon, and the block below runs every time.")
                .xp(20))
            .task(new Task(Task.CHOICE,
                    "A colleague suggests using equalsIgnoreCase for the password "
                    + "so users are not locked out by caps lock. What is wrong "
                    + "with that?")
                .choices(
                    "Nothing, it is more user friendly",
                    "It is slower to run",
                    "It throws away 26 possible characters per position, making the "
                    + "password much faster to guess",
                    "It does not compile for passwords")
                .accept("3", "c")
                .hints(
                    "Think about how many possibilities an attacker has to try.",
                    "If case no longer matters, aBc and ABC and abc are all the same "
                    + "password.")
                .explain(
                    "Ignoring case is right for usernames and wrong for passwords. "
                    + "It collapses upper and lower case into one, cutting the "
                    + "number of possible passwords enormously and making "
                    + "brute-force guessing far cheaper.")
                .xp(15)));

        MISSIONS.add(new Mission("AUTH-004", "Counting Failures", "Authentication", 3)
            .java("while loop", "Counters", "++")
            .cyber("Brute force", "Account lockout")
            .briefing(
                "An account at NORTHSTAR has been hit repeatedly overnight. One "
                + "check is not enough - you need to walk through a series of "
                + "attempts and count how many failed.\n\n"
                + "Counting is what turns raw events into a detection.")
            .teaching(
                "A while loop repeats a block while its test stays true:\n\n"
                + "    int attempt = 1;\n"
                + "    while (attempt <= 5) {\n"
                + "        System.out.println(attempt);\n"
                + "        attempt++;\n"
                + "    }\n\n"
                + "Java checks the test, runs the block, checks again. When the "
                + "test is false it stops.\n\n"
                + "attempt++ means 'add one to attempt'. It is short for\n"
                + "attempt = attempt + 1.\n\n"
                + "Three things must be true or the loop is broken:\n"
                + "  1. the counter exists before the loop\n"
                + "  2. the test can become false\n"
                + "  3. something inside the block moves it towards false\n\n"
                + "Miss the third and the loop never ends. The program does not "
                + "crash; it simply runs for ever.")
            .objectives(
                "Write a while loop with a counter",
                "Keep a separate count of failures",
                "Understand why a loop can fail to stop")
            .mistakes(
                "Forgetting to increase the counter inside the loop",
                "Using < where <= was meant, losing the last value",
                "Putting the summary line inside the loop so it prints every time")
            .task(new Task(Task.PREDICT,
                    "How many lines does this print?")
                .code(
                    "int i = 1;",
                    "while (i <= 4) {",
                    "    System.out.println(\"ATTEMPT \" + i);",
                    "    i++;",
                    "}")
                .accept("4", "four")
                .hints(
                    "Count the values of i for which the test is still true.",
                    "i takes the values 1, 2, 3, 4 and then the test fails.")
                .explain(
                    "The test <= 4 includes 4 itself, so the block runs four "
                    + "times. Written as < 4 it would run three times. That one "
                    + "character is the most common off-by-one error there is.")
                .xp(15))
            .task(new Task(Task.DEBUG,
                    "This loop never stops. Which line number is missing something?")
                .code(
                    "int scan = 1;",
                    "while (scan <= 3) {",
                    "    System.out.println(\"SCANNING \" + scan);",
                    "}")
                .accept("3", "line 3", "4")
                .hints(
                    "Ask what changes between one run of the block and the next.",
                    "Nothing inside the loop touches scan, so the test never becomes "
                    + "false.")
                .explain(
                    "The block never changes scan, so scan <= 3 is true for ever. "
                    + "Adding scan++ inside the block fixes it. An endless loop "
                    + "gives no error and no crash - the only sign is output that "
                    + "will not stop.")
                .xp(20))
            .task(new Task(Task.PREDICT,
                    "This walks through six login attempts. Attempts 2, 3 and 5 "
                    + "failed. What is the final value printed?")
                .code(
                    "int attempt = 1;",
                    "int failures = 0;",
                    "while (attempt <= 6) {",
                    "    if (attempt == 2 || attempt == 3 || attempt == 5) {",
                    "        failures++;",
                    "    }",
                    "    attempt++;",
                    "}",
                    "System.out.println(failures);")
                .accept("3", "three")
                .hints(
                    "The counter only moves when the if is true.",
                    "Three attempt numbers are listed in that condition.")
                .explain(
                    "Two counters doing different jobs: attempt walks through "
                    + "every event, failures moves only when something is wrong. "
                    + "That second counter is what detection is built on.\n\n"
                    + "The || means 'or' - true when any one side is true.")
                .xp(20))
            .task(new Task(Task.WRITE,
                    "Write the line that adds one to a counter called failures.\n"
                    + "Use the short form.")
                .accept("failures++;", "failures++", "++failures;", "++failures")
                .hints(
                    "Two plus signs, straight after the variable name.",
                    "It is the short way of writing failures = failures + 1;")
                .explain(
                    "You will write this line in almost every detection rule you "
                    + "ever build.")
                .xp(10)));

        MISSIONS.add(new Mission("AUTH-005", "Lockout Policy", "Authentication", 3)
            .java("Boundary conditions", ">= vs >", "if / else if / else")
            .cyber("Account lockout policy", "Off-by-one defects")
            .briefing(
                "NORTHSTAR policy says an account locks after 5 failed logins. "
                + "The tool that enforces it was written in a hurry and has never "
                + "been tested at the value that matters.\n\n"
                + "An account that gets one more guess than policy allows is a "
                + "real defect. Attackers do not round down.")
            .teaching(
                "A threshold rule has a BOUNDARY - the value where its answer "
                + "changes.\n\n"
                + "    >= 5   means 5 counts as locked\n"
                + "    >  5   means 5 is still allowed, and 6 locks\n\n"
                + "Those are different rules, one attempt apart.\n\n"
                + "When you test a rule, the useful values are the ones around "
                + "the boundary: 4, 5 and 6. Testing 0 and 50 proves almost "
                + "nothing, because any roughly-correct rule handles them.\n\n"
                + "For more than two outcomes, chain the tests:\n\n"
                + "    if (failures >= 10) { ... }\n"
                + "    else if (failures >= 5) { ... }\n"
                + "    else { ... }\n\n"
                + "Java tries them in order and stops at the first true one, so "
                + "the highest threshold has to be tested first.")
            .objectives(
                "Tell >= and > apart at the boundary",
                "Choose test values that can expose an off-by-one",
                "Order an else-if chain correctly")
            .mistakes(
                "Testing only values far from the threshold",
                "Ordering an else-if chain from lowest to highest",
                "Reading 'after 5 failures' as > 5")
            .task(new Task(Task.PREDICT,
                    "Policy says lock at 5 or more. An account has exactly 5 "
                    + "failures. What does this print?")
                .code(
                    "int failures = 5;",
                    "if (failures > 5) {",
                    "    System.out.println(\"LOCKED\");",
                    "} else {",
                    "    System.out.println(\"OPEN\");",
                    "}")
                .accept("OPEN")
                .hints(
                    "Is 5 greater than 5?",
                    "The test is > not >=, so 5 does not satisfy it.")
                .explain(
                    "5 is not greater than 5, so the account stays open - against "
                    + "policy. The rule should be >= 5. This is a real defect "
                    + "giving an attacker a sixth guess, and it is invisible "
                    + "unless you test exactly 5.")
                .xp(20))
            .task(new Task(Task.CHOICE,
                    "Which set of test values would have caught that bug?")
                .choices(
                    "0, 1 and 2",
                    "10, 20 and 50",
                    "4, 5 and 6",
                    "0 and 100")
                .accept("3", "c")
                .hints(
                    "The threshold is 5. Which values sit around it?",
                    "You want values just below, exactly on, and just above.")
                .explain(
                    "4, 5 and 6 straddle the boundary. Under the correct rule you "
                    + "get OPEN, LOCKED, LOCKED; under the buggy one, OPEN, OPEN, "
                    + "LOCKED. The middle value is the one that tells them apart. "
                    + "Every other option gives the same answer either way.")
                .xp(20))
            .task(new Task(Task.DEBUG,
                    "This severity chain always reports the same thing for any "
                    + "count of 4 or more. Which line number is in the wrong "
                    + "place?")
                .code(
                    "if (failures >= 4) {",
                    "    System.out.println(\"WARNING\");",
                    "} else if (failures >= 10) {",
                    "    System.out.println(\"CRITICAL\");",
                    "} else {",
                    "    System.out.println(\"NORMAL\");",
                    "}")
                .accept("1", "line 1", "3", "line 3")
                .hints(
                    "Java stops at the first test that is true.",
                    "If 12 failures arrive, which test is reached first?")
                .explain(
                    "With 12 failures the first test (>= 4) is already true, so "
                    + "the chain stops and prints WARNING. The CRITICAL branch can "
                    + "never be reached. For thresholds that climb, always test "
                    + "the highest first.")
                .xp(20))
            .task(new Task(Task.WRITE,
                    "Write the condition that correctly locks an account at 5 or "
                    + "more failures. The count is in a variable called failures.\n"
                    + "Type the whole line, ending with {")
                .accept(
                    "if (failures >= 5) {",
                    "if(failures >= 5) {",
                    "if (failures >= 5){",
                    "if(failures >= 5){")
                .hints(
                    "'5 or more' includes 5 itself.",
                    "That is >= rather than >.")
                .explain(
                    "Read the policy wording, not the code, when choosing between "
                    + "> and >=. 'After 5 failures' and '5 or more failures' are "
                    + "different sentences and different rules.")
                .xp(15)));
    }

    // =====================================================================
    // CAMPAIGN 2 - LOG ANALYSIS
    // =====================================================================

    private static void buildLogs() {

        MISSIONS.add(new Mission("LOG-001", "Reading the Record", "Log Analysis", 3)
            .java("Classes", "Objects", "Fields", "new")
            .cyber("Structured logging")
            .briefing(
                "The authentication log arrives as plain text. Every question you "
                + "want to ask it - who failed, how often, from where - means "
                + "picking the same line apart again.\n\n"
                + "Do it once instead, and keep the pieces.")
            .teaching(
                "A class describes what something is like. An object is one "
                + "actual one, built from that description.\n\n"
                + "    class LogEvent {\n"
                + "        String user;\n"
                + "        String ip;\n"
                + "        boolean failed;\n"
                + "    }\n\n"
                + "That creates no events. It says what an event is like.\n\n"
                + "To build one, use new, then reach its parts with a dot:\n\n"
                + "    LogEvent e = new LogEvent();\n"
                + "    e.user = \"jsmith\";\n"
                + "    System.out.println(e.user);\n\n"
                + "The fields are called instance variables. A number field you "
                + "never set starts at 0, a boolean at false, and text at null.\n\n"
                + "Naming a variable is not making an object. Only new builds one.")
            .objectives(
                "Write a class with fields",
                "Build an object with new",
                "Read a field with the dot operator")
            .mistakes(
                "Declaring a variable without new, then using its fields",
                "Storing a true/false flag as the text \"false\"",
                "Expecting two variables to be separate objects after b = a")
            .task(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Rec { int count; boolean flag; }",
                    "",
                    "Rec r = new Rec();",
                    "System.out.println(r.count + \" \" + r.flag);")
                .accept("0 false")
                .hints(
                    "Neither field was given a value. Fields are not left empty.",
                    "Number fields start at 0 and booleans start at false.")
                .explain(
                    "Fields inside an object get starting values automatically: 0 "
                    + "for numbers, false for booleans, null for text. Loose "
                    + "variables inside a method do not - Java refuses to compile "
                    + "if you read one of those before setting it.")
                .xp(15))
            .task(new Task(Task.DEBUG,
                    "This will not compile. Which line number is the problem?")
                .code(
                    "class Host { String name; }",
                    "",
                    "Host h;",
                    "h.name = \"WEB-01\";",
                    "System.out.println(h.name);")
                .accept("3", "line 3")
                .hints(
                    "Read line 3 carefully. It names a Host. Does it make one?",
                    "There is no new anywhere in this code.")
                .explain(
                    "Host h; creates a name for a Host, not a Host. There is "
                    + "nothing for the dot to reach into. It needs "
                    + "Host h = new Host();  - the new is what actually builds "
                    + "the object.")
                .xp(20))
            .task(new Task(Task.PREDICT,
                    "Careful. What does this print?")
                .code(
                    "class Rec { int score; }",
                    "",
                    "Rec a = new Rec();",
                    "Rec b = a;",
                    "b.score = 9;",
                    "System.out.println(a.score);")
                .accept("9", "nine")
                .hints(
                    "Count how many times new appears.",
                    "b = a does not build anything. Both names end up reaching the "
                    + "same object.")
                .explain(
                    "There was only ever one object. b = a copied the way to reach "
                    + "it, not the thing itself, so a change through b is visible "
                    + "through a. Two separate objects need two calls to new.\n\n"
                    + "Nothing warns you about this. It compiles, runs, and "
                    + "quietly reports the wrong thing.")
                .xp(25))
            .task(new Task(Task.WRITE,
                    "Write the line that builds a LogEvent object and stores it in "
                    + "a variable called event.\n"
                    + "Type the whole line.")
                .accept("LogEvent event = new LogEvent();", "LogEvent event = new LogEvent()")
                .hints(
                    "The shape is:  Type name = new Type();",
                    "The type appears twice - once for the variable, once after new.")
                .explain(
                    "LogEvent is the class you will build the rest of the log "
                    + "tooling on.")
                .xp(15)));

        MISSIONS.add(new Mission("LOG-002", "Rules That Travel", "Log Analysis", 4)
            .java("Methods", "void", "Calling a method")
            .cyber("Consistent rule enforcement")
            .briefing(
                "You now have log events as objects. But the rule that judges them "
                + "has been copied into three different tools, and one copy has "
                + "already drifted.\n\n"
                + "A security rule that exists in three places is three rules.")
            .teaching(
                "A method is a named piece of behaviour. Put it inside the class, "
                + "beside the fields:\n\n"
                + "    class LogEvent {\n"
                + "        String user;\n"
                + "        boolean failed;\n"
                + "\n"
                + "        void report() {\n"
                + "            System.out.println(user + \" failed=\" + failed);\n"
                + "        }\n"
                + "    }\n\n"
                + "Two things to notice.\n\n"
                + "Inside the class, a field is used by name with no dot. The "
                + "method belongs to an object, so it can see that object's own "
                + "fields directly.\n\n"
                + "You call it on an object, with a dot and brackets:\n"
                + "    event.report();\n\n"
                + "One method, written once, gives a different answer for every "
                + "object, because each object holds different values.\n\n"
                + "void means the method hands nothing back - it does something "
                + "rather than answering something.")
            .objectives(
                "Write a method inside a class",
                "Call it on an object",
                "Explain why one method beats three copies of a rule")
            .mistakes(
                "Writing a method inside main",
                "Calling a method without the brackets",
                "Copying a rule instead of calling it")
            .task(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Rec {",
                    "    String name;",
                    "    void show() { System.out.println(name); }",
                    "}",
                    "",
                    "Rec a = new Rec(); a.name = \"WEB-01\";",
                    "Rec b = new Rec(); b.name = \"DB-01\";",
                    "a.show();",
                    "b.show();")
                .accept("WEB-01\nDB-01", "WEB-01 DB-01", "web-01 db-01")
                .hints(
                    "The method reads the field of whichever object it is called on.",
                    "Two objects, two calls, two different names.")
                .explain(
                    "One method written once, two different results. That is the "
                    + "whole value of putting behaviour in the class: the code has "
                    + "one place to be wrong and one place to fix.")
                .xp(20))
            .task(new Task(Task.DEBUG,
                    "This will not compile. Which line number is in the wrong place?")
                .code(
                    "class Host { String name; }",
                    "",
                    "public static void main(String[] args) {",
                    "    Host h = new Host();",
                    "    void check() { System.out.println(name); }",
                    "    h.check();",
                    "}")
                .accept("5", "line 5")
                .hints(
                    "Where is the method written? Where should a method live?",
                    "Java has no methods inside methods.")
                .explain(
                    "Line 5 defines a method inside main. Java has no such thing - "
                    + "every method belongs to a class, as a sibling of the fields. "
                    + "There is a second reason too: inside main, the word name "
                    + "means nothing, because main does not belong to a Host.")
                .xp(20))
            .task(new Task(Task.CHOICE,
                    "The lockout rule has been copied into three tools. The "
                    + "threshold changes from 5 to 3. What is the real risk?")
                .choices(
                    "The program will run more slowly",
                    "One copy gets missed, so two accounts with the same history are "
                    + "treated differently",
                    "Java will refuse to compile duplicated logic",
                    "There is no real risk, copies are fine")
                .accept("2", "b")
                .hints(
                    "Think about what happens six months later when someone edits "
                    + "one file.",
                    "Nothing in Java forces the three copies to stay the same.")
                .explain(
                    "Duplicated rules drift. Someone updates two of the three, and "
                    + "from then on one tool enforces a different policy from the "
                    + "others. Nobody notices, because everything still runs. "
                    + "Writing the rule once, in a method, removes the possibility.")
                .xp(20))
            .task(new Task(Task.WRITE,
                    "Write the line that calls a method named report() on an object "
                    + "held in a variable called event.")
                .accept("event.report();", "event.report()")
                .hints(
                    "Object name, a dot, the method name, then brackets.",
                    "The brackets are not optional - without them it is not a call.")
                .explain(
                    "event.report and event.report() are different things. The "
                    + "first names the method; only the second runs it.")
                .xp(10)));

        MISSIONS.add(new Mission("LOG-003", "Same Source, Again and Again", "Log Analysis", 4)
            .java("Arrays", "Array length", "Looping an array")
            .cyber("Brute-force detection")
            .briefing(
                "Overnight, 40 failed logins hit one account. You cannot declare "
                + "forty variables.\n\n"
                + "An array holds many values of the same type under one name, and "
                + "a loop walks through them.")
            .teaching(
                "An array holds a fixed number of values of one type:\n\n"
                + "    String[] users = {\"jsmith\", \"admin\", \"jsmith\"};\n\n"
                + "You reach each one by its POSITION, and positions start at 0:\n\n"
                + "    users[0]   is \"jsmith\"\n"
                + "    users[2]   is \"jsmith\"\n\n"
                + "users.length is how many there are - 3 here. Note it is length "
                + "with no brackets.\n\n"
                + "The last valid position is always length - 1. Asking for "
                + "users[3] on a three-item array throws "
                + "ArrayIndexOutOfBoundsException and stops the program.\n\n"
                + "To walk through it:\n\n"
                + "    int i = 0;\n"
                + "    while (i < users.length) {\n"
                + "        System.out.println(users[i]);\n"
                + "        i++;\n"
                + "    }\n\n"
                + "Note the test is < length, not <= length. Using <= runs one "
                + "step too far and throws.")
            .objectives(
                "Build an array and read a position",
                "Use .length correctly",
                "Loop through every element without running off the end")
            .mistakes(
                "Thinking the first position is 1",
                "Writing <= length instead of < length",
                "Writing length() with brackets, which is the String method")
            .task(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] hosts = {\"WEB-01\", \"DB-01\", \"FILE-01\"};",
                    "System.out.println(hosts[1]);")
                .accept("DB-01", "db-01")
                .hints(
                    "Positions start at 0, not 1.",
                    "hosts[0] is WEB-01, so what is hosts[1]?")
                .explain(
                    "Counting from 0 is the source of endless off-by-one errors. "
                    + "Position 1 is the SECOND item.")
                .xp(15))
            .task(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String[] hosts = {\"WEB-01\", \"DB-01\", \"FILE-01\"};",
                    "System.out.println(hosts.length);")
                .accept("3", "three")
                .hints(
                    "length is how many items there are, not the last position.",
                    "Count the items between the braces.")
                .explain(
                    "There are 3 items, so length is 3 - but the valid positions "
                    + "are 0, 1 and 2. The last position is always length - 1.")
                .xp(10))
            .task(new Task(Task.DEBUG,
                    "This crashes with ArrayIndexOutOfBoundsException. Which line "
                    + "number is wrong?")
                .code(
                    "String[] users = {\"jsmith\", \"admin\", \"contractor\"};",
                    "int i = 0;",
                    "while (i <= users.length) {",
                    "    System.out.println(users[i]);",
                    "    i++;",
                    "}")
                .accept("3", "line 3")
                .hints(
                    "How far does the loop let i go? What is the last valid "
                    + "position?",
                    "length is 3, so the last valid position is 2 - but the test "
                    + "allows i to reach 3.")
                .explain(
                    "<= length lets i reach 3, and users[3] does not exist on a "
                    + "three-item array. The test must be < users.length.\n\n"
                    + "Unlike a compile error, this one only appears when the code "
                    + "runs, and only if the loop gets that far.")
                .xp(25))
            .task(new Task(Task.PREDICT,
                    "The array holds the result of each login attempt. How many "
                    + "failures does this count?")
                .code(
                    "String[] results = {\"FAIL\", \"FAIL\", \"OK\", \"FAIL\", \"OK\"};",
                    "int failures = 0;",
                    "int i = 0;",
                    "while (i < results.length) {",
                    "    if (results[i].equals(\"FAIL\")) {",
                    "        failures++;",
                    "    }",
                    "    i++;",
                    "}",
                    "System.out.println(failures);")
                .accept("3", "three")
                .hints(
                    "Count the FAIL entries in the array.",
                    "The if only fires on the ones that match.")
                .explain(
                    "That is a real detection in ten lines: walk the events, count "
                    + "the ones that matter, report the number.\n\n"
                    + "Note .equals() again rather than ==. These values came from "
                    + "an array, and comparing them with == is the same bug as in "
                    + "the password check.")
                .xp(25)));

        MISSIONS.add(new Mission("LOG-004", "The Watchlist", "Log Analysis", 5)
            .java("ArrayList", "add", "size", "contains")
            .cyber("Block lists", "Indicator tracking")
            .briefing(
                "An array has a fixed size, decided the moment you build it. A "
                + "watchlist of suspicious addresses grows all day.\n\n"
                + "You need a container that does not need its final size known "
                + "in advance.")
            .teaching(
                "An ArrayList is a list that grows as you add to it:\n\n"
                + "    import java.util.ArrayList;\n"
                + "\n"
                + "    ArrayList<String> watchlist = new ArrayList<>();\n"
                + "    watchlist.add(\"10.14.22.9\");\n\n"
                + "The <String> says what it holds. Put anything else in and the "
                + "code will not compile - which is the point.\n\n"
                + "The methods you need most:\n\n"
                + "    list.add(x)        put x on the end\n"
                + "    list.size()        how many are in it\n"
                + "    list.get(0)        the item at a position\n"
                + "    list.contains(x)   is x already in it? true or false\n\n"
                + "Two differences from arrays worth remembering. It is size() "
                + "with brackets, not length. And contains() compares with "
                + ".equals(), so it does the right thing for text without you "
                + "having to ask.")
            .objectives(
                "Create an ArrayList and add to it",
                "Use size() and contains()",
                "Say when a list beats an array")
            .mistakes(
                "Writing .length on an ArrayList",
                "Forgetting the import",
                "Adding a duplicate without checking contains() first")
            .task(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "ArrayList<String> blocked = new ArrayList<>();",
                    "blocked.add(\"10.14.22.9\");",
                    "blocked.add(\"192.168.0.44\");",
                    "System.out.println(blocked.size());")
                .accept("2", "two")
                .hints(
                    "size() is how many items have been added.",
                    "Count the add calls.")
                .explain(
                    "The list started empty and grew to 2. No size was decided in "
                    + "advance, which is exactly what an array cannot do.")
                .xp(15))
            .task(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "ArrayList<String> blocked = new ArrayList<>();",
                    "blocked.add(\"10.14.22.9\");",
                    "System.out.println(blocked.contains(\"10.14.22.9\"));")
                .accept("true")
                .hints(
                    "contains() answers true or false.",
                    "It compares using .equals(), so matching text counts as found.")
                .explain(
                    "contains() compares with .equals() internally, so it does the "
                    + "right thing for text automatically. This is the check you "
                    + "run before adding, to avoid duplicates on a block list.")
                .xp(15))
            .task(new Task(Task.DEBUG,
                    "This will not compile. Which line number is wrong?")
                .code(
                    "ArrayList<String> ips = new ArrayList<>();",
                    "ips.add(\"10.14.22.9\");",
                    "System.out.println(ips.length);")
                .accept("3", "line 3")
                .hints(
                    "Arrays and lists do not count themselves the same way.",
                    "An ArrayList uses size(), with brackets.")
                .explain(
                    "length is for arrays. An ArrayList uses size(). Java also has "
                    + "length() with brackets for Strings, so there are three "
                    + "different spellings for 'how big is this' and mixing them "
                    + "up is normal for a long time.")
                .xp(20))
            .task(new Task(Task.CHOICE,
                    "You are tracking addresses that fail authentication, all day, "
                    + "with no idea how many there will be. Array or ArrayList?")
                .choices(
                    "Array, because it is faster",
                    "ArrayList, because the final count is not known in advance",
                    "Array, because addresses are text",
                    "Neither, use separate variables")
                .accept("2", "b")
                .hints(
                    "An array needs its size decided when it is created.",
                    "Do you know this morning how many addresses will misbehave today?")
                .explain(
                    "You cannot size an array for data that has not arrived yet. "
                    + "Guess too small and it overflows; guess too large and most "
                    + "of it is empty. A list that grows is the right shape for a "
                    + "watchlist.")
                .xp(20)));
    }

    // =====================================================================
    // CAMPAIGN 3 - ACCESS CONTROL
    // =====================================================================

    private static void buildAccessControl() {

        MISSIONS.add(new Mission("ACC-001", "Least Privilege", "Access Control", 4)
            .java("boolean", "Logical operators", "&& and ||")
            .cyber("Authorisation", "Least privilege")
            .briefing(
                "Authentication asks who you are. Authorisation asks what you are "
                + "allowed to do. They are different questions and people confuse "
                + "them constantly.\n\n"
                + "NORTHSTAR's file server needs a rule for who may open payroll "
                + "records.")
            .teaching(
                "A boolean holds true or false. Combine them with:\n\n"
                + "    &&   AND - true only when BOTH sides are true\n"
                + "    ||   OR  - true when AT LEAST ONE side is true\n"
                + "    !    NOT - flips true to false and back\n\n"
                + "    if (isStaff && hasClearance) { ... }\n\n"
                + "Each side has to be a complete test of its own. "
                + "level >= 3 && <= 7 is not valid Java; write "
                + "level >= 3 && level <= 7.\n\n"
                + "Translating a written policy into && and || is where most real "
                + "access-control bugs begin, because English is vague and code "
                + "is not. 'Managers and auditors may view payroll' means a person "
                + "who is EITHER - that is ||. Nobody is both at once, so && would "
                + "let nobody in.\n\n"
                + "The test that saves you: describe one person who should pass, "
                + "then check your condition against them.")
            .objectives(
                "Combine conditions with && and ||",
                "Translate a written policy into a condition",
                "Apply least privilege")
            .mistakes(
                "Using && where the policy means either",
                "Writing x >= 3 && <= 7",
                "Granting access by default when no rule matches")
            .task(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "boolean isStaff = true;",
                    "boolean hasClearance = false;",
                    "System.out.println(isStaff && hasClearance);")
                .accept("false")
                .hints(
                    "&& needs both sides to be true.",
                    "One of them is false.")
                .explain(
                    "&& fails as soon as any side fails. For access control that "
                    + "is the safe direction: missing one requirement means no "
                    + "access.")
                .xp(15))
            .task(new Task(Task.CHOICE,
                    "Policy: 'Managers and auditors may view payroll.' Which "
                    + "condition is correct?")
                .choices(
                    "if (isManager && isAuditor)",
                    "if (isManager || isAuditor)",
                    "if (isManager && !isAuditor)",
                    "if (!isManager || !isAuditor)")
                .accept("2", "b")
                .hints(
                    "Picture one manager who is not an auditor. Should they get in?",
                    "The policy lists two kinds of people who qualify, not one person "
                    + "who must be both.")
                .explain(
                    "The English word 'and' is describing two groups, not two "
                    + "requirements. A manager qualifies. An auditor qualifies. "
                    + "That is ||.\n\n"
                    + "Written as &&, only a person who is somehow both would get "
                    + "access - which in practice is nobody, and the tickets start "
                    + "arriving that afternoon.")
                .xp(25))
            .task(new Task(Task.DEBUG,
                    "This is meant to allow clearance levels 3 to 7 inclusive. It "
                    + "will not compile. Which line is wrong?")
                .code(
                    "int level = 5;",
                    "if (level >= 3 && <= 7) {",
                    "    System.out.println(\"GRANTED\");",
                    "}")
                .accept("2", "line 2")
                .hints(
                    "Read the right-hand side of the && on its own. Is it a complete "
                    + "test?",
                    "<= 7 does not say what is being compared.")
                .explain(
                    "Each side of && must be a whole condition. It has to be "
                    + "level >= 3 && level <= 7. The variable cannot be left "
                    + "implied - English allows that shorthand and Java does not.")
                .xp(20))
            .task(new Task(Task.CHOICE,
                    "An access check runs through its rules, none of them match, "
                    + "and the code reaches the end. What should happen?")
                .choices(
                    "Grant access, since no rule forbade it",
                    "Deny access, since no rule permitted it",
                    "Grant access and write a log entry",
                    "Ask the user to try again")
                .accept("2", "b")
                .hints(
                    "One of these is called 'fail open' and the other 'fail closed'.",
                    "Which one is safe when you have forgotten a case?")
                .explain(
                    "Deny by default - fail closed. An unmatched case means you did "
                    + "not think of that situation, and the safe answer to "
                    + "something you did not think about is no.\n\n"
                    + "Systems that grant by default hand out access every time "
                    + "someone forgets a branch.")
                .xp(25)));

        MISSIONS.add(new Mission("ACC-002", "Hiding the Password", "Access Control", 5)
            .java("private", "Encapsulation", "Getters", "Methods that return")
            .cyber("Encapsulation as a control", "Credential handling")
            .briefing(
                "A code review at NORTHSTAR found the Account class exposes its "
                + "password field to anything that holds an account object.\n\n"
                + "Encapsulation is usually taught as tidiness. It is a security "
                + "control.")
            .teaching(
                "A field marked private cannot be touched from outside its class:\n\n"
                + "    class Account {\n"
                + "        private String password;\n"
                + "\n"
                + "        boolean passwordMatches(String typed) {\n"
                + "            return password.equals(typed);\n"
                + "        }\n"
                + "    }\n\n"
                + "Outside code can no longer read account.password at all. It can "
                + "only ASK whether a guess is right.\n\n"
                + "Notice what that changes. The class no longer hands out the "
                + "secret; it answers a question about it. Nothing outside can "
                + "print it, log it by accident, or compare it the wrong way.\n\n"
                + "boolean in front of the method name is its RETURN TYPE - a "
                + "promise to hand back a true or false. return sends the value "
                + "back and ends the method immediately.\n\n"
                + "A method that reads a private field for you is called a getter. "
                + "Some fields should have one. A password should not.")
            .objectives(
                "Make a field private",
                "Write a method that returns a boolean",
                "Explain why a password needs no getter")
            .mistakes(
                "Adding a getter for every private field out of habit",
                "Returning the password instead of the answer",
                "Forgetting that private means class-level, not object-level")
            .task(new Task(Task.PREDICT,
                    "What does this method return when typed is \"falcon\" and the "
                    + "stored password is \"falcon\"?")
                .code(
                    "private String password;",
                    "",
                    "boolean passwordMatches(String typed) {",
                    "    return password.equals(typed);",
                    "}")
                .accept("true")
                .hints(
                    ".equals() compares the letters and produces true or false.",
                    "That value is handed straight back by return.")
                .explain(
                    "The method answers a question without revealing anything. The "
                    + "caller learns whether the guess was right, and never sees "
                    + "the password itself.")
                .xp(20))
            .task(new Task(Task.CHOICE,
                    "Why is it wrong to add a getPassword() method to this class?")
                .choices(
                    "It is slower than comparing directly",
                    "It puts the secret back within reach of any code holding the "
                    + "object, including logging",
                    "Java does not allow getters for Strings",
                    "It is fine, every private field should have a getter")
                .accept("2", "b")
                .hints(
                    "What was private protecting against, and does a getter undo it?",
                    "Think about where passwords most often leak from in real "
                    + "systems.")
                .explain(
                    "A getter reopens exactly what private closed. Once any code "
                    + "can obtain the password, it can end up in a log file, an "
                    + "error message or a debug print - which is how credentials "
                    + "leak in practice far more often than through clever "
                    + "attacks.\n\n"
                    + "'Every field gets a getter' is a habit, not a rule.")
                .xp(25))
            .task(new Task(Task.DEBUG,
                    "This will not compile. Which line number causes the error?")
                .code(
                    "class Account {",
                    "    private String password;",
                    "}",
                    "",
                    "Account a = new Account();",
                    "System.out.println(a.password);")
                .accept("6", "line 6")
                .hints(
                    "private means the field can only be used inside its own class.",
                    "Line 6 is outside the Account class.")
                .explain(
                    "The compiler reports 'password has private access in Account'. "
                    + "That error is the control doing its job - the leak is stopped "
                    + "before the program ever runs.")
                .xp(20))
            .task(new Task(Task.WRITE,
                    "Write the field declaration for a password that cannot be read "
                    + "from outside the class. Name it password.\n"
                    + "Type the whole line.")
                .accept("private String password;", "private String password")
                .hints(
                    "The keyword goes before the type.",
                    "private, then the type, then the name.")
                .explain(
                    "One keyword, and an entire category of accidental leak becomes "
                    + "a compile error instead of an incident.")
                .xp(15)));

        MISSIONS.add(new Mission("ACC-003", "Never Trust the Input", "Access Control", 5)
            .java("Exceptions", "try / catch", "Integer.parseInt")
            .cyber("Input validation", "Denial of service")
            .briefing(
                "The log parser died at 03:00. One malformed line in a file of "
                + "80,000 brought down the whole overnight run, and the morning "
                + "shift had no report.\n\n"
                + "A parser that crashes on bad input is not just fragile. It is a "
                + "denial of service waiting for anyone who can get a line into "
                + "your log.")
            .teaching(
                "Some operations fail at run time. Turning text into a number is "
                + "the classic one:\n\n"
                + "    int port = Integer.parseInt(\"443\");    works, gives 443\n"
                + "    int port = Integer.parseInt(\"44x\");    throws\n\n"
                + "A throw stops the program unless you catch it:\n\n"
                + "    try {\n"
                + "        int port = Integer.parseInt(text);\n"
                + "        System.out.println(\"PORT \" + port);\n"
                + "    } catch (NumberFormatException e) {\n"
                + "        System.out.println(\"SKIPPING BAD LINE\");\n"
                + "    }\n\n"
                + "Java runs the try block. If nothing goes wrong, the catch is "
                + "skipped entirely. If the parse throws, everything after it in "
                + "the try is abandoned and the catch runs instead.\n\n"
                + "The type in the brackets says which failure you are prepared "
                + "for. Catching one specific thing you understand is right; "
                + "catching everything and carrying on regardless hides real "
                + "problems.\n\n"
                + "Never assume input is well formed. Not from a user, not from a "
                + "file, not from another system.")
            .objectives(
                "Recognise what makes parseInt throw",
                "Write try / catch around risky work",
                "Explain why a crashing parser is a security problem")
            .mistakes(
                "Assuming every line of a log file is well formed",
                "Catching Exception and doing nothing with it",
                "Putting the whole program inside one giant try block")
            .task(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "try {",
                    "    int port = Integer.parseInt(\"8080\");",
                    "    System.out.println(\"PORT \" + port);",
                    "} catch (NumberFormatException e) {",
                    "    System.out.println(\"BAD\");",
                    "}")
                .accept("PORT 8080")
                .hints(
                    "Is \"8080\" a valid whole number?",
                    "If nothing throws, the catch block never runs.")
                .explain(
                    "The parse succeeded, so the try block finished normally and "
                    + "the catch was skipped. A catch block costs nothing when "
                    + "nothing goes wrong.")
                .xp(15))
            .task(new Task(Task.PREDICT,
                    "The log line is damaged. What does this print?")
                .code(
                    "try {",
                    "    int port = Integer.parseInt(\"80x0\");",
                    "    System.out.println(\"PORT \" + port);",
                    "} catch (NumberFormatException e) {",
                    "    System.out.println(\"SKIPPING BAD LINE\");",
                    "}")
                .accept("SKIPPING BAD LINE")
                .hints(
                    "80x0 is not a number, so parseInt throws.",
                    "When it throws, the rest of the try is abandoned.")
                .explain(
                    "The println on line 3 never ran. Once something throws, "
                    + "everything after it inside the try is skipped and control "
                    + "jumps straight to the catch.\n\n"
                    + "That is the difference between skipping one bad line and "
                    + "losing the whole overnight run.")
                .xp(20))
            .task(new Task(Task.CHOICE,
                    "Why is a parser that crashes on malformed input a security "
                    + "problem, not just a bug?")
                .choices(
                    "It runs slower than a parser that does not crash",
                    "Anyone who can get one bad line into the log can stop the "
                    + "monitoring, hiding whatever they do next",
                    "Crashes always mean memory corruption",
                    "It is only a bug, not a security problem")
                .accept("2", "b")
                .hints(
                    "Who gets to write lines into an authentication log?",
                    "What is not being watched while the analyser is down?")
                .explain(
                    "Failed logins are written to the log by whoever attempts them. "
                    + "If a crafted username can kill the parser, an attacker can "
                    + "switch off your detection and then work unobserved.\n\n"
                    + "Availability is one of the three parts of the CIA triad, and "
                    + "this is an attack on it.")
                .xp(25))
            .task(new Task(Task.WRITE,
                    "Write the first line of a catch block that handles the failure "
                    + "Integer.parseInt produces. Call the variable e.\n"
                    + "Type the whole line, ending with {")
                .accept(
                    "} catch (NumberFormatException e) {",
                    "catch (NumberFormatException e) {",
                    "} catch(NumberFormatException e) {",
                    "catch(NumberFormatException e) {")
                .hints(
                    "The exception parseInt throws is called NumberFormatException.",
                    "catch (TypeName variableName) {")
                .explain(
                    "Naming the specific exception means you are handling a failure "
                    + "you understand. Catching Exception instead would also "
                    + "swallow bugs you have not thought about, which is how "
                    + "problems get hidden rather than fixed.")
                .xp(20)));
    }

    // =====================================================================
    // BOSS MISSION
    // =====================================================================

    private static void buildBoss() {

        MISSIONS.add(new Mission("BOSS-001", "Compromised Account Investigation", "Boss", 7)
            .java("Everything so far")
            .cyber("Incident investigation", "Reporting")
            .briefing(
                "03:14. An alert fires on NORTHSTAR's domain controller.\n\n"
                + "The account SVC-BACKUP, a service account that should only ever "
                + "log in from FILE-01, has authenticated from an address nobody "
                + "recognises. You have the log extract and the inventory.\n\n"
                + "This mission uses everything from the three campaigns. No new "
                + "Java. Work it like an investigation.")
            .teaching(
                "No new concepts. You will need:\n\n"
                + "  - counting with a loop\n"
                + "  - comparing text with .equals()\n"
                + "  - thresholds and the >= boundary\n"
                + "  - arrays and their positions\n"
                + "  - try / catch around anything that parses\n"
                + "  - deny by default\n\n"
                + "The log extract for SVC-BACKUP:\n\n"
                + "  1  03:02  FAIL  ip=45.12.9.7\n"
                + "  2  03:04  FAIL  ip=45.12.9.7\n"
                + "  3  03:07  FAIL  ip=45.12.9.7\n"
                + "  4  03:09  FAIL  ip=45.12.9.7\n"
                + "  5  03:11  FAIL  ip=45.12.9.7\n"
                + "  6  03:14  OK    ip=45.12.9.7\n\n"
                + "Expected source for SVC-BACKUP: 10.0.4.11 (FILE-01)")
            .objectives(
                "Count the failed attempts",
                "Apply the lockout policy correctly",
                "Decide whether the account is compromised",
                "Report it accurately")
            .mistakes(
                "Reading the successful login as reassuring",
                "Applying the threshold with > instead of >=",
                "Reporting a conclusion the evidence does not support")
            .task(new Task(Task.RECALL,
                    "How many FAILED attempts are in the extract?")
                .accept("5", "five")
                .hints(
                    "Look at the result column only.",
                    "Count the lines marked FAIL, not the total lines.")
                .explain(
                    "Five failures, then a success. The count is the first fact of "
                    + "any brute-force finding.")
                .xp(20))
            .task(new Task(Task.CHOICE,
                    "NORTHSTAR policy locks an account at 5 or more failures. "
                    + "Should this account have locked before the successful login "
                    + "at 03:14?")
                .choices(
                    "No, 5 is below the threshold",
                    "Yes, the fifth failure met the threshold of 5 or more",
                    "No, lockout only applies to human accounts",
                    "There is not enough information")
                .accept("2", "b")
                .hints(
                    "'5 or more' - does 5 itself qualify?",
                    "The threshold is >= 5, and the fifth failure hit it at 03:11.")
                .explain(
                    "The fifth failure at 03:11 met the policy exactly. The account "
                    + "should have been locked, and the login at 03:14 should never "
                    + "have been possible.\n\n"
                    + "That means you have found two things: an attack, and a "
                    + "lockout control that is not working. The second is arguably "
                    + "the more serious finding.")
                .xp(30))
            .task(new Task(Task.CHOICE,
                    "The login at 03:14 succeeded. What does that tell you?")
                .choices(
                    "The problem resolved itself, no action needed",
                    "The earlier failures were the user mistyping their password",
                    "The guessing worked - the account is very likely compromised",
                    "The log is corrupted")
                .accept("3", "c")
                .hints(
                    "Five failures then a success from an unrecognised address, at "
                    + "three in the morning.",
                    "A service account does not mistype anything. It has no fingers.")
                .explain(
                    "Five failures followed by a success is the signature of "
                    + "successful guessing. For a SERVICE account it is worse: a "
                    + "service account's password is in a configuration file, so it "
                    + "never fails and never has typing errors.\n\n"
                    + "Five failures from a service account is already an "
                    + "impossibility. The success turns an anomaly into an "
                    + "incident.")
                .xp(30))
            .task(new Task(Task.CHOICE,
                    "SVC-BACKUP should only authenticate from 10.0.4.11. It came "
                    + "from 45.12.9.7. Which Java comparison belongs in the check "
                    + "that would have caught this?")
                .choices(
                    "if (sourceIp == expectedIp)",
                    "if (sourceIp.equals(expectedIp))",
                    "if (sourceIp.equalsIgnoreCase(expectedIp))",
                    "if (sourceIp > expectedIp)")
                .accept("2", "b")
                .hints(
                    "The addresses are text, and they arrived from a log file.",
                    "Text read from a file is a separate object, so == compares the "
                    + "wrong thing.")
                .explain(
                    ".equals() is the only correct answer. == would compare object "
                    + "identity on text that came from a file, and would report a "
                    + "mismatch even for the legitimate address - an alert on every "
                    + "single login, which gets the rule switched off within a "
                    + "week.\n\n"
                    + "equalsIgnoreCase adds nothing for numeric addresses, and > "
                    + "does not compile on Strings at all.")
                .xp(30))
            .task(new Task(Task.CHOICE,
                    "Write the finding. Which is the accurate one?")
                .choices(
                    "SVC-BACKUP was attacked but the attack failed",
                    "SVC-BACKUP shows 5 failed logins then a success from an "
                    + "unexpected address; treat as compromised, and the lockout "
                    + "control did not fire",
                    "A user forgot their password overnight",
                    "The log shows suspicious activity requiring further "
                    + "investigation")
                .accept("2", "b")
                .hints(
                    "A good finding states the evidence, the conclusion, and "
                    + "anything else broken that you noticed.",
                    "One option is vague enough to be useless; another contradicts "
                    + "the evidence.")
                .explain(
                    "That finding gives the evidence, the conclusion and the second "
                    + "defect, which is what lets someone act without re-reading "
                    + "the log themselves.\n\n"
                    + "'Requires further investigation' says nothing at 3am. 'The "
                    + "attack failed' contradicts the 03:14 success. Precision in "
                    + "a report is a technical skill, and it is the one most junior "
                    + "analysts are weakest at.\n\n"
                    + "Campaign complete. You have taken raw log text to a defended "
                    + "conclusion.")
                .xp(40)));
    }
}
