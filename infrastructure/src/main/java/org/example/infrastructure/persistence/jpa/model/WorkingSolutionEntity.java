package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter
@Entity
@Setter
@Builder
@AllArgsConstructor
public class WorkingSolutionEntity {
    @Id
    private UUID id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String sourceCode;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private LanguageEntity language;
    @OneToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;
    public WorkingSolutionEntity() {

    }
}

