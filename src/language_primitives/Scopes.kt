package language_primitives

import additional.*
import additional.PatternsLoader.getDependency
import java.util.*

class Scope(private val constructs: Array<Constructable>) : Constructable {
    override fun construct(): String {
        return constructs.joinToString("\n") { it.construct() }
    }

    override fun toString(): String {
        return construct()
    }
}

class SourceCodeFile(private val scopes: Array<Scope>, override var filename: Constructable) : SourceCode {
    override fun construct(): String {
        return textWrap(filename.construct()) + "\n" + Dependencies.construct() +
                scopes.joinToString("\n") { it.construct() }
    }
}

class Project : Constructable {
    /**
     * Creates source code files
     * Creates assembly file
     * */
    companion object {
        var sourceCodeFiles: Array<SourceCode> = emptyArray()
    }

    override fun construct(): String {
        return sourceCodeFiles.joinToString("\n") { it.construct() }
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
        val lib = getDependency(dependency)
        if (lib != null)
            deps_set.add(Dependency(lib))
    }
}

class Dependency(lexeme: Lexemes) : Patternable(lexeme) {
    constructor(lib_name: String) : this(Lexemes(
            hashMapOf(Pair(EXPR_TYPES.LIB, SymbolicSeq(lib_name))))
    )

    override val patternType = PATTERN_TYPES.DEPENDENCY
}

class EntryPoint(lexeme: Lexemes) : Patternable(lexeme) {
    override val patternType = PATTERN_TYPES.ENTRY_POINT
}