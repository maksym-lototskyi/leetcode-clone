package org.example.domain.model.task_comment;

import lombok.Getter;
import org.example.domain.model.task.TaskId;
import org.example.domain.validation.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class TaskCommentThread {
    private final TaskCommentThreadId id;
    private final TaskId taskId;
    private final List<TaskComment> taskComments = new ArrayList<>();

    public TaskCommentThread(TaskId taskId, List<TaskComment> initialComments) {
        ValidationUtils.requireNonNull(taskId, "TaskId cannot be null");
        ValidationUtils.requireNonNull(initialComments, "Initial comments list cannot be null");

        this.id = TaskCommentThreadId.generate();
        this.taskId = taskId;
        this.taskComments.addAll(initialComments);
    }

    public void addComment(TaskComment taskComment) {
        ValidationUtils.requireNonNull(taskComment, "TaskComment cannot be null");
        taskComments.add(taskComment);
    }

    public void removeComment(TaskComment taskComment) {
        taskComments.remove(taskComment);
    }

    public List<TaskComment> getTaskComments() {
        return new ArrayList<>(taskComments);
    }
}
