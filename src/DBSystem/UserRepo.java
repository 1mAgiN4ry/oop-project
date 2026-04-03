package DBSystem;

import AccountSystem.*;

import java.sql.*;

public class UserRepo {
    private final Connection connection;
    private final UserMapper mapper = new UserMapper();

    public UserRepo(DatabaseManager db) {
        connection = db.getConnection();
    }

    // Create
    public void createCustomer(Username username, Password password, Email email) {
        String sql = "INSERT INTO Customers (Username, Password, Email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username.getName());
            ps.setString(2, password.getHash());
            ps.setString(3, email.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create user", e);
        }
    }

    // Read
    public boolean verifyUsername(Username username) {
        String sql = "SELECT 1 FROM Customers WHERE Username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username.getName());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check username", e);
        }
    }

    public boolean verifyPassword(Username username, Password password) {
        String sql = "SELECT Password FROM Customers WHERE Username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username.getName());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return password.verify(rs.getString("Password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to verify password", e);
        }
        return false;
    }

    public User getUserData(Username username) {
        String sql = "SELECT * FROM Customers WHERE Username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username.getName());
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return mapper.createUser(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get user data", e);
        }
    }

    // Update
    public void updateUsername(User user) {
        if (verifyUsername(user.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        String sql = "UPDATE Customers SET Username = ? WHERE UID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername().getName());
            ps.setInt(2, user.getUid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update username", e);
        }
    }

    public void updatePassword(User user) {
        String sql = "UPDATE Customers SET Password = ? WHERE UID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getPassword().getHash());
            ps.setInt(2, user.getUid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update password", e);
        }
    }

    public void updateLoyaltyPoints(User user) {
        String sql = "UPDATE Customers SET LoyaltyPoints = ? WHERE UID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, user.getLoyaltyPoints());
            ps.setInt(2, user.getUid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update loyalty points", e);
        }
    }

    public void updateEmail(User user) {
        String sql = "UPDATE Customers SET Email = ? WHERE UID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getEmail().getEmail());
            ps.setInt(2, user.getUid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update email", e);
        }
    }

    public void updatePhoneNumber(User user) {
        String sql = "UPDATE Customers SET PhoneNumber = ? WHERE UID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getPhoneNumber().getNumber());
            ps.setInt(2, user.getUid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update phone number", e);
        }
    }

    // Delete
    public void deleteUser(User user) {
        String sql = "DELETE FROM Customers WHERE UID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, user.getUid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete user", e);
        }
    }
}
