package org.example.application.task.ports.out;

import org.example.domain.model.task.Task;
import org.example.domain.model.task.TaskId;

import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(TaskId taskId);
}
