package com.kodigo.models;

import com.kodigo.project.Validator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class Customer extends Validator {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String address;
    @Setter
    private ArrayList<Purchase> purchases;

    // validates that the typed name is a string and has a valid length
    public boolean setName(String name) {
        if (validateAlphabetic(name, 50)){
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    // validates that the typed email is valid
    public boolean setEmail(String email) {
        if (validateEmail(email)){
            this.email = email;
            return true;
        } else {
            return false;
        }
    }

    // validates that the address is a string and has a valid length
    public boolean setAddress(String address){
        if (validateAlphanumeric(address, 200)) {
            this.address = address;
            return true;
        } else {
            return false;
        }
    }
}
