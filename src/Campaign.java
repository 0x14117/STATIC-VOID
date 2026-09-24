import java.util.ArrayList;
import java.util.List;

/**
 * One campaign: a numbered block of missions on a single area.
 *
 * The campaign knows how many missions it is eventually meant to hold, so the
 * game can show real progress against the full plan even while most of them
 * are still to be written.
 */
public class Campaign {

    private final int number;
    private final String code;
    private final String topic;
    private final int plannedMissions;
    private final List<Mission> missions = new ArrayList<>();
    private final List<Lab> labs = new ArrayList<>();

    public Campaign(int number, String code, String topic, int plannedMissions) {
        this.number = number;
        this.code = code;
        this.topic = topic;
        this.plannedMissions = plannedMissions;
    }

    public int getNumber() {
        return number;
    }

    public String getCode() {
        return code;
    }

    public String getTopic() {
        return topic;
    }

    public int getPlannedMissions() {
        return plannedMissions;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public Campaign add(Mission mission) {
        mission.setCampaign(this);
        missions.add(mission);
        return this;
    }

    public List<Lab> getLabs() {
        return labs;
    }

    public Campaign addLab(Lab lab) {
        lab.setCampaign(this);
        labs.add(lab);
        return this;
    }

    /** Lab ids look like C01-L05, beside the mission ids they build on. */
    public String labId(int labNumber) {
        return String.format("C%02d-L%02d", number, labNumber);
    }

    public int labsPassedBy(Player player) {
        int passed = 0;
        for (Lab lab : labs) {
            if (player.hasPassedLab(lab.getId())) {
                passed = passed + 1;
            }
        }
        return passed;
    }

    /** "CAMPAIGN 00 - INIT" */
    public String getLabel() {
        return String.format("CAMPAIGN %02d - %s", number, code);
    }

    /** Mission ids look like C00-M001, so they sort and group on sight. */
    public String missionId(int missionNumber) {
        return String.format("C%02d-M%03d", number, missionNumber);
    }

    public boolean isBuilt() {
        return !missions.isEmpty();
    }

    public int completedBy(Player player) {
        int done = 0;
        for (Mission mission : missions) {
            if (player.hasCompleted(mission.getId())) {
                done = done + 1;
            }
        }
        return done;
    }
}
