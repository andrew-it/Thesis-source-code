package additional

import language_primitives.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class UtilsKtTest {
    @Test
    fun cleanStr() {
        val eq = "234"
        assertEquals(cleanStr("2\n3\t4"), eq)
    }
}


internal class ConditionalTest {
    private lateinit var trueSymbol: SymbolicSeq
    private lateinit var falseSymbol: SymbolicSeq
    private lateinit var intValue: VarName
    private lateinit var trueCond: Scope
    private lateinit var falseCond: Scope

    @BeforeEach
    fun setUp() {
        trueSymbol = SymbolicSeq("TRUE")
        falseSymbol = SymbolicSeq("FALSE")
        intValue = VarName("CONDITION")

        trueCond = Scope(arrayOf(trueSymbol))
        falseCond = Scope(arrayOf(falseSymbol))
    }

    @Test
    fun allCasesCondition() {
        val eq = "if(CONDITION){TRUE}else{FALSE}"

        val condition = IfCondition(
                Lexemes(EXPR_TYPES.CONDITION, intValue),
                Lexemes(EXPR_TYPES.BODY, trueCond),
                Lexemes(EXPR_TYPES.BODY, falseCond))

        assertEquals(eq, cleanStr(condition.construct()))
    }

    @Test
    fun trueCaseCondition() {
        val eq = "if(CONDITION){TRUE}"

        val condition = IfCondition(
                Lexemes(EXPR_TYPES.CONDITION, intValue),
                Lexemes(EXPR_TYPES.BODY, trueCond),
                null)
        assertEquals(eq, cleanStr(condition.construct()))
    }

    @Test
    fun ternaryOperator() {
        val eq = "CONDITION?TRUE:FALSE;"
        val condition = TernaryCondition(Lexemes(
                EXPR_TYPES.CONDITION, intValue,
                EXPR_TYPES.BODY, trueCond,
                EXPR_TYPES.BODY, falseCond))

        assertEquals(eq, cleanStr(condition.construct()))
    }

}


internal class FunctionDeclarationTest {
    private lateinit var mainLex: Lexemes
    private lateinit var mainDecl: EntryPoint
    private val eq = "int main(int argc, char **argv){BODY}"


    @BeforeEach
    fun setUp() {
        mainLex = Lexemes(
                EXPR_TYPES.FUNC_NAME, SymbolicSeq("main"),
                EXPR_TYPES.SIGNATURE, Parameters(
                arrayOf(
                        SignatureParam(VarName("argc"), TypeAlias(STD_TYPES.INT)),
                        SignatureParam(VarName("**argv"), TypeAlias(STD_TYPES.CHAR)))),
                EXPR_TYPES.BODY, Scope(arrayOf(SymbolicSeq("BODY"))),
                EXPR_TYPES.RET_TYPE, SymbolicSeq("int")
        )
        mainDecl = EntryPoint(mainLex)
    }

    @Test
    fun funDecl() {
        assertEquals(cleanStr(mainDecl.construct()), eq)
    }
}


internal class VariablesTest {
    private val intValue = VarName("VAR_NAME")

    @Test
    fun intVariableDeclaration() {
        val eq = "int VAR_NAME;"

        val intValLex = Lexemes(
                EXPR_TYPES.VAR_NAME, intValue,
                EXPR_TYPES.RET_TYPE, TypeConverter(STD_TYPES.INT)
        )
        val intVar = VarDecl(intValLex)
        assertEquals(cleanStr(intVar.construct()), eq)
    }

    @Test
    fun intVarAssignment() {
        val eq = "VAR_NAME = 11;"

        val intValAssLex = Lexemes(
                EXPR_TYPES.VAR_NAME, intValue,
                EXPR_TYPES.VALUE, IntValue(SymbolicSeq("11"))
        )
        val intAss = VarAssignment(intValAssLex)
        assertEquals(cleanStr(intAss.construct()), eq)
    }
}