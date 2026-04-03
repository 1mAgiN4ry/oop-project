package OrderingSystem;

import MenuSystem.MenuItem;

public class OrderPrinter {

    public void startOrder() {
        System.out.println("---- New Order ----");
        System.out.println("Type 'Cancel' or '0' to Cancel the Order");
    }

    public void printOrder(Order order) {
        System.out.println("---- Current Order ----");
        for (MenuItem item : order.getOrderItems()) {
            System.out.printf("%-15s £%.2f%n",
                    item.getName(),
                    item.getPrice());
        }

        System.out.println("Order Total: \n£" + order.calculateOrderTotal());
    }

    public void printItemAdded(boolean success) {
        if (success) {
            System.out.println("Item added!\nType 'Show' to show your current Order, or 'Confirm' to confirm your order.");
        } else {
            System.out.println("Invalid item number. Please try again.");
        }

    }

    public void printOrderCompleted() {
        System.out.println("Order confirmed!");
    }

    public void printOrderSubmitFailed() {
        System.out.println("Order failed to submit. Please try again.");
    }

    public void printOrderCancelled() {
        System.out.println("Order cancelled.");
    }


}
