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

        System.out.println("    Order Total:\n£" + order.calculateOrderTotal());
    }

    public void printItemAdded(boolean success) {
        if (success) {
            System.out.println("Item added! - Type 'Show' to show your current Order");
        } else {
            System.out.println("Invalid item number. Please try again.");
        }

    }

    public void printOrderCancelled() {
        System.out.println("Order cancelled.");
    }


}
