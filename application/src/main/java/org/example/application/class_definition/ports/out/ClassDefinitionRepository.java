package org.example.application.class_definition.ports.out;

import org.example.domain.class_definition.ClassDefinition;
import org.example.domain.class_definition.ClassDefinitionId;

import java.util.Collection;
import java.util.List;

public interface ClassDefinitionRepository {
    List<ClassDefinition> getAllByNames(Iterable<String> names);
    List<String> findExistingNames(Iterable<String> names);
    List<ClassDefinitionId> findIdsByNames(Iterable<String> names);

    List<ClassDefinition> findAllByIds(Collection<ClassDefinitionId> relatedClassDefinitions);
}
