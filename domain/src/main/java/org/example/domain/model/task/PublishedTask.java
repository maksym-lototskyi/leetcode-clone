package org.example.domain.model.task;

import lombok.Getter;
import org.example.domain.model.class_definition.ClassDefinitionId;
import org.example.domain.model.topic.TopicId;
import org.example.domain.model.user.UserId;
import org.example.domain.validation.ValidationUtils;

import java.util.Collection;
import java.util.Set;

@Getter
public final class PublishedTask extends Task{
    private final Set<Example> examples;
    private final Set<TestCase> testCases;
    private final Set<ClassDefinitionId> relatedClassDefinitions;
    private final WorkingSolution workingSolution;
    private final long timeLimitMs;
    private final long memoryLimitKb;
    private boolean isArchived;

    public PublishedTask(TaskId taskId, UserId createdBy, TaskSignature taskSignature, TaskTitle title, TaskDescription taskDescription, TaskLevel taskLevel, Collection<TopicId> topics, Collection<Constraint> constraints, Collection<Example> examples, Collection<TestCase> testCases, Collection<ClassDefinitionId> relatedClassDefinitions, WorkingSolution workingSolution, long timeLimitMs, long memoryLimitKb, boolean isArchived) {
        super(taskId, createdBy, taskSignature, title, taskDescription, taskLevel, topics, constraints);
        this.workingSolution = ValidationUtils.requireNonNull(workingSolution, "Working solution cannot be null");
        this.timeLimitMs = ValidationUtils.requireNonNegative(timeLimitMs, "Time limit must be non-negative");
        this.memoryLimitKb = ValidationUtils.requireNonNegative(memoryLimitKb, "Memory limit must be non-negative");
        this.examples = Set.copyOf(ValidationUtils.requireNonEmptyCollection(examples, "Published task must have at least one example"));
        this.testCases = Set.copyOf(ValidationUtils.requireNonEmptyCollection(testCases, "Published task must have at least one test case"));
        this.relatedClassDefinitions = Set.copyOf(ValidationUtils.requireNonEmptyCollection(relatedClassDefinitions, "Published task must have at least one related class definition"));
        this.isArchived = isArchived;
    }

    public void archive(){
        this.isArchived = true;
    }
    public void unarchive(){
        this.isArchived = false;
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
