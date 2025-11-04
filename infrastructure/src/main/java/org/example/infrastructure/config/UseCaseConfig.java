package org.example.infrastructure.config;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.task.use_cases.run.ObjectConverter;
import org.example.application.task.use_cases.run.RunTaskInputBoundary;
import org.example.application.task.use_cases.run.RunTaskInputBoundaryFactory;
import org.example.application.task.use_cases.run.TestRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public RunTaskInputBoundary runTaskInputBoundary(LanguageRepository languageRepository, TaskRepository taskRepository, ClassDefinitionRepository classDefinitionRepository, TestRunner testRunner, ObjectConverter converter) {
        return RunTaskInputBoundaryFactory.create(
                languageRepository,
                taskRepository,
                classDefinitionRepository,
                testRunner,
                converter
        );
    }
}
