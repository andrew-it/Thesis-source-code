package Translator_prot.Expressions;

import java.util.HashMap;

public class Expression extends Patternable {
    private HashMap<EXPR_TYPES, Constructable> lexemes;

    Expression(String _pattern, HashMap<EXPR_TYPES, Constructable> _lexemes){
        this.pattern = _pattern;
        this.lexemes = _lexemes;
    }

    @Override
    public String construct() {
        return this.getConstruct(lexemes);
    }
}
