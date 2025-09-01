package org.example.domain.task;
import org.example.domain.topic.Topic;

import java.util.List;
import java.util.Objects;

public record Task(
        TaskId taskId,
        TaskDescription taskDescription,
        TaskLevel taskLevel,
        List<Topic> topics,
        List<Constraint> constraints,
        String workingSolution,
        long timeLimitMs,
        long memoryLimitKb
) {

    public Task {
        Objects.requireNonNull(taskId, "TaskId cannot be null");
        Objects.requireNonNull(taskDescription, "Task description cannot be null");
        Objects.requireNonNull(taskLevel, "Task level cannot be null");

        if (timeLimitMs <= 0) throw new IllegalArgumentException("Time limit must be positive");
        if (memoryLimitKb <= 0) throw new IllegalArgumentException("Memory limit must be positive");

        if (topics == null || topics.isEmpty()) {
            throw new IllegalArgumentException("Topics cannot be null or empty");
        }
        if (constraints == null || constraints.isEmpty()) {
            throw new IllegalArgumentException("Constraints cannot be null or empty");
        }

        topics = List.copyOf(topics);
        constraints = List.copyOf(constraints);
    }

    public TestCase addTestCase(Input input, Output output) {
        return new TestCase(this.taskId, input, output);
    }

    public Example addExample(Input input, Output output, String explanation) {
        return Example.create(this.taskId, input, output, explanation);
    }
    public record Constraint(String description) {
        public Constraint {
            if (description == null || description.isBlank()) {
                throw new IllegalArgumentException("Constraint description cannot be null or blank");
            }
        }
    }
}
