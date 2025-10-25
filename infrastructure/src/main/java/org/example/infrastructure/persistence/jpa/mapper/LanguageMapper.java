package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.language.Language;
import org.example.domain.language.LanguageId;
import org.example.domain.language.RuntimeImage;
import org.example.domain.language.Version;
import org.example.infrastructure.persistence.jpa.model.LanguageEntity;

public class LanguageMapper {
    public static Language map(LanguageEntity languageEntity) {
        return new Language(
                LanguageId.of(languageEntity.getId()),
                languageEntity.getName(),
                new Version(languageEntity.getVersion()),
                new RuntimeImage(languageEntity.getRuntimeImage())
        );
    }

    public static LanguageEntity map(Language language) {
        return LanguageEntity.builder()
                .id(language.getId().value())
                .version(language.getVersion().value())
                .runtimeImage(language.getRuntimeImage().value())
                .name(language.getName())
                .build();
    }
}
