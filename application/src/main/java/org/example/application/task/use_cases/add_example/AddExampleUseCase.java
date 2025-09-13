package org.example.application.task.use_cases.add_example;

import org.example.application.exception.NotFoundException;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.task.service.IOValidator;
import org.example.domain.task.Input;
import org.example.domain.task.Output;
import org.example.domain.task.TaskId;

public class AddExampleUseCase implements AddExampleInputBoundary {
    private final TaskRepository taskRepository;
    private final IOValidator ioValidator;

    public AddExampleUseCase(TaskRepository taskRepository, IOValidator ioValidator) {
        this.taskRepository = taskRepository;
        this.ioValidator = ioValidator;
    }

    public void execute(AddExampleCommand command){
        taskRepository.findById(TaskId.of(command.taskId())).ifPresentOrElse(task -> {
            task.addExample(new Input(command.input(), ioValidator), new Output(command.output(), ioValidator), command.explanation());
            taskRepository.save(task);
        },
                () -> {
            throw new NotFoundException("Task not found");
        });
    }
}
