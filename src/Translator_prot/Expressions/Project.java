package Translator_prot.Expressions;

import java.io.IOException;
import java.util.ArrayList;

public class Project implements Constructable {
    private ArrayList<SourceCode> files = new ArrayList<>();

    public Project(ArrayList<SourceCode> files) {
        this.files = files;
    }

    public Project(SourceCode sourceCode) {
        this.files.add(sourceCode);
    }

    @Override
    public String construct() throws IOException {
        StringBuilder result = new StringBuilder();
        for (SourceCode file : files) {
            result.append(file.construct());
        }
        return result.toString();
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