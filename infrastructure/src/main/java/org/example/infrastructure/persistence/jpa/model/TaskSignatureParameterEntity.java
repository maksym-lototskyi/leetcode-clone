package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parameter")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskSignatureParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    @ManyToOne
    @JoinColumn(name = "task_signature_id", nullable = false)
    private TaskSignatureEntity taskSignature;
}
