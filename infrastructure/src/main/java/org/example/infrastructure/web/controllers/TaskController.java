package org.example.infrastructure.web.controllers;

import org.example.application.task.use_cases.get_task_page.GetTaskPageInputBoundary;
import org.example.application.task.use_cases.run.RunTaskCommand;
import org.example.application.task.use_cases.run.RunTaskInputBoundary;
import org.example.application.task.use_cases.run.TaskRunResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final RunTaskInputBoundary runTaskUseCase;

    public TaskController(RunTaskInputBoundary runTaskUseCase) {
        this.runTaskUseCase = runTaskUseCase;
    }

    @PostMapping("/run")
    public ResponseEntity<TaskRunResult> runTask(@RequestBody RunTaskCommand command){
        return ResponseEntity.ok(runTaskUseCase.execute(command));
    }
}
