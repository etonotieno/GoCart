#!/bin/sh

echo "Running static analysis."

./gradlew detekt
./gradlew spotlessCheck
