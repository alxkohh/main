package com.dukeacademy.logic.commands.browse;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandFactory;
import com.dukeacademy.logic.question.QuestionsLogic;
import com.dukeacademy.model.state.ApplicationState;

/**
 * The type Browse command factory.
 */
public class BrowseCommandFactory implements CommandFactory {
    private final QuestionsLogic questionsLogic;
    private final ApplicationState applicationState;

    /**
     * Instantiates a new Attempt command factory.
     *
     * @param questionsLogic the questions logic
     */
    public BrowseCommandFactory(QuestionsLogic questionsLogic, ApplicationState applicationState) {
        this.questionsLogic = questionsLogic;
        this.applicationState = applicationState;
    }

    @Override
    public String getCommandWord() {
        return "browse";
    }

    @Override public Command getCommand(String commandArguments) {
        return new BrowseCommand(questionsLogic, applicationState, commandArguments);
    }
}
