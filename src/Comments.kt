class OneStringComment(comment: String) : Patternable(Lexemes(EXPR_TYPES.VALUE, Symbols(comment))) {
    override val patternType = PATTERN_TYPES.ONE_STRING_COMMENT
}