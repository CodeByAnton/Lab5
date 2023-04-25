package com.anton.commandLine.commands;

import com.anton.commandLine.Console;
import com.anton.exeptions.CommandRuntimeException;
import com.anton.exeptions.ExitException;
import com.anton.exeptions.WrongArgumentsException;
import com.anton.managers.CollectionManager;
import com.anton.models.City;
import com.anton.models.Government;


import java.util.Collection;

/**
 * Command that print all collection{@link City} element, which field equals given
 */
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
