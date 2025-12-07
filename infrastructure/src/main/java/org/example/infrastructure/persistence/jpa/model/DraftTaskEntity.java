package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("DRAFT")
@Getter
@Setter
@NoArgsConstructor
public non-sealed class DraftTaskEntity extends TaskEntity {
}

