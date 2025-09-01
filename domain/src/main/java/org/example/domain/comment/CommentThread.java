package org.example.domain.comment;

import lombok.Getter;
import org.example.domain.task.TaskId;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class CommentThread {
    private final CommentThreadId id;
    private final TaskId taskId;
    private final List<Comment> comments = new ArrayList<>();

    public CommentThread(TaskId taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("TaskId cannot be null");
        }
        this.id = CommentThreadId.generate();
        this.taskId = taskId;
    }

    public void addComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }
}
