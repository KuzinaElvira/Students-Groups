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
    
    private boolean isMatches(String str) { 
        Matcher m = pattern.matcher(str);
        return m.matches();
    }
    
    private boolean isContains(String str, String... fields) {
        for (String field : fields) {
            if (field.contains(str)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean doMatch(String str, String... fields){
        if(!str.contains(".") && !str.contains("?") && !str.contains("*") && !str.contains("+") && !str.contains("$") 
            && !str.contains("^") && !str.contains("(") && !str.contains(")") && !str.contains("[") && !str.contains("]")
            && !str.contains("{") && !str.contains("}") && !str.contains("\\")){
            return isContains(str, fields);
        }
        return isMatches(str);
    }
}
