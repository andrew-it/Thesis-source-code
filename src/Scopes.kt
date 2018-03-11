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

class SourceCodeFile(private val scopes: Array<Scope>, override var filename: Constructable) : SourceCode {
    override fun construct(): String {
        return Dependencies.construct() +
                scopes.joinToString("\n") { it.construct() }
    }
}

class ClassDeclaration(lexeme: Lexemes) : Constructable {
    /**
     * lexeme contain: classname, methods array, members array
     * */
    val className = lexeme.lexemes[EXPR_TYPES.TYPE_ALIAS]
    val methodsArray = lexeme.lexemes[EXPR_TYPES.FUNCTION_DECLARATIONS]
    val membersArray = lexeme.lexemes[EXPR_TYPES.MEMBER_DECLARATIONS]
    override fun construct(): String {
        // FIXME !!
        val classFile = _ClassFile(Lexemes(
                EXPR_TYPES.BODY, membersArray!!,
                EXPR_TYPES.FUNCTION_DECLARATIONS, methodsArray!!,
                EXPR_TYPES.TYPE_ALIAS, className!!
        ),
                className)
        return "TODO"
    }
}

class _HeaderFile(lexeme: Lexemes, override var filename: Constructable) : Patternable(lexeme), SourceCode {
    init {
        this.filename = SymbolicSeq(this.filename.construct() + ".h")
    }

    override val pattern_type = PATTERN_TYPES.HEADER_FILE
}

class _ClassFile(lexeme: Lexemes, override var filename: Constructable) : Patternable(lexeme), SourceCode {
    init {
        this.filename = SymbolicSeq(this.filename.construct() + ".c")
    }

    override val pattern_type = PATTERN_TYPES.CLASS_DECL
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
        return sourceCodeFiles.joinToString("\n") {
            textWrap(it.filename.construct()) + "\n" + it.construct()
        }
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