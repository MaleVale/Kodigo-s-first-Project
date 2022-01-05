package com.kodigo.validations;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidation {
    public boolean validateAlphabetic(String value, int max){
        // checks if the string has the max characters allowed
        if (value.length() <= max) {
            // compiles the character sequence as a pattern
            Pattern pat = Pattern.compile("[a-z A-Z]+");
            // checks if there's a match with the pattern and the string value
            Matcher mat = pat.matcher(value);
            // returns true or false
            return mat.matches();
        } else {
            return false;
        }
    }
    public boolean validateAlphanumeric(String value, int max){
        // checks if the value has the max length allowed
        if (value.length() <= max) {
            // compiles the character sequence as a pattern
            Pattern pat = Pattern.compile("^[a-zA-Z0-9_ .]+$");
            // checks if there's a match with the pattern and the value
            Matcher mat = pat.matcher(value);
            // returns true or false
            return mat.matches();
        } else {
            return false;
        }
    }
    public boolean validateEmail(String email){
        // create the EmailValidator instance
        EmailValidator validator = EmailValidator.getInstance();
        // check for valid email addresses using isValid method
        return validator.isValid(email);
    }
}
