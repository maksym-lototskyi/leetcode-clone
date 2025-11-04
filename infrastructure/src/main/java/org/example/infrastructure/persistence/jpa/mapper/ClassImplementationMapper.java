package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.class_definition.ClassImplementation;
import org.example.domain.class_definition.ClassImplementationId;
import org.example.domain.language.LanguageId;
import org.example.infrastructure.persistence.jpa.model.ClassImplementationEntity;

public class ClassImplementationMapper {
    public static ClassImplementation map(ClassImplementationEntity entity){
        return new ClassImplementation(
                ClassImplementationId.of(entity.getId()),
                LanguageId.of(entity.getLanguage().getId()),
                entity.getSourceCode()
        );
    }
}
