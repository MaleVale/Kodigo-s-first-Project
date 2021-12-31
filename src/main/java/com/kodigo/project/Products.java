package com.kodigo.project;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Products {
    private int id;
    private String brand;
    private String name;
    private double price;
    private int stock;

    public String toString(){
        return id + ". Name: " + name + ", Brand: " + brand + ", Price: $" + price + ", Available: " + stock;
    }
}
