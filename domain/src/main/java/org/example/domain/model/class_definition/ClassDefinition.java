package org.example.domain.model.class_definition;

import lombok.Getter;
import org.example.domain.model.language.LanguageId;
import org.example.domain.validation.ValidationUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class ClassDefinition {

    @Getter
    private final ClassDefinitionId id;
    @Getter
    private final ClassName className;
    private final Set<ClassImplementation> implementations = new HashSet<>();

    public ClassDefinition(
            ClassDefinitionId id,
            ClassName className,
            Set<ClassImplementation> implementations
    ) {
        ValidationUtils.requireNonNull(id, "ClassDefinitionId cannot be null");
        ValidationUtils.requireNonNull(className, "ClassName cannot be null");
        ValidationUtils.requireNonEmptyCollection(implementations, "Implementations set cannot be empty");

        if (implementations.stream()
                .map(ClassImplementation::languageId)
                .distinct()
                .count() != implementations.size()) {
            throw new DuplicateLanguageImplementationException("Duplicate language implementations");
        }

        this.id = id;
        this.className = className;
        this.implementations.addAll(implementations);
    }


    public void addImplementation(ClassImplementation implementation) {
        ValidationUtils.requireNonNull(implementation, "Implementation cannot be null");
        if (hasImplementationFor(implementation.languageId())) {
            throw new DuplicateLanguageImplementationException("Implementation already exists for this language");
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

