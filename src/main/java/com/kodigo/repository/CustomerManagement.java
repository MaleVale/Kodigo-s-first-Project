package com.kodigo.repository;

import com.kodigo.models.Customer;
import lombok.Getter;
import lombok.Setter;

public class CustomerManagement {
    @Getter
    @Setter
    private Customer customer;

    public CustomerManagement(){
        this.customer = new Customer();
        this.initialize();
    }
}

