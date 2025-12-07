package org.example.domain.model.submission;

import org.example.domain.validation.ValidationUtils;

public record MemoryLimitExceededResult(TestRun failingTestCase, int testCasesPassed, int totalTestCases) implements SubmissionResult {
    public MemoryLimitExceededResult {
        ValidationUtils.requireNonNull(failingTestCase, "failingTestCase cannot be null");
        ValidationUtils.requirePositiveNumber(totalTestCases, "totalTestCases must be positive");
        ValidationUtils.requireNonNegative(testCasesPassed, "testCasesPassed cannot be negative");
        ValidationUtils.requireLessThen(testCasesPassed, totalTestCases, "testCasesPassed must be less than totalTestCases");
    }

    @Override
    public SubmissionResultStatus status() {
        return SubmissionResultStatus.MEMORY_LIMIT_EXCEEDED;
    }
}
