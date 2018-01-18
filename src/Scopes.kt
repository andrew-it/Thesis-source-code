import PatternsLoader.getDependency
import java.util.*

class Scope(private val construncts: Array<Constructable>) : Constructable {
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

object Dependencies : Constructable {
    private var deps_set: HashSet<Dependency> = hashSetOf()

    override fun construct(): String {
        return deps_set.joinToString("\n") { it.construct() }
    }

    fun addDependency(dependency: String) {
        val lib = getDependency(dependency)
        if (lib != null)
            deps_set.add(Dependency(lib))
    }
}

class Dependency(lexeme: Lexemes) : Patternable(lexeme) {
    constructor(lib_name: String) : this(Lexemes(
            hashMapOf(Pair(EXPR_TYPES.LIB, SymbolicSeq(lib_name))))
    )

    override val pattern_type = PATTERN_TYPES.DEPENDENCY
}

class EntryPoint(lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.ENTRY_POINT
}