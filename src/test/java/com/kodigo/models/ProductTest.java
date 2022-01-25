package com.kodigo.models;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private final Product product = new
            Product(1,
            "Samsung",
            "Note 10",
            BigDecimal.valueOf(356.90),
            5);

    @Test
    void getAmount(){
        assertEquals(BigDecimal.valueOf(1784.50), product.getAmount());
    }

    @Test
    void getTax(){
        assertEquals(BigDecimal.valueOf(46.397), product.getTax().getTax());
    }
}