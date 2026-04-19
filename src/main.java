import java.sql.SQLException;
import java.util.Scanner;

import AccountSystem.*; // CHANGE THIS LATER PLEASE GOD
import DBSystem.UserRepo;
import MenuSystem.MenuManager;
import MenuSystem.MenuPrinter;
import OrderingSystem.OrderManager;
import OrderingSystem.Order;
import OrderingSystem.OrderPrinter;
import DBSystem.DatabaseManager;

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
        e.printStackTrace();
    }

    // Account System
    User testuser = new User (
            69,
            new Username("skibidi"),
            new Password("Skibidi_Toilet420", false),
            0,
            new Email("hello@world.com"),
            new PhoneNumber("123456789")
    );
    AccountPrinter accountPrinter = new AccountPrinter();
    AccountService accountService = new AccountService(new UserRepo(db));
    AccountUI accountUI = new AccountUI(accountService, accountPrinter);

    int functionSwitch;

    Scanner input = new Scanner(System.in); // Scanner for taking user input

    while (true) {
        System.out.println(" --- Welcome to the Gino's Cafeteria App --- ");
        System.out.println("""
        Please select an option:
        1: View Menu & start an Order
        """);

        while(true) {
            try {
                functionSwitch = input.nextInt(); // Gets user input
                input.nextLine(); // Gets rid of buffered new line because of stupid scanner logic (Later input.nextLine would fail otherwise)
                break;
            } catch (InputMismatchException e) {
                input.next();
                System.out.println("Invalid input. Please try again.");
            }
        }

        switch (functionSwitch) {
            case 1:
                menuPrinter.printMenu(menuManager.getAllItems());
                Order currentOrder = orderManager.startNewOrder(testuser);
                orderPrinter.startOrder();

                boolean ordering = true;

                while (ordering) {

                    System.out.println("\nPlease state the number of the item you would like to order:\n");
                    String inputString = input.nextLine();

                    if  (inputString.equalsIgnoreCase("Cancel") || inputString.equalsIgnoreCase("0")) {
                        ordering = false;
                        orderPrinter.printOrderCancelled();
                    } else if (inputString.equalsIgnoreCase("Show")) { // If 'show' inputted, print current order.
                        orderPrinter.printOrder(currentOrder);
                    } else if (inputString.equalsIgnoreCase("Confirm")) { // If 'confirm' inputted, complete order and print message, break order loop.
                        orderPrinter.printOrder(currentOrder); // Print order prior to confirmation
                        boolean completed = orderManager.submitOrder(currentOrder); // Tries to submit, returns bool on whether successful
                        if  (completed) {
                            orderPrinter.printOrderCompleted();
                        } else {
                            orderPrinter.printOrderSubmitFailed();
                        }
                        ordering = false;
                    } else { // If input does not fit above, try parsing for an integer
                        try {
                            int inputItemNumber = Integer.parseInt(inputString);
                            if (menuManager.getMenuItem(inputItemNumber) != null) { // If the input matches a menu item, add item to order and print message
                                orderManager.itemToCurrentOrder(currentOrder, inputItemNumber);
                                orderPrinter.printItemAdded(true);
                            } else { // If the inputted number does not match a menu item, prints item invalid message
                                orderPrinter.printItemAdded(false);
                            }
                        } catch (NumberFormatException e) { // Catches error and prints item invalid message
                            orderPrinter.printItemAdded(false);
                        }
                    }

                }
                break;

                // Code for other functionality goes in its own case (E.g. create / delete account)
//            case 2:
//
//                break;

            default:
                System.out.println("Please select a valid option.");
        }
    }
}

