package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record ExampleId(UUID value) {
    public ExampleId {
        ValidationUtils.requireNonNull(value, "ExampleId value cannot be null");
    }
    public static ExampleId generate() {
        return new ExampleId(UUID.randomUUID());
    }

    public static ExampleId of(UUID id) {
        return new ExampleId(id);
    }
}
