package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "working_solution")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkingSolutionEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String sourceCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;
}

