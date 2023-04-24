package main.java.commandLine.commands;

import main.java.commandLine.Console;
import main.java.exeptions.WrongArgumentsException;
import main.java.managers.CollectionManager;

/**
 * Command that print information about collection
 */
public class Info extends AbstractCommand{
    private CollectionManager collectionManager;
    private Console console;

    public Info(Console console, CollectionManager collectionManager) {
        super("info", ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws WrongArgumentsException {
        if (!args.isBlank()) throw new WrongArgumentsException();
        String lastInitTime = (collectionManager.getLastInitTime() == null || collectionManager.getCollection().size()==0)
                ? "В сессии коллекция не инициализирована"
                : collectionManager.getLastInitTime().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Сведения о коллекции: \n")
                .append("Тип: " + collectionManager.collectionType() + "\n")
                .append("Количество элементов: " + collectionManager.collectionSize() + "\n")
                .append("Дата последней инициализации: " + lastInitTime + "\n");
        ;
        console.print(stringBuilder.toString());
    }
}
