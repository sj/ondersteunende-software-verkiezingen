#!/bin/bash
#
# Very simple build script. Note that this is not intended to build the code in
# a way that makes it actually runnable. It's purely intended to make it build
# so it can be analysed using lgtm.com.
#
# It may run, it may not.

sources_file=`mktemp`
find -name "*.java" >> "$sources_file"

classpath_jars=`find -name "*.jar" | tr "\n" ":"`
classpath=".:$classpath_jars"

numfiles=`wc -l "$sources_file"`

echo "Attempting to build $numfiles source files using the following classpath:"
echo $classpath

javac -Xmaxerrs 10 -encoding ISO-8859-1 -classpath "$classpath" @$sources_file
javac_res="$?"

rm "$sources_file"

if [ "$javac_res" = "0" ]; then
    echo "Build succesful!"
else
    echo "Build failed"
fi
