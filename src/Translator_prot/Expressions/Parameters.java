package Translator_prot.Expressions;

import Translator_prot.Utilities.Utilities;
import java.util.ArrayList;

public class Parameters implements Constructable {
    private String delimeter = ",";
    private ArrayList<String> params = new ArrayList<>();

    public Parameters(ArrayList<String> _params){
        params = _params;
    }

    public Parameters(){}

    @Override
    public String construct() {
        return Utilities.join(params, ",");
    }

    @Override
    public String toString() {
        return construct();
    }
}
