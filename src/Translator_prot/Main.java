package Translator_prot;

import Translator_prot.Expressions.*;

import java.io.IOException;
import java.util.HashMap;

// TODO entry point???
// рассуждения по поводу языка реализации в методологии

public class Main {

    public static void main(String[] args) throws IOException {
        HashMap<EXPR_TYPES, Constructable> print_hm = new HashMap<>();
        print_hm.put(EXPR_TYPES.FUNC_NAME, new Str("printf"));
        print_hm.put(EXPR_TYPES.PARAMS, new StrVariable("Hello world!"));

        FunctionCall print_call = new FunctionCall(print_hm);

        Scope main_scope = new Scope(print_call);

        HashMap<EXPR_TYPES, Constructable> main_hm = new HashMap<>();
        main_hm.put(EXPR_TYPES.FUNC_NAME, new Str("main"));
        main_hm.put(EXPR_TYPES.SIGNATURE, new Parameters());
        main_hm.put(EXPR_TYPES.BODY, main_scope);
        main_hm.put(EXPR_TYPES.RET_TYPE, new Str("int"));

        FunctionDecl main_decl = new FunctionDecl(main_hm);

        Scope global_scope = new Scope(main_decl);

        SourceCode sourceCode = new SourceCode(global_scope);

        Project project = new Project(sourceCode);

        System.out.print(project.toString());
    }
}
