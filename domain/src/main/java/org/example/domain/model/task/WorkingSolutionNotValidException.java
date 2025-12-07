package org.example.domain.model.task;

import lombok.Getter;
import org.example.domain.model.submission.FailedSubmissionResult;
import org.example.domain.model.submission.SubmissionResult;
import org.example.domain.model.submission.SubmissionResultStatus;
import org.example.domain.model.task.TestCaseId;

@Getter
public class WorkingSolutionNotValidException extends RuntimeException {
    private final FailedSubmissionResult result;
    public WorkingSolutionNotValidException(FailedSubmissionResult result) {
        super("Working solution is not valid: " + result.status() +
                " on test case " + result.failingTestCaseId().value());
        this.result = result;
    }
}
