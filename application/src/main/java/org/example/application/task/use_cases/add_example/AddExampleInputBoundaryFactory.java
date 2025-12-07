package org.example.application.task.use_cases.add_example;

import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.model.task.IOValidator;

public class AddExampleInputBoundaryFactory {
    public static AddExampleInputBoundary create(TaskRepository taskRepository, IOValidator ioValidator) {
        return new AddExampleUseCase(taskRepository, ioValidator);
    }
}
