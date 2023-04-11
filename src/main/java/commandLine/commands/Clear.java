package main.java.commandLine.commands;

import main.java.commandLine.Console;
import main.java.exeptions.WrongArgumentsException;
import main.java.managers.CollectionManager;

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
