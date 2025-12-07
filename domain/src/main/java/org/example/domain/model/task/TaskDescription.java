package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

public record TaskDescription(String value) {
    public TaskDescription {
        ValidationUtils.requireNonBlank(value, "Task description cannot be null or blank");
        ValidationUtils.requireMaximumValue(value.length(), 2000, "Task description cannot exceed 2000 characters");
    }

    public static TaskDescription of(String value) {
        return new TaskDescription(value);
    }
}
