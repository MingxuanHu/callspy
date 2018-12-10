package com.zeroturnaround.callspy;

import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CallspyConfig {
    private static Map yaml;
    static {
        try {
            YamlReader reader = new YamlReader(new FileReader("config/callspy-drawer.yml"));
            yaml = (Map) reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Map<String, Object>> getCALLSPY() {
        return (Map) yaml.get("CALLSPY");
    }

    @SuppressWarnings("unchecked")
    public static String getStartWithRegex() {
        String regex = "";
        String prefix = "";
        List<String> params = (List<String>)  getCALLSPY().get("IncludePackages").get("startWith");
        for (String param : params) {
            regex += prefix + param.replace(".", "\\.");
            prefix = "|";
        }
        return "^(" + regex + "){1}.*$";
    }
}
