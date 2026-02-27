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
                } else if (inputString.equalsIgnoreCase("Show")) {
                    orderPrinter.printOrder(currentOrder);
                } else {
                    try {
                        int inputItemNumber = Integer.parseInt(inputString);
                        if (menuManager.getMenuItem(inputItemNumber) != null) {
                            orderManager.itemToCurrentOrder(currentOrder, inputItemNumber);
                            orderPrinter.printItemAdded(true);
                        } else {
                            orderPrinter.printItemAdded(false);
                        }
                    } catch (NumberFormatException e) {
                        orderPrinter.printItemAdded(false);
                    }
                }

            }
            break;

        case 2:

    }
}

