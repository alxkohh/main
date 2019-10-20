package com.dukeacademy.model.question;

/**
 * Represents a test case for a question.
 */
public class TestCase {
    private final String input;
    private final String expectedResult;

    public TestCase(String input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    public String getInput() {
        return input;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    @Override
    public String toString() {
        return "Input: " + input + "Expected: " + expectedResult;
    }
}