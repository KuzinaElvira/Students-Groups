/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.controller.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Elvira
 */
public class CheckMatching {
    
    private Pattern pattern;
    
    public CheckMatching(String regExp){
        pattern = Pattern.compile(regExp);
    }

    public void setPattern(String regExp) {
        pattern = Pattern.compile(regExp);
    }
    
    public boolean isMatches(String str) {
        if(!(str.contains(".") || str.contains("?") || str.contains("*") || str.contains("+") || str.contains("$") 
            || str.contains("^") || str.contains("(") || str.contains(")") || str.contains("[") || str.contains("]")
            || str.contains("{") || str.contains("}") || str.contains("\\"))){
                str = "*" + str + "*";
        }
        Matcher m = pattern.matcher(str);
        return m.matches();
    }
}
