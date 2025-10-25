package org.example.domain.task;

import java.util.Objects;

public record Example(ExampleId exampleId, Input input, Output output, String explanation) {
    public Example{
        Objects.requireNonNull(exampleId, "ExampleId cannot be null");
        Objects.requireNonNull(input, "Input cannot be null");
        Objects.requireNonNull(output, "Output cannot be null");
        Objects.requireNonNull(explanation, "Explanation cannot be null");
    }


    public static Example create(Input input, Output output, String explanation) {
        return new Example(ExampleId.generate(), input, output, explanation);
    }
}
