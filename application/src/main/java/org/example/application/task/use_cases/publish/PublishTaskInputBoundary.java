package org.example.application.task.use_cases.publish;

import java.util.UUID;

public interface PublishTaskInputBoundary {
    void execute(UUID taskId);
}
