/**
 * CAMPAIGN 01 - JAVA ZERO
 * Java from zero. Thirty missions.
 *
 * Campaign 00 produced fixed text. Nothing could be remembered and nothing
 * could change. This campaign gives programs memory: variables, the types
 * they come in, and the operators that work on them.
 *
 * Still no conditions and no loops - those are Campaigns 02 and 03. A mission
 * here may only use printing, comments and whatever this campaign has already
 * introduced.
 */
public class Campaign01 {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(1), "Somewhere to Put a Number", 1)
            .brief(
                "The account audit tool prints the number of failed logins by "
                + "having the number typed into the text. When the count changes, "
                + "somebody has to edit the program.\n\n"
                + "A program that cannot remember a value cannot do anything "
                + "useful with it. This is where that changes.")
            .willLearn("Variables", "int", "Declaring and assigning")
            .whyUseful(
                "Every security tool holds values while it works: counts, "
                + "thresholds, names, addresses. A variable is the box that holds "
                + "one of them, and everything from here is built on it.")
            .concept("Variables and int",
                "A VARIABLE is a named box holding one value.\n"
                + "\n"
                + "Creating one takes three things:\n"
                + "\n"
                + "    int failedLogins = 0;\n"
                + "     |        |        |\n"
                + "    TYPE    NAME    VALUE\n"
                + "\n"
                + "    TYPE   what kind of value it can hold. int means a whole\n"
                + "           number - no decimal point.\n"
                + "    NAME   how you refer to it afterwards.\n"
                + "    VALUE  what goes in it to start with.\n"
                + "\n"
                + "That whole line is called a DECLARATION.\n"
                + "\n"
                + "The = sign does NOT mean 'equals'. It means 'put the value on "
                + "the right into the box on the left'. Read it as 'becomes' and "
                + "it will confuse you less later.\n"
                + "\n"
                + "Once the variable exists, use its name wherever you want its "
                + "value:\n"
                + "\n"
                + "    System.out.println(failedLogins);\n"
                + "\n"
                + "Notice there are no quotes around failedLogins. Quotes would "
                + "make it the literal text 'failedLogins'. Without them it is "
                + "the variable, and what prints is what is inside it.\n"
                + "\n"
                + "An int holds whole numbers only, positive or negative. 7, 0, "
                + "-3. Not 7.5.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failedLogins = 4;",
                "        System.out.println(failedLogins);",
                "    }",
                "}")
            .exampleOutput("4")
            .lineByLine(
                new String[]{"int",
                    "The type. This box can hold a whole number and nothing else. "
                    + "Try to put text in it and the program will not compile."},
                new String[]{"failedLogins",
                    "The name. Chosen by you. It should say what the value IS - "
                    + "failedLogins tells the reader far more than x would."},
                new String[]{"=",
                    "Assignment. Put the value on the right into the box on the "
                    + "left. It is an instruction, not a statement of fact."},
                new String[]{"4",
                    "The starting value."},
                new String[]{";",
                    "Ends the statement, exactly as it did in Campaign 00."},
                new String[]{"System.out.println(failedLogins);",
                    "No quotes, so this prints the CONTENTS of the box - 4. With "
                    + "quotes it would print the word failedLogins instead."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int openPorts = 12;",
                    "System.out.println(openPorts);")
                .accept("12", "twelve")
                .hints(
                    "There are no quotes around the variable name.",
                    "So what prints is what is inside the box.")
                .explain(
                    "12 - the value in the box, not the name of the box. The "
                    + "absence of quotes is what makes the difference.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "Careful with this one. What does it print?")
                .code(
                    "int openPorts = 12;",
                    "System.out.println(\"openPorts\");")
                .accept("openPorts")
                .hints(
                    "Look at the quotes.",
                    "Anything inside double quotes is printed exactly as written, "
                    + "even if it happens to match a variable name.")
                .explain(
                    "openPorts - the word itself. Quotes mean 'print this text', "
                    + "and Java does not look inside them for variable names.\n\n"
                    + "This is worth meeting now, because the two lines look almost "
                    + "identical and do completely different things.")
                .xp(20))
            .objective(
                "Give the audit tool somewhere to hold the failure count.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // declare the variable here",
                "        System.out.println(failedLogins);",
                "    }",
                "}")
            .yourTask(
                "The println already expects a variable called failedLogins "
                + "holding the value 7.\n\n"
                + "Write the declaration. Type the whole line, semicolon "
                + "included.")
            .mainTask(new Task(Task.WRITE,
                    "Declare an int called failedLogins holding 7.")
                .accept("int failedLogins = 7;", "int failedLogins = 7")
                .hints(
                    "Three parts in order: the type, the name, then = and the "
                    + "value.",
                    "The type for a whole number is int.",
                    "int failedLogins = ... and then the number and a semicolon.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int failedLogins = 7;",
                    "        System.out.println(failedLogins);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The declaration creates a box that can hold a whole number, "
                    + "names it failedLogins, and puts 7 into it.\n"
                    + "\n"
                    + "By the time the println runs, that name means something, so "
                    + "Java looks in the box and prints what it finds.\n"
                    + "\n"
                    + "Order matters. Move the declaration below the println and "
                    + "it will not compile, because Java reads top to bottom and "
                    + "the name would not exist yet.")
                .explain(
                    "type, name, =, value, semicolon. That shape does not change.")
                .xp(25))
            .mistakes(
                new String[]{"Putting quotes round a number",
                    "int failedLogins = \"7\"; does not compile. Quotes make it "
                    + "text, and text does not fit in an int box. The error is "
                    + "'incompatible types: String cannot be converted to int'."},
                new String[]{"Printing the name instead of the value",
                    "println(\"failedLogins\") prints the word. Without quotes it "
                    + "prints what is stored. Both compile, so nothing warns you."},
                new String[]{"Using a variable before declaring it",
                    "Java reads top to bottom. The declaration must come first, or "
                    + "you get 'cannot find symbol'."})
            .cyber(
                "A count of failed logins is the simplest detection signal there "
                + "is, and almost every alerting rule is built on something like "
                + "it.\n"
                + "\n"
                + "Notice what having a variable already buys you. The number now "
                + "lives in one place. Change it once and everything that reads it "
                + "sees the new value. Typing 7 into three separate print "
                + "statements means three places to change and two chances to "
                + "forget - and a report that contradicts itself is worse than no "
                + "report, because someone will act on the wrong half of it.")
            .check(new Task(Task.CHOICE,
                    "Which type holds a whole number?")
                .choices("String", "int", "text", "number")
                .accept("2", "b")
                .hints("It is three letters.",
                       "Short for integer.")
                .explain(
                    "int, short for integer. It holds whole numbers, positive or "
                    + "negative, and nothing with a decimal point.")
                .xp(10))
            .check(new Task(Task.DEBUG,
                    "Which line number stops this compiling?")
                .code(
                    "System.out.println(riskScore);",
                    "int riskScore = 5;")
                .accept("1", "line 1")
                .hints(
                    "Java reads from top to bottom.",
                    "At the moment line 1 runs, does riskScore exist yet?")
                .explain(
                    "Line 1 uses riskScore before line 2 creates it. Java reads in "
                    + "order, so the name means nothing yet and you get 'cannot "
                    + "find symbol'. Declare first, use after.")
                .xp(20))
            .recap(
                "A variable is a named box holding one value.\n"
                + "\n"
                + "    int failedLogins = 7;\n"
                + "    TYPE    NAME       VALUE\n"
                + "\n"
                + "int holds whole numbers. The = means 'put into', not 'equals'.\n"
                + "\n"
                + "Printing the name WITHOUT quotes prints the value. WITH quotes "
                + "it prints the word. Declare before you use.")
            .next("Next: a box that holds text instead of a number."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(2), "Somewhere to Put a Name", 1)
            .brief(
                "A count on its own tells you something is wrong but not who it "
                + "happened to. The audit report needs the username beside the "
                + "number.\n\n"
                + "Numbers and text are different kinds of thing, and Java wants "
                + "to know which you mean.")
            .willLearn("String", "Text variables", "Joining text with +")
            .whyUseful(
                "Nearly everything in security work arrives as text: usernames, "
                + "addresses, hostnames, log lines, file paths. String is the type "
                + "that holds it.")
            .concept("String",
                "A String holds text. The declaration has the same three parts as "
                + "an int, with two differences:\n"
                + "\n"
                + "    String username = \"jsmith\";\n"
                + "\n"
                + "    String   capital S. It is the one common type that is\n"
                + "             capitalised, because unlike int it is a class.\n"
                + "    \"jsmith\" the value goes in DOUBLE quotes.\n"
                + "\n"
                + "The quotes are how Java tells text apart from names. Without "
                + "them, jsmith would look like another variable.\n"
                + "\n"
                + "You can join text together with + :\n"
                + "\n"
                + "    System.out.println(\"USER: \" + username);\n"
                + "\n"
                + "That prints  USER: jsmith\n"
                + "\n"
                + "The + drops the variable's value in where the name sits. Notice "
                + "the space after the colon is INSIDE the quotes - + adds nothing "
                + "of its own, exactly as you saw in Campaign 00.\n"
                + "\n"
                + "+ also works with an int on one side:\n"
                + "\n"
                + "    System.out.println(\"FAILED: \" + failedLogins);\n"
                + "\n"
                + "Java turns the number into text to join it on.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String username = \"contractor\";",
                "        int attempts = 9;",
                "        System.out.println(username + \" had \" + attempts);",
                "    }",
                "}")
            .exampleOutput("contractor had 9")
            .lineByLine(
                new String[]{"String username = \"contractor\";",
                    "A text box. Capital S on String, double quotes round the "
                    + "value."},
                new String[]{"int attempts = 9;",
                    "A number box. Lowercase int, no quotes round the value."},
                new String[]{"username + \" had \" + attempts",
                    "Three pieces joined left to right: the text in username, then "
                    + "the literal text had with a space each side, then the number "
                    + "converted to text."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String host = \"WEB-01\";",
                    "System.out.println(\"HOST: \" + host);")
                .accept("HOST: WEB-01")
                .hints(
                    "The + joins the literal text to the value in the box.",
                    "The space after the colon is inside the quotes, so it appears.")
                .explain(
                    "HOST: WEB-01. The literal part came from inside the quotes, "
                    + "the rest from the variable.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "What does this print? Watch the spacing.")
                .code(
                    "String host = \"DB-01\";",
                    "System.out.println(\"HOST:\" + host);")
                .accept("HOST:DB-01")
                .hints(
                    "Is there a space inside those quotes?",
                    "+ does not add a space of its own.")
                .explain(
                    "HOST:DB-01 with no gap. There was no space inside the quotes, "
                    + "so there is none in the output. The spaces you can see "
                    + "around the + in the source are invisible to Java.")
                .xp(20))
            .objective(
                "Put a username and a failure count on one line of the report.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String username = \"jsmith\";",
                "        int failedLogins = 7;",
                "        // print the report line here",
                "    }",
                "}")
            .yourTask(
                "The report line must read exactly:\n\n"
                + "    jsmith FAILED 7\n\n"
                + "Both values must come from the variables, not be typed into "
                + "the text.\n\n"
                + "Write the println. Type the whole statement.")
            .mainTask(new Task(Task.WRITE,
                    "Type the statement that prints  jsmith FAILED 7  using both "
                    + "variables.")
                .accept(
                    "System.out.println(username + \" FAILED \" + failedLogins);",
                    "System.out.println(username + \" FAILED \" + failedLogins)")
                .hints(
                    "Three pieces joined with +: the name variable, some literal "
                    + "text, and the number variable.",
                    "The literal middle piece needs a space at each end, inside "
                    + "the quotes.",
                    "System.out.println(username + \" FAILED \" + ...);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String username = \"jsmith\";",
                    "        int failedLogins = 7;",
                    "        System.out.println(username",
                    "            + \" FAILED \" + failedLogins);",
                    "    }",
                    "}")
                .whyItWorks(
                    "Java works left to right. It takes the text in username, "
                    + "joins the literal \" FAILED \" onto it, then meets an int "
                    + "and converts it to text to join that on too.\n"
                    + "\n"
                    + "The spaces either side of FAILED are inside the quotes, "
                    + "which is the only place a space can come from. Written as "
                    + "\"FAILED\" with no spaces you would get jsmithFAILED7.\n"
                    + "\n"
                    + "Because both values come from variables, changing the "
                    + "username at the top changes the report - nothing else needs "
                    + "editing.")
                .explain(
                    "Values from variables, spaces from inside the quotes. That is "
                    + "the pattern for almost every report line you will write.")
                .xp(25))
            .mistakes(
                new String[]{"Lowercase string",
                    "string username = ... does not compile. String is a class and "
                    + "class names are capitalised. The error is 'cannot find "
                    + "symbol: class string'."},
                new String[]{"Single quotes round text",
                    "'jsmith' is not a String. Single quotes are for one character "
                    + "only, which is a different type you meet later in this "
                    + "campaign."},
                new String[]{"Expecting + to add a space",
                    "It never does. Every space in your output came from inside "
                    + "some pair of quotes."})
            .cyber(
                "Putting the identity next to the number is what makes a finding "
                + "actionable.\n"
                + "\n"
                + "'7 failed logins' tells an analyst almost nothing. '7 failed "
                + "logins for jsmith' tells them who to ring. The same detection, "
                + "the same number, and one of them can be acted on at three in "
                + "the morning.\n"
                + "\n"
                + "This is also why log formats are so rigid. A line that always "
                + "puts the same fields in the same order can be read by a person "
                + "at a glance and by a machine reliably, and one that varies can "
                + "be read properly by neither.")
            .check(new Task(Task.CHOICE,
                    "Which declaration is correct?")
                .choices(
                    "string user = \"admin\";",
                    "String user = \"admin\";",
                    "String user = 'admin';",
                    "String user = admin;")
                .accept("2", "b")
                .hints("Check the capital letter and the kind of quotes.",
                       "Capital S, and double quotes.")
                .explain(
                    "Capital S because String is a class, and double quotes "
                    + "because it is text. The last option has no quotes at all, so "
                    + "Java would look for a variable called admin.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String a = \"ALERT\";",
                    "int n = 3;",
                    "System.out.println(a + n);")
                .accept("ALERT3")
                .hints(
                    "One side of the + is text.",
                    "When either side is text, + joins rather than adds.")
                .explain(
                    "ALERT3, with nothing between them. The int was converted to "
                    + "text and joined on. No space was asked for, so none "
                    + "appeared.")
                .xp(15))
            .recap(
                "    String username = \"jsmith\";\n"
                + "\n"
                + "Capital S. Double quotes. Holds text.\n"
                + "\n"
                + "    + joins pieces together\n"
                + "    + adds no spaces of its own\n"
                + "    + turns a number into text when the other side is text\n"
                + "\n"
                + "int for numbers, String for text. Java wants to know which.")
            .next("Next: changing what is in a box after you have put "
                + "something in it."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(3), "Changing What Is in the Box", 1)
            .brief(
                "The audit tool watches one account through a shift. The failure "
                + "count starts at zero and climbs. A box you can only fill once "
                + "is no use for that.\n\n"
                + "A variable is not carved in stone. It is a box, and what is in "
                + "it can be replaced.")
            .willLearn("Assignment", "Reassignment", "Using a variable's own value")
            .whyUseful(
                "A monitoring tool spends its whole life updating values: counts "
                + "going up, scores being recalculated, status being replaced. "
                + "Every one of those is a reassignment.")
            .concept("Reassignment",
                "Declaring gives you the box AND puts the first value in:\n"
                + "\n"
                + "    int failedLogins = 0;\n"
                + "\n"
                + "After that the box exists. To put a different value in, you do "
                + "NOT write the type again:\n"
                + "\n"
                + "    failedLogins = 3;\n"
                + "\n"
                + "Name, =, new value. That is a plain ASSIGNMENT.\n"
                + "\n"
                + "Writing int a second time is an error, because int means 'make "
                + "a new box called this', and a box by that name already exists.\n"
                + "\n"
                + "A box holds ONE value at a time. The old value is gone - not "
                + "stored anywhere, not recoverable.\n"
                + "\n"
                + "Now the part that trips everybody up. A variable is allowed to "
                + "appear on both sides:\n"
                + "\n"
                + "    failedLogins = failedLogins + 1;\n"
                + "\n"
                + "As maths that is nonsense. As an instruction it is fine, "
                + "because the two sides happen at different times:\n"
                + "\n"
                + "    1. Work out the RIGHT side first. failedLogins is 3\n"
                + "       at that moment, so 3 + 1 gives 4.\n"
                + "    2. Put that answer into the box on the LEFT.\n"
                + "\n"
                + "The box ends up holding 4. Right side first, then store. That "
                + "order is the whole trick.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failedLogins = 0;",
                "        System.out.println(failedLogins);",
                "        failedLogins = 3;",
                "        System.out.println(failedLogins);",
                "        failedLogins = failedLogins + 1;",
                "        System.out.println(failedLogins);",
                "    }",
                "}")
            .exampleOutput("0", "3", "4")
            .lineByLine(
                new String[]{"int failedLogins = 0;",
                    "Declaration. Makes the box and puts 0 in it. The word int "
                    + "appears here and nowhere else for this variable."},
                new String[]{"failedLogins = 3;",
                    "Assignment. No type. The 0 is overwritten and gone."},
                new String[]{"failedLogins = failedLogins + 1;",
                    "The right side is worked out with the CURRENT value: 3 + 1 "
                    + "is 4. Then 4 is stored, replacing the 3."},
                new String[]{"The three printlns",
                    "Each one prints whatever is in the box at that moment. The "
                    + "same line of code prints a different number each time "
                    + "because the contents changed in between."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int alerts = 5;",
                    "alerts = 8;",
                    "System.out.println(alerts);")
                .accept("8", "eight")
                .hints(
                    "Java runs the lines in order.",
                    "A box holds one value. What was the last thing put in?")
                .explain(
                    "8. The 5 was overwritten by line 2 and is gone. Only the "
                    + "most recent value survives.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "This one needs the right side worked out first. What prints?")
                .code(
                    "int score = 10;",
                    "score = score + 5;",
                    "score = score + 5;",
                    "System.out.println(score);")
                .accept("20", "twenty")
                .hints(
                    "Take it one line at a time and write down what is in the box "
                    + "after each.",
                    "After line 2 the box holds 15. Now do line 3 with that value.")
                .explain(
                    "20.\n\n"
                    + "    line 1  box holds 10\n"
                    + "    line 2  10 + 5 is 15, store 15\n"
                    + "    line 3  15 + 5 is 20, store 20\n\n"
                    + "Line 3 is identical to line 2 but gives a different answer, "
                    + "because it runs with a different starting value. Tracing a "
                    + "variable line by line like this is the single most useful "
                    + "habit you can build.")
                .xp(25))
            .objective(
                "Count a second failed login.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failedLogins = 1;",
                "        // add one to failedLogins here",
                "        System.out.println(failedLogins);",
                "    }",
                "}")
            .yourTask(
                "The box already holds 1. Another failure has just come in.\n\n"
                + "Write one line that adds 1 to whatever is already there. Do "
                + "not write 2 - the tool will not know the answer in advance, so "
                + "it has to add to the current value.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line that increases failedLogins by 1.")
                .accept(
                    "failedLogins = failedLogins + 1;",
                    "failedLogins = failedLogins + 1",
                    "failedLogins = 1 + failedLogins;",
                    "failedLogins = 1 + failedLogins")
                .hints(
                    "The box already exists, so no type word this time.",
                    "Shape: name = something ;  where the something works out the "
                    + "new value.",
                    "The new value is the old value plus one: failedLogins + 1.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int failedLogins = 1;",
                    "        failedLogins = failedLogins + 1;",
                    "        System.out.println(failedLogins);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The right side runs first. failedLogins is 1 at that moment, "
                    + "so 1 + 1 gives 2. That 2 is then stored back in the box, "
                    + "replacing the 1.\n"
                    + "\n"
                    + "Writing failedLogins = 2; would print the same thing here "
                    + "and be wrong everywhere else. It only works because you "
                    + "happen to know the count was 1. Real counts arrive one at "
                    + "a time from something you do not control, and 'add one to "
                    + "whatever is there' keeps working no matter what is there.")
                .explain(
                    "Add to the current value, do not replace it with a guess.")
                .xp(30))
            .mistakes(
                new String[]{"Writing the type again",
                    "int failedLogins = failedLogins + 1; tries to create a second "
                    + "box with a name that is taken. The error is 'variable "
                    + "failedLogins is already defined'."},
                new String[]{"Reading = as equals",
                    "x = x + 1 looks impossible if you read it as maths. It is an "
                    + "instruction: work out the right, store it on the left."},
                new String[]{"Expecting the old value to still be there",
                    "Once you assign, the previous value is gone. If you need it "
                    + "later, it has to be kept in a second variable first."})
            .cyber(
                "A counter that climbs is how detection actually works. A tool "
                + "watching authentication holds a number per account and adds one "
                + "each time a login fails - then something compares that number "
                + "against a threshold and decides whether anyone gets woken up.\n"
                + "\n"
                + "The interesting question is when the count goes back down. If "
                + "it is never reset, ten failures spread over six months look "
                + "identical to ten failures in ten seconds, and only one of those "
                + "is an attack. If it is reset too eagerly, an attacker who paces "
                + "themselves stays invisible forever. You are not writing that "
                + "logic yet, but the decision lives in exactly this kind of "
                + "assignment.")
            .check(new Task(Task.DEBUG,
                    "Which line number does not compile?")
                .code(
                    "int riskScore = 4;",
                    "int riskScore = 9;",
                    "System.out.println(riskScore);")
                .accept("2", "line 2")
                .hints(
                    "Which word means 'make a new box'?",
                    "How many times does that word appear for the same name?")
                .explain(
                    "Line 2. int means 'create a variable called this', and one "
                    + "called riskScore already exists. To change it, drop the "
                    + "type: riskScore = 9;")
                .xp(20))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int n = 2;",
                    "n = n + n;",
                    "System.out.println(n);")
                .accept("4", "four")
                .hints(
                    "Work out the right side first, using the value n has now.",
                    "Both n on the right are 2 at that moment.")
                .explain(
                    "4. The right side is worked out before anything is stored, so "
                    + "both copies of n are still 2 and give 4. The new value only "
                    + "lands in the box afterwards.")
                .xp(20))
            .recap(
                "    int failedLogins = 0;   declare - type, once only\n"
                + "    failedLogins = 3;       assign - no type\n"
                + "    failedLogins = failedLogins + 1;\n"
                + "\n"
                + "Right side first, then store on the left.\n"
                + "\n"
                + "A box holds one value at a time. The old one is gone.")
            .next("Next: a type with only two possible values."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(4), "True or False, Nothing Else", 1)
            .brief(
                "Some of what a security tool records is not a count and not a "
                + "name. Is this account locked? Did the check pass? Is this host "
                + "reachable?\n\n"
                + "Those have exactly two answers, and Java has a type that holds "
                + "exactly two values.")
            .willLearn("boolean", "true and false", "Yes/no values")
            .whyUseful(
                "Every decision a program makes eventually comes down to a "
                + "true or false. When you reach conditions in the next campaign, "
                + "this is the type they run on - so it is worth meeting on its "
                + "own first.")
            .concept("boolean",
                "A boolean holds one of two values and nothing else:\n"
                + "\n"
                + "    boolean accountLocked = true;\n"
                + "    boolean scanFinished = false;\n"
                + "\n"
                + "    boolean   lower case b\n"
                + "    true      no quotes\n"
                + "    false     no quotes\n"
                + "\n"
                + "true and false are Java keywords, not text. Put quotes round "
                + "them and you get the String \"true\", which is a different "
                + "thing that happens to look the same when printed.\n"
                + "\n"
                + "There is no maybe, no 0, no empty. Two values, that is the "
                + "whole type.\n"
                + "\n"
                + "Reassignment works exactly as it did for int:\n"
                + "\n"
                + "    boolean accountLocked = false;\n"
                + "    accountLocked = true;\n"
                + "\n"
                + "Printing a boolean prints the word true or the word false.\n"
                + "\n"
                + "Name them so they read as a question with a yes/no answer. "
                + "accountLocked, scanFinished, isAdmin - each one you can put a "
                + "'yes' or 'no' against. A boolean called status tells the reader "
                + "nothing, because they cannot tell what true would mean.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String account = \"contractor\";",
                "        boolean locked = false;",
                "        locked = true;",
                "        System.out.println(account + \" locked: \" + locked);",
                "    }",
                "}")
            .exampleOutput("contractor locked: true")
            .lineByLine(
                new String[]{"boolean locked = false;",
                    "Declares a box that can hold true or false, starting at "
                    + "false. No quotes round false - it is a keyword."},
                new String[]{"locked = true;",
                    "Reassignment. Same rules as int: no type word, old value "
                    + "replaced."},
                new String[]{"\" locked: \" + locked",
                    "The + joins the boolean's value onto the text. Java writes "
                    + "it out as the word true."},
                new String[]{"The leading space in \" locked: \"",
                    "Without it the output would read contractorlocked: true. "
                    + "The + still adds nothing of its own."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "boolean patched = false;",
                    "patched = true;",
                    "System.out.println(patched);")
                .accept("true")
                .hints(
                    "Same rule as the last mission - the last value stored wins.",
                    "Printing a boolean prints the word.")
                .explain(
                    "true. Line 2 replaced the false, and printing a boolean "
                    + "writes out the word.")
                .xp(10))
            .practice(new Task(Task.DEBUG,
                    "This does not compile. Which line is wrong?")
                .code(
                    "boolean reachable = true;",
                    "boolean online = \"true\";",
                    "System.out.println(online);")
                .accept("2", "line 2")
                .hints(
                    "Compare the two declarations closely.",
                    "One of those values is a keyword and the other is text.")
                .explain(
                    "Line 2. \"true\" in quotes is a String, and a String does not "
                    + "fit in a boolean box. The error is 'incompatible types: "
                    + "String cannot be converted to boolean'.\n\n"
                    + "The cruel part is that both print the same thing if you get "
                    + "them past the compiler - so this is a mistake you find by "
                    + "reading, not by looking at output.")
                .xp(25))
            .objective(
                "Record that the contractor account is not yet disabled.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String account = \"contractor\";",
                "        // declare the boolean here",
                "        System.out.println(account + \" disabled: \" + disabled);",
                "    }",
                "}")
            .yourTask(
                "The println expects a boolean called disabled, and the account "
                + "has NOT been disabled yet.\n\n"
                + "Write the declaration.")
            .mainTask(new Task(Task.WRITE,
                    "Declare a boolean called disabled holding false.")
                .accept("boolean disabled = false;", "boolean disabled = false")
                .hints(
                    "Same three parts as always: type, name, value.",
                    "The type is boolean, all lower case.",
                    "Not yet disabled means the value is false, with no quotes.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String account = \"contractor\";",
                    "        boolean disabled = false;",
                    "        System.out.println(account + \" disabled: \" + disabled);",
                    "    }",
                    "}")
                .whyItWorks(
                    "boolean creates a box that will accept true or false and "
                    + "nothing else, and false goes in as the starting value.\n"
                    + "\n"
                    + "That restriction is the point of the type. A count of "
                    + "failures could be any number and an int allows any number. "
                    + "'Has this been disabled' has two answers, so the type has "
                    + "two values, and a third state simply cannot be written "
                    + "down by mistake.\n"
                    + "\n"
                    + "If this were an int holding 0 and 1, somebody would "
                    + "eventually store 2 and nobody would know what it meant.")
                .explain(
                    "boolean, a yes/no name, and a bare true or false.")
                .xp(25))
            .mistakes(
                new String[]{"Quoting true or false",
                    "boolean b = \"true\"; does not compile. true is a keyword, "
                    + "not text. Quotes make it a String."},
                new String[]{"Capital B or capital T",
                    "Boolean and True are not the same words as boolean and true. "
                    + "Java is case sensitive and will not guess."},
                new String[]{"Using 0 and 1",
                    "Some languages treat 0 as false. Java does not. int does not "
                    + "fit in a boolean and the compiler says so."})
            .cyber(
                "Booleans are where security tools record the answers that matter "
                + "most: is multi-factor enabled, is this host patched, did the "
                + "signature verify.\n"
                + "\n"
                + "Because there are only two values, there is no room for a "
                + "cheerful middle state - and that is a feature. A check that can "
                + "come back 'unknown' invites someone to treat unknown as fine. "
                + "Forcing the answer into true or false forces you to decide what "
                + "'we could not tell' should count as. In security it almost "
                + "always has to count as the unsafe answer, because an attacker "
                + "is quite happy for you to assume the best.")
            .check(new Task(Task.CHOICE,
                    "How many values can a boolean hold?")
                .choices("Any whole number", "Two", "Two, plus empty", "As many as you assign")
                .accept("2", "b", "two")
                .hints("Read the type's name.",
                       "true and false. Is there a third?")
                .explain(
                    "Two: true and false. There is no third state and no empty "
                    + "value - the box always holds one of exactly two things.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which declaration compiles?")
                .choices(
                    "Boolean locked = True;",
                    "boolean locked = \"false\";",
                    "boolean locked = false;",
                    "boolean locked = 0;")
                .accept("3", "c")
                .hints(
                    "Lower case type, and a value with no quotes.",
                    "Rule out the quoted one, the capitalised one and the number.")
                .explain(
                    "boolean locked = false; - lower case type, keyword value, no "
                    + "quotes. True with a capital is not a Java keyword, \"false\" "
                    + "in quotes is a String, and 0 is an int.")
                .xp(20))
            .recap(
                "    boolean accountLocked = true;\n"
                + "    boolean scanFinished = false;\n"
                + "\n"
                + "Lower case boolean. Bare true or false, never quoted.\n"
                + "\n"
                + "Exactly two values. Name it so a yes/no answer makes sense.")
            .next("Next: numbers that are not whole."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(5), "Numbers With a Point", 1)
            .brief(
                "The vulnerability scanner scores each finding out of ten, and the "
                + "scores are not whole numbers. 7.5. 9.8. 4.3.\n\n"
                + "An int will not hold those. It needs a different type.")
            .willLearn("double", "Decimal numbers", "Choosing int or double")
            .whyUseful(
                "Severity scores, percentages, averages and timings all arrive "
                + "with a decimal point. Storing them in an int silently throws "
                + "the fraction away, which is how a 9.8 becomes a 9.")
            .concept("double",
                "A double holds a number with a decimal point:\n"
                + "\n"
                + "    double severity = 7.5;\n"
                + "\n"
                + "The name is historical - it means double precision. Treat it as "
                + "'the type for numbers that are not whole'.\n"
                + "\n"
                + "A double can hold a whole number too:\n"
                + "\n"
                + "    double severity = 8;\n"
                + "\n"
                + "That is allowed, and it prints as 8.0. Java knows the box is a "
                + "decimal one and shows it that way.\n"
                + "\n"
                + "Going the other direction is refused:\n"
                + "\n"
                + "    int severity = 7.5;    does NOT compile\n"
                + "\n"
                + "7.5 does not fit in a whole-number box, and Java will not "
                + "quietly drop the .5 for you. It stops and tells you. That "
                + "refusal is deliberate - losing half a point without being told "
                + "is worse than an error message.\n"
                + "\n"
                + "Choosing between them is a question about the thing you are "
                + "storing, not about the number in front of you:\n"
                + "\n"
                + "    int     things you count - failures, ports, users\n"
                + "    double  things you measure - scores, percentages, time\n"
                + "\n"
                + "You cannot have 2.5 failed logins, so a failure count is an "
                + "int even though 2 would fit in a double.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String finding = \"CVE-2024-0011\";",
                "        double severity = 9.8;",
                "        int affectedHosts = 3;",
                "        System.out.println(finding + \" \" + severity);",
                "        System.out.println(\"HOSTS: \" + affectedHosts);",
                "    }",
                "}")
            .exampleOutput("CVE-2024-0011 9.8", "HOSTS: 3")
            .lineByLine(
                new String[]{"double severity = 9.8;",
                    "A decimal box. 9.8 would not fit in an int - that line would "
                    + "not compile."},
                new String[]{"int affectedHosts = 3;",
                    "A count of machines. There is no such thing as 3.5 hosts, so "
                    + "int is the honest type here."},
                new String[]{"Both printlns",
                    "+ turns either kind of number into text to join it on. From "
                    + "the outside they behave the same way."})
            .predict(new Task(Task.PREDICT,
                    "What does this print? Look carefully at the type.")
                .code(
                    "double uptime = 8;",
                    "System.out.println(uptime);")
                .accept("8.0")
                .hints(
                    "The value written is 8, but what kind of box is it going in?",
                    "A double always prints as a decimal, even when the value is "
                    + "whole.")
                .explain(
                    "8.0, not 8. The box is a double, so Java prints it as a "
                    + "decimal number. What you typed was 8; what is stored is the "
                    + "double 8.0.\n\n"
                    + "This catches people out in reports - a column that should "
                    + "read 8 suddenly reads 8.0 and the cause is the type, not "
                    + "the value.")
                .xp(20))
            .practice(new Task(Task.DEBUG,
                    "One of these lines does not compile. Which?")
                .code(
                    "double score = 4;",
                    "int hosts = 2.0;",
                    "System.out.println(score + hosts);")
                .accept("2", "line 2")
                .hints(
                    "One direction is allowed and the other is not.",
                    "A whole number fits in a decimal box. Does a decimal fit in a "
                    + "whole-number box?")
                .explain(
                    "Line 2. 2.0 is a decimal value and an int holds whole numbers "
                    + "only, so Java refuses: 'incompatible types: possible lossy "
                    + "conversion from double to int'.\n\n"
                    + "Line 1 is fine the other way round - 4 fits in a double "
                    + "with nothing lost, and is stored as 4.0. Widening is safe, "
                    + "narrowing loses information, and Java only objects to the "
                    + "one that loses something.")
                .xp(25))
            .objective(
                "Record the severity of a finding the scanner scored at 7.5.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String finding = \"CVE-2024-0099\";",
                "        // declare the severity here",
                "        System.out.println(finding + \" SEVERITY \" + severity);",
                "    }",
                "}")
            .yourTask(
                "Write the declaration for a severity of 7.5.\n\n"
                + "Pick the type that can hold it without losing anything.")
            .mainTask(new Task(Task.WRITE,
                    "Declare the severity variable holding 7.5.")
                .accept("double severity = 7.5;", "double severity = 7.5")
                .hints(
                    "An int cannot hold 7.5 at all, so that is ruled out.",
                    "The decimal type is called double.",
                    "double severity = ... and then the value and a semicolon.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String finding = \"CVE-2024-0099\";",
                    "        double severity = 7.5;",
                    "        System.out.println(finding + \" SEVERITY \" + severity);",
                    "    }",
                    "}")
                .whyItWorks(
                    "double is the type that keeps the fraction. The .5 survives, "
                    + "and the report says 7.5.\n"
                    + "\n"
                    + "Had you written int severity = 7.5; the program would not "
                    + "compile - which is the good outcome. The dangerous version "
                    + "is a program that rounds silently: a 7.5 filed as a 7 sits "
                    + "below a threshold of 7.5 and never gets raised, and nothing "
                    + "anywhere reports that a decision was made.\n"
                    + "\n"
                    + "Choosing the type is choosing what the program is allowed "
                    + "to lose.")
                .explain(
                    "A decimal value needs a decimal type. double keeps the "
                    + "fraction; int refuses to take it at all.")
                .xp(25))
            .mistakes(
                new String[]{"Putting a decimal in an int",
                    "int score = 7.5; does not compile - 'possible lossy "
                    + "conversion from double to int'. Java will not drop the "
                    + "fraction behind your back."},
                new String[]{"Being surprised by 8.0",
                    "double n = 8; prints 8.0. The type decides how it is written "
                    + "out, not the digits you typed."},
                new String[]{"Using double for things you count",
                    "double failedLogins = 3; compiles and prints 3.0. It is legal "
                    + "and it is wrong - a count is a whole thing and the type "
                    + "should say so."})
            .cyber(
                "Severity scoring is how a team decides what to fix first. CVSS "
                + "produces a number from 0.0 to 10.0 with one decimal place, and "
                + "escalation thresholds are usually set on the decimal - 7.0 and "
                + "above is high, 9.0 and above is critical.\n"
                + "\n"
                + "Store those in an int and a 6.9 and a 6.1 both become 6, while "
                + "a 7.0 stays 7. The ordering of your entire backlog shifts, and "
                + "the findings that lost the most are the ones sitting closest to "
                + "the line where someone decides whether to act tonight or next "
                + "quarter. Nobody sees a rounding error. They see a queue that "
                + "looks perfectly reasonable.")
            .check(new Task(Task.CHOICE,
                    "Which is the better type for a count of open ports?")
                .choices("double", "int", "String", "boolean")
                .accept("2", "b", "int")
                .hints(
                    "Can a machine have 4.5 open ports?",
                    "You count ports, you do not measure them.")
                .explain(
                    "int. Ports are counted, so the value is always whole, and the "
                    + "type should say so. A double would compile and print 4.0, "
                    + "which is technically right and quietly misleading.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "double ratio = 3;",
                    "System.out.println(\"RATIO \" + ratio);")
                .accept("RATIO 3.0")
                .hints(
                    "The text is joined exactly as written, space included.",
                    "How does a double print a whole value?")
                .explain(
                    "RATIO 3.0. The space came from inside the quotes, and the "
                    + "value printed as 3.0 because the box is a double.")
                .xp(20))
            .recap(
                "    double severity = 7.5;   decimal numbers\n"
                + "    int hosts = 3;           whole numbers\n"
                + "\n"
                + "A whole number fits in a double and prints as 8.0.\n"
                + "A decimal does NOT fit in an int - the compiler refuses.\n"
                + "\n"
                + "Count things with int. Measure things with double.")
            .next("Next: a box that holds one single character."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(6), "Exactly One Character", 1)
            .brief(
                "The log parser marks each line with a single letter: I for info, "
                + "W for warning, E for error.\n\n"
                + "One letter is text, so a String would work - but Java has a "
                + "type for exactly one character, and the difference between the "
                + "two is worth understanding now.")
            .willLearn("char", "Single quotes", "char against String")
            .whyUseful(
                "Log severity markers, drive letters, single-key menu choices and "
                + "the individual letters inside a String are all chars. When you "
                + "start pulling text apart in a later campaign, what comes out of "
                + "a String one piece at a time is a char.")
            .concept("char",
                "A char holds exactly one character:\n"
                + "\n"
                + "    char severity = 'E';\n"
                + "\n"
                + "    char   lower case\n"
                + "    'E'    SINGLE quotes, not double\n"
                + "\n"
                + "The quote marks are the whole story here:\n"
                + "\n"
                + "    'E'    a char - one character\n"
                + "    \"E\"    a String - text that happens to be one long\n"
                + "\n"
                + "They print identically and they are different types. A char "
                + "will not fit in a String variable and a String will not fit in "
                + "a char variable, whatever it looks like.\n"
                + "\n"
                + "Exactly one means exactly one:\n"
                + "\n"
                + "    char c = 'AB';   does NOT compile - two characters\n"
                + "    char c = '';     does NOT compile - none at all\n"
                + "\n"
                + "A digit in single quotes is a character, not a number:\n"
                + "\n"
                + "    char grade = '7';   the symbol 7\n"
                + "    int count = 7;      the number 7\n"
                + "\n"
                + "You can do arithmetic with a count. You cannot with a symbol - "
                + "or rather you can, and it does something surprising, which is a "
                + "problem for a much later mission.\n"
                + "\n"
                + "A space is a character too: ' ' is perfectly valid.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        char severity = 'W';",
                "        String host = \"WEB-01\";",
                "        System.out.println(\"[\" + severity + \"] \" + host);",
                "    }",
                "}")
            .exampleOutput("[W] WEB-01")
            .lineByLine(
                new String[]{"char severity = 'W';",
                    "Single quotes, one character. Double quotes here would make "
                    + "it a String and the line would not compile."},
                new String[]{"String host = \"WEB-01\";",
                    "Double quotes, any amount of text. Six characters would never "
                    + "fit in a char."},
                new String[]{"\"[\" + severity + \"] \"",
                    "The brackets are plain text in double quotes. + joins the "
                    + "char between them."},
                new String[]{"The space after ]",
                    "Inside the quotes, as always. Without it the output would run "
                    + "together as [W]WEB-01."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "char flag = 'E';",
                    "System.out.println(flag);")
                .accept("E")
                .hints(
                    "No quotes round the variable name in the println.",
                    "So the contents print.")
                .explain(
                    "E. Printing a char writes out that single character - the "
                    + "single quotes are how you wrote it in the source, and they "
                    + "are not part of the value.")
                .xp(10))
            .practice(new Task(Task.DEBUG,
                    "Two of these three lines do not compile. Give the line number "
                    + "of the FIRST one that fails.")
                .code(
                    "char a = 'I';",
                    "char b = \"W\";",
                    "char c = 'ER';")
                .accept("2", "line 2")
                .hints(
                    "Line 1 is correct - use it as your reference.",
                    "Compare the quote marks on line 2, then count the characters "
                    + "on line 3.")
                .explain(
                    "Line 2 fails first: \"W\" in double quotes is a String, and a "
                    + "String does not fit in a char.\n\n"
                    + "Line 3 is wrong too - 'ER' is two characters and a char "
                    + "holds one. Both are errors, and the question asked for the "
                    + "first, which is how a compiler reports them: top to bottom.")
                .xp(25))
            .objective(
                "Mark a log line as an error.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String host = \"DB-01\";",
                "        // declare the level here",
                "        System.out.println(level + \" \" + host);",
                "    }",
                "}")
            .yourTask(
                "The println expects a variable called level holding the single "
                + "character E.\n\n"
                + "Use the type that holds exactly one character, and mind which "
                + "quotes you use.")
            .mainTask(new Task(Task.WRITE,
                    "Declare a char called level holding the character E.")
                .accept("char level = 'E';", "char level = 'E'")
                .hints(
                    "The type is char, all lower case.",
                    "One character means single quotes, not double.",
                    "char level = and then the character in single quotes.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String host = \"DB-01\";",
                    "        char level = 'E';",
                    "        System.out.println(level + \" \" + host);",
                    "    }",
                    "}")
                .whyItWorks(
                    "char is the type for one character and single quotes are how "
                    + "a character is written. Together they say 'this is one "
                    + "symbol', not 'this is text that currently has one symbol "
                    + "in it'.\n"
                    + "\n"
                    + "The output is identical either way, which is exactly why "
                    + "the distinction is worth making deliberately rather than by "
                    + "accident. The type is a promise about what can ever be in "
                    + "there. A char level can never hold \"ERROR\" or an empty "
                    + "value, so every piece of code downstream can count on "
                    + "getting one character - and none of it needs to check.")
                .explain(
                    "char for one character, and single quotes to write it.")
                .xp(25))
            .mistakes(
                new String[]{"Double quotes on a char",
                    "char c = \"E\"; does not compile - that is a String. Single "
                    + "quotes for char, double quotes for String."},
                new String[]{"More than one character",
                    "char c = 'ER'; is an 'unclosed character literal' error. One "
                    + "means one."},
                new String[]{"Single quotes on a String",
                    "String s = 'text'; does not compile either. The two quote "
                    + "styles are not interchangeable in Java."})
            .cyber(
                "Log formats lean on single characters because they are cheap and "
                + "unambiguous. Syslog severity, the I/W/E markers in application "
                + "logs, the r/w/x in Unix permissions - one character each, in a "
                + "fixed position, so a parser can find them without understanding "
                + "the line.\n"
                + "\n"
                + "That fixed width is what makes millions of log lines searchable "
                + "in the first place. It is also what makes a log line worth "
                + "treating with suspicion: if an attacker can get their own text "
                + "into a field, they can write something that looks like a "
                + "perfectly formed log entry and your parser will read it as one. "
                + "Log injection is a real technique, and it starts with a parser "
                + "that trusts the shape of a line.")
            .check(new Task(Task.CHOICE,
                    "Which declaration compiles?")
                .choices(
                    "char c = \"A\";",
                    "char c = 'AB';",
                    "char c = 'A';",
                    "char c = A;")
                .accept("3", "c")
                .hints(
                    "Single quotes, exactly one character.",
                    "Rule out double quotes, two characters and no quotes at all.")
                .explain(
                    "char c = 'A'; - single quotes round exactly one character. "
                    + "\"A\" is a String, 'AB' is two characters, and a bare A "
                    + "would be read as a variable name.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "char a = 'O';",
                    "char b = 'K';",
                    "System.out.println(\"\" + a + b);")
                .accept("OK")
                .hints(
                    "The empty text at the start makes the whole thing a joining "
                    + "job rather than arithmetic.",
                    "Then the two characters are joined on in order.")
                .explain(
                    "OK. Because the first piece is text, + joins rather than "
                    + "adds, and the two characters land side by side.\n\n"
                    + "Without that empty string at the front, 'O' + 'K' would do "
                    + "something unexpected to two chars. That is a story for a "
                    + "later mission - for now, the empty string is the safe way "
                    + "to join characters as text.")
                .xp(25))
            .recap(
                "    char severity = 'E';    one character, SINGLE quotes\n"
                + "    String host = \"WEB-01\";  any text, DOUBLE quotes\n"
                + "\n"
                + "Exactly one character - not two, not zero.\n"
                + "'7' is a symbol. 7 is a number. They are not the same thing.\n"
                + "\n"
                + "They print the same. They are different types.")
            .next("Next: the rules about what you may call a variable."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(7), "What You Are Allowed to Call It", 1)
            .brief(
                "Someone before you wrote the audit tool with variables called x, "
                + "x2 and temp. It works. Nobody can safely change it, because "
                + "nobody can tell what any of them hold.\n\n"
                + "Some naming rules Java enforces. The rest are down to you, and "
                + "they matter more.")
            .willLearn("Naming rules", "camelCase", "Case sensitivity")
            .whyUseful(
                "You will spend far more time reading code than writing it - "
                + "often your own, months later. A good name removes the need to "
                + "work out what something is."
                )
            .concept("Naming variables",
                "Rules Java ENFORCES. Break one and it will not compile:\n"
                + "\n"
                + "    Start with a letter. Digits are fine after that, never\n"
                + "    first. 2ndAttempt is illegal, attempt2 is fine.\n"
                + "\n"
                + "    No spaces. failed logins is two words and Java sees two\n"
                + "    things it does not recognise.\n"
                + "\n"
                + "    No punctuation except _ and $. No dashes - failed-count\n"
                + "    looks like a subtraction.\n"
                + "\n"
                + "    Not a keyword. int, class, new, true and the rest are\n"
                + "    taken.\n"
                + "\n"
                + "Java is CASE SENSITIVE. These are three different variables:\n"
                + "\n"
                + "    failedlogins    failedLogins    FailedLogins\n"
                + "\n"
                + "Declare one and use another and you get 'cannot find symbol'. "
                + "It is one of the most common beginner errors and it always "
                + "looks like the compiler being unreasonable.\n"
                + "\n"
                + "Conventions nobody ENFORCES but everybody follows:\n"
                + "\n"
                + "    camelCase. First word lower case, every word after it\n"
                + "    starts with a capital and no spaces anywhere:\n"
                + "\n"
                + "        failedLogins    sourceAddress    isAdmin\n"
                + "\n"
                + "    Say what it holds. sourceAddress, not s. The compiler is\n"
                + "    perfectly happy with s. Your reader is not.\n"
                + "\n"
                + "    No abbreviating to save typing. You type it once and\n"
                + "    read it fifty times.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String sourceAddress = \"10.0.4.17\";",
                "        int failedLogins = 12;",
                "        boolean accountLocked = true;",
                "        System.out.println(sourceAddress + \" \" + failedLogins);",
                "    }",
                "}")
            .exampleOutput("10.0.4.17 12")
            .lineByLine(
                new String[]{"sourceAddress",
                    "Two words, camelCase, and it says exactly what is in the box. "
                    + "Compare it with addr or s."},
                new String[]{"failedLogins",
                    "Says what is counted, not just that something is counted. "
                    + "count alone would leave the reader guessing."},
                new String[]{"accountLocked",
                    "A boolean, named so that true and false both make obvious "
                    + "sense against it."},
                new String[]{"The println",
                    "The names have to match the declarations exactly, capital "
                    + "letters included. sourceaddress would not compile."})
            .predict(new Task(Task.DEBUG,
                    "Which line stops this compiling?")
                .code(
                    "int failedLogins = 3;",
                    "System.out.println(failedlogins);")
                .accept("2", "line 2")
                .hints(
                    "Compare the two names character by character.",
                    "Java is case sensitive. Is the L the same on both lines?")
                .explain(
                    "Line 2. The declaration says failedLogins with a capital L "
                    + "and line 2 asks for failedlogins with a small one. To Java "
                    + "those are two different names, so it reports 'cannot find "
                    + "symbol'.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Which of these is a LEGAL variable name?")
                .choices(
                    "2ndAttempt",
                    "failed-logins",
                    "source address",
                    "sourceAddress2")
                .accept("4", "d")
                .hints(
                    "Check each against the rules: first character, spaces, "
                    + "punctuation.",
                    "Digits are banned at the START only.")
                .explain(
                    "sourceAddress2. It starts with a letter and the digit is not "
                    + "first.\n\n"
                    + "2ndAttempt starts with a digit. failed-logins contains a "
                    + "dash, which Java reads as subtraction. source address has a "
                    + "space, so Java sees two separate things.")
                .xp(20))
            .objective(
                "Give the source address of the failed logins a name worth "
                + "reading.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // declare the address here",
                "        System.out.println(sourceAddress);",
                "    }",
                "}")
            .yourTask(
                "The println expects a String called sourceAddress holding "
                + "10.0.4.17.\n\n"
                + "Write the declaration. Copy the name exactly - capital A "
                + "included.")
            .mainTask(new Task(Task.WRITE,
                    "Declare a String called sourceAddress holding 10.0.4.17")
                .accept(
                    "String sourceAddress = \"10.0.4.17\";",
                    "String sourceAddress = \"10.0.4.17\"")
                .hints(
                    "An address is text, so the type is String with a capital S.",
                    "The name must match the println exactly, capital A and all.",
                    "String sourceAddress = and then the address in double quotes.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String sourceAddress = \"10.0.4.17\";",
                    "        System.out.println(sourceAddress);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The name in the declaration and the name in the println are "
                    + "identical, character for character, so Java matches them "
                    + "up. sourceaddress or SourceAddress would each be a "
                    + "different name and neither would compile.\n"
                    + "\n"
                    + "The quotes matter too: an IP address contains dots, and "
                    + "without quotes Java would try to read 10.0.4.17 as a number "
                    + "and fail on the second dot.\n"
                    + "\n"
                    + "Notice you could have called it s and this program would "
                    + "behave identically. The name is the only part of a variable "
                    + "that exists purely for humans, which is exactly why it is "
                    + "worth spending a moment on.")
                .explain(
                    "Exact name, capital letters included. Text goes in quotes.")
                .xp(25))
            .mistakes(
                new String[]{"Getting the case wrong",
                    "Declaring failedLogins and using failedlogins gives 'cannot "
                    + "find symbol'. Read the name character by character - the "
                    + "error is usually one capital letter."},
                new String[]{"Spaces or dashes in a name",
                    "failed logins and failed-logins both fail. Join the words "
                    + "with camelCase instead."},
                new String[]{"Names that say nothing",
                    "x, temp, data and thing all compile perfectly. They cost you "
                    + "nothing today and cost the next reader ten minutes."})
            .cyber(
                "An incident is the worst possible time to be reading unfamiliar "
                + "code. At three in the morning, with something live, you will be "
                + "opening a script someone left behind and deciding in minutes "
                + "whether it does what its author claimed.\n"
                + "\n"
                + "Variables called src, s1 and tmp turn that into guesswork. "
                + "Variables called sourceAddress, failedLogins and accountLocked "
                + "let you read it like a sentence. The same goes for code you "
                + "write under pressure and hand to someone else - if they cannot "
                + "read it quickly, they will either rewrite it or run it without "
                + "understanding it, and both are how mistakes get made.")
            .check(new Task(Task.CHOICE,
                    "What is camelCase?")
                .choices(
                    "All capitals with underscores",
                    "First word lower case, later words capitalised, no spaces",
                    "Everything lower case, words joined by dashes",
                    "A rule the compiler enforces")
                .accept("2", "b")
                .hints(
                    "Look at failedLogins and sourceAddress.",
                    "Where are the capitals, and what is at the start?")
                .explain(
                    "First word lower case, every following word capitalised, no "
                    + "separators: failedLogins, sourceAddress. It is a convention, "
                    + "not a compiler rule - but every Java codebase you meet will "
                    + "use it."
                    )
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Why will int class = 3; not compile?")
                .choices(
                    "class is too short",
                    "class is a Java keyword",
                    "Variable names cannot be five letters",
                    "It needs double quotes")
                .accept("2", "b")
                .hints(
                    "The word already means something to Java.",
                    "You have seen it at the top of every program you have written.")
                .explain(
                    "class is a reserved keyword - Java already uses it to mean "
                    + "something, so it cannot double as a variable name. int, "
                    + "new, true, public and the rest are taken in the same way.")
                .xp(20))
            .recap(
                "Enforced: start with a letter, no spaces, no dashes, not a "
                + "keyword.\n"
                + "\n"
                + "Java is CASE SENSITIVE. failedLogins and failedlogins are two "
                + "different variables.\n"
                + "\n"
                + "Convention: camelCase, and a name that says what the value is.\n"
                + "\n"
                + "    sourceAddress    not    s\n"
                + "    failedLogins     not    x2")
            .next("Next: a value the program is not allowed to change."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(8), "A Value That Must Not Move", 2)
            .brief(
                "The lockout threshold is five failures. It is written into the "
                + "tool in four places, and last month somebody changed three of "
                + "them.\n\n"
                + "Some values are meant to stay put. Java lets you say so, and "
                + "then enforces it.")
            .willLearn("final", "Constants", "UPPER_SNAKE_CASE")
            .whyUseful(
                "Thresholds, limits and configured values are exactly the things "
                + "that get changed by accident. Marking them final turns a silent "
                + "bug into a compiler error.")
            .concept("final",
                "Put final in front of a declaration and the value can never be "
                + "replaced:\n"
                + "\n"
                + "    final int LOCKOUT_THRESHOLD = 5;\n"
                + "\n"
                + "After that line, any attempt to assign to it is a compile "
                + "error:\n"
                + "\n"
                + "    LOCKOUT_THRESHOLD = 6;   does NOT compile\n"
                + "\n"
                + "'cannot assign a value to final variable LOCKOUT_THRESHOLD'. "
                + "Not a warning at runtime - a refusal to build at all.\n"
                + "\n"
                + "A final variable that never changes is called a CONSTANT, and "
                + "constants have their own naming convention:\n"
                + "\n"
                + "    UPPER_SNAKE_CASE - every letter capital, words joined by\n"
                + "    underscores.\n"
                + "\n"
                + "        final int LOCKOUT_THRESHOLD = 5;\n"
                + "        final String ALERT_PREFIX = \"[ALERT]\";\n"
                + "        final double CRITICAL_SEVERITY = 9.0;\n"
                + "\n"
                + "That shouting is deliberate. A reader glancing at the code can "
                + "tell instantly which values move and which do not, without "
                + "checking any declarations.\n"
                + "\n"
                + "final works on any type - int, String, boolean, double, char.\n"
                + "\n"
                + "Reading a constant is exactly like reading any other variable. "
                + "The only thing final takes away is the ability to assign to it "
                + "again.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        final int LOCKOUT_THRESHOLD = 5;",
                "        int failedLogins = 3;",
                "        System.out.println(\"FAILURES: \" + failedLogins);",
                "        System.out.println(\"LIMIT:    \" + LOCKOUT_THRESHOLD);",
                "    }",
                "}")
            .exampleOutput("FAILURES: 3", "LIMIT:    5")
            .lineByLine(
                new String[]{"final",
                    "Says this variable gets one value and keeps it. Any later "
                    + "assignment is a compile error."},
                new String[]{"LOCKOUT_THRESHOLD",
                    "Shouting capitals with underscores. The name itself tells a "
                    + "reader it is fixed."},
                new String[]{"int failedLogins = 3;",
                    "No final, normal camelCase. This one is expected to change "
                    + "and the code says so at a glance."},
                new String[]{"Reading the constant",
                    "Exactly like any variable. final restricts writing, not "
                    + "reading."})
            .predict(new Task(Task.DEBUG,
                    "Which line stops this compiling?")
                .code(
                    "final int MAX_ALERTS = 10;",
                    "int alerts = 2;",
                    "MAX_ALERTS = 12;",
                    "System.out.println(alerts);")
                .accept("3", "line 3")
                .hints(
                    "One of these variables was promised never to change.",
                    "Which line breaks that promise?")
                .explain(
                    "Line 3. MAX_ALERTS was declared final on line 1, so assigning "
                    + "to it is refused: 'cannot assign a value to final variable "
                    + "MAX_ALERTS'.\n\n"
                    + "Line 2 is fine - alerts is an ordinary variable and may be "
                    + "changed as often as you like.")
                .xp(20))
            .practice(new Task(Task.CHOICE,
                    "Which of these is the better candidate for final?")
                .choices(
                    "A running count of failed logins",
                    "The lockout threshold of 5",
                    "The username currently being checked",
                    "The number of alerts raised so far")
                .accept("2", "b")
                .hints(
                    "Which one is supposed to stay the same for the whole run?",
                    "Three of these are expected to change as the tool works.")
                .explain(
                    "The lockout threshold. It is a policy decision that holds "
                    + "steady while the tool runs, so nothing should ever assign "
                    + "to it.\n\n"
                    + "The other three all change by design - that is their whole "
                    + "job. Making them final would stop the program working, "
                    + "which is the useful test: if the code cannot do its job "
                    + "without reassigning something, it is not a constant.")
                .xp(25))
            .objective(
                "Pin down the alert threshold so nothing can move it.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // declare the constant here",
                "        int failedLogins = 3;",
                "        System.out.println(failedLogins + \" of \" + ALERT_THRESHOLD);",
                "    }",
                "}")
            .yourTask(
                "The println expects a constant called ALERT_THRESHOLD holding 5, "
                + "and nothing in the program should ever be able to change it.\n\n"
                + "Write the declaration. Match the name exactly.")
            .mainTask(new Task(Task.WRITE,
                    "Declare a constant int called ALERT_THRESHOLD holding 5.")
                .accept(
                    "final int ALERT_THRESHOLD = 5;",
                    "final int ALERT_THRESHOLD = 5")
                .hints(
                    "It is still an ordinary declaration - type, name, value - "
                    + "with one extra word.",
                    "The word that forbids reassignment goes at the very front.",
                    "final int ALERT_THRESHOLD = and then the value and a "
                    + "semicolon.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        final int ALERT_THRESHOLD = 5;",
                    "        int failedLogins = 3;",
                    "        System.out.println(failedLogins + \" of \" + ALERT_THRESHOLD);",
                    "    }",
                    "}")
                .whyItWorks(
                    "final goes in front of the type and makes this the only "
                    + "assignment the variable will ever get. Anything later that "
                    + "tries to change it fails to compile.\n"
                    + "\n"
                    + "The value would print the same without final. What final "
                    + "buys you is the failure - the tool cannot be built at all "
                    + "in a state where something moved the threshold. That is the "
                    + "cheapest kind of safety there is, because it costs one word "
                    + "and catches the problem before the code ever runs.\n"
                    + "\n"
                    + "The shouting name does the other half of the job. Someone "
                    + "reading ALERT_THRESHOLD three hundred lines away knows it "
                    + "is fixed without going to look.")
                .explain(
                    "final in front, UPPER_SNAKE_CASE for the name.")
                .xp(30))
            .mistakes(
                new String[]{"Assigning to a final variable",
                    "Any assignment after the declaration is 'cannot assign a "
                    + "value to final variable'. If you genuinely need it to "
                    + "change, it was not a constant."},
                new String[]{"final after the type",
                    "int final MAX = 5; does not compile. final comes first."},
                new String[]{"camelCase on a constant",
                    "final int alertThreshold = 5; compiles and works. It just "
                    + "reads like an ordinary variable, which throws away half the "
                    + "benefit."})
            .cyber(
                "Security tools are full of numbers that decide something: the "
                + "lockout threshold, the session timeout, the minimum key length, "
                + "the severity that triggers a page.\n"
                + "\n"
                + "The danger with those is not that someone changes them "
                + "maliciously. It is that the same number is typed in five places "
                + "and four get updated. A lockout threshold of 5 in the counting "
                + "code and 50 in the alerting code is not a crash - it is a tool "
                + "that quietly stops catching anything, and looks perfectly "
                + "healthy while it does so. One named constant, marked final, "
                + "read from everywhere, makes that particular disagreement "
                + "impossible to write down.")
            .check(new Task(Task.CHOICE,
                    "What does final do to a variable?")
                .choices(
                    "Deletes it at the end of the program",
                    "Stops its value being changed after it is set",
                    "Makes it faster",
                    "Hides it from other code")
                .accept("2", "b")
                .hints(
                    "Think about what the compiler refuses to do.",
                    "It is about assignment.")
                .explain(
                    "It stops the value being reassigned. One value, set at the "
                    + "declaration, and any later assignment is a compile error.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which name follows the convention for a constant?")
                .choices(
                    "maxAlerts",
                    "MAX_ALERTS",
                    "Max_Alerts",
                    "MAXALERTS")
                .accept("2", "b")
                .hints(
                    "Every letter capital.",
                    "Words separated by underscores so it stays readable.")
                .explain(
                    "MAX_ALERTS. All capitals with underscores between the words. "
                    + "maxAlerts is the convention for an ordinary variable, and "
                    + "MAXALERTS is hard to read once there are three words in it.")
                .xp(20))
            .recap(
                "    final int LOCKOUT_THRESHOLD = 5;\n"
                + "\n"
                + "final means this value is set once and never reassigned. Trying "
                + "to change it is a compile error, not a runtime surprise.\n"
                + "\n"
                + "Constants are named in UPPER_SNAKE_CASE so a reader can spot "
                + "them without checking the declaration.\n"
                + "\n"
                + "One constant beats the same number typed in five places.")
            .next("Next: doing arithmetic with the numbers you have stored."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(9), "Arithmetic", 2)
            .brief(
                "The tool holds counts. Now it has to work with them: total the "
                + "failures across two hosts, subtract the ones already handled, "
                + "work out how far a score is from the threshold.\n\n"
                + "Four operators, and one rule about the order they run in.")
            .willLearn("Arithmetic operators", "Operator precedence", "Expressions")
            .whyUseful(
                "Totals, differences and scores are the arithmetic of nearly every "
                + "security tool. Getting the order of operations wrong produces a "
                + "number that is confidently, quietly wrong.")
            .concept("Arithmetic operators",
                "Four of them, on numbers:\n"
                + "\n"
                + "    +    add\n"
                + "    -    subtract\n"
                + "    *    multiply  (a star, not an x)\n"
                + "    /    divide    (a forward slash)\n"
                + "\n"
                + "Anything that works out to a value is an EXPRESSION. These all "
                + "are:\n"
                + "\n"
                + "    3 + 4\n"
                + "    failedLogins * 2\n"
                + "    webFailures + dbFailures - handled\n"
                + "\n"
                + "An expression can go anywhere a value can go - on the right of "
                + "an =, or straight inside a println:\n"
                + "\n"
                + "    int total = webFailures + dbFailures;\n"
                + "    System.out.println(total * 2);\n"
                + "\n"
                + "Neither of those changes webFailures or dbFailures. Working out "
                + "a value never disturbs the variables it read. Only an "
                + "assignment changes what is in a box.\n"
                + "\n"
                + "PRECEDENCE - the order things happen in:\n"
                + "\n"
                + "    * and / run BEFORE + and -\n"
                + "\n"
                + "    2 + 3 * 4    is 14, not 20\n"
                + "\n"
                + "The 3 * 4 happens first, then the 2 is added. Same rule you "
                + "were taught for maths on paper.\n"
                + "\n"
                + "Brackets override it, and cost nothing:\n"
                + "\n"
                + "    (2 + 3) * 4    is 20\n"
                + "\n"
                + "Use them whenever the order is not instantly obvious. Code that "
                + "needs the reader to recall a precedence table is code that gets "
                + "misread.\n"
                + "\n"
                + "Careful with + : between two numbers it adds, but as soon as "
                + "one side is text it JOINS. You have already seen it do both.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int webFailures = 12;",
                "        int dbFailures = 7;",
                "        int total = webFailures + dbFailures;",
                "        System.out.println(\"TOTAL: \" + total);",
                "        System.out.println(\"DOUBLED: \" + total * 2);",
                "    }",
                "}")
            .exampleOutput("TOTAL: 19", "DOUBLED: 38")
            .lineByLine(
                new String[]{"int total = webFailures + dbFailures;",
                    "The right side is worked out first - 12 + 7 is 19 - and the "
                    + "result goes into total. webFailures and dbFailures are "
                    + "unchanged."},
                new String[]{"\"TOTAL: \" + total",
                    "This + joins, because the left side is text. The same symbol, "
                    + "a completely different job."},
                new String[]{"\"DOUBLED: \" + total * 2",
                    "The * runs first, giving 38, and only then does the + join it "
                    + "onto the text. Precedence decides which + you get."},
                new String[]{"What is NOT changed",
                    "Nothing in either println assigns to anything. total is still "
                    + "19 after the second line prints 38."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int alerts = 2 + 3 * 4;",
                    "System.out.println(alerts);")
                .accept("14", "fourteen")
                .hints(
                    "Which operator runs first?",
                    "* before +, so the 3 * 4 happens before the 2 is added.")
                .explain(
                    "14. The * runs first: 3 * 4 is 12, then 2 + 12 is 14.\n\n"
                    + "Reading strictly left to right would give 20, and that is "
                    + "the mistake. Java follows the same precedence rules as "
                    + "arithmetic on paper.")
                .xp(20))
            .practice(new Task(Task.PREDICT,
                    "Two very similar lines. What are the TWO lines of output?")
                .code(
                    "int a = 5;",
                    "int b = 3;",
                    "System.out.println(a + b);",
                    "System.out.println(\"a + b\");")
                .accept("8 a + b", "8, a + b", "8 and a + b")
                .hints(
                    "The first println has no quotes, so it works something out.",
                    "The second is entirely inside quotes, so nothing is worked "
                    + "out at all.")
                .explain(
                    "    8\n"
                    + "    a + b\n\n"
                    + "Line 3 has no quotes, so Java works out the expression and "
                    + "prints the result. Line 4 is text from start to finish - "
                    + "Java never looks inside quotes for variables or operators, "
                    + "so it prints the characters exactly as written.")
                .xp(30))
            .objective(
                "Total the failures from both hosts.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int webFailures = 9;",
                "        int dbFailures = 4;",
                "        // work out the total here",
                "        System.out.println(\"TOTAL: \" + total);",
                "    }",
                "}")
            .yourTask(
                "Declare an int called total holding the two counts added "
                + "together.\n\n"
                + "Add the variables, not the numbers - the tool will not know the "
                + "counts in advance.")
            .mainTask(new Task(Task.WRITE,
                    "Declare an int called total holding webFailures + dbFailures.")
                .accept(
                    "int total = webFailures + dbFailures;",
                    "int total = webFailures + dbFailures",
                    "int total = dbFailures + webFailures;",
                    "int total = dbFailures + webFailures")
                .hints(
                    "It is a new variable, so it needs a type.",
                    "The value on the right is an expression, not a plain number.",
                    "int total = and then the two variable names with a + between "
                    + "them.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int webFailures = 9;",
                    "        int dbFailures = 4;",
                    "        int total = webFailures + dbFailures;",
                    "        System.out.println(\"TOTAL: \" + total);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The right side is worked out before anything is stored. Java "
                    + "reads the current values - 9 and 4 - adds them to get 13, "
                    + "and puts 13 into the new box.\n"
                    + "\n"
                    + "Both sides are ints, so the + adds. Had either been a "
                    + "String, the same symbol would have joined them into \"94\" "
                    + "instead, and nothing would have warned you.\n"
                    + "\n"
                    + "Writing int total = 13; gives the same output today and "
                    + "breaks the moment either count changes. Adding the "
                    + "variables means the total is always true of whatever the "
                    + "counts currently hold - which is the difference between a "
                    + "tool and a typed-out answer.")
                .explain(
                    "An expression can go on the right of any declaration.")
                .xp(30))
            .mistakes(
                new String[]{"Assuming left to right",
                    "2 + 3 * 4 is 14, not 20. Multiply and divide go first. "
                    + "Brackets make it obvious and cost nothing."},
                new String[]{"Using x for multiply",
                    "3 x 4 does not compile - x looks like a variable name. The "
                    + "operator is a star: 3 * 4."},
                new String[]{"Expecting maths inside quotes",
                    "println(\"2 + 2\") prints 2 + 2. Java never evaluates what is "
                    + "inside double quotes."})
            .cyber(
                "Risk scores are arithmetic. A tool that weighs severity against "
                + "exposure and asset value is multiplying and adding, and the "
                + "order it does that in changes every number it produces.\n"
                + "\n"
                + "That is what makes an arithmetic bug worse than a crash. A "
                + "crash announces itself. A missing pair of brackets produces a "
                + "score that is plausible, sorted into a queue, and acted on - "
                + "and the finding that should have been at the top sits fourteenth "
                + "for a month. Nobody investigates a number that looks reasonable, "
                + "which is precisely why you put the brackets in.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int n = (2 + 3) * 4;",
                    "System.out.println(n);")
                .accept("20", "twenty")
                .hints(
                    "The brackets change the order.",
                    "Whatever is inside them is worked out first.")
                .explain(
                    "20. The brackets force the addition first: 2 + 3 is 5, then "
                    + "5 * 4 is 20. Without them it would be 14.")
                .xp(20))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int hosts = 4;",
                    "System.out.println(hosts * 2);",
                    "System.out.println(hosts);")
                .accept("8 4", "8, 4", "8 and 4")
                .hints(
                    "Working out a value does not change the variable it read.",
                    "Was there any assignment on line 2?")
                .explain(
                    "    8\n"
                    + "    4\n\n"
                    + "Line 2 works out 4 * 2 and prints 8, but nothing was "
                    + "assigned, so hosts still holds 4. Only an = changes what is "
                    + "in a box.")
                .xp(25))
            .recap(
                "    +  -  *  /      and brackets to force the order\n"
                + "\n"
                + "* and / run before + and -, so 2 + 3 * 4 is 14.\n"
                + "\n"
                + "An expression can go anywhere a value can. Working one out "
                + "never changes the variables it read.\n"
                + "\n"
                + "+ adds two numbers and joins anything involving text.")
            .next("Next: what happens when a division does not come out even."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(10), "When Division Does Not Come Out Even", 2)
            .brief(
                "The tool divides 7 alerts between 2 analysts and reports 3 each. "
                + "Seven alerts, two analysts, three each - one alert is "
                + "unaccounted for, and nothing warned anybody.\n\n"
                + "This is the most dangerous thing in the campaign so far, "
                + "because it does not crash and does not warn.")
            .willLearn("Integer division", "The remainder operator", "Losing data silently")
            .whyUseful(
                "Averages, rates, percentages and anything split between things "
                + "all go through a division. Integer division throws away the "
                + "fraction without a word, and the result looks perfectly normal.")
            .concept("Integer division and %",
                "Divide two ints and you get an int. The fraction is not rounded "
                + "- it is thrown away:\n"
                + "\n"
                + "    7 / 2     is 3, not 3.5\n"
                + "    9 / 10    is 0, not 0.9\n"
                + "    19 / 20   is 0\n"
                + "\n"
                + "No error. No warning. The .5 simply never existed.\n"
                + "\n"
                + "It happens because BOTH sides are ints, so Java does the whole "
                + "calculation in whole numbers. Make either side a double and you "
                + "get a decimal answer:\n"
                + "\n"
                + "    7 / 2       is 3\n"
                + "    7.0 / 2     is 3.5\n"
                + "    7 / 2.0     is 3.5\n"
                + "\n"
                + "One decimal point anywhere in the expression is enough.\n"
                + "\n"
                + "This catches people out even when the box is a double:\n"
                + "\n"
                + "    double rate = 7 / 2;     rate holds 3.0\n"
                + "\n"
                + "The division happened first, between two ints, giving 3. Only "
                + "then was the 3 put into a double box. The type of the box does "
                + "not reach back and change how the sum was done.\n"
                + "\n"
                + "THE REMAINDER OPERATOR - % - gives you what was left over:\n"
                + "\n"
                + "    7 / 2    is 3     how many times 2 goes in\n"
                + "    7 % 2    is 1     what is left after that\n"
                + "\n"
                + "    10 % 2   is 0     nothing left, so 10 is even\n"
                + "    10 % 3   is 1\n"
                + "\n"
                + "It is not a percent sign here, whatever the symbol looks like. "
                + "It is called modulo, and it runs at the same precedence as * "
                + "and /.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int alerts = 7;",
                "        int analysts = 2;",
                "        System.out.println(\"EACH:      \" + alerts / analysts);",
                "        System.out.println(\"LEFT OVER: \" + alerts % analysts);",
                "        System.out.println(\"EXACT:     \" + 7.0 / analysts);",
                "    }",
                "}")
            .exampleOutput("EACH:      3", "LEFT OVER: 1", "EXACT:     3.5")
            .lineByLine(
                new String[]{"alerts / analysts",
                    "Two ints, so an int answer: 3. The remaining half alert is "
                    + "discarded with no warning at all."},
                new String[]{"alerts % analysts",
                    "The leftover: 1. Between them, / and % account for everything "
                    + "- three each and one spare."},
                new String[]{"7.0 / analysts",
                    "One side is a double, so the whole division is done in "
                    + "decimals and gives 3.5."},
                new String[]{"Why this is worth a whole mission",
                    "All three lines compile, run, and print a believable number. "
                    + "Only one of them is the answer you wanted, and nothing in "
                    + "the output tells you which."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int alerts = 9;",
                    "int analysts = 4;",
                    "System.out.println(alerts / analysts);")
                .accept("2", "two")
                .hints(
                    "Both variables are ints.",
                    "9 divided by 4 is 2.25 - but an int answer cannot hold the "
                    + ".25.")
                .explain(
                    "2. Two ints give an int result, so the .25 is thrown away. "
                    + "Not rounded to 2.25 and not rounded up to 3 - simply cut "
                    + "off.")
                .xp(20))
            .practice(new Task(Task.PREDICT,
                    "This one is the trap. What does it print?")
                .code(
                    "int total = 9;",
                    "int count = 4;",
                    "double average = total / count;",
                    "System.out.println(average);")
                .accept("2.0")
                .hints(
                    "Work out the right side of line 3 completely before you look "
                    + "at the type on the left.",
                    "Both total and count are ints, so what does that division "
                    + "produce? The box type cannot change a sum that already "
                    + "happened.")
                .explain(
                    "2.0.\n\n"
                    + "The division ran first, between two ints, and gave 2 - the "
                    + ".25 was gone at that point. Storing 2 in a double just "
                    + "prints it as 2.0.\n\n"
                    + "Declaring the box a double does not reach backwards and "
                    + "redo the sum. To get 2.25 one side of the division has to "
                    + "be a double: total / 4.0, or a conversion you will meet "
                    + "later.\n\n"
                    + "This is the single most common numeric bug in beginner Java "
                    + "and it never announces itself - 2.0 looks like a real "
                    + "answer.")
                .xp(35))
            .objective(
                "Find out how many alerts are left over after the split.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int alerts = 11;",
                "        int analysts = 3;",
                "        System.out.println(\"EACH: \" + alerts / analysts);",
                "        // print the leftover here",
                "    }",
                "}")
            .yourTask(
                "Each analyst takes 3 alerts and some are left unassigned.\n\n"
                + "Write one println that prints the leftover - just the number, "
                + "no label.")
            .mainTask(new Task(Task.WRITE,
                    "Print the remainder of alerts divided by analysts.")
                .accept(
                    "System.out.println(alerts % analysts);",
                    "System.out.println(alerts % analysts)")
                .hints(
                    "There is an operator for exactly this and it is not / .",
                    "The remainder operator is the % symbol.",
                    "Same shape as the line above it, with % instead of / and no "
                    + "label.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int alerts = 11;",
                    "        int analysts = 3;",
                    "        System.out.println(\"EACH: \" + alerts / analysts);",
                    "        System.out.println(alerts % analysts);",
                    "    }",
                    "}")
                .whyItWorks(
                    "11 / 3 is 3, and 11 % 3 is 2. Three each accounts for nine "
                    + "alerts, and % tells you the other two exist.\n"
                    + "\n"
                    + "That is the real point of this mission. The / on its own "
                    + "produces a number that looks complete - three each, job "
                    + "done - and two alerts nobody is looking at. The % is how "
                    + "the program finds out what its own division threw away.\n"
                    + "\n"
                    + "Whenever you divide ints, ask what happens to the "
                    + "remainder. Sometimes discarding it is right. It should "
                    + "always be a decision you made, not one the language made "
                    + "quietly on your behalf.")
                .explain(
                    "/ gives how many fit, % gives what is left.")
                .xp(35))
            .mistakes(
                new String[]{"Expecting a decimal from two ints",
                    "7 / 2 is 3. To get 3.5, one side must be a double: 7.0 / 2."},
                new String[]{"Thinking the box type fixes it",
                    "double d = 7 / 2; holds 3.0. The division already happened in "
                    + "ints before anything was stored."},
                new String[]{"Reading % as percent",
                    "It is the remainder operator. 50 % 3 is 2, and has nothing to "
                    + "do with percentages."})
            .cyber(
                "Silent data loss is a category of bug worth learning to fear "
                + "early, and integer division is your first example of it. The "
                + "program does not crash, the output looks sensible, and the "
                + "error only surfaces when someone compares two reports that "
                + "should agree.\n"
                + "\n"
                + "Real consequences follow from exactly this shape of mistake. An "
                + "alert queue divided between analysts leaves a remainder nobody "
                + "owns. A detection rate of 19 out of 20 computed as 19/20 in "
                + "ints reports zero percent coverage and looks like a broken "
                + "sensor. A retention period worked out in whole days rounds an "
                + "investigation window down and deletes the evidence a day early.\n"
                + "\n"
                + "None of those is caught by testing that only checks the program "
                + "runs. They are caught by someone who knows the question to ask.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(10 % 4);")
                .accept("2", "two")
                .hints(
                    "How many times does 4 go into 10, and what is left?",
                    "4 goes in twice, reaching 8.")
                .explain(
                    "2. 4 goes into 10 twice with 2 left over. The / would give "
                    + "the 2 that went in; the % gives the 2 left behind. They are "
                    + "the same number here by coincidence.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "Which expression gives 3.5?")
                .choices(
                    "7 / 2",
                    "7.0 / 2",
                    "(double) in front of nothing",
                    "7 % 2")
                .accept("2", "b")
                .hints(
                    "One side of the division needs a decimal point.",
                    "Look for the option with a .0 in it.")
                .explain(
                    "7.0 / 2. One double in the expression makes the whole "
                    + "division decimal, so you get 3.5. 7 / 2 is 3 and 7 % 2 is "
                    + "the remainder, 1.")
                .xp(20))
            .check(new Task(Task.RECALL,
                    "Fill the gap. To get a decimal answer from a division, at "
                    + "least one side must be a ____ .")
                .accept("double", "a double", "decimal")
                .hints(
                    "It is a type name.",
                    "The type you met for numbers with a point.")
                .explain(
                    "double. Two ints give an int answer no matter what you store "
                    + "it in, so the decimal has to be in the expression itself.")
                .xp(15))
            .recap(
                "    7 / 2    is 3      the fraction is thrown away, silently\n"
                + "    7 % 2    is 1      what was left over\n"
                + "    7.0 / 2  is 3.5    one double makes it decimal\n"
                + "\n"
                + "    double d = 7 / 2;  holds 3.0 - the sum ran in ints first\n"
                + "\n"
                + "No error, no warning, a believable wrong number. Whenever you "
                + "divide ints, decide what happens to the remainder.")
            .next("Next: the shorthand Java gives you for changing a variable "
                + "by a small amount."));
    }
}
