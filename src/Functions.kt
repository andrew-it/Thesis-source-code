open class FunctionCall(lexeme: Lexemes) : Patternable(lexeme) {
    init {
        Dependencies.addDependency(lexeme.lexemes[EXPR_TYPES.FUNC_NAME].toString())
    }

    override val patternType = PATTERN_TYPES.FUNC_CALL
}

open class FunctionDecl(lexeme: Lexemes) : Patternable(lexeme) {
    override val patternType = PATTERN_TYPES.FUNC_DECL

    fun getLexemes(): Lexemes {
        return lexeme
    }
}

class FunctionPrototype(lexeme: Lexemes) : FunctionDecl(lexeme) {
    override val patternType = PATTERN_TYPES.FUNC_PROTOTYPE
}

class CollectionOfFunctions(var collectionOfFunctions: Array<FunctionDecl>) : Constructable {
    override fun construct(): String {
        return collectionOfFunctions.joinToString("\n") { it.construct() }
    }

    fun addFunction(func: FunctionDecl) {
        collectionOfFunctions = collectionOfFunctions.plus(func)
    }

    fun convertToPrototypesCollection(): CollectionOfFuncPrototypes {
        var tmpArray: Array<FunctionPrototype> = emptyArray()

        for (func in collectionOfFunctions) {
            tmpArray = tmpArray.plus(FunctionPrototype(func.lexeme))
        }

        return CollectionOfFuncPrototypes(tmpArray)
    }
}

class CollectionOfFuncPrototypes(private var collectionOfPrototypes: Array<FunctionPrototype>) : Constructable {
    override fun construct(): String {
        return collectionOfPrototypes.joinToString("\n") { it.construct() }
    }

    fun addFuncPrototype(func: FunctionPrototype) {
        collectionOfPrototypes = collectionOfPrototypes.plus(func)
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

open class SignatureParam(name: VarName, returnType: TypeAlias) : Patternable(
        Lexemes(EXPR_TYPES.RET_TYPE, returnType, EXPR_TYPES.VAR_NAME, name)) {
    override val patternType = PATTERN_TYPES.SIG_PARAM
}

class SignatureParamConstPointer(name: VarName, returnType: TypeAlias) :
        SignatureParam(name, returnType) {
    override val patternType = PATTERN_TYPES.SIG_PARAM_CONST_POINTER
}

class Return(expression: Patternable) : Patternable(Lexemes(EXPR_TYPES.VAR_NAME, expression)) {
    override val patternType = PATTERN_TYPES.FUNC_RETURN
}