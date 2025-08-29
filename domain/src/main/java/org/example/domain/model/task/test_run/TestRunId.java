package org.example.domain.model.task.test_run;

import java.util.UUID;

public record TestRunId(UUID value) {
    public TestRunId {
        if (value == null) {
            throw new IllegalArgumentException("TestRunId value cannot be null");
        }
    }

    public static TestRunId generate() {
        return new TestRunId(UUID.randomUUID());
    }
}
