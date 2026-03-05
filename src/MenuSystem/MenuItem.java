package MenuSystem;

public class MenuItem {
    private final int id;
    private final String name;
    private final double price;
    private final String description;
    private final String category;

    public MenuItem(int id, String name, double price, String description, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
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

