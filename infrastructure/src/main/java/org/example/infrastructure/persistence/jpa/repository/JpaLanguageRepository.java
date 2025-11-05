package org.example.infrastructure.persistence.jpa.repository;

import org.example.application.language.ports.out.LanguageRepository;
import org.example.domain.language.Language;
import org.example.domain.language.LanguageId;
import org.example.infrastructure.persistence.jpa.mapper.LanguageMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

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
    public Optional<Language> findByName(String name) {
        return jpaLanguageEntityRepository.findByName(name)
                .map(l -> LanguageMapper.map(l));
    }

    @Override
    public Optional<Language> findById(LanguageId id) {
        return jpaLanguageEntityRepository.findById(id.value())
                .map(l -> LanguageMapper.map(l));
    }

    @Override
    public boolean existsByName(String name) {
        return jpaLanguageEntityRepository.existsByName(name);
    }
}
