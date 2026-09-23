import java.util.List;

/**
 * CYBER//OPS - a Java and cybersecurity training simulator.
 *
 * Start here. This class draws the main terminal, reads the menu choice, and
 * hands off to whichever part of the game was asked for.
 */
public class Main {

    private static Player player;

    public static void main(String[] args) {
        player = SaveFile.load();

        if (player == null) {
            player = firstRun();
        } else {
            Terminal.newScreen();
            Terminal.line("  Save found. Welcome back, " + player.getName() + ".");
        }

        mainLoop();
    }

    // ------------------------------------------------------------ first run

    private static Player firstRun() {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred("CYBER//OPS");
        Terminal.centred("SECURITY OPERATIONS TERMINAL");
        Terminal.rule('+', '=');
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
        Terminal.wrapped("Registered. Clearance TRAINEE. Your first mission is "
                + "waiting.", "  ");
        Terminal.pause();
        return fresh;
    }

    // ------------------------------------------------------------- the menu

    private static void mainLoop() {
        while (true) {
            Mission next = MissionLibrary.nextFor(player);
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
                World.showInventory();
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

        Terminal.line("  STATUS    : ONLINE          " + World.ORGANISATION);
        Terminal.blank();
        Terminal.line("  Analyst   : " + player.getName());
        Terminal.line("  Clearance : " + player.getClearance());
        Terminal.line("  Level     : " + player.getLevel()
                + "   " + xpBar(player.getXpIntoLevel()) + "  "
                + player.getXp() + " XP");
        Terminal.line("  Missions  : " + player.getCompletedMissions().size()
                + " of " + MissionLibrary.all().size() + " complete");
        Terminal.blank();

        if (next == null) {
            Terminal.line("  Active Mission: none - every mission is complete.");
        } else {
            Terminal.line("  Active Mission: " + next.getId() + "  " + next.getTitle());
            Terminal.line("                  " + next.getCampaign()
                    + "   difficulty " + next.getDifficulty() + "/10");
        }

        Terminal.blank();
        Terminal.rule('+', '-');
        Terminal.blank();
        Terminal.line("   1. View Mission        5. Inventory");
        Terminal.line("   2. Start Mission       6. Progress");
        Terminal.line("   3. Training            7. Settings");
        Terminal.line("   4. Java Knowledge      8. Exit");
        Terminal.blank();
    }

    private static String xpBar(int intoLevel) {
        int filled = intoLevel / 10;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < 10; i++) {
            bar.append(i < filled ? '#' : '.');
        }
        bar.append(']');
        return bar.toString();
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
        Terminal.line("  CAMPAIGN   : " + mission.getCampaign());
        Terminal.line("  DIFFICULTY : " + MissionRunner.difficultyBar(mission.getDifficulty())
                + "  " + mission.getDifficulty() + "/10");
        Terminal.line("  JAVA       : " + String.join(", ", mission.getJavaConcepts()));
        if (mission.getCyberConcepts().length > 0) {
            Terminal.line("  SECURITY   : " + String.join(", ", mission.getCyberConcepts()));
        }
        Terminal.line("  TASKS      : " + mission.getTasks().size());
        Terminal.line("  REWARD     : " + mission.totalXp() + " XP");
        Terminal.blank();
        Terminal.heading("BRIEFING");
        Terminal.blank();
        Terminal.wrapped(mission.getBriefing(), "  ");
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

        List<Mission> missions = MissionLibrary.all();
        String campaign = "";
        for (int i = 0; i < missions.size(); i++) {
            Mission mission = missions.get(i);
            if (!mission.getCampaign().equals(campaign)) {
                campaign = mission.getCampaign();
                Terminal.blank();
                Terminal.heading(campaign.toUpperCase());
            }
            String done = player.hasCompleted(mission.getId()) ? "[x]" : "[ ]";
            Terminal.line("  " + done + " " + Terminal.pad(mission.getId(), 10)
                    + Terminal.pad(mission.getTitle(), 32)
                    + "d" + mission.getDifficulty());
        }

        Terminal.blank();
        String choice = Terminal.ask("  Mission ID to play, or ENTER to go back: ");
        if (choice.isEmpty()) {
            return;
        }

        Mission chosen = MissionLibrary.byId(choice);
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
                + " of " + MissionLibrary.all().size());
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
                Mission mission = MissionLibrary.byId(id);
                if (mission != null) {
                    Terminal.line("  " + Terminal.pad(id, 10) + mission.getTitle());
                }
            }
        }
        Terminal.blank();
        Terminal.pause();
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
        Terminal.line("   4. Back");
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
