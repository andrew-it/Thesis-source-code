package mains

import additional.*
import language_primitives.*

/**
 * Procedural programing Testing
 * */

fun main(args: Array<String>) {
    // Print
    val printCall = StandardOutput("Hello world!")
    // Str vars
    val strVal = VarName("strVariable")
    val strValLex = Lexemes(
            EXPR_TYPES.VAR_NAME, strVal,
            EXPR_TYPES.RET_TYPE, TypeConverter(STD_TYPES.STRING)
    )
    val strVar = VarDecl(strValLex)
    // Int vars
    val intVal = VarName("intVariable")
    val intValLex = Lexemes(
            EXPR_TYPES.VAR_NAME, intVal,
            EXPR_TYPES.RET_TYPE, TypeConverter(STD_TYPES.INT)
    )
    val intVar = VarDecl(intValLex)
    // Str var assignment
    val strValAssLex = Lexemes(
            EXPR_TYPES.VAR_NAME, strVal,
            EXPR_TYPES.VALUE, StrValue(SymbolicSeq("SomeSentence"))
    )
    val strAss = VarAssignment(strValAssLex)
    // Int var assignment
    val intValAssLex = Lexemes(
            EXPR_TYPES.VAR_NAME, intVal,
            EXPR_TYPES.VALUE, IntValue(SymbolicSeq("11"))
    )
    val intAss = VarAssignment(intValAssLex)
    // Scopes for conditions
    val trueCond = Scope(arrayOf(printCall))
    val falseCond = Scope(arrayOf(strVar, strAss))
    val condition = IfCondition(
            Lexemes(EXPR_TYPES.CONDITION, intVal),
            Lexemes(EXPR_TYPES.BODY, trueCond),
            Lexemes(EXPR_TYPES.BODY, falseCond))
    // Main
    val mainLex = Lexemes(
            EXPR_TYPES.FUNC_NAME, SymbolicSeq("main"),
            EXPR_TYPES.SIGNATURE, Parameters(
            arrayOf(
                    SignatureParam(VarName("argc"), TypeAlias(STD_TYPES.INT)),
                    SignatureParam(VarName("**argv"), TypeAlias(STD_TYPES.CHAR)))),
            EXPR_TYPES.BODY, Scope(arrayOf(intVar, intAss, condition)),
            EXPR_TYPES.RET_TYPE, TypeAlias(STD_TYPES.INT)
    )
    val mainDeclaration = EntryPoint(mainLex)


    val globalScope = Scope(arrayOf(mainDeclaration))
    val sourceCode = SourceCodeFile(arrayOf(globalScope), SymbolicSeq("main.c"))
    Project.sourceCodeFiles = arrayOf(sourceCode)
    val project = Project()

    print(project.construct())
}