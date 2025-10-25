package org.example.application.task.use_cases.add_test_case;

import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.task.service.IOValidator;

public class AddTestCaseInputBoundaryFactory {
    public static AddTestCaseInputBoundary create(
            TaskRepository taskRepository,
            IOValidator ioValidator
    ){
        return new AddTestCaseUseCase(taskRepository, ioValidator);
    }
}
