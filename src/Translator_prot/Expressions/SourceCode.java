package Translator_prot.Expressions;

import java.io.IOException;
import java.util.ArrayList;

public class SourceCode implements Constructable {
    private ArrayList<Scope> scopes = new ArrayList<>();

    public SourceCode(Scope scope){
        this.scopes.add(scope);
    }

    private String scopeConstructor(){
        StringBuilder result = new StringBuilder();
        for (Scope scope: scopes) {
            result.append(scope.construct()).append("\n");
        }
        return result.toString();
    }

    private String dependencyConstructor() throws IOException {
        return Dependency.construct();
    }

    @Override
    public String construct() throws IOException {
        return dependencyConstructor() + scopeConstructor();
    }

    @Override
    public String toString() {
        try {
            return construct();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
