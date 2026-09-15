"""
Standalone checks for the File I/O sandboxing changes to java_sandbox.py
(allowing File/FileReader/FileWriter within the sandboxed temp dir,
blocking path traversal, and seed_files for read missions).

Run: python3 tests/test_java_sandbox_fileio.py
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from java_sandbox import run_java

passed = 0
failed = 0


def check(name, condition, detail=""):
    global passed, failed
    if condition:
        passed += 1
        print("PASS: {}".format(name))
    else:
        failed += 1
        print("FAIL: {}  {}".format(name, detail))


def main():
    # 1. Writing a file with a relative filename is now allowed
    WRITE = '''import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("data.txt");
        writer.write("hello sandbox");
        writer.close();
        System.out.println("WRITE OK");
    }
}'''
    r = run_java(WRITE)
    check("relative-path file write is allowed", r["ok"] is True and "WRITE OK" in r["output"], r)

    # 2. Writing then reading back the same relative file works end to end
    WRITE_THEN_READ = '''import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("log.txt");
        writer.write("ACCESS GRANTED");
        writer.close();

        BufferedReader reader = new BufferedReader(new FileReader("log.txt"));
        String line = reader.readLine();
        reader.close();
        System.out.println(line);
    }
}'''
    r = run_java(WRITE_THEN_READ)
    check("write then read back the same relative file works", r["ok"] is True and r["output"].strip() == "ACCESS GRANTED", r)

    # 3. seed_files pre-populates a file the player's code can read without writing it first
    READ_SEEDED = '''import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("mission.txt"));
        String line = reader.readLine();
        reader.close();
        System.out.println(line);
    }
}'''
    r = run_java(READ_SEEDED, seed_files={"mission.txt": "CLASSIFIED BRIEFING\n"})
    check("seed_files pre-populates a readable file", r["ok"] is True and r["output"].strip() == "CLASSIFIED BRIEFING", r)

    # 4. Absolute path literals are blocked before compiling
    ABSOLUTE_PATH = '''import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("/etc/passwd");
        writer.write("pwned");
        writer.close();
        System.out.println("should not run");
    }
}'''
    r = run_java(ABSOLUTE_PATH)
    check("absolute path literal is blocked", r["blocked"] is True and "should not run" not in r["output"], r)

    # 5. Path traversal literals are blocked
    TRAVERSAL = '''import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("../../../tmp/escape.txt");
        writer.write("pwned");
        writer.close();
        System.out.println("should not run");
    }
}'''
    r = run_java(TRAVERSAL)
    check("\"..\" traversal literal is blocked", r["blocked"] is True and "should not run" not in r["output"], r)

    # 6. Windows-style absolute path (drive letter) is blocked too, in case
    # this server ever runs on the player's Windows laptop
    WINDOWS_PATH = '''import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("C:\\\\Windows\\\\System32\\\\evil.txt");
        writer.close();
        System.out.println("should not run");
    }
}'''
    r = run_java(WINDOWS_PATH)
    check("Windows drive-letter absolute path is blocked", r["blocked"] is True and "should not run" not in r["output"], r)

    # 7. Still-blocked APIs remain blocked after the BLOCKED_PATTERNS edit
    # (regression: make sure removing File-related entries didn't
    # accidentally remove something else nearby)
    for pattern, snippet in [
        ("Runtime", 'Runtime.getRuntime().exec("whoami");'),
        ("java.net", 'java.net.Socket s = null;'),
        ("ProcessBuilder", 'new ProcessBuilder("ls").start();'),
    ]:
        code = '''public class Main {
    public static void main(String[] args) throws Exception {
        %s
        System.out.println("should not run");
    }
}''' % snippet
        r = run_java(code)
        check("still blocks `{}` after File I/O was allowed".format(pattern), r["blocked"] is True, r)

    # 8. A file created in one run's temp directory does not leak into the
    # next run — each run gets its own fresh, isolated workdir
    WRITE_ONLY = '''import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("leftover.txt");
        writer.write("should not persist");
        writer.close();
        System.out.println("wrote it");
    }
}'''
    READ_SHOULD_FAIL = '''import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("leftover.txt"));
        System.out.println(reader.readLine());
    }
}'''
    r1 = run_java(WRITE_ONLY)
    r2 = run_java(READ_SHOULD_FAIL)
    check(
        "each run gets an isolated temp dir (file from a previous run doesn't leak into the next)",
        r1["ok"] is True and r2["ok"] is False and r2["error"]["type"] == "RuntimeError",
        (r1, r2),
    )

    print("\n{} passed, {} failed".format(passed, failed))
    sys.exit(1 if failed else 0)


if __name__ == "__main__":
    main()
