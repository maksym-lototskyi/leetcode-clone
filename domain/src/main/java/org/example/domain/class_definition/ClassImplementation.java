package org.example.domain.class_definition;

import org.example.domain.language.LanguageId;

public record ClassImplementation(
        ClassImplementationId id,
        LanguageId languageId,
        String sourceCode) {
}
