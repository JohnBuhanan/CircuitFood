#!/usr/bin/env zsh

oldpwd=$(pwd)
gitroot=$(git rev-parse --show-toplevel)

cd "$gitroot"/featuregenerator || exit 1
./gradlew installDist
build/install/featuregenerator/bin/featuregenerator

cd "$oldpwd" || exit 1
