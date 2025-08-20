package org.example.domain.model.user;

public record Email (String value) {
    public Email {
        if (value == null || !value.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}
