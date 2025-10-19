package org.example.domain.submission;

import lombok.Getter;
import org.example.domain.language.LanguageId;
import org.example.domain.task.TaskId;
import org.example.domain.user.UserId;

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
        if (taskId == null) throw new IllegalArgumentException("TaskId cannot be null");
        if (languageId == null) throw new IllegalArgumentException("LanguageId cannot be null");
        if (sourceCode == null || sourceCode.isBlank()) throw new IllegalArgumentException("Source code cannot be null or blank");
        if (userId == null) throw new IllegalArgumentException("UserId cannot be null");

        this.userId = userId;
        this.submissionId = SubmissionId.generate();
        this.timeSubmitted = LocalDateTime.now();
        this.status = SubmissionStatus.PENDING;
        this.taskId = taskId;
        this.languageId = languageId;
        this.sourceCode = sourceCode;
    }

    public void updateStatus(SubmissionStatus newStatus) {
        if (newStatus == null) throw new IllegalArgumentException("Status cannot be null");
        this.status = newStatus;
    }

    public void attachResult(SubmissionResult result) {
        if (result == null) throw new IllegalArgumentException("Result cannot be null");
        this.result = result;
        this.status = SubmissionStatus.FINISHED;
    }
}

