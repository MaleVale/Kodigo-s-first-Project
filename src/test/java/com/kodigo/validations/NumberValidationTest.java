package com.kodigo.validations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberValidationTest {
    private final NumberValidation validate = new NumberValidation();
    @Test
    void validateStringParsableToInt() {
        assertEquals(validate.validateStringParsableToInt("01234"), true);
    }
}