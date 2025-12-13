package org.example.application.task.use_cases.get_task_definition;

public interface GetTaskDefinitionInputBoundary {
    TaskDetailsDto execute(GetTaskDefinitionRequest request);
}
