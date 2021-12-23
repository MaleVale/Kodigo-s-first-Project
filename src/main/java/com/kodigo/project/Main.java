package com.kodigo.project;

import java.util.Scanner;

public class Main {
    // objects and instances
    public static final Clients clients = new Clients();
    public static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        // welcome message
        System.out.println("--------------------- WELCOME TO THE KODIGO'S STORE ---------------------");
        // makes a request for typing name
        enterName();
        // makes a request for typing email
        enterEmail();

        System.out.println(clients.getName() + " " + clients.getEmail());

    }

    public static void enterName(){
        // starts requesting for the data of the client
        System.out.print("Enter your name: ");
        // validates that the typed name is a string, if not, gives three attempts to fix it
        if (!clients.setName(scan.nextLine())){
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

    public static void enterEmail(){
        // requests for the client's email
        System.out.print("Enter your email: ");

        // validates that the typed email is valid, if not, gives three attempts to fix it
        if (!clients.setEmail(scan.nextLine())){
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
}
