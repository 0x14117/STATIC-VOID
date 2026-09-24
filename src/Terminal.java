import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Everything that draws on screen or reads from the keyboard.
 *
 * Kept in one place so the rest of the game never calls System.out directly.
 * If the look of CYBER//OPS ever changes, it changes here and nowhere else.
 *
 * Colour comes from Theme, by role. Padding and wrapping are always worked
 * out on the plain text first and painted afterwards, because colour codes
 * take up characters but no space on screen.
 */
public class Terminal {

    public static final int WIDTH = 62;

    private static final Scanner INPUT = new Scanner(System.in);

    // ---------------------------------------------------------------- output

    public static void line(String text) {
        System.out.println(text);
    }

    /** Text with no line break after it, for a label the next call continues. */
    public static void inline(String text) {
        System.out.print(text);
    }

    /** One line in a theme role - GOOD for correct, BAD for wrong, and so on. */
    public static void lineAs(int role, String text) {
        System.out.println(Theme.paint(role, text));
    }

    public static void blank() {
        System.out.println();
    }

    /** A full-width horizontal rule, e.g. +------------+ */
    public static void rule(char edge, char fill) {
        StringBuilder bar = new StringBuilder();
        bar.append(edge);
        for (int i = 0; i < WIDTH - 2; i++) {
            bar.append(fill);
        }
        bar.append(edge);
        System.out.println(Theme.paint(Theme.FRAME, bar.toString()));
    }

    /** One row of a box, with the text padded out to the full width. */
    public static void boxed(String text) {
        boxed(text, -1);
    }

    private static void boxed(String text, int role) {
        String inside = pad(text, WIDTH - 4);
        if (role >= 0) {
            inside = Theme.paint(role, inside);
        }
        String edge = Theme.paint(Theme.FRAME, "|");
        System.out.println(edge + " " + inside + " " + edge);
    }

    /** One row of a box with the text centred, in the title colour. */
    public static void centred(String text) {
        int space = WIDTH - 4 - text.length();
        if (space < 0) {
            boxed(text, Theme.TITLE);
            return;
        }
        int left = space / 2;
        StringBuilder padded = new StringBuilder();
        for (int i = 0; i < left; i++) {
            padded.append(' ');
        }
        padded.append(text);
        boxed(padded.toString(), Theme.TITLE);
    }

    public static String pad(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        StringBuilder out = new StringBuilder(text);
        while (out.length() < width) {
            out.append(' ');
        }
        return out.toString();
    }

    /** A heading like:  -- SECTION NAME ------------------------- */
    public static void heading(String text) {
        StringBuilder tail = new StringBuilder(" ");
        while (3 + text.length() + tail.length() < WIDTH) {
            tail.append('-');
        }
        System.out.println(Theme.paint(Theme.FRAME, "-- ")
                + Theme.paint(Theme.HEADING, text)
                + Theme.paint(Theme.FRAME, tail.toString()));
    }

    /**
     * Prints text wrapped to the terminal width so a long briefing never runs
     * off the edge of the window.
     */
    public static void wrapped(String text) {
        wrapped(text, "");
    }

    public static void wrapped(String text, String indent) {
        for (String row : wrapLines(text, indent)) {
            System.out.println(row);
        }
    }

    /** Wrapped text in a theme role. Each row is painted after wrapping. */
    public static void wrappedAs(int role, String text, String indent) {
        for (String row : wrapLines(text, indent)) {
            System.out.println(row.isEmpty() ? row : Theme.paint(role, row));
        }
    }

    private static List<String> wrapLines(String text, String indent) {
        List<String> rows = new ArrayList<>();
        for (String paragraph : text.split("\n")) {
            if (paragraph.isEmpty()) {
                rows.add("");
                continue;
            }
            StringBuilder currentLine = new StringBuilder(indent);
            for (String word : paragraph.split(" ")) {
                if (currentLine.length() + word.length() + 1 > WIDTH && currentLine.length() > indent.length()) {
                    rows.add(currentLine.toString());
                    currentLine = new StringBuilder(indent);
                }
                if (currentLine.length() > indent.length()) {
                    currentLine.append(' ');
                }
                currentLine.append(word);
            }
            if (currentLine.length() > indent.length()) {
                rows.add(currentLine.toString());
            }
        }
        return rows;
    }

    /**
     * Wraps text that follows something already printed on the current line,
     * so a numbered option wraps underneath itself instead of back under the
     * left margin.
     */
    public static void wrappedAfterPrefix(String text, String indent, int used) {
        StringBuilder currentLine = new StringBuilder();
        int lineLength = used;
        boolean first = true;
        for (String word : text.split(" ")) {
            if (lineLength + word.length() + 1 > WIDTH && !first) {
                System.out.println(currentLine);
                currentLine = new StringBuilder(indent);
                lineLength = indent.length();
                first = true;
            }
            if (!first) {
                currentLine.append(' ');
                lineLength = lineLength + 1;
            }
            currentLine.append(word);
            lineLength = lineLength + word.length();
            first = false;
        }
        System.out.println(currentLine);
    }

    /**
     * Prints a teaching block: prose is wrapped to the window, but any line
     * the author indented is code laid out on purpose and is printed exactly
     * as written. Rewrapping those would destroy the shape that makes them
     * readable.
     */
    public static void teachingText(String text, String indent) {
        for (String line : text.split("\n")) {
            if (line.trim().isEmpty()) {
                System.out.println();
            } else if (line.startsWith(" ")) {
                System.out.println(indent + Theme.paint(Theme.CODE, line));
            } else {
                wrapped(line, indent);
            }
        }
    }

    /** Java source shown to the player, with line numbers for bug hunting. */
    public static void code(String[] lines) {
        blank();
        for (int i = 0; i < lines.length; i++) {
            String gutter = String.format("   %2d | ", i + 1);
            System.out.println(Theme.paint(Theme.GUTTER, gutter)
                    + Theme.paint(Theme.CODE, lines[i]));
        }
        blank();
    }

    /** Pushes the old screen out of view. Cheaper and safer than clearing. */
    public static void newScreen() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }

    // ----------------------------------------------------------------- input

    public static String ask(String prompt) {
        System.out.print(Theme.paint(Theme.PROMPT, prompt));
        System.out.flush();
        if (!INPUT.hasNextLine()) {
            // Input ran out (the window was closed, or the game is being
            // driven by a script). Treat it as a request to leave.
            return "exit";
        }
        return INPUT.nextLine().trim();
    }

    public static void pause() {
        System.out.println();
        System.out.print(Theme.paint(Theme.MUTED, "[ press ENTER to continue ] "));
        System.out.flush();
        if (INPUT.hasNextLine()) {
            INPUT.nextLine();
        }
    }

    /**
     * A short pause for effect, but only when a person is watching. A script
     * or a pipe gets the output at full speed.
     */
    public static void beat(int millis) {
        if (!Theme.outputIsTerminal()) {
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
