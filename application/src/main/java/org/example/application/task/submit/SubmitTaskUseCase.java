package org.example.application.task.submit;

import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.SubmissionRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.task.ports.out.TaskRunner;
import org.example.application.task.ports.out.TestCaseRepository;
import org.example.domain.model.language.Language;
import org.example.domain.model.submission.Submission;
import org.example.domain.model.submission.SubmissionResult;
import org.example.domain.model.task.Task;
import org.example.domain.model.task.TaskId;
import org.example.domain.model.task.test_case.TestCase;

import java.util.List;

public class SubmitTaskUseCase {
    private final TaskRepository taskRepository;
    private final TestCaseRepository testCaseRepository;
    private final SubmissionRepository submissionRepository;
    private final LanguageRepository languageRepository;
    private final TaskRunner taskRunner;

    public SubmitTaskUseCase(TaskRepository taskRepository, TestCaseRepository testCaseRepository, SubmissionRepository submissionRepository, LanguageRepository languageRepository, TaskRunner taskRunner) {
        this.taskRepository = taskRepository;
        this.testCaseRepository = testCaseRepository;
        this.submissionRepository = submissionRepository;
        this.languageRepository = languageRepository;
        this.taskRunner = taskRunner;
    }

    public void execute(SubmitTaskCommand command) {
        Task task = taskRepository.findById(TaskId.of(command.taskId()))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + command.taskId()));

        Language language = languageRepository.findByName(command.language())
                .orElseThrow(() -> new NotFoundException("Language not found with name: " + command.language()));
        List<TestCase> testCases = testCaseRepository.findAllByTaskId(task.taskId());

        Submission submission = new Submission(task.taskId(), language.getId(), command.sourceCode());
        submissionRepository.save(submission);

        SubmissionResult result = evaluateTestCases(testCases, task, language, command.sourceCode());

        submission.attachResult(result);
        submissionRepository.save(submission);
    }

    private SubmissionResult evaluateTestCases(List<TestCase> testCases, Task task, Language language, String code) {
        long totalExecutionTime = 0;
        long totalMemoryUsed = 0;

        for (TestCase testCase : testCases) {
            var runResult = taskRunner.run(CommandMapper.map(testCase, language.getRuntimeImage(), code));

            if (!runResult.isPassed()) {
                return SubmissionResult.wrongAnswer(testCase.getInput());
            }

            if (runResult.executionTimeMs() > task.timeLimitMs()) {
                return SubmissionResult.timeLimitExceeded(testCase.getInput());
            }

            if (runResult.memoryUsageKb() > task.memoryLimitKb()) {
                return SubmissionResult.memoryLimitExceeded(testCase.getInput());
            }

            totalExecutionTime += runResult.executionTimeMs();
            totalMemoryUsed = Math.max(totalMemoryUsed, runResult.memoryUsageKb());
        }

        return SubmissionResult.accepted(totalExecutionTime, totalMemoryUsed);
    }
}
