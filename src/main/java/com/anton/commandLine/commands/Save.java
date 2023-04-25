package com.anton.commandLine.commands;

import com.anton.commandLine.Console;
import com.anton.exeptions.EmptyCollectionException;
import com.anton.exeptions.WrongArgumentsException;
import com.anton.managers.FileManager;

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
