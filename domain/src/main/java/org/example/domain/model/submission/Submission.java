package org.example.domain.model.submission;

import lombok.Getter;
import org.example.domain.model.language.LanguageId;
import org.example.domain.model.user.UserId;
import org.example.domain.model.task.TaskId;
import org.example.domain.validation.ValidationUtils;

import java.time.LocalDateTime;

@Getter
public final class Submission {
    private final SubmissionId submissionId;
    private final LocalDateTime timeSubmitted;
    private SubmissionStatus status;
    private final TaskId taskId;
    private final UserId userId;
    private final LanguageId languageId;
    private final String sourceCode;
    private SubmissionResult result;

    public Submission(TaskId taskId, UserId userId, LanguageId languageId, String sourceCode) {
        this.submissionId = SubmissionId.generate();
        this.userId = ValidationUtils.requireNonNull(userId, "UserId cannot be null");
        this.taskId = ValidationUtils.requireNonNull(taskId, "TaskId cannot be null");
        this.languageId = ValidationUtils.requireNonNull(languageId, "LanguageId cannot be null");
        this.sourceCode = ValidationUtils.requireNonBlank(sourceCode, "Source code cannot be null or blank");
        this.timeSubmitted = LocalDateTime.now();
        this.status = SubmissionStatus.PENDING;
    }

    public void attachResult(SubmissionResult result) {
        if (result == null) throw new IllegalArgumentException("Result cannot be null");
        if (this.result != null) throw new IllegalStateException("Result already attached");
        this.result = result;
        this.status = SubmissionStatus.FINISHED;
    }
}

