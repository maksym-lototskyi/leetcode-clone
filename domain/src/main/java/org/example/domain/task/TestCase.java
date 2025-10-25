package org.example.domain.task;

import lombok.Getter;

import java.util.Objects;

public record TestCase(TestCaseId testCaseId, Input input, Output expectedOutput) {
    public TestCase{
        Objects.requireNonNull(testCaseId, "TestCaseId cannot be null");
        Objects.requireNonNull(input, "Input cannot be null");
        Objects.requireNonNull(expectedOutput, "ExpectedOutput cannot be null");
    }

    public static TestCase create(Input input, Output expectedOutput) {
        return new TestCase(TestCaseId.generate(), input, expectedOutput);
    }
}
