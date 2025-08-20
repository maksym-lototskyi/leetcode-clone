package org.example.domain.model.submission;

import java.util.Optional;

public record SubmissionResult(
        SubmissionResultStatus status,
        Optional<Long> runtimeInMs,
        Optional<Long> memoryInKb,
        Optional<String> failingTestCase,
        Optional<String> errorMessage
) {
    public SubmissionResult {
        if (status == null) throw new IllegalArgumentException("Status cannot be null");
        runtimeInMs = runtimeInMs == null ? Optional.empty() : runtimeInMs;
        memoryInKb = memoryInKb == null ? Optional.empty() : memoryInKb;
        failingTestCase = failingTestCase == null ? Optional.empty() : failingTestCase;
        errorMessage = errorMessage == null ? Optional.empty() : errorMessage;
    }

    public static SubmissionResult accepted(long runtimeInMs, long memoryInKb) {
        if (runtimeInMs < 0 || memoryInKb < 0) {
            throw new IllegalArgumentException("Runtime and memory must be non-negative");
        }
        return new SubmissionResult(
                SubmissionResultStatus.ACCEPTED,
                Optional.of(runtimeInMs),
                Optional.of(memoryInKb),
                Optional.empty(),
                Optional.empty()
        );
    }

    public static SubmissionResult wrongAnswer(String failingTestCase) {
        if (failingTestCase == null || failingTestCase.isEmpty()) {
            throw new IllegalArgumentException("Failing test case cannot be null or empty");
        }
        return new SubmissionResult(
                SubmissionResultStatus.WRONG_ANSWER,
                Optional.empty(),
                Optional.empty(),
                Optional.of(failingTestCase),
                Optional.empty()
        );
    }

    public static SubmissionResult compileError(String errorMessage) {
        if (errorMessage == null || errorMessage.isEmpty()) {
            throw new IllegalArgumentException("Error message cannot be null or empty");
        }
        return new SubmissionResult(
                SubmissionResultStatus.COMPILE_ERROR,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(errorMessage)
        );
    }

    public static SubmissionResult runtimeError(String errorMessage) {
        if (errorMessage == null || errorMessage.isEmpty()) {
            throw new IllegalArgumentException("Error message cannot be null or empty");
        }
        return new SubmissionResult(
                SubmissionResultStatus.RUNTIME_ERROR,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(errorMessage)
        );
    }

    public static SubmissionResult timeLimitExceeded(String failingTestCase) {
        if (failingTestCase == null || failingTestCase.isEmpty()) {
            throw new IllegalArgumentException("Failing test case cannot be null or empty");
        }
        return new SubmissionResult(
                SubmissionResultStatus.TIME_LIMIT_EXCEEDED,
                Optional.empty(),
                Optional.empty(),
                Optional.of(failingTestCase),
                Optional.empty()
        );
    }

    public static SubmissionResult memoryLimitExceeded(String failingTestCase) {
        if (failingTestCase == null || failingTestCase.isEmpty()) {
            throw new IllegalArgumentException("Failing test case cannot be null or empty");
        }
        return new SubmissionResult(
                SubmissionResultStatus.MEMORY_LIMIT_EXCEEDED,
                Optional.empty(),
                Optional.empty(),
                Optional.of(failingTestCase),
                Optional.empty()
        );
    }
}

