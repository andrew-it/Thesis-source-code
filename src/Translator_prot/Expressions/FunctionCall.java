package Translator_prot.Expressions;

import Translator_prot.Utilities.PatternsLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class FunctionCall extends Expression {

    public FunctionCall(HashMap<EXPR_TYPES, Constructable> _lexemes) throws IOException {
        super(PatternsLoader.getPattern(PATTERN_TYPES.FUNC_CALL), _lexemes);
        Dependency.add_link(_lexemes.get(EXPR_TYPES.FUNC_NAME).construct());
    }
}
