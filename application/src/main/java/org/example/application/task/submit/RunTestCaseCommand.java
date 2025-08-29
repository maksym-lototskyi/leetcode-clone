package org.example.application.task.submit;


public record RunTestCaseCommand(String input, String expectedOutput, String languageImage, String code) {

}
