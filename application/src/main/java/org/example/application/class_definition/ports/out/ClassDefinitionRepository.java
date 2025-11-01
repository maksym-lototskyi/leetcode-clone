package org.example.application.class_definition.ports.out;

import org.example.domain.class_definition.ClassDefinition;
import org.example.domain.class_definition.ClassDefinitionId;
import org.example.domain.class_definition.ClassImplementation;
import org.example.domain.language.LanguageId;

import java.util.List;
import java.util.Optional;

public interface ClassDefinitionRepository {
    List<ClassDefinition> getAllByNames(Iterable<String> names);
    List<String> findExistingNames(Iterable<String> names);
    Optional<ClassImplementation> findImplementationByDefinitionAndLanguageId(ClassDefinitionId classDefinitionId, LanguageId languageId);
}
