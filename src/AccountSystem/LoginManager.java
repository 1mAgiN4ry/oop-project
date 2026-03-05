package AccountSystem;

import java.io.IOException;
import java.util.Scanner;

public class LoginManager {
    private final DatabaseManager dbm;
    public LoginManager(DatabaseManager dbm) {
        this.dbm = dbm;
    }
    // Source - https://stackoverflow.com/a/38365871
    // Posted by Abhishek Kashyap, modified by community. See post 'Timeline' for change history
    // Retrieved 2026-02-26, License - CC BY-SA 3.0
    private static void clearScreen(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loginScreen() {
        String title = "Login Screen";
        String div = "------------------------------";
        String[] options = new String[]{
                "Login as Customer",
                "Login as Admin",
                "Create Account",
                "Exit"
        };
        String prompt = "Enter choice: ";
        String attemptMsg = "Invalid option, try again";

        Scanner sc = new Scanner(System.in);
        int choice = -1;
        boolean first_attempt = true;

        do {
            clearScreen();

            // Print UI
            System.out.println(title);
            System.out.println(div);
            for (int i = 0; i < options.length; i++) {
                System.out.printf("[%d] %s\n", i, options[i]);
            }
            System.out.println(div);
            if (!first_attempt) {
                System.out.println(attemptMsg);
            }
            System.out.print(prompt);

            // Listen for and parse input
            String input = sc.nextLine().trim();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            first_attempt = false;

        } while (choice >= options.length || choice <= -1);

        switch (choice) {
            case 0:
                CustomerLogin();
                break;
            case 1:
                AdminLogin();
                break;
            case 2:
                CreateAccount();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    private void CustomerLogin() {
        String title = "Customer Login";
        String div = "------------------------------";
        Scanner sc = new Scanner(System.in);
        String role = "customer";
        boolean first_attempt = true;
        boolean authenticated = false;

        dbm.connect();

        do {
            clearScreen();

            System.out.println(title);
            System.out.println(div);

            if (!first_attempt) {
                System.out.println("Invalid username or password, try again.");
            }
            System.out.print("Enter Username: ");
            String username = sc.nextLine().trim();

            System.out.print("Enter Password: ");
            String password = sc.nextLine().trim();

            first_attempt = false;
            authenticated = dbm.verifyUser(username, password, role);
        } while (!authenticated);

        dbm.disconnect();
    }

    private void AdminLogin() {
        String title = "Admin Login";
        String div = "------------------------------";
        Scanner sc = new Scanner(System.in);
        String role = "admin";
        boolean first_attempt = true;
        boolean authenticated;

        dbm.connect();

        do {
            clearScreen();

            System.out.println(title);
            System.out.println(div);

            if (!first_attempt) {
                System.out.println("Invalid username or password, try again.");
            }
            System.out.print("Enter Username: ");
            String username = sc.nextLine().trim();

            System.out.print("Enter Password: ");
            String password = sc.nextLine().trim();

            first_attempt = false;
            authenticated = dbm.verifyUser(username, password, role);
        } while (!authenticated);

        dbm.disconnect();
    }

    private void CreateAccount() {
        String title = "Create Account";
        String div = "------------------------------";
        Scanner sc = new Scanner(System.in);
        PasswordHasher ph = new PasswordHasher();
        boolean progress = true;

        dbm.connect();

        do {
            clearScreen();

            System.out.println(title);
            System.out.println(div);
            String username = "";
            String password = "";
            String confirmPassword = "";
            String email = "";

            progress = true;

            System.out.print("Enter Username: ");
            username = sc.nextLine().trim();

            if (dbm.usernameExists(username)) {
                System.out.println("Username already in use, please choose another.");
                progress = false;
            }

            if (progress) {
                System.out.print("Enter Password: ");
                password = sc.nextLine().trim();
            }

            if (!passwordChecker(password)) {
                System.out.println("Password must be at least 8 characters, contain upper and lowercase letters, a number, and a special character.");
                progress = false;
            }

            if (progress) {
                System.out.print("Confirm Password: ");
                confirmPassword = sc.nextLine().trim();
            }

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match.");
                progress = false;
            }

            if (progress) {
                System.out.print("Enter Email: ");
                email = sc.nextLine().trim();
            }

            // RFC 5322 compliant email regex because why not
            if (!email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                System.out.println("Email is invalid.");
                progress = false;
            }

            if (progress) {
                dbm.createCustomer(username, ph.hash(password), email);
            }

        } while (!progress);

        dbm.disconnect();
    }

    private boolean passwordChecker(String password) {
        if (password.length() < 8) return false;
        if (!password.matches(".*[A-Z].*")) return false;
        if (!password.matches(".*[a-z].*")) return false;
        if (!password.matches(".*[0-9].*")) return false;
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) return false;
        return true;
    }
}
