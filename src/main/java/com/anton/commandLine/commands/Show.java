package com.anton.commandLine.commands;

import com.anton.commandLine.Console;
import com.anton.exeptions.WrongArgumentsException;
import com.anton.managers.CollectionManager;
import com.anton.models.City;


import java.util.Collection;


public class Show extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;

    public Show(Console console, CollectionManager collectionManager) {
        super("show", ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws WrongArgumentsException {
        if (!args.isBlank()) throw new WrongArgumentsException();
        Collection<City> collection = collectionManager.getCollection();




        if (collection == null || collection.isEmpty()) {
            console.printError("Коллекцмя еще не инициализирована");
            return;
        }
        console.println(collection.toString());
    }
}
