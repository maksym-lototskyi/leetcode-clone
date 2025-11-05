package org.example.infrastructure.persistence.jpa.repository;

import org.example.domain.task.TaskSummary;
import org.example.infrastructure.persistence.jpa.model.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

interface JpaTaskEntityRepository extends JpaRepository<TaskEntity, UUID> {
    @Query("""
       SELECT new org.example.domain.task.TaskSummary(
           new org.example.domain.task.TaskId(t.taskId), t.title, t.taskLevel, 5.00
       )
       FROM TaskEntity t
""")
    Page<TaskSummary> findAllSummaries(Pageable pageable);

    @Query("""
    SELECT DISTINCT t
    FROM TaskEntity t
    LEFT JOIN FETCH t.classDefinitions
    LEFT JOIN FETCH t.testCases
    WHERE t.taskId = :taskId
""")
    Optional<TaskEntity> findTaskForRuntimeByTaskId(UUID taskId);


}
