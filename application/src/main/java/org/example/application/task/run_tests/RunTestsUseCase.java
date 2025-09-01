package org.example.application.task.run_tests;

import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.language.Language;
import org.example.domain.submission.SubmissionResultStatus;
import org.example.domain.task.Input;
import org.example.domain.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class RunTestsUseCase implements RunTestsInputBoundary{
    private final LanguageRepository languageRepository;
    private final TaskRepository taskRepository;
    private final CustomTestCaseRunner customTestCaseRunner;

    public RunTestsUseCase(LanguageRepository languageRepository, TaskRepository taskRepository, CustomTestCaseRunner customTestCaseRunner) {
        this.languageRepository = languageRepository;
        this.taskRepository = taskRepository;
        this.customTestCaseRunner = customTestCaseRunner;
    }

    @Override
    public RunTestsResultDto execute(RunTestsCommand command) {
        Language language = languageRepository.findByName(command.language())
                .orElseThrow(() -> new NotFoundException("Language not found: " + command.language()));
        Task task = taskRepository.findById(command.taskId())
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + command.taskId()));

        List<TestRunResultDto> results = new ArrayList<>();
        SubmissionResultStatus status = SubmissionResultStatus.ACCEPTED;
        long totalExecutionTimeMs = 0;

        for(Input input : command.inputs()){
            TestRunResultDto result = customTestCaseRunner.runTestCase(
                    input,
                    command.sourceCode(),
                    task.workingSolution(),
                    language.getRuntimeImage()
            );

            if(status == SubmissionResultStatus.ACCEPTED) {
                if (!result.isPassed()) status = SubmissionResultStatus.WRONG_ANSWER;
                else if (result.executionTimeMs() > task.timeLimitMs()) status = SubmissionResultStatus.TIME_LIMIT_EXCEEDED;
                else if (result.memoryUsageKb() > task.memoryLimitKb()) status = SubmissionResultStatus.MEMORY_LIMIT_EXCEEDED;
            }

            results.add(result);
            totalExecutionTimeMs += result.executionTimeMs();
        }

        return new RunTestsResultDto(status, Collections.unmodifiableList(results), totalExecutionTimeMs);
    }
}
