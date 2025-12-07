package org.example.domain.model.submission;

import org.example.domain.model.task.TestCaseId;
import org.example.domain.validation.ValidationUtils;

public record RuntimeErrorResult(TestRun failingTestCase,
                                 String compileErrorMessage,
                                 int testCasesPassed,
                                 int totalTestCases) implements FailedSubmissionResult {
    public RuntimeErrorResult {
        ValidationUtils.requireNonNull(failingTestCase, "failingTestCase cannot be null");
        ValidationUtils.requireNonBlank(compileErrorMessage, "compileErrorMessage cannot be null or blank");
        ValidationUtils.requirePositiveNumber(totalTestCases, "totalTestCases must be positive");
        ValidationUtils.requireNonNegative(testCasesPassed, "testCasesPassed cannot be negative");
        ValidationUtils.requireLessThen(testCasesPassed, totalTestCases, "testCasesPassed must be less than totalTestCases");
    }

    @Override
    public SubmissionResultStatus status() {
        return SubmissionResultStatus.RUNTIME_ERROR;
    }

    @Override
    public TestCaseId failingTestCaseId() {
        return failingTestCase.testCaseId();
    }
}
