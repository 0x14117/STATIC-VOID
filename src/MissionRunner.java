import java.util.List;

/**
 * Plays one mission by walking the teaching template in order:
 *
 *   brief -> what you will learn -> concept -> example -> line by line
 *   -> predict -> practice -> objective and starter -> your task
 *   -> mistakes -> security connection -> knowledge check -> recap
 *
 * The player is never asked to write something the mission has not explained.
 */
public class MissionRunner {

    private static final int HINT_COST = 3;
    private static final int SKIPPED = -1;

    public static void run(Mission mission, Player player) {
        showBrief(mission);
        Terminal.pause();

        showWillLearn(mission);
        Terminal.pause();

        showConcept(mission);
        Terminal.pause();

        int earned = 0;
        int answered = 0;
        int total = 0;

        if (mission.getPredict() != null) {
            total = total + 1;
            int result = ask(mission.getPredict(), "PREDICT THE OUTPUT", player);
            if (result != SKIPPED) {
                answered = answered + 1;
                earned = earned + result;
            }
        }

        if (mission.getPractice() != null) {
            total = total + 1;
            int result = ask(mission.getPractice(), "YOUR FIRST PRACTICE", player);
            if (result != SKIPPED) {
                answered = answered + 1;
                earned = earned + result;
            }
        }

        if (mission.getMainTask() != null) {
            total = total + 1;
            showObjective(mission);
            int result = ask(mission.getMainTask(), "YOUR TASK", player);
            if (result != SKIPPED) {
                answered = answered + 1;
                earned = earned + result;
            }
        }

        showMistakes(mission);
        showCyber(mission);

        List<Task> checks = mission.getKnowledgeCheck();
        if (!checks.isEmpty()) {
            Terminal.newScreen();
            Terminal.heading("KNOWLEDGE CHECK");
            Terminal.blank();
            Terminal.wrapped("A few short questions to prove the idea stuck.", "  ");
            Terminal.pause();

            for (int i = 0; i < checks.size(); i++) {
                total = total + 1;
                int result = ask(checks.get(i), "KNOWLEDGE CHECK " + (i + 1)
                        + " OF " + checks.size(), player);
                if (result != SKIPPED) {
                    answered = answered + 1;
                    earned = earned + result;
                }
            }
        }

        showRecap(mission, player, earned, answered, total);
    }

    // ================================================================ teach

    private static void showBrief(Mission mission) {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred(mission.getId() + "   " + mission.getTitle());
        Terminal.rule('+', '=');
        Terminal.blank();
        Terminal.line("  CAMPAIGN   : " + mission.getCampaign().getLabel());
        Terminal.line("  DIFFICULTY : " + difficultyBar(mission.getDifficulty())
                + "  " + mission.getDifficulty() + "/10");
        Terminal.line("  REWARD     : " + mission.totalXp() + " XP");
        Terminal.blank();
        Terminal.heading("MISSION BRIEF");
        Terminal.blank();
        Terminal.wrapped(mission.getBrief(), "  ");
        Terminal.blank();
    }

    private static void showWillLearn(Mission mission) {
        Terminal.newScreen();
        Terminal.heading("WHAT YOU WILL LEARN");
        Terminal.blank();
        for (String concept : mission.getWillLearn()) {
            Terminal.line("   * " + concept);
        }
        Terminal.blank();
        if (!mission.getWhyUseful().isEmpty()) {
            Terminal.wrapped("Why these are worth knowing:", "  ");
            Terminal.blank();
            Terminal.wrapped(mission.getWhyUseful(), "  ");
            Terminal.blank();
        }
    }

    private static void showConcept(Mission mission) {
        Terminal.newScreen();
        Terminal.heading("JAVA CONCEPT: " + mission.getConceptName().toUpperCase());
        Terminal.blank();
        Terminal.teachingText(mission.getExplanation(), "  ");
        Terminal.blank();

        if (mission.getExample().length > 0) {
            Terminal.heading("SMALL EXAMPLE");
            Terminal.code(mission.getExample());
            if (mission.getExampleOutput().length > 0) {
                Terminal.line("  This prints:");
                Terminal.blank();
                for (String out : mission.getExampleOutput()) {
                    Terminal.line("      " + out);
                }
                Terminal.blank();
            }
        }

        if (mission.getLineByLine().length > 0) {
            Terminal.heading("LINE BY LINE");
            Terminal.blank();
            for (String[] pair : mission.getLineByLine()) {
                Terminal.line("   " + pair[0]);
                Terminal.wrapped(pair[1], "        ");
                Terminal.blank();
            }
        }
    }

    private static void showObjective(Mission mission) {
        Terminal.newScreen();
        Terminal.heading("MISSION OBJECTIVE");
        Terminal.blank();
        Terminal.wrapped(mission.getObjective(), "  ");
        Terminal.blank();

        if (mission.getStarter().length > 0) {
            Terminal.heading("STARTER CODE");
            Terminal.code(mission.getStarter());
        }

        if (!mission.getYourTask().isEmpty()) {
            Terminal.heading("YOUR TASK");
            Terminal.blank();
            Terminal.wrapped(mission.getYourTask(), "  ");
            Terminal.blank();
        }
        Terminal.pause();
    }

    // ================================================================= ask

    /** Asks one question. Returns XP earned, or SKIPPED. */
    private static int ask(Task task, String sectionTitle, Player player) {
        Terminal.newScreen();
        Terminal.heading(sectionTitle);
        Terminal.blank();
        Terminal.wrapped(task.getPrompt(), "  ");

        if (task.getCode().length > 0) {
            Terminal.code(task.getCode());
        }

        if (task.getChoices().length > 0) {
            Terminal.blank();
            for (int i = 0; i < task.getChoices().length; i++) {
                System.out.print("   " + (i + 1) + ") ");
                Terminal.wrappedAfterPrefix(task.getChoices()[i], "      ", 6);
            }
        }

        Terminal.blank();
        Terminal.line("  Answer, or type HINT"
                + (task.hasSolution() ? ", SOLUTION" : "") + ", or SKIP.");
        Terminal.blank();

        int hintsShown = 0;
        int penalty = 0;

        while (true) {
            String answer = Terminal.ask("  > ");

            // An empty line is not a wrong answer. Someone pressed ENTER, or
            // is thinking. Ask again without marking it against them.
            if (answer.isEmpty()) {
                continue;
            }

            if (answer.equalsIgnoreCase("hint")) {
                if (hintsShown >= task.getHints().length) {
                    Terminal.line("  That is every hint. Try SKIP"
                            + (task.hasSolution() ? " or SOLUTION" : "") + ".");
                    Terminal.blank();
                    continue;
                }
                Terminal.blank();
                Terminal.wrapped("HINT " + (hintsShown + 1) + ": "
                        + task.getHints()[hintsShown], "  ");
                Terminal.blank();
                hintsShown = hintsShown + 1;
                penalty = penalty + HINT_COST;
                player.countHint();
                continue;
            }

            if (answer.equalsIgnoreCase("solution")) {
                if (!task.hasSolution()) {
                    Terminal.line("  No full solution for this one. Try HINT.");
                    Terminal.blank();
                    continue;
                }
                showSolution(task);
                Terminal.pause();
                return SKIPPED;
            }

            if (answer.equalsIgnoreCase("skip") || answer.equalsIgnoreCase("exit")) {
                Terminal.blank();
                Terminal.line("  Skipped. The answer was:");
                Terminal.wrapped(task.getFirstAccepted(), "      ");
                Terminal.blank();
                Terminal.wrapped(task.getExplanation(), "  ");
                Terminal.blank();
                Terminal.pause();
                return SKIPPED;
            }

            if (task.matches(answer)) {
                int award = task.getXp() - penalty;
                if (award < 1) {
                    award = 1;
                }
                Terminal.blank();
                Terminal.line("  CORRECT.   +" + award + " XP"
                        + (penalty > 0 ? "   (" + penalty + " spent on hints)" : ""));
                Terminal.blank();
                Terminal.wrapped(task.getExplanation(), "  ");
                Terminal.blank();
                player.addXp(award);
                Terminal.pause();
                return award;
            }

            player.countWrongAnswer();
            Terminal.blank();
            Terminal.line("  Not quite. Try again, or type HINT.");
            Terminal.blank();
        }
    }

    /**
     * Shows the finished code AND the reasoning behind it. Handing over the
     * code on its own would teach copying, which is the one thing this game
     * exists to prevent.
     */
    private static void showSolution(Task task) {
        Terminal.blank();
        Terminal.heading("SOLUTION");
        Terminal.code(task.getSolution());
        if (!task.getWhyItWorks().isEmpty()) {
            Terminal.heading("WHY THIS WORKS");
            Terminal.blank();
            Terminal.teachingText(task.getWhyItWorks(), "  ");
            Terminal.blank();
        }
        Terminal.wrapped("Read it until you could write it again from memory, "
                + "then replay this mission from TRAINING.", "  ");
        Terminal.blank();
    }

    // =============================================================== close

    private static void showMistakes(Mission mission) {
        if (mission.getCommonMistakes().length == 0) {
            return;
        }
        Terminal.newScreen();
        Terminal.heading("COMMON MISTAKES");
        Terminal.blank();
        int number = 1;
        for (String[] pair : mission.getCommonMistakes()) {
            Terminal.line("  MISTAKE " + number + ": " + pair[0]);
            Terminal.blank();
            Terminal.wrapped(pair[1], "      ");
            Terminal.blank();
            number = number + 1;
        }
        Terminal.pause();
    }

    private static void showCyber(Mission mission) {
        if (mission.getCyberConnection().isEmpty()) {
            return;
        }
        Terminal.newScreen();
        Terminal.heading("CYBERSECURITY CONNECTION");
        Terminal.blank();
        Terminal.teachingText(mission.getCyberConnection(), "  ");
        Terminal.blank();
        Terminal.pause();
    }

    private static void showRecap(Mission mission, Player player,
                                  int earned, int answered, int total) {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred("MISSION RECAP");
        Terminal.rule('+', '=');
        Terminal.blank();
        Terminal.teachingText(mission.getRecap(), "  ");
        Terminal.blank();

        Terminal.heading("RESULT");
        Terminal.blank();
        Terminal.line("  Questions answered : " + answered + " of " + total);
        Terminal.line("  XP earned          : " + earned);

        if (answered == total && total > 0) {
            player.markCompleted(mission.getId());
            for (String concept : mission.getWillLearn()) {
                player.learn(concept);
            }
            Terminal.line("  Mission            : COMPLETE");
        } else if (answered > 0) {
            Terminal.line("  Mission            : PARTLY DONE - replay from TRAINING");
        } else {
            Terminal.line("  Mission            : NOT DONE");
        }

        Terminal.blank();
        Terminal.line("  Level " + player.getLevel() + "   "
                + player.getClearance() + "   " + player.getXp() + " XP total");
        Terminal.blank();

        if (!mission.getNextConcept().isEmpty()) {
            Terminal.heading("NEXT");
            Terminal.blank();
            Terminal.wrapped(mission.getNextConcept(), "  ");
            Terminal.blank();
        }
        Terminal.pause();
    }

    static String difficultyBar(int difficulty) {
        StringBuilder bar = new StringBuilder("[");
        for (int i = 1; i <= 10; i++) {
            bar.append(i <= difficulty ? '#' : '.');
        }
        bar.append(']');
        return bar.toString();
    }
}
