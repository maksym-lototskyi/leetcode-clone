package org.example.domain.submission;

public record SubmissionResult(
        SubmissionResultStatus status,
        Long runtimeInMs,
        Long memoryInKb,
        TestRun failingTestCase,
        String errorMessage,
        int testCasesPassed,
        int totalTestCases
) {
    public SubmissionResult {
        if (status == null) throw new IllegalArgumentException("Status cannot be null");
    }

    public static SubmissionResult accepted(long runtimeInMs, long memoryInKb, int testCasesPassed, int totalTestCases) {
        if (runtimeInMs < 0 || memoryInKb < 0) {
            throw new IllegalArgumentException("Runtime and memory must be non-negative");
        }
        return new SubmissionResult(SubmissionResultStatus.ACCEPTED, runtimeInMs, memoryInKb, null, null, testCasesPassed, totalTestCases);
    }

    public static SubmissionResult wrongAnswer(TestRun failingTestCase, int testCasesPassed, int totalTestCases) {
        if (failingTestCase == null) {
            throw new IllegalArgumentException("Failing test case cannot be null");
        }
        return new SubmissionResult(SubmissionResultStatus.WRONG_ANSWER, null, null, failingTestCase, null, testCasesPassed, totalTestCases);
    }

    public static SubmissionResult compileError(String errorMessage, int testCasesPassed, int totalTestCases) {
        if (errorMessage == null || errorMessage.isEmpty()) {
            throw new IllegalArgumentException("Error message cannot be null or empty");
        }
        return new SubmissionResult(SubmissionResultStatus.COMPILE_ERROR, null, null, null, errorMessage, testCasesPassed, totalTestCases);
    }

    public static SubmissionResult runtimeError(String errorMessage, int testCasesPassed, int totalTestCases) {
        if (errorMessage == null || errorMessage.isEmpty()) {
            throw new IllegalArgumentException("Error message cannot be null or empty");
        }
        return new SubmissionResult(SubmissionResultStatus.RUNTIME_ERROR, null, null, null, errorMessage, testCasesPassed, totalTestCases);
    }

    public static SubmissionResult timeLimitExceeded(TestRun failingTestCase, int testCasesPassed, int totalTestCases) {
        if (failingTestCase == null) {
            throw new IllegalArgumentException("Failing test case cannot be null or empty");
        }
        return new SubmissionResult(SubmissionResultStatus.TIME_LIMIT_EXCEEDED, null, null, failingTestCase, null, testCasesPassed, totalTestCases);
    }

    public static SubmissionResult memoryLimitExceeded(TestRun failingTestCase, int testCasesPassed, int totalTestCases) {
        if (failingTestCase == null) {
            throw new IllegalArgumentException("Failing test case cannot be null or empty");
        }
        return new SubmissionResult(SubmissionResultStatus.MEMORY_LIMIT_EXCEEDED, null, null, failingTestCase, null, testCasesPassed, totalTestCases);
    }
}
