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
}

open class SymbolicSeq(private val stub: String) : Constructable {
    override fun construct(): String {
        return stub
    }

    override fun toString(): String {
        return construct()
    }
}