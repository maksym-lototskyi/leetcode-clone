package org.example.application.task.use_cases.publish;

import org.example.application.exception.NotFoundException;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.model.task.DraftTask;
import org.example.domain.model.task.PublishedTask;
import org.example.domain.model.task.TaskId;
import org.example.domain.model.task.WorkingSolutionValidator;

import java.util.UUID;

public class PublishTaskUseCase implements PublishTaskInputBoundary {
    private final TaskRepository taskRepository;
    private final WorkingSolutionValidator validator;

    public PublishTaskUseCase(TaskRepository taskRepository, WorkingSolutionValidator validator) {
        this.taskRepository = taskRepository;
        this.validator = validator;
    }

    public void execute(UUID taskId){
        DraftTask task = taskRepository.findDraftById(TaskId.of(taskId))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + taskId));

        PublishedTask published = task.publish(validator);
        taskRepository.save(published);
    }
}
