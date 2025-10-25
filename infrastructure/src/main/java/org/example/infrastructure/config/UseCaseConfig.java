package org.example.infrastructure.config;

import org.example.application.task.use_cases.run.RunTaskInputBoundary;
import org.example.application.task.use_cases.run.RunTaskInputBoundaryFactory;
import org.example.infrastructure.adapters.DockerTestRunner;
import org.example.infrastructure.adapters.JsonIOValidator;
import org.example.infrastructure.adapters.JsonInputParser;
import org.example.infrastructure.persistence.jpa.repository.JpaLanguageRepository;
import org.example.infrastructure.persistence.jpa.repository.JpaTaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public RunTaskInputBoundary runTaskInputBoundary(){
        return RunTaskInputBoundaryFactory.create(
                new JpaLanguageRepository(),
                new JpaTaskRepository(),
                new DockerTestRunner(),
                new JsonInputParser()
        );
    }
}
