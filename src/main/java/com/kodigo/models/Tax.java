package com.kodigo.models;

import java.math.BigDecimal;

public abstract class Tax {
    private BigDecimal PercentageTax = new BigDecimal("0");
    private BigDecimal Tax = new BigDecimal("0");

    protected void initialize() {
        //set percentage tax
    }

    public BigDecimal getTax(BigDecimal price) {
        return Tax.multiply(PercentageTax);
    }
}
