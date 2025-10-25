package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.task.TaskLevel;
import org.example.domain.task.TaskStatus;

import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID taskId;
    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private TaskSignatureEntity taskSignature;
    @Column(nullable = false, length = 2000, columnDefinition = "TEXT")
    private String taskDescription;
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
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConstraintEntity> constraints;
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExampleEntity> examples;
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestCaseEntity> testCases;
    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private WorkingSolutionEntity workingSolution;
}

