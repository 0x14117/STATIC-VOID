import java.util.ArrayList;
import java.util.List;

/**
 * One mission, laid out as the teaching template.
 *
 * The order of the fields here is the order the player meets them:
 * brief, what you will learn, the concept, a small example explained line by
 * line, a prediction, a practice, then the mission itself with hints, then
 * why it worked, the mistakes to avoid, the security connection, a knowledge
 * check and a recap.
 *
 * A mission is not allowed to ask for anything it has not taught.
 */
public class Mission {

    private final String id;
    private final String title;
    private final int difficulty;
    private Campaign campaign;

    // --- teach ---------------------------------------------------------
    private String brief = "";
    private String[] willLearn = new String[0];
    private String whyUseful = "";
    private String conceptName = "";
    private String explanation = "";
    private String[] example = new String[0];
    private String[] exampleOutput = new String[0];
    private String[] exampleInput = new String[0];
    private String[][] lineByLine = new String[0][];

    // --- try -----------------------------------------------------------
    private Task predict;
    private Task practice;

    // --- do ------------------------------------------------------------
    private String objective = "";
    private String[] starter = new String[0];
    private String yourTask = "";
    private Task mainTask;

    // --- close ---------------------------------------------------------
    private String[][] commonMistakes = new String[0][];
    private String cyberConnection = "";
    private final List<Task> knowledgeCheck = new ArrayList<>();
    private String recap = "";
    private String nextConcept = "";

    public Mission(String id, String title, int difficulty) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
    }

    // --- builders ------------------------------------------------------

    public Mission brief(String text) {
        this.brief = text;
        return this;
    }

    public Mission willLearn(String... concepts) {
        this.willLearn = concepts;
        return this;
    }

    public Mission whyUseful(String text) {
        this.whyUseful = text;
        return this;
    }

    public Mission concept(String name, String text) {
        this.conceptName = name;
        this.explanation = text;
        return this;
    }

    public Mission example(String... lines) {
        this.example = lines;
        return this;
    }

    public Mission exampleOutput(String... lines) {
        this.exampleOutput = lines;
        return this;
    }

    /**
     * What the person at the keyboard types while the example runs, one entry
     * per line. The example output is then shown as the screen would look,
     * typed text included.
     */
    public Mission exampleInput(String... lines) {
        this.exampleInput = lines;
        return this;
    }

    /** Pairs of {code fragment, what it means}. */
    public Mission lineByLine(String[]... pairs) {
        this.lineByLine = pairs;
        return this;
    }

    public Mission predict(Task task) {
        this.predict = task;
        return this;
    }

    public Mission practice(Task task) {
        this.practice = task;
        return this;
    }

    public Mission objective(String text) {
        this.objective = text;
        return this;
    }

    public Mission starter(String... lines) {
        this.starter = lines;
        return this;
    }

    public Mission yourTask(String text) {
        this.yourTask = text;
        return this;
    }

    public Mission mainTask(Task task) {
        this.mainTask = task;
        return this;
    }

    /** Pairs of {the mistake, why it happens and what it does}. */
    public Mission mistakes(String[]... pairs) {
        this.commonMistakes = pairs;
        return this;
    }

    public Mission cyber(String text) {
        this.cyberConnection = text;
        return this;
    }

    public Mission check(Task task) {
        knowledgeCheck.add(task);
        return this;
    }

    public Mission recap(String text) {
        this.recap = text;
        return this;
    }

    public Mission next(String text) {
        this.nextConcept = text;
        return this;
    }

    // --- readers -------------------------------------------------------

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public String getBrief() {
        return brief;
    }

    public String[] getWillLearn() {
        return willLearn;
    }

    public String getWhyUseful() {
        return whyUseful;
    }

    public String getConceptName() {
        return conceptName;
    }

    public String getExplanation() {
        return explanation;
    }

    public String[] getExample() {
        return example;
    }

    public String[] getExampleOutput() {
        return exampleOutput;
    }

    public String[] getExampleInput() {
        return exampleInput;
    }

    public String[][] getLineByLine() {
        return lineByLine;
    }

    public Task getPredict() {
        return predict;
    }

    public Task getPractice() {
        return practice;
    }

    public String getObjective() {
        return objective;
    }

    public String[] getStarter() {
        return starter;
    }

    public String getYourTask() {
        return yourTask;
    }

    public Task getMainTask() {
        return mainTask;
    }

    public String[][] getCommonMistakes() {
        return commonMistakes;
    }

    public String getCyberConnection() {
        return cyberConnection;
    }

    public List<Task> getKnowledgeCheck() {
        return knowledgeCheck;
    }

    public String getRecap() {
        return recap;
    }

    public String getNextConcept() {
        return nextConcept;
    }

    /** Every gradeable question in the mission, in the order they are asked. */
    public List<Task> allTasks() {
        List<Task> tasks = new ArrayList<>();
        if (predict != null) {
            tasks.add(predict);
        }
        if (practice != null) {
            tasks.add(practice);
        }
        if (mainTask != null) {
            tasks.add(mainTask);
        }
        tasks.addAll(knowledgeCheck);
        return tasks;
    }

    public int totalXp() {
        int total = 0;
        for (Task task : allTasks()) {
            total = total + task.getXp();
        }
        return total;
    }
}
