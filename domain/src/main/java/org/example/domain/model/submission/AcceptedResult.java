package org.example.domain.model.submission;

import org.example.domain.model.task.DraftTask;
import org.example.domain.model.task.Task;
import org.example.domain.validation.ValidationUtils;

public record AcceptedResult(Long runtimeInMs,
                             Long memoryInKb,
                             int testCasesPassed,
                             int totalTestCases) implements SubmissionResult{
    public AcceptedResult{
        ValidationUtils.requirePositiveNumber(totalTestCases, "Total test cases must be positive");
        ValidationUtils.requirePositiveNumber(testCasesPassed, "Test cases passed must be positive");
        ValidationUtils.requireEqual(testCasesPassed, totalTestCases, "Test cases passed must be equal to total test cases for an accepted result");
        ValidationUtils.requireNonNegative(runtimeInMs, "Runtime must be non-negative or null");
        ValidationUtils.requireNonNegative(memoryInKb, "Memory must be non-negative or null");
    }

    @Override
    public SubmissionResultStatus status() {
        return SubmissionResultStatus.ACCEPTED;
    }
}
