public class MenuItem {
    private String name;
    private double price;
    private String description;
    private String category;

    public MenuItem(String name, double price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public double getPrice() { return price; }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() { return category; }
}

