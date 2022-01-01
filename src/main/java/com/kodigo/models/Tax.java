package com.kodigo.models;

import java.math.BigDecimal;

public abstract class Tax {
    protected BigDecimal percentageTax = new BigDecimal("0");
    protected BigDecimal tax = new BigDecimal("0");

    protected void initializeTax(BigDecimal price) {
        setPercentageTax();
        setTax(price);
    }

    private void setPercentageTax(){
        this.percentageTax = BigDecimal.valueOf(13);
    }

    private void setTax(BigDecimal price){
        this.tax = price.multiply(this.percentageTax);
    }

    public BigDecimal getTax() {
        return tax;
    }
}
