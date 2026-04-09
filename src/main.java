import java.sql.SQLException;
import java.util.Scanner;

import AccountSystem.*; // CHANGE THIS LATER PLEASE GOD
import DBSystem.UserRepo;
import MenuSystem.MenuManager;
import MenuSystem.MenuPrinter;
import OrderingSystem.OrderManager;
import OrderingSystem.OrderPrinter;
import DBSystem.DatabaseManager;
import OrderingSystem.OrderUI;

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

    // Lovecraft's code (not as if the code above is good either)
    int functionSwitch;

    Scanner input = new Scanner(System.in); // Scanner for taking user input

    while (true) {
        System.out.println(" --- Welcome to the Gino's Cafeteria App --- ");
        System.out.println("""
        Please select an option:
        1: View Menu & start an Order
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
            case 9: // Exit program
                db.disconnect();
                System.exit(0);
            default:
                System.out.println("Please select a valid option.");
        }
    }
}

