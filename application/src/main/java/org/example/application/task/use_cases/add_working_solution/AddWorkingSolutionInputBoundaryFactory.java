package org.example.application.task.use_cases.add_working_solution;

import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.task.service.WorkingSolutionValidator;

public class AddWorkingSolutionInputBoundaryFactory {
    public static AddWorkingSolutionInputBoundary create(
            TaskRepository taskRepository,
            LanguageRepository languageRepository,
            WorkingSolutionValidator validator
    ) {
        return new AddWorkingSolutionUseCase(
                taskRepository, languageRepository, validator
        );
    }
}
