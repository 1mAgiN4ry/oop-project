package OrderingSystem;

import AccountSystem.User;
import MenuSystem.MenuManager;
import MenuSystem.MenuPrinter;

import java.util.Scanner;

public class OrderUI {
    private final MenuManager menuManager;
    private final MenuPrinter menuPrinter;
    private final OrderManager orderManager;
    private final OrderPrinter orderPrinter;
    private final User user;

    public OrderUI(MenuManager pMenuManager, MenuPrinter pMenuPrinter, OrderManager pOrderManager, OrderPrinter pOrderPrinter, User pUser) {
        menuManager = pMenuManager;
        menuPrinter = pMenuPrinter;
        orderManager = pOrderManager;
        orderPrinter = pOrderPrinter;
        user = pUser;
    }

    public void viewMenuAndStartOrder() {
        Scanner input = new Scanner(System.in);

        menuPrinter.printMenu(menuManager.getAllItems());
        Order currentOrder = orderManager.startNewOrder(user);
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
    }
}
