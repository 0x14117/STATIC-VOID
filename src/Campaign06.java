/**
 * CAMPAIGN 06 - OBJECTS
 * Types of your own. Thirty missions.
 *
 * Campaign 05 ended with parallel lists: a name here, its count there, kept
 * in step by care alone. A class puts them together - one Account holding
 * its user AND its failures AND its lock - so they cannot drift apart.
 *
 * Every class in this campaign lives in Main.java, after Main's closing
 * brace; mission 25 shows how real projects give each class its own file.
 * A mission may use Campaigns 00 to 05, plus whatever this campaign has
 * already introduced - and nothing else.
 */
public class Campaign06 {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(1), "A Type of Your Own", 3)
            .brief(
                "Campaign 05's top-offender report kept each user in one "
                + "list and their failure count in another, joined only by "
                + "a shared index. One careless sort and every count "
                + "belonged to someone else. What the report really wanted "
                + "was ONE thing - an account - holding its user and its "
                + "count together. Java lets you invent that type.")
            .willLearn("Classes")
            .whyUseful(
                "Real data comes in records: an account has a name, a "
                + "count and a lock; an alert has a rule, a host and a "
                + "severity. A class describes a record once, and then "
                + "every value of that type carries all its parts together.")
            .concept("Classes",
                "A CLASS declares a new type, and lists the pieces of data "
                + "every value of that type holds:\n"
                + "\n"
                + "    class Account {\n"
                + "        String user;\n"
                + "        int failures;\n"
                + "    }\n"
                + "\n"
                + "Account is now a type, like String. A variable of that type "
                + "holds one account, and one account holds both a user and "
                + "a failure count:\n"
                + "\n"
                + "    Account a = new Account();\n"
                + "    a.user = \"admin\";\n"
                + "    a.failures = 14;\n"
                + "\n"
                + "The next four missions take those three lines apart: what "
                + "an object is (2), the fields inside it (3), what new does "
                + "(4) and the dot (5).\n"
                + "\n"
                + "Where does the class go? In Main.java, AFTER the closing "
                + "brace of class Main - not inside main. Class names start "
                + "with a capital letter, like String and Scanner. (Mission "
                + "25 moves each class into a file of its own.)\n"
                + "\n"
                + "Compare the old way: String[] users and int[] fails, "
                + "matched by index. An Account[] cannot be put out of step "
                + "- the user and the count travel together.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Account a = new Account();",
                "        a.user = \"admin\";",
                "        a.failures = 14;",
                "        System.out.println(a.user + \": \" + a.failures);",
                "    }",
                "}",
                "",
                "class Account {",
                "    String user;",
                "    int failures;",
                "}")
            .exampleOutput(
                "admin: 14")
            .lineByLine(
                new String[]{"class Account { ... }",
                    "A new type, declared after Main. Every Account has a "
                    + "user and a failures count."},
                new String[]{"Account a = new Account();",
                    "A variable of the new type, holding one new account."},
                new String[]{"a.user = \"admin\";",
                    "Sets THIS account's user. The dot reaches inside it."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Host {",
                    "    String name;",
                    "    int port;",
                    "}",
                    "",
                    "Host h = new Host();",
                    "h.name = \"db1\";",
                    "h.port = 5432;",
                    "System.out.println(h.name + \":\" + h.port);")
                .accept("db1:5432")
                .hints("h holds one Host, with a name and a port.",
                       "Both were set before the println.")
                .explain(
                    "db1:5432. The Host h carries both values, and the dot "
                    + "reads each one back out.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "In Main.java, where does class Account go?")
                .choices("Inside the main method",
                         "After the closing brace of class Main",
                         "Before the import lines",
                         "Inside a comment")
                .accept("2", "b")
                .hints("A class is not a statement.",
                       "It sits beside Main, not inside main.")
                .explain(
                    "b. A class is declared at the top level of the file, "
                    + "after Main's last brace. Inside main, Java expects "
                    + "statements, not declarations of types.")
                .xp(10))
            .objective(
                "Give the Alert type a severity.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Alert a = new Alert();",
                "        a.rule = \"Brute force\";",
                "        a.severity = 8;",
                "        System.out.println(a.rule + \" (severity \" + a.severity + \")\");",
                "    }",
                "}",
                "",
                "class Alert {",
                "    String rule;",
                "    // a whole-number field called severity",
                "}")
            .yourTask(
                "Every Alert needs a whole-number severity as well as its "
                + "rule. Write the line that adds it to the class.")
            .mainTask(new Task(Task.WRITE,
                    "Write the field line.")
                .accept("int severity;")
                .hints(
                    "A field is written like a variable declaration.",
                    "Type, then name, then ;",
                    "int severity;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Alert a = new Alert();",
                    "        a.rule = \"Brute force\";",
                    "        a.severity = 8;",
                    "        System.out.println(a.rule + \" (severity \" + a.severity + \")\");",
                    "    }",
                    "}",
                    "",
                    "class Alert {",
                    "    String rule;",
                    "    int severity;",
                    "}")
                .whyItWorks(
                    "With int severity; in the class, every Alert has a "
                    + "severity as well as a rule, so a.severity = 8 has "
                    + "somewhere to go. It prints Brute force (severity 8).\n"
                    + "\n"
                    + "Without the line, main does not compile: 'cannot find "
                    + "symbol - severity'. Java only lets you use the parts "
                    + "the class says every Alert has.")
                .explain(
                    "int severity; - a line inside the class, like a variable.")
                .xp(15))
            .mistakes(
                new String[]{"Declaring the class inside main",
                    "Put it after Main's closing brace."},
                new String[]{"class account",
                    "Class names start with a capital: Account."},
                new String[]{"Using a part the class does not list",
                    "'cannot find symbol'. Add it to the class first."})
            .cyber(
                "Security data is made of records: an authentication event "
                + "has a time, a user, a source and a result; a "
                + "vulnerability has an ID, a host and a score. Kept in "
                + "separate lists, one slip pairs the wrong parts and the "
                + "report points at the wrong person. A class makes the "
                + "record one value, so its parts can only move together.")
            .check(new Task(Task.CHOICE,
                    "What does declaring class Account do?")
                .choices("Creates one account",
                         "Declares a new type that has the listed parts",
                         "Prints an account",
                         "Makes an array of accounts")
                .accept("2", "b")
                .hints("A class is a description.",
                       "Nothing is created until new is used.")
                .explain(
                    "b. The class describes what every Account is made of. "
                    + "Actual accounts are made later, with new (mission 4).")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Rule {",
                    "    int port;",
                    "    boolean allow;",
                    "}",
                    "",
                    "Rule r = new Rule();",
                    "r.port = 22;",
                    "r.allow = false;",
                    "System.out.println(r.port + \" \" + r.allow);")
                .accept("22 false")
                .hints("Both parts were set.",
                       "Read each one with the dot.")
                .explain(
                    "22 false. One Rule, two parts, both read back with "
                    + "the dot.")
                .xp(10))
            .recap(
                "    class Account {       a new type\n"
                + "        String user;      its parts\n"
                + "        int failures;\n"
                + "    }\n"
                + "\n"
                + "Declared after Main in Main.java, named with a capital. "
                + "Its parts travel together.")
            .next("Next: one class, many objects."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(2), "Blueprint and Building", 3)
            .brief(
                "The identity system has one description of what an account "
                + "is - and forty thousand accounts. Locking one of them "
                + "must not lock the other 39,999. That is the difference "
                + "between a class and the objects made from it, and it is "
                + "worth getting straight before anything else.")
            .willLearn("Objects")
            .whyUseful(
                "Every program in this campaign makes many objects from few "
                + "classes. Knowing that each object has its OWN copy of "
                + "every field explains why changing one account never "
                + "changes another.")
            .concept("Objects",
                "A class is a BLUEPRINT. An OBJECT is one thing built from "
                + "it. One blueprint, any number of buildings:\n"
                + "\n"
                + "    class Account            the blueprint (once)\n"
                + "    new Account()            one object (each time)\n"
                + "\n"
                + "Every object gets its OWN copy of every field the class "
                + "lists:\n"
                + "\n"
                + "    Account a = new Account();\n"
                + "    Account b = new Account();\n"
                + "    a.user = \"admin\";     a's user\n"
                + "    b.user = \"jsmith\";    b's user - a separate slot\n"
                + "\n"
                + "    a  ->  [ user: admin   failures: 0 ]\n"
                + "    b  ->  [ user: jsmith  failures: 0 ]\n"
                + "\n"
                + "Setting a.failures changes a's failures and nothing "
                + "else. That is exactly how the real world works: locking "
                + "one account does not lock them all.\n"
                + "\n"
                + "Objects are also called INSTANCES of their class: a and b "
                + "are two instances of Account.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Account a = new Account();",
                "        Account b = new Account();",
                "        a.user = \"admin\";",
                "        b.user = \"jsmith\";",
                "        a.failures = 5;",
                "        a.locked = true;",
                "        System.out.println(a.user + \" \" + a.failures",
                "                + \" \" + a.locked);",
                "        System.out.println(b.user + \" \" + b.failures",
                "                + \" \" + b.locked);",
                "    }",
                "}",
                "",
                "class Account {",
                "    String user;",
                "    int failures;",
                "    boolean locked;",
                "}")
            .exampleOutput(
                "admin 5 true",
                "jsmith 0 false")
            .lineByLine(
                new String[]{"two new Account() calls",
                    "Two separate objects, each with its own three fields."},
                new String[]{"a.locked = true;",
                    "Locks a. b's locked field is a different slot."},
                new String[]{"jsmith 0 false",
                    "b was never changed, so it still holds the starting "
                    + "values (mission 3 explains 0 and false)."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Counter {",
                    "    int n;",
                    "}",
                    "",
                    "Counter x = new Counter();",
                    "Counter y = new Counter();",
                    "x.n = 3;",
                    "y.n = x.n + 4;",
                    "x.n++;",
                    "System.out.println(x.n + \" \" + y.n);")
                .accept("4 7")
                .hints("x and y each have their own n.",
                       "y.n was set from x.n BEFORE x.n went up.")
                .explain(
                    "4 7. y.n got 3 + 4 = 7; then x.n went from 3 to 4. The "
                    + "two n fields never share a value.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "A system has one class Account and 500 users. How many "
                    + "classes and objects is that?")
                .choices("500 classes, 1 object",
                         "1 class, 500 objects",
                         "1 class, 1 object",
                         "500 classes, 500 objects")
                .accept("2", "b")
                .hints("How many blueprints are needed?",
                       "How many buildings?")
                .explain(
                    "b. The class is written once; each user is one object "
                    + "made from it.")
                .xp(10))
            .objective(
                "Give the second analyst an object of their own.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Analyst first = new Analyst();",
                "        first.name = \"Ana\";",
                "        // second: another, separate Analyst object",
                "        second.name = \"Ben\";",
                "        System.out.println(first.name + \" and \" + second.name);",
                "    }",
                "}",
                "",
                "class Analyst {",
                "    String name;",
                "}")
            .yourTask(
                "Declare second as a new, separate Analyst object.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line that declares second.")
                .accept("Analyst second = new Analyst();",
                        "Analyst second=new Analyst();")
                .hints(
                    "The same shape as the line that made first.",
                    "Type, name, =, new Analyst().",
                    "Analyst second = new Analyst();")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Analyst first = new Analyst();",
                    "        first.name = \"Ana\";",
                    "        Analyst second = new Analyst();",
                    "        second.name = \"Ben\";",
                    "        System.out.println(first.name + \" and \" + second.name);",
                    "    }",
                    "}",
                    "",
                    "class Analyst {",
                    "    String name;",
                    "}")
                .whyItWorks(
                    "new Analyst() builds a second object with its own name "
                    + "field, so setting it to Ben leaves Ana untouched: Ana "
                    + "and Ben.\n"
                    + "\n"
                    + "One class, two objects. A third analyst would need "
                    + "only a third new - never a change to the class.")
                .explain(
                    "Analyst second = new Analyst(); - a second object.")
                .xp(15))
            .mistakes(
                new String[]{"Thinking fields are shared",
                    "Each object has its own copy of every field."},
                new String[]{"One class per user",
                    "One class; one object per user."},
                new String[]{"Mixing up the words",
                    "Class = blueprint. Object = instance = one built thing."})
            .cyber(
                "Separate objects means separate state, and state is where "
                + "security lives: this account's failures, that session's "
                + "expiry, this host's patch level. A bug that made two "
                + "users share one object would be a serious flaw - "
                + "locking one would lock both, or unlocking an attacker "
                + "would unlock everyone. Mission 6 shows how that sharing "
                + "can happen by accident.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Flag {",
                    "    boolean on;",
                    "}",
                    "",
                    "Flag p = new Flag();",
                    "Flag q = new Flag();",
                    "p.on = true;",
                    "System.out.println(p.on + \" \" + q.on);")
                .accept("true false")
                .hints("Only p was changed.",
                       "q has its own on field.")
                .explain(
                    "true false. Two objects, two separate on fields.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which word means the same as object?")
                .choices("Class", "Instance", "Field", "Method")
                .accept("2", "b")
                .hints("An object is an ... of its class.",
                       "a and b are two ... of Account.")
                .explain(
                    "b. An object is an instance of its class; the class is "
                    + "the description it was built from.")
                .xp(10))
            .recap(
                "    class Account     the blueprint, written once\n"
                + "    new Account()     one object - an instance\n"
                + "\n"
                + "Each object has its own copy of every field. Changing "
                + "one object never changes another.")
            .next("Next: the fields inside an object."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(3), "What an Object Holds", 3)
            .brief(
                "A vulnerability record needs an ID, a CVSS score with a "
                + "decimal point, whether a patch exists, and a list of "
                + "affected hosts. Each of those is a field, and each has "
                + "its own type. And a brand-new record, before anyone "
                + "fills it in, already holds something in every field - "
                + "worth knowing exactly what.")
            .willLearn("Fields")
            .whyUseful(
                "Fields are an object's memory. Choosing their types well - "
                + "and knowing what they hold before they are set - avoids "
                + "a whole class of bugs, including the null crash of "
                + "mission 7.")
            .concept("Fields",
                "A FIELD is a variable declared inside a class, outside any "
                + "method. Every object gets its own copy. Any type will do:\n"
                + "\n"
                + "    class Vuln {\n"
                + "        String id;          text\n"
                + "        double score;       a decimal\n"
                + "        boolean patched;    yes or no\n"
                + "        String[] hosts;     an array\n"
                + "    }\n"
                + "\n"
                + "Fields are also called the class's MEMBER variables or "
                + "INSTANCE variables - they belong to each instance.\n"
                + "\n"
                + "A field you have not set is NOT an error. Like the slots "
                + "of a new array (Campaign 05), it starts with a DEFAULT:\n"
                + "\n"
                + "    int, double    0 and 0.0\n"
                + "    boolean        false\n"
                + "    String, array, any object type   null\n"
                + "\n"
                + "That is different from a LOCAL variable - one declared "
                + "inside a method. Java refuses to let you read a local "
                + "before you give it a value ('might not have been "
                + "initialized'). Fields always have a value; locals must "
                + "be given one.\n"
                + "\n"
                + "The defaults are safe for numbers and booleans. null is "
                + "not: calling a method on it crashes, which mission 7 "
                + "covers.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Vuln v = new Vuln();",
                "        System.out.println(v.id + \" \" + v.score + \" \" + v.patched);",
                "        v.id = \"CVE-2024-3094\";",
                "        v.score = 10.0;",
                "        v.hosts = new String[]{\"build1\", \"build2\"};",
                "        System.out.println(v.id + \" \" + v.score + \" \" + v.patched);",
                "        System.out.println(\"Hosts: \" + v.hosts.length);",
                "    }",
                "}",
                "",
                "class Vuln {",
                "    String id;",
                "    double score;",
                "    boolean patched;",
                "    String[] hosts;",
                "}")
            .exampleOutput(
                "null 0.0 false",
                "CVE-2024-3094 10.0 false",
                "Hosts: 2")
            .lineByLine(
                new String[]{"null 0.0 false",
                    "A new object's fields hold their defaults - no error."},
                new String[]{"v.hosts = new String[]{...};",
                    "A field can hold an array. Until then it was null."},
                new String[]{"v.patched",
                    "Never set, so still false."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Sensor {",
                    "    String room;",
                    "    int reading;",
                    "    boolean alarm;",
                    "}",
                    "",
                    "Sensor s = new Sensor();",
                    "s.reading = 42;",
                    "System.out.println(s.room + \" \" + s.reading + \" \" + s.alarm);")
                .accept("null 42 false")
                .hints("Only reading was set.",
                       "String fields start as null, booleans as false.")
                .explain(
                    "null 42 false. room and alarm keep their defaults; "
                    + "printing a null String shows the word null.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "class Box {",
                    "    int n;",
                    "}",
                    "",
                    "Box b = new Box();",
                    "System.out.println(b.n);",
                    "int m;",
                    "System.out.println(m);")
                .accept("8", "line 8")
                .hints("One of these is a field, one a local.",
                       "Which one was never given a value?")
                .explain(
                    "Line 8: 'variable m might not have been initialized'. "
                    + "b.n is a field and starts at 0; m is a local, and "
                    + "locals must be given a value before they are read.")
                .xp(20))
            .objective(
                "Add a lock flag to the Session class.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Session s = new Session();",
                "        s.user = \"jsmith\";",
                "        System.out.println(\"Locked at start: \" + s.locked);",
                "        s.locked = true;",
                "        System.out.println(\"Locked now: \" + s.locked);",
                "    }",
                "}",
                "",
                "class Session {",
                "    String user;",
                "    // a true/false field called locked",
                "}")
            .yourTask(
                "Add a boolean field called locked to Session. Do not give "
                + "it a starting value - see what the default is.")
            .mainTask(new Task(Task.WRITE,
                    "Write the field line.")
                .accept("boolean locked;")
                .hints(
                    "True/false is boolean.",
                    "Type, name, semicolon.",
                    "boolean locked;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Session s = new Session();",
                    "        s.user = \"jsmith\";",
                    "        System.out.println(\"Locked at start: \" + s.locked);",
                    "        s.locked = true;",
                    "        System.out.println(\"Locked now: \" + s.locked);",
                    "    }",
                    "}",
                    "",
                    "class Session {",
                    "    String user;",
                    "    boolean locked;",
                    "}")
                .whyItWorks(
                    "A new Session's locked field starts at false, the "
                    + "boolean default, so the first line prints false and "
                    + "the second true.\n"
                    + "\n"
                    + "That default is a design choice worth noticing: a "
                    + "new session starts UNlocked. For something like "
                    + "'is this user an admin', false as the default is "
                    + "exactly the safe starting point you want.")
                .explain(
                    "boolean locked; - starts as false until set.")
                .xp(15))
            .mistakes(
                new String[]{"Declaring fields inside main",
                    "Those are locals. Fields go in the class body."},
                new String[]{"Expecting an error for an unset field",
                    "Fields start at 0, 0.0, false or null."},
                new String[]{"Forgetting null is the object default",
                    "A String field is null until set."})
            .cyber(
                "Defaults are a security decision. A boolean isAdmin that "
                + "starts false fails safe: forget to set it and the user "
                + "gets less access, not more. A boolean isBlocked that "
                + "starts false fails OPEN: forget to set it and a blocked "
                + "user walks in. Name and choose fields so that the "
                + "default value is the safe one.")
            .check(new Task(Task.CHOICE,
                    "A field declared as double rate; in a class. What is "
                    + "it in a brand-new object?")
                .choices("It cannot be read", "0.0", "null", "Random")
                .accept("2", "b")
                .hints("Numbers default to zero.",
                       "It is a double.")
                .explain(
                    "b. 0.0. Number fields start at zero, booleans at false, "
                    + "and object types - String, arrays - at null.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which boolean field has the SAFER default?")
                .choices("boolean accessAllowed;  (false = no access)",
                         "boolean accessDenied;  (false = access)",
                         "Both are equally safe",
                         "Neither has a default")
                .accept("1", "a")
                .hints("What happens if nobody sets it?",
                       "false should mean the cautious answer.")
                .explain(
                    "a. If nobody sets accessAllowed, it stays false: no "
                    + "access. An unset accessDenied is false too - which "
                    + "means access. Fail safe, not open.")
                .xp(10))
            .recap(
                "    fields: variables inside the class, one copy per object\n"
                + "    defaults: 0, 0.0, false, null\n"
                + "    locals: must be given a value before use\n"
                + "\n"
                + "Choose field meanings so the default is the safe one.")
            .next("Next: what new really does."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(4), "new: Making an Object", 4)
            .brief(
                "A shift rota was stored as an array of five Analyst "
                + "objects. The first report crashed with "
                + "NullPointerException before printing a name. The array "
                + "was there. The analysts were not: making an array of "
                + "objects makes the slots, not the objects.")
            .willLearn("new")
            .whyUseful(
                "Knowing exactly when an object exists - and when you only "
                + "have a variable that could point at one - prevents the "
                + "most common crash in object-oriented Java.")
            .concept("new",
                "Declaring a variable of a class type does NOT make an "
                + "object:\n"
                + "\n"
                + "    Account a;              a variable - no object yet\n"
                + "    a = new Account();      NOW there is an object\n"
                + "\n"
                + "new does three things: it makes the object in memory, "
                + "gives every field its default (mission 3), and hands "
                + "back a REFERENCE to it - where it is - which the "
                + "variable keeps. (Mission 10 adds a fourth: it runs a "
                + "constructor.)\n"
                + "\n"
                + "Each new is a separate object. In a loop, new makes one "
                + "per pass.\n"
                + "\n"
                + "ARRAYS OF OBJECTS catch everyone once:\n"
                + "\n"
                + "    Analyst[] rota = new Analyst[5];\n"
                + "\n"
                + "makes an array of 5 slots - all null, the default for "
                + "object types. There are no Analysts yet. Each slot needs "
                + "its own new:\n"
                + "\n"
                + "    for (int i = 0; i < rota.length; i++) {\n"
                + "        rota[i] = new Analyst();\n"
                + "    }\n"
                + "\n"
                + "Reading rota[0].name before that crashes: there is no "
                + "object in slot 0 to read a name from.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Analyst[] rota = new Analyst[3];",
                "        System.out.println(\"Slot 0 before: \" + rota[0]);",
                "        String[] names = {\"Ana\", \"Ben\", \"Cal\"};",
                "        for (int i = 0; i < rota.length; i++) {",
                "            rota[i] = new Analyst();",
                "            rota[i].name = names[i];",
                "        }",
                "        for (Analyst a : rota) {",
                "            System.out.println(\"On shift: \" + a.name);",
                "        }",
                "    }",
                "}",
                "",
                "class Analyst {",
                "    String name;",
                "}")
            .exampleOutput(
                "Slot 0 before: null",
                "On shift: Ana",
                "On shift: Ben",
                "On shift: Cal")
            .lineByLine(
                new String[]{"new Analyst[3]",
                    "Three empty slots. Each holds null, not an Analyst."},
                new String[]{"rota[i] = new Analyst();",
                    "One new per slot: now slot i refers to a real object."},
                new String[]{"for (Analyst a : rota)",
                    "The enhanced for works on arrays of objects too: a is "
                    + "each Analyst in turn."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Host {",
                    "    String name;",
                    "}",
                    "",
                    "Host[] hosts = new Host[2];",
                    "hosts[1] = new Host();",
                    "hosts[1].name = \"fw1\";",
                    "System.out.println(hosts[0] + \" \" + hosts[1].name);")
                .accept("null fw1")
                .hints("Which slot got a new Host?",
                       "The other slot still holds its default.")
                .explain(
                    "null fw1. Only slot 1 was given an object; slot 0 is "
                    + "still null. Printing null is fine - reading "
                    + "hosts[0].name would crash.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "class Account {",
                    "    String user;",
                    "}",
                    "",
                    "Account a;",
                    "a.user = \"root\";",
                    "System.out.println(a.user);")
                .accept("6", "line 6")
                .hints("Was an object ever made?",
                       "a is a local variable with no value.")
                .explain(
                    "Line 6: 'variable a might not have been initialized'. "
                    + "Account a; is only a variable. Without = new "
                    + "Account() there is no object to put a user in.")
                .xp(20))
            .objective(
                "Fill the rota with real objects.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Analyst[] team = new Analyst[3];",
                "        for (int i = 0; i < team.length; i++) {",
                "            // give slot i a new Analyst",
                "            team[i].shift = i + 1;",
                "        }",
                "        System.out.println(\"Last shift: \" + team[2].shift);",
                "    }",
                "}",
                "",
                "class Analyst {",
                "    int shift;",
                "}")
            .yourTask(
                "Write the line that puts a new Analyst object into slot i "
                + "of team, so the next line has an object to set.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line inside the loop.")
                .accept("team[i] = new Analyst();",
                        "team[i]=new Analyst();")
                .hints(
                    "The slot is team[i].",
                    "Assign it a new object of the class.",
                    "team[i] = new Analyst();")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Analyst[] team = new Analyst[3];",
                    "        for (int i = 0; i < team.length; i++) {",
                    "            team[i] = new Analyst();",
                    "            team[i].shift = i + 1;",
                    "        }",
                    "        System.out.println(\"Last shift: \" + team[2].shift);",
                    "    }",
                    "}",
                    "",
                    "class Analyst {",
                    "    int shift;",
                    "}")
                .whyItWorks(
                    "Each pass makes one Analyst and stores it in its slot, "
                    + "then sets its shift. After the loop, three separate "
                    + "objects exist, and team[2].shift is 3.\n"
                    + "\n"
                    + "Without the line, team[i] is null and "
                    + "team[i].shift = ... throws NullPointerException on "
                    + "the very first pass.")
                .explain(
                    "team[i] = new Analyst(); - an object for the slot.")
                .xp(15))
            .mistakes(
                new String[]{"Account a; then using a",
                    "A variable is not an object. Use new."},
                new String[]{"new Analyst[5] and expecting analysts",
                    "Five null slots. Fill each with new."},
                new String[]{"One new outside the loop",
                    "Every slot then shares ONE object (mission 6)."})
            .cyber(
                "A null where an object was expected is one of the most "
                + "common crashes in Java services - and a crash in an "
                + "authentication or logging service is an outage, or a "
                + "gap in the record. Knowing that arrays of objects start "
                + "empty, and filling or checking every slot, removes a "
                + "whole family of them.")
            .check(new Task(Task.CHOICE,
                    "After Host[] h = new Host[4]; how many Host objects "
                    + "exist?")
                .choices("4", "1", "0", "It depends on the class")
                .accept("3", "c")
                .hints("What does a new object array's slot hold?",
                       "null is not an object.")
                .explain(
                    "c. None. There are four slots, all null. Each Host "
                    + "needs its own new.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Tick {",
                    "    int n;",
                    "}",
                    "",
                    "int made = 0;",
                    "for (int i = 0; i < 4; i++) {",
                    "    Tick t = new Tick();",
                    "    t.n = i;",
                    "    made++;",
                    "}",
                    "System.out.println(made);")
                .accept("4")
                .hints("How many times does new run?",
                       "Once per pass.")
                .explain(
                    "4. new inside the loop makes a fresh Tick on every "
                    + "pass - four separate objects.")
                .xp(10))
            .recap(
                "    Account a;              a variable, no object\n"
                + "    a = new Account();      an object, fields defaulted\n"
                + "    new Account[5]          five null slots\n"
                + "\n"
                + "Every object needs its own new.")
            .next("Next: reaching inside objects with the dot."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(5), "Using the Dot", 3)
            .brief(
                "The patch report walks an array of Host objects and needs "
                + "each host's name in capitals, a count of hosts still "
                + "waiting for a patch, and to mark each checked one as "
                + "seen. All of it is the dot - reading a field, writing "
                + "one, and calling a method on what a field holds.")
            .willLearn("The dot operator")
            .whyUseful(
                "The dot is how every piece of an object is reached. "
                + "Reading it fluently - including chains like "
                + "hosts[i].name.length() - is most of what using objects "
                + "means.")
            .concept("The dot operator",
                "object.field reaches a field inside an object, to read it "
                + "or to change it:\n"
                + "\n"
                + "    h.name                read\n"
                + "    h.patched = true;     write\n"
                + "    h.misses++;           update\n"
                + "\n"
                + "A field used this way works exactly like a variable: in "
                + "expressions, conditions, +=, ++.\n"
                + "\n"
                + "The dot CHAINS, read left to right:\n"
                + "\n"
                + "    hosts[i].name.toUpperCase()\n"
                + "    hosts[i]          the Host in slot i\n"
                + "    .name             its name, a String\n"
                + "    .toUpperCase()    a String method on that name\n"
                + "\n"
                + "You have used the dot since Campaign 00 - "
                + "System.out.println is a chain of dots, and "
                + "text.length() is a dot too. Now the thing on the left can "
                + "be an object of your own.\n"
                + "\n"
                + "Each dot needs something real on its left. If "
                + "hosts[i] is null, or its name is null, the next dot has "
                + "nothing to reach into - mission 7.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Host[] hosts = new Host[3];",
                "        String[] names = {\"web1\", \"db1\", \"fw1\"};",
                "        for (int i = 0; i < hosts.length; i++) {",
                "            hosts[i] = new Host();",
                "            hosts[i].name = names[i];",
                "        }",
                "        hosts[1].patched = true;",
                "        int waiting = 0;",
                "        for (Host h : hosts) {",
                "            if (!h.patched) {",
                "                waiting++;",
                "                String loud = h.name.toUpperCase();",
                "                System.out.println(\"Needs patch: \" + loud);",
                "            }",
                "        }",
                "        System.out.println(\"Waiting: \" + waiting);",
                "    }",
                "}",
                "",
                "class Host {",
                "    String name;",
                "    boolean patched;",
                "}")
            .exampleOutput(
                "Needs patch: WEB1",
                "Needs patch: FW1",
                "Waiting: 2")
            .lineByLine(
                new String[]{"hosts[1].patched = true;",
                    "Array slot, then field: the Host in slot 1 is patched."},
                new String[]{"if (!h.patched)",
                    "A boolean field works as a condition directly."},
                new String[]{"h.name.toUpperCase()",
                    "Field, then a String method on it - a chain of dots."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Acct {",
                    "    String user;",
                    "    int fails;",
                    "}",
                    "",
                    "Acct a = new Acct();",
                    "a.user = \"svc_backup\";",
                    "a.fails += 2;",
                    "a.fails++;",
                    "System.out.println(a.user.length() + a.fails);")
                .accept("13")
                .hints("svc_backup has 10 characters.",
                       "fails went 0, 2, 3.")
                .explain(
                    "13. a.user.length() is 10 and a.fails is 3; both are "
                    + "ints, so + adds them.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "hosts is a Host[]. Which reads the length of the name "
                    + "of the host in slot 2?")
                .choices("hosts.name[2].length()",
                         "hosts[2].name.length()",
                         "hosts[2].length().name",
                         "hosts.length[2].name")
                .accept("2", "b")
                .hints("Left to right: the slot, then its field, then the "
                       + "method.",
                       "hosts[2] is a Host.")
                .explain(
                    "b. hosts[2] is the Host, .name its String, .length() "
                    + "that String's length.")
                .xp(10))
            .objective(
                "Record one more failed login on an account.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Acct acct = new Acct();",
                "        acct.user = \"admin\";",
                "        acct.fails = 2;",
                "        // add one to acct's fails",
                "        if (acct.fails >= 3) {",
                "            acct.locked = true;",
                "        }",
                "        System.out.println(acct.user + \" locked: \" + acct.locked);",
                "    }",
                "}",
                "",
                "class Acct {",
                "    String user;",
                "    int fails;",
                "    boolean locked;",
                "}")
            .yourTask(
                "Write the line that adds one to the fails field of acct.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line.")
                .accept("acct.fails++;",
                        "acct.fails += 1;",
                        "acct.fails = acct.fails + 1;",
                        "++acct.fails;")
                .hints(
                    "Reach the field with the dot.",
                    "++ works on a field like on any variable.",
                    "acct.fails++;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Acct acct = new Acct();",
                    "        acct.user = \"admin\";",
                    "        acct.fails = 2;",
                    "        acct.fails++;",
                    "        if (acct.fails >= 3) {",
                    "            acct.locked = true;",
                    "        }",
                    "        System.out.println(acct.user + \" locked: \" + acct.locked);",
                    "    }",
                    "}",
                    "",
                    "class Acct {",
                    "    String user;",
                    "    int fails;",
                    "    boolean locked;",
                    "}")
                .whyItWorks(
                    "acct.fails++ reaches into the object and raises its "
                    + "fails from 2 to 3, so the lockout check passes and "
                    + "prints admin locked: true.\n"
                    + "\n"
                    + "The count and the lock live in the SAME object as the "
                    + "user, so no index can ever pair them with the wrong "
                    + "account - the problem this campaign began with.")
                .explain(
                    "acct.fails++; - the field, through the dot.")
                .xp(15))
            .mistakes(
                new String[]{"fails++ on its own",
                    "Which object's fails? Say acct.fails."},
                new String[]{"Dotting through null",
                    "Every left-hand side must be a real object."},
                new String[]{"hosts.name[i]",
                    "Slot first, then field: hosts[i].name."})
            .cyber(
                "Lockout logic reads naturally with objects: acct.fails++, "
                + "then if (acct.fails >= 3) acct.locked = true. The state "
                + "that matters for security - counts, flags, timestamps - "
                + "sits in the object it belongs to, and the dot makes it "
                + "obvious in review whose state each line changes.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Host {",
                    "    String name;",
                    "}",
                    "",
                    "Host h = new Host();",
                    "h.name = \"web01\";",
                    "System.out.println(h.name.substring(0, 3).toUpperCase());")
                .accept("WEB")
                .hints("Left to right.",
                       "substring(0, 3) is web.")
                .explain(
                    "WEB. The field, then substring on it, then "
                    + "toUpperCase on that result.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which line sets the patched field of the Host in slot "
                    + "0 of hosts?")
                .choices("hosts.patched[0] = true;",
                         "hosts[0].patched = true;",
                         "patched[0] = true;",
                         "hosts[0] = patched;")
                .accept("2", "b")
                .hints("Slot, then field.",
                       "hosts[0] is a Host.")
                .explain(
                    "b. hosts[0] picks the Host; .patched picks its field.")
                .xp(10))
            .recap(
                "    obj.field            read or write a field\n"
                + "    obj.field++          update it\n"
                + "    arr[i].field         a field of the object in slot i\n"
                + "    obj.text.length()    chains, left to right\n"
                + "\n"
                + "Every dot needs a real object on its left.")
            .next("Next: two variables, one object."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(6), "Two Names, One Object", 4)
            .brief(
                "Before resetting an account's failure count, an engineer "
                + "kept a 'backup' with Account backup = original; - then "
                + "reset original, and found the backup reset too. Campaign "
                + "05 met this with arrays. Objects work the same way, and "
                + "it matters even more here.")
            .willLearn("Object references")
            .whyUseful(
                "Every object variable holds a reference, not the object. "
                + "Knowing that explains shared-state bugs, why == on "
                + "objects surprises people, and why a method you pass an "
                + "object to can change it.")
            .concept("Object references",
                "An object variable holds a REFERENCE - where the object "
                + "is - just like an array variable (Campaign 05):\n"
                + "\n"
                + "    Account a = new Account();\n"
                + "    Account b = a;\n"
                + "\n"
                + "    a --\\\n"
                + "         [ user: admin  failures: 4 ]\n"
                + "    b --/\n"
                + "\n"
                + "b = a copies the reference. There is still ONE account, "
                + "with two names. b.failures = 0 resets the account a "
                + "refers to, because it is the same account.\n"
                + "\n"
                + "A real copy is a NEW object, with each field copied "
                + "across:\n"
                + "\n"
                + "    Account backup = new Account();\n"
                + "    backup.user = a.user;\n"
                + "    backup.failures = a.failures;\n"
                + "\n"
                + "== on two object variables asks 'the same object?', not "
                + "'the same contents?' - mission 18 writes equals for "
                + "that.\n"
                + "\n"
                + "The classic slip: ONE new outside a loop, stored in every "
                + "slot. Every slot then refers to the same object, and "
                + "changing 'each one' changes them all.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Account a = new Account();",
                "        a.failures = 4;",
                "        Account b = a;",
                "        b.failures = 0;",
                "        System.out.println(\"a.failures: \" + a.failures);",
                "        System.out.println(\"Same object: \" + (a == b));",
                "        Account[] team = new Account[3];",
                "        Account shared = new Account();",
                "        String[] names = {\"ana\", \"ben\", \"cal\"};",
                "        for (int i = 0; i < team.length; i++) {",
                "            team[i] = shared;",
                "            team[i].user = names[i];",
                "        }",
                "        System.out.println(team[0].user + \" \" + team[1].user);",
                "    }",
                "}",
                "",
                "class Account {",
                "    String user;",
                "    int failures;",
                "}")
            .exampleOutput(
                "a.failures: 0",
                "Same object: true",
                "cal cal")
            .lineByLine(
                new String[]{"Account b = a;",
                    "Copies the reference: one account, two names."},
                new String[]{"b.failures = 0;",
                    "Resets THE account - so a.failures reads 0."},
                new String[]{"team[i] = shared;",
                    "Every slot refers to one object, so the last name "
                    + "written wins: cal, everywhere."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Host {",
                    "    int port;",
                    "}",
                    "",
                    "Host x = new Host();",
                    "Host y = x;",
                    "Host z = new Host();",
                    "y.port = 443;",
                    "z.port = 22;",
                    "System.out.println(x.port + \" \" + (x == y) + \" \" + (x == z));")
                .accept("443 true false")
                .hints("x and y refer to one object; z is another.",
                       "== asks 'same object?'.")
                .explain(
                    "443 true false. Setting y.port set the one object x "
                    + "also refers to; z is separate, so x == z is false.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Two separate Account objects both have user \"admin\" and "
                    + "failures 0. What is a == b?")
                .choices("true, the contents match",
                         "false, they are two objects",
                         "It does not compile",
                         "It depends on the user")
                .accept("2", "b")
                .hints("What does == compare for objects?",
                       "Each new makes a different object.")
                .explain(
                    "b. == compares references. Equal contents do not make "
                    + "two objects the same object.")
                .xp(10))
            .objective(
                "Keep a real backup before resetting the count.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Account original = new Account();",
                "        original.user = \"admin\";",
                "        original.failures = 4;",
                "        // backup: a separate, new Account",
                "        backup.user = original.user;",
                "        backup.failures = original.failures;",
                "        original.failures = 0;",
                "        System.out.println(original.failures + \" \" + backup.failures);",
                "    }",
                "}",
                "",
                "class Account {",
                "    String user;",
                "    int failures;",
                "}")
            .yourTask(
                "Declare backup as a new, separate Account, so the next two "
                + "lines copy the values into an object of its own.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line that declares backup.")
                .accept("Account backup = new Account();",
                        "Account backup=new Account();")
                .hints(
                    "Not = original - that would share the object.",
                    "A new object of its own.",
                    "Account backup = new Account();")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Account original = new Account();",
                    "        original.user = \"admin\";",
                    "        original.failures = 4;",
                    "        Account backup = new Account();",
                    "        backup.user = original.user;",
                    "        backup.failures = original.failures;",
                    "        original.failures = 0;",
                    "        System.out.println(original.failures + \" \" + backup.failures);",
                    "    }",
                    "}",
                    "",
                    "class Account {",
                    "    String user;",
                    "    int failures;",
                    "}")
                .whyItWorks(
                    "backup is its own object, so copying the two fields "
                    + "into it makes a snapshot. Resetting original "
                    + "afterwards leaves the snapshot alone: 0 4.\n"
                    + "\n"
                    + "With Account backup = original; the output would be "
                    + "0 0 - the 'backup' would be the account itself, and "
                    + "the evidence of four failures would be gone.")
                .explain(
                    "Account backup = new Account(); - a separate object.")
                .xp(20))
            .mistakes(
                new String[]{"b = a to copy",
                    "Copies the reference. One object, two names."},
                new String[]{"a == b to compare contents",
                    "== asks 'same object?'. Mission 18 covers equals."},
                new String[]{"One new outside the loop",
                    "Every slot shares it. new inside the loop."})
            .cyber(
                "Shared objects cause some of the nastiest security bugs, "
                + "because nothing crashes. A session object accidentally "
                + "shared between two users means one user sees the "
                + "other's data; a 'copy' of permissions that is really the "
                + "original means granting one person access grants it to "
                + "another. When two things must be independent, make sure "
                + "there are two objects.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Flag {",
                    "    boolean on;",
                    "}",
                    "",
                    "Flag[] f = new Flag[2];",
                    "Flag one = new Flag();",
                    "f[0] = one;",
                    "f[1] = one;",
                    "f[0].on = true;",
                    "System.out.println(f[1].on);")
                .accept("true")
                .hints("How many Flag objects were made?",
                       "Both slots hold the same reference.")
                .explain(
                    "true. Only one Flag exists; both slots refer to it, so "
                    + "turning it on through f[0] shows through f[1].")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which makes b an independent copy of Account a?")
                .choices("Account b = a;",
                         "Account b = new Account(); then copy each field",
                         "Account b == a;",
                         "Account b = a.copy;")
                .accept("2", "b")
                .hints("A copy needs its own object.",
                       "Then the values are copied across.")
                .explain(
                    "b. A new object, with the fields copied, is "
                    + "independent. a shares the object; c and d do not "
                    + "compile.")
                .xp(10))
            .recap(
                "    Account b = a;       one object, two names\n"
                + "    a == b               same object?\n"
                + "    new + copy fields    an independent copy\n"
                + "\n"
                + "One new per object - never one new shared by a loop.")
            .next("Next: a variable with no object at all - null."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(7), "Nothing There: null", 4)
            .brief(
                "The asset lookup returns the Host for a name - or, when no "
                + "such host exists, nothing at all. The first version "
                + "used the result straight away, and the first typo in a "
                + "hostname took the whole inventory service down with "
                + "NullPointerException. The lookup was right to say "
                + "'nothing'. The caller was wrong not to check.")
            .willLearn("null and NullPointerException")
            .whyUseful(
                "null is Java's way of saying 'no object'. It is a normal, "
                + "useful answer - 'not found', 'not set yet' - and the "
                + "single most common cause of crashes when nobody checks "
                + "for it.")
            .concept("null and NullPointerException",
                "An object variable can hold null: a reference to NOTHING. "
                + "You have met it as the default of String fields and of "
                + "new object arrays.\n"
                + "\n"
                + "Holding null is fine. Printing it is fine (it shows "
                + "null). Using the DOT on it is not - there is no object "
                + "to reach into:\n"
                + "\n"
                + "    Host h = null;\n"
                + "    h.name            crash: NullPointerException\n"
                + "    h.name.length()   crash, if name is null\n"
                + "\n"
                + "The trace names java.lang.NullPointerException, and recent "
                + "Java versions add which value was null. The 'at' line, "
                + "as always, is where to look.\n"
                + "\n"
                + "null is often a DELIBERATE answer - a search that returns "
                + "the object it found, or null when there is none:\n"
                + "\n"
                + "    static Host find(Host[] hosts, String name) {\n"
                + "        for (Host h : hosts) {\n"
                + "            if (h.name.equals(name)) {\n"
                + "                return h;\n"
                + "            }\n"
                + "        }\n"
                + "        return null;\n"
                + "    }\n"
                + "\n"
                + "The caller then checks before using it:\n"
                + "\n"
                + "    if (h != null) { ... h.port ... }\n"
                + "\n"
                + "Test null with == and != - it is the one comparison where "
                + "== is exactly right for objects.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Host[] hosts = new Host[2];",
                "        hosts[0] = new Host();",
                "        hosts[0].name = \"db1\";",
                "        hosts[0].port = 5432;",
                "        hosts[1] = new Host();",
                "        hosts[1].name = \"web1\";",
                "        hosts[1].port = 443;",
                "        String[] wanted = {\"web1\", \"db9\"};",
                "        for (String name : wanted) {",
                "            Host h = find(hosts, name);",
                "            if (h != null) {",
                "                System.out.println(name + \": port \" + h.port);",
                "            } else {",
                "                System.out.println(name + \": no such host\");",
                "            }",
                "        }",
                "    }",
                "",
                "    static Host find(Host[] hosts, String name) {",
                "        for (Host h : hosts) {",
                "            if (h.name.equals(name)) {",
                "                return h;",
                "            }",
                "        }",
                "        return null;",
                "    }",
                "}",
                "",
                "class Host {",
                "    String name;",
                "    int port;",
                "}")
            .exampleOutput(
                "web1: port 443",
                "db9: no such host")
            .lineByLine(
                new String[]{"static Host find(...)",
                    "A method can return an object - or null for 'none'."},
                new String[]{"return null;",
                    "After the loop: every host checked, none matched."},
                new String[]{"if (h != null)",
                    "The guard. Only a real object gets the dot."})
            .predict(new Task(Task.CHOICE,
                    "Which of these lines crashes when h is null?")
                .code(
                    "System.out.println(h);",
                    "if (h == null) { ... }",
                    "System.out.println(h.name);",
                    "Host copy = h;")
                .choices("Line 1", "Line 2", "Line 3", "Line 4")
                .accept("3", "c")
                .hints("Which one uses the dot?",
                       "Printing, comparing and copying null are all fine.")
                .explain(
                    "c. Only h.name reaches INTO the object, and there is "
                    + "none. Printing null shows null; comparing and copying "
                    + "a null reference are ordinary operations.")
                .xp(15))
            .practice(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Acct {",
                    "    String user;",
                    "}",
                    "",
                    "Acct[] list = new Acct[3];",
                    "list[1] = new Acct();",
                    "list[1].user = \"root\";",
                    "int real = 0;",
                    "for (Acct a : list) {",
                    "    if (a != null) {",
                    "        real++;",
                    "    }",
                    "}",
                    "System.out.println(real);")
                .accept("1")
                .hints("Which slots hold an object?",
                       "Slots 0 and 2 are still null.")
                .explain(
                    "1. Only slot 1 holds an object; the guard skips the "
                    + "null slots instead of crashing on them.")
                .xp(15))
            .objective(
                "Guard the lookup before using its result.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Host[] hosts = new Host[1];",
                "        hosts[0] = new Host();",
                "        hosts[0].name = \"fw1\";",
                "        hosts[0].owner = \"netops\";",
                "        Host h = find(hosts, \"fw2\");",
                "        // the if: h refers to a real object",
                "            System.out.println(\"Owner: \" + h.owner);",
                "        } else {",
                "            System.out.println(\"Unknown host\");",
                "        }",
                "    }",
                "",
                "    static Host find(Host[] hosts, String name) {",
                "        for (Host h : hosts) {",
                "            if (h.name.equals(name)) {",
                "                return h;",
                "            }",
                "        }",
                "        return null;",
                "    }",
                "}",
                "",
                "class Host {",
                "    String name;",
                "    String owner;",
                "}")
            .yourTask(
                "Write the if that lets the program use h only when find "
                + "returned a real Host.")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line.")
                .accept("if (h != null) {",
                        "if(h != null) {",
                        "if (h != null){",
                        "if (null != h) {")
                .hints(
                    "find returns null for 'not found'.",
                    "Compare with != null.",
                    "if (h != null) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Host[] hosts = new Host[1];",
                    "        hosts[0] = new Host();",
                    "        hosts[0].name = \"fw1\";",
                    "        hosts[0].owner = \"netops\";",
                    "        Host h = find(hosts, \"fw2\");",
                    "        if (h != null) {",
                    "            System.out.println(\"Owner: \" + h.owner);",
                    "        } else {",
                    "            System.out.println(\"Unknown host\");",
                    "        }",
                    "    }",
                    "",
                    "    static Host find(Host[] hosts, String name) {",
                    "        for (Host h : hosts) {",
                    "            if (h.name.equals(name)) {",
                    "                return h;",
                    "            }",
                    "        }",
                    "        return null;",
                    "    }",
                    "}",
                    "",
                    "class Host {",
                    "    String name;",
                    "    String owner;",
                    "}")
                .whyItWorks(
                    "fw2 is not in the array, so find returns null. The "
                    + "guard sends that case to the else: Unknown host. "
                    + "Search for fw1 instead and h is the real Host, so "
                    + "Owner: netops is printed.\n"
                    + "\n"
                    + "Without the guard, h.owner on null throws "
                    + "NullPointerException - one mistyped hostname would "
                    + "crash the program.")
                .explain(
                    "if (h != null) { - only a real object gets the dot.")
                .xp(20))
            .mistakes(
                new String[]{"Using a search result unchecked",
                    "'Not found' is null. Check before the dot."},
                new String[]{"h.equals(null)",
                    "Crashes if h is null. Use h != null."},
                new String[]{"Returning null with no comment",
                    "Say in the method's doc that it may return null."})
            .cyber(
                "A NullPointerException in a server is a denial of service "
                + "waiting for its trigger - often a request for something "
                + "that does not exist, which anyone can send. Worse, some "
                + "code catches the crash and carries on in a half-finished "
                + "state. Check for null where it can appear, and treat "
                + "'not found' as a normal answer, handled on purpose.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Box {",
                    "    String label;",
                    "}",
                    "",
                    "Box b = new Box();",
                    "System.out.println(b.label == null);",
                    "b.label = \"x\";",
                    "System.out.println(b.label == null);")
                .accept("true false")
                .hints("A String field starts as null.",
                       "Then it is set.")
                .explain(
                    "true false. label starts null (a field default), then "
                    + "refers to \"x\". Comparing with null is always safe.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "A method returns the matching Account, or null. What "
                    + "must its caller do?")
                .choices("Nothing - Java handles null",
                         "Check the result is not null before using the dot",
                         "Call result.equals(null)",
                         "Catch every error")
                .accept("2", "b")
                .hints("What happens to result.anything when result is "
                       + "null?",
                       "Guard first.")
                .explain(
                    "b. The caller must check != null first. c crashes "
                    + "itself when result is null.")
                .xp(10))
            .recap(
                "    null                  no object\n"
                + "    printing, ==, copying   all fine\n"
                + "    null.anything         NullPointerException\n"
                + "    if (x != null)        the guard\n"
                + "\n"
                + "Returning null for 'not found' is fine - if every caller "
                + "checks.")
            .next("Next: giving objects behaviour - methods."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(8), "Objects That Do Things", 4)
            .brief(
                "The lockout rule - count a failure, lock at three - has "
                + "been copied into four places that handle logins, and one "
                + "copy locks at four. The rule belongs to the account "
                + "itself. Put it IN the class as a method, and every "
                + "caller just asks the account to record a failure.")
            .willLearn("Instance methods")
            .whyUseful(
                "Methods inside a class put the rules next to the data they "
                + "protect. Callers say what they want - "
                + "acct.recordFailure() - and the class decides how, in "
                + "exactly one place.")
            .concept("Instance methods",
                "A method declared inside a class WITHOUT static is an "
                + "INSTANCE METHOD. It is called on an object, and works on "
                + "that object's fields directly - no dot needed inside:\n"
                + "\n"
                + "    class Account {\n"
                + "        int fails;\n"
                + "        boolean locked;\n"
                + "\n"
                + "        void recordFailure() {\n"
                + "            fails++;\n"
                + "            if (fails >= 3) {\n"
                + "                locked = true;\n"
                + "            }\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    a.recordFailure();    a's fails, a's locked\n"
                + "    b.recordFailure();    b's fails, b's locked\n"
                + "\n"
                + "Inside the method, fails means 'the fails of the object "
                + "this was called on'. The same code serves every account.\n"
                + "\n"
                + "Instance methods take parameters and return values like "
                + "any method from Campaign 03:\n"
                + "\n"
                + "    boolean canLogIn() { return !locked; }\n"
                + "\n"
                + "static methods (Main's helpers) belong to no object; "
                + "instance methods always run ON one. Mission 19 compares "
                + "the two properly.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Account a = new Account();",
                "        a.user = \"admin\";",
                "        Account b = new Account();",
                "        b.user = \"jsmith\";",
                "        a.recordFailure();",
                "        a.recordFailure();",
                "        a.recordFailure();",
                "        b.recordFailure();",
                "        System.out.println(a.status());",
                "        System.out.println(b.status());",
                "        System.out.println(\"jsmith may log in: \" + b.canLogIn());",
                "    }",
                "}",
                "",
                "class Account {",
                "    String user;",
                "    int fails;",
                "    boolean locked;",
                "",
                "    void recordFailure() {",
                "        fails++;",
                "        if (fails >= 3) {",
                "            locked = true;",
                "        }",
                "    }",
                "",
                "    boolean canLogIn() {",
                "        return !locked;",
                "    }",
                "",
                "    String status() {",
                "        return user + \": \" + fails + (locked ? \" LOCKED\" : \"\");",
                "    }",
                "}")
            .exampleOutput(
                "admin: 3 LOCKED",
                "jsmith: 1",
                "jsmith may log in: true")
            .lineByLine(
                new String[]{"void recordFailure()",
                    "No static: it runs on an object and uses its fields."},
                new String[]{"fails++;",
                    "The fails of whichever account it was called on."},
                new String[]{"a.recordFailure(); b.recordFailure();",
                    "One method, two accounts, two separate counts."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Meter {",
                    "    int total;",
                    "",
                    "    void add(int n) {",
                    "        total += n;",
                    "    }",
                    "}",
                    "",
                    "Meter m = new Meter();",
                    "Meter k = new Meter();",
                    "m.add(5);",
                    "k.add(2);",
                    "m.add(3);",
                    "System.out.println(m.total + \" \" + k.total);")
                .accept("8 2")
                .hints("Each call works on its own object's total.",
                       "m got 5 and 3; k got 2.")
                .explain(
                    "8 2. add changes the total of the object it is called "
                    + "on, so m and k keep separate totals.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "class Lamp {",
                    "    boolean on;",
                    "",
                    "    void toggle() {",
                    "        on = !on;",
                    "    }",
                    "}",
                    "",
                    "Lamp l = new Lamp();",
                    "l.toggle();",
                    "toggle();",
                    "System.out.println(l.on);")
                .accept("11", "line 11")
                .hints("An instance method needs an object to run on.",
                       "Which call has none?")
                .explain(
                    "Line 11: 'cannot find symbol'. toggle belongs to Lamp "
                    + "objects, so it must be called on one: l.toggle().")
                .xp(20))
            .objective(
                "Give Sensor a method that says whether it is too hot.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Sensor s = new Sensor();",
                "        s.celsius = 34;",
                "        System.out.println(\"Too hot: \" + s.isHot());",
                "        s.celsius = 21;",
                "        System.out.println(\"Too hot: \" + s.isHot());",
                "    }",
                "}",
                "",
                "class Sensor {",
                "    int celsius;",
                "",
                "    // the header of isHot: returns a boolean, no parameters",
                "        return celsius > 30;",
                "    }",
                "}")
            .yourTask(
                "Write the header of an instance method isHot that takes no "
                + "parameters and returns a boolean.")
            .mainTask(new Task(Task.WRITE,
                    "Write the method header.")
                .accept("boolean isHot() {",
                        "boolean isHot(){",
                        "public boolean isHot() {")
                .hints(
                    "No static - it runs on a Sensor.",
                    "Return type, name, empty brackets.",
                    "boolean isHot() {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Sensor s = new Sensor();",
                    "        s.celsius = 34;",
                    "        System.out.println(\"Too hot: \" + s.isHot());",
                    "        s.celsius = 21;",
                    "        System.out.println(\"Too hot: \" + s.isHot());",
                    "    }",
                    "}",
                    "",
                    "class Sensor {",
                    "    int celsius;",
                    "",
                    "    boolean isHot() {",
                    "        return celsius > 30;",
                    "    }",
                    "}")
                .whyItWorks(
                    "isHot reads the celsius of the Sensor it is called on. "
                    + "The first call sees 34 and returns true; after the "
                    + "reading changes to 21, the same call returns false.\n"
                    + "\n"
                    + "The threshold now lives in one place, inside the "
                    + "class. Every part of the program that asks s.isHot() "
                    + "gets the same rule.")
                .explain(
                    "boolean isHot() { - no static: it runs on a Sensor.")
                .xp(20))
            .mistakes(
                new String[]{"static on an instance method",
                    "Then it has no object - its fields are unreachable."},
                new String[]{"Calling it without an object",
                    "obj.method(), not method()."},
                new String[]{"a.fails inside the class",
                    "Just fails: it means this object's own field."})
            .cyber(
                "A security rule written once, inside the class that owns "
                + "the data, cannot drift. Four hand-copied lockout checks "
                + "will eventually disagree, and an attacker only needs the "
                + "weakest one. acct.recordFailure() gives every login path "
                + "the same rule - and a reviewer one place to check it.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Door {",
                    "    int opens;",
                    "",
                    "    boolean open() {",
                    "        opens++;",
                    "        return opens <= 2;",
                    "    }",
                    "}",
                    "",
                    "Door d = new Door();",
                    "d.open();",
                    "d.open();",
                    "System.out.println(d.open() + \" \" + d.opens);")
                .accept("false 3")
                .hints("The third call makes opens 3.",
                       "3 <= 2 is false.")
                .explain(
                    "false 3. The method changes the object AND returns a "
                    + "value; by the third call opens is 3, so it refuses.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Inside an instance method of Account, what does the "
                    + "plain name fails refer to?")
                .choices("Every account's fails",
                         "The fails of the account the method was called on",
                         "A new local variable",
                         "Nothing - it needs a dot")
                .accept("2", "b")
                .hints("Which object did the call use?",
                       "a.recordFailure() works on a.")
                .explain(
                    "b. An instance method always runs on one object, and "
                    + "its field names mean that object's fields.")
                .xp(10))
            .recap(
                "    class Account {\n"
                + "        void recordFailure() { fails++; ... }\n"
                + "    }\n"
                + "    a.recordFailure();   runs on a\n"
                + "\n"
                + "No static. Fields are used by name inside. The rule "
                + "lives with the data.")
            .next("Next: this - the object a method is running on."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(9), "this", 3)
            .brief(
                "A setter was written as void setPort(int port) { port = "
                + "port; } and every host kept port 0. It compiled. It ran. "
                + "It assigned the parameter to itself and never touched "
                + "the field. The fix is one word, and the word is this.")
            .willLearn("this")
            .whyUseful(
                "Parameters are usually named after the fields they set - "
                + "it is the clearest name. this is how a method tells the "
                + "field apart from the parameter, and how an object refers "
                + "to itself.")
            .concept("this",
                "Inside an instance method, THIS means 'the object this "
                + "method was called on'. this.port is that object's port "
                + "field.\n"
                + "\n"
                + "Usually this is optional: port alone means the field. "
                + "But when a parameter or local has the SAME name, the "
                + "nearer declaration wins - the name SHADOWS the field:\n"
                + "\n"
                + "    void setPort(int port) {\n"
                + "        port = port;         parameter = parameter\n"
                + "    }\n"
                + "\n"
                + "That line changes nothing. this.port reaches past the "
                + "parameter to the field:\n"
                + "\n"
                + "    void setPort(int port) {\n"
                + "        this.port = port;    field = parameter\n"
                + "    }\n"
                + "\n"
                + "this can also be passed along or returned - 'me' - "
                + "which later missions use. For now: write this.field = "
                + "field whenever the names match, and never field = field.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Host h = new Host();",
                "        h.setPortBroken(443);",
                "        System.out.println(\"Broken setter: \" + h.port);",
                "        h.setPort(443);",
                "        System.out.println(\"Fixed setter: \" + h.port);",
                "    }",
                "}",
                "",
                "class Host {",
                "    int port;",
                "",
                "    void setPortBroken(int port) {",
                "        port = port;",
                "    }",
                "",
                "    void setPort(int port) {",
                "        this.port = port;",
                "    }",
                "}")
            .exampleOutput(
                "Broken setter: 0",
                "Fixed setter: 443")
            .lineByLine(
                new String[]{"port = port;",
                    "Both names mean the parameter. The field never changes."},
                new String[]{"this.port = port;",
                    "this.port is the field; port is the parameter."},
                new String[]{"Broken setter: 0",
                    "No error, no warning - just a field left at its "
                    + "default."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class User {",
                    "    String name = \"guest\";",
                    "",
                    "    void rename(String name) {",
                    "        name = name.toUpperCase();",
                    "    }",
                    "}",
                    "",
                    "User u = new User();",
                    "u.rename(\"admin\");",
                    "System.out.println(u.name);")
                .accept("guest")
                .hints("Which name does the method change?",
                       "The parameter shadows the field.")
                .explain(
                    "guest. The method upper-cases its own parameter and "
                    + "throws it away; the field keeps \"guest\" (a field "
                    + "can be given a starting value with =).")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "In void setUser(String user), which line stores the "
                    + "parameter in the field?")
                .choices("user = user;",
                         "this.user = user;",
                         "user = this.user;",
                         "this.user = this.user;")
                .accept("2", "b")
                .hints("The field is this.user.",
                       "Field on the left, parameter on the right.")
                .explain(
                    "b. this.user is the field, user the parameter. c "
                    + "copies the field into the parameter - backwards.")
                .xp(10))
            .objective(
                "Fix the port setter.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Rule r = new Rule();",
                "        r.setPort(8443);",
                "        System.out.println(\"Rule port: \" + r.port);",
                "    }",
                "}",
                "",
                "class Rule {",
                "    int port;",
                "",
                "    void setPort(int port) {",
                "        // store the parameter in the field",
                "    }",
                "}")
            .yourTask(
                "Write the line that stores the parameter port in this "
                + "rule's port field.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line.")
                .accept("this.port = port;")
                .hints(
                    "The names are the same.",
                    "this.port is the field.",
                    "this.port = port;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Rule r = new Rule();",
                    "        r.setPort(8443);",
                    "        System.out.println(\"Rule port: \" + r.port);",
                    "    }",
                    "}",
                    "",
                    "class Rule {",
                    "    int port;",
                    "",
                    "    void setPort(int port) {",
                    "        this.port = port;",
                    "    }",
                    "}")
                .whyItWorks(
                    "this.port reaches past the parameter to the field of "
                    + "the Rule the method was called on, so r's port "
                    + "becomes 8443.\n"
                    + "\n"
                    + "port = port; would compile and print Rule port: 0 - a "
                    + "firewall rule silently left on port 0, with nothing to "
                    + "say anything went wrong.")
                .explain(
                    "this.port = port; - field on the left, parameter right.")
                .xp(15))
            .mistakes(
                new String[]{"field = field;",
                    "Assigns the parameter to itself. Use this.field."},
                new String[]{"this in a static method",
                    "A static method has no object, so no this."},
                new String[]{"Renaming the parameter to dodge it",
                    "Works, but this.x = x is the clear convention."})
            .cyber(
                "The shadowing bug is dangerous precisely because it is "
                + "quiet. A setter meant to raise a security level, record "
                + "an expiry or store a new password hash does nothing, and "
                + "the object keeps its old - or default - value. Tests "
                + "that set a value and read it back catch this at once; "
                + "code review catches field = field on sight.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Cfg {",
                    "    int level = 1;",
                    "",
                    "    void raise(int level) {",
                    "        this.level = level + this.level;",
                    "    }",
                    "}",
                    "",
                    "Cfg c = new Cfg();",
                    "c.raise(4);",
                    "System.out.println(c.level);")
                .accept("5")
                .hints("this.level is the field, 1.",
                       "level is the parameter, 4.")
                .explain(
                    "5. The field becomes the parameter (4) plus the old "
                    + "field (1).")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "What does this mean inside an instance method?")
                .choices("The class itself",
                         "The object the method was called on",
                         "The first parameter",
                         "The main method")
                .accept("2", "b")
                .hints("a.setPort(1): which object?",
                       "this is a.")
                .explain(
                    "b. For a.setPort(1), this is a. For b.setPort(1), this "
                    + "is b.")
                .xp(10))
            .recap(
                "    this          the object the method runs on\n"
                + "    this.port     its field, even when a parameter is\n"
                + "                  also called port\n"
                + "\n"
                + "Never write field = field.")
            .next("Next: constructors - objects that start complete."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(10), "Constructors", 4)
            .brief(
                "Every Alert must have a rule and a severity - but new "
                + "Alert() hands back one with neither, and it is up to "
                + "each caller to remember both lines that fill it in. One "
                + "forgot, and an alert with no rule reached the analysts' "
                + "queue. A constructor makes the object complete from its "
                + "very first moment.")
            .willLearn("Constructors")
            .whyUseful(
                "Constructors set an object up as it is made, from values "
                + "the caller must supply. An Alert that cannot exist "
                + "without a rule is an Alert that is never missing one.")
            .concept("Constructors",
                "A CONSTRUCTOR is a special method that runs as part of "
                + "new. It has the SAME name as the class and NO return "
                + "type - not even void:\n"
                + "\n"
                + "    class Alert {\n"
                + "        String rule;\n"
                + "        int severity;\n"
                + "\n"
                + "        Alert(String rule, int severity) {\n"
                + "            this.rule = rule;\n"
                + "            this.severity = severity;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    Alert a = new Alert(\"Brute force\", 8);\n"
                + "\n"
                + "new now does four things: makes the object, sets the "
                + "field defaults, RUNS THE CONSTRUCTOR with the arguments, "
                + "and hands back the reference.\n"
                + "\n"
                + "Once a class has this constructor, new Alert() with no "
                + "arguments no longer compiles - the caller MUST supply a "
                + "rule and a severity. That is the point.\n"
                + "\n"
                + "this.rule = rule is mission 9's pattern: the parameters "
                + "are named after the fields they fill.\n"
                + "\n"
                + "The classic mistake is writing void Alert(...). With a "
                + "return type it is just an ordinary method that happens "
                + "to share the class's name - and new Alert(\"x\", 1) then "
                + "fails to compile, because there is no such constructor.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Alert a = new Alert(\"Brute force\", 8);",
                "        Alert b = new Alert(\"New admin account\", 9);",
                "        System.out.println(a.rule + \" (\" + a.severity + \")\");",
                "        System.out.println(b.rule + \" (\" + b.severity + \")\");",
                "    }",
                "}",
                "",
                "class Alert {",
                "    String rule;",
                "    int severity;",
                "",
                "    Alert(String rule, int severity) {",
                "        this.rule = rule;",
                "        this.severity = severity;",
                "    }",
                "}")
            .exampleOutput(
                "Brute force (8)",
                "New admin account (9)")
            .lineByLine(
                new String[]{"Alert(String rule, int severity)",
                    "The class's name, no return type: a constructor."},
                new String[]{"new Alert(\"Brute force\", 8)",
                    "The arguments go to the constructor, in order."},
                new String[]{"this.rule = rule;",
                    "Fills the new object's field from the parameter."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Host {",
                    "    String name;",
                    "    int port;",
                    "",
                    "    Host(String name, int port) {",
                    "        this.name = name.toLowerCase();",
                    "        this.port = port;",
                    "    }",
                    "}",
                    "",
                    "Host h = new Host(\"WEB1\", 443);",
                    "System.out.println(h.name + \":\" + h.port);")
                .accept("web1:443")
                .hints("The constructor can do more than copy.",
                       "It lower-cases the name.")
                .explain(
                    "web1:443. The constructor runs during new and can tidy "
                    + "what it stores - every Host is lower-cased from birth.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "class Token {",
                    "    String value;",
                    "",
                    "    void Token(String value) {",
                    "        this.value = value;",
                    "    }",
                    "}",
                    "",
                    "Token t = new Token(\"abc123\");",
                    "System.out.println(t.value);")
                .accept("9", "line 9")
                .hints("Is line 4 really a constructor?",
                       "It has a return type.")
                .explain(
                    "Line 9: 'constructor Token in class Token cannot be "
                    + "applied'. void makes line 4 an ordinary method, so the "
                    + "class only has the automatic no-argument constructor "
                    + "(mission 11), and new Token(\"abc123\") fits nothing.")
                .xp(20))
            .objective(
                "Give Host a constructor that fills both fields.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Host h = new Host(\"db1\", 5432);",
                "        System.out.println(h.name + \" on port \" + h.port);",
                "    }",
                "}",
                "",
                "class Host {",
                "    String name;",
                "    int port;",
                "",
                "    // the constructor header: takes a String name and an int port",
                "        this.name = name;",
                "        this.port = port;",
                "    }",
                "}")
            .yourTask(
                "Write the constructor's header: it takes a String called "
                + "name and an int called port, in that order.")
            .mainTask(new Task(Task.WRITE,
                    "Write the constructor header.")
                .accept("Host(String name, int port) {",
                        "Host(String name, int port){",
                        "public Host(String name, int port) {")
                .hints(
                    "The class's name, then the parameters.",
                    "No return type - not even void.",
                    "Host(String name, int port) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Host h = new Host(\"db1\", 5432);",
                    "        System.out.println(h.name + \" on port \" + h.port);",
                    "    }",
                    "}",
                    "",
                    "class Host {",
                    "    String name;",
                    "    int port;",
                    "",
                    "    Host(String name, int port) {",
                    "        this.name = name;",
                    "        this.port = port;",
                    "    }",
                    "}")
                .whyItWorks(
                    "new Host(\"db1\", 5432) passes the two values to the "
                    + "constructor, which stores them in the new object's "
                    + "fields before new hands it back: db1 on port 5432.\n"
                    + "\n"
                    + "Every Host is now complete the moment it exists - "
                    + "there is no longer any way to make one without a "
                    + "name and a port.")
                .explain(
                    "Host(String name, int port) { - no return type.")
                .xp(20))
            .mistakes(
                new String[]{"void Host(...)",
                    "A return type makes it a method, not a constructor."},
                new String[]{"name = name; in the constructor",
                    "Shadowing again. this.name = name;"},
                new String[]{"new Host() after adding a constructor",
                    "Now the arguments are required (mission 11)."})
            .cyber(
                "An object that can exist half-built will, sooner or later, "
                + "be used half-built: an alert with no severity sorted to "
                + "the bottom of the queue, a session with no user treated "
                + "as anonymous, a rule with no port matching nothing - or "
                + "everything. Constructors that demand every essential "
                + "value close that gap at the source.")
            .check(new Task(Task.CHOICE,
                    "Which is a constructor for class Ticket?")
                .choices("void Ticket(String title) { ... }",
                         "Ticket(String title) { ... }",
                         "static Ticket(String title) { ... }",
                         "String Ticket(String title) { ... }")
                .accept("2", "b")
                .hints("Same name as the class.",
                       "No return type at all.")
                .explain(
                    "b. A constructor has the class's name and no return "
                    + "type. a and d are methods; c is not allowed.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Counter {",
                    "    int n;",
                    "",
                    "    Counter(int start) {",
                    "        n = start * 2;",
                    "    }",
                    "}",
                    "",
                    "Counter c = new Counter(5);",
                    "c.n++;",
                    "System.out.println(c.n);")
                .accept("11")
                .hints("The constructor runs first.",
                       "5 * 2, then ++.")
                .explain(
                    "11. The constructor sets n to 10 during new; then "
                    + "c.n++ makes it 11. (No this needed: there is no "
                    + "parameter called n.)")
                .xp(10))
            .recap(
                "    Alert(String rule, int severity) {\n"
                + "        this.rule = rule;\n"
                + "        this.severity = severity;\n"
                + "    }\n"
                + "    new Alert(\"Brute force\", 8)\n"
                + "\n"
                + "Class's name, no return type. Runs during new.")
            .next("Next: the constructor you get for free - until you "
                + "don't."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(11), "The Free Constructor", 3)
            .brief(
                "Missions 1 to 9 wrote new Host() without any constructor "
                + "in sight - and it worked. Then mission 10 added one, and "
                + "an old line elsewhere, new Ticket(), stopped compiling. "
                + "Java had been supplying a constructor quietly, and "
                + "stopped the moment you wrote your own.")
            .willLearn("The default constructor")
            .whyUseful(
                "Knowing when Java provides a constructor - and when it "
                + "withdraws it - explains a confusing compile error, and "
                + "lets you decide on purpose whether an object may be made "
                + "with no arguments at all.")
            .concept("The default constructor",
                "A class with NO constructors gets one from Java, the "
                + "DEFAULT constructor: no parameters, and it does nothing "
                + "beyond the field defaults. That is why new Host() worked "
                + "in missions 1 to 9.\n"
                + "\n"
                + "The moment you write ANY constructor, Java stops "
                + "supplying it:\n"
                + "\n"
                + "    class Ticket {\n"
                + "        String title;\n"
                + "        Ticket(String title) { this.title = title; }\n"
                + "    }\n"
                + "\n"
                + "    new Ticket(\"Disk full\")    fine\n"
                + "    new Ticket()               does not compile\n"
                + "\n"
                + "Usually that is what you want: a Ticket must have a "
                + "title. If a no-argument version really makes sense, "
                + "write it yourself - and give the fields sensible "
                + "starting values instead of null:\n"
                + "\n"
                + "    Ticket() {\n"
                + "        this.title = \"(untitled)\";\n"
                + "    }\n"
                + "\n"
                + "A class can have both; mission 12 is about several "
                + "constructors side by side.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Note n = new Note();",
                "        System.out.println(\"Note text: \" + n.text);",
                "        Ticket t = new Ticket();",
                "        Ticket u = new Ticket(\"Disk full on db2\");",
                "        System.out.println(t.title);",
                "        System.out.println(u.title);",
                "    }",
                "}",
                "",
                "class Note {",
                "    String text;",
                "}",
                "",
                "class Ticket {",
                "    String title;",
                "",
                "    Ticket() {",
                "        this.title = \"(untitled)\";",
                "    }",
                "",
                "    Ticket(String title) {",
                "        this.title = title;",
                "    }",
                "}")
            .exampleOutput(
                "Note text: null",
                "(untitled)",
                "Disk full on db2")
            .lineByLine(
                new String[]{"class Note { String text; }",
                    "No constructor written, so Java supplies one: new "
                    + "Note() works, and text is null."},
                new String[]{"Ticket() { ... }",
                    "Written by hand, because Ticket has another "
                    + "constructor - and it avoids a null title."},
                new String[]{"new Ticket(\"Disk full on db2\")",
                    "The one-argument constructor."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Cfg {",
                    "    int retries;",
                    "    boolean verbose;",
                    "}",
                    "",
                    "Cfg c = new Cfg();",
                    "System.out.println(c.retries + \" \" + c.verbose);")
                .accept("0 false")
                .hints("Cfg has no constructor. What does Java supply?",
                       "One that leaves the defaults.")
                .explain(
                    "0 false. The default constructor sets nothing, so the "
                    + "fields keep their defaults.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "class Host {",
                    "    String name;",
                    "",
                    "    Host(String name) {",
                    "        this.name = name;",
                    "    }",
                    "}",
                    "",
                    "Host a = new Host(\"web1\");",
                    "Host b = new Host();",
                    "System.out.println(a.name);")
                .accept("10", "line 10")
                .hints("Which constructors does Host have?",
                       "Only one, and it needs a name.")
                .explain(
                    "Line 10. Writing Host(String name) took away the free "
                    + "no-argument constructor, so new Host() fits nothing.")
                .xp(20))
            .objective(
                "Let a Ticket be made with no title - safely.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Ticket blank = new Ticket();",
                "        System.out.println(\"[\" + blank.title + \"]\");",
                "    }",
                "}",
                "",
                "class Ticket {",
                "    String title;",
                "",
                "    // a constructor with no parameters",
                "        this.title = \"(untitled)\";",
                "    }",
                "",
                "    Ticket(String title) {",
                "        this.title = title;",
                "    }",
                "}")
            .yourTask(
                "Write the header of a constructor that takes no "
                + "parameters, so new Ticket() compiles again.")
            .mainTask(new Task(Task.WRITE,
                    "Write the constructor header.")
                .accept("Ticket() {",
                        "Ticket(){",
                        "public Ticket() {")
                .hints(
                    "Same name as the class, no return type.",
                    "Empty brackets: no parameters.",
                    "Ticket() {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Ticket blank = new Ticket();",
                    "        System.out.println(\"[\" + blank.title + \"]\");",
                    "    }",
                    "}",
                    "",
                    "class Ticket {",
                    "    String title;",
                    "",
                    "    Ticket() {",
                    "        this.title = \"(untitled)\";",
                    "    }",
                    "",
                    "    Ticket(String title) {",
                    "        this.title = title;",
                    "    }",
                    "}")
                .whyItWorks(
                    "With Ticket() written by hand, new Ticket() has a "
                    + "constructor to run, and it sets a readable title: "
                    + "[(untitled)].\n"
                    + "\n"
                    + "Java's free constructor would have left title as "
                    + "null - and any later title.length() would crash. "
                    + "Writing it yourself lets you choose a safe start.")
                .explain(
                    "Ticket() { - a no-argument constructor, by hand.")
                .xp(15))
            .mistakes(
                new String[]{"Expecting new X() after adding a constructor",
                    "Java withdraws the free one. Write it if needed."},
                new String[]{"Adding a no-arg constructor by reflex",
                    "Only if an object without those values makes sense."},
                new String[]{"Leaving fields null in it",
                    "Give them safe starting values."})
            .cyber(
                "Whether an object can be made with no arguments is a "
                + "design decision with security weight. If a Session can "
                + "be created without a user, some code path eventually "
                + "will - and then has to guess what an anonymous session "
                + "may do. Leave the no-argument constructor out unless an "
                + "empty object is genuinely safe.")
            .check(new Task(Task.CHOICE,
                    "When does Java supply the default constructor?")
                .choices("Always",
                         "Only when the class declares no constructors",
                         "Only when every field is an int",
                         "Never")
                .accept("2", "b")
                .hints("What happened in mission 10?",
                       "Writing one takes it away.")
                .explain(
                    "b. Only for a class with no constructors of its own.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Session has only Session(String user). Which line "
                    + "compiles?")
                .choices("new Session()",
                         "new Session(\"jsmith\")",
                         "new Session(42)",
                         "Session()")
                .accept("2", "b")
                .hints("Match the only constructor.",
                       "It needs one String.")
                .explain(
                    "b. The arguments must match a constructor that exists: "
                    + "one String.")
                .xp(10))
            .recap(
                "    no constructors written  ->  Java supplies X()\n"
                + "    any constructor written  ->  it does not\n"
                + "\n"
                + "Write X() yourself only when an object without those "
                + "values is safe - and set useful starting values.")
            .next("Next: several constructors in one class."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(12), "Several Ways to Make One", 4)
            .brief(
                "Most alerts arrive with a rule and a severity. Some come "
                + "from an older tool that sends only the rule, and those "
                + "should default to severity 5. Two kinds of caller, one "
                + "class: give Alert two constructors, and let Java pick the "
                + "one that fits the arguments.")
            .willLearn("Constructor overloading")
            .whyUseful(
                "Several constructors let callers supply what they have, "
                + "while the class fills in sensible defaults for the rest. "
                + "It is Campaign 03's overloading, applied to making "
                + "objects.")
            .concept("Constructor overloading",
                "A class can have several constructors, as long as their "
                + "PARAMETER LISTS differ - in number, types or order. "
                + "Java picks the one that matches the arguments:\n"
                + "\n"
                + "    Alert(String rule) {\n"
                + "        this.rule = rule;\n"
                + "        this.severity = 5;          a default\n"
                + "    }\n"
                + "\n"
                + "    Alert(String rule, int severity) {\n"
                + "        this.rule = rule;\n"
                + "        this.severity = severity;\n"
                + "    }\n"
                + "\n"
                + "    new Alert(\"Port scan\")         the first\n"
                + "    new Alert(\"Port scan\", 7)      the second\n"
                + "\n"
                + "This is OVERLOADING, exactly as with methods in Campaign "
                + "03: same name, different parameters. Parameter NAMES do "
                + "not count - two constructors both taking (String) clash, "
                + "whatever their parameters are called.\n"
                + "\n"
                + "Notice the repetition: both constructors set rule. "
                + "Mission 13 removes it, so each default and each rule "
                + "lives in one place.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Alert a = new Alert(\"Port scan\");",
                "        Alert b = new Alert(\"Admin created\", 9);",
                "        System.out.println(a.rule + \": \" + a.severity);",
                "        System.out.println(b.rule + \": \" + b.severity);",
                "    }",
                "}",
                "",
                "class Alert {",
                "    String rule;",
                "    int severity;",
                "",
                "    Alert(String rule) {",
                "        this.rule = rule;",
                "        this.severity = 5;",
                "    }",
                "",
                "    Alert(String rule, int severity) {",
                "        this.rule = rule;",
                "        this.severity = severity;",
                "    }",
                "}")
            .exampleOutput(
                "Port scan: 5",
                "Admin created: 9")
            .lineByLine(
                new String[]{"new Alert(\"Port scan\")",
                    "One String: the first constructor, with the default 5."},
                new String[]{"new Alert(\"Admin created\", 9)",
                    "A String and an int: the second constructor."},
                new String[]{"this.severity = 5;",
                    "The default lives in the class, not in every caller."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Rule {",
                    "    int port;",
                    "    String proto;",
                    "",
                    "    Rule(int port) {",
                    "        this.port = port;",
                    "        this.proto = \"tcp\";",
                    "    }",
                    "",
                    "    Rule(int port, String proto) {",
                    "        this.port = port;",
                    "        this.proto = proto;",
                    "    }",
                    "}",
                    "",
                    "Rule a = new Rule(53, \"udp\");",
                    "Rule b = new Rule(22);",
                    "System.out.println(a.proto + \" \" + b.proto);")
                .accept("udp tcp")
                .hints("Match each new to a constructor.",
                       "One argument gets the default protocol.")
                .explain(
                    "udp tcp. a used the two-argument constructor; b used "
                    + "the one that fills in tcp.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "class User {",
                    "    String name;",
                    "    String role;",
                    "",
                    "    User(String name) {",
                    "        this.name = name;",
                    "    }",
                    "",
                    "    User(String role) {",
                    "        this.role = role;",
                    "    }",
                    "}",
                    "",
                    "User u = new User(\"ana\");",
                    "System.out.println(u.name);")
                .accept("9", "line 9")
                .hints("Do the parameter lists differ?",
                       "Names do not count, only types.")
                .explain(
                    "Line 9: 'constructor User(String) is already defined'. "
                    + "Both take one String; Java could never tell which was "
                    + "meant.")
                .xp(20))
            .objective(
                "Make a session with the standard timeout.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Session s = new Session(\"jsmith\");",
                "        Session t = new Session(\"admin\", 5);",
                "        System.out.println(s.user + \" \" + s.minutes);",
                "        System.out.println(t.user + \" \" + t.minutes);",
                "    }",
                "}",
                "",
                "class Session {",
                "    String user;",
                "    int minutes;",
                "",
                "    // a constructor taking only a String user",
                "        this.user = user;",
                "        this.minutes = 30;",
                "    }",
                "",
                "    Session(String user, int minutes) {",
                "        this.user = user;",
                "        this.minutes = minutes;",
                "    }",
                "}")
            .yourTask(
                "Write the header of the constructor that takes only the "
                + "user, so new Session(\"jsmith\") gets the standard 30 "
                + "minutes.")
            .mainTask(new Task(Task.WRITE,
                    "Write the constructor header.")
                .accept("Session(String user) {",
                        "Session(String user){",
                        "public Session(String user) {")
                .hints(
                    "Same name, different parameters.",
                    "Just one String, called user.",
                    "Session(String user) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Session s = new Session(\"jsmith\");",
                    "        Session t = new Session(\"admin\", 5);",
                    "        System.out.println(s.user + \" \" + s.minutes);",
                    "        System.out.println(t.user + \" \" + t.minutes);",
                    "    }",
                    "}",
                    "",
                    "class Session {",
                    "    String user;",
                    "    int minutes;",
                    "",
                    "    Session(String user) {",
                    "        this.user = user;",
                    "        this.minutes = 30;",
                    "    }",
                    "",
                    "    Session(String user, int minutes) {",
                    "        this.user = user;",
                    "        this.minutes = minutes;",
                    "    }",
                    "}")
                .whyItWorks(
                    "One String matches the new constructor, which fills in "
                    + "30 minutes: jsmith 30. The admin session asks for 5 "
                    + "explicitly and gets the other constructor: admin 5.\n"
                    + "\n"
                    + "Short sessions for privileged users are a common "
                    + "policy; the two constructors let the caller choose "
                    + "without every caller repeating the standard value.")
                .explain(
                    "Session(String user) { - one String, default minutes.")
                .xp(20))
            .mistakes(
                new String[]{"Two constructors, same parameter types",
                    "'already defined'. The types must differ."},
                new String[]{"Changing only parameter names",
                    "Names never tell constructors apart."},
                new String[]{"The same default typed in many places",
                    "Mission 13 keeps it in one."})
            .cyber(
                "Defaults supplied by constructors are policy: 30-minute "
                + "sessions, severity 5, deny-by-default rules. Kept inside "
                + "the class, a policy change is one edit. Scattered across "
                + "callers as literal numbers, some copy is always missed, "
                + "and the system enforces two policies at once.")
            .check(new Task(Task.CHOICE,
                    "Alert has Alert(String) and Alert(String, int). Which "
                    + "does new Alert(\"x\", 3) run?")
                .choices("Alert(String)",
                         "Alert(String, int)",
                         "Both, one after the other",
                         "Neither")
                .accept("2", "b")
                .hints("Count the arguments.",
                       "A String and an int.")
                .explain(
                    "b. Java picks the constructor whose parameters match "
                    + "the arguments.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Which pair of constructors can live in one class?")
                .choices("Host(String a) and Host(String b)",
                         "Host(int port) and Host(String name)",
                         "Host(int x) and void Host(int y)",
                         "None: only one constructor is allowed")
                .accept("2", "b")
                .hints("The types must differ.",
                       "int and String differ.")
                .explain(
                    "b. Different parameter types. a clashes; c's second is "
                    + "a method, not a constructor; d is false.")
                .xp(10))
            .recap(
                "    Alert(String rule)                 default severity\n"
                + "    Alert(String rule, int severity)   chosen severity\n"
                + "\n"
                + "Same name, different parameter types; Java picks by the "
                + "arguments.")
            .next("Next: one constructor calling another."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(13), "Constructors Calling Constructors", 4)
            .brief(
                "Session now has two constructors, and both copy the user "
                + "and set the time. When a rule was added - trim and "
                + "lower-case every username - it went into one "
                + "constructor and not the other. Sessions made the short "
                + "way kept stray capitals. Let one constructor do the work "
                + "and the others pass it along.")
            .willLearn("Constructor chaining")
            .whyUseful(
                "this(...) lets every constructor funnel into one that does "
                + "the real work, so validation and tidying happen in "
                + "exactly one place however the object is made.")
            .concept("Constructor chaining",
                "Inside a constructor, this(...) calls ANOTHER constructor "
                + "of the same class:\n"
                + "\n"
                + "    Session(String user) {\n"
                + "        this(user, 30);              hand over\n"
                + "    }\n"
                + "\n"
                + "    Session(String user, int minutes) {\n"
                + "        this.user = user.trim().toLowerCase();\n"
                + "        this.minutes = minutes;\n"
                + "    }\n"
                + "\n"
                + "new Session(\"JSmith\") runs the first constructor, which "
                + "immediately runs the second with 30. All the real work - "
                + "tidying, storing - is written once, in the most complete "
                + "constructor.\n"
                + "\n"
                + "Two rules:\n"
                + "\n"
                + "    this(...) must be the FIRST statement\n"
                + "    this(...) is not this.field: brackets right\n"
                + "    after this mean 'another constructor'\n"
                + "\n"
                + "The pattern: short constructors supply defaults and "
                + "chain; one full constructor does everything else.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Session a = new Session(\"  JSmith \");",
                "        Session b = new Session(\"ADMIN\", 5);",
                "        System.out.println(a.user + \" \" + a.minutes);",
                "        System.out.println(b.user + \" \" + b.minutes);",
                "    }",
                "}",
                "",
                "class Session {",
                "    String user;",
                "    int minutes;",
                "",
                "    Session(String user) {",
                "        this(user, 30);",
                "    }",
                "",
                "    Session(String user, int minutes) {",
                "        this.user = user.trim().toLowerCase();",
                "        this.minutes = minutes;",
                "    }",
                "}")
            .exampleOutput(
                "jsmith 30",
                "admin 5")
            .lineByLine(
                new String[]{"this(user, 30);",
                    "Runs the two-argument constructor with the default."},
                new String[]{"user.trim().toLowerCase()",
                    "The tidying rule, written once - both ways of making a "
                    + "Session get it."},
                new String[]{"jsmith 30",
                    "The short constructor supplied 30 and chained."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Alert {",
                    "    String rule;",
                    "    int severity;",
                    "",
                    "    Alert(String rule) {",
                    "        this(rule, 5);",
                    "    }",
                    "",
                    "    Alert(String rule, int severity) {",
                    "        this.rule = rule.toUpperCase();",
                    "        this.severity = severity;",
                    "    }",
                    "}",
                    "",
                    "Alert a = new Alert(\"scan\");",
                    "System.out.println(a.rule + \" \" + a.severity);")
                .accept("SCAN 5")
                .hints("The one-argument constructor chains.",
                       "The full constructor upper-cases the rule.")
                .explain(
                    "SCAN 5. The short constructor passes 5 along, and the "
                    + "full one does the upper-casing for both.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "class Host {",
                    "    String name;",
                    "    int port;",
                    "",
                    "    Host(String name) {",
                    "        System.out.println(\"making\");",
                    "        this(name, 443);",
                    "    }",
                    "",
                    "    Host(String name, int port) {",
                    "        this.name = name;",
                    "        this.port = port;",
                    "    }",
                    "}",
                    "",
                    "Host h = new Host(\"web1\");")
                .accept("7", "line 7")
                .hints("Where must this(...) appear?",
                       "Nothing may come before it.")
                .explain(
                    "Line 7: the call to this(...) must be the first "
                    + "statement in the constructor.")
                .xp(20))
            .objective(
                "Chain the short constructor to the full one.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Alert a = new Alert(\"Port scan\");",
                "        System.out.println(a.rule + \": \" + a.severity);",
                "    }",
                "}",
                "",
                "class Alert {",
                "    String rule;",
                "    int severity;",
                "",
                "    Alert(String rule) {",
                "        // hand over to the full constructor, severity 5",
                "    }",
                "",
                "    Alert(String rule, int severity) {",
                "        this.rule = rule.trim();",
                "        this.severity = severity;",
                "    }",
                "}")
            .yourTask(
                "Write the single line that makes Alert(String rule) run "
                + "the full constructor with a severity of 5.")
            .mainTask(new Task(Task.WRITE,
                    "Write the line.")
                .accept("this(rule, 5);",
                        "this(rule,5);")
                .hints(
                    "this, followed straight away by brackets.",
                    "The arguments: the rule, then 5.",
                    "this(rule, 5);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Alert a = new Alert(\"Port scan\");",
                    "        System.out.println(a.rule + \": \" + a.severity);",
                    "    }",
                    "}",
                    "",
                    "class Alert {",
                    "    String rule;",
                    "    int severity;",
                    "",
                    "    Alert(String rule) {",
                    "        this(rule, 5);",
                    "    }",
                    "",
                    "    Alert(String rule, int severity) {",
                    "        this.rule = rule.trim();",
                    "        this.severity = severity;",
                    "    }",
                    "}")
                .whyItWorks(
                    "new Alert(\"Port scan\") runs the short constructor, "
                    + "which hands over to the full one with severity 5. The "
                    + "full one trims and stores both: Port scan: 5.\n"
                    + "\n"
                    + "Any rule added to the full constructor later - a "
                    + "range check on severity, say - now covers alerts made "
                    + "either way, with no second copy to forget.")
                .explain(
                    "this(rule, 5); - the full constructor does the work.")
                .xp(20))
            .mistakes(
                new String[]{"Statements before this(...)",
                    "It must be the first line."},
                new String[]{"Copying the work into every constructor",
                    "Chain to one; fix rules in one place."},
                new String[]{"this.(rule, 5) or Alert(rule, 5)",
                    "It is written this(rule, 5)."})
            .cyber(
                "Constructors are where input first becomes an object, so "
                + "they are where validation belongs. If there are three "
                + "constructors and only two validate, attackers will find "
                + "the third. Chaining every constructor into one that "
                + "checks everything means no way of making the object "
                + "skips the checks.")
            .check(new Task(Task.CHOICE,
                    "What does this(user, 30) do inside a constructor?")
                .choices("Sets the field this to user",
                         "Calls another constructor of the same class",
                         "Makes a second object",
                         "Calls a method called this")
                .accept("2", "b")
                .hints("Brackets straight after this.",
                       "It is not this.field.")
                .explain(
                    "b. It runs the matching constructor on the SAME object "
                    + "- no second object is made.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Box {",
                    "    int w;",
                    "    int h;",
                    "",
                    "    Box(int side) {",
                    "        this(side, side);",
                    "    }",
                    "",
                    "    Box(int w, int h) {",
                    "        this.w = w;",
                    "        this.h = h;",
                    "    }",
                    "}",
                    "",
                    "Box b = new Box(4);",
                    "System.out.println(b.w * b.h);")
                .accept("16")
                .hints("One argument, passed twice.",
                       "4 by 4.")
                .explain(
                    "16. The one-argument constructor chains with side for "
                    + "both width and height.")
                .xp(10))
            .recap(
                "    Session(String user) {\n"
                + "        this(user, 30);      first line, always\n"
                + "    }\n"
                + "\n"
                + "Short constructors supply defaults and chain; one full "
                + "constructor does the real work.")
            .next("Next: private - fields nobody else can touch."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(14), "private: Hands Off", 4)
            .brief(
                "The Account class has a careful recordFailure method that "
                + "locks at three. It did not matter: a password-reset page "
                + "elsewhere wrote acct.failures = 0 and acct.locked = "
                + "false directly, and a locked account under attack was "
                + "quietly unlocked. The rules lived in the class; the "
                + "fields were open to everyone.")
            .willLearn("private")
            .whyUseful(
                "private fields can only be touched by the class's own "
                + "methods. The rules in those methods then cannot be "
                + "bypassed - by mistake, by a rushed fix, or by anyone.")
            .concept("private",
                "Marking a field PRIVATE limits it to code inside its own "
                + "class:\n"
                + "\n"
                + "    class Account {\n"
                + "        private int failures;\n"
                + "        private boolean locked;\n"
                + "\n"
                + "        void recordFailure() {\n"
                + "            failures++;                 fine: inside\n"
                + "            ...\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    acct.failures = 0;      does not compile: outside\n"
                + "\n"
                + "Outside code must go through the methods the class "
                + "chooses to offer - and those methods enforce the rules. "
                + "If there is no unlock method, nothing outside can unlock.\n"
                + "\n"
                + "The fields without a modifier, used so far, are open to "
                + "any class in the same package (Campaign 10). From now "
                + "on, fields are private by default, and every exception "
                + "is a decision.\n"
                + "\n"
                + "private works on methods too: a helper that only the "
                + "class itself should call. And private is checked by the "
                + "COMPILER - it is about which code may use a member, not "
                + "about hiding secrets from someone reading the source.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Account a = new Account(\"admin\");",
                "        a.recordFailure();",
                "        a.recordFailure();",
                "        a.recordFailure();",
                "        System.out.println(a.describe());",
                "        a.recordSuccess();",
                "        System.out.println(a.describe());",
                "    }",
                "}",
                "",
                "class Account {",
                "    private String user;",
                "    private int failures;",
                "    private boolean locked;",
                "",
                "    Account(String user) {",
                "        this.user = user;",
                "    }",
                "",
                "    void recordFailure() {",
                "        failures++;",
                "        if (failures >= 3) {",
                "            locked = true;",
                "        }",
                "    }",
                "",
                "    void recordSuccess() {",
                "        if (!locked) {",
                "            failures = 0;",
                "        }",
                "    }",
                "",
                "    String describe() {",
                "        return user + \": \" + failures + (locked ? \" LOCKED\" : \"\");",
                "    }",
                "}")
            .exampleOutput(
                "admin: 3 LOCKED",
                "admin: 3 LOCKED")
            .lineByLine(
                new String[]{"private int failures;",
                    "Only Account's own methods can read or change it."},
                new String[]{"recordSuccess()",
                    "The only way to reset the count - and it refuses while "
                    + "the account is locked."},
                new String[]{"admin: 3 LOCKED (twice)",
                    "A login success cannot quietly clear a lock; there is "
                    + "no other door to the fields."})
            .predict(new Task(Task.CHOICE,
                    "Account has private int failures. Which line, written "
                    + "in Main, compiles?")
                .code(
                    "a.failures = 0;",
                    "a.recordFailure();",
                    "System.out.println(a.failures);",
                    "a.failures++;")
                .choices("Line 1", "Line 2", "Line 3", "Line 4")
                .accept("2", "b")
                .hints("Which line does not touch the field directly?",
                       "Methods are the way in.")
                .explain(
                    "b. Lines 1, 3 and 4 use the private field from outside "
                    + "the class and are refused. Calling a method the class "
                    + "offers is allowed.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "class Vault {",
                    "    private String code = \"7291\";",
                    "",
                    "    boolean check(String guess) {",
                    "        return code.equals(guess);",
                    "    }",
                    "}",
                    "",
                    "Vault v = new Vault();",
                    "System.out.println(v.check(\"0000\"));",
                    "System.out.println(v.code);")
                .accept("11", "line 11")
                .hints("Line 5 uses code too - but where is it?",
                       "Which use is outside the class?")
                .explain(
                    "Line 11: 'code has private access in Vault'. The class's "
                    + "own method may use code; Main may not.")
                .xp(20))
            .objective(
                "Protect the balance.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Wallet w = new Wallet();",
                "        w.deposit(50);",
                "        w.withdraw(80);",
                "        System.out.println(w.report());",
                "    }",
                "}",
                "",
                "class Wallet {",
                "    // the balance: an int that only Wallet may touch",
                "",
                "    void deposit(int n) {",
                "        if (n > 0) {",
                "            balance += n;",
                "        }",
                "    }",
                "",
                "    void withdraw(int n) {",
                "        if (n > 0 && n <= balance) {",
                "            balance -= n;",
                "        }",
                "    }",
                "",
                "    String report() {",
                "        return \"Balance: \" + balance;",
                "    }",
                "}")
            .yourTask(
                "Declare the balance field as a private int, so the only way "
                + "to change it is through deposit and withdraw.")
            .mainTask(new Task(Task.WRITE,
                    "Write the field line.")
                .accept("private int balance;",
                        "private int balance = 0;")
                .hints(
                    "A field with one extra word in front.",
                    "private, then the type and name.",
                    "private int balance;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Wallet w = new Wallet();",
                    "        w.deposit(50);",
                    "        w.withdraw(80);",
                    "        System.out.println(w.report());",
                    "    }",
                    "}",
                    "",
                    "class Wallet {",
                    "    private int balance;",
                    "",
                    "    void deposit(int n) {",
                    "        if (n > 0) {",
                    "            balance += n;",
                    "        }",
                    "    }",
                    "",
                    "    void withdraw(int n) {",
                    "        if (n > 0 && n <= balance) {",
                    "            balance -= n;",
                    "        }",
                    "    }",
                    "",
                    "    String report() {",
                    "        return \"Balance: \" + balance;",
                    "    }",
                    "}")
                .whyItWorks(
                    "The withdrawal of 80 is refused, because withdraw "
                    + "checks there is enough: Balance: 50. And because "
                    + "balance is private, main could not skip the check by "
                    + "writing w.balance -= 80 - that line would not "
                    + "compile.\n"
                    + "\n"
                    + "A rule is only as strong as the ways around it. "
                    + "private closes every way except the ones the class "
                    + "provides.")
                .explain(
                    "private int balance; - only Wallet's methods reach it.")
                .xp(20))
            .mistakes(
                new String[]{"Rules in methods, fields left open",
                    "Anyone can go around the rules. Make them private."},
                new String[]{"private as secrecy",
                    "It limits which code may use a field, not who reads it."},
                new String[]{"A setter for every private field",
                    "That reopens the door. Offer only what is needed."})
            .cyber(
                "Encapsulation is an access control inside the program. "
                + "Just as an operating system stops one user reading "
                + "another's files, private stops one part of the code "
                + "rewriting another's state. Lockout counters, balances, "
                + "permission flags and token expiry times are exactly the "
                + "fields that must only change through code that checks "
                + "the rules.")
            .check(new Task(Task.CHOICE,
                    "Which code can use a private field of Account?")
                .choices("Any class in Main.java",
                         "Only Account's own constructors and methods",
                         "Only main",
                         "No code at all")
                .accept("2", "b")
                .hints("private means 'this class only'.",
                       "Its own methods still use it.")
                .explain(
                    "b. The class's own code uses its private fields freely; "
                    + "everything else must go through its methods.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Account has private boolean locked and no method that "
                    + "sets it to false. What can unlock an account?")
                .choices("acct.locked = false; in Main",
                         "Nothing outside the class",
                         "Any method in Main",
                         "Calling the constructor again on it")
                .accept("2", "b")
                .hints("How does outside code change a private field?",
                       "Only through the class's methods.")
                .explain(
                    "b. With no unlock method, nothing outside Account can "
                    + "unlock it - the class decides what is possible.")
                .xp(10))
            .recap(
                "    private int failures;    only this class's code\n"
                + "    acct.failures = 0;       refused outside it\n"
                + "\n"
                + "Fields are private by default from now on. Outside code "
                + "goes through methods that enforce the rules.")
            .next("Next: controlled doors - getters and setters."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(15), "Getters and Setters", 3)
            .brief(
                "With its fields private, a Host is safe - and useless: the "
                + "report cannot even read its name. The class needs to "
                + "open a few controlled doors. Read access for the name "
                + "and the port; write access for the port only, because a "
                + "host's name is fixed when it is registered.")
            .willLearn("Getters and setters")
            .whyUseful(
                "Getters and setters are the standard, recognisable doors "
                + "into private fields. Choosing which ones to write - and "
                + "leaving the rest out - is how a class decides what "
                + "others may see and change.")
            .concept("Getters and setters",
                "A GETTER returns a private field's value; a SETTER "
                + "changes it. The names follow a convention every Java "
                + "programmer knows:\n"
                + "\n"
                + "    String getName()          read name\n"
                + "    int getPort()             read port\n"
                + "    void setPort(int port)    change port\n"
                + "    boolean isLocked()        read a boolean: is, not get\n"
                + "\n"
                + "    int getPort() {\n"
                + "        return port;\n"
                + "    }\n"
                + "\n"
                + "    void setPort(int port) {\n"
                + "        this.port = port;\n"
                + "    }\n"
                + "\n"
                + "The important part is which ones you do NOT write:\n"
                + "\n"
                + "    getter, no setter    READ-ONLY: set once in the\n"
                + "                         constructor, never changed\n"
                + "    neither              fully internal\n"
                + "    both                 changeable - so mission 16\n"
                + "                         makes the setter check values\n"
                + "\n"
                + "Writing a getter and setter for every field by reflex "
                + "undoes private. Ask for each field: who needs to read "
                + "it, and who should ever change it?")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Host h = new Host(\"db1\", 5432);",
                "        System.out.println(h.getName() + \":\" + h.getPort());",
                "        h.setPort(6432);",
                "        System.out.println(h.getName() + \":\" + h.getPort());",
                "    }",
                "}",
                "",
                "class Host {",
                "    private String name;",
                "    private int port;",
                "",
                "    Host(String name, int port) {",
                "        this.name = name;",
                "        this.port = port;",
                "    }",
                "",
                "    String getName() {",
                "        return name;",
                "    }",
                "",
                "    int getPort() {",
                "        return port;",
                "    }",
                "",
                "    void setPort(int port) {",
                "        this.port = port;",
                "    }",
                "}")
            .exampleOutput(
                "db1:5432",
                "db1:6432")
            .lineByLine(
                new String[]{"getName() with no setName()",
                    "The name is read-only: fixed by the constructor."},
                new String[]{"setPort(6432)",
                    "The one change the class allows."},
                new String[]{"h.getPort()",
                    "Outside code reads through the door, never the field."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Lock {",
                    "    private boolean locked = true;",
                    "",
                    "    boolean isLocked() {",
                    "        return locked;",
                    "    }",
                    "}",
                    "",
                    "Lock k = new Lock();",
                    "System.out.println(k.isLocked());")
                .accept("true")
                .hints("A boolean getter starts with is.",
                       "The field starts as true.")
                .explain(
                    "true. isLocked reads the private field; with no setter, "
                    + "nothing outside can ever change it.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "A User has private String passwordHash. Which set of "
                    + "methods is the safest to offer?")
                .choices("getPasswordHash() and setPasswordHash()",
                         "getPasswordHash() only",
                         "checkPassword(String attempt) and "
                         + "changePassword(old, new)",
                         "Make the field public instead")
                .accept("3", "c")
                .hints("Does anything outside need the hash itself?",
                       "Offer the operation, not the data.")
                .explain(
                    "c. Callers need to CHECK a password and CHANGE one "
                    + "with the old one - not to read or overwrite the hash. "
                    + "Good doors are operations, not raw getters and "
                    + "setters.")
                .xp(20))
            .objective(
                "Give Host a getter for its name.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Host h = new Host(\"fw1\");",
                "        System.out.println(\"Host: \" + h.getName());",
                "    }",
                "}",
                "",
                "class Host {",
                "    private String name;",
                "",
                "    Host(String name) {",
                "        this.name = name;",
                "    }",
                "",
                "    // the header of the getter for name",
                "        return name;",
                "    }",
                "}")
            .yourTask(
                "Write the header of the getter for the name field: it "
                + "follows the naming convention and returns a String.")
            .mainTask(new Task(Task.WRITE,
                    "Write the getter's header.")
                .accept("String getName() {",
                        "String getName(){",
                        "public String getName() {")
                .hints(
                    "get followed by the field's name, capitalised.",
                    "It returns the field's type.",
                    "String getName() {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Host h = new Host(\"fw1\");",
                    "        System.out.println(\"Host: \" + h.getName());",
                    "    }",
                    "}",
                    "",
                    "class Host {",
                    "    private String name;",
                    "",
                    "    Host(String name) {",
                    "        this.name = name;",
                    "    }",
                    "",
                    "    String getName() {",
                    "        return name;",
                    "    }",
                    "}")
                .whyItWorks(
                    "getName returns the private field's value, so main can "
                    + "print Host: fw1 without being able to change the name. "
                    + "With no setName, the name is fixed from the moment "
                    + "the constructor runs.\n"
                    + "\n"
                    + "Following the convention - getName, getPort, "
                    + "isLocked - means every Java programmer, and many "
                    + "tools, understand the class at a glance.")
                .explain(
                    "String getName() { - read access, nothing more.")
                .xp(15))
            .mistakes(
                new String[]{"A getter and setter for every field",
                    "That is a public field with extra steps."},
                new String[]{"getLocked() for a boolean",
                    "The convention is isLocked()."},
                new String[]{"A setter for something that should be fixed",
                    "Leave it out; set it in the constructor."})
            .cyber(
                "Every setter is a way to change state, so every setter is "
                + "part of the attack surface. A read-only name cannot be "
                + "spoofed later; a password hash with no getter cannot be "
                + "leaked by a careless log line. Design the doors from "
                + "what callers truly need, and prefer operations "
                + "(checkPassword) to raw access (getPasswordHash).")
            .check(new Task(Task.CHOICE,
                    "What is the conventional getter name for a private "
                    + "boolean field called active?")
                .choices("getActive()", "isActive()", "active()", "readActive()")
                .accept("2", "b")
                .hints("Booleans are different.",
                       "It reads like a question.")
                .explain(
                    "b. isActive() - booleans use is instead of get.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "How do you make a private field read-only to the rest "
                    + "of the program?")
                .choices("Write a getter and no setter",
                         "Write a setter and no getter",
                         "Remove private",
                         "Write both")
                .accept("1", "a")
                .hints("Others need to read it.",
                       "Nobody outside should change it.")
                .explain(
                    "a. A getter allows reading; with no setter, only the "
                    + "class itself - usually its constructor - ever sets "
                    + "it.")
                .xp(10))
            .recap(
                "    T getX()          read\n"
                + "    boolean isX()     read a boolean\n"
                + "    void setX(T x)    change\n"
                + "\n"
                + "Write only the doors callers need. Getter without setter "
                + "= read-only.")
            .next("Next: setters that refuse bad values."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(16), "Setters That Say No", 4)
            .brief(
                "Rule.setPort happily accepted 0, -1 and 70000, and the "
                + "firewall loaded rules that could never match anything - "
                + "or, in one parser, matched everything. A setter is a "
                + "door into an object. A good door checks what comes "
                + "through it.")
            .willLearn("Validating setters")
            .whyUseful(
                "If every change goes through a method, and every method "
                + "checks its input, the object can never hold a bad value. "
                + "That guarantee - an INVARIANT - is the real payoff of "
                + "private fields.")
            .concept("Validating setters",
                "A setter should refuse values that make no sense, so the "
                + "object is always valid:\n"
                + "\n"
                + "    boolean setPort(int port) {\n"
                + "        if (port < 1 || port > 65535) {\n"
                + "            return false;          refused, unchanged\n"
                + "        }\n"
                + "        this.port = port;\n"
                + "        return true;               accepted\n"
                + "    }\n"
                + "\n"
                + "Returning a boolean tells the caller whether it worked. "
                + "(Campaign 07 shows the stronger way: throwing an "
                + "exception, which a caller cannot ignore.)\n"
                + "\n"
                + "A rule every object of the class always obeys - 'port is "
                + "from 1 to 65535' - is called an INVARIANT. To keep it:\n"
                + "\n"
                + "    - the field is private\n"
                + "    - every setter checks\n"
                + "    - the constructor checks too, or starts from a\n"
                + "      safe value and uses the setter\n"
                + "\n"
                + "Refuse, do not repair: silently clamping 70000 to 65535 "
                + "hides the caller's bug and produces a rule nobody asked "
                + "for.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Rule r = new Rule();",
                "        int[] tries = {443, 0, 70000, 8443};",
                "        for (int p : tries) {",
                "            boolean ok = r.setPort(p);",
                "            String verdict = ok ? \"ok\" : \"REFUSED\";",
                "            System.out.println(\"set \" + p + \": \" + verdict",
                "                    + \", port is \" + r.getPort());",
                "        }",
                "    }",
                "}",
                "",
                "class Rule {",
                "    private int port = 443;",
                "",
                "    int getPort() {",
                "        return port;",
                "    }",
                "",
                "    boolean setPort(int port) {",
                "        if (port < 1 || port > 65535) {",
                "            return false;",
                "        }",
                "        this.port = port;",
                "        return true;",
                "    }",
                "}")
            .exampleOutput(
                "set 443: ok, port is 443",
                "set 0: REFUSED, port is 443",
                "set 70000: REFUSED, port is 443",
                "set 8443: ok, port is 8443")
            .lineByLine(
                new String[]{"private int port = 443;",
                    "Starts valid, so the invariant holds from the first "
                    + "moment."},
                new String[]{"return false;",
                    "Refused values change nothing: the port stays valid."},
                new String[]{"boolean ok = r.setPort(p);",
                    "The caller learns whether the change happened."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Temp {",
                    "    private int c = 20;",
                    "",
                    "    boolean set(int c) {",
                    "        if (c < -50 || c > 60) {",
                    "            return false;",
                    "        }",
                    "        this.c = c;",
                    "        return true;",
                    "    }",
                    "",
                    "    int get() {",
                    "        return c;",
                    "    }",
                    "}",
                    "",
                    "Temp t = new Temp();",
                    "t.set(35);",
                    "t.set(900);",
                    "System.out.println(t.get());")
                .accept("35")
                .hints("35 is accepted.",
                       "900 is refused and changes nothing.")
                .explain(
                    "35. The second call is refused, so the last accepted "
                    + "value stays.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "setSeverity(int s) must keep severity from 1 to 10. It "
                    + "is called with 14. What is the best behaviour?")
                .choices("Store 14 anyway",
                         "Quietly store 10 instead",
                         "Refuse, leave the old value, and report failure",
                         "Set it to 0")
                .accept("3", "c")
                .hints("What does the caller learn?",
                       "Refuse, do not repair.")
                .explain(
                    "c. Refusing keeps the invariant AND tells the caller "
                    + "something is wrong. Clamping hides the bug; storing "
                    + "14 or 0 breaks the rule.")
                .xp(15))
            .objective(
                "Make the session length setter refuse bad values.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Session s = new Session();",
                "        System.out.println(s.setMinutes(45) + \" \" + s.getMinutes());",
                "        System.out.println(s.setMinutes(0) + \" \" + s.getMinutes());",
                "        System.out.println(s.setMinutes(600) + \" \" + s.getMinutes());",
                "    }",
                "}",
                "",
                "class Session {",
                "    private int minutes = 30;",
                "",
                "    int getMinutes() {",
                "        return minutes;",
                "    }",
                "",
                "    boolean setMinutes(int minutes) {",
                "        // the if: minutes is below 1 or above 480",
                "            return false;",
                "        }",
                "        this.minutes = minutes;",
                "        return true;",
                "    }",
                "}")
            .yourTask(
                "Write the if that catches a session length below 1 minute "
                + "or above 480 minutes (8 hours).")
            .mainTask(new Task(Task.WRITE,
                    "Write the if line.")
                .accept("if (minutes < 1 || minutes > 480) {",
                        "if(minutes < 1 || minutes > 480) {",
                        "if (minutes < 1 || minutes > 480){",
                        "if (minutes > 480 || minutes < 1) {",
                        "if (minutes <= 0 || minutes > 480) {")
                .hints(
                    "Either problem alone is enough to refuse: ||.",
                    "Below 1, or above 480.",
                    "if (minutes < 1 || minutes > 480) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Session s = new Session();",
                    "        System.out.println(s.setMinutes(45) + \" \" + s.getMinutes());",
                    "        System.out.println(s.setMinutes(0) + \" \" + s.getMinutes());",
                    "        System.out.println(s.setMinutes(600) + \" \" + s.getMinutes());",
                    "    }",
                    "}",
                    "",
                    "class Session {",
                    "    private int minutes = 30;",
                    "",
                    "    int getMinutes() {",
                    "        return minutes;",
                    "    }",
                    "",
                    "    boolean setMinutes(int minutes) {",
                    "        if (minutes < 1 || minutes > 480) {",
                    "            return false;",
                    "        }",
                    "        this.minutes = minutes;",
                    "        return true;",
                    "    }",
                    "}")
                .whyItWorks(
                    "45 is accepted: true 45. 0 and 600 are refused, and the "
                    + "session keeps 45: false 45, false 45.\n"
                    + "\n"
                    + "A zero-minute session would log users out at once; a "
                    + "600-minute one would outlive a working day and keep a "
                    + "forgotten session open overnight. The setter keeps "
                    + "every Session inside policy.")
                .explain(
                    "if (minutes < 1 || minutes > 480) { - refuse either end.")
                .xp(20))
            .mistakes(
                new String[]{"Checking only one end",
                    "Test the lower AND upper bound."},
                new String[]{"Changing the field before checking",
                    "Check first; change only when valid."},
                new String[]{"A validating setter, an unchecked constructor",
                    "Both doors need the check."})
            .cyber(
                "Input validation is most reliable at the object's own "
                + "door. Validate in the UI and a script calls the API "
                + "directly; validate in one caller and another forgets. "
                + "Validate in the setter and the constructor, and no path "
                + "can store a bad port, a negative amount or an endless "
                + "session - whatever called it.")
            .check(new Task(Task.CHOICE,
                    "What is an invariant?")
                .choices("A field that never changes",
                         "A rule every object of the class always obeys",
                         "A constructor with no parameters",
                         "A static method")
                .accept("2", "b")
                .hints("'port is from 1 to 65535'.",
                       "True of every object, all the time.")
                .explain(
                    "b. An invariant is a rule that always holds - kept true "
                    + "by private fields and methods that check.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Pin {",
                    "    private String code = \"0000\";",
                    "",
                    "    boolean set(String c) {",
                    "        if (c.length() != 4) {",
                    "            return false;",
                    "        }",
                    "        code = c;",
                    "        return true;",
                    "    }",
                    "",
                    "    String get() {",
                    "        return code;",
                    "    }",
                    "}",
                    "",
                    "Pin p = new Pin();",
                    "boolean a = p.set(\"12\");",
                    "boolean b = p.set(\"4821\");",
                    "System.out.println(a + \" \" + b + \" \" + p.get());")
                .accept("false true 4821")
                .hints("Two characters is refused.",
                       "Four is accepted.")
                .explain(
                    "false true 4821. The refused value left 0000 in place; "
                    + "the valid one replaced it.")
                .xp(10))
            .recap(
                "    boolean setX(v) {\n"
                + "        if (v is bad) return false;   unchanged\n"
                + "        this.x = v;  return true;\n"
                + "    }\n"
                + "\n"
                + "Private field + checking setters + checking constructor "
                + "= an invariant that always holds.")
            .next("Next: toString - how an object prints itself."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(17), "How an Object Prints Itself", 3)
            .brief(
                "The alert log printed lines like Alert@1b6d3586 - where "
                + "each alert lived in memory, which is no use to anyone "
                + "reading a log at 3 a.m. Every class can decide how its "
                + "objects appear as text, by writing one method with a "
                + "name Java already knows: toString.")
            .willLearn("toString")
            .whyUseful(
                "println, + with a String, and every ArrayList print all "
                + "call toString. Writing it once makes every object of the "
                + "class readable in logs, reports and while debugging.")
            .concept("toString",
                "Every object already has a toString method - Java gives "
                + "one to every class. The built-in version returns the "
                + "class name, @, and a hex code: Alert@1b6d3586.\n"
                + "\n"
                + "Write your own, with exactly this header, and Java uses "
                + "yours instead:\n"
                + "\n"
                + "    @Override\n"
                + "    public String toString() {\n"
                + "        return rule + \" (severity \" + severity + \")\";\n"
                + "    }\n"
                + "\n"
                + "    public        required here (Campaign 10 says why)\n"
                + "    @Override     asks the compiler to check that you\n"
                + "                  really are replacing Java's version\n"
                + "\n"
                + "@Override is optional but catches typos: a method named "
                + "tostring or ToString would silently be a NEW method, "
                + "never called by println. With @Override, the typo is a "
                + "compile error.\n"
                + "\n"
                + "Java calls toString for you whenever an object must "
                + "become text:\n"
                + "\n"
                + "    System.out.println(a)       prints a.toString()\n"
                + "    \"Alert: \" + a               joins a.toString()\n"
                + "    System.out.println(list)    each item's toString\n"
                + "\n"
                + "Keep it short and readable - and never include secrets. "
                + "Objects end up in logs.")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Alert a = new Alert(\"Brute force\", 8);",
                "        System.out.println(a);",
                "        String line = \"New: \" + new Alert(\"Port scan\", 4);",
                "        System.out.println(line);",
                "        ArrayList<Alert> queue = new ArrayList<>();",
                "        queue.add(a);",
                "        queue.add(new Alert(\"Admin created\", 9));",
                "        System.out.println(queue);",
                "    }",
                "}",
                "",
                "class Alert {",
                "    private String rule;",
                "    private int severity;",
                "",
                "    Alert(String rule, int severity) {",
                "        this.rule = rule;",
                "        this.severity = severity;",
                "    }",
                "",
                "    @Override",
                "    public String toString() {",
                "        return rule + \" (severity \" + severity + \")\";",
                "    }",
                "}")
            .exampleOutput(
                "Brute force (severity 8)",
                "New: Port scan (severity 4)",
                "[Brute force (severity 8), Admin created (severity 9)]")
            .lineByLine(
                new String[]{"System.out.println(a);",
                    "println calls a.toString() for you."},
                new String[]{"\"New: \" + new Alert(...)",
                    "Joining an object to a String calls toString too."},
                new String[]{"System.out.println(queue);",
                    "A list prints each item with the item's own toString."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Host {",
                    "    String name = \"db1\";",
                    "    int port = 5432;",
                    "",
                    "    @Override",
                    "    public String toString() {",
                    "        return name + \":\" + port;",
                    "    }",
                    "}",
                    "",
                    "Host h = new Host();",
                    "System.out.println(\"Target \" + h);")
                .accept("Target db1:5432")
                .hints("+ with a String calls toString.",
                       "toString returns name:port.")
                .explain(
                    "Target db1:5432. The object became text through its "
                    + "own toString.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "class Ticket {",
                    "    String title = \"Disk full\";",
                    "",
                    "    String toString() {",
                    "        return \"Ticket: \" + title;",
                    "    }",
                    "}",
                    "",
                    "System.out.println(new Ticket());")
                .accept("4", "line 4")
                .hints("Compare the header with the one in the concept.",
                       "One word is missing.")
                .explain(
                    "Line 4: 'attempting to assign weaker access "
                    + "privileges'. Java's toString is public, and a "
                    + "replacement must be public too.")
                .xp(20))
            .objective(
                "Make Host objects print as name:port.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Host h = new Host(\"web1\", 443);",
                "        System.out.println(h);",
                "    }",
                "}",
                "",
                "class Host {",
                "    private String name;",
                "    private int port;",
                "",
                "    Host(String name, int port) {",
                "        this.name = name;",
                "        this.port = port;",
                "    }",
                "",
                "    @Override",
                "    // the header of toString",
                "        return name + \":\" + port;",
                "    }",
                "}")
            .yourTask(
                "Write the exact header that replaces Java's toString.")
            .mainTask(new Task(Task.WRITE,
                    "Write the toString header.")
                .accept("public String toString() {",
                        "public String toString(){")
                .hints(
                    "It must be public.",
                    "It returns a String and takes nothing.",
                    "public String toString() {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Host h = new Host(\"web1\", 443);",
                    "        System.out.println(h);",
                    "    }",
                    "}",
                    "",
                    "class Host {",
                    "    private String name;",
                    "    private int port;",
                    "",
                    "    Host(String name, int port) {",
                    "        this.name = name;",
                    "        this.port = port;",
                    "    }",
                    "",
                    "    @Override",
                    "    public String toString() {",
                    "        return name + \":\" + port;",
                    "    }",
                    "}")
                .whyItWorks(
                    "println(h) calls h.toString(), which now returns "
                    + "web1:443 instead of Host@ and a code.\n"
                    + "\n"
                    + "@Override checked that the header really matches "
                    + "Java's toString - spell it tostring and the compiler "
                    + "says so, instead of println quietly using the old "
                    + "version.")
                .explain(
                    "public String toString() { - replaces Java's version.")
                .xp(15))
            .mistakes(
                new String[]{"Leaving out public",
                    "'weaker access privileges'. It must be public."},
                new String[]{"Printing inside toString",
                    "Return the text; let the caller print it."},
                new String[]{"Secrets in toString",
                    "Objects get logged. Leave hashes and tokens out."})
            .cyber(
                "toString is called in places you did not plan: debug "
                + "logs, error messages, list prints. A User whose toString "
                + "includes the password hash, or a Session whose toString "
                + "includes the token, will eventually write them to a log "
                + "file that far more people can read. Decide what an "
                + "object may say about itself.")
            .check(new Task(Task.CHOICE,
                    "A class has no toString of its own. What does "
                    + "println(obj) show?")
                .choices("Nothing",
                         "Every field's value",
                         "The class name, @ and a hex code",
                         "A compile error")
                .accept("3", "c")
                .hints("Every class gets Java's version.",
                       "Like arrays in Campaign 05.")
                .explain(
                    "c. Java's own toString: something like Alert@1b6d3586.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "What does @Override do?")
                .choices("Makes the method public",
                         "Asks the compiler to check it replaces an "
                         + "existing method",
                         "Calls the old toString first",
                         "Nothing at all")
                .accept("2", "b")
                .hints("It is a note to the compiler.",
                       "It catches typos in the name.")
                .explain(
                    "b. If no existing method is being replaced - a typo in "
                    + "the name, say - the compiler reports it.")
                .xp(10))
            .recap(
                "    @Override\n"
                + "    public String toString() {\n"
                + "        return ...readable text...;\n"
                + "    }\n"
                + "\n"
                + "Used by println, + with Strings, and list printing. "
                + "Keep secrets out of it.")
            .next("Next: when are two objects equal?"));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(18), "When Are Two Objects Equal?", 5)
            .brief(
                "The allowlist is an ArrayList of Host objects, and it "
                + "never allowed anything. allow.contains(new Host(\"db1\", "
                + "5432)) was false even though db1:5432 was in the list. "
                + "contains uses equals, and Java's equals for objects asks "
                + "only 'the same object?'. A class decides what 'equal' "
                + "means for itself.")
            .willLearn("equals for objects")
            .whyUseful(
                "Comparing records by their contents is everyday work - "
                + "same host, same account, same rule. Writing equals makes "
                + "==-style comparisons correct, and makes contains, "
                + "indexOf and remove work on lists of your objects.")
            .concept("equals for objects",
                "Java's built-in equals behaves like ==: true only for the "
                + "SAME object. To compare contents, replace it:\n"
                + "\n"
                + "    @Override\n"
                + "    public boolean equals(Object other) {\n"
                + "        if (!(other instanceof Host)) {\n"
                + "            return false;\n"
                + "        }\n"
                + "        Host h = (Host) other;\n"
                + "        return name.equals(h.name) && port == h.port;\n"
                + "    }\n"
                + "\n"
                + "Line by line:\n"
                + "\n"
                + "    Object other   equals must accept ANY object -\n"
                + "                   that is Java's rule for the header\n"
                + "    instanceof     true when other really is a Host\n"
                + "                   (and false for null)\n"
                + "    (Host) other   a CAST, like (int) 3.9: treat it as\n"
                + "                   a Host, now that we know it is one\n"
                + "    the return     same contents: Strings with equals,\n"
                + "                   numbers with ==\n"
                + "\n"
                + "Note that h.name works even though name is private: "
                + "private means 'this CLASS only', and equals is inside "
                + "Host.\n"
                + "\n"
                + "One more rule: a class that writes equals should also "
                + "write hashCode, so the two agree. Nothing in this "
                + "campaign needs it; Campaign 11's HashMap and HashSet do, "
                + "and it is covered there.")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Host a = new Host(\"db1\", 5432);",
                "        Host b = new Host(\"db1\", 5432);",
                "        System.out.println(\"a == b: \" + (a == b));",
                "        System.out.println(\"a.equals(b): \" + a.equals(b));",
                "        ArrayList<Host> allow = new ArrayList<>();",
                "        allow.add(a);",
                "        System.out.println(allow.contains(new Host(\"db1\", 5432)));",
                "        System.out.println(allow.contains(new Host(\"db1\", 22)));",
                "    }",
                "}",
                "",
                "class Host {",
                "    private String name;",
                "    private int port;",
                "",
                "    Host(String name, int port) {",
                "        this.name = name;",
                "        this.port = port;",
                "    }",
                "",
                "    @Override",
                "    public boolean equals(Object other) {",
                "        if (!(other instanceof Host)) {",
                "            return false;",
                "        }",
                "        Host h = (Host) other;",
                "        return name.equals(h.name) && port == h.port;",
                "    }",
                "}")
            .exampleOutput(
                "a == b: false",
                "a.equals(b): true",
                "true",
                "false")
            .lineByLine(
                new String[]{"a == b: false",
                    "Two separate objects - == still asks 'same object?'."},
                new String[]{"a.equals(b): true",
                    "Our equals compares name and port instead."},
                new String[]{"allow.contains(new Host(\"db1\", 5432))",
                    "contains calls equals on each item, so it now finds a "
                    + "matching host."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Tag {",
                    "    String text;",
                    "",
                    "    Tag(String text) {",
                    "        this.text = text;",
                    "    }",
                    "}",
                    "",
                    "Tag a = new Tag(\"prod\");",
                    "Tag b = new Tag(\"prod\");",
                    "System.out.println(a.equals(b));")
                .accept("false")
                .hints("Does Tag write its own equals?",
                       "Java's own equals asks 'same object?'.")
                .explain(
                    "false. With no equals of its own, Tag uses Java's, "
                    + "which is true only for the very same object.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Why does equals take Object other, not Host other?")
                .choices("Object is shorter",
                         "Java's equals takes Object, and a replacement must "
                         + "match it exactly",
                         "Host cannot be a parameter type",
                         "It makes the method faster")
                .accept("2", "b")
                .hints("It replaces an existing method.",
                       "@Override would complain otherwise.")
                .explain(
                    "b. equals(Host other) would be a NEW method that "
                    + "contains never calls. The header must match Java's: "
                    + "equals(Object other).")
                .xp(15))
            .objective(
                "Compare two accounts by username.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Account a = new Account(\"jsmith\");",
                "        Account b = new Account(\"jsmith\");",
                "        Account c = new Account(\"admin\");",
                "        System.out.println(a.equals(b) + \" \" + a.equals(c));",
                "    }",
                "}",
                "",
                "class Account {",
                "    private String user;",
                "",
                "    Account(String user) {",
                "        this.user = user;",
                "    }",
                "",
                "    @Override",
                "    public boolean equals(Object other) {",
                "        if (!(other instanceof Account)) {",
                "            return false;",
                "        }",
                "        Account a = (Account) other;",
                "        // true when the two users are the same text",
                "    }",
                "}")
            .yourTask(
                "Write the return line: two accounts are equal when their "
                + "user fields hold the same text.")
            .mainTask(new Task(Task.WRITE,
                    "Write the return line.")
                .accept("return user.equals(a.user);",
                        "return this.user.equals(a.user);",
                        "return a.user.equals(user);",
                        "return a.user.equals(this.user);")
                .hints(
                    "Compare Strings with equals, never ==.",
                    "This account's user against a's user.",
                    "return user.equals(a.user);")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Account a = new Account(\"jsmith\");",
                    "        Account b = new Account(\"jsmith\");",
                    "        Account c = new Account(\"admin\");",
                    "        System.out.println(a.equals(b) + \" \" + a.equals(c));",
                    "    }",
                    "}",
                    "",
                    "class Account {",
                    "    private String user;",
                    "",
                    "    Account(String user) {",
                    "        this.user = user;",
                    "    }",
                    "",
                    "    @Override",
                    "    public boolean equals(Object other) {",
                    "        if (!(other instanceof Account)) {",
                    "            return false;",
                    "        }",
                    "        Account a = (Account) other;",
                    "        return user.equals(a.user);",
                    "    }",
                    "}")
                .whyItWorks(
                    "user.equals(a.user) compares the two usernames as text. "
                    + "a and b both hold jsmith, so they are equal; c holds "
                    + "admin, so it is not: true false.\n"
                    + "\n"
                    + "The instanceof check comes first, so comparing an "
                    + "Account with null or with some other kind of object "
                    + "returns false instead of crashing on the cast.")
                .explain(
                    "return user.equals(a.user); - same text, same account.")
                .xp(25))
            .mistakes(
                new String[]{"equals(Host other)",
                    "A new method, never used by contains. Take Object."},
                new String[]{"Comparing String fields with ==",
                    "Use equals inside equals too."},
                new String[]{"Casting before instanceof",
                    "A wrong type crashes. Check first."})
            .cyber(
                "Identity comparisons decide access: is this the same user, "
                + "the same device, the same certificate? Compare the wrong "
                + "way and a match is missed - an allowlist that allows "
                + "nothing, or a blocklist that blocks nothing because the "
                + "blocked entry is 'a different object'. Decide what "
                + "equality means for the record, and write it once, in "
                + "equals.")
            .check(new Task(Task.CHOICE,
                    "What does list.contains(x) use to compare x with each "
                    + "item?")
                .choices("==", "equals", "toString", "hashCode only")
                .accept("2", "b")
                .hints("Campaign 05 said list methods compare with ...",
                       "So a class's own equals matters.")
                .explain(
                    "b. contains, indexOf and remove(Object) all call "
                    + "equals - so they only find matching objects once the "
                    + "class writes it.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "What is other instanceof Host when other is null?")
                .choices("true", "false", "It crashes", "It does not compile")
                .accept("2", "b")
                .hints("null is not a Host.",
                       "instanceof never crashes.")
                .explain(
                    "b. false. That is why the instanceof check also "
                    + "protects equals from null.")
                .xp(10))
            .recap(
                "    @Override\n"
                + "    public boolean equals(Object other) {\n"
                + "        if (!(other instanceof Host)) return false;\n"
                + "        Host h = (Host) other;\n"
                + "        return ...compare the fields...;\n"
                + "    }\n"
                + "\n"
                + "== asks 'same object?'; equals asks 'same contents?'.")
            .next("Next: static - members that belong to the class."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(19), "Belonging to the Class", 4)
            .brief(
                "Every incident ticket needs a unique number: 1, 2, 3, in "
                + "the order they were opened. An ordinary field cannot do "
                + "it - each ticket has its own copy, and none of them "
                + "knows how many others exist. The counter must belong to "
                + "the Ticket CLASS, shared by all tickets. That is what "
                + "static means.")
            .willLearn("Static and instance members")
            .whyUseful(
                "static has been on every main since Campaign 00. Now it "
                + "can be explained: static members belong to the class "
                + "itself, one copy in total; instance members belong to "
                + "each object.")
            .concept("Static and instance members",
                "An INSTANCE field has one copy per object. A STATIC field "
                + "has ONE copy, shared by the class and all its objects:\n"
                + "\n"
                + "    class Ticket {\n"
                + "        private static int nextId = 1;   one, shared\n"
                + "        private int id;                  one per ticket\n"
                + "        private String title;\n"
                + "\n"
                + "        Ticket(String title) {\n"
                + "            this.title = title;\n"
                + "            id = nextId;\n"
                + "            nextId++;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "Each constructor takes the shared counter's value as its "
                + "own id, then moves the counter on - so tickets get 1, 2, "
                + "3.\n"
                + "\n"
                + "Methods follow the same split:\n"
                + "\n"
                + "    instance method   runs on an object; has this;\n"
                + "                      uses instance AND static fields\n"
                + "    static method     belongs to the class; no this,\n"
                + "                      so NO instance fields\n"
                + "\n"
                + "    Ticket.opened()   a static method, called on the\n"
                + "                      class name\n"
                + "\n"
                + "That explains a Campaign 03 error: main is static, so "
                + "inside main there is no object - which is why Main's "
                + "helpers had to be static too. Math.max and "
                + "Integer.parseInt are static for the same reason: they "
                + "need no object.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Ticket a = new Ticket(\"Phishing report\");",
                "        Ticket b = new Ticket(\"Disk full\");",
                "        Ticket c = new Ticket(\"VPN down\");",
                "        System.out.println(a);",
                "        System.out.println(c);",
                "        System.out.println(\"Opened so far: \" + Ticket.opened());",
                "    }",
                "}",
                "",
                "class Ticket {",
                "    private static int nextId = 1;",
                "    private int id;",
                "    private String title;",
                "",
                "    Ticket(String title) {",
                "        this.title = title;",
                "        id = nextId;",
                "        nextId++;",
                "    }",
                "",
                "    static int opened() {",
                "        return nextId - 1;",
                "    }",
                "",
                "    @Override",
                "    public String toString() {",
                "        return \"#\" + id + \" \" + title;",
                "    }",
                "}")
            .exampleOutput(
                "#1 Phishing report",
                "#3 VPN down",
                "Opened so far: 3")
            .lineByLine(
                new String[]{"private static int nextId = 1;",
                    "One counter for the whole class, not one per ticket."},
                new String[]{"id = nextId; nextId++;",
                    "Each ticket copies the counter into its own id, then "
                    + "moves the shared counter on."},
                new String[]{"Ticket.opened()",
                    "A static method, called on the class - no ticket "
                    + "needed."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Probe {",
                    "    static int total = 0;",
                    "    int mine = 0;",
                    "",
                    "    void hit() {",
                    "        total++;",
                    "        mine++;",
                    "    }",
                    "}",
                    "",
                    "Probe p = new Probe();",
                    "Probe q = new Probe();",
                    "p.hit();",
                    "p.hit();",
                    "q.hit();",
                    "System.out.println(p.mine + \" \" + q.mine + \" \" + Probe.total);")
                .accept("2 1 3")
                .hints("mine is per object; total is shared.",
                       "Three hits in all.")
                .explain(
                    "2 1 3. Each object counts its own hits in mine; the "
                    + "one static total counts every hit.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "class Sensor {",
                    "    int reading;",
                    "",
                    "    static boolean isHot() {",
                    "        return reading > 30;",
                    "    }",
                    "}",
                    "",
                    "Sensor s = new Sensor();",
                    "System.out.println(Sensor.isHot());")
                .accept("5", "line 5")
                .hints("Which object's reading would a static method use?",
                       "There is none.")
                .explain(
                    "Line 5: 'non-static variable reading cannot be "
                    + "referenced from a static context'. A static method "
                    + "has no object, so it has no reading. isHot should "
                    + "not be static.")
                .xp(20))
            .objective(
                "Number every asset tag automatically.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Asset a = new Asset(\"laptop\");",
                "        Asset b = new Asset(\"yubikey\");",
                "        System.out.println(a.label() + \" \" + b.label());",
                "    }",
                "}",
                "",
                "class Asset {",
                "    // nextTag: one shared int for the whole class, starting at 100",
                "    private int tag;",
                "    private String kind;",
                "",
                "    Asset(String kind) {",
                "        this.kind = kind;",
                "        tag = nextTag;",
                "        nextTag++;",
                "    }",
                "",
                "    String label() {",
                "        return kind + \"-\" + tag;",
                "    }",
                "}")
            .yourTask(
                "Declare nextTag: a private static int, shared by every "
                + "Asset, starting at 100.")
            .mainTask(new Task(Task.WRITE,
                    "Write the field line.")
                .accept("private static int nextTag = 100;",
                        "static int nextTag = 100;",
                        "private static int nextTag=100;")
                .hints(
                    "static makes it one copy for the class.",
                    "Give it its starting value with =.",
                    "private static int nextTag = 100;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Asset a = new Asset(\"laptop\");",
                    "        Asset b = new Asset(\"yubikey\");",
                    "        System.out.println(a.label() + \" \" + b.label());",
                    "    }",
                    "}",
                    "",
                    "class Asset {",
                    "    private static int nextTag = 100;",
                    "    private int tag;",
                    "    private String kind;",
                    "",
                    "    Asset(String kind) {",
                    "        this.kind = kind;",
                    "        tag = nextTag;",
                    "        nextTag++;",
                    "    }",
                    "",
                    "    String label() {",
                    "        return kind + \"-\" + tag;",
                    "    }",
                    "}")
                .whyItWorks(
                    "There is one nextTag for the class. The laptop takes "
                    + "100 and moves it to 101; the yubikey takes 101: "
                    + "laptop-100 yubikey-101.\n"
                    + "\n"
                    + "Without static, each Asset would have its own nextTag "
                    + "starting at 100, and every asset would be tagged 100 "
                    + "- two laptops with the same tag, and an inventory "
                    + "that cannot tell them apart.")
                .explain(
                    "private static int nextTag = 100; - one, shared.")
                .xp(20))
            .mistakes(
                new String[]{"A per-object counter for IDs",
                    "Every object starts at the same number. Use static."},
                new String[]{"Instance fields in a static method",
                    "No object, no fields. Remove static from the method."},
                new String[]{"static for ordinary data",
                    "Then all objects share one value - rarely wanted."})
            .cyber(
                "Static state is shared by everything that uses the class, "
                + "which makes it powerful and risky. A static counter for "
                + "IDs is fine. A static 'current user' field in a server "
                + "that handles many users at once is a classic, serious "
                + "bug: one user's request sees another user's identity. "
                + "Keep per-user and per-request data in instance fields.")
            .check(new Task(Task.CHOICE,
                    "Which field should be static?")
                .choices("Each account's username",
                         "The number of accounts created so far",
                         "Each account's failure count",
                         "Each account's lock flag")
                .accept("2", "b")
                .hints("Which one belongs to no single account?",
                       "It describes the whole class.")
                .explain(
                    "b. The count of accounts is one value for the class. "
                    + "The others differ per account, so they are instance "
                    + "fields.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Why must helper methods called from main be static?")
                .choices("static methods are faster",
                         "main is static, so there is no object to call "
                         + "instance methods on",
                         "Java forbids instance methods in Main",
                         "They must not use parameters")
                .accept("2", "b")
                .hints("What is this inside main?",
                       "There is no this.")
                .explain(
                    "b. main runs without any Main object, so it can only "
                    + "call methods that need none - static ones - unless it "
                    + "makes an object first.")
                .xp(10))
            .recap(
                "    static int nextId;   one copy, for the class\n"
                + "    int id;              one copy per object\n"
                + "    static method        no this, no instance fields\n"
                + "    ClassName.method()   how static methods are called\n"
                + "\n"
                + "Shared counters: static. Per-object data: instance.")
            .next("Next: a list of objects."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(20), "A List of Objects", 4)
            .brief(
                "Campaign 05's parallel lists - names in one, counts in "
                + "another - can finally retire. An ArrayList<Account> "
                + "holds whole accounts, each carrying its own user, count "
                + "and lock. Sorting, removing or adding can never pair a "
                + "count with the wrong user again.")
            .willLearn("Lists of objects")
            .whyUseful(
                "A list of objects is how most programs hold their data: "
                + "every open alert, every registered host, every active "
                + "session. Everything Campaign 05 taught about lists works "
                + "unchanged - with far richer items.")
            .concept("Lists of objects",
                "A list's type in the angle brackets can be your own class:\n"
                + "\n"
                + "    ArrayList<Account> accounts = new ArrayList<>();\n"
                + "    accounts.add(new Account(\"admin\"));\n"
                + "\n"
                + "Each item is an Account, so the enhanced for hands you "
                + "whole objects, and you call their methods:\n"
                + "\n"
                + "    for (Account a : accounts) {\n"
                + "        if (a.isLocked()) {\n"
                + "            System.out.println(a);\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "The list holds REFERENCES. accounts.get(0) gives the very "
                + "object in the list, not a copy - calling "
                + "get(0).recordFailure() changes the account stored there.\n"
                + "\n"
                + "Searching by a field is Campaign 05's linear search, "
                + "returning the object - or null:\n"
                + "\n"
                + "    static Account find(ArrayList<Account> list,\n"
                + "                        String user) { ... }\n"
                + "\n"
                + "contains, indexOf and remove(Object) need equals "
                + "(mission 18). The index loop and removing backwards "
                + "work exactly as before.")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<Account> accounts = new ArrayList<>();",
                "        accounts.add(new Account(\"admin\"));",
                "        accounts.add(new Account(\"jsmith\"));",
                "        accounts.add(new Account(\"mpatel\"));",
                "        String[] log = {\"admin\", \"admin\", \"jsmith\", \"admin\"};",
                "        for (String user : log) {",
                "            Account a = find(accounts, user);",
                "            if (a != null) {",
                "                a.recordFailure();",
                "            }",
                "        }",
                "        for (Account a : accounts) {",
                "            System.out.println(a);",
                "        }",
                "    }",
                "",
                "    static Account find(ArrayList<Account> list, String user) {",
                "        for (Account a : list) {",
                "            if (a.getUser().equals(user)) {",
                "                return a;",
                "            }",
                "        }",
                "        return null;",
                "    }",
                "}",
                "",
                "class Account {",
                "    private String user;",
                "    private int fails;",
                "",
                "    Account(String user) {",
                "        this.user = user;",
                "    }",
                "",
                "    String getUser() {",
                "        return user;",
                "    }",
                "",
                "    void recordFailure() {",
                "        fails++;",
                "    }",
                "",
                "    boolean isLocked() {",
                "        return fails >= 3;",
                "    }",
                "",
                "    @Override",
                "    public String toString() {",
                "        return user + \": \" + fails + (isLocked() ? \" LOCKED\" : \"\");",
                "    }",
                "}")
            .exampleOutput(
                "admin: 3 LOCKED",
                "jsmith: 1",
                "mpatel: 0")
            .lineByLine(
                new String[]{"ArrayList<Account>",
                    "A list whose items are whole accounts."},
                new String[]{"find(accounts, user)",
                    "Returns the Account object in the list - or null."},
                new String[]{"a.recordFailure();",
                    "Changes the account stored in the list, because a IS "
                    + "that object."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Box {",
                    "    int n;",
                    "}",
                    "",
                    "java.util.ArrayList<Box> list = new java.util.ArrayList<>();",
                    "list.add(new Box());",
                    "list.add(new Box());",
                    "list.get(0).n = 5;",
                    "Box b = list.get(0);",
                    "b.n++;",
                    "System.out.println(list.get(0).n + \" \" + list.get(1).n);")
                .accept("6 0")
                .hints("get returns the object itself, not a copy.",
                       "b and list.get(0) are the same Box.")
                .explain(
                    "6 0. b refers to the Box in slot 0, so b.n++ changes "
                    + "it. The Box in slot 1 was never touched.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Why is ArrayList<Account> safer than a list of names "
                    + "plus a list of counts?")
                .choices("It uses less memory",
                         "Each count travels inside its own account, so "
                         + "nothing can pair it with the wrong name",
                         "It sorts itself",
                         "Lists of objects cannot be changed")
                .accept("2", "b")
                .hints("Remember Campaign 05's warning about parallel lists.",
                       "What happens when one list is reordered?")
                .explain(
                    "b. Add, remove or reorder the list and each account "
                    + "keeps its own count - there is no second list to fall "
                    + "out of step.")
                .xp(15))
            .objective(
                "Report every locked account.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        ArrayList<Account> accounts = new ArrayList<>();",
                "        accounts.add(new Account(\"admin\", 4));",
                "        accounts.add(new Account(\"jsmith\", 1));",
                "        accounts.add(new Account(\"temp01\", 3));",
                "        // the loop header: each Account a in accounts",
                "            if (a.isLocked()) {",
                "                System.out.println(\"LOCKED: \" + a.getUser());",
                "            }",
                "        }",
                "    }",
                "}",
                "",
                "class Account {",
                "    private String user;",
                "    private int fails;",
                "",
                "    Account(String user, int fails) {",
                "        this.user = user;",
                "        this.fails = fails;",
                "    }",
                "",
                "    String getUser() {",
                "        return user;",
                "    }",
                "",
                "    boolean isLocked() {",
                "        return fails >= 3;",
                "    }",
                "}")
            .yourTask(
                "Write the enhanced for header that gives each Account in "
                + "the list, in turn, as a.")
            .mainTask(new Task(Task.WRITE,
                    "Write the loop header.")
                .accept("for (Account a : accounts) {",
                        "for(Account a : accounts) {",
                        "for (Account a: accounts) {",
                        "for (Account a : accounts){")
                .hints(
                    "for (type name : list) {",
                    "The type of each item is Account.",
                    "for (Account a : accounts) {")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        ArrayList<Account> accounts = new ArrayList<>();",
                    "        accounts.add(new Account(\"admin\", 4));",
                    "        accounts.add(new Account(\"jsmith\", 1));",
                    "        accounts.add(new Account(\"temp01\", 3));",
                    "        for (Account a : accounts) {",
                    "            if (a.isLocked()) {",
                    "                System.out.println(\"LOCKED: \" + a.getUser());",
                    "            }",
                    "        }",
                    "    }",
                    "}",
                    "",
                    "class Account {",
                    "    private String user;",
                    "    private int fails;",
                    "",
                    "    Account(String user, int fails) {",
                    "        this.user = user;",
                    "        this.fails = fails;",
                    "    }",
                    "",
                    "    String getUser() {",
                    "        return user;",
                    "    }",
                    "",
                    "    boolean isLocked() {",
                    "        return fails >= 3;",
                    "    }",
                    "}")
                .whyItWorks(
                    "a is each Account in turn - a whole object - so the "
                    + "loop can ask it isLocked() and getUser(). admin (4) "
                    + "and temp01 (3) are locked; jsmith is not.\n"
                    + "\n"
                    + "The lockout rule is the account's own method, so this "
                    + "report and every other part of the program agree on "
                    + "what 'locked' means.")
                .explain(
                    "for (Account a : accounts) { - whole objects, in turn.")
                .xp(20))
            .mistakes(
                new String[]{"Expecting get(i) to return a copy",
                    "It returns the object in the list."},
                new String[]{"contains without equals",
                    "Only finds the very same object. Write equals."},
                new String[]{"Parallel lists out of habit",
                    "One list of objects instead."})
            .cyber(
                "Collections of objects are the working memory of every "
                + "security tool: the sessions a server trusts, the alerts "
                + "waiting for triage, the hosts under watch. Getting the "
                + "reference behaviour right matters - code that 'reads' "
                + "an object from the list and changes it has changed the "
                + "real, stored record.")
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Host {",
                    "    String name;",
                    "    boolean up;",
                    "",
                    "    Host(String name, boolean up) {",
                    "        this.name = name;",
                    "        this.up = up;",
                    "    }",
                    "}",
                    "",
                    "java.util.ArrayList<Host> hs = new java.util.ArrayList<>();",
                    "hs.add(new Host(\"a\", true));",
                    "hs.add(new Host(\"b\", false));",
                    "hs.add(new Host(\"c\", false));",
                    "int down = 0;",
                    "for (Host h : hs) {",
                    "    if (!h.up) {",
                    "        down++;",
                    "    }",
                    "}",
                    "System.out.println(down + \" of \" + hs.size());")
                .accept("2 of 3")
                .hints("Count the hosts that are not up.",
                       "b and c.")
                .explain(
                    "2 of 3. The loop reads each Host's up field directly "
                    + "from the objects in the list.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "What must a class have for list.remove(someObject) to "
                    + "remove an EQUAL object, not just the same one?")
                .choices("A toString method",
                         "An equals method",
                         "A static counter",
                         "A no-argument constructor")
                .accept("2", "b")
                .hints("How do list methods compare items?",
                       "Mission 18.")
                .explain(
                    "b. remove(Object), contains and indexOf all compare "
                    + "with equals.")
                .xp(10))
            .recap(
                "    ArrayList<Account> list = new ArrayList<>();\n"
                + "    list.add(new Account(\"admin\"));\n"
                + "    for (Account a : list) { ... a.method() ... }\n"
                + "\n"
                + "Items are references: changing list.get(i) changes the "
                + "stored object. Searching needs equals or a find method.")
            .next("Next: passing objects to methods."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(21), "Handing Objects to Methods", 4)
            .brief(
                "A helper called quarantine(host) was meant to mark a host "
                + "as isolated. A second helper, reset(host), was meant to "
                + "give the caller a clean host - and did nothing at all. "
                + "Both received an object. Only one of them understood "
                + "what receiving an object means.")
            .willLearn("Objects as parameters")
            .whyUseful(
                "Most real methods take objects: check this account, "
                + "report on that host, compare two rules. Knowing what a "
                + "method can and cannot do to an object it is given "
                + "prevents both kinds of surprise.")
            .concept("Objects as parameters",
                "Passing an object passes a copy of its REFERENCE. The "
                + "parameter and the caller's variable then refer to the "
                + "same object:\n"
                + "\n"
                + "    static void quarantine(Host h) {\n"
                + "        h.setIsolated(true);     changes THE host\n"
                + "    }\n"
                + "\n"
                + "After quarantine(web1), web1 is isolated - the method "
                + "worked on the caller's own object, through the "
                + "reference.\n"
                + "\n"
                + "But assigning a NEW object to the parameter only moves "
                + "the parameter:\n"
                + "\n"
                + "    static void reset(Host h) {\n"
                + "        h = new Host(\"clean\");  h now points elsewhere\n"
                + "    }\n"
                + "\n"
                + "The caller's variable still refers to the old host. To "
                + "hand back a new object, RETURN it:\n"
                + "\n"
                + "    static Host freshCopy(Host h) {\n"
                + "        return new Host(h.getName());\n"
                + "    }\n"
                + "\n"
                + "So: through the reference, a method can CHANGE the "
                + "object; it can never REPLACE the caller's object. It is "
                + "Campaign 05's array rule, exactly.\n"
                + "\n"
                + "Methods can also take two objects and compare or combine "
                + "them - riskier(a, b), a.sameOwner(b).")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Host web1 = new Host(\"web1\");",
                "        quarantine(web1);",
                "        System.out.println(web1);",
                "        reset(web1);",
                "        System.out.println(web1);",
                "        Host copy = freshCopy(web1);",
                "        System.out.println(copy);",
                "    }",
                "",
                "    static void quarantine(Host h) {",
                "        h.setIsolated(true);",
                "    }",
                "",
                "    static void reset(Host h) {",
                "        h = new Host(\"clean\");",
                "    }",
                "",
                "    static Host freshCopy(Host h) {",
                "        return new Host(h.getName());",
                "    }",
                "}",
                "",
                "class Host {",
                "    private String name;",
                "    private boolean isolated;",
                "",
                "    Host(String name) {",
                "        this.name = name;",
                "    }",
                "",
                "    String getName() {",
                "        return name;",
                "    }",
                "",
                "    void setIsolated(boolean isolated) {",
                "        this.isolated = isolated;",
                "    }",
                "",
                "    @Override",
                "    public String toString() {",
                "        return name + (isolated ? \" [ISOLATED]\" : \"\");",
                "    }",
                "}")
            .exampleOutput(
                "web1 [ISOLATED]",
                "web1 [ISOLATED]",
                "web1")
            .lineByLine(
                new String[]{"quarantine(web1);",
                    "The method changed the caller's own host."},
                new String[]{"reset(web1);",
                    "The new Host went into the parameter only - web1 is "
                    + "unchanged."},
                new String[]{"freshCopy(web1)",
                    "Returning an object is how a method hands back a new "
                    + "one."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Acct {",
                    "    int fails;",
                    "}",
                    "",
                    "static void punish(Acct a) {",
                    "    a.fails += 3;",
                    "    a = new Acct();",
                    "    a.fails = 100;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    Acct x = new Acct();",
                    "    punish(x);",
                    "    System.out.println(x.fails);",
                    "}")
                .accept("3")
                .hints("The first line changes x's object.",
                       "After a = new Acct(), a no longer refers to it.")
                .explain(
                    "3. a.fails += 3 changed the caller's object; then a was "
                    + "pointed at a new Acct, and the 100 went there.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "A method must give the caller a brand-new Session. "
                    + "Which design works?")
                .choices("static void make(Session s) { s = new Session(); }",
                         "static Session make() { return new Session(); }",
                         "static void make() { new Session(); }",
                         "static Session make(Session s) { s = null; "
                         + "return s; }")
                .accept("2", "b")
                .hints("Assigning to a parameter does not reach the caller.",
                       "Return it.")
                .explain(
                    "b. The caller receives the new object as the return "
                    + "value. a changes only its own parameter; c throws the "
                    + "object away; d returns null.")
                .xp(15))
            .objective(
                "Write a helper that raises an alert's severity.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Alert a = new Alert(\"Port scan\", 4);",
                "        escalate(a);",
                "        System.out.println(a);",
                "    }",
                "",
                "    // the header: escalate takes an Alert and returns nothing",
                "        alert.setSeverity(alert.getSeverity() + 3);",
                "    }",
                "}",
                "",
                "class Alert {",
                "    private String rule;",
                "    private int severity;",
                "",
                "    Alert(String rule, int severity) {",
                "        this.rule = rule;",
                "        this.severity = severity;",
                "    }",
                "",
                "    int getSeverity() {",
                "        return severity;",
                "    }",
                "",
                "    void setSeverity(int severity) {",
                "        this.severity = Math.min(severity, 10);",
                "    }",
                "",
                "    @Override",
                "    public String toString() {",
                "        return rule + \" (\" + severity + \")\";",
                "    }",
                "}")
            .yourTask(
                "Write the header of a static method escalate that takes "
                + "an Alert called alert and returns nothing.")
            .mainTask(new Task(Task.WRITE,
                    "Write the method header.")
                .accept("static void escalate(Alert alert) {",
                        "static void escalate(Alert alert){",
                        "public static void escalate(Alert alert) {",
                        "private static void escalate(Alert alert) {")
                .hints(
                    "static, because main calls it without an object.",
                    "The parameter's type is Alert.",
                    "static void escalate(Alert alert) {")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Alert a = new Alert(\"Port scan\", 4);",
                    "        escalate(a);",
                    "        System.out.println(a);",
                    "    }",
                    "",
                    "    static void escalate(Alert alert) {",
                    "        alert.setSeverity(alert.getSeverity() + 3);",
                    "    }",
                    "}",
                    "",
                    "class Alert {",
                    "    private String rule;",
                    "    private int severity;",
                    "",
                    "    Alert(String rule, int severity) {",
                    "        this.rule = rule;",
                    "        this.severity = severity;",
                    "    }",
                    "",
                    "    int getSeverity() {",
                    "        return severity;",
                    "    }",
                    "",
                    "    void setSeverity(int severity) {",
                    "        this.severity = Math.min(severity, 10);",
                    "    }",
                    "",
                    "    @Override",
                    "    public String toString() {",
                    "        return rule + \" (\" + severity + \")\";",
                    "    }",
                    "}")
                .whyItWorks(
                    "escalate receives a reference to main's Alert and "
                    + "changes it through its setter, so main's a now reads "
                    + "Port scan (7). No return value is needed.\n"
                    + "\n"
                    + "The change still goes through setSeverity, so the "
                    + "class's own rule - never above 10 - applies even to "
                    + "helpers outside it.")
                .explain(
                    "static void escalate(Alert alert) { - an object in.")
                .xp(20))
            .mistakes(
                new String[]{"Replacing the parameter to 'return' an object",
                    "The caller never sees it. Return the new object."},
                new String[]{"Surprise changes to an object passed in",
                    "Say so in the method's name and comment."},
                new String[]{"Reaching into fields from the helper",
                    "Use the object's methods, so its rules apply."})
            .cyber(
                "A method handed an object can change it - so any method "
                + "you pass a security-relevant object to is trusted with "
                + "it. Passing a live Session into a logging helper that "
                + "'tidies' it, or a permissions object into a display "
                + "method, gives those methods power over it. Pass objects "
                + "whose methods only allow safe changes, and name methods "
                + "that change their arguments clearly.")
            .check(new Task(Task.CHOICE,
                    "What does a method receive when an object is passed "
                    + "to it?")
                .choices("A full copy of the object",
                         "A copy of the reference to the same object",
                         "Only the object's toString",
                         "Nothing until it calls new")
                .accept("2", "b")
                .hints("Mission 6: object variables hold references.",
                       "The reference is copied, not the object.")
                .explain(
                    "b. The parameter refers to the caller's object, so "
                    + "changes through it are seen by the caller.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Pair {",
                    "    int a;",
                    "    int b;",
                    "}",
                    "",
                    "static void swap(Pair p) {",
                    "    int t = p.a;",
                    "    p.a = p.b;",
                    "    p.b = t;",
                    "}",
                    "",
                    "public static void main(String[] args) {",
                    "    Pair p = new Pair();",
                    "    p.a = 1;",
                    "    p.b = 2;",
                    "    swap(p);",
                    "    System.out.println(p.a + \" \" + p.b);",
                    "}")
                .accept("2 1")
                .hints("swap changes the fields of the caller's Pair.",
                       "Through the reference.")
                .explain(
                    "2 1. Unlike swapping two int parameters (which cannot "
                    + "reach the caller), swapping fields of a passed object "
                    + "works.")
                .xp(10))
            .recap(
                "    method(obj)            the method can CHANGE obj\n"
                + "    param = new X();       only moves the parameter\n"
                + "    return new X();        how to hand back a new one\n"
                + "\n"
                + "Passing an object trusts the method with it.")
            .next("Next: objects that hold other objects."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(22), "Objects Inside Objects", 5)
            .brief(
                "An audit needs, for every server, the local accounts on it "
                + "and which of them are locked. A Host and an Account "
                + "class already exist. The missing piece is the "
                + "relationship: a Host HAS accounts. Give Host a field "
                + "that holds them, and methods that answer questions "
                + "about them.")
            .willLearn("Composition")
            .whyUseful(
                "Real things are built from other things: a host has "
                + "accounts, an incident has alerts, a firewall has rules. "
                + "Building classes out of other classes - composition - is "
                + "how programs model that.")
            .concept("Composition",
                "A field can hold any type - including your own classes, "
                + "and lists of them. That is COMPOSITION, the 'has-a' "
                + "relationship:\n"
                + "\n"
                + "    class Host {\n"
                + "        private String name;\n"
                + "        private ArrayList<Account> accounts =\n"
                + "                new ArrayList<>();\n"
                + "\n"
                + "        void addAccount(Account a) {\n"
                + "            accounts.add(a);\n"
                + "        }\n"
                + "\n"
                + "        int lockedCount() {\n"
                + "            int n = 0;\n"
                + "            for (Account a : accounts) {\n"
                + "                if (a.isLocked()) { n++; }\n"
                + "            }\n"
                + "            return n;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "The list is created where it is declared, with = new "
                + "ArrayList<>(), so every Host starts with an empty list - "
                + "never null.\n"
                + "\n"
                + "Each class answers questions about its own data: Host "
                + "counts and loops, and asks each Account isLocked(); "
                + "Account knows what locked means. Neither reaches into "
                + "the other's private fields.\n"
                + "\n"
                + "Keep the list private and offer operations - "
                + "addAccount, lockedCount - rather than a getter that "
                + "hands out the list itself, which would let any caller "
                + "add or remove accounts behind the Host's back.")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Host db = new Host(\"db1\");",
                "        db.addAccount(new Account(\"postgres\", 0));",
                "        db.addAccount(new Account(\"backup\", 5));",
                "        db.addAccount(new Account(\"temp01\", 3));",
                "        System.out.println(db);",
                "        System.out.println(\"Locked: \" + db.lockedCount());",
                "    }",
                "}",
                "",
                "class Host {",
                "    private String name;",
                "    private ArrayList<Account> accounts = new ArrayList<>();",
                "",
                "    Host(String name) {",
                "        this.name = name;",
                "    }",
                "",
                "    void addAccount(Account a) {",
                "        accounts.add(a);",
                "    }",
                "",
                "    int lockedCount() {",
                "        int n = 0;",
                "        for (Account a : accounts) {",
                "            if (a.isLocked()) {",
                "                n++;",
                "            }",
                "        }",
                "        return n;",
                "    }",
                "",
                "    @Override",
                "    public String toString() {",
                "        return name + \" \" + accounts;",
                "    }",
                "}",
                "",
                "class Account {",
                "    private String user;",
                "    private int fails;",
                "",
                "    Account(String user, int fails) {",
                "        this.user = user;",
                "        this.fails = fails;",
                "    }",
                "",
                "    boolean isLocked() {",
                "        return fails >= 3;",
                "    }",
                "",
                "    @Override",
                "    public String toString() {",
                "        return user;",
                "    }",
                "}")
            .exampleOutput(
                "db1 [postgres, backup, temp01]",
                "Locked: 2")
            .lineByLine(
                new String[]{"private ArrayList<Account> accounts = ...",
                    "A Host has accounts - and starts with an empty list, "
                    + "not null."},
                new String[]{"a.isLocked()",
                    "Host asks each Account; Account decides."},
                new String[]{"name + \" \" + accounts",
                    "The list prints using each Account's toString."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Rule {",
                    "    int port;",
                    "",
                    "    Rule(int port) {",
                    "        this.port = port;",
                    "    }",
                    "}",
                    "",
                    "class Firewall {",
                    "    java.util.ArrayList<Rule> rules = new java.util.ArrayList<>();",
                    "",
                    "    boolean allows(int port) {",
                    "        for (Rule r : rules) {",
                    "            if (r.port == port) {",
                    "                return true;",
                    "            }",
                    "        }",
                    "        return false;",
                    "    }",
                    "}",
                    "",
                    "Firewall fw = new Firewall();",
                    "fw.rules.add(new Rule(443));",
                    "System.out.println(fw.allows(443) + \" \" + fw.allows(22));")
                .accept("true false")
                .hints("The Firewall holds a list of Rules.",
                       "Only 443 has a rule.")
                .explain(
                    "true false. allows searches the firewall's own rules; "
                    + "no rule means no, which is deny by default.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Host has private ArrayList<Account> accounts. Which "
                    + "method is the RISKIEST to add?")
                .choices("int lockedCount()",
                         "void addAccount(Account a)",
                         "ArrayList<Account> getAccounts() returning the "
                         + "field itself",
                         "String toString()")
                .accept("3", "c")
                .hints("What could a caller do with the list itself?",
                       "Mission 6: a reference is not a copy.")
                .explain(
                    "c. Handing out the real list lets any caller add, "
                    + "remove or clear accounts without Host knowing. "
                    + "Offer operations, or return a copy.")
                .xp(20))
            .objective(
                "Give Incident a list of its alerts.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Incident inc = new Incident(\"INC-7\");",
                "        inc.add(\"Brute force\");",
                "        inc.add(\"New admin account\");",
                "        System.out.println(inc.summary());",
                "    }",
                "}",
                "",
                "class Incident {",
                "    private String id;",
                "    // alerts: a private list of Strings, created empty here",
                "",
                "    Incident(String id) {",
                "        this.id = id;",
                "    }",
                "",
                "    void add(String alert) {",
                "        alerts.add(alert);",
                "    }",
                "",
                "    String summary() {",
                "        return id + \": \" + alerts.size() + \" alerts \" + alerts;",
                "    }",
                "}")
            .yourTask(
                "Declare alerts: a private ArrayList of Strings, created "
                + "empty right where it is declared.")
            .mainTask(new Task(Task.WRITE,
                    "Write the field line.")
                .accept("private ArrayList<String> alerts = new ArrayList<>();",
                        "private ArrayList<String> alerts = new ArrayList<String>();",
                        "private ArrayList<String> alerts=new ArrayList<>();")
                .hints(
                    "A private field, whose type is a list.",
                    "= new ArrayList<>() so it is never null.",
                    "private ArrayList<String> alerts = new ArrayList<>();")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Incident inc = new Incident(\"INC-7\");",
                    "        inc.add(\"Brute force\");",
                    "        inc.add(\"New admin account\");",
                    "        System.out.println(inc.summary());",
                    "    }",
                    "}",
                    "",
                    "class Incident {",
                    "    private String id;",
                    "    private ArrayList<String> alerts = new ArrayList<>();",
                    "",
                    "    Incident(String id) {",
                    "        this.id = id;",
                    "    }",
                    "",
                    "    void add(String alert) {",
                    "        alerts.add(alert);",
                    "    }",
                    "",
                    "    String summary() {",
                    "        return id + \": \" + alerts.size() + \" alerts \" + alerts;",
                    "    }",
                    "}")
                .whyItWorks(
                    "Every Incident is made with its own empty list, so "
                    + "add has somewhere to put alerts from the very first "
                    + "call: INC-7: 2 alerts [Brute force, New admin "
                    + "account].\n"
                    + "\n"
                    + "Without = new ArrayList<>(), the field would start as "
                    + "null and the first add would throw "
                    + "NullPointerException.")
                .explain(
                    "private ArrayList<String> alerts = new ArrayList<>();")
                .xp(20))
            .mistakes(
                new String[]{"A list field left null",
                    "Create it where it is declared, or in the constructor."},
                new String[]{"Returning the private list from a getter",
                    "Callers can change it. Offer operations."},
                new String[]{"Reaching into the inner objects' fields",
                    "Ask them through their methods."})
            .cyber(
                "Composition is how security models are built: a user HAS "
                + "roles, a role HAS permissions, a host HAS open ports. The "
                + "boundaries between the classes are control points. If "
                + "Host hands out its account list, any code can add a "
                + "backdoor account without going through the checks "
                + "addAccount would make.")
            .check(new Task(Task.CHOICE,
                    "Which relationship is composition ('has-a')?")
                .choices("A Host has a list of Accounts",
                         "An int is a number",
                         "main is static",
                         "String is a class")
                .accept("1", "a")
                .hints("One object holding others.",
                       "A field whose type is another class.")
                .explain(
                    "a. Host holds Accounts in a field - it is composed of "
                    + "them.")
                .xp(10))
            .check(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "class Team {",
                    "    java.util.ArrayList<String> members = new java.util.ArrayList<>();",
                    "}",
                    "",
                    "Team a = new Team();",
                    "Team b = new Team();",
                    "a.members.add(\"ana\");",
                    "System.out.println(a.members.size() + \" \" + b.members.size());")
                .accept("1 0")
                .hints("Each Team gets its own new list.",
                       "Only a's list was added to.")
                .explain(
                    "1 0. The field's = new ArrayList<>() runs for every new "
                    + "Team, so each has a separate list.")
                .xp(10))
            .recap(
                "    class Host {\n"
                + "        private ArrayList<Account> accounts =\n"
                + "                new ArrayList<>();\n"
                + "        void addAccount(Account a) { ... }\n"
                + "    }\n"
                + "\n"
                + "Has-a. Create inner lists at once. Offer operations, not "
                + "the list itself.")
            .next("Next: objects that can never change."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(23), "Objects That Never Change", 4)
            .brief(
                "Evidence records hold a file's name, its SHA-256 hash and "
                + "when it was collected. Once recorded, none of that may "
                + "change - not by a setter, not by a bug, not by anyone. "
                + "Java can enforce that with one keyword on each field.")
            .willLearn("Immutable objects")
            .whyUseful(
                "An object that cannot change after it is made is easy to "
                + "trust, safe to share, and can never be tampered with "
                + "through a stray method call. String has worked this way "
                + "since Campaign 01.")
            .concept("Immutable objects",
                "A FINAL field must be set exactly once - in the "
                + "constructor, or where it is declared - and can never be "
                + "assigned again:\n"
                + "\n"
                + "    class Evidence {\n"
                + "        private final String file;\n"
                + "        private final String sha256;\n"
                + "\n"
                + "        Evidence(String file, String sha256) {\n"
                + "            this.file = file;\n"
                + "            this.sha256 = sha256;\n"
                + "        }\n"
                + "\n"
                + "        String getSha256() { return sha256; }\n"
                + "    }\n"
                + "\n"
                + "Any later sha256 = ... is a COMPILE error. An object "
                + "whose fields are all private and final, with no method "
                + "that changes them, is IMMUTABLE.\n"
                + "\n"
                + "To 'change' an immutable object, make a new one. That is "
                + "exactly what String does: text.toUpperCase() never "
                + "changes text; it returns a new String. Now you know "
                + "why.\n"
                + "\n"
                + "One catch: final fixes the REFERENCE, not the object it "
                + "points to. A private final ArrayList can still have "
                + "items added. For a truly immutable object, its fields "
                + "must be immutable too - or never handed out.")
            .example(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Evidence e = new Evidence(\"auth.log\",",
                "                \"9f86d081884c7d659a2feaa0c55ad015\");",
                "        System.out.println(e);",
                "        Evidence renamed = e.withFile(\"auth.log.1\");",
                "        System.out.println(renamed);",
                "        System.out.println(e);",
                "        String s = \"admin\";",
                "        s.toUpperCase();",
                "        System.out.println(s);",
                "    }",
                "}",
                "",
                "class Evidence {",
                "    private final String file;",
                "    private final String sha256;",
                "",
                "    Evidence(String file, String sha256) {",
                "        this.file = file;",
                "        this.sha256 = sha256;",
                "    }",
                "",
                "    Evidence withFile(String newFile) {",
                "        return new Evidence(newFile, sha256);",
                "    }",
                "",
                "    @Override",
                "    public String toString() {",
                "        return file + \" \" + sha256.substring(0, 8);",
                "    }",
                "}")
            .exampleOutput(
                "auth.log 9f86d081",
                "auth.log.1 9f86d081",
                "auth.log 9f86d081",
                "admin")
            .lineByLine(
                new String[]{"private final String sha256;",
                    "Set once in the constructor; never again."},
                new String[]{"withFile(...)",
                    "'Changing' an immutable object means making a new one; "
                    + "e itself is untouched."},
                new String[]{"s.toUpperCase(); then admin",
                    "String is immutable too: the result was thrown away, "
                    + "and s never changed."})
            .predict(new Task(Task.PREDICT,
                    "What does this print?")
                .code(
                    "String a = \"root\";",
                    "String b = a.replace(\"o\", \"0\");",
                    "System.out.println(a + \" \" + b);")
                .accept("root r00t")
                .hints("Strings are immutable.",
                       "replace returns a new String.")
                .explain(
                    "root r00t. replace built a new String for b; a is "
                    + "exactly as it was.")
                .xp(15))
            .practice(new Task(Task.DEBUG,
                    "Which line does not compile?")
                .code(
                    "class Token {",
                    "    private final String value;",
                    "",
                    "    Token(String value) {",
                    "        this.value = value;",
                    "    }",
                    "",
                    "    void rotate(String next) {",
                    "        value = next;",
                    "    }",
                    "}",
                    "",
                    "Token t = new Token(\"abc\");")
                .accept("9", "line 9")
                .hints("Which field is final?",
                       "Where is it assigned a second time?")
                .explain(
                    "Line 9: 'cannot assign a value to final variable "
                    + "value'. A final field is set once, in the "
                    + "constructor. To rotate a token, make a new Token.")
                .xp(20))
            .objective(
                "Make the hash impossible to change.")
            .starter(
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Evidence e = new Evidence(\"disk.img\", \"a1b2c3d4\");",
                "        System.out.println(e.getFile() + \" \" + e.getHash());",
                "    }",
                "}",
                "",
                "class Evidence {",
                "    private final String file;",
                "    // hash: a private String that can be set only once",
                "",
                "    Evidence(String file, String hash) {",
                "        this.file = file;",
                "        this.hash = hash;",
                "    }",
                "",
                "    String getFile() {",
                "        return file;",
                "    }",
                "",
                "    String getHash() {",
                "        return hash;",
                "    }",
                "}")
            .yourTask(
                "Declare hash as a private, final String field.")
            .mainTask(new Task(Task.WRITE,
                    "Write the field line.")
                .accept("private final String hash;",
                        "final private String hash;")
                .hints(
                    "Like the file field above it.",
                    "private final, then the type and name.",
                    "private final String hash;")
                .solution(
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Evidence e = new Evidence(\"disk.img\", \"a1b2c3d4\");",
                    "        System.out.println(e.getFile() + \" \" + e.getHash());",
                    "    }",
                    "}",
                    "",
                    "class Evidence {",
                    "    private final String file;",
                    "    private final String hash;",
                    "",
                    "    Evidence(String file, String hash) {",
                    "        this.file = file;",
                    "        this.hash = hash;",
                    "    }",
                    "",
                    "    String getFile() {",
                    "        return file;",
                    "    }",
                    "",
                    "    String getHash() {",
                    "        return hash;",
                    "    }",
                    "}")
                .whyItWorks(
                    "The constructor sets hash once, and from then on the "
                    + "compiler rejects any assignment to it - even from "
                    + "inside Evidence. With getters and no setters, the "
                    + "object is immutable: disk.img a1b2c3d4, for good.\n"
                    + "\n"
                    + "final also catches a class of mistakes: forget to set "
                    + "hash in the constructor, and the class does not "
                    + "compile.")
                .explain(
                    "private final String hash; - set once, never again.")
                .xp(20))
            .mistakes(
                new String[]{"A setter on a final field",
                    "Does not compile. Return a new object instead."},
                new String[]{"final list, items still changing",
                    "final fixes the reference, not the list's contents."},
                new String[]{"s.toUpperCase(); on its own",
                    "Strings never change. Keep the result."})
            .cyber(
                "Chain of custody depends on evidence that cannot change "
                + "after collection. Immutable objects give that guarantee "
                + "inside the program: once an Evidence record exists, no "
                + "code path can rewrite its hash. Immutable objects are "
                + "also safe to share between parts of a program, since "
                + "nobody can alter what another part is relying on.")
            .check(new Task(Task.CHOICE,
                    "When can a final field be assigned?")
                .choices("Any time",
                         "Once - where it is declared or in the constructor",
                         "Only in a setter",
                         "Never")
                .accept("2", "b")
                .hints("It must get a value exactly once.",
                       "Before the object is handed out.")
                .explain(
                    "b. Exactly once, while the object is being built. "
                    + "Every later assignment is a compile error.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "Why does name.trim() on its own leave name unchanged?")
                .choices("trim is broken",
                         "String is immutable: trim returns a new String",
                         "name must be final",
                         "trim only works on arrays")
                .accept("2", "b")
                .hints("Can a String ever change?",
                       "Where does the result go?")
                .explain(
                    "b. Every String method that 'changes' text returns a "
                    + "new String. name = name.trim(); keeps the result.")
                .xp(10))
            .recap(
                "    private final T field;   set once, in the constructor\n"
                + "    no setters               nothing can change it\n"
                + "    withX(...)               'change' = a new object\n"
                + "\n"
                + "String is immutable. final fixes references, not the "
                + "objects behind them.")
            .next("Next: from a written spec to a class."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(24), "From a Spec to a Class", 5)
            .brief(
                "Facilities send a one-paragraph spec for door badges: each "
                + "badge has a fixed number and owner, lists the zones it "
                + "opens, can be deactivated but never reactivated, and "
                + "must refuse every door once deactivated. Turning a "
                + "paragraph like that into a class is the skill every "
                + "later campaign relies on.")
            .willLearn("Designing a class")
            .whyUseful(
                "Most classes start as words: a ticket, a spec, a policy. A "
                + "repeatable way to turn words into fields, constructors "
                + "and methods - with the rules built in - makes the result "
                + "correct and hard to misuse.")
            .concept("Designing a class",
                "Read the spec four times, looking for four things:\n"
                + "\n"
                + "    NOUNS it HAS        fields\n"
                + "      number, owner, zones, active\n"
                + "    FIXED at creation   final fields, constructor\n"
                + "      number and owner never change\n"
                + "    VERBS it DOES       methods\n"
                + "      deactivate, canEnter(zone), addZone(zone)\n"
                + "    RULES               checks and invariants\n"
                + "      no reactivation; inactive = no door opens\n"
                + "\n"
                + "Then decide the doors:\n"
                + "\n"
                + "    - every field private\n"
                + "    - a constructor that demands what is required\n"
                + "    - getters only for what callers must read\n"
                + "    - methods for what may happen - and nothing for\n"
                + "      what may not (no reactivate method at all)\n"
                + "\n"
                + "The strongest rules are the ones you enforce by "
                + "OMISSION: if the spec says a badge can never be "
                + "reactivated, the class simply has no way to do it.\n"
                + "\n"
                + "Finally, check the defaults: a new badge should be "
                + "active with NO zones - opening nothing until zones are "
                + "granted.")
            .example(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Badge b = new Badge(4021, \"Ana Silva\");",
                "        b.addZone(\"LOBBY\");",
                "        b.addZone(\"SOC\");",
                "        boolean soc = b.canEnter(\"SOC\");",
                "        boolean dc = b.canEnter(\"DATACENTRE\");",
                "        System.out.println(soc + \" \" + dc);",
                "        b.deactivate();",
                "        System.out.println(b.canEnter(\"LOBBY\"));",
                "        System.out.println(b);",
                "    }",
                "}",
                "",
                "class Badge {",
                "    private final int number;",
                "    private final String owner;",
                "    private ArrayList<String> zones = new ArrayList<>();",
                "    private boolean active = true;",
                "",
                "    Badge(int number, String owner) {",
                "        this.number = number;",
                "        this.owner = owner;",
                "    }",
                "",
                "    void addZone(String zone) {",
                "        if (active && !zones.contains(zone)) {",
                "            zones.add(zone);",
                "        }",
                "    }",
                "",
                "    boolean canEnter(String zone) {",
                "        return active && zones.contains(zone);",
                "    }",
                "",
                "    void deactivate() {",
                "        active = false;",
                "    }",
                "",
                "    @Override",
                "    public String toString() {",
                "        String state = active ? \"\" : \" [INACTIVE]\";",
                "        return \"#\" + number + \" \" + owner + state;",
                "    }",
                "}")
            .exampleOutput(
                "true false",
                "false",
                "#4021 Ana Silva [INACTIVE]")
            .lineByLine(
                new String[]{"private final int number;",
                    "Fixed at creation: final, set by the constructor."},
                new String[]{"return active && zones.contains(zone);",
                    "Two rules in one line: active, AND granted this zone."},
                new String[]{"no reactivate()",
                    "The spec forbids it, so the class has no way to do it."})
            .predict(new Task(Task.CHOICE,
                    "Spec: 'A ticket has an ID assigned when it is opened, a "
                    + "title that can be edited, and a status that only "
                    + "moves forward.' Which field should be final?")
                .choices("title", "status", "id", "None of them")
                .accept("3", "c")
                .hints("Which one never changes after creation?",
                       "Assigned when it is opened.")
                .explain(
                    "c. The ID is fixed at creation. The title may be "
                    + "edited and the status moves - both change, so "
                    + "neither can be final.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Spec: 'An API key can be revoked, and a revoked key can "
                    + "never be used again.' How should the class enforce "
                    + "the second half?")
                .choices("A comment asking callers not to unrevoke",
                         "A setRevoked(boolean) setter",
                         "A revoke() method and no method that clears it",
                         "A public revoked field")
                .accept("3", "c")
                .hints("Enforce by omission.",
                       "What should be impossible?")
                .explain(
                    "c. revoke() only ever sets it; with no way to clear it, "
                    + "the rule holds whatever callers try.")
                .xp(20))
            .objective(
                "Write the badge's door check.")
            .starter(
                "import java.util.ArrayList;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Badge b = new Badge(77);",
                "        b.addZone(\"LAB\");",
                "        boolean lab = b.canEnter(\"LAB\");",
                "        boolean vault = b.canEnter(\"VAULT\");",
                "        System.out.println(lab + \" \" + vault);",
                "        b.deactivate();",
                "        System.out.println(b.canEnter(\"LAB\"));",
                "    }",
                "}",
                "",
                "class Badge {",
                "    private final int number;",
                "    private ArrayList<String> zones = new ArrayList<>();",
                "    private boolean active = true;",
                "",
                "    Badge(int number) {",
                "        this.number = number;",
                "    }",
                "",
                "    void addZone(String zone) {",
                "        zones.add(zone);",
                "    }",
                "",
                "    void deactivate() {",
                "        active = false;",
                "    }",
                "",
                "    boolean canEnter(String zone) {",
                "        // true only if active AND the zone was granted",
                "    }",
                "}")
            .yourTask(
                "Write the return line of canEnter: true only when the badge "
                + "is active AND its zones include the one asked for.")
            .mainTask(new Task(Task.WRITE,
                    "Write the return line.")
                .accept("return active && zones.contains(zone);",
                        "return zones.contains(zone) && active;")
                .hints(
                    "Both conditions must hold: &&.",
                    "zones.contains(zone) checks the grant.",
                    "return active && zones.contains(zone);")
                .solution(
                    "import java.util.ArrayList;",
                    "",
                    "public class Main {",
                    "    public static void main(String[] args) {",
                    "        Badge b = new Badge(77);",
                    "        b.addZone(\"LAB\");",
                    "        boolean lab = b.canEnter(\"LAB\");",
                "        boolean vault = b.canEnter(\"VAULT\");",
                "        System.out.println(lab + \" \" + vault);",
                    "        b.deactivate();",
                    "        System.out.println(b.canEnter(\"LAB\"));",
                    "    }",
                    "}",
                    "",
                    "class Badge {",
                    "    private final int number;",
                    "    private ArrayList<String> zones = new ArrayList<>();",
                    "    private boolean active = true;",
                    "",
                    "    Badge(int number) {",
                    "        this.number = number;",
                    "    }",
                    "",
                    "    void addZone(String zone) {",
                    "        zones.add(zone);",
                    "    }",
                    "",
                    "    void deactivate() {",
                    "        active = false;",
                    "    }",
                    "",
                    "    boolean canEnter(String zone) {",
                    "        return active && zones.contains(zone);",
                    "    }",
                    "}")
                .whyItWorks(
                    "The LAB was granted and the badge is active: true. The "
                    + "VAULT was never granted: false. After deactivation, "
                    + "active is false, so even the LAB is refused.\n"
                    + "\n"
                    + "Deny by default is built in twice: a new badge has no "
                    + "zones, and an inactive one opens nothing whatever its "
                    + "zones say.")
                .explain(
                    "return active && zones.contains(zone); - both must hold.")
                .xp(25))
            .mistakes(
                new String[]{"Setters for every field",
                    "Only what the spec says may change."},
                new String[]{"Rules left as comments",
                    "Build them into methods - or leave the method out."},
                new String[]{"Generous defaults",
                    "New objects should grant nothing until told to."})
            .cyber(
                "Physical access control, API keys, user accounts: each is "
                + "a class whose rules ARE the security policy. Designing "
                + "from the spec - final for what never changes, no method "
                + "for what must never happen, deny by default for what is "
                + "not granted - turns the policy into code that cannot be "
                + "argued with at runtime.")
            .check(new Task(Task.CHOICE,
                    "Which part of a spec usually becomes a METHOD?")
                .choices("A noun the thing has, like 'owner'",
                         "A verb the thing does, like 'deactivate'",
                         "A value that never changes",
                         "The name of the class")
                .accept("2", "b")
                .hints("Things it HAS are fields.",
                       "Things it DOES are methods.")
                .explain(
                    "b. Verbs become methods; nouns become fields; fixed "
                    + "values become final fields.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "A new Badge is made. What should canEnter(\"SOC\") "
                    + "return before any zone is added?")
                .choices("true, badges are trusted",
                         "false, nothing is granted yet",
                         "It should crash",
                         "true for staff only")
                .accept("2", "b")
                .hints("Deny by default.",
                       "The zones list starts empty.")
                .explain(
                    "b. A new badge opens nothing until zones are granted - "
                    + "the safe default.")
                .xp(10))
            .recap(
                "    nouns -> fields        fixed -> final\n"
                + "    verbs -> methods       rules -> checks\n"
                + "    forbidden -> no method at all\n"
                + "    defaults -> grant nothing\n"
                + "\n"
                + "Private fields; only the doors the spec needs.")
            .next("Next: how real projects split classes into files."));

        // ---------------------------------------------------------------
        c.add(new Mission(c.missionId(25), "One Class per File", 3)
            .brief(
                "Every program so far has lived in one file, Main.java, "
                + "with extra classes after Main. Open a real Java project "
                + "and you see Account.java, Host.java, Alert.java - one "
                + "class per file. Here is how that works, and why Java "
                + "insists on it for public classes.")
            .willLearn("One class per file")
            .whyUseful(
                "Real code is organised as many files, one per class. "
                + "Knowing the rule - and how to compile and run a "
                + "multi-file program - lets you move from these exercises "
                + "to any Java project, including your module "
                + "assignments.")
            .concept("One class per file",
                "The rule: a PUBLIC class must be in a file with exactly "
                + "its name. public class Account goes in Account.java, "
                + "and nowhere else.\n"
                + "\n"
                + "That is why the game's extra classes have never said "
                + "public: a file may hold only ONE public class, and in "
                + "Main.java that is Main. Non-public classes may share a "
                + "file, which is handy for small programs - and it is how "
                + "every lab here works.\n"
                + "\n"
                + "A real project puts each class in its own file, in the "
                + "same folder:\n"
                + "\n"
                + "    Account.java     public class Account { ... }\n"
                + "    Host.java        public class Host { ... }\n"
                + "    Main.java        public class Main { ... }\n"
                + "\n"
                + "Compile them together, then run the class with main:\n"
                + "\n"
                + "    javac *.java\n"
                + "    java Main\n"
                + "\n"
                + "javac also compiles any class a file uses, so javac "
                + "Main.java often works too - but *.java is the safe "
                + "habit. Each class becomes its own .class file.\n"
                + "\n"
                + "Bigger projects group files into folders called "
                + "PACKAGES, with a package line at the top of each file - "
                + "Campaign 10. In an IDE such as IntelliJ, New > Java Class "
                + "makes the file with the right name for you.")
            .example(
                "ls",
                "Account.java  Host.java  Main.java",
                "javac *.java",
                "ls *.class",
                "Account.class  Host.class  Main.class",
                "java Main",
                "db1 [postgres, backup, temp01]")
            .exampleOutput(
                "(the terminal session above: three files in, three "
                + "classes out)")
            .lineByLine(
                new String[]{"Account.java  Host.java  Main.java",
                    "One public class per file, each named after its class."},
                new String[]{"javac *.java",
                    "Compiles every file in the folder together."},
                new String[]{"java Main",
                    "Runs the class that has main - no .java, no .class."})
            .predict(new Task(Task.CHOICE,
                    "A file contains public class Alert. What must the file "
                    + "be called?")
                .choices("alert.java", "Alert.java", "Main.java",
                         "Anything ending in .java")
                .accept("2", "b")
                .hints("Exactly the class name.",
                       "Capital letter included.")
                .explain(
                    "b. Alert.java - the name must match exactly, including "
                    + "case.")
                .xp(15))
            .practice(new Task(Task.CHOICE,
                    "Why do the extra classes in the game's Main.java NOT "
                    + "say public?")
                .choices("public is not allowed on classes",
                         "A file may hold only one public class - Main",
                         "They would run faster",
                         "private classes are safer")
                .accept("2", "b")
                .hints("Main.java already has a public class.",
                       "One per file.")
                .explain(
                    "b. Main is the public class of Main.java, so the "
                    + "others must be non-public - or live in their own "
                    + "files.")
                .xp(15))
            .objective(
                "Start the file for the Account class.")
            .starter(
                "// Account.java",
                "",
                "// the class line for a public class Account",
                "    private String user;",
                "",
                "    public Account(String user) {",
                "        this.user = user;",
                "    }",
                "",
                "    public String getUser() {",
                "        return user;",
                "    }",
                "}")
            .yourTask(
                "This is the whole of Account.java. Write its first line: "
                + "the declaration of a public class called Account.")
            .mainTask(new Task(Task.WRITE,
                    "Write the class line.")
                .accept("public class Account {",
                        "public class Account{")
                .hints(
                    "It is the only class in its own file.",
                    "So it can - and should - be public.",
                    "public class Account {")
                .solution(
                    "// Account.java",
                    "",
                    "public class Account {",
                    "    private String user;",
                    "",
                    "    public Account(String user) {",
                    "        this.user = user;",
                    "    }",
                    "",
                    "    public String getUser() {",
                    "        return user;",
                    "    }",
                    "}")
                .whyItWorks(
                    "In its own file, Account can be public, and the file's "
                    + "name, Account.java, matches it exactly. Its "
                    + "constructor and getter are marked public too, so "
                    + "classes in other packages can use them later.\n"
                    + "\n"
                    + "Main.java then just uses new Account(\"admin\") as "
                    + "before; javac *.java compiles both files together.")
                .explain(
                    "public class Account { - in Account.java, by itself.")
                .xp(15))
            .mistakes(
                new String[]{"public class Account in Main.java",
                    "'should be declared in a file named Account.java'."},
                new String[]{"account.java for class Account",
                    "The case must match exactly."},
                new String[]{"java Main.class",
                    "Run with the class name: java Main."})
            .cyber(
                "One class per file is also good security hygiene. Changes "
                + "to Account.java are easy to find in version control and "
                + "easy to review on their own; access rules live in a file "
                + "that can be given extra scrutiny. A single giant file "
                + "hides a one-line change to a permission check among "
                + "thousands of unrelated lines.")
            .check(new Task(Task.CHOICE,
                    "A project has Main.java, Host.java and Rule.java. "
                    + "Which commands compile and run it?")
                .choices("java *.java",
                         "javac *.java, then java Main",
                         "javac Main, then java Main.java",
                         "java Host")
                .accept("2", "b")
                .hints("Compile everything, then run the class with main.",
                       "javac compiles; java runs.")
                .explain(
                    "b. javac *.java compiles all three; java Main runs the "
                    + "program from Main's main method.")
                .xp(10))
            .check(new Task(Task.CHOICE,
                    "How many public classes may one .java file hold?")
                .choices("Any number", "One at most", "Exactly two", "None")
                .accept("2", "b")
                .hints("And its name decides the file's name.",
                       "One.")
                .explain(
                    "b. At most one, named like the file. Non-public "
                    + "classes may share the file.")
                .xp(10))
            .recap(
                "    public class Account   ->   Account.java\n"
                + "    one public class per file\n"
                + "    javac *.java  then  java Main\n"
                + "\n"
                + "The game keeps extra classes non-public inside "
                + "Main.java; real projects give each its own file.")
            .next("Next: encapsulation as a security control."));
    }
}
