package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record TaskId (UUID value) {

    public static TaskId generate() {
        return new TaskId(UUID.randomUUID());
    }
    public static TaskId of(UUID value) {
        return new TaskId(value);
    }

    public TaskId {
        ValidationUtils.requireNonNull(value, "TaskId cannot be null");
    }
}
