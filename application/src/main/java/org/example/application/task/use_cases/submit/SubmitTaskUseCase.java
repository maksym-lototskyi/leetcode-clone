package org.example.application.task.use_cases.submit;

import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.submission.ports.out.SubmissionRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.task.ports.out.TestCaseRepository;
import org.example.application.task.use_cases.run.InputParser;
import org.example.application.task.use_cases.run.TestRunner;
import org.example.application.user.ports.out.UserRepository;
import org.example.domain.language.Language;
import org.example.domain.submission.Submission;
import org.example.domain.submission.SubmissionResult;
import org.example.domain.submission.TestRun;
import org.example.domain.task.Task;
import org.example.domain.task.TaskId;
import org.example.domain.task.TestCase;
import org.example.domain.user.UserId;

import java.util.List;
import java.util.UUID;

public class SubmitTaskUseCase implements SubmitTaskInputBoundary{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TestCaseRepository testCaseRepository;
    private final SubmissionRepository submissionRepository;
    private final LanguageRepository languageRepository;
    private final TestRunner taskRunner;
    private final InputParser parser;

    public SubmitTaskUseCase(TaskRepository taskRepository, UserRepository userRepository, TestCaseRepository testCaseRepository, SubmissionRepository submissionRepository, LanguageRepository languageRepository, TestRunner taskRunner, InputParser parser) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.testCaseRepository = testCaseRepository;
        this.submissionRepository = submissionRepository;
        this.languageRepository = languageRepository;
        this.taskRunner = taskRunner;
        this.parser = parser;
    }

    public void execute(SubmitTaskCommand command, UUID userId) {
        Task task = taskRepository.findById(TaskId.of(command.taskId()))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + command.taskId()));
        if(!userRepository.existsById(UserId.of(userId))){
            throw new NotFoundException("User not found with id: " + userId);
        }
        Language language = languageRepository.findByName(command.language())
                .orElseThrow(() -> new NotFoundException("Language not found with name: " + command.language()));
        List<TestCase> testCases = testCaseRepository.findAllByTaskId(task.getTaskId());

        Submission submission = new Submission(task.getTaskId(), UserId.of(userId), language.getId(), command.sourceCode());
        submissionRepository.save(submission);

        SubmissionResult result = evaluateTestCases(testCases, task, language, command.sourceCode());

        submission.attachResult(result);
        submissionRepository.save(submission);
    }

    private SubmissionResult evaluateTestCases(List<TestCase> testCases, Task task, Language language, String code) {
        long totalExecutionTime = 0;
        long totalMemoryUsed = 0;

        int passedTestCases = 0;

        for (TestCase testCase : testCases) {
            var runResult = taskRunner.run(parser.parse(task.getTaskSignature(), testCase.input().params()), code, language.getRuntimeImage());

            TestRun failingTestCase = TestRun.failing(testCase.testCaseId(), runResult.output());

            if (!runResult.output().equals(testCase.expectedOutput().value()))
                return SubmissionResult.wrongAnswer(failingTestCase, passedTestCases, testCases.size());

            if (runResult.executionTimeMs() > task.getTimeLimitMs())
                return SubmissionResult.timeLimitExceeded(failingTestCase, passedTestCases, testCases.size());

            if (runResult.memoryUsageKb() > task.getMemoryLimitKb())
                return SubmissionResult.memoryLimitExceeded(failingTestCase, passedTestCases, testCases.size());


            switch (runResult.errorType()) {
                case COMPILATION_ERROR -> {
                    return SubmissionResult.compileError(runResult.error(), passedTestCases, testCases.size());
                }
                case RUNTIME_ERROR -> {
                    return SubmissionResult.runtimeError(runResult.error(), passedTestCases, testCases.size());
                }
                case null -> {}
            };


            totalExecutionTime += runResult.executionTimeMs();
            totalMemoryUsed = Math.max(totalMemoryUsed, runResult.memoryUsageKb());
            passedTestCases++;
        }

        return SubmissionResult.accepted(totalExecutionTime, totalMemoryUsed, passedTestCases, testCases.size());
    }
}
