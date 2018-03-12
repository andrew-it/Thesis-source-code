import PatternsLoader.getStdFuncName

class StandardOutput(message: String) : FunctionCall(
        Lexemes(
                EXPR_TYPES.FUNC_NAME, SymbolicSeq(getStdFuncName(STD_FUNCTIONS.CONSOLE_OUT)),
                EXPR_TYPES.PARAMS, StrValue(SymbolicSeq(message))
        )
)

// TODO variable
class StandardInput(variable: VarName) : FunctionCall(
        Lexemes(
                EXPR_TYPES.FUNC_NAME, SymbolicSeq(getStdFuncName(STD_FUNCTIONS.CONSOLE_IN)),
                EXPR_TYPES.PARAMS, StrValue(variable)
        )
)