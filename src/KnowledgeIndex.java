import java.util.ArrayList;
import java.util.List;

/**
 * The Java topics the game covers, and whether the player has met them.
 *
 * A topic counts as learned once a mission teaching it has been fully
 * completed, so this index is earned rather than declared.
 */
public class KnowledgeIndex {

    /** topic | definition | why it matters in security */
    private static final String[][] TOPICS = {
        {"System.out.println",
         "Prints one line of text to the screen.",
         "Every tool reports its findings somehow. This is the simplest way."},
        {"String literals",
         "Text written directly in your code, inside double quotes.",
         "Banners, usernames, log fields - most security data arrives as text."},
        {"Variables",
         "A named box holding one value of a fixed type.",
         "You cannot check a password you have not kept hold of."},
        {"String",
         "The type that holds text.",
         "Usernames, addresses and log lines are all Strings."},
        {"int",
         "The type that holds whole numbers.",
         "Failure counts, ports and clearance levels are all ints."},
        {"if / else",
         "Runs one block or another depending on a test.",
         "Every access decision is an if at the bottom of it."},
        {"String comparison",
         "Comparing text with .equals(), never with ==.",
         "== on a password check is a real and common vulnerability."},
        {".equals()",
         "Asks whether two objects hold the same contents.",
         "The only correct way to compare credentials."},
        {"while loop",
         "Repeats a block while its test stays true.",
         "Log files have thousands of lines. You will not read them by hand."},
        {"Counters",
         "A variable that counts how many times something happened.",
         "Detection is mostly counting, then comparing to a threshold."},
        {"++",
         "Adds one to a variable.",
         "The heart of every counting rule."},
        {"Boundary conditions",
         "The value where a rule changes its answer.",
         "Off-by-one in a lockout gives an attacker an extra guess, every time."},
        {">= vs >",
         "Whether the threshold value itself counts.",
         "'5 or more' and 'more than 5' are different policies."},
        {"if / else if / else",
         "A chain of tests, stopping at the first true one.",
         "Severity grading: critical before warning before normal."},
        {"Classes",
         "A description of what something is like.",
         "A log event has a shape. Describe it once."},
        {"Objects",
         "One actual thing built from a class, using new.",
         "One object per log line, per account, per host."},
        {"Fields",
         "The values an object holds.",
         "The parts of a log line, kept separate and typed."},
        {"new",
         "Builds an object. Naming a variable does not.",
         "Two accounts need two objects, or they share one state."},
        {"Methods",
         "Named behaviour living inside a class.",
         "A rule written once cannot drift out of step with itself."},
        {"void",
         "A method that does something rather than answering something.",
         "Printing a report hands nothing back."},
        {"Arrays",
         "A fixed number of values of one type, under one name.",
         "A batch of log entries, read together."},
        {"Array length",
         "How many items an array holds. The last position is length - 1.",
         "Reading past the end crashes the parser."},
        {"ArrayList",
         "A list that grows as you add to it.",
         "A watchlist does not know its final size this morning."},
        {"boolean",
         "Holds true or false.",
         "Allowed or denied. Failed or succeeded."},
        {"Logical operators",
         "&& means both, || means either, ! means not.",
         "Translating a written policy into a condition."},
        {"private",
         "A field only its own class can touch.",
         "A password nothing outside can read cannot be leaked by accident."},
        {"Encapsulation",
         "Hiding data and exposing only what is safe to ask.",
         "A security control, not just tidiness."},
        {"Exceptions",
         "A failure that stops the program unless it is caught.",
         "One malformed log line should not end the overnight run."},
        {"try / catch",
         "Runs risky work, and handles the failure if it comes.",
         "A parser that survives bad input keeps your monitoring alive."},
    };

    public static void show(Player player) {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred("JAVA KNOWLEDGE");
        Terminal.rule('+', '=');
        Terminal.blank();

        int known = 0;
        for (String[] topic : TOPICS) {
            boolean learned = player.hasLearned(topic[0]);
            if (learned) {
                known = known + 1;
            }
            Terminal.line("  [" + (learned ? "x" : " ") + "] " + topic[0]);
        }

        Terminal.blank();
        Terminal.line("  " + known + " of " + TOPICS.length + " topics learned.");
        Terminal.blank();
        Terminal.wrapped("A topic is ticked once you complete every task in a "
                + "mission that teaches it.", "  ");
        Terminal.blank();

        String choice = Terminal.ask("  Type a topic name to read about it, or ENTER to go back: ");
        if (choice.isEmpty()) {
            return;
        }
        showTopic(choice);
    }

    private static void showTopic(String name) {
        for (String[] topic : TOPICS) {
            if (topic[0].equalsIgnoreCase(name)) {
                Terminal.newScreen();
                Terminal.heading(topic[0].toUpperCase());
                Terminal.blank();
                Terminal.wrapped("WHAT IT IS", "  ");
                Terminal.wrapped(topic[1], "    ");
                Terminal.blank();
                Terminal.wrapped("WHY IT MATTERS IN SECURITY", "  ");
                Terminal.wrapped(topic[2], "    ");
                Terminal.blank();
                Terminal.wrapped("TAUGHT IN", "  ");
                for (String missionId : missionsTeaching(topic[0])) {
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
