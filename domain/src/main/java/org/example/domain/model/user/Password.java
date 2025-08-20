package org.example.domain.model.user;

import lombok.Getter;
import org.example.domain.service.PasswordHasher;

@Getter
public final class Password{
    private final String hashed;
    private Password(String hashed) {
        if (hashed == null || hashed.isBlank()) {
            throw new IllegalArgumentException("Hashed password cannot be null or blank");
        }

        if (hashed.length() < 8) {
            throw new IllegalArgumentException("Hashed password must be at least 8 characters long");
        }
        this.hashed = hashed;
    }
    public static Password fromRaw(String rawPassword, PasswordHasher hasher) {
        return new Password(hasher.hash(rawPassword));
    }
}
