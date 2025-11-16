package org.example.domain.task;

import java.util.UUID;

public record ExampleId(UUID value) {
    public ExampleId {
        if (value == null) {
            throw new IllegalArgumentException("ExampleId name cannot be null");
        }
    }
    public static ExampleId generate() {
        return new ExampleId(UUID.randomUUID());
    }

    public static ExampleId of(UUID id) {
        return new ExampleId(id);
    }
}
