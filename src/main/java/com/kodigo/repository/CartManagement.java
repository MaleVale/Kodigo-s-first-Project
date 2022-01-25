package com.kodigo.repository;

import com.itextpdf.text.DocumentException;
import com.kodigo.helpers.*;
import com.kodigo.models.Cart;
import com.kodigo.models.Product;
import com.kodigo.models.Purchase;
import com.kodigo.validations.NumberValidation;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CartManagement {
    @Getter
    private static Cart cart;
    private static LogCreator log;
    private static NumberValidation numberValidation;

    static {
        cart = new Cart();
        log = LogCreator.getInstance();
        numberValidation = new NumberValidation();
    }

    public static void addToCart() {
        Scanner scan = new Scanner(System.in);
        log.getLogger().info("The customer has started to add products to the cart.\n");
        TableHelper.showProductRepository(ProductRepository.returnProductRepository());
        System.out.println("\n---------------- PLEASE READ ----------------");
        System.out.println("\n1. Type 0 if you want to go back to main menu. " +
                "Type the ID of the product that you want to add to the cart.");

        int lastId = ProductRepository.returnInventoryLength();

        boolean stayOnCart = true;
        while (stayOnCart) {
            System.out.print("\nType the ID of the product that you want to add (1-"+lastId+"): ");
            String idProduct = scan.next();
            if (numberValidation.validateStringParsableToInt(idProduct)) {
                if (Integer.parseInt(idProduct) == 0) {
                    // closes the loop
                    stayOnCart = false;
                    log.getLogger().info("The customer has returned to the main menu.\n");
                    System.out.println("\nGoing back to main menu...");
                } else {
                    // checks that the typed number coincides with a position from the arraylist
                    if (Integer.parseInt(idProduct) > ProductRepository.returnInventoryLength()) {
                        log.getLogger().warning("The customer typed an invalid ID trying to add a product.\n");
                        System.out.println("\nSorry! Looks like the typed ID doesn't coincides with a product.");
                    } else {
                        // saves the id as an integer and subtracts one to coincide it with an arraylist position
                        int id = Integer.parseInt(idProduct) - 1;
                        // checks if the typed id is on the cart
                        if (checkIfIsOnCart(ProductRepository.getProducts().get(id).getId())) {
                            System.out.println("Sorry! Looks like the typed ID is already on the cart.\n" +
                                    "If you want to edit the stock, go back to main menu and remove it from the cart," +
                                    " then come back and add it again with the new stock.");
                            log.getLogger().warning("The customer tried to duplicate a product in the cart.\n");
                        } else {
                            int available = ProductRepository.getProducts().get(id).getStock();
                            System.out.println("\nAvailable: " + available);
                            System.out.print("How much do you want to add of this product? (1-"+available+"): ");
                            String stock = scan.next();
                            if (numberValidation.validateStringParsableToInt(stock)) {
                                // checks if the typed value doesn't surpass the max available
                                if (Integer.parseInt(stock) > ProductRepository.getProducts().get(id).getStock()) {
                                    System.out.println("\nSorry! Looks like stock is not enough.");
                                    log.getLogger().warning(
                                            "The customer tried to add a product to the cart but stock isn't enough.\n"
                                    );
                                } else if (Integer.parseInt(stock) == 0) {
                                    System.out.println("\nSorry! You can't add 0 to cart.");
                                    log.getLogger().warning("The customer tried to add 0 stock of a product.\n");
                                } else {
                                    log.getLogger().info("The customer added a product to the cart.\n");
                                    // adds the product to the arraylist
                                    ProductRepository
                                            .getProducts()
                                            .get(id)
                                            .setStock
                                                    (ProductRepository.
                                                            getProducts().get(id)
                                                            .getStock() - Integer.parseInt(stock));
                                    cart.getCart().add(
                                            new Product(
                                                    ProductRepository.getProducts().get(id).getId(),
                                                    ProductRepository.getProducts().get(id).getBrand(),
                                                    ProductRepository.getProducts().get(id).getName(),
                                                    ProductRepository.getProducts().get(id).getPrice(),
                                                    Integer.parseInt(stock)
                                            )
                                    );
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void  deleteFromCart() {
        Scanner scan = new Scanner(System.in);
        log.getLogger().info("The customer has started to delete products from the cart.\n");
        TableHelper.checkCart(cart.getCart());
        System.out.println("\n---------------- PLEASE READ ----------------");
        System.out.println("\n1. Type 0 if you want to go back to main menu. " +
                "Type the ID of the product that you want to remove from the cart.");

        boolean stayOnCart = true;
        while (stayOnCart) {
            if (cart.getCart().isEmpty()) {
                log.getLogger().warning("The customer tried to delete products from an empty cart.\n");
                stayOnCart = false;
            } else {
                int lastId = cart.getCart().size();
                System.out.print("\nType the ID of the product that you want to remove (1-"+lastId+"): ");
                String idProduct = scan.next();
                if (numberValidation.validateStringParsableToInt(idProduct)) {
                    if (Integer.parseInt(idProduct) == 0) {
                        // closes the loop
                        stayOnCart = false;
                        log.getLogger().info("The customer has returned to the main menu.\n");
                        System.out.println("\nGoing back to main menu...");
                    } else {
                        // checks that the typed ID corresponds to an arraylist position
                        if (Integer.parseInt(idProduct) > cart.getCart().size()) {
                            log.getLogger().warning(
                                    "The customer typed an invalid ID trying to delete a product from the cart.\n"
                            );
                            System.out.println("\nSorry! Looks like the typed ID doesn't coincides with a product.");
                        } else {
                            log.getLogger().info("The customer deleted a product from the cart.\n");
                            // saves the id as an integer and subtracts one to coincide it with an arraylist position
                            int id = Integer.parseInt(idProduct) - 1;

                            //Getting product to update the inventory
                            Product pToDelete = cart.getCart().get(id);
                            Product pFromRepo = ProductRepository.getProducts().stream()
                                    .filter(product -> pToDelete.getName().equals(product.getName()))
                                    .findAny()
                                    .orElse(null);
                            pFromRepo.setStock(pToDelete.getStock() + pFromRepo.getStock());
                            cart.getCart().remove(id);
                            System.out.println("\nProduct removed from the cart successfully!");
                            TableHelper.checkCart(cart.getCart());
                        }
                    }
                }
            }
        }
    }

    public static void checkCart(){
        TableHelper.checkCart(cart.getCart());
    }

    public static boolean checkIfIsOnCart(int id) {
        boolean isOnCart = false;
        for (Product product : cart.getCart()) {
            /* if the product is on the cart, the return will be true, if the product is not in the cart, the return
               will be false, this means that can be added to the cart */
            isOnCart = product.getId() == id;
        }
        return isOnCart;
    }

    public static void endShopping() throws DocumentException, IOException {
        if (!(cart.getCart().isEmpty())) {
            Purchase p = new Purchase(CustomerManagement.getCustomer(), (new Date()), (new ArrayList<>(cart.getCart())));
            CustomerManagement.getCustomer().getPurchases().add(p);
            cart.getCart().clear();
            log.getLogger().fine("The purchase was created successfully.\n");
            System.out.println("Purchase added successfully");

            chooseFileFormat();
        } else {
            log.getLogger().warning("The customer tried to create a purchase with an empty cart.\n");
            System.out.println("There is no products to create a purchase");
        }
    }

    public static void chooseFileFormat() throws DocumentException, IOException {
        GeneratePdf pdf = new GeneratePdf();
        GenerateExcel excel = new GenerateExcel();
        String filename = null;

        System.out.println("\nCongratulations! You're almost ready, " +
                "but we need you to select a file format to send your bill to your email.");

        Scanner scan = new Scanner(System.in);
        boolean stayOnSelection = true;

        while (stayOnSelection){
            System.out.println("\n1. PDF file.");
            System.out.println("2. Excel file.\n");

            System.out.print("Please, choose one of the available types (1/2): ");
            switch (scan.next()) {
                case "1" -> {
                    filename = pdf.generateFile();
                    stayOnSelection = false;
                }
                case "2" -> {
                    filename = excel.generateFile();
                    stayOnSelection = false;
                }
                default -> System.out.println("\nSorry! Looks like you typed an invalid option. Try it again.");
            }
        }

        EmailHelper eh = new EmailHelper();
        eh.sendEmail(filename);
    }


}
