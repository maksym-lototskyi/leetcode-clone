package org.example.domain.task;

import java.util.UUID;

public record WorkingSolutionId(UUID value) {
    public WorkingSolutionId {
        if (value == null) {
            throw new IllegalArgumentException("WorkingSolutionId name cannot be null");
        }
    }

    public static WorkingSolutionId generate() {
        return new WorkingSolutionId(UUID.randomUUID());
    }

    public static WorkingSolutionId of(UUID id) {
        return new WorkingSolutionId(id);
    }
}
