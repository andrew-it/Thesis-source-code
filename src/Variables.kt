open class SomeTypeValue(lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.VAL_INT // FIXME val_int
}

class VarName(name: String) : SymbolicSeq(name)

class ReturnType(val type: STD_TYPES) : Constructable {
    override fun construct(): String {
        return type.toString().toLowerCase() // FIXME make sth more adequate
    }
}

class StrValue(value: SymbolicSeq) : SomeTypeValue(Lexemes(EXPR_TYPES.VALUE, value)) {
    override val pattern_type = PATTERN_TYPES.VAL_STR
}

class IntValue(value: SymbolicSeq) : SomeTypeValue(Lexemes(EXPR_TYPES.VALUE, value)) {
    override val pattern_type = PATTERN_TYPES.VAL_INT
}

class VarDecl(lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.VAR_DECL
}

class VarAssignment(lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.VAR_ASSIGNMENT
}

class CollectionOfVariables(val collectionOfVars: Array<VarDecl>) : Constructable {
    override fun construct(): String {
        return collectionOfVars.joinToString("\n") { it.construct() }
    }

    fun addVar(variable: VarDecl) {
        collectionOfVars.plus(variable)
    }
}