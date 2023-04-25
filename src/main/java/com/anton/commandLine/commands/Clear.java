package com.anton.commandLine.commands;

import com.anton.commandLine.Console;
import com.anton.exeptions.WrongArgumentsException;
import com.anton.managers.CollectionManager;


/**
 * Command that clear collection
 */
public class Clear extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;

    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", ": очистить коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws WrongArgumentsException {
        if (!args.isBlank()) throw new WrongArgumentsException();
        collectionManager.clear();
        console.println("Коллекция очищена");
    }
}
