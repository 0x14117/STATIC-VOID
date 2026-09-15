# STATIC VOID

A Java learning game. Cipher, your handler, teaches you a concept, then hands
you a mission. You write real Java in the browser; it is compiled and run by a
real JDK on your machine, and the mission is graded on what your program
actually printed — and, for the File I/O missions, on what it actually wrote
to disk.

## Getting it running

You need two things installed:

- **Python 3.11 or newer** — `python3 --version`
- **A JDK** (Java 17 or newer) — `javac -version` and `java -version`

Both must be on your PATH. There is nothing to `pip install` — the server is
pure Python standard library.

    git clone https://github.com/0x14117/STATIC-VOID.git
    cd STATIC-VOID
    git checkout claude/mission-practice-steps-2oh6pn
    python3 server.py

Then open <http://localhost:5000> in your browser, enter a codename, and the
first mission loads.

Use a different port with `python3 server.py 8080`.

Your progress is saved to `saves/progress.json` and is not committed.

### If something looks wrong

Run the verifier — it compiles and runs a reference solution for every mission
through the real sandbox and grades it with that mission's own checker:

    python3 tests/test_all_missions.py          # every topic
    python3 tests/test_all_missions.py 6        # just topic 6

    python3 tests/test_teaching_claims.py       # checks the teaching text's
                                                # claims about Java are true

    python3 tests/test_java_sandbox.py          # the sandbox itself
    python3 tests/test_java_sandbox_fileio.py

If those pass and the game still misbehaves, the problem is in the browser or
the server rather than the mission content.

## The curriculum

250 missions, 25 per topic, mapped onto the module syllabus:

| # | Topic | Missions |
|---|-------|----------|
| 1 | Language & IDE Basic Elements | 25 |
| 2 | Variables & Constants | 25 |
| 3 | Operators, Expressions & Statements | 25 |
| 4 | Methods, Parameters and the Stack | 25 |
| 5 | Basic I/O & File I/O | 25 |
| 6 | Selection | in progress |
| 7 | Iteration | to come |
| 8 | Collections | to come |
| 9 | Classes | to come |
| 10 | Exceptions & Event Handling | to come |

Within a topic the missions run in taught order. Missions 5, 10, 15 and 20 are
BOSS checkpoints and mission 25 is the FINAL BOSS; those start from an empty
editor, so you write the class and `main()` yourself.

A mission never uses a concept before it has been taught. Topic 4's recursion
uses the ternary operator for its base case because `if` is not introduced
until Topic 6 — the teach block says so, and shows the `if` version you will
meet later.

## How the project fits together

    server.py          HTTP server: serves the UI, runs and grades submissions
    java_sandbox.py    compiles and runs player code in a throwaway temp dir
    missions/          mission content, one module per topic
      common.py        shared checker factories and boilerplate constants
      topic01.py ...   the missions themselves
    static/            the browser UI
    tests/             the verifiers described above

Every mission carries its own reference solution and its deliberately wrong
submissions, right next to its checker, which is what lets one test grade the
whole curriculum.
