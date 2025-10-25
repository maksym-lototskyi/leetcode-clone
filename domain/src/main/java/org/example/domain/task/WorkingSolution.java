package org.example.domain.task;

import org.example.domain.language.LanguageId;
import org.example.domain.task.service.WorkingSolutionValidator;

public record WorkingSolution(
    String runtimeImage,
    String sourceCode
) {
    public WorkingSolution {
        if (runtimeImage == null || runtimeImage.isBlank()) {
            throw new IllegalArgumentException("Runtime image cannot be null or blank");
        }
        if (sourceCode == null || sourceCode.isBlank()) {
            throw new IllegalArgumentException("Source code cannot be null or blank");
        }
    }

    public static WorkingSolution of(String runtimeImage, String sourceCode) {
        return new WorkingSolution(runtimeImage, sourceCode);
    }
}
