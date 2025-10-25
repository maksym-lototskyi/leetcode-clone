package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ParameterEmbeddable {
    private String name;
    private String type;

    public ParameterEmbeddable(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public ParameterEmbeddable() {

    }
}
