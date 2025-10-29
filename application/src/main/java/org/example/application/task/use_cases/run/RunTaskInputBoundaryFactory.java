package org.example.application.task.use_cases.run;

import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;

public class RunTaskInputBoundaryFactory {
    public static RunTaskInputBoundary create(
            LanguageRepository languageRepository,
            TaskRepository taskRepository,
            TestRunner testRunner,
            ObjectConverter converter
    ){
        return new RunTaskUseCase(languageRepository, taskRepository, testRunner, converter);
    }
}
