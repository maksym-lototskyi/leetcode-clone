package org.example.domain.model.class_definition;

import org.example.domain.model.language.LanguageId;
import org.example.domain.validation.ValidationUtils;

public record ClassImplementation(
        ClassImplementationId id,
        LanguageId languageId,
        String sourceCode) {
    public ClassImplementation {
        ValidationUtils.requireNonNull(id, "ClassImplementationId cannot be null");
        ValidationUtils.requireNonNull(languageId, "LanguageId cannot be null");
        ValidationUtils.requireNonBlank(sourceCode, "Source code cannot be null or blank");
    }
}
