package org.example.application.task.ports.out;

import org.example.domain.model.submission.Submission;

public interface SubmissionRepository {
    void save(Submission submission);
}
