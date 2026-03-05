package OrderingSystem;

import MenuSystem.MenuManager;
import UserSystem.User;
import java.util.HashMap;

public class OrderManager {
    // ----/ Class Variables /----
    private HashMap<Integer,Order> orders;
    private Integer incrementedOrderID;
    private final MenuManager menuManager;

    // ----/ Constructor /----
    public OrderManager(MenuManager menuManagerRef, Integer previousOrderID) {
        orders = new HashMap<>();
        this.menuManager = menuManagerRef;
        incrementedOrderID = previousOrderID; // To avoid assigning a flat 0 value at the start, allows it to be pulled from database
                                                // (E.g. in event of a system restart, it doesn't overwrite)
    }

    // ----/ Methods /----
    public Order startNewOrder(User pOrderUser) // Starts a new order
    {
        Order order = new Order(incrementedOrderID, pOrderUser);
        orders.put(incrementedOrderID, order);
        incrementedOrderID++;
        return order;
    }

    public boolean submitOrder(Order pCompletedOrder) // Finishes an order
    {
        return pCompletedOrder.finaliseOrder(); // Calls function to set order status to 'unpaid', updates order total, and returns if it's successful or not
    }

    public void itemToCurrentOrder(Order order, int itemId) {
        order.addOrderItem(menuManager.getMenuItem(itemId));
    }
}
