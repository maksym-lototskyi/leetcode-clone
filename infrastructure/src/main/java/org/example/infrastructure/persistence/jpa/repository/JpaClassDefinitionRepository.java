package org.example.infrastructure.persistence.jpa.repository;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.domain.model.class_definition.ClassDefinition;
import org.example.domain.model.class_definition.ClassDefinitionId;
import org.example.infrastructure.persistence.jpa.mapper.ClassDefinitionMapper;
import org.example.infrastructure.persistence.jpa.model.ClassDefinitionEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaClassDefinitionRepository implements ClassDefinitionRepository {
    private final JpaClassDefinitionEntityRepository jpaRepository;

    public JpaClassDefinitionRepository(JpaClassDefinitionEntityRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<ClassDefinition> getAllByNames(Iterable<String> names) {
        return null;
    }

    @Override
    public List<String> findExistingNames(Iterable<String> names) {
        return null;
    }

    @Override
    public List<ClassDefinitionId> findIdsByNames(Iterable<String> names) {
        return null;
    }

    @Override
    public List<ClassDefinition> findAllByIds(Collection<ClassDefinitionId> relatedClassDefinitions) {
        List<ClassDefinitionEntity> definitionEntities =  jpaRepository.findAllById(relatedClassDefinitions.stream().map(ClassDefinitionId::id).toList());
        return definitionEntities.stream().map(ClassDefinitionMapper::map).toList();
    }

    @Override
    public Optional<ClassDefinition> findById(ClassDefinitionId classDefinitionId) {
        return jpaRepository.findById(classDefinitionId.id()).map(ClassDefinitionMapper::map);
    }
}
