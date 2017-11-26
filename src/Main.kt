// TODO entry point???
// TODO the table of correspondence between SLang and C standard functions

fun main(args: Array<String>) {
    val print_lex = HashMap<EXPR_TYPES, Constructable>()
    print_lex.put(EXPR_TYPES.FUNC_NAME, StrStub("printf"))
    print_lex.put(EXPR_TYPES.PARAMS, StrVariable(
            Lexemes(EXPR_TYPES.VALUE, StrStub("Hello world!")))
    )

    val print_call = FunctionCall(Lexemes(print_lex))

    val main_call = java.util.HashMap<EXPR_TYPES, Constructable>()
    main_call.put(EXPR_TYPES.FUNC_NAME, StrStub("main"))
    main_call.put(EXPR_TYPES.SIGNATURE, Parameters(arrayOf(StrStub(""))))
    main_call.put(EXPR_TYPES.BODY, Scope(arrayOf(print_call)))
    main_call.put(EXPR_TYPES.RET_TYPE, StrStub("int"))

    val main_decl = FunctionDecl(Lexemes(main_call))

    val global_scope = Scope(arrayOf(main_decl))

    val sourceCode = SourceCode(arrayOf(global_scope))

    val project = Project(arrayOf(sourceCode))

    print(project.construct())
}