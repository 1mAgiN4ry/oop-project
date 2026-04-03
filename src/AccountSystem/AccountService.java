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

    public User login(Username username, Password password) {
        if (!userRepo.verifyUsername(username)) throw new RuntimeException("Username not found");
        if (!userRepo.verifyPassword(username, password)) throw new RuntimeException("Incorrect password");
        return userRepo.getUserData(username);
    }
}