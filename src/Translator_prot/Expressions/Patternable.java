package Translator_prot.Expressions;

import java.io.IOException;
import java.util.HashMap;

abstract class Patternable implements Constructable {
    public String pattern;

    @Override
    public String toString() {
        try {
            return construct();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    String getConstruct(HashMap<EXPR_TYPES, Constructable> lexemes) {
        String tmp_pattern = pattern;
        for (EXPR_TYPES lexeme : lexemes.keySet()) {
            String le = lexeme.toString();
            String constr = lexemes.get(lexeme).toString();
            tmp_pattern = tmp_pattern.replace("%" + lexeme.toString() + "%",
                    lexemes.get(lexeme).toString());
        }
        return tmp_pattern;
    }
}
