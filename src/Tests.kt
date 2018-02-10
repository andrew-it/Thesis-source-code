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
    lateinit var trueSymbol: SymbolicSeq
    lateinit var falseSymbol: SymbolicSeq
    lateinit var int_val: VarName
    lateinit var true_cond: Scope
    lateinit var false_cond: Scope

    @BeforeEach
    fun setUp() {
        trueSymbol = SymbolicSeq("TRUE")
        falseSymbol = SymbolicSeq("FALSE")
        int_val = VarName("CONDITION")

        true_cond = Scope(arrayOf(trueSymbol))
        false_cond = Scope(arrayOf(falseSymbol))


    }

    @Test
    fun allCasesContition() {
        val eq = "if(CONDITION){TRUE}else{FALSE}"

        val condition = IfCondition(
                Lexemes(EXPR_TYPES.CONDITION, int_val),
                Lexemes(EXPR_TYPES.BODY, true_cond),
                Lexemes(EXPR_TYPES.BODY, false_cond))

        assertEquals(eq, cleanStr(condition.construct()))
    }

    @Test
    fun trueCaseCondition() {
        val eq = "if(CONDITION){TRUE}"

        val condition = IfCondition(
                Lexemes(EXPR_TYPES.CONDITION, int_val),
                Lexemes(EXPR_TYPES.BODY, true_cond),
                null)
        assertEquals(eq, cleanStr(condition.construct()))
    }

    @Test
    fun ternaryOperator() {
        val eq = "CONDITION?TRUE:FALSE;"
        val condition = TernaryCondition(Lexemes(
                EXPR_TYPES.CONDITION, int_val,
                EXPR_TYPES.BODY, true_cond,
                EXPR_TYPES.BODY, false_cond))

        assertEquals(eq, cleanStr(condition.construct()))
    }

}


internal class FunctionDeclarationTest {
    lateinit var main_lex: Lexemes
    lateinit var main_decl: EntryPoint
    val eq = "int main(int argc, char **argv){BODY}"


    @BeforeEach
    fun setUp() {
        main_lex = Lexemes(
                EXPR_TYPES.FUNC_NAME, SymbolicSeq("main"),
                EXPR_TYPES.SIGNATURE, Parameters(
                arrayOf(SymbolicSeq("int argc"),
                        SymbolicSeq("char **argv"))),
                EXPR_TYPES.BODY, Scope(arrayOf(SymbolicSeq("BODY"))),
                EXPR_TYPES.RET_TYPE, SymbolicSeq("int")
        )
        main_decl = EntryPoint(main_lex)
    }

    @Test
    fun funDecl() {
        assertEquals(cleanStr(main_decl.construct()), eq)
    }
}


internal class VariablesTest {
    val int_val = VarName("VAR_NAME")

    @Test
    fun intVariableDeclaration() {
        val eq = "int VAR_NAME;"

        val int_val_lex = Lexemes(
                EXPR_TYPES.VAR_NAME, int_val,
                EXPR_TYPES.RET_TYPE, TypeConverter(STD_TYPES.INT)
        )
        val intVar = VarDecl(int_val_lex)
        assertEquals(cleanStr(intVar.construct()), eq)
    }

    @Test
    fun intVarAssignment() {
        val eq = "VAR_NAME = 11;"

        val int_val_ass_lex = Lexemes(
                EXPR_TYPES.VAR_NAME, int_val,
                EXPR_TYPES.VALUE, IntValue(SymbolicSeq("11"))
        )
        val int_ass = VarAssignment(int_val_ass_lex)
        assertEquals(cleanStr(int_ass.construct()), eq)
    }
}