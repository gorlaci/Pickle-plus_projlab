#!/bin/bash
javac -encoding utf8 -d ../proto/target/ ../proto/src/main/java/model/*.java  ../proto/src/main/java/Interpreter.java
FILES=./input/*.txt
    for f in $FILES
    do
        echo "Processing $f file..."
        java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -cp ../proto/target/ Interpreter < $f > ./expected/$(basename $f)
    done
