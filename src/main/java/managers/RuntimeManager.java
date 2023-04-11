package main.java.managers;

import main.java.commandLine.Console;
import main.java.exeptions.CommandRuntimeException;

import main.java.exeptions.ExitException;

import main.java.exeptions.NonexistCommandException;

import main.java.exeptions.WrongArgumentsException;


import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class for work with user input
 */
public class RuntimeManager {
    private final Console console;
    private final CommandManager commandManager;
    public RuntimeManager(Console console, CommandManager commandManager){
        this.console=console;
        this.commandManager=commandManager;

    }

    /**
     * Method for execute command from {@link CommandManager}
     * @param userCommand array[0]-command,array[1]-args
     * @throws NonexistCommandException non exist command
     * @throws ExitException exit from programm
     * @throws WrongArgumentsException wrong arguments
     * @throws CommandRuntimeException command throw mistake during execution
     */

    public void launch(String[] userCommand) throws NonexistCommandException, ExitException, WrongArgumentsException, CommandRuntimeException {
        if (userCommand[0].equals("")) return;
        commandManager.execute(userCommand[0],userCommand[1]);
    }

    public void interactiveMode(){
        Scanner userInputScanner=ScannerManager.getUserInputScanner();
        while (true){
            try{
                if (!userInputScanner.hasNext()) throw new ExitException();
                // Посмотреть это
                String userCommand= userInputScanner.nextLine().trim()+" ";
                this.launch(userCommand.split(" ",2));
                commandManager.addToHistory(userCommand);

            } catch (NoSuchElementException e){
                console.printError("Пользовательский ввод не обнаружен");
            } catch (NonexistCommandException e){
                console.printError("Данная команда отсутствует в списке");
            } catch (WrongArgumentsException e){
                // добавить ещё одно исключение, команда моежт быть без аргуметов или с аргументыми, но принмать определённый тип
                console.printError("Введены неправильные аргументы ");
            } catch (CommandRuntimeException e){
                console.printError("Ошибка во время исполнения");
            } catch (ExitException e){
                console.println("До встречи");
                return;
            }
        }
    }
}
