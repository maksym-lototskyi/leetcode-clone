package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.model.task.*;
import org.example.infrastructure.persistence.jpa.model.TaskEntity;
import org.example.infrastructure.persistence.jpa.model.TestCaseEntity;

public class TestCaseMapper {
    public static TestCase map(TestCaseEntity entity, IOValidator validator) {
        return new TestCase(
                TestCaseId.of(entity.getId()),
                new Input(entity.getInput(),validator),
                new Output(entity.getExpectedOutput(), validator)
        );
    }

    public static TestCaseEntity map(TestCase testCase, TaskEntity task) {
        return TestCaseEntity.builder()
                .id(testCase.testCaseId().value())
                .task(task)
                .input(testCase.input().value())
                .expectedOutput(testCase.expectedOutput().value())
                .build();
    }
}
