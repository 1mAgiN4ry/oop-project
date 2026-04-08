package DBSystem;

import java.sql.*;

public class DatabaseManager {

    private final String url;
    private Connection connection;

    public DatabaseManager(String dbPath) {
        this.url = "jdbc:sqlite:" + dbPath;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(url);
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON;");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to disconnect from database", e);
        }
    }

    public void initSchema() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Customers (
                    UID INTEGER PRIMARY KEY AUTOINCREMENT,
                    Username TEXT NOT NULL UNIQUE,
                    Password TEXT NOT NULL,
                    LoyaltyPoints INTEGER NOT NULL DEFAULT 0,
                    Email TEXT NOT NULL,
                    PhoneNumber TEXT
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Products (
                    UID INTEGER PRIMARY KEY AUTOINCREMENT,
                    Name TEXT NOT NULL,
                    Type TEXT NOT NULL,
                    Price INTEGER NOT NULL,
                    Stock INTEGER NOT NULL DEFAULT 0,
                    Description TEXT
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Orders (
                    UID INTEGER PRIMARY KEY AUTOINCREMENT,
                    UserID INTEGER NOT NULL,
                    PurchaseTime TEXT NOT NULL DEFAULT (datetime('now')),
                    PaymentType TEXT NOT NULL,
                    OrderStatus TEXT NOT NULL,
                    FOREIGN KEY (UserID) REFERENCES User(UID)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS OrderItems (
                    UID INTEGER PRIMARY KEY AUTOINCREMENT,
                    OrderID INTEGER NOT NULL,
                    ProductID INTEGER NOT NULL,
                    Quantity INTEGER NOT NULL,
                    PriceAtPurchase INTEGER NOT NULL,
                    FOREIGN KEY (OrderID) REFERENCES Orders(UID),
                    FOREIGN KEY (ProductID) REFERENCES Products(UID)
                );
            """);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
