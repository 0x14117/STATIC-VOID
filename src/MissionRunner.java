import java.util.List;

/**
 * Plays one mission: shows the briefing and the lesson, asks each task in
 * turn, hands out hints when asked, and scores the result.
 */
public class MissionRunner {

    /** Hints cost XP. Not much, but enough that you try first. */
    private static final int HINT_COST = 3;

    /** Returned by askTask when the player skipped rather than answered. */
    private static final int SKIPPED = -1;

    public static void run(Mission mission, Player player) {
        Terminal.newScreen();
        showBriefing(mission);

        if (!mission.getTeaching().isEmpty()) {
            Terminal.pause();
            showTeaching(mission);
        }

        Terminal.pause();

        int earned = 0;
        int answered = 0;
        List<Task> tasks = mission.getTasks();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int result = askTask(task, i + 1, tasks.size(), player);
            // askTask returns SKIPPED for a task the player gave up on, and
            // the XP earned otherwise. A task answered correctly still counts
            // as answered even if hints reduced what it paid.
            if (result != SKIPPED) {
                answered = answered + 1;
                earned = earned + result;
            }
        }

        debrief(mission, player, earned, answered, tasks.size());
    }

    // --------------------------------------------------------------- display

    private static void showBriefing(Mission mission) {
        Terminal.rule('+', '=');
        Terminal.centred(mission.getId() + "  " + mission.getTitle());
        Terminal.rule('+', '=');
        Terminal.blank();

        Terminal.line("  CAMPAIGN   : " + mission.getCampaign());
        Terminal.line("  DIFFICULTY : " + difficultyBar(mission.getDifficulty())
                + "  " + mission.getDifficulty() + "/10");
        Terminal.line("  JAVA       : " + String.join(", ", mission.getJavaConcepts()));
        if (mission.getCyberConcepts().length > 0) {
            Terminal.line("  SECURITY   : " + String.join(", ", mission.getCyberConcepts()));
        }
        Terminal.line("  REWARD     : " + mission.totalXp() + " XP");
        Terminal.blank();

        Terminal.heading("BRIEFING");
        Terminal.blank();
        Terminal.wrapped(mission.getBriefing(), "  ");
        Terminal.blank();

        if (mission.getObjectives().length > 0) {
            Terminal.heading("OBJECTIVES");
            Terminal.blank();
            for (int i = 0; i < mission.getObjectives().length; i++) {
                Terminal.wrapped((i + 1) + ". " + mission.getObjectives()[i], "  ");
            }
            Terminal.blank();
        }
    }

    private static void showTeaching(Mission mission) {
        Terminal.newScreen();
        Terminal.heading("CONCEPT");
        Terminal.blank();
        // Prose is wrapped so it fits the window, but any line the author
        // indented is code laid out on purpose and is printed exactly as
        // written. Rewrapping those would destroy the indentation that makes
        // them readable.
        for (String line : mission.getTeaching().split("\n")) {
            if (line.startsWith(" ") || line.trim().isEmpty()) {
                Terminal.line("  " + line);
            } else {
                Terminal.wrapped(line, "  ");
            }
        }
        Terminal.blank();

        if (mission.getCommonMistakes().length > 0) {
            Terminal.heading("COMMON MISTAKES");
            Terminal.blank();
            for (String mistake : mission.getCommonMistakes()) {
                Terminal.wrapped("- " + mistake, "  ");
            }
            Terminal.blank();
        }
    }

    // ----------------------------------------------------------------- tasks

    /** Asks one task. Returns the XP earned for it. */
    private static int askTask(Task task, int number, int total, Player player) {
        Terminal.newScreen();
        Terminal.heading("TASK " + number + " OF " + total + "   [" + task.getType() + "]");
        Terminal.blank();
        Terminal.wrapped(task.getPrompt(), "  ");

        if (task.getCode().length > 0) {
            Terminal.code(task.getCode());
        }

        if (task.getChoices().length > 0) {
            Terminal.blank();
            for (int i = 0; i < task.getChoices().length; i++) {
                // The number is printed on its own so a long option wraps
                // underneath itself rather than back under the margin.
                System.out.print("   " + (i + 1) + ") ");
                Terminal.wrappedAfterPrefix(task.getChoices()[i], "      ", 6);
            }
        }

        Terminal.blank();
        Terminal.line("  Type your answer, or HINT for help, or SKIP to move on.");
        Terminal.blank();

        int hintsShown = 0;
        int penalty = 0;

        while (true) {
            String answer = Terminal.ask("  > ");

            if (answer.equalsIgnoreCase("hint")) {
                if (hintsShown >= task.getHints().length) {
                    Terminal.line("  No more hints. Try SKIP if you are stuck.");
                    Terminal.blank();
                    continue;
                }
                Terminal.blank();
                Terminal.wrapped("  HINT " + (hintsShown + 1) + ": "
                        + task.getHints()[hintsShown], "  ");
                Terminal.blank();
                hintsShown = hintsShown + 1;
                penalty = penalty + HINT_COST;
                player.countHint();
                continue;
            }

            if (answer.equalsIgnoreCase("skip") || answer.equalsIgnoreCase("exit")) {
                Terminal.blank();
                Terminal.line("  Skipped. The answer was:");
                Terminal.wrapped("  " + task.getFirstAccepted(), "  ");
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
                Terminal.line("  CORRECT.  +" + award + " XP"
                        + (penalty > 0 ? "  (" + penalty + " XP used on hints)" : ""));
                Terminal.blank();
                Terminal.wrapped(task.getExplanation(), "  ");
                Terminal.blank();
                player.addXp(award);
                Terminal.pause();
                return award;
            }

            player.countWrongAnswer();
            Terminal.blank();
            Terminal.line("  Not right. Try again, or type HINT.");
            Terminal.blank();
        }
    }

    // --------------------------------------------------------------- debrief

    private static void debrief(Mission mission, Player player, int earned,
                                int answered, int total) {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred("MISSION DEBRIEF");
        Terminal.rule('+', '=');
        Terminal.blank();

        Terminal.line("  MISSION  : " + mission.getId() + "  " + mission.getTitle());
        Terminal.line("  TASKS    : " + answered + " of " + total + " answered");
        Terminal.line("  XP GAINED: " + earned);
        Terminal.blank();

        if (answered == total) {
            Terminal.line("  Mission complete. Every task answered.");
            for (String concept : mission.getJavaConcepts()) {
                player.learn(concept);
            }
            player.markCompleted(mission.getId());
        } else if (answered > 0) {
            Terminal.line("  Partly complete. Replay it from TRAINING to finish the rest.");
        } else {
            Terminal.line("  Nothing answered. The mission stays open.");
        }

        Terminal.blank();
        Terminal.line("  LEVEL    : " + player.getLevel()
                + "   (" + player.getClearance() + ")");
        Terminal.line("  TOTAL XP : " + player.getXp());
        Terminal.blank();
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
