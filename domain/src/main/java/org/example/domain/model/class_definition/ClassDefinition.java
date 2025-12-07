package org.example.domain.model.class_definition;

import org.example.domain.model.language.LanguageId;
import org.example.domain.validation.ValidationUtils;

import java.util.Optional;
import java.util.Set;

public record ClassDefinition(
        ClassDefinitionId id,
        String className,
        Set<ClassImplementation> implementations
) {
    public ClassDefinition{
        ValidationUtils.requireNonNull(id, "ClassDefinitionId cannot be null");
        ValidationUtils.requireNonBlank(className, "Class name cannot be null or blank");
        ValidationUtils.requireNonNull(implementations, "Implementations set cannot be null");
        ValidationUtils.requireNonEmptyCollection(implementations, "Implementations set cannot be empty");
    }

    public void addImplementation(ClassImplementation implementation) {
        ValidationUtils.requireNonNull(implementation, "Implementation cannot be null");
        if (hasImplementationFor(implementation.languageId())) {
            throw new IllegalArgumentException("Implementation already exists for this language");
        }
        implementations.add(implementation);
    }

    public boolean hasImplementationFor(LanguageId languageId) {
        ValidationUtils.requireNonNull(languageId, "LanguageId cannot be null");
        return implementations.stream()
                .anyMatch(i -> i.languageId().equals(languageId));
    }

    public Optional<ClassImplementation> getImplementationFor(LanguageId languageId) {
        ValidationUtils.requireNonNull(languageId, "LanguageId cannot be null");
        return implementations.stream()
                .filter(i -> i.languageId().equals(languageId))
                .findFirst();
    }
    public Set<ClassImplementation> implementations() {
        return Set.copyOf(implementations);
    }
}

