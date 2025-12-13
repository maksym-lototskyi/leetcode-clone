package org.example.domain.model.task;

import lombok.Getter;
import org.example.domain.model.class_definition.ClassDefinitionId;
import org.example.domain.model.topic.TopicId;
import org.example.domain.model.user.UserId;
import org.example.domain.validation.ValidationUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public final class DraftTask extends Task{
    private Set<Example> examples;
    private Set<TestCase> testCases;
    private final Set<ClassDefinitionId> relatedClassDefinitions;
    private WorkingSolution workingSolution;
    private long timeLimitMs;
    private long memoryLimitKb;
    public DraftTask(TaskId taskId, UserId createdBy, TaskSignature taskSignature, TaskTitle title, TaskDescription taskDescription, TaskLevel taskLevel, Collection<TopicId> topics, Collection<Constraint> constraints, Collection<ClassDefinitionId> relatedClassDefinitions, long timeLimitMs, long memoryLimitKb) {
        super(taskId, createdBy, taskSignature, title, taskDescription, taskLevel, topics, constraints);
        this.timeLimitMs = ValidationUtils.requireNonNegative(timeLimitMs, "Time limit must be non-negative");
        this.memoryLimitKb = ValidationUtils.requireNonNegative(memoryLimitKb, "Memory limit must be non-negative");
        this.examples = new HashSet<>();
        this.testCases = new HashSet<>();
        this.relatedClassDefinitions = new HashSet<>(ValidationUtils.requireNonNullOrDefault(relatedClassDefinitions, new HashSet<>()));
    }

    public DraftTask(TaskId taskId, UserId createdBy, TaskSignature taskSignature, TaskTitle title, TaskDescription taskDescription, TaskLevel taskLevel, Collection<TopicId> topics, Collection<Constraint> constraints, Collection<Example> examples, Collection<TestCase> testCases, Collection<ClassDefinitionId> relatedClassDefinitions, WorkingSolution workingSolution, long timeLimitMs, long memoryLimitKb) {
        this(taskId, createdBy, taskSignature, title, taskDescription, taskLevel, topics, constraints, relatedClassDefinitions, timeLimitMs, memoryLimitKb);
        this.examples = new HashSet<>(ValidationUtils.requireNonNullOrDefault(examples, new HashSet<>()));
        this.testCases = new HashSet<>(ValidationUtils.requireNonNullOrDefault(testCases, new HashSet<>()));
        this.workingSolution = workingSolution;
    }

    public PublishedTask publish(WorkingSolutionValidator validator){
        ValidationUtils.requireNonNull(validator, "WorkingSolutionValidator cannot be null");
        ValidationUtils.requireNonEmptyCollection(this.examples, "Cannot publish task without examples");
        ValidationUtils.requireNonEmptyCollection(this.testCases, "Cannot publish task without test cases");
        ValidationUtils.requireNonNull(this.workingSolution, "Cannot publish task without a working solution");
        validator.validate(this, this.workingSolution);
        return new PublishedTask(
                this.getTaskId(),
                this.getCreatedBy(),
                this.getTaskSignature(),
                this.getTitle(),
                this.getTaskDescription(),
                this.getTaskLevel(),
                Set.copyOf(this.getTopicIds()),
                Set.copyOf(this.getConstraints()),
                Set.copyOf(this.getExamples()),
                Set.copyOf(this.getTestCases()),
                Set.copyOf(this.getRelatedClassDefinitions()),
                ValidationUtils.requireNonNull(this.workingSolution, "Working solution must be set before publishing the task"),
                this.timeLimitMs,
                this.memoryLimitKb,
                false
        );
    }

    public void addExample(Example example) {
        examples.add(example);
    }
    public void removeExample(Example example) {
        examples.remove(example);
    }
    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }
    public void removeTestCase(TestCase testCase) {
        testCases.remove(testCase);
    }

    public void addClassDefinition(ClassDefinitionId classDefinitionId) {
        relatedClassDefinitions.add(classDefinitionId);
    }
    public void removeClassDefinition(ClassDefinitionId classDefinitionId) {
        relatedClassDefinitions.remove(classDefinitionId);
    }

    public void changeMemoryLimit(long newMemoryLimitKb) {
        this.memoryLimitKb = ValidationUtils.requireNonNegative(newMemoryLimitKb, "Memory limit must be non-negative");
    }

    public void changeTimeLimit(long newTimeLimitMs) {
        this.timeLimitMs = ValidationUtils.requireNonNegative(newTimeLimitMs, "Time limit must be non-negative");
    }

    public void addWorkingSolution(WorkingSolution workingSolution, WorkingSolutionValidator validator) {
        ValidationUtils.requireNonNull(validator, "WorkingSolutionValidator cannot be null");
        ValidationUtils.requireNonNull(workingSolution, "WorkingSolution cannot be null");
        validator.validate(this, workingSolution);
        this.workingSolution = workingSolution;
    }
    public void removeWorkingSolution() {
        this.workingSolution = null;
    }
    @Override
    public Set<Example> getExamples() {
        return Set.copyOf(examples);
    }
    @Override
    public Set<TestCase> getTestCases() {
        return Set.copyOf(testCases);
    }
    @Override
    public Set<ClassDefinitionId> getRelatedClassDefinitions() {
        return Set.copyOf(relatedClassDefinitions);
    }
}
