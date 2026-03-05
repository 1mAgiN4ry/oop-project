package OrderingSystem;

import MenuSystem.*;
import UserSystem.User;
import java.util.ArrayList;

public class Order {
    // ----/ Class Variables /----
    private int orderID;
    private ArrayList<MenuItem> orderItems;
    private User orderUser;
    private Double orderTotalCost;
    private OrderStatus orderStatus;

    // ----/ Constructor /----
    public Order(Integer orderID, User orderUserRef) {
        this.orderID = orderID;
        this.orderUser = orderUserRef;
        orderTotalCost = 0.0;
        orderStatus = OrderStatus.EMPTY;
        orderItems = new ArrayList<MenuItem>();
    }

    // ----/ Methods /----
    public Integer getOrderID() {
        return orderID;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Object addOrderItem(MenuItem menuItem) {
    if (orderStatus == OrderStatus.IN_PROGRESS || orderStatus == OrderStatus.EMPTY) { // If the order is in progress, add item
        Object addedItemInfo;
        addedItemInfo = orderItems.add(menuItem); // Add the item to the order and save the item's info to return it
        if (orderStatus == OrderStatus.EMPTY) { orderStatus = OrderStatus.IN_PROGRESS; } // If it was empty, set to in progress now the item is added
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
        orderTotalCost = tempOrderTotal;
        return orderTotalCost;
    }

    public boolean finaliseOrder() {
        if  (orderStatus == OrderStatus.IN_PROGRESS) {
            // Send to database / log manager here?

            orderTotalCost = calculateOrderTotal(); // Calculate total
            orderStatus = OrderStatus.UNPAID; // Set order status
            return true;
        } else {
            return false;
        }

    }

}

