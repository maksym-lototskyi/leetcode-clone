package org.example.infrastructure.persistence.jpa.repository;

import org.example.infrastructure.persistence.jpa.model.TestCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface JpaTestCaseRepository extends JpaRepository<TestCaseEntity, UUID> {
    List<TestCaseEntity> findByTask_TaskId(UUID taskId);
}
