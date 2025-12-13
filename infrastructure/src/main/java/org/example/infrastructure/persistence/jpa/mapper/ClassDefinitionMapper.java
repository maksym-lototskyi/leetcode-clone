package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.model.class_definition.ClassDefinition;
import org.example.domain.model.class_definition.ClassDefinitionId;
import org.example.domain.model.class_definition.ClassName;
import org.example.infrastructure.persistence.jpa.model.ClassDefinitionEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ClassDefinitionMapper {
    private final ClassImplementationMapper mapper;

    public ClassDefinitionMapper(ClassImplementationMapper mapper) {
        this.mapper = mapper;
    }

    public static ClassDefinition map(ClassDefinitionEntity entity){
        return new ClassDefinition(
                ClassDefinitionId.of(entity.getId()),
                ClassName.of(entity.getName()),
                entity.getImplementations().stream()
                        .map(implEntity -> ClassImplementationMapper.map(implEntity))
                        .collect(Collectors.toSet())
        );
    }

    public ClassDefinitionEntity map(ClassDefinition classDefinition){
        ClassDefinitionEntity entity = new ClassDefinitionEntity();
        entity.setId(classDefinition.getId().id());
        entity.setName(classDefinition.getClassName().value());
        entity.setImplementations(classDefinition.implementations().stream()
                .map(mapper::map)
                .collect(Collectors.toSet()));
        return entity;
    }
}
