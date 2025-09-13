package org.example.application.task.use_cases.run;

import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.language.Language;
import org.example.domain.submission.SubmissionResultStatus;
import org.example.domain.task.*;

import java.util.List;

import static org.example.application.task.use_cases.run.StatusDeterminer.determineStatus;

class RunTaskUseCase implements RunTaskInputBoundary {
    private final LanguageRepository languageRepository;
    private final TaskRepository taskRepository;
    private final TestRunner testRunner;
    private final InputParser inputParser;

    public RunTaskUseCase(LanguageRepository languageRepository, TaskRepository taskRepository, TestRunner testRunner, InputParser inputParser) {
        this.languageRepository = languageRepository;
        this.taskRepository = taskRepository;
        this.testRunner = testRunner;
        this.inputParser = inputParser;
    }

    @Override
    public TaskRunResult execute(RunTaskCommand command) {
        Language userLanguage = languageRepository.findByName(command.language())
                .orElseThrow(() -> new NotFoundException("Language not found: " + command.language()));
        Task task = taskRepository.findById(new TaskId(command.taskId()))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + command.taskId()));
        WorkingSolution taskWorkingSolution = task.getWorkingSolution();

        List<Object> parsedInputs = inputParser.parse(task.getTaskSignature(), command.rawInputs());
        TestRunResult userResult = testRunner.run(parsedInputs, command.sourceCode(), userLanguage.getRuntimeImage());
        TestRunResult workingSolutionResult = testRunner.run(parsedInputs, taskWorkingSolution.sourceCode(), taskWorkingSolution.runtimeImage());

        SubmissionResultStatus status = determineStatus(userResult, workingSolutionResult.output(), task.getTimeLimitMs(), task.getMemoryLimitKb());
        return new TaskRunResult(command.rawInputs(), workingSolutionResult.output(), userResult.output(), status == SubmissionResultStatus.ACCEPTED, userResult.executionTimeMs(), status);
    }


}
