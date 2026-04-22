import java.sql.SQLException;
import java.util.Scanner;

import AccountSystem.AccountPrinter;
import AccountSystem.AccountService;
import AccountSystem.AccountUI;
import AccountSystem.User;
import DBSystem.UserRepo;
import DBSystem.DatabaseManager;
import MenuSystem.MenuManager;
import MenuSystem.MenuPrinter;
import OrderingSystem.OrderManager;
import OrderingSystem.OrderPrinter;
import OrderingSystem.OrderUI;
import PaymentSystem.PaymentCash;
import AccountSystem.User;
import PaymentSystem.PaymentCard;
import PaymentSystem.PaymentCash;
import PaymentSystem.ReceiptPrinter;

void main(String[] args) {
    // --- Variables ---
    // Menu System
    MenuManager menuManager = new MenuManager();
    MenuPrinter menuPrinter = new MenuPrinter();

    // Order System
    OrderManager orderManager = new OrderManager(menuManager, 0);
    OrderPrinter orderPrinter = new OrderPrinter();

    // Database
    DatabaseManager db = new DatabaseManager("db.db");
    db.connect();
    try {
        db.initSchema();
    } catch (SQLException e) {
        throw new RuntimeException("Failed to init database schema ");
    }

    // Account System
    AccountPrinter accountPrinter = new AccountPrinter();
    AccountService accountService = new AccountService(new UserRepo(db));
    AccountUI accountUI = new AccountUI(accountService, accountPrinter);

    User user = null;
    int choice;

    do {
        choice = accountUI.mainMenu();

        switch (choice) {
            case 0 -> accountUI.createAccount();
            case 1 -> user = accountUI.login();
            case 2 -> System.exit(0);
        }
    } while (choice != 1);

    int functionSwitch;

    Scanner input = new Scanner(System.in); // Scanner for taking user input

    boolean shouldExit = false;
    while (!shouldExit) {
        System.out.println(" --- Welcome to the Gino's Cafeteria App --- ");
        System.out.println("""
        Please select an option:
        1: View Menu & start an Order
        6: View user data
        7: Update user data
        8: Delete account
        9: Exit
        """);
        functionSwitch = input.nextInt(); // Gets user input
        input.nextLine(); // Gets rid of buffered new line because of stupid scanner logic (Later input.nextLine would fail otherwise)

        switch (functionSwitch) {
            case 1:
                OrderUI orderUI = new OrderUI(menuManager, menuPrinter, orderManager, orderPrinter, user);
                orderUI.viewMenuAndStartOrder();
                break;
            // case X: for additional functionality
            // Somewhere here handle account deletion
            case 6: // View user data
                accountUI.displayUserData(user);
                break;
            case 7: // Update user data
                user = accountUI.updateUserData(user);
                break;
            case 8: // Delete account
                accountUI.deleteAccount(user);
                // break out of the loop
                shouldExit = true;
                break;
            case 9: // Exit program
                shouldExit = true;
                break;
            default:
                System.out.println("Please select a valid option.");
        }
    }

    db.disconnect();
}

