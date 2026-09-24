import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

/**
 * Proves every lab can be done, using the same LabBench that judges the
 * learner.
 *
 *   reference solution   must compile and pass EVERY test
 *   starter              must compile (unless the lab is about fixing it,
 *                        in which case it must NOT compile), and must fail
 *                        at least one test - a starter that already passes
 *                        teaches nothing
 *   hidden tests         a program that prints the first sample's answer
 *                        word for word must fail at least one test, so
 *                        hard-coding the sample never passes a lab that
 *                        reads input
 *
 *   javac -d check src/*.java tools/*.java
 *   java -cp check CheckLabs            every lab
 *   java -cp check CheckLabs C01        one campaign
 */
public class CheckLabs {

    static int checks = 0;
    static int failures = 0;

    public static void main(String[] args) throws IOException {
        String only = args.length > 0 ? args[0] : "";
        File work = Files.createTempDirectory("cyberops-labs").toFile();

        for (Lab lab : CampaignIndex.allLabs()) {
            if (!lab.getId().startsWith(only)) {
                continue;
            }
            String id = lab.getId();

            // --- the reference solution passes everything -----------------
            File solution = new File(work, id + "-solution");
            write(solution, lab.getSolution());
            LabBench.Compiled built = LabBench.compile(solution);
            check(id + " solution compiles", built.ok, built.messages);
            if (built.ok) {
                List<LabBench.Result> results = LabBench.runTests(lab, solution);
                for (int i = 0; i < results.size(); i++) {
                    LabBench.Result r = results.get(i);
                    check(id + " solution passes test " + (i + 1), r.passed,
                          describe(r));
                }
            }

            // --- the starter is a start, not an answer --------------------
            File starter = new File(work, id + "-starter");
            write(starter, lab.getStarter());
            LabBench.Compiled started = LabBench.compile(starter);
            if (lab.hasBrokenStarter()) {
                check(id + " broken starter really does not compile", !started.ok,
                      "it compiles");
            } else {
                check(id + " starter compiles", started.ok, started.messages);
                if (started.ok) {
                    check(id + " starter does not already pass",
                          !allPass(LabBench.runTests(lab, starter)), "it passes");
                }
            }

            // --- hard-coding the sample must not pass ---------------------
            if (lab.readsInput()) {
                File cheat = new File(work, id + "-hardcoded");
                write(cheat, hardCoded(lab));
                LabBench.Compiled cheatBuilt = LabBench.compile(cheat);
                if (cheatBuilt.ok) {
                    check(id + " hard-coding the sample fails a hidden test",
                          !allPass(LabBench.runTests(lab, cheat)), "it passes");
                }
            }
        }

        LabBench.deleteTree(work);
        System.out.println();
        System.out.println(checks + " checks, " + failures + " failed");
        System.exit(failures > 0 ? 1 : 0);
    }

    static void check(String what, boolean ok, String detail) {
        checks++;
        if (!ok) {
            failures++;
            System.out.println("FAIL: " + what + "\n      " + detail.replace("\n", "\n      "));
        }
    }

    static boolean allPass(List<LabBench.Result> results) {
        for (LabBench.Result r : results) {
            if (!r.passed) {
                return false;
            }
        }
        return true;
    }

    static String describe(LabBench.Result r) {
        return (r.problem.isEmpty() ? "" : r.problem + "\n")
                + "expected:\n" + String.join("\n", r.test.getScreen())
                + "\ngot:\n" + r.screen;
    }

    /**
     * The laziest cheat: read the input so the echoes match, then print the
     * first sample's output lines word for word.
     */
    static String[] hardCoded(Lab lab) {
        LabTest sample = lab.getTests().get(0);
        StringBuilder body = new StringBuilder();
        body.append("import java.util.Scanner;\n");
        body.append("public class Main {\n");
        body.append("    public static void main(String[] args) {\n");
        body.append("        Scanner in = new Scanner(System.in);\n");
        body.append("        while (in.hasNextLine()) { in.nextLine(); }\n");
        for (String line : sample.getScreen()) {
            boolean typed = false;
            for (String input : sample.getInput()) {
                typed = typed || line.endsWith(input);
            }
            if (!typed) {
                body.append("        System.out.println(\"")
                    .append(line.replace("\\", "\\\\").replace("\"", "\\\""))
                    .append("\");\n");
            }
        }
        body.append("    }\n}\n");
        return body.toString().split("\n");
    }

    static void write(File folder, String[] lines) throws IOException {
        LabBench.deleteTree(folder);
        folder.mkdirs();
        try (PrintWriter out = new PrintWriter(new FileWriter(new File(folder, "Main.java")))) {
            for (String line : lines) {
                out.println(line);
            }
        }
    }
}
