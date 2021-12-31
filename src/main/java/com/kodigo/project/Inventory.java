package com.kodigo.project;

import java.math.BigDecimal;

public class Inventory {
    public void showInventory(){

        // object array based on Products class
        Products[] array = new Products[7];
        array[0] = new Products(1, "Under Armour", "Charged Assert", BigDecimal.valueOf(56.90), 32);
        array[1] = new Products(2, "Steve Madden", "Fenta",  BigDecimal.valueOf(49.08), 100);
        array[2] = new Products(3, "Clarks", "Tilden Cap Oxford",  BigDecimal.valueOf(78.94), 45);
        array[3] = new Products(4, "Adidas", "Kaptir 2.0", BigDecimal.valueOf(169.99), 8);
        array[4] = new Products(5, "Brooks", "Launch 8", BigDecimal.valueOf(95.99), 21);
        array[5] = new Products(6, "Clarks", "Tilden Free",  BigDecimal.valueOf(51.87), 98);
        array[6] = new Products(7, "Puma", "Suede Classic XXI",  BigDecimal.valueOf(55.99), 31);

        // message
        System.out.println("\nWelcome! These are our available products\n");

        // prints the array
        for (Products products : array) {
            System.out.println(products.toString());
        }
    }


}
