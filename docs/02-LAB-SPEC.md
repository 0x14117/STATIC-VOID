# Lab specification

This is the contract. Every lab follows it. If a lab cannot follow it, the lab
is wrong — not the spec.

---

## The shape of a lab

A lab teaches **one idea**. Not two. If you are tempted to add a second idea,
that is a second lab.

    1. HEADER            id, title, chapter, the idea in one line
    2. WHAT YOU LEARN    one or two sentences
    3. WHY IT MATTERS    the real security consequence
    4. EXPLAIN           the idea, in plain English
    5. EXAMPLE           working code, what it prints, and why
    6. TASKS             4 to 6, easiest first, each with its own explanation
    7. RECAP             3 to 5 bullets

The learner reads 1–5, then works through 6, then reads 7.

---

## 4. EXPLAIN — the rules

**Build the mental model, do not just state the syntax.** The learner needs to
know what the computer is actually doing. "`.equals()` compares the characters,
`==` asks whether they are the same object in memory" beats "use `.equals()`
for strings".

**Say why it is like that.** A rule with a reason is remembered. A rule without
one is guessed at under exam pressure.

**Name the mistake before they make it.** Every EXPLAIN ends with the specific
wrong thing a learner does here, and what it looks like when it happens. Not
"be careful with `==`" but "`==` on strings sometimes returns true, which is
worse than always failing, because you will conclude it works".

**Length:** long enough to teach, short enough to read before losing patience.
Roughly 150–400 words. If it is longer, the lab is teaching two ideas.

## 5. EXAMPLE — the rules

**It must be complete, runnable code.** Not a fragment with `...` in it.

**Show what it prints.** Always. The learner should be able to check their
mental model against reality without running anything.

**Different values from every task.** This is not negotiable. If the example
uses `"OPEN"` and Task 1 uses `"OPEN"`, the learner copies and learns nothing.
Change the values, change the variable names, change the scenario.

**Where the idea has a broken form worth seeing, show BROKEN then FIXED.**
Two blocks, both runnable, both with their real output. This is the most
effective teaching device available for traps, and it should be used whenever
one exists.

## 6. TASKS — the rules

### How many
**Four minimum, six maximum.** One task does not build a habit. Seven is
grinding.

### The ladder
Tasks get harder in a fixed shape:

| Task | Role | How far from the example |
|---|---|---|
| 1 | **Warm-up** | Very close. Change one or two things. Should take under two minutes and should feel easy — that is its job. |
| 2 | **Variation** | Same idea, different situation. No longer a template match. |
| 3 | **Apply** | Use it on realistic security data — a real log line, a real password, a real hash. |
| 4 | **Combine** | This idea plus something from an earlier chapter. This is where it becomes knowledge rather than a trick. |
| 5 | **Predict or repair** *(optional)* | Either "what does this print?" answered by writing code that produces it, or working code broken in a named way that must be fixed. |
| 6 | **Extend** *(optional)* | Add a piece to the chapter's security tool. |

Not every lab needs 5 and 6. Every lab needs 1 to 4.

### Every single task carries
| Field | Rule |
|---|---|
| `brief` | What to do. Concrete. Exact expected output stated or shown. |
| `starter` | Pre-filled editor code. **Must not already pass** — enforced by the tests. |
| `hints` | Exactly 2, revealed one at a time. Hint 1 points at the approach. Hint 2 is nearly the answer. |
| `check` | The grader. Grades behaviour — what it printed, what it wrote — not source text, except where source is the only honest way (see below). |
| `solution` | A reference solution, run and graded on every test run. |
| `explanation` | **Shown after the task is passed.** Not a "well done". It says what to notice, why it worked, and what would have happened if they had done the obvious wrong thing. |
| `wrong` | Optional but wanted: the plausible wrong answer, which the tests confirm is rejected. |

### The explanation after a task
This is the part that turns practice into understanding, so it does real work:

- **What to notice** — the thing they may have done correctly without seeing why.
- **The near miss** — what the common wrong version would have printed.
- **Where it goes next** — one line, only if it genuinely connects forward.

Three to six sentences. Not a paragraph of praise.

---

## Grading rules

**Grade behaviour, not source code.** What did the program print? What did it
write to disk? That is the truth. A learner who finds a different correct way
to reach the right answer has not cheated, they have learned.

**Source checks are the narrow exception.** Some things cannot be verified from
output — did they actually use a `Scanner` rather than hardcoding the answer,
did they actually write a comment, did they actually use `.equals()` rather
than `==` when both give the same result here. Those use a source requirement,
and it is kept as loose as possible.

**A task that can be passed by printing a constant is a broken task.** If
`System.out.println("42")` passes, the task teaches nothing. Either the values
must come from somewhere the learner does not control, or a source requirement
must close the hole.

---

## Writing style

**Short sentences.** One idea each.

**Plain words.** "Use" not "utilise". "Before" not "prior to".

**No idioms, no wordplay, no cleverness.** The reader may not be a native
English speaker and is here to learn Java, not to decode a joke.

**Second person.** "You write a method that..." not "the student will...".

**Code in code blocks.** Never inline in a sentence where it could be misread.

**Never say "simply", "just", "obviously", or "of course".** If it were
obvious, there would be no lab.

---

## Banned

- **Characters, narrators, roleplay, story.** No handler, no agency, no plot.
- **Fake security.** Naming a variable `hackerPassword` is not a security
  exercise. The code must do something a security person actually does.
- **Filler tasks.** A task that is the previous task with different numbers and
  nothing new to think about.
- **A concept used before its chapter.** See `01-CURRICULUM.md`.
- **Unverified content.** No lab ships without every task's reference solution
  passing in the test run.
- **A teach claim that has not been checked against a real JDK.** If a lab says
  Java does X, there is a test that proves Java does X. See
  `tests/test_teaching_claims.py`.
