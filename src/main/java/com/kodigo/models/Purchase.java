package com.kodigo.models;

import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Data
public class Purchase {
    private int id;
    private Date date;
    private Customer customer;
    private ArrayList<Product> products;
    private BigDecimal subTotal = new BigDecimal("0");
    private BigDecimal tax = new BigDecimal("0");
    private BigDecimal total = new BigDecimal("0");

    public Purchase(Customer customer, Date date, ArrayList<Product> products) {
        this.customer = customer;
        this.date = date;
        this.products = products;
        this.id = assignKey();
        setSubTotal(products);
        setTax(products);
        setTotal(products);
    }

    private int assignKey() {
            return (this.customer.getPurchases().size()+1);
    }

    private void setSubTotal(ArrayList<Product> products) {
        products.forEach(product -> {
                this.subTotal = this.subTotal.add((BigDecimal.valueOf(product.getStock())).multiply(product.getPrice()));
        });
    }

    private void setTax(ArrayList<Product> products) {
        products.forEach(product -> {
            this.tax = this.tax.add((BigDecimal.valueOf(product.getStock())).multiply(product.getTax()));
        });
    }

    private void setTotal(ArrayList<Product> products) {
        this.total = this.subTotal.add(this.tax);
    }
}
