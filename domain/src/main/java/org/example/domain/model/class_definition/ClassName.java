package org.example.domain.model.class_definition;

import org.example.domain.validation.ValidationUtils;

public record ClassName(String value) {
    public ClassName {
        ValidationUtils.requireNonBlank(value, "Class name cannot be null or blank");
        ValidationUtils.requireMaximumValue(value.length(), 50, "Class name cannot exceed 50 characters");
    }

    public static ClassName of(String name) {
        return new ClassName(name);
    }
}
