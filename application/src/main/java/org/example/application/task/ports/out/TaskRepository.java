package org.example.application.task.ports.out;

import org.example.domain.task.Task;
import org.example.domain.task.TaskId;

import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(TaskId taskId);
}
