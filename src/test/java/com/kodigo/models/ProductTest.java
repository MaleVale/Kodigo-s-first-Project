package com.kodigo.models;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private final Product product = new Product(1, "Samsung", "Note 10",  BigDecimal.valueOf(356.90), 5);
    @Test
    void cartToString() {
        assertEquals(product.cartToString(), String.format(". Shoes: Note 10 | Units: 5 | Price: $356.90 | Amount: $1784.50"));
    }
}