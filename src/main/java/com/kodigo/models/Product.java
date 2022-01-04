package com.kodigo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
public class Product extends Tax{

    private int id;
    private String brand;
    private String name;
    private BigDecimal price;
    private int stock;

    public Product(int id, String brand, String name, BigDecimal price, int stock){
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.stock = stock;
        initializeTax(price);
    }

    public String toString(){
        return String.format("%d. Shoes: %s | Available: %d | Price: $%.2f", id, name, stock, price);
    }

    public String cartToString(){
        return String.format(". Shoes: %s | On cart: %d | Price: $%.2f | Subtotal: $%.2f",
                name, stock, price, price.multiply(BigDecimal.valueOf(stock)));
    }

}
