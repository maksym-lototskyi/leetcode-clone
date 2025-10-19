package org.example.application.task.use_cases.submit;

import java.util.UUID;

public interface SubmitTaskInputBoundary {
    void execute(SubmitTaskCommand submitTaskCommand, UUID userId);
}
