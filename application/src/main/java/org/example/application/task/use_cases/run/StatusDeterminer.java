package org.example.application.task.use_cases.run;

import org.example.domain.submission.SubmissionResultStatus;

public class StatusDeterminer {
    public static SubmissionResultStatus determineStatus(TestRunResult userResult, String expectedOutput, long timeLimitMs, long memoryLimitKb) {
        SubmissionResultStatus status = SubmissionResultStatus.ACCEPTED;

        if(userResult.errorType() != null && userResult.errorType() == ErrorType.RUNTIME_ERROR) status = SubmissionResultStatus.RUNTIME_ERROR;
        else if(userResult.errorType() != null && userResult.errorType() == ErrorType.COMPILATION_ERROR) status = SubmissionResultStatus.COMPILE_ERROR;
        else if(!userResult.output().equals(expectedOutput)) status = SubmissionResultStatus.WRONG_ANSWER;
        else if(userResult.executionTimeMs() > timeLimitMs) status = SubmissionResultStatus.TIME_LIMIT_EXCEEDED;
        else if(userResult.memoryUsageKb() > memoryLimitKb) status = SubmissionResultStatus.MEMORY_LIMIT_EXCEEDED;
        return status;
    }
}
