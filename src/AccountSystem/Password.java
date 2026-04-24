package AccountSystem;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class Password {
    //https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/argon2/Argon2PasswordEncoder.html
    // Using Spring's default parameters
    private final Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    private final String hash;

    public Password(String input, boolean fromDB) {
        if (!fromDB) {
            if (input.length() < 8) throw new IllegalArgumentException("Password is too short");
            if (!input.matches(".*[A-Z].*")) throw new IllegalArgumentException("Password needs uppercase");
            if (!input.matches(".*[a-z].*")) throw new IllegalArgumentException("Password needs lowercase");
            if (!input.matches(".*[0-9].*")) throw new IllegalArgumentException("Password needs number");
            if (!input.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) throw new IllegalArgumentException("Password needs special character");

            hash = hash(input);
        } else {
            hash = input;
        }
    }

    public String getHash() {
        return hash;
    }

    private String hash(String password) {
        return encoder.encode(password);
    }

    public boolean verify(String password, String hash) {
        return encoder.matches(password, hash);
    }
}
