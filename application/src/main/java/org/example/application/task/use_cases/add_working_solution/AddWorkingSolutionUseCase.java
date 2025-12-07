package org.example.application.task.use_cases.add_working_solution;

import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.model.language.Language;
import org.example.domain.model.task.*;

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
        DraftTask task = taskRepository.findDraftById(TaskId.of(command.taskId()))
                .orElseThrow(() -> new NotFoundException("Draft task not found with id: " + command.taskId()));

        Language language = repository.findByName(command.programmingLanguage())
                .orElseThrow(() -> new NotFoundException("Language not found with name: " + command.programmingLanguage()));

        WorkingSolution candidate = WorkingSolution.create(language.getId(), command.workingSolutionCode());

        task.addWorkingSolution(candidate, validator);
        taskRepository.save(task);
    }
}
