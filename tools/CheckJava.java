import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Compiles and runs the Java inside every mission, using the real JDK.
 *
 * CheckAll proves a mission is complete. This proves it is TRUE. A beginner
 * takes every example and every "this prints X" on trust, so a single wrong
 * claim teaches them something false and they will not know to doubt it.
 *
 *   example     must compile, and print exactly its stated output
 *   solution    must compile and run
 *   PREDICT     the snippet is run, and its output must be the first
 *               accepted answer (several output lines are joined by a space)
 *   DEBUG       the snippet must FAIL to compile, and the first error must be
 *               on the line the task says it is
 *
 * A PREDICT task that asks something other than "what is printed" - how many
 * lines, which character - is skipped by starting its prompt without "print".
 *
 * Slow on purpose: it starts the compiler and a JVM for every snippet. Run it
 * when you change mission content, not on every build.
 *
 *   javac -d check src/*.java tools/CheckJava.java
 *   java -cp check CheckJava            every built mission
 *   java -cp check CheckJava C01        missions whose id starts C01
 */
public class CheckJava {

    static int checks = 0;
    static int failures = 0;
    static Path work;

    /** Lines the wrapper puts above a snippet, so errors map to task lines. */
    static final int WRAP_OFFSET = 3;

    public static void main(String[] args) throws Exception {
        String only = args.length > 0 ? args[0] : "";
        work = Files.createTempDirectory("cyberops-check");

        for (Mission m : CampaignIndex.allMissions()) {
            if (!m.getId().startsWith(only)) {
                continue;
            }
            String id = m.getId();

            checkExample(m);

            for (Task t : m.allTasks()) {
                String tag = id + "/" + t.getType();

                if (t.hasSolution() && isProgram(t.getSolution())) {
                    Result sol = run(t.getSolution(), t.getInput());
                    check(tag + " solution compiles and runs",
                          sol.compiled && sol.ran, sol.errors);
                }

                if (t.getType().equals(Task.PREDICT) && t.getCode().length > 0) {
                    checkPredict(tag, t);
                }

                if (t.getType().equals(Task.DEBUG) && t.getCode().length > 0) {
                    boolean whole = isProgram(t.getCode());
                    boolean mixed = !whole && isMixed(t.getCode());
                    Result d = run(asProgram(t.getCode()), t.getInput());
                    if (d.compiled) {
                        check(tag + " snippet really fails to compile", false,
                              "it compiled");
                        continue;
                    }
                    int line = d.firstErrorLine - (whole ? 0 : WRAP_OFFSET);
                    if (mixed) {
                        int at = d.firstErrorLine - 1;
                        line = at >= 0 && at < origin.length ? origin[at] : -1;
                    }
                    check(tag + " error is on the stated line",
                          t.matches(String.valueOf(line)),
                          "javac reports line " + line + ", task expects "
                          + t.getFirstAccepted() + "\n        " + d.errors);
                }
            }
        }

        System.out.println();
        System.out.println(checks + " checks, " + failures + " failed");
        System.exit(failures > 0 ? 1 : 0);
    }

    /**
     * Not every example is a Java program. Some show a terminal session, some
     * show a compiler's error message, some are deliberately broken. Each is
     * checked for what it claims to be, and anything that cannot be checked
     * is listed rather than silently passed.
     */
    static void checkExample(Mission m) throws Exception {
        String id = m.getId();
        String[] code = m.getExample();
        String[] claimed = m.getExampleOutput();
        String first = claimed.length > 0 ? claimed[0] : "";

        if (code.length == 0) {
            return;
        }
        if (isShell(code) || code[0].startsWith("Main.java:")) {
            System.out.println("  skip: " + id + " example is not Java source");
            return;
        }

        String[] typed = m.getExampleInput();
        Result ex = run(asProgram(code), typed);

        if (first.startsWith("(") && first.contains("does not compile")) {
            check(id + " example really does not compile", !ex.compiled,
                  "it compiled");
            return;
        }
        check(id + " example compiles", ex.compiled, ex.errors);
        if (ex.compiled) {
            // The mission shows the screen, where typed text appears. The
            // program's own output does not contain it, so take it out.
            String want = rtrimLines(removeTyped(String.join("\n", claimed), typed));
            String got = rtrimLines(expandTabs(ex.output));
            check(id + " example prints what it claims", got.equals(want),
                  "\n      claims [" + want + "]\n      prints [" + got + "]");
        }
    }

    static void checkPredict(String tag, Task t) throws Exception {
        String[] code = t.getCode();
        String prompt = t.getPrompt().toLowerCase();
        Result p = run(asProgram(code), t.getInput());
        if (!p.compiled) {
            check(tag + " snippet compiles", false, p.errors);
            return;
        }
        if (prompt.contains("how many lines")) {
            String lines = String.valueOf(p.lineCount);
            check(tag + " line count is what Java prints", t.matches(lines),
                  "Java prints " + lines + " lines, task expects "
                  + t.getFirstAccepted());
            return;
        }
        if (!prompt.contains("print") && !prompt.contains("output")) {
            System.out.println("  skip: " + tag + " asks something other than "
                    + "the output");
            return;
        }
        String joined = p.output.trim().replaceAll("\\s*\\n\\s*", " ");
        check(tag + " answer is what Java prints", t.matches(joined),
              "Java prints [" + joined + "], task expects ["
              + t.getFirstAccepted() + "]");
    }

    /**
     * Each typed line ends where the person pressed ENTER, so it sits at the
     * end of a screen line. Removing it and its line break, in order, leaves
     * exactly what the program itself wrote.
     */
    static String removeTyped(String screen, String[] typed) {
        int from = 0;
        for (String line : typed) {
            int at = screen.indexOf(line + "\n", from);
            if (at < 0 && screen.endsWith(line)) {
                at = screen.length() - line.length();
            }
            if (at < 0) {
                return screen + "   (typed \"" + line + "\" is not on screen)";
            }
            int end = Math.min(screen.length(), at + line.length() + 1);
            screen = screen.substring(0, at) + screen.substring(end);
            from = at;
        }
        return screen;
    }

    /** Spaces at the end of a line are invisible on screen, so ignored. */
    static String rtrimLines(String text) {
        StringBuilder out = new StringBuilder();
        String[] lines = text.split("\n", -1);
        for (int i = 0; i < lines.length; i++) {
            out.append(i == 0 ? "" : "\n").append(lines[i].replaceAll("\\s+$", ""));
        }
        return out.toString();
    }

    static boolean isShell(String[] lines) {
        for (String line : lines) {
            String l = line.trim();
            if (l.startsWith("javac ") || l.startsWith("java ")) {
                return true;
            }
        }
        return false;
    }

    /** What a terminal shows for a tab: spaces up to the next 8-column stop. */
    static String expandTabs(String text) {
        StringBuilder out = new StringBuilder();
        int column = 0;
        for (char c : text.toCharArray()) {
            if (c == '\t') {
                do {
                    out.append(' ');
                    column++;
                } while (column % 8 != 0);
            } else {
                out.append(c);
                column = (c == '\n') ? 0 : column + 1;
            }
        }
        return out.toString();
    }

    static void check(String what, boolean ok, String detail) {
        checks++;
        if (!ok) {
            failures++;
            System.out.println("FAIL: " + what + "   " + detail);
        }
    }

    static boolean isProgram(String[] lines) {
        for (String line : lines) {
            if (line.contains("public class ") || line.startsWith("import ")) {
                return true;
            }
        }
        return false;
    }

    /**
     * From Campaign 06 on, a snippet can declare a small class of its own at
     * the left margin - "class Host { ... }" - beside the statements that use
     * it. The class goes into the file after Main, the statements into main.
     */
    static boolean isMixed(String[] snippet) {
        for (String line : snippet) {
            if (line.startsWith("class ")) {
                return true;
            }
        }
        return false;
    }

    /** For a mixed snippet: file line (index) to snippet line (1-based). */
    static int[] origin = new int[0];

    static String[] wrapMixed(String[] snippet) {
        List<String> file = new ArrayList<>();
        List<Integer> from = new ArrayList<>();
        List<Integer> statements = new ArrayList<>();
        List<Integer> classes = new ArrayList<>();
        int depth = 0;
        for (int i = 0; i < snippet.length; i++) {
            String line = snippet[i];
            if (depth == 0 && !line.startsWith("class ")) {
                statements.add(i);
                continue;
            }
            classes.add(i);
            depth += count(line, '{') - count(line, '}');
        }
        String[] body = new String[statements.size()];
        for (int k = 0; k < body.length; k++) {
            body[k] = snippet[statements.get(k)];
        }
        boolean classBody = isClassBody(body);
        file.add("import java.util.*;");
        from.add(0);
        file.add("public class Main {");
        from.add(0);
        if (!classBody) {
            file.add("    public static void main(String[] args) {");
            from.add(0);
        }
        for (int k : statements) {
            file.add((classBody ? "    " : "        ") + snippet[k]);
            from.add(k + 1);
        }
        if (!classBody) {
            file.add("    }");
            from.add(0);
        }
        file.add("}");
        from.add(0);
        for (int k : classes) {
            file.add(snippet[k]);
            from.add(k + 1);
        }
        origin = new int[from.size()];
        for (int k = 0; k < origin.length; k++) {
            origin[k] = from.get(k);
        }
        return file.toArray(new String[0]);
    }

    static int count(String text, char c) {
        int n = 0;
        for (char x : text.toCharArray()) {
            n += x == c ? 1 : 0;
        }
        return n;
    }

    /** Whole program, mixed snippet, or statements - whichever it is. */
    static String[] asProgram(String[] code) {
        if (isProgram(code)) {
            return code;
        }
        return isMixed(code) ? wrapMixed(code) : wrap(code);
    }

    /**
     * From Campaign 03 on, a snippet can show the inside of a class: methods
     * side by side, main among them. Those go straight into the class. Both
     * wrappers put three lines above the snippet, so WRAP_OFFSET holds.
     */
    static String[] wrap(String[] snippet) {
        List<String> out = new ArrayList<>();
        out.add("import java.util.Scanner;");
        out.add("public class Main {");
        if (isClassBody(snippet)) {
            out.add("");
            for (String line : snippet) {
                out.add("    " + line);
            }
        } else {
            out.add("    public static void main(String[] args) {");
            for (String line : snippet) {
                out.add("        " + line);
            }
            out.add("    }");
        }
        out.add("}");
        return out.toArray(new String[0]);
    }

    /** A declaration at the left margin: static, or public/private static. */
    static boolean isClassBody(String[] snippet) {
        for (String line : snippet) {
            if (line.startsWith("static ") || line.startsWith("public static ")
                    || line.startsWith("private static ")) {
                return true;
            }
        }
        return false;
    }

    static class Result {
        boolean compiled;
        boolean ran;
        String output = "";
        String errors = "";
        int firstErrorLine = -1;
        int lineCount;
    }

    static int counter = 0;

    static Result run(String[] program, String[] typed)
            throws IOException, InterruptedException {
        Result r = new Result();
        Path dir = work.resolve("p" + (counter++));
        Files.createDirectories(dir);
        Files.write(dir.resolve("Main.java"), List.of(program), StandardCharsets.UTF_8);

        Process javac = new ProcessBuilder("javac", "-d", ".", "Main.java")
                .directory(dir.toFile()).redirectErrorStream(true).start();
        String compileOut = read(javac);
        r.compiled = javac.waitFor() == 0;
        if (!r.compiled) {
            r.errors = firstLine(compileOut);
            java.util.regex.Matcher m = java.util.regex.Pattern
                    .compile("Main\\.java:(\\d+):").matcher(compileOut);
            if (m.find()) {
                r.firstErrorLine = Integer.parseInt(m.group(1));
            }
            return r;
        }

        Process java = new ProcessBuilder("java", "-cp", ".", "Main")
                .directory(dir.toFile()).redirectErrorStream(true).start();
        for (String line : typed) {
            java.getOutputStream().write((line + "\n").getBytes(StandardCharsets.UTF_8));
        }
        java.getOutputStream().close();
        if (!java.waitFor(20, TimeUnit.SECONDS)) {
            java.destroyForcibly();
            r.errors = "timed out";
            return r;
        }
        String out = stripToolNoise(read(java));
        r.ran = java.exitValue() == 0;
        // Every println ends a line. Output that stops mid-line is one more.
        int breaks = out.length() - out.replace("\n", "").length();
        r.lineCount = breaks + (out.isEmpty() || out.endsWith("\n") ? 0 : 1);
        r.output = out.replaceAll("\\s+$", "");
        if (!r.ran) {
            r.errors = firstLine(out);
        }
        return r;
    }

    static String read(Process p) throws IOException {
        return new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    /** Some environments make the JVM announce its options. Not our output. */
    static String stripToolNoise(String text) {
        StringBuilder kept = new StringBuilder();
        boolean first = true;
        for (String line : text.split("\n", -1)) {
            if (line.startsWith("Picked up JAVA_TOOL_OPTIONS")
                    || line.startsWith("Picked up _JAVA_OPTIONS")) {
                continue;
            }
            if (!first) {
                kept.append("\n");
            }
            first = false;
            kept.append(line.replace("\r", ""));
        }
        return kept.toString();
    }

    static String firstLine(String text) {
        for (String line : stripToolNoise(text).split("\n")) {
            if (!line.isBlank()) {
                return line.trim();
            }
        }
        return "";
    }
}
