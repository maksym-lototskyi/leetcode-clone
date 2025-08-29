package org.example.domain.model.task;
import org.example.domain.model.task.example.Example;
import org.example.domain.model.topic.Topic;

import java.util.List;
import java.util.Objects;

public record Task(
        TaskId taskId,
        TaskDescription taskDescription,
        TaskLevel taskLevel,
        List<Topic> topics,
        List<Constraint> constraints,
        long timeLimitMs,
        long memoryLimitKb
) {

    public Task {
        Objects.requireNonNull(taskId, "TaskId cannot be null");
        Objects.requireNonNull(taskDescription, "Task description cannot be null");
        Objects.requireNonNull(taskLevel, "Task level cannot be null");

        if (topics == null || topics.isEmpty()) {
            throw new IllegalArgumentException("Topics cannot be null or empty");
        }
        if (constraints == null || constraints.isEmpty()) {
            throw new IllegalArgumentException("Constraints cannot be null or empty");
        }

        topics = List.copyOf(topics);
        constraints = List.copyOf(constraints);
    }

    public Example createExample(String input, String output, String explanation) {
        return Example.create(this.taskId, input, output, explanation);
    }

    public static Task create(
            TaskDescription taskDescription,
            TaskLevel taskLevel,
            List<Topic> topics,
            List<Constraint> constraints,
            long timeLimitMs,
            long memoryLimitKb
    ) {
        return new Task(TaskId.generate(), taskDescription, taskLevel, topics,  constraints, timeLimitMs, memoryLimitKb);
    }
    public record Constraint(String description) {
        public Constraint {
            if (description == null || description.isBlank()) {
                throw new IllegalArgumentException("Constraint description cannot be null or blank");
            }
        }
    }
}
