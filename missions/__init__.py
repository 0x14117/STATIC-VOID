"""
STATIC VOID mission content, one module per syllabus topic.

This was a single missions.py while the game had 25 missions. At the
target of 250 — 25 per topic across the ten topics of the module — one
file stopped being workable, so each topic now lives in its own module and
this package stitches them back into the single MISSIONS mapping that
server.py and the checkers already expected. Nothing that imports
`from missions import MISSIONS, check_mission` had to change.

Mission ids are "t<topic>m<number>", e.g. t7m13 is Topic 7, mission 13.
Within a topic the missions are ordered as a taught sequence, and every
fifth one is a BOSS that combines the preceding four with no starter code:
missions 5, 10, 15 and 20 are checkpoints, and mission 25 is the topic's
FINAL BOSS.
"""

from . import (
    topic01, topic02, topic03, topic04, topic05,
    topic06, topic07, topic08, topic09, topic10,
)

TOPIC_MODULES = [
    topic01, topic02, topic03, topic04, topic05,
    topic06, topic07, topic08, topic09, topic10,
]

TOPIC_NAMES = {
    1: "Language & IDE Basic Elements",
    2: "Variables & Constants",
    3: "Operators, Expressions & Statements",
    4: "Methods, Parameters and the Stack",
    5: "Basic I/O & File I/O",
    6: "Selection",
    7: "Iteration",
    8: "Collections",
    9: "Classes",
    10: "Exceptions & Event Handling",
}


def _build_missions():
    """Merge the per-topic mappings in syllabus order.

    A duplicate id across two modules would silently overwrite a mission —
    easy to do when copying a batch of five as a starting point for the
    next — so it fails loudly here instead of quietly losing content.
    """
    merged = {}
    for module in TOPIC_MODULES:
        for mission_id, mission in module.MISSIONS.items():
            if mission_id in merged:
                raise ValueError(
                    "Duplicate mission id {!r} — defined twice across the topic "
                    "modules.".format(mission_id)
                )
            merged[mission_id] = mission
    return merged


MISSIONS = _build_missions()


def check_mission(mission_id, code, output, files=None):
    """files is the {filename: contents} dict of whatever the player's
    code wrote to the sandboxed run directory (see java_sandbox), so
    File I/O missions can verify real written content instead of
    trusting a printed success message. Missions that don't involve
    files simply ignore it."""
    mission = MISSIONS.get(mission_id)
    if mission is None:
        return False, "That mission doesn't exist."
    return mission["check"](code, output, files or {})
