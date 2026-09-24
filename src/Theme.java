import java.io.Console;
import java.lang.reflect.Method;

/**
 * The colours of the terminal.
 *
 * Colour is done with ANSI escape codes: a short sequence such as ESC[32m that
 * the terminal reads as "switch to green" instead of printing. Almost every
 * modern terminal understands them. Some older Windows consoles do not, and
 * print them as junk like <-[32m, so colour is only switched on where it is
 * known to work, or after the player has said it looks right. PLAIN turns it
 * off entirely.
 *
 * Every colour choice is made by ROLE - frame, heading, code, good, bad -
 * never by writing a colour at the point of use. A theme is just a mapping
 * from roles to colours, so adding one is a single line in THEMES.
 *
 * The colours are the basic sixteen, and bright white and black are avoided,
 * so every theme stays readable on a dark or a light background.
 */
public class Theme {

    // Roles. Terminal and the rest of the game only ever name these.
    public static final int FRAME = 0;     // rules and box edges
    public static final int TITLE = 1;     // the logo and screen titles
    public static final int HEADING = 2;   // section headings
    public static final int CODE = 3;      // Java source
    public static final int GUTTER = 4;    // line numbers beside code
    public static final int PROMPT = 5;    // where the player types
    public static final int ACCENT = 6;    // values worth noticing
    public static final int GOOD = 7;      // correct, complete, online
    public static final int BAD = 8;       // wrong, failed
    public static final int WARN = 9;      // hints, cautions
    public static final int MUTED = 10;    // press ENTER, secondary text

    /**
     * name | description | then one ANSI code per role, in the order above.
     * GOOD, BAD, WARN and MUTED mean the same in every theme, so a player who
     * switches theme never has to relearn what red means.
     */
    private static final String[][] THEMES = {
        {"PHOSPHOR", "classic green-screen terminal",
         "32", "1;32", "1;32", "32", "90", "1;32", "1;32", "1;32", "1;31", "33", "90"},
        {"AMBER", "retro amber monitor",
         "33", "1;33", "1;33", "33", "90", "1;33", "1;33", "1;32", "1;31", "33", "90"},
        {"BLUE TEAM", "defender's SOC console",
         "36", "1;36", "1;36", "36", "90", "1;36", "1;34", "1;32", "1;31", "33", "90"},
        {"RED TEAM", "attacker's shell",
         "31", "1;31", "1;31", "35", "90", "1;31", "1;35", "1;32", "1;31", "33", "90"},
        {"PLAIN", "no colour at all",
         "", "", "", "", "", "", "", "", "", "", ""},
    };

    private static final String ESC = "\u001b[";
    private static final String RESET = ESC + "0m";

    private static int current = indexOf("PLAIN");

    /**
     * True once the player has picked a theme, by answering the colour check
     * or in Settings. Only a choice is saved; an automatic decision is made
     * again at every start, because the same save may be played in a
     * different terminal next time.
     */
    private static boolean chosen = false;

    // ------------------------------------------------------------ painting

    /** text wrapped in the current theme's colour for this role. */
    public static String paint(int role, String text) {
        String code = THEMES[current][2 + role];
        if (code.isEmpty() || text.isEmpty()) {
            return text;
        }
        return ESC + code + "m" + text + RESET;
    }

    /** The same, in a named theme - used to preview themes in Settings. */
    public static String paintAs(String themeName, int role, String text) {
        int saved = current;
        current = indexOf(themeName);
        String out = paint(role, text);
        current = saved;
        return out;
    }

    public static boolean isColour() {
        return !THEMES[current][0].equals("PLAIN");
    }

    // ------------------------------------------------------------ choosing

    public static String current() {
        return THEMES[current][0];
    }

    /** The player's own choice: applied now, and remembered in the save. */
    public static void choose(String name) {
        if (indexOf(name) >= 0) {
            use(name);
            chosen = true;
        }
    }

    public static boolean isChosen() {
        return chosen;
    }

    /** Switches theme. Unknown names are ignored, so a bad save cannot break it. */
    public static void use(String name) {
        int found = indexOf(name);
        if (found >= 0) {
            current = found;
        }
    }

    public static String[] names() {
        String[] out = new String[THEMES.length];
        for (int i = 0; i < THEMES.length; i++) {
            out[i] = THEMES[i][0];
        }
        return out;
    }

    public static String describe(String name) {
        int i = indexOf(name);
        return i >= 0 ? THEMES[i][1] : "";
    }

    private static int indexOf(String name) {
        for (int i = 0; i < THEMES.length; i++) {
            if (THEMES[i][0].equalsIgnoreCase(name == null ? "" : name.trim())) {
                return i;
            }
        }
        return -1;
    }

    // ----------------------------------------------------------- detecting

    public static final int YES = 1;
    public static final int NO = 2;
    public static final int ASK = 3;

    /**
     * Can colour be switched on without asking?
     *
     * NO when output is not a screen (a script, a file, a pipe) or the player
     * has set NO_COLOR, the common convention for "never colour". YES where the
     * terminal is known to understand ANSI. ASK on a Windows console that
     * might not, because only the player can see what it looks like.
     */
    public static int colourSupport() {
        if (System.getenv("NO_COLOR") != null) {
            return NO;
        }
        if (!outputIsTerminal()) {
            return NO;
        }
        String os = System.getProperty("os.name", "").toLowerCase();
        if (!os.contains("win")) {
            String term = System.getenv("TERM");
            return (term == null || term.equals("dumb")) ? NO : YES;
        }
        if (System.getenv("WT_SESSION") != null            // Windows Terminal
                || System.getenv("TERM_PROGRAM") != null    // VS Code and others
                || "ON".equalsIgnoreCase(System.getenv("ConEmuANSI"))
                || System.getenv("ANSICON") != null) {
            return YES;
        }
        return ASK;
    }

    /**
     * Before Java 22, System.console() is null whenever output is redirected.
     * From Java 22 it is never null, and Console.isTerminal() answers instead.
     * Reflection lets one piece of code handle both.
     */
    public static boolean outputIsTerminal() {
        Console console = System.console();
        if (console == null) {
            return false;
        }
        try {
            Method isTerminal = Console.class.getMethod("isTerminal");
            return (Boolean) isTerminal.invoke(console);
        } catch (Exception e) {
            return true;    // older Java: a console exists, so it is a terminal
        }
    }

    /** A sample of green, raw, for the one-time "what do you see?" test. */
    public static String testSample() {
        return ESC + "1;32mGREEN" + RESET;
    }
}
