---
name: write-lab
description: Write or revise a STATIC VOID lab — one Java concept explained in plain English, with a worked example and four to six graded practice tasks built around defensive security work. Use whenever adding a lab, adding tasks to an existing lab, or reviewing a lab against the spec. Enforces the anatomy, the task ladder, the verification rules and the banned patterns from docs/02-LAB-SPEC.md.
---

# Writing a lab

You are writing teaching material for one person learning Java from Head First
Java, for a cybersecurity career. Read `docs/02-LAB-SPEC.md` and
`docs/04-SECURITY-THREAD.md` before the first lab of a session.

## Before writing

1. **Which chapter?** Check `docs/01-CURRICULUM.md`. A lab may use only ideas
   from its own chapter or earlier. Verify this before designing tasks, not
   after.
2. **What one idea?** Write it in a single sentence. If you need "and", split
   it into two labs.
3. **What does it build?** Find the row in `docs/04-SECURITY-THREAD.md`. The
   lab contributes a real piece of the log analyser.
4. **Read the neighbours.** Open the chapter module and read the labs either
   side, so the difficulty steps evenly and nothing repeats.

## The anatomy

```python
"chNN-labNN": {
    "chapter": N, "chapter_title": "...", "title": "...",
    "idea": "one sentence",
    "learn": "...",      # 1-2 sentences
    "matters": "...",    # the real security consequence
    "explain": "...",    # 150-400 words, plain English
    "example": "...",    # complete runnable code + its output + why
    "recap": ["...", "...", "..."],
    "tasks": [ ... ],    # 4 to 6
}
```

### EXPLAIN
Build the mental model, not the syntax list. Say **why** Java is like that.
End by naming the specific mistake a learner makes here and what it looks like
when it happens.

### EXAMPLE
Complete and runnable, never a fragment. Always show what it prints.
**Different values and names from every task in the lab** — otherwise the
learner copies. Where the idea has a classic broken form, show BROKEN with its
real output, then FIXED with its real output.

### The task ladder
| # | Role | Distance from the example |
|---|---|---|
| 1 | warm-up | very close; change one or two things; under two minutes |
| 2 | variation | same idea, new situation |
| 3 | apply | on realistic security data |
| 4 | combine | this idea plus an earlier chapter's |
| 5 | predict / repair | optional |
| 6 | extend | optional; adds to the log analyser |

Tasks 1–4 are required.

### Every task
```python
{
    "id": "t1",
    "role": "warm-up",
    "brief": "...",           # concrete; state the exact expected output
    "starter": "...",         # MUST NOT already pass
    "hints": ["approach", "nearly the answer"],   # exactly 2
    "check": make_checker(...),
    "solution": "...",        # verified on every test run
    "explanation": "...",     # shown ONLY after passing
    "wrong": ["..."],         # optional; verified to be rejected
}
```

The `explanation` does three things in three to six sentences: what to notice,
what the common wrong version would have printed, and — only if it genuinely
connects — where this goes next. It is not praise.

## Grading

Grade **behaviour**: what it printed, what it wrote. Use a source requirement
only where output cannot tell the truth (did they really use a `Scanner`, did
they really write a comment, did they really use `.equals()` where `==` happens
to agree). Keep source requirements as loose as possible.

**If `System.out.println("42")` would pass, the task is broken.** Either the
value must come from somewhere the learner does not control, or add a source
requirement.

## Style

- Short sentences. One idea each.
- Plain words. "Use", not "utilise".
- No idioms, no jokes, no cleverness. The reader is learning Java, not English.
- Second person: "you write a method that...".
- Never "simply", "just", "obviously", "of course".

## Banned

- Characters, narrators, story, roleplay.
- Fake security: an exercise that reads the same with the security words
  removed. Test it by removing them.
- A concept from a later chapter.
- A task that is the previous task with different numbers.
- Any claim about Java behaviour that is not covered by a test in
  `tests/test_teaching_claims.py`.

## Finishing — not optional

```bash
python3 tests/test_all_labs.py <chapter>
```

Must report `0 failed`. That proves, for every task: the reference solution is
accepted, each `wrong` submission is rejected, and the starter code does not
already pass.

If the lab asserts anything about how Java behaves, add it to
`tests/test_teaching_claims.py` and run that too. A wrong claim in an
explanation teaches something false that gets carried into an exam.

Commit only after both are green.
