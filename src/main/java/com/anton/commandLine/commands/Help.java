package com.anton.commandLine.commands;

import com.anton.commandLine.Console;
import com.anton.exeptions.WrongArgumentsException;
import com.anton.managers.CommandManager;


/**
 * Command, that print all available commands and their description
 */
public class Help extends AbstractCommand{
    private CommandManager commandManager;
    private Console console;
    public Help(Console console,CommandManager commandManager){
        super("help",": вывести справку по доступным командам");
        this.commandManager=commandManager;
        this.console=console;
    }
    @Override
    public void execute(String args) throws WrongArgumentsException {
        if (!args.isBlank()) throw new WrongArgumentsException();
        commandManager.getCommands().forEach(command->console.println(command.getName()+command.getDescripion()));

    }

}
