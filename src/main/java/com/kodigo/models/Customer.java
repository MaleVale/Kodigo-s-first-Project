package com.kodigo.models;

import com.kodigo.validations.StringValidation;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@NoArgsConstructor
@Data
public class Customer {
    private String name;
    private String email;
    private String address;
    private ArrayList<Purchase> purchases = new ArrayList<>();

    private StringValidation validator = new StringValidation();

    public boolean setName(String name) {
        if (validator.validateAlphabetic(name, 50)){
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    public boolean setEmail(String email) {
        if (validator.validateEmail(email)){
            this.email = email;
            return true;
        } else {
            return false;
        }
    }

    public boolean setAddress(String address){
        if (validator.validateAlphanumeric(address, 200)) {
            this.address = address;
            return true;
        } else {
            return false;
        }
    }
}