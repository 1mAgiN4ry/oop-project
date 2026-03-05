package MenuSystem;

import java.util.HashMap;
import java.util.HashSet;

public class MenuManager {
    // ----/ Class Variables /----
    private int itemID; // Incremented on each item added to menu
    private HashMap<Integer, MenuItem> menuList;

    // ----/ Constructor /----
    public MenuManager() {
        menuList = new HashMap<>();
        itemID = 1;

        // Add items to Menu
        addItem("Bruschetta",9.99, "The famous Italian appetiser", "Starter");
        addItem("Herbal Tea",15.99, "Mmm yum", "Drink");
        addItem("Pasta", 10.99, "Authentic Italian pasta", "Main");
        addItem("Coffee",15.99, "A rich Italian-blend that gives you a taste of Italy", "Drink");
        addItem("Salad",8.99, "Get your veggies in!", "Starter");

    }

    // ----/ Methods /----
    public MenuItem getMenuItem(int index) {
        return menuList.get(index);
    }

    public HashMap<Integer,MenuItem> getAllItems() {
        return menuList;
    }

    private void addItem(String pName, double pPrice, String pDescription, String pCategory) {
        MenuItem menuItem = new MenuItem(itemID, pName, pPrice, pDescription, pCategory);
        menuList.put(itemID, menuItem);
        itemID++;
    }

}
