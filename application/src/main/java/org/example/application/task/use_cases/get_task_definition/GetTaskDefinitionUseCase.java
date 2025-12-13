package org.example.application.task.use_cases.get_task_definition;

import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.topic.ports.out.TopicRepository;
import org.example.domain.model.language.Language;
import org.example.domain.model.language.LanguageName;
import org.example.domain.model.task.Task;
import org.example.domain.model.task.TaskId;
import org.example.domain.model.topic.Topic;

import java.util.List;

class GetTaskDefinitionUseCase implements GetTaskDefinitionInputBoundary {
    private final TaskRepository taskRepository;
    private final TopicRepository topicRepository;
    private final LanguageRepository languageRepository;
    private final StarterCodeGenerator starterCodeGenerator;

    public GetTaskDefinitionUseCase(TaskRepository taskRepository, TopicRepository topicRepository, LanguageRepository languageRepository, StarterCodeGenerator starterCodeGenerator) {
        this.taskRepository = taskRepository;
        this.topicRepository = topicRepository;
        this.languageRepository = languageRepository;
        this.starterCodeGenerator = starterCodeGenerator;
    }

    public TaskDetailsDto execute(GetTaskDefinitionRequest request) {
        Task task = taskRepository.findPublishedTaskDefinition(TaskId.of(request.taskId()))
                .orElseThrow(() -> new NotFoundException("Task not found"));
        List<Topic> topics = topicRepository.findAllByIds(task.getTopicIds());
        LanguageName languageName;

        if(request.languageName().isPresent()) {
            languageName = LanguageName.fromString(request.languageName().get());
        }
        else languageName = Language.DEFAULT;

        if(!languageRepository.existsByName(languageName)) {
            throw new NotFoundException("Language not supported: " + languageName);
        }

        String starterCode = starterCodeGenerator.generateStarterCode(task.getTaskSignature(), languageName);
        return TaskDefinitionMapper.toDto(task, topics, starterCode);
    }
}
