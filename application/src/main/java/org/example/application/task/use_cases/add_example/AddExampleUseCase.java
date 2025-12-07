package org.example.application.task.use_cases.add_example;

import org.example.application.exception.NotFoundException;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.model.task.Example;
import org.example.domain.model.task.IOValidator;
import org.example.domain.model.task.Input;
import org.example.domain.model.task.Output;
import org.example.domain.model.task.TaskId;

public class AddExampleUseCase implements AddExampleInputBoundary {
    private final TaskRepository taskRepository;
    private final IOValidator ioValidator;

    public AddExampleUseCase(TaskRepository taskRepository, IOValidator ioValidator) {
        this.taskRepository = taskRepository;
        this.ioValidator = ioValidator;
    }

    public void execute(AddExampleCommand command){
        taskRepository.findDraftById(TaskId.of(command.taskId())).ifPresentOrElse(task -> {
            Input input = new Input(command.input(), ioValidator);
            Output output = new Output(command.output(), ioValidator);
            String explanation = command.explanation();

            task.addExample(Example.create(input, output, explanation));
            taskRepository.save(task);
        },
                () -> {
            throw new NotFoundException("Task not found");
        });
    }
}
