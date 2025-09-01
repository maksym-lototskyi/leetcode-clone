package org.example.domain.user;

public record UserName(String value) {

    public UserName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("User name cannot be null or blank");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("User name cannot exceed 100 characters");
        }
    }
}
