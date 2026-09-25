import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Labs section: every screen from the campaign list down to a single
 * lab, and the actions on it - create the file, test, run, hint, solution.
 */
public class LabDesk {

    /** Each hint taken costs this much of the lab's XP. */
    public static final int HINT_COST = 5;

    /** Hints shown this session, per lab. */
    private static final Map<String, Integer> HINTS_SHOWN = new HashMap<>();

    // ------------------------------------------------------ campaign list

    public static void show(Player player) {
        while (true) {
            Terminal.newScreen();
            Terminal.rule('+', '=');
            Terminal.centred("LABS");
            Terminal.rule('+', '=');
            Terminal.blank();
            Terminal.wrapped("Missions teach one idea at a time. Labs are where you "
                    + "write whole programs yourself, in your own editor, and the "
                    + "game compiles and tests them.", "  ");
            Terminal.blank();

            int shown = 0;
            for (Campaign campaign : CampaignIndex.all()) {
                if (campaign.getLabs().isEmpty()) {
                    continue;
                }
                shown = shown + 1;
                int core = 0;
                int coreDone = 0;
                for (Lab lab : campaign.getLabs()) {
                    if (lab.isCore()) {
                        core = core + 1;
                        if (player.hasPassedLab(lab.getId())) {
                            coreDone = coreDone + 1;
                        }
                    }
                }
                String number = String.format("%02d", campaign.getNumber());
                Terminal.line("   " + Theme.paint(Theme.ACCENT, number + ".") + " "
                        + Terminal.pad(campaign.getLabel(), 28)
                        + campaign.labsPassedBy(player) + " of "
                        + campaign.getLabs().size() + " passed"
                        + Theme.paint(Theme.MUTED, "  (core " + coreDone + "/" + core + ")"));
            }
            if (shown == 0) {
                Terminal.line("  No labs yet.");
            }

            Terminal.blank();
            Terminal.line("   " + Theme.paint(Theme.ACCENT, "?.") + " How labs work");
            Terminal.blank();
            String choice = Terminal.ask("  Campaign number, a lab ID like C00-L01, "
                    + "or ENTER to go back: ");
            if (choice.isEmpty() || choice.equalsIgnoreCase("exit")) {
                return;
            }
            if (choice.equals("?")) {
                explain();
                continue;
            }
            Lab direct = CampaignIndex.labById(choice);
            if (direct != null) {
                openLab(direct, player);
                continue;
            }
            Campaign chosen = null;
            try {
                chosen = CampaignIndex.byNumber(Integer.parseInt(choice.trim()));
            } catch (NumberFormatException e) {
                chosen = null;
            }
            if (chosen == null || chosen.getLabs().isEmpty()) {
                Terminal.lineAs(Theme.WARN, "  No labs there.");
                Terminal.pause();
                continue;
            }
            listLabs(chosen, player);
        }
    }

    private static void explain() {
        Terminal.newScreen();
        Terminal.heading("HOW LABS WORK");
        Terminal.blank();
        Terminal.teachingText(
                "1. Open a lab and read the brief, the specification and the "
                + "sample run.\n"
                + "\n"
                + "2. Choose CREATE. The game writes a starter file for you:\n"
                + "\n"
                + "       labs/C00-L01/Main.java\n"
                + "\n"
                + "   Open it in any editor - Notepad, VS Code, IntelliJ - and "
                + "write your program. Keep the class called Main.\n"
                + "\n"
                + "3. Choose TEST. The game compiles your file with the real "
                + "Java compiler and runs it against several tests. Some tests "
                + "are the sample runs; some are hidden and use different "
                + "values, so a program that only prints the expected answer "
                + "will not pass.\n"
                + "\n"
                + "4. If a test fails you see the input, what was expected, what "
                + "your program printed, and the first line that differs. Fix "
                + "it and test again, as often as you like. Testing is free.\n"
                + "\n"
                + "5. RUN starts your program so you can try it yourself.\n"
                + "\n"
                + "6. HINT gives the next hint, each more specific than the last, "
                + "for " + HINT_COST + " XP each. SOLUTION shows a complete "
                + "program and why it works; passing after that earns a quarter "
                + "of the XP.\n"
                + "\n"
                + "Output is compared exactly: capitals, spelling and spaces "
                + "inside a line all count. Spaces at the end of a line and "
                + "blank lines at the very end do not.\n"
                + "\n"
                + "CORE labs are the path through a campaign. STRETCH labs are "
                + "extra practice for when something has not stuck.", "  ");
        Terminal.pause();
    }

    // ----------------------------------------------------------- lab list

    private static void listLabs(Campaign campaign, Player player) {
        while (true) {
            Terminal.newScreen();
            Terminal.heading(campaign.getLabel() + " : LABS");
            Terminal.blank();
            for (Lab lab : campaign.getLabs()) {
                String tick = player.hasPassedLab(lab.getId())
                        ? Theme.paint(Theme.GOOD, "[x]") : "[ ]";
                String tier = lab.isCore() ? Theme.paint(Theme.ACCENT, "CORE   ")
                        : Theme.paint(Theme.MUTED, "STRETCH");
                Terminal.line("  " + tick + " " + Terminal.pad(lab.getId(), 9)
                        + Terminal.pad(lab.getTitle(), 26) + Terminal.pad(lab.getSize(), 9)
                        + tier);
            }
            Terminal.blank();
            String choice = Terminal.ask("  Lab ID or its number (1, 2...), or ENTER "
                    + "to go back: ");
            if (choice.isEmpty() || choice.equalsIgnoreCase("exit")) {
                return;
            }
            Lab lab = CampaignIndex.labById(choice);
            if (lab == null) {
                try {
                    int index = Integer.parseInt(choice.trim()) - 1;
                    if (index >= 0 && index < campaign.getLabs().size()) {
                        lab = campaign.getLabs().get(index);
                    }
                } catch (NumberFormatException e) {
                    lab = null;
                }
            }
            if (lab == null) {
                Terminal.lineAs(Theme.WARN, "  No lab by that name.");
                Terminal.pause();
                continue;
            }
            openLab(lab, player);
        }
    }

    // ------------------------------------------------------------ one lab

    public static void openLab(Lab lab, Player player) {
        showBrief(lab, player);
        while (true) {
            File source = LabBench.sourceFor(lab);
            int hintsLeft = lab.getHints().length - hintsShown(lab);

            Terminal.blank();
            Terminal.rule('+', '-');
            Terminal.line("  " + Theme.paint(Theme.ACCENT, lab.getId()) + "  "
                    + lab.getTitle() + "   "
                    + (player.hasPassedLab(lab.getId())
                       ? Theme.paint(Theme.GOOD, "PASSED") : "not passed yet"));
            Terminal.line("  Your file: " + source.getPath().replace('\\', '/')
                    + (source.exists() ? "" : Theme.paint(Theme.MUTED, "  (not created yet)")));
            Terminal.blank();
            option("1", source.exists() ? "Reset my file to the starter" : "Create my starter file");
            option("2", "Test my program");
            option("3", "Run my program myself");
            option("4", "Hint" + (hintsLeft > 0 ? "  (" + hintsLeft + " left, "
                    + HINT_COST + " XP each)" : "  (all shown)"));
            option("5", "Show the solution");
            option("6", "Read the brief again");
            option("7", "Back");
            Terminal.blank();

            String choice = Terminal.ask("  Select: ");
            if (choice.equals("1")) {
                createStarter(lab, source.exists());
            } else if (choice.equals("2")) {
                test(lab, player);
            } else if (choice.equals("3")) {
                runYourself(lab);
            } else if (choice.equals("4")) {
                hint(lab);
            } else if (choice.equals("5")) {
                solution(lab, player);
            } else if (choice.equals("6")) {
                showBrief(lab, player);
            } else if (choice.equals("7") || choice.isEmpty()
                    || choice.equalsIgnoreCase("exit")) {
                return;
            }
        }
    }

    private static void option(String key, String text) {
        Terminal.line("   " + Theme.paint(Theme.ACCENT, key + ".") + " " + text);
    }

    private static void showBrief(Lab lab, Player player) {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred(lab.getId() + "  " + lab.getTitle().toUpperCase());
        Terminal.rule('+', '=');
        Terminal.blank();
        Terminal.line("  " + lab.getSize() + " lab   "
                + (lab.isCore() ? "CORE" : "STRETCH") + "   about "
                + lab.getTimeEstimate() + "   " + lab.getXp() + " XP");

        Mission basis = CampaignIndex.byId(lab.getAfter());
        if (basis != null) {
            String done = player.hasCompleted(basis.getId())
                    ? Theme.paint(Theme.GOOD, "done")
                    : Theme.paint(Theme.WARN, "not done yet - worth doing first");
            Terminal.line("  Builds on " + basis.getId() + " " + basis.getTitle());
            Terminal.line("  (" + done + ")");
        }

        Terminal.blank();
        Terminal.heading("BRIEF");
        Terminal.blank();
        Terminal.teachingText(lab.getBrief(), "  ");

        Terminal.blank();
        Terminal.heading("YOU WILL PRACTISE");
        Terminal.blank();
        for (String concept : lab.getPractises()) {
            Terminal.line("  - " + concept);
        }

        Terminal.blank();
        Terminal.heading("SPECIFICATION");
        Terminal.blank();
        String[] spec = lab.getSpec();
        for (int i = 0; i < spec.length; i++) {
            Terminal.inline(" " + Theme.paint(Theme.ACCENT, String.format("%3d.", i + 1)) + " ");
            Terminal.wrappedAfterPrefix(spec[i], "      ", 6);
        }

        if (!lab.getNeededMethods().isEmpty()) {
            Terminal.blank();
            Terminal.heading("METHODS YOUR PROGRAM MUST DECLARE");
            Terminal.blank();
            for (String method : lab.getNeededMethods()) {
                Terminal.lineAs(Theme.CODE, "    " + method);
            }
            Terminal.blank();
            Terminal.wrapped("The tests check these exist, with exactly these "
                    + "return and parameter types. Parameter names are yours "
                    + "to choose.", "  ");
        }

        List<LabTest> tests = lab.getTests();
        int sampleNumber = 0;
        for (LabTest test : tests) {
            if (test.isHidden()) {
                continue;
            }
            sampleNumber = sampleNumber + 1;
            Terminal.blank();
            Terminal.heading("SAMPLE RUN " + sampleNumber);
            Terminal.blank();
            if (test.getInput().length > 0) {
                Terminal.wrapped("The person types "
                        + String.join(", then ", test.getInput())
                        + ", pressing ENTER each time. The screen shows:", "  ");
                Terminal.blank();
            }
            for (String line : test.getScreen()) {
                Terminal.lineAs(Theme.CODE, "      " + line);
            }
        }
        int hidden = tests.size() - sampleNumber;
        if (hidden > 0) {
            Terminal.blank();
            Terminal.lineAs(Theme.MUTED, "  Plus " + hidden + " hidden test"
                    + (hidden == 1 ? "" : "s") + " with different values.");
        }
        Terminal.pause();
    }

    // -------------------------------------------------------------- actions

    private static void createStarter(Lab lab, boolean exists) {
        File source = LabBench.sourceFor(lab);
        if (exists) {
            Terminal.blank();
            Terminal.wrappedAs(Theme.WARN, "This replaces your file with the "
                    + "original starter. Your work in it will be lost.", "  ");
            String confirm = Terminal.ask("  Type RESET to confirm: ");
            if (!confirm.equals("RESET")) {
                Terminal.line("  Kept your file.");
                Terminal.pause();
                return;
            }
        }
        source.getParentFile().mkdirs();
        try (PrintWriter out = new PrintWriter(new FileWriter(source))) {
            for (String line : lab.getStarter()) {
                out.println(line);
            }
        } catch (IOException e) {
            Terminal.lineAs(Theme.BAD, "  Could not write the file: " + e.getMessage());
            Terminal.pause();
            return;
        }
        Terminal.blank();
        Terminal.lineAs(Theme.GOOD, "  Starter file written.");
        Terminal.blank();
        Terminal.line("  Open this file in your editor:");
        Terminal.blank();
        Terminal.lineAs(Theme.ACCENT, "      " + source.getAbsolutePath());
        Terminal.blank();
        Terminal.wrapped("Write your program there and save it. Then come back and "
                + "choose Test.", "  ");
        Terminal.pause();
    }

    private static boolean compileOrExplain(Lab lab) {
        File source = LabBench.sourceFor(lab);
        if (!source.exists()) {
            Terminal.blank();
            Terminal.lineAs(Theme.WARN, "  There is no file yet. Choose 1 to create "
                    + "the starter first.");
            Terminal.pause();
            return false;
        }
        Terminal.blank();
        Terminal.line("  Compiling " + source.getPath().replace('\\', '/') + " ...");
        LabBench.Compiled compiled = LabBench.compile(source.getParentFile());
        if (compiled.ok) {
            return true;
        }
        Terminal.blank();
        Terminal.lineAs(Theme.BAD, "  It does not compile yet. The compiler says:");
        Terminal.blank();
        String[] lines = compiled.messages.split("\n");
        for (int i = 0; i < lines.length && i < 24; i++) {
            Terminal.lineAs(Theme.CODE, "    " + lines[i]);
        }
        Terminal.blank();
        Terminal.wrapped("Start with the FIRST error. The number after Main.java: is "
                + "the line to look at, and the caret ^ points at where Java got "
                + "confused. Fix one thing, save, and test again.", "  ");
        Terminal.pause();
        return false;
    }

    private static void test(Lab lab, Player player) {
        if (!compileOrExplain(lab)) {
            return;
        }
        List<String> missing = LabBench.missingMethods(lab, LabBench.folderFor(lab));
        if (!missing.isEmpty()) {
            Terminal.lineAs(Theme.GOOD, "  Compiled.");
            Terminal.blank();
            Terminal.lineAs(Theme.BAD, "  This lab needs methods your program does "
                    + "not declare:");
            Terminal.blank();
            for (String method : missing) {
                Terminal.lineAs(Theme.CODE, "    " + method);
            }
            Terminal.blank();
            Terminal.wrapped("Check each one's name, its return type, its parameter "
                    + "types and their order, and that it is static. Then test "
                    + "again.", "  ");
            Terminal.pause();
            return;
        }
        List<String> typed = LabBench.forbiddenFound(lab, LabBench.folderFor(lab));
        if (!typed.isEmpty()) {
            Terminal.lineAs(Theme.GOOD, "  Compiled.");
            Terminal.blank();
            Terminal.wrappedAs(Theme.BAD, "This lab asks your program to WORK OUT "
                    + "its answers, but these appear in your code as typed "
                    + "text: " + String.join("  ", typed), "  ");
            Terminal.blank();
            Terminal.wrapped("Calculate them from the variables instead - that "
                    + "is the skill the lab is practising. (Comments do not "
                    + "count, so notes about the answer are fine.)", "  ");
            Terminal.pause();
            return;
        }
        List<LabBench.Result> results = LabBench.runTests(lab, LabBench.folderFor(lab));
        Terminal.lineAs(Theme.GOOD, "  Compiled.");
        Terminal.blank();

        LabBench.Result firstFailure = null;
        int passed = 0;
        int number = 0;
        for (LabBench.Result result : results) {
            number = number + 1;
            String kind = result.test.isHidden() ? "hidden" : "sample";
            String verdict = result.passed ? Theme.paint(Theme.GOOD, "PASS")
                                           : Theme.paint(Theme.BAD, "FAIL");
            Terminal.line("  Test " + number + " (" + kind + ")   " + verdict);
            if (result.passed) {
                passed = passed + 1;
            } else if (firstFailure == null) {
                firstFailure = result;
            }
        }
        Terminal.blank();

        if (firstFailure != null) {
            explainFailure(firstFailure);
            Terminal.line("  " + passed + " of " + results.size() + " tests passed.");
            Terminal.pause();
            return;
        }

        Terminal.lineAs(Theme.GOOD, results.size() == 1 ? "  The test passed."
                : "  All " + results.size() + " tests passed.");
        if (player.hasPassedLab(lab.getId())) {
            Terminal.line("  (Already passed before - no new XP, but well done again.)");
        } else {
            int award = xpFor(lab, player);
            player.passLab(lab.getId());
            player.addXp(award);
            SaveFile.save(player);
            Terminal.blank();
            Terminal.line(Theme.paint(Theme.GOOD, "  LAB PASSED.")
                    + Theme.paint(Theme.ACCENT, "   +" + award + " XP"));
        }
        Terminal.blank();
        Terminal.wrapped("Want to compare with the reference solution? There is "
                + "always more than one right answer.", "  ");
        String compare = Terminal.ask("  Show it? (y/N): ");
        if (compare.equalsIgnoreCase("y") || compare.equalsIgnoreCase("yes")) {
            printSolution(lab);
        }
        Terminal.pause();
    }

    private static int xpFor(Lab lab, Player player) {
        int base = lab.getXp();
        if (player.sawLabSolution(lab.getId())) {
            return Math.max(1, base / 4);
        }
        return Math.max(base / 4, base - HINT_COST * hintsShown(lab));
    }

    private static void explainFailure(LabBench.Result result) {
        LabTest test = result.test;
        Terminal.heading("WHAT WENT WRONG");
        Terminal.blank();
        if (test.isHidden()) {
            Terminal.wrapped("This is a hidden test: the same task with different "
                    + "values. It is shown now so you can see why.", "  ");
            Terminal.blank();
        }
        if (test.getInput().length > 0) {
            StringBuilder typed = new StringBuilder();
            for (String line : test.getInput()) {
                typed.append(typed.length() == 0 ? "" : "  then  ")
                     .append("|").append(line).append("|");
            }
            Terminal.line("  Typed: " + Theme.paint(Theme.ACCENT, typed.toString()));
            Terminal.lineAs(Theme.MUTED, "         (between the | marks, spaces included)");
            Terminal.blank();
        }
        if (!result.problem.isEmpty()) {
            Terminal.wrappedAs(Theme.WARN, result.problem, "  ");
            Terminal.blank();
        }

        List<String> expected = LabBench.normalise(String.join("\n", test.getScreen()));
        List<String> actual = LabBench.normalise(result.screen);
        int diff = result.firstDifference;

        Terminal.line("  Expected:");
        printLines(expected, diff, 30);
        Terminal.blank();
        Terminal.line("  Your program:");
        if (actual.isEmpty()) {
            Terminal.lineAs(Theme.MUTED, "      (printed nothing)");
        } else {
            printLines(actual, diff, 30);
        }
        Terminal.blank();

        if (diff >= 0 && result.problem.isEmpty()) {
            String want = diff < expected.size() ? expected.get(diff) : null;
            String got = diff < actual.size() ? actual.get(diff) : null;
            Terminal.line("  First difference, line " + (diff + 1) + ":");
            Terminal.line("      expected  " + (want == null
                    ? Theme.paint(Theme.MUTED, "(nothing - your output has extra lines)")
                    : "|" + want + "|"));
            Terminal.line("      yours     " + (got == null
                    ? Theme.paint(Theme.MUTED, "(nothing - your output stops early)")
                    : "|" + got + "|"));
            Terminal.lineAs(Theme.MUTED, "      (the | marks show where each line starts and ends)");
            Terminal.blank();
        }
    }

    private static void printLines(List<String> lines, int mark, int limit) {
        for (int i = 0; i < lines.size() && i < limit; i++) {
            String number = String.format("%3d", i + 1);
            String body = lines.get(i);
            if (i == mark) {
                Terminal.line("  " + Theme.paint(Theme.BAD, ">" + number + " ")
                        + Theme.paint(Theme.CODE, body));
            } else {
                Terminal.line("  " + Theme.paint(Theme.GUTTER, " " + number + " ")
                        + Theme.paint(Theme.CODE, body));
            }
        }
        if (lines.size() > limit) {
            Terminal.lineAs(Theme.MUTED, "       ... " + (lines.size() - limit)
                    + " more lines");
        }
    }

    private static void runYourself(Lab lab) {
        if (!compileOrExplain(lab)) {
            return;
        }
        File build = new File(LabBench.folderFor(lab), "build");
        Terminal.blank();
        Terminal.lineAs(Theme.MUTED, "  ---- your program starts ----");
        try {
            Process run = new ProcessBuilder("java", "-cp", build.getAbsolutePath(), "Main")
                    .inheritIO().start();
            int code = run.waitFor();
            Terminal.lineAs(Theme.MUTED, "  ---- your program ended"
                    + (code == 0 ? "" : " (exit code " + code + ")") + " ----");
        } catch (IOException e) {
            Terminal.lineAs(Theme.BAD, "  Could not start java: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Terminal.pause();
    }

    private static void hint(Lab lab) {
        String[] hints = lab.getHints();
        int shown = hintsShown(lab);
        Terminal.blank();
        Terminal.heading("HINTS");
        Terminal.blank();
        if (shown < hints.length) {
            shown = shown + 1;
            HINTS_SHOWN.put(lab.getId(), shown);
        }
        for (int i = 0; i < shown; i++) {
            Terminal.lineAs(Theme.WARN, "  HINT " + (i + 1) + " of " + hints.length);
            Terminal.teachingText(hints[i], "  ");
            Terminal.blank();
        }
        if (shown == hints.length) {
            Terminal.lineAs(Theme.MUTED, "  That is every hint. The full solution is option 5.");
        }
        Terminal.pause();
    }

    private static int hintsShown(Lab lab) {
        Integer shown = HINTS_SHOWN.get(lab.getId());
        return shown == null ? 0 : shown;
    }

    private static void solution(Lab lab, Player player) {
        Terminal.blank();
        if (!player.hasPassedLab(lab.getId())) {
            Terminal.wrappedAs(Theme.WARN, "Seeing the solution before passing means "
                    + "this lab earns a quarter of its XP when you do pass it. You "
                    + "will still need to write and test the program yourself.", "  ");
            String confirm = Terminal.ask("  Type SHOW to see it, or ENTER to keep "
                    + "trying: ");
            if (!confirm.equalsIgnoreCase("show")) {
                return;
            }
            player.markLabSolutionSeen(lab.getId());
            SaveFile.save(player);
        }
        printSolution(lab);
        Terminal.pause();
    }

    private static void printSolution(Lab lab) {
        Terminal.blank();
        Terminal.heading("REFERENCE SOLUTION");
        Terminal.code(lab.getSolution());
        Terminal.heading("WHY IT WORKS");
        Terminal.blank();
        Terminal.teachingText(lab.getWalkthrough(), "  ");
        Terminal.blank();
        Terminal.wrappedAs(Theme.MUTED, "Read it, close it, then write it again from "
                + "memory in your own file. That is where the learning happens.", "  ");
    }
}
