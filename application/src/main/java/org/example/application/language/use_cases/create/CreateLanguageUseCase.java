package org.example.application.language.use_cases.create;

import org.example.application.language.ports.out.LanguageRepository;
import org.example.domain.model.language.FileExtension;
import org.example.domain.model.language.Language;
import org.example.domain.model.language.LanguageName;
import org.example.domain.model.language.Version;

import java.util.UUID;

class CreateLanguageUseCase implements CreateLanguageInputBoundary {
    private final LanguageRepository languageRepository;

    public CreateLanguageUseCase(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public UUID execute(CreateLanguageCommand command) {
        if(languageRepository.existsByName(command.name())){
            throw new LanguageAlreadyExistsException("Language already exists with name: " + command.name());
        }
        var language = Language.create(command.name(), Version.of(command.version()), FileExtension.of(command.extension()));
        languageRepository.save(language);
        return language.getId().value();
    }
}
