package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task_signature")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskSignatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String functionName;
    @Column(nullable = false)
    private String returnType;
    @OneToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;
    @OneToMany(mappedBy = "taskSignature", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskSignatureParameterEntity> parameters = new ArrayList<>();

}
