package org.example.application.task.ports.out;

import org.example.domain.model.task.*;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> loadTaskDefinition(TaskId taskId);

    Optional<DraftTask> findDraftById(TaskId taskId);
    Optional<PublishedTask> findPublishedTaskById(TaskId taskId);
    List<TaskSummary> findPublishedTaskPage(int pageNumber, int pageSize);
    void save(Task task);
    boolean existsById(TaskId taskId);
}
