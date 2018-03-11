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
    val typeAlias = filename.construct()
    val superName = VarName("_super")

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
                if (signature is Parameters) {
                    signature.addParameter(SignatureParam(VarName("self"), TypeAlias(typeAlias)))
                } else {
                    // TODO Throwable
//                    throw PIZDEC
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

class ClassTypeAlias(private val stub: String) : SymbolicSeq(stub)