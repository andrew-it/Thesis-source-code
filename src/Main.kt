fun main(args: Array<String>) {
    // Print
    val print_lex = Lexemes(
            EXPR_TYPES.FUNC_NAME, SymbolicSeq("printf"),
            EXPR_TYPES.PARAMS, StrValue(SymbolicSeq("Hello world!"))
    )
    val print_call = FunctionCall(print_lex)
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
    // Scopes for conditions
    val true_cond = Scope(arrayOf(print_call))
    val false_cond = Scope(arrayOf(strVar))
    val condition = IfCondition(
            Lexemes(EXPR_TYPES.CONDITION, int_val),
            Lexemes(EXPR_TYPES.BODY, true_cond),
            Lexemes(EXPR_TYPES.BODY, false_cond))
    // Main
    val main_lex = Lexemes(
            EXPR_TYPES.FUNC_NAME, SymbolicSeq("main"),
            EXPR_TYPES.SIGNATURE, Parameters(
            arrayOf(SymbolicSeq("int argc"),
                    SymbolicSeq("char **argv"))),
            EXPR_TYPES.BODY, Scope(arrayOf(intVar, int_ass, condition)),
            EXPR_TYPES.RET_TYPE, SymbolicSeq("int")
    )
    val main_decl = EntryPoint(main_lex)


    val global_scope = Scope(arrayOf(main_decl))
    val sourceCode = SourceCode(arrayOf(global_scope))
    val project = Project(arrayOf(sourceCode))


    print(project.construct())
}