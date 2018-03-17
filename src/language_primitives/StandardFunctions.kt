package language_primitives

import additional.EXPR_TYPES
import additional.Lexemes
import additional.PatternsLoader.getStdFuncName
import additional.STD_FUNCTIONS
import additional.SymbolicSeq

// TODO variables
class StandardOutput(message: String) : FunctionCall(
        Lexemes(
                EXPR_TYPES.FUNC_NAME, SymbolicSeq(getStdFuncName(STD_FUNCTIONS.CONSOLE_OUT)),
                EXPR_TYPES.PARAMS, StrValue(SymbolicSeq(message))
        )
)

// TODO variables
class StandardInput(variable: VarName) : FunctionCall(
        Lexemes(
                EXPR_TYPES.FUNC_NAME, SymbolicSeq(getStdFuncName(STD_FUNCTIONS.CONSOLE_IN)),
                EXPR_TYPES.PARAMS, StrValue(variable)
        )
)