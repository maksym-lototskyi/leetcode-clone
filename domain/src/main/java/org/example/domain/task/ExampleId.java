package org.example.domain.task;

import java.util.UUID;

public record ExampleId(UUID value) {
    public ExampleId {
        if (value == null) {
            throw new IllegalArgumentException("ExampleId value cannot be null");
        }
    }
    public static ExampleId generate() {
        return new ExampleId(UUID.randomUUID());
    }
}
