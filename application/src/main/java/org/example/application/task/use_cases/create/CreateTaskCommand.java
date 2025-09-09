package org.example.application.task.use_cases.create;
import java.util.List;
import java.util.UUID;

public record CreateTaskCommand(
        String description,
        String taskLevel,
        List<UUID> topicIds,
        List<String> constraints,
        long timeLimitMs,
        long memoryLimitKb
) {}

