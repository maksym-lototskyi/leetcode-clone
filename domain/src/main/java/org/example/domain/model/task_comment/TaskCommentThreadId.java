package org.example.domain.model.task_comment;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record TaskCommentThreadId(UUID id) {
    public TaskCommentThreadId {
        ValidationUtils.requireNonNull(id, "CommentThreadId cannot be null");
    }
    public static TaskCommentThreadId generate() {
        return new TaskCommentThreadId(UUID.randomUUID());
    }
}
