package main.java.commandLine.commands;

import main.java.commandLine.Console;
import main.java.exeptions.AbsenceIdException;
import main.java.exeptions.WrongArgumentsException;
import main.java.managers.CollectionManager;

public class RemoveById extends AbstractCommand {
    private CollectionManager collectionManager;
    private Console console;

    public RemoveById(Console console, CollectionManager collectionManager) {
        super("remove_by_id", " id: удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws WrongArgumentsException, AbsenceIdException {
        if (args.isBlank()) throw new WrongArgumentsException();
        try {
            int id = Integer.parseInt(args.trim());
            if (!collectionManager.checkExist(id)) throw new AbsenceIdException();
            collectionManager.removeElement(collectionManager.getById(id));
            console.println(("Объект удален успешно"));
        } catch (AbsenceIdException e) {
            console.printError("В коллекции нет элемента с таким id");
        } catch (NumberFormatException e) {
            console.printError("id должно быть числом типа int");
        }
    }
}