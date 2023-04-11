package main.java.commandLine.commands;

import main.java.exeptions.*;
import main.java.managers.CommandManager;
import main.java.managers.FileManager;
import main.java.commandLine.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import main.java.commandLine.Console;

public class ExecuteScript extends AbstractCommand{

    private FileManager fileManager;
    private final Console console;
    private final CommandManager commandManager;

    public ExecuteScript(Console console, FileManager fileManager, CommandManager commandManager) {
        super("execute_script", " file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.console = console;
        this.fileManager = fileManager;
        this.commandManager = commandManager;
    }


    @Override
    public void execute(String args) throws CommandRuntimeException, ExitException, WrongArgumentsException {
        if (args == null || args.isEmpty()) {
            console.printError("Путь не распознан");
            return;
        }
        else console.printError("Путь получен успешно");

        try {
            Console.setFileMode(true);
            ExecuteFileManager.pushFile(args);
            for (String line = ExecuteFileManager.readLine(); line != null; line = ExecuteFileManager.readLine()) {
                try{
                    commandManager.addToHistory(line);
                    String[] massiv = (line + " ").split(" ", 2);
                    if (massiv[0].isBlank()) return;
                    if (massiv[0].equals("execute_script")){
                        if(ExecuteFileManager.fileRepeat(massiv[1])){
                            console.printError("Найдена рекурсия по пути " + new File(massiv[1]).getAbsolutePath());
                            continue;
                        }
                    }
                    console.println("Выполнение команды " + massiv[0]);
                    commandManager.execute(massiv[0], massiv[1]);
                    if (massiv[0].equals("execute_script")){
                        ExecuteFileManager.popFile();
                    }
                } catch (NoSuchElementException exception) {
                    console.printError("Пользовательский ввод не обнаружен!");
                } catch (NonexistCommandException e) {
                    console.printError("Такой команды нет в списке");
                } catch (WrongArgumentsException e) {
                    console.printError("Введены неправильные аргументы команды");
                } catch (CommandRuntimeException e) {
                    console.printError("Ошибка при исполнении команды");
                }
            }
            ExecuteFileManager.popFile();
        }  catch (NonexistCommandException e){
            console.printError("Такой команды не существует");
        } catch (FileNotFoundException fileNotFoundException){
            console.printError("Такого файла не существует");
        } catch (IOException e) {
            console.printError("Ошибка ввода вывода");
        }
        Console.setFileMode(false);
    }

}
