/**
 * Verifies every campaign and mission without playing the game.
 *
 * The teaching template is a promise: a mission explains before it asks, and
 * never asks for something it has not taught. Most of that is judgement, but
 * the parts a machine can check are checked here, on every build.
 */
public class CheckAll {

    static int checks = 0;
    static int failures = 0;

    static void check(String what, boolean ok, String detail) {
        checks++;
        if (!ok) {
            failures++;
            System.out.println("FAIL: " + what + "   " + detail);
        }
    }

    public static void main(String[] args) {
        int missionCount = 0;
        int taskCount = 0;
        int totalXp = 0;

        for (Campaign campaign : CampaignIndex.all()) {
            String label = campaign.getLabel();
            check(label + " has a topic", !campaign.getTopic().isEmpty(), "");
            check(label + " plans at least one mission",
                  campaign.getPlannedMissions() > 0, "");

            if (!campaign.isBuilt()) {
                continue;
            }

            check(label + " does not exceed its plan",
                  campaign.getMissions().size() <= campaign.getPlannedMissions(),
                  campaign.getMissions().size() + " > " + campaign.getPlannedMissions());

            int expected = 1;
            for (Mission m : campaign.getMissions()) {
                missionCount++;
                String id = m.getId();

                check(id + " id matches its position",
                      id.equals(campaign.missionId(expected)),
                      "expected " + campaign.missionId(expected));
                expected++;

                // --- the template must be filled in -----------------------
                check(id + " has a title", !m.getTitle().isEmpty(), "");
                check(id + " has a brief", m.getBrief().length() > 60, "");
                check(id + " says what you will learn",
                      m.getWillLearn().length > 0, "");
                check(id + " says why it is useful",
                      m.getWhyUseful().length() > 40, "");
                check(id + " names the concept",
                      !m.getConceptName().isEmpty(), "");
                check(id + " explains the concept before asking",
                      m.getExplanation().length() > 200,
                      "explanation is " + m.getExplanation().length() + " chars");
                check(id + " shows an example", m.getExample().length > 0, "");
                check(id + " has a recap", m.getRecap().length() > 50, "");
                check(id + " points at what comes next",
                      !m.getNextConcept().isEmpty(), "");
                check(id + " has a difficulty in range",
                      m.getDifficulty() >= 1 && m.getDifficulty() <= 10, "");

                // --- it must ask, not just tell ---------------------------
                check(id + " asks a prediction", m.getPredict() != null, "");
                check(id + " gives a practice", m.getPractice() != null, "");
                check(id + " has a main task", m.getMainTask() != null, "");
                check(id + " has 2 or more knowledge checks",
                      m.getKnowledgeCheck().size() >= 2,
                      "got " + m.getKnowledgeCheck().size());

                // --- the main task must be answerable ---------------------
                if (m.getMainTask() != null) {
                    Task main = m.getMainTask();
                    check(id + " main task can be solved on request",
                          main.hasSolution(), "no solution to show");
                    check(id + " explains why the solution works",
                          main.getWhyItWorks().length() > 80,
                          "a solution without reasoning teaches copying");
                    check(id + " main task has 2+ hints",
                          main.getHints().length >= 2,
                          "got " + main.getHints().length);
                }

                // --- every question ---------------------------------------
                for (Task t : m.allTasks()) {
                    taskCount++;
                    totalXp += t.getXp();
                    String tag = id + "/" + t.getType();

                    check(tag + " has a prompt", t.getPrompt().length() > 10, "");
                    check(tag + " has an accepted answer",
                          !t.getFirstAccepted().isEmpty(), t.getPrompt());
                    check(tag + " accepts its own answer",
                          t.matches(t.getFirstAccepted()), t.getFirstAccepted());
                    check(tag + " rejects nonsense", !t.matches("zzqqxx"),
                          "accepted junk");
                    check(tag + " rejects an empty answer", !t.matches(""), "");
                    check(tag + " has hints", t.getHints().length >= 2,
                          "got " + t.getHints().length);
                    check(tag + " explains the answer",
                          t.getExplanation().length() > 30, "");
                    check(tag + " awards xp", t.getXp() > 0, "");
                    if (t.getType().equals(Task.CHOICE)) {
                        check(tag + " offers choices", t.getChoices().length >= 2, "");
                    }
                }
            }
        }

        // Answers must survive the sloppiness a real person types with.
        Task probe = CampaignIndex.byId("C00-M001").getPredict();
        check("matching ignores case", probe.matches("B") || probe.matches("2"), "");
        check("matching ignores padding", probe.matches("  2  "), "");

        System.out.println();
        System.out.println("campaigns planned : " + CampaignIndex.all().size());
        System.out.println("missions planned  : " + CampaignIndex.plannedTotal());
        System.out.println("missions built    : " + missionCount);
        System.out.println("questions         : " + taskCount);
        System.out.println("total xp          : " + totalXp);
        System.out.println();
        System.out.println(checks + " checks, " + failures + " failed");
        System.exit(failures > 0 ? 1 : 0);
    }
}
