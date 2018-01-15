class FunctionCall(lexeme: Lexemes) : Patternable(lexeme) {
    init {
        Dependencies.addDependency(lexeme.lexemes[EXPR_TYPES.FUNC_NAME].toString())
    }

    override val pattern_type = PATTERN_TYPES.FUNC_CALL
}

class FunctionDecl(lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.FUNC_DECL
}

class Parameters(private val params: Array<SymbolicSeq>) : Constructable {
    override fun construct(): String {
        return params.joinToString(",") { it.construct() }
    }
}
