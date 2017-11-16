package Translator_prot.Expressions;

import java.util.ArrayList;

public class Scope implements Constructable{
    private ArrayList<Expression> expressions = new ArrayList<>();

    public Scope(ArrayList<Expression> expressions) {
        this.expressions = expressions;
    }

    public Scope(Expression expression){
        this.expressions.add(expression);
    }

    @Override
    public String construct() {
        StringBuilder result = new StringBuilder();
        for (Expression expression: expressions) {
            result.append(expression.construct()).append("\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return construct();
    }
}
