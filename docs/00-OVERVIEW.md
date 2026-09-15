# STATIC VOID — Overview

A Java learning tool. You read an explanation, study a worked example, then
do several practice tasks. Your code is compiled and run by a real JDK, and
graded on what it actually did.

The name is the Java pun in `public static void main`. It is the only joke in
the project.

---

## What this is for

One person learning Java properly, from the Head First Java book, with enough
practice on each idea to make it stick — and with the practice built around
cybersecurity work, because that is the field the learning is for.

## The five decisions this project is built on

**1. Plain voice. No character, no story.**
There is no handler, no narrator, no roleplay. Explanations are written the way
a good teacher talks: short sentences, plain words, no cleverness. If a sentence
can be shorter, it is shorter.

**2. Head First Java is the spine.**
Chapter order comes from the book, not from a university module list. You read
a chapter, then do the labs for that chapter. See `01-CURRICULUM.md`.

**3. The security is real, not decorative.**
An exercise is not "print HACKER ACCESS GRANTED". An exercise is *write the
password strength rule*, *parse the failed-login log*, *compare the file hash*.
The code you write is code a security person actually writes. Renaming a
variable to `hacker` is not a security exercise and is not allowed.
See `04-SECURITY-THREAD.md`.

**4. Repetition is the point.**
One concept is not one task. A lab explains the idea, shows one worked example,
then gives you **four to six tasks** on that same idea, getting harder. You do
not learn `.equals()` by using it once. See `02-LAB-SPEC.md`.

**5. Everything is verified.**
Every task carries a reference solution that is compiled, run, and graded by
that task's own checker on every test run. If a task cannot be passed, the
build fails and I find out — not you, three weeks later, convinced your own
code is broken. See `03-ARCHITECTURE.md`.

---

## What a learner actually sees

    LAB 3.2 — Comparing Passwords Safely

    WHAT YOU WILL LEARN     one or two sentences
    WHY IT MATTERS          the real security consequence
    EXPLAIN                 the idea, in plain English
    EXAMPLE                 working code + what it prints + why
    TASK 1 .. TASK 5        practice, easiest first
                            each with hints, and an explanation after you pass
    WHAT YOU NOW KNOW       short recap

The example never uses the same values as the tasks. You cannot copy and paste
your way through; you have to move the idea across.

---

## History

This project had an earlier design: a handler character called "Cipher",
missions ordered by the LJMU module syllabus, one task per mission. 145 of
those were built and fully verified (1232 checks, 378 sandbox runs, all
passing).

That version is preserved at commit **`797cef7`** on the
`claude/mission-practice-steps-2oh6pn` branch, and it runs. It was replaced —
not because it was broken, but because one task per concept is not enough
practice, and the fictional wrapper was costing reading effort that the
explanations needed instead.

## Where to look next

| File | What it settles |
|---|---|
| `01-CURRICULUM.md` | Which chapters, which labs, in what order, and what the sandbox cannot run |
| `02-LAB-SPEC.md` | The anatomy of a lab and every rule for writing one |
| `03-ARCHITECTURE.md` | Data model, file layout, how grading works |
| `04-SECURITY-THREAD.md` | The real tool each chapter builds toward |
| `.claude/skills/write-lab/SKILL.md` | The rules above, in executable form |
