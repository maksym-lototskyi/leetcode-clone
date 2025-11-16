package org.example.domain.task;
import lombok.Getter;
import org.example.domain.class_definition.ClassDefinitionId;
import org.example.domain.task.exception.WorkingSolutionNotValidException;
import org.example.domain.task.service.WorkingSolutionValidator;
import org.example.domain.topic.TopicId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Task {
    private final TaskId taskId;
    private final TaskSignature taskSignature;
    private final String title;
    private final TaskDescription taskDescription;
    private final TaskLevel taskLevel;
    private TaskStatus status;
    private final List<TopicId> topics;
    private final List<Constraint> constraints;
    private final List<Example> examples;
    private final List<TestCase> testCases;
    private final List<ClassDefinitionId> relatedClassDefinitions;
    private WorkingSolution workingSolution;
    private long timeLimitMs;
    private long memoryLimitKb;


    public Task(TaskId taskId, TaskSignature taskSignature, String title, TaskDescription taskDescription, TaskLevel taskLevel, TaskStatus status, List<TopicId> topics, List<Constraint> constraints, List<Example> examples, List<TestCase> testCases, List<ClassDefinitionId> relatedClassDefinitions, WorkingSolution workingSolution, long timeLimitMs, long memoryLimitKb) {
        Objects.requireNonNull(taskId, "TaskId cannot be null");
        Objects.requireNonNull(taskDescription, "Task description cannot be null");
        Objects.requireNonNull(taskLevel, "Task level cannot be null");
        Objects.requireNonNull(status, "Task status cannot be null");
        Objects.requireNonNull(taskSignature, "Task signature cannot be null");

        if(title == null || title.isEmpty()) throw new IllegalArgumentException("Title cannot be null or empty");

        if (timeLimitMs <= 0) throw new IllegalArgumentException("Time limit must be positive");
        if (memoryLimitKb <= 0) throw new IllegalArgumentException("Memory limit must be positive");

        if (constraints == null || constraints.isEmpty()) {
            throw new IllegalArgumentException("Constraints cannot be null or empty");
        }

        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.title = title;
        this.taskLevel = taskLevel;
        this.status = status;
        this.timeLimitMs = timeLimitMs;
        this.memoryLimitKb = memoryLimitKb;
        this.topics = new ArrayList<>(topics != null ? topics : new ArrayList<>());
        this.constraints = List.copyOf(constraints);
        this.taskSignature = taskSignature;
        this.workingSolution = workingSolution;
        this.examples = new ArrayList<>(examples != null ? examples : new ArrayList<>());
        this.testCases = new ArrayList<>(testCases != null ? testCases : new ArrayList<>());
        this.relatedClassDefinitions = new ArrayList<>(relatedClassDefinitions != null ? relatedClassDefinitions : new ArrayList<>());
    }

    public void publish(){
        if(this.status != TaskStatus.DRAFT){
            throw new IllegalStateException("Only tasks in DRAFT status can be published");
        }
        if(this.workingSolution == null){
            throw new IllegalStateException("Cannot publish task without a working solution");
        }
        if(this.examples == null || this.examples.isEmpty()){
            throw new IllegalStateException("Cannot publish task without at least one example");
        }
        if(testCases.isEmpty()) throw new IllegalStateException("Cannot publish task without test cases");
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

    public static Task draft(TaskSignature taskSignature, String title, TaskDescription taskDescription, TaskLevel taskLevel, List<TopicId> topics, List<Constraint> constraints, List<ClassDefinitionId> relatedClassDefinitions, long timeLimitMs, long memoryLimitKb) {
        return new Task(TaskId.generate(), taskSignature, title, taskDescription, taskLevel, TaskStatus.DRAFT, topics, constraints,null, null, relatedClassDefinitions, null, timeLimitMs, memoryLimitKb);
    }

    public void addExample(Example example) {
        examples.add(example);
    }

    public List<Example> getExamples() {
        return List.copyOf(examples);
    }
    public List<Constraint> getConstraints() {
        return List.copyOf(constraints);
    }
    public List<TopicId> getTopics() {
        return List.copyOf(topics);
    }

    public void addWorkingSolution(WorkingSolution workingSolution, WorkingSolutionValidator validator) {
        validator.validate(this, workingSolution);
        this.workingSolution = workingSolution;
    }

    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    public record Constraint(String description) {
        public Constraint {
            if (description == null || description.isBlank()) {
                throw new IllegalArgumentException("Constraint description cannot be null or blank");
            }
        }
    }
}
