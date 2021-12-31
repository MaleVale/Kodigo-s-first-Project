package com.kodigo.project;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Inventory {
    // array lists for saving the selected products / inventory
    private static final ArrayList<Products> cart = new ArrayList<Products>();
    private static final ArrayList<Products> inventoryList = new ArrayList<Products>();
    public static void showInventory(){
        // data for the array
        inventoryList.add(new Products(1, "Under Armour", "Charged Assert", BigDecimal.valueOf(56.90), 32));
        inventoryList.add(new Products(2, "Steve Madden", "Fenta",  BigDecimal.valueOf(49.08), 100));
        inventoryList.add(new Products(3, "Clarks", "Tilden Cap Oxford",  BigDecimal.valueOf(78.94), 45));
        inventoryList.add(new Products(4, "Adidas", "Kaptir 2.0", BigDecimal.valueOf(169.99), 8));
        inventoryList.add(new Products(5, "Brooks", "Launch 8", BigDecimal.valueOf(95.99), 21));
        inventoryList.add(new Products(6, "Clarks", "Tilden Free",  BigDecimal.valueOf(51.87), 98));
        inventoryList.add(new Products(7, "Puma", "Suede Classic XXI",  BigDecimal.valueOf(55.99), 31));

        // message
        System.out.println("\nWelcome! These are our available products\n");

        // prints the array
        for (Products products : inventoryList) {
            System.out.println(products.toString());
        }
    }

    public int returnInventoryLength(){
        // return array length
        return inventoryList.size();
    }

    public void addToCart(int i){
        cart.add(inventoryList.get(i));
    }


}
