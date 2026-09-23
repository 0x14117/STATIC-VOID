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

On Windows, double-click **play.bat** - or from a terminal:

    .\play.bat

On Mac or Linux:

    ./play.sh

That compiles the game and starts it. If you would rather do the two steps
yourself:

    javac -d out src/*.java
    java -cp out Main

Your progress is saved to `cyberops-save.txt` in the folder you run from.
It is plain text; you can open it and read exactly what the game remembers.

## What is in it

Twenty campaigns are planned, 510 missions in total. They are written one at
a time so each can be tested properly, and the game shows honest progress
against the whole plan rather than pretending the plan is only as large as
what exists.

    CAMPAIGN 00 - INIT              10 missions    BUILT
    CAMPAIGN 01 - JAVA ZERO         30 missions    BUILT
    CAMPAIGN 02 - CONDITIONAL       30 missions    10 BUILT
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

Answers are judged the way Java would judge them. Extra spaces and
surrounding quotes do not matter. Capital letters do, when you are writing
code or predicting output: `system.out.println` is not Java, and a program
that prints `jsmith` did not print `JSmith`. If only your capitals are wrong,
the game tells you so. Choice letters and line numbers can be typed any way.

## The code

    src/Terminal.java         all screen drawing and keyboard input
    src/Player.java           name, XP, level, what you have completed
    src/SaveFile.java         reads and writes the plain-text save
    src/Task.java             one question and how its answer is judged
    src/Mission.java          a mission: the whole teaching template
    src/Campaign.java         one campaign and its missions
    src/CampaignIndex.java    all twenty campaigns, built or planned
    src/Campaign00.java       CAMPAIGN 00 - INIT
    src/Campaign01.java       CAMPAIGN 01 - JAVA ZERO
    src/Campaign02.java       CAMPAIGN 02 - CONDITIONAL
    src/MissionRunner.java    plays a mission and scores it
    src/KnowledgeIndex.java   the Java topics you have earned
    src/World.java            NORTHSTAR SYSTEMS: hosts and accounts
    src/Main.java             the terminal, the menu, the game loop

    tools/CheckAll.java       verifies every mission is complete
    tools/CheckJava.java      verifies the Java in every mission is true

## Checking the content

Two checks, for two different kinds of mistake.

**Is every mission complete?** Fast; run it after any change.

    javac -d check src/*.java tools/CheckAll.java
    java -cp check CheckAll

Every template section is filled, every task accepts its own answer and
rejects nonsense and wrong capitals, every line fits the terminal, and the
Java Knowledge index agrees with what the missions teach.

**Is everything the missions claim actually true?** Slow - it runs the real
compiler and JVM on every snippet - so run it after changing mission content.

    javac -d check src/*.java tools/CheckJava.java
    java -cp check CheckJava            every mission
    java -cp check CheckJava C01        one campaign

Every example compiles and prints exactly the output the mission shows.
Every solution compiles and runs. Every "what does this print" answer is
what Java really prints. Every "which line does not compile" snippet really
fails, on the line the task names. A beginner takes all of that on trust, so
none of it is left to trust.
