#!/bin/sh
# CYBER//OPS - compile and play, in one step.
set -e

# Move to the project folder first, so a path with spaces above us cannot
# matter and the source pattern stays short.
cd "$(dirname "$0")"

echo "Compiling..."
javac -d out src/*.java

echo
java -cp out Main
