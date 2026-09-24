import java.util.List;

/**
 * CYBER//OPS - a Java and cybersecurity training simulator.
 *
 * Start here. This class draws the main terminal, reads the menu choice, and
 * hands off to whichever part of the game was asked for.
 */
public class Main {

    private static Player player;

    private static final String[] LOGO = {
        "     ________  ______  __________     __ ______  ____  _____",
        "    / ____/\\ \\/ / __ )/ ____/ __ \\  _/_//_/ __ \\/ __ \\/ ___/",
        "   / /      \\  / __  / __/ / /_/ /_/_//_// / / / /_/ /\\__ \\",
        "  / /___    / / /_/ / /___/ _, _//_//_/ / /_/ / ____/___/ /",
        "  \\____/   /_/_____/_____/_/ |_/_//_/   \\____/_/    /____/"
    };

    public static void main(String[] args) {
        player = SaveFile.load();
        if (!Theme.isChosen()) {
            pickColour();
        }

        banner();
        boot();

        if (player == null) {
            player = firstRun();
        } else {
            Terminal.blank();
            Terminal.line("  Save found. Welcome back, "
                    + Theme.paint(Theme.ACCENT, player.getName()) + ".");
            Terminal.pause();
        }

        mainLoop();
    }

    // ------------------------------------------------------------ the look

    /**
     * Colour where it is known to work, none where it cannot be seen, and on
     * a Windows console that might print escape codes as junk, a one-time
     * question - because only the player can see what their screen shows.
     */
    private static void pickColour() {
        int support = Theme.colourSupport();
        if (support == Theme.YES) {
            Theme.use("PHOSPHOR");
            return;
        }
        if (support == Theme.NO) {
            Theme.use("PLAIN");
            return;
        }

        Terminal.newScreen();
        Terminal.line("  One quick check before we start.");
        Terminal.blank();
        Terminal.line("  The next line should say GREEN, in green:");
        Terminal.blank();
        Terminal.line("      " + Theme.testSample());
        Terminal.blank();
        Terminal.line("   1. I see the word GREEN, in colour");
        Terminal.line("   2. I see odd symbols around it, like <-[1;32m");
        Terminal.blank();
        String answer = Terminal.ask("  Select: ");
        Theme.choose(answer.equals("1") ? "PHOSPHOR" : "PLAIN");
        Terminal.blank();
        Terminal.wrapped("Noted. You can change this any time in Settings > "
                + "Theme.", "  ");
    }

    private static void banner() {
        Terminal.newScreen();
        for (String row : LOGO) {
            Terminal.lineAs(Theme.TITLE, row);
        }
        Terminal.blank();
        Terminal.lineAs(Theme.ACCENT,
                "      S E C U R I T Y   O P E R A T I O N S   T E R M I N A L");
        Terminal.rule('+', '=');
    }

    /** A short start-up sequence. Instant when no one is watching. */
    private static void boot() {
        Terminal.blank();
        bootLine("Link to " + World.ORGANISATION, "established");
        bootLine("Training network", World.HOSTS.length + " hosts, "
                + World.ACCOUNTS.length + " accounts");
        bootLine("Mission library", CampaignIndex.builtTotal() + " missions ready");
        bootLine("Live systems", "none - sandbox only");
    }

    private static void bootLine(String label, String value) {
        StringBuilder dots = new StringBuilder(" ");
        while (label.length() + dots.length() < 30) {
            dots.append('.');
        }
        Terminal.beat(140);
        Terminal.line("  " + Theme.paint(Theme.GOOD, "[ OK ]") + " " + label
                + Theme.paint(Theme.MUTED, dots.toString()) + " "
                + Theme.paint(Theme.ACCENT, value));
    }

    // ------------------------------------------------------------ first run

    private static Player firstRun() {
        Terminal.blank();
        Terminal.wrapped("You are joining the security desk at "
                + World.ORGANISATION + " as a junior analyst.", "  ");
        Terminal.blank();
        Terminal.wrapped("The missions here teach Java programming and connect it "
                + "to the security work you will actually do. Everything is "
                + "simulated locally. Nothing touches a real system.", "  ");
        Terminal.blank();

        String name = Terminal.ask("  Analyst name: ");
        if (name.isEmpty() || name.equalsIgnoreCase("exit")) {
            name = "analyst";
        }

        Player fresh = new Player(name);
        SaveFile.save(fresh);

        Terminal.blank();
        Terminal.line("  Registered. Clearance "
                + Theme.paint(Theme.ACCENT, "TRAINEE")
                + ". Your first mission is waiting.");
        Terminal.pause();
        return fresh;
    }

    // ------------------------------------------------------------- the menu

    private static void mainLoop() {
        while (true) {
            Mission next = CampaignIndex.nextFor(player);
            showDashboard(next);

            String choice = Terminal.ask("  Select: ");

            if (choice.equals("1")) {
                viewMission(next);
            } else if (choice.equals("2")) {
                startMission(next);
            } else if (choice.equals("3")) {
                training();
            } else if (choice.equals("4")) {
                KnowledgeIndex.show(player);
            } else if (choice.equals("5")) {
                CampaignIndex.showTree(player);
            } else if (choice.equals("6")) {
                progress();
            } else if (choice.equals("7")) {
                settings();
            } else if (choice.equals("8") || choice.equalsIgnoreCase("exit")
                    || choice.equalsIgnoreCase("quit")) {
                SaveFile.save(player);
                Terminal.blank();
                Terminal.line("  Progress saved. Terminal closed.");
                Terminal.blank();
                return;
            } else {
                Terminal.line("  Not an option. Choose 1 to 8.");
                Terminal.pause();
            }
        }
    }

    private static void showDashboard(Mission next) {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred("CYBER//OPS");
        Terminal.centred("SECURITY OPERATIONS TERMINAL");
        Terminal.rule('+', '=');
        Terminal.blank();

        Terminal.line("  STATUS    : " + Theme.paint(Theme.GOOD, "ONLINE")
                + "          " + World.ORGANISATION);
        Terminal.blank();
        Terminal.line("  Analyst   : " + Theme.paint(Theme.ACCENT, player.getName()));
        Terminal.line("  Clearance : " + Theme.paint(Theme.ACCENT, player.getClearance()));
        Terminal.line("  Level     : " + player.getLevel()
                + "   " + xpBar(player.getXpIntoLevel()) + "  "
                + player.getXp() + " XP");
        Terminal.line("  Missions  : " + player.getCompletedMissions().size()
                + " of " + CampaignIndex.builtTotal() + " built"
                + "   (" + CampaignIndex.plannedTotal() + " planned)");
        Terminal.blank();

        if (next == null) {
            Terminal.line("  Active Mission: none - every mission is complete.");
        } else {
            Terminal.line("  Active Mission: " + Theme.paint(Theme.ACCENT, next.getId())
                    + "  " + next.getTitle());
            Terminal.line("                  " + next.getCampaign().getLabel()
                    + "   difficulty " + next.getDifficulty() + "/10");
        }

        Terminal.blank();
        Terminal.rule('+', '-');
        Terminal.blank();
        menuRow("1", "View Mission", "5", "Campaign Map");
        menuRow("2", "Start Mission", "6", "Progress");
        menuRow("3", "Training", "7", "Settings");
        menuRow("4", "Java Knowledge", "8", "Exit");
        Terminal.blank();
    }

    private static void menuRow(String leftKey, String left,
                                String rightKey, String right) {
        Terminal.line("   " + Theme.paint(Theme.ACCENT, leftKey + ".") + " "
                + Terminal.pad(left, 19) + " "
                + Theme.paint(Theme.ACCENT, rightKey + ".") + " " + right);
    }

    private static String xpBar(int intoLevel) {
        int filled = intoLevel / 10;
        StringBuilder full = new StringBuilder();
        StringBuilder empty = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (i < filled) {
                full.append('#');
            } else {
                empty.append('.');
            }
        }
        return "[" + Theme.paint(Theme.ACCENT, full.toString())
                + Theme.paint(Theme.MUTED, empty.toString()) + "]";
    }

    // ------------------------------------------------------------- the menu items

    private static void viewMission(Mission mission) {
        if (mission == null) {
            Terminal.line("  Nothing outstanding. Try TRAINING to replay a mission.");
            Terminal.pause();
            return;
        }
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred(mission.getId() + "  " + mission.getTitle());
        Terminal.rule('+', '=');
        Terminal.blank();
        Terminal.line("  CAMPAIGN   : " + mission.getCampaign().getLabel());
        Terminal.line("  DIFFICULTY : " + MissionRunner.difficultyBar(mission.getDifficulty())
                + "  " + mission.getDifficulty() + "/10");
        Terminal.line("  TEACHES    : " + String.join(", ", mission.getWillLearn()));
        Terminal.line("  QUESTIONS  : " + mission.allTasks().size());
        Terminal.line("  REWARD     : " + mission.totalXp() + " XP");
        Terminal.blank();
        Terminal.heading("MISSION BRIEF");
        Terminal.blank();
        Terminal.wrapped(mission.getBrief(), "  ");
        Terminal.blank();
        Terminal.pause();
    }

    private static void startMission(Mission mission) {
        if (mission == null) {
            Terminal.line("  Nothing outstanding. Try TRAINING to replay a mission.");
            Terminal.pause();
            return;
        }
        MissionRunner.run(mission, player);
        SaveFile.save(player);
    }

    private static void training() {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred("TRAINING - REPLAY ANY MISSION");
        Terminal.rule('+', '=');
        Terminal.blank();

        for (Campaign campaign : CampaignIndex.all()) {
            if (!campaign.isBuilt()) {
                continue;
            }
            Terminal.blank();
            Terminal.heading(campaign.getLabel());
            for (Mission mission : campaign.getMissions()) {
                String done = player.hasCompleted(mission.getId())
                        ? Theme.paint(Theme.GOOD, "[x]") : "[ ]";
                Terminal.line("  " + done + " " + Terminal.pad(mission.getId(), 11)
                        + Terminal.pad(mission.getTitle(), 30)
                        + "d" + mission.getDifficulty());
            }
        }

        Terminal.blank();
        String choice = Terminal.ask("  Mission ID to play, or ENTER to go back: ");
        if (choice.isEmpty()) {
            return;
        }

        Mission chosen = CampaignIndex.byId(choice);
        if (chosen == null) {
            Terminal.line("  No mission with that ID.");
            Terminal.pause();
            return;
        }
        MissionRunner.run(chosen, player);
        SaveFile.save(player);
    }

    private static void progress() {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred("PROGRESS");
        Terminal.rule('+', '=');
        Terminal.blank();

        Terminal.line("  Analyst        : " + player.getName());
        Terminal.line("  Clearance      : " + player.getClearance());
        Terminal.line("  Level          : " + player.getLevel());
        Terminal.line("  Total XP       : " + player.getXp());
        Terminal.line("  XP this level  : " + player.getXpIntoLevel() + " / 100");
        Terminal.blank();
        Terminal.line("  Missions done  : " + player.getCompletedMissions().size()
                + " of " + CampaignIndex.builtTotal() + " built"
                + ", " + CampaignIndex.plannedTotal() + " planned");
        Terminal.line("  Topics learned : " + player.getLearnedTopics().size());
        Terminal.line("  Hints used     : " + player.getHintsUsed());
        Terminal.line("  Wrong answers  : " + player.getWrongAnswers());
        Terminal.blank();

        if (player.getWrongAnswers() > 0 || player.getHintsUsed() > 0) {
            Terminal.wrapped("Hints and wrong answers are counted, not punished. "
                    + "They show which ideas to revisit.", "  ");
            Terminal.blank();
        }

        Terminal.heading("COMPLETED");
        Terminal.blank();
        if (player.getCompletedMissions().isEmpty()) {
            Terminal.line("  None yet.");
        } else {
            for (String id : player.getCompletedMissions()) {
                Mission mission = CampaignIndex.byId(id);
                if (mission != null) {
                    Terminal.line("  " + Terminal.pad(id, 10) + mission.getTitle());
                }
            }
        }
        Terminal.blank();
        Terminal.pause();
    }

    private static void chooseTheme() {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred("THEME");
        Terminal.rule('+', '=');
        Terminal.blank();

        String[] names = Theme.names();
        for (int i = 0; i < names.length; i++) {
            String sample = Theme.paintAs(names[i], Theme.FRAME, "+==")
                    + Theme.paintAs(names[i], Theme.TITLE, " CYBER//OPS ")
                    + Theme.paintAs(names[i], Theme.FRAME, "==+")
                    + "  " + Theme.paintAs(names[i], Theme.GOOD, "CORRECT")
                    + " " + Theme.paintAs(names[i], Theme.BAD, "WRONG");
            Terminal.line("   " + Theme.paint(Theme.ACCENT, (i + 1) + ".") + " "
                    + Terminal.pad(names[i], 10) + "  " + sample);
            Terminal.line("              " + Theme.describe(names[i]));
        }

        Terminal.blank();
        Terminal.wrapped("If the samples show odd symbols instead of colours, "
                + "your console cannot display colour. Choose PLAIN.", "  ");
        Terminal.blank();

        String choice = Terminal.ask("  Theme number, or ENTER to keep "
                + Theme.current() + ": ");
        for (int i = 0; i < names.length; i++) {
            if (choice.equals(String.valueOf(i + 1))) {
                Theme.choose(names[i]);
                SaveFile.save(player);
                Terminal.blank();
                Terminal.line("  Theme set to " + Theme.paint(Theme.ACCENT, names[i]) + ".");
                Terminal.pause();
                return;
            }
        }
    }

    private static void settings() {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred("SETTINGS");
        Terminal.rule('+', '=');
        Terminal.blank();
        Terminal.line("   1. Change analyst name");
        Terminal.line("   2. Reset all progress");
        Terminal.line("   3. Where is my save file?");
        Terminal.line("   4. NORTHSTAR inventory");
        Terminal.line("   5. Theme           now: " + Theme.current());
        Terminal.line("   6. Back");
        Terminal.blank();

        String choice = Terminal.ask("  Select: ");

        if (choice.equals("1")) {
            String name = Terminal.ask("  New name: ");
            if (!name.isEmpty()) {
                player.setName(name);
                SaveFile.save(player);
                Terminal.line("  Name changed to " + name + ".");
            }
            Terminal.pause();
        } else if (choice.equals("2")) {
            Terminal.blank();
            Terminal.wrapped("This erases every completed mission, all XP and "
                    + "every learned topic. It cannot be undone.", "  ");
            Terminal.blank();
            String confirm = Terminal.ask("  Type RESET to confirm: ");
            if (confirm.equals("RESET")) {
                SaveFile.delete();
                player = new Player(player.getName());
                SaveFile.save(player);
                Terminal.line("  Progress reset.");
            } else {
                Terminal.line("  Cancelled. Nothing was changed.");
            }
            Terminal.pause();
        } else if (choice.equals("4")) {
            World.showInventory();
        } else if (choice.equals("5")) {
            chooseTheme();
        } else if (choice.equals("3")) {
            Terminal.blank();
            Terminal.wrapped("Your progress is in cyberops-save.txt, in the folder "
                    + "you run the game from. It is plain text - open it and you "
                    + "can read exactly what the game remembers about you.", "  ");
            Terminal.blank();
            Terminal.pause();
        }
    }
}
