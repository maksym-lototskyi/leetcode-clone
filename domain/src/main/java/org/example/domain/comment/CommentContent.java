package org.example.domain.comment;

public record CommentContent(String value) {
    public CommentContent {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Comment content cannot be null or blank");
        }
        if (value.length() > 500) {
            throw new IllegalArgumentException("Comment content cannot exceed 500 characters");
        }
    }

    public static CommentContent of(String value) {
        return new CommentContent(value);
    }
}
