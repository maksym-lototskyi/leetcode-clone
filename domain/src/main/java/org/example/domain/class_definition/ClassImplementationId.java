package org.example.domain.class_definition;

import java.util.UUID;

public record ClassImplementationId(UUID value) {
    public ClassImplementationId {
        if (value == null) {
            throw new IllegalArgumentException("ClassImplementationId value cannot be null");
        }
    }

    public static ClassImplementationId of(UUID uuid) {
        return new ClassImplementationId(uuid);
    }
    public static ClassImplementationId generate() {
        return new ClassImplementationId(UUID.randomUUID());
    }
}
