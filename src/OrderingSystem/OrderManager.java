package OrderingSystem;
import MenuSystem.MenuManager;
import UserSystem.User;

import java.util.HashMap;

public class OrderManager {
    private HashMap<Integer,Order> orders;
    private Integer incrementedOrderID;
    private MenuManager menuManager;

    public OrderManager(MenuManager menuManagerRef, Integer previousOrderID) {
        this.menuManager = menuManagerRef;
        incrementedOrderID = previousOrderID; // To avoid assigning a flat 0 value at the start, allows it to be pulled from database
                                                // (E.g. in event of a system restart, it doesn't overwrite)

    }

    public HashMap<Integer,Order> getOrders() { // Gets completed orders from array

        return orders;
    }

    public void addOrders(Order[] orders) { // Adds completed order to array

    }

    public void startNewOrder(User pOrderUser) // Starts a new order
    {
        Order currentOrder = new Order(incrementedOrderID, menuManager, pOrderUser);
        System.out.println("Please state the number of the item you would like to order:\n");

    }

    public void completeOrder(Order pCompletedOrder) // Finishes an order
    {
        pCompletedOrder.completeOrder(); // Calls function to set order status to completed
        orders.put(incrementedOrderID, pCompletedOrder);
        incrementedOrderID++;
    }

}
