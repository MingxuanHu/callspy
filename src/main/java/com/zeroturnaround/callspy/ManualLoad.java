package com.zeroturnaround.callspy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ManualLoad {
    private static boolean ifLoad = false;
    private static String outputFile;
    private static List<String> loadedHistory;
    private static int loadedHistoryPointer = 0;
    private static List<String> choosingHistory;

    public static boolean ifLoad() {
        return ifLoad;
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
                if (line.trim().isEmpty())
                    continue;
                loadedHistory.add(line);
            }
        }
    }

    public static String getNextLoaded() {
        if (loadedHistoryPointer == loadedHistory.size())
            return null;
        return loadedHistory.get(loadedHistoryPointer++);
    }


    public static void log(String input) {
        if (choosingHistory == null) {
            choosingHistory = new ArrayList<>();
        }
        choosingHistory.add(input);
    }

    public static void flush() throws Exception {
        try (PrintWriter out = new PrintWriter(outputFile)) {
            if (choosingHistory == null || choosingHistory.isEmpty())
                return;
            for (String str : choosingHistory) {
                out.println(str);
            }
        }
    }
}
