// TODO the table of correspondence between SLang and C standard functions
// FIXME: Incorrect function

fun main(args: Array<String>) {
    // Print
    val print_lex = Lexemes(
            EXPR_TYPES.FUNC_NAME, SymbolicSeq("pgrinf"),
            EXPR_TYPES.PARAMS, StrValue(SymbolicSeq("Hello world!"))
    )
    val print_call = FunctionCall(print_lex)
    // Main
    val main_lex = Lexemes(
            EXPR_TYPES.FUNC_NAME, SymbolicSeq("main"),
            EXPR_TYPES.SIGNATURE, Parameters(
            arrayOf(SymbolicSeq("int argc"),
                    SymbolicSeq("char **argv"))),
            EXPR_TYPES.BODY, Scope(arrayOf(print_call)),
            EXPR_TYPES.RET_TYPE, SymbolicSeq("int")
    )
    val main_decl = EntryPoint(main_lex)
    // Str vars
    val str_val = VarName("strVariable")
    val str_val_lex = Lexemes(
            EXPR_TYPES.VAR_NAME, str_val,
            EXPR_TYPES.RET_TYPE, TypeConverter(STD_TYPES.STRING)
    )
    val strVar = VarDecl(str_val_lex)
    // Int vars
    val int_val = VarName("intVariable")
    val int_val_lex = Lexemes(
            EXPR_TYPES.VAR_NAME, int_val,
            EXPR_TYPES.RET_TYPE, TypeConverter(STD_TYPES.INT)
    )
    val intVar = VarDecl(int_val_lex)
    // Str var assignment
    val str_val_ass_lex = Lexemes(
            EXPR_TYPES.VAR_NAME, str_val,
            EXPR_TYPES.VALUE, StrValue(SymbolicSeq("SomeSentence"))
    )
    val str_ass = VarAssignment(str_val_ass_lex)
    // Int var assignment
    val int_val_ass_lex = Lexemes(
            EXPR_TYPES.VAR_NAME, int_val,
            EXPR_TYPES.VALUE, IntValue(SymbolicSeq("11"))
    )
    val int_ass = VarAssignment(int_val_ass_lex)


    val global_scope = Scope(arrayOf(main_decl, strVar, intVar, str_ass, int_ass))
    val sourceCode = SourceCode(arrayOf(global_scope))
    val project = Project(arrayOf(sourceCode))

    print(project.construct())
}