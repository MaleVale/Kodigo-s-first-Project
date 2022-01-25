import com.itextpdf.text.DocumentException;
import com.itextpdf.text.DocumentException;
import com.kodigo.helpers.LogCreator;
import com.kodigo.helpers.TableHelper;
import com.kodigo.repository.CartManagement;
import com.kodigo.repository.CustomerManagement;
import com.kodigo.repository.ProductRepository;
import java.io.IOException;
import java.util.*;

public class  Main {

    public static void main(String[] args) throws DocumentException, IOException {
        System.out.println("--------------------- WELCOME TO THE KODIGO'S STORE ---------------------");
        CustomerManagement.enterCustomerData();
        startMenu();
    }

    public static void startMenu() throws DocumentException, IOException {
        // instances and objects
        LogCreator log = LogCreator.getInstance();
        Scanner scan = new Scanner(System.in);

        //Logger
        log.getLogger().info("The customer has entered his data and joined to the menu successfully.\n");
        // variable for the loop
        boolean stayOnMenu = true;
        // starts the loop of the menu
        while (stayOnMenu) {
            System.out.println("\n--------------------- What do you want to do? ---------------------");
            // available options of the menu
            System.out.println("\n1. Add products to the cart");
            System.out.println("2. Delete products from the cart");
            System.out.println("3. Check products of the cart");
            System.out.println("4. See available products");
            System.out.println("5. End shopping");
            System.out.println("6. Exit");
            System.out.print("\nSelect an option (1-6): ");
            // switch for multiple cases
            switch (scan.next()) {
                case "1" -> CartManagement.addToCart();
                case "2" -> CartManagement.deleteFromCart();
                case "3" -> CartManagement.checkCart();
                case "4" -> TableHelper.showProductRepository(ProductRepository.returnProductRepository());
                case "5" -> CartManagement.endShopping();
                case "6" -> stayOnMenu = false;
                default -> System.out.println("You have entered an invalid option.");
            }
        }
    }


}

