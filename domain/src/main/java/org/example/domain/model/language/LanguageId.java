package org.example.domain.model.language;

import java.util.UUID;

public record LanguageId(UUID value) {
    public static LanguageId generate() {
        return new LanguageId(UUID.randomUUID());
    }
    public LanguageId{
        if (value == null) {
            throw new IllegalArgumentException("LanguageId cannot be null");
        }
    }

    public static LanguageId of(UUID id) {
        return new LanguageId(id);
    }
}
