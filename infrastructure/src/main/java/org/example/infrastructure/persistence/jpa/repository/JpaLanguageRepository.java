package org.example.infrastructure.persistence.jpa.repository;

import org.example.application.language.ports.out.LanguageRepository;
import org.example.domain.language.Language;
import org.example.domain.language.LanguageId;

import java.util.Optional;

public class JpaLanguageRepository implements LanguageRepository {
    @Override
    public Optional<Language> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Language> findById(LanguageId id) {
        return Optional.empty();
    }
}
