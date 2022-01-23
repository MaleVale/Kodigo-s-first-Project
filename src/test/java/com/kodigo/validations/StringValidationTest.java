package com.kodigo.validations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringValidationTest {
    private final StringValidation validate = new StringValidation();
    @Test
    void validateAlphabetic() {
        assertTrue(validate.validateAlphabetic("Hello", 5));
    }

    @Test
    void validateAlphanumeric() {
        assertTrue(validate.validateAlphanumeric("Marun 5", 7));
    }

    @Test
    void validateEmail() {
        assertTrue(validate.validateEmail("test@email.com"));
    }
}