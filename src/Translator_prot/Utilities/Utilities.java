package Translator_prot.Utilities;

import java.util.ArrayList;

public class Utilities {
    public static String join(ArrayList<String> list, String delimeter){
        String result = "";
        for (int i = 0; i < list.size(); i++){
            if(list.size()>1 && i < list.size() - 1)
                result += list.get(i) + delimeter;
            else
                result += list.get(i);
        }
        return result;
    }
}
