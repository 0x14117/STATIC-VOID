# The security thread

Every lab builds a piece of one real tool: a **log analyser** — the thing that
reads authentication logs, works out who is being attacked, and raises an
alert. A small SIEM.

This is defensive work. You are writing the code that detects and reports, not
code that attacks. That is also where the Java jobs are.

By the end you have a working tool you wrote yourself, one chapter at a time.

---

## Why a log analyser

It was chosen because it fits the book's teaching order almost exactly, which
is rare and worth using:

- Log lines are **text you must not trust** → parsing, validation, exceptions.
- Events have kinds that share behaviour → **inheritance and polymorphism**,
  arriving exactly when the book teaches them.
- Counting per user, per IP → **collections**, arriving when the book teaches them.
- "Tell someone when it is bad" → **event handling**, same.
- It is genuinely useful, so you are never writing a toy.

---

## What each chapter contributes

| Ch | Book topic | What you build | The security idea behind it |
|----|-----------|----------------|------------------------------|
| 1 | First contact | A header for the report | — |
| 2 | Classes and objects | `LogEvent` — one line of a log as an object | Structured data beats raw strings |
| 3 | Primitives and references | Comparing usernames and passwords | `==` on a password check is a real bug |
| 4 | How objects behave | `Account` with a private password field | **Encapsulation is a security control** |
| 5 | Flow control | Password strength rules; failed-attempt lockout | Weak rules are how credential stuffing wins |
| 6 | `ArrayList` | A list of failed attempts | Keeping evidence |
| 7 | Inheritance | `LoginEvent`, `FileAccessEvent` from `SecurityEvent` | Different events, shared handling |
| 8 | Interfaces, abstract | A `Detector` interface; several detectors run in one loop | Plugin architecture — how real scanners are built |
| 9 | Constructors, lifetime | `Session` with a creation time and an expiry | Sessions that never expire are a vulnerability |
| 10 | `static`, Math, parsing | Checksums; parsing port numbers from text | Parsing untrusted numbers is where crashes start |
| 11 | Exceptions | Surviving a malformed log line | **A parser that crashes on bad input is a denial of service** |
| 12 | Event handling | An `AlertListener` notified when a threat is found | The observer pattern, i.e. how alerting works |
| 14 | File I/O, serialization | Reading log files; writing an audit trail | Audit logs, and why deserialising untrusted data is dangerous |
| 15 | Threads | Processing several log files at once | Race conditions are a vulnerability class, not just a bug |
| 16 | Collections, generics | Failed logins per user; an IP block list | The data structures detection actually runs on |
| 17 | Lambdas and streams | Filtering the event stream for what matters | Expressing a detection rule as a filter |

---

## What counts as a real security exercise

**Yes:**
- Write the rule that decides whether a password is strong enough.
- Split a log line into its fields and cope when a field is missing.
- Count failed logins per user and flag the fifth in a row.
- Compare two file hashes and report a mismatch.
- Keep a password field private and expose only a `check` method.
- Decide whether a session has expired.

**No:**
- Print `ACCESS GRANTED` in capitals.
- Name a variable `hackerCode`.
- Any exercise that would be identical with the security words removed.

The test: **take the security words out. If the exercise is unchanged, it was
never a security exercise.** Renaming `x` to `threatLevel` teaches nothing.

---

## Sample data

Realistic but invented. Committed under `labs/data/` so every lab draws from
the same log format instead of inventing one each time.

```
2026-03-14T08:12:04Z  auth  FAIL  user=jsmith     ip=10.14.22.9    reason=bad_password
2026-03-14T08:12:09Z  auth  FAIL  user=jsmith     ip=10.14.22.9    reason=bad_password
2026-03-14T08:12:14Z  auth  OK    user=jsmith     ip=10.14.22.9
2026-03-14T08:13:01Z  file  DENY  user=contractor path=payroll.csv
```

Fixed width where it helps early labs, with deliberately malformed lines
appearing from Chapter 11 onward — because that is when you learn to survive
them.

No real IP addresses, no real names, no real credentials. Ever.

---

## Scope

This teaches **defensive** security engineering: detection, validation,
hardening, safe handling of untrusted input. That is what the log analyser is,
and it is what the Java in a security career is actually used for.

Where a lab explains an attack, it explains it to defend against it — what
credential stuffing is, so your password rule is the right shape; what insecure
deserialization is, so you never call `readObject` on data from outside.
Understanding the attack is part of writing the defence.
