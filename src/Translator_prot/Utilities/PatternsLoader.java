package Translator_prot.Utilities;

import Translator_prot.Expressions.EXPR_TYPES;
import Translator_prot.Expressions.PATTERN_TYPES;
import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class PatternsLoader {
    static private String path_str = "c_patterns.json";
    static private String file_content;
    static private JSONObject json_object = null;
    private PatternsLoader(){}

    public static String getPattern(PATTERN_TYPES expr_type) throws IOException {
        if(json_object == null)
            loadPatterns();
        return json_object.getJSONObject("patterns").
                getJSONObject(expr_type.toString()).getString("pattern");
    }

    public static String getDependency(String func_name) throws IOException {
        if(json_object == null)
            loadPatterns();
        JSONObject dependencies = json_object.getJSONObject("dependencies");
        for (String key:dependencies.toMap().keySet()) {
            if(dependencies.getJSONArray(key).toList().contains(func_name))
                return key;
        }
        return null;
    }

    private static void loadPatterns() throws IOException {
        path_str = System.getProperty("user.dir") + "/patterns/c_patterns.json";
        file_content = new String(Files.readAllBytes(Paths.get(path_str)));
        json_object = new JSONObject(file_content);
    }
}
