package org.example.infrastructure.web.controllers;

import org.example.application.task.use_cases.get_task_page.GetTaskPageInputBoundary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final GetTaskPageInputBoundary getTaskPageUseCase;

    public TaskController(GetTaskPageInputBoundary getTaskPageUseCase) {
        this.getTaskPageUseCase = getTaskPageUseCase;
    }
}
