package org.example.domain.task;

import java.util.UUID;

public record TaskId (UUID value) {

    public static TaskId generate() {
        return new TaskId(UUID.randomUUID());
    }
    public static TaskId of(UUID value) {
        return new TaskId(value);
    }

    public TaskId {
        if (value == null) {
            throw new IllegalArgumentException("TaskId cannot be null");
        }
    }
}
