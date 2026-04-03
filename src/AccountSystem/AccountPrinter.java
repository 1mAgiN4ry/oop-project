package AccountSystem;

import java.util.List;
import java.util.Scanner;

public class AccountPrinter {
    private final Scanner scanner = new Scanner(System.in);
    private final String separator = "------------------------------";

    public void displayTitle(String title) {
        clearScreen();
        System.out.println(title);
        displaySeparator();
    }

    public void displayMenu(String title, List<String> options) {
        displayTitle(title);
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d] %s%n", i, options.get(i));
        }
        displaySeparator();
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayErrors(List<String> errors) {
        errors.forEach(System.out::println);
    }

    public void displaySeparator() {
        System.out.println(separator);
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
    }
}
