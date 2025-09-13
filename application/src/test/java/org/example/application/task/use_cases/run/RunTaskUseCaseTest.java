package org.example.application.task.use_cases.run;

import org.example.application.task.application_service.FakeTestRunner;
import org.example.application.task.application_service.TestInputParser;
import org.example.application.task.repos.TestLanguageRepository;
import org.example.application.task.repos.TestTaskRepository;
import org.example.domain.language.LanguageId;
import org.example.domain.submission.SubmissionResultStatus;
import org.example.domain.task.WorkingSolution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class RunTaskUseCaseTest {
    private TestLanguageRepository languageRepository = new TestLanguageRepository();
    private TestTaskRepository taskRepository = new TestTaskRepository();
    private FakeTestRunner runner = new FakeTestRunner();
    private InputParser parser = new TestInputParser();
    private RunTaskUseCase useCase = new RunTaskUseCase(languageRepository,
            taskRepository,
            runner,
            parser);

    @Test
    public void testThatRunTaskUseCaseReturnsWrongAnswerIfOutputsDoNotMatch(){
        runner.setUserResult(TestRunResult.success("1", 1, 1));
        runner.setWorkingSolutionResult(TestRunResult.success("2", 1, 1));
        taskRepository.setWorkingSolution(
                new WorkingSolution(LanguageId.generate(), "code")
        );
        var response = useCase.execute(new RunTaskCommand(UUID.randomUUID(), List.of("2"), "code", "python"));

        Assertions.assertFalse(response.isPassed());
        Assertions.assertEquals(response.status(), SubmissionResultStatus.WRONG_ANSWER);
        Assertions.assertEquals(response.expectedOutput(), "2");
        Assertions.assertEquals(response.actualOutput(), "1");
        Assertions.assertEquals(response.input(), List.of("2"));
    }
}