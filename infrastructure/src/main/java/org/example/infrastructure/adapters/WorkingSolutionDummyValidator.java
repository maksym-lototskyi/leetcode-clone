package org.example.infrastructure.adapters;

import org.example.domain.task.Task;
import org.example.domain.task.TestCase;
import org.example.domain.task.WorkingSolution;
import org.example.domain.task.service.WorkingSolutionValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkingSolutionDummyValidator implements WorkingSolutionValidator {
    @Override
    public void validate(Task task, WorkingSolution workingSolution, List<TestCase> testCases) {

    }
}
