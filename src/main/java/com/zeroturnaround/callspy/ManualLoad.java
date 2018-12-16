package com.zeroturnaround.callspy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ManualLoad {
    private static boolean yesForAll = false;
    private static boolean noForAll = false;
    private static boolean ifLoad = false;
    private static String outputFile;
    private static List<String> loadedHistory;
    private static int loadedHistoryPointer = 0;
    private static List<String> choosingHistory;
    private static List<String> choosingHistoryLog;

    public static boolean ifLoad() {
        return ifLoad;
    }

    public static void doYesForAll() {
        yesForAll = true;
    }

    public static void doNoForAll() {
        noForAll = true;
    }

    public static void load(String inputFile, String outputFile) throws Exception {
        ManualLoad.outputFile = outputFile;
        ifLoad = true;

        if (loadedHistory == null) {
            loadedHistory = new ArrayList<>();
        }


        try (FileReader fileReader = new FileReader(inputFile)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().isEmpty() || line.trim().startsWith("#"))
                    continue;

                loadedHistory.add(line.split("-->")[0]);
            }
        }
    }

    public static String getNextLoaded() {
        if (yesForAll)
            return "y";
        if (noForAll)
            return "n";
        if (loadedHistoryPointer == loadedHistory.size())
            return null;
        return loadedHistory.get(loadedHistoryPointer++);
    }


    public static void log(String input, String inputLog) {
        if (yesForAll || noForAll)
            return;
        if (choosingHistory == null) {
            choosingHistory = new LinkedList<>();
            choosingHistoryLog = new LinkedList<>();
        }
        choosingHistory.add(input);
        choosingHistoryLog.add(inputLog);
    }

    public static void flush() throws Exception {
        try (PrintWriter out = new PrintWriter(outputFile)) {
            if (choosingHistory == null || choosingHistory.isEmpty())
                return;
            ListIterator<String> listIterator = choosingHistoryLog.listIterator();
            for (String str : choosingHistory) {
                out.println(str + "-->" + listIterator.next());
            }
        }
    }
}
