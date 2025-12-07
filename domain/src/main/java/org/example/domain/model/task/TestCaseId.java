package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record TestCaseId(UUID value) {
    public TestCaseId {
        ValidationUtils.requireNonNull(value, "TestCaseId cannot be null");
    }

    public static TestCaseId generate() {
        return new TestCaseId(UUID.randomUUID());
    }

    public static TestCaseId of(UUID id) {
        return new TestCaseId(id);
    }
}
