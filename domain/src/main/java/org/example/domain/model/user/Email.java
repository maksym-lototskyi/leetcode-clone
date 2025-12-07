package org.example.domain.model.user;

import org.example.domain.validation.ValidationUtils;

public record Email (String value) {
    public Email {
        ValidationUtils.requireCorrectStringRegex(value, "Email", "^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$");
    }
}
