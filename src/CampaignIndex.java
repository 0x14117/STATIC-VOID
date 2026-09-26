import java.util.ArrayList;
import java.util.List;

/**
 * The full plan: twenty campaigns, 510 missions.
 *
 * Campaigns 00 to 08 follow the module syllabus, in its order, so the game
 * and the lectures move together. 09 to 19 go beyond the module. See
 * docs/CURRICULUM.md for what each one covers.
 *
 * Every campaign is declared here with the number of missions it is meant to
 * hold, whether or not those missions exist yet. That way the game can show
 * honest progress against the whole plan rather than pretending the plan is
 * only as big as what has been written.
 *
 * Campaigns are built one at a time. A campaign with no missions yet shows as
 * LOCKED.
 */
public class CampaignIndex {

    private static final List<Campaign> CAMPAIGNS = new ArrayList<>();

    static {
        // Part 1: the module.
        declare(0,  "INIT",              "Language and IDE basics",      10);
        declare(1,  "JAVA ZERO",         "Variables, operators, I/O",    30);
        declare(2,  "CONDITIONAL",       "Selection and switch",         30);
        declare(3,  "METHODS",           "Methods and the stack",        30);
        declare(4,  "LOOP//CONTROL",     "Iteration",                    30);
        declare(5,  "COLLECTIONS",       "Arrays and ArrayList",         30);
        declare(6,  "OBJECTS",           "Classes and constructors",     30);
        declare(7,  "EXCEPTIONS",        "Exceptions and events",        25);
        declare(8,  "FILES",             "File I/O",                     25);
        // Part 2: beyond the module.
        declare(9,  "DEBUG",             "Debugging and testing",        30);
        declare(10, "OOP",               "Inheritance and interfaces",   30);
        declare(11, "DATA STRUCTURES",   "Maps, sets and generics",      30);
        declare(12, "ALGORITHMS",        "Algorithms and problem solving",25);
        declare(13, "SECURE CODE",       "Secure Java programming",      25);
        declare(14, "CYBER OPS",         "Cybersecurity programming",    25);
        declare(15, "SOC",               "SOC and log analysis",         25);
        declare(16, "NETWORK",           "Networking concepts",          20);
        declare(17, "CRYPTO",            "Cryptography concepts",        20);
        declare(18, "INCIDENT RESPONSE", "Incident response",            20);
        declare(19, "FINAL SOC",         "Final SOC operations",         20);

        // Missions are added campaign by campaign as each one is built.
        Campaign00.build(byNumber(0));
        Campaign01.build(byNumber(1));
        Campaign02.build(byNumber(2));
        Campaign03.build(byNumber(3));
        Campaign04.build(byNumber(4));

        // Labs: whole programs, written by the learner, tested by the game.
        Campaign00Labs.build(byNumber(0));
        Campaign01Labs.build(byNumber(1));
        Campaign02Labs.build(byNumber(2));
        Campaign03Labs.build(byNumber(3));
        Campaign04Labs.build(byNumber(4));
    }

    private static void declare(int number, String code, String topic, int planned) {
        CAMPAIGNS.add(new Campaign(number, code, topic, planned));
    }

    public static List<Lab> allLabs() {
        List<Lab> labs = new ArrayList<>();
        for (Campaign campaign : CAMPAIGNS) {
            labs.addAll(campaign.getLabs());
        }
        return labs;
    }

    public static Lab labById(String id) {
        for (Lab lab : allLabs()) {
            if (lab.getId().equalsIgnoreCase(id.trim())) {
                return lab;
            }
        }
        return null;
    }

    public static List<Campaign> all() {
        return CAMPAIGNS;
    }

    public static Campaign byNumber(int number) {
        for (Campaign campaign : CAMPAIGNS) {
            if (campaign.getNumber() == number) {
                return campaign;
            }
        }
        return null;
    }

    public static List<Mission> allMissions() {
        List<Mission> missions = new ArrayList<>();
        for (Campaign campaign : CAMPAIGNS) {
            missions.addAll(campaign.getMissions());
        }
        return missions;
    }

    public static Mission byId(String id) {
        for (Mission mission : allMissions()) {
            if (mission.getId().equalsIgnoreCase(id)) {
                return mission;
            }
        }
        return null;
    }

    /** The first mission the player has not finished. */
    public static Mission nextFor(Player player) {
        for (Mission mission : allMissions()) {
            if (!player.hasCompleted(mission.getId())) {
                return mission;
            }
        }
        return null;
    }

    public static int plannedTotal() {
        int total = 0;
        for (Campaign campaign : CAMPAIGNS) {
            total = total + campaign.getPlannedMissions();
        }
        return total;
    }

    public static int builtTotal() {
        return allMissions().size();
    }

    /** The campaign tree, with progress on the campaigns that exist. */
    public static void showTree(Player player) {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred("CAMPAIGN MAP");
        Terminal.rule('+', '=');
        Terminal.blank();

        for (Campaign campaign : CAMPAIGNS) {
            String marker;
            String progress;

            if (!campaign.isBuilt()) {
                marker = Theme.paint(Theme.MUTED, "[locked]");
                progress = "";
            } else {
                int done = campaign.completedBy(player);
                int built = campaign.getMissions().size();
                marker = done == built ? Theme.paint(Theme.GOOD, "[  done]")
                                       : Theme.paint(Theme.ACCENT, "[  open]");
                progress = "  " + done + "/" + built + " played"
                        + (built < campaign.getPlannedMissions()
                           ? "   (" + built + " of " + campaign.getPlannedMissions() + " written)"
                           : "");
            }

            Terminal.line("  " + marker + " " + campaign.getLabel());
            Terminal.line("           " + Terminal.pad(campaign.getTopic(), 32)
                    + campaign.getPlannedMissions() + " missions" + progress);
        }

        Terminal.blank();
        Terminal.line("  " + builtTotal() + " missions built of " + plannedTotal() + " planned.");
        Terminal.blank();
        Terminal.wrapped("Campaigns are written one at a time so each can be "
                + "tested properly. A locked campaign has no missions yet.", "  ");
        Terminal.blank();
        Terminal.pause();
    }
}
