# STATIC VOID

A Java learning game. You play an agent; Cipher, the handler, teaches you a
concept and then hands you a mission. You write real Java in the browser, it
is compiled and run by a real JDK on the server, and the mission is graded on
what your program actually printed (and, for the File I/O missions, on what it
actually wrote to disk).

The curriculum is mapped topic-for-topic onto the LJMU module syllabus:

1. Language & IDE Basic Elements
2. Variables & Constants
3. Operators, Expressions & Statements
4. Methods, Parameters and the Stack
5. Basic I/O & File I/O
6. Selection
7. Iteration
8. Collections
9. Classes
10. Exceptions & Event Handling

## Running it

Requires Python 3.11+ and a JDK (`javac` and `java` on PATH). No pip packages.

    python3 server.py          # then open http://localhost:5000

## Running the tests

    python3 tests/test_java_sandbox.py
    python3 tests/test_java_sandbox_fileio.py
    python3 tests/test_topic1_missions.py    # ...and topic2..topic5
