package org.example.domain.model.class_definition;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record ClassImplementationId(UUID value) {
    public ClassImplementationId {
        ValidationUtils.requireNonNull(value, "ClassImplementationId value cannot be null");
    }

    public static ClassImplementationId of(UUID uuid) {
        return new ClassImplementationId(uuid);
    }
    public static ClassImplementationId generate() {
        return new ClassImplementationId(UUID.randomUUID());
    }
}
