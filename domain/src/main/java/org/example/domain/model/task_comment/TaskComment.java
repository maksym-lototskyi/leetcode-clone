package org.example.domain.model.task_comment;

import lombok.Getter;
import org.example.domain.model.user.UserId;
import org.example.domain.validation.ValidationUtils;

import java.util.*;

public final class TaskComment {
    @Getter
    private final TaskCommentId commentId;
    @Getter
    private CommentContent commentContent;
    @Getter
    private final UserId author;
    private final Set<UserId> likedBy = new HashSet<>();
    private final List<TaskComment> replies = new ArrayList<>();

    public TaskComment(CommentContent commentContent, UserId author,
                       Collection<UserId> likedBy, Collection<TaskComment> replies) {
        if (commentContent == null) {
            throw new IllegalArgumentException("Comment content cannot be null");
        }
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

        this.commentId = TaskCommentId.generate();
        this.commentContent = commentContent;
        this.author = author;

        if (likedBy != null) {
            this.likedBy.addAll(likedBy);
        }
        if (replies != null) {
            this.replies.addAll(replies);
        }
    }

    public void like(UserId userId) {
        ValidationUtils.requireNonNull(userId, "User ID cannot be null");
        likedBy.add(userId);
    }

    public void unlike(UserId userId) {
        ValidationUtils.requireNonNull(userId, "User ID cannot be null");
        likedBy.remove(userId);
    }

    public void reply(TaskComment reply) {
        ValidationUtils.requireNonNull(reply, "Reply cannot be null");
        replies.add(reply);
    }

    public void removeReply(TaskComment reply) {
        replies.remove(reply);
    }

    public void editContent(CommentContent newContent) {
        ValidationUtils.requireNonNull(newContent, "New content cannot be null");
        this.commentContent = newContent;
    }

    public List<TaskComment> replies() {
        return List.copyOf(replies);
    }

    public Set<UserId> likedBy() {
        return Set.copyOf(likedBy);
    }
}

