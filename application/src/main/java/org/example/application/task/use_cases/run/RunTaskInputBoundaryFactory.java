package org.example.application.task.use_cases.run;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;

public class RunTaskInputBoundaryFactory {
    public static RunTaskInputBoundary create(
            LanguageRepository languageRepository,
            TaskRepository taskRepository,
            ClassDefinitionRepository classDefinitionRepository,
            TestRunner testRunner,
            ObjectConverter converter
    ){
        return new RunTaskUseCase(languageRepository, taskRepository, classDefinitionRepository, testRunner, converter);
    }
}
