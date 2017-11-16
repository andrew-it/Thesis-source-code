package Translator_prot.Expressions;

public class Str implements Constructable {
    private String lexeme;

    public Str(String lexeme){
        this.lexeme = lexeme;
    }

    @Override
    public String construct() {
        return lexeme;
    }

    @Override
    public String toString() {
        return construct();
    }
}
