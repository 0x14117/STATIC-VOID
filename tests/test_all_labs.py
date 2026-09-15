"""
Verifier for every lab and every task.

For each task, using the real javac/java sandbox:

  1. the reference solution compiles, runs, and is ACCEPTED by the checker
  2. every "wrong" submission is REJECTED
  3. the starter code is NOT already accepted — a task whose pre-filled
     editor content passes teaches nothing

and for each lab, the structural rules from docs/02-LAB-SPEC.md: the six
sections are present, the task ladder starts with a warm-up and has at least
four tasks, every task has exactly two hints and an explanation, and the
worked example does not appear verbatim in any solution (the mechanical half
of "the example must use different values from the tasks").

Run:  python3 tests/test_all_labs.py        (everything)
      python3 tests/test_all_labs.py 3      (just chapter 3)
"""
import os
import re
import sys
from concurrent.futures import ThreadPoolExecutor

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from java_sandbox import run_java
from labs import LABS, check_task

WORKERS = int(os.environ.get("STATIC_VOID_TEST_WORKERS", "8"))

LAB_FIELDS = ("chapter", "chapter_title", "title", "idea",
              "learn", "matters", "explain", "example", "recap", "tasks")
TASK_FIELDS = ("id", "role", "brief", "starter", "hints",
               "check", "solution", "explanation")

REQUIRED_ROLES_MIN = 4

passed = 0
failures = []


def check(name, condition, detail=""):
    global passed
    if condition:
        passed += 1
    else:
        failures.append((name, detail))


def code_lines(text):
    """Non-trivial lines of code, for the example-vs-solution overlap check."""
    lines = []
    for raw in text.split("\n"):
        line = raw.strip()
        if len(line) > 25 and not line.startswith("//") and not line.startswith("*"):
            lines.append(line)
    return set(lines)


def structural_checks(chapters):
    for lab_id, lab in sorted(LABS.items()):
        if lab["chapter"] not in chapters:
            continue

        missing = [f for f in LAB_FIELDS if not lab.get(f)]
        check("{} has every section".format(lab_id), not missing,
              "missing/empty: {}".format(missing))
        if missing:
            continue

        check("{} id matches its chapter".format(lab_id),
              lab_id.startswith("ch{:02d}-".format(lab["chapter"])),
              "id {!r} vs chapter {}".format(lab_id, lab["chapter"]))
        check("{} explains before it tests".format(lab_id),
              len(lab["explain"]) >= 400,
              "explain is only {} chars".format(len(lab["explain"])))
        check("{} says why it matters".format(lab_id),
              len(lab["matters"]) >= 80,
              "matters is only {} chars".format(len(lab["matters"])))
        check("{} recaps in 3-6 bullets".format(lab_id),
              3 <= len(lab["recap"]) <= 6,
              "got {}".format(len(lab["recap"])))

        tasks = lab["tasks"]
        check("{} has at least {} tasks".format(lab_id, REQUIRED_ROLES_MIN),
              len(tasks) >= REQUIRED_ROLES_MIN, "got {}".format(len(tasks)))
        check("{} has at most 6 tasks".format(lab_id), len(tasks) <= 6,
              "got {}".format(len(tasks)))
        check("{} starts with a warm-up".format(lab_id),
              tasks and tasks[0]["role"] == "warm-up",
              "first role is {!r}".format(tasks[0]["role"] if tasks else None))

        example_lines = code_lines(lab["example"])

        for task in tasks:
            label = "{}/{}".format(lab_id, task["id"])
            task_missing = [f for f in TASK_FIELDS if not task.get(f)]
            check("{} has every field".format(label), not task_missing,
                  "missing/empty: {}".format(task_missing))
            if task_missing:
                continue

            check("{} has exactly 2 hints".format(label),
                  len(task["hints"]) == 2 and all(h.strip() for h in task["hints"]),
                  "got {}".format(len(task["hints"])))
            check("{} explains after passing".format(label),
                  len(task["explanation"]) >= 120,
                  "explanation is only {} chars".format(len(task["explanation"])))
            check("{} brief states what to produce".format(label),
                  len(task["brief"]) >= 60,
                  "brief is only {} chars".format(len(task["brief"])))

            shared = example_lines & code_lines(task["solution"])
            check("{} solution is not copied from the example".format(label),
                  not shared,
                  "lines shared with the worked example: {}".format(sorted(shared)))


def grade(lab_id, task_id, code):
    lab, task = None, None
    for candidate in LABS[lab_id]["tasks"]:
        if candidate["id"] == task_id:
            task = candidate
    result = run_java(code,
                      input_values=task.get("inputs"),
                      seed_files=task.get("seed_files"))
    if not result["ok"]:
        return False, result["error"]["message"]
    return check_task(lab_id, task_id, code, result["output"], result.get("files"))


def jobs_for(chapters):
    jobs = []
    for lab_id, lab in LABS.items():
        if lab["chapter"] not in chapters:
            continue
        for task in lab["tasks"]:
            jobs.append(("solution", lab_id, task["id"], task["solution"]))
            for index, wrong in enumerate(task.get("wrong") or []):
                jobs.append(("wrong{}".format(index + 1), lab_id, task["id"], wrong))
            jobs.append(("starter", lab_id, task["id"], task["starter"]))
    return jobs


def run_job(job):
    kind, lab_id, task_id, code = job
    ok, message = grade(lab_id, task_id, code)
    return kind, lab_id, task_id, ok, message


def main():
    requested = [int(a) for a in sys.argv[1:] if a.isdigit()]
    chapters = requested or sorted({lab["chapter"] for lab in LABS.values()})

    present = sorted({lab["chapter"] for lab in LABS.values() if lab["chapter"] in chapters})
    labs_here = [l for l in LABS.values() if l["chapter"] in present]
    task_count = sum(len(l["tasks"]) for l in labs_here)
    print("Verifying chapter(s) {} — {} labs, {} tasks\n".format(
        ", ".join(str(c) for c in present), len(labs_here), task_count))

    structural_checks(present)

    jobs = jobs_for(present)
    with ThreadPoolExecutor(max_workers=WORKERS) as pool:
        for kind, lab_id, task_id, ok, message in pool.map(run_job, jobs):
            label = "{}/{}".format(lab_id, task_id)
            if kind == "solution":
                check("{} reference solution is accepted".format(label), ok, message)
            elif kind == "starter":
                check("{} starter code does not already pass".format(label), not ok,
                      "the pre-filled editor content passes on its own")
            else:
                check("{} {} is rejected".format(label, kind), not ok,
                      "a deliberately wrong submission was accepted")

    for lab_id, lab in sorted(LABS.items()):
        if lab["chapter"] not in present:
            continue
        roles = ", ".join(t["role"] for t in lab["tasks"])
        print("  {}  {:<32} {} tasks: {}".format(
            lab_id, lab["title"], len(lab["tasks"]), roles))

    print("\n{} checks passed, {} failed  ({} sandbox runs)".format(
        passed, len(failures), len(jobs)))
    for name, detail in failures:
        print("  FAIL: {}  {}".format(name, detail))
    sys.exit(1 if failures else 0)


if __name__ == "__main__":
    main()
