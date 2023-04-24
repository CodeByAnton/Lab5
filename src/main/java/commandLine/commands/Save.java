package main.java.commandLine.commands;

import main.java.commandLine.Console;
import main.java.exeptions.EmptyCollectionException;
import main.java.exeptions.WrongArgumentsException;
import main.java.managers.FileManager;

public class Save extends AbstractCommand {
    private FileManager fileManager;
    private Console console;

    public Save(Console console, FileManager fileManager) {
        super("save", ": сохранить коллекцию в файл");
        this.fileManager = fileManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws WrongArgumentsException {
        if (!args.isBlank()) throw new WrongArgumentsException();
        try {
            fileManager.saveObjects();
        } catch (EmptyCollectionException e) {
            console.printError("Коллекция ещё не проинициализирована, сохранять нечего");
            return;
        }
        console.println("Объекты успешно сохранены");
    }
}
