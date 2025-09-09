package org.example.domain.task;

import org.example.domain.language.LanguageId;

public record WorkingSolution(
    TaskId taskId,
    LanguageId language,
    String sourceCode
) {
}
