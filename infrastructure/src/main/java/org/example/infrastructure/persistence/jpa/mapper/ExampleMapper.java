package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.model.task.*;
import org.example.infrastructure.persistence.jpa.model.ExampleEntity;
import org.example.infrastructure.persistence.jpa.model.TaskEntity;

public class ExampleMapper {
    public static Example map(ExampleEntity example, IOValidator validator){
        return new Example(
                ExampleId.of(example.getId()),
                new Input(example.getInput(), validator),
                new Output(example.getOutput(), validator),
                example.getExplanation()
        );
    }

    public static ExampleEntity map(Example example, TaskEntity task){
        return ExampleEntity.builder()
                .id(example.exampleId().value())
                .task(task)
                .input(example.input().value())
                .output(example.output().value())
                .explanation(example.explanation())
                .build();
    }
}
