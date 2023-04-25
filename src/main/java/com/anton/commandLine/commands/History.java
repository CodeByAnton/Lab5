package com.anton.commandLine.commands;

import com.anton.commandLine.Console;
import com.anton.exeptions.CommandRuntimeException;
import com.anton.exeptions.ExitException;
import com.anton.exeptions.WrongArgumentsException;
import com.anton.managers.CommandManager;

import java.util.List;

/**
 * Command that print last 14 command, without their arguments
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
