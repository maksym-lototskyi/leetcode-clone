package org.example.domain.class_definition;

import org.example.domain.language.LanguageId;

import java.util.Optional;
import java.util.Set;

public record ClassDefinition(
        ClassDefinitionId id,
        String className,
        Set<ClassImplementation> implementations
) {

    public void addImplementation(ClassImplementation implementation) {
        if (hasImplementationFor(implementation.languageId())) {
            throw new IllegalArgumentException("Implementation already exists for this language");
        }
        implementations.add(implementation);
    }

    public boolean hasImplementationFor(LanguageId languageId) {
        return implementations.stream()
                .anyMatch(i -> i.languageId().equals(languageId));
    }

    public Optional<ClassImplementation> getImplementationFor(LanguageId languageId) {
        return implementations.stream()
                .filter(i -> i.languageId().equals(languageId))
                .findFirst();
    }
}

