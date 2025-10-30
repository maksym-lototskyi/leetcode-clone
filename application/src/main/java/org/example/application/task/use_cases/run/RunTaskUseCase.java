package org.example.application.task.use_cases.run;

import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.language.Language;
import org.example.domain.submission.SubmissionResultStatus;
import org.example.domain.task.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.example.application.task.use_cases.run.StatusDeterminer.determineStatus;

class RunTaskUseCase implements RunTaskInputBoundary {
    private final LanguageRepository languageRepository;
    private final TaskRepository taskRepository;
    private final TestRunner testRunner;
    private final ObjectConverter converter;

    public RunTaskUseCase(LanguageRepository languageRepository, TaskRepository taskRepository, TestRunner testRunner, ObjectConverter converter) {
        this.languageRepository = languageRepository;
        this.taskRepository = taskRepository;
        this.testRunner = testRunner;
        this.converter = converter;
    }

    @Override
    public TaskRunResult execute(RunTaskCommand command) throws IOException {
        Task task = taskRepository.findById(new TaskId(command.taskId()))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + command.taskId()));
        WorkingSolution taskWorkingSolution = task.getWorkingSolution();

        Language userLanguage = languageRepository.findByName(command.language())
                .orElseThrow(() -> new NotFoundException("Language not found: " + command.language()));

        Language workingSolutionLanguage = languageRepository.findById(taskWorkingSolution.languageId())
                .orElseThrow(() -> new NotFoundException("Language not found with id: " + taskWorkingSolution.languageId()));

        String convertedSignature = converter.convert(task.getTaskSignature());

        LanguageDto languageDto = new LanguageDto(userLanguage.getName(), userLanguage.getFileExtension());

        LanguageDto workingSolutionLanguageDto = new LanguageDto(
                workingSolutionLanguage.getName(),
                workingSolutionLanguage.getFileExtension()
        );

        ExecutionContext userExecutionContext = new ExecutionContext(command.sourceCode(), command.input(), convertedSignature, Map.of());
        ExecutionContext workingSolutionExecutionContext = new ExecutionContext(taskWorkingSolution.sourceCode(), command.input(), convertedSignature, Map.of());

        TestRunResult userResult = testRunner.run(languageDto, userExecutionContext);
        TestRunResult workingSolutionResult = testRunner.run(workingSolutionLanguageDto, workingSolutionExecutionContext);

        SubmissionResultStatus status = determineStatus(userResult, workingSolutionResult.output(), task.getTimeLimitMs(), task.getMemoryLimitKb());
        return new TaskRunResult(command.input(), workingSolutionResult.output(), userResult.output(), status == SubmissionResultStatus.ACCEPTED, userResult.executionTimeMs(), status);
    }


}
