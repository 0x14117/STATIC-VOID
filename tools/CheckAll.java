/**
 * Verifies every mission and task in the library, without playing the game:
 * that each task has an accepted answer, hints, an explanation, and that its
 * own first accepted answer is actually accepted by its matcher.
 */
public class CheckAll {
    static int checks = 0;
    static int failures = 0;

    static void check(String what, boolean ok, String detail) {
        checks++;
        if (!ok) {
            failures++;
            System.out.println("FAIL: " + what + "  " + detail);
        }
    }

    public static void main(String[] args) {
        int taskCount = 0;
        int totalXp = 0;

        for (Mission m : MissionLibrary.all()) {
            String id = m.getId();
            check(id + " has a title", !m.getTitle().isEmpty(), "");
            check(id + " has a briefing", m.getBriefing().length() > 40, "");
            check(id + " has java concepts", m.getJavaConcepts().length > 0, "");
            check(id + " has at least 3 tasks", m.getTasks().size() >= 3,
                  "got " + m.getTasks().size());
            check(id + " difficulty in range",
                  m.getDifficulty() >= 1 && m.getDifficulty() <= 10, "");

            for (Task t : m.getTasks()) {
                taskCount++;
                totalXp += t.getXp();
                String label = id + "/" + t.getType();
                check(label + " has a prompt", t.getPrompt().length() > 10, "");
                check(label + " has an accepted answer",
                      !t.getFirstAccepted().isEmpty(), t.getPrompt());
                check(label + " accepts its own answer",
                      t.matches(t.getFirstAccepted()), t.getFirstAccepted());
                check(label + " has 2+ hints or is a CHOICE",
                      t.getHints().length >= 2, "hints=" + t.getHints().length);
                check(label + " has an explanation",
                      t.getExplanation().length() > 30, "");
                check(label + " rejects nonsense",
                      !t.matches("zzzqqq"), "accepted a junk answer");
                check(label + " rejects empty", !t.matches(""), "accepted empty");
                if (t.getType().equals(Task.CHOICE)) {
                    check(label + " has choices", t.getChoices().length >= 2, "");
                }
                check(label + " awards xp", t.getXp() > 0, "");
            }
        }

        // Answers should survive the sloppiness a real player types with.
        Task probe = MissionLibrary.byId("AUTH-001").getTasks().get(0);
        check("matching ignores case", probe.matches("access terminal"), "");
        check("matching ignores padding", probe.matches("  ACCESS TERMINAL  "), "");

        System.out.println();
        System.out.println("missions : " + MissionLibrary.all().size());
        System.out.println("tasks    : " + taskCount);
        System.out.println("total xp : " + totalXp);
        System.out.println(checks + " checks, " + failures + " failed");
        System.exit(failures > 0 ? 1 : 0);
    }
}
