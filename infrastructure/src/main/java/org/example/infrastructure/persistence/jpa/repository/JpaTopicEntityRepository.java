package org.example.infrastructure.persistence.jpa.repository;

import org.example.infrastructure.persistence.jpa.model.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTopicEntityRepository extends JpaRepository<TopicEntity, UUID> {
}
