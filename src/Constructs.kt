import kotlin.collections.HashMap
import java.util.*

data class Lexemes(val lexemes: HashMap<EXPR_TYPES, Lexemable>)

interface Constructable {
    fun construct(): String
}

interface Lexemable

abstract class Patternable(private val lexeme: Lexemes) : Constructable, Lexemable {
    abstract val pattern_type: PATTERN_TYPES

    override fun construct(): String {
        var tmp_pattern = PatternsLoader.getPatternByType(pattern_type)
        for (lexeme in lexeme.lexemes.keys)
            tmp_pattern = tmp_pattern.replace(lexeme.toString(),
                    this.lexeme.lexemes[lexeme].toString())
        return tmp_pattern
    }

    override fun toString(): String {
        return construct()
    }
}

object Dependencies: Constructable{
    private var deps_set: HashSet<Dependency> = hashSetOf()
    override fun construct(): String {
        return deps_set.joinToString("\n") { it.construct() }
    }

    fun addDependency(dependency: String){
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

class Scope(private val construncts: Array<Patternable>) : Constructable, Lexemable {
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

class StrStub(private val stub: String) : Constructable, Lexemable {
    override fun construct(): String {
        return stub
    }

    override fun toString(): String {
        return construct()
    }
}

class StrVariable(private val value: String) : Constructable, Lexemable {
    override fun construct(): String {
        return "\"$value\""
    }

    override fun toString(): String {
        return construct()
    }
}

class Parameters(private val params: Array<StrStub>) : Constructable, Lexemable {
    override fun construct(): String {
        return params.joinToString(",") { it.construct() }
    }
    override fun toString(): String {
        return construct()
    }
}