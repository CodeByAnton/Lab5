package main.java.commandLine.commands;

import main.java.commandLine.Console;
import main.java.exeptions.AbsenceElementException;
import main.java.exeptions.ExceptionInFileMode;
import main.java.exeptions.WrongArgumentsException;
import main.java.managers.CollectionManager;
import main.java.models.City;
import main.java.models.froms.CityForm;

import java.util.Collection;
import java.util.Objects;

public class RemoveLower extends AbstractCommand{

    private CollectionManager collectionManager;
    private Console console;
    public RemoveLower(Console console,CollectionManager collectionManager){
        super("remove_lower"," {element}: удалить из коллекции все элементы, меньше заданный");
        this.collectionManager=collectionManager;
        this.console=console;

    }

    @Override
    public void execute(String args) throws WrongArgumentsException, AbsenceElementException {
        if (!args.isBlank()) throw new WrongArgumentsException();
        try{
            console.println("Создание элемента City");
            City newElement=new CityForm(console).build();
            console.println(("Создание объекта City завершено"));
            Collection<City> forRemove=collectionManager.getCollection()
                    .stream().filter(Objects::nonNull)
                    .filter(city->city.compareTo(newElement)<0).toList();
            collectionManager.removeElements(forRemove);
            console.println("Удалены элементы больше чем заданный ");

        }catch (AbsenceElementException e){
            console.printError("В коллекции нет элеметов");
        } catch (ExceptionInFileMode e){
            console.printError("Поля в файле не валидны, объект не создан");
        }
    }
}
