import java.util.HashSet

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

object Dependencies : Constructable {
    // TODO: create list of dependencies. after that, check functions in this list on adding
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
            hashMapOf(Pair(EXPR_TYPES.LIB, SymbolicSeq(PatternsLoader.getDependency(func_name)))))
    )

    override val pattern_type = PATTERN_TYPES.DEPENDENCY
}