package com.kodigo.repository;

import com.kodigo.helpers.LogCreator;
import com.kodigo.models.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.Scanner;

public class CustomerManagement {
    @Getter
    @Setter
    private static Customer customer;
    private static LogCreator log;

    static {
        customer = new Customer();
        log = LogCreator.getInstance();
    }

    public static void enterCustomerData(){
        enterName();
        enterEmail();
        enterAddress();
    }

    public static void enterName() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your name: ");
        // validates that the typed name is a string and has a valid length, if not, gives three attempts to fix it
        if (!getCustomer().setName(scan.nextLine())) {
            log.getLogger().warning("The customer didn't type a valid name. He has 3 attempts to fix it.\n");
            System.out.println("You have entered an invalid name. You have 3 attempts to type a valid name. \n");
            // boolean variable to check if the user typed a correct name after the for loop ends
            boolean valid = false;
            // int variable just to count attempts taken
            int attempts = 1;
            // the for loop is going to end when "i" reaches 3
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter your name: ");
                /* if the return of the method setName() is true, breaks the for loop and sets
                 the boolean variable to true */
                if (getCustomer().setName(scan.nextLine())) {
                    valid = true;
                    break;
                }
                // shows a message if the method setName() returned false
                System.out.println("Number of attempts: " + attempts + "\n");
                log.getLogger().warning(
                        String.format("The customer didn't type a valid name. Attempts taken: %o%n", attempts)
                );
                attempts++;
            }

            if (!valid) {
                System.out.println("Sorry! It looks that you didn't type a valid name.");
                log.getLogger().severe("The program got killed because the customer didn't type a valid name.\n");
                System.exit(0);
            }
        }
    }

    public static void enterEmail() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your email: ");
        // validates that the typed email is valid, if not, gives three attempts to fix it
        if (!getCustomer().setEmail(scan.nextLine())) {
            log.getLogger().warning("The customer didn't type a valid email. He has 3 attempts to fix it.\n");
            System.out.println("You have entered an invalid email. You have 3 attempts to type a valid email. \n");
            // boolean variable to check if the user typed a correct email after the for loop ends
            boolean valid = false;
            // int variable just to count attempts taken
            int attempts = 1;
            // the for loop is going to end when "i" reaches 3
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter your email: ");
                /* if the return of the method setName() is true, breaks the for loop and sets
                 the boolean variable to true */
                if (getCustomer().setEmail(scan.nextLine())) {
                    valid = true;
                    break;
                }
                // shows a message if the method setName() returned false
                System.out.println("Number of attempts: " + attempts + "\n");
                log.getLogger().warning(
                        String.format("The customer didn't type a valid email. Attempts taken: %o%n", attempts)
                );
                attempts++;
            }

            // if the valid variable is different from true, is going to kill the program.
            if (!valid) {
                System.out.println("Sorry! It looks that you didn't type a valid email.");
                log.getLogger().severe("The program got killed because the customer didn't type a valid email.\n");
                System.exit(0);
            }
        }
    }

    public static void enterAddress() {
        Scanner scan = new Scanner(System.in);
        // starts requesting for the data of the client
        System.out.print("Enter your address: ");
        // validates that the typed address is a string and has a valid length, if not, gives three attempts to fix it
        if (!getCustomer().setAddress(scan.nextLine())) {
            log.getLogger().warning("The customer didn't type a valid address. He has 3 attempts to fix it.\n");
            System.out.println("You have entered an invalid address. You have 3 attempts to type a valid address. \n");
            // boolean variable to check if the user typed a correct address after the for loop ends
            boolean valid = false;
            int attempts = 1;
            // the for loop is going to end when "i" reaches 3
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter your address: ");
                /* if the return of the method setAddress() is true, breaks the for loop and sets
                 the boolean variable to true */
                if (getCustomer().setAddress(scan.nextLine())) {
                    valid = true;
                    break;
                }
                // shows a message if the method setAddress() returned false
                System.out.println("Number of attempts: " + attempts + "\n");
                log.getLogger().warning(
                        String.format("The customer didn't type a valid address. Attempts taken: %o%n", attempts)
                );
                attempts++;
            }

            // if the valid variable is different from true, is going to kill the program.
            if (!valid) {
                // shows the message
                System.out.println("Sorry! It looks that you didn't type a valid address.");
                log.getLogger().severe("The program got killed because the customer didn't type a valid address.\n");
                System.exit(0);
            }
        }
    }

}

