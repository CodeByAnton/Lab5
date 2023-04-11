package main.java.commandLine.commands;

import main.java.commandLine.Console;
import main.java.exeptions.CommandRuntimeException;
import main.java.exeptions.ExitException;
import main.java.exeptions.WrongArgumentsException;
import main.java.managers.CollectionManager;
import main.java.models.City;

import java.util.Collection;
import java.util.Comparator;

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
