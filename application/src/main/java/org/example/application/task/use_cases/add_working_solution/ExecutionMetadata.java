package org.example.application.task.use_cases.add_working_solution;

import org.example.domain.model.task.TaskSignature;

public record ExecutionMetadata(String sourceCode, TaskSignature signature, long timeLimitMs, long memoryLimitMb) {
}
