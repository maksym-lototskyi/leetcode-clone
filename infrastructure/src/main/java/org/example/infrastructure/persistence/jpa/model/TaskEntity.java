package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.task.TaskLevel;
import org.example.domain.task.TaskStatus;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "task")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    @Id
    private UUID taskId;

    @Embedded
    private TaskSignatureEmbeddable taskSignature;

    @Column(nullable = false, length = 2000, columnDefinition = "TEXT")
    private String taskDescription;
    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskLevel taskLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus taskStatus;

    private long timeLimitMs;
    private long memoryLimitKb;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "task_topic",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<TopicEntity> topics;

    @ElementCollection
    @CollectionTable(
            name = "task_constraints",
            joinColumns = @JoinColumn(name = "task_id")
    )

    @Column(name = "description", nullable = false)
    private List<String> constraints;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExampleEntity> examples;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestCaseEntity> testCases;

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private WorkingSolutionEntity workingSolutionEntity;
    @ManyToMany
    @JoinTable(
            name = "task_class_definition",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "class_definition_id")
    )
    private Set<ClassDefinitionEntity> classDefinitions;
}

