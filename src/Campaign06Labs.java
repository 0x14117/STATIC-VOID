/**
 * LABS - CAMPAIGN 06 - OBJECTS
 *
 * Programs built from classes of your own. Every lab names the members its
 * classes must declare - "in Account: void recordFailure()" - and the game
 * checks them in the compiled program, so a lab about classes cannot be
 * passed with everything in main. Extra classes go in Main.java, after
 * Main's closing brace.
 */
public class Campaign06Labs {

    public static void build(Campaign c) {

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(1), "A Host Class", Lab.SMALL)
            .after("C06-M008")
            .brief(
                "The asset register wants each server as one object: a name "
                + "and a port, travelling together. Write a Host class with "
                + "two fields and two methods, then use it from main to "
                + "describe the host someone types in.")
            .practises("Classes", "Fields", "Instance methods")
            .spec(
                "Prompt Host name: and Port: and read a name (trim it) and a whole number.",
                "Make a Host object and set its name and port fields.",
                "describe() returns name:port. isWellKnown() is true when the port is below 1024.",
                "Print Host: <describe()> then Well-known port: yes or no.")
            .needsMethod("in Host: String describe()")
            .needsMethod("in Host: boolean isWellKnown()")
            .needsMethod("in Host: String name")
            .needsMethod("in Host: int port")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read the name and port, make a Host, then report",
                "    }",
                "}",
                "",
                "class Host {",
                "    // fields: name and port",
                "    // methods: describe() and isWellKnown()",
                "}")
            .hints(
                "Fields go inside class Host, outside any method: "
                + "String name; and int port;",
                "In main: Host h = new Host(); then h.name = ...; and "
                + "h.port = ...;",
                "describe() returns name + \":\" + port - inside the class, "
                + "the fields need no h. in front.",
                "isWellKnown() returns port < 1024. main turns true or false "
                + "into yes or no.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Host name: \");",
                "        String name = input.nextLine().trim();",
                "        System.out.print(\"Port: \");",
                "        int port = Integer.parseInt(input.nextLine().trim());",
                "        Host h = new Host();",
                "        h.name = name;",
                "        h.port = port;",
                "        System.out.println(\"Host: \" + h.describe());",
                "        System.out.println(\"Well-known port: \"",
                "                + (h.isWellKnown() ? \"yes\" : \"no\"));",
                "    }",
                "}",
                "",
                "class Host {",
                "    String name;",
                "    int port;",
                "",
                "    String describe() {",
                "        return name + \":\" + port;",
                "    }",
                "",
                "    boolean isWellKnown() {",
                "        return port < 1024;",
                "    }",
                "}")
            .walkthrough(
                "Host is a new type with two parts. main makes one object "
                + "with new, fills its fields with the dot, then asks the "
                + "object to describe itself. Inside describe and "
                + "isWellKnown, name and port mean THIS host's fields - the "
                + "methods work for any Host, not just this one.\n"
                + "\n"
                + "Putting the port rule in the class matters: every part "
                + "of a program that asks h.isWellKnown() gets the same "
                + "answer, and the 1024 boundary lives in one place. The "
                + "hidden tests check both sides of it: 1023 is well-known, "
                + "1024 is not.")
            .sample(Lab.typing("web1", "443"),
                "Host name: web1",
                "Port: 443",
                "Host: web1:443",
                "Well-known port: yes")
            .hidden(Lab.typing("  db1 ", "5432"),
                "Host name:   db1",
                "Port: 5432",
                "Host: db1:5432",
                "Well-known port: no")
            .hidden(Lab.typing("legacy", "1023"),
                "Host name: legacy",
                "Port: 1023",
                "Host: legacy:1023",
                "Well-known port: yes")
            .hidden(Lab.typing("app", "1024"),
                "Host name: app",
                "Port: 1024",
                "Host: app:1024",
                "Well-known port: no"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(2), "Constructors", Lab.SMALL)
            .after("C06-M013")
            .brief(
                "Alerts arrive with a rule and, sometimes, a severity. When "
                + "the severity is missing it defaults to 5. Give Alert two "
                + "constructors - one chaining to the other - so every "
                + "Alert is complete from the moment it is made.")
            .practises("Constructors", "Constructor overloading", "Constructor chaining")
            .spec(
                "Prompt Rule: and read a line (trim it). Empty: print INVALID and stop.",
                "Prompt Severity (blank for default): and read a line (trim it).",
                "Blank: make the Alert with new Alert(rule). Otherwise it must be a whole number from 1 to 10 (digits only), else INVALID; make it with new Alert(rule, severity).",
                "Alert(String) chains to Alert(String, int) with severity 5, using this(...).",
                "Print Alert: <describe()>, where describe() returns <rule> (severity <n>).")
            .needsMethod("in Alert: Alert(String)")
            .needsMethod("in Alert: Alert(String, int)")
            .needsMethod("in Alert: String describe()")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read the rule and the severity, then make the Alert",
                "    }",
                "}",
                "",
                "class Alert {",
                "    String rule;",
                "    int severity;",
                "",
                "    // two constructors, and describe()",
                "}")
            .hints(
                "Alert(String rule, int severity) does the real work: "
                + "this.rule = rule; this.severity = severity;",
                "Alert(String rule) has one line: this(rule, 5);",
                "Check the severity text is not empty and every character "
                + "is a digit before Integer.parseInt.",
                "Keep it to at most 2 digits before parsing, then check 1 "
                + "to 10.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"Rule: \");",
                "        String rule = input.nextLine().trim();",
                "        if (rule.isEmpty()) {",
                "            System.out.println(\"INVALID\");",
                "            return;",
                "        }",
                "        System.out.print(\"Severity (blank for default): \");",
                "        String text = input.nextLine().trim();",
                "        Alert a;",
                "        if (text.isEmpty()) {",
                "            a = new Alert(rule);",
                "        } else {",
                "            int s = toSeverity(text);",
                "            if (s == -1) {",
                "                System.out.println(\"INVALID\");",
                "                return;",
                "            }",
                "            a = new Alert(rule, s);",
                "        }",
                "        System.out.println(\"Alert: \" + a.describe());",
                "    }",
                "",
                "    static int toSeverity(String t) {",
                "        if (t.length() > 2) {",
                "            return -1;",
                "        }",
                "        for (int i = 0; i < t.length(); i++) {",
                "            if (!Character.isDigit(t.charAt(i))) {",
                "                return -1;",
                "            }",
                "        }",
                "        int s = Integer.parseInt(t);",
                "        return s >= 1 && s <= 10 ? s : -1;",
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
                "        this.rule = rule;",
                "        this.severity = severity;",
                "    }",
                "",
                "    String describe() {",
                "        return rule + \" (severity \" + severity + \")\";",
                "    }",
                "}")
            .walkthrough(
                "The two constructors are overloads: Java picks by the "
                + "arguments. The one-argument version does nothing itself "
                + "- this(rule, 5) hands over to the full constructor, so "
                + "storing the fields is written once. If a rule were added "
                + "there later, such as trimming or a range check, alerts "
                + "made either way would get it.\n"
                + "\n"
                + "main decides which constructor to call from what was "
                + "typed, validating the severity as text before parsing so "
                + "\"high\" or \"999999999999\" cannot crash it. By the time "
                + "any Alert exists, it has both a rule and a sensible "
                + "severity.")
            .sample(Lab.typing("Port scan", ""),
                "Rule: Port scan",
                "Severity (blank for default):",
                "Alert: Port scan (severity 5)")
            .hidden(Lab.typing("Brute force", "8"),
                "Rule: Brute force",
                "Severity (blank for default): 8",
                "Alert: Brute force (severity 8)")
            .hidden(Lab.typing("Admin created", "10"),
                "Rule: Admin created",
                "Severity (blank for default): 10",
                "Alert: Admin created (severity 10)")
            .hidden(Lab.typing("x", "0"),
                "Rule: x",
                "Severity (blank for default): 0",
                "INVALID")
            .hidden(Lab.typing("x", "high"),
                "Rule: x",
                "Severity (blank for default): high",
                "INVALID")
            .hidden(Lab.typing("   ", "5"),
                "Rule:",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(3), "Getters and Setters", Lab.SMALL)
            .after("C06-M015")
            .brief(
                "A Session holds a user and a time-out in minutes, both "
                + "private. The user is fixed when the session starts; the "
                + "time-out can be changed later. Write exactly the doors "
                + "that allows: two getters, one setter - and no way to "
                + "change the user.")
            .practises("private", "Getters and setters", "Read-only fields")
            .spec(
                "Session has private fields user (String) and minutes (int), set by a constructor Session(String, int).",
                "It offers getUser(), getMinutes() and setMinutes(int) - and no way to change the user.",
                "Prompt User: , Minutes: and New minutes: , reading a name (trim it) and two whole numbers.",
                "Make the Session with the first two values; print Before: <user> <minutes>.",
                "Call setMinutes with the third; print After: <user> <minutes>.")
            .needsMethod("in Session: private String user")
            .needsMethod("in Session: private int minutes")
            .needsMethod("in Session: Session(String, int)")
            .needsMethod("in Session: String getUser()")
            .needsMethod("in Session: int getMinutes()")
            .needsMethod("in Session: void setMinutes(int)")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        // read three values, make a Session, report before and after",
                "    }",
                "}",
                "",
                "class Session {",
                "    // private fields, a constructor, two getters, one setter",
                "}")
            .hints(
                "private String user; and private int minutes; - main can "
                + "no longer write s.user.",
                "Getters are one line each: return user; and return "
                + "minutes;",
                "setMinutes(int minutes) needs this.minutes = minutes; - "
                + "the parameter shadows the field.",
                "Leave setUser out entirely: that is what makes the user "
                + "read-only.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        System.out.print(\"User: \");",
                "        String user = input.nextLine().trim();",
                "        System.out.print(\"Minutes: \");",
                "        int first = Integer.parseInt(input.nextLine().trim());",
                "        System.out.print(\"New minutes: \");",
                "        int second = Integer.parseInt(input.nextLine().trim());",
                "        Session s = new Session(user, first);",
                "        System.out.println(\"Before: \" + s.getUser() + \" \"",
                "                + s.getMinutes());",
                "        s.setMinutes(second);",
                "        System.out.println(\"After: \" + s.getUser() + \" \"",
                "                + s.getMinutes());",
                "    }",
                "}",
                "",
                "class Session {",
                "    private String user;",
                "    private int minutes;",
                "",
                "    Session(String user, int minutes) {",
                "        this.user = user;",
                "        this.minutes = minutes;",
                "    }",
                "",
                "    String getUser() {",
                "        return user;",
                "    }",
                "",
                "    int getMinutes() {",
                "        return minutes;",
                "    }",
                "",
                "    void setMinutes(int minutes) {",
                "        this.minutes = minutes;",
                "    }",
                "}")
            .walkthrough(
                "Both fields are private, so main reaches them only through "
                + "the methods Session offers. getUser and getMinutes let it "
                + "read; setMinutes lets it change the time-out; nothing "
                + "lets it change the user, so the user is read-only after "
                + "the constructor - by omission, not by a comment asking "
                + "nicely.\n"
                + "\n"
                + "this.minutes = minutes in the setter is the mission 9 "
                + "fix: without this, the parameter would be assigned to "
                + "itself and the After line would show the old value. The "
                + "hidden test that changes 30 to 5 catches exactly that.")
            .sample(Lab.typing("jsmith", "30", "15"),
                "User: jsmith",
                "Minutes: 30",
                "New minutes: 15",
                "Before: jsmith 30",
                "After: jsmith 15")
            .hidden(Lab.typing(" admin ", "30", "5"),
                "User:  admin",
                "Minutes: 30",
                "New minutes: 5",
                "Before: admin 30",
                "After: admin 5")
            .hidden(Lab.typing("svc", "480", "480"),
                "User: svc",
                "Minutes: 480",
                "New minutes: 480",
                "Before: svc 480",
                "After: svc 480"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(4), "toString", Lab.SMALL)
            .after("C06-M019")
            .brief(
                "The service desk wants tickets that print themselves: "
                + "priority, number and title, in one readable line, "
                + "whether printed alone or as part of a list. Each ticket "
                + "gets the next number automatically from a counter the "
                + "Ticket class keeps.")
            .practises("toString", "Static and instance members", "Constructors")
            .spec(
                "Prompt Tickets (1-5): and read a whole number. Outside 1 to 5: INVALID.",
                "For each ticket i from 1, prompt Title i: and Priority i (1-3): - a title (trim it) and a whole number. A priority outside 1 to 3 is stored as 3.",
                "Ticket(String, int) takes its number from a private static counter that starts at 101.",
                "toString() returns [P<priority>] #<number> <title>.",
                "Print each ticket on its own line with println(ticket), then Queue: followed by the list of all tickets as println shows an ArrayList.")
            .needsMethod("in Ticket: Ticket(String, int)")
            .needsMethod("in Ticket: String toString()")
            .needsMethod("in Ticket: private static int nextNumber")
            .starter(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        ArrayList<Ticket> queue = new ArrayList<>();",
                "        // read the count and each ticket, then print them",
                "    }",
                "}",
                "",
                "class Ticket {",
                "    // a static counter, the fields, a constructor, toString",
                "}")
            .hints(
                "private static int nextNumber = 101; is shared by every "
                + "Ticket.",
                "In the constructor: number = nextNumber; nextNumber++;",
                "The header must be exactly public String toString() - with "
                + "@Override above it to catch typos.",
                "println(queue) calls toString on each ticket for you.")
            .solution(
                "import java.util.ArrayList;",
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        ArrayList<Ticket> queue = new ArrayList<>();",
                "        System.out.print(\"Tickets (1-5): \");",
                "        int n = Integer.parseInt(input.nextLine().trim());",
                "        if (n < 1 || n > 5) {",
                "            System.out.println(\"INVALID\");",
                "            return;",
                "        }",
                "        for (int i = 1; i <= n; i++) {",
                "            System.out.print(\"Title \" + i + \": \");",
                "            String title = input.nextLine().trim();",
                "            System.out.print(\"Priority \" + i + \" (1-3): \");",
                "            int p = Integer.parseInt(input.nextLine().trim());",
                "            queue.add(new Ticket(title, p));",
                "        }",
                "        for (Ticket t : queue) {",
                "            System.out.println(t);",
                "        }",
                "        System.out.println(\"Queue: \" + queue);",
                "    }",
                "}",
                "",
                "class Ticket {",
                "    private static int nextNumber = 101;",
                "    private final int number;",
                "    private final String title;",
                "    private final int priority;",
                "",
                "    Ticket(String title, int priority) {",
                "        this.number = nextNumber;",
                "        nextNumber++;",
                "        this.title = title;",
                "        boolean known = priority >= 1 && priority <= 3;",
                "        this.priority = known ? priority : 3;",
                "    }",
                "",
                "    @Override",
                "    public String toString() {",
                "        return \"[P\" + priority + \"] #\" + number + \" \" + title;",
                "    }",
                "}")
            .walkthrough(
                "nextNumber is static: one counter for the whole class. "
                + "Each constructor copies its current value into the "
                + "ticket's own number, then moves it on - so tickets get "
                + "101, 102, 103 whoever makes them.\n"
                + "\n"
                + "toString replaces Java's Ticket@1b6d3586 with a readable "
                + "line, and it is used automatically: println(t) calls it, "
                + "and println(queue) calls it for every ticket inside the "
                + "list's brackets. The out-of-range priority is not "
                + "refused here but stored as the lowest, 3 - a deliberate, "
                + "documented choice, so a typo never jumps the queue.")
            .sample(Lab.typing("2", "Disk full on db2", "1", "Printer jam", "3"),
                "Tickets (1-5): 2",
                "Title 1: Disk full on db2",
                "Priority 1 (1-3): 1",
                "Title 2: Printer jam",
                "Priority 2 (1-3): 3",
                "[P1] #101 Disk full on db2",
                "[P3] #102 Printer jam",
                "Queue: [[P1] #101 Disk full on db2, [P3] #102 Printer jam]")
            .hidden(Lab.typing("1", "VPN down", "7"),
                "Tickets (1-5): 1",
                "Title 1: VPN down",
                "Priority 1 (1-3): 7",
                "[P3] #101 VPN down",
                "Queue: [[P3] #101 VPN down]")
            .hidden(Lab.typing("3", "a", "2", "b", "2", "c", "1"),
                "Tickets (1-5): 3",
                "Title 1: a",
                "Priority 1 (1-3): 2",
                "Title 2: b",
                "Priority 2 (1-3): 2",
                "Title 3: c",
                "Priority 3 (1-3): 1",
                "[P2] #101 a",
                "[P2] #102 b",
                "[P1] #103 c",
                "Queue: [[P2] #101 a, [P2] #102 b, [P1] #103 c]")
            .hidden(Lab.typing("6"),
                "Tickets (1-5): 6",
                "INVALID"));

        // ---------------------------------------------------------------
        c.addLab(new Lab(c.labId(5), "Validated Setter", Lab.SMALL)
            .after("C06-M016")
            .brief(
                "A firewall Rule's port must always be from 1 to 65535. "
                + "Engineers type new ports one after another; each is "
                + "offered to the rule's setter, which refuses anything "
                + "invalid and keeps the last good value. The rule must "
                + "never hold a bad port, however it is used.")
            .practises("Validating setters", "private", "Validating text before parsing")
            .spec(
                "Rule has a private int port, starting at 443, and int getPort().",
                "setPort(int) returns false and changes nothing for a port outside 1 to 65535; otherwise it stores it and returns true.",
                "Repeatedly prompt Port: and read a line (trim it) until END.",
                "Text that is not 1 to 5 digits cannot be a port: print REFUSED <text>. Otherwise offer it to setPort: print OK <port>, or REFUSED <text>.",
                "After END, print Final port: <getPort()>.")
            .needsMethod("in Rule: private int port")
            .needsMethod("in Rule: boolean setPort(int)")
            .needsMethod("in Rule: int getPort()")
            .starter(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        Rule rule = new Rule();",
                "        // offer each typed port until END, then the final port",
                "    }",
                "}",
                "",
                "class Rule {",
                "    private int port = 443;",
                "",
                "    // getPort() and a validating setPort(int)",
                "}")
            .hints(
                "setPort checks first: if (port < 1 || port > 65535) "
                + "return false; - then this.port = port; return true;",
                "The text check belongs in main: parseInt would crash on "
                + "\"http\".",
                "At most 5 digits keeps a long number from overflowing int "
                + "before setPort sees it.",
                "A refused port leaves the rule as it was - that is the "
                + "invariant.")
            .solution(
                "import java.util.Scanner;",
                "",
                "public class Main {",
                "    public static void main(String[] args) {",
                "        Scanner input = new Scanner(System.in);",
                "        Rule rule = new Rule();",
                "        while (true) {",
                "            System.out.print(\"Port: \");",
                "            String text = input.nextLine().trim();",
                "            if (text.equals(\"END\")) {",
                "                break;",
                "            }",
                "            boolean ok = isDigits(text)",
                "                    && rule.setPort(Integer.parseInt(text));",
                "            if (ok) {",
                "                System.out.println(\"OK \" + rule.getPort());",
                "            } else {",
                "                System.out.println(\"REFUSED \" + text);",
                "            }",
                "        }",
                "        System.out.println(\"Final port: \" + rule.getPort());",
                "    }",
                "",
                "    static boolean isDigits(String s) {",
                "        if (s.isEmpty() || s.length() > 5) {",
                "            return false;",
                "        }",
                "        for (int i = 0; i < s.length(); i++) {",
                "            if (!Character.isDigit(s.charAt(i))) {",
                "                return false;",
                "            }",
                "        }",
                "        return true;",
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
            .walkthrough(
                "Two layers of checking, each in its right place. main "
                + "makes sure the TEXT can be a number before parsing it, "
                + "so input like http or 99999999999 cannot crash the "
                + "program. The Rule makes sure the NUMBER is a valid port "
                + "before storing it, so no caller - this one or any other "
                + "- can give it port 0 or 70000.\n"
                + "\n"
                + "The && means setPort is only called when isDigits passed. "
                + "A refused value changes nothing, so the final port is "
                + "always the last value that was accepted - the rule's "
                + "invariant holds from the first moment to the last.")
            .sample(Lab.typing("8443", "0", "70000", "22", "END"),
                "Port: 8443",
                "OK 8443",
                "Port: 0",
                "REFUSED 0",
                "Port: 70000",
                "REFUSED 70000",
                "Port: 22",
                "OK 22",
                "Port: END",
                "Final port: 22")
            .hidden(Lab.typing("END"),
                "Port: END",
                "Final port: 443")
            .hidden(Lab.typing("http", "-1", "65535", "65536", "1", "END"),
                "Port: http",
                "REFUSED http",
                "Port: -1",
                "REFUSED -1",
                "Port: 65535",
                "OK 65535",
                "Port: 65536",
                "REFUSED 65536",
                "Port: 1",
                "OK 1",
                "Port: END",
                "Final port: 1")
            .hidden(Lab.typing("99999999999", "", "443", "END"),
                "Port: 99999999999",
                "REFUSED 99999999999",
                "Port:",
                "REFUSED",
                "Port: 443",
                "OK 443",
                "Port: END",
                "Final port: 443"));
    }
}
