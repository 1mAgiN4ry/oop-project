package OrderingSystem;

public enum OrderStatus {
    IN_PROGRESS("Customer currently ordering"),
    UNPAID("Order submitted and unpaid"),
    COMPLETED("Order submitted and paid");

    private String description;

    OrderStatus(String description) { // Constructor for Enum
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
