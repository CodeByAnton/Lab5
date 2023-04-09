package main.java.managers;

import main.java.commandLine.Console;
import main.java.exeptions.CommandRuntimeExeption;
import main.java.exeptions.ExitExeption;
import main.java.exeptions.NonexistCommandExeption;
import main.java.exeptions.WrongArgumentsExeption;

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
     * @throws NonexistCommandExeption non exist command
     * @throws ExitExeption exit from programm
     * @throws WrongArgumentsExeption wrong arguments
     * @throws CommandRuntimeExeption command throw mistake during execution
     */

    public void launch(String[] userCommand) throws NonexistCommandExeption,ExitExeption, WrongArgumentsExeption, CommandRuntimeExeption{
        if (userCommand[0].equals("")) return;
        commandManager.execute(userCommand[0],userCommand[1]);
    }

    public void interactiveMode(){
        Scanner userInputScanner=ScannerManager.getUserInputScanner();
        while (true){
            try{
                if (!userInputScanner.hasNext()) throw new ExitExeption();
                // Посмотреть это
                String userCommand= userInputScanner.nextLine().trim()+" ";
                this.launch(userCommand.split("",2));
                commandManager.addToHistory(userCommand);

            } catch (NoSuchElementException e){
                console.printError("Пользовательский ввод не обнаружен");
            } catch (NonexistCommandExeption e){
                console.printError("Данная команда отсутствует в списке");
            } catch (WrongArgumentsExeption e){
                // добавить ещё одно исключение, команда моежт быть без аргуметов или с аргументыми, но принмать определённый тип
                console.printError("Введены неправильные аргументы ");
            } catch (CommandRuntimeExeption e){
                console.printError("Ошибка во время исполнения");
            } catch (ExitExeption e){
                console.println("До встречи");
                return;
            }
        }
    }
}
