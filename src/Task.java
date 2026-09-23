/**
 * One question inside a mission.
 *
 * A task never asks the player to paste a whole program. It asks them to
 * predict what code will print, find the line holding a bug, choose between
 * options, or write one missing line. That keeps the game lightweight and
 * makes copying an answer from somewhere else pointless.
 */
public class Task {

    /** What kind of question this is. Decides how it is presented. */
    public static final String PREDICT = "PREDICT";
    public static final String DEBUG = "DEBUG";
    public static final String WRITE = "WRITE";
    public static final String CHOICE = "CHOICE";
    public static final String RECALL = "RECALL";

    private final String type;
    private final String prompt;
    private String[] code = new String[0];
    private String[] choices = new String[0];
    private String[] accepted = new String[0];
    private String[] hints = new String[0];
    private String explanation = "";
    private String[] solution = new String[0];
    private String whyItWorks = "";
    private int xp = 10;

    public Task(String type, String prompt) {
        this.type = type;
        this.prompt = prompt;
    }

    public Task code(String... lines) {
        this.code = lines;
        return this;
    }

    public Task choices(String... options) {
        this.choices = options;
        return this;
    }

    /** Every answer that counts as correct. Compared loosely - see matches(). */
    public Task accept(String... answers) {
        this.accepted = answers;
        return this;
    }

    public Task hints(String... hintLines) {
        this.hints = hintLines;
        return this;
    }

    public Task explain(String text) {
        this.explanation = text;
        return this;
    }

    public Task xp(int amount) {
        this.xp = amount;
        return this;
    }

    /** The finished code, shown only when the player asks for SOLUTION. */
    public Task solution(String... lines) {
        this.solution = lines;
        return this;
    }

    /**
     * Why the solution is correct, in terms of what happens when it runs.
     * Handing over code without this teaches copying, not programming.
     */
    public Task whyItWorks(String text) {
        this.whyItWorks = text;
        return this;
    }

    public String[] getSolution() {
        return solution;
    }

    public String getWhyItWorks() {
        return whyItWorks;
    }

    public boolean hasSolution() {
        return solution.length > 0;
    }

    public String getType() {
        return type;
    }

    public String getPrompt() {
        return prompt;
    }

    public String[] getCode() {
        return code;
    }

    public String[] getChoices() {
        return choices;
    }

    public String[] getHints() {
        return hints;
    }

    public String getExplanation() {
        return explanation;
    }

    public int getXp() {
        return xp;
    }

    public String getFirstAccepted() {
        if (accepted.length == 0) {
            return "";
        }
        return accepted[0];
    }

    /**
     * Is this answer right?
     *
     * Comparison is deliberately forgiving about things that are not the
     * point: capitals, extra spaces, and surrounding quotes. It is NOT
     * forgiving about the answer itself.
     */
    public boolean matches(String answer) {
        String given = normalise(answer);
        if (given.isEmpty()) {
            return false;
        }
        for (String candidate : accepted) {
            if (normalise(candidate).equals(given)) {
                return true;
            }
        }
        return false;
    }

    private String normalise(String text) {
        String out = text.trim().toLowerCase();
        // Collapse runs of whitespace so "a   +  b" matches "a + b".
        out = out.replaceAll("\\s+", " ");
        // A player may or may not wrap their answer in quotes. Neither is wrong.
        if (out.length() >= 2 && out.startsWith("\"") && out.endsWith("\"")) {
            out = out.substring(1, out.length() - 1).trim();
        }
        return out;
    }
}
