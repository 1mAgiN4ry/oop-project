package MenuSystem;

import java.util.HashSet;
import java.util.HashMap;

public class MenuPrinter {

    public void printMenu(HashMap<Integer,MenuItem> items) {
        HashSet<String> itemCategories = calcItemCategories(items); // Call function to calculate unique categories
        String[] tempCategoriesArray = itemCategories.toArray(new String[itemCategories.size()]); // Used as a reference to match item categories against

        System.out.print("------- Gino's Menu -------\n");
        // Code for sorting menu items into their categories
        for (int i = 0; i < itemCategories.size(); i++ ) { // Increment counter on each iteration through menu items
            System.out.print(" -- " + tempCategoriesArray[i] + " -- \n");
            for (MenuItem menuItem : items.values()) { // Iterate through menu items
                if (menuItem.getCategory().equals(tempCategoriesArray[i])) { // If item's category = current category iteration loop...
                    System.out.printf("%-10s %-15s £%-9.2f %-55s%n", // Defines size of 'columns' | NOTE: If too many characters are in a column, this needs editing.
                            menuItem.getId(),
                            menuItem.getName(), // Calls functions to display information in console
                            menuItem.getPrice(),
                            menuItem.getDescription());
                }
            }
        }
    }

    private HashSet<String> calcItemCategories(HashMap<Integer,MenuItem> items) {
        HashSet<String> tempCategories = new HashSet<>();
        for (MenuItem menuItem : items.values()) { // Iterates through list of menu items, adds unique categories to a list using HashSet to only store unique values
            tempCategories.add(menuItem.getCategory());
        }
        return tempCategories;
    }

}
