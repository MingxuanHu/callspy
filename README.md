callspy2.0
=======
[中文](https://blog.csdn.net/xuan602/article/details/84931665)

A simple tracing agent which prints your single thread java program's dynamic call graph as a raw text tree or [Gephi](https://gephi.org/) importable file.

#### Build:
```
gradle build
```
#### Usage:
Assume that you are at the root directory of the project and you have built the project using gradle.
```
usage: java -javaagent:build/libs/callspy-0.1.jar
            com.zeroturnaround.callspy.CallspyDrawer [-m] [-o] [-l
            manual/in.txt manual/out.txt] [-f output/call/tree.txt] [-g
            output/Gephi/graph.xml] -c main.class.name [-a
            arg1,arg2,arg3,...]
 -a,--arguments <arg>   The argument(s) of the main class if it has any,
                        separated by comma without space.
 -c,--class <arg>       The main class of which a call graph will be
                        generated.
 -f,--file <arg>        Output the call tree to file.
 -g,--gephi <arg>       Use the call tree to generate a Gephi xml file,
                        must be used together with -f.
 -l,--load <arg> <arg>  Load the manual choosing process from a file (can
                        be an empty file), and write your choosing history
                        to another file.
 -m,--manual            Manually choose which method to be logged in call
                        graph.
 -o,--console           Set if you want the call tree to be printed to
                        console.
```

#### Draw the examples:
- Simply print the call graph on console.
```
java -javaagent:build/libs/callspy-0.1.jar com.zeroturnaround.callspy.CallspyDrawer -o -c org.example.simple.Class1
```
- Save the call graph as a raw text tree and generate its Gephi counterpart.
```
java -javaagent:build/libs/callspy-0.1.jar com.zeroturnaround.callspy.CallspyDrawer -f call-tree.txt -g gephi.gexf -c org.example.simple.Class1
```
- Manually choose which method call to be logged in call graph, all the calls under this call will be ignored if you choose not to log this method. This could be useful when you are trying to generate a call graph for a big project.
```
java -javaagent:build/libs/callspy-0.1.jar com.zeroturnaround.callspy.CallspyDrawer -m -f call-tree.txt -c org.example.simple.Class1
```
- Load a manual choosing history from a file and output your method choosing history to a file. Better to be used when manual choosing is enable, this give you an opportunity to redo any mistakes: you can modify the output choosing history and load it again.
    - You can try `fuck off` during the method choosing process, this will save your current process and exit the program.
    - You can also use `yes for all` during the choosing process, this will automatically log all remaining methods. This could be useful when you not sure if you should skip a method or not. Automatically inputted yes will not be logged, thus you can easily resume latter by loading the saved process.
```
java -javaagent:build/libs/callspy-0.1.jar com.zeroturnaround.callspy.CallspyDrawer -m -o -l manual-in.txt manual-out.txt -c org.example.simple.Class1
```
#### Draw your own code:
- The simplest way is write your code under org.example package and set your main class name as the argument of `-c`.
- If you want to draw call graph of some third party packages, update config/callspy-drawer.yml by appending the prefix for the package of your interest under `startWith`, than use gradle to add dependency. You can refer to `org.example.spring.ioc.DemoFileSystemXmlApplicationContext` as an example.
- If you have your own jar file at somewhere, you still need to update the yml file as aforementioned, than add the path of your jar file to classpath:
```
java -classpath  C:\the\path\to\your\jar\file.jar -javaagent:build/libs/callspy-0.1.jar com.zeroturnaround.callspy.CallspyDrawer -m -o -l manual-in.txt manual-out.txt -f call-tree.txt -g gephi.gexf -c has.been.added.to.yml.YourClass
```
