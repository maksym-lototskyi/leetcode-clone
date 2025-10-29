package org.example.infrastructure.web.controllers;

import org.example.application.task.use_cases.run.ObjectConverter;
import org.example.application.task.use_cases.run.RunTaskCommand;
import org.example.application.task.use_cases.run.RunTaskInputBoundary;
import org.example.application.task.use_cases.run.TaskRunResult;
import org.example.infrastructure.web.dtos.RunTaskRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final RunTaskInputBoundary runTaskUseCase;
    private final ObjectConverter objectConverter;

    public TaskController(RunTaskInputBoundary runTaskUseCase, ObjectConverter objectConverter) {
        this.runTaskUseCase = runTaskUseCase;
        this.objectConverter = objectConverter;
    }

    @PostMapping("/run")
    public ResponseEntity<TaskRunResult> runTask(@RequestBody RunTaskRequestDto dto){
        RunTaskCommand command = new RunTaskCommand(
                dto.taskId(),
                objectConverter.convert(dto.input()),
                dto.sourceCode(),
                dto.language()
        );

        try {
            return ResponseEntity.ok(runTaskUseCase.execute(command));
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
