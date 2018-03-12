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
        for (le in lexeme.lexemes.keys)
            tmpPattern = tmpPattern.replace(le.toString(),
                    lexeme.lexemes[le]!!.construct()) // FIXME !!
        return tmpPattern
    }

    override fun toString(): String {
        return construct()
    }
}

// TODO use it
abstract class CollectionOfSth(val collectionOfSth: Array<Constructable>) : Constructable {
    override fun construct(): String {
        return collectionOfSth.joinToString("\n") { it.construct() }
    }

    fun addInstance(instance: Constructable) {
        collectionOfSth.plus(instance)
    }
}