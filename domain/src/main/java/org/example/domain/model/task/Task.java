package org.example.domain.model.task;
import lombok.Getter;
import org.example.domain.model.class_definition.ClassDefinitionId;
import org.example.domain.model.topic.TopicId;
import org.example.domain.validation.ValidationUtils;

import java.util.*;

@Getter
public class Task {
    private final TaskId taskId;
    private final TaskSignature taskSignature;
    private final String title;
    private final TaskDescription taskDescription;
    private final TaskLevel taskLevel;
    private TaskStatus status;
    private final Set<TopicId> topics;
    private final Set<Constraint> constraints;
    private final Set<Example> examples;
    private final Set<TestCase> testCases;
    private final Set<ClassDefinitionId> relatedClassDefinitions;
    private WorkingSolution workingSolution;
    private long timeLimitMs;
    private long memoryLimitKb;


    public Task(TaskId taskId, TaskSignature taskSignature, String title, TaskDescription taskDescription, TaskLevel taskLevel, TaskStatus status, List<TopicId> topics, List<Constraint> constraints, List<Example> examples, List<TestCase> testCases, List<ClassDefinitionId> relatedClassDefinitions, WorkingSolution workingSolution, long timeLimitMs, long memoryLimitKb) {
        this.taskId = ValidationUtils.requireNonNull(taskId, "Task ID cannot be null");
        this.taskDescription = ValidationUtils.requireNonNull(taskDescription, "Task description cannot be null");
        this.taskLevel = ValidationUtils.requireNonNull(taskLevel, "Task level cannot be null");
        this.status = ValidationUtils.requireNonNull(status, "Task status cannot be null");
        this.taskSignature = ValidationUtils.requireNonNull(taskSignature, "Task signature cannot be null");
        this.title = ValidationUtils.requireNonBlank(title, "Task title cannot be null or blank");
        this.timeLimitMs = ValidationUtils.requireNonNegative(timeLimitMs, "Time limit must be non-negative");
        this.memoryLimitKb = ValidationUtils.requireNonNegative(memoryLimitKb, "Memory limit must be non-negative");
        this.constraints = (Set<Constraint>) ValidationUtils.requireNonEmptyCollection(constraints, "Task must have at least one constraint");

        this.topics = new HashSet<>(topics != null ? topics : new HashSet<>());
        this.workingSolution = workingSolution;
        this.examples = new HashSet<>(ValidationUtils.requireNonNullOrDefault(examples, new HashSet<>()));
        this.testCases = new HashSet<>(ValidationUtils.requireNonNullOrDefault(testCases, new HashSet<>()));
        this.relatedClassDefinitions = new HashSet<>(ValidationUtils.requireNonNullOrDefault(relatedClassDefinitions, new HashSet<>()));
    }

    public void publish(){
        if(this.status != TaskStatus.DRAFT){
            throw new IllegalStateException("Only tasks in DRAFT status can be published");
        }
        ValidationUtils.requireNonNull(this.workingSolution, "Cannot publish task without a working solution");
        ValidationUtils.requireNonEmptyCollection(this.testCases, "Cannot publish task without at least one test case");
        ValidationUtils.requireNonEmptyCollection(this.examples, "Cannot publish task without at least one example");

        this.status = TaskStatus.PUBLISHED;
    }

    public void changeMemoryLimit(long newMemoryLimitKb) {
        this.memoryLimitKb = ValidationUtils.requireNonNegative(newMemoryLimitKb, "Memory limit must be non-negative");
    }

    public void changeTimeLimit(long newTimeLimitMs) {
        this.timeLimitMs = ValidationUtils.requireNonNegative(newTimeLimitMs, "Time limit must be non-negative");
    }

    public static Task draft(TaskSignature taskSignature, String title, TaskDescription taskDescription, TaskLevel taskLevel, List<TopicId> topics, List<Constraint> constraints, List<ClassDefinitionId> relatedClassDefinitions, long timeLimitMs, long memoryLimitKb) {
        return new Task(TaskId.generate(), taskSignature, title, taskDescription, taskLevel, TaskStatus.DRAFT, topics, constraints,null, null, relatedClassDefinitions, null, timeLimitMs, memoryLimitKb);
    }

    public void addExample(Example example) {
        examples.add(example);
    }

    public Set<Example> getExamples() {
        return Set.copyOf(examples);
    }
    public Set<Constraint> getConstraints() {
        return Set.copyOf(constraints);
    }
    public Set<TopicId> getTopics() {
        return Set.copyOf(topics);
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
            ValidationUtils.requireNonBlank(description, "Constraint description cannot be null or blank");
        }
    }
}
