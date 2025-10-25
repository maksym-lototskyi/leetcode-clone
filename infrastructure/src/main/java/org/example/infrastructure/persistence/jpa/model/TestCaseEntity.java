package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "test_case")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestCaseEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String expectedOutput;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;
    @ElementCollection
    @CollectionTable(
            name = "test_case_input",
            joinColumns = @JoinColumn(name = "test_case_id")
    )
    @Column(name = "value", nullable = false)
    private List<String> inputs;
}
