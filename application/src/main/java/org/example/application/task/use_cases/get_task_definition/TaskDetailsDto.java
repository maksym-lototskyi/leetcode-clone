package org.example.application.task.use_cases.get_task_definition;

import org.example.domain.model.task.TaskLevel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TaskDetailsDto(
        UUID taskId,
        String title,
        String description,
        String starterCode,
        TaskLevel level,
        List<String> topics,
        List<ExampleDto> examples,
        List<String> constraints,
        List<String> hints
) {
}
