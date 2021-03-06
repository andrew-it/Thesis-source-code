package language_primitives

import additional.Lexemes
import additional.PATTERN_TYPES

class _IfCondition(lexeme: Lexemes) : Patternable(lexeme) {
    override val patternType: PATTERN_TYPES = PATTERN_TYPES.IF_CONDITION
}

class _ElseCondition(lexeme: Lexemes) : Patternable(lexeme) {
    override val patternType: PATTERN_TYPES = PATTERN_TYPES.ELSE_CONDITION
}

class TernaryCondition(lexeme: Lexemes) : Patternable(lexeme) {
    override val patternType: PATTERN_TYPES = PATTERN_TYPES.TERNARY
}

class IfCondition(private val Cond: Lexemes, private val If: Lexemes, private val Else: Lexemes? = null) : Constructable {
    override fun construct(): String {
        If.merge(Cond)
        val ifCondition = _IfCondition(If)
        var condition: String = ifCondition.construct()
        if (Else != null) {
            val elseCondition = _ElseCondition(Else)
            condition += elseCondition.construct()
        }
        return condition
    }
}