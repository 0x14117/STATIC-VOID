#!/bin/sh
# CYBER//OPS - compile and play, in one step.
set -e
cd "$(dirname "$0")"

echo "Compiling..."
javac -d out src/*.java

echo
java -cp out Main
