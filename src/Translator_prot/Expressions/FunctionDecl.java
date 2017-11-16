package Translator_prot.Expressions;

import Translator_prot.Utilities.PatternsLoader;

import java.io.IOException;
import java.util.HashMap;

public class FunctionDecl extends Expression {

    public FunctionDecl(HashMap<EXPR_TYPES, Constructable> _lexemes) throws IOException {
        super(PatternsLoader.getPattern(PATTERN_TYPES.FUNC_DECL), _lexemes);
    }
}
