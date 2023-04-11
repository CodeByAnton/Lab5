package main.java.commandLine.commands;

import main.java.commandLine.Console;
import main.java.exeptions.CommandRuntimeException;
import main.java.exeptions.ExitException;
import main.java.exeptions.WrongArgumentsException;
import main.java.managers.CommandManager;
import java.util.List;

/**
 * print last 14 command
 */
public class History extends AbstractCommand{
    private CommandManager commandManager;
    private Console console;
    public History(Console console,CommandManager commandManager){
        super("history"," : вывести последние 14 команд без их аргуметов ");
        this.commandManager=commandManager;
        this.console=console;
    }

    @Override
    public void execute(String args) throws ExitException, WrongArgumentsException, CommandRuntimeException {
        if (!args.isBlank()) throw new WrongArgumentsException();
        List<String> history=commandManager.getCommandHistory();
        for (String command:history.subList(Math.max(history.size()-14,0),history.size())){
            console.println(command);
        }
    }
}
