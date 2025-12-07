package org.example.infrastructure.persistence.jpa.repository;

import org.example.infrastructure.persistence.jpa.model.PublishedTaskEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface JpaPublishedTaskEntityRepository extends CrudRepository<PublishedTaskEntity, UUID> {
}
