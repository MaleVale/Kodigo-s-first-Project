package com.kodigo.helpers;

import com.kodigo.models.Product;
import de.vandermeer.asciitable.AsciiTable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class TableHelper {
    public void showProductRepository(ArrayList<Product> productRepository){
        System.out.println("\nThese are our available products:");
        AsciiTable at = new AsciiTable();
        // adds a rule
        at.addRule();
        // headers
        at.addRow("ID", "Brand", "Name", "Unit Price", "Available");
        // adds a rule
        at.addRule();
        for (Product product : productRepository){
            at.addRow(product.getId(),
                    product.getBrand(),
                    product.getName(),
                    "$"+product.getPrice(),
                    product.getStock());
            // adds a rule
            at.addRule();
        }
        System.out.println(at.render(115));
    }

    public void checkCart(ArrayList<Product> cart){
        if (cart.isEmpty()) {
            System.out.println("\nThe cart is empty!");
        } else {
            System.out.println("\nThis is what's on your cart: ");
            // instance for AsciiTable dependency
            AsciiTable at = new AsciiTable();
            // adds a rule
            at.addRule();
            // headers
            at.addRow("ID", "Brand", "Name", "On cart", "Unit price", "Amount");
            // adds a rule
            at.addRule();
            // variable for counting array
            int count = 1;
            // shows the products added to the arraylist
            for (Product product : cart) {
                at.addRow(count,
                        product.getBrand(),
                        product.getName(),
                        product.getStock(),
                        "$"+product.getPrice(),
                        "$"+product.getPrice().multiply(BigDecimal.valueOf(product.getStock())));
                // adds a rule
                at.addRule();
                count++;
            }
            System.out.println(at.render(115));
        }
    }
}
