import java.util.ArrayList;
import java.util.List;

/**
 * The full plan: twenty campaigns, 530 missions.
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
        declare(0,  "INIT",              "Setup and Java orientation",   10);
        declare(1,  "JAVA ZERO",         "Java from zero",               30);
        declare(2,  "CONDITIONAL",       "Conditions and decisions",     30);
        declare(3,  "LOOP//CONTROL",     "Loops",                        30);
        declare(4,  "METHODS",           "Methods",                      30);
        declare(5,  "DATA",              "Arrays and Strings",           30);
        declare(6,  "DEBUG",             "Debugging",                    30);
        declare(7,  "OBJECTS",           "Classes and objects",          30);
        declare(8,  "OOP",               "Object-oriented programming",  30);
        declare(9,  "COLLECTIONS",       "Collections",                  30);
        declare(10, "EXCEPTIONS",        "Exceptions and error handling",25);
        declare(11, "FILES",             "Files and data processing",    25);
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
    }

    private static void declare(int number, String code, String topic, int planned) {
        CAMPAIGNS.add(new Campaign(number, code, topic, planned));
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
                marker = "[locked]";
                progress = "";
            } else {
                int done = campaign.completedBy(player);
                int built = campaign.getMissions().size();
                marker = done == built ? "[  done]" : "[  open]";
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
