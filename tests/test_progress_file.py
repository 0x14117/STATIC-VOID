"""
The save file must never be able to break a run.

Reported from a real first run: a save written by this project's earlier
version used the keys {"codename", "completed_missions"}. The new server read
it straight through, so the first SUCCESSFUL submission died with
KeyError: 'completed' after the task had already been graded and passed. The
learner solved the task and got a dead connection instead of their
explanation.

A file that merely parses is not a file in the right shape. Every field is
rebuilt on load, and this proves it for every shape a save file can be in.

Run: python3 tests/test_progress_file.py
"""
import json
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

import server

passed = 0
failures = []


def check(name, condition, detail=""):
    global passed
    if condition:
        passed += 1
    else:
        failures.append((name, detail))


def with_save_file(contents):
    """Write contents (raw text) as the save file and load it back."""
    os.makedirs(server.SAVES_DIR, exist_ok=True)
    with open(server.SAVE_FILE, "w", encoding="utf-8") as f:
        f.write(contents)
    return server._load_progress()


def is_valid(progress):
    return (isinstance(progress, dict)
            and "name" in progress
            and isinstance(progress.get("completed"), list)
            and all(isinstance(x, str) for x in progress["completed"]))


backup = None
if os.path.exists(server.SAVE_FILE):
    with open(server.SAVE_FILE, "r", encoding="utf-8") as f:
        backup = f.read()

try:
    CASES = [
        ("the previous version's schema",
         '{"codename": "Afaq", "completed_missions": ["t1m1", "t3m2"]}'),
        ("an empty object", "{}"),
        ("corrupt JSON", "{not json at all"),
        ("JSON that is a list, not an object", "[1, 2, 3]"),
        ("JSON that is a bare string", '"hello"'),
        ("completed holding the wrong type", '{"name": "A", "completed": "nope"}'),
        ("completed holding non-strings", '{"name": "A", "completed": [1, null, "ch03-lab02/t1"]}'),
        ("an empty file", ""),
        ("the current schema", '{"name": "Afaq", "completed": ["ch03-lab02/t1"]}'),
    ]

    for name, contents in CASES:
        progress = with_save_file(contents)
        check("survives {}".format(name), is_valid(progress), progress)

    # The specific regression: the old schema must not carry mission ids over,
    # because "t3m2" names nothing in the lab format and would mark the wrong
    # things done.
    progress = with_save_file('{"codename": "Afaq", "completed_missions": ["t1m1"]}')
    check("keeps the name from an old save", progress["name"] == "Afaq", progress)
    check("drops mission ids that mean nothing now", progress["completed"] == [], progress)

    # And the real thing it broke: recording a completed task.
    progress = with_save_file('{"codename": "Afaq", "completed_missions": ["t1m1"]}')
    progress["completed"].append("ch03-lab02/t1")   # this is the line that used to raise
    server._save_progress(progress)
    reloaded = server._load_progress()
    check("a completed task can be recorded after an old save",
          reloaded["completed"] == ["ch03-lab02/t1"], reloaded)

    # Missing file entirely.
    os.remove(server.SAVE_FILE)
    check("survives no save file at all", is_valid(server._load_progress()))

finally:
    if backup is None:
        if os.path.exists(server.SAVE_FILE):
            os.remove(server.SAVE_FILE)
    else:
        with open(server.SAVE_FILE, "w", encoding="utf-8") as f:
            f.write(backup)

print("{} checks passed, {} failed".format(passed, len(failures)))
for name, detail in failures:
    print("  FAIL: {}  {}".format(name, detail))
sys.exit(1 if failures else 0)
