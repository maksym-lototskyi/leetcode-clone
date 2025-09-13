package org.example.application.task.repos;

import org.example.application.language.ports.out.LanguageRepository;
import org.example.domain.language.Language;
import org.example.domain.language.LanguageId;

import java.util.Optional;

public class TestLanguageRepository implements LanguageRepository {
    Language language = new Language("Java", "21", "oracle:21");
    @Override
    public Optional<Language> findByName(String name) {
        return Optional.of(language);
    }

    @Override
    public Optional<Language> findById(LanguageId id) {
        return Optional.of(language);
    }
}
