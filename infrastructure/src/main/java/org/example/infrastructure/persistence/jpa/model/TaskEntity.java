package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.model.task.TaskLevel;
import org.example.domain.model.task.TaskStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "task")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "task_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public sealed abstract class TaskEntity permits DraftTaskEntity, PublishedTaskEntity {

    @Id
    @Column(name = "task_id", nullable = false, updatable = false)
    private UUID taskId;

    @Embedded
    private TaskSignatureEmbeddable taskSignature;

    @Column(nullable = false)
    private String title;

    @Column(name = "task_description", nullable = false, length = 2000, columnDefinition = "TEXT")
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskLevel taskLevel;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @ManyToMany
    @JoinTable(
            name = "task_topic",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<TopicEntity> topics = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "task_constraints", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "description", nullable = false)
    private Set<String> constraints = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExampleEntity> examples = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TestCaseEntity> testCases = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "task_class_definition",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "class_definition_id")
    )
    private Set<ClassDefinitionEntity> relatedClassDefinitions = new HashSet<>();

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private WorkingSolutionEntity workingSolution;

    @Column(name = "time_limit_ms", nullable = false)
    private long timeLimitMs;

    @Column(name = "memory_limit_kb", nullable = false)
    private long memoryLimitKb;
}


