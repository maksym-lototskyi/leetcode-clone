package org.example.domain.model.task_comment;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record TaskCommentId(UUID value) {
    public TaskCommentId {
        ValidationUtils.requireNonNull(value, "CommentId cannot be null");
    }
    public static TaskCommentId generate() {
        return new TaskCommentId(UUID.randomUUID());
    }
}
