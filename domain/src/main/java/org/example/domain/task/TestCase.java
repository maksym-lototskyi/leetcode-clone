package org.example.domain.task;

import lombok.Getter;

import java.util.Objects;

@Getter
public record TestCase(TestCaseId testCaseId, TaskId taskId, Input input, Output expectedOutput) {
    public TestCase{
        Objects.requireNonNull(testCaseId, "TestCaseId cannot be null");
        Objects.requireNonNull(taskId, "TaskId cannot be null");
        Objects.requireNonNull(input, "Input cannot be null");
        Objects.requireNonNull(expectedOutput, "ExpectedOutput cannot be null");
    }

    public static TestCase create(TaskId taskId, Input input, Output expectedOutput) {
        return new TestCase(TestCaseId.generate(), taskId, input, expectedOutput);
    }
}
