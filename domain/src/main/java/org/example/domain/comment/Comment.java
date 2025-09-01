package org.example.domain.comment;

import lombok.Getter;
import org.example.domain.user.UserId;

import java.util.*;

@Getter
public final class Comment {
    private final CommentId commentId;
    private CommentContent commentContent;
    private final UserId author;
    private final Set<UserId> likedBy = new HashSet<>();
    private final List<Comment> replies = new ArrayList<>();

    public Comment(CommentContent commentContent, UserId author,
                   Collection<UserId> likedBy, Collection<Comment> replies) {
        if (commentContent == null) {
            throw new IllegalArgumentException("Comment content cannot be null");
        }
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

        this.commentId = CommentId.generate();
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
        if (userId == null) throw new IllegalArgumentException("User ID cannot be null");
        likedBy.add(userId);
    }

    public void unlike(UserId userId) {
        if (userId == null) throw new IllegalArgumentException("User ID cannot be null");
        likedBy.remove(userId);
    }

    public void reply(Comment reply) {
        if (reply == null) throw new IllegalArgumentException("Reply cannot be null");
        replies.add(reply);
    }

    public void removeReply(Comment reply) {
        replies.remove(reply);
    }

    public void editContent(CommentContent newContent) {
        if (newContent == null) throw new IllegalArgumentException("Content cannot be null");
        this.commentContent = newContent;
    }

    public List<Comment> getReplies() {
        return new ArrayList<>(replies);
    }

    public Set<UserId> getLikedBy() {
        return new HashSet<>(likedBy);
    }
}

