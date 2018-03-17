package mains

import additional.EXPR_TYPES
import additional.Lexemes
import additional.STD_TYPES
import additional.SymbolicSeq
import language_primitives.*

/**
 * OOP Testing
 * */

fun main(args: Array<String>) {

    val shapeMoveFunc = FunctionDecl(Lexemes(
            EXPR_TYPES.RET_TYPE, TypeAlias(STD_TYPES.VOID),
            EXPR_TYPES.FUNC_NAME, SymbolicSeq("moveBy"),
            EXPR_TYPES.SIGNATURE, Parameters(
            arrayOf(SignatureParam(VarName("x"), TypeAlias(STD_TYPES.INT)),
                    SignatureParam(VarName("y"), TypeAlias(STD_TYPES.INT)))),
            EXPR_TYPES.BODY, Scope(arrayOf(SymbolicSeq("//TODO")))
    ))
    val shapeXVar = VarDecl(Lexemes(
            EXPR_TYPES.RET_TYPE, TypeAlias(STD_TYPES.INT),
            EXPR_TYPES.VAR_NAME, VarName("X")
    ))
    val shapeYVar = VarDecl(Lexemes(
            EXPR_TYPES.RET_TYPE, TypeAlias(STD_TYPES.INT),
            EXPR_TYPES.VAR_NAME, VarName("Y")
    ))

    val shapeMethodCollection = CollectionOfFunctions(arrayOf(shapeMoveFunc))

    val classLexemes = Lexemes(
            EXPR_TYPES.TYPE_ALIAS, ClassTypeAlias("Shape"),
            EXPR_TYPES.MEMBER_DECLARATIONS,
            CollectionOfVariables(arrayOf(shapeXVar, shapeYVar)),
            EXPR_TYPES.FUNCTION_PROT_DECLARATIONS,
            shapeMethodCollection.convertToPrototypesCollection(),
            EXPR_TYPES.FUNCTION_DECLARATIONS,
            shapeMethodCollection
    )

    val classShape = Class(classLexemes)

    Project.sourceCodeFiles = arrayOf(classShape)
    val project = Project()

    print(project)
}