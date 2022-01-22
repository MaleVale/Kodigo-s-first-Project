package com.kodigo.validations;

import com.kodigo.helpers.LogCreator;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;

public class NumberValidation {
    public static LogCreator log = LogCreator.getInstance();

    public boolean validateStringParsableToInt(String value){
        if (NumberUtils.isParsable(value)){
            return true;
        } else {
            System.out.println("\nSorry! Looks like you didn't type a number.");
            log.getLogger().warning("The customer didn't type a number.\n");
            return false;
        }
    }
}
