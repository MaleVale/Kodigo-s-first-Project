package com.kodigo.project;

import lombok.Getter;

@Getter

public class Clients extends Validator {

    private String name;
    private String email;
    private String address;

    public boolean setName(String name) {
        if (validateString(name)){
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    public boolean setEmail(String email) {
        if (validateEmail(email)){
            this.email = email;
            return true;
        } else {
            return false;
        }
    }
}

