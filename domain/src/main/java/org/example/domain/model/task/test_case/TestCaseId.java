package org.example.domain.model.task.test_case;

import java.util.UUID;

public record TestCaseId(UUID value) {
    public TestCaseId {
        if (value == null) {
            throw new IllegalArgumentException("TestCaseId value cannot be null");
        }
    }

    public static TestCaseId generate() {
        return new TestCaseId(UUID.randomUUID());
    }
}
