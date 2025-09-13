package org.example.domain.task;

import org.example.domain.language.LanguageId;

public record WorkingSolution(
    String runtimeImage,
    String sourceCode
) {
}
