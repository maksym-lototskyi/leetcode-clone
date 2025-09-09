package org.example.domain.task;
import lombok.Getter;
import org.example.domain.language.LanguageId;
import org.example.domain.topic.Topic;

import java.util.List;
import java.util.Objects;

@Getter
public class Task {
    private final TaskId taskId;
    private final TaskSignature taskSignature;
    private final TaskDescription taskDescription;
    private final TaskLevel taskLevel;
    private TaskStatus status;
    private final List<Topic> topics;
    private final List<Constraint> constraints;
    private long timeLimitMs;
    private long memoryLimitKb;

    private Task(TaskId taskId, TaskSignature taskSignature, TaskDescription taskDescription, TaskLevel taskLevel, TaskStatus status, List<Topic> topics, List<Constraint> constraints, long timeLimitMs, long memoryLimitKb) {
        Objects.requireNonNull(taskId, "TaskId cannot be null");
        Objects.requireNonNull(taskDescription, "Task description cannot be null");
        Objects.requireNonNull(taskLevel, "Task level cannot be null");
        Objects.requireNonNull(status);
        Objects.requireNonNull(taskSignature, "Task signature cannot be null");

        if (timeLimitMs <= 0) throw new IllegalArgumentException("Time limit must be positive");
        if (memoryLimitKb <= 0) throw new IllegalArgumentException("Memory limit must be positive");

        if (topics == null || topics.isEmpty()) {
            throw new IllegalArgumentException("Topics cannot be null or empty");
        }
        if (constraints == null || constraints.isEmpty()) {
            throw new IllegalArgumentException("Constraints cannot be null or empty");
        }
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.taskLevel = taskLevel;
        this.status = status;
        this.timeLimitMs = timeLimitMs;
        this.memoryLimitKb = memoryLimitKb;
        this.topics = List.copyOf(topics);
        this.constraints = List.copyOf(constraints);
        this.taskSignature = taskSignature;
    }

    public void publish(){
        this.status = TaskStatus.PUBLISHED;
    }

    public void changeMemoryLimit(long newMemoryLimitKb) {
        if (newMemoryLimitKb <= 0) throw new IllegalArgumentException("Memory limit must be positive");
        this.memoryLimitKb = newMemoryLimitKb;
    }

    public void changeTimeLimit(long newTimeLimitMs) {
        if (newTimeLimitMs <= 0) throw new IllegalArgumentException("Time limit must be positive");
        this.timeLimitMs = newTimeLimitMs;
    }

    public static Task draft(TaskSignature taskSignature, TaskDescription taskDescription, TaskLevel taskLevel, List<Topic> topics, List<Constraint> constraints, long timeLimitMs, long memoryLimitKb) {
        return new Task(TaskId.generate(), taskSignature, taskDescription, taskLevel, TaskStatus.DRAFT, topics, constraints, timeLimitMs, memoryLimitKb);
    }

    public TestCase addTestCase(Input input, Output output) {
        return TestCase.create(this.taskId, input, output);
    }

    public Example addExample(Input input, Output output, String explanation) {
        return Example.create(this.taskId, input, output, explanation);
    }

    public WorkingSolution addWorkingSolution(String sourceCode, LanguageId language) {
        return new WorkingSolution(this.taskId, language, sourceCode);
    }
    public record Constraint(String description) {
        public Constraint {
            if (description == null || description.isBlank()) {
                throw new IllegalArgumentException("Constraint description cannot be null or blank");
            }
        }
    }
}
