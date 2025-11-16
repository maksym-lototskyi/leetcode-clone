package org.example.application.task.use_cases.create;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.topic.ports.out.TopicRepository;

public class CreateTaskInputBoundaryFactory {
    public static CreateTaskInputBoundary create(TaskRepository taskRepository, TopicRepository topicRepository, ClassDefinitionRepository classDefinitionRepository) {
        return new CreateTaskUseCase(
                taskRepository,
                topicRepository,
                classDefinitionRepository
        );
    }
}
