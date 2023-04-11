package main.java.commandLine.commands;

import main.java.commandLine.Console;
import main.java.exeptions.CommandRuntimeException;
import main.java.exeptions.ExitException;
import main.java.exeptions.WrongArgumentsException;
import main.java.managers.CollectionManager;
import main.java.models.City;

import java.util.Collection;
import java.util.Comparator;

public class MinByPopulation extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;
    public MinByPopulation(Console console,CollectionManager collectionManager){
        super("min_by_population",": вывести любой объект из коллекции, значение поля population которого является минимальным ");
        this.collectionManager=collectionManager;
        this.console=console;
    }

    @Override
    public void execute(String args) throws  WrongArgumentsException {
        if (!args.isBlank()) throw new WrongArgumentsException();
        Collection<City> city=collectionManager.getCollection();
        console.println( city.stream()
                .min(Comparator.comparing(City::getPopulation)).toString());



    }
}
