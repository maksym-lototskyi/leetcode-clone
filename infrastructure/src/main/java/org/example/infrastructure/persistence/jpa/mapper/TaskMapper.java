package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.class_definition.ClassDefinitionId;
import org.example.domain.language.LanguageId;
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
                taskEntity.getExamples().stream().map(exampleEntity -> new Example(
                        ExampleId.of(exampleEntity.getId()),
                        new Input(exampleEntity.getInput(), validator),
                        new Output(exampleEntity.getOutput(), validator),
                        exampleEntity.getExplanation()
                )).toList(),

                taskEntity.getTestCases().stream().map(testCaseEntity -> new TestCase(
                        TestCaseId.of(testCaseEntity.getId()),
                        new Input(testCaseEntity.getInput(), validator),
                        new Output(testCaseEntity.getExpectedOutput(), validator)
                )).toList(),
                taskEntity.getClassDefinitions().stream()
                        .map(cdEntity -> ClassDefinitionId.of(cdEntity.getId()))
                        .toList(),
                WorkingSolution.of(
                        WorkingSolutionId.of(taskEntity.getWorkingSolutionEntity().getId()),
                        LanguageId.of(taskEntity.getWorkingSolutionEntity().getLanguage().getId()),
                        taskEntity.getWorkingSolutionEntity().getSourceCode()
                ),
                taskEntity.getTimeLimitMs(),
                taskEntity.getMemoryLimitKb()
        );
    }

    public static TaskEntity map(Task task, List<TopicEntity>  topics, WorkingSolutionEntity workingSolution){
        TaskSignatureEmbeddable taskSignatureEmbeddable = new TaskSignatureEmbeddable(
                task.getTaskSignature().functionName(),
                task.getTaskSignature().parameters()
                        .stream()
                        .map(p -> new ParameterEmbeddable(p.name(), p.type()))
                        .toList(),
                task.getTaskSignature().returnType());


        return TaskEntity.builder()
                .taskId(task.getTaskId().value())
                .taskLevel(task.getTaskLevel())
                .title(task.getTitle())
                .taskSignature(taskSignatureEmbeddable)
                .taskDescription(task.getTaskDescription().value())
                .memoryLimitKb(task.getMemoryLimitKb())
                .timeLimitMs(task.getTimeLimitMs())
                .workingSolutionEntity(workingSolution)
                .examples(task.getExamples().stream()
                        .map(e -> ExampleEntity.builder()
                                .id(e.exampleId().value())
                                .explanation(e.explanation())
                                .output(e.output().value())
                                .input(e.input().getInput())
                                .build())
                        .toList())
                .testCases(task.getTestCases().stream()
                        .map(tc -> TestCaseEntity.builder()
                                .id(tc.testCaseId().value())
                                .expectedOutput(tc.expectedOutput().value())
                                .input(tc.input().getInput())
                                .build())
                        .toList())
                .constraints(task.getConstraints().stream()
                        .map(c -> c.description())
                        .toList())
                .topics(topics)
                .build();
    }
}
