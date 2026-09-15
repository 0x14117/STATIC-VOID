# Curriculum

The chapter order comes from **Head First Java**. You read a chapter in the
book, then do that chapter's labs here.

> **Check this list against your copy.** This map is written for the **3rd
> edition** (2022, Java 17). If your book is the 2nd edition the chapter
> numbers after 11 are different — tell me and I will renumber. Everything
> else in this document still holds.

---

## Rule: nothing is used before it is taught

A lab may only use ideas from its own chapter or an earlier one. If a lab needs
something from a later chapter, the lab is wrong and must be redesigned.

This has real consequences. Head First Java teaches objects in Chapter 2, long
before `if` and loops arrive properly in Chapter 5. So the early labs work with
objects and methods but cannot branch or loop, and that is correct — it is the
book's order, and it is a deliberate choice by the book's authors.

Where a lab would genuinely be better with a later tool, the explanation says
so in one line: *"There is a shorter way to write this once you reach Chapter
11. For now, do it like this."* That is honest and it stops the learner
thinking they have missed something.

---

## Chapter map

Lab counts are a target, not a promise. A chapter gets the labs it needs.

| Ch | Book title | What it covers | Labs | Runs in browser? |
|----|-----------|----------------|------|------------------|
| 1 | Breaking the Surface | main, printing, basic loops and conditionals as a preview | 4 | yes |
| 2 | A Trip to Objectville | classes vs objects, instance variables, methods | 5 | yes |
| 3 | Know Your Variables | primitives, references, the heap, `==` vs `.equals()` | 6 | yes |
| 4 | How Objects Behave | parameters, return values, getters/setters, encapsulation | 6 | yes |
| 5 | Extra-Strength Methods | flow control, loops, operators, casting | 7 | yes |
| 6 | Using the Java Library | `ArrayList`, the API docs, `import` | 6 | yes |
| 7 | Better Living in Objectville | inheritance, overriding, `super` | 6 | yes |
| 8 | Serious Polymorphism | abstract classes, interfaces | 6 | yes |
| 9 | Life and Death of an Object | constructors, scope, the stack and heap, garbage collection | 5 | yes |
| 10 | Numbers Matter | `static`, `Math`, wrappers, parsing, formatting | 6 | yes |
| 11 | Risky Behavior | exceptions, `try`/`catch`/`finally`, `throw` | 6 | yes |
| 12 | A Very Graphic Story | GUI, **event handling**, inner classes | 3 | **partly** — see below |
| 13 | Work on Your Swing | layout managers, components | 0 | **no** — see below |
| 14 | Saving Objects | serialization, file I/O | 6 | mostly |
| 15 | Make a Connection | networking, threads | 3 | **partly** — see below |
| 16 | Data Structures | collections, generics, sorting | 7 | yes |
| 17 | Lambdas and Streams | lambdas, `Stream` | 5 | yes |
| 18 | Packages, Jars, Deployment | packaging | 0 | **no** — see below |

**Rough total: 87 labs, each with 4–6 tasks → roughly 400–500 graded tasks.**

That is more practice than the 250 missions of the previous design, arranged so
that the repetition lands on one idea at a time instead of being spread thin.

---

## What the sandbox cannot run, and what we do about it

Your code runs in a locked-down temporary directory. Some things are blocked on
purpose. Pretending otherwise would waste your time, so here is the truth for
each affected chapter.

### Chapter 12–13: GUI and Swing
**Blocked:** there is no screen. A `JFrame` has nothing to appear on.

**What we do:** Chapter 12's real lesson for a programmer is **event handling
and inner classes** — the callback pattern, not the buttons. Those three labs
teach it with a listener interface you write yourself, which is exactly the
mechanism `ActionListener` uses underneath, and it runs headless perfectly.

Chapter 13 (layout managers) gets **no labs**. It is worth reading and it
cannot be practised here. The docs say so rather than faking it.

### Chapter 15: Networking
**Blocked:** `java.net` is refused by the sandbox's security scan, because
letting learner code open sockets from a server is a bad idea.

**What we do:** the threads half of the chapter runs fine and gets labs. The
sockets half is taught by reading and by writing the *protocol handling* — the
parsing and state machine logic — against text, which is the part that carries
actual security weight anyway. Marked clearly as such.

### Chapter 18: Packaging and deployment
**Blocked:** the sandbox compiles one file, `Main.java`. It has no notion of a
jar.

**What we do:** no labs. This is a "do it on your own machine" chapter, and the
docs will point at that instead of inventing something fake.

### Also blocked, everywhere
`java.nio` (use `java.io`), reflection, `Runtime`, `ProcessBuilder`,
`System.exit`, and any file path that is absolute or contains `..`.

Full list and reasoning: `java_sandbox.py`.

---

## Reading order

Read the chapter in the book **first**, then do the labs. The labs assume you
have read it. They re-explain every idea they use — you should never be stuck
because you forgot a detail — but they are practice, not a replacement for the
book.
