package org.example.application.task.use_cases.add_test_case;

import org.example.application.exception.NotFoundException;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.task.ports.out.TestCaseRepository;
import org.example.domain.task.*;
import org.example.domain.task.service.IOValidator;

class AddTestCaseUseCase implements AddTestCaseInputBoundary {
    private final TaskRepository taskRepository;
    private final TestCaseRepository testCaseRepository;
    private final IOValidator ioValidator;

    public AddTestCaseUseCase(TaskRepository taskRepository, TestCaseRepository testCaseRepository, IOValidator ioValidator) {
        this.taskRepository = taskRepository;
        this.testCaseRepository = testCaseRepository;
        this.ioValidator = ioValidator;
    }

    public void execute(AddTestCaseCommand command){
        if(!taskRepository.existsById(TaskId.of(command.taskId()))) throw new NotFoundException("Task not found");

        testCaseRepository.save(
                TestCase.create(
                        TaskId.of(command.taskId()),
                        new Input(command.input(), ioValidator),
                        new Output(command.expectedOutput(), ioValidator)
                )
        );
    }
}
