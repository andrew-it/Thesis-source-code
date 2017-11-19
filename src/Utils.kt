import org.json.JSONObject
import java.nio.file.Files
import java.nio.file.Paths

object PatternsLoader{
    private var path_str = "src/c_patterns.json"
    private var file_content: String = String(Files.readAllBytes(Paths.get(path_str)))
    private var json_object: JSONObject = JSONObject(file_content)


    fun getPatternByType(type: PATTERN_TYPES): String{
        return json_object.getJSONObject("patterns")
                .getJSONObject(type.toString())
                .getString("pattern")
    }

    fun getDependency(func_name: String): String{
        val dependencies = json_object.getJSONObject("dependencies")
        for (key in dependencies.toMap().keys) {
            if (dependencies.getJSONArray(key).toList().contains(func_name))
                return key
        }
        return ""
    }
}

