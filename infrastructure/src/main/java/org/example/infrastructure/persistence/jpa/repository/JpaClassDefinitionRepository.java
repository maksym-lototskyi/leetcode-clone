package org.example.infrastructure.persistence.jpa.repository;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.domain.class_definition.ClassDefinition;
import org.example.domain.class_definition.ClassDefinitionId;
import org.example.domain.class_definition.ClassImplementation;
import org.example.domain.language.LanguageId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaClassDefinitionRepository implements ClassDefinitionRepository {
    @Override
    public List<ClassDefinition> getAllByNames(Iterable<String> names) {
        return null;
    }

    @Override
    public List<String> findExistingNames(Iterable<String> names) {
        return null;
    }

    @Override
    public Optional<ClassImplementation> findImplementationByDefinitionAndLanguageId(ClassDefinitionId classDefinitionId, LanguageId languageId) {
        return Optional.empty();
    }

    @Override
    public List<ClassImplementation> findClassImplementationsByDefinitionIdsAndLanguageId(Iterable<ClassDefinitionId> classDefinitionIds, LanguageId languageId) {
        return null;
    }

    @Override
    public List<ClassDefinition> findAllByIds(Iterable<ClassDefinitionId> relatedClassDefinitions) {
        return null;
    }
}
