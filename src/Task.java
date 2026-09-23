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
    private String[] input = new String[0];
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
    /**
     * What is typed at the keyboard when this task's code or solution runs,
     * one entry per line. Used by tools/CheckJava; the prompt must tell the
     * player the same thing in words.
     */
    public Task input(String... lines) {
        this.input = lines;
        return this;
    }

    public String[] getInput() {
        return input;
    }

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

    public String[] getAccepted() {
        return accepted;
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
     * Comparison is forgiving about things that are not the point: extra
     * spaces and surrounding quotes. Capitals depend on the question. Java
     * source and program output are both case sensitive - System and system
     * are different words, and a program that prints jsmith did not print
     * JSmith - so PREDICT and WRITE answers must match exactly. A choice
     * letter or a line number means the same whatever its case.
     */
    public boolean matches(String answer) {
        return matchesWith(answer, !isCaseExact());
    }

    /**
     * Right apart from capital letters. Lets the game say precisely what is
     * wrong rather than a flat "not quite".
     */
    public boolean matchesIgnoringCase(String answer) {
        return matchesWith(answer, true);
    }

    public boolean isCaseExact() {
        return type.equals(PREDICT) || type.equals(WRITE);
    }

    private boolean matchesWith(String answer, boolean ignoreCase) {
        String given = normalise(answer, ignoreCase);
        if (given.isEmpty()) {
            return false;
        }
        for (String candidate : accepted) {
            if (normalise(candidate, ignoreCase).equals(given)) {
                return true;
            }
        }
        return false;
    }

    private String normalise(String text, boolean ignoreCase) {
        String out = text.trim();
        if (ignoreCase) {
            out = out.toLowerCase();
        }
        // Collapse runs of whitespace so "a   +  b" matches "a + b".
        out = out.replaceAll("\\s+", " ");
        // A player may or may not wrap their answer in quotes. Neither is wrong.
        if (out.length() >= 2 && out.startsWith("\"") && out.endsWith("\"")) {
            out = out.substring(1, out.length() - 1).trim();
        }
        return out;
    }
}
