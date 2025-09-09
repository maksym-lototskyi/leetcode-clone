package org.example.application.task.use_cases.run;

import java.util.List;
import java.util.UUID;

public record RunTaskCommand(UUID taskId, List<String> rawInputs, String sourceCode, String language) {
}
