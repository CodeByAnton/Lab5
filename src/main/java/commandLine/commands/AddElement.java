package main.java.commandLine.commands;

import main.java.commandLine.Console;
import main.java.exeptions.*;
import main.java.managers.CollectionManager;
import main.java.models.City;
import main.java.models.froms.CityForm;

public class AddElement extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;
    public AddElement(Console console,CollectionManager collectionManager){
        super("add"," {element}: добавить новый элемент в коллекцию");
        this.collectionManager=collectionManager;
        this.console=console;
    }

    @Override
    public void execute(String args) throws  WrongArgumentsException{
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
