package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "class_implementation")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassImplementationEntity {
    @Id
    private UUID id;
    @Column(name = "source_code", columnDefinition = "TEXT", nullable = false)
    private String sourceCode;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private LanguageEntity language;
    @ManyToOne
    @JoinColumn(name = "class_definition_id")
    private ClassDefinitionEntity classDefinition;
}
