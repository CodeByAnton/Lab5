package com.anton;



import com.anton.commandLine.Console;
import com.anton.commandLine.commands.*;
import com.anton.exeptions.ExitException;
import com.anton.managers.CollectionManager;
import com.anton.managers.CommandManager;
import com.anton.managers.FileManager;
import com.anton.managers.RuntimeManager;

import java.util.List;

public class Main {
    public static void main(String args[]){
        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();

        try {
            FileManager fileManager = new FileManager(console, collectionManager, args[0]);

            CommandManager commandManager = new CommandManager();
            try {
                fileManager.findFile();
                fileManager.createObjects();
            } catch (ExitException e) {
                console.println("До встречи!");
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
                    new ExecuteScript(console, commandManager),
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
        catch (ArrayIndexOutOfBoundsException e){
            console.printError("Файл должен передавться в качестве аргумента командной строки");
        } catch (NullPointerException e){
            console.printError("Данные в файле для комнады execute_script не верны");
        }



    }
}
