"""
Chapter 2 — A Trip to Objectville.

Classes and objects: a class is the description, an object is one actual thing
made from it. Methods here take no parameters and return nothing, because
parameters and return values are Chapter 4 — what matters now is that a method
can see the fields of the object it belongs to.

Security thread: LogEvent, one line of a log as an object.
"""

from .common import make_checker

LABS = {

    "ch02-lab01": {
        "chapter": 2,
        "chapter_title": "A Trip to Objectville",
        "title": "A Class and a Thing Made From It",
        "idea": "A class describes what something is like; an object is one actual one",

        "learn": (
            "How to write a class with fields, and how to make an object from "
            "it and fill those fields in."
        ),

        "matters": (
            "So far every fact in your programs has been a loose variable. Real "
            "security data does not arrive as loose facts — it arrives in "
            "records. One login attempt has a user, an address, a time and a "
            "result, and those four belong together. Keeping them together in "
            "one object is what stops you pairing the wrong username with the "
            "wrong address as soon as you have more than one of them."
        ),

        "explain": (
            "A class is a description. An object is one real thing built from "
            "that description.\n\n"
            "A form is a useful way to think about it. The blank form says what "
            "details exist: name, address, date. That is the class. A filled-in "
            "form, with one person's actual details on it, is an object. One "
            "blank form, a thousand filled-in copies.\n\n"
            "You write the class by naming the details it holds. These are "
            "called INSTANCE VARIABLES, or fields:\n\n"
            "    class Device {\n"
            "        String name;\n"
            "        int riskScore;\n"
            "    }\n\n"
            "That creates no Device. It says what a Device is like.\n\n"
            "To make one, use new:\n\n"
            "    Device laptop = new Device();\n\n"
            "Now there is one actual Device, and laptop is how you reach it.\n\n"
            "To get at its fields, use a dot:\n\n"
            "    laptop.name = \"reception-pc\";\n"
            "    System.out.println(laptop.name);\n\n"
            "Read the dot as 'belonging to'. laptop.name is the name belonging "
            "to that laptop.\n\n"
            "A field you never set is not empty in a useful way. Numbers start "
            "at 0 and text starts at null, which means 'pointing at nothing'.\n\n"
            "THE MISTAKE TO EXPECT: writing Device laptop; and then using "
            "laptop.name. You have named a Device but never made one, and Java "
            "stops you with 'variable laptop might not have been initialized'. "
            "The new is what builds the thing."
        ),

        "example": (
            "A complete program. The class goes outside main, beside it:\n\n"
            "    class Device {\n"
            "        String name;\n"
            "        int riskScore;\n"
            "    }\n"
            "\n"
            "    public class Main {\n"
            "        public static void main(String[] args) {\n"
            "            Device laptop = new Device();\n"
            "            laptop.name = \"reception-pc\";\n"
            "            laptop.riskScore = 4;\n"
            "\n"
            "            System.out.println(laptop.name + \" risk \" + laptop.riskScore);\n"
            "        }\n"
            "    }\n\n"
            "prints:\n\n"
            "    reception-pc risk 4\n\n"
            "Three things happened in order: the class said what a Device is "
            "like, new built one, and the dots filled it in and read it back."
        ),

        "recap": [
            "A class describes what something is like; it is not a thing itself.",
            "An object is one actual thing, built with new.",
            "Fields (instance variables) are the details an object holds.",
            "The dot reaches into an object: account.username means the username belonging to account.",
            "Naming a variable is not making an object — new is what builds it.",
        ],

        "tasks": [
            {
                "id": "t1",
                "role": "warm-up",
                "brief": (
                    "The class is written for you. In main, make one ScanTarget, "
                    "set its host to gateway-02, and print that host.\n\n"
                    "Expected output:\n"
                    "gateway-02"
                ),
                "starter": (
                    "class ScanTarget {\n"
                    "    String host;\n"
                    "    int openPorts;\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // make a ScanTarget, set its host, print it\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Build one with new: ScanTarget target = new ScanTarget();",
                    "Then target.host = \"gateway-02\"; and print target.host",
                ],
                "solution": '''class ScanTarget {
    String host;
    int openPorts;
}

public class Main {
    public static void main(String[] args) {
        ScanTarget target = new ScanTarget();
        target.host = "gateway-02";
        System.out.println(target.host);
    }
}''',
                "wrong": [
                    '''class ScanTarget {
    String host;
    int openPorts;
}

public class Main {
    public static void main(String[] args) {
        System.out.println("gateway-02");
    }
}''',
                ],
                "check": make_checker(
                    expected_output="gateway-02",
                    wrong_hint="I need gateway-02 printed, read back out of a ScanTarget object.",
                    requires=["new ScanTarget", ".host"],
                    requires_hint="Make a real object with new ScanTarget() and set its host field, rather than printing the text directly.",
                ),
                "explanation": (
                    "You made one actual ScanTarget and put a value in it. The "
                    "class above main was only the description — nothing existed "
                    "until new ran.\n\n"
                    "Printing \"gateway-02\" directly would have produced the same "
                    "line and taught nothing, which is why the host had to come "
                    "back out of the object."
                ),
            },

            {
                "id": "t2",
                "role": "variation",
                "brief": (
                    "This time write the class yourself.\n\n"
                    "Write a class called Account with two fields: a String "
                    "called username and an int called failedLogins.\n\n"
                    "In main, make one Account, set the username to jsmith and "
                    "failedLogins to 3, then print both on one line.\n\n"
                    "Expected output:\n"
                    "jsmith has 3 failed logins"
                ),
                "starter": (
                    "// write your Account class here\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // make one, fill it in, print it\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "The class goes above or below Main, never inside it: class Account { String username; int failedLogins; }",
                    "Build the line with +: user.username + \" has \" + user.failedLogins + \" failed logins\"",
                ],
                "solution": '''class Account {
    String username;
    int failedLogins;
}

public class Main {
    public static void main(String[] args) {
        Account user = new Account();
        user.username = "jsmith";
        user.failedLogins = 3;

        System.out.println(user.username + " has " + user.failedLogins + " failed logins");
    }
}''',
                "check": make_checker(
                    expected_output="jsmith has 3 failed logins",
                    wrong_hint="I need the line: jsmith has 3 failed logins",
                    close_hint="Close. Check the spacing, and that both values come from the object's fields.",
                    requires=["class Account", "new Account", ".username", ".failedLogins"],
                    requires_hint="I need an Account class with both fields, an object made with new, and both values read back from it.",
                ),
                "explanation": (
                    "Two facts that belong together, held in one thing. The "
                    "username and the count cannot drift apart now, because they "
                    "are the same object.\n\n"
                    "Notice the class sits beside Main, not inside it. Java has "
                    "no classes nested in methods. Both are top-level, and only "
                    "one of them is public."
                ),
            },

            {
                "id": "t3",
                "role": "apply",
                "brief": (
                    "A real log record holds more than two facts.\n\n"
                    "Write a class called LoginRecord with four fields: String "
                    "user, String ip, boolean succeeded, and int attemptNumber.\n\n"
                    "In main, make one and fill it in with: user contractor, ip "
                    "10.14.22.9, succeeded false, attemptNumber 4. Then print it "
                    "as one line.\n\n"
                    "Expected output:\n"
                    "contractor 10.14.22.9 ok=false attempt=4"
                ),
                "starter": (
                    "// write your LoginRecord class here\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // make one, fill it in, print the line\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "A boolean field holds true or false with no quotes: record.succeeded = false;",
                    "Join it all with +: record.user + \" \" + record.ip + \" ok=\" + record.succeeded + \" attempt=\" + record.attemptNumber",
                ],
                "solution": '''class LoginRecord {
    String user;
    String ip;
    boolean succeeded;
    int attemptNumber;
}

public class Main {
    public static void main(String[] args) {
        LoginRecord record = new LoginRecord();
        record.user = "contractor";
        record.ip = "10.14.22.9";
        record.succeeded = false;
        record.attemptNumber = 4;

        System.out.println(record.user + " " + record.ip + " ok=" + record.succeeded + " attempt=" + record.attemptNumber);
    }
}''',
                "check": make_checker(
                    expected_output="contractor 10.14.22.9 ok=false attempt=4",
                    wrong_hint="I need the line: contractor 10.14.22.9 ok=false attempt=4",
                    close_hint="Close. Check the single spaces and the ok= and attempt= labels.",
                    requires=["class LoginRecord", "boolean", "new LoginRecord"],
                    requires_hint="I need a LoginRecord class with all four fields, including the boolean, and an object made from it.",
                ),
                "explanation": (
                    "Four facts, one record. That is what a log line actually "
                    "is, and holding it this way means you can hand the whole "
                    "thing around as one item.\n\n"
                    "The boolean took true or false with no quotes. Written as "
                    "\"false\" it would be text that happens to spell a word, and "
                    "you could not test it. The difference starts to matter in "
                    "the next task."
                ),
            },

            {
                "id": "t4",
                "role": "combine",
                "brief": (
                    "Objects hold the facts; the if statements from Chapter 1 "
                    "judge them.\n\n"
                    "Write the whole program from an empty editor.\n\n"
                    "Write a class called Session with a String user and an int "
                    "idleMinutes. In main, make one for the user admin with 45 "
                    "idle minutes.\n\n"
                    "Print the user and the idle time, then a verdict: EXPIRED "
                    "when idleMinutes is 30 or more, otherwise ACTIVE.\n\n"
                    "Expected output:\n"
                    "admin idle 45\n"
                    "EXPIRED"
                ),
                "starter": "",
                "hints": [
                    "Write the class, then Main, then inside main build the Session and set both fields.",
                    "The test reads the field: if (session.idleMinutes >= 30) { ... } else { ... }",
                ],
                "solution": '''class Session {
    String user;
    int idleMinutes;
}

public class Main {
    public static void main(String[] args) {
        Session session = new Session();
        session.user = "admin";
        session.idleMinutes = 45;

        System.out.println(session.user + " idle " + session.idleMinutes);

        if (session.idleMinutes >= 30) {
            System.out.println("EXPIRED");
        } else {
            System.out.println("ACTIVE");
        }
    }
}''',
                "check": make_checker(
                    expected_output="admin idle 45\nEXPIRED",
                    wrong_hint="I need two lines: admin idle 45, then EXPIRED.",
                    close_hint="Close. The verdict has to come from an if testing the object's idleMinutes field.",
                    requires=["class Session", "new Session", "if (", "idleMinutes"],
                    requires_hint="I need the Session class, an object made with new, and an if that tests its idleMinutes field.",
                ),
                "explanation": (
                    "The if tested a field belonging to an object rather than a "
                    "loose variable. That is the shape of nearly all real "
                    "checks — the data is in a record and the rule looks inside "
                    "it.\n\n"
                    "A session that never expires is a genuine vulnerability: a "
                    "stolen session token stays valid for ever. The rule you "
                    "wrote is a real one, and it is three lines long.\n\n"
                    "Change 45 to 10 and the verdict changes with no other edit."
                ),
            },

            {
                "id": "t5",
                "role": "repair",
                "brief": (
                    "This program will not compile. The class is fine and main "
                    "looks reasonable, but one thing was never done.\n\n"
                    "Fix it so it prints:\n"
                    "firewall-01 is offline"
                ),
                "starter": (
                    "class Host {\n"
                    "    String name;\n"
                    "    boolean online;\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        Host host;\n"
                    "        host.name = \"firewall-01\";\n"
                    "        host.online = false;\n\n"
                    "        System.out.println(host.name + \" is offline\");\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Read the first line of main. It names a Host. Does it make one?",
                    "Host host = new Host();  — the new is what builds the object.",
                ],
                "solution": '''class Host {
    String name;
    boolean online;
}

public class Main {
    public static void main(String[] args) {
        Host host = new Host();
        host.name = "firewall-01";
        host.online = false;

        System.out.println(host.name + " is offline");
    }
}''',
                "check": make_checker(
                    expected_output="firewall-01 is offline",
                    wrong_hint="Still not compiling, or the output is wrong. The object has to be built before its fields can be set.",
                    requires=["new Host"],
                    requires_hint="The missing piece is new Host() — declaring the variable does not create the object.",
                ),
                "explanation": (
                    "Host host; created a name for a Host, not a Host. There was "
                    "nothing for the dots to reach into, and Java said the "
                    "variable might not have been initialized.\n\n"
                    "This is the difference the whole chapter turns on. The class "
                    "is a description, the variable is a label, and new is the "
                    "only thing that actually builds something.\n\n"
                    "Java catching this at compile time is a kindness. In "
                    "Chapter 11 you meet the version it cannot catch until the "
                    "program is running."
                ),
            },
        ],
    },

    "ch02-lab02": {
        "chapter": 2,
        "chapter_title": "A Trip to Objectville",
        "title": "Every Object Has Its Own",
        "idea": "Each object made from a class carries its own separate copy of the fields",

        "learn": (
            "That two objects of the same class hold their own values, and "
            "changing one does not touch the other."
        ),

        "matters": (
            "You will never analyse one account. You analyse hundreds, and each "
            "needs its own failure count. If those counts were shared, every "
            "account would look identical and your detection would be useless. "
            "Separate state per object is what makes per-user, per-host and "
            "per-address tracking possible at all."
        ),

        "explain": (
            "One class can make as many objects as you like, and each one gets "
            "its own set of fields.\n\n"
            "    Device a = new Device();\n"
            "    Device b = new Device();\n\n"
            "That is two separate Devices. Setting a.riskScore does nothing to "
            "b.riskScore. They came from the same description and they are not "
            "connected.\n\n"
            "This is worth being certain about, because it is what makes objects "
            "useful. The class is written once. The objects are however many you "
            "need, each remembering its own situation.\n\n"
            "Each new also builds a fresh object, so you can create one, fill it "
            "in, and create another without the first being disturbed.\n\n"
            "Be careful with one thing. Two variables can point at the SAME "
            "object:\n\n"
            "    Device a = new Device();\n"
            "    Device b = a;              // no new here\n"
            "    b.riskScore = 9;\n"
            "    System.out.println(a.riskScore);   // prints 9\n\n"
            "There was only ever one Device. b = a copied the way to reach it, "
            "not the thing itself. Both names lead to the same object, so a "
            "change through either is visible through both.\n\n"
            "THE MISTAKE TO EXPECT: expecting b = a to give you a second, "
            "independent copy. It does not. Only new makes a new object. This "
            "catches people constantly, and Chapter 3 is where it gets explained "
            "properly."
        ),

        "example": (
            "A complete program with two separate objects:\n\n"
            "    class Device {\n"
            "        String name;\n"
            "        int riskScore;\n"
            "    }\n"
            "\n"
            "    public class Main {\n"
            "        public static void main(String[] args) {\n"
            "            Device first = new Device();\n"
            "            first.name = \"reception-pc\";\n"
            "            first.riskScore = 2;\n"
            "\n"
            "            Device second = new Device();\n"
            "            second.name = \"server-room-nas\";\n"
            "            second.riskScore = 8;\n"
            "\n"
            "            System.out.println(first.name + \" \" + first.riskScore);\n"
            "            System.out.println(second.name + \" \" + second.riskScore);\n"
            "        }\n"
            "    }\n\n"
            "prints:\n\n"
            "    reception-pc 2\n"
            "    server-room-nas 8\n\n"
            "Two news, two objects, two sets of fields. Setting the second one's "
            "values left the first one exactly as it was."
        ),

        "recap": [
            "One class can make any number of objects.",
            "Each object has its own copy of every field.",
            "Changing one object's field does not affect another's.",
            "Only new builds a new object.",
            "b = a does not copy an object; both names then reach the same one.",
        ],

        "tasks": [
            {
                "id": "t1",
                "role": "warm-up",
                "brief": (
                    "The class is written for you. Make TWO separate Account "
                    "objects and print each one's username.\n\n"
                    "The first is jsmith, the second is contractor.\n\n"
                    "Expected output:\n"
                    "jsmith\n"
                    "contractor"
                ),
                "starter": (
                    "class Account {\n"
                    "    String username;\n"
                    "    int failedLogins;\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // make two accounts and print both usernames\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Two variables and two separate calls to new Account().",
                    "Account one = new Account(); one.username = \"jsmith\"; then the same shape again for the second.",
                ],
                "solution": '''class Account {
    String username;
    int failedLogins;
}

public class Main {
    public static void main(String[] args) {
        Account one = new Account();
        one.username = "jsmith";

        Account two = new Account();
        two.username = "contractor";

        System.out.println(one.username);
        System.out.println(two.username);
    }
}''',
                "check": make_checker(
                    expected_output="jsmith\ncontractor",
                    wrong_hint="I need two lines: jsmith, then contractor — each read out of its own object.",
                    close_hint="If both lines are the same, both variables are reaching the same object. You need new twice.",
                    requires=["new Account"],
                    requires_hint="Build two objects, each with its own new Account().",
                ),
                "explanation": (
                    "Two objects, two usernames, no interference. The second "
                    "new built something completely fresh.\n\n"
                    "Had you written Account two = one; instead, both names would "
                    "point at the same account and both lines would say "
                    "contractor — because there would only ever have been one "
                    "object to change."
                ),
            },

            {
                "id": "t2",
                "role": "variation",
                "brief": (
                    "Prove the objects are independent.\n\n"
                    "Make two Account objects. Set the first one's failedLogins "
                    "to 7. Do not set the second one's at all.\n\n"
                    "Print both counts.\n\n"
                    "Expected output:\n"
                    "7\n"
                    "0\n\n"
                    "The second prints 0 because a number field you never set "
                    "starts at 0."
                ),
                "starter": (
                    "class Account {\n"
                    "    String username;\n"
                    "    int failedLogins;\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // two accounts; set only the first one's count\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Make both objects, but only assign failedLogins on one of them.",
                    "Then print one.failedLogins and two.failedLogins on separate lines.",
                ],
                "solution": '''class Account {
    String username;
    int failedLogins;
}

public class Main {
    public static void main(String[] args) {
        Account one = new Account();
        one.failedLogins = 7;

        Account two = new Account();

        System.out.println(one.failedLogins);
        System.out.println(two.failedLogins);
    }
}''',
                "check": make_checker(
                    expected_output="7\n0",
                    wrong_hint="I need two lines: 7, then 0.",
                    close_hint="If both say 7, the two variables are reaching the same object — use new twice.",
                    requires=["new Account"],
                    requires_hint="This needs two objects built with new, with only one of them given a count.",
                ),
                "explanation": (
                    "Setting one count left the other at its starting value. The "
                    "two objects share nothing.\n\n"
                    "That 0 was not something you wrote. Number fields inside an "
                    "object begin at 0 and booleans at false, whether you ask for "
                    "it or not. Loose variables inside a method do not get that "
                    "treatment — Chapter 3 covers the difference, and it is a "
                    "genuine inconsistency in the language rather than something "
                    "you have misread."
                ),
            },

            {
                "id": "t3",
                "role": "apply",
                "brief": (
                    "Three accounts, three separate failure counts.\n\n"
                    "Write a class called Account with a String username and an "
                    "int failedLogins.\n\n"
                    "In main, make three: jsmith with 2, contractor with 9, and "
                    "backup-svc with 0. Print one line for each in that order.\n\n"
                    "Expected output:\n"
                    "jsmith 2\n"
                    "contractor 9\n"
                    "backup-svc 0"
                ),
                "starter": (
                    "// write your Account class here\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // three accounts, three lines\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "The same four lines repeated three times: new, set the name, set the count, and later print.",
                    "Give the three variables different names so you can tell them apart — a, b, c will do.",
                ],
                "solution": '''class Account {
    String username;
    int failedLogins;
}

public class Main {
    public static void main(String[] args) {
        Account a = new Account();
        a.username = "jsmith";
        a.failedLogins = 2;

        Account b = new Account();
        b.username = "contractor";
        b.failedLogins = 9;

        Account c = new Account();
        c.username = "backup-svc";
        c.failedLogins = 0;

        System.out.println(a.username + " " + a.failedLogins);
        System.out.println(b.username + " " + b.failedLogins);
        System.out.println(c.username + " " + c.failedLogins);
    }
}''',
                "check": make_checker(
                    expected_output="jsmith 2\ncontractor 9\nbackup-svc 0",
                    wrong_hint="I need three lines, one per account, in that order.",
                    close_hint="Close. Check each name is paired with its own count.",
                    requires=["class Account", "new Account"],
                    requires_hint="Three separate Account objects, each built with new.",
                ),
                "explanation": (
                    "Three records, each keeping its own count. contractor's 9 "
                    "did not leak into anyone else's.\n\n"
                    "Notice how repetitive that was: the same four lines, three "
                    "times, with three variable names to keep straight. At ten "
                    "accounts it becomes unmanageable, and at a thousand it is "
                    "impossible. Chapter 6 gives you the container that fixes "
                    "it.\n\n"
                    "Feeling that limit is the point of writing it out."
                ),
            },

            {
                "id": "t4",
                "role": "combine",
                "brief": (
                    "Several objects, judged by the rules from Chapter 1.\n\n"
                    "Write the whole program from an empty editor.\n\n"
                    "Write a class Account with a String username and an int "
                    "failedLogins. Make two: jsmith with 2 and contractor with 9.\n\n"
                    "For each one print the username, a space, and then LOCKED "
                    "when its count is 5 or more, or OK when it is not.\n\n"
                    "Expected output:\n"
                    "jsmith OK\n"
                    "contractor LOCKED"
                ),
                "starter": "",
                "hints": [
                    "Build both objects first, then do the two checks.",
                    "Each check is an if/else printing a.username + \" LOCKED\" or a.username + \" OK\"",
                ],
                "solution": '''class Account {
    String username;
    int failedLogins;
}

public class Main {
    public static void main(String[] args) {
        Account a = new Account();
        a.username = "jsmith";
        a.failedLogins = 2;

        Account b = new Account();
        b.username = "contractor";
        b.failedLogins = 9;

        if (a.failedLogins >= 5) {
            System.out.println(a.username + " LOCKED");
        } else {
            System.out.println(a.username + " OK");
        }

        if (b.failedLogins >= 5) {
            System.out.println(b.username + " LOCKED");
        } else {
            System.out.println(b.username + " OK");
        }
    }
}''',
                "check": make_checker(
                    expected_output="jsmith OK\ncontractor LOCKED",
                    wrong_hint="I need two lines, each with a username and a verdict.",
                    close_hint="Close. Check each verdict comes from testing that account's own count against 5.",
                    requires=["class Account", "new Account", "if (", "failedLogins"],
                    requires_hint="I need the class, two objects made with new, and an if testing each one's failedLogins.",
                ),
                "explanation": (
                    "The same rule ran twice against different data and gave "
                    "different answers. That is a detection rule.\n\n"
                    "Look at what you had to write, though: the same if/else "
                    "typed out twice, once per account. Two accounts, two copies. "
                    "The rule is now in two places, and if the threshold changes "
                    "you have to remember both.\n\n"
                    "Chapter 4 fixes that by letting you write the rule once and "
                    "call it by name."
                ),
            },

            {
                "id": "t5",
                "role": "repair",
                "brief": (
                    "This program should report two different hosts. It reports "
                    "the same one twice.\n\n"
                    "It compiles and runs, so there is no error message to read. "
                    "Find why both lines are the same and fix it.\n\n"
                    "Expected output:\n"
                    "web-01 is up\n"
                    "db-02 is down"
                ),
                "starter": (
                    "class Host {\n"
                    "    String name;\n"
                    "    String state;\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        Host first = new Host();\n"
                    "        first.name = \"web-01\";\n"
                    "        first.state = \"up\";\n\n"
                    "        Host second = first;\n"
                    "        second.name = \"db-02\";\n"
                    "        second.state = \"down\";\n\n"
                    "        System.out.println(first.name + \" is \" + first.state);\n"
                    "        System.out.println(second.name + \" is \" + second.state);\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Look at the line that makes the second host. Is there a new on it?",
                    "Host second = first; makes both names reach one object. It should be Host second = new Host();",
                ],
                "solution": '''class Host {
    String name;
    String state;
}

public class Main {
    public static void main(String[] args) {
        Host first = new Host();
        first.name = "web-01";
        first.state = "up";

        Host second = new Host();
        second.name = "db-02";
        second.state = "down";

        System.out.println(first.name + " is " + first.state);
        System.out.println(second.name + " is " + second.state);
    }
}''',
                "check": make_checker(
                    expected_output="web-01 is up\ndb-02 is down",
                    wrong_hint="I need two different lines: web-01 is up, then db-02 is down.",
                    close_hint="Both lines still the same? The second variable is still reaching the first object.",
                    requires=["new Host"],
                    requires_hint="The second host needs its own new Host() rather than being assigned from the first.",
                ),
                "explanation": (
                    "There was only ever one Host. Host second = first; copied "
                    "the way to reach the object, not the object, so both names "
                    "led to the same one and the second set of assignments "
                    "overwrote the first.\n\n"
                    "Nothing warned you. It compiled, it ran, and it quietly "
                    "reported the wrong thing — which is worse than a crash, "
                    "because a crash is obvious.\n\n"
                    "This is the single most important idea in Chapter 3, and you "
                    "have now been bitten by it once before it was explained. "
                    "That is deliberate."
                ),
            },
        ],
    },

    "ch02-lab03": {
        "chapter": 2,
        "chapter_title": "A Trip to Objectville",
        "title": "Objects That Do Things",
        "idea": "A method inside a class can see that object's own fields and act on them",

        "learn": (
            "How to give a class behaviour, and why a method written once can "
            "give a different answer for every object."
        ),

        "matters": (
            "In the last lab you wrote the same lock check twice, once per "
            "account, and the rule ended up in two places. Rules that live in "
            "two places drift apart, and a security rule that drifts is a hole. "
            "Putting the rule inside the class means it is written once and "
            "every account is judged by exactly the same code."
        ),

        "explain": (
            "A class can hold behaviour as well as facts. Behaviour goes in a "
            "METHOD, written inside the class next to the fields:\n\n"
            "    class Device {\n"
            "        String name;\n"
            "        int riskScore;\n"
            "\n"
            "        void report() {\n"
            "            System.out.println(name + \" scores \" + riskScore);\n"
            "        }\n"
            "    }\n\n"
            "Two things to notice.\n\n"
            "First, the method uses name and riskScore with no dot in front of "
            "them. Inside the class, a field is just there. The method belongs "
            "to an object, so it can see that object's own fields directly.\n\n"
            "Second, you call it on an object, with a dot, and empty brackets:\n\n"
            "    laptop.report();\n\n"
            "Read it the same way as a field: the report belonging to laptop. "
            "And because it reads that object's fields, calling it on a "
            "different Device prints different text. One method, written once, "
            "behaving differently for each object — because each object holds "
            "different values.\n\n"
            "The word void in front means this method hands nothing back. It "
            "does something (prints) rather than answering something. Methods "
            "that answer are Chapter 4.\n\n"
            "The empty brackets are not optional. laptop.report is not a call; "
            "laptop.report() is.\n\n"
            "THE MISTAKE TO EXPECT: writing the method outside the class, or "
            "inside main. A method belongs to a class, next to the fields it "
            "uses. Put it inside main and it will not compile, because Java has "
            "no methods inside methods."
        ),

        "example": (
            "A complete program. One method, two objects, two different "
            "results:\n\n"
            "    class Device {\n"
            "        String name;\n"
            "        int riskScore;\n"
            "\n"
            "        void report() {\n"
            "            System.out.println(name + \" scores \" + riskScore);\n"
            "        }\n"
            "    }\n"
            "\n"
            "    public class Main {\n"
            "        public static void main(String[] args) {\n"
            "            Device laptop = new Device();\n"
            "            laptop.name = \"reception-pc\";\n"
            "            laptop.riskScore = 2;\n"
            "\n"
            "            Device nas = new Device();\n"
            "            nas.name = \"server-room-nas\";\n"
            "            nas.riskScore = 8;\n"
            "\n"
            "            laptop.report();\n"
            "            nas.report();\n"
            "        }\n"
            "    }\n\n"
            "prints:\n\n"
            "    reception-pc scores 2\n"
            "    server-room-nas scores 8\n\n"
            "The report method was written once. It printed different text each "
            "time because it read the fields of whichever object it was called "
            "on."
        ),

        "recap": [
            "A method goes inside the class, beside the fields.",
            "Inside the class, fields are used by name with no dot.",
            "Call a method on an object with a dot and brackets: account.check();",
            "void means the method does something rather than answering something.",
            "One method gives different results per object, because each object holds different values.",
        ],

        "tasks": [
            {
                "id": "t1",
                "role": "warm-up",
                "brief": (
                    "The class has its fields already. Add a method called "
                    "show() that prints the username.\n\n"
                    "Then call it on the account in main.\n\n"
                    "Expected output:\n"
                    "jsmith"
                ),
                "starter": (
                    "class Account {\n"
                    "    String username;\n"
                    "    int failedLogins;\n\n"
                    "    // add your show() method here\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        Account user = new Account();\n"
                    "        user.username = \"jsmith\";\n\n"
                    "        // call show() on it\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Inside the class: void show() { System.out.println(username); }",
                    "In main, call it with a dot and empty brackets: user.show();",
                ],
                "solution": '''class Account {
    String username;
    int failedLogins;

    void show() {
        System.out.println(username);
    }
}

public class Main {
    public static void main(String[] args) {
        Account user = new Account();
        user.username = "jsmith";

        user.show();
    }
}''',
                "wrong": [
                    '''class Account {
    String username;
    int failedLogins;
}

public class Main {
    public static void main(String[] args) {
        Account user = new Account();
        user.username = "jsmith";

        System.out.println(user.username);
    }
}''',
                ],
                "check": make_checker(
                    expected_output="jsmith",
                    wrong_hint="I need jsmith printed by a show() method belonging to the Account.",
                    requires=["void show", ".show()"],
                    requires_hint="Write a show() method inside the class and call it with user.show();",
                ),
                "explanation": (
                    "The method printed username with no dot in front of it. "
                    "Inside the class the field is simply in scope — the method "
                    "belongs to the object, so it can see what that object "
                    "holds.\n\n"
                    "Printing user.username from main would have produced the "
                    "same line, and the difference matters: the class now knows "
                    "how to describe itself, and main no longer has to."
                ),
            },

            {
                "id": "t2",
                "role": "variation",
                "brief": (
                    "One method, two objects, two different results.\n\n"
                    "Add a method status() to the class that prints the username, "
                    "a space, and the failure count.\n\n"
                    "In main, make two accounts — jsmith with 2 and contractor "
                    "with 9 — and call status() on each.\n\n"
                    "Expected output:\n"
                    "jsmith 2\n"
                    "contractor 9"
                ),
                "starter": (
                    "class Account {\n"
                    "    String username;\n"
                    "    int failedLogins;\n\n"
                    "    // add your status() method here\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // two accounts, then call status() on each\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "The method body is one println joining both fields: username + \" \" + failedLogins",
                    "Write the method once. Call it twice, on two different objects.",
                ],
                "solution": '''class Account {
    String username;
    int failedLogins;

    void status() {
        System.out.println(username + " " + failedLogins);
    }
}

public class Main {
    public static void main(String[] args) {
        Account a = new Account();
        a.username = "jsmith";
        a.failedLogins = 2;

        Account b = new Account();
        b.username = "contractor";
        b.failedLogins = 9;

        a.status();
        b.status();
    }
}''',
                "check": make_checker(
                    expected_output="jsmith 2\ncontractor 9",
                    wrong_hint="I need two lines, one per account, printed by the status() method.",
                    close_hint="Close. Check both objects are built with new and that status() reads both fields.",
                    requires=["void status", ".status()"],
                    requires_hint="I need one status() method inside the class, called on each of the two objects.",
                ),
                "explanation": (
                    "You wrote the printing code once and got two different "
                    "lines out of it.\n\n"
                    "This is the first real payoff of objects. In Chapter 1 two "
                    "different outputs meant two sets of print statements. Here "
                    "the behaviour is written once and the data varies, which is "
                    "the right way round — code you write once has one place to "
                    "be wrong and one place to fix."
                ),
            },

            {
                "id": "t3",
                "role": "apply",
                "brief": (
                    "Now put a real rule inside the class.\n\n"
                    "Write a class Account with a String username and an int "
                    "failedLogins, and a method checkLock() that prints the "
                    "username, a space, and then LOCKED when failedLogins is 5 "
                    "or more, or OK when it is not.\n\n"
                    "In main, make two accounts — jsmith with 2 and contractor "
                    "with 9 — and call checkLock() on each.\n\n"
                    "Expected output:\n"
                    "jsmith OK\n"
                    "contractor LOCKED"
                ),
                "starter": (
                    "// write your Account class, with the checkLock() method\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // two accounts, then call checkLock() on each\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "The if goes INSIDE the method, and the method goes inside the class.",
                    "void checkLock() { if (failedLogins >= 5) { ... } else { ... } }",
                ],
                "solution": '''class Account {
    String username;
    int failedLogins;

    void checkLock() {
        if (failedLogins >= 5) {
            System.out.println(username + " LOCKED");
        } else {
            System.out.println(username + " OK");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Account a = new Account();
        a.username = "jsmith";
        a.failedLogins = 2;

        Account b = new Account();
        b.username = "contractor";
        b.failedLogins = 9;

        a.checkLock();
        b.checkLock();
    }
}''',
                "check": make_checker(
                    expected_output="jsmith OK\ncontractor LOCKED",
                    wrong_hint="I need two lines: jsmith OK, then contractor LOCKED.",
                    close_hint="Close. The if must be inside checkLock(), testing that object's own failedLogins.",
                    requires=["void checkLock", "if (", ".checkLock()"],
                    requires_hint="The rule has to live inside a checkLock() method in the class, called on each object.",
                ),
                "explanation": (
                    "Compare this with the same task in the last lab. There the "
                    "if/else was typed out twice, once per account. Here it is "
                    "written once and called twice.\n\n"
                    "That is the difference between a rule and a copy of a rule. "
                    "Change the threshold from 5 to 3 now and there is exactly "
                    "one line to edit, and every account is judged by the new "
                    "rule immediately. In the two-copy version you would change "
                    "one, miss the other, and have an account that locks at a "
                    "different threshold from its neighbour without anyone "
                    "noticing."
                ),
            },

            {
                "id": "t4",
                "role": "combine",
                "brief": (
                    "Everything so far, from an empty editor.\n\n"
                    "Write a class Host with a String name, an int failedLogins, "
                    "and a boolean patched.\n\n"
                    "Give it a method audit() that prints one line: the name, a "
                    "space, then ATTACK when failedLogins is 10 or more, or "
                    "UNPATCHED when it is not but patched is false, or CLEAN "
                    "otherwise.\n\n"
                    "In main make three hosts and call audit() on each:\n"
                    "  web-01 with 14 failures, patched true\n"
                    "  db-02 with 1 failure, patched false\n"
                    "  mail-03 with 0 failures, patched true\n\n"
                    "Expected output:\n"
                    "web-01 ATTACK\n"
                    "db-02 UNPATCHED\n"
                    "mail-03 CLEAN"
                ),
                "starter": "",
                "hints": [
                    "Three outcomes means an if / else if / else chain inside audit().",
                    "Test the most serious first: if (failedLogins >= 10) then else if (patched == false) then else.",
                ],
                "solution": '''class Host {
    String name;
    int failedLogins;
    boolean patched;

    void audit() {
        if (failedLogins >= 10) {
            System.out.println(name + " ATTACK");
        } else if (patched == false) {
            System.out.println(name + " UNPATCHED");
        } else {
            System.out.println(name + " CLEAN");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Host a = new Host();
        a.name = "web-01";
        a.failedLogins = 14;
        a.patched = true;

        Host b = new Host();
        b.name = "db-02";
        b.failedLogins = 1;
        b.patched = false;

        Host c = new Host();
        c.name = "mail-03";
        c.failedLogins = 0;
        c.patched = true;

        a.audit();
        b.audit();
        c.audit();
    }
}''',
                "check": make_checker(
                    expected_output="web-01 ATTACK\ndb-02 UNPATCHED\nmail-03 CLEAN",
                    wrong_hint="I need three lines, one per host.",
                    close_hint="Close. Check the order of the tests — the attack check has to come first.",
                    requires=["class Host", "void audit", "else if", ".audit()"],
                    requires_hint="I need the Host class with an audit() method using an else-if chain, called on three objects.",
                ),
                "explanation": (
                    "One rule, three hosts, three different verdicts, and the "
                    "rule exists in exactly one place.\n\n"
                    "Note that web-01 was unpatched-checked never — it had 14 "
                    "failures, so the chain stopped at the first test and never "
                    "looked at patched. That is correct: a host under active "
                    "attack is reported as under attack, whatever its patch "
                    "state.\n\n"
                    "You could write patched == false as !patched, which reads "
                    "as 'not patched'. Chapter 5 covers that."
                ),
            },

            {
                "id": "t5",
                "role": "repair",
                "brief": (
                    "Someone put the method in the wrong place, and this will "
                    "not compile.\n\n"
                    "Move it so the program works and prints:\n"
                    "firewall-01 checked"
                ),
                "starter": (
                    "class Host {\n"
                    "    String name;\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        Host h = new Host();\n"
                    "        h.name = \"firewall-01\";\n\n"
                    "        void check() {\n"
                    "            System.out.println(name + \" checked\");\n"
                    "        }\n\n"
                    "        h.check();\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "A method cannot live inside another method. Where should check() actually be?",
                    "Move the whole check() method up into the Host class, beside the name field.",
                ],
                "solution": '''class Host {
    String name;

    void check() {
        System.out.println(name + " checked");
    }
}

public class Main {
    public static void main(String[] args) {
        Host h = new Host();
        h.name = "firewall-01";

        h.check();
    }
}''',
                "check": make_checker(
                    expected_output="firewall-01 checked",
                    wrong_hint="Still not compiling. The method needs to be inside the class, not inside main.",
                    requires=["void check", ".check()"],
                    requires_hint="Keep the check() method and move it into the Host class, then call it with h.check();",
                ),
                "explanation": (
                    "Java has no methods inside methods. Every method belongs to "
                    "a class, as a sibling of the fields and the other methods.\n\n"
                    "There was a second reason it could not work where it was. "
                    "Inside main, the word name means nothing — main does not "
                    "belong to a Host and has no name to see. Once the method is "
                    "in the class, it belongs to an object, and name is that "
                    "object's own field."
                ),
            },
        ],
    },

    "ch02-lab04": {
        "chapter": 2,
        "chapter_title": "A Trip to Objectville",
        "title": "The LogEvent Class",
        "idea": "A log line is a record with fields, and the record can describe and judge itself",

        "learn": (
            "How to turn one line of a log file into an object that holds its "
            "parts and knows how to report on itself."
        ),

        "matters": (
            "This is the class the rest of the project is built on. A log line "
            "arrives as one long piece of text, and text is the worst possible "
            "shape for asking questions. You cannot count a string, compare it "
            "sensibly, or sort by its fourth field without pulling it apart "
            "every single time. Turn it into an object once, and every question "
            "after that is easy."
        ),

        "explain": (
            "Here is a real line from an authentication log:\n\n"
            "    2026-03-14T08:12:04Z  auth  FAIL  user=jsmith  ip=10.14.22.9\n\n"
            "As text, that is a single value. To ask 'how many failures did "
            "jsmith have' you would have to search inside the string, find the "
            "user part, work out where it ends, and do it again for every line. "
            "Every question means picking the line apart again.\n\n"
            "The alternative is to pick it apart ONCE and keep the pieces:\n\n"
            "    class LogEvent {\n"
            "        String timestamp;\n"
            "        String user;\n"
            "        String ip;\n"
            "        boolean failed;\n"
            "    }\n\n"
            "Now the parts are separate and each has a type. failed is a real "
            "boolean, so you can test it. Later, when counts and times arrive, "
            "they can be real numbers you can compare and add up.\n\n"
            "This is what people mean by STRUCTURED DATA. The information was "
            "always there in the text; giving it a shape is what makes it "
            "usable.\n\n"
            "Give the class methods and the record can also describe itself:\n\n"
            "    void describe() {\n"
            "        System.out.println(user + \" from \" + ip);\n"
            "    }\n\n"
            "The knowledge of how a LogEvent should look on screen now lives "
            "with the LogEvent, instead of being spread through whatever code "
            "happens to print one.\n\n"
            "THE MISTAKE TO EXPECT: storing everything as String because it "
            "arrived as text. A failure flag kept as \"false\" is a word, not a "
            "condition — you cannot test it with an if without comparing text, "
            "and comparing text has a trap in it that Chapter 3 is about to "
            "show you."
        ),

        "example": (
            "A complete program building one event and reporting on it:\n\n"
            "    class AlertEvent {\n"
            "        String rule;\n"
            "        String source;\n"
            "        boolean blocked;\n"
            "\n"
            "        void describe() {\n"
            "            System.out.println(rule + \" from \" + source);\n"
            "        }\n"
            "    }\n"
            "\n"
            "    public class Main {\n"
            "        public static void main(String[] args) {\n"
            "            AlertEvent alert = new AlertEvent();\n"
            "            alert.rule = \"port-scan\";\n"
            "            alert.source = \"10.14.22.9\";\n"
            "            alert.blocked = true;\n"
            "\n"
            "            alert.describe();\n"
            "        }\n"
            "    }\n\n"
            "prints:\n\n"
            "    port-scan from 10.14.22.9\n\n"
            "The blocked field was not printed, and that is fine. The object "
            "holds more than any one report shows."
        ),

        "recap": [
            "A log line as text is hard to question; as an object it is easy.",
            "Each part of the line becomes a field with its own type.",
            "A failure flag should be a boolean, not the word \"false\".",
            "A method on the class lets the record describe itself.",
            "Structuring the data once makes every later question cheaper.",
        ],

        "tasks": [
            {
                "id": "t1",
                "role": "warm-up",
                "brief": (
                    "The LogEvent class is written for you, with no methods yet.\n\n"
                    "In main, build one event from this log line:\n\n"
                    "  2026-03-14T08:12:04Z  auth  FAIL  user=jsmith  ip=10.14.22.9\n\n"
                    "Set timestamp, user and ip to the values from that line, and "
                    "failed to true. Then print the user and ip on one line.\n\n"
                    "Expected output:\n"
                    "jsmith 10.14.22.9"
                ),
                "starter": (
                    "class LogEvent {\n"
                    "    String timestamp;\n"
                    "    String user;\n"
                    "    String ip;\n"
                    "    boolean failed;\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // build one LogEvent from the line above, then print\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "LogEvent event = new LogEvent(); then set each of the four fields.",
                    "The line has FAIL on it, so failed is true — with no quotes.",
                ],
                "solution": '''class LogEvent {
    String timestamp;
    String user;
    String ip;
    boolean failed;
}

public class Main {
    public static void main(String[] args) {
        LogEvent event = new LogEvent();
        event.timestamp = "2026-03-14T08:12:04Z";
        event.user = "jsmith";
        event.ip = "10.14.22.9";
        event.failed = true;

        System.out.println(event.user + " " + event.ip);
    }
}''',
                "check": make_checker(
                    expected_output="jsmith 10.14.22.9",
                    wrong_hint="I need the line: jsmith 10.14.22.9 — both read out of the LogEvent object.",
                    requires=["new LogEvent", ".user", ".ip", ".failed"],
                    requires_hint="Build a real LogEvent with new, set all four fields including failed, and read the user and ip back out.",
                ),
                "explanation": (
                    "One line of text is now four separate, typed values. That "
                    "is the whole move.\n\n"
                    "You printed only two of them, and the other two are still "
                    "there to be used. An object holds everything the line said, "
                    "whether or not any particular report shows it."
                ),
            },

            {
                "id": "t2",
                "role": "variation",
                "brief": (
                    "Give the class the ability to describe itself.\n\n"
                    "Add a method describe() to LogEvent that prints the user, "
                    "the word from, and the ip — for example:\n\n"
                    "  jsmith from 10.14.22.9\n\n"
                    "Build the same event as before and call describe() on it.\n\n"
                    "Expected output:\n"
                    "jsmith from 10.14.22.9"
                ),
                "starter": (
                    "class LogEvent {\n"
                    "    String timestamp;\n"
                    "    String user;\n"
                    "    String ip;\n"
                    "    boolean failed;\n\n"
                    "    // add describe() here\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // build the event and call describe()\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "void describe() { System.out.println(user + \" from \" + ip); }",
                    "Then in main: event.describe();",
                ],
                "solution": '''class LogEvent {
    String timestamp;
    String user;
    String ip;
    boolean failed;

    void describe() {
        System.out.println(user + " from " + ip);
    }
}

public class Main {
    public static void main(String[] args) {
        LogEvent event = new LogEvent();
        event.user = "jsmith";
        event.ip = "10.14.22.9";
        event.failed = true;

        event.describe();
    }
}''',
                "check": make_checker(
                    expected_output="jsmith from 10.14.22.9",
                    wrong_hint="I need the line: jsmith from 10.14.22.9, printed by describe().",
                    requires=["void describe", ".describe()"],
                    requires_hint="Add a describe() method to the class and call it on the object.",
                ),
                "explanation": (
                    "The knowledge of how an event should look now lives in the "
                    "class.\n\n"
                    "That matters more than it seems. When the report format "
                    "changes — and it always does — there is one method to edit, "
                    "not every place in the program that ever printed an event."
                ),
            },

            {
                "id": "t3",
                "role": "apply",
                "brief": (
                    "A record should be able to judge itself too.\n\n"
                    "Write the LogEvent class with the four fields, a describe() "
                    "method as before, and a second method severity() that "
                    "prints SEVERITY: high when failed is true, or SEVERITY: "
                    "info when it is false.\n\n"
                    "In main, build an event for user contractor from ip "
                    "10.14.22.9 with failed set to true, then call describe() and "
                    "severity().\n\n"
                    "Expected output:\n"
                    "contractor from 10.14.22.9\n"
                    "SEVERITY: high"
                ),
                "starter": (
                    "// write the LogEvent class with describe() and severity()\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // build the event, call both methods\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "A boolean field can be tested directly: if (failed) { ... } else { ... }",
                    "Two methods inside the class, called one after the other in main.",
                ],
                "solution": '''class LogEvent {
    String timestamp;
    String user;
    String ip;
    boolean failed;

    void describe() {
        System.out.println(user + " from " + ip);
    }

    void severity() {
        if (failed) {
            System.out.println("SEVERITY: high");
        } else {
            System.out.println("SEVERITY: info");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LogEvent event = new LogEvent();
        event.user = "contractor";
        event.ip = "10.14.22.9";
        event.failed = true;

        event.describe();
        event.severity();
    }
}''',
                "check": make_checker(
                    expected_output="contractor from 10.14.22.9\nSEVERITY: high",
                    wrong_hint="I need two lines: the description, then SEVERITY: high.",
                    close_hint="Close. Check severity() tests the failed field rather than printing a fixed answer.",
                    requires=["void describe", "void severity", "failed"],
                    requires_hint="I need both methods in the class, with severity() deciding from the failed field.",
                ),
                "explanation": (
                    "Notice if (failed) with no comparison after it. A boolean "
                    "field is already a true-or-false answer, so there is nothing "
                    "to compare it to. Writing if (failed == true) works and says "
                    "the same thing twice.\n\n"
                    "The record now holds its facts, describes itself and rates "
                    "itself. Everything the rest of the program needs to know "
                    "about an event, the event can answer."
                ),
            },

            {
                "id": "t4",
                "role": "combine",
                "brief": (
                    "Three log lines, three objects, one class. From an empty "
                    "editor.\n\n"
                    "Write LogEvent with String user, String ip, boolean failed, "
                    "and a method report() printing the user, a space, the ip, a "
                    "space, and then FAIL or OK depending on the failed field.\n\n"
                    "In main, build three events and report each:\n"
                    "  jsmith     10.14.22.9   failed\n"
                    "  jsmith     10.14.22.9   failed\n"
                    "  jsmith     10.14.22.9   succeeded\n\n"
                    "Expected output:\n"
                    "jsmith 10.14.22.9 FAIL\n"
                    "jsmith 10.14.22.9 FAIL\n"
                    "jsmith 10.14.22.9 OK"
                ),
                "starter": "",
                "hints": [
                    "Three separate objects, each with its own new LogEvent().",
                    "report() holds the if: if (failed) print ... + \" FAIL\" else print ... + \" OK\"",
                ],
                "solution": '''class LogEvent {
    String user;
    String ip;
    boolean failed;

    void report() {
        if (failed) {
            System.out.println(user + " " + ip + " FAIL");
        } else {
            System.out.println(user + " " + ip + " OK");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LogEvent first = new LogEvent();
        first.user = "jsmith";
        first.ip = "10.14.22.9";
        first.failed = true;

        LogEvent second = new LogEvent();
        second.user = "jsmith";
        second.ip = "10.14.22.9";
        second.failed = true;

        LogEvent third = new LogEvent();
        third.user = "jsmith";
        third.ip = "10.14.22.9";
        third.failed = false;

        first.report();
        second.report();
        third.report();
    }
}''',
                "check": make_checker(
                    expected_output="jsmith 10.14.22.9 FAIL\njsmith 10.14.22.9 FAIL\njsmith 10.14.22.9 OK",
                    wrong_hint="I need three lines: two FAIL then one OK.",
                    close_hint="Close. Each event needs its own new LogEvent(), and the third one has failed set to false.",
                    requires=["class LogEvent", "void report", "new LogEvent"],
                    requires_hint="I need the LogEvent class with a report() method and three separate objects built with new.",
                ),
                "explanation": (
                    "Those three events are the beginning of a real log: two "
                    "failures then a success, same user, same address. A person "
                    "reading that sees someone who mistyped their password twice "
                    "and then got in.\n\n"
                    "What the program cannot do yet is notice that itself. It "
                    "prints three lines and draws no conclusion, because nothing "
                    "is counting. That is the next task."
                ),
            },

            {
                "id": "t5",
                "role": "extend",
                "brief": (
                    "Now make the program draw a conclusion, using the counting "
                    "loop from Chapter 1.\n\n"
                    "From an empty editor, write LogEvent with String user, "
                    "String ip and boolean failed, plus a report() method as "
                    "before.\n\n"
                    "In main, build four events for user jsmith from ip "
                    "10.14.22.9: the first three failed, the fourth succeeded. "
                    "Report each one.\n\n"
                    "Count the failures as you go, in an int, and afterwards "
                    "print the total and a verdict: BRUTE FORCE SUSPECTED when "
                    "the count is 3 or more, otherwise NORMAL.\n\n"
                    "Expected output:\n"
                    "jsmith 10.14.22.9 FAIL\n"
                    "jsmith 10.14.22.9 FAIL\n"
                    "jsmith 10.14.22.9 FAIL\n"
                    "jsmith 10.14.22.9 OK\n"
                    "FAILURES: 3\n"
                    "BRUTE FORCE SUSPECTED"
                ),
                "starter": "",
                "hints": [
                    "After building and reporting each event, test its failed field and add to your counter.",
                    "int failures = 0; then for each event: if (e.failed) { failures++; }",
                ],
                "solution": '''class LogEvent {
    String user;
    String ip;
    boolean failed;

    void report() {
        if (failed) {
            System.out.println(user + " " + ip + " FAIL");
        } else {
            System.out.println(user + " " + ip + " OK");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int failures = 0;

        LogEvent a = new LogEvent();
        a.user = "jsmith";
        a.ip = "10.14.22.9";
        a.failed = true;

        LogEvent b = new LogEvent();
        b.user = "jsmith";
        b.ip = "10.14.22.9";
        b.failed = true;

        LogEvent c = new LogEvent();
        c.user = "jsmith";
        c.ip = "10.14.22.9";
        c.failed = true;

        LogEvent d = new LogEvent();
        d.user = "jsmith";
        d.ip = "10.14.22.9";
        d.failed = false;

        a.report();
        if (a.failed) {
            failures++;
        }
        b.report();
        if (b.failed) {
            failures++;
        }
        c.report();
        if (c.failed) {
            failures++;
        }
        d.report();
        if (d.failed) {
            failures++;
        }

        System.out.println("FAILURES: " + failures);

        if (failures >= 3) {
            System.out.println("BRUTE FORCE SUSPECTED");
        } else {
            System.out.println("NORMAL");
        }
    }
}''',
                "check": make_checker(
                    expected_output=(
                        "jsmith 10.14.22.9 FAIL\njsmith 10.14.22.9 FAIL\n"
                        "jsmith 10.14.22.9 FAIL\njsmith 10.14.22.9 OK\n"
                        "FAILURES: 3\nBRUTE FORCE SUSPECTED"
                    ),
                    wrong_hint="I need four event lines, then the failure count, then the verdict.",
                    close_hint="Close. The count must be worked out from the events' failed fields, not typed in.",
                    requires=["class LogEvent", "new LogEvent", "failures"],
                    requires_hint="I need the LogEvent class, four objects built with new, and a counter that adds up their failed fields.",
                ),
                "explanation": (
                    "That is a working detection, end to end: structured events "
                    "in, a count, a threshold, a verdict out.\n\n"
                    "It is also obviously clumsy. Four objects means four "
                    "variable names, four near-identical blocks of setup, and "
                    "four copies of the same counting if. At forty events it "
                    "would be unwritable.\n\n"
                    "Everything from here is about removing that clumsiness "
                    "without losing what the program does. Chapter 4 lets you "
                    "hand an event to a method instead of repeating the code. "
                    "Chapter 6 lets you hold all four in one list and loop over "
                    "them. The logic you just wrote stays; only the repetition "
                    "goes."
                ),
            },
        ],
    },

    "ch02-lab05": {
        "chapter": 2,
        "chapter_title": "A Trip to Objectville",
        "title": "Putting Your Class Through Its Paces",
        "idea": "main exists to build objects and try them, especially at the values where a rule changes its mind",

        "learn": (
            "How to write a small program whose only job is to exercise a class "
            "you wrote, and which values are worth trying."
        ),

        "matters": (
            "A security rule that has only ever been tried on obvious cases has "
            "not been tested. Nobody doubts that 50 failed logins is an attack "
            "or that 0 is fine. The rule either works or fails at the value "
            "where it changes its mind — exactly 5, or exactly 30 minutes — and "
            "that is the one value people forget to try. Attackers do not forget."
        ),

        "explain": (
            "In Java, the class with main in it is often not the interesting "
            "class. The interesting class holds the fields and the rules; main "
            "exists to build objects from it and put them through their paces.\n\n"
            "    class Rule {          <- the thing being tested\n"
            "        ...\n"
            "    }\n"
            "\n"
            "    public class Main {   <- the thing doing the testing\n"
            "        public static void main(String[] args) {\n"
            "            // build objects, call methods, look at the output\n"
            "        }\n"
            "    }\n\n"
            "This is the ancestor of every testing tool you will ever use, and "
            "doing it by hand first is worth more than being handed a framework.\n\n"
            "The real skill is choosing WHICH objects to build. A rule that "
            "says 'lock at 5 or more failures' has a BOUNDARY at 5. Around it "
            "there are three values worth trying:\n\n"
            "    4    just below      should not lock\n"
            "    5    exactly on it   should lock\n"
            "    6    just above      should lock\n\n"
            "Test 0 and 50 and you learn almost nothing, because any rule that "
            "is roughly right handles those. Test 4, 5 and 6 and you find out "
            "whether the rule says >= 5 or > 5 — and those two are a different "
            "rule, off by one account.\n\n"
            "Off-by-one at a boundary is one of the most common real defects "
            "there is. A lockout that triggers one attempt late gives an "
            "attacker one more guess, every time, for ever.\n\n"
            "THE MISTAKE TO EXPECT: testing only the values you had in mind when "
            "you wrote the rule. Those are the ones it is guaranteed to pass. "
            "Pick the values that would embarrass it."
        ),

        "example": (
            "A complete program: a class with a rule, and a main that tries it "
            "on both sides of its boundary.\n\n"
            "    class Quota {\n"
            "        String label;\n"
            "        int usedGb;\n"
            "\n"
            "        void check() {\n"
            "            if (usedGb >= 100) {\n"
            "                System.out.println(label + \" OVER\");\n"
            "            } else {\n"
            "                System.out.println(label + \" UNDER\");\n"
            "            }\n"
            "        }\n"
            "    }\n"
            "\n"
            "    public class Main {\n"
            "        public static void main(String[] args) {\n"
            "            Quota below = new Quota();\n"
            "            below.label = \"below\";\n"
            "            below.usedGb = 99;\n"
            "\n"
            "            Quota exact = new Quota();\n"
            "            exact.label = \"exact\";\n"
            "            exact.usedGb = 100;\n"
            "\n"
            "            below.check();\n"
            "            exact.check();\n"
            "        }\n"
            "    }\n\n"
            "prints:\n\n"
            "    below UNDER\n"
            "    exact OVER\n\n"
            "The second one is the test that earns its keep. Had the rule said "
            "usedGb > 100, that line would have printed exact UNDER and the bug "
            "would be visible immediately."
        ),

        "recap": [
            "The class with main often exists only to exercise another class.",
            "A rule's boundary is the value where its answer changes.",
            "Test just below, exactly on, and just above the boundary.",
            "Values far from the boundary prove very little.",
            "An off-by-one in a lockout rule gives an attacker an extra guess every time.",
        ],

        "tasks": [
            {
                "id": "t1",
                "role": "warm-up",
                "brief": (
                    "The Lockout class is written and its rule is correct. Write "
                    "the main that tries it.\n\n"
                    "Build one Lockout with the label test-a and 7 failures, and "
                    "call check() on it.\n\n"
                    "Expected output:\n"
                    "test-a LOCKED"
                ),
                "starter": (
                    "class Lockout {\n"
                    "    String label;\n"
                    "    int failures;\n\n"
                    "    void check() {\n"
                    "        if (failures >= 5) {\n"
                    "            System.out.println(label + \" LOCKED\");\n"
                    "        } else {\n"
                    "            System.out.println(label + \" OPEN\");\n"
                    "        }\n"
                    "    }\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // build one Lockout and check it\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Build it with new, set both fields, then call the method.",
                    "Lockout a = new Lockout(); a.label = \"test-a\"; a.failures = 7; a.check();",
                ],
                "solution": '''class Lockout {
    String label;
    int failures;

    void check() {
        if (failures >= 5) {
            System.out.println(label + " LOCKED");
        } else {
            System.out.println(label + " OPEN");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Lockout a = new Lockout();
        a.label = "test-a";
        a.failures = 7;
        a.check();
    }
}''',
                "check": make_checker(
                    expected_output="test-a LOCKED",
                    wrong_hint="I need the line: test-a LOCKED, produced by calling check() on a Lockout.",
                    requires=["new Lockout", ".check()"],
                    requires_hint="Build a Lockout with new and call its check() method.",
                ),
                "explanation": (
                    "main did nothing but build an object and call a method. "
                    "That is its whole job here.\n\n"
                    "7 is a long way above the threshold, so this test would "
                    "pass whether the rule said >= 5 or > 5. It proves the class "
                    "runs. It does not prove the rule is right."
                ),
            },

            {
                "id": "t2",
                "role": "variation",
                "brief": (
                    "One test only ever shows you one answer. Try both.\n\n"
                    "Using the same Lockout class, build two objects and check "
                    "each: below with 2 failures, and above with 9.\n\n"
                    "Expected output:\n"
                    "below OPEN\n"
                    "above LOCKED"
                ),
                "starter": (
                    "class Lockout {\n"
                    "    String label;\n"
                    "    int failures;\n\n"
                    "    void check() {\n"
                    "        if (failures >= 5) {\n"
                    "            System.out.println(label + \" LOCKED\");\n"
                    "        } else {\n"
                    "            System.out.println(label + \" OPEN\");\n"
                    "        }\n"
                    "    }\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // build two Lockouts and check both\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Two objects, each with its own new, its own label and its own count.",
                    "Call check() on each, in the order below then above.",
                ],
                "solution": '''class Lockout {
    String label;
    int failures;

    void check() {
        if (failures >= 5) {
            System.out.println(label + " LOCKED");
        } else {
            System.out.println(label + " OPEN");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Lockout below = new Lockout();
        below.label = "below";
        below.failures = 2;

        Lockout above = new Lockout();
        above.label = "above";
        above.failures = 9;

        below.check();
        above.check();
    }
}''',
                "check": make_checker(
                    expected_output="below OPEN\nabove LOCKED",
                    wrong_hint="I need two lines: below OPEN, then above LOCKED.",
                    close_hint="Close. Each object needs its own new Lockout() and its own count.",
                    requires=["new Lockout", ".check()"],
                    requires_hint="Two separate Lockout objects, each checked.",
                ),
                "explanation": (
                    "Both branches of the rule have now run at least once. That "
                    "is better than one test, and it is still not enough.\n\n"
                    "2 and 9 sit far from the threshold. Every rule in the "
                    "neighbourhood of correct handles them — >= 5, > 5, >= 4 and "
                    "> 3 all give exactly this output. The next task goes where "
                    "the answers differ."
                ),
            },

            {
                "id": "t3",
                "role": "apply",
                "brief": (
                    "Test the boundary. The rule locks at 5 or more, so the "
                    "interesting values are 4, 5 and 6.\n\n"
                    "Using the same Lockout class, build three objects labelled "
                    "four, five and six with those counts, and check each.\n\n"
                    "Expected output:\n"
                    "four OPEN\n"
                    "five LOCKED\n"
                    "six LOCKED"
                ),
                "starter": (
                    "class Lockout {\n"
                    "    String label;\n"
                    "    int failures;\n\n"
                    "    void check() {\n"
                    "        if (failures >= 5) {\n"
                    "            System.out.println(label + \" LOCKED\");\n"
                    "        } else {\n"
                    "            System.out.println(label + \" OPEN\");\n"
                    "        }\n"
                    "    }\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        // three objects: 4, 5 and 6 failures\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "Three objects with counts 4, 5 and 6, labelled four, five and six.",
                    "Check them in that order so the output reads four, five, six.",
                ],
                "solution": '''class Lockout {
    String label;
    int failures;

    void check() {
        if (failures >= 5) {
            System.out.println(label + " LOCKED");
        } else {
            System.out.println(label + " OPEN");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Lockout four = new Lockout();
        four.label = "four";
        four.failures = 4;

        Lockout five = new Lockout();
        five.label = "five";
        five.failures = 5;

        Lockout six = new Lockout();
        six.label = "six";
        six.failures = 6;

        four.check();
        five.check();
        six.check();
    }
}''',
                "check": make_checker(
                    expected_output="four OPEN\nfive LOCKED\nsix LOCKED",
                    wrong_hint="I need three lines, for counts 4, 5 and 6.",
                    close_hint="Close. Check the counts are exactly 4, 5 and 6, and that the labels match.",
                    requires=["new Lockout", ".check()"],
                    requires_hint="Three Lockout objects with counts of 4, 5 and 6.",
                ),
                "explanation": (
                    "The middle line is the one that matters. five LOCKED is "
                    "what tells you the rule says 5 or more rather than more "
                    "than 5.\n\n"
                    "Had the rule been written failures > 5, this exact test "
                    "would have printed five OPEN, and you would have found a "
                    "real defect in three lines of testing — an account allowed "
                    "a sixth guess it should never have had.\n\n"
                    "Three values around the boundary. That is the habit worth "
                    "keeping."
                ),
            },

            {
                "id": "t4",
                "role": "combine",
                "brief": (
                    "Write both classes yourself, from an empty editor.\n\n"
                    "Write a class SessionRule with a String label and an int "
                    "idleMinutes, and a method check() that prints the label, a "
                    "space, and EXPIRED when idleMinutes is 30 or more, or "
                    "ACTIVE when it is not.\n\n"
                    "Then write main to test it at the boundary: three objects "
                    "labelled t29, t30 and t31 with idle times of 29, 30 and 31.\n\n"
                    "Expected output:\n"
                    "t29 ACTIVE\n"
                    "t30 EXPIRED\n"
                    "t31 EXPIRED"
                ),
                "starter": "",
                "hints": [
                    "The rule class first, with the if inside check(), then the Main class with the three objects.",
                    "30 or more means >= 30. Getting this exactly right is what t30 is testing.",
                ],
                "solution": '''class SessionRule {
    String label;
    int idleMinutes;

    void check() {
        if (idleMinutes >= 30) {
            System.out.println(label + " EXPIRED");
        } else {
            System.out.println(label + " ACTIVE");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SessionRule a = new SessionRule();
        a.label = "t29";
        a.idleMinutes = 29;

        SessionRule b = new SessionRule();
        b.label = "t30";
        b.idleMinutes = 30;

        SessionRule c = new SessionRule();
        c.label = "t31";
        c.idleMinutes = 31;

        a.check();
        b.check();
        c.check();
    }
}''',
                "wrong": [
                    '''class SessionRule {
    String label;
    int idleMinutes;

    void check() {
        if (idleMinutes > 30) {
            System.out.println(label + " EXPIRED");
        } else {
            System.out.println(label + " ACTIVE");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SessionRule a = new SessionRule();
        a.label = "t29";
        a.idleMinutes = 29;

        SessionRule b = new SessionRule();
        b.label = "t30";
        b.idleMinutes = 30;

        SessionRule c = new SessionRule();
        c.label = "t31";
        c.idleMinutes = 31;

        a.check();
        b.check();
        c.check();
    }
}''',
                ],
                "check": make_checker(
                    expected_output="t29 ACTIVE\nt30 EXPIRED\nt31 EXPIRED",
                    wrong_hint="I need three lines, for idle times 29, 30 and 31.",
                    close_hint="If t30 says ACTIVE, the rule is using > 30 where it should use >= 30.",
                    requires=["class SessionRule", "new SessionRule", "if (", ".check()"],
                    requires_hint="I need the SessionRule class with a check() method, and three objects tested at 29, 30 and 31.",
                ),
                "explanation": (
                    "You wrote the rule and the test for it, and the test was "
                    "aimed at the one value that could tell them apart.\n\n"
                    "A session that stays alive one minute past its limit sounds "
                    "harmless. It is not: a stolen session token is valid for "
                    "exactly as long as the rule says, and 'the rule is off by "
                    "one' is how those windows quietly grow.\n\n"
                    "Notice you chose the test values from the rule's wording — "
                    "'30 or more' — rather than from the code. That is the right "
                    "way round."
                ),
            },

            {
                "id": "t5",
                "role": "repair",
                "brief": (
                    "Here is a rule with a real off-by-one bug, and a test that "
                    "fails to catch it. The test passes happily, because it only "
                    "tries values far from the boundary.\n\n"
                    "Two things to do:\n"
                    "1. Change the three test objects to use 2, 3 and 4 failures, "
                    "labelled t2, t3 and t4.\n"
                    "2. Fix the rule so it locks at 3 or more, as its comment says.\n\n"
                    "Expected output:\n"
                    "t2 OPEN\n"
                    "t3 LOCKED\n"
                    "t4 LOCKED"
                ),
                "starter": (
                    "class Lockout {\n"
                    "    String label;\n"
                    "    int failures;\n\n"
                    "    // should lock at 3 or more failures\n"
                    "    void check() {\n"
                    "        if (failures > 3) {\n"
                    "            System.out.println(label + \" LOCKED\");\n"
                    "        } else {\n"
                    "            System.out.println(label + \" OPEN\");\n"
                    "        }\n"
                    "    }\n"
                    "}\n\n"
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        Lockout a = new Lockout();\n"
                    "        a.label = \"t0\";\n"
                    "        a.failures = 0;\n\n"
                    "        Lockout b = new Lockout();\n"
                    "        b.label = \"t20\";\n"
                    "        b.failures = 20;\n\n"
                    "        Lockout c = new Lockout();\n"
                    "        c.label = \"t50\";\n"
                    "        c.failures = 50;\n\n"
                    "        a.check();\n"
                    "        b.check();\n"
                    "        c.check();\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "0, 20 and 50 tell you nothing about a threshold of 3. Change them to 2, 3 and 4.",
                    "Once t3 is being tested, the bug shows: > 3 must become >= 3.",
                ],
                "solution": '''class Lockout {
    String label;
    int failures;

    // should lock at 3 or more failures
    void check() {
        if (failures >= 3) {
            System.out.println(label + " LOCKED");
        } else {
            System.out.println(label + " OPEN");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Lockout a = new Lockout();
        a.label = "t2";
        a.failures = 2;

        Lockout b = new Lockout();
        b.label = "t3";
        b.failures = 3;

        Lockout c = new Lockout();
        c.label = "t4";
        c.failures = 4;

        a.check();
        b.check();
        c.check();
    }
}''',
                "check": make_checker(
                    expected_output="t2 OPEN\nt3 LOCKED\nt4 LOCKED",
                    wrong_hint="I need three lines for counts 2, 3 and 4 — labelled t2, t3 and t4.",
                    close_hint="If t3 says OPEN, the rule still uses > 3. It should be >= 3.",
                    requires=[">= 3"],
                    requires_hint="The rule needs to lock at 3 or more, which is >= 3, not > 3.",
                ),
                "explanation": (
                    "The original test passed. Every line of it was correct, the "
                    "program ran, nothing was red — and the rule was wrong.\n\n"
                    "That is what a test aimed at the wrong values buys you: "
                    "confidence with no information. 0, 20 and 50 all give the "
                    "same answer under > 3 as under >= 3, so the test could not "
                    "tell the two rules apart.\n\n"
                    "The moment you tested 3 itself, the defect was obvious. An "
                    "account was getting a fourth attempt that policy said it "
                    "should never have had, and the only way to see it was to "
                    "aim at the boundary.\n\n"
                    "This is Chapter 2 finished. You can describe a thing as a "
                    "class, make many of them, give them behaviour, and check "
                    "that the behaviour is right."
                ),
            },
        ],
    },
}
