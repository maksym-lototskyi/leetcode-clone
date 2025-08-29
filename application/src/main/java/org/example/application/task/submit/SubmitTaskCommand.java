package org.example.application.task.submit;

import java.util.UUID;

public record SubmitTaskCommand(UUID taskId, String sourceCode, String language, UUID userId) {
}
