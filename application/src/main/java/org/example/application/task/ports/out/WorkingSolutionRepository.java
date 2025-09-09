package org.example.application.task.ports.out;

import org.example.domain.task.TaskId;
import org.example.domain.task.WorkingSolution;

import java.util.Optional;

public interface WorkingSolutionRepository {
    Optional<WorkingSolution> findByTaskId(TaskId taskId);
}
