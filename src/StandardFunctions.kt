import PatternsLoader.getStdFuncName

class StandardOutput(message: String) : FunctionCall(Lexemes(
        EXPR_TYPES.FUNC_NAME, SymbolicSeq(getStdFuncName(STD_FUNCTIONS.CONSOLE_OUT)),
        EXPR_TYPES.PARAMS, StrValue(SymbolicSeq(message)
)))