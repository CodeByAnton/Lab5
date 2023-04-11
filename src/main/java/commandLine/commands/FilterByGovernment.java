package main.java.commandLine.commands;

import main.java.commandLine.Console;
import main.java.exeptions.CommandRuntimeException;
import main.java.exeptions.ExitException;
import main.java.exeptions.WrongArgumentsException;
import main.java.managers.CollectionManager;
import main.java.models.City;
import main.java.models.Government;

import java.util.Collection;

public class FilterByGovernment extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;
    public FilterByGovernment(Console console,CollectionManager collectionManager){
        super("filter_by_government"," government: вывести элементы, значение поля government которых равно заданному");
        this.collectionManager=collectionManager;
        this.console=console;
    }

    @Override
    public void execute(String args) throws ExitException, WrongArgumentsException, CommandRuntimeException {
        if (args.isBlank()) throw new WrongArgumentsException();
        try {
            Collection<City> collection=collectionManager.getCollection();
            Government government=Government.valueOf(args.trim().toUpperCase());
            for (City city:collection){
                if (city.getGovernment().equals(government)){
                    console.println(city.toString());
                }
            }


        } catch (IllegalArgumentException e){
            console.printError("Нужно вводить одно из полей Government");
            console.println(Government.names());
        }
    }
}
