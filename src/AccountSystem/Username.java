package AccountSystem;

public class Username {
    private final String name;

    public Username(String input) {
        if (input.length() < 3) throw new IllegalArgumentException("Username too short");
        if (input.length() > 32) throw new IllegalArgumentException("Username too long");
        if (!input.matches("^[a-zA-Z0-9_-]+$")) throw new IllegalArgumentException("Username can only contain letters, numbers, underscores and dashes");

        name = input;
    }

    public String getName() {
        return name;
    }
}
