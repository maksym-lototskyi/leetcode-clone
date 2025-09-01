package org.example.domain.submission;

import java.util.UUID;

public record SubmissionId(UUID value) {

    public static SubmissionId generate() {
        return new SubmissionId(UUID.randomUUID());
    }

    public SubmissionId {
        if (value == null) {
            throw new IllegalArgumentException("SubmissionId cannot be null");
        }
    }
}
