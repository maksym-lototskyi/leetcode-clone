package org.example.domain.model.submission;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record SubmissionId(UUID value) {

    public static SubmissionId generate() {
        return new SubmissionId(UUID.randomUUID());
    }

    public SubmissionId {
        ValidationUtils.requireNonNull(value, "SubmissionId value cannot be null");
    }
}
