package com.kodigo.project;

import org.apache.commons.validator.routines.EmailValidator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean validateString(String name){
        // compiles the character sequence as a pattern
        Pattern pat = Pattern.compile("[a-zA-Z]+");
        Matcher mat = pat.matcher(name);
        return mat.matches();
    }

    public boolean validateEmail(String email){
        // create the EmailValidator instance
        EmailValidator validator = EmailValidator.getInstance();

        // check for valid email addresses using isValid method
        return validator.isValid(email);
    }
}
