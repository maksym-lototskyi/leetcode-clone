package org.example.application.task.use_cases.publish;

import org.example.application.task.ports.out.TaskRepository;

public class PublishTaskInputBoundaryFactory {
    public static PublishTaskInputBoundary create(TaskRepository taskRepository) {
        return new PublishTaskUseCase(taskRepository);
    }
}
