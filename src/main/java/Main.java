import com.kodigo.models.Product;
import com.kodigo.models.Purchase;
import com.kodigo.repository.CustomerManagement;
import com.kodigo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

public class  Main {
    // objects and instances
    public static CustomerManagement customerManagement = new CustomerManagement();
    public static ProductRepository productRepository = new ProductRepository();
    public static final Scanner scan = new Scanner(System.in);
    // arraylist for cart
    public static ArrayList<Product> cart = new ArrayList<Product>();

    public static void main(String[] args) throws IOException {
        // welcome Message
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
        if (!customerManagement.getCustomer().setName(scan.nextLine())) {
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
                if (customerManagement.getCustomer().setName(scan.nextLine())) {
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
        if (!customerManagement.getCustomer().setEmail(scan.nextLine())) {
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

    public static void startShopping() throws IOException {
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
                case "3" -> checkCart();
                // shows the available products
                case "4" -> productRepository.showProductRepository();
                // ends the shopping
                case "5" -> {
                    //stayOnMenu = false;
                    endShopping();
                    printPDF();
                }
                // closes the program
                case "6" -> stayOnMenu = false;
                // default option
                default -> System.out.println("You have entered an invalid option.");
                //prints and sents de bill via email.

            }
        }

    }

    public static void addToCart() {
        // shows the list of the available products
        productRepository.showProductRepository();
        // message
        System.out.println("\n---------------- PLEASE READ ----------------");
        System.out.println("\n1. Type 0 if you want to go back to main menu. " +
                "Type the ID of the product that you want to add to the cart.");
        // variable for the loop
        boolean stayOnCart = true;
        // loop
        while (stayOnCart) {
            // message
            System.out.print("\nType the ID of the product that you want to add: ");
            // captures the typed value as string
            String idProduct = scan.next();
            // checks if the typed value is parsable to integer
            if (NumberUtils.isParsable(idProduct)) {
                // checks if the typed value is 0, or if it is available at the inventory.
                if (Integer.parseInt(idProduct) == 0) {
                    // closes the loop
                    stayOnCart = false;
                    // message
                    System.out.println("\nGoing back to main menu...");
                } else {
                    // checks that the typed number coincides with a position from the arraylist
                    if (Integer.parseInt(idProduct) > productRepository.returnInventoryLength()) {
                        // message
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
                            // shows how much is available
                            System.out.println("\nAvailable: " + productRepository.getProducts().get(id).getStock());
                            // requests to the customer how much stock wants to add to the cart
                            System.out.print("How much do you want to add of this product?: ");
                            // saves the typed value
                            String stock = scan.next();
                            // checks if the typed value is parsable to integer
                            if (NumberUtils.isParsable(stock)) {
                                // checks if the typed value doesn't surpass the max available
                                if (Integer.parseInt(stock) > productRepository.getProducts().get(id).getStock()) {
                                    // message
                                    System.out.println("\nSorry! Looks like stock is not enough.");
                                } else if (Integer.parseInt(stock) == 0) {
                                    // message
                                    System.out.println("\nSorry! You can't add 0 to cart.");
                                } else {
                                    // adds the product to the arraylist
                                    productRepository
                                            .getProducts()
                                            .get(id)
                                            .setStock(productRepository.getProducts().get(id).getStock() - Integer.parseInt(stock));
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
                            } else {
                                // message
                                System.out.println("Sorry! Looks like you typed an invalid character.");
                            }
                        }
                    }
                }
            } else {
                // the typed value is invalid.
                System.out.println("\nSorry! Looks like you didn't type a number.");
            }
        }

    }

    public static void deleteFromCart() {
        // shows the products added to the cart
        checkCart();
        // message
        System.out.println("\n---------------- PLEASE READ ----------------");
        System.out.println("\n1. Type 0 if you want to go back to main menu. " +
                "Type the ID of the product that you want to remove from the cart.");
        // variable for the loop
        boolean stayOnCart = true;
        // loop
        while (stayOnCart) {
            if (cart.isEmpty()) {
                stayOnCart = false;
            } else {
                // message
                System.out.print("\nType the ID of the product that you want to remove: ");
                // captures the typed value as string
                String idProduct = scan.next();
                // checks if the typed value is parsable to integer
                if (NumberUtils.isParsable(idProduct)) {
                    // checks if the typed value is 0, or if it is available at the inventory.
                    if (Integer.parseInt(idProduct) == 0) {
                        // closes the loop
                        stayOnCart = false;
                        // message
                        System.out.println("\nGoing back to main menu...");
                    } else {
                        // checks that the typed ID corresponds to an arraylist position
                        if (Integer.parseInt(idProduct) > cart.size()) {
                            // message
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
                            // removes the product from the arraylist
                            cart.remove(id);

                            // message
                            System.out.println("\nProduct removed from the cart successfully!");
                            // shows the cart again
                            checkCart();
                        }
                    }
                } else {
                    // the typed value is invalid.
                    System.out.println("\nSorry! Looks like you didn't type a number.");
                }
            }
        }
    }

    public static boolean checkIfIsOnCart(int id) {
        // variable for return
        boolean isOnCart = false;
        // checks if the given id corresponds to an existing product on the cart
        for (Product product : cart) {
            /* if the product is on the cart, the return will be true, if the product is not in the cart, the return
               will be false, this means that can be added to the cart */
            isOnCart = product.getId() == id;
        }
        return isOnCart;
    }

    public static void checkCart() {
        if (cart.isEmpty()) {
            // message
            System.out.println("\nThe cart is empty!");
        } else {
            int count = 1;
            // shows the products added to the arraylist
            for (Product product : cart) {
                System.out.println(count + product.cartToString());
                // sums to the count variable
                count++;
            }
        }
    }

    public static void endShopping() {
        if (!(cart.size() == 0)) {
            //If there is products in var 'cart' we can add it to the purchase
            Purchase p = new Purchase(customerManagement.getCustomer(), (new Date()), (new ArrayList<>(cart)));
            //Adding purchase to the current customer
            customerManagement.getCustomer().getPurchases().add(p);
            //clear var 'cart' when the purchase are completed
            cart.clear();
            System.out.println("Purchase added successfully");
        } else {
            System.out.println("There is no products to create a purchase");
        }
    }

    public static void printPDF() throws IOException {

            try (PDDocument doc = new PDDocument()) {

                PDPage myPage = new PDPage();
                doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {
                //Page configuration
                cont.beginText();
                cont.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD_ITALIC), 12);
                cont.setLeading(20.5f);
                cont.newLineAtOffset(50, 500);

                //The following lines writes on the PDF file
                String title = "<                                                 -¡THANK YOU FOR YOUR PURCHASE!-                                             >";
                cont.showText(title);

                cont.newLine();
                String line = "                                               Down below are your selected items and the total                       ";
                cont.showText(line);

                cont.newLine();
                String date = "DATE: 1-4-2022";
                cont.showText(date);

                cont.newLine();
                String subTitle = "PRODUCTS:";
                cont.showText(subTitle);

                cont.newLine();
                for (int i = 0; i < customerManagement.getCustomer().getPurchases().get(customerManagement.getCustomer().getPurchases().size()-1).getProducts().size(); i++) {
                    cont.showText((i+1)+customerManagement.getCustomer().getPurchases().get(customerManagement.getCustomer().getPurchases().size()-1).getProducts().get(i).cartToString());
                    cont.appendRawCommands("'\n");
                }
                cont.newLine();
                BigDecimal Total = customerManagement.getCustomer().getPurchases().get(0).getTotal();
                cont.showText("TOTAL : $"+Total);

                cont.endText();
            }

                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(Calendar.getInstance().getTime());
            doc.save("src/main/resources/bill "+ timeStamp +".pdf");

        }
        }
    public static class SentPDF {
        public static void main(String[] args) throws MessagingException {
            // Assuming you are sending email from through gmails smtp
            String host = "smtp.gmail.com";

            // Get system properties
            Properties properties = System.getProperties();

            //Getting the absolute path of the system
            String absolutePath = properties.getProperty("user.dir");

            // Setup mail server
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            // Recipient's email ID needs to be mentioned.
            String to = "malenavalentina0703@gmail.com";

            // Sender's email ID needs to be mentioned
            String from = "proyectokodigo123@gmail.com";

            // Get the Session object.// and pass
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication("proyectokodigo123@gmail.com", "callefalsa123");
                }
            });
            //session.setDebug(true);
            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);
                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));
                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                // Set Subject: header field
                message.setSubject("Your bill is here!");
                Multipart multipart = new MimeMultipart();
                MimeBodyPart attachmentPart = new MimeBodyPart();
                MimeBodyPart textPart = new MimeBodyPart();
                try {

                    File f = new File(absolutePath + "src/main/resources/bill.pdf");

                    attachmentPart.attachFile(f);
                    textPart.setText("You can find your bill in the attached file");
                    multipart.addBodyPart(textPart);
                    multipart.addBodyPart(attachmentPart);

                } catch (IOException e) {

                    e.printStackTrace();

                }

                message.setContent(multipart);

                System.out.println("sending...");
                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }

    }


    }

