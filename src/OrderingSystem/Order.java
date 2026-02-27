package OrderingSystem;

import MenuSystem.*;
import UserSystem.User;
import java.util.ArrayList;

public class Order {
    // ----/ Class Variables /----
    private int orderID;
    private ArrayList<MenuItem> orderItems;
    //private MenuManager menuManager;
    private User orderUser;
    private Double orderTotalCost;
    private OrderStatus orderStatus;

    // ----/ Constructor /----
    public Order(Integer orderID, MenuManager menuManagerRef, User orderUserRef) {
        this.orderID = orderID;
        this.orderUser = orderUserRef;
        orderTotalCost = 0.0;
        orderStatus = OrderStatus.IN_PROGRESS;
        orderItems = new ArrayList<MenuItem>();
    }

    // ----/ Methods /----
    public Integer getOrderID() {
        return orderID;
    }

    public Object addOrderItem(MenuItem menuItem) {
    if (orderStatus == OrderStatus.IN_PROGRESS) { // If the order is in progress, add item
        Object addedItemInfo;
        addedItemInfo = orderItems.add(menuItem);
        return addedItemInfo;
    } else {
        System.out.println("Order has already been added or cancelled."); // Not sure how this could be printed, but provides explanation in case it is
        return null;
    }

    }

    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
    }

    public Double calculateOrderTotal() {
        Double tempOrderTotal = 0.0;
        for (MenuItem item : orderItems) {
            tempOrderTotal += item.getPrice();
        }
        return tempOrderTotal;
    }

    public void showCurrentOrder()
    {
        for (MenuItem item : orderItems) {
            System.out.println(item.getName());
            System.out.println(item.getPrice());
        }
    }

    public void completeOrder() {
        // Send to database / log manager here?

        orderTotalCost = calculateOrderTotal(); // Calculate total
        orderStatus = OrderStatus.COMPLETED; // Set order status
    }
}

