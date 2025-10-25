package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
public class WorkingSolutionEmbeddable {
    @Column(columnDefinition = "TEXT")
    private String sourceCode;
    @Column()
    private String runtimeImage;

    public WorkingSolutionEmbeddable(String sourceCode, String runtimeImage) {
        this.sourceCode = sourceCode;
        this.runtimeImage = runtimeImage;
    }

    public WorkingSolutionEmbeddable() {

    }
}

