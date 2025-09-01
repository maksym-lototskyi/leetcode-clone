package org.example.domain.task;

import lombok.Getter;

@Getter
public class TestCase {
    private final TestCaseId testCaseId;
    private final TaskId taskId;
    private final Input input;
    private final Output expectedOutput;

    TestCase(TaskId taskId, Input input, Output expectedOutput){
        this.testCaseId = TestCaseId.generate();
        this.taskId = taskId;
        this.input = input;
        this.expectedOutput = expectedOutput;

        if(taskId == null){
            throw new IllegalArgumentException("TaskId cannot be null");
        }
        if(input == null || expectedOutput == null){
            throw new IllegalArgumentException("Input and expected output cannot be null");
        }
    }
}
