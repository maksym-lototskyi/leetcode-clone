package org.example.application.task.submit;

import org.example.domain.model.task.test_case.TestCase;

class CommandMapper {
    static RunTestCaseCommand map(TestCase testCase, String languageImage, String code){
        return new RunTestCaseCommand(
                testCase.getInput(),
                testCase.getExpectedOutput(),
                languageImage,
                code
        );
    }
}
