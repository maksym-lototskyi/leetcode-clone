package org.example.infrastructure.persistence.jpa.repository;

import org.example.application.task.ports.out.TaskRepository;

import java.util.List;
import java.util.Optional;

public class JpaTaskRepository implements TaskRepository {
    @Override
    public Optional<org.example.domain.task.Task> findById(org.example.domain.task.TaskId taskId) {
        return Optional.empty();
    }

    @Override
    public List<org.example.domain.task.TaskSummary> findTaskSummaries(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public boolean existsById(org.example.domain.task.TaskId taskId) {
        return false;
    }

    @Override
    public org.example.domain.task.Task save(org.example.domain.task.Task task) {
        return null;
    }
}
