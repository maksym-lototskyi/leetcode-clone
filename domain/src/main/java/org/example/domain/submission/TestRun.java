package org.example.domain.submission;

import org.example.domain.task.TestCaseId;

import java.util.Objects;

public record TestRun(TestRunId testRunId, TestCaseId testCaseId, String actualOutput, boolean isPassed) {
    public TestRun {
        Objects.requireNonNull(testRunId);
        Objects.requireNonNull(testCaseId);
        Objects.requireNonNull(actualOutput);
    }

    public static TestRun create(TestCaseId testCaseId, String actualOutput, boolean isPassed) {
        return new TestRun(TestRunId.generate(), testCaseId, actualOutput, isPassed);
    }

    public static TestRun failing(TestCaseId testCaseId, String actualOutput) {
        return new TestRun(TestRunId.generate(), testCaseId, actualOutput, false);
    }
}
