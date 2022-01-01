package com.kodigo.repository;

import com.kodigo.models.Product;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductRepository {
    @Getter
    private ArrayList<Product> products;

    public ProductRepository() {
        this.initialize();
    }

    private void initialize() {
        // data for the array
        products.add((new Product(1, "Under Armour", "Charged Assert", BigDecimal.valueOf(56.90), 32)));
        products.add(new Product(2, "Steve Madden", "Fenta", BigDecimal.valueOf(49.08), 100));
        products.add(new Product(3, "Clarks", "Tilden Cap Oxford", BigDecimal.valueOf(78.94), 45));
        products.add(new Product(4, "Adidas", "Kaptir 2.0", BigDecimal.valueOf(169.99), 8));
        products.add(new Product(5, "Brooks", "Launch 8", BigDecimal.valueOf(95.99), 21));
        products.add(new Product(6, "Clarks", "Tilden Free", BigDecimal.valueOf(51.87), 98));
        products.add(new Product(7, "Puma", "Suede Classic XXI", BigDecimal.valueOf(55.99), 31));
    }

    public void showProductRepository() {
        // message
        System.out.println("\nWelcome! These are our available products\n");

        // prints the array
        for (Product p : products) {
            System.out.println(products.toString());
        }
    }

    public boolean isProductAvailable(Product product) {
        return false;
    }

    public int returnInventoryLength(){
        // return array length
        return products.size();
    }

    /*
    public void addToCart(int i){
        cart.add(inventoryList.get(i));
    }
    */
}
