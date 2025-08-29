package org.example.domain.model.task.test_case;

import lombok.Getter;
import org.example.domain.model.task.TaskId;

@Getter
public class TestCase {
    private final TestCaseId testCaseId;
    private final TaskId taskId;
    private final String input;
    private final String expectedOutput;

    public TestCase(TaskId taskId, String input, String expectedOutput){
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
        if(input.isEmpty() || expectedOutput.isEmpty()){
            throw new IllegalArgumentException("Input and expected output cannot be empty");
        }

    }
}
