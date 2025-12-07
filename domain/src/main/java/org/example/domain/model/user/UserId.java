package org.example.domain.model.user;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record UserId (UUID value) {
    public UserId{
        ValidationUtils.requireNonNull(value, "UserId value cannot be null");
    }
    public static UserId of(UUID value) {
        return new UserId(value);
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }
}
