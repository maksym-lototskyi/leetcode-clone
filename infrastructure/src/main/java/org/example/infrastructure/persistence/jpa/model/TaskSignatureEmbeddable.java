package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Embeddable
@Builder
@Getter
@Setter
public class TaskSignatureEmbeddable {
    private String functionName;
    private String returnType;

    @ElementCollection
    @CollectionTable(
            name = "task_signature_parameters",
            joinColumns = @JoinColumn(name = "task_signature_id")
    )
    private List<ParameterEmbeddable> parameters;

    protected TaskSignatureEmbeddable() {}

    public TaskSignatureEmbeddable(String functionName, List<ParameterEmbeddable> parameters, String returnType) {
        this.functionName = functionName;
        this.parameters = parameters;
        this.returnType = returnType;
    }
}

