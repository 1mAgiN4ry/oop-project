package AccountSystem;

import java.util.ArrayList;
import java.util.List;

public class AccountUI {
    private final AccountService service;
    private final AccountPrinter printer;

    public AccountUI(AccountService service, AccountPrinter printer) {
        this.service = service;
        this.printer = printer;
    }

    public int mainMenu() {
        List<String> options = List.of("Create Account", "Login", "Exit");
        int choice = -1;

        printer.displayMenu("Account Menu", options);
        do {
            try {
                choice = Integer.parseInt(printer.getInput("Enter your choice: "));
            } catch (NumberFormatException e) {
                printer.displayMessage("Invalid input, try again");
                printer.displaySeparator();
            }
        } while (choice < 0 || choice >= options.size());

        return choice;
    }

    public void createAccount() {
        List<String> errors = new ArrayList<>();

        Username username = null;
        Password password = null;
        Email email = null;

        do {
            errors.clear();
            printer.displayTitle("Create Account");

            try {
                username = new Username(printer.getInput("Enter username: "));
            } catch (IllegalArgumentException e) { errors.add(e.getMessage()); }

            try {
                password = new Password(printer.getInput("Enter password: "), false);
            } catch (IllegalArgumentException e) { errors.add(e.getMessage()); }

            try {
                email = new Email(printer.getInput("Enter email: "));
            } catch (IllegalArgumentException e) { errors.add(e.getMessage()); }

            try {
                if (errors.isEmpty()) service.createAccount(username, password, email);
            } catch (RuntimeException e) { errors.add(e.getMessage()); }

            printer.displaySeparator();
            printer.displayErrors(errors);

        } while (!errors.isEmpty());

        printer.displayMessage("Account created successfully");
    }

    public User login() {
        List<String> errors = new ArrayList<>();

        Username username = null;
        String password = "";
        User user = null;

        do {
            errors.clear();
            printer.displayTitle("Login");

            try {
                username = new Username(printer.getInput("Enter username: "));
            } catch (IllegalArgumentException e) { errors.add(e.getMessage()); }

            try {
                password = printer.getInput("Enter password: ");
                // Validate
                new Password(password, false);
            } catch (IllegalArgumentException e) { errors.add(e.getMessage()); }

            try {
                if (errors.isEmpty()) user = service.login(username, password);
            } catch (RuntimeException e) { errors.add(e.getMessage()); }

            printer.displaySeparator();
            printer.displayErrors(errors);

        } while (!errors.isEmpty());

        return user;
    }

    public void displayUserData(User user) {
        printer.displayTitle(user.getUsername().getName() + "'s Data");

        printer.displayMessage("Loyalty Points: " + user.getLoyaltyPoints());
        printer.displayMessage("Email: " + user.getEmail().getEmail());
        if (user.getPhoneNumber().getNumber() != null) {
            printer.displayMessage("Phone Number: " + user.getPhoneNumber().getNumber());
        } else {
            printer.displayMessage("No phone number has been added");
        }
    }

    public User updateUserData(User user) {
        List<String> options = List.of(
                "Update Username",
                "Update Password",
                "Update Email",
                "Update Phone Number"
        );

        printer.displayTitle("Change user data");

        int choice = -1;
        do {
            try {
                choice = Integer.parseInt(printer.getInput("Enter your choice: "));
            } catch (NumberFormatException e) {
                printer.displayMessage("Invalid input, try again");
                printer.displaySeparator();
            }
        } while (choice < 0 || choice >= options.size());

        List<String> errors = new ArrayList<>();
        do {
            errors.clear();
            try {
                switch (choice) {
                    case 0 -> user = service.updateUsername(user, printer.getInput("Enter new username: "));
                    case 1 -> user = service.updatePassword(user, printer.getInput("Enter new password: "));
                    case 2 -> user = service.updateEmail(user, printer.getInput("Enter new email: "));
                    case 3 -> user = service.updatePhoneNumber(user, printer.getInput("Enter new phone number: "));
                }
            } catch (RuntimeException e) {
                errors.add(e.getMessage());
                printer.displayErrors(errors);
            }
        } while (!errors.isEmpty());

        printer.displayMessage("Updated successfully");
        return user;
    }

    public void deleteAccount(User user) {
        List<String> errors = new ArrayList<>();

        String password = "";

        do {
            errors.clear();
            printer.displayTitle("Delete Account");

            try {
                password = printer.getInput("Enter password: ");
                // Validate
                new Password(password, false);
            } catch (IllegalArgumentException e) { errors.add(e.getMessage()); }

            try {
                if (errors.isEmpty()) service.deleteAccount(user, password);
            } catch (RuntimeException e) { errors.add(e.getMessage()); }

            printer.displaySeparator();
            printer.displayErrors(errors);

        } while (!errors.isEmpty());
    }
}
