package org.example.application.language.ports.out;

import org.example.domain.language.Language;
import org.example.domain.language.LanguageId;

import java.util.Optional;

public interface LanguageRepository {
    Optional<Language> findByName(String name);
    Optional<Language> findById(LanguageId id);
}
