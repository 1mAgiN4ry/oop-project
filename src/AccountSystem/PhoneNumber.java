package AccountSystem;

public class PhoneNumber {
    private final String number;

    public PhoneNumber(String input) {
        if (!input.matches("^\\+?[0-9]{7,15}$")) throw new IllegalArgumentException("Invalid phone number");
        number = input;
    }

    public String getNumber() { return number; }
}
