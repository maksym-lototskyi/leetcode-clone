package org.example.application.task.ports.out;

import org.example.domain.task.Task;
import org.example.domain.task.TaskId;
import org.example.domain.task.TaskSummary;
import org.example.domain.task.TestCase;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(TaskId taskId);
    List<TaskSummary> findTaskSummaries(int pageNumber, int pageSize);
    void save(Task task);
    List<TestCase> findAllTestCasesByTaskId(TaskId taskId);
    boolean existsById(TaskId taskId);
}
