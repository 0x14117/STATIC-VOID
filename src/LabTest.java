/**
 * One test case for a lab: what is typed, and what the screen should show.
 *
 * The expected output is written as the SCREEN looks, typed input included,
 * because that is what the learner sees in the sample run and when they run
 * the program themselves. LabEcho makes a test run produce the same thing.
 */
public class LabTest {

    private final String[] input;
    private final String[] screen;
    private final boolean hidden;

    public LabTest(String[] input, String[] screen, boolean hidden) {
        this.input = input;
        this.screen = screen;
        this.hidden = hidden;
    }

    public String[] getInput() {
        return input;
    }

    public String[] getScreen() {
        return screen;
    }

    /** Hidden tests use values the brief never shows, so hard-coding fails. */
    public boolean isHidden() {
        return hidden;
    }
}
