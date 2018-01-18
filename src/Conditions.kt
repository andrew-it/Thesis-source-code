class _IfCondition(lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type: PATTERN_TYPES = PATTERN_TYPES.IF_CONDITION
}

class _ElseCondition(lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type: PATTERN_TYPES = PATTERN_TYPES.ELSE_CONDITION
}

class TernaryCondition(lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type: PATTERN_TYPES = PATTERN_TYPES.TERNARY
}

class IfCondition(val Cond: Lexemes, val If: Lexemes, val Else: Lexemes? = null) : Constructable {
    override fun construct(): String {
        If.merge(Cond)
        val ifCondition: _IfCondition = _IfCondition(If)
        var condition: String = ifCondition.construct()
        if (Else != null) {
            val elseCondition = _ElseCondition(Else)
            condition += elseCondition.construct()
        }
        return condition
    }
}