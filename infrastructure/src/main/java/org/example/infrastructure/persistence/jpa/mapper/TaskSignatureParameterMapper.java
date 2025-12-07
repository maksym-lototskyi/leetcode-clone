package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.model.task.TaskSignature;
import org.example.infrastructure.persistence.jpa.model.ParameterEmbeddable;

public class TaskSignatureParameterMapper {
    public static ParameterEmbeddable map(TaskSignature.Parameter parameter){
        return new ParameterEmbeddable(
                parameter.type(),
                parameter.name()
        );
    }

    public static TaskSignature.Parameter map(ParameterEmbeddable embeddable){
        return new TaskSignature.Parameter(
                embeddable.getType(),
                embeddable.getName()
        );
    }
}
