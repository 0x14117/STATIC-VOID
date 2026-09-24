/**
 * CAMPAIGN 01 - JAVA ZERO
 * Java from zero. Thirty missions.
 *
 * Campaign 00 produced fixed text. Nothing could be remembered and nothing
 * could change. This campaign gives programs memory: variables, the types
 * they come in, and the operators that work on them.
 *
 * Still no conditions and no loops - those are Campaigns 02 and 04. A mission
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
            .willLearn("Reassignment", "Assignment", "Using a variable's own value")
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
                    "This does not compile. Which line is wrong?")
                .code(
                    "char a = 'I';",
                    "char b = \"W\";",
                    "char c = 'E';")
                .accept("2", "line 2")
                .hints(
                    "Lines 1 and 3 are correct - use them as your reference.",
                    "Compare the quote marks.")
                .explain(
                    "Line 2. \"W\" in double quotes is a String, and a String "
                    + "does not fit in a char, even one that is a single letter "
                    + "long. 'incompatible types: String cannot be converted to "
                    + "char'.\n\n"
                    + "The value looks right, which is exactly why this one gets "
                    + "past people. Java goes by the quote marks, not by how "
                    + "many letters are inside them.")
                .xp(20))
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

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(11), "Shorthand for a Running Total", 2)
            .brief(
                "The audit tool keeps a running total of failures across every "
                + "host it checks. Each host adds its count to the total.\n\n"
                + "You already know how to write that. Java also has a shorter "
                + "way, and you will see it in almost every program you read.")
            .willLearn("Compound assignment", "+= and -=", "*= and /=")
            .whyUseful(
                "Running totals, scores that go up and down, budgets being spent "
                + "- all of them are a variable changed by an amount. The short "
                + "form is what real code uses, so you need to read it fluently.")
            .concept("Compound assignment",
                "You met this shape in mission 3:\n"
                + "\n"
                + "    total = total + webFailures;\n"
                + "\n"
                + "The name appears twice. Java lets you write it once:\n"
                + "\n"
                + "    total += webFailures;\n"
                + "\n"
                + "Both lines do EXACTLY the same thing. += means 'add this to "
                + "what is already there'. There is one for each operator:\n"
                + "\n"
                + "    x += 5;    same as    x = x + 5;\n"
                + "    x -= 5;    same as    x = x - 5;\n"
                + "    x *= 5;    same as    x = x * 5;\n"
                + "    x /= 5;    same as    x = x / 5;\n"
                + "\n"
                + "The two characters are written together with no space. + = "
                + "with a gap does not compile.\n"
                + "\n"
                + "Everything you learned still applies. /= on an int is still "
                + "integer division, so the fraction still vanishes.\n"
                + "\n"
                + "+= works on Strings too, where it joins:\n"
                + "\n"
                + "    String report = \"FAILED:\";\n"
                + "    report += \" jsmith\";\n"
                + "\n"
                + "report now holds FAILED: jsmith.\n"
                + "\n"
                + "One trap. The order of the two symbols matters:\n"
                + "\n"
                + "    total += 5;    adds 5 to total\n"
                + "    total =+ 5;    sets total to +5\n"
                + "\n"
                + "The second one compiles. Java reads it as total = +5, a "
                + "positive five, and your running total is wiped.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int total = 0;",
                "        total += 12;",
                "        total += 7;",
                "        total -= 4;",
                "        System.out.println(\"OPEN FAILURES: \" + total);",
                "    }",
                "}")
            .exampleOutput("OPEN FAILURES: 15")
            .lineByLine(
                new String[]{"int total = 0;",
                    "A running total starts at zero, before anything has been "
                    + "counted."},
                new String[]{"total += 12;",
                    "Adds 12 to what is there. 0 becomes 12."},
                new String[]{"total += 7;",
                    "12 becomes 19. Same line shape, different result, because "
                    + "the starting value changed."},
                new String[]{"total -= 4;",
                    "Four were resolved, so take them off. 19 becomes 15."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int score = 10;",
                    "score += 5;",
                    "score -= 3;",
                    "System.out.println(score);")
                .accept("12", "twelve")
                .hints(
                    "Trace the box after each line.",
                    "10, then 15, then...")
                .explain(
                    "12. 10 plus 5 is 15, then 15 minus 3 is 12. Each line works "
                    + "from whatever the previous line left behind.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "The same shorthand on text. What does this print?")
                .code(
                    "String log = \"LOGIN\";",
                    "log += \" FAILED\";",
                    "log += \" jsmith\";",
                    "System.out.println(log);")
                .accept("LOGIN FAILED jsmith")
                .hints(
                    "On a String, += joins rather than adds.",
                    "Each piece brings its own leading space.")
                .explain(
                    "LOGIN FAILED jsmith. On a String, += joins the new text onto "
                    + "the end. The spaces are there because each added piece "
                    + "starts with one - += adds nothing of its own, just like "
                    + "+.")
                .xp(20))
            .objective(
                "Add the database server's failures to the running total.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int total = 9;",
                "        int dbFailures = 4;",
                "        // add dbFailures to total here",
                "        System.out.println(\"TOTAL: \" + total);",
                "    }",
                "}")
            .yourTask(
                "total already holds the web server's 9. Add dbFailures to it "
                + "using the shorthand from this mission.")
            .mainTask(new Task(Task.WRITE,
                    "Add dbFailures to total using the shorthand operator.")
                .accept("total += dbFailures;", "total += dbFailures")
                .hints(
                    "You are changing total, so total goes on the left.",
                    "The operator is + and = written together, in that order.",
                    "total += and then the variable being added, and a semicolon.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int total = 9;",
                    "        int dbFailures = 4;",
                    "        total += dbFailures;",
                    "        System.out.println(\"TOTAL: \" + total);",
                    "    }",
                    "}")
                .whyItWorks(
                    "total += dbFailures means total = total + dbFailures. Java "
                    + "reads the current total, 9, adds 4, and stores 13 back in "
                    + "total. dbFailures is read, not changed.\n"
                    + "\n"
                    + "The long form would have been just as correct. The short "
                    + "form exists because the variable being updated is named "
                    + "once, so there is no way to update one variable while "
                    + "accidentally reading from a similarly named one - total "
                    + "= totals + dbFailures is a bug the shorthand cannot "
                    + "contain.")
                .explain(
                    "The variable being changed goes on the left, the amount on "
                    + "the right.")
                .xp(25))
            .mistakes(
                new String[]{"=+ instead of +=",
                    "total =+ 5; compiles and sets total to 5, throwing away the "
                    + "running total. The + comes first."},
                new String[]{"A space between the symbols",
                    "total + = 5; does not compile. The two characters are one "
                    + "operator and must touch."},
                new String[]{"Forgetting /= is integer division",
                    "int n = 7; n /= 2; leaves 3. The shorthand does not change "
                    + "the rules of the operator inside it."})
            .cyber(
                "Almost every metric on a security dashboard is a running total: "
                + "bytes out per host, alerts per rule, failed logins per source. "
                + "They are updated thousands of times, each update a += of some "
                + "small amount.\n"
                + "\n"
                + "That volume is why the =+ typo is worth remembering. A total "
                + "that is reset on every update always looks small and calm. "
                + "An exfiltration of gigabytes, recorded as 'the size of the "
                + "last packet', never crosses a threshold. The program runs, "
                + "the dashboard is green, and the attack is invisible because "
                + "of a pair of characters written the wrong way round.")
            .check(new Task(Task.CHOICE,
                    "Which line is the same as  hosts = hosts * 2;")
                .choices("hosts =* 2;", "hosts *= 2;", "hosts * = 2;", "hosts ** 2;")
                .accept("2", "b")
                .hints(
                    "The operator comes first, then the =.",
                    "No space between the two symbols.")
                .explain(
                    "hosts *= 2; - operator then =, touching. hosts =* 2 does not "
                    + "compile, hosts * = 2 does not compile, and ** is not a Java "
                    + "operator at all.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int budget = 20;",
                    "budget /= 3;",
                    "System.out.println(budget);")
                .accept("6", "six")
                .hints(
                    "budget /= 3 means budget = budget / 3.",
                    "Both sides are ints. What happens to the fraction?")
                .explain(
                    "6. 20 / 3 is 6.66..., but both sides are ints so the "
                    + "fraction is thrown away. The shorthand follows exactly the "
                    + "same rules as the long form.")
                .xp(20))
            .recap(
                "    x += 5;    x = x + 5\n"
                + "    x -= 5;    x = x - 5\n"
                + "    x *= 5;    x = x * 5\n"
                + "    x /= 5;    x = x / 5    still integer division\n"
                + "\n"
                + "On a String, += joins.\n"
                + "\n"
                + "Operator first, then =, touching. x =+ 5 compiles and sets x "
                + "to 5.")
            .next("Next: the even shorter way to count by one."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(12), "Counting by One", 2)
            .brief(
                "Most counting in a security tool goes up by exactly one. One "
                + "more failed login. One more alert. One more blocked "
                + "connection.\n\n"
                + "It happens so often that Java gives it its own operator.")
            .willLearn("++", "--", "Counting by one")
            .whyUseful(
                "Counting one event at a time is the most common operation in "
                + "detection code. ++ is how it is written everywhere, and you "
                + "will be reading it from now until the end of the course.")
            .concept("++ and --",
                "Three ways to add one, all doing the same thing:\n"
                + "\n"
                + "    failedLogins = failedLogins + 1;\n"
                + "    failedLogins += 1;\n"
                + "    failedLogins++;\n"
                + "\n"
                + "++ means 'add one'. -- means 'take one away':\n"
                + "\n"
                + "    activeSessions--;\n"
                + "\n"
                + "These are called INCREMENT and DECREMENT.\n"
                + "\n"
                + "They work on number variables. They do not work on a String "
                + "or a boolean, and they do not work on a final variable, since "
                + "they are a kind of assignment.\n"
                + "\n"
                + "They do not work on a plain number either. 5++ means nothing, "
                + "because there is no box to put the answer in.\n"
                + "\n"
                + "For now, always write ++ and -- as a statement on their own "
                + "line, exactly as above. They can also appear inside a larger "
                + "expression, where they behave in a way that surprises almost "
                + "everyone. That comes much later, once you have the tools to "
                + "see what is happening. On its own line there is no surprise: "
                + "the variable goes up by one.\n"
                + "\n"
                + "A tempting alternative that does NOT work:\n"
                + "\n"
                + "    failedLogins + 1;\n"
                + "\n"
                + "That works out a value and then does nothing with it. Java "
                + "refuses it: 'not a statement'. Adding one to a number is not "
                + "the same as storing the result.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int alerts = 0;",
                "        alerts++;",
                "        alerts++;",
                "        alerts++;",
                "        alerts--;",
                "        System.out.println(\"OPEN ALERTS: \" + alerts);",
                "    }",
                "}")
            .exampleOutput("OPEN ALERTS: 2")
            .lineByLine(
                new String[]{"int alerts = 0;",
                    "Counters start at zero."},
                new String[]{"alerts++; three times",
                    "Three alerts arrive. 0, 1, 2, 3."},
                new String[]{"alerts--;",
                    "One is closed. Back to 2."},
                new String[]{"No = anywhere after the declaration",
                    "++ and -- store the result themselves. That is the whole "
                    + "point of them."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int blocked = 5;",
                    "blocked++;",
                    "blocked++;",
                    "blocked--;",
                    "System.out.println(blocked);")
                .accept("6", "six")
                .hints(
                    "Two up, one down.",
                    "5, 6, 7, then back one.")
                .explain(
                    "6. Up to 6, up to 7, down to 6. Each line works on the value "
                    + "the one before left behind.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line stops this compiling?")
                .code(
                    "final int MAX_SESSIONS = 3;",
                    "int sessions = 0;",
                    "sessions++;",
                    "MAX_SESSIONS++;")
                .accept("4", "line 4")
                .hints(
                    "++ is a kind of assignment.",
                    "Which variable promised never to be assigned again?")
                .explain(
                    "Line 4. ++ changes the variable it is attached to, and "
                    + "MAX_SESSIONS is final - it cannot be changed by any means, "
                    + "shorthand included. 'cannot assign a value to final "
                    + "variable MAX_SESSIONS'.")
                .xp(20))
            .objective(
                "Record one more failed login.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failedLogins = 4;",
                "        // one more failure has arrived",
                "        System.out.println(failedLogins);",
                "    }",
                "}")
            .yourTask(
                "Add one to failedLogins using the shortest form Java has.")
            .mainTask(new Task(Task.WRITE,
                    "Increase failedLogins by one with the increment operator.")
                .accept("failedLogins++;", "failedLogins++")
                .hints(
                    "It is shorter than += 1.",
                    "Two plus signs.",
                    "The variable's name, then ++, then a semicolon.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int failedLogins = 4;",
                    "        failedLogins++;",
                    "        System.out.println(failedLogins);",
                    "    }",
                    "}")
                .whyItWorks(
                    "failedLogins++ reads the value, adds one, and stores the "
                    + "result back, all in one step. 4 becomes 5 and the println "
                    + "shows 5.\n"
                    + "\n"
                    + "It is exactly failedLogins = failedLogins + 1 written in "
                    + "the form every Java programmer recognises at a glance. "
                    + "When you read a detection rule and see a ++, you now know "
                    + "one event has just been counted.")
                .explain(
                    "Name, then ++. It adds one and stores the result.")
                .xp(25))
            .mistakes(
                new String[]{"Writing x + 1; on its own",
                    "It works out a value and throws it away. Java rejects it as "
                    + "'not a statement'. Use x++ or x = x + 1."},
                new String[]{"++ on a final variable",
                    "It is an assignment, so it is refused just like any other "
                    + "assignment to a final."},
                new String[]{"Using ++ inside a bigger expression",
                    "Legal, but it has a twist you have not learned yet. Keep it "
                    + "on its own line for now."})
            .cyber(
                "Brute-force detection is, at bottom, a ++ and a comparison. "
                + "Each failure increments a counter for that account or source "
                + "address; something checks whether the counter has reached "
                + "the threshold.\n"
                + "\n"
                + "Where the ++ sits is a security decision. Count per account "
                + "and an attacker trying one password against ten thousand "
                + "accounts - password spraying - never pushes any single "
                + "counter above 1. Count per source address and they spread "
                + "across a botnet. The increment itself is trivial; choosing "
                + "what it counts is the actual design work.")
            .check(new Task(Task.CHOICE,
                    "Which of these does NOT add one to count?")
                .choices("count++;", "count += 1;", "count = count + 1;", "count + 1;")
                .accept("4", "d")
                .hints(
                    "Three of these store a result. One does not.",
                    "Where does the answer go in each one?")
                .explain(
                    "count + 1; works out a value and has nowhere to put it, so "
                    + "Java refuses it as 'not a statement'. The other three all "
                    + "store count plus one back into count.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int sessions = 3;",
                    "sessions--;",
                    "sessions--;",
                    "sessions--;",
                    "System.out.println(sessions);")
                .accept("0", "zero")
                .hints(
                    "Three takeaways from three.",
                    "Count down one line at a time.")
                .explain(
                    "0. Three decrements from 3. Nothing stops it going further "
                    + "- a fourth -- would give -1, and Java would not object, "
                    + "because it has no idea a session count cannot be negative. "
                    + "Only you know that.")
                .xp(15))
            .recap(
                "    count++;    add one\n"
                + "    count--;    take one away\n"
                + "\n"
                + "Same as count = count + 1, just shorter.\n"
                + "\n"
                + "Numbers only, never on a final, never on a plain number.\n"
                + "\n"
                + "Keep it on its own line for now.")
            .next("Next: what happens when an int and a double meet in the "
                + "same calculation."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(13), "When Whole Meets Decimal", 3)
            .brief(
                "The tool reports alerts per hour. The alert count is a whole "
                + "number. The time window is 2.5 hours. What type is the "
                + "answer?\n\n"
                + "Java has a rule for this, and it is the key to fixing the "
                + "division problem from mission 10.")
            .willLearn("Mixed arithmetic", "Type promotion")
            .whyUseful(
                "Rates, averages and ratios nearly always mix a count with a "
                + "measurement. Knowing what type the result will be tells you "
                + "whether a fraction survives or is silently lost.")
            .concept("Mixed arithmetic",
                "When an operator has an int on one side and a double on the "
                + "other, Java first turns the int into a double, then does the "
                + "sum in decimals:\n"
                + "\n"
                + "    5 * 1.5     5 becomes 5.0, answer 7.5\n"
                + "    7 / 2.0     7 becomes 7.0, answer 3.5\n"
                + "    3 + 0.0     3 becomes 3.0, answer 3.0\n"
                + "\n"
                + "This is called PROMOTION. It always goes towards the type that "
                + "can hold more, so nothing is lost. The int is never harmed by "
                + "becoming a double.\n"
                + "\n"
                + "The rule applies one operator at a time, in precedence order. "
                + "Each step looks only at its own two sides:\n"
                + "\n"
                + "    10 / 4 * 2.0\n"
                + "\n"
                + "    step 1   10 / 4     both ints, so 2\n"
                + "    step 2   2 * 2.0    one double, so 4.0\n"
                + "\n"
                + "The double arrived too late. By the time it appeared, the "
                + "division had already happened in ints and the .5 was gone. "
                + "Whether a fraction survives depends on WHEN the double joins "
                + "the calculation, not whether it is somewhere in the line.\n"
                + "\n"
                + "And the result of mixed arithmetic is a double, even when it "
                + "happens to be whole:\n"
                + "\n"
                + "    int result = 2 * 1.5;    does NOT compile\n"
                + "\n"
                + "2 * 1.5 is 3.0, a double, and Java will not put a double in an "
                + "int box. It checks the TYPE of the expression, not whether "
                + "this particular value would fit.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int alerts = 7;",
                "        double hours = 2.0;",
                "        double rate = alerts / hours;",
                "        System.out.println(\"ALERTS/HOUR: \" + rate);",
                "    }",
                "}")
            .exampleOutput("ALERTS/HOUR: 3.5")
            .lineByLine(
                new String[]{"int alerts = 7;",
                    "A count - whole, so an int."},
                new String[]{"double hours = 2.0;",
                    "A measurement - could be 2.5 tomorrow, so a double."},
                new String[]{"alerts / hours",
                    "One side is a double, so 7 is promoted to 7.0 and the "
                    + "division is done in decimals. 3.5."},
                new String[]{"double rate",
                    "The result is a double, so it needs a double box. An int "
                    + "here would not compile."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int hosts = 3;",
                    "double load = 1.5;",
                    "System.out.println(hosts * load);")
                .accept("4.5")
                .hints(
                    "One side is a double.",
                    "3 becomes 3.0, then multiply.")
                .explain(
                    "4.5. hosts is promoted to 3.0, and 3.0 * 1.5 is 4.5. The "
                    + "result is a double.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "Take this one step at a time. What does it print?")
                .code("System.out.println(10 / 4 * 2.0);")
                .accept("4.0")
                .hints(
                    "/ and * have the same precedence, so they run left to "
                    + "right.",
                    "Step 1 is 10 / 4. What types are on each side of it?")
                .explain(
                    "4.0, not 5.0.\n"
                    + "\n"
                    + "    step 1   10 / 4    both ints   2\n"
                    + "    step 2   2 * 2.0   mixed       4.0\n"
                    + "\n"
                    + "The .5 was lost in step 1, before any double was involved. "
                    + "Promotion only helps the operator it takes part in. "
                    + "Writing 10 / 4.0 * 2 instead would give 5.0, because the "
                    + "double joins at the division.")
                .xp(30))
            .objective(
                "Work out the alert rate across a window measured in hours.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int alerts = 9;",
                "        double hours = 2.0;",
                "        // work out the rate here",
                "        System.out.println(rate);",
                "    }",
                "}")
            .yourTask(
                "Declare rate holding alerts divided by hours.\n\n"
                + "Pick the type that can hold what that division produces.")
            .mainTask(new Task(Task.WRITE,
                    "Declare rate as alerts divided by hours.")
                .accept("double rate = alerts / hours;", "double rate = alerts / hours")
                .hints(
                    "One side of the division is a double. What type is the "
                    + "result?",
                    "Mixed arithmetic always gives a double.",
                    "double rate = and then the division.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int alerts = 9;",
                    "        double hours = 2.0;",
                    "        double rate = alerts / hours;",
                    "        System.out.println(rate);",
                    "    }",
                    "}")
                .whyItWorks(
                    "hours is a double, so alerts is promoted and the division is "
                    + "done in decimals: 9.0 / 2.0 is 4.5. The result is a double, "
                    + "so it goes in a double box.\n"
                    + "\n"
                    + "Compare mission 10, where total / count were both ints and "
                    + "the average came out as 2.0. The difference is not the box "
                    + "on the left. It is that a double is already INSIDE the "
                    + "division here, so there is nothing for Java to throw "
                    + "away.\n"
                    + "\n"
                    + "int rate = alerts / hours; would not compile, and that is "
                    + "Java protecting the .5.")
                .explain(
                    "A double in the division means a double result.")
                .xp(25))
            .mistakes(
                new String[]{"Thinking a double anywhere in the line is enough",
                    "10 / 4 * 2.0 is 4.0. Promotion only applies to the "
                    + "operator the double is part of."},
                new String[]{"Storing a mixed result in an int",
                    "int n = 2 * 1.5; does not compile, even though the value is "
                    + "whole. Java checks the type, not the value."},
                new String[]{"Expecting a whole-looking result to print whole",
                    "3 + 0.0 prints 3.0. The result is a double, so it prints "
                    + "like one."})
            .cyber(
                "Rates are how anomalies are found. Normal is 3 failed logins an "
                + "hour; tonight it is 300. Normal outbound traffic is 2.1 GB a "
                + "day; this host sent 40.\n"
                + "\n"
                + "A rate computed in whole numbers is coarse enough to hide in. "
                + "If a baseline of 0.4 alerts per minute is stored as 0, then "
                + "any rate above zero looks infinitely unusual and the rule "
                + "fires constantly - and a rule that fires constantly gets "
                + "switched off. Getting the type right is what keeps a "
                + "detection sensitive enough to be useful and quiet enough to "
                + "be trusted.")
            .check(new Task(Task.CHOICE,
                    "What type is the result of  4 + 1.0 ?")
                .choices("int", "double", "It depends on the box", "String")
                .accept("2", "b")
                .hints(
                    "One side is a double.",
                    "Promotion goes towards the type that holds more.")
                .explain(
                    "double. The 4 is promoted to 4.0 and the answer is 5.0. The "
                    + "type of an expression comes from what is in it, never from "
                    + "the box it is later stored in.")
                .xp(10))
            .check(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "double a = 3 * 2;",
                    "int b = 3 * 2;",
                    "int c = 3 * 2.0;")
                .accept("3", "line 3")
                .hints(
                    "Work out the TYPE of each right-hand side.",
                    "A double result cannot go into an int box.")
                .explain(
                    "Line 3. 3 * 2.0 is a double, even though its value is 6.0, "
                    + "and a double does not fit in an int.\n\n"
                    + "Line 1 is fine: 3 * 2 is the int 6, and an int fits safely "
                    + "into a double box as 6.0.")
                .xp(20))
            .recap(
                "int with double: the int is promoted and the result is a "
                + "double.\n"
                + "\n"
                + "    7 / 2.0         3.5\n"
                + "    10 / 4 * 2.0    4.0  - the division ran in ints first\n"
                + "\n"
                + "Promotion works one operator at a time. The double has to be "
                + "part of the division to save the fraction.\n"
                + "\n"
                + "Java checks the type of an expression, not its value.")
            .next("Next: converting a value to another type on purpose."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(14), "Converting on Purpose", 3)
            .brief(
                "Mission 10 left a bug unsolved. total and count are both ints, "
                + "so total / count throws the fraction away, and neither of them "
                + "should be changed to a double - they are counts.\n\n"
                + "What you need is a way to say 'treat this one as a double, "
                + "just for this sum'.")
            .willLearn("Casting", "(int) and (double)", "Truncation")
            .whyUseful(
                "Real data rarely arrives in the type you want. Casting is how "
                + "you convert deliberately - and knowing exactly what a cast "
                + "does to a value is how you avoid it quietly damaging your "
                + "numbers.")
            .concept("Casting",
                "A CAST converts a value to another type. The type goes in "
                + "brackets in front of the value:\n"
                + "\n"
                + "    (double) total    the value of total, as a double\n"
                + "    (int) 9.8         the value 9.8, as an int\n"
                + "\n"
                + "A cast does not change the variable. total is still an int "
                + "holding the same number. The cast produces a converted COPY "
                + "for this one use.\n"
                + "\n"
                + "Fixing mission 10's average:\n"
                + "\n"
                + "    double average = (double) total / count;\n"
                + "\n"
                + "A cast binds tighter than any arithmetic operator, so it "
                + "happens first, to total alone. Now the division has a double "
                + "on one side, promotion does the rest, and the fraction "
                + "survives.\n"
                + "\n"
                + "Where the cast sits is everything:\n"
                + "\n"
                + "    (double) total / count      9.0 / 4    2.25\n"
                + "    (double) (total / count)    (double) 2   2.0\n"
                + "\n"
                + "The second one divides in ints FIRST, because the brackets "
                + "say so, and then converts the already-damaged 2.\n"
                + "\n"
                + "Casting a double to an int does not round. It cuts off "
                + "everything after the point:\n"
                + "\n"
                + "    (int) 9.2     9\n"
                + "    (int) 9.99    9\n"
                + "    (int) -2.7    -2    towards zero, not down\n"
                + "\n"
                + "This is called TRUNCATION. Java lets you do it because you "
                + "asked explicitly - the cast is you saying 'I know I am losing "
                + "the fraction'. Without the cast, int n = 9.99; is refused.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int total = 9;",
                "        int count = 4;",
                "        double average = (double) total / count;",
                "        System.out.println(\"AVERAGE: \" + average);",
                "        System.out.println(\"WHOLE:   \" + (int) average);",
                "    }",
                "}")
            .exampleOutput("AVERAGE: 2.25", "WHOLE:   2")
            .lineByLine(
                new String[]{"(double) total",
                    "The cast happens first. 9 becomes 9.0 for this sum only. "
                    + "total itself is still the int 9."},
                new String[]{"/ count",
                    "Now a double divided by an int. count is promoted and the "
                    + "answer is 2.25."},
                new String[]{"(int) average",
                    "Converts 2.25 to an int by cutting off the fraction. 2."},
                new String[]{"average afterwards",
                    "Still 2.25. The cast made a copy; the variable was never "
                    + "touched."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "double score = 9.99;",
                    "System.out.println((int) score);")
                .accept("9", "nine")
                .hints(
                    "A cast to int does not round.",
                    "Everything after the decimal point is cut off.")
                .explain(
                    "9. Casting to int truncates - 9.99 loses its .99 and becomes "
                    + "9, not 10. If you want rounding, that is a different tool, "
                    + "coming soon.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "Two lines, one bracket's difference. What are the TWO lines "
                    + "of output?")
                .code(
                    "int total = 9;",
                    "int count = 4;",
                    "System.out.println((double) (total / count));",
                    "System.out.println((double) total / count);")
                .accept("2.0 2.25", "2.0, 2.25", "2.0 and 2.25")
                .hints(
                    "On line 3, the brackets force the division to happen before "
                    + "the cast.",
                    "On line 4, the cast applies to total alone, before the "
                    + "division.")
                .explain(
                    "    2.0\n"
                    + "    2.25\n"
                    + "\n"
                    + "Line 3 divides first, in ints, giving 2, then converts 2 to "
                    + "2.0. The cast came too late to help.\n"
                    + "\n"
                    + "Line 4 converts total to 9.0 first, so the division is "
                    + "decimal and gives 2.25. Same parts, different order, "
                    + "different answer.")
                .xp(30))
            .objective(
                "Fix the average-failures-per-host report.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int failures = 17;",
                "        int hosts = 4;",
                "        // work out the average here",
                "        System.out.println(average);",
                "    }",
                "}")
            .yourTask(
                "Declare average as failures divided by hosts, keeping the "
                + "fraction. Do not change either declaration - they are counts "
                + "and should stay ints.")
            .mainTask(new Task(Task.WRITE,
                    "Declare average, casting so the fraction survives.")
                .accept(
                    "double average = (double) failures / hosts;",
                    "double average = (double) failures / hosts",
                    "double average = (double)failures / hosts;",
                    "double average = (double)failures / hosts",
                    "double average = failures / (double) hosts;",
                    "double average = failures / (double) hosts",
                    "double average = failures / (double)hosts;",
                    "double average = failures / (double)hosts")
                .hints(
                    "The answer has a fraction, so the box is a double.",
                    "One side of the division must be a double BEFORE the "
                    + "division happens.",
                    "double average = (double) failures / hosts;  - the cast goes "
                    + "directly in front of one variable.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int failures = 17;",
                    "        int hosts = 4;",
                    "        double average = (double) failures / hosts;",
                    "        System.out.println(average);",
                    "    }",
                    "}")
                .whyItWorks(
                    "(double) failures converts 17 to 17.0 before anything else "
                    + "happens, because a cast binds tighter than /. The division "
                    + "then has a double on one side, hosts is promoted, and the "
                    + "answer is 4.25.\n"
                    + "\n"
                    + "failures and hosts are untouched. They are still ints, still "
                    + "counts, still correct for everything else that uses them. "
                    + "The cast converted a copy for this one sum.\n"
                    + "\n"
                    + "(double) (failures / hosts) would print 4.0: the brackets "
                    + "make the int division happen first, and the cast only "
                    + "dresses up the damage. Casting either variable works; "
                    + "casting the result does not.")
                .explain(
                    "Cast one operand, not the result. Then the division is "
                    + "decimal.")
                .xp(35))
            .mistakes(
                new String[]{"Casting the result instead of an operand",
                    "(double) (a / b) divides in ints first. Put the cast on a "
                    + "or on b."},
                new String[]{"Expecting (int) to round",
                    "(int) 9.99 is 9. A cast cuts off; it never rounds up."},
                new String[]{"Thinking a cast changes the variable",
                    "(double) total gives a converted copy. total is still an "
                    + "int afterwards."})
            .cyber(
                "Truncation is a quiet way for numbers to move across a line "
                + "that matters.\n"
                + "\n"
                + "A service promises 99.5% availability. Measured uptime is "
                + "99.7%, comfortably inside. A report casts it to an int for a "
                + "tidy dashboard and shows 99 - a breach that never happened, "
                + "and an incident call for nothing. Turn it round and a 4.9% "
                + "false-negative rate shown as 4 passes a 'below 5' audit it "
                + "should not have.\n"
                + "\n"
                + "Neither of those is a crash. The cast did exactly what it "
                + "says. The mistake was not deciding, on purpose, what the "
                + "fraction was worth.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println((int) -2.7);")
                .accept("-2")
                .hints(
                    "Truncation cuts off the fraction.",
                    "It moves towards zero, not downwards.")
                .explain(
                    "-2. The .7 is cut off, which moves the value towards zero. "
                    + "Rounding down would have given -3, and that is not what a "
                    + "cast does.")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "With int a = 7 and int b = 2, which gives 3.5?")
                .choices(
                    "(double) (a / b)",
                    "(double) a / b",
                    "(int) a / b",
                    "a / b")
                .accept("2", "b")
                .hints(
                    "The double has to exist before the division runs.",
                    "Which option casts an operand rather than the result?")
                .explain(
                    "(double) a / b. The cast converts a to 7.0 first, then the "
                    + "division is decimal. Casting the result gives 3.0, and the "
                    + "other two stay entirely in ints.")
                .xp(20))
            .recap(
                "    (double) total    a double copy of total\n"
                + "    (int) 9.99        9 - cut off, never rounded\n"
                + "\n"
                + "A cast binds tighter than arithmetic, so it applies to the "
                + "value right after it.\n"
                + "\n"
                + "    (double) a / b      fraction survives\n"
                + "    (double) (a / b)    fraction already lost\n"
                + "\n"
                + "The variable itself never changes type.")
            .next("Next: what happens when a number is too big for its box."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(15), "Too Big for the Box", 3)
            .brief(
                "The firewall counts every byte it lets out. By Wednesday the "
                + "counter shows a negative number of bytes.\n\n"
                + "Nothing crashed. Every box has a size limit, and this one "
                + "was full.")
            .willLearn("Integer overflow", "long", "int range")
            .whyUseful(
                "Byte counts, timestamps and totals over long periods outgrow an "
                + "int far sooner than you would expect. When they do, Java does "
                + "not stop - it wraps round silently.")
            .concept("Overflow and long",
                "An int has a fixed size, so it has a largest value:\n"
                + "\n"
                + "    2147483647    about 2.1 billion\n"
                + "\n"
                + "You do not need to memorise it. Java has a named constant - "
                + "final, in UPPER_SNAKE_CASE, like the ones you write:\n"
                + "\n"
                + "    Integer.MAX_VALUE    2147483647\n"
                + "    Integer.MIN_VALUE    -2147483648\n"
                + "\n"
                + "Add one to the largest int and you do not get an error. You "
                + "get the SMALLEST int:\n"
                + "\n"
                + "    int bytes = Integer.MAX_VALUE;\n"
                + "    bytes++;\n"
                + "    System.out.println(bytes);    -2147483648\n"
                + "\n"
                + "This is OVERFLOW. The value wraps round like a mileage "
                + "counter rolling over, and nothing tells you.\n"
                + "\n"
                + "When a value could get that big, use a LONG. It is a whole "
                + "number type like int, with a vastly bigger range - about 9.2 "
                + "quintillion.\n"
                + "\n"
                + "    long bytesSent = 5000000000L;\n"
                + "\n"
                + "Note the L on the end. A plain whole number written in your "
                + "code is treated as an int, and 5000000000 is too big to BE "
                + "an int, so without the L it does not compile: 'integer "
                + "number too large'. The L says 'this number is a long'. Use a "
                + "capital - a small l looks like the digit 1.\n"
                + "\n"
                + "A long can still overflow. It just takes a great deal "
                + "longer.\n"
                + "\n"
                + "So which to use? int for anything with a sensible upper "
                + "limit - ports, users, attempts. long for anything that "
                + "accumulates without one - bytes, milliseconds, totals over "
                + "months.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int small = Integer.MAX_VALUE;",
                "        small++;",
                "        long large = Integer.MAX_VALUE;",
                "        large++;",
                "        System.out.println(\"int:  \" + small);",
                "        System.out.println(\"long: \" + large);",
                "    }",
                "}")
            .exampleOutput("int:  -2147483648", "long: 2147483648")
            .lineByLine(
                new String[]{"int small = Integer.MAX_VALUE;",
                    "The biggest value an int can hold."},
                new String[]{"small++;",
                    "One too many. It wraps round to the most negative int. No "
                    + "error, no warning."},
                new String[]{"long large = Integer.MAX_VALUE;",
                    "The same starting value in a long box. An int always fits "
                    + "into a long, so no L is needed here."},
                new String[]{"large++;",
                    "Plenty of room left. It simply becomes 2147483648."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int counter = Integer.MAX_VALUE;",
                    "counter++;",
                    "System.out.println(counter);")
                .accept("-2147483648")
                .hints(
                    "counter starts at the largest int there is.",
                    "Going past the top wraps round to the bottom.")
                .explain(
                    "-2147483648, which is Integer.MIN_VALUE. The counter did not "
                    + "stop and did not crash. It wrapped round to the most "
                    + "negative int and carried on as if that were a normal "
                    + "number.")
                .xp(20))
            .practice(new Task(Task.CHOICE,
                    "Which of these should be a long rather than an int?")
                .choices(
                    "The number of open ports on one host",
                    "Failed logins for one account today",
                    "Total bytes the firewall has let out this year",
                    "The lockout threshold")
                .accept("3", "c")
                .hints(
                    "Which one has no sensible upper limit?",
                    "A busy network moves more than 2.1 billion bytes in an "
                    + "afternoon.")
                .explain(
                    "Total bytes this year. 2.1 billion bytes is about 2 GB, "
                    + "which a single server can send before lunch. The other "
                    + "three have natural limits far below an int's range - a "
                    + "host has at most 65535 ports.")
                .xp(20))
            .objective(
                "Give the outbound byte counter a box big enough for a year.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        // declare bytesSent here",
                "        System.out.println(\"BYTES OUT: \" + bytesSent);",
                "    }",
                "}")
            .yourTask(
                "Declare bytesSent holding 5000000000 - five billion, more than "
                + "an int can hold. Choose the type, and remember what a number "
                + "that size needs on the end.")
            .mainTask(new Task(Task.WRITE,
                    "Declare bytesSent holding 5000000000.")
                .accept("long bytesSent = 5000000000L;", "long bytesSent = 5000000000L",
                        "long bytesSent = 5000000000l;", "long bytesSent = 5000000000l")
                .hints(
                    "Five billion is more than Integer.MAX_VALUE, so not an int.",
                    "The bigger whole-number type is long.",
                    "long bytesSent = 5000000000L;  - the L makes the number "
                    + "itself a long.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        long bytesSent = 5000000000L;",
                    "        System.out.println(\"BYTES OUT: \" + bytesSent);",
                    "    }",
                    "}")
                .whyItWorks(
                    "A long has room for about 9.2 quintillion, so five billion "
                    + "fits with enormous space to spare.\n"
                    + "\n"
                    + "The L matters separately from the type. Java reads "
                    + "5000000000 in your code before it looks at the box on the "
                    + "left, and a plain whole number is assumed to be an int. "
                    + "Five billion cannot be an int, so without the L the line "
                    + "fails with 'integer number too large' - even though the "
                    + "box would have held it. The L tells Java what the number "
                    + "IS, and then it fits.")
                .explain(
                    "long for the box, and L on a number too big to be an int.")
                .xp(30))
            .mistakes(
                new String[]{"Forgetting the L",
                    "long n = 5000000000; does not compile: 'integer number too "
                    + "large'. The number itself must be marked as a long."},
                new String[]{"Expecting Java to warn about overflow",
                    "It does not. int arithmetic wraps round silently. You have "
                    + "to choose a type big enough in advance."},
                new String[]{"A small l",
                    "5000000000l is legal and looks like 50000000001. Always use "
                    + "a capital L."})
            .cyber(
                "Integer overflow is a recognised class of vulnerability, not "
                + "just a bug.\n"
                + "\n"
                + "In languages that manage memory by hand, an overflowed size "
                + "calculation can allocate a tiny buffer and then write a huge "
                + "amount into it - one of the oldest routes to running an "
                + "attacker's code. Java protects memory, so here the damage is "
                + "to logic instead: a byte counter that wraps negative sits "
                + "far below any exfiltration threshold, and a rate limiter "
                + "whose counter wraps round lets the flood through as if it "
                + "had just started.\n"
                + "\n"
                + "Systems that stored time as a 32-bit count of seconds since "
                + "1970 run out in January 2038. Somebody chose that box size "
                + "decades ago, reasonably, for a program nobody expected to "
                + "still be running.")
            .check(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "long a = 42;",
                    "long b = 3000000000;",
                    "long c = 3000000000L;")
                .accept("2", "line 2")
                .hints(
                    "Is 3000000000 small enough to be an int?",
                    "Which line is missing its L?")
                .explain(
                    "Line 2. Three billion is bigger than Integer.MAX_VALUE, so it "
                    + "cannot be a plain int number, and it has no L. 'integer "
                    + "number too large'.\n\n"
                    + "Line 1 is fine: 42 is a perfectly good int, and an int fits "
                    + "into a long box.")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "What happens when an int goes past Integer.MAX_VALUE?")
                .choices(
                    "The program crashes",
                    "It stays at the maximum",
                    "It wraps round to the most negative int",
                    "It becomes a long automatically")
                .accept("3", "c")
                .hints(
                    "Remember the counter in the example.",
                    "Java does not stop you, and does not change the type.")
                .explain(
                    "It wraps round to Integer.MIN_VALUE, with no error and no "
                    + "warning. That silence is what makes overflow dangerous.")
                .xp(15))
            .recap(
                "    Integer.MAX_VALUE    2147483647\n"
                + "\n"
                + "One past the top wraps to the most negative int. Silently.\n"
                + "\n"
                + "    long bytesSent = 5000000000L;\n"
                + "\n"
                + "long for values that accumulate without limit. Capital L on "
                + "any number too big to be an int.")
            .next("Next: asking a String a question about itself."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(16), "Asking a String a Question", 2)
            .brief(
                "The login service passes every username it receives to the "
                + "audit tool. Most are six or seven characters long. This "
                + "morning one of them was four hundred.\n\n"
                + "Nobody has a four-hundred-character username. Something is "
                + "probing the login form. To notice, the tool has to be able to "
                + "ask how long a piece of text is.")
            .willLearn("length()", "Calling a method on a String", "The dot")
            .whyUseful(
                "A String is not just a box of text. It can answer questions "
                + "about itself and hand back changed versions of itself. From "
                + "here to the end of this campaign, you are learning to use "
                + "those abilities.")
            .concept("length()",
                "Every String can tell you how many characters it holds:\n"
                + "\n"
                + "    String username = \"jsmith\";\n"
                + "    int size = username.length();\n"
                + "\n"
                + "size now holds 6.\n"
                + "\n"
                + "Read  username.length()  in three parts:\n"
                + "\n"
                + "    username    the String you are asking\n"
                + "    .           'use something belonging to it'\n"
                + "    length()    what you are asking it to do\n"
                + "\n"
                + "length is a METHOD - a named piece of behaviour that belongs "
                + "to the String. The brackets on the end are how you CALL it, "
                + "which means 'do it now'. They are required even though "
                + "nothing goes inside them.\n"
                + "\n"
                + "You have been calling a method since your first program: "
                + "println belongs to System.out, and the dots in "
                + "System.out.println mean exactly the same thing. Writing "
                + "methods of your own is Campaign 03. For now you are using "
                + "the ones String already has.\n"
                + "\n"
                + "length() hands back an int, so you can store it, print it or "
                + "do arithmetic with it like any other int.\n"
                + "\n"
                + "EVERY character counts - letters, digits, dots, and spaces:\n"
                + "\n"
                + "    \"admin\"       5\n"
                + "    \"10.0.0.1\"    8\n"
                + "    \"a b\"         3\n"
                + "    \"\"            0    the empty String\n"
                + "\n"
                + "The empty String holds no characters at all. It is still a "
                + "String, and its length is 0.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String username = \"contractor\";",
                "        int size = username.length();",
                "        System.out.println(username + \" is \" + size + \" long\");",
                "    }",
                "}")
            .exampleOutput("contractor is 10 long")
            .lineByLine(
                new String[]{"username.length()",
                    "Asks the String in username how many characters it has. It "
                    + "answers 10."},
                new String[]{"int size = ...",
                    "The answer is an int, so it goes in an int box like any "
                    + "other whole number."},
                new String[]{"The brackets",
                    "They make it a call. username.length without them does not "
                    + "compile - Java looks for a variable called length and "
                    + "finds none."},
                new String[]{"username afterwards",
                    "Unchanged. Asking a question does not alter the thing you "
                    + "asked."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String role = \"admin\";",
                    "System.out.println(role.length());")
                .accept("5", "five")
                .hints(
                    "Count the characters inside the quotes.",
                    "a, d, m, i, n.")
                .explain(
                    "5. The quotes are not part of the String, so they are not "
                    + "counted.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "Every character counts. What does this print?")
                .code(
                    "String address = \"10.0.4.17\";",
                    "System.out.println(address.length());")
                .accept("9", "nine")
                .hints(
                    "Dots are characters too.",
                    "Six digits and three dots.")
                .explain(
                    "9. 1, 0, ., 0, ., 4, ., 1, 7. A String does not know that "
                    + "this text is an address, or that dots are separators. It "
                    + "counts characters, all of them.")
                .xp(20))
            .objective(
                "Measure the username the login service just passed in.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String username = \"svc-backup-legacy\";",
                "        // measure it here",
                "        System.out.println(\"LENGTH: \" + nameLength);",
                "    }",
                "}")
            .yourTask(
                "Declare an int called nameLength holding the length of "
                + "username. Ask the String - do not count it yourself.")
            .mainTask(new Task(Task.WRITE,
                    "Declare nameLength holding the length of username.")
                .accept("int nameLength = username.length();",
                        "int nameLength = username.length()")
                .hints(
                    "length() gives back a whole number, so the box is an int.",
                    "Ask username with a dot, then the method name.",
                    "int nameLength = username.length();  - brackets included.")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String username = \"svc-backup-legacy\";",
                    "        int nameLength = username.length();",
                    "        System.out.println(\"LENGTH: \" + nameLength);",
                    "    }",
                    "}")
                .whyItWorks(
                    "username.length() asks the String how long it is, and it "
                    + "answers 17. That int goes into nameLength.\n"
                    + "\n"
                    + "Writing int nameLength = 17; would print the same and be "
                    + "useless: it is true of this username only. The audit tool "
                    + "will see a different username every few milliseconds, and "
                    + "only asking the String itself gives an answer that is "
                    + "right for whichever one arrives.")
                .explain(
                    "Ask the String with .length(), brackets and all.")
                .xp(25))
            .mistakes(
                new String[]{"Leaving off the brackets",
                    "username.length does not compile: 'cannot find symbol'. "
                    + "The brackets are what make it a call."},
                new String[]{"A capital L",
                    "username.Length() does not exist. Method names follow "
                    + "camelCase like variables do."},
                new String[]{"Not counting spaces or punctuation",
                    "\"a b\" has length 3. Every character counts, including "
                    + "the ones you cannot see."})
            .cyber(
                "Length is one of the cheapest and most useful signals there "
                + "is. Real usernames, hostnames and search terms have typical "
                + "lengths. Attack payloads usually do not.\n"
                + "\n"
                + "SQL injection strings, script tags, directory traversal "
                + "sequences and buffer overflow attempts all tend to be long, "
                + "because they carry code as well as data. Web application "
                + "firewalls reject fields over a length limit before looking "
                + "at anything else, and a sudden jump in average field length "
                + "in a log is often the first visible sign of automated "
                + "probing.\n"
                + "\n"
                + "It is also why password rules measure length. Twelve "
                + "characters of anything is harder to guess than eight "
                + "characters of cleverness.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String note = \"\";",
                    "System.out.println(note.length());")
                .accept("0", "zero")
                .hints(
                    "There is nothing between the quotes.",
                    "It is still a String. How many characters does it have?")
                .explain(
                    "0. The empty String is a real String with no characters in "
                    + "it. It is not the same as having no String at all - a "
                    + "difference that will matter a great deal later.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String user = \"jsmith \";",
                    "System.out.println(user.length());")
                .accept("7", "seven")
                .hints(
                    "Look very carefully at the end of the text.",
                    "There is a space before the closing quote.")
                .explain(
                    "7. There is a space after jsmith, and a space is a "
                    + "character. You cannot see it when it is printed, but the "
                    + "String knows it is there - and so will any comparison "
                    + "against \"jsmith\". Next-but-one mission deals with "
                    + "exactly this.")
                .xp(20))
            .recap(
                "    username.length()    how many characters, as an int\n"
                + "\n"
                + "The dot means 'use something belonging to this'. The brackets "
                + "mean 'do it now', and are required.\n"
                + "\n"
                + "Every character counts, spaces included. \"\" has length 0.")
            .next("Next: asking a String for a changed version of itself - and "
                + "the trap that comes with it."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(17), "Strings Do Not Change", 3)
            .brief(
                "Two accounts appear in the audit: Admin and admin. To a person "
                + "they are the same name. To Java they are different Strings.\n\n"
                + "The fix is to convert usernames to one case before using "
                + "them. There is a method for that - and a trap in using it "
                + "that catches nearly everyone once.")
            .willLearn("toUpperCase()", "toLowerCase()", "Strings do not change")
            .whyUseful(
                "Comparing names, filtering logs and matching indicators all "
                + "need text in one consistent form. And the rule you learn "
                + "here - a String is never changed, only replaced - applies to "
                + "every String method you will ever call.")
            .concept("Converting case",
                "Two methods hand back a version of the String in one case:\n"
                + "\n"
                + "    String name = \"JSmith\";\n"
                + "    name.toLowerCase()    \"jsmith\"\n"
                + "    name.toUpperCase()    \"JSMITH\"\n"
                + "\n"
                + "Digits, dots and spaces are left as they are. Only letters "
                + "change.\n"
                + "\n"
                + "Now the rule that matters more than either method:\n"
                + "\n"
                + "    A String NEVER changes.\n"
                + "\n"
                + "toLowerCase() does not alter name. It builds a NEW String "
                + "and hands it back. name still holds \"JSmith\". So this line "
                + "does nothing useful at all:\n"
                + "\n"
                + "    name.toLowerCase();\n"
                + "\n"
                + "It makes a lower-case copy and then throws it away, because "
                + "nothing kept it. Java does not warn you - the line is "
                + "legal.\n"
                + "\n"
                + "To keep the result, store it:\n"
                + "\n"
                + "    String clean = name.toLowerCase();    new box\n"
                + "    name = name.toLowerCase();            same box\n"
                + "\n"
                + "The second is ordinary reassignment, exactly like "
                + "x = x + 1. The right side builds the new String, then it is "
                + "stored in name, replacing the old one.\n"
                + "\n"
                + "Types whose values can never be altered are called "
                + "IMMUTABLE. String is one. Every String method you meet - "
                + "this mission and the next three - hands back something new "
                + "and leaves the original exactly as it was.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String name = \"JSmith\";",
                "        name.toLowerCase();",
                "        System.out.println(name);",
                "        name = name.toLowerCase();",
                "        System.out.println(name);",
                "    }",
                "}")
            .exampleOutput("JSmith", "jsmith")
            .lineByLine(
                new String[]{"name.toLowerCase();",
                    "Builds \"jsmith\" and throws it away. name is untouched."},
                new String[]{"The first println",
                    "Still JSmith. The previous line changed nothing."},
                new String[]{"name = name.toLowerCase();",
                    "Builds \"jsmith\" and this time stores it back in name."},
                new String[]{"The second println",
                    "jsmith. The box now holds the new String."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String host = \"web-01\";",
                    "System.out.println(host.toUpperCase());")
                .accept("WEB-01")
                .hints(
                    "Every letter becomes a capital.",
                    "The dash and the digits have no case, so they stay as they "
                    + "are.")
                .explain(
                    "WEB-01. The letters were raised; the dash and the digits "
                    + "were left alone. This line prints the new String directly "
                    + "without storing it, which is fine - it is being used, "
                    + "just not kept.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "The trap. What does this print?")
                .code(
                    "String role = \"admin\";",
                    "role.toUpperCase();",
                    "System.out.println(role);")
                .accept("admin")
                .hints(
                    "Does line 2 store its result anywhere?",
                    "A String never changes. What is still in role?")
                .explain(
                    "admin, in lower case. Line 2 built \"ADMIN\" and discarded "
                    + "it, because nothing stored it. role was never changed, "
                    + "because a String cannot be.\n\n"
                    + "If you answered ADMIN, you have made the most common "
                    + "String mistake in Java, and now you have made it here "
                    + "instead of in a security check.")
                .xp(30))
            .objective(
                "Normalise the username before it goes into the audit.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String username = \"JSmith\";",
                "        // convert username to lower case here",
                "        System.out.println(\"AUDIT: \" + username);",
                "    }",
                "}")
            .yourTask(
                "Change what is in username to its lower-case version, so the "
                + "println shows jsmith. Keep the same variable - do not declare "
                + "a new one.")
            .mainTask(new Task(Task.WRITE,
                    "Replace username with its lower-case version.")
                .accept("username = username.toLowerCase();",
                        "username = username.toLowerCase()")
                .hints(
                    "Calling the method on its own changes nothing. The result "
                    + "has to be stored.",
                    "Store it back in the same variable - reassignment.",
                    "username = username.toLowerCase();")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String username = \"JSmith\";",
                    "        username = username.toLowerCase();",
                    "        System.out.println(\"AUDIT: \" + username);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The right side runs first: username.toLowerCase() builds a "
                    + "new String, \"jsmith\". The = then stores it in username, "
                    + "replacing \"JSmith\".\n"
                    + "\n"
                    + "Writing just username.toLowerCase(); compiles, runs, and "
                    + "prints JSmith - the correct method, called correctly, "
                    + "with its answer thrown on the floor. That is the whole "
                    + "lesson of this mission: with a String, calling the method "
                    + "is only half of it. Keeping the result is the other half.")
                .explain(
                    "Call the method, then store what it hands back.")
                .xp(30))
            .mistakes(
                new String[]{"Calling the method and not storing the result",
                    "name.toLowerCase(); compiles and does nothing. Write "
                    + "name = name.toLowerCase();"},
                new String[]{"Expecting digits or symbols to change",
                    "Only letters have a case. \"web-01\".toUpperCase() is "
                    + "\"WEB-01\"."},
                new String[]{"Forgetting the brackets",
                    "name.toUpperCase without () does not compile. It is a "
                    + "method call, like length()."})
            .cyber(
                "Case is a classic place for security checks to disagree with "
                + "each other.\n"
                + "\n"
                + "A blocklist contains \"admin\". An attacker registers "
                + "\"Admin\". If the blocklist check compares exact text and "
                + "the login system ignores case, both checks pass and the "
                + "attacker holds an account every human reading the logs "
                + "will take for the real one. The same trick works on file "
                + "extensions: a filter that blocks \".exe\" and not \".EXE\" "
                + "blocks nothing on Windows.\n"
                + "\n"
                + "The defence is to normalise first - convert to one case, "
                + "then compare. And the way that defence fails is exactly "
                + "the trap in this mission: a normalising call whose result "
                + "is never stored. The code looks protected. The check runs "
                + "on the raw input.")
            .check(new Task(Task.PREDICT,
                    "What are the TWO lines of output?")
                .code(
                    "String user = \"m.reyes\";",
                    "String loud = user.toUpperCase();",
                    "System.out.println(loud);",
                    "System.out.println(user);")
                .accept("M.REYES m.reyes", "M.REYES, m.reyes", "M.REYES and m.reyes")
                .hints(
                    "loud holds the new String. What does user hold?",
                    "A String never changes.")
                .explain(
                    "    M.REYES\n"
                    + "    m.reyes\n"
                    + "\n"
                    + "The upper-case version was stored in a new variable, "
                    + "loud. user still holds the original, because calling a "
                    + "method on a String never alters it.")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "Which line actually changes what is stored in host?")
                .choices(
                    "host.toUpperCase();",
                    "host = host.toUpperCase();",
                    "String.toUpperCase(host);",
                    "host.toUpperCase() = host;")
                .accept("2", "b")
                .hints(
                    "The result has to be stored.",
                    "Only one of these has host on the left of an =.")
                .explain(
                    "host = host.toUpperCase(); - it builds the new String and "
                    + "stores it in host. Option A throws the result away. C and "
                    + "D do not compile.")
                .xp(15))
            .recap(
                "    name.toUpperCase()    a NEW String in capitals\n"
                + "    name.toLowerCase()    a NEW String in small letters\n"
                + "\n"
                + "A String never changes. Methods hand back a new one.\n"
                + "\n"
                + "    name.toLowerCase();           does nothing useful\n"
                + "    name = name.toLowerCase();    keeps the result")
            .next("Next: the characters you cannot see."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(18), "The Characters You Cannot See", 3)
            .brief(
                "Two accounts in the audit look identical: admin and admin. One "
                + "of them has a space after it.\n\n"
                + "Text typed by people, copied from emails or read from files "
                + "picks up stray spaces at the ends. Java counts every one of "
                + "them.")
            .willLearn("trim()", "Leading and trailing spaces", "Chaining calls")
            .whyUseful(
                "Almost any text that arrives from outside the program - a "
                + "form, a file, a network message - needs its edges cleaned "
                + "before it is compared or stored. trim() is the first step of "
                + "cleaning input.")
            .concept("trim()",
                "trim() hands back a copy with the spaces removed from BOTH "
                + "ends:\n"
                + "\n"
                + "    \"  jsmith  \".trim()      \"jsmith\"\n"
                + "\n"
                + "Spaces at the start are LEADING spaces. Spaces at the end "
                + "are TRAILING spaces. trim() removes both. It also removes "
                + "tabs and line breaks at the ends.\n"
                + "\n"
                + "It does NOT touch anything in the middle:\n"
                + "\n"
                + "    \"  j smith  \".trim()     \"j smith\"\n"
                + "\n"
                + "And like every String method, it hands back a NEW String. "
                + "The original is unchanged, so the result must be stored:\n"
                + "\n"
                + "    String clean = raw.trim();\n"
                + "\n"
                + "CHAINING. A method call on a String gives back a String, and "
                + "you can call another method straight on that:\n"
                + "\n"
                + "    String clean = raw.trim().toLowerCase();\n"
                + "\n"
                + "Read it left to right:\n"
                + "\n"
                + "    raw                   \"  JSmith \"\n"
                + "    raw.trim()            \"JSmith\"\n"
                + "    ....toLowerCase()     \"jsmith\"\n"
                + "\n"
                + "Each call works on what the call before it handed back. raw "
                + "itself is still \"  JSmith \" at the end.\n"
                + "\n"
                + "trim then lower-case is the standard first step for a "
                + "username: remove the invisible edges, then settle the "
                + "case.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String raw = \"  Admin \";",
                "        String clean = raw.trim().toLowerCase();",
                "        System.out.println(\"[\" + raw + \"] \" + raw.length());",
                "        System.out.println(\"[\" + clean + \"] \" + clean.length());",
                "    }",
                "}")
            .exampleOutput("[  Admin ] 8", "[admin] 5")
            .lineByLine(
                new String[]{"raw.trim()",
                    "Hands back \"Admin\" - both ends cleaned."},
                new String[]{".toLowerCase()",
                    "Called on what trim() handed back. Gives \"admin\"."},
                new String[]{"The brackets in the println",
                    "Square brackets around the value make invisible spaces "
                    + "visible. A useful habit when checking text."},
                new String[]{"raw afterwards",
                    "Still 8 characters, spaces and capital A included. Neither "
                    + "call changed it."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String raw = \"  jsmith  \";",
                    "String clean = raw.trim();",
                    "System.out.println(clean.length());")
                .accept("6", "six")
                .hints(
                    "trim() removes the spaces at both ends.",
                    "What is left is just the name.")
                .explain(
                    "6. trim() removed the two leading and two trailing spaces, "
                    + "leaving jsmith - six characters.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String raw = \" a okafor \";",
                    "System.out.println(\"[\" + raw.trim() + \"]\");")
                .accept("[a okafor]")
                .hints(
                    "Which spaces does trim() remove?",
                    "Only the ones at the ends. The one in the middle stays.")
                .explain(
                    "[a okafor]. The spaces at the two ends are gone and the one "
                    + "in the middle survived. trim() cleans edges, it does not "
                    + "remove every space - which is what you want, because "
                    + "sometimes the space in the middle is real.")
                .xp(20))
            .objective(
                "Clean a username that arrived from the web form.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String input = \"  jsmith \";",
                "        // clean the input here",
                "        System.out.println(\"[\" + clean + \"]\");",
                "    }",
                "}")
            .yourTask(
                "Declare a String called clean holding input with the spaces "
                + "removed from both ends.")
            .mainTask(new Task(Task.WRITE,
                    "Declare clean as input with its edges trimmed.")
                .accept("String clean = input.trim();", "String clean = input.trim()")
                .hints(
                    "It is a new String, so declare it with its type.",
                    "The method that removes edge spaces is trim().",
                    "String clean = input.trim();")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String input = \"  jsmith \";",
                    "        String clean = input.trim();",
                    "        System.out.println(\"[\" + clean + \"]\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "input.trim() builds a new String without the spaces at "
                    + "either end, and it is stored in clean. The println shows "
                    + "[jsmith] with nothing inside the brackets but the name.\n"
                    + "\n"
                    + "input is untouched, which is often what you want: keep "
                    + "the raw value for the audit record, exactly as it "
                    + "arrived, and use the clean one for decisions. If someone "
                    + "later asks why a login matched, you can show both.")
                .explain(
                    "trim() returns the cleaned copy. Store it.")
                .xp(25))
            .mistakes(
                new String[]{"Expecting trim() to remove every space",
                    "It only touches the ends. \" a b \".trim() is \"a b\"."},
                new String[]{"Calling it without storing the result",
                    "input.trim(); on its own changes nothing, exactly like "
                    + "toLowerCase() in the last mission."},
                new String[]{"Losing track of a chain",
                    "In raw.trim().toLowerCase(), the second call works on the "
                    + "result of the first, not on raw."})
            .cyber(
                "Invisible characters are an old and still effective trick.\n"
                + "\n"
                + "Some systems compare usernames with trailing spaces ignored "
                + "and store them with the spaces kept - so \"admin \" can be "
                + "registered as a new account, and later be treated as "
                + "\"admin\" by the part of the system that checks passwords. "
                + "Variations of this have produced real account takeovers.\n"
                + "\n"
                + "The defence is to decide on one clean form and convert to "
                + "it the moment input arrives, before any check sees it: "
                + "trim, then settle the case. Every later comparison then "
                + "works on the same text the attacker cannot vary.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String raw = \"  WEB-01 \";",
                    "System.out.println(raw.trim().toLowerCase());")
                .accept("web-01")
                .hints(
                    "Work left to right: trim first.",
                    "Then lower-case what trim handed back.")
                .explain(
                    "web-01. trim() gave \"WEB-01\", then toLowerCase() gave "
                    + "\"web-01\". Each link in the chain works on the result of "
                    + "the one before it.")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "What does trim() remove?")
                .choices(
                    "Every space in the String",
                    "Spaces at the start and end only",
                    "The first and last character",
                    "Capital letters")
                .accept("2", "b")
                .hints(
                    "Think of \" a okafor \".",
                    "Did the middle space survive?")
                .explain(
                    "Spaces - and tabs and line breaks - at the start and end "
                    + "only. Anything in the middle is left as it is.")
                .xp(10))
            .recap(
                "    raw.trim()    a new String, edge spaces removed\n"
                + "\n"
                + "Leading and trailing only. The middle is untouched.\n"
                + "\n"
                + "Calls can be chained. Each one works on what the last one "
                + "handed back:\n"
                + "\n"
                + "    raw.trim().toLowerCase()")
            .next("Next: pulling a single character out of a String by its "
                + "position."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(19), "Counting From Zero", 3)
            .brief(
                "Every line in the application log starts with a single letter: "
                + "I, W or E. The audit tool needs that first letter on its own "
                + "to know how serious the line is.\n\n"
                + "To get one character out of a String you give its position. "
                + "And positions in Java start somewhere most people do not "
                + "expect.")
            .willLearn("charAt()", "Indexes start at 0", "The last index")
            .whyUseful(
                "Positions - called indexes - are how you reach inside text now "
                + "and inside arrays and lists later. Counting from zero is the "
                + "source of more off-by-one bugs than anything else in "
                + "programming, so it pays to get it firmly now.")
            .concept("charAt() and indexes",
                "Every character in a String has a position, called its "
                + "INDEX. The first character is at index 0, not 1:\n"
                + "\n"
                + "    String host = \"DB-01\";\n"
                + "\n"
                + "    index    0   1   2   3   4\n"
                + "    char     D   B   -   0   1\n"
                + "\n"
                + "charAt hands back the character at an index:\n"
                + "\n"
                + "    host.charAt(0)    'D'\n"
                + "    host.charAt(2)    '-'\n"
                + "\n"
                + "The index goes inside the brackets. What comes back is a "
                + "char, the one-character type from mission 6 - not a "
                + "String.\n"
                + "\n"
                + "Because counting starts at 0, the LAST index is one less "
                + "than the length:\n"
                + "\n"
                + "    host.length()                5\n"
                + "    last index                   4\n"
                + "    host.charAt(host.length() - 1)    '1'\n"
                + "\n"
                + "Ask for an index that does not exist and the program "
                + "compiles, starts, and then stops dead when it reaches that "
                + "line:\n"
                + "\n"
                + "    host.charAt(5)\n"
                + "    StringIndexOutOfBoundsException\n"
                + "\n"
                + "That is a crash at RUN time, not a compile error. The "
                + "compiler cannot know how long the String will be. Handling "
                + "crashes like this properly is Campaign 07. For now, the "
                + "defence is knowing the rule: valid indexes go from 0 to "
                + "length() - 1.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String line = \"E login failed for contractor\";",
                "        char level = line.charAt(0);",
                "        char last = line.charAt(line.length() - 1);",
                "        System.out.println(\"LEVEL: \" + level);",
                "        System.out.println(\"LAST:  \" + last);",
                "    }",
                "}")
            .exampleOutput("LEVEL: E", "LAST:  r")
            .lineByLine(
                new String[]{"line.charAt(0)",
                    "The first character - index 0. It comes back as a char, so "
                    + "it goes in a char box."},
                new String[]{"line.length() - 1",
                    "The line is 29 characters long, so the last index is 28. "
                    + "Worked out, not counted by hand."},
                new String[]{"line.charAt(line.length() - 1)",
                    "The last character, whatever the line's length. This shape "
                    + "is worth remembering."},
                new String[]{"What would crash",
                    "line.charAt(line.length()) - one past the end. Always one "
                    + "less than the length."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String level = \"ERROR\";",
                    "System.out.println(level.charAt(1));")
                .accept("R")
                .hints(
                    "The first character is at index 0.",
                    "Index 0 is E. What is at index 1?")
                .explain(
                    "R. Index 0 is E, index 1 is the first R. If you answered E, "
                    + "you counted from 1 - which is the exact mistake this "
                    + "mission exists to fix.")
                .xp(20))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String host = \"WEB-01\";",
                    "System.out.println(host.charAt(host.length() - 1));")
                .accept("1")
                .hints(
                    "Work out host.length() first.",
                    "6 characters, so the last index is 5.")
                .explain(
                    "1. host is 6 characters long, 6 - 1 is 5, and index 5 is "
                    + "the last character: 1. This is how to reach the end of "
                    + "any String without knowing its length in advance.")
                .xp(25))
            .objective(
                "Pull the severity letter off the front of a log line.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String line = \"W disk usage at 91 percent on FILE-01\";",
                "        // take the first character here",
                "        System.out.println(\"SEVERITY: \" + level);",
                "    }",
                "}")
            .yourTask(
                "Declare a char called level holding the first character of "
                + "line.")
            .mainTask(new Task(Task.WRITE,
                    "Declare level holding the first character of line.")
                .accept("char level = line.charAt(0);", "char level = line.charAt(0)")
                .hints(
                    "charAt hands back a char, so that is the type.",
                    "The first character is at index 0, not 1.",
                    "char level = line.charAt(0);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String line = \"W disk usage at 91 percent on FILE-01\";",
                    "        char level = line.charAt(0);",
                    "        System.out.println(\"SEVERITY: \" + level);",
                    "    }",
                    "}")
                .whyItWorks(
                    "Index 0 is the first character, W, and charAt hands it "
                    + "back as a char - which is the type of the box.\n"
                    + "\n"
                    + "charAt(1) would compile, run and give a space: the "
                    + "character after the W. Nothing would crash. The tool "
                    + "would simply report every line's severity as blank, and "
                    + "you would find out when someone asked why no warnings "
                    + "had been raised all week. Counting from 1 does not "
                    + "always fail loudly; sometimes it just quietly reads the "
                    + "wrong thing.")
                .explain(
                    "The first character is at index 0.")
                .xp(25))
            .mistakes(
                new String[]{"Counting from 1",
                    "charAt(1) is the SECOND character. The first is charAt(0)."},
                new String[]{"Using length() as the last index",
                    "s.charAt(s.length()) is one past the end and crashes. The "
                    + "last index is length() - 1."},
                new String[]{"Storing the result in a String",
                    "String c = s.charAt(0); does not compile. charAt gives a "
                    + "char."})
            .cyber(
                "Reading a fixed position in a line is how a great deal of log "
                + "parsing works: severity in column 0, a timestamp in the "
                + "first 19 characters, an address at a known offset.\n"
                + "\n"
                + "It is also fragile against input you do not control. A "
                + "parser that assumes every line has at least 20 characters "
                + "will crash on the first empty or truncated line - and an "
                + "attacker who can get a short line into your logs can stop "
                + "your monitoring with it. Off-by-one reads are a genuine and "
                + "recurring source of vulnerabilities in parsers of every kind. "
                + "Knowing exactly where 0 and length() - 1 are is the first "
                + "defence.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String host = \"DC-01\";",
                    "System.out.println(host.charAt(2));")
                .accept("-")
                .hints(
                    "D is at 0.",
                    "Count 0, 1, 2.")
                .explain(
                    "-. D is at 0, C is at 1, and the dash is at 2. Punctuation "
                    + "has an index just like a letter does.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "What is the last valid index of \"admin\"?")
                .choices("5", "4", "6", "0")
                .accept("2", "b")
                .hints(
                    "\"admin\" has length 5.",
                    "The last index is always length - 1.")
                .explain(
                    "4. Five characters at indexes 0, 1, 2, 3, 4. Index 5 does "
                    + "not exist, and asking for it crashes the program.")
                .xp(15))
            .recap(
                "    s.charAt(0)                first character, as a char\n"
                + "    s.charAt(s.length() - 1)   last character\n"
                + "\n"
                + "Indexes start at 0. The last one is length() - 1.\n"
                + "\n"
                + "Outside that range: a crash at run time, not a compile "
                + "error.")
            .next("Next: cutting a whole piece out of a String."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(20), "Cutting Out a Piece", 4)
            .brief(
                "The authentication log writes every line the same way:\n\n"
                + "    2024-03-11 ERROR login failed for contractor\n\n"
                + "The date is always the first ten characters. The level always "
                + "follows. The audit tool needs to cut those pieces out and "
                + "work with them separately.")
            .willLearn("substring()", "Start and end indexes", "Fixed-width parsing")
            .whyUseful(
                "Pulling fields out of structured text is most of what log "
                + "analysis is. substring is the most direct tool for it, and "
                + "its one odd rule is the source of a great many off-by-one "
                + "errors.")
            .concept("substring()",
                "substring cuts out part of a String and hands it back as a "
                + "new String. Give it where to start and where to stop:\n"
                + "\n"
                + "    s.substring(start, end)\n"
                + "\n"
                + "The odd rule: START IS INCLUDED, END IS NOT. The cut stops "
                + "just BEFORE the end index.\n"
                + "\n"
                + "    String host = \"NORTHSTAR\";\n"
                + "\n"
                + "    index    0 1 2 3 4 5 6 7 8\n"
                + "    char     N O R T H S T A R\n"
                + "\n"
                + "    host.substring(0, 5)    \"NORTH\"  indexes 0 to 4\n"
                + "    host.substring(5, 9)    \"STAR\"   indexes 5 to 8\n"
                + "\n"
                + "There is a useful consequence. The number of characters you "
                + "get is simply end minus start:\n"
                + "\n"
                + "    substring(0, 5)    5 characters\n"
                + "    substring(5, 9)    4 characters\n"
                + "\n"
                + "And one cut's end can be the next cut's start, with nothing "
                + "missed and nothing repeated. That is the reason for the "
                + "rule.\n"
                + "\n"
                + "Give only a start and you get everything from there to the "
                + "end:\n"
                + "\n"
                + "    host.substring(5)       \"STAR\"\n"
                + "\n"
                + "An end equal to length() is allowed, since the end is not "
                + "included. Anything beyond that, or a start after the end, "
                + "crashes at run time just as charAt does.\n"
                + "\n"
                + "Like every String method, the original is left exactly as "
                + "it was.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String line = \"2024-03-11 ERROR login failed\";",
                "        String date = line.substring(0, 10);",
                "        String rest = line.substring(11);",
                "        System.out.println(\"DATE: \" + date);",
                "        System.out.println(\"REST: \" + rest);",
                "    }",
                "}")
            .exampleOutput("DATE: 2024-03-11", "REST: ERROR login failed")
            .lineByLine(
                new String[]{"line.substring(0, 10)",
                    "Indexes 0 to 9 - the ten characters of the date. Index 10 "
                    + "is the space, and it is not included."},
                new String[]{"line.substring(11)",
                    "From index 11 to the end. 11, not 10, to skip the space."},
                new String[]{"10 - 0 is 10",
                    "The date has ten characters. end minus start tells you how "
                    + "long a cut is without counting."},
                new String[]{"line afterwards",
                    "Untouched. Both cuts are new Strings."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String org = \"NORTHSTAR\";",
                    "System.out.println(org.substring(0, 5));")
                .accept("NORTH")
                .hints(
                    "Index 5 is NOT included.",
                    "You get indexes 0, 1, 2, 3 and 4 - five characters.")
                .explain(
                    "NORTH. Indexes 0 to 4. The S at index 5 is where the cut "
                    + "stops, and the stop is not included. 5 - 0 is 5 "
                    + "characters.")
                .xp(20))
            .practice(new Task(Task.PREDICT,
                    "Write out the indexes before you answer. What does this "
                    + "print?")
                .code(
                    "String address = \"10.0.4.17\";",
                    "System.out.println(address.substring(5));")
                .accept("4.17")
                .hints(
                    "Label each character with its index, starting at 0.",
                    "1 is 0, 0 is 1, the dot is 2, 0 is 3, the dot is 4. What is "
                    + "at 5?")
                .explain(
                    "4.17.\n"
                    + "\n"
                    + "    index    0 1 2 3 4 5 6 7 8\n"
                    + "    char     1 0 . 0 . 4 . 1 7\n"
                    + "\n"
                    + "From index 5 to the end. When positions get confusing, "
                    + "write the index row out - it is what experienced "
                    + "programmers do too.")
                .xp(30))
            .objective(
                "Cut the severity level out of an authentication log line.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String line = \"2024-03-11 ERROR login failed for contractor\";",
                "        // cut out the level here",
                "        System.out.println(\"[\" + level + \"]\");",
                "    }",
                "}")
            .yourTask(
                "The date is indexes 0 to 9. Index 10 is a space. The level, "
                + "ERROR, starts at index 11 and is five characters long.\n\n"
                + "Declare a String called level holding just ERROR. Work out "
                + "the end index from the start and the length.")
            .mainTask(new Task(Task.WRITE,
                    "Declare level holding the five characters from index 11.")
                .accept("String level = line.substring(11, 16);",
                        "String level = line.substring(11, 16)")
                .hints(
                    "substring(start, end), and the end is not included.",
                    "Characters you get = end - start. You want 5, starting at "
                    + "11.",
                    "11 + 5 is 16: String level = line.substring(11, 16);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String line = \"2024-03-11 ERROR login failed for contractor\";",
                    "        String level = line.substring(11, 16);",
                    "        System.out.println(\"[\" + level + \"]\");",
                    "    }",
                    "}")
                .whyItWorks(
                    "The start, 11, is included; the end, 16, is not. That gives "
                    + "indexes 11 to 15 - five characters, E R R O R. The "
                    + "brackets in the output show [ERROR] with nothing extra "
                    + "on either side.\n"
                    + "\n"
                    + "The reliable way to get the end is start plus length. "
                    + "Counting characters by eye gives 15 - the index of the "
                    + "last R - and substring(11, 15) quietly returns ERRO. No "
                    + "crash, a truncated value, and a filter looking for ERROR "
                    + "that never matches anything.")
                .explain(
                    "End is start plus length, because the end is not included.")
                .xp(35))
            .mistakes(
                new String[]{"Using the last index as the end",
                    "substring(11, 15) gives four characters. The end is one "
                    + "past the last character you want."},
                new String[]{"Counting from 1",
                    "Every substring position is an index, so the first "
                    + "character is 0."},
                new String[]{"Going past the end",
                    "An end greater than length() crashes at run time. "
                    + "length() itself is allowed."})
            .cyber(
                "Cutting fields out by position is fast and simple, and it "
                + "works right up until a line arrives that is not the shape "
                + "you expected.\n"
                + "\n"
                + "A username with a space in it shifts every field after it. "
                + "A line that was truncated in transit is shorter than your "
                + "end index and crashes the parser. An attacker who can "
                + "control part of a log line - a username, a user agent, a "
                + "requested URL - can often choose exactly what lands at the "
                + "positions your parser reads, and make an ERROR look like "
                + "INFO.\n"
                + "\n"
                + "That is why later campaigns parse more carefully: check the "
                + "line is long enough, split on separators rather than "
                + "trusting positions, and treat anything that does not fit as "
                + "suspicious rather than as data.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String role = \"ADMIN\";",
                    "System.out.println(role.substring(1, 3));")
                .accept("DM")
                .hints(
                    "Indexes 1 and 2. Not 3.",
                    "A is 0, D is 1, M is 2.")
                .explain(
                    "DM. Index 1 is D, index 2 is M, and the cut stops before "
                    + "index 3. 3 - 1 is 2 characters.")
                .xp(20))
            .check(new Task(Task.RECALL,
                    "How many characters does  s.substring(3, 7)  give you? "
                    + "Answer with a number.")
                .accept("4", "four")
                .hints(
                    "There is a shortcut that needs no counting.",
                    "end minus start.")
                .explain(
                    "4. end minus start: 7 - 3. That is the payoff of the "
                    + "end-is-not-included rule - lengths fall straight out of "
                    + "the arithmetic.")
                .xp(15))
            .recap(
                "    s.substring(start, end)   start included, end NOT\n"
                + "    s.substring(start)        from start to the end\n"
                + "\n"
                + "Characters you get = end - start.\n"
                + "To take n characters from start: substring(start, start + n)\n"
                + "\n"
                + "An end past length() crashes at run time.")
            .next("Next: reading what the analyst types, instead of fixing "
                + "every value in the code."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(21), "Asking the Analyst", 3)
            .brief(
                "Every value in the audit tool so far has been typed into the "
                + "code. To check a different host, someone edits the program "
                + "and compiles it again.\n\n"
                + "A tool worth using asks. This mission lets the program wait "
                + "for the analyst to type something, and use what they typed.")
            .willLearn("Scanner", "Reading a line of input", "import")
            .whyUseful(
                "Input is where a program stops being a fixed script and "
                + "starts being a tool. It is also where data from outside - "
                + "data you did not write and cannot trust - first enters your "
                + "code.")
            .concept("Reading input with Scanner",
                "Reading the keyboard takes three lines. Learn them as a "
                + "recipe for now; each part is explained properly later.\n"
                + "\n"
                + "1. Above the class, say where Scanner lives:\n"
                + "\n"
                + "    import java.util.Scanner;\n"
                + "\n"
                + "Scanner is not built into every program the way String "
                + "is. It lives in a library package called java.util, and "
                + "import tells Java to fetch it. Without this line, every "
                + "mention of Scanner is 'cannot find symbol'.\n"
                + "\n"
                + "2. Inside main, make one Scanner attached to the keyboard:\n"
                + "\n"
                + "    Scanner input = new Scanner(System.in);\n"
                + "\n"
                + "System.out is the screen; System.in is the keyboard. The "
                + "word new builds something - Campaign 06 is about exactly "
                + "that. input is just a variable name.\n"
                + "\n"
                + "3. Each time you want a line, ask for one:\n"
                + "\n"
                + "    String host = input.nextLine();\n"
                + "\n"
                + "The program STOPS on this line and waits. When the "
                + "person presses ENTER, everything they typed on that line "
                + "comes back as a String. The ENTER itself is not included; "
                + "spaces they typed are.\n"
                + "\n"
                + "What comes back is ALWAYS a String - even if they type 42. "
                + "Turning it into a number is the next mission.\n"
                + "\n"
                + "Make one Scanner and reuse it for every line. There is no "
                + "reason to make a second.\n"
                + "\n"
                + "Print a prompt with print, not println, so the typing "
                + "happens on the same line as the question.")
            .example(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Host: \");",
                "        String host = input.nextLine();",
                "        System.out.println(\"Scanning \" + host);",
                "    }",
                "}")
            .exampleInput("WEB-01")
            .exampleOutput("Host: WEB-01", "Scanning WEB-01")
            .lineByLine(
                new String[]{"import java.util.Scanner;",
                    "Goes above the class, not inside it. Tells Java where "
                    + "Scanner comes from."},
                new String[]{"Scanner input = new Scanner(System.in);",
                    "One Scanner, reading from the keyboard. Made once, used as "
                    + "often as you like."},
                new String[]{"System.out.print(\"Host: \");",
                    "print, so the cursor waits on this line. The trailing "
                    + "space keeps the typing from touching the colon."},
                new String[]{"input.nextLine()",
                    "The program pauses here until ENTER, then hands back what "
                    + "was typed, as a String."})
            .predict(new Task(Task.PREDICT,
                    "The analyst types a.okafor and presses ENTER. What does "
                    + "this print?")
                .code(
                    "Scanner in = new Scanner(System.in);",
                    "String name = in.nextLine();",
                    "System.out.println(\"HELLO \" + name.toUpperCase());")
                .input("a.okafor")
                .accept("HELLO A.OKAFOR")
                .hints(
                    "name holds exactly what was typed.",
                    "Then it is joined on in capitals.")
                .explain(
                    "HELLO A.OKAFOR. Once nextLine hands the text back, it is "
                    + "an ordinary String - every method you have learned works "
                    + "on it.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "This program does not compile. Which line does javac "
                    + "report?")
                .code(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Scanner in = new Scanner(System.in);",
                    "        String host = in.nextLine();",
                    "        System.out.println(host);",
                    "    }",
                    "}")
                .accept("3", "line 3")
                .hints(
                    "Something that should be above the class is missing.",
                    "The error lands on the first line that uses the missing "
                    + "name.")
                .explain(
                    "Line 3: 'cannot find symbol - class Scanner'. The import "
                    + "is missing, so Java has never heard of Scanner.\n\n"
                    + "Notice the error is reported where Scanner is USED, not "
                    + "where the import should have been. Compiler errors point "
                    + "at where Java got confused, which is often not where the "
                    + "fix goes.")
                .xp(25))
            .objective(
                "Let the analyst type which host to check.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Host: \");",
                "        // read the host here",
                "        System.out.println(\"CHECKING \" + host);",
                "    }",
                "}")
            .yourTask(
                "The Scanner is ready and the prompt is printed. Declare a "
                + "String called host holding the line the analyst types.")
            .mainTask(new Task(Task.WRITE,
                    "Declare host holding the next line typed.")
                .input("DB-01")
                .accept("String host = input.nextLine();", "String host = input.nextLine()")
                .hints(
                    "Input always arrives as a String.",
                    "Ask the Scanner called input for the next line.",
                    "String host = input.nextLine();")
                .solution(
                    "import java.util.Scanner;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Scanner input = new Scanner(System.in);",
                    "        System.out.print(\"Host: \");",
                    "        String host = input.nextLine();",
                    "        System.out.println(\"CHECKING \" + host);",
                    "    }",
                    "}")
                .whyItWorks(
                    "input.nextLine() stops the program until the analyst "
                    + "presses ENTER, then hands back what they typed. It is a "
                    + "String, so it goes into a String box.\n"
                    + "\n"
                    + "The program now does something the last twenty could "
                    + "not: produce a different result each time it runs, "
                    + "without being edited. That is also the moment it starts "
                    + "trusting someone else. Whatever is typed flows straight "
                    + "into host and on into everything that uses it.")
                .explain(
                    "nextLine() waits for ENTER and hands back a String.")
                .xp(25))
            .mistakes(
                new String[]{"Forgetting the import",
                    "Every use of Scanner becomes 'cannot find symbol'. The "
                    + "import goes on the very first line, above the class."},
                new String[]{"Expecting a number",
                    "int n = input.nextLine(); does not compile. nextLine "
                    + "always gives a String."},
                new String[]{"Using println for the prompt",
                    "It works, but the answer is typed on the line below the "
                    + "question. print keeps them together."})
            .cyber(
                "The line with nextLine on it is a TRUST BOUNDARY: everything "
                + "above it is your code, everything that comes through it is "
                + "somebody else's.\n"
                + "\n"
                + "Here the somebody is an analyst at a keyboard. In real tools "
                + "it is a web form, a network packet, a file uploaded by a "
                + "stranger - and the program cannot tell a mistake from an "
                + "attack. The typed line may be empty, four thousand "
                + "characters long, full of line breaks, or deliberately built "
                + "to break whatever reads it next.\n"
                + "\n"
                + "Nearly every injection vulnerability that exists comes from "
                + "a program that took input at a boundary like this and used "
                + "it as if it had written it itself. Every mission from here "
                + "treats input that way: untrusted until checked.")
            .check(new Task(Task.PREDICT,
                    "The analyst types jsmith followed by ONE space, then ENTER. "
                    + "What does this print?")
                .code(
                    "Scanner in = new Scanner(System.in);",
                    "String user = in.nextLine();",
                    "System.out.println(user.length());")
                .input("jsmith ")
                .accept("7", "seven")
                .hints(
                    "nextLine keeps everything typed except the ENTER.",
                    "Is a space a character?")
                .explain(
                    "7. The space was typed, so it is in the String. The ENTER "
                    + "was not kept. This is exactly why input is usually "
                    + "trimmed before anything else happens to it.")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "What type does nextLine() hand back?")
                .choices("int", "char", "String", "It depends what was typed")
                .accept("3", "c")
                .hints(
                    "Typing 42 and typing jsmith come back the same way.",
                    "It is the type for text.")
                .explain(
                    "Always a String, whatever was typed. \"42\" is two "
                    + "characters until something converts it.")
                .xp(10))
            .recap(
                "Above the class:\n"
                + "\n"
                + "    import java.util.Scanner;\n"
                + "\n"
                + "Once, inside main:\n"
                + "\n"
                + "    Scanner input = new Scanner(System.in);\n"
                + "\n"
                + "Each time you need a line:\n"
                + "\n"
                + "    String line = input.nextLine();\n"
                + "\n"
                + "nextLine waits for ENTER and always hands back a String.\n"
                + "\n"
                + "Input crosses a trust boundary. Treat it as untrusted.")
            .next("Next: turning typed text into a number you can do "
                + "arithmetic with."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(22), "Text That Looks Like a Number", 3)
            .brief(
                "The analyst types how many failed logins to allow before "
                + "lockout. They type 5. The tool adds one for the warning "
                + "level and reports 51.\n\n"
                + "Typed input is text, and + on text joins. Before the tool "
                + "can count with it, the text has to become a number.")
            .willLearn("Integer.parseInt", "Double.parseDouble", "Bad input crashes")
            .whyUseful(
                "Numbers arrive as text constantly: typed in, read from files, "
                + "cut out of log lines with substring. Converting them is "
                + "routine - and it is also where hostile or broken input "
                + "first gets the chance to stop your program.")
            .concept("Converting text to numbers",
                "\"443\" and 443 are different things. One is three "
                + "characters, the other is a number:\n"
                + "\n"
                + "    \"443\" + 1    \"4431\"   joins\n"
                + "    443 + 1      444      adds\n"
                + "\n"
                + "To convert:\n"
                + "\n"
                + "    int port = Integer.parseInt(\"443\");\n"
                + "    double score = Double.parseDouble(\"7.5\");\n"
                + "\n"
                + "(Long.parseLong exists too, for longs.)\n"
                + "\n"
                + "These methods belong to Integer and Double themselves, not "
                + "to a particular value, so you call them on the type's name. "
                + "The text goes in the brackets; the number comes back.\n"
                + "\n"
                + "The text must be EXACTLY a number. Anything else crashes "
                + "the program at run time with a NumberFormatException:\n"
                + "\n"
                + "    Integer.parseInt(\"42\")     42\n"
                + "    Integer.parseInt(\"-7\")     -7\n"
                + "    Integer.parseInt(\" 42\")    CRASH - a space\n"
                + "    Integer.parseInt(\"4.2\")    CRASH - not whole\n"
                + "    Integer.parseInt(\"ten\")    CRASH\n"
                + "    Integer.parseInt(\"\")       CRASH - nothing there\n"
                + "\n"
                + "The space one catches people most, because typed input "
                + "often has one. trim() first:\n"
                + "\n"
                + "    int n = Integer.parseInt(text.trim());\n"
                + "\n"
                + "That fixes stray spaces. It does not fix 'ten'. Checking "
                + "whether text is a valid number before converting needs "
                + "decisions, which is Campaign 02, and recovering from the "
                + "crash is Campaign 07. For now, know exactly what crashes "
                + "and why.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String portText = \"8080\";",
                "        int port = Integer.parseInt(portText);",
                "        System.out.println(portText + 1);",
                "        System.out.println(port + 1);",
                "    }",
                "}")
            .exampleOutput("80801", "8081")
            .lineByLine(
                new String[]{"String portText = \"8080\";",
                    "Four characters that happen to be digits."},
                new String[]{"Integer.parseInt(portText)",
                    "Reads the characters and builds the int 8080."},
                new String[]{"portText + 1",
                    "Text plus anything joins. 80801 - a port that does not "
                    + "exist."},
                new String[]{"port + 1",
                    "An int plus an int adds. 8081."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String a = \"20\";",
                    "String b = \"5\";",
                    "System.out.println(a + b);")
                .accept("205")
                .hints(
                    "What type are a and b?",
                    "+ on two Strings joins them.")
                .explain(
                    "205. They look like numbers but they are Strings, so + "
                    + "joins them. Nothing in the output warns you that this "
                    + "was not the sum you meant.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Which of these makes Integer.parseInt crash?")
                .choices("\"42\"", "\"-7\"", "\" 42\"", "\"0\"")
                .accept("3", "c")
                .hints(
                    "Negative numbers and zero are fine.",
                    "Look at the very first character of each.")
                .explain(
                    "\" 42\" - the leading space. parseInt accepts digits and "
                    + "an optional sign and nothing else, so the space alone is "
                    + "enough to crash it. Trim first.")
                .xp(20))
            .objective(
                "Turn the typed lockout threshold into a number.")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Lockout after: \");",
                "        String text = input.nextLine();",
                "        // convert text here",
                "        System.out.println(\"WARN AT: \" + (limit - 1));",
                "    }",
                "}")
            .yourTask(
                "Declare an int called limit holding the number in text. The "
                + "analyst may have typed a stray space, so remove the edges "
                + "before converting.")
            .mainTask(new Task(Task.WRITE,
                    "Declare limit as the int value of text, trimmed first.")
                .input(" 5")
                .accept("int limit = Integer.parseInt(text.trim());",
                        "int limit = Integer.parseInt(text.trim())")
                .hints(
                    "Integer.parseInt turns text into an int.",
                    "trim() the text before handing it over, inside the "
                    + "brackets.",
                    "int limit = Integer.parseInt(text.trim());")
                .solution(
                    "import java.util.Scanner;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Scanner input = new Scanner(System.in);",
                    "        System.out.print(\"Lockout after: \");",
                    "        String text = input.nextLine();",
                    "        int limit = Integer.parseInt(text.trim());",
                    "        System.out.println(\"WARN AT: \" + (limit - 1));",
                    "    }",
                    "}")
                .whyItWorks(
                    "text.trim() runs first and removes any spaces at the ends. "
                    + "Its result goes straight into Integer.parseInt, which "
                    + "builds the int. limit is a real number, so limit - 1 "
                    + "subtracts.\n"
                    + "\n"
                    + "Without trim, an analyst who typed ' 5' would crash the "
                    + "tool - not because they typed something wrong, but "
                    + "because the program was stricter than it needed to be. "
                    + "Trimming makes the tool forgiving of harmless mistakes. "
                    + "It does not make it safe against 'five', and that "
                    + "distinction matters: forgiveness and validation are "
                    + "different jobs.")
                .explain(
                    "trim, then parseInt, in one line.")
                .xp(30))
            .mistakes(
                new String[]{"Doing arithmetic on the text",
                    "\"5\" + 1 is \"51\". Convert first, then calculate."},
                new String[]{"Not trimming",
                    "A single stray space crashes parseInt. Trim typed input "
                    + "before converting it."},
                new String[]{"parseInt on a decimal",
                    "Integer.parseInt(\"4.5\") crashes. Use Double.parseDouble "
                    + "for text with a point."})
            .cyber(
                "A program that crashes on bad input has handed the off switch "
                + "to whoever supplies the input.\n"
                + "\n"
                + "Monitoring tools read numbers out of logs all day: ports, "
                + "sizes, status codes, durations. If one malformed field - "
                + "a truncated line, a corrupt entry, or one crafted on purpose "
                + "- crashes the parser, the tool stops watching. An attacker "
                + "who knows that can blind your monitoring with a single "
                + "carefully broken log line before doing the thing they "
                + "actually came to do.\n"
                + "\n"
                + "Knowing precisely which inputs crash parseInt is the first "
                + "step to making sure none of them reach it unchecked.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "double score = Double.parseDouble(\"7.5\");",
                    "System.out.println(score * 2);")
                .accept("15.0")
                .hints(
                    "After conversion it is an ordinary double.",
                    "A double result prints with a decimal point.")
                .explain(
                    "15.0. The text became the double 7.5, and doubling a "
                    + "double gives a double.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "What happens when Integer.parseInt(\"ten\") runs?")
                .choices(
                    "It returns 10",
                    "It returns 0",
                    "The program crashes with NumberFormatException",
                    "It does not compile")
                .accept("3", "c")
                .hints(
                    "The compiler cannot know what text will be there when the "
                    + "program runs.",
                    "parseInt only understands digits.")
                .explain(
                    "It crashes when that line runs. It compiles fine, because "
                    + "the compiler only sees a String going in - it has no idea "
                    + "what the String will contain.")
                .xp(15))
            .recap(
                "    Integer.parseInt(\"443\")      443\n"
                + "    Double.parseDouble(\"7.5\")    7.5\n"
                + "\n"
                + "Text must be exactly a number. Spaces, words, a decimal "
                + "point for parseInt, or nothing at all: crash at run time.\n"
                + "\n"
                + "Trim typed text before converting it.")
            .next("Next: tools for keeping a number inside safe limits."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(23), "Keeping Numbers in Bounds", 3)
            .brief(
                "The audit API lets the caller say how many records they want "
                + "per request. Somebody asked for five million. The server "
                + "tried to give them.\n\n"
                + "A number from outside needs limits. Java has ready-made "
                + "tools for the commonest ones.")
            .willLearn("Math methods", "Math.max and Math.min", "Math.abs")
            .whyUseful(
                "Capping a value, finding the larger of two readings, measuring "
                + "a difference regardless of direction - all everyday jobs, "
                + "all one method call.")
            .concept("Math.max, Math.min, Math.abs",
                "Math is a class of ready-made calculations. Like "
                + "Integer.parseInt, you call them on the class name. No "
                + "import needed - Math is always available.\n"
                + "\n"
                + "    Math.max(a, b)    the larger of the two\n"
                + "    Math.min(a, b)    the smaller of the two\n"
                + "    Math.abs(x)       x without its minus sign\n"
                + "\n"
                + "    Math.max(3, 9)     9\n"
                + "    Math.min(3, 9)     3\n"
                + "    Math.abs(-6)       6\n"
                + "    Math.abs(6)        6\n"
                + "\n"
                + "Two values go into max and min, separated by a comma. The "
                + "result's type follows the usual rules: two ints give an int, "
                + "and if either is a double the answer is a double.\n"
                + "\n"
                + "The name trips people up. To put a CEILING on a value - "
                + "never more than 100 - you use MIN:\n"
                + "\n"
                + "    int allowed = Math.min(requested, 100);\n"
                + "\n"
                + "Whatever was asked for, the smaller of it and 100 wins, so "
                + "the answer is never above 100. Likewise a FLOOR - never "
                + "less than 1 - uses max.\n"
                + "\n"
                + "One sharp edge, straight from mission 15: Integer.MIN_VALUE "
                + "has no positive partner in an int. Math.abs of it overflows "
                + "and gives back the same negative number.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int requested = 5000000;",
                "        final int MAX_PAGE = 100;",
                "        int allowed = Math.min(requested, MAX_PAGE);",
                "        System.out.println(\"REQUESTED: \" + requested);",
                "        System.out.println(\"ALLOWED:   \" + allowed);",
                "    }",
                "}")
            .exampleOutput("REQUESTED: 5000000", "ALLOWED:   100")
            .lineByLine(
                new String[]{"final int MAX_PAGE = 100;",
                    "The policy, as a constant. One place to change it."},
                new String[]{"Math.min(requested, MAX_PAGE)",
                    "The smaller of five million and 100 is 100. A ceiling "
                    + "uses min."},
                new String[]{"If requested were 20",
                    "Math.min(20, 100) is 20. Reasonable requests pass through "
                    + "untouched."},
                new String[]{"requested afterwards",
                    "Still five million. Keep it - it belongs in the audit "
                    + "log."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(Math.max(3, 9));")
                .accept("9", "nine")
                .hints("max gives the larger.", "Which is larger, 3 or 9?")
                .explain(
                    "9. max hands back whichever of the two values is larger, "
                    + "whatever order they are given in.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "Two clocks disagree. What does this print?")
                .code(
                    "int drift = 4 - 10;",
                    "System.out.println(Math.abs(drift));")
                .accept("6", "six")
                .hints(
                    "Work out drift first.",
                    "abs removes the minus sign.")
                .explain(
                    "6. drift is -6, and abs gives the size of the difference "
                    + "without caring which clock is ahead. That is usually "
                    + "the question you are actually asking.")
                .xp(15))
            .objective(
                "Cap the number of records a caller can pull in one request.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        int requested = 800;",
                "        final int MAX_PAGE = 50;",
                "        // cap the request here",
                "        System.out.println(\"SENDING \" + pageSize);",
                "    }",
                "}")
            .yourTask(
                "Declare an int called pageSize holding requested, but never "
                + "more than MAX_PAGE.")
            .mainTask(new Task(Task.WRITE,
                    "Declare pageSize: requested, capped at MAX_PAGE.")
                .accept("int pageSize = Math.min(requested, MAX_PAGE);",
                        "int pageSize = Math.min(requested, MAX_PAGE)",
                        "int pageSize = Math.min(MAX_PAGE, requested);",
                        "int pageSize = Math.min(MAX_PAGE, requested)")
                .hints(
                    "A ceiling means the answer can never be bigger than the "
                    + "limit.",
                    "Which gives the smaller of two values - max or min?",
                    "int pageSize = Math.min(requested, MAX_PAGE);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        int requested = 800;",
                    "        final int MAX_PAGE = 50;",
                    "        int pageSize = Math.min(requested, MAX_PAGE);",
                    "        System.out.println(\"SENDING \" + pageSize);",
                    "    }",
                    "}")
                .whyItWorks(
                    "Math.min hands back whichever is smaller. 800 against 50 "
                    + "gives 50, so the ceiling holds. A request for 20 would "
                    + "give 20, so honest callers are unaffected.\n"
                    + "\n"
                    + "Math.max would have done the opposite: sent 800, or "
                    + "more, for any request above the limit - a ceiling that "
                    + "only ever lets bigger numbers through. It compiles, it "
                    + "runs, and it is backwards. When capping, say it aloud: "
                    + "'the smaller of what they asked and what we allow'.")
                .explain(
                    "A ceiling is a min: the smaller of what was asked for and "
                    + "what is allowed.")
                .xp(25))
            .mistakes(
                new String[]{"Using max for a ceiling",
                    "Math.max(requested, 100) is never LESS than 100. For a "
                    + "ceiling you want min."},
                new String[]{"Forgetting the class name",
                    "min(a, b) on its own is 'cannot find symbol'. It is "
                    + "Math.min."},
                new String[]{"Trusting abs to always be positive",
                    "Math.abs(Integer.MIN_VALUE) is still negative. Overflow "
                    + "has no exceptions."})
            .cyber(
                "Unbounded numbers from outside are a classic denial-of-service "
                + "route. A page size of five million makes the server load "
                + "five million records into memory. A retry count of a "
                + "billion ties up a worker forever. A requested timeout of "
                + "a year keeps a connection open for a year.\n"
                + "\n"
                + "The fix is the same every time: decide the largest value you "
                + "are willing to honour, and clamp to it before using the "
                + "number for anything. It costs one line, it does not reject "
                + "legitimate callers, and it turns an attacker's five million "
                + "into a harmless hundred. Keep the original value too - a "
                + "request for five million records is itself worth an alert.")
            .check(new Task(Task.CHOICE,
                    "To make sure attempts is never MORE than 10, which do you "
                    + "use?")
                .choices("Math.max(attempts, 10)", "Math.min(attempts, 10)",
                         "Math.abs(attempts)", "Math.max(10, attempts)")
                .accept("2", "b")
                .hints(
                    "Never more than 10 is a ceiling.",
                    "A ceiling picks the smaller value.")
                .explain(
                    "Math.min(attempts, 10). If attempts is 15, the smaller is "
                    + "10. If it is 3, the smaller is 3. Both max options do the "
                    + "reverse.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(Math.max(2, 2.5));")
                .accept("2.5")
                .hints(
                    "One of the two values is a double.",
                    "Mixed arithmetic rules apply: the answer is a double.")
                .explain(
                    "2.5. 2 is promoted to 2.0, and the larger is 2.5. With a "
                    + "double in either slot, the result is a double.")
                .xp(20))
            .recap(
                "    Math.max(a, b)    larger\n"
                + "    Math.min(a, b)    smaller\n"
                + "    Math.abs(x)       distance from zero\n"
                + "\n"
                + "A ceiling uses MIN. A floor uses MAX.\n"
                + "\n"
                + "Clamp every number that comes from outside before you use "
                + "it.")
            .next("Next: rounding properly, as promised back in mission 14."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(24), "Rounding on Purpose", 3)
            .brief(
                "Mission 14 showed that (int) chops the fraction off: 99.7% "
                + "uptime reported as 99. Sometimes that is right. Usually, "
                + "for a report, what you meant was the NEAREST whole number.\n\n"
                + "That is a different operation, with a surprise in its return "
                + "type.")
            .willLearn("Math.round", "Rounding against truncating",
                       "Rounding to decimal places")
            .whyUseful(
                "Every report, dashboard and alert shows rounded numbers. "
                + "Choosing between rounding and truncating - and rounding to "
                + "the right number of places - decides what the reader "
                + "believes.")
            .concept("Math.round",
                "Math.round gives the nearest whole number. A half rounds "
                + "UP:\n"
                + "\n"
                + "    Math.round(2.4)     2\n"
                + "    Math.round(2.5)     3\n"
                + "    Math.round(2.6)     3\n"
                + "    Math.round(99.7)    100\n"
                + "\n"
                + "'Up' means towards the larger number, so negative halves go "
                + "towards zero:\n"
                + "\n"
                + "    Math.round(-2.5)    -2\n"
                + "\n"
                + "Compare the cast, which never rounds:\n"
                + "\n"
                + "    (int) 99.7          99\n"
                + "    Math.round(99.7)    100\n"
                + "\n"
                + "THE SURPRISE. Given a double, Math.round hands back a LONG, "
                + "not an int. So this does not compile:\n"
                + "\n"
                + "    int r = Math.round(7.6);\n"
                + "\n"
                + "'possible lossy conversion from long to int'. Store it in a "
                + "long, or cast the result if you know it fits:\n"
                + "\n"
                + "    long r = Math.round(7.6);\n"
                + "    int r = (int) Math.round(7.6);\n"
                + "\n"
                + "ROUNDING TO DECIMAL PLACES. Math.round only rounds to whole "
                + "numbers, but you can shift the point first:\n"
                + "\n"
                + "    Math.round(7.46 * 10) / 10.0\n"
                + "\n"
                + "    7.46 * 10      74.6\n"
                + "    round          75\n"
                + "    75 / 10.0      7.5\n"
                + "\n"
                + "The 10.0 matters - dividing by plain 10 would be integer "
                + "division and give 7. Everything from mission 10 still "
                + "applies.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        double uptime = 99.7;",
                "        System.out.println(\"CAST:  \" + (int) uptime);",
                "        System.out.println(\"ROUND: \" + Math.round(uptime));",
                "        double score = 7.46;",
                "        System.out.println(\"1 DP:  \" + Math.round(score * 10) / 10.0);",
                "    }",
                "}")
            .exampleOutput("CAST:  99", "ROUND: 100", "1 DP:  7.5")
            .lineByLine(
                new String[]{"(int) uptime",
                    "Truncates. 99.7 loses its .7 and becomes 99."},
                new String[]{"Math.round(uptime)",
                    "Nearest whole number. 99.7 is closer to 100."},
                new String[]{"score * 10",
                    "Moves the point one place right: 74.6."},
                new String[]{"/ 10.0",
                    "Rounds to 75, then moves the point back. The .0 keeps the "
                    + "division decimal."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(Math.round(4.5));")
                .accept("5", "five")
                .hints("Exactly half.", "Halves round up.")
                .explain("5. A half rounds up, towards the larger number.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "What are the TWO lines of output?")
                .code(
                    "double load = 8.9;",
                    "System.out.println((int) load);",
                    "System.out.println(Math.round(load));")
                .accept("8 9", "8, 9", "8 and 9")
                .hints(
                    "One line truncates and one rounds.",
                    "8.9 is much nearer 9 than 8.")
                .explain(
                    "    8\n"
                    + "    9\n"
                    + "\n"
                    + "The cast cut off the .9. Math.round went to the nearest. "
                    + "Almost a whole unit apart, from the same value.")
                .xp(20))
            .objective(
                "Report a severity score as its nearest whole number.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        double severity = 6.7;",
                "        // round severity here",
                "        System.out.println(\"SEVERITY \" + rounded);",
                "    }",
                "}")
            .yourTask(
                "Declare rounded holding severity rounded to the nearest whole "
                + "number. Choose a type that Math.round's result will fit "
                + "into without a cast.")
            .mainTask(new Task(Task.WRITE,
                    "Declare rounded as severity rounded to the nearest whole.")
                .accept("long rounded = Math.round(severity);",
                        "long rounded = Math.round(severity)",
                        "int rounded = (int) Math.round(severity);",
                        "int rounded = (int) Math.round(severity)")
                .hints(
                    "Rounding, not truncating, so not a cast on severity.",
                    "Math.round of a double hands back a long.",
                    "long rounded = Math.round(severity);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        double severity = 6.7;",
                    "        long rounded = Math.round(severity);",
                    "        System.out.println(\"SEVERITY \" + rounded);",
                    "    }",
                    "}")
                .whyItWorks(
                    "Math.round(6.7) is 7 - nearer 7 than 6 - and it arrives "
                    + "as a long, so a long box takes it directly.\n"
                    + "\n"
                    + "(int) severity would have said 6 and compiled happily. "
                    + "On a scale where 7.0 is where 'high' begins, that single "
                    + "choice decides whether the finding is filed as medium "
                    + "or high. Neither is a bug in Java; one of them is a "
                    + "bug in the report.")
                .explain(
                    "Math.round for nearest, into a long.")
                .xp(25))
            .mistakes(
                new String[]{"Storing the result in an int",
                    "int r = Math.round(x); does not compile - it hands back a "
                    + "long. Use long, or cast the result."},
                new String[]{"Dividing by 10 instead of 10.0",
                    "Math.round(x * 10) / 10 is integer division. The decimal "
                    + "place you just rounded to is thrown away."},
                new String[]{"Using a cast when you meant to round",
                    "(int) 9.99 is 9. If the nearest number is what you mean, "
                    + "say so with Math.round."})
            .cyber(
                "Which rounding to use is not always yours to choose. CVSS, the "
                + "standard behind most vulnerability scores, defines its own "
                + "rule: scores are always rounded UP to one decimal place. A "
                + "raw 7.01 is published as 7.1.\n"
                + "\n"
                + "A tool that recomputes scores with Math.round gives 7.0 "
                + "instead - different from every scanner and advisory the "
                + "team compares against. Nobody can reconcile the numbers, "
                + "trust in the tool drops, and people go back to reading "
                + "the advisory by hand. The lesson is not 'always use "
                + "Math.round'. It is that rounding is a decision, and when a "
                + "standard has already made it, you follow the standard.")
            .check(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "double s = 6.7;",
                    "int r = Math.round(s);",
                    "System.out.println(r);")
                .accept("2", "line 2")
                .hints(
                    "What type does Math.round give back for a double?",
                    "Can that type go into an int box without a cast?")
                .explain(
                    "Line 2. Math.round of a double is a long, and a long does "
                    + "not fit an int without a cast. 'possible lossy conversion "
                    + "from long to int'.")
                .xp(20))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(Math.round(3.14159 * 100) / 100.0);")
                .accept("3.14")
                .hints(
                    "Shift the point two places, round, shift it back.",
                    "314.159 rounds to 314.")
                .explain(
                    "3.14. Multiplying by 100 moves the point two places, "
                    + "rounding gives 314, and dividing by 100.0 moves it back. "
                    + "Two decimal places.")
                .xp(20))
            .recap(
                "    Math.round(2.5)    3     nearest, halves go up\n"
                + "    (int) 2.9          2     cut off, never rounded\n"
                + "\n"
                + "Math.round of a double hands back a long.\n"
                + "\n"
                + "    Math.round(x * 10) / 10.0    one decimal place\n"
                + "\n"
                + "Rounding is a decision. Make it deliberately.")
            .next("Next: finding where something is inside a String."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(25), "Finding Your Place", 3)
            .brief(
                "Mission 20 cut fields out of a log line by fixed positions. "
                + "Email addresses do not have fixed positions: the @ is "
                + "wherever the name ends.\n\n"
                + "Before you can cut, you need to find.")
            .willLearn("indexOf()", "lastIndexOf()", "-1 means not found")
            .whyUseful(
                "Real data is rarely fixed-width. Finding a separator and "
                + "cutting around it is how addresses, key=value pairs, URLs "
                + "and file names get taken apart.")
            .concept("indexOf()",
                "indexOf searches a String and hands back the index where "
                + "the text FIRST appears:\n"
                + "\n"
                + "    String email = \"jsmith@northstar.example\";\n"
                + "    email.indexOf(\"@\")    6\n"
                + "\n"
                + "It returns an int - a position, counted from 0 as always. "
                + "The search is case-sensitive, and you can search for "
                + "longer text too:\n"
                + "\n"
                + "    \"FAILED login\".indexOf(\"login\")    7\n"
                + "\n"
                + "If the text is NOT there, the answer is -1. Not an error, "
                + "not a crash - just -1, because no real index is negative.\n"
                + "\n"
                + "lastIndexOf searches from the other end and gives the LAST "
                + "place the text appears. Handy for file extensions:\n"
                + "\n"
                + "    \"report.pdf.exe\".lastIndexOf(\".\")    10\n"
                + "\n"
                + "FIND, THEN CUT. Combined with substring:\n"
                + "\n"
                + "    int at = email.indexOf(\"@\");\n"
                + "    String user   = email.substring(0, at);\n"
                + "    String domain = email.substring(at + 1);\n"
                + "\n"
                + "The @ is at index at. Everything before it is the user. "
                + "at + 1 skips past the @ itself.\n"
                + "\n"
                + "THE DANGER. If there is no @, at is -1, and at + 1 is 0. "
                + "substring(0) is the WHOLE String. No crash - the 'domain' "
                + "is quietly the entire input. Checking for -1 first needs a "
                + "decision, which is exactly what Campaign 02 is for.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String email = \"a.okafor@northstar.example\";",
                "        int at = email.indexOf(\"@\");",
                "        System.out.println(\"AT:     \" + at);",
                "        System.out.println(\"USER:   \" + email.substring(0, at));",
                "        System.out.println(\"DOMAIN: \" + email.substring(at + 1));",
                "    }",
                "}")
            .exampleOutput("AT:     8", "USER:   a.okafor", "DOMAIN: northstar.example")
            .lineByLine(
                new String[]{"email.indexOf(\"@\")",
                    "a.okafor is eight characters, indexes 0 to 7, so the @ is "
                    + "at 8."},
                new String[]{"substring(0, at)",
                    "From 0 up to, but not including, the @."},
                new String[]{"substring(at + 1)",
                    "From just after the @ to the end."},
                new String[]{"Nothing is counted by hand",
                    "A longer or shorter name moves the @, and indexOf finds "
                    + "it wherever it is."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String ip = \"10.0.4.17\";",
                    "System.out.println(ip.indexOf(\".\"));")
                .accept("2", "two")
                .hints("The FIRST dot.", "1 is at 0, 0 is at 1...")
                .explain(
                    "2. There are three dots; indexOf reports the first one it "
                    + "meets.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String user = \"jsmith\";",
                    "System.out.println(user.indexOf(\"@\"));")
                .accept("-1")
                .hints(
                    "Is there an @ in jsmith?",
                    "What does indexOf say when it finds nothing?")
                .explain(
                    "-1. There is no @, so there is no position to report. -1 "
                    + "is the agreed signal for 'not found', precisely because "
                    + "it can never be a real index.")
                .xp(20))
            .objective(
                "Split a key=value setting at its equals sign.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String entry = \"user=m.reyes\";",
                "        // find the = here",
                "        String value = entry.substring(eq + 1);",
                "        System.out.println(\"VALUE: \" + value);",
                "    }",
                "}")
            .yourTask(
                "Declare an int called eq holding the position of the = sign "
                + "in entry. Let Java find it; do not count.")
            .mainTask(new Task(Task.WRITE,
                    "Declare eq as the index of \"=\" in entry.")
                .accept("int eq = entry.indexOf(\"=\");", "int eq = entry.indexOf(\"=\")",
                        "int eq = entry.indexOf('=');", "int eq = entry.indexOf('=')")
                .hints(
                    "A position is an int.",
                    "indexOf, called on entry, with the text to find in the "
                    + "brackets.",
                    "int eq = entry.indexOf(\"=\");")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String entry = \"user=m.reyes\";",
                    "        int eq = entry.indexOf(\"=\");",
                    "        String value = entry.substring(eq + 1);",
                    "        System.out.println(\"VALUE: \" + value);",
                    "    }",
                    "}")
                .whyItWorks(
                    "indexOf finds the = at index 4. substring(eq + 1) starts "
                    + "one past it, so value is m.reyes.\n"
                    + "\n"
                    + "Because the position is found rather than typed, the "
                    + "same two lines split host=DB-01, level=critical or any "
                    + "other pair. And they share the danger in this "
                    + "mission's concept: feed them a line with no = and eq is "
                    + "-1, value is the whole line, and nothing complains.")
                .explain(
                    "indexOf finds the separator wherever it is.")
                .xp(25))
            .mistakes(
                new String[]{"Forgetting the + 1",
                    "substring(at) includes the separator itself: @northstar."},
                new String[]{"Treating -1 as a real position",
                    "substring(-1 + 1) is the whole String. No crash, wrong "
                    + "answer."},
                new String[]{"Expecting it to ignore case",
                    "\"LOGIN\".indexOf(\"login\") is -1. Lower-case both sides "
                    + "first if case should not matter."})
            .cyber(
                "File names are a favourite place to hide. Windows hides known "
                + "extensions by default, so invoice.pdf.exe is shown as "
                + "invoice.pdf - with a PDF icon, if the attacker embedded "
                + "one. The file is a program.\n"
                + "\n"
                + "A check that uses indexOf(\".\") finds the FIRST dot and "
                + "decides the extension is .pdf.exe, or cuts out pdf and "
                + "decides the file is a document. lastIndexOf finds the dot "
                + "that actually decides what Windows will do with the file. "
                + "Picking the right one is the difference between a filter "
                + "and a decoration.")
            .check(new Task(Task.PREDICT,
                    "There is no @ in this address. What does this print?")
                .code(
                    "String email = \"no-at-sign\";",
                    "System.out.println(email.substring(email.indexOf(\"@\") + 1));")
                .accept("no-at-sign")
                .hints(
                    "Work out indexOf first. What is it when nothing is found?",
                    "-1 + 1 is 0. What does substring(0) give?")
                .explain(
                    "no-at-sign - the whole thing. indexOf gave -1, the + 1 made "
                    + "it 0, and substring(0) is everything. The code meant to "
                    + "extract a domain, and returned the entire input without "
                    + "a murmur.")
                .xp(25))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String file = \"invoice.pdf.exe\";",
                    "System.out.println(file.substring(file.lastIndexOf(\".\")));")
                .accept(".exe")
                .hints(
                    "lastIndexOf finds the LAST dot.",
                    "substring from there runs to the end.")
                .explain(
                    ".exe - from the last dot to the end, which is the extension "
                    + "that actually decides what the file does.")
                .xp(20))
            .recap(
                "    s.indexOf(\"@\")        first position, or -1\n"
                + "    s.lastIndexOf(\".\")    last position, or -1\n"
                + "\n"
                + "Find, then cut:\n"
                + "\n"
                + "    s.substring(0, at)    before\n"
                + "    s.substring(at + 1)   after\n"
                + "\n"
                + "-1 is not a position. Used as one, it gives wrong answers "
                + "quietly.")
            .next("Next: asking whether a String contains, starts with or "
                + "ends with something."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(26), "Yes or No Questions About Text", 3)
            .brief(
                "The mail gateway quarantines attachments that are programs. "
                + "Its rule is simple: if the file name ends with .exe, hold "
                + "it.\n\n"
                + "This morning Payroll.PDF.EXE went straight through. The rule "
                + "was asking the right question the wrong way.")
            .willLearn("contains()", "startsWith() and endsWith()",
                       "Methods that answer true or false")
            .whyUseful(
                "Matching text - an indicator in a log, a dangerous extension, "
                + "a URL scheme - is the most basic detection there is. These "
                + "three methods do it, and their answers are booleans ready "
                + "for the decisions of Campaign 02.")
            .concept("contains, startsWith, endsWith",
                "Three methods that answer a yes/no question about a String. "
                + "Each hands back a boolean:\n"
                + "\n"
                + "    s.contains(\"x\")      does x appear anywhere?\n"
                + "    s.startsWith(\"x\")    does s begin with x?\n"
                + "    s.endsWith(\"x\")      does s finish with x?\n"
                + "\n"
                + "    String url = \"https://northstar.example/login\";\n"
                + "    url.startsWith(\"https://\")    true\n"
                + "    url.contains(\"login\")         true\n"
                + "    url.endsWith(\".exe\")          false\n"
                + "\n"
                + "The answer can be stored in a boolean or printed, like any "
                + "other boolean:\n"
                + "\n"
                + "    boolean secure = url.startsWith(\"https://\");\n"
                + "\n"
                + "All three are CASE-SENSITIVE. \"FILE.EXE\".endsWith(\".exe\") "
                + "is false. When case should not matter - and for file "
                + "names on Windows it does not - lower-case first, then "
                + "ask:\n"
                + "\n"
                + "    name.toLowerCase().endsWith(\".exe\")\n"
                + "\n"
                + "contains matches ANYWHERE, including inside other words. "
                + "\"sysadmin\".contains(\"admin\") is true. That makes it "
                + "easy to use and easy to over-match.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String name = \"Payroll.PDF.EXE\";",
                "        String lower = name.toLowerCase();",
                "        System.out.println(\"RAW .exe?   \" + name.endsWith(\".exe\"));",
                "        System.out.println(\"LOWER .exe? \" + lower.endsWith(\".exe\"));",
                "        System.out.println(\"HAS .pdf?   \" + lower.contains(\".pdf\"));",
                "    }",
                "}")
            .exampleOutput("RAW .exe?   false", "LOWER .exe? true", "HAS .pdf?   true")
            .lineByLine(
                new String[]{"name.endsWith(\".exe\")",
                    "false. The name ends .EXE in capitals, and the match is "
                    + "case-sensitive. This is the gateway's bug."},
                new String[]{"lower.endsWith(\".exe\")",
                    "true. Lower-case first, then ask, and the program is "
                    + "caught."},
                new String[]{"lower.contains(\".pdf\")",
                    "true as well - which is exactly what the attacker was "
                    + "counting on a person noticing."},
                new String[]{"The answers",
                    "Booleans. Printed here; stored and acted on once you have "
                    + "if statements."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String line = \"FAILED login for jsmith\";",
                    "System.out.println(line.contains(\"login\"));")
                .accept("true")
                .hints("Does login appear anywhere in the line?",
                       "It does not have to be at the start.")
                .explain("true. contains looks anywhere in the String.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String file = \"report.EXE\";",
                    "System.out.println(file.endsWith(\".exe\"));")
                .accept("false")
                .hints(
                    "Compare the letters exactly, capitals included.",
                    "Is .EXE the same text as .exe?")
                .explain(
                    "false. .EXE and .exe are different text, and endsWith "
                    + "compares exactly. Windows will still run it. This is "
                    + "the whole bypass.")
                .xp(20))
            .objective(
                "Fix the attachment check so capitals cannot slip past it.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String name = \"Payroll.PDF.EXE\";",
                "        // decide whether it is a program here",
                "        System.out.println(\"QUARANTINE: \" + executable);",
                "    }",
                "}")
            .yourTask(
                "Declare a boolean called executable that is true when name "
                + "ends with .exe in ANY mix of capitals. Do it in one line by "
                + "chaining.")
            .mainTask(new Task(Task.WRITE,
                    "Declare executable: does name end with .exe, ignoring case?")
                .accept("boolean executable = name.toLowerCase().endsWith(\".exe\");",
                        "boolean executable = name.toLowerCase().endsWith(\".exe\")")
                .hints(
                    "The answer is true or false, so the type is boolean.",
                    "Lower-case the name first, then ask endsWith on the result.",
                    "boolean executable = name.toLowerCase().endsWith(\".exe\");")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String name = \"Payroll.PDF.EXE\";",
                    "        boolean executable = name.toLowerCase().endsWith(\".exe\");",
                    "        System.out.println(\"QUARANTINE: \" + executable);",
                    "    }",
                    "}")
                .whyItWorks(
                    "toLowerCase() hands back \"payroll.pdf.exe\", and endsWith "
                    + "is asked of that. Every spelling - .EXE, .Exe, .eXe - "
                    + "becomes .exe before the question, so there is only one "
                    + "spelling left to match.\n"
                    + "\n"
                    + "Chaining in the other order, name.endsWith(\".exe\")"
                    + ".toLowerCase(), does not even compile: endsWith gives a "
                    + "boolean, and a boolean has no toLowerCase. The order is "
                    + "the logic - normalise, then test.")
                .explain(
                    "Normalise the case, then ask the question.")
                .xp(30))
            .mistakes(
                new String[]{"Matching without normalising case",
                    "An attacker picks the capitals. Lower-case first, then "
                    + "compare with a lower-case pattern."},
                new String[]{"contains where you meant endsWith",
                    "contains(\".exe\") also flags readme.exe.txt, which is a "
                    + "text file. Ask the question you mean."},
                new String[]{"Forgetting over-matching",
                    "contains(\"admin\") is true for sysadmin, badminton and "
                    + "administrator."})
            .cyber(
                "Text matching is the front line of detection - antivirus "
                + "signatures, mail filters, web firewall rules, SIEM searches "
                + "- and the first thing attackers probe is how exactly it "
                + "matches.\n"
                + "\n"
                + "Change the capitals. Add a second extension. Put a space or "
                + "a dot at the end, which Windows quietly strips. Encode a "
                + "character. Each is a way of making text mean the same thing "
                + "to the system that acts on it while looking different to "
                + "the check that guards it.\n"
                + "\n"
                + "The rule that defeats most of them: bring the input to one "
                + "canonical form first - trimmed, one case, decoded - and "
                + "only then compare. Checking a raw value against a pattern "
                + "is checking the attacker's choice of spelling.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String url = \"http://northstar.example\";",
                    "System.out.println(url.startsWith(\"https://\"));")
                .accept("false")
                .hints(
                    "Compare the first eight characters carefully.",
                    "Is there an s?")
                .explain(
                    "false. The URL begins http://, without the s. startsWith "
                    + "needs every character of the pattern to match.")
                .xp(15))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(\"sysadmin\".contains(\"admin\"));")
                .accept("true")
                .hints("contains matches anywhere.",
                       "Look at the last five letters of sysadmin.")
                .explain(
                    "true. admin sits inside sysadmin. A rule meant to spot "
                    + "the admin account would fire on this one too - false "
                    + "positives like that are why alert rules get tuned.")
                .xp(15))
            .recap(
                "    s.contains(x)      anywhere\n"
                + "    s.startsWith(x)    at the beginning\n"
                + "    s.endsWith(x)      at the end\n"
                + "\n"
                + "Each answers with a boolean. All are case-sensitive.\n"
                + "\n"
                + "Normalise first, then match:\n"
                + "\n"
                + "    name.toLowerCase().endsWith(\".exe\")")
            .next("Next: swapping one piece of text for another - and "
                + "stopping forged log lines."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(27), "Forged Lines in the Log", 3)
            .brief(
                "The authentication log shows a failed login for 'guest', "
                + "followed immediately by a successful admin login. Nobody "
                + "logged in as admin.\n\n"
                + "Both lines were written by one failed attempt. The username "
                + "had a line break inside it.")
            .willLearn("replace()", "Log injection", "Neutralising input")
            .whyUseful(
                "Swapping text is how input is cleaned before it is written "
                + "anywhere that trusts its shape: logs, reports, other "
                + "programs. replace is the simplest cleaning tool Java has.")
            .concept("replace()",
                "replace hands back a copy with EVERY occurrence of one piece "
                + "of text swapped for another:\n"
                + "\n"
                + "    \"10-0-4-17\".replace(\"-\", \".\")    \"10.0.4.17\"\n"
                + "\n"
                + "Every one, not just the first. Replacing with the empty "
                + "String deletes:\n"
                + "\n"
                + "    \"10.0.4.17\".replace(\".\", \"\")     \"100417\"\n"
                + "\n"
                + "It is case-sensitive, and like every String method it leaves "
                + "the original alone - store the result.\n"
                + "\n"
                + "LOG INJECTION. A log is a file of lines, and a program "
                + "reading it trusts that each line is one event. Remember \\n "
                + "from Campaign 00: a line break inside a String. If a "
                + "username contains one, then\n"
                + "\n"
                + "    \"WARN login failed for \" + username\n"
                + "\n"
                + "writes TWO lines, and the second one says whatever the "
                + "attacker chose. It looks exactly like a real entry.\n"
                + "\n"
                + "The defence is to make line breaks in input harmless before "
                + "writing them:\n"
                + "\n"
                + "    String safe = username.replace(\"\\n\", \"\\\\n\");\n"
                + "\n"
                + "That swaps each real line break for the two visible "
                + "characters \\ and n. The whole attempt stays on one line, "
                + "and an analyst can SEE that something odd was typed - which "
                + "is better than silently deleting the evidence.\n"
                + "\n"
                + "Real logs also meet \\r, the carriage return, which some "
                + "viewers treat as a line break too. A careful logger "
                + "neutralises both.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String username = \"guest\\nINFO login ok for admin\";",
                "        System.out.println(\"WARN login failed for \" + username);",
                "        String safe = username.replace(\"\\n\", \"\\\\n\");",
                "        System.out.println(\"WARN login failed for \" + safe);",
                "    }",
                "}")
            .exampleOutput(
                "WARN login failed for guest",
                "INFO login ok for admin",
                "WARN login failed for guest\\nINFO login ok for admin")
            .lineByLine(
                new String[]{"The username",
                    "What an attacker typed into the login form, line break "
                    + "and all."},
                new String[]{"The first println",
                    "Writes two lines. The second is a forged entry that any "
                    + "log reader will believe."},
                new String[]{"replace(\"\\n\", \"\\\\n\")",
                    "\"\\n\" is a real line break. \"\\\\n\" is a backslash "
                    + "followed by n - two visible characters."},
                new String[]{"The second println",
                    "One line. The attempt is recorded, visibly strange, and "
                    + "cannot pretend to be anything else."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(\"10-0-4-17\".replace(\"-\", \".\"));")
                .accept("10.0.4.17")
                .hints("Every dash is swapped, not just the first.",
                       "Three dashes, three dots.")
                .explain("10.0.4.17. replace changes every occurrence.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String host = \"web-01\";",
                    "host.replace(\"web\", \"db\");",
                    "System.out.println(host);")
                .accept("web-01")
                .hints(
                    "Was the result of line 2 stored?",
                    "A String never changes.")
                .explain(
                    "web-01. Line 2 built \"db-01\" and threw it away. The same "
                    + "trap as mission 17, and in security code it is worse: a "
                    + "sanitising call whose result is ignored sanitises "
                    + "nothing, while looking as if it does.")
                .xp(20))
            .objective(
                "Make a username safe to write into the authentication log.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String username = \"guest\\nINFO login ok for admin\";",
                "        // neutralise the line breaks here",
                "        System.out.println(\"WARN login failed for \" + safe);",
                "    }",
                "}")
            .yourTask(
                "Declare a String called safe holding username with every line "
                + "break replaced by a single space, so the whole attempt lands "
                + "on one line.")
            .mainTask(new Task(Task.WRITE,
                    "Declare safe: username with each \\n replaced by a space.")
                .accept("String safe = username.replace(\"\\n\", \" \");",
                        "String safe = username.replace(\"\\n\", \" \")",
                        "String safe = username.replace('\\n', ' ');",
                        "String safe = username.replace('\\n', ' ')")
                .hints(
                    "replace(what to find, what to put instead).",
                    "A line break is written \\n inside quotes. A space is "
                    + "written \" \".",
                    "String safe = username.replace(\"\\n\", \" \");")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String username = \"guest\\nINFO login ok for admin\";",
                    "        String safe = username.replace(\"\\n\", \" \");",
                    "        System.out.println(\"WARN login failed for \" + safe);",
                    "    }",
                    "}")
                .whyItWorks(
                    "replace finds every real line break in username and puts a "
                    + "space in its place, and the result is stored in safe. "
                    + "Printed, the whole thing is one line: WARN login failed "
                    + "for guest INFO login ok for admin.\n"
                    + "\n"
                    + "It still reads oddly - and that is fine. The attack "
                    + "depended on creating a SEPARATE line that looked "
                    + "genuine. Inside the WARN line, the text is plainly part "
                    + "of a username somebody typed, and plainly suspicious. "
                    + "Replacing with \\\\n instead of a space, as in the "
                    + "example, makes the evidence even clearer.")
                .explain(
                    "Replace the line break, store the result, write the safe "
                    + "copy.")
                .xp(30))
            .mistakes(
                new String[]{"Not storing the result",
                    "username.replace(...); alone changes nothing. The log gets "
                    + "the raw input."},
                new String[]{"Only handling \\n",
                    "\\r is a line break to many log viewers too. Neutralise "
                    + "both."},
                new String[]{"Deleting instead of neutralising",
                    "replace(\"\\n\", \"\") hides the attempt. Making it "
                    + "visible preserves evidence."})
            .cyber(
                "Log injection - log forging - is a catalogued weakness "
                + "(CWE-117), and it attacks the thing investigators rely on "
                + "most. Forged lines can invent logins that never happened, "
                + "bury a real event under noise, or break the parser that "
                + "feeds the SIEM so that everything after them is lost.\n"
                + "\n"
                + "The general rule it teaches is one of the most important in "
                + "the course: data that crosses into another format must be "
                + "made safe for THAT format. A line break is harmless in a "
                + "variable and dangerous in a log. A quote is harmless in a "
                + "log and dangerous in a database query. An angle bracket is "
                + "harmless in a database and dangerous in a web page. Same "
                + "idea, every time - and replace is the first tool for it.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println(\"a.b.c\".replace(\".\", \"\"));")
                .accept("abc")
                .hints("Replacing with the empty String deletes.",
                       "Every dot goes.")
                .explain("abc. Each dot was replaced with nothing.")
                .xp(15))
            .check(new Task(Task.CHOICE,
                    "How many matches does replace change?")
                .choices("Only the first", "Only the last", "Every one",
                         "One per line")
                .accept("3", "c")
                .hints("Think back to 10-0-4-17.",
                       "All three dashes changed.")
                .explain(
                    "Every one. replace goes through the whole String.")
                .xp(10))
            .recap(
                "    s.replace(find, putInstead)   a new String, every match\n"
                + "\n"
                + "Store the result. Case-sensitive.\n"
                + "\n"
                + "A line break in input can forge a log entry. Neutralise it "
                + "before writing:\n"
                + "\n"
                + "    username.replace(\"\\n\", \"\\\\n\")")
            .next("Next: printing tidy, aligned reports."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(28), "A Report Worth Reading", 3)
            .brief(
                "The morning summary joins its values with + and spaces. "
                + "Every host name is a different length, so the columns "
                + "stagger down the page and nobody reads past the third "
                + "line.\n\n"
                + "Java has a way to print values into a fixed layout.")
            .willLearn("printf", "Format specifiers", "String.format")
            .whyUseful(
                "Aligned columns and fixed decimal places turn a stream of "
                + "values into something a tired analyst can scan in seconds. "
                + "Every report you write from now on can use it.")
            .concept("printf",
                "printf prints a TEMPLATE with gaps, and fills the gaps with "
                + "values listed after it:\n"
                + "\n"
                + "    System.out.printf(\"%s has %d failures%n\", user, n);\n"
                + "\n"
                + "Each gap is a FORMAT SPECIFIER starting with %:\n"
                + "\n"
                + "    %s     a String\n"
                + "    %d     a whole number - int or long\n"
                + "    %f     a double\n"
                + "    %.1f   a double, rounded to 1 decimal place\n"
                + "    %n     a line break\n"
                + "\n"
                + "Values fill the gaps in order. printf does NOT move to a "
                + "new line by itself, like print - end with %n.\n"
                + "\n"
                + "A number between % and the letter sets a WIDTH, padding with "
                + "spaces:\n"
                + "\n"
                + "    %5d     right-aligned in 5 columns     \"   42\"\n"
                + "    %-8s    left-aligned in 8 columns      \"WEB-01  \"\n"
                + "\n"
                + "Numbers usually align right, names left. Widths are what "
                + "make columns.\n"
                + "\n"
                + "The type must match. %d given a double compiles, and then "
                + "CRASHES when it runs - the compiler does not check the "
                + "template against the values.\n"
                + "\n"
                + "String.format takes the same template and hands the result "
                + "back as a String instead of printing it:\n"
                + "\n"
                + "    String row = String.format(\"%-8s %5d\", host, n);")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        System.out.printf(\"%-8s %5d %6.1f%n\", \"WEB-01\", 12, 7.46);",
                "        System.out.printf(\"%-8s %5d %6.1f%n\", \"DC-01\", 3, 9.8);",
                "    }",
                "}")
            .exampleOutput("WEB-01      12    7.5", "DC-01        3    9.8")
            .lineByLine(
                new String[]{"%-8s",
                    "The host name, left-aligned in 8 columns. Both names start "
                    + "at the margin and the next column starts in the same "
                    + "place."},
                new String[]{"%5d",
                    "The count, right-aligned in 5, so the units line up under "
                    + "each other."},
                new String[]{"%6.1f",
                    "The score in 6 columns, one decimal place. 7.46 is rounded "
                    + "to 7.5 for display - the variable is not changed."},
                new String[]{"%n",
                    "Ends the line. Without it both rows would run together."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.printf(\"PORT %d OPEN%n\", 443);")
                .accept("PORT 443 OPEN")
                .hints("The %d gap is filled with 443.",
                       "%n ends the line and prints nothing visible.")
                .explain("PORT 443 OPEN. The value slots into the gap.")
                .xp(10))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.printf(\"%.2f%n\", 2.0 / 3);")
                .accept("0.67")
                .hints(
                    "2.0 / 3 is 0.6666...",
                    "%.2f shows two decimal places, rounded.")
                .explain(
                    "0.67. Rounded to two places for display. Compare mission "
                    + "24's Math.round(x * 100) / 100.0 - printf is the easy "
                    + "way when you only need to SHOW the rounded value.")
                .xp(20))
            .objective(
                "Print one line of the failed-login summary.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String user = \"contractor\";",
                "        int failures = 9;",
                "        // print: contractor has 9 failures",
                "    }",
                "}")
            .yourTask(
                "Use printf to print  contractor has 9 failures  followed by a "
                + "line break. Use placeholders for BOTH values - do not type "
                + "them into the template.")
            .mainTask(new Task(Task.WRITE,
                    "printf the line, with %s and %d, ending in %n.")
                .accept("System.out.printf(\"%s has %d failures%n\", user, failures);",
                        "System.out.printf(\"%s has %d failures%n\", user, failures)")
                .hints(
                    "The template is the fixed text with a gap for each value.",
                    "user is a String (%s), failures is an int (%d), then %n.",
                    "System.out.printf(\"%s has %d failures%n\", user, failures);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String user = \"contractor\";",
                    "        int failures = 9;",
                    "        System.out.printf(\"%s has %d failures%n\", user, failures);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The template holds everything that never changes. %s is "
                    + "filled with user and %d with failures, in that order, and "
                    + "%n ends the line.\n"
                    + "\n"
                    + "Putting the values in the right order matters as much as "
                    + "the specifiers: swap user and failures and %s gets an int "
                    + "(which it tolerates) while %d gets a String, which "
                    + "crashes. The template and the list are two halves of one "
                    + "statement, and Java only checks that they agree when the "
                    + "line runs.")
                .explain(
                    "One gap per value, in order, and %n to finish.")
                .xp(30))
            .mistakes(
                new String[]{"Forgetting %n",
                    "printf never adds a line break. The next output joins "
                    + "straight onto this line."},
                new String[]{"The wrong specifier",
                    "%d with a double compiles and then crashes when it runs. "
                    + "Use %f for doubles."},
                new String[]{"Values in the wrong order",
                    "Gaps are filled strictly left to right."})
            .cyber(
                "Most of what a security analyst reads is output from tools, "
                + "and most of it is read under time pressure. A report that "
                + "lines up lets the eye run down a column and stop at the one "
                + "value that is out of place. A ragged one gets skimmed, and "
                + "skimmed reports are where the one important line is "
                + "missed.\n"
                + "\n"
                + "One caution, carried from the last mission. Format templates "
                + "should always be written by you, never taken from input. "
                + "In some languages a user-supplied template is a serious "
                + "vulnerability in its own right, able to read or even write "
                + "memory. In Java it is mostly a way to crash the program - "
                + "but the habit to build is the same: input fills the gaps, "
                + "it never supplies the template.")
            .check(new Task(Task.CHOICE,
                    "Which specifier shows a double with one decimal place?")
                .choices("%d", "%1d", "%.1f", "%s1")
                .accept("3", "c")
                .hints("Doubles use f.", "The .1 sets the decimal places.")
                .explain(
                    "%.1f - f for a floating-point number, .1 for one place.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String row = String.format(\"[%5s]\", \"DB\");",
                    "System.out.println(row);")
                .accept("[   DB]")
                .hints(
                    "%5s pads to 5 columns, on the left.",
                    "DB is 2 characters, so 3 spaces come first.")
                .explain(
                    "[   DB] - three spaces then DB, five columns in all. "
                    + "Without a minus sign, padding goes on the left.")
                .xp(20))
            .recap(
                "    System.out.printf(template, values...);\n"
                + "\n"
                + "    %s  String    %d  whole number    %f  double\n"
                + "    %.1f  one decimal place    %n  line break\n"
                + "    %5d  right-aligned    %-8s  left-aligned\n"
                + "\n"
                + "Values fill gaps in order. A mismatch crashes at run time.\n"
                + "\n"
                + "String.format builds the same text without printing it.")
            .next("Next: taking a whole log line apart, using everything so "
                + "far."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(29), "Taking a Log Line Apart", 5)
            .brief(
                "The firewall writes one line per blocked connection:\n\n"
                + "    BLOCK 10.0.4.17 443\n\n"
                + "an action, a source address and a port, separated by single "
                + "spaces. The address is a different length on every line, so "
                + "fixed positions will not work.\n\n"
                + "Everything needed to take it apart properly, you already "
                + "have.")
            .willLearn("Parsing a log line", "indexOf with a starting point",
                       "Combining String methods")
            .whyUseful(
                "Turning a line of text into separate, typed values is the "
                + "first step of every log tool, SIEM parser and detection "
                + "rule. This mission is that step, written by you.")
            .concept("Parsing by separators",
                "The plan: find each space, cut between them, convert what "
                + "should be a number.\n"
                + "\n"
                + "One new detail. indexOf can start searching from a position "
                + "you choose:\n"
                + "\n"
                + "    s.indexOf(\" \", from)\n"
                + "\n"
                + "That finds the first space AT OR AFTER index from. To find "
                + "the second space, search from just past the first:\n"
                + "\n"
                + "    String line = \"BLOCK 10.0.4.17 443\";\n"
                + "    int first  = line.indexOf(\" \");\n"
                + "    int second = line.indexOf(\" \", first + 1);\n"
                + "\n"
                + "    index    0    5         15\n"
                + "    line     BLOCK 10.0.4.17 443\n"
                + "\n"
                + "first is 5 and second is 15. Now cut:\n"
                + "\n"
                + "    line.substring(0, first)             \"BLOCK\"\n"
                + "    line.substring(first + 1, second)    \"10.0.4.17\"\n"
                + "    line.substring(second + 1)           \"443\"\n"
                + "\n"
                + "Each + 1 steps over a space. The port is still text, so "
                + "convert it:\n"
                + "\n"
                + "    int port = Integer.parseInt(line.substring(second + 1));\n"
                + "\n"
                + "Nothing here depends on how long the address is. A line with "
                + "192.168.100.200 in it moves both spaces, and indexOf finds "
                + "them wherever they are.\n"
                + "\n"
                + "What this parser does NOT survive: a missing field, a double "
                + "space, a port that is not a number. Each is -1 or a crash "
                + "waiting to happen. Checking for them is Campaign 02; "
                + "recovering from them is Campaign 07. Knowing they exist is "
                + "today.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String line = \"BLOCK 10.0.4.17 443\";",
                "        int first = line.indexOf(\" \");",
                "        int second = line.indexOf(\" \", first + 1);",
                "        String action = line.substring(0, first);",
                "        String source = line.substring(first + 1, second);",
                "        int port = Integer.parseInt(line.substring(second + 1));",
                "        System.out.printf(\"%s from %s on port %d%n\",",
                "                action, source, port);",
                "    }",
                "}")
            .exampleOutput("BLOCK from 10.0.4.17 on port 443")
            .lineByLine(
                new String[]{"line.indexOf(\" \")",
                    "The first space, after BLOCK: index 5."},
                new String[]{"line.indexOf(\" \", first + 1)",
                    "Searching from 6 onward skips the first space and finds "
                    + "the second: 15."},
                new String[]{"substring(first + 1, second)",
                    "From just after the first space up to the second. The "
                    + "address, whatever its length."},
                new String[]{"Integer.parseInt(...)",
                    "The last field was text. Now it is an int you could "
                    + "compare or count with."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String s = \"a b c\";",
                    "System.out.println(s.indexOf(\" \", 2));")
                .accept("3", "three")
                .hints(
                    "Spaces are at 1 and 3.",
                    "The search starts at index 2, so the space at 1 is "
                    + "skipped.")
                .explain(
                    "3. Searching begins at index 2, so the space at 1 is never "
                    + "seen; the next one is at 3.")
                .xp(20))
            .practice(new Task(Task.CHOICE,
                    "With first and second holding the two space positions, "
                    + "which gives the MIDDLE field?")
                .choices(
                    "line.substring(first, second)",
                    "line.substring(first + 1, second)",
                    "line.substring(first + 1, second + 1)",
                    "line.substring(first, second - 1)")
                .accept("2", "b")
                .hints(
                    "The field starts one after the first space.",
                    "The end is not included, so ending AT the second space "
                    + "stops just before it.")
                .explain(
                    "substring(first + 1, second). Start one past the first "
                    + "space; end at the second, which is excluded. Option A "
                    + "includes the leading space, C includes the trailing one, "
                    + "and D does both wrong.")
                .xp(25))
            .objective(
                "Pull the attempt count off the end of an authentication line.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String line = \"FAIL m.reyes 7\";",
                "        int first = line.indexOf(\" \");",
                "        int second = line.indexOf(\" \", first + 1);",
                "        // take the attempt count here",
                "        System.out.println(\"NEXT WOULD BE \" + (attempts + 1));",
                "    }",
                "}")
            .yourTask(
                "The count is everything after the second space. Declare an int "
                + "called attempts holding it as a number, in one line.")
            .mainTask(new Task(Task.WRITE,
                    "Declare attempts: the text after second, as an int.")
                .accept("int attempts = Integer.parseInt(line.substring(second + 1));",
                        "int attempts = Integer.parseInt(line.substring(second + 1))")
                .hints(
                    "Two steps: cut the text out, then convert it.",
                    "line.substring(second + 1) gives the text after the space.",
                    "int attempts = Integer.parseInt(line.substring(second + 1));")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String line = \"FAIL m.reyes 7\";",
                    "        int first = line.indexOf(\" \");",
                    "        int second = line.indexOf(\" \", first + 1);",
                    "        int attempts = Integer.parseInt(line.substring(second + 1));",
                    "        System.out.println(\"NEXT WOULD BE \" + (attempts + 1));",
                    "    }",
                    "}")
                .whyItWorks(
                    "The inner call runs first: substring(second + 1) cuts \"7\" "
                    + "off the end, skipping the space. parseInt turns that into "
                    + "the int 7, and attempts + 1 is 8 - a sum, not \"71\".\n"
                    + "\n"
                    + "Without the + 1, the text would be \" 7\" with its "
                    + "leading space, and parseInt would crash. Six missions "
                    + "of detail - indexes, the excluded end, conversion, "
                    + "what crashes it - all had to be right at once for this "
                    + "line to work. That is what parsing is.")
                .explain(
                    "Cut the text after the space, then convert it.")
                .xp(40))
            .mistakes(
                new String[]{"Searching from first instead of first + 1",
                    "indexOf(\" \", first) finds the first space again, because "
                    + "the search includes its starting index."},
                new String[]{"Keeping the separator",
                    "substring(second) includes the space, and parseInt "
                    + "crashes on it."},
                new String[]{"Trusting the shape",
                    "A line with a missing field makes indexOf return -1. The "
                    + "parser does not notice; its output is just wrong."})
            .cyber(
                "Every SIEM, log shipper and detection pipeline starts by doing "
                + "what you just did: turning a line of text into named, typed "
                + "fields. Parsers are therefore some of the most attacked code "
                + "in security tooling, because they are the first thing to "
                + "touch data an attacker may control.\n"
                + "\n"
                + "A field containing an extra space shifts everything after "
                + "it, so the username lands where the address should be. A "
                + "missing field returns -1 and the whole line lands in one "
                + "variable. A number that is not a number crashes the "
                + "pipeline. Real parsers survive these by checking each step - "
                + "and by treating any line that does not fit as an event worth "
                + "reporting in itself.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String line = \"ALLOW 192.168.1.20 22\";",
                    "int first = line.indexOf(\" \");",
                    "int second = line.indexOf(\" \", first + 1);",
                    "System.out.println(line.substring(first + 1, second));")
                .accept("192.168.1.20")
                .hints(
                    "This is the middle field.",
                    "Between the two spaces.")
                .explain(
                    "192.168.1.20. A longer address than the example, and the "
                    + "same code found it - that is the point of searching for "
                    + "separators rather than counting positions.")
                .xp(20))
            .check(new Task(Task.PREDICT,
                    "The same parser meets a line with a field missing. What "
                    + "does this print?")
                .code(
                    "String line = \"BLOCK 10.0.4.17\";",
                    "int first = line.indexOf(\" \");",
                    "int second = line.indexOf(\" \", first + 1);",
                    "System.out.println(second);")
                .accept("-1")
                .hints(
                    "How many spaces are in this line?",
                    "What does indexOf give when it finds nothing?")
                .explain(
                    "-1. There is no second space. Any substring built from "
                    + "second from here on is wrong or a crash - which is why "
                    + "Campaign 02 starts by teaching the program to check.")
                .xp(20))
            .recap(
                "    int first  = line.indexOf(\" \");\n"
                + "    int second = line.indexOf(\" \", first + 1);\n"
                + "\n"
                + "    line.substring(0, first)            field 1\n"
                + "    line.substring(first + 1, second)   field 2\n"
                + "    line.substring(second + 1)          field 3\n"
                + "\n"
                + "Find separators, cut between them, convert what is "
                + "numeric. A missing separator is -1, silently.")
            .next("Next: the JAVA ZERO checkpoint."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(30), "JAVA ZERO COMPLETE", 5)
            .brief(
                "Thirty missions ago the audit tool could only print text "
                + "typed into it. Now it reads input, stores and converts "
                + "values, does arithmetic without losing data, and takes "
                + "text apart.\n\n"
                + "This checkpoint mixes all of it. No new Java - only whether "
                + "the old Java stuck.")
            .willLearn("Recall of the whole campaign")
            .whyUseful(
                "Each mission taught one idea on its own. Real code uses a "
                + "dozen at once, and the mistakes live where they meet: a "
                + "cast in the wrong place, a String method result not kept, "
                + "an index off by one inside a substring.")
            .concept("Everything, together",
                "The campaign in one page.\n"
                + "\n"
                + "TYPES\n"
                + "    int  long  double  boolean  char  String\n"
                + "    count with int, measure with double, huge with long\n"
                + "    final for values that must not change\n"
                + "\n"
                + "ARITHMETIC\n"
                + "    * / % before + -    brackets to be sure\n"
                + "    int / int throws the fraction away\n"
                + "    (double) a / b keeps it    (int) x cuts it off\n"
                + "    Math.round rounds, and hands back a long\n"
                + "    past Integer.MAX_VALUE wraps round, silently\n"
                + "\n"
                + "STRINGS - never change, so store what methods return\n"
                + "    length()  charAt(i)  substring(a, b)\n"
                + "    indexOf(x)  -1 when missing\n"
                + "    trim()  toLowerCase()  replace(a, b)\n"
                + "    contains  startsWith  endsWith - give booleans\n"
                + "    indexes run from 0 to length() - 1\n"
                + "\n"
                + "INPUT AND OUTPUT\n"
                + "    input.nextLine() - always a String, never trusted\n"
                + "    Integer.parseInt - crashes on anything not a number\n"
                + "    printf with %s %d %.1f %n\n"
                + "\n"
                + "SECURITY HABITS\n"
                + "    normalise, then compare\n"
                + "    clamp numbers from outside\n"
                + "    neutralise line breaks before logging\n"
                + "    wrong answers that do not crash are the dangerous ones")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        final int LOCKOUT = 5;",
                "        String raw = \"  JSmith \";",
                "        String user = raw.trim().toLowerCase();",
                "        int failures = 7;",
                "        int over = failures - LOCKOUT;",
                "        double perHour = (double) failures / 2;",
                "        System.out.printf(\"%s: %d failures, %d over, %.1f/hour%n\",",
                "                user, failures, over, perHour);",
                "    }",
                "}")
            .exampleOutput("jsmith: 7 failures, 2 over, 3.5/hour")
            .lineByLine(
                new String[]{"final int LOCKOUT = 5;",
                    "Mission 8. A policy value that nothing may move."},
                new String[]{"raw.trim().toLowerCase()",
                    "Missions 17 and 18. Normalised, and stored."},
                new String[]{"(double) failures / 2",
                    "Missions 10 and 14. The cast on an operand saves the .5."},
                new String[]{"printf",
                    "Mission 28. One template, four values, in order. A "
                    + "statement may carry on across lines - the semicolon "
                    + "ends it, not the line break."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "int a = 7;",
                    "int b = 2;",
                    "System.out.println(a / b + a % b);")
                .accept("4", "four")
                .hints(
                    "/ and % run before +.",
                    "7 / 2 in ints, then 7 % 2, then add.")
                .explain(
                    "4. 7 / 2 is 3 (fraction discarded), 7 % 2 is 1, and 3 + 1 "
                    + "is 4.")
                .xp(20))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "String host = \"WEB-01\";",
                    "char first = host.charAt(0);",
                    "int size = host.length;",
                    "System.out.println(first + \" \" + size);")
                .accept("3", "line 3")
                .hints(
                    "Every String method needs something at the end.",
                    "Look for missing brackets.")
                .explain(
                    "Line 3. length is a method, so it needs brackets: "
                    + "host.length(). Without them Java looks for a variable "
                    + "called length and finds none.")
                .xp(20))
            .objective(
                "Accept a page size from a caller, safely.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        String text = \"250\";",
                "        final int MAX = 100;",
                "        // convert and cap here",
                "        System.out.println(\"PAGE SIZE: \" + limit);",
                "    }",
                "}")
            .yourTask(
                "text came from outside. In ONE line, declare an int called "
                + "limit: text converted to a number, and never more than MAX.")
            .mainTask(new Task(Task.WRITE,
                    "Declare limit: text as an int, capped at MAX.")
                .accept("int limit = Math.min(Integer.parseInt(text), MAX);",
                        "int limit = Math.min(Integer.parseInt(text), MAX)",
                        "int limit = Math.min(MAX, Integer.parseInt(text));",
                        "int limit = Math.min(MAX, Integer.parseInt(text))")
                .hints(
                    "Two jobs: convert, then cap. The conversion goes inside.",
                    "A ceiling is Math.min. Converting is Integer.parseInt.",
                    "int limit = Math.min(Integer.parseInt(text), MAX);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        String text = \"250\";",
                    "        final int MAX = 100;",
                    "        int limit = Math.min(Integer.parseInt(text), MAX);",
                    "        System.out.println(\"PAGE SIZE: \" + limit);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The innermost call runs first: Integer.parseInt(text) "
                    + "turns \"250\" into 250. Math.min then compares 250 with "
                    + "MAX and keeps the smaller, 100.\n"
                    + "\n"
                    + "Two missions' ideas in one line, and the order is the "
                    + "logic: you cannot cap text, so conversion has to come "
                    + "first. The line still trusts that text is a number; "
                    + "'lots' would crash it. Making that decision safely is "
                    + "the first thing the next campaign teaches.")
                .explain(
                    "Convert inside, cap outside: you cannot take the smaller "
                    + "of two values until both are numbers.")
                .xp(40))
            .mistakes(
                new String[]{"Forgetting to store a String result",
                    "s.trim(); s.toLowerCase(); s.replace(...); - all do "
                    + "nothing unless kept."},
                new String[]{"Losing a fraction",
                    "Integer division, a cast on the result, a missing .0 - "
                    + "three routes to the same silent loss."},
                new String[]{"Off by one",
                    "Indexes start at 0. The end of substring is excluded. "
                    + "The last index is length() - 1."})
            .cyber(
                "Look back at the security lessons in this campaign and one "
                + "pattern repeats: the dangerous bugs were the quiet ones. "
                + "Integer division, overflow, truncation, ignored String "
                + "results, -1 used as a position. None of them crashed. Each "
                + "produced a believable wrong answer, and a believable wrong "
                + "answer in a security tool is a decision made on false "
                + "information.\n"
                + "\n"
                + "The other pattern: input decides. What someone types, or "
                + "sends, or puts in a file name, can crash a parser, forge a "
                + "log line or slip past a filter - unless it is normalised, "
                + "bounded and neutralised first.\n"
                + "\n"
                + "Both patterns need the same missing tool: the ability to "
                + "look at a value and DECIDE. That is Campaign 02.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String s = \"Admin\";",
                    "s.toLowerCase();",
                    "System.out.println(s.length() + s.substring(1, 3));")
                .accept("5dm")
                .hints(
                    "Line 2 did not store anything.",
                    "An int + a String joins.")
                .explain(
                    "5dm. s is still \"Admin\", length 5. substring(1, 3) is "
                    + "\"dm\". An int joined to a String gives \"5dm\" - three "
                    + "missions' traps in one line.")
                .xp(25))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code("System.out.println((int) 9.9 + Math.round(9.9));")
                .accept("19", "nineteen")
                .hints(
                    "The cast truncates. Math.round rounds.",
                    "9 + 10.")
                .explain(
                    "19. (int) 9.9 is 9, Math.round(9.9) is 10, and both are "
                    + "whole numbers, so + adds.")
                .xp(20))
            .check(new Task(Task.CHOICE,
                    "Which value is most likely to overflow an int?")
                .choices(
                    "Failed logins for one account today",
                    "Open ports on a host",
                    "Bytes sent by a file server this month",
                    "The lockout threshold")
                .accept("3", "c")
                .hints(
                    "An int tops out around 2.1 billion.",
                    "Which one grows without a natural limit?")
                .explain(
                    "Bytes sent this month. 2.1 billion bytes is about 2 GB, "
                    + "which a file server passes in minutes. That is a long.")
                .xp(15))
            .recap(
                "CAMPAIGN 01 - JAVA ZERO complete.\n"
                + "\n"
                + "You can store, convert, calculate, read input, take text "
                + "apart and write tidy reports - and you know where each of "
                + "those goes quietly wrong.\n"
                + "\n"
                + "What your programs cannot do yet is choose. Every line runs, "
                + "every time, whatever the data says.")
            .next("Next: CAMPAIGN 02 - CONDITIONAL. Programs that decide."));
    }
}
