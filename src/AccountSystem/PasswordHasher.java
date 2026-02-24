package AccountSystem;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class PasswordHasher {
    //https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/argon2/Argon2PasswordEncoder.html
    // Using Spring's default parameters
    private final Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    public String hash(String password) {
        return encoder.encode(password);
    }

    public boolean verify(String password, String hashedPassword) {
        return encoder.matches(password, hashedPassword);
    }
}
