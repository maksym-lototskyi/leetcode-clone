package org.example.application.task.use_cases.publish;

import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.model.task.WorkingSolutionValidator;

public class PublishTaskInputBoundaryFactory {
    public static PublishTaskInputBoundary create(TaskRepository taskRepository, WorkingSolutionValidator validator) {
        return new PublishTaskUseCase(taskRepository, validator);
    }
}
