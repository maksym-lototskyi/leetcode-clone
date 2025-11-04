package org.example.infrastructure.persistence.jpa.repository;

import org.example.infrastructure.persistence.jpa.model.ClassDefinitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaClassDefinitionEntityRepository extends JpaRepository<ClassDefinitionEntity, UUID> {
    @Query("""
        SELECT cd.id FROM ClassDefinitionEntity cd
        JOIN cd.associatedTasks t
        WHERE t.taskId = :taskId
""")
    List<UUID> findIdsByTaskId(UUID taskId);
}
