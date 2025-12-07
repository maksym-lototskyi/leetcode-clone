package org.example.domain.model.task;

import lombok.Getter;
import org.example.domain.model.submission.SubmissionResultStatus;
import org.example.domain.model.task.TestCaseId;

@Getter
public class WorkingSolutionNotValidException extends RuntimeException {
    private final TestCaseId testCaseId;
    private final SubmissionResultStatus status;
    public WorkingSolutionNotValidException(TestCaseId testCaseId, SubmissionResultStatus status) {
        super("Working solution is not valid for test case: " + testCaseId.value()+ " with status: " + status);
        this.testCaseId = testCaseId;
        this.status = status;
    }

}
