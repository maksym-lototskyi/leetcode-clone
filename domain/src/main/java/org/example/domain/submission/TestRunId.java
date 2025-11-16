package org.example.domain.submission;

import java.util.UUID;

public record TestRunId(UUID value) {
    public TestRunId {
        if (value == null) {
            throw new IllegalArgumentException("TestRunId name cannot be null");
        }
    }

    public static TestRunId generate() {
        return new TestRunId(UUID.randomUUID());
    }
}
