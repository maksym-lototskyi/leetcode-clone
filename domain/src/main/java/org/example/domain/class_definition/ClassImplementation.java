package org.example.domain.class_definition;

import org.example.domain.language.LanguageId;

public record ClassImplementation(
        ClassImplementationId id,
        String className,
        LanguageId languageId,
        String sourceCode) {
}
