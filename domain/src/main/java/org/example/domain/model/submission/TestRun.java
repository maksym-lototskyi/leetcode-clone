package org.example.domain.model.submission;

import org.example.domain.model.task.TestCaseId;
import org.example.domain.validation.ValidationUtils;

import java.util.Objects;

public record TestRun(TestRunId testRunId, TestCaseId testCaseId, String actualOutput, boolean isPassed) {
    public TestRun {
        ValidationUtils.requireNonNull(testRunId, "testRunId cannot be null");
        ValidationUtils.requireNonNull(testCaseId, "testCaseId cannot be null");
        ValidationUtils.requireNonNull(actualOutput, "actualOutput cannot be null");
    }

    public static TestRun create(TestCaseId testCaseId, String actualOutput, boolean isPassed) {
        return new TestRun(TestRunId.generate(), testCaseId, actualOutput, isPassed);
    }

    public static TestRun failing(TestCaseId testCaseId, String actualOutput) {
        return new TestRun(TestRunId.generate(), testCaseId, actualOutput, false);
    }
}
