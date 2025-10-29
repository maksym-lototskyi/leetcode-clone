package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "language")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LanguageEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String version;
    @Column(nullable = false)
    private String runtimeImage;
    @Column(nullable = false)
    private String fileExtension;
    @OneToMany(mappedBy = "language")
    private List<WorkingSolutionEntity> workingSolutions;

}
