package AccountSystem;

public class User {
    private final int uid;
    private Username username;
    private Password hash;
    private int loyaltyPoints;
    private Email email;
    private PhoneNumber phoneNumber;

    public User(int uid, Username username, Password hash, int loyaltyPoints, Email email, PhoneNumber phoneNumber) {
        this.uid = uid;
        this.username = username;
        this.hash = hash;
        this.loyaltyPoints = loyaltyPoints;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public int getUid() {
        return uid;
    }

    public Username getUsername() {
        return username;
    }
    
    public Password getPassword() {
        return hash;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    // Setters
    public void setUsername(String username) {
        this.username = new Username(username);
    }

    public void setPassword(String password) {
        this.hash = new Password(password, false);
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }
}
