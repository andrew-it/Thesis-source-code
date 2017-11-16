package Translator_prot.Expressions;

import Translator_prot.Utilities.PatternsLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Dependency {
    private static HashSet<String> used_libs = new HashSet<>();
    private static HashMap<String, String> C_dependencies_table = new HashMap<>();
    private static String pattern = "#include <%LIB%>";

    private Dependency() {
    }

    private static void init() throws IOException {
        pattern = PatternsLoader.getPattern(PATTERN_TYPES.DEPENDENCY);
    }

    public static String construct() throws IOException {
        String result = "";
        for (String lib : used_libs) {
            result = result.concat(pattern.replace("%LIB%", lib));
        }
        return result;
    }

    public static void add_link(String link) throws IOException {
        String lib = PatternsLoader.getDependency(link);
        if (lib != null)
            used_libs.add(lib);
    }
}
