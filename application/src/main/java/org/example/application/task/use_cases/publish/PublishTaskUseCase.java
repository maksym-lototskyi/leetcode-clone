package org.example.application.task.use_cases.publish;

import org.example.application.exception.NotFoundException;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.model.task.TaskId;

import java.util.UUID;

public class PublishTaskUseCase implements PublishTaskInputBoundary {
    private final TaskRepository taskRepository;

    public PublishTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute(UUID taskId){
        var task = taskRepository.loadTaskForRuntime(TaskId.of(taskId))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + taskId));

        task.publish();
        taskRepository.save(task);
    }
}
