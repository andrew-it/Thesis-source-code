package Translator_prot.Expressions;

import Translator_prot.Utilities.PatternsLoader;

import java.io.IOException;

public class StrVariable extends Patternable {
    private String lexeme;
    private String wrapper;

    public StrVariable(String lexeme) throws IOException {
        this.lexeme = lexeme;
        this.wrapper = PatternsLoader.getPattern(PATTERN_TYPES.STR_VAR);
    }

    @Override
    public String construct() {
        return wrapper + lexeme + wrapper;
    }

    public String toString() {
        return construct();
    }
}
