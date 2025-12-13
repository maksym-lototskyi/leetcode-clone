package org.example.application.task.use_cases.create_draft;

import java.util.UUID;

public interface CreateDraftTaskInputBoundary {
    UUID execute(CreateDraftTaskCommand command);
}
