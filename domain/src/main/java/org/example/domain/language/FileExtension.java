package org.example.domain.language;

public record FileExtension(String value) {
    public FileExtension {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("File extension cannot be null or blank");
        }
        if (!value.startsWith(".")) {
            throw new IllegalArgumentException("File extension must start with a dot");
        }

        if(value.length() < 2) {
            throw new IllegalArgumentException("File extension must have at least one character after the dot");
        }
        if(value.contains(" ")) {
            throw new IllegalArgumentException("File extension cannot contain spaces");
        }
    }

    public static FileExtension of(String value) {
        return new FileExtension(value);
    }


}
