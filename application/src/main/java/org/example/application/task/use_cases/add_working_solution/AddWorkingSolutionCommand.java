package org.example.application.task.use_cases.add_working_solution;

import java.util.UUID;

public record AddWorkingSolutionCommand(
        UUID taskId,
        String workingSolutionCode,
        String programmingLanguage
) {
}
