/** TODO
 * 1. Разделять на файлы: вроде c и h
 * 2. Добавлять (include) эти файлы в Main
 * 3. В каждую функцию добавлять указатель на this (self)
 *
 * =========== TYPE_ALIAS.h ===========
 * #ifndef TYPE_ALIAS
 * #define TYPE_ALIAS
 *
 * typedef struct {
 *      Super_type super;
 *      params...
 * } Class_alias;
 *
 * void TYPE_ALIAS_constructor(self_pointer, args..);
 *
 * ret_type TYPE_ALIAS_func_name(self_pointer, args..) ;
 *
 * #endif
 *
 * =========== TYPE_ALIAS.c ===========
 * #include "TYPE_ALIAS.h"
 *
 * void TYPE_ALIAS_constructor(self_pointer, args..) {
 *      body...
 * }
 *
 * ret_type TYPE_ALIAS_func_name(self_pointer, args..) {
 *      body...
 * }
 **/

class Class(private val lexeme: Lexemes) : SourceCode {
    // FIXME !!
    override var filename = lexeme.lexemes[EXPR_TYPES.TYPE_ALIAS]!!
    private val typeAlias = filename.construct()
    private val superName = VarName("_super")
    private val thisPointerName = VarName("this")

    fun addSuper(superTypeAlias: ClassTypeAlias) {
        val memberDeclarations = lexeme.lexemes[EXPR_TYPES.MEMBER_DECLARATIONS]

        if (memberDeclarations != null && memberDeclarations is CollectionOfVariables) {
            memberDeclarations.addVar(VarDecl(Lexemes(
                    EXPR_TYPES.VAR_NAME, superName,
                    EXPR_TYPES.RET_TYPE, superTypeAlias))
            )
        }
    }

    fun incertSelfPointerToFuntions() {
        val methodsDeclarations = lexeme.lexemes[EXPR_TYPES.FUNCTION_DECLARATIONS]

        if (methodsDeclarations != null && methodsDeclarations is CollectionOfFunctions) {
            for (method in methodsDeclarations.collectionOfFunctions) {
                val signature = method.lexeme.lexemes[EXPR_TYPES.SIGNATURE]
                val funcName = method.lexeme.lexemes[EXPR_TYPES.FUNC_NAME]
                if (signature is Parameters) {
                    signature.addParameter(SignatureParamConstPointer(thisPointerName, TypeAlias(typeAlias)))
                } else {
                    throw NotAllowedLexeme("Broken signature in class '$filename', function '$funcName'")
                }

            }
        }
    }

    val header = _HeaderFile(lexeme, filename)
    val source = _ClassFile(lexeme, filename)
    // TODO selfPointer and super pointer
    override fun construct(): String {
        incertSelfPointerToFuntions()
        return textWrap(filename.construct() + ".c") + "\n" + source.construct() +
                textWrap(filename.construct() + ".h") + "\n" + header.construct()
    }

}

class ClassTypeAlias(type: String) : TypeAlias(type)

class _HeaderFile(lexeme: Lexemes, override var filename: Constructable) : Patternable(lexeme), SourceCode {
    init {
        this.filename = SymbolicSeq(this.filename.construct() + ".h")
    }

    override val patternType = PATTERN_TYPES.HEADER_FILE
}

class _ClassFile(lexeme: Lexemes, override var filename: Constructable) : Patternable(lexeme), SourceCode {
    init {
        this.filename = SymbolicSeq(this.filename.construct() + ".c")
    }

    override val patternType = PATTERN_TYPES.CLASS_DECL
}