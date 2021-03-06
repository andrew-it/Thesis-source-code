package language_primitives

import additional.*

/** Plan:
 * 1. Divide into the files with .c and .h extensions
 * 2. Include above files to the Main
 * 3. Add self-pointer to every member of class
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
    override var filename = lexeme.lexemes[EXPR_TYPES.TYPE_ALIAS]!!

    init {
        val name = lexeme.lexemes[EXPR_TYPES.TYPE_ALIAS]
        if (name is Constructable)
            filename = name
        else
            throw NotAllowedLexeme("LanguagePrimitives.Class haven't filename")
    }

    private val typeAlias = filename.construct()
    private val superName = VarName("_super")
    private val thisPointerName = VarName("this")


    private fun addSuper(superTypeAlias: ClassTypeAlias) {
        val memberDeclarations = lexeme.lexemes[EXPR_TYPES.MEMBER_DECLARATIONS]

        if (memberDeclarations != null && memberDeclarations is CollectionOfVariables) {
            memberDeclarations.addVar(VarDecl(Lexemes(
                    EXPR_TYPES.VAR_NAME, superName,
                    EXPR_TYPES.RET_TYPE, superTypeAlias))
            )
        }
    }

    private fun insertSelfPointerToFunctions() {
        val methodsDeclarations = lexeme.lexemes[EXPR_TYPES.FUNCTION_DECLARATIONS]

        if (methodsDeclarations != null && methodsDeclarations is CollectionOfFunctions) {
            for (method in methodsDeclarations.collectionOfFunctions) {
                val signature = method.lexeme.lexemes[EXPR_TYPES.SIGNATURE]
                val funcName = method.lexeme.lexemes[EXPR_TYPES.FUNC_NAME]
                if (signature is Parameters) {
                    signature.addParameter(SignatureParamConstPointer(thisPointerName, ClassTypeAlias(typeAlias)))
                } else {
                    throw NotAllowedLexeme("Broken signature in class '$filename', function '$funcName'")
                }
            }
        }
    }

    override fun construct(): String {
        val header = HeaderFile(lexeme, filename)
        val source = ClassFile(lexeme, filename)
        Dependencies.addDependency(typeAlias)
        insertSelfPointerToFunctions()
        return textWrap(filename.construct() + ".c") + "\n" + source.construct() +
                textWrap(filename.construct() + ".h") + "\n" + header.construct()
    }
}

class ClassTypeAlias(type: String) : TypeAlias(type)

class HeaderFile(lexeme: Lexemes, override var filename: Constructable) : Patternable(lexeme), SourceCode {
    init {
        this.filename = SymbolicSeq(this.filename.construct() + ".h")
    }

    override val patternType = PATTERN_TYPES.HEADER_FILE
}

class ClassFile(lexeme: Lexemes, override var filename: Constructable) : Patternable(lexeme), SourceCode {
    init {
        this.filename = SymbolicSeq(this.filename.construct() + ".c")
    }

    override val patternType = PATTERN_TYPES.CLASS_DECL
}