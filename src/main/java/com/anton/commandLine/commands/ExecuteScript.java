package com.anton.commandLine.commands;

import com.anton.commandLine.ExecuteFileManager;
import com.anton.exeptions.CommandRuntimeException;
import com.anton.exeptions.ExitException;
import com.anton.exeptions.NonexistCommandException;
import com.anton.exeptions.WrongArgumentsException;
import com.anton.managers.CommandManager;
import com.anton.commandLine.Console;



import java.io.*;

import java.util.NoSuchElementException;



/**
 * Command for execute script from file
 */
public class ExecuteScript extends AbstractCommand {

    private final Console console;
    private final CommandManager commandManager;

    public ExecuteScript(Console console, CommandManager commandManager) {
        super("execute_script", "file_name - считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.console = console;
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String args) throws CommandRuntimeException, ExitException, WrongArgumentsException {
        if (args == null || args.isEmpty()) {
            console.printError("Путь не распознан");
            return;
        } else {
            console.println("Путь получен успешно");
        }

        try {
            Console.setFileMode(true);
            ExecuteFileManager.pushFile(args);
            while (!ExecuteFileManager.getFileReaders().isEmpty()) {
                String line = ExecuteFileManager.readLine();
                if (line == null) {
                    ExecuteFileManager.popFile();
                    continue;
                }
                try {
                    commandManager.addToHistory(line);
                    String[] massiv = (line + " ").split(" ", 2);
                    if (massiv[0].isBlank()) continue;
                    if (massiv[0].equals("execute_script")) {
                        if (ExecuteFileManager.fileRepeat(massiv[1])) {
                            console.printError("Найдена рекурсия по пути " + new File(massiv[1]).getAbsolutePath());
                            continue;
                        }
                    }
                    console.println("Выполнение команды " + massiv[0]);
                    commandManager.execute(massiv[0], massiv[1]);
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
        } catch (NonexistCommandException e) {
            console.printError("Такой команды не существует");
        } catch (FileNotFoundException fileNotFoundException) {
            console.printError("Такого файла не существует");
        } catch (IOException e) {
            console.printError("Ошибка ввода вывода");
        }
        finally {
            Console.setFileMode(false);
        }
    }
}





