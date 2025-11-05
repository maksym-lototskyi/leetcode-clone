package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Embeddable
@Getter
@Setter
public class TaskSignatureEmbeddable {
    private String methodName;
    private String returnType;

    @ElementCollection
    @CollectionTable(
            name = "task_signature_parameters",
            joinColumns = @JoinColumn(name = "task_signature_id")
    )
    private List<ParameterEmbeddable> parameters;

    protected TaskSignatureEmbeddable() {}

    public TaskSignatureEmbeddable(String methodName, List<ParameterEmbeddable> parameters, String returnType) {
        this.methodName = methodName;
        this.parameters = parameters;
        this.returnType = returnType;
    }
}

