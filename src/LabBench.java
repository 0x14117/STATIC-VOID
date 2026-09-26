import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Compiles and tests a learner's lab program with the real JDK.
 *
 * The game and tools/CheckLabs both use this class, so a reference solution
 * is judged by exactly the same code that judges the learner.
 */
public class LabBench {

    /** Longer than any honest lab needs; short enough to catch a hang. */
    public static final int RUN_SECONDS = 5;

    /** Stops a program that prints forever from eating all the memory. */
    private static final int MAX_OUTPUT = 20000;

    public static class Compiled {
        public boolean ok;
        public String messages = "";
    }

    public static class Result {
        public LabTest test;
        public boolean passed;
        public String screen = "";
        public String problem = "";
        public int firstDifference = -1;
    }

    // ------------------------------------------------------------ locations

    public static File folderFor(Lab lab) {
        return new File("labs", lab.getId());
    }

    public static File sourceFor(Lab lab) {
        return new File(folderFor(lab), "Main.java");
    }

    // ------------------------------------------------------------ compiling

    /** Compiles every .java file in the folder into folder/build. */
    public static Compiled compile(File folder) {
        Compiled result = new Compiled();
        File build = new File(folder, "build");
        deleteTree(build);
        build.mkdirs();

        List<String> command = new ArrayList<>();
        command.add("javac");
        command.add("-d");
        command.add(build.getAbsolutePath());
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".java")) {
                    command.add(file.getAbsolutePath());
                }
            }
        }
        if (command.size() == 3) {
            result.messages = "There is no .java file in " + folder.getPath() + ".";
            return result;
        }

        try {
            Process javac = new ProcessBuilder(command).redirectErrorStream(true).start();
            javac.getOutputStream().close();
            String output = readAll(javac.getInputStream(), MAX_OUTPUT);
            javac.waitFor();
            result.ok = javac.exitValue() == 0;
            result.messages = tidyCompilerOutput(output, folder);
        } catch (IOException e) {
            result.messages = "Could not start javac. Is a JDK installed? ("
                    + e.getMessage() + ")";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            result.messages = "Compiling was interrupted.";
        }
        return result;
    }

    // -------------------------------------------------------------- testing

    public static List<Result> runTests(Lab lab, File folder) {
        List<Result> results = new ArrayList<>();
        for (LabTest test : lab.getTests()) {
            results.add(runTest(test, folder));
        }
        return results;
    }

    public static Result runTest(LabTest test, File folder) {
        Result result = new Result();
        result.test = test;

        File build = new File(folder, "build");
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("-cp");
        command.add(build.getAbsolutePath());
        command.add("LabEcho");
        command.add("Main");

        try {
            installHarness(build);
            Process run = new ProcessBuilder(command).redirectErrorStream(true).start();

            OutputStream keyboard = run.getOutputStream();
            for (String line : test.getInput()) {
                keyboard.write((line + "\n").getBytes(StandardCharsets.UTF_8));
            }
            keyboard.close();

            OutputReader reader = new OutputReader(run.getInputStream());
            reader.start();
            boolean finished = run.waitFor(RUN_SECONDS, TimeUnit.SECONDS);
            if (!finished) {
                run.destroyForcibly();
                run.waitFor(2, TimeUnit.SECONDS);
            }
            reader.join(2000);
            result.screen = stripJvmNoise(reader.text());

            if (!finished) {
                result.problem = "Your program was still running after " + RUN_SECONDS
                        + " seconds, so it was stopped. Is it waiting for input the "
                        + "test does not give, or stuck repeating something?";
                return result;
            }
            if (result.screen.contains("Exception in thread \"main\"")) {
                result.problem = "Your program crashed. The error is at the end of "
                        + "its output below.";
            }
        } catch (IOException e) {
            result.problem = "Could not start java: " + e.getMessage();
            return result;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            result.problem = "Testing was interrupted.";
            return result;
        }

        List<String> expected = normalise(String.join("\n", test.getScreen()));
        List<String> actual = normalise(result.screen);
        result.firstDifference = firstDifference(expected, actual);
        result.passed = result.firstDifference < 0 && result.problem.isEmpty();
        return result;
    }

    /**
     * The lab's needed methods that the compiled program does not declare,
     * each as the lab wrote it. They are read from the class file; nothing
     * in the learner's program runs.
     */
    public static List<String> missingMethods(Lab lab, File folder) {
        List<String> missing = new ArrayList<>();
        if (lab.getNeededMethods().isEmpty()) {
            return missing;
        }
        List<String> declared = new ArrayList<>();
        File build = new File(folder, "build");
        try (URLClassLoader loader = new URLClassLoader(
                new URL[]{build.toURI().toURL()}, null)) {
            Class<?> main = Class.forName("Main", false, loader);
            for (Method method : main.getDeclaredMethods()) {
                declared.add(signatureOf(method));
            }
        } catch (Exception | LinkageError e) {
            // Unreadable: every needed method counts as missing.
        }
        for (String needed : lab.getNeededMethods()) {
            if (!declared.contains(withoutTypeArguments(tidySignature(needed)))) {
                missing.add(needed);
            }
        }
        return missing;
    }

    /**
     * The lab's forbidden fragments that appear in the learner's source,
     * outside comments. Comments are ignored so that notes like
     * "// should print 38" never count against anyone.
     */
    public static List<String> forbiddenFound(Lab lab, File folder) {
        List<String> found = new ArrayList<>();
        if (lab.getForbidden().isEmpty()) {
            return found;
        }
        String code = "";
        try {
            code = new String(java.nio.file.Files.readAllBytes(
                    new File(folder, "Main.java").toPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return found;
        }
        code = withoutComments(code);
        for (String fragment : lab.getForbidden()) {
            if (code.contains(fragment)) {
                found.add(fragment);
            }
        }
        return found;
    }

    /** Source with block comments and // comments removed; strings kept. */
    static String withoutComments(String code) {
        StringBuilder out = new StringBuilder();
        boolean inString = false;
        boolean inChar = false;
        int i = 0;
        while (i < code.length()) {
            char c = code.charAt(i);
            char next = i + 1 < code.length() ? code.charAt(i + 1) : ' ';
            if (!inString && !inChar && c == '/' && next == '*') {
                int end = code.indexOf("*/", i + 2);
                i = end < 0 ? code.length() : end + 2;
                continue;
            }
            if (!inString && !inChar && c == '/' && next == '/') {
                while (i < code.length() && code.charAt(i) != '\n') {
                    i++;
                }
                continue;
            }
            if (c == '\\' && (inString || inChar) && i + 1 < code.length()) {
                out.append(c).append(next);
                i += 2;
                continue;
            }
            if (c == '"' && !inChar) {
                inString = !inString;
            } else if (c == '\'' && !inString) {
                inChar = !inChar;
            }
            out.append(c);
            i++;
        }
        return out.toString();
    }

    /** "static boolean isValidPort(String)" for a compiled method. */
    static String signatureOf(Method method) {
        StringBuilder text = new StringBuilder();
        if (Modifier.isStatic(method.getModifiers())) {
            text.append("static ");
        }
        text.append(method.getReturnType().getSimpleName()).append(' ')
            .append(method.getName()).append('(');
        Class<?>[] types = method.getParameterTypes();
        for (int i = 0; i < types.length; i++) {
            text.append(i == 0 ? "" : ", ").append(types[i].getSimpleName());
        }
        return text.append(')').toString();
    }

    /**
     * Reflection sees ArrayList, never ArrayList<String>: Java erases type
     * arguments when it compiles. The brief shows them, because they tell
     * the learner what to write; the check compares without them.
     */
    static String withoutTypeArguments(String signature) {
        String before;
        do {
            before = signature;
            signature = signature.replaceAll("<[^<>]*>", "");
        } while (!signature.equals(before));
        return signature;
    }

    /** One space between words, ", " between parameters, none inside (). */
    static String tidySignature(String signature) {
        return signature.trim().replaceAll("\\s+", " ")
                .replaceAll("\\s*,\\s*", ", ")
                .replaceAll("\\s*\\(\\s*", "(")
                .replaceAll("\\s*\\)", ")");
    }

    /**
     * Compared line by line, exactly - capitals, spelling and spacing inside
     * a line all count. Three things do not: tabs are expanded the way a
     * terminal shows them, spaces at the END of a line are invisible and so
     * ignored, and so are blank lines after the last one.
     */
    public static List<String> normalise(String text) {
        List<String> lines = new ArrayList<>();
        for (String line : text.replace("\r", "").split("\n", -1)) {
            lines.add(rtrim(expandTabs(line)));
        }
        while (!lines.isEmpty() && lines.get(lines.size() - 1).isEmpty()) {
            lines.remove(lines.size() - 1);
        }
        return lines;
    }

    /** Index of the first line that differs, or -1 when they match. */
    public static int firstDifference(List<String> expected, List<String> actual) {
        int longest = Math.max(expected.size(), actual.size());
        for (int i = 0; i < longest; i++) {
            String want = i < expected.size() ? expected.get(i) : null;
            String got = i < actual.size() ? actual.get(i) : null;
            if (want == null || got == null || !want.equals(got)) {
                return i;
            }
        }
        return -1;
    }

    // ------------------------------------------------------------- helpers

    /**
     * Copies LabEcho into the learner's build folder, so the program runs
     * with ONLY its own classes and the harness on the classpath. With the
     * game's classes there too, a learner whose class was misspelt would
     * have the game's own Main run instead.
     */
    private static void installHarness(File build) throws IOException {
        String[] parts = {"LabEcho.class", "LabEcho$EchoingInput.class"};
        for (String part : parts) {
            InputStream in = LabBench.class.getResourceAsStream("/" + part);
            if (in == null) {
                throw new IOException("the game's " + part + " is missing - recompile the game");
            }
            try (InputStream source = in;
                 OutputStream out = new java.io.FileOutputStream(new File(build, part))) {
                byte[] buffer = new byte[4096];
                int count = source.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = source.read(buffer);
                }
            }
        }
    }

    /** Absolute paths mean nothing to the learner. Show labs/C01-L05/Main.java. */
    private static String tidyCompilerOutput(String output, File folder) {
        String absolute = folder.getAbsolutePath() + File.separator;
        String shown = folder.getPath().replace('\\', '/') + "/";
        return stripJvmNoise(output.replace(absolute, shown)).trim();
    }

    private static String stripJvmNoise(String text) {
        StringBuilder kept = new StringBuilder();
        boolean first = true;
        for (String line : text.replace("\r", "").split("\n", -1)) {
            if (line.startsWith("Picked up JAVA_TOOL_OPTIONS")
                    || line.startsWith("Picked up _JAVA_OPTIONS")) {
                continue;
            }
            if (!first) {
                kept.append('\n');
            }
            kept.append(line);
            first = false;
        }
        return kept.toString();
    }

    private static String expandTabs(String line) {
        StringBuilder out = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '\t') {
                do {
                    out.append(' ');
                } while (out.length() % 8 != 0);
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    private static String rtrim(String line) {
        int end = line.length();
        while (end > 0 && line.charAt(end - 1) == ' ') {
            end--;
        }
        return line.substring(0, end);
    }

    private static String readAll(InputStream in, int limit) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int count = in.read(buffer);
        while (count != -1) {
            if (out.size() < limit) {
                out.write(buffer, 0, Math.min(count, limit - out.size()));
            }
            count = in.read(buffer);
        }
        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }

    public static void deleteTree(File file) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                deleteTree(child);
            }
        }
        file.delete();
    }

    /**
     * Reads the program's output on its own thread. Without this, a program
     * that prints a lot fills the pipe, blocks, and looks like a hang. What
     * was read is kept even if the program is stopped part way, because that
     * partial output is exactly what the learner needs to see.
     */
    private static class OutputReader extends Thread {

        private final InputStream in;
        private final ByteArrayOutputStream collected = new ByteArrayOutputStream();

        OutputReader(InputStream in) {
            this.in = in;
            setDaemon(true);
        }

        @Override
        public void run() {
            byte[] buffer = new byte[4096];
            try {
                int count = in.read(buffer);
                while (count != -1) {
                    synchronized (collected) {
                        if (collected.size() < MAX_OUTPUT) {
                            collected.write(buffer, 0,
                                    Math.min(count, MAX_OUTPUT - collected.size()));
                        }
                    }
                    count = in.read(buffer);
                }
            } catch (IOException e) {
                // The process was stopped. Whatever was read is kept.
            }
        }

        String text() {
            synchronized (collected) {
                return new String(collected.toByteArray(), StandardCharsets.UTF_8);
            }
        }
    }
}
