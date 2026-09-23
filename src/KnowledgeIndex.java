import java.util.ArrayList;
import java.util.List;

/**
 * The Java topics the game covers, and whether the player has met them.
 *
 * A topic counts as learned once a mission teaching it has been fully
 * completed, so this index is earned rather than declared.
 */
public class KnowledgeIndex {

    /**
     * campaign | topic | definition | why it matters in security
     *
     * A topic name must match a willLearn entry on at least one mission
     * exactly, because that is what ticks it. CheckAll enforces both
     * directions: every mission ticks at least one topic here, and once a
     * campaign is complete every topic listed for it has been taught.
     */
    private static final String[][] TOPICS = {
        {"0", "JVM",
         "The Java Virtual Machine. Runs compiled bytecode on any system.",
         "One .class file runs anywhere - for your tools and for malware."},
        {"0", "JDK",
         "The Java Development Kit: the compiler plus everything to run code.",
         "A compiler on a server is a tool an attacker can use too."},
        {"0", "main method",
         "Where a Java program starts running.",
         "Reading any program, suspicious or not, starts at its entry point."},
        {"0", "System.out.println",
         "Prints one line of text to the screen.",
         "Every tool reports its findings somehow. This is the simplest way."},
        {"0", "javac",
         "The compiler. Turns .java source into .class bytecode.",
         "Every mistake javac catches is one that never reaches production."},
        {"0", "Comments",
         "Notes in the source that Java ignores: // and /* */.",
         "Code says what. Only a comment can say why a check exists."},
        {"0", "Semicolons",
         "Mark where a statement ends. Line breaks do not.",
         "The error points at the next line. Knowing why saves the hunt."},
        {"0", "print vs println",
         "print stays on the line. println moves to the next one.",
         "Reports other tools parse must be laid out exactly."},
        {"0", "Escape sequences",
         "\\n, \\t and \\\" - characters you cannot type directly.",
         "Injected newlines and quotes are how log forgery works."},
        {"0", "Reading compiler errors",
         "File, line, message, and a caret pointing at the problem.",
         "Reading errors calmly is most of debugging."},
        {"0", "Producing exact output",
         "Output that matches character for character.",
         "One stray space breaks the tool that reads your report."},

        {"1", "Variables",
         "A named box holding one value of a fixed type.",
         "You cannot check a value you have not kept hold of."},
        {"1", "int",
         "The type that holds whole numbers.",
         "Failure counts, ports and clearance levels are all ints."},
        {"1", "String",
         "The type that holds text.",
         "Usernames, addresses and log lines are all Strings."},
        {"1", "Reassignment",
         "Putting a new value in a box that already exists.",
         "Every counter a detection rule keeps is reassigned constantly."},
        {"1", "boolean",
         "Holds true or false.",
         "Allowed or denied. Patched or not. No third answer."},
        {"1", "double",
         "The type that holds numbers with a decimal point.",
         "Severity scores stored as ints lose the part that ranks them."},
        {"1", "char",
         "Exactly one character, written in single quotes.",
         "Log levels and permission flags are single characters."},
        {"1", "Naming rules",
         "What Java allows a name to be, and what it forbids.",
         "Code you cannot read quickly is code you run on trust."},
        {"1", "final",
         "Marks a value that can never be reassigned.",
         "A threshold typed in five places will be wrong in one."},
        {"1", "Arithmetic operators",
         "+ - * / on numbers, with * and / before + and -.",
         "Risk scores are arithmetic. Wrong order, wrong priorities."},
        {"1", "Integer division",
         "int divided by int gives an int. The fraction is thrown away.",
         "Silent data loss: no crash, a believable wrong number."},
        {"1", "The remainder operator",
         "% gives what is left over after dividing.",
         "Finds what a division discarded, like unassigned alerts."},
        {"1", "Compound assignment",
         "+=, -=, *= and /= change a variable using its own value.",
         "Running totals across hosts are built this way."},
        {"1", "++",
         "Adds one to a variable. -- takes one away.",
         "The heart of every counting rule."},
        {"1", "Mixed arithmetic",
         "An int meeting a double makes the whole expression a double.",
         "Where a calculation turns decimal decides what it keeps."},
        {"1", "Casting",
         "Converting a value to another type on purpose: (int), (double).",
         "(int) cuts off, it does not round. 99.7% becomes 99."},
        {"1", "long",
         "A whole-number type with a far larger range than int.",
         "Byte counts and timestamps outgrow an int quickly."},
        {"1", "Integer overflow",
         "Going past a type's largest value wraps round to its smallest.",
         "A counter that wraps negative resets a rate limit silently."},
        {"1", "length()",
         "Asks a String how many characters it holds.",
         "A 400-character username is a probe, not a person."},
        {"1", "Strings do not change",
         "String methods hand back a new String. The old one is untouched.",
         "A sanitising call whose result is ignored sanitises nothing."},
        {"1", "trim()",
         "Removes spaces from both ends of a String.",
         "\"admin \" and \"admin\" must not become two accounts."},
        {"1", "charAt()",
         "Returns the single character at a position.",
         "Pulling a flag out of a fixed position in a log line."},
        {"1", "Indexes start at 0",
         "The first character is at 0, the last at length() - 1.",
         "Off-by-one reads are how parsers crash on hostile input."},
        {"1", "substring()",
         "Cuts out part of a String. The end position is not included.",
         "Fixed-width log parsing, and why it is fragile."},

        {"2", "if / else",
         "Runs one block or another depending on a test.",
         "Every access decision is an if at the bottom of it."},
        {"2", "if / else if / else",
         "A chain of tests, stopping at the first true one.",
         "Severity grading: critical before warning before normal."},
        {"2", "Boundary conditions",
         "The value where a rule changes its answer.",
         "Off-by-one in a lockout gives an attacker an extra guess."},
        {"2", ">= vs >",
         "Whether the threshold value itself counts.",
         "'5 or more' and 'more than 5' are different policies."},
        {"2", "Logical operators",
         "&& means both, || means either, ! means not.",
         "Translating a written policy into a condition."},
        {"2", "String comparison",
         "Comparing text with .equals(), never with ==.",
         "== on a password check is a real and common vulnerability."},
        {"2", ".equals()",
         "Asks whether two objects hold the same contents.",
         "The only correct way to compare credentials."},
        {"3", "while loop",
         "Repeats a block while its test stays true.",
         "Log files have thousands of lines. You will not read them by hand."},
        {"3", "Counters",
         "A variable that counts how many times something happened.",
         "Detection is mostly counting, then comparing to a threshold."},
        {"4", "Methods",
         "Named behaviour you write once and call by name.",
         "A rule written once cannot drift out of step with itself."},
        {"4", "void",
         "A method that does something rather than answering something.",
         "Printing a report hands nothing back."},
        {"5", "Arrays",
         "A fixed number of values of one type, under one name.",
         "A batch of log entries, read together."},
        {"5", "Array length",
         "How many items an array holds. The last position is length - 1.",
         "Reading past the end crashes the parser."},
        {"7", "Classes",
         "A description of what something is like.",
         "A log event has a shape. Describe it once."},
        {"7", "Objects",
         "One actual thing built from a class, using new.",
         "One object per log line, per account, per host."},
        {"7", "Fields",
         "The values an object holds.",
         "The parts of a log line, kept separate and typed."},
        {"7", "new",
         "Builds an object. Naming a variable does not.",
         "Two accounts need two objects, or they share one state."},
        {"8", "private",
         "A field only its own class can touch.",
         "A password nothing outside can read cannot leak by accident."},
        {"8", "Encapsulation",
         "Hiding data and exposing only what is safe to ask.",
         "A security control, not just tidiness."},
        {"9", "ArrayList",
         "A list that grows as you add to it.",
         "A watchlist does not know its final size this morning."},
        {"10", "Exceptions",
         "A failure that stops the program unless it is caught.",
         "One malformed log line should not end the overnight run."},
        {"10", "try / catch",
         "Runs risky work, and handles the failure if it comes.",
         "A parser that survives bad input keeps monitoring alive."},
    };

    /** For CheckAll: every topic, as campaign | name | definition | why. */
    public static String[][] topics() {
        return TOPICS;
    }

    public static void show(Player player) {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred("JAVA KNOWLEDGE");
        Terminal.rule('+', '=');

        int known = 0;
        int shown = 0;
        int hidden = 0;
        for (Campaign campaign : CampaignIndex.all()) {
            String number = String.valueOf(campaign.getNumber());
            if (!campaign.isBuilt()) {
                for (String[] topic : TOPICS) {
                    if (topic[0].equals(number)) {
                        hidden = hidden + 1;
                    }
                }
                continue;
            }
            Terminal.blank();
            Terminal.line("  " + campaign.getLabel());
            for (String[] topic : TOPICS) {
                if (!topic[0].equals(number)) {
                    continue;
                }
                boolean learned = player.hasLearned(topic[1]);
                if (learned) {
                    known = known + 1;
                }
                shown = shown + 1;
                Terminal.line("    [" + (learned ? "x" : " ") + "] " + topic[1]);
            }
        }

        Terminal.blank();
        Terminal.line("  " + known + " of " + shown + " topics learned.");
        if (hidden > 0) {
            Terminal.line("  " + hidden + " more arrive with campaigns not yet built.");
        }
        Terminal.blank();
        Terminal.wrapped("A topic is ticked once you complete a mission that "
                + "teaches it.", "  ");
        Terminal.blank();

        String choice = Terminal.ask("  Type a topic name to read about it, or ENTER to go back: ");
        if (choice.isEmpty()) {
            return;
        }
        showTopic(choice);
    }

    private static void showTopic(String name) {
        for (String[] topic : TOPICS) {
            if (topic[1].equalsIgnoreCase(name.trim())) {
                Terminal.newScreen();
                Terminal.heading(topic[1].toUpperCase());
                Terminal.blank();
                Terminal.wrapped("WHAT IT IS", "  ");
                Terminal.wrapped(topic[2], "    ");
                Terminal.blank();
                Terminal.wrapped("WHY IT MATTERS IN SECURITY", "  ");
                Terminal.wrapped(topic[3], "    ");
                Terminal.blank();
                Terminal.wrapped("TAUGHT IN", "  ");
                for (String missionId : missionsTeaching(topic[1])) {
                    Terminal.line("    " + missionId);
                }
                Terminal.blank();
                Terminal.pause();
                return;
            }
        }
        Terminal.line("  No topic by that name.");
        Terminal.pause();
    }

    private static List<String> missionsTeaching(String topic) {
        List<String> found = new ArrayList<>();
        for (Mission mission : CampaignIndex.allMissions()) {
            for (String concept : mission.getWillLearn()) {
                if (concept.equalsIgnoreCase(topic)) {
                    found.add(mission.getId() + "  " + mission.getTitle());
                }
            }
        }
        if (found.isEmpty()) {
            found.add("(not yet covered by a mission)");
        }
        return found;
    }
}
