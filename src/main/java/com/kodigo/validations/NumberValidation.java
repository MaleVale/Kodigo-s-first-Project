package com.kodigo.validations;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberValidation {
    public boolean validateStringParsableToInt(String value){
        if (NumberUtils.isParsable(value)){
            return true;
        } else {
            System.out.println("\nSorry! Looks like you didn't type a number.");
            return false;
        }
    }
}
