package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("PUBLISHED")
@Getter
@Setter
@NoArgsConstructor
public non-sealed class PublishedTaskEntity extends TaskEntity {
    @Column(name = "archived", nullable = false)
    private boolean archived = false;
}

