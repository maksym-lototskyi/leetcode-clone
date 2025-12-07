package org.example.application.task.ports.out;

import org.example.domain.model.task.Task;
import org.example.domain.model.task.TaskId;
import org.example.domain.model.task.TaskSummary;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> loadTaskDefinition(TaskId taskId);
    Optional<Task> loadTaskForRuntime(TaskId taskId);
    List<TaskSummary> findTaskSummaries(int pageNumber, int pageSize);
    void save(Task task);
    boolean existsById(TaskId taskId);
}
