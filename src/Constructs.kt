import kotlin.collections.HashMap
import java.util.*

interface Constructable {
    fun construct(): String
}

data class Lexemes(val lexemes: HashMap<EXPR_TYPES, Constructable>) {
    constructor(type: EXPR_TYPES, lexeme: Constructable) : this(
            hashMapOf(Pair(type, lexeme))
    )
}

abstract class Patternable(private val lexeme: Lexemes) : Constructable {
    abstract val pattern_type: PATTERN_TYPES

    override fun construct(): String {
        var tmp_pattern = PatternsLoader.getPatternByType(pattern_type)
        for (le in lexeme.lexemes.keys)
            tmp_pattern = tmp_pattern.replace(le.toString(),
                    lexeme.lexemes[le].construct()) // TODO !!
        return tmp_pattern
    }

    override fun toString(): String {
        return construct()
    }
}

object Dependencies : Constructable {
    private var deps_set: HashSet<Dependency> = hashSetOf()
    override fun construct(): String {
        return deps_set.joinToString("\n") { it.construct() }
    }

    fun addDependency(dependency: String) {
        deps_set.add(Dependency(dependency))
    }
}

class Dependency(lexeme: Lexemes) : Patternable(lexeme) {
    constructor(func_name: String) : this(Lexemes(
            hashMapOf(Pair(EXPR_TYPES.LIB, StrStub(PatternsLoader.getDependency(func_name)))))
    )

    override val pattern_type = PATTERN_TYPES.DEPENDENCY
}

class FunctionCall(lexeme: Lexemes) : Patternable(lexeme) {
    init {
        Dependencies.addDependency(lexeme.lexemes[EXPR_TYPES.FUNC_NAME].toString())
    }

    override val pattern_type = PATTERN_TYPES.FUNC_CALL
    override fun construct(): String {
        return super.construct()
    }
}

class FunctionDecl(lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.FUNC_DECL
}

class Scope(private val construncts: Array<Patternable>) : Constructable {
    override fun construct(): String {
        return construncts.joinToString("\n") { it.construct() }
    }

    override fun toString(): String {
        return construct()
    }
}

class SourceCode(private val scopes: Array<Scope>) : Constructable {
    override fun construct(): String {
        return Dependencies.construct() +
                scopes.joinToString("\n") { it.construct() }
    }
}

class Project(private val files: Array<SourceCode>) : Constructable {
    override fun construct(): String {
        return files.joinToString("======") { it.construct() }
    }
}

class StrStub(private val stub: String) : Constructable {
    override fun construct(): String {
        return stub
    }
}

class StrVariable(private val lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.VAR_STR
}

class Parameters(private val params: Array<StrStub>) : Constructable {
    override fun construct(): String {
        return params.joinToString(",") { it.construct() }
    }
}