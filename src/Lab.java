import java.util.ArrayList;
import java.util.List;

/**
 * A lab: a whole program the learner writes in their own editor, which the
 * game then compiles and tests.
 *
 * Missions teach one idea at a time with short questions. Labs are the other
 * half of learning to program: starting from a nearly empty file and making
 * something that works. Built the same way as a Mission, with chained calls.
 */
public class Lab {

    public static final String SMALL = "SMALL";
    public static final String MEDIUM = "MEDIUM";
    public static final String BIG = "BIG";
    public static final String CAPSTONE = "CAPSTONE";

    /** For labs that read nothing from the keyboard. */
    public static final String[] NO_INPUT = new String[0];

    private final String id;
    private final String title;
    private final String size;
    private Campaign campaign;
    private boolean core = true;
    private String after = "";
    private boolean brokenStarter = false;
    private String brief = "";
    private String[] practises = new String[0];
    private String[] spec = new String[0];
    private String[] starter = new String[0];
    private String[] hints = new String[0];
    private String[] solution = new String[0];
    private String walkthrough = "";
    private final List<LabTest> tests = new ArrayList<>();
    private final List<String> neededMethods = new ArrayList<>();

    public Lab(String id, String title, String size) {
        this.id = id;
        this.title = title;
        this.size = size;
    }

    /** The keys typed during a test, one entry per line. */
    public static String[] typing(String... lines) {
        return lines;
    }

    // ------------------------------------------------------------ building

    /** Extra practice, not on the core path. */
    public Lab stretch() {
        this.core = false;
        return this;
    }

    /** The mission this lab assumes has been completed. */
    public Lab after(String missionId) {
        this.after = missionId;
        return this;
    }

    /** The starter is meant not to compile: fixing it is the lab. */
    public Lab brokenStarter() {
        this.brokenStarter = true;
        return this;
    }

    public Lab brief(String text) {
        this.brief = text;
        return this;
    }

    public Lab practises(String... concepts) {
        this.practises = concepts;
        return this;
    }

    /** The requirements, one per entry. Shown numbered. */
    public Lab spec(String... requirements) {
        this.spec = requirements;
        return this;
    }

    public Lab starter(String... lines) {
        this.starter = lines;
        return this;
    }

    /** Progressive: the first nudges, the last nearly gives it away. */
    public Lab hints(String... hintText) {
        this.hints = hintText;
        return this;
    }

    public Lab solution(String... lines) {
        this.solution = lines;
        return this;
    }

    public Lab walkthrough(String text) {
        this.walkthrough = text;
        return this;
    }

    /**
     * A method the program must declare, written like a header without the
     * parameter names: "static boolean isValidPort(String)". Labs about
     * methods use this, so a program that crams everything into main does
     * not pass just by printing the right text.
     */
    public Lab needsMethod(String signature) {
        neededMethods.add(signature);
        return this;
    }

    /** A test shown in the brief as a sample run. */
    public Lab sample(String[] input, String... screen) {
        tests.add(new LabTest(input, screen, false));
        return this;
    }

    /** A test the learner does not see until it fails. */
    public Lab hidden(String[] input, String... screen) {
        tests.add(new LabTest(input, screen, true));
        return this;
    }

    void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    // ------------------------------------------------------------- reading

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSize() {
        return size;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public boolean isCore() {
        return core;
    }

    public String getAfter() {
        return after;
    }

    public boolean hasBrokenStarter() {
        return brokenStarter;
    }

    public String getBrief() {
        return brief;
    }

    public String[] getPractises() {
        return practises;
    }

    public String[] getSpec() {
        return spec;
    }

    public String[] getStarter() {
        return starter;
    }

    public String[] getHints() {
        return hints;
    }

    public String[] getSolution() {
        return solution;
    }

    public String getWalkthrough() {
        return walkthrough;
    }

    public List<LabTest> getTests() {
        return tests;
    }

    public List<String> getNeededMethods() {
        return neededMethods;
    }

    public boolean readsInput() {
        for (LabTest test : tests) {
            if (test.getInput().length > 0) {
                return true;
            }
        }
        return false;
    }

    public int getXp() {
        if (size.equals(CAPSTONE)) {
            return 150;
        } else if (size.equals(BIG)) {
            return 100;
        } else if (size.equals(MEDIUM)) {
            return 60;
        }
        return 30;
    }

    public String getTimeEstimate() {
        if (size.equals(CAPSTONE)) {
            return "2-5 hours";
        } else if (size.equals(BIG)) {
            return "1-3 hours";
        } else if (size.equals(MEDIUM)) {
            return "30-60 min";
        }
        return "10-20 min";
    }
}
