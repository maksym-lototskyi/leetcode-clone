package org.example.domain.model.task.example;

import org.example.domain.model.task.TaskId;

import java.util.Objects;

public record Example(ExampleId exampleId, TaskId taskId, String input, String output, String explanation) {
    public Example {
        Objects.requireNonNull(taskId, "TaskId cannot be null");
        Objects.requireNonNull(exampleId, "ExampleId cannot be null");
        Objects.requireNonNull(input, "Input cannot be null");
        Objects.requireNonNull(output, "Output cannot be null");
    }

    public static Example create(TaskId taskId, String input, String output, String explanation) {
        return new Example(ExampleId.generate(), taskId, input, output, explanation);
    }
}
