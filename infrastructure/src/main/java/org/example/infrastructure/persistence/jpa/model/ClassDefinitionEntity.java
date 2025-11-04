package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "class_definition")
public class ClassDefinitionEntity {
    @Id
    private UUID id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "classDefinition")
    private Set<ClassImplementationEntity> implementations;
    @ManyToMany(mappedBy = "classDefinitions")
    private List<TaskEntity> associatedTasks;
}
