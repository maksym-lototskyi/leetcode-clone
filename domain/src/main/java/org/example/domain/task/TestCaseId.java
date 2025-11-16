package org.example.domain.task;

import java.util.UUID;

public record TestCaseId(UUID value) {
    public TestCaseId {
        if (value == null) {
            throw new IllegalArgumentException("TestCaseId name cannot be null");
        }
    }

    public static TestCaseId generate() {
        return new TestCaseId(UUID.randomUUID());
    }

    public static TestCaseId of(UUID id) {
        return new TestCaseId(id);
    }
}
