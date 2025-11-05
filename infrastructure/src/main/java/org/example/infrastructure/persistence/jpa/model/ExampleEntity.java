package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "example")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExampleEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String input;
    @Column(nullable = false)
    private String output;
    private String explanation;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;
}
