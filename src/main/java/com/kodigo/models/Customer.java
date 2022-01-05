package com.kodigo.models;

import com.kodigo.validations.StringValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
public class Customer {
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private String address;
    @Setter
    @Getter
    private ArrayList<Purchase> purchases = new ArrayList<>();

    private StringValidation validator = new StringValidation();

    // validates that the typed name is a string and has a valid length
    public boolean setName(String name) {
        if (validator.validateAlphabetic(name, 50)){
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    // validates that the typed email is valid
    public boolean setEmail(String email) {
        if (validator.validateEmail(email)){
            this.email = email;
            return true;
        } else {
            return false;
        }
    }

    // validates that the address is a string and has a valid length
    public boolean setAddress(String address){
        if (validator.validateAlphanumeric(address, 200)) {
            this.address = address;
            return true;
        } else {
            return false;
        }
    }
}