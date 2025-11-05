package org.example.infrastructure.persistence.jpa.repository;

import org.example.domain.topic.TopicId;
import org.example.infrastructure.persistence.jpa.model.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface JpaTopicEntityRepository extends JpaRepository<TopicEntity, UUID> {
    @Query("""
        SELECT CASE WHEN COUNT(t) = :#{#list.size()} THEN true ELSE false END
        FROM TopicEntity t
        WHERE t.id IN :list
    """)
    boolean existAllByIds(List<UUID> list);
}
