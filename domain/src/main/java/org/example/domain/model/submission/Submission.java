package org.example.domain.model.submission;

import lombok.Getter;
import org.example.domain.model.language.LanguageId;
import org.example.domain.model.task.TaskId;

import java.time.LocalDateTime;

@Getter
public final class Submission {
    private final SubmissionId submissionId;
    private final LocalDateTime timeSubmitted;
    private SubmissionStatus status;
    private final TaskId taskId;
    private final LanguageId languageId;
    private final String sourceCode;
    private SubmissionResult result;

    public Submission(TaskId taskId, LanguageId languageId, String sourceCode) {
        if (taskId == null) throw new IllegalArgumentException("TaskId cannot be null");
        if (languageId == null) throw new IllegalArgumentException("LanguageId cannot be null");
        if (sourceCode == null || sourceCode.isBlank()) throw new IllegalArgumentException("Source code cannot be null or blank");

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

