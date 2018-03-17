package additional

import language_primitives.Constructable
import org.json.JSONObject
import java.nio.file.Files
import java.nio.file.Paths

object PatternsLoader {
    private const val path_str = "src/json_patterns/c_patterns.json"
    private val file_content: String = String(Files.readAllBytes(Paths.get(path_str)))
    private val json_object: JSONObject = JSONObject(file_content)
    private val dependencies: JSONObject = json_object.getJSONObject("dependencies")
    private val standard_types: JSONObject = json_object.getJSONObject("standard_types")
    private val patterns: JSONObject = json_object.getJSONObject("patterns")
    private val standard_functions: JSONObject = json_object.getJSONObject("standard_functions")


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
        return if (standard_types.has(slang_type.toString()))
            standard_types.getString(slang_type.toString())
        else
            "ERROR_IN_TYPES"
    }

    fun getStdFuncName(func_name: STD_FUNCTIONS): String {
        return standard_functions.getJSONObject(func_name.toString().toLowerCase()).getString("name")
    }
}

class TypeConverter(private val type: STD_TYPES) : Constructable {
    override fun construct(): String {
        return PatternsLoader.getType(type)
    }
}

fun cleanStr(str: String): String {
    return str.replace("\n", "").replace("\t", "")
}

fun textWrap(str: String): String {
    return "\n========\t$str\t========\n"
}