import java.util.ArrayList;
import java.util.List;

/**
 * One mission: a briefing, the concepts it teaches, and its tasks.
 */
public class Mission {

    private final String id;
    private final String title;
    private final String campaign;
    private final int difficulty;

    private String briefing = "";
    private String[] javaConcepts = new String[0];
    private String[] cyberConcepts = new String[0];
    private String[] objectives = new String[0];
    private String[] commonMistakes = new String[0];
    private String teaching = "";
    private final List<Task> tasks = new ArrayList<>();

    public Mission(String id, String title, String campaign, int difficulty) {
        this.id = id;
        this.title = title;
        this.campaign = campaign;
        this.difficulty = difficulty;
    }

    public Mission briefing(String text) {
        this.briefing = text;
        return this;
    }

    /** The concept lesson, shown before the tasks when it is a new idea. */
    public Mission teaching(String text) {
        this.teaching = text;
        return this;
    }

    public Mission java(String... concepts) {
        this.javaConcepts = concepts;
        return this;
    }

    public Mission cyber(String... concepts) {
        this.cyberConcepts = concepts;
        return this;
    }

    public Mission objectives(String... items) {
        this.objectives = items;
        return this;
    }

    public Mission mistakes(String... items) {
        this.commonMistakes = items;
        return this;
    }

    public Mission task(Task task) {
        tasks.add(task);
        return this;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCampaign() {
        return campaign;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getBriefing() {
        return briefing;
    }

    public String getTeaching() {
        return teaching;
    }

    public String[] getJavaConcepts() {
        return javaConcepts;
    }

    public String[] getCyberConcepts() {
        return cyberConcepts;
    }

    public String[] getObjectives() {
        return objectives;
    }

    public String[] getCommonMistakes() {
        return commonMistakes;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /** Total XP available, used for the mission list and the debrief. */
    public int totalXp() {
        int total = 0;
        for (Task task : tasks) {
            total = total + task.getXp();
        }
        return total;
    }
}
