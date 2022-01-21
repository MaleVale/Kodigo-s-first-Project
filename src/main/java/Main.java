import com.kodigo.helpers.EmailHelper;
import com.kodigo.helpers.GenerateBill;
import com.kodigo.helpers.TableHelper;
import com.kodigo.models.Product;
import com.kodigo.models.Purchase;
import com.kodigo.repository.CustomerManagement;
import com.kodigo.repository.ProductRepository;
import com.kodigo.validations.NumberValidation;

import java.util.*;

public class  Main {
    // objects and instances
    public static CustomerManagement customerManagement = new CustomerManagement();
    public static ProductRepository productRepository = new ProductRepository();
    public static TableHelper tableHelper = new TableHelper();
    public static Scanner scan = new Scanner(System.in);
    public static NumberValidation numberValidation = new NumberValidation();
    // arraylist for cart
    public static ArrayList<Product> cart = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("--------------------- WELCOME TO THE KODIGO'S STORE ---------------------");
        enterName();
        enterEmail();
        enterAddress();
        startShopping();
    }

    public static void enterName() {
        System.out.print("Enter your name: ");
        // validates that the typed name is a string and has a valid length, if not, gives three attempts to fix it
        if (!customerManagement.getCustomer().setName(scan.nextLine())) {
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
                if (customerManagement.getCustomer().setName(scan.nextLine())) {
                    valid = true;
                    break;
                }
                // shows a message if the method setName() returned false
                System.out.println("Number of attempts: " + attempts + "\n");
                attempts++;
            }

            if (!valid) {
                System.out.println("Sorry! It looks that you didn't type a valid name.");
                System.exit(0);
            }
        }
    }

    public static void enterEmail() {
        System.out.print("Enter your email: ");
        // validates that the typed email is valid, if not, gives three attempts to fix it
        if (!customerManagement.getCustomer().setEmail(scan.nextLine())) {
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
                if (customerManagement.getCustomer().setEmail(scan.nextLine())) {
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
        if (!customerManagement.getCustomer().setAddress(scan.nextLine())) {
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
                if (customerManagement.getCustomer().setAddress(scan.nextLine())) {
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

    public static void startShopping() {
        // variable for the loop
        boolean stayOnMenu = true;
        // starts the loop of the menu
        while (stayOnMenu) {
            // message
            System.out.println("\n--------------------- What do you want to do? ---------------------");
            // available options of the menu
            System.out.println("\n1. Add products to the cart");
            System.out.println("2. Delete products from the cart");
            System.out.println("3. Check products of the cart");
            System.out.println("4. See available products");
            System.out.println("5. End shopping");
            System.out.println("6. Exit");
            // message
            System.out.print("\nSelect an option: ");
            // switch for multiple cases
            switch (scan.next()) {
                // add products to the cart
                case "1" -> addToCart();
                // delete products from the cart
                case "2" -> deleteFromCart();
                // checks the products added to the cart
                case "3" -> tableHelper.checkCart(cart);
                // shows the available products
                case "4" -> tableHelper.showProductRepository(productRepository.returnProductRepository());
                // ends the shopping
                case "5" -> endShopping();
                // closes the program
                case "6" -> {
                    // sets boolean variable to false for breaking loop
                    stayOnMenu = false;
                    // closes the scanner
                    scan.close();
                }
                // default option
                default -> System.out.println("You have entered an invalid option.");
            }
        }

    }

    public static void addToCart() {
        // shows the list of the available products
        tableHelper.showProductRepository(productRepository.returnProductRepository());
        System.out.println("\n---------------- PLEASE READ ----------------");
        System.out.println("\n1. Type 0 if you want to go back to main menu. " +
                "Type the ID of the product that you want to add to the cart.");

        boolean stayOnCart = true;
        while (stayOnCart) {
            System.out.print("\nType the ID of the product that you want to add: ");
            String idProduct = scan.next();
            if (numberValidation.validateStringParsableToInt(idProduct)) {
                if (Integer.parseInt(idProduct) == 0) {
                    // closes the loop
                    stayOnCart = false;
                    System.out.println("\nGoing back to main menu...");
                } else {
                    // checks that the typed number coincides with a position from the arraylist
                    if (Integer.parseInt(idProduct) > productRepository.returnInventoryLength()) {
                        System.out.println("\nSorry! Looks like the typed ID doesn't coincides with a product.");
                    } else {
                        // saves the id as an integer and subtracts one to coincide it with an arraylist position
                        int id = Integer.parseInt(idProduct) - 1;
                        // checks if the typed id is on the cart
                        if (checkIfIsOnCart(productRepository.getProducts().get(id).getId())) {
                            System.out.println("Sorry! Looks like the typed ID is already on the cart.\n" +
                                    "If you want to edit the stock, go back to main menu and remove it from the cart," +
                                    " then come back and add it again with the new stock.");
                        } else {
                            System.out.println("\nAvailable: " + productRepository.getProducts().get(id).getStock());
                            System.out.print("How much do you want to add of this product?: ");
                            String stock = scan.next();
                            if (numberValidation.validateStringParsableToInt(stock)) {
                                // checks if the typed value doesn't surpass the max available
                                if (Integer.parseInt(stock) > productRepository.getProducts().get(id).getStock()) {
                                    System.out.println("\nSorry! Looks like stock is not enough.");
                                } else if (Integer.parseInt(stock) == 0) {
                                    System.out.println("\nSorry! You can't add 0 to cart.");
                                } else {
                                    // adds the product to the arraylist
                                    productRepository
                                            .getProducts()
                                            .get(id)
                                            .setStock
                                                    (productRepository.
                                                            getProducts().get(id)
                                                            .getStock() - Integer.parseInt(stock));
                                    cart.add(
                                            new Product(
                                                    productRepository.getProducts().get(id).getId(),
                                                    productRepository.getProducts().get(id).getBrand(),
                                                    productRepository.getProducts().get(id).getName(),
                                                    productRepository.getProducts().get(id).getPrice(),
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

    public static void deleteFromCart() {
        tableHelper.checkCart(cart);
        System.out.println("\n---------------- PLEASE READ ----------------");
        System.out.println("\n1. Type 0 if you want to go back to main menu. " +
                "Type the ID of the product that you want to remove from the cart.");

        boolean stayOnCart = true;
        while (stayOnCart) {
            if (cart.isEmpty()) {
                stayOnCart = false;
            } else {
                System.out.print("\nType the ID of the product that you want to remove: ");
                String idProduct = scan.next();
                if (numberValidation.validateStringParsableToInt(idProduct)) {
                    if (Integer.parseInt(idProduct) == 0) {
                        // closes the loop
                        stayOnCart = false;
                        System.out.println("\nGoing back to main menu...");
                    } else {
                        // checks that the typed ID corresponds to an arraylist position
                        if (Integer.parseInt(idProduct) > cart.size()) {
                            System.out.println("\nSorry! Looks like the typed ID doesn't coincides with a product.");
                        } else {
                            // saves the id as an integer and subtracts one to coincide it with an arraylist position
                            int id = Integer.parseInt(idProduct) - 1;

                            //Getting product to update the inventory
                            Product pToDelete = cart.get(id);
                            Product pFromRepo = productRepository.getProducts().stream()
                                    .filter(product -> pToDelete.getName().equals(product.getName()))
                                    .findAny()
                                    .orElse(null);
                            pFromRepo.setStock(pToDelete.getStock() + pFromRepo.getStock());
                            cart.remove(id);
                            System.out.println("\nProduct removed from the cart successfully!");
                            tableHelper.checkCart(cart);
                        }
                    }
                }
            }
        }
    }

    public static boolean checkIfIsOnCart(int id) {
        boolean isOnCart = false;
        for (Product product : cart) {
            /* if the product is on the cart, the return will be true, if the product is not in the cart, the return
               will be false, this means that can be added to the cart */
            isOnCart = product.getId() == id;
        }
        return isOnCart;
    }

    public static void endShopping() {
        if (!(cart.isEmpty())) {
            Purchase p = new Purchase(customerManagement.getCustomer(), (new Date()), (new ArrayList<>(cart)));
            customerManagement.getCustomer().getPurchases().add(p);
            cart.clear();
            System.out.println("Purchase added successfully");

            GenerateBill gb = new GenerateBill();
            String filename = gb.generatePdf(customerManagement);

            EmailHelper eh = new EmailHelper();
            eh.sendEmail(customerManagement, filename);
        } else {
            System.out.println("There is no products to create a purchase");
        }
    }


}

