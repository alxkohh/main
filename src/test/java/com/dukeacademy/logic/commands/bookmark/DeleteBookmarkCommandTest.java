package com.dukeacademy.logic.commands.bookmark;

import static com.dukeacademy.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.testutil.MockQuestionsLogic;

class DeleteBookmarkCommandTest {

    @Test
    void testExecute() throws CommandException {
        MockQuestionsLogic questionsLogic = MockQuestionsLogic.getMockQuestionsLogicWithTypicalQuestions();
        DeleteBookmarkCommand deleteBookmarkCommand = new DeleteBookmarkCommand(-1, questionsLogic);
        assertThrows(CommandException.class,
                "Index -1 entered out of range for current list of questions.", () ->
                        deleteBookmarkCommand.execute());
    }
}
