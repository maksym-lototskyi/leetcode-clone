package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.task.Input;
import org.example.domain.task.Output;
import org.example.domain.task.TestCase;
import org.example.domain.task.TestCaseId;
import org.example.domain.task.service.IOValidator;
import org.example.infrastructure.persistence.jpa.model.TestCaseEntity;

public class TestCaseMapper {
    public static TestCase map(TestCaseEntity entity, IOValidator validator) {
        return new TestCase(
                TestCaseId.of(entity.getId()),
                new Input(entity.getInput(),validator),
                new Output(entity.getExpectedOutput(), validator)
        );
    }
}
