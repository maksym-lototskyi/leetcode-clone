package org.example.application.language.ports.out;

import org.example.domain.model.language.Language;

import java.util.Optional;

public interface LanguageRepository {
    Optional<Language> findByName(String name);
}
