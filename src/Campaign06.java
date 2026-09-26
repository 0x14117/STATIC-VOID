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
    }
}
