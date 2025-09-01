package org.example.domain.task;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Example{
    private final ExampleId exampleId;
    private final TaskId taskId;
    private final Input input;
    private final Output output;
    private final String explanation;
    Example(ExampleId exampleId, TaskId taskId, Input input, Output output, String explanation) {
        this.exampleId = exampleId;
        this.taskId = taskId;
        this.input = input;
        this.output = output;
        this.explanation = explanation;
        Objects.requireNonNull(taskId, "TaskId cannot be null");
        Objects.requireNonNull(exampleId, "ExampleId cannot be null");
        Objects.requireNonNull(input, "Input cannot be null");
        Objects.requireNonNull(output, "Output cannot be null");
        Objects.requireNonNull(explanation, "Explanation cannot be null");
    }


    public static Example create(TaskId taskId, Input input, Output output, String explanation) {
        return new Example(ExampleId.generate(), taskId, input, output, explanation);
    }
}
