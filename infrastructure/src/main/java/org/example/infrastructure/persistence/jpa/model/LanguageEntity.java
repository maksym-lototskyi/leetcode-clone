package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

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

}
