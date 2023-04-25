package com.anton.commandLine.commands;

import com.anton.commandLine.Console;
import com.anton.exeptions.CommandRuntimeException;
import com.anton.exeptions.ExitException;
import com.anton.exeptions.WrongArgumentsException;
import com.anton.managers.CollectionManager;
import com.anton.models.City;


import java.util.Collection;
import java.util.Comparator;

/**
 *
 */
public class PrintAscending extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;
    public PrintAscending(Console console,CollectionManager collectionManager){
        super("print_ascending",": вывести элементы коллекции в порядке возрастания");
        this.collectionManager=collectionManager;
        this.console=console;
    }

    @Override
    public void execute(String args) throws ExitException, WrongArgumentsException, CommandRuntimeException {
        if (!args.isBlank()) throw new WrongArgumentsException();
        Collection<City> cities=collectionManager.getCollection();
        cities.stream()
                .sorted(Comparator.comparing(City::getArea) )
                .forEach(city ->console.println(city.toString()));

    }
}
