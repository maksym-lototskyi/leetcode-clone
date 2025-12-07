package org.example.domain.model.language;

import org.example.domain.validation.ValidationUtils;

public record FileExtension(String value) {
    public FileExtension {
        ValidationUtils.requireNonBlank(value, "File extension cannot be null or blank");
        ValidationUtils.requireCorrectStringRegex(value, "^\\.[a-zA-Z0-9]+$", "File extension must start with a dot followed by alphanumeric characters");
        ValidationUtils.requireMinimumValue(value.length(), 2, "File extension must have at least one character after the dot");
    }

    public static FileExtension of(String value) {
        return new FileExtension(value);
    }
}
