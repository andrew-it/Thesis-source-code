interface Constructable {
    fun construct(): String
}

interface SourceCode : Constructable {
    var filename: Constructable
}

abstract class Patternable(val lexeme: Lexemes) : Constructable {
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

// TODO use it
abstract class CollectionOfSth(val collectionOfSth: Array<Constructable>) : Constructable {
    override fun construct(): String {
        return collectionOfSth.joinToString("\n") { it.construct() }
    }

    fun addInstance(instance: Constructable) {
        collectionOfSth.plus(instance)
    }
}