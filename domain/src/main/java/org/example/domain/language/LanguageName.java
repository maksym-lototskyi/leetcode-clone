package org.example.domain.language;

public record LanguageName(String value) {
    public LanguageName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Language name cannot be null or blank");
        }

        if(value.length() > 50) {
            throw new IllegalArgumentException("Language name cannot exceed 50 characters");
        }
    }

    public static LanguageName of(String value) {
        return new LanguageName(value);
    }
}
