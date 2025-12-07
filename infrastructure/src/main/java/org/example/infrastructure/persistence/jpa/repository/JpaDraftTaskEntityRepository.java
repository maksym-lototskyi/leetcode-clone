package org.example.infrastructure.persistence.jpa.repository;

import org.example.infrastructure.persistence.jpa.model.DraftTaskEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface JpaDraftTaskEntityRepository extends CrudRepository<DraftTaskEntity, UUID> {
}
