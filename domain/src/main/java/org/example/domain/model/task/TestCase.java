package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

public record TestCase(TestCaseId testCaseId, Input input, Output expectedOutput) {
    public TestCase{
        ValidationUtils.requireNonNull(testCaseId, "TestCaseId cannot be null");
        ValidationUtils.requireNonNull(input, "Input cannot be null");
        ValidationUtils.requireNonNull(expectedOutput, "ExpectedOutput cannot be null");
    }

    public static TestCase create(Input input, Output expectedOutput) {
        return new TestCase(TestCaseId.generate(), input, expectedOutput);
    }
}
