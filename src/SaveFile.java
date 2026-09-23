import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Loads and saves progress as one small text file.
 *
 * Deliberately plain text rather than anything clever: you can open it, read
 * it, and see exactly what the game is remembering about you.
 */
public class SaveFile {

    private static final String FILE_NAME = "cyberops-save.txt";

    public static void save(Player player) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(FILE_NAME))) {
            out.write("name=" + player.getName());
            out.newLine();
            out.write("xp=" + player.getXp());
            out.newLine();
            out.write("hints=" + player.getHintsUsed());
            out.newLine();
            out.write("wrong=" + player.getWrongAnswers());
            out.newLine();
            out.write("completed=" + String.join(",", player.getCompletedMissions()));
            out.newLine();
            out.write("topics=" + String.join(",", player.getLearnedTopics()));
            out.newLine();
        } catch (IOException e) {
            Terminal.line("  Could not save progress: " + e.getMessage());
        }
    }

    /** Returns the saved player, or null when there is no save file yet. */
    public static Player load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return null;
        }

        String name = "analyst";
        int xp = 0;
        int hints = 0;
        int wrong = 0;
        String completed = "";
        String topics = "";

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line = in.readLine();
            while (line != null) {
                int split = line.indexOf('=');
                if (split > 0) {
                    String key = line.substring(0, split);
                    String value = line.substring(split + 1);
                    if (key.equals("name")) {
                        name = value;
                    } else if (key.equals("xp")) {
                        xp = readNumber(value);
                    } else if (key.equals("hints")) {
                        hints = readNumber(value);
                    } else if (key.equals("wrong")) {
                        wrong = readNumber(value);
                    } else if (key.equals("completed")) {
                        completed = value;
                    } else if (key.equals("topics")) {
                        topics = value;
                    }
                }
                line = in.readLine();
            }
        } catch (IOException e) {
            Terminal.line("  Could not read the save file, starting fresh.");
            return null;
        }

        Player player = new Player(name);
        player.restore(xp, hints, wrong);
        for (String id : completed.split(",")) {
            if (!id.isEmpty()) {
                player.markCompleted(id);
            }
        }
        for (String topic : topics.split(",")) {
            if (!topic.isEmpty()) {
                player.learn(topic);
            }
        }
        return player;
    }

    public static void delete() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * A save file can be edited by hand, so a number in it might not be a
     * number. Refusing to start over one bad character would be worse than
     * treating it as zero.
     */
    private static int readNumber(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
