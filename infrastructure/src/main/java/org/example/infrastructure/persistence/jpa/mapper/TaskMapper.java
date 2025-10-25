package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.task.*;
import org.example.domain.task.service.IOValidator;
import org.example.infrastructure.persistence.jpa.model.TaskEntity;

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
                TaskDescription.of(taskEntity.getTaskDescription()),
                taskEntity.getTimeLimitMs(),
                taskEntity.getMemoryLimitKb(),
                new WorkingSolution(
                        taskEntity.getWorkingSolution().getLanguage().getRuntimeImage(),
                        taskEntity.getWorkingSolution().getSourceCode()
                ),
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
                )).toList()


        );
    }
}
