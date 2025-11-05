package org.example.infrastructure.config;

import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.language.use_cases.create.CreateLanguageInputBoundary;
import org.example.application.language.use_cases.create.CreateLanguageInputBoundaryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LanguageUseCaseConfig {
    @Bean
    public CreateLanguageInputBoundary createLanguageUseCase(LanguageRepository languageRepository) {
        return CreateLanguageInputBoundaryFactory.create(languageRepository);
    }
}
