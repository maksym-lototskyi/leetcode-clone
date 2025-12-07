package org.example.domain.model.submission;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record TestRunId(UUID value) {
    public TestRunId {
        ValidationUtils.requireNonNull(value, "TestRunId value cannot be null");
    }

    public static TestRunId generate() {
        return new TestRunId(UUID.randomUUID());
    }
}
