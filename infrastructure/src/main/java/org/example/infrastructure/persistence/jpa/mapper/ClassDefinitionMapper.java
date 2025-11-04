package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.class_definition.ClassDefinition;
import org.example.domain.class_definition.ClassDefinitionId;
import org.example.infrastructure.persistence.jpa.model.ClassDefinitionEntity;

import java.util.Optional;
import java.util.stream.Collectors;

public class ClassDefinitionMapper {
    public static ClassDefinition map(ClassDefinitionEntity entity){
        return new ClassDefinition(
                ClassDefinitionId.of(entity.getId()),
                entity.getName(),
                entity.getImplementations().stream()
                        .map(implEntity -> ClassImplementationMapper.map(implEntity))
                        .collect(Collectors.toSet())
        );
    }
}
