package org.example.domain.model.language;

import org.example.domain.validation.ValidationUtils;

public record LanguageName(String value) {
    public LanguageName {
        ValidationUtils.requireNonBlank(value, "Language name cannot be null or blank");
        ValidationUtils.requireMaximumValue(value.length(), 50, "Language name cannot exceed 50 characters");
    }

    public static LanguageName of(String value) {
        return new LanguageName(value);
    }
}
