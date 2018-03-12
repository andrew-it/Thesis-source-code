fun main(args: Array<String>) {
    // Function createTextNode
    val createTextNodeLexemes = Lexemes(
            EXPR_TYPES.FUNC_NAME, SymbolicSeq("Document.createTextNode"),
            EXPR_TYPES.PARAMS, StrValue(SymbolicSeq("Hello, World!"))
    )
    val createTextNode = FunctionCall(createTextNodeLexemes)

    // text var
    val varName = VarName("text")
    val textValLexemes = Lexemes(
            EXPR_TYPES.VAR_NAME, varName,
            EXPR_TYPES.VALUE, createTextNode
    )
    val textVar = VarAssignment(textValLexemes)
    val createTextVar = VarDecl(textValLexemes)

    // Function body.appendChild
    val bodyAppendChildLexemes = Lexemes(
            EXPR_TYPES.FUNC_NAME, SymbolicSeq("Document.body.appendChild"),
            EXPR_TYPES.PARAMS, varName
    )
    val bodyAppendChild = FunctionCall(bodyAppendChildLexemes)

    val globalScope = Scope(arrayOf(createTextVar, textVar, bodyAppendChild))
    val sourceCode = SourceCodeFile(arrayOf(globalScope), SymbolicSeq("main.js"))
    Project.sourceCodeFiles = arrayOf(sourceCode)
    val project = Project()

    print(project.construct())

}