package org.example.application.language.ports.out;

import org.example.domain.model.language.Language;
import org.example.domain.model.language.LanguageId;
import org.example.domain.model.language.LanguageName;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository {
    void save(Language language);
    Optional<Language> findByName(LanguageName name);
    Optional<Language> findById(LanguageId id);
    boolean existsByName(LanguageName name);

    List<Language> findAllSupportedLanguages();
}
