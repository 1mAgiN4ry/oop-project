package OrderingSystem;
import MenuSystem.*;
import UserSystem.User;

public class Order {
    private MenuItem[] orderItems;
    private MenuManager menuManager;
    private User orderUser;
    private Double orderTotalCost;

    public Order(MenuManager menuManagerRef, User orderUserRef) {
        this.menuManager = menuManagerRef;
        this.orderUser = orderUserRef;
        orderTotalCost = 0.0;
    }

    public void addOrderItem(Integer itemID, Integer quantity) {
        MenuItem addedItem;
        addedItem = menuManager.menuList.get(itemID); // Set the 'added item' by grabbing the menu list using the stated item ID
        orderTotalCost =+ addedItem.getPrice() * quantity; // Add cost of item * quantity

    }

    public Double calculateOrderTotal() {
        Double tempOrderTotal = 0.0;
        for (MenuItem item : orderItems) {
            tempOrderTotal += item.getPrice();
        }
        return tempOrderTotal;
    }

    public void completeOrder() {
        // Send to database / log manager
    }
}

