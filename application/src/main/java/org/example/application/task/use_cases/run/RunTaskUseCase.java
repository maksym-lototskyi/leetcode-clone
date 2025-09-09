package org.example.application.task.use_cases.run;

import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.task.ports.out.WorkingSolutionRepository;
import org.example.domain.language.Language;
import org.example.domain.submission.SubmissionResultStatus;
import org.example.domain.task.Task;
import org.example.domain.task.TaskId;
import org.example.domain.task.WorkingSolution;

import java.util.List;

class RunTaskUseCase implements RunTaskInputBoundary {
    private final LanguageRepository languageRepository;
    private final TaskRepository taskRepository;
    private final TestRunner testRunner;
    private final InputParser inputParser;
    private final WorkingSolutionRepository workingSolutionRepository;

    public RunTaskUseCase(LanguageRepository languageRepository, TaskRepository taskRepository, TestRunner testRunner, InputParser inputParser, WorkingSolutionRepository workingSolutionRepository) {
        this.languageRepository = languageRepository;
        this.taskRepository = taskRepository;
        this.testRunner = testRunner;
        this.inputParser = inputParser;
        this.workingSolutionRepository = workingSolutionRepository;
    }

    @Override
    public TaskRunResult execute(RunTaskCommand command) {
        Language userLanguage = languageRepository.findByName(command.language())
                .orElseThrow(() -> new NotFoundException("Language not found: " + command.language()));
        Task task = taskRepository.findById(new TaskId(command.taskId()))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + command.taskId()));
        WorkingSolution taskWorkingSolution = workingSolutionRepository.findByTaskId(new TaskId(command.taskId()))
                .orElseThrow(() -> new NotFoundException("Working solution not found for task id: " + command.taskId()));
        Language workingSolutionLanguage = languageRepository.findById(taskWorkingSolution.language())
                .orElseThrow(() -> new NotFoundException("Language not found: " + taskWorkingSolution.language()));

        List<Object> parsedInputs = inputParser.parse(task.getTaskSignature(), command.rawInputs());
        TestRunResult userResult = testRunner.run(parsedInputs, command.sourceCode(), userLanguage.getRuntimeImage());
        TestRunResult workingSolutionResult = testRunner.run(parsedInputs, taskWorkingSolution.sourceCode(), workingSolutionLanguage.getRuntimeImage());

        SubmissionResultStatus status = determineStatus(userResult, workingSolutionResult, task);
        return new TaskRunResult(command.rawInputs(), workingSolutionResult.output(), userResult.output(), status == SubmissionResultStatus.ACCEPTED, userResult.executionTimeMs(), status);
    }

    private static SubmissionResultStatus determineStatus(TestRunResult userResult, TestRunResult workingSolutionResult, Task task) {
        SubmissionResultStatus status = SubmissionResultStatus.ACCEPTED;

        if(userResult.errorType() != null && userResult.errorType() == ErrorType.RUNTIME_ERROR) status = SubmissionResultStatus.RUNTIME_ERROR;
        else if(userResult.errorType() != null && userResult.errorType() == ErrorType.COMPILATION_ERROR) status = SubmissionResultStatus.COMPILE_ERROR;
        else if(!userResult.output().equals(workingSolutionResult.output())) status = SubmissionResultStatus.WRONG_ANSWER;
        else if(userResult.executionTimeMs() > task.getTimeLimitMs()) status = SubmissionResultStatus.TIME_LIMIT_EXCEEDED;
        else if(userResult.memoryUsageKb() > task.getMemoryLimitKb()) status = SubmissionResultStatus.MEMORY_LIMIT_EXCEEDED;
        return status;
    }
}
