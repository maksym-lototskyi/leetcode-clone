package org.example.domain.model.task;

import org.example.domain.model.language.LanguageId;
import org.example.domain.validation.ValidationUtils;

public record WorkingSolution(
        WorkingSolutionId id,
        LanguageId languageId,
        String sourceCode
) {
    public WorkingSolution {
        ValidationUtils.requireNonNull(id, "WorkingSolutionId cannot be null");
        ValidationUtils.requireNonNull(languageId, "LanguageId cannot be null");
        ValidationUtils.requireNonBlank(sourceCode, "Source code cannot be null or blank");
    }

    public static WorkingSolution create(LanguageId languageId, String sourceCode) {
        return new WorkingSolution(WorkingSolutionId.generate(), languageId, sourceCode);
    }

    public static WorkingSolution of(WorkingSolutionId id, LanguageId languageId, String sourceCode) {
        return new WorkingSolution(id, languageId, sourceCode);
    }
}
