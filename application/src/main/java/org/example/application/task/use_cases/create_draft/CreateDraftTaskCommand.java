package org.example.application.task.use_cases.create_draft;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record CreateDraftTaskCommand(
        UUID createdByUserId,
        String title,
        String functionName,
        String returnType,
        String description,
        String taskLevel,
        List<UUID> topicIds,
        List<String> constraints,
        List<String> parameterNames,
        List<String> parameterTypes,
        Set<String> classDefinitionNames,
        long timeLimitMs,
        long memoryLimitKb
) {}

