package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record WorkingSolutionId(UUID value) {
    public WorkingSolutionId {
        ValidationUtils.requireNonNull(value, "WorkingSolutionId value cannot be null");
    }

    public static WorkingSolutionId generate() {
        return new WorkingSolutionId(UUID.randomUUID());
    }

    public static WorkingSolutionId of(UUID id) {
        return new WorkingSolutionId(id);
    }
}
