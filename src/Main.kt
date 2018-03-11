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
            arrayOf(
                    SignatureParam(VarName("argc"), TypeAlias(STD_TYPES.INT)),
                    SignatureParam(VarName("**argv"), TypeAlias(STD_TYPES.CHAR)))),
            EXPR_TYPES.BODY, Scope(arrayOf(intVar, int_ass, condition)),
            EXPR_TYPES.RET_TYPE, TypeAlias(STD_TYPES.INT)
    )
    val main_decl = EntryPoint(main_lex)

    // Class Shape
    val shape_move_func = FunctionDecl(Lexemes(
            EXPR_TYPES.RET_TYPE, TypeAlias(STD_TYPES.VOID),
            EXPR_TYPES.FUNC_NAME, SymbolicSeq("moveBy"),
            EXPR_TYPES.SIGNATURE, Parameters(
            arrayOf(SignatureParam(VarName("x"), TypeAlias(STD_TYPES.INT)),
                    SignatureParam(VarName("y"), TypeAlias(STD_TYPES.INT)))),
            EXPR_TYPES.BODY, Scope(arrayOf(SymbolicSeq("//TODO")))
    ))
    val shape_x_var = VarDecl(Lexemes(
            EXPR_TYPES.RET_TYPE, TypeAlias(STD_TYPES.INT),
            EXPR_TYPES.VAR_NAME, VarName("X")
    ))
    val shape_y_var = VarDecl(Lexemes(
            EXPR_TYPES.RET_TYPE, TypeAlias(STD_TYPES.INT),
            EXPR_TYPES.VAR_NAME, VarName("Y")
    ))

    val Shape_method_collection = CollectionOfFunctions(arrayOf(shape_move_func))

    val class_lexemes = Lexemes(
            EXPR_TYPES.TYPE_ALIAS, ClassTypeAlias("Shape"),
            EXPR_TYPES.MEMBER_DECLARATIONS,
            CollectionOfVariables(arrayOf(shape_x_var, shape_y_var)),
            EXPR_TYPES.FUNCTION_PROT_DECLARATIONS,
            Shape_method_collection.convertToProrotypesCollection(),
            EXPR_TYPES.FUNCTION_DECLARATIONS,
            Shape_method_collection
    )

    val class_Shape = Class(class_lexemes)


    val global_scope = Scope(arrayOf(main_decl))
    val sourceCode = SourceCodeFile(arrayOf(global_scope), SymbolicSeq("main.c"))
    Project.sourceCodeFiles = arrayOf(sourceCode, class_Shape)
    val project = Project()

    print(project.construct())
}