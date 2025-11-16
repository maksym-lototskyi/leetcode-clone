package org.example.application.language.ports.out;

import org.example.domain.language.Language;
import org.example.domain.language.LanguageId;

import java.util.Optional;
import java.util.UUID;

public interface LanguageRepository {
    void save(Language language);
    Optional<Language> findByName(String name);
    Optional<Language> findById(LanguageId id);
    boolean existsByName(String name);
}
