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

    /**
     * Teaching prose is printed by Terminal.teachingText, which wraps ordinary
     * paragraphs but prints indented lines verbatim so that diagrams and code
     * layout survive. A verbatim line wider than the terminal breaks the frame,
     * and nobody notices until they play the mission on a narrow window.
     */
    static void checkProseWidth(String tag, String text) {
        if (text == null) {
            return;
        }
        int worst = 0;
        String offender = "";
        for (String raw : text.split("\n", -1)) {
            if (!raw.startsWith(" ")) {
                continue;   // wrapped by teachingText, so its width is safe
            }
            int rendered = 2 + raw.length();   // the "  " indent teachingText adds
            if (rendered > worst) {
                worst = rendered;
                offender = raw.trim();
            }
        }
        check(tag + " fits the terminal", worst <= Terminal.WIDTH,
              worst + " cols: " + offender);
    }

    /**
     * Code blocks are printed with a "   nn | " gutter and never wrapped, since
     * breaking a line of Java changes what it means. Eighty columns is the
     * narrowest terminal worth supporting, so that is the ceiling.
     */
    static void checkCodeWidth(String tag, String[] lines) {
        if (lines == null) {
            return;
        }
        int worst = 0;
        String offender = "";
        for (String raw : lines) {
            int rendered = 8 + raw.length();   // "   nn | "
            if (rendered > worst) {
                worst = rendered;
                offender = raw.trim();
            }
        }
        check(tag + " fits 80 columns", worst <= 78,
              worst + " cols: " + offender);
    }

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

                // --- it must still fit on a screen ------------------------
                checkProseWidth(id + " concept", m.getExplanation());
                checkProseWidth(id + " recap", m.getRecap());
                checkProseWidth(id + " cyber connection", m.getCyberConnection());
                checkCodeWidth(id + " example", m.getExample());
                checkCodeWidth(id + " starter", m.getStarter());

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

                    checkProseWidth(tag + " explanation", t.getExplanation());
                    checkCodeWidth(tag + " code", t.getCode());
                    if (t.hasSolution()) {
                        checkCodeWidth(tag + " solution", t.getSolution());
                        checkProseWidth(tag + " reasoning", t.getWhyItWorks());
                    }

                    check(tag + " has a prompt", t.getPrompt().length() > 10, "");
                    check(tag + " has an accepted answer",
                          !t.getFirstAccepted().isEmpty(), t.getPrompt());
                    check(tag + " accepts its own answer",
                          t.matches(t.getFirstAccepted()), t.getFirstAccepted());
                    check(tag + " rejects nonsense", !t.matches("zzqqxx"),
                          "accepted junk");
                    check(tag + " rejects an empty answer", !t.matches(""), "");
                    if (t.isCaseExact()) {
                        String first = t.getFirstAccepted();
                        String shouted = first.toUpperCase();
                        boolean listed = false;
                        for (String a : t.getAccepted()) {
                            listed = listed || a.equals(shouted);
                        }
                        if (!shouted.equals(first) && !listed) {
                            check(tag + " rejects wrong capitals",
                                  !t.matches(shouted), "accepted " + shouted);
                        }
                    }
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

        // --- labs ------------------------------------------------------------
        // Whether each lab can actually be done is tools/CheckLabs' job, which
        // runs the real compiler. These are the fast checks on its content.
        java.util.Set<String> labIds = new java.util.HashSet<>();
        int labCount = 0;
        for (Campaign campaign : CampaignIndex.all()) {
            for (int i = 0; i < campaign.getLabs().size(); i++) {
                Lab lab = campaign.getLabs().get(i);
                String id = lab.getId();
                labCount++;
                check(id + " is numbered in order", id.equals(campaign.labId(i + 1)), "");
                check(id + " id is unique", labIds.add(id), "");
                check(id + " has a title", lab.getTitle().length() > 3, "");
                check(id + " has a known size",
                      java.util.Arrays.asList(Lab.SMALL, Lab.MEDIUM, Lab.BIG, Lab.CAPSTONE)
                          .contains(lab.getSize()), lab.getSize());
                check(id + " builds on a real mission",
                      CampaignIndex.byId(lab.getAfter()) != null, lab.getAfter());
                check(id + " has a brief", lab.getBrief().length() > 100, "");
                check(id + " says what it practises", lab.getPractises().length >= 1, "");
                check(id + " has a specification", lab.getSpec().length >= 2, "");
                check(id + " has a starter", lab.getStarter().length >= 3, "");
                check(id + " has 3 or more hints", lab.getHints().length >= 3,
                      "got " + lab.getHints().length);
                check(id + " has a solution", lab.getSolution().length >= 3, "");
                check(id + " explains its solution", lab.getWalkthrough().length() > 150,
                      "a solution without reasoning teaches copying");
                boolean sample = false;
                boolean hidden = false;
                for (LabTest t : lab.getTests()) {
                    sample = sample || !t.isHidden();
                    hidden = hidden || t.isHidden();
                    for (String line : t.getScreen()) {
                        check(id + " sample line fits the screen",
                              6 + line.length() <= Terminal.WIDTH + 16, line);
                    }
                }
                check(id + " shows a sample run", sample, "");
                if (lab.readsInput()) {
                    check(id + " has a hidden test", hidden,
                          "input labs need hidden tests so hard-coding fails");
                }
                checkProseWidth(id + " brief", lab.getBrief());
                checkProseWidth(id + " walkthrough", lab.getWalkthrough());
                for (String h : lab.getHints()) {
                    checkProseWidth(id + " hint", h);
                }
                checkCodeWidth(id + " starter", lab.getStarter());
                checkCodeWidth(id + " solution", lab.getSolution());
            }
        }
        System.out.println("labs built        : " + labCount);

        // --- the knowledge index and the missions agree -------------------
        // Every mission ticks at least one topic, except a campaign's closing
        // checkpoint, which reviews rather than introduces. And once a
        // campaign is complete, every topic promised for it has been taught.
        java.util.Set<String> indexed = new java.util.HashSet<>();
        for (String[] topic : KnowledgeIndex.topics()) {
            indexed.add(topic[1].toLowerCase());
        }
        for (Campaign campaign : CampaignIndex.all()) {
            java.util.List<Mission> built = campaign.getMissions();
            boolean complete = built.size() == campaign.getPlannedMissions();
            java.util.Set<String> taught = new java.util.HashSet<>();
            for (int i = 0; i < built.size(); i++) {
                Mission m = built.get(i);
                boolean ticks = false;
                for (String concept : m.getWillLearn()) {
                    taught.add(concept.toLowerCase());
                    ticks = ticks || indexed.contains(concept.toLowerCase());
                }
                boolean closing = complete && i == built.size() - 1;
                if (!closing) {
                    check(m.getId() + " ticks a knowledge topic", ticks,
                          "none of its willLearn entries is in KnowledgeIndex");
                }
            }
            if (complete) {
                for (String[] topic : KnowledgeIndex.topics()) {
                    if (topic[0].equals(String.valueOf(campaign.getNumber()))) {
                        check(campaign.getLabel() + " teaches " + topic[1],
                              taught.contains(topic[1].toLowerCase()), "");
                    }
                }
            }
        }

        // Answers must survive the sloppiness a real person types with...
        Task probe = CampaignIndex.byId("C00-M001").getPredict();
        check("matching ignores padding", probe.matches("  2  ")
              || probe.matches("  b  "), "");
        // ...but not a wrong capital where Java itself would care.
        Task exact = CampaignIndex.byId("C01-M001").getPractice();
        check("output is graded case-exact", exact.matches("openPorts")
              && !exact.matches("openports"), "openports accepted");
        check("a wrong capital is recognised as nearly right",
              exact.matchesIgnoringCase("OPENPORTS"), "");

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
