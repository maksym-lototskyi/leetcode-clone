package org.example.domain.model.task;
import lombok.Getter;
import org.example.domain.model.class_definition.ClassDefinitionId;
import org.example.domain.model.topic.TopicId;
import org.example.domain.model.user.UserId;
import org.example.domain.validation.ValidationUtils;

import java.util.*;

@Getter
public abstract sealed class Task permits DraftTask, PublishedTask {
    private final TaskId taskId;
    private final TaskSignature taskSignature;
    private TaskTitle title;
    private TaskDescription taskDescription;
    private TaskLevel taskLevel;
    private final UserId createdBy;
    private final Set<TopicId> topicIds;
    private final Set<Constraint> constraints;


    Task(TaskId taskId, UserId createdBy, TaskSignature taskSignature, TaskTitle title, TaskDescription taskDescription, TaskLevel taskLevel, Collection<TopicId> topicIds, Collection<Constraint> constraints) {
        this.taskId = ValidationUtils.requireNonNull(taskId, "Task ID cannot be null");
        this.taskDescription = ValidationUtils.requireNonNull(taskDescription, "Task description cannot be null");
        this.taskLevel = ValidationUtils.requireNonNull(taskLevel, "Task level cannot be null");
        this.taskSignature = ValidationUtils.requireNonNull(taskSignature, "Task signature cannot be null");
        this.title = ValidationUtils.requireNonNull(title, "Task title cannot be null");
        this.createdBy = ValidationUtils.requireNonNull(createdBy, "CreatedBy user ID cannot be null");
        this.constraints = (Set<Constraint>) ValidationUtils.requireNonEmptyCollection(constraints, "Task must have at least one constraint");
        this.topicIds = new HashSet<>(topicIds != null ? topicIds : new HashSet<>());
    }

    public static Task draft(UserId createdBy, TaskSignature taskSignature, TaskTitle title, TaskDescription taskDescription, TaskLevel taskLevel, Collection<TopicId> topics, Collection<Constraint> constraints, Collection<ClassDefinitionId> relatedClassDefinitions, long timeLimitMs, long memoryLimitKb) {
        return new DraftTask(TaskId.generate(), createdBy, taskSignature, title, taskDescription, taskLevel, topics, constraints, relatedClassDefinitions, timeLimitMs, memoryLimitKb);
    }
    public void changeTitle(TaskTitle newTitle) {
        this.title = ValidationUtils.requireNonNull(newTitle, "Task title cannot be null");
    }
    public void changeDescription(TaskDescription newDescription) {
        this.taskDescription = ValidationUtils.requireNonNull(newDescription, "Task description cannot be null");
    }
    public void changeLevel(TaskLevel newLevel) {
        this.taskLevel = ValidationUtils.requireNonNull(newLevel, "Task level cannot be null");
    }
    public Set<Constraint> getConstraints() {
        return Set.copyOf(constraints);
    }
    public Set<TopicId> getTopicIds() {
        return Set.copyOf(topicIds);
    }
    public abstract Set<Example> getExamples();
    public abstract Set<TestCase> getTestCases();
    public abstract WorkingSolution getWorkingSolution();
    public abstract Set<ClassDefinitionId> getRelatedClassDefinitions();
    public abstract long getTimeLimitMs();
    public abstract long getMemoryLimitKb();

    public record Constraint(String description) {
        public Constraint {
            ValidationUtils.requireNonBlank(description, "Constraint description cannot be null or blank");
        }
    }
}
