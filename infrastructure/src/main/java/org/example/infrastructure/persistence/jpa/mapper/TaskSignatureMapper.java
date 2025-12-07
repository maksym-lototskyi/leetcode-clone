package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.model.task.TaskSignature;
import org.example.infrastructure.persistence.jpa.model.TaskSignatureEmbeddable;

public class TaskSignatureMapper {
    public static TaskSignature map(TaskSignatureEmbeddable signature){
        return new TaskSignature(
                signature.getMethodName(),
                signature.getParameters().stream().map(TaskSignatureParameterMapper::map).toList(),
                signature.getReturnType()
        );
    }

    public static TaskSignatureEmbeddable map(TaskSignature signature){
        return new TaskSignatureEmbeddable(
                signature.methodName(),
                signature.parameters().stream().map(TaskSignatureParameterMapper::map).toList(),
                signature.returnType()
        );
    }
}
