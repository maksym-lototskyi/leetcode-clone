package org.example.domain.model.task_comment;

import org.example.domain.validation.ValidationUtils;

public record CommentContent(String value) {
    public CommentContent {
        ValidationUtils.requireNonBlank(value, "Comment content cannot be null or blank");
        ValidationUtils.requireMaximumValue(value.length(), 1000, "Comment content cannot exceed 1000 characters");
    }

    public static CommentContent of(String value) {
        return new CommentContent(value);
    }
}
