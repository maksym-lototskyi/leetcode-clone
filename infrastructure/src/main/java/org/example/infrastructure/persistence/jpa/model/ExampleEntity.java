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
    @ElementCollection
    @CollectionTable(
            name = "example_input",
            joinColumns = @JoinColumn(name = "example_id")
    )
    @Column(name = "value", nullable = false)
    private List<String> inputs;
    @Column(nullable = false)
    private String output;
    private String explanation;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;
}
