import java.util.Scanner;
import MenuSystem.MenuManager;
import MenuSystem.MenuPrinter;
import OrderingSystem.OrderManager;
import OrderingSystem.Order;
import OrderingSystem.OrderPrinter;
import UserSystem.User;

void main(String[] args) {
    // Variables
    MenuManager menuManager = new MenuManager();
    OrderManager orderManager = new OrderManager(menuManager, 0);
    OrderPrinter orderPrinter = new OrderPrinter();
    MenuPrinter menuPrinter = new MenuPrinter();
    int functionSwitch;

    User exampleUser = new User(); // Temporary example user

    // Assume user logged in
    Scanner input = new Scanner(System.in); // Scanner for taking user input

    while (true) {
        System.out.println(" --- Welcome to the Gino's Cafeteria App --- ");
        System.out.println("""
        Please select an option:
        1: View Menu & start an Order
        """);
        functionSwitch = input.nextInt(); // Gets user input
        input.nextLine(); // Gets rid of buffered new line because of stupid scanner logic (Later input.nextLine would fail otherwise)

        switch (functionSwitch) {
            case 1:
                menuPrinter.printMenu(menuManager.getAllItems());
                Order currentOrder = orderManager.startNewOrder(exampleUser);
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
                        boolean completed = orderManager.submitOrder(currentOrder);
                        if  (completed) {
                            orderPrinter.printOrderCompleted();
                        } else {
                            orderPrinter.printOrderCancelled();
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

