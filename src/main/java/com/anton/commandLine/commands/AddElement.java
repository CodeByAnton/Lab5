package com.anton.commandLine.commands;

import com.anton.commandLine.Console;
import com.anton.exeptions.ExceptionInFileMode;
import com.anton.exeptions.InvalidFormException;
import com.anton.exeptions.WrongArgumentsException;
import com.anton.managers.CollectionManager;
import com.anton.models.forms.CityForm;
import com.anton.models.City;


/**
 * Command that create new City element and add his in to collection{@link City}
 */
public class AddElement extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;
    public AddElement(Console console,CollectionManager collectionManager){
        super("add"," {element}: добавить новый элемент в коллекцию");
        this.collectionManager=collectionManager;
        this.console=console;
    }

    @Override
    public void execute(String args) throws WrongArgumentsException {
        if (!args.isBlank()) throw new WrongArgumentsException();
        try{
            console.println("Создание объекта City");
            collectionManager.addElement(new CityForm(console).build());
            console.println("Создание объекта City успешно завершено");

        } catch (InvalidFormException e){
            console.printError("Поля объекта не валидны! Объект не создан");
        } catch (ExceptionInFileMode e){
            console.printError("Поля объекта не валидны! Объект не создан");
        }
    }
}
