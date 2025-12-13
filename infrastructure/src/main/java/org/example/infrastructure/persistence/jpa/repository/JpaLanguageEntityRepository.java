package org.example.infrastructure.persistence.jpa.repository;

import org.example.domain.model.language.LanguageName;
import org.example.infrastructure.persistence.jpa.model.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface JpaLanguageEntityRepository extends JpaRepository<LanguageEntity, UUID> {
    Optional<LanguageEntity> findByName(LanguageName name);

    boolean existsByName(LanguageName name);
}
