package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Entity
@Setter
@Builder
@AllArgsConstructor
public class WorkingSolutionEntity {
    @Id
    private Long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String sourceCode;
    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;
    @OneToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;
    public WorkingSolutionEntity() {

    }
}

