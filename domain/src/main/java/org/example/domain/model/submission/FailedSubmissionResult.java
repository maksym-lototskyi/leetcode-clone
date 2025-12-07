package org.example.domain.model.submission;

import org.example.domain.model.task.TestCaseId;

public sealed interface FailedSubmissionResult extends SubmissionResult permits
        CompileErrorResult, MemoryLimitExceededResult, RuntimeErrorResult, TimeLimitExceededResult, WrongAnswerResult{
    TestCaseId failingTestCaseId();
}
