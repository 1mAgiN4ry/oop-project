package AccountSystem;

import DBSystem.UserRepo;

public class AccountService {
    private final UserRepo userRepo;

    public AccountService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void createAccount(Username username, Password password, Email email) {
        if (userRepo.verifyUsername(username)) throw new RuntimeException("Username already taken");
        userRepo.createCustomer(username, password, email);
    }

    public User login(Username username, String password) {
        if (!userRepo.verifyUsername(username)) throw new RuntimeException("Username not found");
        if (!userRepo.verifyPassword(username, password)) throw new RuntimeException("Incorrect password");
        return userRepo.getUserData(username);
    }

    public User updateUsername(User user, String input) {
        user.setUsername(input);
        userRepo.updateUsername(user);
        return user;
    }

    public User updatePassword(User user, String input) {
        user.setPassword(input);
        userRepo.updatePassword(user);
        return user;
    }

    public User updateEmail(User user, String input) {
        user.setEmail(input);
        userRepo.updateEmail(user);
        return user;
    }

    public User updatePhoneNumber(User user, String input) {
        user.setPhoneNumber(input);
        userRepo.updatePhoneNumber(user);
        return user;
    }

    public void deleteAccount(User user, String password) {
        if (!userRepo.verifyPassword(user.getUsername(), password)) throw new RuntimeException("Incorrect password");
        userRepo.deleteUser(user);
    }

    public void updateLoyaltyPoints(User user) {
        userRepo.updateLoyaltyPoints(user);
    }
}
