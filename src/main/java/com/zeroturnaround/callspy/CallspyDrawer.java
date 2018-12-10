package com.zeroturnaround.callspy;

import com.zeroturnaround.callspy.gephi.GephiGenerator;
import org.apache.commons.cli.*;

public class CallspyDrawer {
    public static void main(String... args) {
        Options options = new Options();

        Option manualOpt = new Option("m", "manual", false,
                "manually choose which method to be logged in call graph.");
        manualOpt.setRequired(false);
        options.addOption(manualOpt);

        Option consoleOpt = new Option("o", "console", false,
                "set if you want the call tree to be printed to console.");
        consoleOpt.setRequired(false);
        options.addOption(consoleOpt);

        Option fileOpt = new Option("f", "file", true,
                "output the call tree to file.");
        fileOpt.setRequired(false);
        options.addOption(fileOpt);

        Option gephiOpt = new Option("g", "gephi", true,
                "use the call tree to generate a Gephi xml file, must be used together with -f.");
        gephiOpt.setRequired(false);
        options.addOption(gephiOpt);

        Option classOpt = new Option("c", "class", true,
                "The main class of which a call graph will be generated.");
        classOpt.setRequired(true);
        options.addOption(classOpt);

        Option argOpt = new Option("a", "arguments", true,
                "The argument(s) of the main class if it has any, separated by comma without space.");
        argOpt.setRequired(false);
        options.addOption(argOpt);



        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("gephi") && ! cmd.hasOption("file"))
                throw new Exception("-g must be used together with -f");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java -javaagent:build/libs/callspy-0.1.jar " +
                    "com.zeroturnaround.callspy.CallspyDrawer [-m] [-o] [-f output/call/tree.txt] " +
                            "[-g output/Gephi/graph.xml] -c main.class.name [-a arg1,arg2,arg3,...]",
                    options);
        }

        try {
            if (cmd.hasOption("manual"))
                MyAdvice.manual = true;

            if (cmd.hasOption("file")) {
                Stack.outputFile = cmd.getOptionValue("file");
                Stack.stringBuffer = new StringBuffer(524288);
            }

            if (cmd.hasOption("console")) {
                Stack.outputToConsole = true;
            }

            String[] classArgs = new String[0];
            if (cmd.hasOption("arguments"))
                classArgs = cmd.getOptionValue("arguments").split(",");
            Class.forName(cmd.getOptionValue("class"))
                    .getMethod("main", classArgs.getClass())
                    .invoke(null, (Object) classArgs);

            if (cmd.hasOption("file")) {
                Stack.flushToFile();
                System.out.println("-- The call tree file has been generated: " + Stack.outputFile);
                if (cmd.hasOption("gephi")) {
                    GephiGenerator.generateGephiXml(Stack.outputFile, cmd.getOptionValue("gephi"));
                    System.out.println("-- The Gephi xml file has been generated: " + cmd.getOptionValue("gephi"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
