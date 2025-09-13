package org.example.application.task.use_cases.add_example;

import java.util.List;
import java.util.UUID;

public record AddExampleCommand(UUID taskId, List<String> input, String output, String explanation) {

}
