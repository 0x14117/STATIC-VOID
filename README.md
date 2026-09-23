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

Twenty campaigns are planned, 510 missions in total. They are written one at
a time so each can be tested properly, and the game shows honest progress
against the whole plan rather than pretending the plan is only as large as
what exists.

    CAMPAIGN 00 - INIT              10 missions    BUILT
    CAMPAIGN 01 - JAVA ZERO         30 missions
    CAMPAIGN 02 - CONDITIONAL       30 missions
    CAMPAIGN 03 - LOOP//CONTROL     30 missions
    CAMPAIGN 04 - METHODS           30 missions
    CAMPAIGN 05 - DATA              30 missions
    CAMPAIGN 06 - DEBUG             30 missions
    CAMPAIGN 07 - OBJECTS           30 missions
    CAMPAIGN 08 - OOP               30 missions
    CAMPAIGN 09 - COLLECTIONS       30 missions
    CAMPAIGN 10 - EXCEPTIONS        25 missions
    CAMPAIGN 11 - FILES             25 missions
    CAMPAIGN 12 - ALGORITHMS        25 missions
    CAMPAIGN 13 - SECURE CODE       25 missions
    CAMPAIGN 14 - CYBER OPS         25 missions
    CAMPAIGN 15 - SOC               25 missions
    CAMPAIGN 16 - NETWORK           20 missions
    CAMPAIGN 17 - CRYPTO            20 missions
    CAMPAIGN 18 - INCIDENT RESPONSE 20 missions
    CAMPAIGN 19 - FINAL SOC         20 missions

## How a mission teaches

Every mission follows the same shape, and never asks for something it has not
explained first:

    MISSION BRIEF          the security situation, in plain language
    WHAT YOU WILL LEARN    the concepts, and why they are worth knowing
    JAVA CONCEPT           the explanation itself
    SMALL EXAMPLE          short, runnable, with its output shown
    LINE BY LINE           each important line, explained separately
    PREDICT THE OUTPUT     you answer before you are told
    YOUR FIRST PRACTICE    a small question on the same idea
    MISSION OBJECTIVE      what the program needs to do
    STARTER CODE           partially written, never finished
    YOUR TASK              you complete it
    HINTS                  progressive, and each costs a little XP
    SOLUTION               only when you ask, and always with the reasoning
    COMMON MISTAKES        the specific ones for this concept
    CYBERSECURITY CONNECTION   why this matters for security work
    KNOWLEDGE CHECK        two to four short questions
    MISSION RECAP          what you learned, and what comes next

Typing SOLUTION never gives you code on its own. It gives the code and an
explanation of why it works, because code without the reasoning teaches
copying.

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
