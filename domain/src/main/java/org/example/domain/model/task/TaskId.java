package org.example.domain.model.task;

import java.util.UUID;

public record TaskId (UUID value) {

    public static TaskId generate() {
        return new TaskId(UUID.randomUUID());
    }

    public TaskId {
        if (value == null) {
            throw new IllegalArgumentException("TaskId cannot be null");
        }
    }
}
