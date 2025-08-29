package org.example.domain.model.task.test_run;

import org.example.domain.model.task.TaskId;
import org.example.domain.model.user.UserId;

public record TestRun(TestRunId testRunId, TaskId taskId, UserId userId, String input, boolean wasRun, boolean isPassed) {
    public TestRun {
        if (testRunId == null) {
            throw new IllegalArgumentException("testRunId cannot be null");
        }
        if (taskId == null) {
            throw new IllegalArgumentException("taskId cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("input cannot be null or empty");
        }
    }
}
