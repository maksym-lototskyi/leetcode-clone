package org.example.infrastructure.persistence.jpa.repository;

import org.example.application.language.ports.out.LanguageRepository;
import org.example.domain.model.language.Language;
import org.example.domain.model.language.LanguageId;
import org.example.domain.model.language.LanguageName;
import org.example.infrastructure.persistence.jpa.mapper.LanguageMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class JpaLanguageRepository implements LanguageRepository {
    private final JpaLanguageEntityRepository jpaLanguageEntityRepository;

    public JpaLanguageRepository(JpaLanguageEntityRepository jpaLanguageEntityRepository) {
        this.jpaLanguageEntityRepository = jpaLanguageEntityRepository;
    }

    @Override
    public void save(Language language) {
        var entity = LanguageMapper.map(language);
        jpaLanguageEntityRepository.save(entity);
    }

    @Override
    public Optional<Language> findByName(LanguageName name) {
        return jpaLanguageEntityRepository.findByName(name)
                .map(LanguageMapper::map);
    }

    @Override
    public Optional<Language> findById(LanguageId id) {
        return jpaLanguageEntityRepository.findById(id.value())
                .map(LanguageMapper::map);
    }

    @Override
    public boolean existsByName(LanguageName name) {
        return jpaLanguageEntityRepository.existsByName(name);
    }

    @Override
    public List<Language> findAllSupportedLanguages() {
        return jpaLanguageEntityRepository.findAll()
                .stream()
                .map(LanguageMapper::map)
                .toList();
    }
}
