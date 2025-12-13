package org.example.application.task.use_cases.create_draft;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.topic.ports.out.TopicRepository;

public class CreateDraftTaskInputBoundaryFactory {
    public static CreateDraftTaskInputBoundary create(TaskRepository taskRepository, TopicRepository topicRepository, ClassDefinitionRepository classDefinitionRepository) {
        return new CreateDraftTaskUseCase(
                taskRepository,
                topicRepository,
                classDefinitionRepository
        );
    }
}
