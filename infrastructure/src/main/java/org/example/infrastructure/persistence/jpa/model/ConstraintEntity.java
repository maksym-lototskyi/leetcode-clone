package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "constraint")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConstraintEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;
}
