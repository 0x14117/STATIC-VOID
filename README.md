# STATIC VOID

A Java learning game. Cipher, your handler, teaches you a concept, then hands
you a mission. You write real Java in the browser; it is compiled and run by a
real JDK on your machine, and the mission is graded on what your program
actually printed — and, for the File I/O missions, on what it actually wrote
to disk.

## Getting it running

You need two things installed, both on your PATH:

- **Python 3.11 or newer**
- **A JDK, Java 17 or newer** — the full JDK, not just a JRE, because the
  game compiles your code with `javac`

There is nothing to `pip install`; the server is pure Python standard library.

Check what you have:

    python --version        # Windows      (or: py --version)
    python3 --version       # macOS/Linux
    javac -version
    java -version

If `python` on Windows prints *"Python was not found; run without arguments to
install from the Microsoft Store"*, that is Windows' placeholder stub, not
Python. Install the real thing from <https://www.python.org/downloads/> and
**tick "Add python.exe to PATH"** on the first screen of the installer, then
open a new terminal.

If `javac` is not found but you have IntelliJ or Eclipse installed, you do have
a JDK — it just is not on your PATH. Installing one from
<https://adoptium.net/> is quicker than repairing the PATH by hand.

### Run it

    git clone https://github.com/0x14117/STATIC-VOID.git
    cd STATIC-VOID
    git checkout claude/mission-practice-steps-2oh6pn

    py server.py            # Windows
    python3 server.py       # macOS/Linux

Then open <http://localhost:5000> in your browser, enter a codename, and the
first mission loads.

Use a different port with `py server.py 8080` if something already has 5000.

Windows note: `python3` is usually not a command. Use `py`, which the
python.org installer always provides, or `python`.

Your progress is saved to `saves/progress.json` and is not committed.

### If something looks wrong

Run the verifier — it compiles and runs a reference solution for every mission
through the real sandbox and grades it with that mission's own checker:

    python3 tests/test_all_labs.py              # every lab
    python3 tests/test_all_labs.py 3            # just chapter 3

    python3 tests/test_teaching_claims.py       # checks the teaching text's
                                                # claims about Java are true

    python3 tests/test_java_sandbox.py          # the sandbox itself
    python3 tests/test_java_sandbox_fileio.py
    python3 tests/test_error_paths.py           # compile errors name YOUR
                                                # file, not the server's

(On Windows, `py` in place of `python3` throughout.)

If those pass and the game still misbehaves, the problem is in the browser or
the server rather than the mission content.

## What you get right now

**One lab is built: `ch03-lab02 — Comparing Passwords Safely`.** Five tasks,
from a warm-up to a repair job, teaching `==` versus `.equals()` as a login
check. It is the reference lab — the one the format is being judged by before
the rest are written.

The design, the rules and the full chapter map are in `docs/`. Start with
`docs/05-SAMPLE-LAB.md`, which is that lab written out as you read it.

## The curriculum

Chapter order comes from **Head First Java**. You read a chapter in the book,
then do that chapter's labs here. Roughly 87 labs are planned, each with 4 to 6
graded tasks. Full map, including the chapters the sandbox cannot run and what
is done instead: `docs/01-CURRICULUM.md`.

A lab is one idea:

    WHAT YOU WILL LEARN  ->  WHY IT MATTERS  ->  EXPLAIN  ->  EXAMPLE
    ->  TASK 1..5 (each with hints, and an explanation after you pass)
    ->  WHAT YOU NOW KNOW

Every task builds a piece of one real tool: a log analyser that reads
authentication logs and works out who is being attacked. See
`docs/04-SECURITY-THREAD.md`.

## How the project fits together

    server.py          HTTP server: serves the UI, runs and grades submissions
    java_sandbox.py    compiles and runs your code in a throwaway temp dir
    labs/              lab content, one module per book chapter
      common.py        shared checker factories and starter-code constants
      chapter03.py     the labs themselves
    static/            the browser UI
    tests/             the verifiers described above
    docs/              the design, the rules, the chapter map
    missions/          the previous format, kept until the new one is proven

Every task carries its own reference solution and its deliberately wrong
submissions, right next to its checker. That is what lets one test grade the
whole curriculum.
