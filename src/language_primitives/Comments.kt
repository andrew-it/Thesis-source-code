package language_primitives

import additional.EXPR_TYPES
import additional.Lexemes
import additional.PATTERN_TYPES
import additional.Symbols

class OneStringComment(comment: String) : Patternable(Lexemes(EXPR_TYPES.VALUE, Symbols(comment))) {
    override val patternType = PATTERN_TYPES.ONE_STRING_COMMENT
}