package OrderingSystem;

import MenuSystem.MenuManager;
import UserSystem.User;
import java.util.HashMap;

public class OrderManager {
    // ----/ Class Variables /----
    private HashMap<Integer,Order> orders;
    private Integer incrementedOrderID;
    private MenuManager menuManager;

    // ----/ Constructor /----
    public OrderManager(MenuManager menuManagerRef, Integer previousOrderID) {
        orders = new HashMap<>();
        this.menuManager = menuManagerRef;
        incrementedOrderID = previousOrderID; // To avoid assigning a flat 0 value at the start, allows it to be pulled from database
                                                // (E.g. in event of a system restart, it doesn't overwrite)
    }

    // ----/ Methods /----
    public void addOrder(Order pOrder) { // Adds order to array
        int orderID = pOrder.getOrderID();
        orders.put(orderID, pOrder);
    }

    public Order startNewOrder(User pOrderUser) // Starts a new order
    {
        Order order = new Order(incrementedOrderID, menuManager, pOrderUser);
        orders.put(incrementedOrderID, order);
        incrementedOrderID++;
        return order;
    }

    public void completeOrder(Order pCompletedOrder) // Finishes an order
    {
        pCompletedOrder.completeOrder(); // Calls function to set order status to completed
        orders.put(incrementedOrderID, pCompletedOrder);
        incrementedOrderID++;
    }

    public void itemToCurrentOrder(Order order, int itemId) {
        order.addOrderItem(menuManager.getMenuItem(itemId));
    }
}
