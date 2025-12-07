package org.example.domain.model.submission;

public sealed interface SubmissionResult permits AcceptedResult, FailedSubmissionResult {
    SubmissionResultStatus status();

    static SubmissionResult accepted(long runtimeInMs, long memoryInKb, int testCasesPassed, int totalTestCases) {
        return new AcceptedResult(runtimeInMs, memoryInKb,  testCasesPassed, totalTestCases);
    }

    static SubmissionResult wrongAnswer(TestRun failingTestCase, int testCasesPassed, int totalTestCases) {
        return new WrongAnswerResult( failingTestCase, testCasesPassed, totalTestCases);
    }

    static SubmissionResult compileError(TestRun failingTestCase, String errorMessage, int testCasesPassed, int totalTestCases) {
        return new CompileErrorResult(failingTestCase, errorMessage, testCasesPassed, totalTestCases);
    }

    static SubmissionResult runtimeError(TestRun failingTestCase, String errorMessage, int testCasesPassed, int totalTestCases) {
        return new RuntimeErrorResult(failingTestCase, errorMessage, testCasesPassed, totalTestCases);
    }

    static SubmissionResult timeLimitExceeded(TestRun failingTestCase, int testCasesPassed, int totalTestCases) {
        return new TimeLimitExceededResult(failingTestCase, testCasesPassed, totalTestCases);
    }

    static SubmissionResult memoryLimitExceeded(TestRun failingTestCase, int testCasesPassed, int totalTestCases) {
        return new MemoryLimitExceededResult(failingTestCase, testCasesPassed, totalTestCases);
    }
}
