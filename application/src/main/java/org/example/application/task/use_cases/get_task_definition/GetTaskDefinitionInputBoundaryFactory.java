package org.example.application.task.use_cases.get_task_definition;

import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.topic.ports.out.TopicRepository;

public class GetTaskDefinitionInputBoundaryFactory {
    public static GetTaskDefinitionInputBoundary create(TaskRepository taskRepository, TopicRepository topicRepository, LanguageRepository languageRepository, StarterCodeGenerator starterCodeGenerator) {
        return new GetTaskDefinitionUseCase(taskRepository, topicRepository, languageRepository, starterCodeGenerator);
    }
}
