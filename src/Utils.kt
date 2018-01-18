import org.json.JSONObject
import java.nio.file.Files
import java.nio.file.Paths

object PatternsLoader {
    private const val path_str = "src/c_patterns.json"
    private val file_content: String = String(Files.readAllBytes(Paths.get(path_str)))
    private val json_object: JSONObject = JSONObject(file_content)
    private val dependencies: JSONObject = json_object.getJSONObject("dependencies")
    private val standard_types: JSONObject = json_object.getJSONObject("standard_types")
    private val patterns: JSONObject = json_object.getJSONObject("patterns")


    fun getPatternByType(type: PATTERN_TYPES): String {
        return patterns.getJSONObject(type.toString())
                .getString("pattern")
    }

    fun getDependency(func_name: String): String? {
        return dependencies.toMap().keys.firstOrNull {
            dependencies.getJSONArray(it).toList().contains(func_name)
        }
    }

    fun getType(slang_type: STD_TYPES): String {
        if (standard_types.has(slang_type.toString()))
            return standard_types.getString(slang_type.toString())
        else
            return "ERROR_IN_TYPES"
    }
}

class TypeConverter(private val type: STD_TYPES) : Constructable {
    override fun construct(): String {
        return PatternsLoader.getType(type)
    }
}