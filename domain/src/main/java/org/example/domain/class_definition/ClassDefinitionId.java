package org.example.domain.class_definition;

import java.util.UUID;

public record ClassDefinitionId(UUID id) {
    public ClassDefinitionId {
        if (id == null) {
            throw new IllegalArgumentException("CustomClassDefinitionId cannot be null");
        }
    }
    public static ClassDefinitionId of(UUID id) {
        return new ClassDefinitionId(id);
    }
    public static ClassDefinitionId generate() {
        return new ClassDefinitionId(UUID.randomUUID());
    }
}
