package com.kodigo.project;

public class Inventory {
    public void showInventory(){
        // object array based on Products class
        Products[] array = new Products[7];
        array[0] = new Products(1, "Under Armour", "Charged Assert", 52.50, 32);
        array[1] = new Products(2, "Steve Madden", "Fenta", 49.08, 100);
        array[2] = new Products(3, "Clarks", "Tilden Cap Oxford", 78.94, 45);
        array[3] = new Products(4, "Adidas", "Kaptir 2.0", 169.99, 8);
        array[4] = new Products(5, "Brooks", "Launch 8", 95.99, 21);
        array[5] = new Products(6, "Clarks", "Tilden Free", 51.87, 98);
        array[6] = new Products(7, "Puma", "Suede Classic XXI", 55.99, 31);

        // message
        System.out.println("\nWelcome! These are our available products\n");

        // prints the array
        for (Products products : array) {
            System.out.println(products.toString());
        }
    }


}
