interface Constructable {
    fun construct(): String
}

abstract class Patternable(private val lexeme: Lexemes) : Constructable {
    abstract val pattern_type: PATTERN_TYPES

    override fun construct(): String {
        var tmp_pattern = PatternsLoader.getPatternByType(pattern_type)
        for (le in lexeme.lexemes.keys)
            tmp_pattern = tmp_pattern.replace(le.toString(),
                    lexeme.lexemes[le]!!.construct()) // FIXME !!
        return tmp_pattern
    }

    override fun toString(): String {
        return construct()
    }
}