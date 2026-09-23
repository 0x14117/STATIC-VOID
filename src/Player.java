import java.util.ArrayList;
import java.util.List;

/**
 * The analyst: who they are and how far they have got.
 */
public class Player {

    private String name;
    private int xp;
    private int hintsUsed;
    private int wrongAnswers;
    private final List<String> completedMissions = new ArrayList<>();
    private final List<String> learnedTopics = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getXp() {
        return xp;
    }

    public void addXp(int amount) {
        xp = xp + amount;
    }

    public int getHintsUsed() {
        return hintsUsed;
    }

    public void countHint() {
        hintsUsed = hintsUsed + 1;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void countWrongAnswer() {
        wrongAnswers = wrongAnswers + 1;
    }

    /**
     * Level is derived from XP rather than stored, so the two can never
     * disagree with each other. 100 XP per level.
     */
    public int getLevel() {
        return (xp / 100) + 1;
    }

    public int getXpIntoLevel() {
        return xp % 100;
    }

    /** Clearance is the job title that comes with the level. */
    public String getClearance() {
        int level = getLevel();
        if (level >= 10) {
            return "SOC LEAD";
        } else if (level >= 7) {
            return "SENIOR ANALYST";
        } else if (level >= 4) {
            return "ANALYST";
        } else if (level >= 2) {
            return "JUNIOR ANALYST";
        }
        return "TRAINEE";
    }

    public List<String> getCompletedMissions() {
        return completedMissions;
    }

    public boolean hasCompleted(String missionId) {
        return completedMissions.contains(missionId);
    }

    public void markCompleted(String missionId) {
        if (!hasCompleted(missionId)) {
            completedMissions.add(missionId);
        }
    }

    public List<String> getLearnedTopics() {
        return learnedTopics;
    }

    public boolean hasLearned(String topic) {
        return learnedTopics.contains(topic);
    }

    public void learn(String topic) {
        if (!hasLearned(topic)) {
            learnedTopics.add(topic);
        }
    }

    // Used by the save file, which stores these as plain text.
    public void restore(int xp, int hintsUsed, int wrongAnswers) {
        this.xp = xp;
        this.hintsUsed = hintsUsed;
        this.wrongAnswers = wrongAnswers;
    }
}
