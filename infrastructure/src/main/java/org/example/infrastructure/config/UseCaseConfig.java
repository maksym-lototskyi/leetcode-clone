package org.example.infrastructure.config;

import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.task.use_cases.run.RunTaskInputBoundary;
import org.example.application.task.use_cases.run.RunTaskInputBoundaryFactory;
import org.example.infrastructure.adapters.DockerTestRunner;
import org.example.infrastructure.adapters.JsonInputParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public RunTaskInputBoundary runTaskInputBoundary(LanguageRepository languageRepository, TaskRepository taskRepository) {
        return RunTaskInputBoundaryFactory.create(
                languageRepository,
                taskRepository,
                new DockerTestRunner(),
                new JsonInputParser()
        );
    }
}
