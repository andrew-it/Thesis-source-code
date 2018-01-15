open class SomeTypeValue(private val lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.VAL_INT
}

class VarName(private val name: String) : SymbolicSeq(name)

class StrValue(private val value: SymbolicSeq) : SomeTypeValue(Lexemes(EXPR_TYPES.VALUE, value)) {
    override val pattern_type = PATTERN_TYPES.VAL_STR
}

class IntValue(private val value: SymbolicSeq) : SomeTypeValue(Lexemes(EXPR_TYPES.VALUE, value)) {
    override val pattern_type = PATTERN_TYPES.VAL_INT
}

class VarDecl(private val lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.VAR_DECL
}

class VarAssignment(private val lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.VAR_ASSIGNMENT
}