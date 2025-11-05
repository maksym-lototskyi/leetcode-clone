package org.example.application.task.use_cases.submit;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.submission.ports.out.SubmissionRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.task.use_cases.run.*;
import org.example.application.user.ports.out.UserRepository;
import org.example.domain.class_definition.ClassDefinition;
import org.example.domain.language.Language;
import org.example.domain.submission.Submission;
import org.example.domain.submission.SubmissionResult;
import org.example.domain.submission.TestRun;
import org.example.domain.task.Task;
import org.example.domain.task.TaskId;
import org.example.domain.task.TestCase;
import org.example.domain.user.UserId;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

class SubmitTaskUseCase implements SubmitTaskInputBoundary{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final SubmissionRepository submissionRepository;
    private final LanguageRepository languageRepository;
    private final ClassDefinitionRepository classDefinitionRepository;
    private final TestRunner taskRunner;
    private final ObjectConverter converter;

    public SubmitTaskUseCase(TaskRepository taskRepository, UserRepository userRepository, SubmissionRepository submissionRepository, LanguageRepository languageRepository, ClassDefinitionRepository classDefinitionRepository, TestRunner taskRunner, ObjectConverter converter) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.submissionRepository = submissionRepository;
        this.languageRepository = languageRepository;
        this.classDefinitionRepository = classDefinitionRepository;
        this.taskRunner = taskRunner;
        this.converter = converter;
    }

    public void execute(SubmitTaskCommand command, UUID userId) throws IOException {
        Task task = taskRepository.loadTaskForRuntime(TaskId.of(command.taskId()))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + command.taskId()));
        if(!userRepository.existsById(UserId.of(userId))){
            throw new NotFoundException("User not found with id: " + userId);
        }
        Language language = languageRepository.findByName(command.language())
                .orElseThrow(() -> new NotFoundException("Language not found with name: " + command.language()));

        Submission submission = new Submission(task.getTaskId(), UserId.of(userId), language.getId(), command.sourceCode());
        submissionRepository.save(submission);

        List<ClassDefinition> relatedClassDefinitions = classDefinitionRepository.findAllByIds(task.getRelatedClassDefinitions());
        List<AdditionalClassDto> additionalClasses = ClassImplementationMapper.convertToAdditionalClassDtos(relatedClassDefinitions, language.getId());

        SubmissionResult result = evaluateTestCases(additionalClasses, task, language, command.sourceCode());

        submission.attachResult(result);
        submissionRepository.save(submission);
    }

    private SubmissionResult evaluateTestCases(List<AdditionalClassDto> additionalClasses, Task task, Language language, String code) throws IOException {
        List<TestCase> testCases = task.getTestCases();
        long totalExecutionTime = 0;
        long totalMemoryUsed = 0;

        int passedTestCases = 0;

        for (TestCase testCase : testCases) {
            LanguageDto languageDto = new LanguageDto(
                    language.getName(),
                    language.getFileExtension()
            );
            ExecutionContext context = new ExecutionContext(
                    code,
                    testCase.input().getInput(),
                    converter.convert(task.getTaskSignature()),
                    additionalClasses
            );
            var runResult = taskRunner.run(languageDto, context);

            TestRun failingTestCase = TestRun.failing(testCase.testCaseId(), runResult.result());

            if (!runResult.result().equals(testCase.expectedOutput().value()))
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
