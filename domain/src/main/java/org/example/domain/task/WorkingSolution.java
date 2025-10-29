package org.example.domain.task;

import org.example.domain.language.LanguageId;
import org.example.domain.task.service.WorkingSolutionValidator;

public record WorkingSolution(
    LanguageId languageId,
    String sourceCode
) {
    public WorkingSolution {
        if(languageId == null) {
            throw new IllegalArgumentException("LanguageId cannot be null");
        }
        if (sourceCode == null || sourceCode.isBlank()) {
            throw new IllegalArgumentException("Source code cannot be null or blank");
        }
    }

    public static WorkingSolution of(LanguageId languageId, String sourceCode) {
        return new WorkingSolution(languageId, sourceCode);
    }
}
