#!/bin/bash
javac -encoding utf8 -d ../proto/target/ ./src/main/java/model/*.java  ./src/main/java/Interpreter.java
FILES=./input/*.txt
    for f in $FILES
    do
        java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -cp ../proto/target/ Interpreter < $f > ./output/$(basename $f)
    done
