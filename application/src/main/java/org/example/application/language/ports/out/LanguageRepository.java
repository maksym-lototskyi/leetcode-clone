package org.example.application.language.ports.out;

import org.example.domain.model.language.Language;
import org.example.domain.model.language.LanguageId;

import java.util.Optional;

public interface LanguageRepository {
    void save(Language language);
    Optional<Language> findByName(String name);
    Optional<Language> findById(LanguageId id);
    boolean existsByName(String name);
}
