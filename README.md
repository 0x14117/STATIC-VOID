# CYBER//OPS

A Java and cybersecurity training simulator that runs in your terminal.

You are a junior analyst at NORTHSTAR SYSTEMS. Missions teach you Java, and
connect that Java to the security work it is actually used for: checking
credentials, counting failed logins, parsing logs, controlling access,
surviving malformed input.

Every task asks you to think, not to copy. You predict what code will print,
find which line holds a bug, choose between options, or write one missing
line. There is nowhere to paste an answer from.

Everything is simulated locally. Nothing touches a real system.

## What you need

A JDK, Java 17 or newer. Check with:

    javac -version

Nothing else. No libraries, no build tool, no internet.

## Play

    javac -d out src/*.java
    java -cp out Main

On Windows, use `src\*.java` if your shell objects to the forward slash.

Your progress is saved to `cyberops-save.txt` in the folder you run from.
It is plain text; you can open it and read exactly what the game remembers.

## What is in it

    13 missions, 53 tasks, 985 XP

    AUTHENTICATION   banners, variables, the password check, counting
                     failures, lockout policy and its boundary
    LOG ANALYSIS     log events as objects, rules as methods, arrays,
                     the growing watchlist
    ACCESS CONTROL   least privilege, encapsulation as a control,
                     surviving malformed input
    BOSS             a full compromised-account investigation

Each mission teaches its concept before asking anything, gives progressive
hints when you ask for them, and explains the answer afterwards. Hints cost
a little XP, so you try first.

## The code

    src/Terminal.java         all screen drawing and keyboard input
    src/Player.java           name, XP, level, what you have completed
    src/SaveFile.java         reads and writes the plain-text save
    src/Task.java             one question and how its answer is judged
    src/Mission.java          a mission: briefing, concepts, tasks
    src/MissionLibrary.java   every mission in the game
    src/MissionRunner.java    plays a mission and scores it
    src/KnowledgeIndex.java   the Java topics you have earned
    src/World.java            NORTHSTAR SYSTEMS: hosts and accounts
    src/Main.java             the terminal, the menu, the game loop

    tools/CheckAll.java       verifies every mission and task

## Checking the content

    javac -d check src/*.java tools/CheckAll.java
    java -cp check CheckAll

It confirms every task has an accepted answer, hints, an explanation, that
each task accepts its own answer, and that none of them accept nonsense.
