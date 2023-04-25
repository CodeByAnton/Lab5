package com.anton.commandLine.commands;

import com.anton.commandLine.*;
import com.anton.exeptions.AbsenceIdException;
import com.anton.exeptions.ExceptionInFileMode;
import com.anton.exeptions.InvalidFormException;
import com.anton.exeptions.WrongArgumentsException;
import com.anton.managers.CollectionManager;
import com.anton.models.City;
import com.anton.models.forms.CityForm;


public class Update extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;
    public Update(Console console,CollectionManager collectionManager){
        super("update"," id {element}: бновить значение элемента коллекции, id оторого равен заданному ");
        this.collectionManager=collectionManager;
        this.console=console;
    }

    @Override
    public void execute(String args) throws WrongArgumentsException, AbsenceIdException {
        if (args.isBlank()) throw new WrongArgumentsException();
        try{
            int id=Integer.parseInt(args.trim());
            if (!collectionManager.checkExist(id)) throw new AbsenceIdException();
            console.println("Обновление значения элемента коллекции с данным id");
            City newCity=new CityForm(console).build();
            collectionManager.editById(id,newCity);
            console.println("Обновление значения элемента с данным id авершено успешно");

        }catch (AbsenceIdException e){
            console.printError("В коллекции нет элемента с таким id");

        }catch (InvalidFormException e){
            console.printError("Поля не валидны, объект не создан");
        }catch (NumberFormatException e){
            console.printError("id должно быть число типа int");

        }catch (ExceptionInFileMode e){
            console.printError("Поля в файле не валидны");
        }
    }
}
