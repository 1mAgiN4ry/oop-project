import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;

public class MenuManager {
    private Map<Integer, MenuItem> menuList;
    public HashSet<String> itemCategories;

    public MenuManager() {
        menuList = new HashMap<>();

        // Add items to Menu
        addItem("Bruschetta",9.99, "The famous Italian appetiser", "Starter");
        addItem("Tea",15.99, "Mmm yum", "Drink");
        addItem("Pasta", 10.99, "Authentic Italian pasta", "Main");
        addItem("Coffee",15.99, "A rich Italian-blend that gives you a taste of Italy", "Drink");
        addItem("Salad",8.99, "Get your veggies in!", "Starter");


        // Call function to calculate unique categories - retrieved at instantiation as oppose to showMenu() call for efficiency
        itemCategories = calcItemCategories();
    }

    private HashSet<String> calcItemCategories() {
        HashSet<String> tempCategories = new HashSet<>();
        for (MenuItem menuItem : menuList.values()) { // Iterates through list of menu items, adds unique categories to a list using HashSet
            tempCategories.add(menuItem.getCategory());
        }
        return tempCategories;
    }

    private void addItem(String pName, double pPrice, String pDescription, String pCategory) {
        MenuItem menuItem = new MenuItem(pName, pPrice, pDescription, pCategory);
        menuList.put(menuList.size(), menuItem);
    }

    public void showMenu() {
        String[] tempCategoriesArray = itemCategories.toArray(new String[itemCategories.size()]); // Used as a reference to match item categories against
        System.out.print("------- Gino's Menu -------\n");

        // Code for sorting menu items into their categories
        for ( int i = 0; i < itemCategories.size(); i++ ) { // Increment counter on each iteration through menu items
            System.out.print(" -- " + tempCategoriesArray[i] + " -- \n");
            for (MenuItem menuItem : menuList.values()) { // Iterate through menu items
                if (menuItem.getCategory() == tempCategoriesArray[i]) { // If item's category = current category iteration loop...
                    System.out.println(menuItem.getName());
                    System.out.println("£" + menuItem.getPrice() + " | " +  menuItem.getDescription() + "\n");
                }
            }
        }

    }


}

