package com.kodigo.repository;

import com.kodigo.models.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private static final ArrayList<Product> products = new ArrayList<>();

    static {
        products.add((new Product(1, "Under Armour", "Charged Assert", BigDecimal.valueOf(56.90), 32)));
        products.add(new Product(2, "Steve Madden", "Fenta", BigDecimal.valueOf(49.08), 100));
        products.add(new Product(3, "Clarks", "Tilden Cap Oxford", BigDecimal.valueOf(78.94), 45));
        products.add(new Product(4, "Adidas", "Kaptir 2.0", BigDecimal.valueOf(169.99), 8));
        products.add(new Product(5, "Brooks", "Launch 8", BigDecimal.valueOf(95.99), 21));
        products.add(new Product(6, "Clarks", "Tilden Free", BigDecimal.valueOf(51.87), 98));
        products.add(new Product(7, "Puma", "Suede Classic XXI", BigDecimal.valueOf(55.99), 31));
    }

    @Test
    void returnProductRepository() {
        assertEquals(ProductRepository.getProducts(), ProductRepository.returnProductRepository());
    }

    @Test
    void returnInventoryLength() {
        assertEquals(7, ProductRepository.returnInventoryLength());
    }
}