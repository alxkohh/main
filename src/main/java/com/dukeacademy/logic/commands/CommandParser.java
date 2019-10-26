package com.dukeacademy.logic.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.commons.exceptions.IllegalValueException;
import com.dukeacademy.commons.util.StringUtil;
import com.dukeacademy.logic.commands.exceptions.InvalidCommandArgumentsException;
import com.dukeacademy.logic.commands.exceptions.InvalidCommandKeywordException;
import com.dukeacademy.logic.parser.exceptions.ParseException;

/**
 * Helper class used by CommandLogicManager to keep track and instantiate registered Command classes.
 */
public class CommandParser {
    private static final String commandNotRecognizedMessage = "Command word not recognized : ";
    private static final String commandNotValidWord = "Command is not a valid word : ";

    private Map<String, CommandSupplier> commandFactoryMap;
    private Logger logger = LogsCenter.getLogger(CommandParser.class);

    public CommandParser() {
        this.commandFactoryMap = new HashMap<>();
    }

    /**
     * Registers a command into the command parser for future access.
     *
     * @param commandWord     the keyword of the command.
     * @param commandSupplier the supplier of the command.
     */
    public void registerCommand(String commandWord, CommandSupplier commandSupplier) {
        this.commandFactoryMap.put(commandWord, commandSupplier);
    }

    /**
     * Instantiates and returns a Command class instance based on the command text provided.
     *
     * @param commandText the command text used to invoke the command.
     * @return a command instance corresponding to the command text.
     * @throws ParseException if the command keyword or arguments are invalid.
     */
    public Command parseCommandText(String commandText) throws InvalidCommandKeywordException,
            InvalidCommandArgumentsException {
        String commandWord;
        String commandArguments;

        try {
            commandWord = StringUtil.getFirstWord(commandText);
            commandArguments = StringUtil.removeFirstWord(commandText);
        } catch (IllegalValueException e) {
            logger.warning(commandNotValidWord + commandText);
            throw new InvalidCommandKeywordException(commandNotValidWord + commandText);
        }

        CommandSupplier supplier = this.commandFactoryMap.get(commandWord);
        if (supplier == null) {
            logger.warning(commandNotRecognizedMessage + commandWord);
            throw new InvalidCommandKeywordException(commandNotRecognizedMessage + commandWord);
        }

        try {
            return supplier.getCommand(commandArguments);
        } catch (InvalidCommandArgumentsException e) {
            logger.warning("Invalid command arguments : " + commandArguments);
            throw e;
        }
    }
}
