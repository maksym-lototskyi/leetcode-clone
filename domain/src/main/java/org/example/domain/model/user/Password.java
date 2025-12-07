package org.example.domain.model.user;

import lombok.Getter;
import org.example.domain.validation.ValidationUtils;

@Getter
public record Password(String hashed) {
    public Password {
        ValidationUtils.requireNonBlank(hashed, "Hashed password cannot be blank");
    }

    public static Password fromRaw(String rawPassword, PasswordHasher hasher) {
        return new Password(hasher.hash(rawPassword));
    }
}
