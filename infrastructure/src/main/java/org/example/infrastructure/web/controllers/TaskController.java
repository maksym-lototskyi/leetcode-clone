package org.example.infrastructure.web.controllers;

import org.example.application.task.use_cases.add_example.AddExampleCommand;
import org.example.application.task.use_cases.add_example.AddExampleInputBoundary;
import org.example.application.task.use_cases.add_test_case.AddTestCaseCommand;
import org.example.application.task.use_cases.add_test_case.AddTestCaseInputBoundary;
import org.example.application.task.use_cases.add_working_solution.AddWorkingSolutionCommand;
import org.example.application.task.use_cases.add_working_solution.AddWorkingSolutionInputBoundary;
import org.example.application.task.use_cases.create_draft.CreateDraftTaskCommand;
import org.example.application.task.use_cases.create_draft.CreateDraftTaskInputBoundary;
import org.example.application.task.use_cases.get_task_definition.GetTaskDefinitionInputBoundary;
import org.example.application.task.use_cases.get_task_definition.GetTaskDefinitionRequest;
import org.example.application.task.use_cases.get_task_definition.TaskDetailsDto;
import org.example.application.task.use_cases.publish.PublishTaskInputBoundary;
import org.example.application.task.use_cases.run.RunTaskCommand;
import org.example.application.task.use_cases.run.RunTaskInputBoundary;
import org.example.application.task.use_cases.run.TaskRunResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final RunTaskInputBoundary runTaskUseCase;
    private final CreateDraftTaskInputBoundary createDraftTaskInputBoundary;
    private final AddTestCaseInputBoundary addTestCaseInputBoundary;
    private final AddExampleInputBoundary addExampleInputBoundary;
    private final AddWorkingSolutionInputBoundary addWorkingSolutionInputBoundary;
    private final PublishTaskInputBoundary publishTaskInputBoundary;
    private final GetTaskDefinitionInputBoundary getTaskDefinitionInputBoundary;

    public TaskController(RunTaskInputBoundary runTaskUseCase, CreateDraftTaskInputBoundary createDraftTaskInputBoundary, AddTestCaseInputBoundary addTestCaseInputBoundary, AddExampleInputBoundary addExampleInputBoundary, AddWorkingSolutionInputBoundary addWorkingSolutionInputBoundary, PublishTaskInputBoundary publishTaskInputBoundary, GetTaskDefinitionInputBoundary getTaskDefinitionInputBoundary) {
        this.runTaskUseCase = runTaskUseCase;
        this.createDraftTaskInputBoundary = createDraftTaskInputBoundary;
        this.addTestCaseInputBoundary = addTestCaseInputBoundary;
        this.addExampleInputBoundary = addExampleInputBoundary;
        this.addWorkingSolutionInputBoundary = addWorkingSolutionInputBoundary;
        this.publishTaskInputBoundary = publishTaskInputBoundary;
        this.getTaskDefinitionInputBoundary = getTaskDefinitionInputBoundary;
    }

    @PostMapping("/run")
    public ResponseEntity<TaskRunResult> runTask(@RequestBody RunTaskCommand dto){
        try {
            return ResponseEntity.ok(runTaskUseCase.execute(dto));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/draft")
    public ResponseEntity<String> createDraft(@RequestBody CreateDraftTaskCommand command){
        UUID id = createDraftTaskInputBoundary.execute(command);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/add-test-case")
    public ResponseEntity<String> addTestCase(@RequestBody AddTestCaseCommand addTestCaseCommand){
        addTestCaseInputBoundary.execute(addTestCaseCommand);
        return ResponseEntity.ok("Test case added successfully");
    }

    @PostMapping("/add-example")
    public ResponseEntity<String> addExample(@RequestBody AddExampleCommand addExampleCommand){
        addExampleInputBoundary.execute(addExampleCommand);
        return ResponseEntity.ok("Example added successfully");
    }

    @PostMapping("/add-working-solution")
    public ResponseEntity<String> addWorkingSolution(@RequestBody AddWorkingSolutionCommand command){
        addWorkingSolutionInputBoundary.execute(command);
        return ResponseEntity.ok("Working solution added successfully");
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<String> publishTask(@PathVariable UUID id) {
        publishTaskInputBoundary.execute(id);
        return ResponseEntity.ok("Task published successfully");
    }

    @GetMapping("/{id}/definition")
    public ResponseEntity<TaskDetailsDto> getTaskDefinition(@PathVariable UUID id, @RequestParam(required = false) String languageName) {
        var request = new GetTaskDefinitionRequest(id, Optional.ofNullable(languageName));

        TaskDetailsDto taskDetails = getTaskDefinitionInputBoundary.execute(request);
        return ResponseEntity.ok(taskDetails);
    }

}
