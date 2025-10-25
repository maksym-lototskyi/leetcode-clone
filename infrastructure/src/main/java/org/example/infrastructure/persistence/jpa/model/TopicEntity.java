package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "topic")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicEntity {
    @Id
    private UUID id;
    private String name;
    @ManyToMany(mappedBy = "topics")
    private List<TaskEntity> tasks = new ArrayList<>();
}
