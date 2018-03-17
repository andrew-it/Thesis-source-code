package language_primitives

import additional.EXPR_TYPES
import additional.Lexemes
import additional.STD_TYPES
import additional.SymbolicSeq

/**
 * LanguagePrimitives.Class object -- hardcoded
 * */

fun main(args: Array<String>) {
    val reimplementComment = OneStringComment("Reimplement it")
    val objectName = "_Object"

    val returnedStr = StrValue(SymbolicSeq(objectName))
    val strName = VarName("returnedStr")
    val strDecl = VarDecl(Lexemes(EXPR_TYPES.RET_TYPE, TypeAlias(STD_TYPES.STRING), EXPR_TYPES.VAR_NAME, strName))
    val strAss = VarAssignment(Lexemes(EXPR_TYPES.VAR_NAME, strName, EXPR_TYPES.VALUE, returnedStr))

    val toStringFunc = FunctionDecl(Lexemes(
            EXPR_TYPES.RET_TYPE, TypeAlias(STD_TYPES.STRING),
            EXPR_TYPES.FUNC_NAME, SymbolicSeq("toString"),
            EXPR_TYPES.SIGNATURE, Parameters(emptyArray()),
            EXPR_TYPES.BODY,
            Scope(arrayOf(
                    reimplementComment, strDecl, strAss, Return(strName)
            ))
    ))

    val returnedHash = IntValue(SymbolicSeq("0"))

    val hashCodeFunc = FunctionDecl(Lexemes(
            EXPR_TYPES.RET_TYPE, TypeAlias(STD_TYPES.INT),
            EXPR_TYPES.FUNC_NAME, SymbolicSeq("hashCode"),
            EXPR_TYPES.SIGNATURE, Parameters(emptyArray()),
            EXPR_TYPES.BODY,
            Scope(arrayOf(
                    reimplementComment, Return(returnedHash)
            ))
    ))

    val objectMethodCollection = CollectionOfFunctions(arrayOf(toStringFunc, hashCodeFunc))

    val classLexemes = Lexemes(
            EXPR_TYPES.TYPE_ALIAS, ClassTypeAlias(objectName),
            EXPR_TYPES.MEMBER_DECLARATIONS,
            CollectionOfVariables(emptyArray()),
            EXPR_TYPES.FUNCTION_PROT_DECLARATIONS,
            objectMethodCollection.convertToPrototypesCollection(),
            EXPR_TYPES.FUNCTION_DECLARATIONS,
            objectMethodCollection
    )

    val classObject = Class(classLexemes)

    Project.sourceCodeFiles = arrayOf(classObject)
    val project = Project()

    print(project)
}