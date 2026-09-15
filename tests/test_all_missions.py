"""
Whole-curriculum verifier.

Every mission carries its own reference solution (and, usually, one or
more deliberately wrong submissions) right next to its checker, so this
one test can grade the entire curriculum instead of ten near-identical
per-topic test files drifting apart from each other.

For each mission it checks, using the real javac/java sandbox:

  1. the reference solution compiles, runs, and is accepted by the checker
  2. every "wrong" submission is rejected
  3. the starter boilerplate is NOT already accepted — a mission whose
     pre-filled editor content passes is teaching nothing

plus structural rules that are easy to break when writing content in
batches: ids contiguous within a topic, titles unique, exactly two hints,
a worked example in the teaching text, and BOSS missions starting from an
empty editor.

Run:  python3 tests/test_all_missions.py        (everything)
      python3 tests/test_all_missions.py 7      (just topic 7)
      python3 tests/test_all_missions.py 7 8    (topics 7 and 8)
"""
import os
import re
import sys
from concurrent.futures import ThreadPoolExecutor

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from java_sandbox import run_java
from missions import MISSIONS, TOPIC_NAMES, check_mission

# javac/java are the bottleneck and both are subprocesses, so threads are
# fine here — 250 missions serially takes minutes, in parallel it's a
# fraction of that.
WORKERS = int(os.environ.get("STATIC_VOID_TEST_WORKERS", "8"))

# A mission's teaching text has to show the concept in action, not just
# describe it. Most use "WORKED EXAMPLE"; a few teach by contrasting a
# broken version with the fix, which is the same thing in a better shape
# for that particular lesson.
EXAMPLE_MARKERS = ("WORKED EXAMPLE", "BROKEN", "EXAMPLE:")

REQUIRED_KEYS = ("title", "topic", "topic_name", "concept", "teach",
                 "briefing", "hints", "check", "solution")

passed = 0
failures = []


def check(name, condition, detail=""):
    global passed
    if condition:
        passed += 1
    else:
        failures.append((name, detail))


def is_boss(mission):
    return mission["title"].startswith(("BOSS", "FINAL BOSS"))


def grade(mission_id, code):
    """Run a submission the way the server would and return
    (passed, reason, sandbox_result)."""
    mission = MISSIONS[mission_id]
    result = run_java(
        code,
        input_values=mission.get("inputs"),
        seed_files=mission.get("seed_files"),
    )
    if not result["ok"]:
        return False, result["error"]["message"], result
    ok, reason = check_mission(mission_id, code, result["output"], result.get("files"))
    return ok, reason, result


def structural_checks(topics):
    for topic in topics:
        ids = [m for m in MISSIONS if MISSIONS[m]["topic"] == topic]
        if not ids:
            continue
        numbers = sorted(int(re.match(r"t\d+m(\d+)$", m).group(1)) for m in ids)
        check("topic {} ids are contiguous from 1".format(topic),
              numbers == list(range(1, len(numbers) + 1)),
              "got {}".format(numbers))

        titles = [MISSIONS[m]["title"] for m in ids]
        check("topic {} titles are unique".format(topic),
              len(set(titles)) == len(titles),
              "duplicates: {}".format([t for t in titles if titles.count(t) > 1]))

        for mission_id in sorted(ids, key=lambda m: int(m.split("m")[1])):
            mission = MISSIONS[mission_id]
            label = mission_id

            missing = [k for k in REQUIRED_KEYS if not mission.get(k)]
            check("{} has every required field".format(label), not missing,
                  "missing/empty: {}".format(missing))
            if missing:
                continue

            check("{} topic_name matches the syllabus".format(label),
                  mission["topic_name"] == TOPIC_NAMES[topic],
                  "got {!r}".format(mission["topic_name"]))
            check("{} has exactly 2 hints".format(label),
                  len(mission["hints"]) == 2 and all(h.strip() for h in mission["hints"]),
                  "got {}".format(len(mission["hints"])))
            check("{} teaches before it tests".format(label),
                  len(mission["teach"]) >= 200,
                  "teach is only {} chars".format(len(mission["teach"])))

            if is_boss(mission):
                check("{} (BOSS) starts from an empty editor".format(label),
                      mission.get("boilerplate", "") == "",
                      "boss missions give no starter code")
            else:
                check("{} shows a worked example".format(label),
                      any(marker in mission["teach"] for marker in EXAMPLE_MARKERS),
                      "teach has none of {}".format(EXAMPLE_MARKERS))
                check("{} has starter code".format(label),
                      bool(mission.get("boilerplate")),
                      "non-boss missions pre-fill the editor")


def behavioural_jobs(topics):
    jobs = []
    for mission_id, mission in MISSIONS.items():
        if mission["topic"] not in topics:
            continue
        jobs.append(("solution", mission_id, mission["solution"]))
        for index, wrong in enumerate(mission.get("wrong") or []):
            jobs.append(("wrong{}".format(index + 1), mission_id, wrong))
        boilerplate = mission.get("boilerplate")
        if boilerplate and not is_boss(mission):
            jobs.append(("boilerplate", mission_id, boilerplate))
    return jobs


def run_job(job):
    kind, mission_id, code = job
    ok, reason, _ = grade(mission_id, code)
    return kind, mission_id, ok, reason


def main():
    requested = [int(a) for a in sys.argv[1:] if a.isdigit()]
    topics = requested or sorted(TOPIC_NAMES)

    built = [t for t in topics if any(m["topic"] == t for m in MISSIONS.values())]
    print("Verifying topics {} — {} missions\n".format(
        ", ".join(str(t) for t in built), sum(1 for m in MISSIONS.values() if m["topic"] in built)))

    structural_checks(built)

    jobs = behavioural_jobs(built)
    with ThreadPoolExecutor(max_workers=WORKERS) as pool:
        for kind, mission_id, ok, reason in pool.map(run_job, jobs):
            if kind == "solution":
                check("{} reference solution passes".format(mission_id), ok, reason)
            elif kind == "boilerplate":
                check("{} starter code does not already pass".format(mission_id), not ok,
                      "the pre-filled editor content passes the checker on its own")
            else:
                check("{} {} submission is rejected".format(mission_id, kind), not ok,
                      "a deliberately wrong submission was accepted")

    for topic in built:
        count = sum(1 for m in MISSIONS.values() if m["topic"] == topic)
        print("  Topic {:>2}  {:<38} {:>2}/25 missions".format(topic, TOPIC_NAMES[topic], count))

    total = sum(1 for m in MISSIONS.values() if m["topic"] in built)
    print("\n{} checks passed, {} failed  ({} missions, {} sandbox runs)".format(
        passed, len(failures), total, len(jobs)))
    for name, detail in failures:
        print("  FAIL: {}  {}".format(name, detail))
    sys.exit(1 if failures else 0)


if __name__ == "__main__":
    main()
