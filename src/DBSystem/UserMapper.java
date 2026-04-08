package DBSystem;

import AccountSystem.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public User createUser(ResultSet rs) throws SQLException {
        int uid = rs.getInt("UID");
        int loyaltyPoints = rs.getInt("LoyaltyPoints");

        Username username = new Username(rs.getString("Username"));
        Password password = new Password(rs.getString("Password"), true);
        Email email = new Email(rs.getString("Email"));

        PhoneNumber phoneNumber;
        if (rs.getString("PhoneNumber") != null) {
            phoneNumber = new PhoneNumber(rs.getString("PhoneNumber"));
        } else {
            phoneNumber = null;
        }

        return new User(uid, username, password, loyaltyPoints, email, phoneNumber);
    }
}
