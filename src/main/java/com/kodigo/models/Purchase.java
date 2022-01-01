package com.kodigo.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Data
public class Purchase {
    private int id;
    private Date date;
    private ArrayList<Product> products;

    public BigDecimal getSubTotal(){return null;}
    public BigDecimal getTax(){return null;}
    public BigDecimal getTotal(){return null;}
}
