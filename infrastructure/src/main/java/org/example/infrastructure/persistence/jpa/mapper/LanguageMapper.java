package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.language.*;
import org.example.infrastructure.persistence.jpa.model.LanguageEntity;

public class LanguageMapper {
    public static Language map(LanguageEntity languageEntity) {
        return new Language(
                LanguageId.of(languageEntity.getId()),
                LanguageName.of(languageEntity.getName()),
                new Version(languageEntity.getVersion()),
                FileExtension.of(languageEntity.getFileExtension())
        );
    }

    public static LanguageEntity map(Language language) {
        return LanguageEntity.builder()
                .id(language.getId().value())
                .version(language.getVersion().value())
                .name(language.getName().value())
                .fileExtension(language.getFileExtension().value())
                .build();
    }
}
