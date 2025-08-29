package org.example.domain.model.comment;

import java.util.UUID;

public record CommentThreadId(UUID id) {
    public CommentThreadId {
        if (id == null) {
            throw new IllegalArgumentException("CommentThreadId cannot be null");
        }
    }
    public static CommentThreadId generate() {
        return new CommentThreadId(UUID.randomUUID());
    }
}
