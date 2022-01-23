package com.kodigo.repository;

import com.kodigo.models.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private final ArrayList<Product> products = new ArrayList<Product>();

    @Test
    void returnProductRepository() {
        assertEquals(ProductRepository.returnProductRepository(), products);
    }

    @Test
    void returnInventoryLength() {
        products.add((new Product(1, "Under Armour", "Charged Assert", BigDecimal.valueOf(56.90), 32)));
        products.add(new Product(2, "Steve Madden", "Fenta", BigDecimal.valueOf(49.08), 100));
        products.add(new Product(3, "Clarks", "Tilden Cap Oxford", BigDecimal.valueOf(78.94), 45));

        assertEquals(ProductRepository.returnInventoryLength(), 0);
    }
}