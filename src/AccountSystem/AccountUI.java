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

    public void mainMenu() {
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

        switch (choice) {
            case 0 -> createAccount();
            case 1 -> login();
            case 2 -> {} // caller handles exit
        }
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
        Password password = null;
        User user = null;

        do {
            errors.clear();
            printer.displayTitle("Login");

            try {
                username = new Username(printer.getInput("Enter username: "));
            } catch (IllegalArgumentException e) { errors.add(e.getMessage()); }

            try {
                password = new Password(printer.getInput("Enter password: "), false);
            } catch (IllegalArgumentException e) { errors.add(e.getMessage()); }

            try {
                if (errors.isEmpty()) user = service.login(username, password);
            } catch (RuntimeException e) { errors.add(e.getMessage()); }

            printer.displaySeparator();
            printer.displayErrors(errors);

        } while (!errors.isEmpty());

        return user;
    }
}
