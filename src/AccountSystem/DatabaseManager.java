package AccountSystem;

import java.sql.*;
import java.util.Objects;

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
                    CustomerID INTEGER NOT NULL,
                    PurchaseTime TEXT NOT NULL DEFAULT (datetime('now')),
                    OrderStatus TEXT NOT NULL,
                    PaymentType TEXT NOT NULL,
                    FOREIGN KEY (CustomerID) REFERENCES Customers(UID)
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

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Admins (
                    UID INTEGER PRIMARY KEY AUTOINCREMENT,
                    Username TEXT NOT NULL UNIQUE,
                    Password TEXT NOT NULL
                );
            """);
        }
    }

    public void createCustomer(String username, String password, String email) {
        String sql = "INSERT INTO Customers (Username, Password, Email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (!keys.next()) throw new SQLException("Insert succeeded but no key was returned");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create user", e);
        }
    }

    public void createAdmin(String username, String password) {
        String sql = "INSERT INTO Admins (Username, Password) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (!keys.next()) throw new SQLException("Insert succeeded but no key was returned");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create admin", e);
        }
    }

    public boolean usernameExists(String username) {
        String sql = "SELECT 1 FROM Customers WHERE Username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check username: " + username, e);
        }
    }

    public boolean verifyUser(String username, String password, String role) {
        PasswordHasher ph = new PasswordHasher();
        String sql;
        if (Objects.equals(role, "customer")) {
            sql = "SELECT Password FROM Customers WHERE Username = ?";
        } else {
            sql = "SELECT Password FROM Admins WHERE Username = ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return ph.verify(password, rs.getString("Password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to verify customer: " + username, e);
        }
        return false;
    }

    public int getLoyaltyPoints(String username) {
        String sql = "SELECT LoyaltyPoints FROM Customers WHERE Username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("LoyaltyPoints");
                throw new RuntimeException("Customer not found: " + username);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get loyalty points for user: " + username, e);
        }
    }

    public void createProduct(String name, String type, int price, int stock, String description) {
        String sql = "INSERT INTO Products () VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setInt(3, price);
            ps.setInt(4, stock);
            ps.setString(5, description);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (!keys.next()) throw new SQLException("Insert succeeded but no key was returned");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create product: " + name, e);
        }
    }
}
