package org.example.domain.model.submission;

public record SubmissionResult(
        SubmissionResultStatus status,
        Long runtimeInMs,
        Long memoryInKb,
        String failingTestCase,
        String errorMessage
) {
    public SubmissionResult {
        if (status == null) throw new IllegalArgumentException("Status cannot be null");
    }

    public static SubmissionResult accepted(long runtimeInMs, long memoryInKb) {
        if (runtimeInMs < 0 || memoryInKb < 0) {
            throw new IllegalArgumentException("Runtime and memory must be non-negative");
        }
        return new SubmissionResult(SubmissionResultStatus.ACCEPTED, runtimeInMs, memoryInKb, null, null);
    }

    public static SubmissionResult wrongAnswer(String failingTestCase) {
        if (failingTestCase == null || failingTestCase.isEmpty()) {
            throw new IllegalArgumentException("Failing test case cannot be null or empty");
        }
        return new SubmissionResult(SubmissionResultStatus.WRONG_ANSWER, null, null, failingTestCase, null);
    }

    public static SubmissionResult compileError(String errorMessage) {
        if (errorMessage == null || errorMessage.isEmpty()) {
            throw new IllegalArgumentException("Error message cannot be null or empty");
        }
        return new SubmissionResult(SubmissionResultStatus.COMPILE_ERROR, null, null, null, errorMessage);
    }

    public static SubmissionResult runtimeError(String errorMessage) {
        if (errorMessage == null || errorMessage.isEmpty()) {
            throw new IllegalArgumentException("Error message cannot be null or empty");
        }
        return new SubmissionResult(SubmissionResultStatus.RUNTIME_ERROR, null, null, null, errorMessage);
    }

    public static SubmissionResult timeLimitExceeded(String failingTestCase) {
        if (failingTestCase == null || failingTestCase.isEmpty()) {
            throw new IllegalArgumentException("Failing test case cannot be null or empty");
        }
        return new SubmissionResult(SubmissionResultStatus.TIME_LIMIT_EXCEEDED, null, null, failingTestCase, null);
    }

    public static SubmissionResult memoryLimitExceeded(String failingTestCase) {
        if (failingTestCase == null || failingTestCase.isEmpty()) {
            throw new IllegalArgumentException("Failing test case cannot be null or empty");
        }
        return new SubmissionResult(SubmissionResultStatus.MEMORY_LIMIT_EXCEEDED, null, null, failingTestCase, null);
    }
}
