package org.example.domain.model.class_definition;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record ClassDefinitionId(UUID id) {
    public ClassDefinitionId {
        ValidationUtils.requireNonNull(id, "ClassDefinitionId value cannot be null");
    }
    public static ClassDefinitionId of(UUID id) {
        return new ClassDefinitionId(id);
    }
    public static ClassDefinitionId generate() {
        return new ClassDefinitionId(UUID.randomUUID());
    }
}
