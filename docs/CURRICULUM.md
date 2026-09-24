# CYBER//OPS curriculum plan

This is the plan the game is built from: which campaign teaches which part of
the module, what every campaign's missions cover, and the labs that go with
each one. Built items are marked; everything else is the plan.

---

## 1. The module syllabus, and where each part is taught

| Module syllabus | Campaign | Status |
|---|---|---|
| Language & IDE basic elements | 00 INIT | built |
| Variables & constants | 01 JAVA ZERO | built |
| Operators, expressions & statements | 01 JAVA ZERO | built |
| Basic I/O | 01 JAVA ZERO (Scanner, parseInt, printf) | built |
| Selection: Boolean operators & expressions | 02 CONDITIONAL, M001-M020 | built |
| If, if-else, else if & switch-case | 02 CONDITIONAL, M002-M030 | 20 of 30 built |
| Methods, parameters and the stack | 03 METHODS | planned |
| Iteration: while, for, do-while, break | 04 LOOP//CONTROL | planned |
| Collections: raw arrays and ArrayList | 05 COLLECTIONS | planned |
| Classes: composite types, members, constructors | 06 OBJECTS | planned |
| Exceptions & event handling: try, catch, finally | 07 EXCEPTIONS | planned |
| File I/O | 08 FILES | planned |

### Two deliberate differences from the syllabus order

**Methods come after Selection, not before it.** The syllabus lists methods
before selection, but Campaign 02 was already under way. Methods follows
straight after it, still ahead of loops.

**File I/O comes after Exceptions.** The syllabus pairs file I/O with basic
I/O. In Java, opening a file forces you to deal with `IOException`, so file
I/O taught before exceptions would mean using `try`, `catch` or `throws`
without explaining them. That breaks the game's first rule: never use what
has not been taught.

### Open question for the module

The syllabus pairs **event handling** with exceptions. In Java, "event
handling" usually means GUI listeners: a button is clicked and a method runs.
That needs interfaces, which the syllabus does not list separately.
Campaign 07 plans a short, self-contained introduction to listeners, with an
optional Swing lab. If the lectures mean something different, this part
changes.

---

## 2. Campaign order

**Part 1: the module (campaigns 00 to 08).** Everything assessed.

| # | Campaign | Covers | Missions | Labs |
|---|---|---|---|---|
| 00 | INIT | language & IDE basics | 10 | 10 |
| 01 | JAVA ZERO | variables, operators, Strings, basic I/O | 30 | 30 |
| 02 | CONDITIONAL | selection, switch | 30 | 30 |
| 03 | METHODS | methods, parameters, the stack | 30 | 30 |
| 04 | LOOP//CONTROL | while, for, do-while, break | 30 | 30 |
| 05 | COLLECTIONS | arrays, ArrayList | 30 | 30 |
| 06 | OBJECTS | classes, members, constructors | 30 | 30 |
| 07 | EXCEPTIONS | try, catch, finally, event handling | 25 | 30 |
| 08 | FILES | file I/O | 25 | 30 |

**Part 2: beyond the module (campaigns 09 to 19).** Deeper Java and applied
security, for after the module or for anyone going further.

| # | Campaign | Covers | Missions |
|---|---|---|---|
| 09 | DEBUG | systematic debugging, testing | 30 |
| 10 | OOP | inheritance, interfaces, polymorphism | 30 |
| 11 | DATA STRUCTURES | maps, sets, generics | 30 |
| 12 | ALGORITHMS | searching, sorting, complexity | 25 |
| 13 | SECURE CODE | input validation, injection, secrets | 25 |
| 14 | CYBER OPS | security tooling in Java | 25 |
| 15 | SOC | log analysis, detection rules | 25 |
| 16 | NETWORK | sockets, protocols | 20 |
| 17 | CRYPTO | hashing, encryption concepts | 20 |
| 18 | INCIDENT RESPONSE | triage, evidence, reporting | 20 |
| 19 | FINAL SOC | the whole job, end to end | 20 |

510 missions in total. Labs for Part 2 are planned when each campaign is
reached.

---

## 3. Missions still to write in Part 1

Each line is one mission, one idea.

### 02 CONDITIONAL (11-20 built, 21-30 remaining)

11. Comparing text: `==` against `.equals()`
12. `equalsIgnoreCase`, and when case should matter
13. Comparing decimals: why `0.1 + 0.2 == 0.3` is false
14. Characters: `Character.isDigit`, `isLetter`, `isUpperCase`
15. Nested `if`
16. Checking for `-1` before `substring`
17. Checking text before `parseInt`
18. `isEmpty` and `isBlank`
19. Variables declared inside a block: scope
20. The conditional operator `? :`
21. `switch` on a number
22. `switch` fall-through, and `break`
23. `switch` on text
24. Switch expressions with `->`
25. `default`: the case you did not think of
26. De Morgan's laws: turning a condition inside out
27. A full access-control rule
28. A lockout decision program with input
29. An input-validation pipeline
30. CONDITIONAL COMPLETE: checkpoint

### 03 METHODS

1. Why methods exist: the same code in three places
2. Writing a `static void` method
3. Calling a method, and where execution goes
4. One parameter
5. Several parameters, and their order
6. Returning a value
7. Return types: `int`, `double`, `boolean`, `String`
8. Using a returned value in a condition
9. Guard clauses: returning early
10. Boolean methods: `isValidPort`
11. Naming methods
12. Local variables live inside their method
13. Pass by value: a method gets a copy
14. Passing a String: immutability again
15. Overloading: same name, different parameters
16. The call stack: frames
17. Reading a stack trace, top to bottom
18. Methods calling methods
19. A first look at recursion, and `StackOverflowError`
20. Class constants with `static final`
21. Static fields against local variables
22. Decomposing a problem into methods
23. Testing a method from `main`
24. Documentation comments
25. Refactoring repeated code into a method
26. A validation helper library
27. Methods that return early on bad input
28. A sanitiser method for log output
29. A small security toolkit of methods
30. METHODS COMPLETE: checkpoint

### 04 LOOP//CONTROL

1. `while`: repeat while true
2. The loop condition and the update
3. Counting loops
4. Infinite loops, and how to stop one
5. Sentinel loops: read until "done"
6. Off-by-one in loops
7. `for`: the counting loop in one line
8. Looping over a String's characters
9. Accumulating: sum and count
10. Finding the maximum and minimum
11. `do-while`: at least once
12. Retry until the input is valid
13. `break`: leaving early
14. `continue`: skipping one pass
15. Nested loops
16. Choosing `while`, `for` or `do-while`
17. Loops that call methods
18. Counting digits, letters and symbols: password strength
19. Searching text character by character
20. Brute force, and why lockout works
21. A lockout simulator
22. Rate limiting over time
23. A menu loop
24. Loops and short-circuit guards
25. Building output line by line
26. Loop tracing on paper
27. Common loop bugs
28. A log-line scanner
29. A port-range sweeper, simulated
30. LOOP//CONTROL COMPLETE: checkpoint

### 05 COLLECTIONS

1. Why arrays: one name, many values
2. Declaring and creating an array
3. Default values
4. Indexing, and `length`
5. Looping over an array with `for`
6. The enhanced `for`
7. `ArrayIndexOutOfBoundsException`
8. Arrays of Strings
9. Arrays as parameters and return values
10. Two names, one array: references
11. Copying an array properly
12. Linear search
13. Maximum, minimum, average
14. Counting matches
15. Parallel arrays
16. Two-dimensional arrays
17. `split`: from a line to an array
18. `ArrayList`: a list that grows
19. `add`, `get`, `size`
20. `remove`, `contains`, `indexOf`
21. Looping over an `ArrayList`
22. Wrapper classes and autoboxing
23. The `remove(int)` trap
24. Arrays or `ArrayList`?
25. Sorting with `Arrays.sort` and `Collections.sort`
26. A watchlist
27. Removing duplicates
28. Top offenders from a log
29. An allowlist check
30. COLLECTIONS COMPLETE: checkpoint

### 06 OBJECTS

1. Why classes: a type of your own
2. A class is a blueprint
3. Fields: an object's members
4. `new`: making an object
5. Using the dot
6. References: two variables, one object
7. `null`, and `NullPointerException`
8. Instance methods
9. `this`
10. Constructors
11. The default constructor
12. Several constructors
13. One constructor calling another: `this(...)`
14. `private` fields
15. Getters and setters
16. Validation in a setter
17. `toString`
18. `equals` for objects
19. `static` against instance members
20. Objects in an `ArrayList`
21. Objects as parameters
22. Composite types: a `Host` that holds `Account`s
23. Immutable objects: `final` fields
24. Designing a class from a specification
25. One class per file
26. Encapsulation as a security control
27. An `Alert` class
28. A `LogEvent` class
29. An `AccountStore`
30. OBJECTS COMPLETE: checkpoint

### 07 EXCEPTIONS (25 missions)

1. What an exception is
2. Reading the stack trace again
3. `try` and `catch`
4. Catching the right exception
5. Several `catch` blocks
6. `finally`
7. Checked and unchecked exceptions
8. `throw`
9. `IllegalArgumentException` for bad input
10. `throws`
11. A custom exception class
12. Never swallow an exception
13. Error messages that leak information
14. Failing closed when something throws
15. Retry with exceptions
16. Exceptions and methods: where to catch
17. Exceptions and objects: guarding constructors
18. What an event is
19. Listeners: code that waits to be called
20. A listener interface of your own
21. Lambdas as listeners
22. A Swing button, optional
23. Events against exceptions
24. A robust input reader
25. EXCEPTIONS COMPLETE: checkpoint

### 08 FILES (25 missions)

1. Files and paths
2. Reading with `Scanner(File)`
3. `FileNotFoundException`
4. Reading every line
5. `BufferedReader`
6. `try`-with-resources
7. Writing with `PrintWriter`
8. Appending instead of overwriting
9. CSV: `split` on commas
10. Counting failures in a log file
11. Writing a report file
12. Path traversal: `../` and why it matters
13. Checking a file exists before using it
14. Character encodings, briefly
15. Large files: line by line, not all at once
16. `Files.readAllLines`
17. Parsing timestamps from a log
18. Skipping malformed lines safely
19. Summarising by user
20. Writing CSV output
21. Tamper evidence: a simple checksum
22. Log rotation
23. Configuration files
24. A mini SIEM, part 1
25. FILES COMPLETE: checkpoint

---

## 4. Labs

Missions teach one idea at a time. **Labs are where you write whole
programs.** They are a separate section of the game (**4. Labs** on the main
menu) and run from each campaign's lab list.

### How a lab works

1. You read the brief, the specification and a sample run.
2. The game creates a starter file, `labs/C01-L05/Main.java`. Open it in any
   editor (Notepad, VS Code, IntelliJ) and write your program.
3. **Test** compiles your file with the real Java compiler and runs it
   against several test cases. Some tests are shown in the brief. Others are
   hidden and use different values, so a program that just prints the
   expected answer does not pass.
4. A failed test shows exactly what went wrong: the input, what was
   expected, what your program printed, and the first line that differs.
5. **Run** starts your program so you can try it yourself.
6. **Hint** shows the next hint. Each lab has at least three, getting more
   specific each time.
7. **Solution** shows a complete working program and a walkthrough of why it
   works. It asks you to confirm first.

When every test passes, the lab is complete. You can then compare your
program with the reference solution at no cost. There is always more than
one right answer.

### Sizes and tiers

| Size | Typical time | XP |
|---|---|---|
| SMALL | 10-20 minutes | 30 |
| MEDIUM | 30-60 minutes | 60 |
| BIG | 1-3 hours | 100 |
| CAPSTONE | 2-5 hours | 150 |

Each hint costs 5 XP. Passing after viewing the solution earns a quarter of
the XP, because the program still has to be written and tested.

**CORE** labs are the path: about ten per campaign, roughly 8-10 hours in
total. **STRETCH** labs are extra practice, for when a topic has not stuck
or before an assessment.

Each lab lists the mission it builds on. It can be opened at any time, but it
assumes that mission has been completed.

### Rules every lab follows

- Only Java taught up to its recommended mission.
- The output is specified exactly, and a sample run shows it.
- At least three hints and a full solution with a walkthrough.
- Tests with different values whenever the lab reads input.
- The reference solution is checked by `tools/CheckLabs.java`: it must
  compile and pass every test. The starter must compile (unless the lab is
  about fixing a broken one) and must not already pass.

---

## 5. Lab lists

Tier: **C** = core, **S** = stretch. "After" is the mission a lab assumes.

### 00 INIT: 10 labs, all small (built)

| Lab | Title | Tier | After |
|---|---|---|---|
| L01 | First Contact | C | M002 |
| L02 | The Login Banner | C | M006 |
| L03 | Fix the Build | C | M008 |
| L04 | One Line, Four Pieces | S | M006 |
| L05 | Host Table | C | M007 |
| L06 | Quote the Alert | C | M007 |
| L07 | Silence the Debug Lines | S | M004 |
| L08 | Read the Error | C | M008 |
| L09 | Analyst Badge | S | M009 |
| L10 | Shift Handover Note | C | M010 |

### 01 JAVA ZERO: 30 labs (built)

| Lab | Title | Size | Tier | After |
|---|---|---|---|---|
| L01 | Running Total | SMALL | C | M011 |
| L02 | Alert Split | SMALL | C | M010 |
| L03 | Overflow Watch | SMALL | S | M015 |
| L04 | Username Inspector | SMALL | C | M019 |
| L05 | Hello, Analyst | SMALL | C | M021 |
| L06 | Queue the Scan | SMALL | S | M021 |
| L07 | Neighbouring Ports | SMALL | C | M022 |
| L08 | Seconds to Clock | SMALL | C | M022 |
| L09 | Clamp the Request | SMALL | C | M023 |
| L10 | Clock Drift | SMALL | S | M023 |
| L11 | Score Rounding | SMALL | S | M024 |
| L12 | Email Splitter | MEDIUM | C | M025 |
| L13 | File Extension | SMALL | S | M025 |
| L14 | Executable Check | SMALL | S | M026 |
| L15 | Password Redactor | MEDIUM | C | M027 |
| L16 | Report Row | SMALL | S | M028 |
| L17 | Failure Rate | SMALL | S | M028 |
| L18 | Firewall Line Parser | MEDIUM | C | M029 |
| L19 | Auth Line Parser | MEDIUM | S | M029 |
| L20 | Username Normaliser | SMALL | S | M021 |
| L21 | Analyst Initials | SMALL | S | M022 |
| L22 | First Octet | SMALL | S | M025 |
| L23 | Timestamp Cutter | MEDIUM | S | M022 |
| L24 | Session Length | MEDIUM | S | M022 |
| L25 | Data Allowance | MEDIUM | S | M022 |
| L26 | Password Length Advisor | SMALL | S | M023 |
| L27 | Unit Converter | MEDIUM | S | M028 |
| L28 | Risk Score | MEDIUM | S | M028 |
| L29 | Alert Summary Card | BIG | S | M029 |
| L30 | Incident Ticket Formatter | CAPSTONE | C | M030 |

### 02 CONDITIONAL: 30 labs

| Lab | Title | Size | Tier |
|---|---|---|---|
| L01 | Over the Threshold | SMALL | C |
| L02 | Lock or Warn | SMALL | C |
| L03 | Password Length Gate | SMALL | C |
| L04 | Severity Grader | SMALL | C |
| L05 | Port Validator | SMALL | C |
| L06 | Office Hours | SMALL | S |
| L07 | Safe First Character | SMALL | S |
| L08 | Username Match | SMALL | C |
| L09 | Case-Blind Blocklist | SMALL | S |
| L10 | Extension Gate | SMALL | S |
| L11 | Leap-Year Log Rotation | MEDIUM | S |
| L12 | Login Decision Engine | MEDIUM | C |
| L13 | Protocol by Port (switch) | MEDIUM | C |
| L14 | Command Router (switch on text) | MEDIUM | S |
| L15 | Safe Email Parser | MEDIUM | C |
| L16 | Validated Number Input | MEDIUM | S |
| L17 | Risk Band | MEDIUM | S |
| L18 | Account Status Report | MEDIUM | S |
| L19 | Password Rule Checker | MEDIUM | C |
| L20 | Time-Window Rule | MEDIUM | S |
| L21 | Two-Factor Flow | MEDIUM | S |
| L22 | IP Class Finder | MEDIUM | S |
| L23 | HTTP Status Explainer | MEDIUM | S |
| L24 | Discount Abuse Check | MEDIUM | S |
| L25 | Triage Router | BIG | S |
| L26 | Access Matrix | BIG | S |
| L27 | Alert Deduplicator | BIG | S |
| L28 | Rule Tester | BIG | S |
| L29 | Change Approval Gate | BIG | S |
| L30 | Firewall Rule Checker | CAPSTONE | C |

### 03 METHODS: 30 labs

| Lab | Title | Size | Tier |
|---|---|---|---|
| L01 | Banner Method | SMALL | C |
| L02 | Square and Cube | SMALL | S |
| L03 | isValidPort | SMALL | C |
| L04 | Severity Label | SMALL | C |
| L05 | Normalise Username | SMALL | C |
| L06 | Minutes to Hours | SMALL | S |
| L07 | Safe Log Line | SMALL | C |
| L08 | Overloaded Formatter | SMALL | S |
| L09 | Domain Of | SMALL | S |
| L10 | Clamp | SMALL | S |
| L11 | Password Score | MEDIUM | C |
| L12 | Validation Library | MEDIUM | C |
| L13 | Ticket ID Generator | MEDIUM | S |
| L14 | Risk Calculator | MEDIUM | C |
| L15 | Guard Clauses | MEDIUM | S |
| L16 | Unit Conversion Kit | MEDIUM | S |
| L17 | Stack Trace Detective | MEDIUM | C |
| L18 | Recursion Countdown | MEDIUM | S |
| L19 | Recursive Sum of Digits | MEDIUM | S |
| L20 | Refactor the Mess | MEDIUM | C |
| L21 | Mask a Card Number | MEDIUM | S |
| L22 | Time Formatter | MEDIUM | S |
| L23 | Report Builder | BIG | S |
| L24 | Input Helpers | BIG | S |
| L25 | Log Line Toolkit | BIG | S |
| L26 | Access Rule Methods | BIG | S |
| L27 | Tested Calculator | BIG | S |
| L28 | Caesar Shift, One Letter | MEDIUM | S |
| L29 | Checksum Digit | MEDIUM | S |
| L30 | Security Toolkit | CAPSTONE | C |

### 04 LOOP//CONTROL: 30 labs

| Lab | Title | Size | Tier |
|---|---|---|---|
| L01 | Countdown | SMALL | C |
| L02 | Sum to N | SMALL | C |
| L03 | Times Table | SMALL | S |
| L04 | Read Until Done | SMALL | C |
| L05 | Retry the Port | SMALL | C |
| L06 | Count the Digits | SMALL | C |
| L07 | Reverse a String | SMALL | S |
| L08 | Largest Reading | SMALL | S |
| L09 | Average Until Blank | MEDIUM | S |
| L10 | Password Strength | MEDIUM | C |
| L11 | Three Strikes Login | MEDIUM | C |
| L12 | Menu Loop | MEDIUM | C |
| L13 | Guess the PIN | MEDIUM | S |
| L14 | Character Frequency | MEDIUM | S |
| L15 | Box Drawer | MEDIUM | S |
| L16 | Prime Checker | MEDIUM | S |
| L17 | FizzBuzz, Security Edition | SMALL | S |
| L18 | Palindrome Check | MEDIUM | S |
| L19 | Caesar Cipher | MEDIUM | C |
| L20 | Vowel Stripper | SMALL | S |
| L21 | Rate Limiter | BIG | S |
| L22 | Brute-Force Estimator | BIG | S |
| L23 | Log Scanner | BIG | C |
| L24 | Port Range Report | BIG | S |
| L25 | Run-Length Encoder | BIG | S |
| L26 | Hex Dump | BIG | S |
| L27 | Pattern Printer | MEDIUM | S |
| L28 | Collatz Tracker | MEDIUM | S |
| L29 | Binary Converter | MEDIUM | S |
| L30 | Lockout Simulator | CAPSTONE | C |

### 05 COLLECTIONS: 30 labs

| Lab | Title | Size | Tier |
|---|---|---|---|
| L01 | Five Readings | SMALL | C |
| L02 | Reverse the List | SMALL | S |
| L03 | Maximum and Minimum | SMALL | C |
| L04 | Count Above Threshold | SMALL | C |
| L05 | Find the Host | SMALL | C |
| L06 | Split the Line | SMALL | C |
| L07 | Parallel Arrays Report | MEDIUM | S |
| L08 | Grid of Alerts | MEDIUM | S |
| L09 | Watchlist | MEDIUM | C |
| L10 | Remove Duplicates | MEDIUM | S |
| L11 | Sorted Scores | MEDIUM | S |
| L12 | Top Offender | MEDIUM | C |
| L13 | Allowlist | MEDIUM | C |
| L14 | Merge Two Lists | MEDIUM | S |
| L15 | Rotate an Array | MEDIUM | S |
| L16 | Histogram | MEDIUM | S |
| L17 | Frequency Table | MEDIUM | S |
| L18 | Second Largest | SMALL | S |
| L19 | CSV Row Parser | MEDIUM | C |
| L20 | Queue of Tickets | MEDIUM | S |
| L21 | Undo History | MEDIUM | S |
| L22 | Matrix Totals | MEDIUM | S |
| L23 | Password History | BIG | S |
| L24 | Failed Logins by User | BIG | S |
| L25 | Sliding Window Alerts | BIG | S |
| L26 | Seat Map | BIG | S |
| L27 | Inventory Manager | BIG | S |
| L28 | Binary Search | BIG | S |
| L29 | Bubble Sort, Traced | BIG | S |
| L30 | Log Summary Report | CAPSTONE | C |

### 06 OBJECTS: 30 labs

| Lab | Title | Size | Tier |
|---|---|---|---|
| L01 | A Host Class | SMALL | C |
| L02 | Constructors | SMALL | C |
| L03 | Getters and Setters | SMALL | C |
| L04 | toString | SMALL | C |
| L05 | Validated Setter | SMALL | C |
| L06 | Account with Lockout | MEDIUM | C |
| L07 | Alert Class | MEDIUM | S |
| L08 | Log Event | MEDIUM | C |
| L09 | Bank Account, Guarded | MEDIUM | S |
| L10 | Counter Object | SMALL | S |
| L11 | Immutable Point | SMALL | S |
| L12 | Equality | MEDIUM | S |
| L13 | Static ID Counter | MEDIUM | S |
| L14 | Hosts in a List | MEDIUM | C |
| L15 | Host Holds Accounts | MEDIUM | C |
| L16 | Ticket Class | MEDIUM | S |
| L17 | Password Policy Object | MEDIUM | S |
| L18 | Temperature Sensor | MEDIUM | S |
| L19 | Session Object | MEDIUM | S |
| L20 | Library of Books | BIG | S |
| L21 | Shopping Cart | BIG | S |
| L22 | Firewall Rule Object | BIG | S |
| L23 | Contact Book | BIG | S |
| L24 | Parking Garage | BIG | S |
| L25 | Student Grades | BIG | S |
| L26 | Deck of Cards | BIG | S |
| L27 | Access Card System | BIG | S |
| L28 | Vulnerability Record | BIG | S |
| L29 | Asset Register | BIG | S |
| L30 | Account Store | CAPSTONE | C |

### 07 EXCEPTIONS: 30 labs

| Lab | Title | Size | Tier |
|---|---|---|---|
| L01 | Catch the Bad Number | SMALL | C |
| L02 | Ask Again | SMALL | C |
| L03 | Finally, Always | SMALL | C |
| L04 | Safe Division | SMALL | S |
| L05 | Two Kinds of Failure | SMALL | S |
| L06 | Throw on Bad Port | SMALL | C |
| L07 | Guarded Constructor | MEDIUM | C |
| L08 | Custom Exception | MEDIUM | C |
| L09 | Safe Index | SMALL | S |
| L10 | Parse or Default | SMALL | S |
| L11 | Robust Menu | MEDIUM | C |
| L12 | Error Messages That Do Not Leak | MEDIUM | S |
| L13 | Fail Closed | MEDIUM | S |
| L14 | Retry Limit | MEDIUM | S |
| L15 | Where to Catch | MEDIUM | S |
| L16 | Exception Chain | MEDIUM | S |
| L17 | Validating Record Parser | BIG | S |
| L18 | Robust Calculator | BIG | S |
| L19 | A Listener | MEDIUM | C |
| L20 | Several Listeners | MEDIUM | S |
| L21 | Lambda Listeners | MEDIUM | S |
| L22 | Alarm Panel Events | BIG | S |
| L23 | Swing Button | MEDIUM | S |
| L24 | Swing Login Form | BIG | S |
| L25 | Event Log | BIG | S |
| L26 | Watchdog | BIG | S |
| L27 | Transaction Rollback | BIG | S |
| L28 | Input Reader Class | BIG | S |
| L29 | Error Report | BIG | S |
| L30 | Robust Command Console | CAPSTONE | C |

### 08 FILES: 30 labs

| Lab | Title | Size | Tier |
|---|---|---|---|
| L01 | Read a File | SMALL | C |
| L02 | Count the Lines | SMALL | C |
| L03 | Write a Report | SMALL | C |
| L04 | Append a Log Entry | SMALL | C |
| L05 | Missing File | SMALL | C |
| L06 | CSV Totals | MEDIUM | C |
| L07 | Failures per User | MEDIUM | C |
| L08 | Filter a Log | MEDIUM | S |
| L09 | Copy a File | SMALL | S |
| L10 | Word Count | MEDIUM | S |
| L11 | Config Loader | MEDIUM | S |
| L12 | Skip Malformed Lines | MEDIUM | S |
| L13 | Safe File Name | MEDIUM | C |
| L14 | Merge Two Logs | MEDIUM | S |
| L15 | Time Range Filter | MEDIUM | S |
| L16 | Report to CSV | MEDIUM | S |
| L17 | Checksum a File | MEDIUM | S |
| L18 | Diff Two Files | BIG | S |
| L19 | Grep Clone | BIG | S |
| L20 | Log Rotator | BIG | S |
| L21 | Access Log Statistics | BIG | S |
| L22 | Brute-Force Finder | BIG | C |
| L23 | Watchlist from File | BIG | S |
| L24 | Contact Book on Disk | BIG | S |
| L25 | Quiz from a File | BIG | S |
| L26 | Encrypted Notes (Caesar) | BIG | S |
| L27 | Inventory Persistence | BIG | S |
| L28 | Report Card Generator | BIG | S |
| L29 | Evidence Log | BIG | S |
| L30 | Mini SIEM | CAPSTONE | C |
