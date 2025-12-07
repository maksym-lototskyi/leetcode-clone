package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

public record TaskSummary(TaskId taskId, String title, TaskLevel difficultyLevel, double acceptanceRate) {
    public TaskSummary {
        ValidationUtils.requireNonNull(taskId, "TaskId cannot be null");
        ValidationUtils.requireNonBlank(title, "Title cannot be null or blank");
        ValidationUtils.requireNonNull(difficultyLevel, "Difficulty level cannot be null");
        ValidationUtils.requireValueInRange(acceptanceRate, 0.0, 100.0, "Acceptance rate must be between 0 and 100");
    }
}
