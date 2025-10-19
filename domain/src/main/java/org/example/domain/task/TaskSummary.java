package org.example.domain.task;

public record TaskSummary(TaskId taskId, String title, String difficultyLevel, double acceptanceRate) {
    public TaskSummary {
        if (taskId == null) {
            throw new IllegalArgumentException("TaskId cannot be null");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (difficultyLevel == null || difficultyLevel.isBlank()) {
            throw new IllegalArgumentException("Difficulty level cannot be null or blank");
        }
        if (acceptanceRate < 0.0 || acceptanceRate > 100.0) {
            throw new IllegalArgumentException("Acceptance rate must be between 0 and 100");
        }
    }
}
