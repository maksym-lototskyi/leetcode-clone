package org.example.domain.model.user;

import org.example.domain.validation.ValidationUtils;

public record UserName(String value) {

    public UserName {
        ValidationUtils.requireNonBlank(value, "User name cannot be blank");
        ValidationUtils.requireMaximumValue(value.length(), 50, "User name cannot exceed 50 characters");
    }
}
