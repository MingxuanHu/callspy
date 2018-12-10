callspy
=======

A simple tracing agent

Build:
gradlew jar

Run:
java -Xbootclasspath/p:lib/byte-buddy-1.4.25.jar:build/libs/callspy-0.1.jar -javaagent:build/libs/callspy-0.1.jar org.example.callspy.example.RandomClassCallingThings

gradle clean build

java -javaagent:build/libs/callspy-0.1.jar com.zeroturnaround.callspy.CallspyDrawer -c org.example.callspy.example.RandomClassCallingThings

java -javaagent:build/libs/callspy-0.1.jar com.zeroturnaround.callspy.CallspyDrawer -c org.example.spring.ioc.DemoFileSystemXmlApplicationContext

java -javaagent:build/libs/callspy-0.1.jar com.zeroturnaround.callspy.CallspyDrawer -m -o -f call-tree.txt -g gephi.gexf -c org.example.simple.Class1
