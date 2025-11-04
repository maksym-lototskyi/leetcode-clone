package org.example.domain.task;

import org.example.domain.language.LanguageId;

public record WorkingSolution(
        WorkingSolutionId id,
        LanguageId languageId,
        String sourceCode
) {
    public WorkingSolution {
        if(id == null) {
            throw new IllegalArgumentException("WorkingSolutionId cannot be null");
        }
        if(languageId == null) {
            throw new IllegalArgumentException("LanguageId cannot be null");
        }
        if (sourceCode == null || sourceCode.isBlank()) {
            throw new IllegalArgumentException("Source code cannot be null or blank");
        }
    }

    public static WorkingSolution create(LanguageId languageId, String sourceCode) {
        return new WorkingSolution(WorkingSolutionId.generate(), languageId, sourceCode);
    }

    public static WorkingSolution of(WorkingSolutionId id, LanguageId languageId, String sourceCode) {
        return new WorkingSolution(id, languageId, sourceCode);
    }
}
