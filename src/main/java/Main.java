package main.java;


import main.java.commandLine.Console;
import main.java.commandLine.commands.*;
import main.java.exeptions.ExitException;
import main.java.managers.CollectionManager;
import main.java.managers.CommandManager;
import main.java.managers.FileManager;
import main.java.managers.RuntimeManager;

import java.util.List;

public class Main {
    public static void main(String args[]){
        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(console, collectionManager,"check.xml");
        CommandManager commandManager = new CommandManager();
        try{
            fileManager.findFile();
            fileManager.createObjects();
        } catch (ExitException e){
            console.println(" До встречи!");
            return;
        }

        commandManager.addCommand(List.of(
                new Help(console, commandManager),
                new Info(console, collectionManager),
                new Show(console, collectionManager),
                new AddElement(console, collectionManager),
                new Update(console, collectionManager),
                new RemoveById(console, collectionManager),
                new Clear(console, collectionManager),
                new Save(console, fileManager),
                new ExecuteScript(console, fileManager, commandManager),
                new Exit(),
                new RemoveLower(console, collectionManager),
                new RemoveGreater(console, collectionManager),
                new History(console, commandManager),
                new FilterByGovernment(console, collectionManager),
                new PrintAscending(console, collectionManager),
                new MinByPopulation(console, collectionManager)
        ));
        new RuntimeManager(console, commandManager).interactiveMode();



    }
}
