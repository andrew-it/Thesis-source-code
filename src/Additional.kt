data class Lexemes(val lexemes: HashMap<EXPR_TYPES, Constructable>) {
    constructor(type: EXPR_TYPES, lexeme: Constructable) : this(
            hashMapOf(Pair(type, lexeme))
    )

    constructor(type1: EXPR_TYPES, lexeme1: Constructable,
                type2: EXPR_TYPES, lexeme2: Constructable) : this(
            hashMapOf(Pair(type1, lexeme1), Pair(type2, lexeme2))
    )

    constructor(type1: EXPR_TYPES, lexeme1: Constructable,
                type2: EXPR_TYPES, lexeme2: Constructable,
                type3: EXPR_TYPES, lexeme3: Constructable) : this(
            hashMapOf(Pair(type1, lexeme1), Pair(type2, lexeme2), Pair(type3, lexeme3))
    )

    constructor(type1: EXPR_TYPES, lexeme1: Constructable,
                type2: EXPR_TYPES, lexeme2: Constructable,
                type3: EXPR_TYPES, lexeme3: Constructable,
                type4: EXPR_TYPES, lexeme4: Constructable) : this(
            hashMapOf(Pair(type1, lexeme1), Pair(type2, lexeme2), Pair(type3, lexeme3), Pair(type4, lexeme4))
    )

    fun merge(lexemes: Lexemes) {
        this.lexemes.putAll(lexemes.lexemes)
    }

    fun addLexeme(type: EXPR_TYPES, lexeme: Constructable) {
        this.lexemes.put(type, lexeme)
    }
}

open class SymbolicSeq(stub: String) : Patternable(Lexemes(EXPR_TYPES.VALUE, Symbols(stub))) {
    override val patternType = PATTERN_TYPES.SYMBOLIC_SEQ
}

class Symbols(private val stub: String) : Constructable {
    override fun construct(): String {
        return stub
    }

    override fun toString(): String {
        return construct()
    }
}