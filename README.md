# CYBER//OPS

A terminal-based Java training simulator.

You play a junior security analyst at a fictional organisation. The missions
teach Java programming, and then gradually connect that Java to real
cybersecurity work: authentication, logs, access control, incidents.

Everything is simulated locally. Nothing attacks any real system.

## Status

**Phase 1 of 16: project skeleton.**

Right now the program prints a banner and exits. That is deliberate — the
point of this phase is to prove the toolchain works before anything is built
on top of it.

## What you need

- A JDK, Java 17 or newer. Check with `javac -version`.

Nothing else. No build tool, no libraries, no framework.

## Compile and run

From the project folder:

    javac -d out src/Main.java
    java -cp out Main

`javac` turns your source code into class files and puts them in `out/`.
`java` runs the compiled program, looking for classes in `out/`.

## Folder layout

    src/        Java source code — the files you write
    out/        compiled class files — built by javac, not committed
    README.md   this file
