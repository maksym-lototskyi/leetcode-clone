package org.example.infrastructure.persistence.jpa.mapper;

import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.domain.model.class_definition.ClassImplementation;
import org.example.domain.model.class_definition.ClassImplementationId;
import org.example.domain.model.language.Language;
import org.example.domain.model.language.LanguageId;
import org.example.infrastructure.persistence.jpa.model.ClassImplementationEntity;
import org.example.infrastructure.persistence.jpa.model.LanguageEntity;
import org.springframework.stereotype.Component;

@Component
public class ClassImplementationMapper {
    private final LanguageRepository languageRepository;

    public ClassImplementationMapper(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public static ClassImplementation map(ClassImplementationEntity entity){
        return new ClassImplementation(
                ClassImplementationId.of(entity.getId()),
                LanguageId.of(entity.getLanguage().getId()),
                entity.getSourceCode()
        );
    }

    public ClassImplementationEntity map(ClassImplementation implementation){
        Language language = languageRepository.findById(implementation.languageId())
                .orElseThrow(() -> new NotFoundException("Language not found with id: " + implementation.languageId().value()));
        ClassImplementationEntity entity = new ClassImplementationEntity();
        entity.setId(implementation.id().value());
        entity.setLanguage(LanguageMapper.map(language));
        entity.setSourceCode(implementation.sourceCode());
        return entity;
    }
}
