#!/bin/sh
# CYBER//OPS - compile and play, in one step.

# Move to the project folder first, so a path with spaces above us cannot
# matter and the source pattern stays short.
cd "$(dirname "$0")" || exit 1

if ! command -v javac >/dev/null 2>&1; then
    echo "javac was not found, so the game cannot be compiled."
    echo "You need a JDK (Java Development Kit), not just Java."
    echo "See 'Step 1: install Java' in README.md, or https://adoptium.net/"
    exit 1
fi

if [ ! -d src ]; then
    echo "There is no src folder here."
    echo "If this folder only contains README.md, you are on the main branch."
    echo "Switch to the branch holding the game:"
    echo
    echo "    git checkout claude/mission-practice-steps-2oh6pn"
    exit 1
fi

echo "Compiling..."
if ! javac -d out src/*.java; then
    echo
    echo "Compile failed. Nothing was run."
    exit 1
fi

echo
exec java -cp out Main
