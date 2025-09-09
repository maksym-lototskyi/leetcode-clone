package org.example.domain.task;

import java.util.List;
import java.util.Objects;

public record TaskSignature(
        String functionName,
        List<Parameter> parameters,
        String returnType
) {
    public record Parameter(String name, String type) {
        public Parameter {
            Objects.requireNonNull(name, "Parameter name cannot be null");
            Objects.requireNonNull(type, "Parameter type cannot be null");
        }
    }

    public TaskSignature {
        Objects.requireNonNull(functionName, "Function name cannot be null");
        Objects.requireNonNull(parameters, "Parameters cannot be null");
        Objects.requireNonNull(returnType, "Return type cannot be null");
        if (parameters.isEmpty()) throw new IllegalArgumentException("There must be at least one parameter");
    }
}

