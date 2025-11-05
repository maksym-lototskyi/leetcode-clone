package org.example.application.task.use_cases.add_working_solution;

import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.language.Language;
import org.example.domain.task.Task;
import org.example.domain.task.TaskId;
import org.example.domain.task.TestCase;
import org.example.domain.task.WorkingSolution;
import org.example.domain.task.service.WorkingSolutionValidator;

import java.util.List;

class AddWorkingSolutionUseCase implements AddWorkingSolutionInputBoundary {
    private final TaskRepository taskRepository;
    private final LanguageRepository repository;
    private final WorkingSolutionValidator validator;
    AddWorkingSolutionUseCase(TaskRepository taskRepository, LanguageRepository repository, WorkingSolutionValidator validator) {
        this.taskRepository = taskRepository;
        this.repository = repository;
        this.validator = validator;
    }

    public void execute(AddWorkingSolutionCommand command){
        Task task = taskRepository.loadTaskForRuntime(TaskId.of(command.taskId()))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + command.taskId()));

        Language language = repository.findByName(command.programmingLanguage())
                .orElseThrow(() -> new NotFoundException("Language not found with name: " + command.programmingLanguage()));

        WorkingSolution candidate = WorkingSolution.create(language.getId(), command.workingSolutionCode());

        task.addWorkingSolution(candidate, validator);
        taskRepository.save(task);
    }
}
