package com.kodigo.project;

import lombok.Getter;

@Getter
public class Clients extends Validator {

    private String name;
    private String email;
    private String address;

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

