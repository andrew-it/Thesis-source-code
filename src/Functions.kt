class FunctionCall(lexeme: Lexemes) : Patternable(lexeme) {
    init {
        Dependencies.addDependency(lexeme.lexemes[EXPR_TYPES.FUNC_NAME].toString())
    }

    override val pattern_type = PATTERN_TYPES.FUNC_CALL
}

class FunctionDecl(lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.FUNC_DECL

    fun getLexemes(): Lexemes {
        return lexeme
    }
}

class FunctionPrototype(lexeme: Lexemes) : Patternable(lexeme) {
    override val pattern_type = PATTERN_TYPES.FUNC_PROTOTYPE
}

class CollectionOfFunctions(val collectionOfFunctions: Array<FunctionDecl>) : Constructable {
    override fun construct(): String {
        return collectionOfFunctions.joinToString("\n") { it.construct() }
    }

    fun addFunction(func: FunctionDecl) {
        collectionOfFunctions.plus(func)
    }

    fun convertToProrotypesCollection(): CollectionOfFuncPrototypes {
        var tmpArray: Array<FunctionPrototype> = emptyArray()

        for (func in collectionOfFunctions) {
            var newProt = FunctionPrototype(func.lexeme)
            tmpArray = tmpArray.plus(newProt)
        }

        return CollectionOfFuncPrototypes(tmpArray)
    }
}

class CollectionOfFuncPrototypes(val collectionOfPrototypes: Array<FunctionPrototype>) : Constructable {
    override fun construct(): String {
        return collectionOfPrototypes.joinToString("\n") { it.construct() }
    }

    fun addFuncPrototype(func: FunctionPrototype) {
        collectionOfPrototypes.plus(func)
    }
}

class Parameters(private var params: Array<SignatureParam>) : Constructable {
    override fun construct(): String {
        return params.joinToString(", ") { it.construct() }
    }

    fun addParameter(param: SignatureParam) {
        params = arrayOf(param) + params
    }
}

class SignatureParam(val name: VarName, val returnType: TypeAlias) : Constructable {
    override fun construct(): String {
        return returnType.construct() + " " + name.construct()
    }
}