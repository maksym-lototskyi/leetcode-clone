package org.example.application.submission.ports.out;

import org.example.domain.submission.Submission;

public interface SubmissionRepository {
    void save(Submission submission);
}
