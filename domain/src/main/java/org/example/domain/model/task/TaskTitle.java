package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

public record TaskTitle(String value) {
    public TaskTitle{
        ValidationUtils.requireNonBlank(value, "Task title cannot be null or blank");
        ValidationUtils.requireMaximumValue(value.length(), 70, "Task title cannot exceed 70 characters");
    }

    public static TaskTitle of(String title) {
        return new TaskTitle(title);
    }
}
