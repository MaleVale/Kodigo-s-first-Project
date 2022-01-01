package com.kodigo.repository;

import com.kodigo.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
public class ProductRepository {
    @Getter
    private ArrayList<Product> products;

    public ProductRepository(){
        this.initialize();
    }

    private void initialize(){}

    public boolean isProductAvailable(Product product){
        return false;
    }
}
