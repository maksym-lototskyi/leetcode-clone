package org.example.application.task.use_cases.submit;

import org.example.application.task.use_cases.run.*;
import org.example.domain.model.language.Language;
import org.example.domain.model.submission.SubmissionResult;
import org.example.domain.model.submission.TestRun;
import org.example.domain.model.task.Task;
import org.example.domain.model.task.TestCase;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class TestCaseEvaluator {
    private final TestRunner taskRunner;
    private final ObjectConverter converter;

    public TestCaseEvaluator(TestRunner taskRunner, ObjectConverter converter) {
        this.taskRunner = taskRunner;
        this.converter = converter;
    }

    public SubmissionResult evaluateTestCases(Task task, Language language, List<AdditionalClassDto> classes, String sourceCode) {
        Set<TestCase> testCases = task.getTestCases();
        long totalExecutionTime = 0;
        long totalMemoryUsed = 0;

        int passedTestCases = 0;

        for (TestCase testCase : testCases) {
            ExecutionContext context = new ExecutionContext(
                    sourceCode,
                    testCase.input().getInput(),
                    converter.convert(task.getTaskSignature()),
                    classes
            );
            TestRunResult runResult;
            try {
                runResult = taskRunner.run(LanguageMapper.map(language), context);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            TestRun failingTestCase = TestRun.failing(testCase.testCaseId(), runResult.result());

            if (!runResult.result().equals(testCase.expectedOutput().value()))
                return SubmissionResult.wrongAnswer(failingTestCase, passedTestCases, testCases.size());

            if (runResult.executionTimeMs() > task.getTimeLimitMs())
                return SubmissionResult.timeLimitExceeded(failingTestCase, passedTestCases, testCases.size());

            if (runResult.memoryUsageKb() > task.getMemoryLimitKb())
                return SubmissionResult.memoryLimitExceeded(failingTestCase, passedTestCases, testCases.size());


            switch (runResult.errorType()) {
                case COMPILATION_ERROR -> {
                    return SubmissionResult.compileError(failingTestCase, runResult.error(), passedTestCases, testCases.size());
                }
                case RUNTIME_ERROR -> {
                    return SubmissionResult.runtimeError(failingTestCase, runResult.error(), passedTestCases, testCases.size());
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
