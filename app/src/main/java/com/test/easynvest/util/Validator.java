package com.test.easynvest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\-\\_\\+]+(\\.[_A-Za-z0-9-\\-\\_\\+]+)*@[A-Za-z0-9\\-\\_]+(\\.[A-Za-z0-9\\-\\_]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean validateEmail(String s) {
        if(s != null && !s.contentEquals("")) {
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public static boolean validatePhone(String s) {
        //remove ()- and empty spaces
        s = s.replace("(", "");
        s = s.replace(")", "");
        s = s.replace("-", "");
        s = s.replace(" ", "");
        if(s.equals("")) return false;
        return s.length() == 11 ? true : false;
    }
}
