package com.dukeacademy.logic.commands.bookmark;

import java.util.logging.Logger;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.question.QuestionsLogic;
import com.dukeacademy.model.question.Question;

/**
 * Command to bookmark a question.
 */
public class BookmarkCommand implements Command {
    private final Logger logger;
    private final QuestionsLogic questionsLogic;
    private final int index;

    /**
     * Instantiates a new Bookmark command.
     * @param index                  the index
     * @param questionsLogic         the questions logic
     */
    public BookmarkCommand(int index, QuestionsLogic questionsLogic) {
        this.logger = LogsCenter.getLogger(BookmarkCommand.class);
        this.index = index - 1;
        this.questionsLogic = questionsLogic;
    }

    /**
     * Executes the bookmark command.
     * Execution of this command is conditional in nature. If question that user chooses to bookmark
     * is already bookmarked, we will simply notify the user of that through the CLI feedback panel.
     * Otherwise if the question that user selects is not already bookmarked, we will update the question
     * with a bookmarked version of the same question and notify user of a successful bookmark action.
     * @return
     * @throws CommandException
     */
    @Override
    public CommandResult execute() throws CommandException {

        try {
            // The question selected by the user, to add a bookmark
            Question userSelection = this.questionsLogic.getQuestion(index);
            boolean userSelectionIsBookmarked = userSelection.isBookmarked();

            // If question selected is already bookmarked
            if (userSelectionIsBookmarked) {
                // Simply notify user that question is already bookmarked
                String feedback = "Question " + (index + 1) + " : " + userSelection.getTitle()
                        + " - is already bookmarked.";
                return new CommandResult(feedback, false, false);
            } else {
                // Replace question selected with a bookmarked version
                Question bookmarkedQuestion = userSelection.withNewIsBookmarked(true);
                this.questionsLogic.setQuestion(index, bookmarkedQuestion);
                logger.info("Bookmarked question at index " + index + " : " + bookmarkedQuestion);

                // Notify user of successful bookmark action
                String feedback = "Bookmarked question " + (index + 1) + " : " + bookmarkedQuestion.getTitle();
                return new CommandResult(feedback, false, false);
            }

        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Index " + (index + 1) + " entered out of range for current list of questions.");
        }
    }

}
