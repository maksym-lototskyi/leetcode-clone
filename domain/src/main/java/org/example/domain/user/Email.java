package org.example.domain.user;

public record Email (String value) {
    public Email {
        if (value == null || !value.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}
