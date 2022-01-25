package com.kodigo.models;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Product {

    private int id;
    private String brand;
    private String name;
    private BigDecimal price;
    private int stock;
    private Tax tax;

    public Product(int id, String brand, String name, BigDecimal price, int stock){
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.stock = stock;
        tax = new Tax();
        tax.initializeTax(price);
    }

    public BigDecimal getAmount(){
        return getPrice().multiply(BigDecimal.valueOf(getStock()));
    }

    public String cartToString(){
        return String.format(". Shoes: %s | Units: %d | Price: $%.2f | Amount: $%.2f",
                name, stock, price, price.multiply(BigDecimal.valueOf(stock)));
    }


}
