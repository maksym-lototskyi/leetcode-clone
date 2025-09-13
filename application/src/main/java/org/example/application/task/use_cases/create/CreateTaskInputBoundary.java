package org.example.application.task.use_cases.create;

import java.util.UUID;

public interface CreateTaskInputBoundary {
    UUID execute(CreateTaskCommand command);
}
