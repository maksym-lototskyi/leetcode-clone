package org.example.application.task.use_cases.get_task_definition;


import java.util.Optional;
import java.util.UUID;

public record GetTaskDefinitionRequest(UUID taskId, Optional<String> languageName) {

}
