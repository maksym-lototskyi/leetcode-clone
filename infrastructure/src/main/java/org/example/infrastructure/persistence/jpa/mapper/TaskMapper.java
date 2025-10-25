package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.task.*;
import org.example.domain.task.service.IOValidator;
import org.example.domain.topic.Topic;
import org.example.domain.topic.TopicId;
import org.example.infrastructure.persistence.jpa.model.*;

import java.util.List;

public class TaskMapper {
    public static Task map(TaskEntity taskEntity, IOValidator validator){
        return new Task(
                TaskId.of(taskEntity.getTaskId()),
                TaskSignature.of(
                        taskEntity.getTaskSignature().getFunctionName(),
                        taskEntity.getTaskSignature().getParameters()
                                .stream()
                                .map(param -> new TaskSignature.Parameter(
                                        param.getName(),
                                        param.getType()
                                )).toList(),
                        taskEntity.getTaskSignature().getReturnType()
                ),
                taskEntity.getTitle(),
                TaskDescription.of(taskEntity.getTaskDescription()),
                taskEntity.getTaskLevel(),
                taskEntity.getTaskStatus(),
                taskEntity.getTopics().stream()
                        .map(t -> new Topic(TopicId.of(t.getId()), t.getName()))
                        .toList(),
                taskEntity.getConstraints()
                        .stream()
                        .map(Task.Constraint::new)
                        .toList(),
                taskEntity.getTimeLimitMs(),
                taskEntity.getMemoryLimitKb(),
                new WorkingSolution(
                        taskEntity.getWorkingSolution().getRuntimeImage(),
                        taskEntity.getWorkingSolution().getSourceCode()
                ),
                taskEntity.getExamples().stream().map(exampleEntity -> new Example(
                        ExampleId.of(exampleEntity.getId()),
                        new Input(exampleEntity.getInputs(), validator),
                        new Output(exampleEntity.getOutput(), validator),
                        exampleEntity.getExplanation()
                )).toList(),
                taskEntity.getTestCases().stream().map(testCaseEntity -> new TestCase(
                        TestCaseId.of(testCaseEntity.getId()),
                        new Input(testCaseEntity.getInputs(), validator),
                        new Output(testCaseEntity.getExpectedOutput(), validator)
                )).toList()
        );
    }

    public static TaskEntity map(Task task, List<TopicEntity>  topics){
        TaskSignatureEmbeddable taskSignatureEmbeddable = TaskSignatureEmbeddable.builder()
                .functionName(task.getTaskSignature().functionName())
                .parameters(task.getTaskSignature().parameters()
                        .stream()
                        .map(p -> new ParameterEmbeddable(p.name(), p.type()))
                        .toList())
                .returnType(task.getTaskSignature().returnType())
                .build();

        WorkingSolution workingSolution = task.getWorkingSolution();

        WorkingSolutionEmbeddable workingSolutionEmbeddable = workingSolution == null ? null :
                new WorkingSolutionEmbeddable(workingSolution.sourceCode(), workingSolution.runtimeImage());

        return TaskEntity.builder()
                .taskId(task.getTaskId().value())
                .taskLevel(task.getTaskLevel())
                .title(task.getTitle())
                .taskSignature(taskSignatureEmbeddable)
                .taskDescription(task.getTaskDescription().value())
                .memoryLimitKb(task.getMemoryLimitKb())
                .timeLimitMs(task.getTimeLimitMs())
                .workingSolution(workingSolutionEmbeddable)
                .examples(task.getExamples().stream()
                        .map(e -> ExampleEntity.builder()
                                .id(e.exampleId().value())
                                .explanation(e.explanation())
                                .output(e.output().value())
                                .inputs(e.input().params())
                                .build())
                        .toList())
                .testCases(task.getTestCases().stream()
                        .map(tc -> TestCaseEntity.builder()
                                .id(tc.testCaseId().value())
                                .expectedOutput(tc.expectedOutput().value())
                                .inputs(tc.input().params())
                                .build())
                        .toList())
                .constraints(task.getConstraints().stream()
                        .map(c -> c.description())
                        .toList())
                .topics(topics)
                .build();
    }
}
