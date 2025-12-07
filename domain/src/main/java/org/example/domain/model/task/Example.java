package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

public record Example(ExampleId exampleId, Input input, Output output, String explanation) {
    public Example{
        ValidationUtils.requireNonNull(exampleId, "ExampleId cannot be null");
        ValidationUtils.requireNonNull(input, "Input cannot be null");
        ValidationUtils.requireNonNull(output, "Output cannot be null");
        ValidationUtils.requireNonBlank(explanation, "Explanation cannot be null or blank");
    }


    public static Example create(Input input, Output output, String explanation) {
        return new Example(ExampleId.generate(), input, output, explanation);
    }
}
