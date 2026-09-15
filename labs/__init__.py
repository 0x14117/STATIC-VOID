"""
Lab content, one module per Head First Java chapter.

A lab is one idea: an explanation, a worked example, and four to six graded
practice tasks. See docs/02-LAB-SPEC.md for the contract every lab follows.

Lab ids are "chNN-labNN" and task ids are "tN" within a lab, so a task is
addressed globally as "ch03-lab02/t4".
"""

from . import chapter03

CHAPTER_MODULES = [
    chapter03,
]

CHAPTER_TITLES = {
    1: "Breaking the Surface",
    2: "A Trip to Objectville",
    3: "Know Your Variables",
    4: "How Objects Behave",
    5: "Extra-Strength Methods",
    6: "Using the Java Library",
    7: "Better Living in Objectville",
    8: "Serious Polymorphism",
    9: "Life and Death of an Object",
    10: "Numbers Matter",
    11: "Risky Behavior",
    12: "A Very Graphic Story",
    14: "Saving Objects",
    15: "Make a Connection",
    16: "Data Structures",
    17: "Lambdas and Streams",
}


def _build():
    """Merge the per-chapter mappings in order.

    A duplicate lab id would silently replace a whole lab — easy to do when
    starting a new lab from a copy of the last one — so it fails loudly here
    instead of quietly losing five tasks.
    """
    merged = {}
    for module in CHAPTER_MODULES:
        for lab_id, lab in module.LABS.items():
            if lab_id in merged:
                raise ValueError(
                    "Duplicate lab id {!r} across chapter modules.".format(lab_id)
                )
            seen = set()
            for task in lab["tasks"]:
                if task["id"] in seen:
                    raise ValueError(
                        "Duplicate task id {!r} in lab {!r}.".format(task["id"], lab_id)
                    )
                seen.add(task["id"])
            merged[lab_id] = lab
    return merged


LABS = _build()


def get_task(lab_id, task_id):
    """Return (lab, task), or (None, None) if either id is unknown."""
    lab = LABS.get(lab_id)
    if lab is None:
        return None, None
    for task in lab["tasks"]:
        if task["id"] == task_id:
            return lab, task
    return lab, None


def check_task(lab_id, task_id, code, output, files=None):
    """Grade one submission. Returns (passed, message)."""
    lab, task = get_task(lab_id, task_id)
    if task is None:
        return False, "That task does not exist."
    return task["check"](code, output, files or {})
