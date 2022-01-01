package com.kodigo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Product extends Tax{

    private int id;
    private String brand;
    private String name;
    private BigDecimal price;
    private int stock;

    public Product(){
        super.initialize();
    }

    public String toString(){
        return String.format("%d. Shoes: %s | Available: %d | Price: $%.2f", id, name, stock, price);
    }

}
