# Architecture

## What changes from the old design, and why

The old data model was one mission, one check:

```python
MISSIONS = { "t3m2": { "title": ..., "teach": ..., "check": <one function> } }
```

The Lab format needs one **lab** to hold one explanation and **several tasks**,
each separately graded and separately explained. That is a different shape, and
retro-fitting it to the old dict would produce something confusing. So the
model changes.

## The lab model

```python
LABS = {
    "ch03-lab02": {
        "chapter": 3,
        "chapter_title": "Know Your Variables",
        "title": "Comparing Passwords Safely",
        "idea": "== compares references; .equals() compares contents",

        "learn": "...",          # WHAT YOU WILL LEARN  — 1-2 sentences
        "matters": "...",        # WHY IT MATTERS       — the security reality
        "explain": "...",        # EXPLAIN              — plain English
        "example": "...",        # EXAMPLE              — code + output + why
        "recap": ["...", ...],   # RECAP                — 3-5 bullets

        "tasks": [
            {
                "id": "t1",
                "role": "warm-up",        # warm-up|variation|apply|combine|
                                          # predict|repair|extend
                "brief": "...",
                "starter": "...",         # must not already pass
                "hints": ["...", "..."],  # exactly 2
                "check": <function>,
                "solution": "...",        # verified every test run
                "explanation": "...",     # shown after passing
                "wrong": ["..."],         # optional; verified to fail
                "inputs": ["..."],        # optional, for Scanner tasks
                "seed_files": {...},      # optional, for file tasks
            },
            ...
        ],
    },
}
```

### Why the lab id looks like that
`ch03-lab02` sorts correctly as text, says which chapter it belongs to without
a lookup, and does not need renumbering when a lab is inserted between two
others — the next one just becomes `ch03-lab02b` if it must.

## File layout

```
labs/
  __init__.py          merges every chapter module into LABS; refuses duplicates
  common.py            checker factories, starter-code constants, shared text
  chapter01.py         one module per chapter
  chapter02.py
  ...
java_sandbox.py        unchanged — compile and run in a throwaway temp dir
server.py              serves labs and grades task submissions
static/                the browser UI
tests/
  test_all_labs.py     runs every task's reference solution and grades it
  test_teaching_claims.py   proves the teach text's claims about Java are true
  test_error_paths.py  compile errors name the learner's file, not the server's
  test_java_sandbox*.py     the sandbox itself
docs/                  this documentation
.claude/skills/write-lab/  the authoring rules, executable
```

`missions/` stays untouched at commit `797cef7` until the new structure is
proven, then is removed in one commit.

## Server API

| Route | Purpose |
|---|---|
| `GET /labs` | Every lab, with its tasks. Never includes `solution` or `check`. |
| `GET /progress` | Which task ids are done. |
| `POST /run` | `{lab_id, task_id, code}` → compile, run, grade, explain. |
| `POST /register` | Set the learner's name. |

**`solution` must never reach the browser.** The `/labs` handler builds its
response field by field rather than dumping the dict, so a new field cannot
leak by being added later. There is a test for this.

### What `POST /run` returns

```json
{
  "ok": true,           // did it compile and run
  "passed": true,       // did the checker accept it
  "output": "...",      // captured stdout
  "error": null,        // {type, message} when it failed to build or run
  "feedback": "...",    // the hint when failed
  "explanation": "..."  // the task's explanation — ONLY when passed
}
```

The explanation is withheld until the task is passed. Sending it early would
give the answer away.

## Grading

Unchanged in principle, and it is the part of the old design that worked:

1. `java_sandbox.run_java` compiles the submission as `Main.java` in a fresh
   temp directory, runs it with a timeout and a heap cap, captures stdout and
   any files it wrote, and deletes the directory.
2. The task's `check(code, output, files)` returns `(passed, message)`.

## Verification — what the tests guarantee

`tests/test_all_labs.py` runs on every change and asserts, for every task:

1. The reference solution compiles, runs, and is **accepted**.
2. Every `wrong` submission is **rejected**.
3. The `starter` code is **not** already accepted.

and for every lab:

4. Task ids are unique; the ladder starts at a warm-up.
5. Exactly 2 hints per task.
6. `explain`, `example`, `matters`, `recap` are all present and non-trivial.
7. The example's code does not appear verbatim in any task's solution — the
   anti-copy-paste rule, mechanically enforced.

Rule 7 is new and is the mechanical half of "the example must use different
values". It cannot catch a lazy example on its own, but it catches the worst
case automatically.

## Learner progress

`saves/progress.json`, git-ignored. Keyed by full task id (`ch03-lab02/t3`) so
that finishing three tasks out of five in a lab is recorded honestly.
