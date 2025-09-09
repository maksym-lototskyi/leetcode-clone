package org.example.application.task.use_cases.submit;

import java.util.UUID;

public record SubmitTaskCommand(UUID taskId, String sourceCode, String language, UUID userId) {
}
