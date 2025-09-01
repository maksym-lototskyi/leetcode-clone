package org.example.domain.task;

public record TaskDescription(String value) {
    public TaskDescription {
        if (value == null) {
            throw new IllegalArgumentException("Task description cannot be null");
        }
        if (value.length() > 1000) {
            throw new IllegalArgumentException("Task description cannot exceed 1000 characters");
        }
    }

    public static TaskDescription of(String value) {
        return new TaskDescription(value);
    }

    public static TaskDescription empty() {
        return new TaskDescription("");
    }
}
