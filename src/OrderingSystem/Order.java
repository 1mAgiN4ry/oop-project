package OrderingSystem;
import MenuSystem.*;
import UserSystem.User;

public class Order {
    private int orderID;
    private MenuItem[] orderItems;
    private MenuManager menuManager;
    private User orderUser;
    private Double orderTotalCost;
    private orderStatusEnum orderStatus;
    private enum orderStatusEnum {
        IN_PROGRESS("Customer currently ordering"),
        UNPAID("Order submitted and unpaid"),
        COMPLETED("Order submitted and paid"),
        CANCELED("Order cancelled");

        private String description;

        orderStatusEnum(String description) { // Constructor for Enum
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public Order(Integer OrderID, MenuManager menuManagerRef, User orderUserRef) {
        this.orderID = OrderID;
        this.menuManager = menuManagerRef;
        this.orderUser = orderUserRef;
        orderTotalCost = 0.0;
        orderStatus = orderStatusEnum.IN_PROGRESS;
    }

    public void addOrderItem(Integer itemID) {
        MenuItem addedItem;
        addedItem = menuManager.menuList.get(itemID); // Set the 'added item' by grabbing the menu list using the stated item ID
        orderTotalCost =+ addedItem.getPrice(); // Add cost of item

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
        // Send to database / log manager
        orderStatus = orderStatusEnum.COMPLETED;
    }
}

