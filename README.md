callspy
=======

A simple tracing agent

Build:
gradlew jar

Run:
java -Xbootclasspath/p:lib/byte-buddy-1.4.25.jar:build/libs/callspy-0.1.jar -javaagent:build/libs/callspy-0.1.jar org.example.callspy.example.RandomClassCallingThings
