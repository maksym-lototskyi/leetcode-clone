package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.model.task.Input;
import org.example.domain.model.task.Output;
import org.example.domain.model.task.TestCase;
import org.example.domain.model.task.TestCaseId;
import org.example.domain.model.task.IOValidator;
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
