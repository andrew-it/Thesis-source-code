interface Constructable {
    fun construct(): String
}

interface SourceCode : Constructable {
    var filename: Constructable
}

abstract class Patternable(val lexeme: Lexemes) : Constructable {
    abstract val patternType: PATTERN_TYPES

    override fun construct(): String {
        var tmpPattern = PatternsLoader.getPatternByType(patternType)
        for (le in lexeme.lexemes.keys) {
            val constructableLexeme = lexeme.lexemes[le]
            if (constructableLexeme is Constructable)
                tmpPattern = tmpPattern.replace(le.toString(),
                        constructableLexeme.construct())
        }
        return tmpPattern
    }

    override fun toString(): String {
        return construct()
    }
}
