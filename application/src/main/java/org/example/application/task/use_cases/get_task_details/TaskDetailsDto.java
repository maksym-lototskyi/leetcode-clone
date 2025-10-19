package org.example.application.task.use_cases.get_task_details;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDetailsDto(
        UUID id,
        String title,
        String description,
        String status,
        LocalDateTime createdAt
) {
}
