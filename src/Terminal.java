import java.util.Scanner;

/**
 * Everything that draws on screen or reads from the keyboard.
 *
 * Kept in one place so the rest of the game never calls System.out directly.
 * If the look of CYBER//OPS ever changes, it changes here and nowhere else.
 */
public class Terminal {

    public static final int WIDTH = 62;

    private static final Scanner INPUT = new Scanner(System.in);

    // ---------------------------------------------------------------- output

    public static void line(String text) {
        System.out.println(text);
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
        System.out.println(bar);
    }

    /** One row of a box, with the text padded out to the full width. */
    public static void boxed(String text) {
        System.out.println("| " + pad(text, WIDTH - 4) + " |");
    }

    /** One row of a box with the text centred. */
    public static void centred(String text) {
        int space = WIDTH - 4 - text.length();
        if (space < 0) {
            boxed(text);
            return;
        }
        int left = space / 2;
        StringBuilder padded = new StringBuilder();
        for (int i = 0; i < left; i++) {
            padded.append(' ');
        }
        padded.append(text);
        boxed(padded.toString());
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
        StringBuilder out = new StringBuilder("-- " + text + " ");
        while (out.length() < WIDTH) {
            out.append('-');
        }
        System.out.println(out);
    }

    /**
     * Prints text wrapped to the terminal width so a long briefing never runs
     * off the edge of the window.
     */
    public static void wrapped(String text) {
        wrapped(text, "");
    }

    public static void wrapped(String text, String indent) {
        for (String paragraph : text.split("\n")) {
            if (paragraph.isEmpty()) {
                System.out.println();
                continue;
            }
            StringBuilder currentLine = new StringBuilder(indent);
            for (String word : paragraph.split(" ")) {
                if (currentLine.length() + word.length() + 1 > WIDTH && currentLine.length() > indent.length()) {
                    System.out.println(currentLine);
                    currentLine = new StringBuilder(indent);
                }
                if (currentLine.length() > indent.length()) {
                    currentLine.append(' ');
                }
                currentLine.append(word);
            }
            if (currentLine.length() > indent.length()) {
                System.out.println(currentLine);
            }
        }
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
                System.out.println(indent + line);
            } else {
                wrapped(line, indent);
            }
        }
    }

    /** Java source shown to the player, with line numbers for bug hunting. */
    public static void code(String[] lines) {
        blank();
        for (int i = 0; i < lines.length; i++) {
            System.out.printf("   %2d | %s%n", i + 1, lines[i]);
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
        System.out.print(prompt);
        System.out.flush();
        if (!INPUT.hasNextLine()) {
            // Input ran out (the window was closed, or the game is being
            // driven by a script). Treat it as a request to leave.
            return "exit";
        }
        return INPUT.nextLine().trim();
    }

    public static void pause() {
        ask("\n[ press ENTER to continue ] ");
    }
}
