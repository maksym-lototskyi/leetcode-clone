package org.example.application.task.use_cases.add_test_case;

import org.example.application.exception.NotFoundException;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.task.*;
import org.example.domain.task.service.IOValidator;

class AddTestCaseUseCase implements AddTestCaseInputBoundary {
    private final TaskRepository taskRepository;
    private final IOValidator ioValidator;

    public AddTestCaseUseCase(TaskRepository taskRepository, IOValidator ioValidator) {
        this.taskRepository = taskRepository;
        this.ioValidator = ioValidator;
    }

    public void execute(AddTestCaseCommand command){
        Task task = taskRepository.findById(TaskId.of(command.taskId())).orElseThrow(() -> new NotFoundException("Task not found"));

        task.addTestCase(TestCase.create(
                new Input(command.input(), ioValidator),
                new Output(command.expectedOutput(), ioValidator)
        ));
        taskRepository.save(task);
    }
}
