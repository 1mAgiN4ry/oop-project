package MenuSystem;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private int id;
    private String name;
    private double price;
    private String description;
    private String category;
    private List<String> validCategories = new ArrayList<>();

    public MenuItem(int id, String name, double price, String description, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;

        // Add valid categories for menu items to be in
        validCategories.add("Food");
        validCategories.add("Drink");

    }

    public int getId() { return id; }
    public double getPrice() { return price; }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getCategory() { return category; }

    // For testing contents of the menu hashmap
    @Override
    public String toString() {
        return "ID:" + id + " Name:" + name + " Price:£" + price + " Description:" + description + " Category:" + category;
    }
}

