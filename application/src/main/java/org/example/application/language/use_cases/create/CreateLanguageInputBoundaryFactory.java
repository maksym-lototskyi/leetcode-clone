package org.example.application.language.use_cases.create;

import org.example.application.language.ports.out.LanguageRepository;

public class CreateLanguageInputBoundaryFactory {
    public static CreateLanguageInputBoundary create(LanguageRepository languageRepository) {
        return new CreateLanguageUseCase(languageRepository);
    }
}
