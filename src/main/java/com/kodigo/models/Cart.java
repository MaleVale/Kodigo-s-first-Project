package com.kodigo.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Cart {
    private ArrayList<Product> cart;

    public Cart(){
        cart = new ArrayList<>();
    }
}
