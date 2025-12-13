package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.model.language.LanguageName;

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
    @Enumerated(EnumType.STRING)
    private LanguageName name;
    @Column(nullable = false)
    private String version;
    @Column(nullable = false)
    private String fileExtension;
    @OneToMany(mappedBy = "language")
    private List<WorkingSolutionEntity> workingSolutions;

}
