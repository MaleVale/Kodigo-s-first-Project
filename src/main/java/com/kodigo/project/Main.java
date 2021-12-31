package com.kodigo.project;

import java.util.Scanner;

public class Main {
    // objects and instances
    public static final Clients clients = new Clients();
    public static final Inventory inventory = new Inventory();
    public static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // welcome message
        System.out.println("--------------------- WELCOME TO THE KODIGO'S STORE ---------------------");
        // makes a request for typing name
        enterName();
        // makes a request for typing email
        enterEmail();
        // makes a request for typing address
        enterAddress();
        // starts the loop for the shopping until the client decides to stop it
        startShopping();

    }

    public static void enterName() {
        // starts requesting for the data of the client
        System.out.print("Enter your name: ");
        // validates that the typed name is a string and has a valid length, if not, gives three attempts to fix it
        if (!clients.setName(scan.nextLine())) {
            // shows a message
            System.out.println("You have entered an invalid name. You have 3 attempts to type a valid name. \n");
            // boolean variable to check if the user typed a correct name after the for loop ends
            boolean valid = false;
            // int variable just to count attempts taken
            int attempts = 1;
            // the for loop is going to end when "i" reaches 3
            for (int i = 0; i < 3; i++) {
                // starts requesting for the data of the client
                System.out.print("Enter your name: ");
                /* if the return of the method setName() is true, breaks the for loop and sets
                 the boolean variable to true */
                if (clients.setName(scan.nextLine())) {
                    valid = true;
                    break;
                }
                // shows a message if the method setName() returned false
                System.out.println("Number of attempts: " + attempts + "\n");
                // sums one attempt
                attempts++;
            }

            // if the valid variable is different from true, is going to kill the program.
            if (!valid) {
                // shows the message
                System.out.println("Sorry! It looks that you didn't type a valid name.");
                // kills the program
                System.exit(0);
            }
        }
    }

    public static void enterEmail() {
        // requests for the client's email
        System.out.print("Enter your email: ");

        // validates that the typed email is valid, if not, gives three attempts to fix it
        if (!clients.setEmail(scan.nextLine())) {
            // shows a message
            System.out.println("You have entered an invalid email. You have 3 attempts to type a valid email. \n");
            // boolean variable to check if the user typed a correct email after the for loop ends
            boolean valid = false;
            // int variable just to count attempts taken
            int attempts = 1;
            // the for loop is going to end when "i" reaches 3
            for (int i = 0; i < 3; i++) {
                // starts requesting for the data of the client
                System.out.print("Enter your email: ");
                /* if the return of the method setName() is true, breaks the for loop and sets
                 the boolean variable to true */
                if (clients.setEmail(scan.nextLine())) {
                    valid = true;
                    break;
                }
                // shows a message if the method setName() returned false
                System.out.println("Number of attempts: " + attempts + "\n");
                // sums one attempt
                attempts++;
            }

            // if the valid variable is different from true, is going to kill the program.
            if (!valid) {
                // shows the message
                System.out.println("Sorry! It looks that you didn't type a valid email.");
                // kills the program
                System.exit(0);
            }
        }
    }

    public static void enterAddress() {
        // starts requesting for the data of the client
        System.out.print("Enter your address: ");
        // validates that the typed address is a string and has a valid length, if not, gives three attempts to fix it
        if (!clients.setName(scan.nextLine())) {
            // shows a message
            System.out.println("You have entered an invalid address. You have 3 attempts to type a valid address. \n");
            // boolean variable to check if the user typed a correct address after the for loop ends
            boolean valid = false;
            // int variable just to count attempts taken
            int attempts = 1;
            // the for loop is going to end when "i" reaches 3
            for (int i = 0; i < 3; i++) {
                // starts requesting for the data of the client
                System.out.print("Enter your address: ");
                /* if the return of the method setAddress() is true, breaks the for loop and sets
                 the boolean variable to true */
                if (clients.setAddress(scan.nextLine())) {
                    valid = true;
                    break;
                }
                // shows a message if the method setAddress() returned false
                System.out.println("Number of attempts: " + attempts + "\n");
                // sums one attempt
                attempts++;
            }

            // if the valid variable is different from true, is going to kill the program.
            if (!valid) {
                // shows the message
                System.out.println("Sorry! It looks that you didn't type a valid address.");
                // kills the program
                System.exit(0);
            }
        }
    }

    public static void startShopping(){
        // variable for the loop
        boolean stayOnMenu = true;
        // starts the loop of the menu
        while(stayOnMenu){
            // message
            System.out.println("\n--------------------- What do you want to do? ---------------------");
            // available options of the menu
            System.out.println("\n1. Add products to the cart");
            System.out.println("2. Delete products from the cart");
            System.out.println("3. Check products of the cart");
            System.out.println("4. See available products");
            System.out.println("5. End shopping");
            // message
            System.out.print("\nSelect an option: ");
            // switch for multiple cases
            switch (scan.nextLine()){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    // shows the list of the available products
                    inventory.showInventory();
                    break;
                case "5":
                    stayOnMenu = false;
                    break;
                default:
                    System.out.println("You have entered an invalid option.");
                    break;

            }
        }

    }



}

